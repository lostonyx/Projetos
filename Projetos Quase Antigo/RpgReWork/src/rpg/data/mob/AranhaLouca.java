package rpg.data.mob;

import java.util.ArrayList;

import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import net.minecraft.server.v1_8_R3.EntitySpider;
import net.minecraft.server.v1_8_R3.World;
import rpg.mob.type.D;

public class AranhaLouca implements D{

	@Override
	public Tipo tipo() {
		return Tipo.D;
	}

	@Override
	public String name() {
		return "AranhaLouca";
	}

	@Override
	public String nameDisplay() {
		return "Aranha Maconheira";
	}

	@Override
	public EntityType type() {
		return EntityType.ZOMBIE;
	}

	@Override
	public float life() {
		return 69;
	}

	@Override
	public Double damage() {
		return 2.5;
	}

	@Override
	public int Level() {
		return 15;
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
