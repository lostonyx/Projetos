package com.criptonnetwork.util;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftLivingEntity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.world.ChunkUnloadEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.minecraft.server.v1_8_R3.Entity;
import net.minecraft.server.v1_8_R3.EntityInsentient;
import net.minecraft.server.v1_8_R3.EntityLiving;
import net.minecraft.server.v1_8_R3.GenericAttributes;
import net.minecraft.server.v1_8_R3.IAttribute;
import net.minecraft.server.v1_8_R3.NBTTagCompound;

/**
 * Created by JoanE on 07/09/2017.
 */
public class CustomEntity implements Listener {

    private final LivingEntity e;

    private boolean b;

    private double i;

    public CustomEntity(Location l, EntityType e) {
        this.e = (LivingEntity) l.getWorld().spawnEntity(l, e); // Spawn entity on x location.
        this.b = false;
        i = 0;
    }

    public double getMaxHealth() {
        return e.getMaxHealth();
    }

    public void setMaxHealth(double i) {
        e.setMaxHealth(i);
    }

    public double getHealth() {
        return e.getHealth();
    }

    public void setHealth(double i) {
        e.setHealth(i);
    }

    public String getCustomName() {
        return e.getCustomName();
    }
    
    public void setCustomName() {
    	
    }
    @EventHandler
    public void onTarget(EntityTargetEvent e) {
    	for(Player p : Bukkit.getOnlinePlayers()) {
    		if(e.getTarget().hasMetadata(p.getName() +"'s pet")) {
    			e.setCancelled(false);
    		}
    	}
    
    }
    
    
    public static void freezeEntity(LivingEntity e) {
        Entity en = ((CraftEntity) e).getHandle();
        NBTTagCompound c = new NBTTagCompound();
        en.c(c);
        c.setByte("NoAI", (byte) 1); // Freeze custom entity.
        en.f(c);
    }

    public static void unfreezeEntity(LivingEntity e) {
        Entity en = ((CraftEntity) e).getHandle();
        NBTTagCompound c = new NBTTagCompound();
        en.c(c);
        c.setByte("NoAI", (byte) 0); // Unfreeze custom entity.
        en.f(c);
    }

    public List<org.bukkit.entity.Entity> getNearbyEntities(Location l, int i) {
        List<org.bukkit.entity.Entity> eL = new ArrayList<>();
        for (org.bukkit.entity.Entity e : l.getWorld().getEntities())
            if (l.distance(e.getLocation()) <= i) eL.add(e);
        return eL;
    }

    public void addAttribute(GenericAttributes i, double v) {
        EntityLiving eL = ((CraftLivingEntity) e).getHandle();
        eL.getAttributeInstance((IAttribute) i).setValue(v);
    }

    public void setCustomName(String s) {
        e.setCustomName(s);
        e.setCustomNameVisible(true);
    }

    public void mountInto(LivingEntity e) {
        e.setPassenger(this.e); // Entity mount into x entity.
    }

    public List<PotionEffect> getPotionEffects() {
        List<PotionEffect> l = new ArrayList<>();
        l.addAll(e.getActivePotionEffects());
        return l;
    }

    public List<PotionEffectType> getPotionEffectsType() {
        List<PotionEffectType> l = new ArrayList<>();
        for (PotionEffect e : e.getActivePotionEffects()) l.add(e.getType());
        return l;
    }

    public boolean hasPottionEffectType(PotionEffectType eT) {
        List<PotionEffectType> l = new ArrayList<>();
        for (PotionEffect e : e.getActivePotionEffects()) l.add(e.getType());
        return l.contains(eT);
    }

    public void addPotionEffect(PotionEffectType eT, int lvl, int duration) {
        e.addPotionEffect(new PotionEffect(eT, duration, lvl - 1));
    }

    public void setUndamagable(boolean i) {
        this.b = i;
    }

    public boolean isUndamagable() {
        return this.b;
    }

    public void setReduceDamage(double i) {
        this.i = i;
    }

    public double getReduceDamage() {
        return i;
    }

    public static void walkTo(LivingEntity e , Location l, double speed) {
        EntityInsentient c = (EntityInsentient) ((CraftLivingEntity) e).getHandle();
        c.getNavigation().a(l.getX(), l.getY(), l.getZ(), speed); // Entity move to x location with x speed.
    }

    public LivingEntity getEntity() {
        return e;
    }

    @EventHandler
    public void onDamageByEntity(EntityDamageByEntityEvent e) {
        if (e.getEntity().equals(this.e)) {
            double i = this.i * e.getDamage() / 100;
            e.setDamage(e.getDamage() - i); // Reduce damage to entity.

            if (b) e.setCancelled(true); // Cancel damage to entity.
        }
    }

    @EventHandler
    public void onDamageEntity(EntityDamageEvent e) {
        if (e.getEntity().equals(this.e)) {
            double i = this.i * e.getDamage() / 100;
            e.setDamage(e.getDamage() - i); // Reduce damage to entity.

            if (b) e.setCancelled(true); // Cancel damage to entity.
        }
    }

    @EventHandler
    public void onUnloadChunk(ChunkUnloadEvent e) {
        if (b) for (org.bukkit.entity.Entity entity : e.getChunk().getEntities())
                if (entity.equals(this.e)) e.setCancelled(true); // Cancel despawn entity.
    }

}
 