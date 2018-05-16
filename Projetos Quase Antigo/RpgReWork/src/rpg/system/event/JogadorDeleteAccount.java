package rpg.system.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import rpg.system.gui.GuiAccountDelete;
import rpg.system.models.Account;
import rpg.system.models.Jogador;
import rpg.system.models.JogadorManager;

public class JogadorDeleteAccount extends Event implements Cancellable   {
	
	public JogadorDeleteAccount(Jogador j,Account a) {
		j.getContas().remove(a);
		j.update();
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
