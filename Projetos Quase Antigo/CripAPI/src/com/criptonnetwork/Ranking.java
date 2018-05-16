package com.criptonnetwork;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;

import com.criptonnetwork.database.Database;
import com.criptonnetwork.util.HCStrings;
import com.criptonnetwork.util.SasukeItem;
import com.criptonnetwork.util.SpigotAPI;
import com.google.common.collect.Maps;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

public class Ranking {

	public static HashMap<String, Integer> contas = Maps.newHashMap();
	private static List<Entry<String, Integer>> valores;
	private static List<ArmorStand> holograms;

	public static void atualizarDados() {
		if (!contas.isEmpty()) {
			contas.clear();
		}
		MongoCollection<Document> coll = Database.getDataBase("api").getCollection("gamers");
		FindIterable<Document> lista = coll.find();

		for (Document d : lista) {
			int coins = d.getInteger("coins");
			String name = d.getString("name");
			contas.put(name, coins);
		}

	}
	
	public void giveJoinItems(Player p) {
		p.getInventory().addItem(SasukeItem.createInventoryItem(Material.APPLE, "Steve Jobs", 0, 1, new String[] {}, 
			() -> {
			if(p.getLevel() >= 20) {
				p.sendMessage("§cInfo> funcionou.");
			}
		}));
	}
	

	public static void broadCast() {
		atualizarDados();
		Stream<Entry<String, Integer>> entrada = contas.entrySet().stream()
				.sorted((x, y) -> y.getValue().compareTo(x.getValue()));
		valores = entrada.collect(Collectors.toList());
		int i = 0;
		Bukkit.broadcastMessage("§c");
		Bukkit.broadcastMessage("§c");
		Bukkit.broadcastMessage("§c");
		Bukkit.broadcastMessage("§c");
		Bukkit.broadcastMessage("§c");
		Bukkit.broadcastMessage("§c");
		Bukkit.broadcastMessage("§6Top 10 - CriptonCoins");
		Bukkit.broadcastMessage("§c");
		
		for (Entry<String, Integer> sete : valores) {
			String name = sete.getKey();
			int value = sete.getValue();
			i++;
			if (i > 10)
				break;
			Bukkit.broadcastMessage(i + "°     §a" + name + "     §7" + value + " Criptoncoins");
		}
		
		Bukkit.broadcastMessage("§c");
		Bukkit.broadcastMessage("§fCompre criptoncoins na loja: §cwww.criptonnetwork.com.br");

	}

}
