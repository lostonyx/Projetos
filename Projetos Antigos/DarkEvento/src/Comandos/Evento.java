/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comandos;

import static Eventos.Eventos.pgn;
import static Eventos.Eventos.pgt;
import darkevento.DarkEvento;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

/**
 *
 * @author WillianDev
 */
public class Evento implements CommandExecutor{
    DarkEvento plugin;
     
    Server sv = Bukkit.getServer();
    public Inventory inv = null;
    
    public Evento(DarkEvento plugin) {
        this.plugin = plugin;
    }  
    @Override
    public boolean onCommand(CommandSender cs, Command cmnd, String string, String[] strings) {
        
        Player p = (Player)cs;
        if(Bukkit.getOnlinePlayers().size() >= 3){
        if(Eventos.Eventos.PreLobby==true){
        if(Eventos.Eventos.StartCorrida == true){
        if(cmnd.getName().equalsIgnoreCase("evento")){
         p.getPlayer().getInventory().clear();
         Eventos.Eventos.pg.add(p.getName());
         World w = Bukkit.getWorld("evento");
         p.sendMessage("§b§l[§f§lCorrida§b§l] §f§lVoçê entrou no evento!");
         double x = Bukkit.getPluginManager().getPlugin("DarkEvento").getConfig().getDouble("evento.lobby.X");
         double y = Bukkit.getPluginManager().getPlugin("DarkEvento").getConfig().getDouble("evento.lobby.Y");
         double z = Bukkit.getPluginManager().getPlugin("DarkEvento").getConfig().getDouble("evento.lobby.Z");
         float pa = Bukkit.getPluginManager().getPlugin("DarkEvento").getConfig().getInt("evento.lobby.P");
         float a = Bukkit.getPluginManager().getPlugin("DarkEvento").getConfig().getInt("evento.lobby.A");
         Location loc = new Location(w, x, y, z, pa, a);
         p.teleport(loc);
        }
        }
        if(Eventos.Eventos.StartSpleef == true){
        if(Eventos.Eventos.pg.contains(p.getPlayer().getName())){
            p.sendMessage("§b§l[§f§lSpleef§b§l] §f§lVoçê já esta no evento!");
        }
        else{
        if(cmnd.getName().equalsIgnoreCase("evento")){
         pgt.put(5, pgt.get(5)+1);
         pgn.put(p.getName(), 1);
         p.getPlayer().getInventory().clear();
         Eventos.Eventos.pg.add(p.getName());
         World w = Bukkit.getWorld("evento");
        // p.sendMessage("§b§l[§f§lSpleef§b§l] §f§l"+ pgt.get(5));
         p.sendMessage("§b§l[§f§lSpleef§b§l] §f§lVoçê entrou no evento!");
         double x = Bukkit.getPluginManager().getPlugin("DarkEvento").getConfig().getDouble("evento.lobby.X");
         double y = Bukkit.getPluginManager().getPlugin("DarkEvento").getConfig().getDouble("evento.lobby.Y");
         double z = Bukkit.getPluginManager().getPlugin("DarkEvento").getConfig().getDouble("evento.lobby.Z");
         float pa = Bukkit.getPluginManager().getPlugin("DarkEvento").getConfig().getInt("evento.lobby.P");
         float a = Bukkit.getPluginManager().getPlugin("DarkEvento").getConfig().getInt("evento.lobby.A");
         Location loc = new Location(w, x, y, z, pa, a);
         p.teleport(loc);
         inv = sv.createInventory(null, 27, "§6Shop - Spleef");
         ItemStack i1 = new ItemStack(160);
         ItemMeta i1meta = i1.getItemMeta();
         i1meta.setDisplayName("");
         i1.setItemMeta(i1meta);
         
         ItemStack i2 = new ItemStack(160);
         ItemMeta i2meta = i2.getItemMeta();
         i2meta.setDisplayName("");
         i2.setItemMeta(i2meta);
         
         ItemStack i3 = new ItemStack(160);
         ItemMeta i3meta = i3.getItemMeta();
         i3meta.setDisplayName("");
         i3.setItemMeta(i3meta);
               
         SkullMeta  meta = (SkullMeta) Bukkit.getItemFactory().getItemMeta(Material.SKULL_ITEM);
         meta.setOwner(p.getName());
         meta.setDisplayName(p.getName());
         ItemStack i4 = new ItemStack(Material.SKULL_ITEM,1 , (byte)3);
         i4.setItemMeta(meta);
         
         
         ItemStack i5 = new ItemStack(54);
         ItemMeta i5meta = i5.getItemMeta();
         i5meta.setDisplayName("§4§lLoja Spleef");
         i5.setItemMeta(i5meta);  
         
         ItemStack i6 = new ItemStack(340);
         ItemMeta i6meta = i6.getItemMeta();
         i6meta.setDisplayName("§4§lCreditos");
         i6.setItemMeta(i6meta);
         
         ItemStack i7 = new ItemStack(160);
         ItemMeta i7meta = i7.getItemMeta();
         i7meta.setDisplayName("");
         i7.setItemMeta(i7meta);
         
         ItemStack i8 = new ItemStack(160);
         ItemMeta i8meta = i8.getItemMeta();
         i8meta.setDisplayName("");
         i8.setItemMeta(i8meta);
         
         ItemStack i9 = new ItemStack(160);
         ItemMeta i9meta = i9.getItemMeta();
         i9meta.setDisplayName("");
         i9.setItemMeta(i9meta);
         p.getInventory().setItem(0, i1);
         p.getInventory().setItem(1, i2);
         p.getInventory().setItem(2, i3);
         p.getInventory().setItem(3, i4);
         p.getInventory().setItem(4, i5);
         p.getInventory().setItem(5, i6);
         p.getInventory().setItem(6, i7);
         p.getInventory().setItem(7, i8);
         p.getInventory().setItem(8, i9);
         p.setGameMode(GameMode.SURVIVAL);
        }            
        }
        
        }
        }
        }
        else{
            p.sendMessage("§b§l[§f§lEvento§b§l] §f§lnenhum evento disponivel!");
        }
        return false;
    }
    
}
