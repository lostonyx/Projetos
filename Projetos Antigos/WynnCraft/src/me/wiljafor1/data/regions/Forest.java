package me.wiljafor1.data.regions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import me.wiljafor1.Config;
import me.wiljafor1.interfaces.City;
import me.wiljafor1.system.mobs.Mobs;
import me.wiljafor1.utils.Cuboid;
import net.minecraft.server.v1_8_R3.EntityLiving;

public class Forest implements City {

	@Override
	public Cuboid getRegion() {
		return new Cuboid(new Location(getSpawn().getWorld(), 875.668, 0, -1531.358), new Location(getSpawn().getWorld(), 771.487, 255, -1665.395));
	}

	@Override
	public String getName() {
		return "Forest";
	}

	@Override
	public Config getConfig() {
		return new Config("forest.yml");
	}

	@Override
	public boolean isCity() {
		return true;
	}

	@Override
	public boolean isDungeon() {
		return false;
	}

	@Override
	public boolean isQuest() {
		return false;
	}

	@Override
	public boolean haveBGM() {
		return true;
	}

	@Override
	public String getNBS() {
		return "forest.nbs";
	}

	@Override
	public Location getSpawn() {
		return new Location(Bukkit.getWorld("world"), 815.350, 77, -1596.628);
	}

	@Override
	public List<Cuboid> getDoors() {
		return null;
	}

	@Override
	public int BGMInSeconds() {
		return 140;
	}

	@Override
	public int id() {
		return 4;
	}


	@Override
	public Cuboid preRegion() {
		return new Cuboid(new Location(getSpawn().getWorld(), 875.668, 0, -1531.358).add(50, 0, 50), new Location(getSpawn().getWorld(), 771.487, 255, -1665.395).add(-50, 0, -50));
	}

	@Override
	public Runnable init() {
		// TODO Auto-generated method stub
		return new Runnable() {
			public void run() {
				
			}
		};
	}
}
