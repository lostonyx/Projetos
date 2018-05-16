package com.dayz;

import java.util.List;
import java.util.Map.Entry;
import java.util.Random;

import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.criptonnetwork.RegisterUtils;
import com.criptonnetwork.database.Database;
import com.criptonnetwork.util.ProgressBar;
import com.criptonnetwork.util.SasukeScore;
import com.dayz.api.HTTPServerThread;
import com.dayz.domination.DominationListener;
import com.dayz.domination.WorldBorderListener;
import com.dayz.type.Account;
import com.dayz.type.Classe;
import com.dayz.type.ClasseManager;
import com.dayz.type.Jogador;
import com.dayz.type.JogadorManager;
import com.dayz.utils.motdAPI;
import com.google.common.collect.Lists;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin {
	public static Main instacia;
	private HTTPServerThread serverThread;
	public void onEnable() {
		instacia = this;
		Bukkit.getScheduler().runTaskTimer(this, () -> {
			update();
			DominationListener.checkdomination();
			WorldBorderListener.checkWorldBorder();
		}, 0, 20);
		Bukkit.getWorlds().forEach(w -> {
			w.getEntities().forEach(entity -> {
				if(entity.getType() != EntityType.PLAYER) {
					entity.remove();
				}
			});
			
		});
		
		Bukkit.getOnlinePlayers().forEach(d -> {
			d.kickPlayer(ChatColor.RED+"Reniciando \nAguarde!!!");
			JogadorManager.createOne(d);
		});

		RegisterUtils.getPackages(getFile(), "com.dayz").forEach(c -> {
			
			if(Classe.class.isAssignableFrom(c)) {
				try {
					ClasseManager.add((Classe)c.newInstance());
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			
			if (Listener.class.isAssignableFrom(c)) {
				try {
					getServer().getPluginManager().registerEvents((Listener) c.newInstance(), this);
				} catch (Exception e) {
					getServer().getConsoleSender().sendMessage(ChatColor.RED + ":" + e.getMessage());
				}
			}

			if (Command.class.isAssignableFrom(c)) {
				try {
					RegisterUtils.createCommand((Command) c.newInstance());
				} catch (Exception e) {
					getServer().getConsoleSender().sendMessage(ChatColor.RED + ":" + e.getMessage());
				}
			}

		});
		try {
			serverThread = new HTTPServerThread(this);
			serverThread.start();
		} catch (Exception e2) {
			
		}
	}
	
	public void onDisable(){
    	serverThread.stopSocket();
    	serverThread.stop();
	}
	
	
	public static void update() {
		String[] z = new String[] {"§c" , "§6" , "§e" , "§f"} ;
		for (Entry<Player, SasukeScore> set : Jogador.scores.entrySet()) {
			Player player = set.getKey();
			SasukeScore s = set.getValue();
			Jogador j = JogadorManager.getJogador(player);
			if(j.getCurrentAccount() != null) {
				Account a = j.getCurrentAccount();
				s.update("§4" , 11);
				s.update("§fVida: §c"+ a.getVida().intValue() +"/100", 10);
				s.update("§1" , 9);
				s.update("§fKills: §c" + a.getKills() , 8);
				s.update("§fDeaths: §c" + a.getDeaths() , 7);
				s.update("§fZombie Kills: §c" + a.getTotalzombies(),  6);
				s.update("§2", 5);
				s.update("§fLevel: §a" + a.getLevel(), 4);
				s.update("§fProgresso: §a" + a.getXp() +"/"+ (a.getLevel() * a.getLevel() )* 500 , 3);
				s.update("   §8[" + ProgressBar.getProgressBar(a.getXp(), (a.getLevel() * a.getLevel()) * 500, 10, "+", "§2", "§4") + "§8]" , 2);
				s.update("§3" , 1);
				s.update("§fMoney: §c" + a.getMoney() , 0);
				player.getScoreboard().getObjective(DisplaySlot.SIDEBAR).setDisplayName(z[new Random().nextInt(z.length)] + "§l" + "CRIPTON DAYZ");
			}else {
				player.sendTitle(" ", "§eEscolha seu Personagem");
			}
		
		}
	}

	public static Main getDayz() {
		return instacia;
	}


	
	
}
