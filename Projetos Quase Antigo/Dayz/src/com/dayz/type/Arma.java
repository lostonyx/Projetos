package com.dayz.type;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.criptonnetwork.util.CItem;

public class Arma {

	public static ItemStack createWeapon(String name, Material mat, int currentbullet, int maxbullet, double recoil,
			double damage, int bulletId) {

		ItemStack i = new CItem(mat).setName(name)
				.setLore(new String[] { "§fBalas: §c" + currentbullet + "/" + maxbullet, // 0
						"§fRecoil: §c" + recoil, // 1
						"§fDamage: §c" + damage, // 2
						"§fTipo de Munição: §c" + bulletId, // 3
						""

				}).build();

		return i;
	}

	public static int getCurrentBullet(ItemStack item) {
		if (item != null && item.hasItemMeta()) {
			if (item.getItemMeta().hasLore()) {
				String name = item.getItemMeta().getLore().get(0);
				int bullet = Integer.parseInt(name.split("/")[0].replaceAll("[^1234567890]", ""));
				return bullet;
			}
		}
		return -1;

	}

	public static int getBulletID(ItemStack item) {
		if (item != null && item.hasItemMeta()) {
			if (item.getItemMeta().hasLore()) {
				String name = item.getItemMeta().getLore().get(3);
				int bullet = Integer.parseInt(name.split("Tipo de Munição:")[1].replaceAll("[^1234567890]", ""));
				return bullet;
			}
		}
		return -1;

	}

	public static double getRecoil(ItemStack item) {
		if (item != null && item.hasItemMeta()) {
			if (item.getItemMeta().hasLore()) {
				String name = item.getItemMeta().getLore().get(1);
				int bullet = Integer.parseInt(name.split("Recoil:")[1].replaceAll("[^1234567890]", ""));
				return bullet;
			}
		}
		return -1d;

	}

	public static double getDamage(ItemStack item) {
		if (item != null && item.hasItemMeta()) {
			if (item.getItemMeta().hasLore()) {
				String name = item.getItemMeta().getLore().get(2);
				int bullet = Integer.parseInt(name.split("Damage:")[1].replaceAll("[^1234567890]", ""));
				return bullet;
			}
		}
		return -1d;

	}

	public static int getMaxBullet(ItemStack item) {
		if (item != null && item.hasItemMeta()) {
			if (item.getItemMeta().hasLore()) {
				String name = item.getItemMeta().getLore().get(0);
				int bullet = Integer.parseInt(name.split("/")[1].replaceAll("[^1234567890]", ""));
				return bullet;
			}
		}
		return -1;

	}

	public static void setCurrentBullet(ItemStack item) {
		if (item != null && item.hasItemMeta()) {
			int currentbullet = getCurrentBullet(item) == -1 ? 0 : getCurrentBullet(item);
			int maxbullet = getMaxBullet(item) == -1 ? 0 : getCurrentBullet(item);

			double recoil = getRecoil(item) == -1d ? 0 : getRecoil(item);
			double damage = getDamage(item) == -1d ? 0 : getDamage(item);

			int bulletId = getBulletID(item) == -1d ? 0 : getBulletID(item);

			if (item.getItemMeta().hasLore()) {
				List<String> lista = Arrays.asList(new String[] { 
						"§fBalas: §c" + currentbullet + "/" + maxbullet, // 0
						"§fRecoil: §c" + recoil, // 1
						"§fDamage: §c" + damage, // 2
						"§fTipo de Munição: §c" + bulletId, // 3
						""

				});
				ItemMeta i = item.getItemMeta();
				i.setLore(lista);
				item.setItemMeta(i);
			}
		}
	}

}
