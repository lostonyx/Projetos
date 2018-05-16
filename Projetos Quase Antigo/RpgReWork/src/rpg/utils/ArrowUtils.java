package rpg.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import de.slikey.effectlib.util.ParticleEffect;
import rpg.system.models.Jogador;
import rpg.utils.SimpleRunnable.TaskType;

public class ArrowUtils{

	public static void spawnArrowEffected(Jogador j, ParticleEffect eff, boolean explode) {
        //double damage = (j.getLevel() + j.getAtk()) / 2;
        Arrow a = j.getPlayer().launchProjectile(Arrow.class);
        a.setVelocity(j.getPlayer().getLocation().getDirection().multiply(4));
        a.setShooter(j.getPlayer());
        SimpleRunnable r = new SimpleRunnable().getInstance();
        r.createTaskTimer(TaskType.SYNC, j.getPlayer()+"-ArrowUtils", new Runnable() {
			@Override
			public void run() {
				for(Player online : Bukkit.getOnlinePlayers()){
					if(a.isValid()) {
						Location loc = a.getLocation();
	                    ParticleEffect.FLAME.display(1.0F, 1.0F, 1.0F, 0.0F, 0, loc, new Player[]{online});
					}
                }	
				if(a.isOnGround()) {
			         if(explode) {
			            Bukkit.getOnlinePlayers().forEach((p) -> {
			               ParticleEffect.EXPLOSION_LARGE.display(1.0F, 1.0F, 1.0F, 0.0F, 0, a.getLocation(), new Player[]{p});
			            });
			         }
			         r.cancel(j.getPlayer()+"-ArrowUtils");
			         a.remove();
				}
			}
		}, 0L, 0L);
    }
	
	public static void spawnTripleArrowEffected(Jogador j, ParticleEffect eff, boolean explode) {
        //double damage = (j.getLevel() + j.getAtk()) / 2;
        Arrow a = j.getPlayer().launchProjectile(Arrow.class);
        a.setVelocity(j.getPlayer().getLocation().getDirection().multiply(4));
        a.setShooter(j.getPlayer());
        Arrow a1 = j.getPlayer().launchProjectile(Arrow.class);
        a.setVelocity(j.getPlayer().getLocation().getDirection().multiply(4));
        a.setShooter(j.getPlayer());
        Arrow a3 = j.getPlayer().launchProjectile(Arrow.class);
        a.setVelocity(j.getPlayer().getLocation().getDirection().multiply(4));
        a.setShooter(j.getPlayer());
        SimpleRunnable r = new SimpleRunnable().getInstance();
        r.createTaskTimer(TaskType.SYNC, j.getPlayer()+"-ArrowUtils", new Runnable() {
			@Override
			public void run() {
				for(Player online : Bukkit.getOnlinePlayers()){
					if(a.isValid()) {
						Location loc = a.getLocation();
	                    eff.display(1.0F, 1.0F, 1.0F, 0.0F, 0, loc, new Player[]{online});
					}
					if(a1.isValid()) {
						Location loc = a1.getLocation();
						eff.display(1.0F, 1.0F, 1.0F, 0.0F, 0, loc, new Player[]{online});
					}
					if(a3.isValid()) {
						Location loc = a3.getLocation();
						eff.display(1.0F, 1.0F, 1.0F, 0.0F, 0, loc, new Player[]{online});
					}
                }	
			}
		}, 0L, 0L);
    }
}
