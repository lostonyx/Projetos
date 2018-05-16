package com.dayz.type.types;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.criptonnetwork.util.ItemAPI;
import com.dayz.type.Classe;

public class Fuzileiro implements Classe {

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Fuzileiro";
	}

	@Override
	public ItemStack getIcon() {
		// TODO Auto-generated method stub
		return ItemAPI.newItem(Material.APPLE, "§eFuzileiro", 1, 0, getDescription());
	}

	@Override
	public String[] getDescription() {
		// TODO Auto-generated method stub
		return new String[] {
				"§eSenta o dedo na AK",
				"§eDescrição totalmente ilustrativa"
		};
	}

}
