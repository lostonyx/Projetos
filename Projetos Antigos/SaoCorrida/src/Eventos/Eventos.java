/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Eventos;

/**
 *
 * @author WillianDev
 */
import br.com.devpaulo.legendchat.api.events.ChatMessageEvent;
import net.wiljafor1.SaoCorrida;
import net.wiljafor1.SaoCorrida;
import java.io.File;
import java.util.ArrayList;
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
    SaoCorrida plugin;
        Server sv = Bukkit.getServer();
    public Inventory inv = null;
    public Eventos(SaoCorrida plugin) {
        this.plugin = plugin;
    }   
    public int stop;
    BukkitScheduler scheduler1 = getServer().getScheduler();
    
    public static HashMap<Integer, String> mgp = new HashMap();
    public static HashMap<Integer, Integer> pgt = new HashMap();
    public static HashMap<String, Integer> pgn = new HashMap();
    public static ArrayList kits1 = new ArrayList();
    public static ArrayList kits2 = new ArrayList();
    public static ArrayList kits3 = new ArrayList();
    public static ArrayList kits4 = new ArrayList();
    public static ArrayList l1 = new ArrayList();// luga 1
    public static ArrayList l2 = new ArrayList();// lugar 2
    public static ArrayList l3 = new ArrayList();//lugar 3
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
    // Evento Corrida
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        
if(StartCorrida == true){
        if(pg.contains(event.getPlayer().getName())){
        double y = event.getPlayer().getLocation().getY() -1;
        String w = Bukkit.getPluginManager().getPlugin("SaoCorrida").getConfig().getString("evento.corrida.W");
        Location loc = new Location(Bukkit.getWorld(w), event.getPlayer().getLocation().getX(), y, event.getPlayer().getLocation().getBlockZ());
        if(loc.getBlock().getType() == Material.WATER){
              double x = Bukkit.getPluginManager().getPlugin("SaoCorrida").getConfig().getDouble("evento.corrida.X");
              double ys = Bukkit.getPluginManager().getPlugin("SaoCorrida").getConfig().getDouble("evento.corrida.Y");
              double z = Bukkit.getPluginManager().getPlugin("SaoCorrida").getConfig().getDouble("evento.corrida.Z");
              float pa = Bukkit.getPluginManager().getPlugin("SaoCorrida").getConfig().getInt("evento.corrida.P");
              float a = Bukkit.getPluginManager().getPlugin("SaoCorrida").getConfig().getInt("evento.corrida.A");
              Location loca = new Location(Bukkit.getWorld(w), x, ys, z, pa, a);
              event.getPlayer().teleport(loca);  
        }        
        if(loc.getBlock().getType() == Material.BEDROCK){
            if(l1.isEmpty() == true){
              l1.add(event.getPlayer().getName());
              for (Player p : Bukkit.getOnlinePlayers()){
                    if(pg.contains(p.getName())){
                    p.sendMessage("§7§m-§b*§7§m-§e"+l1.toString()+ " Chegou em §b#1 Lugar §7§m-§b*§7§m-");    
                    }  
             }
              //tp l1 para lobby  
            }
            if(l1.contains(event.getPlayer().getName())){
            }
            else{
            if(l2.isEmpty() == true){
              l2.add(event.getPlayer().getName());
              for (Player p : Bukkit.getOnlinePlayers()){
                    if(pg.contains(p.getName())){
                    p.sendMessage("§7§m-§b*§7§m-§e"+l2.toString()+ " Chegou em §b#2 Lugar §7§m-§b*§7§m-");    
                    }  
             } 
              //tp l2 para lobby  
            }
            }
            if(!l2.contains(event.getPlayer().getName()) && !l1.contains(event.getPlayer().getName())){
            if(l3.isEmpty() == true){
              l3.add(event.getPlayer().getName());
              for (Player p : Bukkit.getOnlinePlayers()){
                    if(pg.contains(p.getName())){
                    p.sendMessage("§7§m-§b*§7§m-§e"+l3.toString()+ " Chegou em §b#3 Lugar §7§m-§b*§7§m-");  
                    p.sendMessage("§7§m-§b*§7§m-§e evento acabou! §7§m-§b*§7§m-");
                    EndCorrida = true;
        if(EndCorrida=true){
        
        stop =  scheduler1.scheduleSyncDelayedTask(plugin, new Runnable(){
        public void run(){ 
            Bukkit.getScheduler().cancelTask(stop);
            for (Player p : Bukkit.getOnlinePlayers()){

            //da tp pg para lobby
              if(l1.isEmpty() || l2.isEmpty() || l3.isEmpty()){
                  
              }
              else{
         for(Player v : Bukkit.getOnlinePlayers()){
         if(pg.contains(v.getName())){
              String w = Bukkit.getPluginManager().getPlugin("SaoCorrida").getConfig().getString("evento.spawn.W");
              double x = Bukkit.getPluginManager().getPlugin("SaoCorrida").getConfig().getDouble("evento.spawn.X");
              double y = Bukkit.getPluginManager().getPlugin("SaoCorrida").getConfig().getDouble("evento.spawn.Y");
              double z = Bukkit.getPluginManager().getPlugin("SaoCorrida").getConfig().getDouble("evento.spawn.Z");
              float pa = Bukkit.getPluginManager().getPlugin("SaoCorrida").getConfig().getInt("evento.spawn.P");
              float a = Bukkit.getPluginManager().getPlugin("SaoCorrida").getConfig().getInt("evento.spawn.A");
              Location loc = new Location(Bukkit.getWorld(w), x, y, z, pa, a);
              v.teleport(locsave.get(p));
              v.getInventory().setContents(invsave.get(v));
         }
         }
         
              p.sendMessage("§7§m-§b*§7§m-§e 1º Lugar para "+ l1.toString()+" §7§m-§b*§7§m-§e");
              p.sendMessage("§7§m-§b*§7§m-§e 2º Lugar para "+ l2.toString()+" §7§m-§b*§7§m-§e");
              p.sendMessage("§7§m-§b*§7§m-§e 3º Lugar para "+ l3.toString()+" §7§m-§b*§7§m-§e");
              
            if(l1.contains(p.getPlayer().getName())){
            plugin.economy.depositPlayer(p.getPlayer().getName(), plugin.getConfig().getDouble("Premio1"));
            plugin.getConfig().set("FlashAtual", null);
            plugin.getConfig().set("FlashAtual", p.getPlayer().getName());
            plugin.flash = plugin.getConfig().getString("FlashAtual");
            plugin.saveConfig();
            }
            if(l2.contains(p.getPlayer().getName())){
            plugin.economy.depositPlayer(p.getPlayer().getName(), plugin.getConfig().getDouble("Premio2"));    
            }
            if(l3.contains(p.getPlayer().getName())){
            plugin.economy.depositPlayer(p.getPlayer().getName(), plugin.getConfig().getDouble("Premio3"));    
            } 
              }

              StartCorrida= false;
              EndCorrida=false;

              Bukkit.getScheduler().cancelTask(stop);
              Bukkit.getScheduler().cancelTask(task_13);
              Bukkit.getScheduler().cancelTask(task_14);
              

             }
            Bukkit.getScheduler().cancelTask(task_13);
            Bukkit.getScheduler().cancelTask(task_14);
            l1.clear();
            l2.clear();
            l3.clear();
            pg.clear();
            StartCorrida= false;
            EndCorrida=false;
            Bukkit.getScheduler().cancelTask(stop);
        }
        
        }, 1 * 30);
        }
                    }  
             } 
            //tp l3 para lobby  
            
            }
            }
            }
            //event.getPlayer().sendMessage(l1.toString() +" "+ l2.toString() + " "+ l3.toString());
        }
}       
    }
    // evento termina aqui de move EVENTE do spleef

  @EventHandler(priority = EventPriority.HIGHEST)
  public void command(PlayerCommandPreprocessEvent e) {
    Player p = e.getPlayer();
    String[] args = e.getMessage().split(" ");
        
        String Comandos = args[0].replace("/", "");
        if(args[0].contains("/sair")){
        e.setCancelled(true);
        if(StartCorrida==true){
            if(pg.contains(e.getPlayer().getName())){
         pg.remove(e.getPlayer().getName());
         p.getInventory().setContents(invsave.get(p));
         p.sendMessage("§7§m-§b*§7§m-§e Voce saiu do §bevento! §7§m-§b*§7§m-");
         p.teleport(locsave.get(p));
            }
            else{
            p.sendMessage("§7§m-§b*§7§m-§e Voce nao esta em um §bevento! §7§m-§b*§7§m-");       
            }
        }
        else{
        p.sendMessage("§7§m-§b*§7§m-§e Nenhum §bevento ocorrendo! §7§m-§b*§7§m-");    
        }
        }
        return;    
  }
        
    @EventHandler
    public void Sair(PlayerQuitEvent e){
        if(pg.contains(e.getPlayer().getName())){
                        Player p = e.getPlayer();
                        p.getInventory().setContents(invsave.get(p));
                        String w = Bukkit.getPluginManager().getPlugin("SaoCorrida").getConfig().getString("evento.spawn.W");
                        double x = Bukkit.getPluginManager().getPlugin("SaoCorrida").getConfig().getDouble("evento.spawn.X");
                        double y = Bukkit.getPluginManager().getPlugin("SaoCorrida").getConfig().getDouble("evento.spawn.Y");
                        double z = Bukkit.getPluginManager().getPlugin("SaoCorrida").getConfig().getDouble("evento.spawn.Z");
                        float pa = Bukkit.getPluginManager().getPlugin("SaoCorrida").getConfig().getInt("evento.spawn.P");
                        float a = Bukkit.getPluginManager().getPlugin("SaoCorrida").getConfig().getInt("evento.spawn.A");
                        Location loc = new Location(Bukkit.getWorld(w), x, y, z, pa, a);
                        p.teleport(loc);
                        pg.remove(e.getPlayer().getName());
        }
    }
    
    @EventHandler
    public void onChat(ChatMessageEvent e){
        if((e.getTags().contains("flash")) && (e.getSender().getName().equals(plugin.flash))){
            String tag = plugin.getConfig().getString("PrefixoFlash");
            e.setTagValue("flash", tag.replaceAll("&", "§"));
            
        }
    }
    
    @EventHandler
    public void StartEvento(PlayerDeathEvent e){
    if(StartCorrida==true){    
         for(Player v : Bukkit.getOnlinePlayers()){
         if(pg.contains(e.getEntity().getName())){
         pg.remove(e.getEntity().getName());
         e.getEntity().getInventory().clear();
         v.sendMessage("§b§l[§f§lCorrida§b§l] §f§l"+e.getEntity().getName()+" morreu no evento!");
         }
         }
    
    }
    }
@EventHandler
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
@EventHandler
public void Inventario(InventoryClickEvent e) {
    Player p = (Player) e.getWhoClicked();
    if(pg.contains(p.getName())) {
        e.setCancelled(true);
    }
    
}
}