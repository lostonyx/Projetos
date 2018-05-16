package com.dayz.safezone;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import com.dayz.domination.Domination;
import com.google.common.collect.Lists;

public class SafezoneManager {
	
	
	public static List<Safezone> safezones = Lists.newArrayList();
	
	
	public static List<Safezone> getSafezones(){
		return safezones;
	}
	
	public static Safezone createSafezone(String name , Location loc1 , Location loc2) {
		Safezone s = new Safezone(name , loc1, loc2);
		safezones.add(s);
		return s;
	}
	
	public static boolean isOnSafeZone(Player p) {
		return getCurrentSafezone(p) != null;
	}
	
	public static boolean isOnSafeZoneEntity(LivingEntity e) {
		return getCurrentSafezoneEntity(e) != null;
	}
	
	public static Safezone getCurrentSafezone(Player p) {
		for(Safezone s : getSafezones()) {
			if(s.getC().containsLocation(p.getLocation())) {
				return s;
			}
		}
		
		return null;
	}
	
	public static Safezone getCurrentSafezoneEntity(LivingEntity e) {
		for(Safezone s : getSafezones()) {
			if(s.getC().containsLocation(e.getLocation())) {
				return s;
			}
		}
		
		return null;
	}
	
	public static Safezone getSafezonePerDistance(Player p) {
		for (Safezone s : getSafezones()) {
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
	
	public static Safezone getSafezone(String name) {
		for(Safezone s : safezones) {
			if(s.getNome().equalsIgnoreCase("name")) {
				return s;
			}
		}
		
		return null;
	}
	
}
