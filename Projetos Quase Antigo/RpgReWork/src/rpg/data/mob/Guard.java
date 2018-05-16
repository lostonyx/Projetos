package rpg.data.mob;

import java.util.ArrayList;

import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import rpg.mob.Mobs;
import rpg.mob.type.A;

public class Guard implements A {

	@Override
	public String name() {
		return "Guard";
	}

	@Override
	public float life() {
		return 40000;
	}

	@Override
	public Double damage() {
		return 10.0;
	}


	@Override
	public String nameDisplay() {
		return "Ragni Guard";
	}

	@Override
	public EntityType type() {
		return EntityType.IRON_GOLEM;
	}


	@Override
	public int Level() {
		return 10;
	}

	@Override
	public Tipo tipo() {
		return Tipo.A;
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
		return CEntityCustom.Defesa;
	}

}
