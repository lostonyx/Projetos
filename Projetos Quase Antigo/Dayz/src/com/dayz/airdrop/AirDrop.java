package com.dayz.airdrop;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;

public class AirDrop {

	
	private Inventory loot;
	private Location cords;
	private Material blockair;
	
	public AirDrop(Location loc){
		this.cords = loc;
	}

	public Inventory getLoot() {
		return loot;
	}

	public void setLoot(Inventory loot) {
		this.loot = loot;
	}

	public Location getCords() {
		return cords;
	}

	public void setCords(Location cords) {
		this.cords = cords;
	}

	public Material getBlockair() {
		return blockair;
	}

	public void setBlockair(Material blockair) {
		this.blockair = blockair;
	}
}
