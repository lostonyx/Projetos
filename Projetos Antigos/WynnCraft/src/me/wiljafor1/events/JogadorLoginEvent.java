package me.wiljafor1.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.Inventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.wiljafor1.models.Account;
import me.wiljafor1.models.Gamer;
import me.wiljafor1.models.GamerManager;
import me.wiljafor1.models.WynnPlayer;
import me.wiljafor1.system.cache.PreCuboId;
import me.wiljafor1.utils.SerializeApi;
import me.wiljafor1.utils.SimpleRunnable;
import me.wiljafor1.utils.SimpleRunnable.TaskType;

public class JogadorLoginEvent extends Event implements Cancellable{

	public JogadorLoginEvent(Player p, WynnPlayer w, Account a) {
		w.setCurrentAccount(a);
		w.update();
		p.setFoodLevel(a.getMana());
		Inventory inv = w.getCurrentAccount().getInv();
		p.getInventory().setContents(inv.getContents().clone());
		p.updateInventory();
		p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 150, 30));
        p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 150, -30));
        p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 150, 30));
		PreCuboId.forcepreload(SerializeApi.DeserializarLoc(a.getLastLoc()));
		SimpleRunnable r = new SimpleRunnable().getInstance();
		r.createTaskLater(TaskType.SYNC, "login"+p.getName(), new Runnable() {
			@Override
			public void run() {
				p.sendMessage("tp");
				p.teleport(SerializeApi.DeserializarLoc(a.getLastLoc()));		
			}
		}, 20*5);
		Gamer g = GamerManager.getGamer(p);
		g.setLogged(true);
		g.update();	
	}

	private static final HandlerList handlers = new HandlerList();
	private boolean cancelled;

	public HandlerList getHandlers() {
		return handlers;
	}
	
	public static HandlerList getHandlerList() {
        return handlers;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }
}
