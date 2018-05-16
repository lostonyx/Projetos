package com.dayz.airdrop;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.criptonnetwork.util.ItemAPI;
import com.dayz.Main;
import com.dayz.utils.FloatingItem;

import de.slikey.effectlib.util.ParticleEffect;

public class AirDropManager {
	public static AirDrop createAirDrop(Location loc, Player p) {
		AirDrop s = new AirDrop(loc);
		s.setLoot(p.getInventory());
		s.setBlockair(Material.NOTE_BLOCK);
		createAnimation(s);
		return s;
	}
	
	public static void explode(Location loc) {
		Bukkit.getOnlinePlayers().forEach(p -> {
			ParticleEffect.EXPLOSION_HUGE.display(2, 2, 2, 0, 0, loc,p);
			p.playSound(loc, Sound.EXPLODE, 10, 10);
		});
		
	
	
	}
	
	
	public static void createAnimation(AirDrop s) {
		Location loc = s.getCords().add(0,50,0);
		
		new BukkitRunnable() {
			private int x = 0;
			public void run() {
				Bukkit.broadcastMessage("1" + x);
				x = x + 1;
				explode(new Location(loc.getWorld() , loc.getX() , loc.getY() - x , loc.getZ()));
				if(x >= 50) {
					cancel();
					FloatingItem f = new FloatingItem(new Location(loc.getWorld() , loc.getX() , loc.getY() - x , loc.getZ()));
					f.spawn(ItemAPI.newItem(Material.CHEST, "§c"), true, "§cAirdrop");
					f.enable(Main.getDayz());
					Bukkit.getScheduler().runTaskLater(Main.getDayz(), () -> {
						f.delete();
					}, 20 * 60);
				}

			}
		}.runTaskTimer(Main.getDayz(), 0, 10);

	}
}
