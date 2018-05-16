package me.wiljafor1.system.gui;

import java.util.ArrayList;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import me.wiljafor1.events.JogadorCreateAccount;
import me.wiljafor1.models.Account;
import me.wiljafor1.models.Classe;
import me.wiljafor1.models.ClasseManager;
import me.wiljafor1.models.WynnPlayer;
import me.wiljafor1.models.WynnPlayerManager;
import me.wiljafor1.system.Metados;
import me.wiljafor1.utils.SerializeApi;

public class GuiCreateAccount implements Listener{
	public static String title;

	private static Map<Player,Integer> id = Maps.newHashMap();
	
	public static void open(Player player, int idaccount) {
		title = "["+(idaccount+1)+"] Escolha sua Classe";
		Inventory inv = Bukkit.createInventory(null, 9 * 3, title);

		int i = 11;
		for (Classe c : ClasseManager.classes) {
			i++;
			inv.setItem(i, c.getIcon());
		}
		id.put(player, idaccount);
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
					Account a = new Account(id.get(p), 1, 0, 5, 20, 8, SerializeApi.SerializarLoc(p.getLocation()), c.getName(), SerializeApi.Serializar(p.getInventory()), 0, rf);
					w.getContas().add(a);
					w.update();
					w.setCurrentAccount(a);
					Metados.kitstarter(p);
				}	
			}
		}

	}
}
