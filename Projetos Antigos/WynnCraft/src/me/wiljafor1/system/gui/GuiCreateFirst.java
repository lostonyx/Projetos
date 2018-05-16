package me.wiljafor1.system.gui;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.google.common.collect.Lists;

import me.wiljafor1.events.JogadorCreateAccount;
import me.wiljafor1.items.Lores;
import me.wiljafor1.models.Account;
import me.wiljafor1.models.Classe;
import me.wiljafor1.models.ClasseManager;
import me.wiljafor1.models.WynnPlayer;
import me.wiljafor1.models.WynnPlayerManager;
import me.wiljafor1.system.Metados;
import me.wiljafor1.utils.SerializeApi;
import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_8_R3.InventoryUtils;

public class GuiCreateFirst implements Listener {

	public static String title = title = "[1] Escolha sua Classe";;

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
		WynnPlayer w = WynnPlayerManager.getWynnPlayer(p);
		if (e.getInventory().getTitle().equalsIgnoreCase(title)) {
			e.setCancelled(true);
			p.closeInventory();
			for (Classe c : ClasseManager.classes) {
				if(e.getCurrentItem().isSimilar(c.getIcon())) {
					ArrayList<Integer> rf = Lists.newArrayList();
					JogadorCreateAccount event = new JogadorCreateAccount(p);
					Bukkit.getServer().getPluginManager().callEvent(event);
					rf.add(0);
					Account a = new Account(0, 1, 0, 9999, 20, 8, SerializeApi.SerializarLoc(p.getLocation()), c.getName(), SerializeApi.Serializar(p.getInventory()), 0, rf);
					w.getContas().add(a);
					w.update();
					w.setCurrentAccount(a);
					Metados.kitstarter(p);
				}	
			}
		}

	}

}
