package rpg.data.mob;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import rpg.mob.MobManager;
import rpg.mob.MobTrans;
import rpg.mob.Mobs.Tipo;
import rpg.mob.type.B;
import rpg.mob.type.D;

public class EsqueletoModo1 implements D {

	@Override
	public Tipo tipo() {
		return Tipo.D;
	}

	@Override
	public String name() {
		return "Esqueleto1";
	}

	@Override
	public String nameDisplay() {
		return "Esqueleto Assustado";
	}

	@Override
	public EntityType type() {
		return EntityType.SKELETON;
	}

	@Override
	public float life() {
		return 45;
	}

	@Override
	public Double damage() {
		return 5.0;
	}

	@Override
	public int Level() {
		return 7;
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
		return false;
	}

	@Override
	public CEntityCustom classe() {
		return CEntityCustom.Assustado;
	}

}
