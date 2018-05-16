package com.dayz.type;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import com.criptonnetwork.Main;
import com.criptonnetwork.database.Database;
import com.criptonnetwork.util.SasukeScore;
import com.dayz.utils.SerializeApi;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mongodb.client.MongoCollection;

import net.md_5.bungee.api.ChatColor;

public class Jogador {
	private static MongoCollection<Document> jogadores = Database.getDataBase("dayz").getCollection("jogadores");
	
	private Player player;
	private Account currentAccount;
	private List<Account> contas;
	private List<String> friends;
	private String id;
	
	
	public static HashMap<Player, SasukeScore> scores = Maps.newHashMap();

	public Jogador(Player player) {
	
		this.player = player;
		this.id = player.getUniqueId().toString();
		this.contas = Lists.newArrayList();
		this.currentAccount = null;
		Document jogador = jogadores.find(new Document("id", this.id)).first();
		if (jogador != null) {
			this.id = jogador.getString("id");

			List<Document> doc = (List<Document>) jogador.get("contas");
			List<Account> accounts = Lists.newArrayList();
			for (Document d : doc) {
				int id = d.getInteger("id");
				String classe = d.getString("classe");
				String inv = d.getString("inv");
				int level = d.getInteger("level");
				int xp = d.getInteger("xp");
				int kills = d.getInteger("kills");
				int deaths = d.getInteger("deaths");
				int totalZombies = d.getInteger("totalZombies");
				Double vida = d.getDouble("vida");
				String loc = d.getString("ultimolocal");
				Double radiacao = d.getDouble("radiacao");
				int karma = d.getInteger("karma");
				double money = d.getDouble("money");
				Account a = new Account(id, classe, inv, level, xp, kills, deaths, totalZombies, vida, loc , radiacao, karma, money);
				accounts.add(a);
			}
			this.contas  = accounts;
		} else {
			jogador = new Document();
			jogador.put("id", this.id);
			List<Document> doc = Lists.newArrayList();
			if(!contas.isEmpty()) {
				for (Account a : contas) {
					Document d = new Document();
					d.put("id", a.getId());
					d.put("inv", a.getInvString());
					d.put("kills", a.getKills());
					d.put("deaths", a.getDeaths());
					d.put("totalZombies", a.getTotalzombies());
					d.put("level", a.getLevel());
					d.put("xp", a.getXp());
					d.put("classe", a.getClasse());
					d.put("vida", a.getVida());
					d.put("ultimolocal", a.getUltimolocal());
					d.put("radiacao", a.getCurrentRadiation());
					d.put("karma", a.getKarma());
					d.put("money", a.getMoney());
					doc.add(d);
				}
			}
			//jogador.put("contas", doc);
			jogadores.insertOne(jogador);
		}
	}

	public void update() {// Tentar Salvar o Account
		Document jogador = jogadores.find(new Document("id", this.id)).first();
		jogador.put("id", this.id);
		List<Document> docs = Lists.newArrayList();
		for (Account a : contas) {
			Document d = new Document();
			d.put("id", a.getId());
			d.put("inv", a.getInvString());
			d.put("kills", a.getKills());
			d.put("deaths", a.getDeaths());
			d.put("totalZombies", a.getTotalzombies());
			d.put("level", a.getLevel());
			d.put("xp", a.getXp());
			d.put("classe", a.getClasse());
			d.put("vida", a.getVida());
			d.put("ultimolocal", a.getUltimolocal());
			d.put("radiacao", a.getCurrentRadiation());
			d.put("karma", a.getKarma());
			d.put("money", a.getMoney());
			docs.add(d);
		}
		jogador.put("contas", docs);
		
		jogadores.updateOne(new Document("id", this.id.toString()), new Document("$set", jogador));

	}

	public Player getPlayer() {
		return player;
	}

	public Account getCurrentAccount() {
		return currentAccount;
	}

	public void setCurrentAccount(Account a) {
		this.currentAccount = a;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public List<Account> getContas() {
		return contas;
	}

	public void setContas(List<Account> contas) {
		this.contas = contas;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
