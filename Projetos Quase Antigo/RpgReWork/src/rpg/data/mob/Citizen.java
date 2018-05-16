package rpg.data.mob;

import java.util.ArrayList;

import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import rpg.mob.MobDisPlayer;
import rpg.mob.type.B;

public class Citizen implements B,MobDisPlayer{

	@Override
	public Tipo tipo() {
		return Tipo.B;
	}

	@Override
	public String name() {
		return "Citizen";
	}

	@Override
	public String nameDisplay() {
		return "Ragni Citizen";
	}

	@Override
	public EntityType type() {
		return EntityType.VILLAGER;
	}

	@Override
	public float life() {
		return 15;
	}

	@Override
	public Double damage() {
		return 0.0;
	}

	@Override
	public int Level() {
		return 10;
	}

	@Override
	public Boolean player() {
		return true;
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

	@Override
	public String SkinName() {
		return null;
	}

}
