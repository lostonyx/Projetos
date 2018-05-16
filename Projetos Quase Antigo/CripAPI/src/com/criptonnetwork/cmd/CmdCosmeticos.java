package com.criptonnetwork.cmd;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.criptonnetwork.cosmeticos.CosmeticoManager;
import com.criptonnetwork.cosmeticos.gui.GuiComseticos;
import com.criptonnetwork.type.Gamer;
import com.criptonnetwork.type.GamerManager;
import com.criptonnetwork.util.HCStrings;

import net.md_5.bungee.api.ChatColor;

public class CmdCosmeticos extends Command{

	public CmdCosmeticos() {
		super("cosmeticos");
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean execute(CommandSender s, String a, String[] args) {
		if(!(s instanceof Player)) return false;
		
		if(args.length == 0) {
			GuiComseticos.open((Player)s);
		}else if(args[0].equalsIgnoreCase("add")) {
			if(args.length == 1) {
				s.sendMessage(ChatColor.RED + "/cosmetico add [player] <nome do cosmetico>");
			}else if((!Bukkit.getPlayer(args[1]).isOnline()) || Bukkit.getPlayer(args[1]) == null){
				s.sendMessage(ChatColor.RED + "jogador offline.");
			}else {
				Player p = Bukkit.getPlayer(args[1]);
				Gamer g = GamerManager.getGamer(p);
				if(args.length == 2) {
					s.sendMessage(ChatColor.RED + "Você precisa especificar um cosmetico!");
				}else if(CosmeticoManager.getCosmetico(HCStrings.allArgs(2, args)) == null) {
					s.sendMessage(ChatColor.RED + "Este cosmetico nao existe!");
				}else {
					g.getCosmeticos().add(HCStrings.allArgs(2, args));
					g.update();
					s.sendMessage(ChatColor.GREEN + "Sucesso! cosmetico adicionado com sucesso!");
				}
				
			}
		}
		
		return false;
	}

}
