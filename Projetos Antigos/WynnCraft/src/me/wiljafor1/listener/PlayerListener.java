package me.wiljafor1.listener;

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

import me.wiljafor1.events.JogadorDeathEvent;
import me.wiljafor1.events.JogadorFoundNewRegion;
import me.wiljafor1.interfaces.City;
import me.wiljafor1.interfaces.Region;
import me.wiljafor1.interfaces.RegionManager;
import me.wiljafor1.models.Account;
import me.wiljafor1.models.WynnPlayer;
import me.wiljafor1.models.WynnPlayerManager;
import me.wiljafor1.utils.SimpleRunnable;
import me.wiljafor1.utils.SimpleRunnable.TaskType;

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
	
	/*@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent e) {
		WynnPlayer w = WynnPlayerManager.getWynnPlayer(e.getPlayer());
		if(w.getCurrentAccount() != null) {
			Account a = w.getCurrentAccount();
			if(a.getLastcity() != 0) {
				City r = (City) RegionManager.getCityId(a.getLastcity());
				e.setRespawnLocation(r.getSpawn());
			}
		}
	}
	*/
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
		WynnPlayer w = WynnPlayerManager.getWynnPlayer(e.getPlayer());
		if(w.getCurrentAccount() != null) {
			Account a = w.getCurrentAccount();
			if(RegionManager.getCurrentRegion(e.getPlayer().getLocation()) != null) {
				JogadorFoundNewRegion event = new JogadorFoundNewRegion(e.getPlayer());
				Bukkit.getPluginManager().callEvent(event);
			}
		}
	}
	
	@EventHandler
    public void damage(EntityDamageByEntityEvent e) {
        Entity entity = e.getEntity();
        if(entity instanceof Player) {
            Damageable d = (Damageable)entity;
            WynnPlayer pw = WynnPlayerManager.getWynnPlayer((Player)entity);
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
}
