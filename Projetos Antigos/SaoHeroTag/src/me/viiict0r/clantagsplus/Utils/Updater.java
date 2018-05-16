package me.viiict0r.clantagsplus.Utils;

import me.viiict0r.clantagsplus.Main;
import me.viiict0r.clantagsplus.SC;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class Updater implements Listener{
	
	public static void update(final Scoreboard sb){
		
		Thread th = new Thread(new Runnable() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				try {
					for (Player p : Bukkit.getOnlinePlayers()){
						Team t;
						if (sb.getTeam(p.getName().toLowerCase()) != null){
							t = sb.getTeam(p.getName().toLowerCase());
						} else {
							t = sb.registerNewTeam(p.getName().toLowerCase());
						}
						
						if (SC.hasClan(p)){
							String tag = Main.config.getConfig().getString("Tags." + getTag(p));
							String clanFormat = Main.config.getConfig().getString("Clan_Tag_Format").replaceAll("&", "§").replaceAll("@clan", SC.getTag(p));
							String finaltag = tag.replaceAll("@clantag", clanFormat).replaceAll("&", "§");
							if (finaltag.length() > 16){
								finaltag = finaltag.substring(0, 16);
							}
							t.setPrefix(finaltag);
						} else {
							String tag = Main.config.getConfig().getString("Tags." + getTag(p));
							if (tag.length() > 16){
								tag = tag.substring(0, 16);
							}
							String finaltag = tag.replaceAll("&", "§").replace("@clantag", "§");
							if (finaltag.length() > 16){
								finaltag = finaltag.substring(0, 16);
							}
							t.setPrefix(finaltag);
						}
						t.addPlayer(p);
					}
					
				} catch (Exception e){
					e.printStackTrace();
				}
				
			}
		});
		th.start();
		
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		Player p = e.getPlayer();
		update(p.getScoreboard());
	}
	
	public static String getTag(Player p){
		for (String tag : Main.config.getConfig().getConfigurationSection("Tags").getKeys(false)){
			if (p.hasPermission("viclantagsplus." + tag)){
				return tag;
			}
		}
		return null;
	}
}
