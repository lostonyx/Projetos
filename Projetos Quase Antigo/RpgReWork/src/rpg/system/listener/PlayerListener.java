package rpg.system.listener;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import rpg.system.event.JogadorDeathEvent;
import rpg.system.event.JogadorFoundNewRegion;
import rpg.system.models.Account;
import rpg.system.models.City;
import rpg.system.models.Jogador;
import rpg.system.models.JogadorManager;
import rpg.system.models.Region;
import rpg.system.models.RegionManager;
import rpg.utils.HCStrings;
import rpg.utils.SimpleRunnable;
import rpg.utils.SimpleRunnable.TaskType;

public class PlayerListener implements Listener{
	@EventHandler
	public void onEntityDeath(PlayerDeathEvent e) {
	    SimpleRunnable r = SimpleRunnable.getInstance();
	    r.createTaskLater(TaskType.SYNC, "death-"+e.getEntity().getName(), new Runnable() {
			@Override
			public void run() {
				e.getEntity().spigot().respawn();
			}
		}, 1L);
	}
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
		Jogador w = JogadorManager.getWynnPlayer(e.getPlayer());
		if(w.getCurrentAccount() != null) {
			Account a = w.getCurrentAccount();
			if(RegionManager.getCurrentRegion(e.getPlayer().getLocation()) != null) {
				if(RegionManager.getCurrentRegion(e.getPlayer().getLocation())  instanceof City) {
					City r = (City)RegionManager.getCurrentRegion(e.getPlayer().getLocation());
					if(!a.getRegionfound().contains(r.id())) {
						a.getRegionfound().add(r.id());
						w.update();
						JogadorFoundNewRegion event = new JogadorFoundNewRegion(e.getPlayer(), r);
						Bukkit.getPluginManager().callEvent(event);
					}
				}
			}
		}
	}
	
	@EventHandler
    public void damage(EntityDamageByEntityEvent e) {
        Entity entity = e.getEntity();
        if(entity instanceof Player) {
            Damageable d = (Damageable)entity;
            Jogador pw = JogadorManager.getWynnPlayer((Player)entity);
            if(pw.getCurrentAccount() != null) {
            	Account a = pw.getCurrentAccount();
            	Double dano = e.getDamage();
            	int vida = a.getHealth() - dano.intValue();
            	if(vida <= 0) {
            		JogadorDeathEvent event = new JogadorDeathEvent((Player) d);
            		Bukkit.getPluginManager().callEvent(event);
            		d.damage(0);
            	}
            	else {
            		a.setHealth(vida);	
            	}
            }
        }
    }
	
	@EventHandler
    public void OnDamage(EntityDamageEvent e) {
        Entity entity = e.getEntity();
        if(entity instanceof Player) {
        	if(e.getCause() != DamageCause.ENTITY_ATTACK) {
        		if(e.getCause() == DamageCause.FALL) {
            		e.setCancelled(true);
            	}
        	}
        }
    }
	
	@EventHandler
	public void discoberta(JogadorFoundNewRegion e) {
		if(e.getR() instanceof City) {
			City c = (City) e.getR();
			HCStrings.sendCenteredMsg(e.getP(), new String[] { "", "§6§lArea Nova descoberta", "", "§eCidade de "+e.getR().getName(), "", });
			HCStrings.sendCenteredMsg(e.getP(), c.getDescription());
		}
	}
}
