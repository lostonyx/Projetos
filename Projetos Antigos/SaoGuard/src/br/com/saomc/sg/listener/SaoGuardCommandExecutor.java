package br.com.saomc.sg.listener;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.milkbowl.vault.economy.Economy;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.block.Sign;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.inventory.ItemStack;

import br.com.saomc.sg.SgConfig;
import br.com.saomc.sg.SgPermissions;
import br.com.saomc.sg.SaoGuard;
import static br.com.saomc.sg.SaoGuard.plugin;
import br.com.saomc.sg.jdbc.dao.BackupDAO;
import br.com.saomc.sg.jdbc.dao.BlockDAO;
import br.com.saomc.sg.jdbc.dao.ChestDAO;
import br.com.saomc.sg.jdbc.dao.EnchantmentDAO;
import br.com.saomc.sg.jdbc.dao.EntityDAO;
import br.com.saomc.sg.jdbc.dao.FlagDAO;
import br.com.saomc.sg.jdbc.dao.ItemDAO;
import br.com.saomc.sg.jdbc.dao.PlayerDAO;
import br.com.saomc.sg.jdbc.dao.RegionDAO;
import br.com.saomc.sg.jdbc.dao.SignDAO;
import br.com.saomc.sg.jdbc.dao.model.Backup;
import br.com.saomc.sg.jdbc.dao.model.Flag;
import br.com.saomc.sg.jdbc.dao.model.Item;
import br.com.saomc.sg.jdbc.dao.model.Region;
import br.com.saomc.sg.util.CItem;
import br.com.saomc.sg.util.Messages;
import br.com.saomc.sg.util.Util;
import com.sk89q.worldedit.BlockVector;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.bukkit.RegionContainer;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.domains.DefaultDomain;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.DefaultFlag;
import com.sk89q.worldguard.protection.flags.InvalidFlagFormat;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.managers.storage.StorageException;
import com.sk89q.worldguard.protection.regions.ProtectedCuboidRegion;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class SaoGuardCommandExecutor implements CommandExecutor,Listener  {

	public ConsoleCommandSender console = Bukkit.getConsoleSender();

	public final String PrefixYellow = ChatColor.YELLOW.toString();
	public final String PrefixBlue = ChatColor.DARK_AQUA.toString();
	public final String PrefixRed = ChatColor.DARK_RED.toString();

	public final String PrefixYellowConsole = ChatColor.GOLD + "[SaoGuard] " + ChatColor.YELLOW;
	public final String PrefixBlueConsole = ChatColor.BLUE + "[SaoGuard] " + ChatColor.DARK_AQUA;
	public final String PrefixRedConsole = ChatColor.RED + "[SaoGuard] " + ChatColor.DARK_RED;

	private String PrefixCommand;
	private WorldGuardPlugin worldGuard;
	public SgConfig sgConfig;

	private SaoGuard plugin;
	private Economy econ = null;

	private final String STATUS_SALE = "sale";
	private final String STATUS_RENT = "rent";
	private final Integer maxLengthRegionName = 16;

	private final Integer TOTAL_BLOCKS_PER_ROW = 10000;


	public SaoGuardCommandExecutor(SgConfig hgConfig, SaoGuard plugin, Economy econ, WorldGuardPlugin worldGuard) {
		super();
		this.sgConfig = hgConfig;
		this.plugin = plugin;
		PrefixCommand = ChatColor.AQUA + hgConfig.getCommandName() + " " + ChatColor.GREEN;
		this.econ = econ;
		this.worldGuard = worldGuard;
	}

    @EventHandler
    public void Interações(InventoryClickEvent event){
        if(plugin.getConfig().getBoolean("menugui")==true){
        Player player = (Player)event.getWhoClicked(); 
        RegionContainer container = plugin.getWorldGuard().getRegionContainer();
        RegionManager rm = container.get(player.getWorld());
        ApplicableRegionSet set = rm.getApplicableRegions(player.getLocation());
        set.toString().toLowerCase();
	String id = set.iterator().next().getId();
        if(event.getInventory().getTitle().equalsIgnoreCase(""+rm.getRegion(id).getId().toString())){
        event.setCancelled(true);
        }
        if(event.getInventory().getTitle().equalsIgnoreCase("§7§lDiario - Ver")){//mexer
        event.setCancelled(true);    
        }
        if(event.getCurrentItem() != null){
            if(event.getCurrentItem().getType() != Material.AIR){
                if(event.getCurrentItem().hasItemMeta()){
                    String nome = player.getName();
                    if(event.getInventory().getTitle().equalsIgnoreCase("Deletar Terreno?")){
                    if((event.getCurrentItem().getItemMeta().getDisplayName().contains("§2Sim"))){
                    event.setCancelled(true);
                    if(event.getWhoClicked() instanceof Player) {
                    player.closeInventory();
                    String[] arg = new String[4];
                    arg[0] = "del";
                    arg[1] = rm.getRegion(id).getId().toString().replace(""+player.getName().toLowerCase()+"_", "");
                    delGui(player, rm.getRegion(id).getId().toString().replace(""+player.getName().toLowerCase()+"_", ""));
                    }  
                    }
                    if((event.getCurrentItem().getItemMeta().getDisplayName().contains("§4Nao"))){
                    event.setCancelled(true);
                    if(event.getWhoClicked() instanceof Player) {
                    player.closeInventory();
                    }  
                    }
                    }
                    
                    }
                    }
                    }
                }
        else{
            return;
        }
    }
        
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(PrefixRed + Messages.getString("sg.player"));
			return true;
		}
		Player player = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("saoguard")) {
                    //player.sendMessage(sgConfig.getWorldPossivel());
                    //player.sendMessage(player.getWorld().getName().toString());
                    if(player.getWorld().getName().toString().equalsIgnoreCase(sgConfig.getWorldPossivel())){
			if (args.length == 0) {//args.length == 0
				player.sendMessage(PrefixYellow + Messages.getString("sg.help_title", 1));
//				player.sendMessage("    ");

				if (player.hasPermission(SgPermissions.build) || player.isOp() || player.hasPermission(SgPermissions.all)) {
					player.sendMessage(PrefixCommand + Messages.getString("sg.build_text"));
					player.sendMessage(ChatColor.WHITE + Messages.getString("sg.build_menu"));
				}

				if (player.hasPermission(SgPermissions.buy) || player.isOp() || player.hasPermission(SgPermissions.all)) {
					player.sendMessage(PrefixCommand + Messages.getString("sg.buy_text"));
					player.sendMessage(ChatColor.WHITE + Messages.getString("sg.buy_menu"));
				}

				if (player.hasPermission(SgPermissions.sell) || player.isOp() || player.hasPermission(SgPermissions.all)) {
					player.sendMessage(PrefixCommand + Messages.getString("sg.sell_text"));
					player.sendMessage(ChatColor.WHITE + Messages.getString("sg.sell_menu"));
				}

				if (player.hasPermission(SgPermissions.expand) || player.isOp() || player.hasPermission(SgPermissions.all)) {
					player.sendMessage(PrefixCommand + Messages.getString("sg.expand_text"));
					player.sendMessage(ChatColor.WHITE + Messages.getString("sg.expand_menu"));
				}

				if (player.hasPermission(SgPermissions.del) || player.isOp() || player.hasPermission(SgPermissions.all)) {
					player.sendMessage(PrefixCommand + Messages.getString("sg.del_text"));
					player.sendMessage(ChatColor.WHITE + Messages.getString("sg.del_menu"));
				}

				if (player.hasPermission(SgPermissions.addfriend) || player.isOp() || player.hasPermission(SgPermissions.all)) {
					player.sendMessage(PrefixCommand + Messages.getString("sg.add_friend_text"));
					player.sendMessage(ChatColor.WHITE + Messages.getString("sg.add_friend_menu"));
				}

				if (player.hasPermission(SgPermissions.delfriend) || player.isOp() || player.hasPermission(SgPermissions.all)) {
					player.sendMessage(PrefixCommand + Messages.getString("sg.del_friend_text"));
					player.sendMessage(ChatColor.WHITE + Messages.getString("sg.del_friend_menu"));
				}


				player.sendMessage(PrefixYellow + Messages.getString("sg.optional"));

				return true;
			} else if (args[0].equalsIgnoreCase("2")) {//
				player.sendMessage(PrefixYellow + Messages.getString("sg.help_title", 2));
//				player.sendMessage("    ");

				if (player.hasPermission(SgPermissions.pvp) || player.isOp() || player.hasPermission(SgPermissions.all)) {
					player.sendMessage(PrefixCommand + Messages.getString("sg.pvp_text"));
					player.sendMessage(ChatColor.WHITE + Messages.getString("sg.pvp_menu"));
				}

				if (player.hasPermission(SgPermissions.msg) || player.isOp() || player.hasPermission(SgPermissions.all)) {
					//player.sendMessage(PrefixCommand + Messages.getString("sg.msg_text"));
					//player.sendMessage(ChatColor.WHITE + Messages.getString("sg.msg_menu"));
				}

				if (player.hasPermission(SgPermissions.list) || player.isOp() || player.hasPermission(SgPermissions.all)) {
					player.sendMessage(PrefixCommand + Messages.getString("sg.list_text"));
					player.sendMessage(ChatColor.WHITE + Messages.getString("sg.list_menu"));
				}

				if (player.hasPermission(SgPermissions.info) || player.isOp() || player.hasPermission(SgPermissions.all)) {
					player.sendMessage(PrefixCommand + Messages.getString("sg.info_text"));
					player.sendMessage(ChatColor.WHITE + Messages.getString("sg.info_menu"));
				}

				if (player.hasPermission(SgPermissions.prices) || player.isOp() || player.hasPermission(SgPermissions.all)) {
					//player.sendMessage(PrefixCommand + Messages.getString("sg.prices_text"));
					//player.sendMessage(ChatColor.WHITE + Messages.getString("sg.prices_menu"));
				}

				if (player.hasPermission(SgPermissions.tp) || player.isOp() || player.hasPermission(SgPermissions.all)) {
					player.sendMessage(PrefixCommand + Messages.getString("sg.tp_text"));
					player.sendMessage(ChatColor.WHITE + Messages.getString("sg.tp_menu"));
				}

				if (player.hasPermission(SgPermissions.admin) || player.isOp()) {
					player.sendMessage(PrefixCommand + Messages.getString("sg.admin_text"));
					player.sendMessage(ChatColor.WHITE + Messages.getString("sg.admin_menu"));
				}

				player.sendMessage(PrefixYellow + Messages.getString("sg.optional"));

				return true;

			} else if (args[0].equalsIgnoreCase(Messages.getString("sg.command.admin"))) {

				if (player.isOp() || player.hasPermission(SgPermissions.all) || player.hasPermission(SgPermissions.admin)) {
					return admin(player);
				} else {
					player.sendMessage(PrefixRed + Messages.getString("sg.permission_denied"));
				}

			} else if (args[0].equalsIgnoreCase(Messages.getString("sg.command.build"))) {

				if (player.isOp() || player.hasPermission(SgPermissions.all) || player.hasPermission(SgPermissions.build)) {
                                    //player.sendMessage(""+checarport(player));
                                    if(checarport(player) == false){
                                        player.sendMessage(PrefixRed + Messages.getString("sg.erro_prot"));   
                                    }
                                    else{
                                        return build(player, args);  
                                    }
				} else {
					player.sendMessage(PrefixRed + Messages.getString("sg.permission_denied"));
				}

			} else if (args[0].equalsIgnoreCase(Messages.getString("sg.command.buy"))) {

				if (player.isOp() || player.hasPermission(SgPermissions.all) || player.hasPermission(SgPermissions.buy)) {
					return buy(player, args);
				} else {
					player.sendMessage(PrefixRed + Messages.getString("sg.permission_denied"));
				}

			} else if (args[0].equalsIgnoreCase(Messages.getString("sg.command.sell"))) {

				if (player.isOp() || player.hasPermission(SgPermissions.all) || player.hasPermission(SgPermissions.sell)) {
					return sell(player, args);
				} else {
					player.sendMessage(PrefixRed + Messages.getString("sg.permission_denied"));
				}

			} else if (args[0].equalsIgnoreCase(Messages.getString("sg.command.expand"))) {

				if (player.isOp() || player.hasPermission(SgPermissions.all) || player.hasPermission(SgPermissions.expand)) {
					return expand(player, args);
				} else {
					player.sendMessage(PrefixRed + Messages.getString("sg.permission_denied"));
				}

			} else if (args[0].equalsIgnoreCase(Messages.getString("sg.command.pvp"))) {

				if (player.isOp() || player.hasPermission(SgPermissions.all) || player.hasPermission(SgPermissions.pvp)) {
					return pvp(sender, player, args);
				} else {
					player.sendMessage(PrefixRed + ChatColor.RED + Messages.getString("sg.permission_denied"));
				}

			} else if (args[0].equalsIgnoreCase(Messages.getString("sg.command.del"))) {

				if (player.isOp() || player.hasPermission(SgPermissions.all) || player.hasPermission(SgPermissions.del)) {
					return del(player, args);
				} else {
					player.sendMessage(PrefixRed + Messages.getString("sg.permission_denied"));
				}

			} else if (args[0].equalsIgnoreCase(Messages.getString("sg.command.addfriend"))) {

				if (player.isOp() || player.hasPermission(SgPermissions.all) || player.hasPermission(SgPermissions.addfriend)) {
					return addFriend(player, args);
				} else {
					player.sendMessage(PrefixRed + Messages.getString("sg.permission_denied"));
				}

			} else if (args[0].equalsIgnoreCase(Messages.getString("sg.command.delfriend"))) {

				if (player.isOp() || player.hasPermission(SgPermissions.all) || player.hasPermission(SgPermissions.delfriend)) {
					return delFriend(player, args);
				} else {
					player.sendMessage(PrefixRed + Messages.getString("sg.permission_denied"));
				}

			} else if (args[0].equalsIgnoreCase(Messages.getString("sg.command.info"))) {

				if (player.isOp() || player.hasPermission(SgPermissions.all) || player.hasPermission(SgPermissions.info)) {
					return info(player);
				} else {
					player.sendMessage(PrefixRed + Messages.getString("sg.permission_denied"));
				}

			} else if (args[0].equalsIgnoreCase(Messages.getString("sg.command.list"))) {

				if (player.isOp() || player.hasPermission(SgPermissions.all) || player.hasPermission(SgPermissions.list)) {
					return list(player, args);
				} else {
					player.sendMessage(PrefixRed + Messages.getString("sg.permission_denied"));
				}

			} else if (args[0].equalsIgnoreCase(Messages.getString("sg.command.prices"))) {

				if (player.isOp() || player.hasPermission(SgPermissions.all) || player.hasPermission(SgPermissions.prices)) {
					return prices(player);
				} else {
					player.sendMessage(PrefixRed + Messages.getString("sg.permission_denied"));
				}

			} else if (args[0].equalsIgnoreCase(Messages.getString("sg.command.tp"))) {

				if (player.isOp() || player.hasPermission(SgPermissions.all) || player.hasPermission(SgPermissions.tp)) {
					return tp(player, args);
				} else {
					player.sendMessage(PrefixRed + Messages.getString("sg.permission_denied"));
				}

			} else if (args[0].equalsIgnoreCase(Messages.getString("sg.command.sync"))) {

				if (player.isOp()) {
					//return sync(player);
                                        player.sendMessage(PrefixRed + "Funcao desativada por equanto!");
				} else {
					player.sendMessage(PrefixRed + Messages.getString("sg.permission_denied"));
				}

			} else if (args[0].equalsIgnoreCase(Messages.getString("sg.command.prot"))) {

				if (player.isOp()) {
					//return sync(player);
                                        if (args.length != 2) {
                                        player.sendMessage(PrefixYellow + PrefixCommand + Messages.getString("sg.prot_text"));
                                        return true;
                                        }
                                        if(args[1].equalsIgnoreCase("pos1")){
                                            setarpos(player, "pos1");
                                        }
                                        if(args[1].equalsIgnoreCase("pos2")){
                                            setarpos(player, "pos2");    
                                        }
				} else {
					player.sendMessage(PrefixRed + Messages.getString("sg.permission_denied"));
				}

			}else if (args[0].equalsIgnoreCase(Messages.getString("sg.command.block"))) {

				if (player.isOp() || player.hasPermission(SgPermissions.all) || player.hasPermission(SgPermissions.block)) {
					//return block(player, args);
				} else {
					player.sendMessage(PrefixRed + Messages.getString("sg.permission_denied"));
				}

			} else {
				player.sendMessage(PrefixRed + Messages.getString("sg.command_not_exist"));
			}
                        
                    }
                    else{
                        player.sendMessage(PrefixRed + Messages.getString("sg.not_world"));    
                    }

		}

		return false;
	}


	private Boolean admin(Player player) {
		player.sendMessage(PrefixCommand + Messages.getString("sg.admin.list_text"));
		player.sendMessage(ChatColor.WHITE + Messages.getString("sg.admin.list_menu"));

		player.sendMessage(PrefixCommand + Messages.getString("sg.admin.tp_text"));
		player.sendMessage(ChatColor.WHITE + Messages.getString("sg.admin.tp_menu"));

		player.sendMessage(PrefixCommand + Messages.getString("sg.sync_text"));
		player.sendMessage(ChatColor.WHITE + Messages.getString("sg.sync_menu"));
                
		player.sendMessage(PrefixCommand + Messages.getString("sg.prot_text"));
		player.sendMessage(ChatColor.WHITE + Messages.getString("sg.prot_menu"));

//		player.sendMessage(PrefixCommand + Messages.getString("sg.block_text"));
//		player.sendMessage(ChatColor.WHITE + Messages.getString("sg.block_menu"));

		return true;

	}
        
	public Boolean checarport(Player player) {
            Boolean okay = null;
            if(plugin.getConfig().getBoolean("protspawn")==true){
                Double x = player.getLocation().getX();
                Double z = player.getLocation().getZ();
                Double x1 = plugin.getConfig().getDouble("protloc.pos1.X")+51.0;
                Double x2 = plugin.getConfig().getDouble("protloc.pos2.X")-51.0;
                Double z1 = plugin.getConfig().getDouble("protloc.pos1.Z")+51.0;
                Double z2 = plugin.getConfig().getDouble("protloc.pos2.Z")-51.0;
                if((x1 > x) && (z1 > z)){
                    if((x > x2) && (z > z2)){
                    okay = false;  
                    player.sendMessage(PrefixRed + Messages.getString("sg.erro_prot_ande").replace("%X%", ""+x1.intValue()).replace("%Z%", ""+z1.intValue()));   
                    }
                    else{
                        okay = true;
                        //player.sendMessage("false");
                    }
                    
                }
                else{
                    //player.sendMessage(sgConfig.toString());
                    //player.sendMessage("n total");
                    okay = true;
                }
            }
            else{
                //player.sendMessage("aii...");
                okay = true;    
            }
            return okay;
        }
        
	private Boolean setarpos(Player player, String pos) {
		if(pos.equalsIgnoreCase("pos1")){
                        plugin.getConfig().set("protloc.pos1", player.getWorld().getName());
                        plugin.getConfig().set("protloc.pos1.X", Double.valueOf(player.getLocation().getX()));
                        plugin.getConfig().set("protloc.pos1.Z", Double.valueOf(player.getLocation().getZ()));
                        player.sendMessage("§bPos1 §bsetada com sucesso!");
                        plugin.saveConfig();
                        return true;   
                }
                if(pos.equalsIgnoreCase("pos2")){
                        plugin.getConfig().set("protloc.pos2", player.getWorld().getName());
                        plugin.getConfig().set("protloc.pos2.X", Double.valueOf(player.getLocation().getX()));
                        plugin.getConfig().set("protloc.pos2.Z", Double.valueOf(player.getLocation().getZ()));
                        player.sendMessage("§bPos2 §bsetada com sucesso!");
                        plugin.saveConfig();
                        return true;     
                }
		return true;
	}

	private Boolean build(Player player, String[] args) {

		String regionName;
		Integer size;

		try {
			PlayerDAO playerDAO = new PlayerDAO(sgConfig);
			RegionDAO regionDAO = new RegionDAO(sgConfig);

			br.com.saomc.sg.jdbc.dao.model.Player playerBd = playerDAO.findByName(player.getName());

			if (Util.empty(playerBd.getId())) {
				playerBd.setName(player.getName());
				playerDAO.insert(playerBd);
			} else {
                                
				// Validations
				if (!player.isOp() && !player.hasPermission(SgPermissions.admin)) {
					Integer totalRegions = regionDAO.countRegionByPlayer(playerBd);
					Boolean possuiPermissaoExtra = false;

					// TODO verificar permissao de uma maneira melhor
					for (int i = 1; i < 50; i++) {
						if (player.hasPermission(SgPermissions.x + i)) {
							possuiPermissaoExtra = true;
							if (totalRegions >= i) {
								player.sendMessage(PrefixRed + Messages.getString("sg.max_regions", i));
								return true;
							} else {
								break;
							}
						}
					}

					if (!possuiPermissaoExtra && totalRegions >= sgConfig.getMaxAreas()) {
						player.sendMessage(PrefixRed + Messages.getString("sg.max_regions", sgConfig.getMaxAreas()));
						return true;
					}
				}

			}

		} catch (SQLException e1) {
			// alguma coisa deu errado ao tentar comprar a regiao
			e1.printStackTrace();
		}

		if (args.length != 3) {
			player.sendMessage(PrefixYellow + PrefixCommand + Messages.getString("sg.build_text"));
			return true;
		}

		regionName = args[1];

		if (regionName.length() > maxLengthRegionName) {
			player.sendMessage(PrefixRed + Messages.getString("sg.max_digits", maxLengthRegionName));
			return true;
		}

		try {
			size = Integer.parseInt(args[2]);

			if ((size < sgConfig.getMinSize() || size > sgConfig.getMaxSize()) && !player.isOp()) {
				player.sendMessage(PrefixRed + Messages.getString("sg.min_and_max", sgConfig.getMinSize(), sgConfig.getMaxSize()));
				return true;
			}
		} catch (NumberFormatException e) {
			player.sendMessage(PrefixRed + Messages.getString("sg.invalid_size"));
			return true;
		}

		Boolean noFence = false;

		try {
			if ("nofence".equalsIgnoreCase(args[3]) || "semcerca".equalsIgnoreCase(args[3])) {
				noFence = true;
			}
		} catch (Exception e) {
		}
                
                if(plugin.getConfig().getBoolean("buildper") == true){
                if(player.hasPermission("saoguard.tamanho.10")){
                    if(size <= 10){
                    construir(player, size, size, regionName, noFence);    
                    }
                    else{
                    player.sendMessage(PrefixRed + Messages.getString("sg.invalid_size_per"));
                    }
                }
                else if (player.hasPermission("saoguard.tamanho.20")){
                    if(size < 20){
                    construir(player, size, size, regionName, noFence);    
                    }
                    else{
                    player.sendMessage(PrefixRed + Messages.getString("sg.invalid_size_per"));
                    }    
                }
                else if(player.hasPermission("saoguard.tamanho.30")){
                    if(size < 30){
                    construir(player, size, size, regionName, noFence);    
                    }
                    else{
                    player.sendMessage(PrefixRed + Messages.getString("sg.invalid_size_per"));
                    }      
                }
                else if(player.hasPermission("saoguard.tamanho.40")){
                    if(size < 40){
                    construir(player, size, size, regionName, noFence);    
                    }
                    else{
                    player.sendMessage(PrefixRed + Messages.getString("sg.invalid_size_per"));
                    }      
                }
                else if(player.hasPermission("saoguard.tamanho.50")){
                    if(size < 50){
                    construir(player, size, size, regionName, noFence);    
                    }
                    else{
                    player.sendMessage(PrefixRed + Messages.getString("sg.invalid_size_per"));
                    }      
                }
                else if(player.hasPermission("saoguard.tamanho.60")){
                    if(size < 60){
                    construir(player, size, size, regionName, noFence);    
                    }
                    else{
                    player.sendMessage(PrefixRed + Messages.getString("sg.invalid_size_per"));
                    }      
                }
                else if(player.hasPermission("saoguard.tamanho.70")){
                    if(size < 70){
                    construir(player, size, size, regionName, noFence);    
                    }
                    else{
                    player.sendMessage(PrefixRed + Messages.getString("sg.invalid_size_per"));
                    }      
                }
                else if(player.hasPermission("saoguard.tamanho.80")){
                    if(size < 80){
                    construir(player, size, size, regionName, noFence);    
                    }
                    else{
                    player.sendMessage(PrefixRed + Messages.getString("sg.invalid_size_per"));
                    }     
                }
                else if(player.hasPermission("saoguard.tamanho.90")){
                    if(size < 90){
                    construir(player, size, size, regionName, noFence);    
                    }
                    else{
                    player.sendMessage(PrefixRed + Messages.getString("sg.invalid_size_per"));
                    }        
                }
                else if(player.hasPermission("saoguard.tamanho.100")){
                    if(size < 100){
                    construir(player, size, size, regionName, noFence);    
                    }
                    else{
                    player.sendMessage(PrefixRed + Messages.getString("sg.invalid_size_per"));
                    }    
                }  
                }
                else{
                construir(player, size, size, regionName, noFence);        
                }
                
		return true;

	}


	private Boolean buy(Player player, String[] args) {

		String regionName;
		String oldOwnerName;
		String newOwnerName = player.getName().toLowerCase();

		if (args.length != 3) {
			player.sendMessage(PrefixYellow + PrefixCommand + Messages.getString("sg.buy_text"));
			return true;
		}

		try {
			regionName = args[1];
			oldOwnerName = args[2].toLowerCase();
		} catch (Exception e) {
			player.sendMessage(PrefixYellow + PrefixCommand + Messages.getString("sg.buy_text"));
			return true;
		}

		try {
			PlayerDAO playerDAO = new PlayerDAO(sgConfig);
			RegionDAO regionDAO = new RegionDAO(sgConfig);

			br.com.saomc.sg.jdbc.dao.model.Player oldOwner = playerDAO.findByName(oldOwnerName);
			br.com.saomc.sg.jdbc.dao.model.Player newOwner = playerDAO.findByName(newOwnerName);

			if (Util.empty(oldOwner.getName())) {
				player.sendMessage(PrefixRed + Messages.getString("sg.player_never_build", oldOwnerName));
			} else {
				if (Util.empty(newOwner.getName())) {
					newOwner.setName(newOwnerName);
					playerDAO.insert(newOwner);
					newOwner = playerDAO.findByName(newOwnerName);
				}

				Region region = regionDAO.findByOwnerAndName(oldOwner, regionName);

				if (Util.empty(region) || Util.empty(region.getStatus())) {
					player.sendMessage(PrefixRed + Messages.getString("sg.area_not_found", regionName));
				} else {

					if (oldOwnerName.equalsIgnoreCase(newOwnerName)) {
						player.sendMessage(PrefixRed + Messages.getString("sg.region_same_owner"));
						return true;
					}
                                        Region regiond = regionDAO.findByOwnerAndName(newOwner, regionName) ;
                                        player.sendMessage(""+regiond.getName());
                                        player.sendMessage(""+region.getName());
                                        if(regiond.getName() == null){
					// Validations
					if (!player.isOp()) {
						Integer totalRegions = regionDAO.countRegionByPlayer(newOwner);
						Boolean possuiPermissaoExtra = false;

						// TODO verificar permissao de uma maneira melhor
						for (int i = 1; i < 50; i++) {
							if (player.hasPermission(SgPermissions.x + i)) {
								possuiPermissaoExtra = true;
								if (totalRegions >= i) {
									player.sendMessage(PrefixRed + Messages.getString("sg.max_regions", i));
									return true;
								} else {
									break;
								}
							}
						}

						if (!possuiPermissaoExtra && totalRegions >= sgConfig.getMaxAreas()) {
							player.sendMessage(PrefixRed + Messages.getString("sg.max_regions", sgConfig.getMaxAreas()));
							return true;
						}
					}

					if (econ.getBalance(newOwner.getName()) < region.getSellingPrice()) {
						player.sendMessage(PrefixRed + Messages.getString("sg.enough_money", sgConfig.getCoinName() + region.getSellingPrice()));
						return true;
					}

					RegionManager regionManager = null;
					for (World world : worldGuard.getServer().getWorlds()) {
						if (world.getName().equalsIgnoreCase(region.getWorld())) {
							regionManager = worldGuard.getGlobalRegionManager().get(world);
							break;
						}

					}

					ProtectedRegion protectedRegion = regionManager.getRegion(region.getFullName());
					for (String playerName : protectedRegion.getOwners().getPlayers()) {
						protectedRegion.getOwners().removePlayer(playerName);
					}
					protectedRegion.getOwners().addPlayer(player.getName());

					regionManager.removeRegion(region.getFullName());
					regionManager.addRegion(protectedRegion);
					regionManager.save();

					String oldRegionFullName = oldOwnerName + "_" + region.getName();
					String newRegionFullName = newOwnerName + "_" + region.getName();

					renameRegion(regionManager, region, oldRegionFullName, newRegionFullName);

					//region.setName(region.getName() + oldOwnerName);
                                        region.setName(region.getName());
					region.setFullName(newRegionFullName);

					econ.withdrawPlayer(newOwner.getName(), region.getSellingPrice());
					econ.depositPlayer(oldOwner.getName(), region.getSellingPrice());

					Integer price = region.getSellingPrice();

					region.setOwner(newOwner);
					region.setStatus("");
					region.setSellingPrice(0);
					regionDAO.update(region);

					player.sendMessage(PrefixYellow + Messages.getString("sg.buy_are_sucess", sgConfig.getCoinName() + price));

                                        }
                                        else{
                                        if (regiond.getName().equalsIgnoreCase(region.getName())) {
					// Validations
					if (!player.isOp()) {
						Integer totalRegions = regionDAO.countRegionByPlayer(newOwner);
						Boolean possuiPermissaoExtra = false;

						// TODO verificar permissao de uma maneira melhor
						for (int i = 1; i < 50; i++) {
							if (player.hasPermission(SgPermissions.x + i)) {
								possuiPermissaoExtra = true;
								if (totalRegions >= i) {
									player.sendMessage(PrefixRed + Messages.getString("sg.max_regions", i));
									return true;
								} else {
									break;
								}
							}
						}

						if (!possuiPermissaoExtra && totalRegions >= sgConfig.getMaxAreas()) {
							player.sendMessage(PrefixRed + Messages.getString("sg.max_regions", sgConfig.getMaxAreas()));
							return true;
						}
					}

					if (econ.getBalance(newOwner.getName()) < region.getSellingPrice()) {
						player.sendMessage(PrefixRed + Messages.getString("sg.enough_money", sgConfig.getCoinName() + region.getSellingPrice()));
						return true;
					}

					RegionManager regionManager = null;
					for (World world : worldGuard.getServer().getWorlds()) {
						if (world.getName().equalsIgnoreCase(region.getWorld())) {
							regionManager = worldGuard.getGlobalRegionManager().get(world);
							break;
						}

					}

					ProtectedRegion protectedRegion = regionManager.getRegion(region.getFullName());
					for (String playerName : protectedRegion.getOwners().getPlayers()) {
						protectedRegion.getOwners().removePlayer(playerName);
					}
					protectedRegion.getOwners().addPlayer(player.getName());

					regionManager.removeRegion(region.getFullName());
					regionManager.addRegion(protectedRegion);
					regionManager.save();

					String oldRegionFullName = oldOwnerName + "_" + region.getName();
					String newRegionFullName = newOwnerName + "_" + region.getName()+"1";

					renameRegion(regionManager, region, oldRegionFullName, newRegionFullName);

					//region.setName(region.getName() + oldOwnerName);
                                        region.setName(region.getName()+"1");
					region.setFullName(newRegionFullName);

					econ.withdrawPlayer(newOwner.getName(), region.getSellingPrice());
					econ.depositPlayer(oldOwner.getName(), region.getSellingPrice());

					Integer price = region.getSellingPrice();

					region.setOwner(newOwner);
					region.setStatus("");
					region.setSellingPrice(0);
					regionDAO.update(region);

					player.sendMessage(PrefixYellow + Messages.getString("sg.buy_are_sucess", sgConfig.getCoinName() + price));

                                        }
                                        else{//NomeDoPLayer_NomeDaArea
					// Validations
					if (!player.isOp()) {
						Integer totalRegions = regionDAO.countRegionByPlayer(newOwner);
						Boolean possuiPermissaoExtra = false;

						// TODO verificar permissao de uma maneira melhor
						for (int i = 1; i < 50; i++) {
							if (player.hasPermission(SgPermissions.x + i)) {
								possuiPermissaoExtra = true;
								if (totalRegions >= i) {
									player.sendMessage(PrefixRed + Messages.getString("sg.max_regions", i));
									return true;
								} else {
									break;
								}
							}
						}

						if (!possuiPermissaoExtra && totalRegions >= sgConfig.getMaxAreas()) {
							player.sendMessage(PrefixRed + Messages.getString("sg.max_regions", sgConfig.getMaxAreas()));
							return true;
						}
					}

					if (econ.getBalance(newOwner.getName()) < region.getSellingPrice()) {
						player.sendMessage(PrefixRed + Messages.getString("sg.enough_money", sgConfig.getCoinName() + region.getSellingPrice()));
						return true;
					}

					RegionManager regionManager = null;
					for (World world : worldGuard.getServer().getWorlds()) {
						if (world.getName().equalsIgnoreCase(region.getWorld())) {
							regionManager = worldGuard.getGlobalRegionManager().get(world);
							break;
						}

					}

					ProtectedRegion protectedRegion = regionManager.getRegion(region.getFullName());
					for (String playerName : protectedRegion.getOwners().getPlayers()) {
						protectedRegion.getOwners().removePlayer(playerName);
					}
					protectedRegion.getOwners().addPlayer(player.getName());

					regionManager.removeRegion(region.getFullName());
					regionManager.addRegion(protectedRegion);
					regionManager.save();

					String oldRegionFullName = oldOwnerName + "_" + region.getName();
					String newRegionFullName = newOwnerName + "_" + region.getName();

					renameRegion(regionManager, region, oldRegionFullName, newRegionFullName);

					//region.setName(region.getName() + oldOwnerName);
                                        region.setName(region.getName());
					region.setFullName(newRegionFullName);

					econ.withdrawPlayer(newOwner.getName(), region.getSellingPrice());
					econ.depositPlayer(oldOwner.getName(), region.getSellingPrice());

					Integer price = region.getSellingPrice();

					region.setOwner(newOwner);
					region.setStatus("");
					region.setSellingPrice(0);
					regionDAO.update(region);

					player.sendMessage(PrefixYellow + Messages.getString("sg.buy_are_sucess", sgConfig.getCoinName() + price));

                                        }
                                        }


				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;

	}

        //ajeitar bug dupe de nome
	private Boolean sell(Player player, String[] args) {

		if (args.length != 3) {
			player.sendMessage(PrefixYellow + PrefixCommand + Messages.getString("sg.sell_text"));
			return true;
		}

		String regionName;
		Double price = 0.0;
		Boolean cancel = false;

		try {
			regionName = args[1];

			if ("cancelar".equalsIgnoreCase(args[2])) {
				cancel = true;
			} else {
				price = Double.parseDouble(args[2]);
			}
		} catch (Exception e) {
			player.sendMessage(PrefixYellow + PrefixCommand + Messages.getString("sg.sell_text"));
			return true;
		}

		try {
			PlayerDAO playerDAO = new PlayerDAO(sgConfig);
			RegionDAO regionDAO = new RegionDAO(sgConfig);

			br.com.saomc.sg.jdbc.dao.model.Player owner = playerDAO.findByName(player.getName().toLowerCase());
			Region region = regionDAO.findByOwnerAndName(owner, regionName);

			if (cancel) {
				if (Util.empty(region.getStatus())) {
					player.sendMessage(PrefixRed + Messages.getString("sg.region_not_for_sale", region.getName()));
				} else {
					region.setStatus("");
					region.setSellingPrice(price.intValue());

					regionDAO.update(region);
					player.sendMessage(PrefixRed + Messages.getString("sg.cancel_sell", region.getName()));
				}

			} else {
				if (STATUS_SALE.equalsIgnoreCase(region.getStatus())) {
					player.sendMessage(PrefixRed + Messages.getString("sg.already_on_sale"));
					return true;
				}

				if (STATUS_RENT.equalsIgnoreCase(region.getStatus())) {
					player.sendMessage(PrefixRed + Messages.getString("sg.cant_sell_tenancy"));
					return true;
				}
                                if(region.getName() == null){
                                        player.sendMessage(PrefixRed + Messages.getString("sg.nao_tem_terreno"));
					return true;    
                                }
                                else{
				region.setStatus(STATUS_SALE);
				region.setSellingPrice(price.intValue());

				regionDAO.update(region);
				player.sendMessage(PrefixYellow + Messages.getString("sg.area_sale_success"));
                                }
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return true;
	}


	private Boolean expand(Player player, String[] args) {
                RegionContainer container = plugin.getWorldGuard().getRegionContainer();
		RegionManager regions = container.get(player.getWorld());

		String regionName;
		String regionFullName;
		Integer expansion;

		if (args.length != 3) {
			player.sendMessage(PrefixYellow + PrefixCommand + Messages.getString("sg.expand_text"));
			return true;
		}

		try {
			regionName = args[1];
			regionFullName = player.getName() + "_" + regionName;
		} catch (Exception e) {
			player.sendMessage(PrefixYellow + PrefixCommand + Messages.getString("sg.expand_text"));
			return true;
		}

		try {
			expansion = Integer.parseInt(args[2]);
		} catch (NumberFormatException e) {
			player.sendMessage(PrefixRed + Messages.getString("sg.invalid_size"));
			return true;
		}

		if (regions.getRegion(regionFullName) == null) {
			player.sendMessage(PrefixRed + Messages.getString("sg.area_not_found", regionName));
			return true;
		}

		if (econ.getBalance(player.getName()) < expansion * sgConfig.getBlockPurchaseExpand()) {
			player.sendMessage(PrefixRed + Messages.getString("sg.enough_money", sgConfig.getCoinName() + expansion * sgConfig.getBlockPurchaseExpand()));
			return true;
		}

		ProtectedRegion protectedRegion = regions.getRegion(regionFullName);
		int xmin = protectedRegion.getMinimumPoint().getBlockX();
		int zmin = protectedRegion.getMinimumPoint().getBlockZ();
		int xmax = protectedRegion.getMaximumPoint().getBlockX();
		int zmax = protectedRegion.getMaximumPoint().getBlockZ();

		Integer areaNova = (expansion * 2) + xmax - xmin;
		if (areaNova > sgConfig.getMaxSize()) {
			String tamanhoMax = Integer.toString(sgConfig.getMaxSize());
			String tamanhaArea = Integer.toString(xmax - xmin);
			String tamanhoAreaNovo = Integer.toString(areaNova);

			player.sendMessage(PrefixRed + Messages.getString("sg.max_expand_exceeded", tamanhoMax, tamanhaArea, tamanhoAreaNovo));
			return true;
		}

		regions.removeRegion(regionFullName);


            try {
                regions.save();
            } catch (StorageException ex) {
                Logger.getLogger(SaoGuardCommandExecutor.class.getName()).log(Level.SEVERE, null, ex);
            }
		

		expandir(player, xmin, xmax, zmin, zmax, regionName, expansion);
		return true;
	}


	private Boolean msg(Player player, String[] args) {
		RegionContainer container = plugin.getWorldGuard().getRegionContainer();
		RegionManager regions = container.get(player.getWorld());

		String regionName;
		String regionFullName;
		StringBuffer message = new StringBuffer();

		if (args.length < 2) {
			player.sendMessage(PrefixYellow + PrefixCommand + Messages.getString("sg.msg_text"));
			return true;
		}

		try {
			regionName = args[1];
			regionFullName = player.getName() + "_" + regionName;

			message.append(args[2]);
			for (int i = 3; i < args.length; i++) {
				message.append(" ");
				message.append(args[i]);
			}
		} catch (Exception e) {
			player.sendMessage(PrefixYellow + PrefixCommand + Messages.getString("sg.msg_text"));
			return true;
		}

		if (regions.getRegion(regionFullName) == null) {
			player.sendMessage(PrefixRed + Messages.getString("sg.area_not_found", regionName));
			return true;
		}

		ProtectedRegion region = regions.getRegion(regionFullName);

            try {
                regions.save();
            } catch (StorageException ex) {
                Logger.getLogger(SaoGuardCommandExecutor.class.getName()).log(Level.SEVERE, null, ex);
            }

		return true;
	}


	Boolean del(Player player, String[] args) {

		String regionName;
		String regionFullName;

		if (args.length != 2) {
			player.sendMessage(PrefixYellow + PrefixCommand + Messages.getString("sg.del_text"));
			return true;
		}

		try {
			regionName = args[1].toLowerCase();
			regionFullName = player.getName() + "_" + regionName;
		} catch (Exception e) {
			player.sendMessage(PrefixYellow + PrefixCommand + Messages.getString("sg.del_text"));
			return true;
		}

		try {
			PlayerDAO playerDAO = new PlayerDAO(sgConfig);
			RegionDAO regionDAO = new RegionDAO(sgConfig);
			FlagDAO flagDAO = new FlagDAO(sgConfig);

			br.com.saomc.sg.jdbc.dao.model.Player owner = playerDAO.findByName(player.getName().toLowerCase());
                        if (regionDAO.hasRegion(regionFullName)) {
				for (Region region : regionDAO.listByFullName(regionFullName)) {
					flagDAO.deleteByRegionId(region.getId());
				}
				//regionDAO.deleteByFullName(regionFullName);
				player.sendMessage(PrefixYellow + Messages.getString("sg.area_removed", ChatColor.YELLOW + regionName + ChatColor.AQUA));
			} else {
				player.sendMessage(PrefixRed + Messages.getString("sg.area_not_found", regionName));
			}
			for (World world : worldGuard.getServer().getWorlds()) {

				RegionContainer container = plugin.getWorldGuard().getRegionContainer();
                                RegionManager regions = container.get(player.getWorld());

				if (regions.getRegion(regionFullName) != null) {

					ProtectedRegion protectedRegion = regions.getRegion(regionFullName);
					delParede(player, protectedRegion.getMinimumPoint().getBlockX(), protectedRegion.getMaximumPoint().getBlockX(), protectedRegion.getMinimumPoint().getBlockZ(), protectedRegion.getMaximumPoint().getBlockZ());
                                        flagDAO.deleteByRegionId(regionDAO.findByFullName(protectedRegion.getId()).getId());
                                        regionDAO.delete(regionDAO.findByFullName(protectedRegion.getId()).getId());
                                        
                                        regions.removeRegion(regionFullName);

                                    try {
                                        regions.save();
                                    } catch (StorageException ex) {
                                        Logger.getLogger(SaoGuardCommandExecutor.class.getName()).log(Level.SEVERE, null, ex);
                                    }


				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return true;
	}

        public Boolean delGui(Player player, String nome) {

		String regionName;
		String regionFullName;


		try {
			regionName = nome.toLowerCase();
			regionFullName = player.getName() + "_" + regionName;
		} catch (Exception e) {
			player.sendMessage(PrefixYellow + PrefixCommand + Messages.getString("sg.del_text"));
			return true;
		}

		try {
			PlayerDAO playerDAO = new PlayerDAO(sgConfig);
			RegionDAO regionDAO = new RegionDAO(sgConfig);
			FlagDAO flagDAO = new FlagDAO(sgConfig);

			br.com.saomc.sg.jdbc.dao.model.Player owner = playerDAO.findByName(player.getName().toLowerCase());

			for (World world : worldGuard.getServer().getWorlds()) {

				RegionContainer container = plugin.getWorldGuard().getRegionContainer();
                                RegionManager regions = container.get(player.getWorld());

				if (regions.getRegion(regionFullName) != null) {

					ProtectedRegion protectedRegion = regions.getRegion(regionFullName);
					delParede(player, protectedRegion.getMinimumPoint().getBlockX(), protectedRegion.getMaximumPoint().getBlockX(), protectedRegion.getMinimumPoint().getBlockZ(), protectedRegion.getMaximumPoint().getBlockZ());
                                        flagDAO.deleteByRegionId(regionDAO.findByFullName(protectedRegion.getId()).getId());
                                        regionDAO.delete(regionDAO.findByFullName(protectedRegion.getId()).getId());
                                        
                                        regions.removeRegion(regionFullName);

                                    try {
                                        regions.save();
                                    } catch (StorageException ex) {
                                        Logger.getLogger(SaoGuardCommandExecutor.class.getName()).log(Level.SEVERE, null, ex);
                                    }


				}

			}

			if (regionDAO.hasRegion(regionFullName)) {
				for (Region region : regionDAO.listByFullName(regionFullName)) {
					flagDAO.deleteByRegionId(region.getId());
				}
				regionDAO.deleteByFullName(regionFullName);
				player.sendMessage(PrefixYellow + Messages.getString("sg.area_removed", ChatColor.YELLOW + regionName + ChatColor.AQUA));
			} else {
				player.sendMessage(PrefixRed + Messages.getString("sg.area_not_found", regionName));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return true;
	}
        
	private Boolean rename(Player player, String[] args) {

		if (args.length != 3) {
			player.sendMessage(PrefixYellow + PrefixCommand + Messages.getString("sg.rename_text"));
			return true;
		}

		String oldRegionName = args[1];
		String newRegionName = args[2];
		String oldRegionFullName = player.getName().toLowerCase() + "_" + oldRegionName;
		String newRegionFullName = player.getName().toLowerCase() + "_" + newRegionName;

		try {
			PlayerDAO playerDAO = new PlayerDAO(sgConfig);
			RegionDAO regionDAO = new RegionDAO(sgConfig);

			br.com.saomc.sg.jdbc.dao.model.Player owner = playerDAO.findByName(player.getName());
			Region region = regionDAO.findByOwnerAndName(owner, oldRegionName);
			region.setName(newRegionName);
			region.setFullName(newRegionFullName);

			if (regionDAO.hasRegion(newRegionFullName)) {
				player.sendMessage(PrefixYellow + PrefixCommand + Messages.getString("sg.rename_text"));
				return true;
			} else {
				RegionManager regionManager = null;
				for (World world : worldGuard.getServer().getWorlds()) {
					if (world.getName().equalsIgnoreCase(region.getWorld())) {
						regionManager = worldGuard.getGlobalRegionManager().get(world);
						break;
					}

				}

				renameRegion(regionManager, region, oldRegionFullName, newRegionFullName);
				regionDAO.update(region);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}


	private void renameRegion(RegionManager regionManager, Region region, String oldRegionFullName, String newRegionFullName) {
                ProtectedRegion protectedRegion = regionManager.getRegion(oldRegionFullName);

		BlockVector blockVectorInitial = new BlockVector(region.getInitialPositionX(), region.getInitialPositionY(), region.getInitialPositionZ());
		BlockVector blockVectorFinal = new BlockVector(region.getFinalPositionX(), region.getFinalPositionY(), region.getFinalPositionZ());
		ProtectedCuboidRegion protectedCuboidRegion = new ProtectedCuboidRegion(newRegionFullName, blockVectorInitial, blockVectorFinal);

		protectedCuboidRegion.setPriority(100);
		protectedCuboidRegion.setOwners(protectedRegion.getOwners());
		protectedCuboidRegion.setMembers(protectedRegion.getMembers());
		protectedCuboidRegion.setFlags(protectedRegion.getFlags());

		regionManager.addRegion(protectedCuboidRegion);
		regionManager.removeRegion(oldRegionFullName);
            try {
                regionManager.save();
            } catch (StorageException ex) {
                Logger.getLogger(SaoGuardCommandExecutor.class.getName()).log(Level.SEVERE, null, ex);
            }
	}


	private Boolean pvp(CommandSender sender, Player player, String[] args) {
                RegionContainer container = plugin.getWorldGuard().getRegionContainer();
                RegionManager regions = container.get(player.getWorld());

		String regionName;
		String regionFullName;
		String pvp;

		if (args.length != 3) {
			player.sendMessage(PrefixYellow + PrefixCommand + Messages.getString("sg.pvp_text"));
			return true;
		}

		try {
			regionName = args[1].toLowerCase();
			regionFullName = player.getName() + "_" + regionName;
			pvp = args[2];
		} catch (Exception e) {
			player.sendMessage(PrefixYellow + PrefixCommand + Messages.getString("sg.pvp_text"));
			return true;
		}

		if (regions.getRegion(regionFullName) == null) {
			player.sendMessage(PrefixRed + Messages.getString("sg.area_not_found", regionName));
			return true;
		}

		if (econ.getBalance(player.getName()) < sgConfig.getPVPPrice()) {
			player.sendMessage(PrefixRed + Messages.getString("sg.pvp", sgConfig.getCoinName() + sgConfig.getPVPPrice()));
			return true;
		}

		ProtectedRegion protectedRegion = regions.getRegion(regionFullName);
		if (pvp.equalsIgnoreCase("on")) {

			
				protectedRegion.setFlag(DefaultFlag.PVP, StateFlag.State.ALLOW);
				player.sendMessage(PrefixYellow + Messages.getString("sg.pvp_enabled", regionName));
				econ.withdrawPlayer(player.getName(), sgConfig.getPVPPrice());
			
		} else if (pvp.equalsIgnoreCase("off")) {

				protectedRegion.setFlag(DefaultFlag.PVP, StateFlag.State.DENY);
				player.sendMessage(PrefixYellow + Messages.getString("sg.pvp_disabled", regionName));
				econ.withdrawPlayer(player.getName(), sgConfig.getPVPPrice());
			

		} else {
			player.sendMessage(PrefixYellow + PrefixCommand + Messages.getString("sg.pvp_text"));
			return true;
		}

		
            try {
                regions.save();
            } catch (StorageException ex) {
                Logger.getLogger(SaoGuardCommandExecutor.class.getName()).log(Level.SEVERE, null, ex);
            }
		

		return true;
	}


	private Boolean addFriend(Player player, String[] args) {

		String regionName;
		String regionFullName;
		String member;

		if (args.length != 3) {
			player.sendMessage(PrefixYellow + PrefixCommand + Messages.getString("sg.add_friend_text"));
			return true;
		}

		try {
			regionName = args[1].toLowerCase();
			regionFullName = player.getName() + "_" + regionName;
			member = args[2];
		} catch (Exception e) {
			player.sendMessage(PrefixYellow + PrefixCommand + Messages.getString("sg.add_friend_text"));
			return true;
		}

		Player gPlayer = (Bukkit.getServer().getPlayer(member));
		if (gPlayer == null) {
			player.sendMessage(PrefixRed + Messages.getString("sg.player_offline", member));
			return true;
		}
                RegionContainer container = plugin.getWorldGuard().getRegionContainer();
		RegionManager regions = container.get(player.getWorld());
		ProtectedRegion protectedRegion = regions.getRegion(regionFullName);

		if (Util.empty(protectedRegion)) {
			player.sendMessage(PrefixRed + Messages.getString("sg.area_not_found", regionName));
			return true;
		}

		protectedRegion.getMembers().addPlayer(gPlayer.getName());
		player.sendMessage(PrefixYellow + Messages.getString("sg.add_player_to_area", gPlayer.getName()));
		
            try {
                regions.save();
            } catch (StorageException ex) {
                Logger.getLogger(SaoGuardCommandExecutor.class.getName()).log(Level.SEVERE, null, ex);
            }
		

		return true;
	}


	private Boolean delFriend(Player player, String[] args) {

		String regionName;
		String regionFullName;
		String member;

		if (args.length != 3) {
			player.sendMessage(PrefixYellow + PrefixCommand + Messages.getString("sg.del_friend_text"));
			return true;
		}

		try {
			regionName = args[1].toLowerCase();
			regionFullName = player.getName() + "_" + regionName;
			member = args[2];
		} catch (Exception e) {
			player.sendMessage(PrefixYellow + PrefixCommand + Messages.getString("sg.del_friend_text"));
			return true;
		}
                RegionContainer container = plugin.getWorldGuard().getRegionContainer();
		RegionManager mgr = container.get(player.getWorld());
		if (mgr.getRegion(regionFullName) == null) {
			player.sendMessage(PrefixRed + Messages.getString("sg.area_not_found", regionName));
			return true;
		}
		ProtectedRegion region = mgr.getRegion(regionFullName);
		region.getMembers().removePlayer(member);
		player.sendMessage(PrefixYellow + Messages.getString("sg.remove_player_to_area", member));

		
            try {
                mgr.save();
            } catch (StorageException ex) {
                Logger.getLogger(SaoGuardCommandExecutor.class.getName()).log(Level.SEVERE, null, ex);
            }
		

		return true;
	}


	private Boolean info(Player player) {

		RegionManager regionManager = worldGuard.getRegionManager(player.getWorld());
		ApplicableRegionSet set = regionManager.getApplicableRegions(player.getLocation());

		//console.sendMessage("set: " + set);
		//console.sendMessage("set.size(): " + set.size());
		//console.sendMessage("player.getLocation(): " + player.getLocation());

		if (set.size() == 0) {
			player.sendMessage(PrefixRed + Messages.getString("sg.area_not_exist"));
			return true;
		}

		set.toString().toLowerCase();
		String id = set.iterator().next().getId();
		Double tamanho = regionManager.getRegion(id).getMaximumPoint().getX() - regionManager.getRegion(id).getMinimumPoint().getX();

		if (player.getName().equalsIgnoreCase(regionManager.getRegion(id).getOwners().toUserFriendlyString()) || player.isOp()) {
			if (!player.isOp()) {
				player.sendMessage(PrefixYellow + Messages.getString("sg.area_belongs_you"));
			} else {
				player.sendMessage(PrefixYellow + Messages.getString("sg.area_player", ChatColor.RED + regionManager.getRegion(id).getOwners().toUserFriendlyString()));
			}

			player.sendMessage(PrefixYellow + Messages.getString("sg.area_valor", ChatColor.RED + id.split("_")[id.split("_").length - 1]));
			player.sendMessage(PrefixYellow + Messages.getString("sg.size_valor", ChatColor.RED + "" + tamanho.intValue() + " x " + tamanho.intValue()));

			if (regionManager.getRegion(id).getMembers().size() != 0) {
				player.sendMessage(PrefixYellow + Messages.getString("sg.members_valor", ChatColor.YELLOW + regionManager.getRegion(id).getMembers().toUserFriendlyString()));
			} else {
				player.sendMessage(PrefixYellow + Messages.getString("sg.members_valor_none", ChatColor.RED));
			}
		} else {
			player.sendMessage(PrefixRed + Messages.getString("sg.area_player", ChatColor.RED + regionManager.getRegion(id).getOwners().toUserFriendlyString()));
			player.sendMessage(PrefixRed + Messages.getString("sg.area_valor", ChatColor.RED + id.split("_")[id.split("_").length - 1]));
			player.sendMessage(PrefixRed + Messages.getString("sg.size_valor", ChatColor.RED + "" + tamanho.intValue() + " x " + tamanho.intValue()));
		}

		return true;

	}


	private Boolean list(Player player, String[] args) {

		String parametro = "";

		try {
			parametro = args[1];
		} catch (Exception e) {
		}

		if (Util.empty(parametro)) {

			try {
				PlayerDAO playerDAO = new PlayerDAO(sgConfig);
				RegionDAO regionDAO = new RegionDAO(sgConfig);

				br.com.saomc.sg.jdbc.dao.model.Player hgPlayer = playerDAO.findByName(player.getName());

				List<Region> regions = regionDAO.listByOwner(hgPlayer);

				player.sendMessage(PrefixYellow + Messages.getString("sg.your_areas"));

				for (Region region : regions) {
					player.sendMessage(ChatColor.YELLOW + region.getWorld() + " - " + region.getName() + " (" + region.getFinalPositionX() + ", " + region.getFinalPositionY() + ", "
							+ region.getFinalPositionZ() + ") ");
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} else if (parametro.equalsIgnoreCase("sale") || parametro.equalsIgnoreCase("venda")) {

			try {
				RegionDAO regionDAO = new RegionDAO(sgConfig);
				List<Region> regions = regionDAO.listByStatus(STATUS_SALE);
				player.sendMessage(PrefixYellow + Messages.getString("sg.regions_sale"));

				for (Region region : regions) {
					player.sendMessage(PrefixYellow + region.getWorld() + " - " + region.getOwner().getName() + " - " + sgConfig.getCoinName() + region.getSellingPrice() + " - " + region.getName()
							+ " (" + region.getFinalPositionX() + ", " + region.getFinalPositionY() + ", " + region.getFinalPositionZ() + ") ");
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} else if (parametro.equalsIgnoreCase("tenancy") || parametro.equalsIgnoreCase("aluguel")) {

			try {
				RegionDAO regionDAO = new RegionDAO(sgConfig);
				List<Region> regions = regionDAO.listByStatus(STATUS_RENT);
				player.sendMessage(PrefixYellow + Messages.getString("sg.regions_tenancy"));

				for (Region region : regions) {
					player.sendMessage(PrefixYellow + region.getWorld() + " - " + region.getOwner().getName() + " - " + sgConfig.getCoinName() + region.getTenancyTax() + " - " + region.getName()
							+ " (" + region.getFinalPositionX() + ", " + region.getFinalPositionY() + ", " + region.getFinalPositionZ() + ") ");
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} else if (parametro.equalsIgnoreCase("all") || parametro.equalsIgnoreCase("todos")) {

			try {
				RegionDAO regionDAO = new RegionDAO(sgConfig);
				List<Region> regions = regionDAO.listAll();
				player.sendMessage(PrefixYellow + Messages.getString("sg.regions_all"));

				for (Region region : regions) {
					player.sendMessage(PrefixYellow + region.getWorld() + " - " + region.getOwner().getName() + " - " + sgConfig.getCoinName() + region.getTenancyTax() + " - " + region.getName()
							+ " (" + region.getFinalPositionX() + ", " + region.getFinalPositionY() + ", " + region.getFinalPositionZ() + ") ");
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return true;
	}


	private Boolean prices(Player player) {

		Double precoCompra = sgConfig.getBlockPurchasePrice().doubleValue();
		Double precoExpansao = sgConfig.getBlockPurchaseExpand().doubleValue();
		Double precoPVP = sgConfig.getPVPPrice().doubleValue();

		player.sendMessage(PrefixYellow + Messages.getString("sg.price_buy", ChatColor.RED + "" + precoCompra.intValue()));
		player.sendMessage(PrefixYellow + Messages.getString("sg.price_expansion", ChatColor.RED + "" + precoExpansao.intValue()));
		player.sendMessage(PrefixYellow + Messages.getString("sg.price_pvp", ChatColor.RED + "" + precoPVP.intValue()));

		return true;
	}


	private Boolean tp(Player player, String[] args) {

		if (args.length != 2) {
			player.sendMessage(PrefixYellow + PrefixCommand + Messages.getString("sg.tp_text"));
			return true;
		}

		String regionName = args[1];

		try {
			PlayerDAO playerDAO = new PlayerDAO(sgConfig);
			RegionDAO regionDAO = new RegionDAO(sgConfig);

			br.com.saomc.sg.jdbc.dao.model.Player hgPlayer = playerDAO.findByName(player.getName());

			Region region = null;

			if (player.isOp()) {
				region = regionDAO.findByFullName(regionName);
			} else {
				region = regionDAO.findByOwnerAndName(hgPlayer, regionName);
			}

			if (!regionDAO.hasRegion(region.getFullName())) {
				player.sendMessage(PrefixRed + Messages.getString("sg.area_not_found", regionName));
				return true;
			}

			for (World world : plugin.getServer().getWorlds()) {
				if (world.getName().equals(region.getWorld())) {
					Double x = region.getInitialPositionX().doubleValue();
					Double y = new Double(world.getHighestBlockAt(region.getInitialPositionX(), region.getInitialPositionZ()).getY());
					Double z = region.getInitialPositionZ().doubleValue();

					if (!player.isOp()) {
						player.sendMessage(PrefixYellow + Messages.getString("sg.teleport", sgConfig.getTpDelay()));
						try {
							Thread.sleep(sgConfig.getTpDelay() * 1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					player.teleport(new Location(world, x, y, z));
					break;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return true;
	}


	private Boolean sync(Player player) {
		try {
			PlayerDAO playerDAO = new PlayerDAO(sgConfig);
			RegionDAO regionDAO = new RegionDAO(sgConfig);
			FlagDAO flagDAO = new FlagDAO(sgConfig);

			for (World world : plugin.getServer().getWorlds()) {
				RegionManager regionManager = worldGuard.getGlobalRegionManager().get(world);
				Map<String, ProtectedRegion> regions = regionManager.getRegions();

				console.sendMessage(PrefixYellowConsole + world.getName());

				for (String regionfullName : regions.keySet()) {
					if (StringUtils.countMatches(regionfullName, "_") == 1) {
						ProtectedRegion protectedRegion = regions.get(regionfullName);
						console.sendMessage(PrefixYellowConsole + regionfullName);
						String[] str = regionfullName.split("_");
						String playerName = str[0];
						String regionName = str[1];

						br.com.saomc.sg.jdbc.dao.model.Player owner = playerDAO.findByName(playerName);

						if (Util.empty(owner.getId())) {
							owner.setName(playerName);
							playerDAO.insert(owner);
							owner = playerDAO.findByName(playerName);
						}
						console.sendMessage(PrefixBlueConsole + owner);

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

						console.sendMessage(PrefixBlueConsole + region);
					}
				}
			}

			if (!Util.empty(player)) {
				player.sendMessage(PrefixYellow + "Sucessfull");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}


	/**
	 * Funcao removida do worldguard para entender como funcionam as flags
	 * 
	 */
	public String appendFlagsList(ProtectedRegion protectedRegion) {
		StringBuffer str = new StringBuffer();

		for (com.sk89q.worldguard.protection.flags.Flag<?> flag : DefaultFlag.getFlags()) {
			Object val = protectedRegion.getFlag(flag);

			str.append(", ");
			str.append(flag.getName()).append(": ").append(String.valueOf(val));
		}

		str.append("(none)");

		return str.toString();
	}


	// TODO precisa fazer
	@SuppressWarnings("unused")
	private Boolean block(Player player,String[] args) {
            if (args.length != 2) {
			player.sendMessage(PrefixYellow + PrefixCommand + Messages.getString("sg.del_text"));
			return true;
            }
            String nomeplayerblock = args[1].toLowerCase();
            //Player pb = Bukkit.getPlayer(nomeplayerblock);
            RegionManager rm = worldGuard.getRegionManager(player.getWorld());
	    ApplicableRegionSet set = rm.getApplicableRegions(player.getLocation());
            set.toString().toLowerCase();
            String id = set.iterator().next().getId();
            try {
            PlayerDAO playerDAO = new PlayerDAO(sgConfig);
            RegionDAO regionDAO = new RegionDAO(sgConfig);
            BlockDAO blockDAO = new BlockDAO(sgConfig);
            Region region = null;
            region = regionDAO.findByFullName(rm.getRegion(id).getId().toString());
            String pt = blockDAO.findTerrenoid(region.getId().toString());
            if(pt == null){
                pt= "";
            }
            
            player.sendMessage("Id"+region.getId());
            player.sendMessage("blocked"+pt);
            player.sendMessage(""+region.getFullName());
            blockDAO.insert2(region, pt+"."+nomeplayerblock);
            //player.sendMessage(""+region.getFullName());
            }
            catch (SQLException e) {
			e.printStackTrace();
	}
		return true;
	}


	// TODO
	/**
	 * 
	 * Coisas que precisam ser feitas:
	 * Ovelhas nao vem coloridas;
	 * Cavalos nao spawnam;
	 * Placas e outros blocos estam vindo virados para mesma direcao;
	 * escadas nao colam na parede;
	 * blocos estao vindo do mesmo tipo;
	 * algumas vezes da nullpointer nas placas ao restaurar;
	 * portas e camas nao estam vindo direito;
	 */
	@SuppressWarnings("unused")
	private Boolean backup(Player player, String[] args) {

		World world = null;

		if (args.length >= 2) {
			try {
				world = plugin.getServer().getWorld(args[1]);
			} catch (Exception e) {
				player.sendMessage("World dont exist");
				return true;
			}
		} else {
			world = player.getWorld();
		}

		if (Util.empty(world)) {
			return true;
		}

		try {
			BackupDAO backupDAO = new BackupDAO(sgConfig);
			EntityDAO entityDAO = new EntityDAO(sgConfig);
			ChestDAO chestDAO = new ChestDAO(sgConfig);
			BlockDAO blockDAO = new BlockDAO(sgConfig);
			SignDAO signDAO = new SignDAO(sgConfig);
			ItemDAO itemDAO = new ItemDAO(sgConfig);
			EnchantmentDAO enchantmentDAO = new EnchantmentDAO(sgConfig);

			if (args.length == 3 && "del".equalsIgnoreCase(args[2])) {
				enchantmentDAO.dropTable();
				itemDAO.dropTable();
				signDAO.dropTable();
				chestDAO.dropTable();
				blockDAO.dropTable();
				entityDAO.dropTable();
				backupDAO.dropTable();

				player.sendMessage("Backup removed");
				return true;
			}

			if (sgConfig.getIsMySQL()) {
				backupDAO.createTableMySql();
				entityDAO.createTableMySql();
				blockDAO.createTableMySql();
				signDAO.createTableMySql();
				chestDAO.createTableMySql();
				itemDAO.createTableMySql();
				enchantmentDAO.createTableMySql();
			} else {
				backupDAO.createTableSqlite();
				entityDAO.createTableSqlite();
				blockDAO.createTableSqlite();
				signDAO.createTableSqlite();
				chestDAO.createTableSqlite();
				itemDAO.createTableSqlite();
				enchantmentDAO.createTableSqlite();
			}

			player.sendMessage("Backup started");
			console.sendMessage(PrefixBlueConsole + "Backup started");

			RegionManager regionManager = worldGuard.getGlobalRegionManager().get(world);
			Map<String, ProtectedRegion> regions = regionManager.getRegions();
			StringBuffer blocksAndLocations = new StringBuffer();
			StringBuffer specialBlocksAndLocations = new StringBuffer();

			Integer contador = 0;
			Integer totalBlocksPerRegion = 0;
			Integer totalBlocks = 0;
			Integer totalRegions = 0;
			Integer blocksPerRow = 0;

			Backup backup = new Backup();
			backup.setWorld(world.getName());
			backup.setDate(new Date());

			backupDAO.insert(backup);
			backup = backupDAO.findBy(backup);

			for (String regionfullName : regions.keySet()) {
				ProtectedRegion protectedRegion = regions.get(regionfullName);

				if (!"__global__".equalsIgnoreCase(regionfullName)) {

					totalRegions++;
					totalBlocksPerRegion = 0;

					Integer initialX = protectedRegion.getMinimumPoint().getBlockX();
					Integer initialY = protectedRegion.getMinimumPoint().getBlockY();
					Integer initialZ = protectedRegion.getMinimumPoint().getBlockZ();

					Integer finalX = protectedRegion.getMaximumPoint().getBlockX();
					Integer finalY = protectedRegion.getMaximumPoint().getBlockY();
					Integer finalZ = protectedRegion.getMaximumPoint().getBlockZ();

					for (int x = initialX; x <= finalX; x++) {
						for (int y = initialY; y <= finalY; y++) {
							for (int z = initialZ; z <= finalZ; z++) {
								Block block = world.getBlockAt(x, y, z);

//							BlockFace blockFace = block.getFace(world.getBlockAt(x, y + 1, z));

								contador++;
								totalBlocks++;
								totalBlocksPerRegion++;

								String[] specialBlocks = {"WOODEN_DOOR", "IRON_DOOR_BLOCK", "CROPS", "LADDER", "STONE_BUTTON", "BROWN_MUSHROOM", "RED_MUSHROOM", "FIRE", "REDSTONE_WIRE",
										"SUGAR_CANE_BLOCK", "WOOD_DOOR", "VINE", "IRON_DOOR", "NETHER_WARTS", "TORCH", "REDSTONE_TORCH_OFF", "REDSTONE_TORCH_ON", "LEVER", "STONE_PLATE", "WOOD_PLATE"};

								if (ArrayUtils.contains(specialBlocks, block.getType().toString())) {

									specialBlocksAndLocations.append(block.getType().toString() + ";" + x + ";" + y + ";" + z + ":");

								} else if ("SIGN_POST".equals(block.getType().toString()) || "WALL_SIGN".equals(block.getType().toString()) || "SIGN".equals(block.getType().toString())) {
									Sign sign = (Sign) block.getState();

									br.com.saomc.sg.jdbc.dao.model.Sign hgSign = new br.com.saomc.sg.jdbc.dao.model.Sign();

									StringBuilder lines = new StringBuilder();

									for (String line : sign.getLines()) {
										lines.append(line + "_");
									}

									hgSign.setLine(lines.toString());
									hgSign.setX(x);
									hgSign.setY(y);
									hgSign.setZ(z);
									hgSign.setPitch(sign.getLocation().getPitch());
									hgSign.setYaw(sign.getLocation().getYaw());
									hgSign.setType(sign.getType().toString());
									hgSign.setBackup(backup);

									signDAO.insert(hgSign);

								} else if (!"CHEST".equals(block.getType().toString())) {

									if (blocksPerRow >= TOTAL_BLOCKS_PER_ROW) {
//									sendMessage(PrefixRedConsole + "totalBlocks: " + contador);
										br.com.saomc.sg.jdbc.dao.model.Block hgBlock = new br.com.saomc.sg.jdbc.dao.model.Block();
										hgBlock.setBlocksAndLocations(blocksAndLocations.toString());
										blockDAO.insert(hgBlock);

										blocksPerRow = 0;
										blocksAndLocations = new StringBuffer();
									}
									blocksAndLocations.append(block.getType().toString() + ";" + x + ";" + y + ";" + z + ":");
									blocksPerRow++;

								} else {

									Chest chest = (Chest) block.getState();
									br.com.saomc.sg.jdbc.dao.model.Chest hgChest = new br.com.saomc.sg.jdbc.dao.model.Chest();

									hgChest.setType(block.getType().toString());
									hgChest.setX(x);
									hgChest.setY(y);
									hgChest.setZ(z);
									hgChest.setPitch(block.getLocation().getPitch());
									hgChest.setYaw(block.getLocation().getYaw());
									hgChest.setBackup(backup);

									chestDAO.insert(hgChest);
									hgChest = chestDAO.findBy(hgChest);

									for (ItemStack itemStack : chest.getBlockInventory()) {

										if (!Util.empty(itemStack)) {
											Item item = new Item();
											item.setType(itemStack.getType().toString());
											item.setAmount(itemStack.getAmount());
											item.setDurability(itemStack.getDurability());
											item.setChest(hgChest);

											itemDAO.insert(item);
											item = itemDAO.findBy(item);

											List<br.com.saomc.sg.jdbc.dao.model.Enchantment> enchantments = new ArrayList<br.com.saomc.sg.jdbc.dao.model.Enchantment>();
											// Gets a map containing all enchantments and their levels on this item.
											for (Entry<Enchantment, Integer> entry : itemStack.getEnchantments().entrySet()) {
												Enchantment key = entry.getKey(); // enchantment
												Integer value = entry.getValue(); // level

												br.com.saomc.sg.jdbc.dao.model.Enchantment enchantment = new br.com.saomc.sg.jdbc.dao.model.Enchantment();
												enchantment.setType(key.getName());
												enchantment.setLevel(value);
												enchantment.setItem(item);

												enchantmentDAO.insert(enchantment);
												enchantments.add(enchantment);
											}
											item.setEnchantments(enchantments);
											if (item.getEnchantments().size() > 0) {
												item.setHasEnchantment(true);
												itemDAO.update(item);
											}
										}
									}

								}
							}
						}
					}

					//blockDAO.insert(new br.com.saomc.sg.jdbc.dao.model.Block(blocksAndLocations.toString()));
					//blockDAO.insert(new br.com.saomc.sg.jdbc.dao.model.Block(specialBlocksAndLocations.toString()));

					blocksAndLocations = new StringBuffer();
					specialBlocksAndLocations = new StringBuffer();

					player.sendMessage("Region " + regionfullName + " saved.");
					console.sendMessage(PrefixBlueConsole + "Region " + regionfullName + " saved. Total blocks: " + totalBlocksPerRegion);
				}
			}

			console.sendMessage(PrefixBlueConsole + "Total blocks:" + totalBlocks);
			console.sendMessage(PrefixBlueConsole + "Total regions:" + totalRegions);

			contador = 0;
			// salvando entidades
			for (Entity entity : world.getEntities()) {

				String[] entityType = {"CHICKEN", "PAINTING", "COW", "SHEEP", "PIG", "OCELOT", "WOLF", "HORSE"};

				if (ArrayUtils.contains(entityType, entity.getType().toString())) {
					contador++;

					Location location = entity.getLocation();

					br.com.saomc.sg.jdbc.dao.model.Entity hgEntity = new br.com.saomc.sg.jdbc.dao.model.Entity();
					hgEntity.setType(entity.getType().toString());
					hgEntity.setWorld(world.toString());

					hgEntity.setX(new Double(location.getX()).intValue());
					hgEntity.setY(new Double(location.getY()).intValue());
					hgEntity.setZ(new Double(location.getZ()).intValue());

					if (entity instanceof Sheep) {
						Sheep sheep = (Sheep) entity;
						hgEntity.setColor(sheep.getColor().toString());
					} else if (entity instanceof Horse) {
						Horse horse = (Horse) entity;
						hgEntity.setVariant(horse.getVariant().toString());
						hgEntity.setColor(horse.getColor().toString());
						hgEntity.setStyle(horse.getStyle().toString());
						hgEntity.setCustomName(horse.getCustomName());

						hgEntity.setHealth(horse.getHealth());
						hgEntity.setMaxHealth(horse.getMaxHealth());
						hgEntity.setAge(horse.getAge());
						hgEntity.setTamed(Boolean.valueOf(horse.isTamed()));

						if (!Util.empty(horse.getInventory().getSaddle())) {
							hgEntity.setSaddled(true);
						} else {
							hgEntity.setSaddled(false);
						}

						if (!Util.empty(horse.getInventory().getArmor())) {
							console.sendMessage(PrefixRedConsole + horse.getInventory().getArmor().getType().toString());
							hgEntity.setArmor(horse.getInventory().getArmor().getType().toString());
						}

					}

					entityDAO.insert(hgEntity);

				}
			}

			console.sendMessage(PrefixRedConsole + "totalEntitys: " + contador);

			player.sendMessage("Backup complete");
			console.sendMessage(PrefixBlueConsole + "Backup complete");

		} catch (Exception e) {
			try {
				BackupDAO backupDAO = new BackupDAO(sgConfig);
				EntityDAO animalDAO = new EntityDAO(sgConfig);
				ChestDAO chestDAO = new ChestDAO(sgConfig);
				BlockDAO blockDAO = new BlockDAO(sgConfig);
				ItemDAO itemDAO = new ItemDAO(sgConfig);
				EnchantmentDAO enchantmentDAO = new EnchantmentDAO(sgConfig);

				enchantmentDAO.dropTable();
				itemDAO.dropTable();
				chestDAO.dropTable();
				blockDAO.dropTable();
				animalDAO.dropTable();
				backupDAO.dropTable();
			} catch (Exception e2) {
			} finally {
				e.printStackTrace();
			}
		}

		return true;
	}


	// TODO ainda nao esta pronto. Problema com os blocos
	@SuppressWarnings("unused")
	private Boolean restore(Player player, String[] args) {

		World world = null;

		if (args.length >= 2) {
			try {
				world = plugin.getServer().getWorld(args[1]);
			} catch (Exception e) {
				player.sendMessage("World dont exist");
				return true;
			}
		} else {
			world = player.getWorld();
		}

		if (Util.empty(world)) {
			return true;
		}

		try {
			// recuperando do banco
			BackupDAO backupDAO = new BackupDAO(sgConfig);
			EntityDAO entityDAO = new EntityDAO(sgConfig);
			BlockDAO blockDAO = new BlockDAO(sgConfig);
			SignDAO signDAO = new SignDAO(sgConfig);
			ChestDAO chestDAO = new ChestDAO(sgConfig);
			ItemDAO itemDAO = new ItemDAO(sgConfig);
			EnchantmentDAO enchantmentDAO = new EnchantmentDAO(sgConfig);

			player.sendMessage("Restore started");
			console.sendMessage(PrefixBlueConsole + "Restore started");

			Backup backup = backupDAO.findByWorld(world.getName());

			if (Util.empty(backup.getId())) {
				player.sendMessage("You need to make a backup first");
				return true;
			}

			// Primeiro vai recuperar os blocos que nao sao baus, logo nao tem itens.
			for (br.com.saomc.sg.jdbc.dao.model.Block hgBlock : blockDAO.listAll()) {
				String[] blocks = hgBlock.getBlocksAndLocations().split(":");

				for (String strBlock : blocks) {
					String[] str = strBlock.split(";");

					String type = str[0];
					Integer x = Integer.parseInt(str[1]);
					Integer y = Integer.parseInt(str[2]);
					Integer z = Integer.parseInt(str[3]);
//					Float pitch = Float.parseFloat(str[4]);
//					Float yaw = Float.parseFloat(str[5]);
//					String faceDirection = str[6];

					Block block = world.getBlockAt(new Location(world, x, y, z));
					block.setType(Material.getMaterial(type));

//					BlockFace blockFace = block.getFace(world.getBlockAt(x, y + 1, z));
//					blockFace.
				}

			}

			// Pegando baus com seus itens.
			for (br.com.saomc.sg.jdbc.dao.model.Chest hgChest : chestDAO.listAll()) {
				Block block = world.getBlockAt(new Location(world, hgChest.getX(), hgChest.getY(), hgChest.getZ(), hgChest.getYaw(), hgChest.getPitch()));
				block.setType(Material.getMaterial(hgChest.getType()));
				Chest chest = (Chest) block.getState();

				for (Item item : itemDAO.listByChestId(hgChest.getId())) {
					ItemStack itemstack = new ItemStack(Material.getMaterial(item.getType()), item.getAmount(), item.getDurability());

					if (item.getHasEnchantment()) {
						item.setEnchantments(enchantmentDAO.findByItemId(item.getId()));

						for (br.com.saomc.sg.jdbc.dao.model.Enchantment enchantment : enchantmentDAO.findByItemId(item.getId())) {
							itemstack.addEnchantment(Enchantment.getByName(enchantment.getType()), enchantment.getLevel());
						}

					}
					chest.getBlockInventory().addItem(itemstack);
				}

			}

			// placas
//			for (com.sg.jdbc.dao.model.Sign hgSign : signDAO.listAll()) {
//				System.out.println(hgSign);
//				sendMessage(PrefixBlueConsole + hgSign);
//				Block block = world.getBlockAt(new Location(world, hgSign.getX(), hgSign.getY(), hgSign.getZ(), hgSign.getYaw(), hgSign.getPitch()));
//				block.setType(Material.getMaterial(hgSign.getType()));
//				block.setType(Material.SIGN_POST);
//				Sign sign = (Sign) block.getState();
//
//				Integer index = 0;
//				for (String line : hgSign.getLines().split("_")) {
//					sign.setLine(index, line);
//					index++;
//				}
//
//				sign.update();
//
//			}

			// animais
			for (br.com.saomc.sg.jdbc.dao.model.Entity hgEntity : entityDAO.listAll()) {
				Location location = new Location(world, hgEntity.getX(), hgEntity.getY(), hgEntity.getZ());
				Entity entity = null;

				if (hgEntity.getType().equalsIgnoreCase("horse")) {
					entity = world.spawnEntity(location, EntityType.HORSE);
					Horse horse = (Horse) entity;

					horse.setVariant(Horse.Variant.valueOf(hgEntity.getVariant()));
					horse.setColor(Horse.Color.valueOf(hgEntity.getColor()));
					horse.setStyle(Horse.Style.valueOf(hgEntity.getStyle()));
					horse.setCustomName(hgEntity.getCustomName());
					horse.setHealth(hgEntity.getHealth());
					horse.setMaxHealth(hgEntity.getMaxHealth());
					horse.setAge(hgEntity.getAge());

					if (hgEntity.getTamed()) {
						horse.setTamed(hgEntity.getTamed());
					}

					if (hgEntity.getSaddled()) {
						horse.getInventory().setSaddle(new ItemStack(Material.SADDLE));
					}

					if (!Util.empty(hgEntity.getArmor())) {
						horse.getInventory().setArmor(new ItemStack(Material.getMaterial(hgEntity.getArmor())));
					}
				} else {

					if (hgEntity.getType().equalsIgnoreCase("CHICKEN")) {
						entity = world.spawnEntity(location, EntityType.CHICKEN);
					} else if (hgEntity.getType().equalsIgnoreCase("COW")) {
						entity = world.spawnEntity(location, EntityType.COW);
					} else if (hgEntity.getType().equalsIgnoreCase("SHEEP")) {
						entity = world.spawnEntity(location, EntityType.SHEEP);
						Sheep sheep = (Sheep) entity;
						sheep.setColor(DyeColor.valueOf(hgEntity.getColor()));
					} else if (hgEntity.getType().equalsIgnoreCase("PIG")) {
						entity = world.spawnEntity(location, EntityType.PIG);
					} else if (hgEntity.getType().equalsIgnoreCase("OCELOT")) {
						entity = world.spawnEntity(location, EntityType.OCELOT);
					} else if (hgEntity.getType().equalsIgnoreCase("WOLF")) {
						entity = world.spawnEntity(location, EntityType.WOLF);
//					} else if (hgAnimal.getType().equalsIgnoreCase("PAINTING")) {
//						entity = world.spawnEntity(location, EntityType.PAINTING);
					}

//					Entity animal = entity;
//
//					animal.setCustomName(hgAnimal.getCustomName());
//					animal.setHealth(hgAnimal.getHealth());
//					animal.setAge(hgAnimal.getAge());
				}

//				if (this.config.getString("animals." + animalUUID + ".chestcontent") != null) {
//					spawnedAnimal.setCarryingChest(true);
//					spawnedAnimal.getInventory().setContents((ItemStack[]) this.config.get("animals." + animalUUID + ".chestcontent"));
//				}

			}

			player.sendMessage("Restore complete");
			console.sendMessage(PrefixBlueConsole + "Restore complete");

//			enchantmentDAO.dropTable();
//			itemDAO.dropTable();
//			chestDAO.dropTable();
//			blockDAO.dropTable();
//			entityDAO.dropTable();
//			backupDAO.dropTable();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return true;
	}


	public void construir(Player player, int sizeX, int sizeZ, String regionName, Boolean noFence) {
                RegionContainer container = plugin.getWorldGuard().getRegionContainer();
		RegionManager regions = container.get(player.getWorld());

		try {
			RegionDAO regionDAO = new RegionDAO(sgConfig);

			if (regionDAO.hasRegion(player.getName().toLowerCase() + "_" + regionName.toLowerCase())) {
				player.sendMessage(PrefixYellow + Messages.getString("sg.name_area") + " " + regionName.toLowerCase());
				return;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		Integer initialPositionX = player.getLocation().getBlockX() - sizeX / 2;
		Integer initialPositionY = sgConfig.getMinHeight();
		Integer initialPositionZ = player.getLocation().getBlockZ() - sizeZ / 2;
		Integer finalPositionX = player.getLocation().getBlockX() + sizeX / 2;
		Integer finalPositionY = sgConfig.getMaxHeight();
		Integer finalPositionZ = player.getLocation().getBlockZ() + sizeZ / 2;

		BlockVector blockVectorInitial = new BlockVector(initialPositionX, sgConfig.getMaxHeight(), initialPositionZ);
		BlockVector blockVectorFinal = new BlockVector(finalPositionX, sgConfig.getMinHeight(), finalPositionZ);
		ProtectedCuboidRegion protectedCuboidRegion = new ProtectedCuboidRegion(player.getName().toLowerCase() + "_" + regionName.toLowerCase(), blockVectorInitial, blockVectorFinal);
		DefaultDomain defaultDomain = new DefaultDomain();
		regions.addRegion(protectedCuboidRegion);
		protectedCuboidRegion.setPriority(100);
		defaultDomain.addPlayer(player.getName());
		protectedCuboidRegion.setOwners(defaultDomain);
                protectedCuboidRegion.setFlag(DefaultFlag.PVP, StateFlag.State.ALLOW);

                protectedCuboidRegion.setFlag(DefaultFlag.PVP, StateFlag.State.ALLOW);
                protectedCuboidRegion.setFlag(DefaultFlag.USE, StateFlag.State.DENY);
                protectedCuboidRegion.setFlag(DefaultFlag.ENDER_BUILD, StateFlag.State.DENY);
                protectedCuboidRegion.setFlag(DefaultFlag.CREEPER_EXPLOSION, StateFlag.State.DENY);
                protectedCuboidRegion.setFlag(DefaultFlag.GHAST_FIREBALL, StateFlag.State.DENY);

		ApplicableRegionSet regionss = regions.getApplicableRegions(protectedCuboidRegion);
		LocalPlayer localPlayer = worldGuard.wrapPlayer(player);
		if (econ.getBalance(player.getName()) < sizeX * sgConfig.getBlockPurchasePrice()) {
			player.sendMessage(PrefixRed + Messages.getString("sg.enough_money", sgConfig.getCoinName() + sizeX * sgConfig.getBlockPurchasePrice()));
			regions.removeRegion(player.getName().toLowerCase() + "_" + regionName.toLowerCase());
			return;
		}

		if (!regionss.isOwnerOfAll(localPlayer) && !player.isOp() && !player.hasPermission(SgPermissions.admin)) {
			player.sendMessage(PrefixYellow + Messages.getString("sg.move_away"));
			regions.removeRegion(player.getName().toLowerCase() + "_" + regionName.toLowerCase());
			return;
		}

		
            try {
                regions.save();
            } catch (StorageException ex) {
                Logger.getLogger(SaoGuardCommandExecutor.class.getName()).log(Level.SEVERE, null, ex);
            }
		
		player.sendMessage(PrefixYellow + Messages.getString("sg.buy_are_sucess", ChatColor.RED + sgConfig.getCoinName() + sizeX * sgConfig.getBlockPurchasePrice()));
		player.sendMessage(PrefixYellow + Messages.getString("sg.region_and_size", ChatColor.RED + regionName.toLowerCase() + ChatColor.AQUA, ChatColor.RED + "" + sizeX + ChatColor.AQUA + " x " + ChatColor.RED + sizeZ + ChatColor.AQUA));

		econ.withdrawPlayer(player.getName(), sizeX * sgConfig.getBlockPurchasePrice());

		try {
			PlayerDAO playerDAO = new PlayerDAO(sgConfig);
			RegionDAO regionDAO = new RegionDAO(sgConfig);
			FlagDAO flagDAO = new FlagDAO(sgConfig);

			Region region = new Region();
			region.setOwner(playerDAO.findByName(player.getName()));
			region.setName(regionName.toLowerCase());
			region.setFullName(player.getName().toLowerCase() + "_" + regionName.toLowerCase());
			region.setWorld(player.getWorld().getName());
			region.setInitialPositionX(initialPositionX);
			region.setInitialPositionY(initialPositionY);
			region.setInitialPositionZ(initialPositionZ);
			region.setFinalPositionX(finalPositionX);
			region.setFinalPositionY(finalPositionY);
			region.setFinalPositionZ(finalPositionZ);

			regionDAO.insert(region);
			region.setId(regionDAO.findIdByRegion(region));

			flagDAO.insert(new Flag(DefaultFlag.PVP.getName(), "alow", region));
			flagDAO.insert(new Flag(DefaultFlag.USE.getName(), "deny", region));
			flagDAO.insert(new Flag(DefaultFlag.ENDER_BUILD.getName(), "deny", region));
			flagDAO.insert(new Flag(DefaultFlag.CREEPER_EXPLOSION.getName(), "deny", region));
			flagDAO.insert(new Flag(DefaultFlag.GHAST_FIREBALL.getName(), "deny", region));

		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Levantando a cerca
		if (!noFence) {
			if (sgConfig.getRemoveTree() == true) {
				for (int y = 20; y < 90; y++) {
					for (int x = initialPositionX; x < finalPositionX; x++) {
						for (int z = initialPositionZ; z < finalPositionZ; z++) {
							Block block = new Location(player.getWorld(), x, y, z).getBlock();
							if (block.getType() == Material.LEAVES || block.getType() == Material.LOG) {
								block.setType(Material.AIR);
							}
						}
					}
				}
			}

			if (sgConfig.getAlign() == false) {
				for (int x = initialPositionX; x < finalPositionX; x++) {
					Block block1 = player.getWorld().getHighestBlockAt(x, initialPositionZ);
					Block block2 = player.getWorld().getHighestBlockAt(x, finalPositionZ);

//					block1.setTypeId(sgConfig.getBlockId());
//					block2.setTypeId(sgConfig.getBlockId());
					block1.setType(Material.getMaterial(sgConfig.getBlockName()));
					block2.setType(Material.getMaterial(sgConfig.getBlockName()));

				}

				for (int z = initialPositionZ; z < finalPositionZ; z++) {
					Block block1 = player.getWorld().getHighestBlockAt(initialPositionX, z);
					Block block2 = player.getWorld().getHighestBlockAt(finalPositionX, z);

//					block1.setTypeId(sgConfig.getBlockId());
//					block2.setTypeId(sgConfig.getBlockId());
					block1.setType(Material.getMaterial(sgConfig.getBlockName()));
					block2.setType(Material.getMaterial(sgConfig.getBlockName()));
				}

			} else {
				Integer y = player.getLocation().getBlockY();
				for (int x = initialPositionX; x < finalPositionX; x++) {
					Block block1 = player.getWorld().getBlockAt(x, y, initialPositionZ);
					Block block2 = player.getWorld().getBlockAt(x, y, finalPositionZ);

//					block1.setTypeId(sgConfig.getBlockId());
//					block2.setTypeId(sgConfig.getBlockId());
					block1.setType(Material.getMaterial(sgConfig.getBlockName()));
					block2.setType(Material.getMaterial(sgConfig.getBlockName()));
				}

				for (int z = initialPositionZ; z < finalPositionZ; z++) {
					Block block1 = player.getWorld().getBlockAt(initialPositionX, y, z);
					Block block2 = player.getWorld().getBlockAt(finalPositionX, y, z);

//					block1.setTypeId(sgConfig.getBlockId());
//					block2.setTypeId(sgConfig.getBlockId());
					block1.setType(Material.getMaterial(sgConfig.getBlockName()));
					block2.setType(Material.getMaterial(sgConfig.getBlockName()));
				}
			}

		}

	}


	// TODO revisar
	public void delParede(Player player, int xmin, int xmax, int zmin, int zmax) {
		Location loc = player.getLocation();
		World world = loc.getWorld();

		for (int y = sgConfig.getMinHeight(); y < sgConfig.getMaxHeight(); y++) {
			for (int x = xmin; x < xmax; x++) {
				Block xb = world.getBlockAt(x, y, zmin);
//				if (xb.getTypeId() == sgConfig.getBlockId()) {
//					xb.setTypeId(0);
//				}
				if (sgConfig.getBlockName().equals(xb.getType().toString())) {
					xb.setType(Material.AIR);
				}
			}
			for (int x2 = xmin; x2 <= xmax; x2++) {
				Block xb = world.getBlockAt(x2, y, zmax);
//				if (xb.getTypeId() == sgConfig.getBlockId()) {
//					xb.setTypeId(0);
//				}
				if (sgConfig.getBlockName().equals(xb.getType().toString())) {
					xb.setType(Material.AIR);
				}
			}
			for (int z1 = zmin; z1 < zmax; z1++) {
				Block zb = world.getBlockAt(xmin, y, z1);
//				if (zb.getTypeId() == sgConfig.getBlockId()) {
//					zb.setTypeId(0);
//				}
				if (sgConfig.getBlockName().equals(zb.getType().toString())) {
					zb.setType(Material.AIR);
				}
			}
			for (int z2 = zmin; z2 <= zmax; z2++) {
				Block zb = world.getBlockAt(xmax, y, z2);
//				if (zb.getTypeId() == sgConfig.getBlockId()) {
//					zb.setTypeId(0);
//				}
				if (sgConfig.getBlockName().equals(zb.getType().toString())) {
					zb.setType(Material.AIR);
				}
			}
		}
	}


	// TODO revisar
	public int ifParede(Player player, int xmin, int xmax, int zmin, int zmax) {
		Location loc = player.getLocation();
		World w = loc.getWorld();
		for (int y = sgConfig.getMinHeight(); y < sgConfig.getMaxHeight(); y++) {
			for (int x = xmin; x < xmax; x++) {
				Block xb = w.getBlockAt(x, y, zmin);
//				if (xb.getTypeId() == sgConfig.getBlockId()) {
//					return xb.getY();
//				}
				if (sgConfig.getBlockName().equals(xb.getType().toString())) {
					return xb.getY();
				}
			}
			for (int x2 = xmin; x2 <= xmax; x2++) {
				Block xb = w.getBlockAt(x2, y, zmax);
//				if (xb.getTypeId() == sgConfig.getBlockId()) {
//					return xb.getY();
//				}
				if (sgConfig.getBlockName().equals(xb.getType().toString())) {
					return xb.getY();
				}
			}
			for (int z1 = zmin; z1 < zmax; z1++) {
				Block zb = w.getBlockAt(xmin, y, z1);
//				if (zb.getTypeId() == sgConfig.getBlockId()) {
//					return zb.getY();
//				}
				if (sgConfig.getBlockName().equals(zb.getType().toString())) {
					return zb.getY();
				}
			}
			for (int z2 = zmin; z2 <= zmax; z2++) {
				Block zb = w.getBlockAt(xmax, y, z2);
//				if (zb.getTypeId() == sgConfig.getBlockId()) {
//					return zb.getY();
//				}
				if (sgConfig.getBlockName().equals(zb.getType().toString())) {
					return zb.getY();
				}

			}
		}
		return 0;
	}


	// TODO revisar
	public void expandir(Player player, int xmin, int xmax, int zmin, int zmax, String area, Integer tamanho) {
                RegionContainer container = plugin.getWorldGuard().getRegionContainer();
		RegionManager mgr = container.get(player.getWorld());
		BlockVector bv1 = new BlockVector(xmin - tamanho, sgConfig.getMinHeight(), zmin - tamanho);
		BlockVector bv2 = new BlockVector(xmax + tamanho, sgConfig.getMaxHeight(), zmax + tamanho);
		ProtectedCuboidRegion pr = new ProtectedCuboidRegion(player.getName().toLowerCase() + "_" + area.toLowerCase(), bv1, bv2);
		DefaultDomain dd = new DefaultDomain();
		mgr.addRegion(pr);
		pr.setPriority(100);
		dd.addPlayer(player.getName());
		pr.setOwners(dd);

		pr.setFlag(DefaultFlag.PVP, StateFlag.State.ALLOW);
		pr.setFlag(DefaultFlag.USE, StateFlag.State.DENY);
		pr.setFlag(DefaultFlag.ENDER_BUILD, StateFlag.State.DENY);
		pr.setFlag(DefaultFlag.CREEPER_EXPLOSION, StateFlag.State.DENY);
		
		ApplicableRegionSet regions = mgr.getApplicableRegions(pr);
		LocalPlayer localPlayer = worldGuard.wrapPlayer(player);

		if (!regions.isOwnerOfAll(localPlayer) && (!player.isOp())) {
			player.sendMessage(PrefixRed + Messages.getString("sg.move_away"));
			mgr.removeRegion(player.getName().toLowerCase() + "_" + area.toLowerCase());
			BlockVector bv1n = new BlockVector(xmin, sgConfig.getMinHeight(), zmin);
			BlockVector bv2n = new BlockVector(xmax, sgConfig.getMaxHeight(), zmax);
			pr.setMaximumPoint(bv2n);
			pr.setMinimumPoint(bv1n);
			mgr.addRegion(pr);
			
			pr.setFlag(DefaultFlag.PVP, StateFlag.State.ALLOW);
                        pr.setFlag(DefaultFlag.USE, StateFlag.State.DENY);
                        pr.setFlag(DefaultFlag.ENDER_BUILD, StateFlag.State.DENY);
                        pr.setFlag(DefaultFlag.CREEPER_EXPLOSION, StateFlag.State.DENY);
			
			
                    try {
                        mgr.save();
                    } catch (StorageException ex) {
                        Logger.getLogger(SaoGuardCommandExecutor.class.getName()).log(Level.SEVERE, null, ex);
                    }
			
			return;
		}

		
            try {
                mgr.save();
            } catch (StorageException ex) {
                Logger.getLogger(SaoGuardCommandExecutor.class.getName()).log(Level.SEVERE, null, ex);
            }
		
		delParede(player, xmin, xmax, zmin, zmax);
		paredeEx(player, xmin - tamanho, xmax + tamanho, zmin - tamanho, zmax + tamanho, ifParede(player, xmin, xmax, zmin, zmax));

		econ.withdrawPlayer(player.getName(), tamanho * sgConfig.getBlockPurchaseExpand());

		String tamanhoArea = ChatColor.RED + area.toLowerCase() + ChatColor.AQUA;
		String tamanhoAreaNovo = ChatColor.RED + Integer.toString((tamanho * 2) + xmax - xmin) + ChatColor.AQUA;
		String preco = ChatColor.RED + "[" + sgConfig.getCoinName() + tamanho * sgConfig.getBlockPurchaseExpand() + "]";

		player.sendMessage(ChatColor.AQUA + Messages.getString("sg.expand_success", tamanhoArea, tamanhoAreaNovo, preco));
	}


	// TODO revisar
	public void paredeEx(Player player, int xmin, int xmax, int zmin, int zmax, int y) {
		Location loc = player.getLocation();
		World w = loc.getWorld();
		if (sgConfig.getAlign() == false) {
			for (int x = xmin; x < xmax; x++) {
				Block xb = w.getHighestBlockAt(x, zmin);
//				xb.setTypeId(sgConfig.getBlockId());
				xb.setType(Material.getMaterial(sgConfig.getBlockName()));
			}
			for (int x2 = xmin; x2 <= xmax; x2++) {
				Block xb = w.getHighestBlockAt(x2, zmax);
//				xb.setTypeId(sgConfig.getBlockId());
				xb.setType(Material.getMaterial(sgConfig.getBlockName()));
			}
			for (int z1 = zmin; z1 < zmax; z1++) {
				Block zb = w.getHighestBlockAt(xmin, z1);
//				zb.setTypeId(sgConfig.getBlockId());
				zb.setType(Material.getMaterial(sgConfig.getBlockName()));
			}
			for (int z2 = zmin; z2 <= zmax; z2++) {
				Block zb = w.getHighestBlockAt(xmax, z2);
//				zb.setTypeId(sgConfig.getBlockId());
				zb.setType(Material.getMaterial(sgConfig.getBlockName()));
			}
		} else {
			for (int x = xmin; x < xmax; x++) {
				Block xb = w.getBlockAt(x, y, zmin);
				xb.setType(Material.FENCE);
			}
			for (int x2 = xmin; x2 <= xmax; x2++) {
				Block xb = w.getBlockAt(x2, y, zmax);//
//				xb.setTypeId(sgConfig.getBlockId());
				xb.setType(Material.getMaterial(sgConfig.getBlockName()));
			}
			for (int z1 = zmin; z1 < zmax; z1++) {
				Block zb = w.getBlockAt(xmin, y, z1);
//				zb.setTypeId(sgConfig.getBlockId());
				zb.setType(Material.getMaterial(sgConfig.getBlockName()));
			}
			for (int z2 = zmin; z2 <= zmax; z2++) {
				Block zb = w.getBlockAt(xmax, y, z2);
//				zb.setTypeId(sgConfig.getBlockId());
				zb.setType(Material.getMaterial(sgConfig.getBlockName()));
			}
		}
	}

}
