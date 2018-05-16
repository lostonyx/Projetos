package rpg.system.gui;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import net.md_5.bungee.api.ChatColor;
import rpg.system.Lores;
import rpg.system.Metodos;
import rpg.system.event.JogadorDeleteAccount;
import rpg.system.event.JogadorLoginEvent;
import rpg.system.models.Account;
import rpg.system.models.Gamer;
import rpg.system.models.GamerManager;
import rpg.system.models.Jogador;
import rpg.system.models.JogadorManager;
import rpg.utils.ItemAPI;
import rpg.utils.ProgressBar;
import rpg.utils.SerializeApi;

public class GuiAccountSelect implements Listener {
	public static void open(Player player) {
		Jogador w = JogadorManager.getWynnPlayer(player);
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
				inv.setItem(id, ItemAPI.newItem(Metodos.iconeclasse(a.getClassp()), "§6Personagem #" + (a.getId() + 1), 1, 0,
						"§fClasse: §c" + a.getClassp(), "§fLevel: §c" + a.getLevel(), "§fProgresso: " + ProgressBar
								.getProgressBar(a.getXp(), a.getLevel() * a.getLevel() * 500, 10, "|", "§e", "§7"), "§aClique com botão esquerdo para logar", "§aClique com botão direito para deletar essa conta"));	
			
		}
		player.openInventory(inv);

	}

	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if (e.getCurrentItem() == null)
			return;
		Player p = (Player) e.getWhoClicked();
		Jogador w = JogadorManager.getWynnPlayer(p);
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
									if (e.getClick() == ClickType.RIGHT) {
										GuiAccountDelete.open(p, w, a);
									}
									if (e.getClick() == ClickType.LEFT) {
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

}
