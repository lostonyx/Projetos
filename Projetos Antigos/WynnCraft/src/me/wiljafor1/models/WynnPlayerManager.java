package me.wiljafor1.models;

import java.util.List;

import org.bukkit.entity.Player;


import com.google.common.collect.Lists;

public class WynnPlayerManager {

	public static List<WynnPlayer> wynns = Lists.newArrayList();
	public static List<Player> regenhealth = Lists.newArrayList();
	public static List<Player> regenmana = Lists.newArrayList();

	public static WynnPlayer createOne(Player player) {
		WynnPlayer w = new WynnPlayer(player);
		wynns.add(w);
		return w;
	}
	
	public static List<WynnPlayer> getJogadores(){
		return wynns;
	}

	public static WynnPlayer getWynnPlayer(Player player) {
		for (WynnPlayer w : wynns) {
			if (w.getPlayer().equals(player)) {
				return w;
			}
		}
		return null;
	}

	public static void remove(WynnPlayer w) {
		wynns.remove(w);
	}
}
