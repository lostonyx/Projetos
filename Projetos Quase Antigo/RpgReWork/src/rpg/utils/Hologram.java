package rpg.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Damageable;
import org.bukkit.scheduler.BukkitRunnable;



public class Hologram {

	public static ArmorStand hologram(Location loc, String s) {
		ArmorStand a = loc.getWorld().spawn(loc, ArmorStand.class);
		a.setArms(false);
		a.setGravity(false);
		a.setVisible(false);
		a.setMarker(true);
		a.setSmall(true);
		a.setCustomNameVisible(true);
		a.setCustomName(s);

		return a;
	}

	public static ArmorStand spawnHealthBar(Damageable i) {
		ArmorStand a = i.getWorld().spawn(i.getLocation(), ArmorStand.class);
		a.setArms(false);
		a.setGravity(false);
		a.setVisible(false);
		a.setSmall(true);
		a.setMarker(true);
		a.setCustomNameVisible(true);
		a.setCustomName(ProgressBar.getProgressBar((int) i.getHealth(), (int) i.getMaxHealth(), 20, "|", "§a", "§c"));
		i.setPassenger(a);

		return a;

	}

	public static void spawnHologram(Location loc, int time, String... str) {
		for (String s : str) {
			ArmorStand a = hologram(loc.add(0, -0.05, 0), s);

			
		}

	}
}
