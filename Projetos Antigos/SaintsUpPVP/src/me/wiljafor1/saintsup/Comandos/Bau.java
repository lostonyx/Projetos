package me.wiljafor1.saintsup.Comandos;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import me.wiljafor1.saintsup.APIs.BauManager;
import me.wiljafor1.saintsup.APIs.Messages;

public class Bau implements CommandExecutor {

	private final BauManager BauManager;

	public Bau(BauManager chestManager) {
		this.BauManager = chestManager;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		if (!(sender instanceof Player)) {
			return true;
		}
		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("bau")) {
			if (args.length == 0) {
				if (p.hasPermission("saints.bau")) {
					Inventory chest = BauManager.pegarChest(p);
					p.openInventory(chest);
				} else {
                                        p.sendMessage(Messages.getString("su.nopermbau").replace("&", "§"));
					//p.sendMessage("§cComando dispon§vel para jogadores VIPS ou rank Obsidian!");
					//p.sendMessage("§cAdquira VIP em nossa loja:");
					//p.sendMessage("§7www.niferkits.com/loja");
				}
				return true;
			}
		}
		return false;
	}
}