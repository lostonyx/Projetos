package com.criptonnetwork.cosmeticos;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface Cosmetico{
	
	
	public void use(Player p);
	public String getName();
	public ItemStack getIcon();
	public CosmeticType getType();
	
	
	

}
