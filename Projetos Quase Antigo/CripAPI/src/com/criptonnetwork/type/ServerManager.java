package com.criptonnetwork.type;

import java.util.List;

import org.bson.Document;

import com.criptonnetwork.database.Database;
import com.google.common.collect.Lists;
import com.mongodb.client.MongoCollection;

public class ServerManager {

	public static List<ServerInstance> instances = Lists.newArrayList();

	public static ServerInstance createServerInstance(String servername, boolean autorized) {
		ServerInstance sv = new ServerInstance(servername);
		sv.setAutorized(autorized);

		instances.add(sv);

		return sv;
	}

	public static Document getDocument(String serverNname) {
		MongoCollection<Document> gamers = Database.getDataBase("api").getCollection("servidores");
		Document doc = gamers.find(new Document("name", serverNname)).first();
		return doc;
	}

	public static ServerInstance getServer(String name) {
		for (ServerInstance svs : instances) {
			if (svs.getName().equalsIgnoreCase(name)) {
				return svs;
			}
		}

		return null;
	}

}
