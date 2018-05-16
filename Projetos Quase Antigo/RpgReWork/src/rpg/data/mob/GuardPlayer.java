package rpg.data.mob;

import java.util.ArrayList;

import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import rpg.mob.MobDisPlayer;
import rpg.mob.type.A;

public class GuardPlayer implements A,MobDisPlayer{

	@Override
	public Tipo tipo() {
		return Tipo.A;
	}

	@Override
	public String name() {
		return "DisGuard";
	}

	@Override
	public String nameDisplay() {
		return "Ragni NCP Guard";
	}

	@Override
	public EntityType type() {
		return EntityType.IRON_GOLEM;
	}

	@Override
	public float life() {
		return 400000;
	}

	@Override
	public Double damage() {
		return null;
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
	public String SkinName() {
		return null;
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
