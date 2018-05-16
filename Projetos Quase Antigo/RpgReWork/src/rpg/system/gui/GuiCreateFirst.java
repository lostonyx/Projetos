package rpg.system.gui;

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

import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_8_R3.InventoryUtils;
import rpg.system.Lores;
import rpg.system.Metodos;
import rpg.system.RpgSetup;
import rpg.system.event.JogadorCreateAccount;
import rpg.system.models.Account;
import rpg.system.models.Classe;
import rpg.system.models.ClasseManager;
import rpg.system.models.Jogador;
import rpg.system.models.JogadorManager;
import rpg.utils.SerializeApi;

public class GuiCreateFirst implements Listener {

	public static String title = title = "[N] Escolha sua Classe";;

	public static void open(Player player) {
		/*Inventory inv = Bukkit.createInventory(null, 9 * 3, title);

		int i = 11;
		for (Classe c : ClasseManager.classes) {
			i++;
			inv.setItem(i, c.getIcon());
		}*/
		Inventory inv = Bukkit.createInventory(null, 9 * 2, title);
		inv.setItem(1, ClasseManager.getClasse("Archer").getIcon());
		inv.setItem(3, ClasseManager.getClasse("Warrior").getIcon());
		inv.setItem(5, ClasseManager.getClasse("Mage").getIcon());
		inv.setItem(7, ClasseManager.getClasse("Assassin").getIcon());
		player.openInventory(inv);

	}

	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if(e.getCurrentItem() == null) return;
		Player p = (Player) e.getWhoClicked();
		Jogador w = JogadorManager.getWynnPlayer(p);
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
					p.teleport(RpgSetup.LOCALNOVACONTA);
					Metodos.kitstarter(p, a);
				}	
			}
		}

	}

}
