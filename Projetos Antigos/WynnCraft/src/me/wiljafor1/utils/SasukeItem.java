package me.wiljafor1.utils;

import java.util.Arrays;
import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import com.google.common.collect.Maps;

public class SasukeItem implements Listener {

	public static HashMap<ItemStack, ItemAction> actions = Maps.newHashMap();
	public static HashMap<ItemStack, Runnable> guiitem = Maps.newHashMap();

	@EventHandler
	public void onClick(PlayerInteractEvent e) {

		Player p = e.getPlayer();
		if (p.getItemInHand() == null)
			return;
		else if (!p.getItemInHand().hasItemMeta())
			return;
		else if (!actions.containsKey(p.getItemInHand()))
			return;
		else {
			ItemStack i = p.getItemInHand();
			if (e.getAction().name().contains(actions.get(i).getAction())) {
				if (actions.get(i).getRunnable() != null) {
					actions.get(i).getRunnable().run();
				}
			}
		}
	}

	@EventHandler
	public void onClickInventory(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		ItemStack i = e.getCurrentItem();
		if (guiitem.containsKey(i)) {
			e.setCancelled(true);
			guiitem.get(i).run();
		}
	}

	public static ItemStack createInteractItem(ItemStack item, String clickAction, Runnable onClick) {
		actions.put(item, new ItemAction(clickAction, onClick));
		return item;
	}
	
	
	public static ItemStack createInteractItem(Material material , String displayName , int data , int amount , String[] lore 
			, String clickAction, Runnable onClick) {
		ItemStack item = ItemAPI.newItem(material, displayName, amount, data, lore);
		
		actions.put(item, new ItemAction(clickAction, onClick));
		return item;
	}

	public static ItemStack createInventoryItem(Material material , String displayName , int data , int amount , String[] lore , Runnable onClick) {
		ItemStack item = ItemAPI.newItem(material, displayName, amount, data, lore);
		guiitem.put(item, onClick);
		return item;
	}
	
	public static ItemStack createInventoryItem(ItemStack item, Runnable onClick) {
		guiitem.put(item, onClick);
		return item;
	}

	public static ItemStack getNamedSkull(String nick, String nome, String[] lore) {
		@SuppressWarnings("deprecation")
		ItemStack skull = new ItemStack(397, 1, (short) 3);
		SkullMeta meta = (SkullMeta) skull.getItemMeta();
		meta.setDisplayName(nome);
		meta.setLore(Arrays.asList(lore));
		meta.setOwner(nick);
		skull.setItemMeta(meta);

		return skull;
	}

	public static ItemStack createItem(Material material, int data, int amount, String display, String... lore) {
		return ItemAPI.newItem(material, display, amount, data, lore);
	}

	public static class ItemAction {
		private String action;
		private Runnable runnable;

		public ItemAction(String action, Runnable runnable) {
			this.action = action;
			this.runnable = runnable;
		}

		public String getAction() {
			return action;
		}

		public void setAction(String action) {
			this.action = action;
		}

		public Runnable getRunnable() {
			return runnable;
		}

		public void setRunnable(Runnable runnable) {
			this.runnable = runnable;
		}
	}
}
