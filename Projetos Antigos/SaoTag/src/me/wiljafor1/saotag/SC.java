package me.wiljafor1.saotag;

import net.sacredlabyrinth.phaed.simpleclans.ClanPlayer;
import net.sacredlabyrinth.phaed.simpleclans.SimpleClans;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import net.sacredlabyrinth.phaed.simpleclans.Clan;
import net.sacredlabyrinth.phaed.simpleclans.SimpleClans;
import net.sacredlabyrinth.phaed.simpleclans.managers.ClanManager;

public class SC {

	public static String getTag(Player p){
		
		String tagcolor = null;
		String tag = null;
                ClanManager clanManager = SimpleClans.getInstance().getClanManager();
                boolean hasClan = clanManager.getClanPlayer(p) != null;
		ClanPlayer cp = clanManager.getClanPlayer(p);
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
                ClanManager clanManager = SimpleClans.getInstance().getClanManager();
		ClanPlayer cp = clanManager.getClanPlayer(p);
		if (cp != null){
			return true;
		} else {
			return false;
		}
	}

}
