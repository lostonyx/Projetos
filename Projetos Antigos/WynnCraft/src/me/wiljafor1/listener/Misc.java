package me.wiljafor1.listener;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class Misc implements Listener{
	@EventHandler
	public void removespawn(CreatureSpawnEvent e) {
		if(e.getEntity() instanceof ArmorStand) {
			return;
		}
		else if(e.getSpawnReason() != SpawnReason.CUSTOM) {
			e.setCancelled(true);
		}
	}
	@EventHandler
	public void onPlayerInteractEntity(PlayerInteractEntityEvent event){
	    if(event.getRightClicked().getType() != EntityType.VILLAGER) return;
	    event.setCancelled(true);
	}
}
