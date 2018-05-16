package me.wiljafor1.system.mobs;

import org.bukkit.entity.EntityType;

public interface A extends Mobs{
	public enum Mode
	{
	  GUARD, 
	  NORMAL;
	}
	public Mode mode();
	public Boolean player();
	public String SkinName();
	public Boolean city();
}

