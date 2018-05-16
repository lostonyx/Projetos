package me.wiljafor1.system.mobs;

import org.bukkit.entity.EntityType;


public interface Mobs {
	public enum Tipo
	{
	  A, //Mobs com nick azul claro o protegerão de mobs hostis nas proximidades.
	  B, //Mobs com nick verdes são passivas, elas não irão atacá-lo.
	  C, //Mobs com nick amarelas são neutras, elas só atacarão você se as machucar primeiro.
	  D; //Mobs com nick vermelhos são hostis, tentarão matá-lo.
	}
	public Tipo tipo();
	public String name();
	public String nameDisplay();
	public EntityType type();
	public float life();
	public Double damage();
	public int Level();
	public int Amount();//Quantidade quando for spawnar
}
