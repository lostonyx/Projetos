package com.criptonnetwork.gui;

import java.util.Arrays;
import java.util.Random;

import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.criptonnetwork.type.Gamer;
import com.criptonnetwork.type.GamerManager;
import com.criptonnetwork.util.CItem;
import com.criptonnetwork.util.Extra;
import com.criptonnetwork.util.ItemAPI;

public class GuiFriends implements Listener{
	// ?
	public static Inventory open(Player p) {
		Inventory inv = Bukkit.createInventory(null, 9 * 6, "Seus Amigos");
		int id = 9;
		Gamer g = GamerManager.getGamer(p);

		if (g.getFriends().isEmpty() || g.getFriends().size() < 1) {
			return inv;
		} else {
			for (int i = 0; i < inv.getSize(); i++) {
				inv.setItem(i, ItemAPI.newItem(Material.STAINED_GLASS_PANE, "", 1, 3, ""));
			}

			for (String s : g.getFriends()) {
				while (ItemAPI.isColumn(id, 1) | ItemAPI.isColumn(id, 9) | ItemAPI.isColumn(id, 1)
						| ItemAPI.isColumn(id, 8)) {
					id++;
				}
				id++;
				Document doc = Gamer.gamers.find(new Document("name", s)).first();
				if(doc != null) {
					String name = doc.getString("name");
					boolean isOnline = doc.getBoolean("isOnline");
					String servidor = doc.getString("lastServer");

					if (isOnline) {
						inv.setItem(id, new CItem(Material.SKULL_ITEM).setDurability(3).setSkullOwner(s).setName("§a" + s)
								.setLore(new String[] {
										"§7Status: §2Online!",
										"§7Servidor: §e"  + servidor,

						}).build());
					}else {
						inv.setItem(id, new CItem(Material.SKULL_ITEM).setDurability(1).setName("§c" + s)
								.setLore(new String[] {
										"§7Status: §cOffline",
										"§7Ultimo Servidor: §e"  + servidor,

						}).build());
					}
				}else {
					inv.setItem(id, new CItem(Material.SKULL_ITEM).setDurability(1).setName("§c" + s)
							.setLore(new String[] {
									"§7Status: §cOffline",
									"§7Nenhum servidor logado antes.",

					}).build());
				}

			}
			p.openInventory(inv);
			
			return inv;
		}

	}
	
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		
		
	}
	
	
	

}