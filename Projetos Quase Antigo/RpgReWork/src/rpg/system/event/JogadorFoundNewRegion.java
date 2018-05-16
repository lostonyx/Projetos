package rpg.system.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import rpg.system.models.Account;
import rpg.system.models.City;
import rpg.system.models.Jogador;
import rpg.system.models.JogadorManager;
import rpg.system.models.Region;
import rpg.system.models.RegionManager;

public class JogadorFoundNewRegion extends Event implements Cancellable{
	
	private Region r;
	private Player p;
	
	public JogadorFoundNewRegion(Player p,Region r) {
		this.p = p;
		this.r = r;
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

	/**
	 * @return the r
	 */
	public Region getR() {
		return r;
	}

	/**
	 * @param r the r to set
	 */
	public void setR(Region r) {
		this.r = r;
	}

	/**
	 * @return the p
	 */
	public Player getP() {
		return p;
	}

	/**
	 * @param p the p to set
	 */
	public void setP(Player p) {
		this.p = p;
	}
}
