package com.criptonnetwork.type;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.bson.Document;
import org.bukkit.entity.Player;

import com.criptonnetwork.cosmeticos.CosmeticType;
import com.criptonnetwork.cosmeticos.Cosmetico;
import com.criptonnetwork.cosmeticos.CosmeticoManager;
import com.criptonnetwork.database.Database;
import com.google.common.collect.Lists;
import com.mongodb.client.MongoCollection;

public class Gamer {
	/**
	 * Aqui carrega todos o dados de players
	 * 
	 *
	 *
	 */
	public static MongoCollection<Document> gamers = Database.getDataBase("api").getCollection("gamers");
	private boolean isLogged;
	private String senha, id, name;
	private long lastTimePlayed;
	private String group;
	private List<String> permissions;
	private List<String> friends;
	private List<String> cosmeticos;
	private List<Pacote> pacotes;
	private String lastAdress;
	private String lastServer;
	private boolean isOnline;
	private int criptonCoins;
	

	private Player player;

	public Gamer(Player player) {
		this.senha = null;
		this.name = player.getName();
		this.id = player.getUniqueId().toString();
		this.group = "Membro";
		this.cosmeticos = Arrays.asList("Efeito de Chamas", "Efeito de Sangue");
		this.permissions = Lists.newArrayList();
		this.friends = Arrays.asList("cardies", "Wiljafor1");
		this.lastTimePlayed = System.currentTimeMillis();
		this.player = player;
		String[] ip = player.getAddress().toString().replace("/", "").split(":");
		this.lastAdress = ip[0];
		this.isOnline = true;
		this.criptonCoins = 0;
		this.lastServer = "LOBBY-1";

		Document gamer = gamers.find(new Document("id", this.id)).first();

		if (gamer != null) {
			this.senha = gamer.getString("senha");
			this.id = gamer.getString("id");
			this.name = gamer.getString("name");
			this.group = gamer.getString("group");
			this.friends = (List) gamer.get("friends");
			this.permissions = (List) gamer.get("permissions");
			this.cosmeticos = (List) gamer.get("cosmeticos");
			this.lastAdress = gamer.getString("lastAdress");
			this.lastTimePlayed = gamer.getLong("lastTimePlayed");
			this.lastServer = gamer.getString("lastServer");
			this.isOnline = gamer.getBoolean("isOnline");
			this.criptonCoins = gamer.getInteger("coins");
		} else {
			gamer = new Document();
			gamer.put("cosmeticos", this.cosmeticos);
			gamer.put("id", this.id);
			gamer.put("name", this.name);
			gamer.put("senha", this.senha);
			gamer.put("group", this.group);
			gamer.put("friends", this.friends);
			gamer.put("permissions", this.permissions);
			gamer.put("lastTimePlayed", this.lastTimePlayed);
			gamer.put("lastAdress", this.lastAdress);
			gamer.put("isOnline", this.isOnline);
			gamer.put("lastServer", this.lastServer);
			gamer.put("coins", this.criptonCoins);
			gamers.insertOne(gamer);
		}
	}

	public Gamer(String ip) {
		this.senha = null;
		this.id = null;
		this.group = "Membro";
		this.permissions = Lists.newArrayList();
		this.friends = Arrays.asList("Notch", "Wiljafor1");
		this.lastTimePlayed = System.currentTimeMillis();
		this.player = null;
		this.lastAdress = ip;
		this.isOnline = true;
		this.lastServer = "LOBBY-1";
		Document gamer = gamers.find(new Document("ip", this.lastAdress)).first();
		if (gamer != null) {
			this.senha = gamer.getString("senha");
			this.id = gamer.getString("id");
			this.name = gamer.getString("name");
			this.group = gamer.getString("group");
			this.friends = (List) gamer.get("friends");
			this.permissions = (List) gamer.get("permissions");
			this.cosmeticos = (List) gamer.get("cosmeticos");
			this.lastAdress = gamer.getString("lastAdress");
			this.lastTimePlayed = gamer.getLong("lastTimePlayed");
			this.lastServer = gamer.getString("lastServer");
			this.isOnline = gamer.getBoolean("isOnline");
			this.criptonCoins = gamer.getInteger("coins");

		}
	}

	/**
	 * Aqui atualiza os dados(Me corrija se eu estiver errado)
	 * 
	 * 
	 *
	 */
	public void update() {
		Document gamer = gamers.find(new Document("id", this.id)).first();
		gamer.put("cosmeticos", this.cosmeticos);
		gamer.put("id", this.id);
		gamer.put("name", this.name);
		gamer.put("senha", this.senha);
		gamer.put("group", this.group);
		gamer.put("friends", this.friends);
		gamer.put("permissions", this.permissions);
		gamer.put("lastTimePlayed", this.lastTimePlayed);
		gamer.put("lastAdress", this.lastAdress);
		gamer.put("isOnline", this.isOnline);
		gamer.put("lastServer", this.lastServer);
		gamer.put("coins", this.criptonCoins);
		gamers.updateOne(new Document("id", this.id.toString()), new Document("$set", gamer));

	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getLastTimePlayed() {
		return lastTimePlayed;
	}

	public void setLastTimePlayed(long lastTimePlayed) {
		this.lastTimePlayed = lastTimePlayed;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public List<String> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<String> permissions) {
		this.permissions = permissions;
	}

	public String getLastAdress() {
		return lastAdress;
	}

	public void setLastAdress(String lastAdress) {
		this.lastAdress = lastAdress;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public void tString() {
		this.player.sendMessage("Senha: " + senha);
		this.player.sendMessage("Id: " + id);
		this.player.sendMessage("Group: " + group);
		this.player.sendMessage("Permissions: " + permissions);
		this.player.sendMessage("Ip: " + lastAdress);
		this.player.sendMessage("lastTimePlayed: " + lastTimePlayed);
	}

	public List<String> getFriends() {
		return friends;
	}

	public void setFriends(List<String> friends) {
		this.friends = friends;
	}

	public String getLastServer() {
		return lastServer;
	}

	public void setLastServer(String lastServer) {
		this.lastServer = lastServer;
	}

	public boolean isOnline() {
		return isOnline;
	}

	public void setOnline(boolean isOnline) {
		this.isOnline = isOnline;
	}

	public boolean isLogged() {
		return isLogged;
	}

	public void setLogged(boolean isLogged) {
		this.isLogged = isLogged;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Cosmetico> getAllCosmeticos() {
		List<Cosmetico> cos = Lists.newArrayList();
		for (String s : this.cosmeticos) {
			if (CosmeticoManager.getCosmetico(s) != null) {
				cos.add(CosmeticoManager.getCosmetico(s));
			}
		}

		return cos;

	}

	public List<Cosmetico> getEffects() {
		return getAllCosmeticos().stream().filter(c -> c.getType() == CosmeticType.EFFECT).collect(Collectors.toList());
	}

	public List<Cosmetico> getPets() {
		return getAllCosmeticos().stream().filter(c -> c.getType() == CosmeticType.PET).collect(Collectors.toList());
	}

	public List<String> getCosmeticos() {
		return cosmeticos;
	}

	public void setCosmeticos(List<String> cosmeticos) {
		this.cosmeticos = cosmeticos;
	}

	public int getCriptonCoins() {
		return criptonCoins;
	}

	public void setCriptonCoins(int criptonCoins) {
		this.criptonCoins = criptonCoins;
	}

}
