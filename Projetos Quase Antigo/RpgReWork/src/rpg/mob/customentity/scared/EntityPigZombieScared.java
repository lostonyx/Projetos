package rpg.mob.customentity.scared;

import java.lang.reflect.Field;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.util.UnsafeList;

import net.minecraft.server.v1_8_R3.EntityPigZombie;
import net.minecraft.server.v1_8_R3.EntitySkeleton;
import net.minecraft.server.v1_8_R3.PathfinderGoalSelector;
import net.minecraft.server.v1_8_R3.World;
import rpg.mob.MobBase;
import rpg.mob.MobUtils;

public class EntityPigZombieScared extends EntityPigZombie implements MobBase{

	public EntityPigZombieScared(World world) {
		super(world);
		MobUtils.ativarModoCorreBerg(this, goalSelector, targetSelector); 
	}


}
