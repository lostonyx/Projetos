package me.wiljafor1.system.mobs;

import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import me.libraryaddict.disguise.disguisetypes.MobDisguise;
import me.libraryaddict.disguise.disguisetypes.PlayerDisguise;
import me.wiljafor1.system.mobs.Mobs.Tipo;
import me.wiljafor1.system.mobs.customentity.a.EntityGuard;
import net.minecraft.server.v1_8_R3.Entity;
import net.minecraft.server.v1_8_R3.EntityInsentient;
import net.minecraft.server.v1_8_R3.EntityIronGolem;
import net.minecraft.server.v1_8_R3.EntityLiving;
import net.minecraft.server.v1_8_R3.EntityVillager;
import net.minecraft.server.v1_8_R3.EntityZombie;
import net.minecraft.server.v1_8_R3.GenericAttributes;
import net.minecraft.server.v1_8_R3.PathfinderGoalFloat;
import net.minecraft.server.v1_8_R3.World;

public class MobManager {

	public static List<Mobs> mobs = Lists.newArrayList();

	public static Mobs getMob(String name) {
		for (Mobs m : mobs) {
			if (m.name().equalsIgnoreCase(name)) {
				return m;
			}
		}
		return null;
	}

	public static void add(Mobs m) {
		mobs.add(m);
	}

	public static void remove(Mobs m) {
		mobs.remove(m);
	}
	
	/*public static void spawn(Mobs m, Location loc, int quant) {
		for(int y = 1 ; y < quant+1 ; y++) {
			if(m.type().equals(EntityType.IRON_GOLEM)) {
				if(m.city() == true) {
					Passive c =(Passive) m;
					if(c.mode().equals(Mode.GUARD)) {
						EntityLiving entity = null;
				        CraftWorld craftWorld = (CraftWorld)loc.getWorld();
				        World world = craftWorld.getHandle();
				        entity = new EntityGuard(loc.getWorld(), loc);
				        entity.getAttributeInstance(GenericAttributes.maxHealth).setValue(m.life());
				        entity.getAttributeInstance(GenericAttributes.FOLLOW_RANGE).setValue(10);
				        entity.setCustomNameVisible(true);
				        entity.setCustomName(m.name());
				        entity.setCustomNameVisible(false);
				        entity.setHealth(m.life());	
				        spawnEntity(entity, loc); 
					}else {
						EntityLiving entity = null;
				        CraftWorld craftWorld = (CraftWorld)loc.getWorld();
				        World world = craftWorld.getHandle();
				        entity = new EntityIronGolem(world);
				        entity.getAttributeInstance(GenericAttributes.maxHealth).setValue(m.life());
				        entity.setCustomNameVisible(true);
				        entity.setCustomName(m.name());
				        entity.setCustomNameVisible(false);
				        entity.setHealth(m.life());	
				        spawnEntity(entity, loc); 
					}
					
				}
			}
			if(m.type().equals(EntityType.ZOMBIE)) {
				EntityLiving entity = null;
		        CraftWorld craftWorld = (CraftWorld)loc.getWorld();
		        World world = craftWorld.getHandle();
		        entity = new EntityZombie(world);
		        entity.getAttributeInstance(GenericAttributes.maxHealth).setValue(m.life());
		        entity.getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).setValue(m.damage());
		        entity.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(0.3);
		        entity.setCustomNameVisible(true);
		        entity.setCustomName(m.name());
		        entity.setCustomNameVisible(false);
		        entity.setHealth(m.life());	
		        spawnEntity(entity, loc);
			}     
		}
	}*/
	
	public static void spawn(Mobs m, Location loc, int quant) {
		if (!loc.getChunk().isLoaded()) loc.getChunk().load();
		for(int y = 1 ; y < quant+1 ; y++) {
			if(m.tipo().equals(Tipo.A)) {
				A mob = (A) m;
				if(mob.city()) {
					if(mob.mode().equals(me.wiljafor1.system.mobs.A.Mode.GUARD)) {
						if(mob.type().equals(EntityType.IRON_GOLEM)) {
							//EntityLiving entity = null;
					        CraftWorld craftWorld = (CraftWorld)loc.getWorld();
					        World world = craftWorld.getHandle();
					        EntityGuard entity = EntityGuard.start(loc);
					        entity.getAttributeInstance(GenericAttributes.maxHealth).setValue(m.life());
					        entity.getAttributeInstance(GenericAttributes.FOLLOW_RANGE).setValue(15);
					        entity.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(0.28);
					        entity.setCustomNameVisible(true);
					        entity.setCustomName(m.name());
					        entity.setCustomNameVisible(false);
					        entity.setHealth(m.life());	
					        spawnEntity(entity, loc); 
						}
					}
					else if(mob.mode().equals(me.wiljafor1.system.mobs.A.Mode.NORMAL)) {
						if(mob.type().equals(EntityType.IRON_GOLEM)) {
							
						}
					}
				}
			}    
			if(m.tipo().equals(Tipo.B)) {
				B mob = (B) m;
				if(mob.type().equals(EntityType.VILLAGER)) {
					EntityLiving entity = null;
			        CraftWorld craftWorld = (CraftWorld)loc.getWorld();
			        World world = craftWorld.getHandle();
			        entity = new EntityVillager(world);
			        entity.getAttributeInstance(GenericAttributes.maxHealth).setValue(m.life());
			        entity.setCustomNameVisible(true);
			        entity.setCustomName(m.name());
			        entity.setCustomNameVisible(false);
			        entity.setHealth(m.life());	
			        spawnEntity(entity, loc);
				}
			}    
			if(m.tipo().equals(Tipo.C)) {
				C mob = (C) m;
			}  
			if(m.tipo().equals(Tipo.D)) {
				D mob = (D) m;
				if(mob.type().equals(EntityType.ZOMBIE)) {
					EntityLiving entity = null;
			        CraftWorld craftWorld = (CraftWorld)loc.getWorld();
			        World world = craftWorld.getHandle();
			        entity = new EntityZombie(world);
			        entity.getAttributeInstance(GenericAttributes.maxHealth).setValue(m.life());
			        entity.getAttributeInstance(GenericAttributes.FOLLOW_RANGE).setValue(30);
			        entity.getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).setValue(m.damage());
			        entity.setCustomNameVisible(true);
			        entity.setCustomName(m.name());
			        entity.setCustomNameVisible(false);
			        entity.setHealth(m.life());	
			        spawnEntity(entity, loc);
				}
			} 
		}
	}
	
    private static Entity spawnEntity(Entity entity, Location loc) {
    	if (!loc.getChunk().isLoaded()) loc.getChunk().load();
        entity.setLocation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
        ((CraftWorld) loc.getWorld()).getHandle().addEntity(entity, SpawnReason.CUSTOM);
        return entity;
    }
}
