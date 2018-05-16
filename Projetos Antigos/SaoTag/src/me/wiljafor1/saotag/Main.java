package me.wiljafor1.saotag;

import java.io.File;
import java.util.HashMap;

import me.wiljafor1.Utils.ConfigAccesor;
import me.wiljafor1.Utils.Updater;
import net.sacredlabyrinth.phaed.simpleclans.SimpleClans;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Main extends JavaPlugin{
	
	public static Main plugin;
	public static ConfigAccesor config;
	public static HashMap<String, Integer> tempoDeUpdate;
	
	public void onEnable(){
		config = new ConfigAccesor(this, "config.yml");
		
		Bukkit.getConsoleSender().sendMessage("§3<--------- §bSaoTags §3-------->");
                if (Bukkit.getPluginManager().getPlugin("SimpleClans") != null)
                {
                    getLogger().info("SimpleClans detectado!");
                }
                else
                {
                    getLogger().info("SimpleClans nao detectado. Plugin desativado.");
                    Bukkit.getPluginManager().disablePlugin(this);
                }
		getServer().getPluginManager().registerEvents(new Updater(), this);
		getServer().getConsoleSender().sendMessage("§3[SaoTags] §bSaoTags habilitado com sucesso, §fv" + getDescription().getVersion());
		loadConfig();
		configure();
		Bukkit.getConsoleSender().sendMessage("§3<--------- §bSaoTags §3-------->");
	}
	
	public void onDisable(){
		System.out.println("[SaoTags] Plugin desativado");
		
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
	

}
