package me.wiljafor1.system.mobs.ragni;

import org.bukkit.entity.EntityType;

import me.wiljafor1.system.mobs.A;

public class GuardPlayer implements A{

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
	public Mode mode() {
		return Mode.GUARD;
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
	public Boolean city() {
		return true;
	}

	@Override
	public int Amount() {
		// TODO Auto-generated method stub
		return 1;
	}

}
