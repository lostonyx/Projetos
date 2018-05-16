package me.wiljafor1.models;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import com.google.common.collect.Lists;

public class GamerManager implements Listener {

	public static List<Gamer> gamers = Lists.newArrayList();

	public static Gamer createOne(Player player) {
		Gamer g = new Gamer(player);
		gamers.add(g);
		return g;
	}

	public static Gamer getGamer(Player player) {

		for (Gamer g : gamers) {
			if (g.getPlayer().equals(player)) {
				return g;
			}
		}

		return null;

	}

	public static Player getPlayer(String adress) {
		for (Player p : Bukkit.getOnlinePlayers()) {
			if (p.getAddress().toString().replace("/", "").split(":")[0].equalsIgnoreCase(adress)) {
				return p;
			}
		}

		return null;
	}

	public static Gamer getGamerByIP(String ip) {
		for (Gamer g : gamers) {
			if (g.getLastAdress().equals(ip)) {
				return g;
			}
		}
		return null;

	}

	public static void remove(Gamer g) {
		gamers.remove(g);
	}

}
