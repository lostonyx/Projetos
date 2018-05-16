package com.dayz.domination;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.google.common.collect.Lists;

public class DominationManager {

	public static List<Domination> dominations = Lists.newArrayList();

	public static List<Domination> getDominations() {
		return dominations;
	}

	public static Domination createDomination(String name, Location loc1, Location loc2, int time) {// Cria
																									// uma
																									// area
		Domination s = new Domination(name, loc1, loc2, time);
		dominations.add(s);
		return s;
	}

	public static boolean isOnDomination(Player p) {// Verifica se o player esta
													// na domination
		return getCurrentDomination(p) != null;
	}

	public static Domination setDominationPlayer(Player p) {// Seta o nome da
															// pessoa que
															// dominou a area
		for (Domination s : getDominations()) {
			if (s.getC().containsLocation(p.getLocation())) {
				s.setPlayerName(p.getName());
			}
		}
		return null;
	}

	public static Domination getCurrentDomination(Player p) {// Pegar area pela
																// loc do player
		for (Domination s : getDominations()) {
			if (s.getC().containsLocation(p.getLocation())) {
				return s;
			}
		}
		return null;
	}

	public static Domination getDominationPerDistance(Player p) {//Pegar uma distancia por um certa distancia
		for (Domination s : getDominations()) {
			Location loc1 = s.getCenter();
			Location loc2 = p.getLocation();
			loc1.setY(25);
			loc2.setY(25);
			if (loc1.distance(loc2) < 85) {
				return s;
			}
		}
		return null;
	}

	public static Domination getDomination(String name) {// Pega a area por nome
		for (Domination s : dominations) {
			if (s.getNome().equalsIgnoreCase("name")) {
				return s;
			}
		}
		return null;
	}

}
