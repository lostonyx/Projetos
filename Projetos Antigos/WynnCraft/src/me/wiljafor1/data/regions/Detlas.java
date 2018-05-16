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

public class Detlas implements City {

	@Override
	public Cuboid getRegion() {
		return new Cuboid(new Location(getSpawn().getWorld(), 386.349, 0, -1503.465), new Location(getSpawn().getWorld(), 549.459, 255, -1661.554));
	}

	@Override
	public String getName() {
		return "Detlas";
	}

	@Override
	public Config getConfig() {
		return new Config("detlas.yml");
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
		return 140;
	}

	@Override
	public String getNBS() {
		return "detlas.nbs";
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
		return 2;
	}


	@Override
	public Cuboid preRegion() {
		return new Cuboid(new Location(getSpawn().getWorld(), 386.349, 0, -1503.465).add(50, 0, 50), new Location(getSpawn().getWorld(), 549.459, 255, -1661.554).add(50, 0, 50));
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
