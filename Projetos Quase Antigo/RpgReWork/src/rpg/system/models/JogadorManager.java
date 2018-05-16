package rpg.system.models;

import java.util.List;

import org.bukkit.entity.Player;


import com.google.common.collect.Lists;

public class JogadorManager {

	public static List<Jogador> wynns = Lists.newArrayList();
	public static List<Player> regenhealth = Lists.newArrayList();
	public static List<Player> regenmana = Lists.newArrayList();

	public static Jogador createOne(Player player) {
		Jogador w = new Jogador(player);
		wynns.add(w);
		return w;
	}
	
	public static List<Jogador> getJogadores(){
		return wynns;
	}

	public static Jogador getWynnPlayer(Player player) {
		for (Jogador w : wynns) {
			if (w.getPlayer().equals(player)) {
				return w;
			}
		}
		return null;
	}

	public static void remove(Jogador w) {
		wynns.remove(w);
	}
}
