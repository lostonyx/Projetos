package me.wiljafor1.interfaces;

import java.util.Map;

import org.bukkit.Location;

import me.wiljafor1.Config;
import me.wiljafor1.system.mobs.Mobs;
import me.wiljafor1.utils.Cuboid;



public interface Region {

	public Cuboid getRegion();
	public Cuboid preRegion();
	public String getName();
	public Config getConfig();
	public boolean isCity();
	public boolean isDungeon();
	public boolean isQuest();
	public boolean haveBGM();
	public int BGMInSeconds();
	public String getNBS();
	public Runnable init();
}
