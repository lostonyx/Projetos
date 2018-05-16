package me.wiljafor1.system.mobs.ragni;

import org.bukkit.entity.EntityType;

import me.wiljafor1.system.mobs.B;

public class Citizen implements B{

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
		return 5;
	}

}
