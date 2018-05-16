package me.wiljafor1.system.mobs.customentity.a;

import java.lang.reflect.Field;

import org.bukkit.Location;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

import me.wiljafor1.utils.PathfinderGoalWalktoTile;
import net.minecraft.server.v1_8_R3.EntityGolem;
import net.minecraft.server.v1_8_R3.EntityZombie;
import net.minecraft.server.v1_8_R3.PathfinderGoalFloat;
import net.minecraft.server.v1_8_R3.PathfinderGoalHurtByTarget;
import net.minecraft.server.v1_8_R3.PathfinderGoalLookAtPlayer;
import net.minecraft.server.v1_8_R3.PathfinderGoalMeleeAttack;
import net.minecraft.server.v1_8_R3.PathfinderGoalMoveThroughVillage;
import net.minecraft.server.v1_8_R3.PathfinderGoalNearestAttackableTarget;
import net.minecraft.server.v1_8_R3.PathfinderGoalSelector;
import net.minecraft.server.v1_8_R3.World;

import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.util.UnsafeList;

public class Guard extends EntityGolem{

	private Location lc;
	
	public Guard(World world) {
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
		if(lc != null) {
			this.goalSelector.a(1, new PathfinderGoalWalktoTile(this, lc));	
		}
        this.goalSelector.a(4, new PathfinderGoalMeleeAttack(this, EntityZombie.class, 1.0D, true));
        this.goalSelector.a(6, new PathfinderGoalMoveThroughVillage(this, 1.0D, false));
        this.goalSelector.a(8, new PathfinderGoalLookAtPlayer(this, EntityZombie.class, 8.0F));
        this.targetSelector.a(1, new PathfinderGoalHurtByTarget(this, false));
        this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget(this, EntityZombie.class, false));
	}

	public Guard setLoc(Location lc) {
		this.lc = lc;
		return null;
	}
	
	public static Guard spawn(Location location, Location lc){
		try {
			if (!location.getChunk().isLoaded()) location.getChunk().load();
			Guard guard = new Guard(((CraftWorld) location.getWorld()).getHandle());
			guard.setLoc(lc);
			guard.setPosition(location.getX(), location.getY(), location.getZ());
			(((CraftWorld) location.getWorld()).getHandle()).addEntity(guard, SpawnReason.CUSTOM);
			return guard;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
