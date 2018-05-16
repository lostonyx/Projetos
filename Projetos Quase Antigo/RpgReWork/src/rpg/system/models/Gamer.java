package rpg.system.models;
import java.util.Arrays;
import java.util.List;
import org.bson.Document;
import org.bukkit.entity.Player;
import com.google.common.collect.Lists;
import com.mongodb.client.MongoCollection;

import rpg.system.Database;

public class Gamer {
	public static MongoCollection<Document> gamers = Database.getDataBase("wynn").getCollection("players");
	private boolean isLogged;
	private String id, name;
	private long lastTimePlayed;
	private String group;
	private List<String> permissions;
	private List<String> friends;
	private String lastAdress;
	private boolean isOnline;
	private int Coins;
	

	private Player player;

	@SuppressWarnings("unchecked")
	public Gamer(Player player) {
		this.name = player.getName();
		this.id = player.getUniqueId().toString();
		this.group = "Member";
		this.permissions = Lists.newArrayList();
		this.friends = Arrays.asList("WynnCraft");
		this.lastTimePlayed = System.currentTimeMillis();
		this.player = player;
		String[] ip = player.getAddress().toString().replace("/", "").split(":");
		this.lastAdress = ip[0];
		this.isOnline = true;
		this.Coins = 0;

		Document gamer = gamers.find(new Document("id", this.id)).first();

		if (gamer != null) {
			this.id = gamer.getString("id");
			this.name = gamer.getString("name");
			this.group = gamer.getString("group");
			this.friends = (List<String>) gamer.get("friends");
			this.permissions = (List<String>) gamer.get("permissions");
			this.lastAdress = gamer.getString("lastAdress");
			this.lastTimePlayed = gamer.getLong("lastTimePlayed");
			this.isOnline = gamer.getBoolean("isOnline");
			this.Coins = gamer.getInteger("coins");
		} else {
			gamer = new Document();
			gamer.put("id", this.id);
			gamer.put("name", this.name);
			gamer.put("group", this.group);
			gamer.put("friends", this.friends);
			gamer.put("permissions", this.permissions);
			gamer.put("lastTimePlayed", this.lastTimePlayed);
			gamer.put("lastAdress", this.lastAdress);
			gamer.put("isOnline", this.isOnline);
			gamer.put("coins", this.Coins);
			gamers.insertOne(gamer);
		}
	}

	@SuppressWarnings("unchecked")
	public Gamer(String ip) {
		this.id = null;
		this.group = "Membro";
		this.permissions = Lists.newArrayList();
		this.friends = Arrays.asList("WynnCraft");
		this.lastTimePlayed = System.currentTimeMillis();
		this.player = null;
		this.lastAdress = ip;
		this.isOnline = true;
		Document gamer = gamers.find(new Document("ip", this.lastAdress)).first();
		if (gamer != null) {
			this.id = gamer.getString("id");
			this.name = gamer.getString("name");
			this.group = gamer.getString("group");
			this.friends = (List<String>) gamer.get("friends");
			this.permissions = (List<String>) gamer.get("permissions");
			this.lastAdress = gamer.getString("lastAdress");
			this.lastTimePlayed = gamer.getLong("lastTimePlayed");
			this.isOnline = gamer.getBoolean("isOnline");

		}
	}

	public void update() {
		Document gamer = gamers.find(new Document("id", this.id)).first();
		gamer.put("id", this.id);
		gamer.put("name", this.name);
		gamer.put("group", this.group);
		gamer.put("friends", this.friends);
		gamer.put("permissions", this.permissions);
		gamer.put("lastTimePlayed", this.lastTimePlayed);
		gamer.put("lastAdress", this.lastAdress);
		gamer.put("isOnline", this.isOnline);
		gamer.put("coins", this.Coins);
		gamers.updateOne(new Document("id", this.id.toString()), new Document("$set", gamer));

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

	public int getCoins() {
		return Coins;
	}

	public void setCoins(int Coins) {
		this.Coins = Coins;
	}

}
