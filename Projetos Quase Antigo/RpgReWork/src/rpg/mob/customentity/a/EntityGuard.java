package rpg.mob.customentity.a;

import java.lang.reflect.Field;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.util.UnsafeList;

import net.minecraft.server.v1_8_R3.*;
import rpg.customentity.pathfinder.PathfinderGoalWalktoTile;
import rpg.mob.MobManager;

public class EntityGuard extends EntityIronGolem{
	private static Location l;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
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
        	}
        catch (Exception exc) {
        	exc.printStackTrace();
        }  
        this.goalSelector.a(0, new PathfinderGoalFloat(this));
        if(l != null) {
        	this.goalSelector.a(1, new PathfinderGoalWalktoTile(this, l));		
        }
        this.goalSelector.a(4, new PathfinderGoalMeleeAttack(this, EntityZombie.class, 1.0D, true));
        this.goalSelector.a(6, new PathfinderGoalMoveThroughVillage(this, 1.0D, false));
        this.goalSelector.a(8, new PathfinderGoalLookAtPlayer(this, EntityZombie.class, 8.0F));
        this.targetSelector.a(1, new PathfinderGoalHurtByTarget(this, false));
        this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget(this, EntityZombie.class, false));
    }

	public static EntityGuard start(Location loc) {
		l = loc;
		CraftWorld craftWorld = (CraftWorld)loc.getWorld();
        World world = craftWorld.getHandle();
        EntityGuard entity = new EntityGuard(world);
		return entity;
	}
	
}
