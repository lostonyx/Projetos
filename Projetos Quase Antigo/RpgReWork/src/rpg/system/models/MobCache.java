package rpg.system.models;

import java.util.ArrayList;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Damageable;

import com.google.common.collect.Maps;

import rpg.mob.Mobs;
import rpg.mob.Mobs.Tipo;
import rpg.system.listener.MobListener;

public class MobCache {

	private String m;
	private Location ultimoloc;
	
	public MobCache(String m, Location loc) {
		this.setM(m);
		this.setUltimoloc(loc);
	}

	/**
	 * @return the m
	 */
	public String getM() {
		return m;
	}

	/**
	 * @param m the m to set
	 */
	public void setM(String m) {
		this.m = m;
	}


	/**
	 * @return the ultimoloc
	 */
	public Location getUltimoloc() {
		return ultimoloc;
	}

	/**
	 * @param ultimoloc the ultimoloc to set
	 */
	public void setUltimoloc(Location ultimoloc) {
		this.ultimoloc = ultimoloc;
	}
	
}
