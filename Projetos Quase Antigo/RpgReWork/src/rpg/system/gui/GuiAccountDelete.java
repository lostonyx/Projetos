package rpg.system.gui;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import net.md_5.bungee.api.ChatColor;
import rpg.system.event.JogadorDeleteAccount;
import rpg.system.event.JogadorLoginEvent;
import rpg.system.models.Account;
import rpg.system.models.Classe;
import rpg.system.models.ClasseManager;
import rpg.system.models.Jogador;
import rpg.system.models.JogadorManager;
import rpg.utils.CItem;

public class GuiAccountDelete implements Listener  {
	
	public static String title = title = "Deletar a Conta";
	public static HashMap<Player, Account> accounttemp = Maps.newHashMap();
	
	public static void open(Player player, Jogador w, Account a) {
		Inventory inv = Bukkit.createInventory(null, 9 * 3, title);

		inv.setItem(12, new CItem(Material.STAINED_CLAY).setDurability(5).setName("§2Sim").setLore(new String[] {
				"§2Confirme para deletar a conta","§l§4Esse processo nao tem volta!","Menssagem meramente ilustrativa!"
		}).build());
		inv.setItem(14, new CItem(Material.STAINED_CLAY).setDurability(14).setName("§2Nao").setLore(new String[] {
				"§2Para cancelar cliqui aqui","Menssagem meramente ilustrativa!"
		}).build());
		player.openInventory(inv);
		accounttemp.put(player, a);
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if (e.getCurrentItem() == null)
			return;
		Player p = (Player) e.getWhoClicked();
		Jogador w = JogadorManager.getWynnPlayer(p);
		ItemStack i = e.getCurrentItem();
		if (e.getInventory().getTitle().equalsIgnoreCase("Deletar a Conta")) {
			if (i.hasItemMeta()) {
				e.setCancelled(true);
				if(i.getItemMeta().getDisplayName().equals("§2Sim")) {
					JogadorDeleteAccount event = new JogadorDeleteAccount(w, accounttemp.get(p));
					Bukkit.getServer().getPluginManager().callEvent(event);
					accounttemp.clear();
					p.closeInventory();
				}
				else {
					p.closeInventory();
					GuiAccountSelect.open(p);
					accounttemp.clear();
				}
			}
		}

	}
	
}
