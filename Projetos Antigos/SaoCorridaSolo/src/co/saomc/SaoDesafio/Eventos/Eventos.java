/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.saomc.SaoDesafio.Eventos;

/**
 *
 * @author WillianDev
 */
import br.com.devpaulo.legendchat.api.events.ChatMessageEvent;
import co.saomc.SaoDesafio.SaoDesafio;
import co.saomc.SaoDesafio.SaoDesafio;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import net.milkbowl.vault.economy.Economy;
import net.minecraft.server.v1_8_R3.Block;
import org.bukkit.Bukkit;
import static org.bukkit.Bukkit.getServer;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.Server;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.BlockFace;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.util.Vector;

/**
 *
 * @author WillianDev
 */
public class Eventos implements Listener{  

    public static void EndCorrida() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static void StartCorrida() {
        PreLobby=true;
        StartCorrida();
    }
    SaoDesafio plugin;
        Server sv = Bukkit.getServer();
    public Inventory inv = null;
    public Eventos(SaoDesafio plugin) {
        this.plugin = plugin;
    }   
    public int stop;
    BukkitScheduler scheduler1 = getServer().getScheduler();
    public static ArrayList<String> pg = new ArrayList<String>();// Players Geral
    public static HashMap<Player, Location> locsave = new HashMap<Player, Location>();
    public static HashMap<Player, ItemStack[]> invsave = new HashMap<Player, ItemStack[]>();
    public static boolean PreLobby = false;
    public static boolean StartCorrida = false;   // Start do evento
    public static boolean EndCorrida = false;
    public static int task;
    public static int task_1;
    public static int task_2;
    public static int task_3;
    public static int task_4;
    public static int task_5;
    public static int task_6;
    public static int task_7;
    public static int task_8;
    public static int task_9;
    public static int task_10;
    public static int task_11;
    public static int task_12;
    public static int task_13;
    public static int task_14;
    private HashSet<String> _riding = new HashSet<String>();
    
@EventHandler//Desafio Corrida
public void onPlayerMove(PlayerMoveEvent event) {
        if(pg.contains(event.getPlayer().getName())){
        Player p = event.getPlayer();
        double y = event.getPlayer().getLocation().getY() -1;
        String w = plugin.getConfig().getString("desafio.W");
        Location loc = new Location(Bukkit.getWorld(w), event.getPlayer().getLocation().getX(), y, event.getPlayer().getLocation().getBlockZ());
        if((loc.getBlock().getType() == Material.WATER) && loc.getBlock().getType() == Material.WATER_LILY){
        p.sendMessage("Game Over!");
        p.teleport(locsave.get(p));
        pg.remove(p.getName());
        }        
        if(loc.getBlock().getType() == Material.BEDROCK){
            
        plugin.TempoFinal = tempo(Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND));//double aqui
                //Calendar.getInstance().get(12);
        double Tempo = plugin.TempoFinal - plugin.TempoInicial;//double aqui
        p.sendMessage("§7§m-§b*§7§m-§e Seu tempo é de "+ Tempo +"!§7§m-§b*§7§m-");
        if(Tempo < plugin.TempoFlash){
            p.sendMessage("§7§m-§b*§7§m-§e Voce bateu o recorde! §7§m-§b*§7§m-");    
            plugin.getConfig().set("FlashAtual", p.getName());
            plugin.getConfig().set("FlashTempo", Tempo);
            plugin.saveConfig();
        }
        else{
            p.sendMessage("§7§m-§b*§7§m-§e Que pena tenta na proxima! §7§m-§b*§7§m-");
        }
        p.teleport(locsave.get(p));
        p.getInventory().setContents(invsave.get(p));
        pg.remove(p.getName());
        plugin.TempoFinal = 0;
        plugin.TempoInicial = 0;
        }
        }      
    }
