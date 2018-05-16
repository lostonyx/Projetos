package rpg.system.models;

import java.util.ArrayList;
import java.util.Map;

import org.bukkit.Location;

import rpg.Config;
import rpg.mob.Mobs;
import rpg.utils.Cuboid;



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
	public ArrayList<Mobs> mobsproximos();
	
}
