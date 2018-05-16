package rpg.system.spell.archer;

import java.util.Arrays;
import java.util.List;

import org.bukkit.entity.Player;

import de.slikey.effectlib.util.ParticleEffect;
import net.md_5.bungee.api.ChatColor;
import rpg.system.Metodos;
import rpg.system.models.Classe;
import rpg.system.models.ClasseManager;
import rpg.system.models.Jogador;
import rpg.system.spell.Spell;
import rpg.system.spell.SpellType;
import rpg.utils.ArrowUtils;

public class ArrowCannon implements Spell {

	@Override
	public void cast(Jogador j) {
		Player p = j.getPlayer();
		p.sendMessage("skill 2");
		if (j.getCurrentAccount().getMana() > getManaCost()) {
			Metodos.usarmana(getManaCost(), p);
            ArrowUtils.spawnArrowEffected(j, ParticleEffect.EXPLOSION_NORMAL, true);
        }
        else {
            p.sendMessage(ChatColor.RED + "Voce nao possui mana suficiente para usar esta habilidade.");
        }    
	}
	@Override
	public String getName() {
		return "Flecha Dinamica";
	}

	@Override
	public List<Integer> getCombo() {
		return Arrays.asList(0, 1, 1);
	}

	@Override
	public int getLevelMin() {
		return 0;
	}

	@Override
	public int getManaCost() {
		return 2;
	}

	@Override
	public Classe getClasse() {
		return ClasseManager.getClasse("Archer");
	}

	@Override
	public SpellType getType() {
		return SpellType.ACTIVE;
	}

}
