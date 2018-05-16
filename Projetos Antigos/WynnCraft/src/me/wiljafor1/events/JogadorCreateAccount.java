package me.wiljafor1.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class JogadorCreateAccount extends Event{
	private Player p;
	
	public JogadorCreateAccount(Player p) {
		this.p = p;
	}

	private static final HandlerList handlers = new HandlerList();

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
	
	public static HandlerList getHandlerList() {
        return handlers;
    }

	public Player getP() {
		return p;
	}

	public void setP(Player p) {
		this.p = p;
	}

}
