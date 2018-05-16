package me.wiljafor1.saintsup.APIs;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;

import me.wiljafor1.saintsup.Main;

public class CrateSend {

	private Location location;

	public CrateSend(String ID) {
		Location location1 = new Location(Bukkit.getWorld("World"), Main.plugin.getConfig().getInt("Bau." + ID + ".X"),
				Main.plugin.getConfig().getInt("Bau." + ID + ".Y"), Main.plugin.getConfig().getInt("Bau." + ID + ".Z"));
		this.location = location1;
		deletar();
	}

	public CrateSend(Location location, String ID) {
		this.location = location;
		Main.plugin.getConfig().set("Bau." + ID + ".X", location.getBlockX());
		Main.plugin.getConfig().set("Bau." + ID + ".Y", location.getBlockY());
		Main.plugin.getConfig().set("Bau." + ID + ".Z", location.getBlockZ());
		Main.plugin.saveConfig();
		criar();
	}

	public void deletar() {
		this.location.getBlock().setType(Material.AIR);
	}

	private void criar() {
		this.location.getBlock().setType(Material.CHEST);
	}
}