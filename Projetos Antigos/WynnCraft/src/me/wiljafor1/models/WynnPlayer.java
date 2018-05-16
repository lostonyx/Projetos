package me.wiljafor1.models;

import java.util.HashMap;
import java.util.List;

import org.bson.Document;
import org.bukkit.entity.Player;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mongodb.client.MongoCollection;

import me.wiljafor1.interfaces.Region;
import me.wiljafor1.system.Database;
import me.wiljafor1.utils.SasukeScore;

public class WynnPlayer {
	private static MongoCollection<Document> jogadores = Database.getDataBase("wynn").getCollection("wynnplayer");
	
	private Player player;
	private Account currentAccount;
	private List<Account> contas;
	private List<String> friends;
	private String id;
	
	
	public static HashMap<Player, SasukeScore> scores = Maps.newHashMap();

	public WynnPlayer(Player player) {
	
		this.player = player;
		this.id = player.getUniqueId().toString();
		this.contas = Lists.newArrayList();
		this.currentAccount = null;
		Document jogador = jogadores.find(new Document("id", this.id)).first();
		if (jogador != null) {
			this.id = jogador.getString("id");

			List<Document> doc = (List<Document>) jogador.get("accounts");
			List<Account> accounts = Lists.newArrayList();
			for (Document d : doc) {
				int id = d.getInteger("id");
				int level = d.getInteger("level");
				int xp = d.getInteger("xp");
				int health = d.getInteger("health");
				int mana = d.getInteger("mana");
				int soul = d.getInteger("soul");
				String classp = d.getString("class");
				String inv = d.getString("inv");	
				String loc = d.getString("loc");
				int lc = d.getInteger("lastcity");
				List<Integer> rf = (List<Integer>) d.get("rf");
				Account a = new Account(id, level, xp, health, mana, soul, loc, classp, inv, lc, rf);
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
					d.put("level", a.getLevel());
					d.put("xp", a.getXp());
					d.put("health", a.getHealth());
					d.put("mana", a.getMana());
					d.put("soul", a.getSoul());
					d.put("loc", a.getLastLoc());
					d.put("class", a.getClassp());
					d.put("inv", a.getInvString());
					d.put("lastcity", a.getLastcity());
					d.put("rf", a.getRegionfound());
					doc.add(d);
				}
			}
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
			d.put("level", a.getLevel());
			d.put("xp", a.getXp());
			d.put("health", a.getHealth());
			d.put("mana", a.getMana());
			d.put("soul", a.getSoul());
			d.put("loc", a.getLastLoc());
			d.put("class", a.getClassp());
			d.put("inv", a.getInvString());
			d.put("lastcity", a.getLastcity());
			d.put("rf", a.getRegionfound());
			docs.add(d);
		}
		jogador.put("accounts", docs);
		
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
