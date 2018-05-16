package me.wiljafor1.data.regions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import com.google.common.collect.Maps;

import me.wiljafor1.Config;
import me.wiljafor1.interfaces.City;
import me.wiljafor1.system.cache.PreCuboId;
import me.wiljafor1.system.mobs.Mobs;
import me.wiljafor1.utils.Cuboid;
import net.minecraft.server.v1_8_R3.EntityLiving;

public class Ragni implements City {

	@Override
	public Cuboid getRegion() {
		return new Cuboid(new Location(getSpawn().getWorld(), -740.250, 255, -1754.318), new Location(getSpawn().getWorld(), -943.700, 0, -1417.700));
	}

	@Override
	public String getName() {
		return "Ragni";
	}

	@Override
	public Config getConfig() {
		return new Config("ragni.yml");
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
		return "ragni.nbs";
	}

	@Override
	public Location getSpawn() {
		return new Location(Bukkit.getWorld("world"), -882.104, 68, -1575.961);
	}

	@Override
	public List<Cuboid> getDoors() {
		return null;
	}

	@Override
	public int BGMInSeconds() {
		return 70;
	}

	@Override
	public int id() {
		return 1;
	}

	@Override
	public Cuboid preRegion() {
		return new Cuboid(new Location(getSpawn().getWorld(), -633, 0, -1841), new Location(getSpawn().getWorld(), -981, 255, -1348));
	}

	@Override
	public Runnable init() {
		return new Runnable() {
			public void run() {
				PreCuboId pc = PreCuboId.Getinstance();
				//pc.registermob("Guard", new Location(Bukkit.getWorld("world"), -758.395, 67.0, -1573.537));
				//pc.registermob("Guard", new Location(Bukkit.getWorld("world"), -758.500, 67.0, -1578.300));
				
				//pc.registermob("Guard", new Location(Bukkit.getWorld("world"), -878.500, 67.0, -1451.500));
				//pc.registermob("Guard", new Location(Bukkit.getWorld("world"), -884.500, 67.0, -1451.500));
				
				//pc.registermob("Citizen", new Location(Bukkit.getWorld("world"), -816.500, 67.0, -1575.500));
				pc.registermob("CitizenN", new Location(Bukkit.getWorld("world"), -820.500, 67.0, -1575.500));
			}
		};
	}

}
