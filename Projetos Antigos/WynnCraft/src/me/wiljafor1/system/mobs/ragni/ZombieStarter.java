package me.wiljafor1.system.mobs.ragni;

import org.bukkit.entity.EntityType;

import me.wiljafor1.system.mobs.D;
import me.wiljafor1.system.mobs.Mobs;

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
		// TODO Auto-generated method stub
		return 5;
	}

}
