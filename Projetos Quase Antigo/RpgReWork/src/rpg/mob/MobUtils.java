package rpg.mob;

import java.lang.reflect.Field;

import org.bukkit.craftbukkit.v1_8_R3.util.UnsafeList;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;

import net.minecraft.server.v1_8_R3.*;
import rpg.customentity.pathfinder.PathfinderGoalSpiderMeleeAttack;
import rpg.customentity.pathfinder.PathfinderGoalSpiderNearestAttackableTarget;

public class MobUtils {

	public static void ativarAttackMob(EntityCreature entity, PathfinderGoalSelector goalSelector, PathfinderGoalSelector targetSelector) {
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
        goalSelector.a(1, new PathfinderGoalBreakDoor((EntityInsentient)entity));
        goalSelector.a(4, new PathfinderGoalMoveTowardsRestriction(entity, 1.0));
        goalSelector.a(5, new PathfinderGoalMoveThroughVillage(entity, 1.0, false));
        goalSelector.a(6, new PathfinderGoalRandomStroll(entity, 1.0));
        goalSelector.a(7, new PathfinderGoalLookAtPlayer((EntityInsentient)entity, EntityHuman.class, 8.0f));
        goalSelector.a(7, new PathfinderGoalRandomLookaround((EntityInsentient)entity));
        targetSelector.a(1, new PathfinderGoalHurtByTarget(entity, false, new Class[0]));
        goalSelector.a(2, new PathfinderGoalMeleeAttack(entity, EntityHuman.class, 1.0, false));
        goalSelector.a(3, new PathfinderGoalMeleeAttack(entity, EntityVillager.class, 1.0, true));
        targetSelector.a(2, new PathfinderGoalNearestAttackableTarget(entity, EntityVillager.class, false));
        targetSelector.a(2, new PathfinderGoalNearestAttackableTarget(entity, EntityHuman.class, false));
	}
	
	public static void ativarModoCorreBerg(EntityCreature entity, PathfinderGoalSelector goalSelector, PathfinderGoalSelector targetSelector) {
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
		goalSelector.a(0, new PathfinderGoalFloat(entity));
        goalSelector.a(1, new PathfinderGoalAvoidTarget(entity, EntityHuman.class, 8.0F, 0.6D, 0.6D));
	}
	
	public static void ativarModoDefenderMob(EntityCreature entity, PathfinderGoalSelector goalSelector, PathfinderGoalSelector targetSelector) {
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
        goalSelector.a(1, (PathfinderGoal)new PathfinderGoalBreakDoor(entity));
        goalSelector.a(4, (PathfinderGoal)new PathfinderGoalMoveTowardsRestriction(entity, 1.0));
        goalSelector.a(5, (PathfinderGoal)new PathfinderGoalMoveThroughVillage(entity, 1.0, false));
        goalSelector.a(6, (PathfinderGoal)new PathfinderGoalRandomStroll(entity, 1.0));
        goalSelector.a(7, (PathfinderGoal)new PathfinderGoalLookAtPlayer(entity, EntityHuman.class, 8.0f));
        goalSelector.a(7, (PathfinderGoal)new PathfinderGoalRandomLookaround(entity));
        targetSelector.a(1, (PathfinderGoal)new PathfinderGoalHurtByTarget(entity, false, new Class[0]));
        goalSelector.a(2, (PathfinderGoal)new PathfinderGoalMeleeAttack(entity, EntityZombie.class, 1.0, false));
        goalSelector.a(3, (PathfinderGoal)new PathfinderGoalMeleeAttack(entity, EntitySkeleton.class, 1.0, true));
        targetSelector.a(2, (PathfinderGoal)new PathfinderGoalNearestAttackableTarget(entity, EntitySkeleton.class, false));
        targetSelector.a(2, (PathfinderGoal)new PathfinderGoalNearestAttackableTarget(entity, EntityZombie.class, false));
        goalSelector.a(3, (PathfinderGoal)new PathfinderGoalMeleeAttack(entity, EntityCreeper.class, 1.0, true));
        targetSelector.a(2, (PathfinderGoal)new PathfinderGoalNearestAttackableTarget(entity, EntityCreeper.class, false));
        goalSelector.a(3, (PathfinderGoal)new PathfinderGoalMeleeAttack(entity, EntitySpider.class, 1.0, true));
        targetSelector.a(2, (PathfinderGoal)new PathfinderGoalNearestAttackableTarget(entity, EntitySpider.class, false));
    }
    
