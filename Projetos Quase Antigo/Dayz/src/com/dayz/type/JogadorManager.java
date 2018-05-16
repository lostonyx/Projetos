package com.dayz.type;

import java.util.List;

import org.bukkit.entity.Player;

import com.google.common.collect.Lists;

public class JogadorManager {

	public static List<Jogador> jogadores = Lists.newArrayList();

	public static Jogador createOne(Player player) {
		Jogador j = new Jogador(player);
		jogadores.add(j);
		return j;
	}
	
	public static List<Jogador> getJogadores(){
		return jogadores;
	}

	public static Jogador getJogador(Player player) {
		for (Jogador j : jogadores) {
			if (j.getPlayer().equals(player)) {
				return j;
			}
		}
		return null;
	}

	public static void remove(Jogador j) {
		jogadores.remove(j);
	}

}
