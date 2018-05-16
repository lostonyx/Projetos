package rpg.system.event;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.Inventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import rpg.system.models.Account;
import rpg.system.models.Gamer;
import rpg.system.models.GamerManager;
import rpg.system.models.Jogador;
import rpg.utils.SerializeApi;
import rpg.utils.SimpleRunnable;
import rpg.utils.SimpleRunnable.TaskType;

public class JogadorLoginEvent extends Event implements Cancellable{

	public JogadorLoginEvent(Player p, Jogador w, Account a) {
		w.setCurrentAccount(a);
		w.update();
		p.setFoodLevel(a.getMana());
		Inventory inv = w.getCurrentAccount().getInv();
		p.getInventory().setContents(inv.getContents().clone());
		p.updateInventory();
		//p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 150, 30));
        //p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 150, -30));
        //p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 150, 30));
		SimpleRunnable r = new SimpleRunnable().getInstance();
		r.createTaskLater(TaskType.SYNC, "login"+p.getName(), new Runnable() {
			@Override
			public void run() {
				p.setGameMode(GameMode.ADVENTURE);
				p.teleport(SerializeApi.DeserializarLoc(a.getLastLoc()));		
			}
		}, 0);//20 * 5
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
