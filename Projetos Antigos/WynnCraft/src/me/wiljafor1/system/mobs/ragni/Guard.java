package me.wiljafor1.system.mobs.ragni;

import org.bukkit.entity.EntityType;

import me.wiljafor1.system.mobs.A;
import me.wiljafor1.system.mobs.Mobs;

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
	public Boolean player() {
		return false;
	}

	@Override
	public String SkinName() {
		return "lul";
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
	public Boolean city() {
		return true;
	}

	@Override
	public Mode mode() {
		return Mode.GUARD;
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
		// TODO Auto-generated method stub
		return 1;
	}

}
