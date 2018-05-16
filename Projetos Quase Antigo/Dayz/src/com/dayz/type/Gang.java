package com.dayz.type;

import java.util.List;

import org.bson.Document;
import org.bukkit.Bukkit;

import com.criptonnetwork.database.Database;
import com.google.common.collect.Lists;
import com.mongodb.client.MongoCollection;

public class Gang {
	public static enum GangRank {
		MEMBER, MODERATOR, ADMIN
	}

	private static MongoCollection<Document> gangs = Database.getDataBase("dayz").getCollection("gangs");

	private List<String> membros; // Format cardies:Membros
	private String name;
	private boolean friendFire;

	public Gang(String name) {
		this.name = name;
		this.membros = Lists.newArrayList();
		this.friendFire = true;
		Document gang = gangs.find(new Document("name", this.name)).first();

		if (gang != null) {
			this.name = gang.getString("name");
			this.membros = (List) gang.get("membros");
			this.friendFire = gang.getBoolean("ff");
		} else {
			gang = new Document();
			gang.put("name", name);
			gang.put("membros", membros);
			gang.put("ff", this.friendFire);
			gangs.insertOne(gang);
		}
	}

	public List<String> getMembros() {
		return membros;
	}

	public void setMembros(List<String> membros) {
		this.membros = membros;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isFriendFire() {
		return friendFire;
	}

	public void setFriendFire(boolean friendFire) {
		this.friendFire = friendFire;
	}

}
