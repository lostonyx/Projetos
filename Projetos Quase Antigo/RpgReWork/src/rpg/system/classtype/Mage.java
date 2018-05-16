package rpg.system.classtype;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import rpg.system.models.Classe;
import rpg.utils.ItemAPI;

public class Mage implements Classe {

	@Override
	public String getName() {
		return "Mage";
	}

	@Override
	public ItemStack getIcon() {
		return ItemAPI.newItem(Material.STICK, "§eMage", 1, 0, getDescription());
	}

	@Override
	public String[] getDescription() {
		return new String[] {
				"§earrow arrow arrow jump bla bla bla",
		};
	}

}
