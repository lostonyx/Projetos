package com.criptonnetwork.cosmeticos.music;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.criptonnetwork.cosmeticos.CosmeticType;
import com.criptonnetwork.cosmeticos.Cosmetico;
import com.criptonnetwork.util.CItem;

public class Sao implements Cosmetico{

	@Override
	public void use(Player p) {
		
	}

	@Override
	public String getName() {
		return "Sword Art Online Tema";
	}

	@Override
	public CosmeticType getType() {
		return CosmeticType.MUSIC;
	}

	@Override
	public ItemStack getIcon() {
		return new CItem(Material.RECORD_3).build();
	}

}
