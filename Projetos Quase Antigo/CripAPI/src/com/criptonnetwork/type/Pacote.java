package com.criptonnetwork.type;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.criptonnetwork.util.ExtraAPI;
import com.criptonnetwork.util.ItemAPI;

public class Pacote {
	public static enum PacoteType {
		VIP, CASH, MONEY, COSMETICO
	}

	private String name;
	private int custo;
	private long time;
	private PacoteType type;
	private ItemStack icon;
	
	public Pacote(String name, int custo, long time, PacoteType type) {
		setName(name);
		setCusto(custo);
		setTime(time);
		setType(type);
		
		setIcon(ItemAPI.newItem(Material.AIR, "§e" + name, 1, 0, "§eTempo: §f" + ExtraAPI.formatTime(time) ,
				"§eCusto: §fR$ " + custo ,
				"§eTipo: §f" + type.name()));
		

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCusto() {
		return custo;
	}

	public void setCusto(int custo) {
		this.custo = custo;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public PacoteType getType() {
		return type;
	}

	public void setType(PacoteType type) {
		this.type = type;
	}

	public ItemStack getIcon() {
		return icon;
	}

	public void setIcon(ItemStack icon) {
		this.icon = icon;
	}

}
