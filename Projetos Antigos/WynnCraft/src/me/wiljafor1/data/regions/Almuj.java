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

public class Almuj implements City {

	@Override
	public Cuboid getRegion() {
		return new Cuboid(new Location(getSpawn().getWorld(), 838.579, 0, -2055.534), new Location(getSpawn().getWorld(), 1066.638, 255, -1858.205));
	}

	@Override
	public String getName() {
		return "almuj";
	}

	@Override
	public Config getConfig() {
		return new Config("almuj.yml");
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
	public int BGMInSeconds() {
		return 93;
	}

	@Override
	public String getNBS() {
		return "almuj.nbs";
	}

	@Override
	public Location getSpawn() {
		return new Location(Bukkit.getWorld("world"), 470, 68, -1581.954);
	}

	@Override
	public List<Cuboid> getDoors() {
		return null;
	}

	@Override
	public int id() {
		return 3;
	}


	@Override
	public Cuboid preRegion() {
		return new Cuboid(new Location(getSpawn().getWorld(), 838.579, 0, -2055.534).add(50, 0, 50), new Location(getSpawn().getWorld(), 1066.638, 255, -1858.205).add(-50, 0, -50));
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
