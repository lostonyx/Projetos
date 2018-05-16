package me.wiljafor1.events;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import me.wiljafor1.interfaces.City;
import me.wiljafor1.interfaces.RegionManager;
import me.wiljafor1.models.Account;
import me.wiljafor1.models.WynnPlayer;
import me.wiljafor1.models.WynnPlayerManager;

public class JogadorDeathEvent extends Event implements Cancellable  {
	
	private Location local;

	public JogadorDeathEvent(Player p) {
		WynnPlayer w = WynnPlayerManager.getWynnPlayer(p);
		if(w.getCurrentAccount() != null) {
			Account a = w.getCurrentAccount();
			if(a.getLastcity() != 0) {
				City r = (City) RegionManager.getCityId(a.getLastcity());
				if(local != null) {
					p.teleport(local);
				}
				else {
					p.teleport(r.getSpawn());
				}
			}
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

	public Location getLocal() {
		return local;
	}

	public void setLocal(Location local) {
		this.local = local;
	}
}
