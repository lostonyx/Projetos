package rpg.mob.type;

import java.util.ArrayList;

import org.bukkit.entity.EntityType;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.inventory.ItemStack;

import rpg.mob.Mobs;

public interface E extends Mobs {

	public void interact(EntityInteractEvent e);
	public void interactinv(InventoryInteractEvent e);

}
