package me.wiljafor1.saintsup.Comandos;

import me.wiljafor1.saintsup.APIs.Messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.wiljafor1.saintsup.Eventos.Board;

public class Ping implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		if (!(sender instanceof Player)) {
			return true;
		}
		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("ping")) {
			p.sendMessage(Messages.getString("su.ping").replace("{ping}", Board.pegarPing(p)).replace("&", "§"));
		}
		return true;
	}
}