@EventHandler(priority = EventPriority.HIGHEST)//Comando quitar
public void command(PlayerCommandPreprocessEvent e) {
    Player p = e.getPlayer();
    String[] args = e.getMessage().split(" ");
        String Comandos = args[0].replace("/", "");
        if(args[0].contains("/quitar")){
        e.setCancelled(true);
            if(pg.contains(e.getPlayer().getName())){
                pg.remove(e.getPlayer().getName());
                p.getInventory().setContents(invsave.get(p));
                p.sendMessage("§7§m-§b*§7§m-§e Voce saiu do §bDesafio! §7§m-§b*§7§m-");
                p.teleport(locsave.get(p));
            }
            else{
                p.sendMessage("§7§m-§b*§7§m-§e Voce nao esta no modo §bdesafio! §7§m-§b*§7§m-");       
            }
        }
        if(args[0].contains("/desafio")){
        e.setCancelled(true);
            if(pg.contains(p.getName())){
            p.sendMessage("§7§m-§b*§7§m-§e Voce já no §bDesafio §7§m-§b*§7§m-");    
            }
            else{
                if(pg.size() == 1){
                p.sendMessage("§7§m-§b*§7§m-§e Já tem um player nesse momento! §7§m-§b*§7§m-");
                }
                else{
                if((p.getInventory().getHelmet() == null) && (p.getInventory().getChestplate()== null) && (p.getInventory().getBoots()== null) && (p.getInventory().getLeggings()== null)){
                    invsave.put(p, p.getInventory().getContents());
                    locsave.put(p, p.getLocation());
                    pg.add(p.getName());
                    p.sendMessage("§7§m-§b*§7§m-§e Voce entrou no §bDesafio §7§m-§b*§7§m-");
                    plugin.TempoInicial = tempo(Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND));//double aqui
                    String w = plugin.getConfig().getString("desafio.W");
                    double x = plugin.getConfig().getDouble("desafio.X");
                    double y = plugin.getConfig().getDouble("desafio.Y");
                    double z = plugin.getConfig().getDouble("desafio.Z");
                    float pa = plugin.getConfig().getInt("desafio.P");
                    float a = plugin.getConfig().getInt("desafio.A");
                    Location loc = new Location(Bukkit.getWorld(w), x, y, z, pa, a);
                    p.teleport(loc);    
                    ItemStack[] armorContents = p.getInventory().getArmorContents().clone();
                    p.getPlayer().getInventory().clear();
                    p.getInventory().setArmorContents(armorContents);
                    p.updateInventory(); 
                }
                else{
                    p.sendMessage("§7§m-§b*§7§m-§e Por Favor retire suas §barmaduras! §7§m-§b*§7§m-");    
                }
                }
            }
        }
        return;    
  } 
@EventHandler//Evento de Deslogar no Server
public void Sair(PlayerQuitEvent e){
        for (Player p : Bukkit.getOnlinePlayers()){
           if(pg.contains(e.getPlayer().getName())){
            p.getInventory().setContents(invsave.get(p));
            p.teleport(locsave.get(p));
            pg.remove(e.getPlayer().getName());
           }
        }
    }   
@EventHandler//Define A TAG No LegendChat
public void onChat(ChatMessageEvent e){
        if((e.getTags().contains("flash")) && (e.getSender().getName().equals(plugin.flash))){
            String tag = plugin.getConfig().getString("PrefixoFlash");
            e.setTagValue("flash", tag.replaceAll("&", "§"));
            
        }
    }  
@EventHandler//WTF
public void StartEvento(PlayerDeathEvent e){
    /*if(StartCorrida==true){    
         for(Player v : Bukkit.getOnlinePlayers()){
         if(pg.contains(e.getEntity().getName())){
         pg.remove(e.getEntity().getName());
         e.getEntity().getInventory().clear();
         v.sendMessage("§b§l[§f§lCorrida§b§l] §f§l"+e.getEntity().getName()+" morreu no evento!");
         }
         }
    
    }*/
    }
@EventHandler//blockpvp
public void BloquarPVP(EntityDamageByEntityEvent e) {
    if (e.getEntity() instanceof Player) {
    if(e.getDamager() instanceof Player) {
    Player p1 = (Player) e.getDamager();
    Player p2 = (Player) e.getEntity();
    if ((pg.contains(p1.getPlayer().getName())) &&
          ((pg.contains(p2.getName()))))  {
        e.setCancelled(true);       
    }
    if (p1 == null) {
        p1.getName();
    }
    if(p1 == p2) {
        e.setCancelled(true);
    }
	}
    }
}
@EventHandler//BlockInv
public void Inventario(InventoryClickEvent e) {
    Player p = (Player) e.getWhoClicked();
    if(pg.contains(p.getName())) {
        e.setCancelled(true);
    }
    
}
public double tempo(int valor1, int valor2){
    String tempo;
    tempo = valor1+"."+valor2;
    return(Double.parseDouble(tempo));
}
}