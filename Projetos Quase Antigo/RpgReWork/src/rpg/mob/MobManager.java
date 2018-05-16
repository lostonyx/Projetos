package rpg.mob;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import net.minecraft.server.v1_8_R3.Entity;
import net.minecraft.server.v1_8_R3.EntityBat;
import net.minecraft.server.v1_8_R3.EntityBlaze;
import net.minecraft.server.v1_8_R3.EntityCaveSpider;
import net.minecraft.server.v1_8_R3.EntityChicken;
import net.minecraft.server.v1_8_R3.EntityCow;
import net.minecraft.server.v1_8_R3.EntityCreeper;
import net.minecraft.server.v1_8_R3.EntityEnderDragon;
import net.minecraft.server.v1_8_R3.EntityEnderman;
import net.minecraft.server.v1_8_R3.EntityEndermite;
import net.minecraft.server.v1_8_R3.EntityGhast;
import net.minecraft.server.v1_8_R3.EntityGiantZombie;
import net.minecraft.server.v1_8_R3.EntityGuardian;
import net.minecraft.server.v1_8_R3.EntityHorse;
import net.minecraft.server.v1_8_R3.EntityInsentient;
import net.minecraft.server.v1_8_R3.EntityIronGolem;
import net.minecraft.server.v1_8_R3.EntityLiving;
import net.minecraft.server.v1_8_R3.EntityMagmaCube;
import net.minecraft.server.v1_8_R3.EntityMushroomCow;
import net.minecraft.server.v1_8_R3.EntityOcelot;
import net.minecraft.server.v1_8_R3.EntityPig;
import net.minecraft.server.v1_8_R3.EntityPigZombie;
import net.minecraft.server.v1_8_R3.EntityRabbit;
import net.minecraft.server.v1_8_R3.EntitySheep;
import net.minecraft.server.v1_8_R3.EntitySilverfish;
import net.minecraft.server.v1_8_R3.EntitySkeleton;
import net.minecraft.server.v1_8_R3.EntitySlime;
import net.minecraft.server.v1_8_R3.EntitySnowman;
import net.minecraft.server.v1_8_R3.EntitySpider;
import net.minecraft.server.v1_8_R3.EntitySquid;
import net.minecraft.server.v1_8_R3.EntityVillager;
import net.minecraft.server.v1_8_R3.EntityWitch;
import net.minecraft.server.v1_8_R3.EntityWither;
import net.minecraft.server.v1_8_R3.EntityWolf;
import net.minecraft.server.v1_8_R3.EntityZombie;
import net.minecraft.server.v1_8_R3.GenericAttributes;
import net.minecraft.server.v1_8_R3.PathfinderGoalFloat;
import net.minecraft.server.v1_8_R3.World;
import rpg.mob.Mobs.Tipo;
import rpg.mob.customentity.a.EntityGuard;
import rpg.mob.customentity.scared.EntitySkeletonScared;
import rpg.mob.type.A;
import rpg.mob.type.B;
import rpg.mob.type.C;
import rpg.mob.type.D;
import rpg.system.models.RegionManager;

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
	
	public static void spawn(Mobs m, Location loc, int quant) {
		if (!loc.getChunk().isLoaded()) loc.getChunk().load();
		for(int y = 1 ; y < quant+1 ; y++) {
			if(m.classe().equals(Mobs.CEntityCustom.Defesa)) {
		        spawnEntity(getClassMob(m.classe(), m.type(), loc, m), loc); 
			}
			if(m.classe().equals(Mobs.CEntityCustom.Normal)) {
				spawnEntity(getClassMob(m.classe(), m.type(), loc, m), loc); 
			}
			if(m.classe().equals(Mobs.CEntityCustom.Assustado)) {
		        spawnEntity(getClassMob(m.classe(), m.type(), loc, m), loc); 
			}
			if(m.classe().equals(Mobs.CEntityCustom.Ataque)) {
				spawnEntity(getClassMob(m.classe(), m.type(), loc, m), loc); 
			}
			/*if(m.tipo().equals(Tipo.A)) {
				if(m.classe().equals(Mobs.CEntityCustom.Defesa)) {
			        spawnEntity(getClassMob(m.classe(), m.type(), loc, m), loc); 
				}
				if(m.classe().equals(Mobs.CEntityCustom.Normal)) {
					spawnEntity(getClassMob(m.classe(), m.type(), loc, m), loc); 
				}
				//A mob = (A) m;	
			}    
			if(m.tipo().equals(Tipo.B)) {
				//B mob = (B) m;
				if(m.classe().equals(Mobs.CEntityCustom.Defesa)) {
			        spawnEntity(getClassMob(m.classe(), m.type(), loc, m), loc); 
				}
				if(m.classe().equals(Mobs.CEntityCustom.Normal)) {
					spawnEntity(getClassMob(m.classe(), m.type(), loc, m), loc); 
				}
				if(m.classe().equals(Mobs.CEntityCustom.Assustado)) {
			        spawnEntity(getClassMob(m.classe(), m.type(), loc, m), loc); 
				}
				if(m.classe().equals(Mobs.CEntityCustom.Ataque)) {
					spawnEntity(getClassMob(m.classe(), m.type(), loc, m), loc); 
				}
			}    
			if(m.tipo().equals(Tipo.C)) {
				//C mob = (C) m;
				if(m.classe().equals(Mobs.CEntityCustom.Defesa)) {
			        spawnEntity(getClassMob(m.classe(), m.type(), loc, m), loc); 
				}
				if(m.classe().equals(Mobs.CEntityCustom.Normal)) {
					spawnEntity(getClassMob(m.classe(), m.type(), loc, m), loc); 
				}
				if(m.classe().equals(Mobs.CEntityCustom.Assustado)) {
			        spawnEntity(getClassMob(m.classe(), m.type(), loc, m), loc); 
				}
				if(m.classe().equals(Mobs.CEntityCustom.Ataque)) {
					spawnEntity(getClassMob(m.classe(), m.type(), loc, m), loc); 
				}
			}  
			if(m.tipo().equals(Tipo.D)) {
				//D mob = (D) m;
				if(m.classe().equals(Mobs.CEntityCustom.Defesa)) {
			        spawnEntity(getClassMob(m.classe(), m.type(), loc, m), loc); 
				}
				if(m.classe().equals(Mobs.CEntityCustom.Normal)) {
					spawnEntity(getClassMob(m.classe(), m.type(), loc, m), loc); 
				}
				if(m.classe().equals(Mobs.CEntityCustom.Assustado)) {
			        spawnEntity(getClassMob(m.classe(), m.type(), loc, m), loc); 
				}
				if(m.classe().equals(Mobs.CEntityCustom.Ataque)) {
					spawnEntity(getClassMob(m.classe(), m.type(), loc, m), loc); 
				}
			} */
		}
	}
	
    private static Entity spawnEntity(Entity entity, Location loc) {
    	if (!loc.getChunk().isLoaded()) loc.getChunk().load();
        entity.setLocation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
        ((CraftWorld) loc.getWorld()).getHandle().addEntity(entity, SpawnReason.CUSTOM);
        return entity;
    }
    
    public static void spawnEntity(String nome, Location loc) {
		for (Mobs m : MobManager.mobs) {
			if(m.name().equalsIgnoreCase(nome)) {
					spawn(m, loc, m.Amount());
			}	
		}
	}
    
    public static Entity getClassMob(Mobs.CEntityCustom tc, EntityType te, Location loc, Mobs m) {
    	if(tc.equals(Mobs.CEntityCustom.Assustado)) {
    		if(te.equals(EntityType.SKELETON)) {
    			EntityLiving entity = null;
    			CraftWorld craftWorld = (CraftWorld)loc.getWorld();
		        World world = craftWorld.getHandle();
		        entity = new EntitySkeletonScared(world);
		        entity.getAttributeInstance(GenericAttributes.maxHealth).setValue(m.life());
		        entity.getAttributeInstance(GenericAttributes.FOLLOW_RANGE).setValue(15);
		        entity.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(0.28);
		        entity.setCustomNameVisible(true);
		        entity.setCustomName(m.name());
		        entity.setCustomNameVisible(false);
		        entity.setHealth(m.life());	
		        return (Entity)entity;
    		}
    	}
    	if(tc.equals(Mobs.CEntityCustom.Defesa)) {
    		if(te.equals(EntityType.IRON_GOLEM)) {
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
		        return (Entity)entity;
    		}
    	}
    	if(tc.equals(Mobs.CEntityCustom.Ataque)) {
    		
    	}
    	if(tc.equals(Mobs.CEntityCustom.Normal)) {
    		if(te.equals(EntityType.ZOMBIE)) {
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
		        return (Entity)entity;
    		}
    		if(te.equals(EntityType.WOLF)) {
    			EntityLiving entity = null;
		        CraftWorld craftWorld = (CraftWorld)loc.getWorld();
		        World world = craftWorld.getHandle();
		        entity = new EntityWolf(world);
		        entity.getAttributeInstance(GenericAttributes.maxHealth).setValue(m.life());
		        entity.getAttributeInstance(GenericAttributes.FOLLOW_RANGE).setValue(30);
		        entity.getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).setValue(m.damage());
		        entity.setCustomNameVisible(true);
		        entity.setCustomName(m.name());
		        entity.setCustomNameVisible(false);
		        entity.setHealth(m.life());	
		        return (Entity)entity;
    		}
    		if(te.equals(EntityType.WITHER)) {
    			EntityLiving entity = null;
		        CraftWorld craftWorld = (CraftWorld)loc.getWorld();
		        World world = craftWorld.getHandle();
		        entity = new EntityWither(world);
		        entity.getAttributeInstance(GenericAttributes.maxHealth).setValue(m.life());
		        entity.getAttributeInstance(GenericAttributes.FOLLOW_RANGE).setValue(30);
		        entity.getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).setValue(m.damage());
		        entity.setCustomNameVisible(true);
		        entity.setCustomName(m.name());
		        entity.setCustomNameVisible(false);
		        entity.setHealth(m.life());	
		        return (Entity)entity;
    		}
    		if(te.equals(EntityType.WITCH)) {
    			EntityLiving entity = null;
		        CraftWorld craftWorld = (CraftWorld)loc.getWorld();
		        World world = craftWorld.getHandle();
		        entity = new EntityWitch(world);
		        entity.getAttributeInstance(GenericAttributes.maxHealth).setValue(m.life());
		        entity.getAttributeInstance(GenericAttributes.FOLLOW_RANGE).setValue(30);
		        entity.getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).setValue(m.damage());
		        entity.setCustomNameVisible(true);
		        entity.setCustomName(m.name());
		        entity.setCustomNameVisible(false);
		        entity.setHealth(m.life());	
		        return (Entity)entity;
    		}
    		if(te.equals(EntityType.VILLAGER)) {
    			EntityLiving entity = null;
		        CraftWorld craftWorld = (CraftWorld)loc.getWorld();
		        World world = craftWorld.getHandle();
		        entity = new EntityVillager(world);
		        entity.getAttributeInstance(GenericAttributes.maxHealth).setValue(m.life());
		        entity.getAttributeInstance(GenericAttributes.FOLLOW_RANGE).setValue(30);
		        entity.setCustomNameVisible(true);
		        entity.setCustomName(m.name());
		        entity.setCustomNameVisible(false);
		        entity.setHealth(m.life());	
		        return (Entity)entity;
    		}
    		if(te.equals(EntityType.SQUID)) {
    			EntityLiving entity = null;
		        CraftWorld craftWorld = (CraftWorld)loc.getWorld();
		        World world = craftWorld.getHandle();
		        entity = new EntitySquid(world);
		        entity.getAttributeInstance(GenericAttributes.maxHealth).setValue(m.life());
		        entity.getAttributeInstance(GenericAttributes.FOLLOW_RANGE).setValue(30);
		        entity.getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).setValue(m.damage());
		        entity.setCustomNameVisible(true);
		        entity.setCustomName(m.name());
		        entity.setCustomNameVisible(false);
		        entity.setHealth(m.life());	
		        return (Entity)entity;
    		}
    		if(te.equals(EntityType.SPIDER)) {
    			EntityLiving entity = null;
		        CraftWorld craftWorld = (CraftWorld)loc.getWorld();
		        World world = craftWorld.getHandle();
		        entity = new EntitySpider(world);
		        entity.getAttributeInstance(GenericAttributes.maxHealth).setValue(m.life());
		        entity.getAttributeInstance(GenericAttributes.FOLLOW_RANGE).setValue(30);
		        entity.getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).setValue(m.damage());
		        entity.setCustomNameVisible(true);
		        entity.setCustomName(m.name());
		        entity.setCustomNameVisible(false);
		        entity.setHealth(m.life());	
		        return (Entity)entity;
    		}
    		if(te.equals(EntityType.SNOWMAN)) {
    			EntityLiving entity = null;
		        CraftWorld craftWorld = (CraftWorld)loc.getWorld();
		        World world = craftWorld.getHandle();
		        entity = new EntitySnowman(world);
		        entity.getAttributeInstance(GenericAttributes.maxHealth).setValue(m.life());
		        entity.getAttributeInstance(GenericAttributes.FOLLOW_RANGE).setValue(30);
		        entity.getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).setValue(m.damage());
		        entity.setCustomNameVisible(true);
		        entity.setCustomName(m.name());
		        entity.setCustomNameVisible(false);
		        entity.setHealth(m.life());	
		        return (Entity)entity;
    		}
    		if(te.equals(EntityType.SLIME)) {
    			EntityLiving entity = null;
		        CraftWorld craftWorld = (CraftWorld)loc.getWorld();
		        World world = craftWorld.getHandle();
		        entity = new EntitySlime(world);
		        entity.getAttributeInstance(GenericAttributes.maxHealth).setValue(m.life());
		        entity.getAttributeInstance(GenericAttributes.FOLLOW_RANGE).setValue(30);
		        entity.getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).setValue(m.damage());
		        entity.setCustomNameVisible(true);
		        entity.setCustomName(m.name());
		        entity.setCustomNameVisible(false);
		        entity.setHealth(m.life());	
		        return (Entity)entity;
    		}
    		if(te.equals(EntityType.SKELETON)) {
    			EntityLiving entity = null;
		        CraftWorld craftWorld = (CraftWorld)loc.getWorld();
		        World world = craftWorld.getHandle();
		        entity = new EntitySkeleton(world);
		        entity.getAttributeInstance(GenericAttributes.maxHealth).setValue(m.life());
		        entity.getAttributeInstance(GenericAttributes.FOLLOW_RANGE).setValue(30);
		        entity.getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).setValue(m.damage());
		        entity.setCustomNameVisible(true);
		        entity.setCustomName(m.name());
		        entity.setCustomNameVisible(false);
		        entity.setHealth(m.life());	
		        return (Entity)entity;
    		}
    		if(te.equals(EntityType.SILVERFISH)) {
    			EntityLiving entity = null;
		        CraftWorld craftWorld = (CraftWorld)loc.getWorld();
		        World world = craftWorld.getHandle();
		        entity = new EntitySilverfish(world);
		        entity.getAttributeInstance(GenericAttributes.maxHealth).setValue(m.life());
		        entity.getAttributeInstance(GenericAttributes.FOLLOW_RANGE).setValue(30);
		        entity.getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).setValue(m.damage());
		        entity.setCustomNameVisible(true);
		        entity.setCustomName(m.name());
		        entity.setCustomNameVisible(false);
		        entity.setHealth(m.life());	
		        return (Entity)entity;
    		}
    		if(te.equals(EntityType.SHEEP)) {
    			EntityLiving entity = null;
		        CraftWorld craftWorld = (CraftWorld)loc.getWorld();
		        World world = craftWorld.getHandle();
		        entity = new EntitySheep(world);
		        entity.getAttributeInstance(GenericAttributes.maxHealth).setValue(m.life());
		        entity.getAttributeInstance(GenericAttributes.FOLLOW_RANGE).setValue(30);
		        entity.getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).setValue(m.damage());
		        entity.setCustomNameVisible(true);
		        entity.setCustomName(m.name());
		        entity.setCustomNameVisible(false);
		        entity.setHealth(m.life());	
		        return (Entity)entity;
    		}
    		if(te.equals(EntityType.RABBIT)) {
    			EntityLiving entity = null;
		        CraftWorld craftWorld = (CraftWorld)loc.getWorld();
		        World world = craftWorld.getHandle();
		        entity = new EntityRabbit(world);
		        entity.getAttributeInstance(GenericAttributes.maxHealth).setValue(m.life());
		        entity.getAttributeInstance(GenericAttributes.FOLLOW_RANGE).setValue(30);
		        entity.getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).setValue(m.damage());
		        entity.setCustomNameVisible(true);
		        entity.setCustomName(m.name());
		        entity.setCustomNameVisible(false);
		        entity.setHealth(m.life());	
		        return (Entity)entity;
    		}
    		if(te.equals(EntityType.PIG_ZOMBIE)) {
    			EntityLiving entity = null;
		        CraftWorld craftWorld = (CraftWorld)loc.getWorld();
		        World world = craftWorld.getHandle();
		        entity = new EntityPigZombie(world);
		        entity.getAttributeInstance(GenericAttributes.maxHealth).setValue(m.life());
		        entity.getAttributeInstance(GenericAttributes.FOLLOW_RANGE).setValue(30);
		        entity.getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).setValue(m.damage());
		        entity.setCustomNameVisible(true);
		        entity.setCustomName(m.name());
		        entity.setCustomNameVisible(false);
		        entity.setHealth(m.life());	
		        return (Entity)entity;
    		}
    		if(te.equals(EntityType.PIG)) {
    			EntityLiving entity = null;
		        CraftWorld craftWorld = (CraftWorld)loc.getWorld();
		        World world = craftWorld.getHandle();
		        entity = new EntityPig(world);
		        entity.getAttributeInstance(GenericAttributes.maxHealth).setValue(m.life());
		        entity.getAttributeInstance(GenericAttributes.FOLLOW_RANGE).setValue(30);
		        entity.getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).setValue(m.damage());
		        entity.setCustomNameVisible(true);
		        entity.setCustomName(m.name());
		        entity.setCustomNameVisible(false);
		        entity.setHealth(m.life());	
		        return (Entity)entity;
    		}
    		if(te.equals(EntityType.OCELOT)) {
    			EntityLiving entity = null;
		        CraftWorld craftWorld = (CraftWorld)loc.getWorld();
		        World world = craftWorld.getHandle();
		        entity = new EntityOcelot(world);
		        entity.getAttributeInstance(GenericAttributes.maxHealth).setValue(m.life());
		        entity.getAttributeInstance(GenericAttributes.FOLLOW_RANGE).setValue(30);
		        entity.getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).setValue(m.damage());
		        entity.setCustomNameVisible(true);
		        entity.setCustomName(m.name());
		        entity.setCustomNameVisible(false);
		        entity.setHealth(m.life());	
		        return (Entity)entity;
    		}
    		if(te.equals(EntityType.MUSHROOM_COW)) {
    			EntityLiving entity = null;
		        CraftWorld craftWorld = (CraftWorld)loc.getWorld();
		        World world = craftWorld.getHandle();
		        entity = new EntityMushroomCow(world);
		        entity.getAttributeInstance(GenericAttributes.maxHealth).setValue(m.life());
		        entity.getAttributeInstance(GenericAttributes.FOLLOW_RANGE).setValue(30);
		        entity.getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).setValue(m.damage());
		        entity.setCustomNameVisible(true);
		        entity.setCustomName(m.name());
		        entity.setCustomNameVisible(false);
		        entity.setHealth(m.life());	
		        return (Entity)entity;
    		}
    		if(te.equals(EntityType.MAGMA_CUBE)) {
    			EntityLiving entity = null;
		        CraftWorld craftWorld = (CraftWorld)loc.getWorld();
		        World world = craftWorld.getHandle();
		        entity = new EntityMagmaCube(world);
		        entity.getAttributeInstance(GenericAttributes.maxHealth).setValue(m.life());
		        entity.getAttributeInstance(GenericAttributes.FOLLOW_RANGE).setValue(30);
		        entity.getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).setValue(m.damage());
		        entity.setCustomNameVisible(true);
		        entity.setCustomName(m.name());
		        entity.setCustomNameVisible(false);
		        entity.setHealth(m.life());	
		        return (Entity)entity;
    		}
    		if(te.equals(EntityType.IRON_GOLEM)) {
    			EntityLiving entity = null;
		        CraftWorld craftWorld = (CraftWorld)loc.getWorld();
		        World world = craftWorld.getHandle();
		        entity = new EntityIronGolem(world);
		        entity.getAttributeInstance(GenericAttributes.maxHealth).setValue(m.life());
		        entity.getAttributeInstance(GenericAttributes.FOLLOW_RANGE).setValue(30);
		        entity.getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).setValue(m.damage());
		        entity.setCustomNameVisible(true);
		        entity.setCustomName(m.name());
		        entity.setCustomNameVisible(false);
		        entity.setHealth(m.life());	
		        return (Entity)entity;
    		}
    		if(te.equals(EntityType.HORSE)) {
    			EntityLiving entity = null;
		        CraftWorld craftWorld = (CraftWorld)loc.getWorld();
		        World world = craftWorld.getHandle();
		        entity = new EntityHorse(world);
		        entity.getAttributeInstance(GenericAttributes.maxHealth).setValue(m.life());
		        entity.getAttributeInstance(GenericAttributes.FOLLOW_RANGE).setValue(30);
		        entity.getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).setValue(m.damage());
		        entity.setCustomNameVisible(true);
		        entity.setCustomName(m.name());
		        entity.setCustomNameVisible(false);
		        entity.setHealth(m.life());	
		        return (Entity)entity;
    		}
    		if(te.equals(EntityType.GUARDIAN)) {
    			EntityLiving entity = null;
		        CraftWorld craftWorld = (CraftWorld)loc.getWorld();
		        World world = craftWorld.getHandle();
		        entity = new EntityGuardian(world);
		        entity.getAttributeInstance(GenericAttributes.maxHealth).setValue(m.life());
		        entity.getAttributeInstance(GenericAttributes.FOLLOW_RANGE).setValue(30);
		        entity.getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).setValue(m.damage());
		        entity.setCustomNameVisible(true);
		        entity.setCustomName(m.name());
		        entity.setCustomNameVisible(false);
		        entity.setHealth(m.life());	
		        return (Entity)entity;
    		}
    		if(te.equals(EntityType.GIANT)) {
    			EntityLiving entity = null;
		        CraftWorld craftWorld = (CraftWorld)loc.getWorld();
		        World world = craftWorld.getHandle();
		        entity = new EntityGiantZombie(world);
		        entity.getAttributeInstance(GenericAttributes.maxHealth).setValue(m.life());
		        entity.getAttributeInstance(GenericAttributes.FOLLOW_RANGE).setValue(30);
		        entity.getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).setValue(m.damage());
		        entity.setCustomNameVisible(true);
		        entity.setCustomName(m.name());
		        entity.setCustomNameVisible(false);
		        entity.setHealth(m.life());	
		        return (Entity)entity;
    		}
    		if(te.equals(EntityType.GHAST)) {
    			EntityLiving entity = null;
		        CraftWorld craftWorld = (CraftWorld)loc.getWorld();
		        World world = craftWorld.getHandle();
		        entity = new EntityGhast(world);
		        entity.getAttributeInstance(GenericAttributes.maxHealth).setValue(m.life());
		        entity.getAttributeInstance(GenericAttributes.FOLLOW_RANGE).setValue(30);
		        entity.getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).setValue(m.damage());
		        entity.setCustomNameVisible(true);
		        entity.setCustomName(m.name());
		        entity.setCustomNameVisible(false);
		        entity.setHealth(m.life());	
		        return (Entity)entity;
    		}
    		if(te.equals(EntityType.ENDERMITE)) {
    			EntityLiving entity = null;
		        CraftWorld craftWorld = (CraftWorld)loc.getWorld();
		        World world = craftWorld.getHandle();
		        entity = new EntityEndermite(world);
		        entity.getAttributeInstance(GenericAttributes.maxHealth).setValue(m.life());
		        entity.getAttributeInstance(GenericAttributes.FOLLOW_RANGE).setValue(30);
		        entity.getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).setValue(m.damage());
		        entity.setCustomNameVisible(true);
		        entity.setCustomName(m.name());
		        entity.setCustomNameVisible(false);
		        entity.setHealth(m.life());	
		        return (Entity)entity;
    		}
    		if(te.equals(EntityType.ENDERMAN)) {
    			EntityLiving entity = null;
		        CraftWorld craftWorld = (CraftWorld)loc.getWorld();
		        World world = craftWorld.getHandle();
		        entity = new EntityEnderman(world);
		        entity.getAttributeInstance(GenericAttributes.maxHealth).setValue(m.life());
		        entity.getAttributeInstance(GenericAttributes.FOLLOW_RANGE).setValue(30);
		        entity.getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).setValue(m.damage());
		        entity.setCustomNameVisible(true);
		        entity.setCustomName(m.name());
		        entity.setCustomNameVisible(false);
		        entity.setHealth(m.life());	
		        return (Entity)entity;
    		}
    		if(te.equals(EntityType.ENDER_DRAGON)) {
    			EntityLiving entity = null;
		        CraftWorld craftWorld = (CraftWorld)loc.getWorld();
		        World world = craftWorld.getHandle();
		        entity = new EntityEnderDragon(world);
		        entity.getAttributeInstance(GenericAttributes.maxHealth).setValue(m.life());
		        entity.getAttributeInstance(GenericAttributes.FOLLOW_RANGE).setValue(30);
		        entity.getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).setValue(m.damage());
		        entity.setCustomNameVisible(true);
		        entity.setCustomName(m.name());
		        entity.setCustomNameVisible(false);
		        entity.setHealth(m.life());	
		        return (Entity)entity;
    		}
    		if(te.equals(EntityType.CREEPER)) {
    			EntityLiving entity = null;
		        CraftWorld craftWorld = (CraftWorld)loc.getWorld();
		        World world = craftWorld.getHandle();
		        entity = new EntityCreeper(world);
		        entity.getAttributeInstance(GenericAttributes.maxHealth).setValue(m.life());
		        entity.getAttributeInstance(GenericAttributes.FOLLOW_RANGE).setValue(30);
		        entity.getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).setValue(m.damage());
		        entity.setCustomNameVisible(true);
		        entity.setCustomName(m.name());
		        entity.setCustomNameVisible(false);
		        entity.setHealth(m.life());	
		        return (Entity)entity;
    		}
    		if(te.equals(EntityType.CHICKEN)) {
    			EntityLiving entity = null;
		        CraftWorld craftWorld = (CraftWorld)loc.getWorld();
		        World world = craftWorld.getHandle();
		        entity = new EntityChicken(world);
		        entity.getAttributeInstance(GenericAttributes.maxHealth).setValue(m.life());
		        entity.getAttributeInstance(GenericAttributes.FOLLOW_RANGE).setValue(30);
		        entity.getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).setValue(m.damage());
		        entity.setCustomNameVisible(true);
		        entity.setCustomName(m.name());
		        entity.setCustomNameVisible(false);
		        entity.setHealth(m.life());	
		        return (Entity)entity;
    		}
    		if(te.equals(EntityType.CAVE_SPIDER)) {
    			EntityLiving entity = null;
		        CraftWorld craftWorld = (CraftWorld)loc.getWorld();
		        World world = craftWorld.getHandle();
		        entity = new EntityCaveSpider(world);
		        entity.getAttributeInstance(GenericAttributes.maxHealth).setValue(m.life());
		        entity.getAttributeInstance(GenericAttributes.FOLLOW_RANGE).setValue(30);
		        entity.getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).setValue(m.damage());
		        entity.setCustomNameVisible(true);
		        entity.setCustomName(m.name());
		        entity.setCustomNameVisible(false);
		        entity.setHealth(m.life());	
		        return (Entity)entity;
    		}
    		if(te.equals(EntityType.BLAZE)) {
    			EntityLiving entity = null;
		        CraftWorld craftWorld = (CraftWorld)loc.getWorld();
		        World world = craftWorld.getHandle();
		        entity = new EntityBlaze(world);
		        entity.getAttributeInstance(GenericAttributes.maxHealth).setValue(m.life());
		        entity.getAttributeInstance(GenericAttributes.FOLLOW_RANGE).setValue(30);
		        entity.getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).setValue(m.damage());
		        entity.setCustomNameVisible(true);
		        entity.setCustomName(m.name());
		        entity.setCustomNameVisible(false);
		        entity.setHealth(m.life());	
		        return (Entity)entity;
    		}
    		if(te.equals(EntityType.BAT)) {
    			EntityLiving entity = null;
		        CraftWorld craftWorld = (CraftWorld)loc.getWorld();
		        World world = craftWorld.getHandle();
		        entity = new EntityBat(world);
		        entity.getAttributeInstance(GenericAttributes.maxHealth).setValue(m.life());
		        entity.getAttributeInstance(GenericAttributes.FOLLOW_RANGE).setValue(30);
		        entity.getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).setValue(m.damage());
		        entity.setCustomNameVisible(true);
		        entity.setCustomName(m.name());
		        entity.setCustomNameVisible(false);
		        entity.setHealth(m.life());	
		        return (Entity)entity;
    		}
    	}
		return null;
    }
    
}
