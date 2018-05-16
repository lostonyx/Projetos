package rpg.system.models;

import java.util.List;

import org.bukkit.Location;

import rpg.utils.Cuboid;

public interface City extends Region{
	public Location getSpawn();
	public List<Cuboid> getDoors();
	public int id();
	public String[] getDescription();
	
}
