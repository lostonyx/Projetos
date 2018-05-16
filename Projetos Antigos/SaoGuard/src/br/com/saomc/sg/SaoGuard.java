package br.com.saomc.sg;

import br.com.saomc.sg.jdbc.dao.BlockDAO;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import br.com.saomc.sg.jdbc.dao.FlagDAO;
import br.com.saomc.sg.jdbc.dao.PlayerDAO;
import br.com.saomc.sg.jdbc.dao.RegionDAO;
import br.com.saomc.sg.jdbc.dao.model.Flag;
import br.com.saomc.sg.jdbc.dao.model.Region;
import br.com.saomc.sg.listener.CommandSetHomeListener;
import br.com.saomc.sg.listener.SaoGuardCommandExecutor;
import br.com.saomc.sg.util.Config;
import br.com.saomc.sg.util.Messages;
import br.com.saomc.sg.util.Util;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.flags.DefaultFlag;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

public class SaoGuard extends JavaPlugin implements Listener {

	public ConsoleCommandSender console = Bukkit.getConsoleSender();//Console
	public int NewRomam = Font.ROMAN_BASELINE;
        //Prefix
	public final String PrefixYellow = ChatColor.YELLOW.toString();
	public final String PrefixBlue = ChatColor.DARK_AQUA.toString();
	public final String PrefixRed = ChatColor.DARK_RED.toString();
        //Prefix console
	public final String PrefixYellowConsole = ChatColor.GOLD + "[SaoGuard] " + ChatColor.YELLOW;
	public final String PrefixBlueConsole = ChatColor.BLUE + "[SaoGuard] " + ChatColor.DARK_AQUA;
	public final String PrefixRedConsole = ChatColor.RED + "[SaoGuard] " + ChatColor.DARK_RED;
        //Variaveis
	public SgConfig SgConfig;
	public static Permission perms = null;
	public static Economy econ = null;
	public File configFile;
        public Config bl;
	public FileConfiguration config;
        //Instacia do plugin
	public static SaoGuard plugin;
        
        
        public SaoGuard(){
        bl = new Config(this, "blacklist.yml");
        }
        //Instancia
	public static SaoGuard getSaoGuard() {
		return (SaoGuard) Bukkit.getPluginManager().getPlugin("SaoGuard");
	}

        //Manda menssagem
	public void sendMessage(String message) {
		try {
			console.sendMessage(message);
		} catch (Exception e) {
			System.out.println(message);
		}
	}


