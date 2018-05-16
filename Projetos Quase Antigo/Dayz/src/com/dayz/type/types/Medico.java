package com.dayz.type.types;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.criptonnetwork.util.ItemAPI;
import com.dayz.type.Classe;

public class Medico implements Classe{

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Medico";
	}

	@Override
	public ItemStack getIcon() {
		// TODO Auto-generated method stub
		return  ItemAPI.newItem(Material.EMERALD, "§eMedico", 1, 0, getDescription());
	}

	@Override
	public String[] getDescription() {
		// TODO Auto-generated method stub
		return new String[] {
				"§eNão mata nada so serve pra ajuda",
			
		};
	}
	
	
	
	
	

}
