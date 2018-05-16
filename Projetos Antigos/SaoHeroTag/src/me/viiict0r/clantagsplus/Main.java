package me.viiict0r.clantagsplus;

import java.io.File;
import java.util.HashMap;

import me.viiict0r.clantagsplus.Utils.ConfigAccesor;
import me.viiict0r.clantagsplus.Utils.Updater;
import net.sacredlabyrinth.phaed.simpleclans.SimpleClans;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Main extends JavaPlugin{
	
	public static Main plugin;
	public static ConfigAccesor config;
	public static HashMap<String, Integer> tempoDeUpdate = new HashMap<String, Integer>();
	
	public void onEnable(){
		config = new ConfigAccesor(this, "config.yml");
		plugin = this;
		
		Bukkit.getConsoleSender().sendMessage("§3<--------- §bvIClanTagsPLUS §3-------->");
		if (SC.hookSimpleClans()){
			Bukkit.getConsoleSender().sendMessage("§3[vIClanTagsPLUS] §bSimpleClans encontrado.");
		} else {
			if (getServer().getPluginManager().getPlugin("SimpleClans") != null){
				SC.sc = (SimpleClans) getServer().getPluginManager().getPlugin("SimpleClans"); 
				Bukkit.getConsoleSender().sendMessage("§3[vIClanTagsPLUS] §bSimpleClans encontrado.");
			} else {
				Bukkit.getConsoleSender().sendMessage("§3[vIClanTagsPLUS] §bSimpleClans nao encontrado, desabilitando.");
				getServer().getPluginManager().disablePlugin(this);
				return;
			}
		}
		getServer().getPluginManager().registerEvents(new Updater(), this);
		getServer().getConsoleSender().sendMessage("§3[vIClanTagsPLUS] §bvIClanTagsPLUS habilitado com sucesso, §fv" + getDescription().getVersion() + " §bBy Viiict0r");
		loadConfig();
		configure();
		saveHelp();
		Bukkit.getConsoleSender().sendMessage("§3<--------- §bvIClanTagsPLUS §3-------->");
	}
	
	public void onDisable(){
		System.out.println("[vIClanTagsPLUS] Plugin desativado");
		
	}
	
	public void loadConfig(){
		File f = new File(getDataFolder(), "config.yml");
		if (!f.exists()){
			config.getConfig().options().copyDefaults(true);
			config.saveDefaultConfig();
		}
		config.saveConfig();
		tempoDeUpdate.put("UpdateDelay", getConfig().getInt("Tempo_de_update"));
	}
	
	public void configure(){
		new BukkitRunnable() {
			
			@Override
			public void run() {
				for (Player p : Bukkit.getOnlinePlayers()){
					Updater.update(p.getScoreboard());
				}
				
			}
		}.runTaskTimer(this, 0, 20*tempoDeUpdate.get("UpdateDelay"));
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player){
			if (label.equalsIgnoreCase("ctp") || label.equalsIgnoreCase("clantagsplus")){
				Player p = (Player) sender;
				if (p.hasPermission("viclantagsplus.admin")){
					if (args.length == 0){
						if (p.hasPermission("viclantagsplus.admin")){
							p.sendMessage("§3vIClanTagsPLUS - §fv" + getDescription().getVersion());
							p.sendMessage("§b» §7/ctp reload - Recarregar a config");
							p.sendMessage("§b» §7/ctp creditos - Mostrar os creditos");
						} else {
							p.sendMessage("§3[vIClanTagsPLUS] §bBy Viiict0r");
						}
					} else {
						if (args[0].equalsIgnoreCase("reload")){
							config.reloadConfig();
							p.sendMessage("§aConfiguraçao recarregada com sucesso");
							tempoDeUpdate.put("UpdateDelay", getConfig().getInt("Tempo_de_update"));
							return false;
						}
						if (args[0].equalsIgnoreCase("creditos")){
							p.sendMessage("§7Criado por: §aViiict0r | Skype: SlipKnoTPacks");
							return false;
						}
						p.sendMessage("§3vIClanTagsPLUS - §fv" + getDescription().getVersion());
						p.sendMessage("§b» §7/ctp reload - Recarregar a config");
						p.sendMessage("§b» §7/ctp creditos - Mostrar os creditos");
					}
				} else {
					p.sendMessage("§3[vIClanTagsPLUS] §bBy Viiict0r");
				}
			}
		}
		return false;
	}
	public void saveHelp(){
		File help = new File(getDataFolder(), "Importante.txt");
		if (!help.exists()){
			saveResource("Importante.txt", true);
		}
	}

}
