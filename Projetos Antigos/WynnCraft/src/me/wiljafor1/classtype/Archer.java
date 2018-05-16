package me.wiljafor1.classtype;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import me.wiljafor1.models.Classe;
import me.wiljafor1.utils.ItemAPI;

public class Archer implements Classe {

	@Override
	public String getName() {
		return "Archer";
	}

	@Override
	public ItemStack getIcon() {
		return ItemAPI.newItem(Material.BOW, "§eArcher", 1, 0, getDescription());
	}

	@Override
	public String[] getDescription() {
		return new String[] {
				"§earrow arrow arrow jump bla bla bla",
		};
	}

}