    public static void enableSpiderMob(EntityCreature entity, PathfinderGoalSelector goalSelector, PathfinderGoalSelector targetSelector) {
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
        goalSelector.a(1, (PathfinderGoal)new PathfinderGoalFloat(entity));
        goalSelector.a(3, (PathfinderGoal)new PathfinderGoalLeapAtTarget(entity, 0.4f));
        goalSelector.a(4, (PathfinderGoal)new PathfinderGoalSpiderMeleeAttack(entity, EntityHuman.class));
        goalSelector.a(4, (PathfinderGoal)new PathfinderGoalSpiderMeleeAttack(entity, EntityVillager.class));
        goalSelector.a(5, (PathfinderGoal)new PathfinderGoalRandomStroll(entity, 0.8));
        goalSelector.a(6, (PathfinderGoal)new PathfinderGoalLookAtPlayer(entity, EntityHuman.class, 8.0f));
        goalSelector.a(6, (PathfinderGoal)new PathfinderGoalRandomLookaround(entity));
        targetSelector.a(1, (PathfinderGoal)new PathfinderGoalHurtByTarget(entity, false, new Class[0]));
        targetSelector.a(2, (PathfinderGoal)new PathfinderGoalSpiderNearestAttackableTarget(entity, EntityHuman.class));
        targetSelector.a(3, (PathfinderGoal)new PathfinderGoalSpiderNearestAttackableTarget(entity, EntityVillager.class));
    }
    
    public static void ativarSlimeMob(EntityInsentient entity, PathfinderGoalSelector goalSelector, PathfinderGoalSelector targetSelector) {
        try {
            Field bField = PathfinderGoalSelector.class.getDeclaredField("b");
            bField.setAccessible(true);
            Field cField = PathfinderGoalSelector.class.getDeclaredField("c");
            cField.setAccessible(true);
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
        targetSelector.a(5, new PathfinderGoalNearestAttackableTargetInsentient(entity, EntityVillager.class));
    }
    
    public static void ativarShootingMob(IRangedEntity entity, PathfinderGoalSelector goalSelector, PathfinderGoalSelector targetSelector) {
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
        goalSelector.a(4, new PathfinderGoalArrowAttack(entity, 1.0, 20, 60, 15.0f));
        goalSelector.a(4, new PathfinderGoalMeleeAttack((EntityCreature)entity, EntityHuman.class, 1.2, false));
        goalSelector.a(4, new PathfinderGoalMeleeAttack((EntityCreature)entity, EntityVillager.class, 1.2, false));
        goalSelector.a(1, new PathfinderGoalFloat((EntityInsentient)entity));
        goalSelector.a(4, new PathfinderGoalRandomStroll((EntityCreature)entity, 1.0));
        goalSelector.a(6, new PathfinderGoalLookAtPlayer((EntityInsentient)entity, EntityHuman.class, 8.0f));
        goalSelector.a(6, new PathfinderGoalRandomLookaround((EntityInsentient)entity));
        targetSelector.a(1, new PathfinderGoalHurtByTarget((EntityCreature)entity, false, new Class[0]));
        targetSelector.a(2, new PathfinderGoalNearestAttackableTarget((EntityCreature)entity, EntityHuman.class, true));
        targetSelector.a(3, new PathfinderGoalNearestAttackableTarget((EntityCreature)entity, EntityVillager.class, true));
    }
	
    public static void attackPassive(EntityCreature damager, Entity damaged, Float damage) {
        damaged.damageEntity(DamageSource.mobAttack(damager), damage);
     }

}
