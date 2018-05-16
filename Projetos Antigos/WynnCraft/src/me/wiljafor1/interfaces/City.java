package me.wiljafor1.interfaces;

import java.util.List;

import org.bukkit.Location;

import me.wiljafor1.utils.Cuboid;

public interface City extends Region{
	public Location getSpawn();
	public List<Cuboid> getDoors();
	public int id();
	
}
