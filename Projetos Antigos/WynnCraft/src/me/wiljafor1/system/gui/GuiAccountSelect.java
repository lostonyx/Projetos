package me.wiljafor1.system.gui;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.wiljafor1.events.JogadorLoginEvent;
import me.wiljafor1.items.Lores;
import me.wiljafor1.models.Account;
import me.wiljafor1.models.Gamer;
import me.wiljafor1.models.GamerManager;
import me.wiljafor1.models.WynnPlayer;
import me.wiljafor1.models.WynnPlayerManager;
import me.wiljafor1.system.cache.PreCuboId;
import me.wiljafor1.utils.ItemAPI;
import me.wiljafor1.utils.ProgressBar;
import me.wiljafor1.utils.SerializeApi;
import net.md_5.bungee.api.ChatColor;

public class GuiAccountSelect implements Listener {
	public static void open(Player player) {
		WynnPlayer w = WynnPlayerManager.getWynnPlayer(player);
		Inventory inv = Bukkit.createInventory(null, 9 * 3, "Escolha seu Personagem");
		int id = 9;
		inv.setItem(11, ItemAPI.newItem(Material.STAINED_GLASS_PANE, "§6Personagem #1", 1, 15, "§6Criar um novo Personagem"));
		inv.setItem(12, ItemAPI.newItem(Material.STAINED_GLASS_PANE, "§6Personagem #2", 1, 15, "§6Criar um novo Personagem"));
		inv.setItem(13, ItemAPI.newItem(Material.STAINED_GLASS_PANE, "§6Personagem #3", 1, 15, "§6Criar um novo Personagem"));
		inv.setItem(14, ItemAPI.newItem(Material.STAINED_GLASS_PANE, "§6Personagem #4", 1, 15, "§6Criar um novo Personagem"));
		inv.setItem(15, ItemAPI.newItem(Material.STAINED_GLASS_PANE, "§6Personagem #5", 1, 15, "§6Criar um novo Personagem"));
		for (Account a : w.getContas()) {
			while (ItemAPI.isColumn(id, 1) | ItemAPI.isColumn(id, 9) | ItemAPI.isColumn(id, 1)
					| ItemAPI.isColumn(id, 8)) {
				id++;
			}
			id++;
				if(a.getId()+1 == 1) {
					id = 11;
				}
				else if(a.getId()+1 == 2) {
					id = 12;
				}
				else if(a.getId()+1 == 3) {
					id = 13;
				}
				else if(a.getId()+1 == 4) {
					id = 14;
				}
				else if(a.getId()+1 == 5) {
					id = 15;
				}
				inv.setItem(id, ItemAPI.newItem(Material.STAINED_GLASS_PANE, "§6Personagem #" + (a.getId() + 1), 1, 5,
						"§fClasse: §c" + a.getClassp(), "§fLevel: §c" + a.getLevel(), "§fProgresso: " + ProgressBar
								.getProgressBar(a.getXp(), a.getLevel() * a.getLevel() * 500, 10, "|", "§e", "§7")));	
			
		}
		player.openInventory(inv);

	}

	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if (e.getCurrentItem() == null)
			return;
		Player p = (Player) e.getWhoClicked();
		WynnPlayer w = WynnPlayerManager.getWynnPlayer(p);
		ItemStack i = e.getCurrentItem();
		if (e.getInventory().getTitle().equalsIgnoreCase("Escolha seu Personagem")) {
			if (i.hasItemMeta()) {
				e.setCancelled(true);
				String name = i.getItemMeta().getDisplayName();
				String namefix = ChatColor.stripColor(name);
				int id = Integer.parseInt(namefix.split("#")[1]) - 1;
				if(i.getItemMeta().getLore().get(0).equals("§6Criar um novo Personagem")) {
					GuiCreateAccount.open(p, id);
				}
				else {
					for (Account a : w.getContas()) {
						if (a != null) {
							if(a.getId() == id) {
									p.closeInventory();
									JogadorLoginEvent event = new JogadorLoginEvent(p, w, a);
									Bukkit.getServer().getPluginManager().callEvent(event);
							}
						}
					}	
				}
			}
		}

	}

}
