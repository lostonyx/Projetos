package com.dayz.cmds;

import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import com.criptonnetwork.database.Database;
import com.dayz.airdrop.AirDropManager;
import com.dayz.domination.Domination;
import com.dayz.domination.DominationManager;
import com.dayz.ranking.RankMoney;
import com.dayz.safezone.SafezoneManager;
import com.dayz.type.Gang;
import com.dayz.utils.WorldBorderApi;
import com.mojang.authlib.GameProfile;
import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

import net.minecraft.server.v1_8_R3.PacketPlayOutNamedSoundEffect;

public class dev extends Command{
	public dev() {
		super("dev");
	}

	@Override
	public boolean execute(CommandSender s, String a, String[] args) {
		if(!(s instanceof Player)) return false;
		Player p = (Player)s;
		if(args.length == 0){
			
		}
		else if(args.length == 1){
			if(args[0].contains("domination")){
				DominationManager.createDomination("Mongo", p.getLocation().add(-10, 0, -10), p.getLocation().add(10, 255, 10), 60);
				p.sendTitle("Dev", "Area criado com sucesso!");	
			}
			else if(args[0].contains("safezone")){
				SafezoneManager.createSafezone("Spawn", p.getLocation().add(-10, 0, -10), p.getLocation().add(10, 255, 10));
				p.sendTitle("Dev", "Save criado com sucesso!");	
			}
			else if(args[0].contains("airdrop")){
				AirDropManager.createAirDrop(p.getLocation(), p);
				p.sendTitle("Dev", "Save criado com sucesso!");	
			}
		}


		return false;
	}
}
