package rpg.system.spell;

import java.util.*;

import com.google.common.collect.Lists;


public class SpellManager
{
    public static List<Spell> spells = Lists.newArrayList();

    public static Spell getSpell(String name) {
        for (Spell s : spells) {
            if (s.getName().equalsIgnoreCase(name)) {
                return s;
            }
        }
        return null;
    }
    
    public static void add(Spell s) {
    	spells.add(s);
	}
    
	public static void remove(Spell s) {
		spells.remove(s);
	}
    
}
