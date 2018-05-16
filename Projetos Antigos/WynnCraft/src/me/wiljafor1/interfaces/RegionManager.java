package me.wiljafor1.interfaces;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.xxmicloxx.NoteBlockAPI.NBSDecoder;
import com.xxmicloxx.NoteBlockAPI.RadioSongPlayer;
import com.xxmicloxx.NoteBlockAPI.Song;
import com.xxmicloxx.NoteBlockAPI.SongPlayer;

import me.wiljafor1.Main;
import me.wiljafor1.system.mobs.Mobs;
import me.wiljafor1.utils.Cooldown;
import me.wiljafor1.utils.SimpleRunnable;
import me.wiljafor1.utils.SimpleRunnable.TaskType;


public class RegionManager {
	public static List<Region> regions = Lists.newArrayList();
	public static Map<String, Integer> playerinregion = Maps.newHashMap();
	public static Map<String, Boolean> regionhasplayer = Maps.newHashMap();
	public static Map<String, Boolean> regionhasmob = Maps.newHashMap();
	public static List<String> regionpreload = Lists.newArrayList();
	
	////
	
	public static Map<Location, Mobs> cachemob = Maps.newHashMap();
	//public static Map<Mobs, Integer> cachemobamount = Maps.newHashMap();
	
	public static void addOne(Region r) {
		regions.add(r);
		SimpleRunnable rr = SimpleRunnable.getInstance();
		rr.createTaskLater(TaskType.SYNC, r.getName(), r.init(), 0);
		if(!r.getConfig().existeConfig()) {
			r.getConfig().saveConfig();
		}
	}
	
	public static Region getRegion(String name) {
		for(Region r : regions) {
			if(r.getName().equalsIgnoreCase(name)){
				return r;
			}
		}
		return null;
	}
	
	public static Region getCityId(int id) {
		for(Region r : regions) {
			City rr = (City) r;
			if (rr.id() == id) {
				return r;
			}
		}
		return null;
	}
	
	public static void CheckBGM(){
		Bukkit.getOnlinePlayers().forEach( v -> {
			if(getCurrentRegion(v.getLocation()) != null) {
				Region r = getCurrentRegion(v.getLocation());
				if(r.haveBGM()) {
					String cooldownName = "theme";
					int timeInSeconds = r.BGMInSeconds();
					Cooldown c = new Cooldown(v.getUniqueId(), cooldownName, timeInSeconds);
					if (!c.isInCooldown(cooldownName)) {
						c.start();
						Song s = NBSDecoder.parse(new File(Main.GetInstance().getDataFolder()+"/nbs/", r.getNBS()));
						SongPlayer sp = new RadioSongPlayer(s);
						sp.setAutoDestroy(true);
						sp.addPlayer(v);
						sp.setFadeDone(1);
						sp.setPlaying(true);
					}	
				}
			}
			else {
				String cooldownName = "theme";
				int timeInSeconds = 35;
				Cooldown c = new Cooldown(v.getUniqueId(), cooldownName, timeInSeconds);
				if (!c.isInCooldown(cooldownName)) {
					c.start();
					Song s = NBSDecoder.parse(new File(Main.GetInstance().getDataFolder()+"/nbs/", "ad.nbs"));
					SongPlayer sp = new RadioSongPlayer(s);
					sp.setAutoDestroy(true);
					sp.addPlayer(v);
					sp.setFadeDone(1);
					sp.setPlaying(true);
				}		
			}
		});
	}
	
	public static Region getCurrentRegion(Location loc) {//Cuboid
		for(Region r : regions) {
			if (r.getRegion().containsLocation(loc)) {
				return r;
			}
		}
		return null;
	}
	
	public static Region getPreRegion(Location loc) {//PreCuboid
		for(Region r : regions) {
			if (r.preRegion().containsLocation(loc)) {
				return r;
			}
		}
		return null;
	}
}
