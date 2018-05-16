package rpg.system.listener;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class Misc implements Listener{
	@EventHandler
	public void removespawn(CreatureSpawnEvent e) {
		if(e.getEntity() instanceof ArmorStand) {
			return;
		}
		else if(e.getSpawnReason() == SpawnReason.CUSTOM) {
			return;
		}
		else if(e.getSpawnReason() == SpawnReason.NATURAL) {
			return;
		}
		else {
			e.setCancelled(true);
		}
	}
	@EventHandler
	public void onPlayerInteractEntity(PlayerInteractEntityEvent event){
	    if(event.getRightClicked().getType() != EntityType.VILLAGER) return;
	    event.setCancelled(true);
	}
	
	@EventHandler
    public void onHit(ProjectileHitEvent e) 
    {
        Projectile p = e.getEntity(); 
        if(p instanceof Arrow)
        {
            p.remove();
        }
        return;
    }
}
