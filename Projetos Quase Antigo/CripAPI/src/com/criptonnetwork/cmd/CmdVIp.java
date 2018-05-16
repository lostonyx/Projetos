package com.criptonnetwork.cmd;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.criptonnetwork.type.Pacote;
import com.criptonnetwork.type.PacoteManager;
import com.criptonnetwork.util.SimpleItem;
import com.criptonnetwork.util.SimpleItem.InventoryAction;

public class CmdVIp extends Command{

	public CmdVIp() {
		super("vip");
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean execute(CommandSender sender, String arg1, String[] args) {
		if(!(sender instanceof Player)) return false;
		openVIP((Player)sender);
		return false;
	}
	
	
	void openVIP(Player p) {
		Inventory inv = Bukkit.createInventory(null , 9 * 5 , "Escolha o Pacote.");
		
		for(Pacote pc : PacoteManager.pacotes) {
			ItemStack i = pc.getIcon();
			inv.addItem(i);
			SimpleItem.createInventoryItem(i, new InventoryAction(inv, () -> {
				p.closeInventory();
				p.sendMessage("§eVIP LORDE");
				p.sendMessage("");
				p.sendMessage("§6§l§nClique aqui para comprar");
				p.sendMessage("");
				p.sendMessage("§aTempo:§7 " + pc.getTime() + " Dias.");
				p.sendMessage("§aPreço:§7 " + pc.getCusto() + " Reais.");
				p.sendMessage("§aTipo: §f" + pc.getType().name());
				p.sendMessage("");
			}, p, true));
		}
		p.openInventory(inv);
		
	}

}
