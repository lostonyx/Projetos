package com.dayz.cmds;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.UUID;

import javax.net.ssl.HttpsURLConnection;


import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import com.criptonnetwork.database.Database;
import com.dayz.ranking.RankMoney;
import com.dayz.safezone.Safezone;
import com.dayz.safezone.SafezoneManager;
import com.dayz.type.Gang;
import com.dayz.type.GangManager;
import com.mojang.authlib.GameProfile;
import com.mongodb.Block;
import com.mongodb.DB;
import com.mojang.authlib.properties.Property;
import com.mongodb.DBCursor;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

import net.minecraft.server.v1_8_R3.PacketPlayOutNamedSoundEffect;

public class weapon extends Command{

	public weapon() {
		super("weapon");
	}

	@Override
	public boolean execute(CommandSender s, String a, String[] args) {
		if(!(s instanceof Player)) return false;
		Player p = (Player)s;
		
		//-270 560 / -220 485
		//Location locOne = new Location(p.getWorld(), -270,0, 560);
		//Location locTwo = new Location(p.getWorld(), -220, 256, 485);
		
		SafezoneManager.createSafezone("Estadio", p.getLocation().add(30, 255, 30), p.getLocation().add(-30, 0, -30));
		String safezone = "Nenhuma";
		if(SafezoneManager.getCurrentSafezone(p) != null) {
			safezone = SafezoneManager.getCurrentSafezone(p).getNome();
		}
		p.sendMessage("§fCurrent Account actived: " + safezone);
		//p.sendMessage("teste lul: ");
		Gang g = new Gang("Vingancas");
		RankMoney.atualizar();
		RankMoney.sendTopMoney(p);
    	GameProfile newSkinProfile = new GameProfile(p.getUniqueId(), p.getName());
    	setSkin(newSkinProfile, p.getUniqueId());
		loadGangs();
		MongoCollection<Document> gan = Database.getDataBase("dayz").getCollection("gangs");
		FindIterable<Document> iterable = Database.getDataBase("dayz").getCollection("gangs").find();
		iterable.forEach(new Block<Document>() {
		        @Override
		        public void apply(final Document document) {
		        	//Bukkit.broadcastMessage("teste: "+ document);
		            System.out.println(document);
		        }
		   });
		PacketPlayOutNamedSoundEffect sound = new PacketPlayOutNamedSoundEffect("lul", p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ(), 300f, 1f);
		for(Player q : Bukkit.getOnlinePlayers()) {
			CraftPlayer pp = (CraftPlayer) q;
			pp.getHandle().playerConnection.sendPacket(sound);
		}
		//ItemStack item = Arma.createWeapon("AK47", Material.GOLD_RECORD, 30, 30, 1.0, 6.5, 1);
		//p.getInventory().addItem(item);
		//p.updateInventory();
		return false;
	}
	
	public static boolean setSkin(GameProfile profile, UUID uuid) {
	    try {
	        HttpsURLConnection connection = (HttpsURLConnection) new URL(String.format("https://sessionserver.mojang.com/session/minecraft/profile/%s?unsigned=false", uuid)).openConnection();
	        if (connection.getResponseCode() == HttpsURLConnection.HTTP_OK) {
	            String reply = new BufferedReader(new InputStreamReader(connection.getInputStream())).readLine();
	            String skin = reply.split("\"value\":\"")[1].split("\"")[0];
	            String signature = reply.split("\"signature\":\"")[1].split("\"")[0];
	            profile.getProperties().put("textures", new Property("textures", skin, signature));
	            return true;
	        } else {
	            System.out.println("Connection could not be opened (Response code " + connection.getResponseCode() + ", " + connection.getResponseMessage() + ")");
	            return false;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	public void loadGangs() {
		MongoCollection<Document> jogadores = Database.getDataBase("dayz").getCollection("gangs");
	    FindIterable<Document> teste = jogadores.find();
	    Bukkit.broadcastMessage("Carregou!");
	    for(Document d : teste) {
	    	String name = d.getString("name");
	    	boolean ff = d.getBoolean("ff");
	    	Bukkit.broadcastMessage("Nomes: " + name);
	    	Gang g = GangManager.getGang(name);
	    	
	    }
	}
	    
	
	 
}
