package rpg.system;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import rpg.utils.CItem;
import rpg.utils.SimpleRunnable;
import rpg.utils.SimpleRunnable.TaskType;

public class notification {

	
	
	public static void notificarpeloitem(String msg, Player p) {
		if(p.getItemInHand() != null) {
			if(p.getItemInHand().hasItemMeta()) {
				ItemStack item = p.getItemInHand();
				CItem c1 = new CItem(item);
				CItem c2 = c1.clone();
				c2.setName(msg);
				p.setItemInHand(c2.build());
				p.updateInventory();
				SimpleRunnable r = SimpleRunnable.getInstance();
				r.createTaskLater(TaskType.SYNC, p.getName()+"noti", new Runnable() {
					@Override
					public void run() {
						p.setItemInHand(c1.build());
						p.updateInventory();
					}
				}, 20*2);
			}
			
		}
	}
	
}
