package me.wiljafor1.system.mobs;

import org.bukkit.entity.EntityType;


public interface Mobs {
	public enum Tipo
	{
	  A, //Mobs com nick azul claro o proteger�o de mobs hostis nas proximidades.
	  B, //Mobs com nick verdes s�o passivas, elas n�o ir�o atac�-lo.
	  C, //Mobs com nick amarelas s�o neutras, elas s� atacar�o voc� se as machucar primeiro.
	  D; //Mobs com nick vermelhos s�o hostis, tentar�o mat�-lo.
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
