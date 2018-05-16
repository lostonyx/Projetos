package com.criptonnetwork.cosmeticos.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import com.criptonnetwork.cosmeticos.CosmeticType;
import com.criptonnetwork.cosmeticos.Cosmetico;
import com.criptonnetwork.cosmeticos.CosmeticoManager;
import com.criptonnetwork.type.Gamer;
import com.criptonnetwork.type.GamerManager;
import com.criptonnetwork.util.CItem;
import com.criptonnetwork.util.ItemAPI;

public class GuiComseticos implements Listener{
	
	public static void open(Player p) {
		Inventory inv = Bukkit.createInventory(null, 9 * 4  , "Cosmeticos");
		Gamer g = GamerManager.getGamer(p);
		inv.setItem(11, ItemAPI.newItem(Material.BLAZE_POWDER, "§eEfeitos", 1, 0, 
				"§7Clique para ver seus efeitos" , 
				"",
				"§fVocê possui: §a" + g.getEffects().size() + "/" + CosmeticoManager.getCosmeticoList(CosmeticType.EFFECT).size()));
		inv.setItem(12, ItemAPI.newItem(Material.RECORD_10, "§eMusica", 1, 0, 
				"§7Em breve"));
		inv.setItem(13, ItemAPI.newItem(Material.MONSTER_EGG, "§ePets", 1, 0, 
				"§7Clique para ver seus pets" , 
				"",
				"§fVocê possui: §a" + g.getPets().size() + "/" + CosmeticoManager.getCosmeticoList(CosmeticType.PET).size()));
		inv.setItem(14, ItemAPI.newItem(Material.STORAGE_MINECART, "§eAcessorios", 1, 0, 
				"§7Em breve"));
		inv.setItem(15, ItemAPI.newItem(Material.NAME_TAG, "§eTitulos", 1, 0, 
				"§7Em breve"));
		p.openInventory(inv);
		
	}
	
	
	public static void openEffects(Gamer g) {
		Player p = g.getPlayer();
		Inventory inv = Bukkit.createInventory(null, 9 * 4  , "Efeitos");
		int id = 9;
		
		
		for(Cosmetico cos : g.getEffects()) {
			while (ItemAPI.isColumn(id, 1) | ItemAPI.isColumn(id, 9) | ItemAPI.isColumn(id, 1)
					| ItemAPI.isColumn(id, 8)) {
				id++;
			}
			id++;
			inv.setItem(id, cos.getIcon());
		}
		
		p.openInventory(inv);
	}
	
	public static void openPets(Gamer g) {
		Player p = g.getPlayer();
		Inventory inv = Bukkit.createInventory(null, 9 * 4  , "Pets");
		int id = 9;
		
		for(Cosmetico cos : g.getPets()) {
			while (ItemAPI.isColumn(id, 1) | ItemAPI.isColumn(id, 9) | ItemAPI.isColumn(id, 1)
					| ItemAPI.isColumn(id, 8)) {
				id++;
			}
			id++;
			inv.setItem(id, cos.getIcon());
		}
		
		p.openInventory(inv);
	}
	
	
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if(e.getCurrentItem() == null) return;
		Player p = (Player) e.getWhoClicked();
		Gamer g = GamerManager.getGamer(p);
		
		if(e.getInventory().getTitle().equalsIgnoreCase("Cosmeticos")) {
			e.setCancelled(true);
			if(e.getRawSlot() == 11) {
				openEffects(g);
			}
			if(e.getRawSlot() == 13) {
				openPets(g);
			}
		}
		
		if(e.getInventory().getTitle().equalsIgnoreCase("Efeitos")) {
			e.setCancelled(true);
			for(Cosmetico c : g.getAllCosmeticos()) {
				if(e.getCurrentItem().isSimilar(c.getIcon())) {
					c.use(p);
					p.sendMessage("§eCosmetico> §7Você ativou o " + c.getName());
				}
			}
		}
		
		if(e.getInventory().getTitle().equalsIgnoreCase("Pets")) {
			e.setCancelled(true);
			for(Cosmetico c : g.getAllCosmeticos()) {
				if(e.getCurrentItem().isSimilar(c.getIcon())) {
					c.use(p);
					p.sendMessage("§eCosmetico> §7Você spawnou " + c.getName());
				}
			}
		}

		
	}
	

}
