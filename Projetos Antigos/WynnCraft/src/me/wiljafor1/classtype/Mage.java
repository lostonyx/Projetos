package me.wiljafor1.classtype;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import me.wiljafor1.models.Classe;
import me.wiljafor1.utils.ItemAPI;

public class Mage implements Classe {

	@Override
	public String getName() {
		return "Mage";
	}

	@Override
	public ItemStack getIcon() {
		return ItemAPI.newItem(Material.BIRCH_DOOR_ITEM, "§eMage", 1, 0, getDescription());
	}

	@Override
	public String[] getDescription() {
		return new String[] {
				"§earrow arrow arrow jump bla bla bla",
		};
	}

}
