package rpg.data.mob;

import java.util.ArrayList;

import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import rpg.mob.Mobs;
import rpg.mob.type.D;

public class ZombieStarter implements D {

	@Override
	public String name() {
		return "ZombieStarter";
	}

	@Override
	public float life() {
		return 25;
	}

	@Override
	public Double damage() {
		return 0.5;
	}

	@Override
	public String nameDisplay() {
		return "Zombi Crackudo";
	}

	@Override
	public EntityType type() {
		return EntityType.ZOMBIE;
	}


	@Override
	public int Level() {
		return 5;
	}

	@Override
	public Tipo tipo() {
		return Tipo.D;
	}

	@Override
	public int Amount() {
		return 1;
	}

	@Override
	public ArrayList<ItemStack> drop() {
		return null;
	}

	@Override
	public boolean respawn() {
		return true;
	}

	@Override
	public CEntityCustom classe() {
		return CEntityCustom.Normal;
	}
	

}
