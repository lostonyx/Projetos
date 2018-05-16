package com.dayz.type;

import java.util.List;

import org.bukkit.entity.Player;

import com.google.common.collect.Lists;

public class GangManager {
	public static List<Gang> gangs = Lists.newArrayList();

	public static Gang createOne(String name) {
		Gang j = new Gang(name);
		gangs.add(j);
		return j;
	}
	
	public static List<Gang> getJogadores(){
		return gangs;
	}

	public static Gang getGang(String name) {
		for (Gang j : gangs) {
			if (j.getName().equalsIgnoreCase(name)) {
				return j;
			}
		}
		return null;
	}

	public static void remove(Gang j) {
		gangs.remove(j);
	}
}
