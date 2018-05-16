package me.viiict0r.clantagsplus;

import net.sacredlabyrinth.phaed.simpleclans.ClanPlayer;
import net.sacredlabyrinth.phaed.simpleclans.SimpleClans;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.p000ison.dev.simpleclans2.api.SCCore;
import com.p000ison.dev.simpleclans2.api.clanplayer.ClanPlayerManager;

public class SC {
	
	public static SCCore core;
	public static SimpleClans sc;

	public static boolean hookSimpleClans()
	    {
	        try {
	            for (Plugin plugin : Main.plugin.getServer().getPluginManager().getPlugins()) {
	                if (plugin instanceof SCCore) {
	                    core = (SCCore) plugin;
	                    return true;
	                }
	            }
	        } catch (NoClassDefFoundError e) {
	            return false;
	        }

	        return false;
	}

	public static ClanPlayerManager getClanPlayerManager()
	 {
	        return core.getClanPlayerManager();
	 }

	public static com.p000ison.dev.simpleclans2.api.clan.ClanManager getClanManager()
	 {
	        return core.getClanManager();
	 }
	public static String getTag(Player p){
		
		String tagcolor = null;
		String tag = null;
		ClanPlayer cp = sc.getClanManager().getClanPlayer(p);
		if (cp != null){
			tagcolor = cp.getTagLabel().replaceAll("[\\[\\]\\s.]", "").replaceAll("(§([0-9|a-f|r]))+", "$1");
			tag = tagcolor.substring(0, tagcolor.length() - 2);
			return tag.replaceAll("&", "§");
		} else {
			tag = "";
			return tag;
		}
	}
	public static boolean hasClan(Player p){
		ClanPlayer cp = sc.getClanManager().getClanPlayer(p);
		if (cp != null){
			return true;
		} else {
			return false;
		}
	}

}
