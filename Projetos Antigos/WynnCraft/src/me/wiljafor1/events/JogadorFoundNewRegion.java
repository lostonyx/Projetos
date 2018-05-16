package me.wiljafor1.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import me.wiljafor1.interfaces.City;
import me.wiljafor1.interfaces.RegionManager;
import me.wiljafor1.models.Account;
import me.wiljafor1.models.WynnPlayer;
import me.wiljafor1.models.WynnPlayerManager;

public class JogadorFoundNewRegion extends Event implements Cancellable{
	
	public JogadorFoundNewRegion(Player p) {
		WynnPlayer w = WynnPlayerManager.getWynnPlayer(p);
		Account a = w.getCurrentAccount();
		City r = (City)RegionManager.getCurrentRegion(p.getLocation());
		if(!a.getRegionfound().contains(r.id())) {
			a.getRegionfound().add(r.id());
			w.update();
		}
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
