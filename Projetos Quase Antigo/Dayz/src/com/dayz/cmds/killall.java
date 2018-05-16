package com.dayz.cmds;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_8_R3.PacketPlayOutNamedSoundEffect;

public class killall  extends Command{
	public killall() {
		super("killall");
	}

	@Override
	public boolean execute(CommandSender s, String a, String[] args) {
		if(!(s instanceof Player)) return false;
        Player player = (Player) s;
        if(player.isOp() == false){ player.sendMessage(ChatColor.RED+"Somente Staff!"); return false;};
        for(Entity en : player.getWorld().getEntities()){
            if(!(en instanceof Player)) {
            en.remove();
            }
        }
        player.sendMessage(ChatColor.RED+"Voce matou " +player.getWorld().getEntities().size()+ " mobs.");
        return false;
	}
}