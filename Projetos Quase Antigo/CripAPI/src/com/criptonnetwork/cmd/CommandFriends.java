package com.criptonnetwork.cmd;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.criptonnetwork.gui.GuiFriends;
import com.criptonnetwork.type.Gamer;
import com.criptonnetwork.type.GamerManager;

public class CommandFriends extends Command{
	// UPDATE: List<String> to List<Gamer>
	public CommandFriends() {
		super("amigos");
		setAliases(Arrays.asList("friends" , "amg"));
	}
	
	@Override
	public boolean execute(CommandSender sender, String l, String[] args) {
		if(!(sender instanceof Player)) return false;
		Player p = (Player) sender;
		Gamer g = GamerManager.getGamer(p);
		GuiFriends.open(p);
		

		
		return false;
	}
	
	
	
	
}
