package rpg.system.event;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import rpg.system.models.Account;
import rpg.system.models.City;
import rpg.system.models.Jogador;
import rpg.system.models.JogadorManager;
import rpg.system.models.RegionManager;

public class JogadorLevelUpEvent extends Event implements Cancellable  {
	
	Player p;

	public JogadorLevelUpEvent(Player p) {
		this.p = p;
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
