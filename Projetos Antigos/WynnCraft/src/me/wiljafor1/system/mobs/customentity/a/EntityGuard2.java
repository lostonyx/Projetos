package me.wiljafor1.system.mobs.customentity.a;

import java.lang.reflect.Field;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.util.UnsafeList;

import net.minecraft.server.v1_8_R3.*;

public class EntityGuard2 extends EntityIronGolem{
	private Location l;
    //https://www.spigotmc.org/threads/pets-that-spawn-by-player-and-follow-them.33874/
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public EntityGuard2(org.bukkit.World world, Location lc) {
        super(((CraftWorld)world).getHandle());
        try {
        	Field bField = PathfinderGoalSelector.class.getDeclaredField("b");
        	bField.setAccessible(true);
        	Field cField = PathfinderGoalSelector.class.getDeclaredField("c");
        	cField.setAccessible(true);
        	bField.set(goalSelector, new UnsafeList<PathfinderGoalSelector>());
        	bField.set(targetSelector, new UnsafeList<PathfinderGoalSelector>());
        	cField.set(goalSelector, new UnsafeList<PathfinderGoalSelector>());
        	cField.set(targetSelector, new UnsafeList<PathfinderGoalSelector>());
        	} catch (Exception exc) {
        	exc.printStackTrace();
        	}  
        this.goalSelector.a(0, new PathfinderGoalFloat(this));
        //this.goalSelector.a(2, new PathfinderGoalMeleeAttack(this, EntityHuman.class, 1.0D, false));
        this.goalSelector.a(1, new PathfinderGoalWalktoTile(this, lc));
        this.goalSelector.a(4, new PathfinderGoalMeleeAttack(this, EntityZombie.class, 1.0D, true));
        this.goalSelector.a(6, new PathfinderGoalMoveThroughVillage(this, 1.0D, false));
        this.goalSelector.a(8, new PathfinderGoalLookAtPlayer(this, EntityZombie.class, 8.0F));
        this.targetSelector.a(1, new PathfinderGoalHurtByTarget(this, false));
        //this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget(this, EntityHuman.class, true));
        this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget(this, EntityZombie.class, false));
        /*this.goalSelector.a(0, new PathfinderGoalFloat(this));
        this.goalSelector.a(1, new PathfinderGoalWalktoTile(this, lc));
        this.goalSelector.a(2, new PathfinderGoalMeleeAttack(this, EntityZombie.class, 8.0D, false));
        this.targetSelector.a(1, new PathfinderGoalHurtByTarget(this, false, new Class[] { EntityZombie.class}));
        this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget(this, EntityZombie.class, true));
        this.goalSelector.a(1, new PathfinderGoalMeleeAttack(this, 1.0D, true));
        this.goalSelector.a(2, new PathfinderGoalMoveTowardsTarget(this, 0.9D, 32.0F));
        this.goalSelector.a(3, new PathfinderGoalMoveThroughVillage(this, 0.6D, true));
        this.goalSelector.a(4, new PathfinderGoalMoveTowardsRestriction(this, 1.0D));
        this.goalSelector.a(6, new PathfinderGoalRandomStroll(this, 0.6D));*/
    }
	/*@SuppressWarnings({ "unchecked", "rawtypes" })
	public EntityGuard(World world) {
        super(world);
        try {
        	Field bField = PathfinderGoalSelector.class.getDeclaredField("b");
        	bField.setAccessible(true);
        	Field cField = PathfinderGoalSelector.class.getDeclaredField("c");
        	cField.setAccessible(true);
        	bField.set(goalSelector, new UnsafeList<PathfinderGoalSelector>());
        	bField.set(targetSelector, new UnsafeList<PathfinderGoalSelector>());
        	cField.set(goalSelector, new UnsafeList<PathfinderGoalSelector>());
        	cField.set(targetSelector, new UnsafeList<PathfinderGoalSelector>());
        	} catch (Exception exc) {
        	exc.printStackTrace();
        	}  
        this.goalSelector.a(0, new PathfinderGoalFloat(this));
        this.goalSelector.a(4, new PathfinderGoalMeleeAttack(this, EntityZombie.class, 1.0D, true));
        this.goalSelector.a(5, new PathfinderGoalMoveTowardsRestriction(this, 1.0D));
        this.goalSelector.a(6, new PathfinderGoalMoveThroughVillage(this, 1.0D, false));
        this.goalSelector.a(7, new PathfinderGoalRandomStroll(this, 1.0D));
        this.goalSelector.a(8, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
        this.goalSelector.a(8, new PathfinderGoalRandomLookaround(this));
        this.targetSelector.a(1, new PathfinderGoalHurtByTarget(this, true));
        this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget(this, EntityZombie.class, false));
    }*/
	
	

    public Location getL() {
		return l;
	}



	public void setL(Location l) {
		this.l = l;
	}



	public static class PathfinderGoalWalktoTile extends PathfinderGoal {
        private EntityInsentient entity;
        private PathEntity path;
        private Location loc;
        public PathfinderGoalWalktoTile(EntityInsentient entitycreature, Location loc) {
            this.entity = entitycreature;
            this.loc = loc; 
        }
        @Override
        public boolean a() {
        	if(entity.getGoalTarget() == null) {
        		Location targetLocation = loc;
                boolean flag = this.entity.getNavigation().m();
                this.entity.getNavigation();
                this.path = this.entity.getNavigation().a(targetLocation.getX() + 1, targetLocation.getY(), targetLocation.getZ() + 1);
                this.entity.getNavigation();
                if (this.path != null) {
                    this.c();
                }
        	}
        	return this.path != null;
        }
        @Override
        public void c() {
        	if(entity.getGoalTarget() == null) {
        		this.entity.getNavigation().a(this.path, 1.5D);
        	}
        }
    }
	
}
