package rpg.mob.customentity.scared;

import java.lang.reflect.Field;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.util.UnsafeList;

import net.minecraft.server.v1_8_R3.EntitySkeleton;
import net.minecraft.server.v1_8_R3.EntityWolf;
import net.minecraft.server.v1_8_R3.EntityZombie;
import net.minecraft.server.v1_8_R3.PathfinderGoalSelector;
import net.minecraft.server.v1_8_R3.World;
import rpg.mob.MobBase;
import rpg.mob.MobUtils;

public class EntityWolfScared extends EntityWolf implements MobBase{

	public EntityWolfScared(World world) {
		super(world);
		MobUtils.ativarModoCorreBerg(this, goalSelector, targetSelector); 
	}


}
