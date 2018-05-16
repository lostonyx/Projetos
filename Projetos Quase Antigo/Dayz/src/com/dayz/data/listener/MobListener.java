package com.dayz.data.listener;

import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftLivingEntity;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Creature;
import org.bukkit.entity.CreatureType;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Giant;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.event.entity.EntityCombustEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.vehicle.VehicleExitEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

import com.criptonnetwork.util.ProgressBar;
import com.dayz.type.Jogador;
import com.dayz.type.JogadorManager;
import com.dayz.utils.Hologram;

import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import me.libraryaddict.disguise.disguisetypes.MobDisguise;
import net.minecraft.server.v1_8_R3.AttributeInstance;
import net.minecraft.server.v1_8_R3.AttributeModifier;
import net.minecraft.server.v1_8_R3.EntityCreature;
import net.minecraft.server.v1_8_R3.EntityInsentient;
import net.minecraft.server.v1_8_R3.GenericAttributes;

public class MobListener implements Listener {

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onEntitySpawn(CreatureSpawnEvent event){
        LivingEntity entity = event.getEntity();
            if (entity.getType() == EntityType.ZOMBIE){
                EntityInsentient nmsEntity = (EntityInsentient) ((CraftLivingEntity) entity).getHandle();
                nmsEntity.getAttributeInstance(GenericAttributes.maxHealth).setValue(50.0);
                nmsEntity.getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).setValue(3.0);
                nmsEntity.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(0.3);
                entity.setCustomName("§4Zombie");
                entity.setCustomNameVisible(true);
                entity.setHealth(50.0);
            }
            else if (entity.getType() == EntityType.ARMOR_STAND){
                return;
            }
            else{
            	event.setCancelled(true);
            }

    }
	
    @EventHandler
    public void onSpawnMobS(CreatureSpawnEvent event){
    	if(event.getSpawnReason() == SpawnReason.SPAWNER){
    		if(event.getEntityType() == EntityType.ZOMBIE){
        		LivingEntity entity = event.getEntity();
        		EntityInsentient nmsEntity = (EntityInsentient) ((CraftLivingEntity) entity).getHandle();
                nmsEntity.getAttributeInstance(GenericAttributes.maxHealth).setValue(200.0);
                nmsEntity.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(5.3);
                //Hologram.spawnNameMob(entity);
                entity.setCustomName("§2Zombie");
                entity.setCustomNameVisible(true);
                entity.setHealth(200.0);
                MobDisguise mobDisguise = new MobDisguise(DisguiseType.GIANT);
                DisguiseAPI.disguiseToAll(entity, mobDisguise);
    		}
    		else{
    			event.setCancelled(true);
    		}
    	}
    }
    
	@EventHandler
	public void onKill(EntityDeathEvent e) {
		Entity entity = e.getEntity();
		if (entity instanceof Player)
			return;
		if(entity instanceof Zombie){
			if(e.getEntity().getKiller() instanceof Player){
				Player p = e.getEntity().getKiller();

				Jogador j = JogadorManager.getJogador(p);
				int xp = new Random().nextInt(j.getCurrentAccount().getLevel() * 10);
				j.getCurrentAccount().setTotalzombies(j.getCurrentAccount().getTotalzombies()+1);
				j.getCurrentAccount().setXp(j.getCurrentAccount().getXp() + xp);
				j.update();
				p.sendMessage("§c+ " + xp + " de experiencia.");
			
			}
			else return;
		}
		if(entity.getPassenger() != null) {
			entity.getPassenger().remove();
		}

	}

	@EventHandler
	public void removeWeather(WeatherChangeEvent e) {
		if (e.getWorld().isThundering() == true) {
			e.getWorld().setThundering(false);
		} else {
			//e.setCancelled(true);
		}
	}
	
	
	@EventHandler
	public void onDamage(EntityDamageEvent e) {
		if (e.getEntity() instanceof Player)
			return;
		if (e.isCancelled())
			return;
		if (!(e.getEntity() instanceof Damageable))
			return;
		if (e.getEntity() instanceof ArmorStand)
			return;
		Damageable i = (Damageable) e.getEntity();
		if (i.getPassenger() != null) {
			if (i.getPassenger() instanceof ArmorStand) {
				i.getPassenger().setCustomName(
						ProgressBar.getProgressBar((int) i.getHealth(), (int) i.getMaxHealth(), 20, "|", "§a", "§c"));
				Hologram.spawnHologram(i.getLocation().add(0, 2, 0), 2, "§4-" + e.getDamage() + "");
				
			}
		} else {
			Hologram.spawnHealthBar(i);
		}

	}

	
	 @EventHandler
	 public void onEntityCombust(EntityCombustEvent event){
	 if(event.getEntity() instanceof Zombie){
	 event.setCancelled(true);
	 }
	 } 
	 
	 //https://www.spigotmc.org/threads/passengers-leave-vehicles-when-spawned-underwater-despite-cancelling-the-entity_attach-packet.119938/
	    @EventHandler
	    public void onUnmount(VehicleExitEvent event){
	        if(event.getVehicle() instanceof Zombie){
	            if(event.getExited() instanceof ArmorStand){
	            event.setCancelled(true);
	            }
	        }
	    }
}
