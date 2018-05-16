/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comandos;

import darknesstuto.DarkNessTuto;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.entity.Blaze;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

/**
 *
 * @author WillianDev
 */
public class SetarMob implements CommandExecutor{
    DarkNessTuto plugin;
    public SetarMob(DarkNessTuto plugin) {
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender cs, Command cmnd, String string, String[] args) {
            Player p = (Player)cs;
        if(cs instanceof Player) {
            if(cs.hasPermission("dk.tuto.criar")) {
                if(cmnd.getName().equalsIgnoreCase("npc"))
                    if(args.length == 2) {
                        if(args[0].equalsIgnoreCase("criar")) {
                            if(args[1].equalsIgnoreCase("warp")) {
                                p.sendMessage("§f[§6§lTutorial§f] §aVocê criou o §cNPC §ado tutorial warp com sucesso!");
				Villager v = (Villager)p.getLocation().getWorld().spawnEntity(p.getLocation(), EntityType.VILLAGER);
				v.setCustomName("§f[§6§lTutorial§f]Warps");
				v.setCustomNameVisible(true);
                                v.setProfession(Villager.Profession.FARMER);
                                v.setFireTicks(69);
                                
                            net.minecraft.server.v1_8_R3.Entity nmsEntity = ((CraftEntity) v).getHandle();
			    NBTTagCompound tag = nmsEntity.getNBTTag();
			    if (tag == null) {
			        tag = new NBTTagCompound();
			    }
			    nmsEntity.c(tag);
			    tag.setInt("NoAI", 1);
			    nmsEntity.f(tag);
                            }
                            if(args[1].equalsIgnoreCase("ranks")) {
                                p.sendMessage("§f[§6§lTutorial§f] §aVocê criou o §cNPC §ado tutorial ranks com sucesso!");
				Villager v = (Villager)p.getLocation().getWorld().spawnEntity(p.getLocation(), EntityType.VILLAGER);
				v.setCustomName("§f[§6§lTutorial§f]Ranks");
				v.setCustomNameVisible(true);
                                v.setProfession(Villager.Profession.FARMER);
                                v.setFireTicks(69);
                                
                            net.minecraft.server.v1_8_R3.Entity nmsEntity = ((CraftEntity) v).getHandle();
			    NBTTagCompound tag = nmsEntity.getNBTTag();
			    if (tag == null) {
			        tag = new NBTTagCompound();
			    }
			    nmsEntity.c(tag);
			    tag.setInt("NoAI", 1);
			    nmsEntity.f(tag);
                            }
                            if(args[1].equalsIgnoreCase("regras")) {
                                p.sendMessage("§f[§6§lTutorial§f] §aVocê criou o §cNPC §ado tutorial regras com sucesso!");
				Villager v = (Villager)p.getLocation().getWorld().spawnEntity(p.getLocation(), EntityType.VILLAGER);
				v.setCustomName("§f[§6§lTutorial§f]Regras");
				v.setCustomNameVisible(true);
                                v.setProfession(Villager.Profession.FARMER);
                                v.setFireTicks(69);
                                
                            net.minecraft.server.v1_8_R3.Entity nmsEntity = ((CraftEntity) v).getHandle();
			    NBTTagCompound tag = nmsEntity.getNBTTag();
			    if (tag == null) {
			        tag = new NBTTagCompound();
			    }
			    nmsEntity.c(tag);
			    tag.setInt("NoAI", 1);
			    nmsEntity.f(tag);
                            }  
                            if(args[1].equalsIgnoreCase("end")) {
                                p.sendMessage("§f[§6§lTutorial§f] §aVocê criou o §cNPC §ado tutorial concluir com sucesso!");
				Villager v = (Villager)p.getLocation().getWorld().spawnEntity(p.getLocation(), EntityType.VILLAGER);
				v.setCustomName("§f[§6§lTutorial§f]Concluir");
				v.setCustomNameVisible(true);
                                v.setProfession(Villager.Profession.FARMER);
                                v.setFireTicks(69);
                                
                            net.minecraft.server.v1_8_R3.Entity nmsEntity = ((CraftEntity) v).getHandle();
			    NBTTagCompound tag = nmsEntity.getNBTTag();
			    if (tag == null) {
			        tag = new NBTTagCompound();
			    }
			    nmsEntity.c(tag);
			    tag.setInt("NoAI", 1);
			    nmsEntity.f(tag);
                            }                          
                        }
                    } else {
                      p.sendMessage("§f[§6§lTutorial§f] §cUse : /npc <arg 1> <arg 2>");
                    }
            }
        }
        return false;
    }
    
}
