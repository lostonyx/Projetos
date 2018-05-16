package com.dayz.gui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import com.dayz.type.Account;
import com.dayz.type.Classe;
import com.dayz.type.ClasseManager;
import com.dayz.type.Jogador;
import com.dayz.type.JogadorManager;
import com.dayz.utils.SerializeApi;

import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_8_R3.InventoryUtils;

public class GuiCreateFirst implements Listener {

	public static String title = "Escolha sua Classe";

	public static void open(Player player) {
		Inventory inv = Bukkit.createInventory(null, 9 * 3, title);

		int i = 11;
		for (Classe c : ClasseManager.classes) {
			i++;
			inv.setItem(i, c.getIcon());
		}
		player.openInventory(inv);

	}

	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if(e.getCurrentItem() == null) return;
		Player p = (Player) e.getWhoClicked();
		Jogador j = JogadorManager.getJogador(p);
		if (e.getInventory().getTitle().equalsIgnoreCase(title)) {
			e.setCancelled(true);
			p.closeInventory();
			for (Classe c : ClasseManager.classes) {
				if(e.getCurrentItem().isSimilar(c.getIcon())) {
					Account a = new Account(0, c.getName(), SerializeApi.Serializar(p.getInventory()), 1, 0, 0, 0, 0, (double) 100, SerializeApi.SerializarLoc(p.getLocation()), 0.0,0, 30.0);
					j.getContas().add(a);
					j.update();
				}	
			}
		}

	}

}
