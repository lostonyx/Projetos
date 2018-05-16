package rpg.mob;

import java.util.ArrayList;

import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import me.libraryaddict.disguise.disguisetypes.DisguiseType;


public interface Mobs {
	public enum Tipo
	{
	  A, //Mobs com nick azul claro o protegerão de mobs hostis nas proximidades.
	  B, //Mobs com nick verdes são passivas, elas não irão atacá-lo.
	  C, //Mobs com nick amarelas são neutras, elas só atacarão você se as machucar primeiro.
	  D, //Mobs com nick vermelhos são hostis, tentarão matá-lo.
	  E; //Mobs com nick roxo sao npc de venda
	}
	public enum CEntityCustom{
		  Assustado, 
		  Defesa,
		  Normal,
		  Ataque;
		}
	public Tipo tipo();
	public CEntityCustom classe();
	public String name();
	public String nameDisplay();
	public EntityType type();
	public float life();
	public Double damage();
	public int Level();
	public int Amount();//Quantidade quando for spawnar
	public ArrayList<ItemStack> drop();
	public boolean respawn();
}
