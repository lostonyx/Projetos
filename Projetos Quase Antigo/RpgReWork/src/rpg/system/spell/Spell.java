package rpg.system.spell;

import java.util.List;
import rpg.system.models.Classe;
import rpg.system.models.Jogador;

public interface Spell {
	//https://wynncraft.gamepedia.com/Archer
	//
	
	void cast(Jogador j);
	String getName();
	/*
	 * 0,0,0 - 9
	 * 1,1,1 - 8
	 * 1,0,0 - 7
	 * 0,1,0 - 6
	 * 0,0,1 - 5
	 * 1,1,0 - 4
	 * 1,0,1 - 3
	 * 0,1,1 - 2
	 * 1,1,0 - 1
	 */
	List<Integer> getCombo();
    
    int getLevelMin();
    
    int getManaCost();
    
    Classe getClasse();
    
    SpellType getType();
    
}
