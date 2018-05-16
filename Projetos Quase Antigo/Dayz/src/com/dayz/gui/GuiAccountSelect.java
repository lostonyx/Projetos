package com.dayz.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.criptonnetwork.util.ItemAPI;
import com.criptonnetwork.util.ProgressBar;
import com.dayz.listener.ConectionEvent;
import com.dayz.type.Account;
import com.dayz.type.Jogador;
import com.dayz.type.JogadorManager;
import com.dayz.utils.SerializeApi;

import net.md_5.bungee.api.ChatColor;

public class GuiAccountSelect implements Listener {

	public static void open(Player player) {
		Jogador j = JogadorManager.getJogador(player);
		Inventory inv = Bukkit.createInventory(null, 9 * 3, "Escolha seu Personagem");
		int id = 9;
		for (Account a : j.getContas()) {
			while (ItemAPI.isColumn(id, 1) | ItemAPI.isColumn(id, 9) | ItemAPI.isColumn(id, 1)
					| ItemAPI.isColumn(id, 8)) {
				id++;
			}

			id++;
			inv.setItem(id, ItemAPI.newItem(Material.NAME_TAG, "§6Personagem #" + j.getContas().indexOf(a) + 1, 1, 0,
					"§fClasse: §c" + a.getClasse(),"§fDinheiro: §2" + a.getMoney(), "§fLevel: §c" + a.getLevel(), "§fProgresso: " + ProgressBar
							.getProgressBar(a.getXp(), a.getLevel() * a.getLevel() * 500, 10, "|", "§e", "§7")));
		}

		player.openInventory(inv);

	}

	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if (e.getCurrentItem() == null)
			return;
		Player p = (Player) e.getWhoClicked();
		Jogador j = JogadorManager.getJogador(p);
		ItemStack i = e.getCurrentItem();
		if (e.getInventory().getTitle().equalsIgnoreCase("Escolha seu Personagem")) {
			if (i.hasItemMeta()) {
				e.setCancelled(true);
				p.closeInventory();
				String name = i.getItemMeta().getDisplayName();
				String namefix = ChatColor.stripColor(name);
				int id = Integer.parseInt(namefix.split("#")[1]) - 1;
				for (Account a : j.getContas()) {
					if (a != null) {
						j.setCurrentAccount(a);
						j.update();
						// p.sendMessage("Criar conta: "+j.getCurrentAccount());
						p.teleport(SerializeApi.DeserializarLoc(a.getUltimolocal()));
						Inventory inv = j.getCurrentAccount().getInv();
						p.getInventory().setContents(inv.getContents().clone());
						p.updateInventory();
						p.setTexturePack("https://download.nodecdn.net/containers/nodecraft/minepack/d69d4919ac5d9d5749fd7e66c7d657e2.zip");
						ConectionEvent.Score(p);
					}

				}
			}
		}

	}

}
