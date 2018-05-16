package me.wiljafor1.system.mobs.ragni;

import org.bukkit.entity.EntityType;

import me.wiljafor1.system.mobs.B;
import me.wiljafor1.system.mobs.Mobs.Tipo;

public class CitizenNormal implements B{
	@Override
	public Tipo tipo() {
		return Tipo.B;
	}

	@Override
	public String name() {
		return "CitizenN";
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
		return 20000;
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
		return false;
	}

	@Override
	public String SkinName() {
		return "lul";
	}

	@Override
	public Boolean city() {
		return true;
	}

	@Override
	public int Amount() {
		return 5;
	}
}
