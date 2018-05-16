package com.dayz.type.types;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.criptonnetwork.util.ItemAPI;
import com.dayz.type.Classe;

public class Sniper implements Classe{

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Sniper";
	}

	@Override
	public ItemStack getIcon() {
		// TODO Auto-generated method stub
		return ItemAPI.newItem(Material.APPLE, "§eSniper", 1, 0, getDescription());
	}

	@Override
	public String[] getDescription() {
		// TODO Auto-generated method stub
		return new String[] {
				"§eMuito apelão",
				"§eDescrição totalmente ilustrativa"
		};
	}

}
