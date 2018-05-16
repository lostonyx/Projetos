/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.saomc.SaoDesafio.Comandos;

import static co.saomc.SaoDesafio.Eventos.Eventos.EndCorrida;
import co.saomc.SaoDesafio.SaoDesafio;
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
import org.bukkit.Bukkit;
import static org.bukkit.Bukkit.getServer;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import static co.saomc.SaoDesafio.Eventos.Eventos.PreLobby;
import static co.saomc.SaoDesafio.Eventos.Eventos.StartCorrida;
import static co.saomc.SaoDesafio.Eventos.Eventos.invsave;
import static co.saomc.SaoDesafio.Eventos.Eventos.locsave;
import static co.saomc.SaoDesafio.Eventos.Eventos.pg;
import static co.saomc.SaoDesafio.Eventos.Eventos.task;
import static co.saomc.SaoDesafio.Eventos.Eventos.task_1;
import static co.saomc.SaoDesafio.Eventos.Eventos.task_10;
import static co.saomc.SaoDesafio.Eventos.Eventos.task_11;
import static co.saomc.SaoDesafio.Eventos.Eventos.task_12;
import static co.saomc.SaoDesafio.Eventos.Eventos.task_13;
import static co.saomc.SaoDesafio.Eventos.Eventos.task_2;
import static co.saomc.SaoDesafio.Eventos.Eventos.task_3;
import static co.saomc.SaoDesafio.Eventos.Eventos.task_4;
import static co.saomc.SaoDesafio.Eventos.Eventos.task_5;
import static co.saomc.SaoDesafio.Eventos.Eventos.task_6;
import static co.saomc.SaoDesafio.Eventos.Eventos.task_7;
import static co.saomc.SaoDesafio.Eventos.Eventos.task_8;
import static co.saomc.SaoDesafio.Eventos.Eventos.task_9;
import co.saomc.SaoDesafio.SaoDesafio;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import org.bukkit.Bukkit;
import static org.bukkit.Bukkit.getServer;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;
import static co.saomc.SaoDesafio.Eventos.Eventos.pg;
import static co.saomc.SaoDesafio.Eventos.Eventos.task;
import static co.saomc.SaoDesafio.Eventos.Eventos.task_1;
import static co.saomc.SaoDesafio.Eventos.Eventos.task_10;
import static co.saomc.SaoDesafio.Eventos.Eventos.task_11;
import static co.saomc.SaoDesafio.Eventos.Eventos.task_12;
import static co.saomc.SaoDesafio.Eventos.Eventos.task_13;
import static co.saomc.SaoDesafio.Eventos.Eventos.task_14;
import static co.saomc.SaoDesafio.Eventos.Eventos.task_2;
import static co.saomc.SaoDesafio.Eventos.Eventos.task_3;
import static co.saomc.SaoDesafio.Eventos.Eventos.task_4;
import static co.saomc.SaoDesafio.Eventos.Eventos.task_5;
import static co.saomc.SaoDesafio.Eventos.Eventos.task_6;
import static co.saomc.SaoDesafio.Eventos.Eventos.task_7;
import static co.saomc.SaoDesafio.Eventos.Eventos.task_8;
import static co.saomc.SaoDesafio.Eventos.Eventos.task_9;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import static co.saomc.SaoDesafio.Eventos.Eventos.EndCorrida;
import static co.saomc.SaoDesafio.Eventos.Eventos.StartCorrida;

/**
 *
 * @author WillianDev
 */
public class Comandos implements CommandExecutor{
    SaoDesafio plugin;
     
    Server sv = Bukkit.getServer();
    public Inventory inv = null;
    
    public Comandos(SaoDesafio plugin) {
        this.plugin = plugin;
    } 
    private World world;
    int max = Bukkit.getOnlinePlayers().size();
    BukkitScheduler scheduler1 = getServer().getScheduler();
    @Override
    public boolean onCommand(CommandSender cs, Command cmnd, String string, String[] strings) {
        Player p = (Player)cs;
        Player player = (Player)cs;
        if(player.hasPermission("saocorrida.admin")) {
        if (cmnd.getName().equalsIgnoreCase("saodesafio"))
        {
            if(strings.length == 0){
         p.sendMessage("§b§l[§f§lDesafio§b§l] §2§lUse /saodesafio setspawn <corrida>");
         p.sendMessage("§b§l[§f§lDesafio§b§l] §4§lCriado por Wiljafor1 :D"); 
            }
            if(strings.length == 1){
         p.sendMessage("§b§l[§f§lDesafio§b§l] §2§lUse /saodesafio setspawn <corrida>");
         p.sendMessage("§b§l[§f§lDesafio§b§l] §4§lCriado por Wiljafor1 :D"); 
            }
            if (strings.length == 2) {
                if (strings[0].equalsIgnoreCase("setspawn")) {
                    if (strings[1].equalsIgnoreCase("corrida")) {
                        plugin.getConfig().set("desafio.W", player.getWorld().getName());
                        plugin.getConfig().set("desafio.X", Double.valueOf(player.getLocation().getX()));
                        plugin.getConfig().set("desafio.Y", Double.valueOf(player.getLocation().getY()));
                        plugin.getConfig().set("desafio.Z", Double.valueOf(player.getLocation().getZ()));
                        plugin.getConfig().set("desafio.P", Double.valueOf(player.getLocation().getPitch()));
                        plugin.getConfig().set("desafio.A", Double.valueOf(player.getLocation().getYaw()));
                        player.sendMessage("§bDesafio §bsetado com sucesso!");
                        plugin.saveConfig();
                        return true;
                    }
                    return false;
                }
                return false; 
            }
            
            return false;   
        }
        return false;
    }
    return false;
}    
}
