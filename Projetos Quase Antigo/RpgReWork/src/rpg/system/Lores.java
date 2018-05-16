package rpg.system;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import rpg.utils.Extra;
import rpg.utils.HCStrings;
import rpg.utils.ItemAPI;


public class Lores {

	public static ItemStack rpgItem(Material mat , String name , String... lore) {
		ItemStack i = ItemAPI.newItem(mat, name, 1, 0, lore);
		ItemMeta m = i.getItemMeta();
		m.addItemFlags(ItemFlag.HIDE_ATTRIBUTES , ItemFlag.HIDE_ENCHANTS);
		i.setItemMeta(m);
		
		return i;
	}

	public static String getIndex(List<String> yes, String index) {
		for (int i = 0; i < yes.size(); i++) {
			if (yes.get(i).contains(index)) {
				return yes.get(i);
			}
		}
		return null;
	}

	public static boolean containsAtribute(String atr, ItemStack item) {
		if (item != null) {
			if (item.hasItemMeta()) {
				ItemMeta m = item.getItemMeta();
				if (m.hasLore()) {
					if (getIndex(m.getLore(), atr) != null) {
						return true;
					}
				}
			}
		}

		return false;
	}
	
	public static int getRandomValue(ItemStack item , String atr) {
		if(item != null) {
			if(containsAtribute(atr, item)) {
				if(getIndex(item.getItemMeta().getLore(), atr).contains("-")) {
					String indice = getIndex(item.getItemMeta().getLore(), atr);
					indice = ChatColor.stripColor(indice);
					int min = 0;
					int max = 0;
					if(HCStrings.isInt(indice.split("-")[1])) {
						max = Integer.parseInt(indice.split("-")[1]);
					}
					if(HCStrings.isInt(indice.split("-")[0])) {
						min = Integer.parseInt(indice.split("-")[0]);
					}
					return Extra.getRandomInt(min, max);
				}
			}
		}
		
		return 0;
	}
	
	public static int getAtrValue(ItemStack item, String atr) {
		if (item != null) {
			if (item.hasItemMeta()) {
				ItemMeta m = item.getItemMeta();
				if (m.hasLore()) {
					for (int i = 0; i < m.getLore().size(); i++) {
						if (m.getLore().get(i).contains(atr)) {
							String f = ChatColor.stripColor(m.getLore().get(i));
							int value = Integer.parseInt(f.replaceAll("[^1234567890-]", ""));
							return value;
						}
					}
				}
			}
		}

		return 0;
	}
	
	public static double getDoubleValue(ItemStack item, String atr) {
		if (item != null) {
			if (item.hasItemMeta()) {
				ItemMeta m = item.getItemMeta();
				if (m.hasLore()) {
					for (int i = 0; i < m.getLore().size(); i++) {
						if (m.getLore().get(i).contains(atr)) {
							String f = ChatColor.stripColor(m.getLore().get(i));
							double value = Double.parseDouble(f.replaceAll("[^1234567890,-]", ""));
							return value;
						}
					}
				}
			}
		}

		return 0;
	}

	public static String getValue(ItemStack item, String atr) {
		if (item != null) {
			if (item.hasItemMeta()) {
				ItemMeta m = item.getItemMeta();
				if (m.hasLore()) {
					for (int i = 0; i < m.getLore().size(); i++) {
						if (m.getLore().get(i).contains(atr)) {
							String f = ChatColor.stripColor(m.getLore().get(i));
							String[] valor = f.split(":");
							return valor[1].replaceAll("[^qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM]", "");
						}
					}
				}
			}
		}
		return null;

	}
	
}
