package me.wiljafor1.items;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.wiljafor1.models.WynnPlayer;
import me.wiljafor1.models.WynnPlayerManager;
import me.wiljafor1.system.gameplay.vida;



public class ItemListener implements Listener{
	
	@EventHandler
	public void onHeldItem(PlayerItemHeldEvent e) {
		Player p = e.getPlayer();
		WynnPlayer w = WynnPlayerManager.getWynnPlayer(p);
		if(p.getInventory().getItem(e.getNewSlot()) != null) {
			if(p.getInventory().getItem(e.getNewSlot()).hasItemMeta()) {
				if(p.getInventory().getItem(e.getNewSlot()).getItemMeta().hasLore()) {
					ItemStack i = p.getInventory().getItem(e.getNewSlot());
					if(Lores.containsAtribute("Level Minimo", i)) {
						int lvmin = Lores.getAtrValue(i, "Level Minimo");
						if(w.getCurrentAccount().getLevel() < lvmin) {
							e.setCancelled(true);
							p.sendMessage("§cVocê nao tem level para usar este item , level minimo é de " + lvmin + ".");
						}
					}
					
				}
			}
		}
	}
	/*@EventHandler
	public void onMaodePunheta(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if(p.getInventory().getItemInHand() != null) {
			vida.usarmana(1, p);
			p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 300, 5));
		}
		
	}*/

}
