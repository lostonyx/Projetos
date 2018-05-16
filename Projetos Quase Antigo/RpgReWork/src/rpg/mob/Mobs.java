package rpg.mob;

import java.util.ArrayList;

import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import me.libraryaddict.disguise.disguisetypes.DisguiseType;


public interface Mobs {
	public enum Tipo
	{
	  A, //Mobs com nick azul claro o proteger�o de mobs hostis nas proximidades.
	  B, //Mobs com nick verdes s�o passivas, elas n�o ir�o atac�-lo.
	  C, //Mobs com nick amarelas s�o neutras, elas s� atacar�o voc� se as machucar primeiro.
	  D, //Mobs com nick vermelhos s�o hostis, tentar�o mat�-lo.
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
