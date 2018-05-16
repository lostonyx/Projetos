package com.dayz.ranking;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.dayz.type.Account;
import com.dayz.type.Jogador;
import com.dayz.type.JogadorManager;
import com.google.common.collect.Maps;

public class RankMoney {
	
	public static HashMap<String, Integer> contas = Maps.newHashMap();
	
	
	public static void atualizar() {
		for(Player p : Bukkit.getOnlinePlayers()) {
			Jogador j = JogadorManager.getJogador(p);
			Account a = j.getCurrentAccount();
			int money = (int) a.getMoney().doubleValue();
			contas.put(p.getName(), money);
		}
	}
	
	
	
	public static void sendTopMoney(Player p) {
		Stream<Entry<String, Integer>> entrada = contas.entrySet().stream().sorted((x , y) -> y.getValue().compareTo(x.getValue()));
		List<Entry<String , Integer>> lista = entrada.collect(Collectors.toList());
		int i = 1;
		for(Entry<String , Integer> players : lista) {
			
			String player = players.getKey();
			int money = players.getValue();
			
			p.sendMessage("§a" + i + "°   " + player  + " - " + money);
			
			i ++;
			if(i > 10) {
				break;
			}
		}
		
	}
	

}