	@Override
	public void onEnable() {
		plugin = this;
                bl.saveDefaultConfig();
		if (!verificarDependencias()) {
			return;
		}

		configFile = new File(getDataFolder(), "config.yml");
		try {
			if (!configFile.exists()) {
				saveDefaultConfig();
				configFile.getParentFile().mkdirs();
				sendMessage(PrefixBlueConsole + "Config.yml created!");
			}

			// TODO fazer tratamento de cada config com mensagem de erro personalizada

			SgConfig = new SgConfig();
			//Messages.enable(SgConfig.getLanguage());
                        Messages.enable("pt_BR");
		} catch (Exception e) {
			Messages.enable("pt_BR");
			System.out.println(PrefixBlueConsole + Messages.getString("sg.loaded"));
			sendMessage(PrefixBlueConsole + Messages.getString("sg.loaded"));
		}
		if (!setupEconomy()) {
			sendMessage(PrefixRedConsole + "Erro no plugin, verefique se tem Vault/WorldEdit/WorldGuard!");
			getServer().getPluginManager().disablePlugin(this);
			return;
		}

		setupPermissions();
		sendMessage(PrefixYellowConsole + "Plugin foi iniciado!");
		sendMessage(PrefixYellowConsole + "Version: "+plugin.getDescription().getVersion());
		sendMessage(PrefixYellowConsole + "By Wiljafor1.");
                sendMessage(PrefixYellowConsole + "Oi galera do gamersboard, por favor me ajude se tiver algum bug reporte no forum!");
		sendMessage(PrefixYellowConsole + "Bom Uso :)");
                getServer().getPluginManager().registerEvents(new SaoGuardCommandExecutor(SgConfig, plugin, econ, getWorldGuard()), this);
		getServer().getPluginManager().registerEvents(new CommandSetHomeListener(SgConfig), this);
		getCommand("saoguard").setExecutor(new SaoGuardCommandExecutor(SgConfig, plugin, econ, getWorldGuard()));

//		worldGuard = getWorldGuard();


		try {
			PlayerDAO playerDAO = new PlayerDAO(SgConfig);
			RegionDAO regionDAO = new RegionDAO(SgConfig);
			FlagDAO flagDAO = new FlagDAO(SgConfig);
                        BlockDAO blockDAO = new BlockDAO(SgConfig);

			if (SgConfig.getIsMySQL()) {
				playerDAO.createTableMySql();
				regionDAO.createTableMySql();
				flagDAO.createTableMySql();
			} else {
				playerDAO.createTableSqlite();
				regionDAO.createTableSqlite();
				flagDAO.createTableSqlite();
                                blockDAO.createTableSqlite();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		sync();
	}


	@Override
	public void onDisable() {
		sendMessage(PrefixBlueConsole + Messages.getString("sg.plugin_disabled"));
	}


	private Boolean verificarDependencias() {
		if (getServer().getPluginManager().getPlugin("WorldEdit") == null) {
			sendMessage(PrefixRedConsole + "Plugin 'WorldEdit' nao encontrado.");
			Bukkit.getPluginManager().disablePlugin(this);
			return false;
		}

		if (getServer().getPluginManager().getPlugin("WorldGuard") == null) {
			sendMessage(PrefixRedConsole + "Plugin 'WorldGuard' nao encontrado.");
			Bukkit.getPluginManager().disablePlugin(this);
			return false;
		}

		if (getServer().getPluginManager().getPlugin("Vault") == null) {
			sendMessage(PrefixRedConsole + "Plugin 'Vault' nao encontrado.");
			Bukkit.getPluginManager().disablePlugin(this);
			return false;
		}

		return true;
	}


	private boolean setupEconomy() {
		RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
		if (economyProvider != null) {
			econ = economyProvider.getProvider();
		}

		return (econ != null);
	}


	private boolean setupPermissions() {
		RegisteredServiceProvider<Permission> permissionProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
		if (permissionProvider != null) {
			perms = permissionProvider.getProvider();
		}
		return (perms != null);
	}


	public WorldGuardPlugin getWorldGuard() {
		Plugin plugin = getServer().getPluginManager().getPlugin("WorldGuard");
		if (plugin == null || !(plugin instanceof WorldGuardPlugin)) {
			return null;
		}

		return (WorldGuardPlugin) plugin;
	}


	public WorldEditPlugin getWorldEdit() {
		Plugin plugin = getServer().getPluginManager().getPlugin("WorldEdit");
		if (plugin == null || !(plugin instanceof WorldEditPlugin)) {
			return null;
		}
		return (WorldEditPlugin) plugin;
	}


	private Boolean sync() {
		try {
			PlayerDAO playerDAO = new PlayerDAO(SgConfig);
			RegionDAO regionDAO = new RegionDAO(SgConfig);
			FlagDAO flagDAO = new FlagDAO(SgConfig);

			for (World world : plugin.getServer().getWorlds()) {
				RegionManager regionManager = getWorldGuard().getGlobalRegionManager().get(world);
				Map<String, ProtectedRegion> regions = regionManager.getRegions();

				//console.sendMessage(PrefixYellowConsole + world.getName());

				for (String regionfullName : regions.keySet()) {
					if (StringUtils.countMatches(regionfullName, "_") == 1) {
						ProtectedRegion protectedRegion = regions.get(regionfullName);
						//console.sendMessage(PrefixYellowConsole + regionfullName);
						String[] str = regionfullName.split("_");
						String playerName = str[0];
						String regionName = str[1];

						br.com.saomc.sg.jdbc.dao.model.Player owner = playerDAO.findByName(playerName);

						if (Util.empty(owner.getId())) {
							owner.setName(playerName);
							playerDAO.insert(owner);
							owner = playerDAO.findByName(playerName);
						}
						//console.sendMessage(PrefixBlueConsole + owner);

						Region region = new Region();

						region.setOwner(owner);
						region.setName(regionName);
						region.setFullName(regionfullName);
						region.setWorld(world.getName());
						region.setInitialPositionX(protectedRegion.getMinimumPoint().getBlockX());
						region.setInitialPositionY(protectedRegion.getMinimumPoint().getBlockY());
						region.setInitialPositionZ(protectedRegion.getMinimumPoint().getBlockZ());
						region.setFinalPositionX(protectedRegion.getMaximumPoint().getBlockX());
						region.setFinalPositionY(protectedRegion.getMaximumPoint().getBlockY());
						region.setFinalPositionZ(protectedRegion.getMaximumPoint().getBlockZ());

						Integer regionId = regionDAO.findIdByRegion(region);
						if (regionId == 0) {
							regionDAO.insert(region);
							region.setId(regionDAO.findIdByRegion(region));

							// for complicado necessario para conseguir o nome da flag e seu valor. Tirado diretamente do codigo do worldguard
							for (com.sk89q.worldguard.protection.flags.Flag<?> defaultFlags : DefaultFlag.getFlags()) {
								Object flag = protectedRegion.getFlag(defaultFlags);

								if (Util.empty(flag)) {
									continue;
								}

								Flag hgFlag = new Flag();
								hgFlag.setName(defaultFlags.getName());
								hgFlag.setValue(String.valueOf(flag));
								hgFlag.setRegion(region);

								flagDAO.insert(hgFlag);
							}
						}

						//console.sendMessage(PrefixBlueConsole + region);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}

}