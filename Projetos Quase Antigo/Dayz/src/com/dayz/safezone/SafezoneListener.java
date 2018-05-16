package com.dayz.safezone;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftLivingEntity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import net.minecraft.server.v1_8_R3.EntityInsentient;
import net.minecraft.server.v1_8_R3.GenericAttributes;

public class SafezoneListener {

    @EventHandler
    public void onTestEntityDamage(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player){
            if (event.getEntity() instanceof Player) {
            Player p =  (Player) event.getEntity() ;
            if(SafezoneManager.isOnSafeZone(p)) 
            	p.sendMessage("PVP OFF");
            	event.setCancelled(true);
            }
        }
    }
    //Teste - BlockMonster
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onEntitySpawn(CreatureSpawnEvent event){
        LivingEntity e = event.getEntity();
        if(SafezoneManager.isOnSafeZoneEntity(e)){
        	event.setCancelled(true);
        }
    }
}
