/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Eventos;

import core.util.UtilBlock;
import core.util.UtilEnt;
import darkevento.DarkEvento;
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
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
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
    DarkEvento plugin;
        Server sv = Bukkit.getServer();
    public Inventory inv = null;
    public Eventos(DarkEvento plugin) {
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
    public static boolean PreLobby = false;
    public static boolean StartCorrida = false;   // Start do evento
    public static boolean StartSpleef = false;
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
    private HashSet<String> _riding = new HashSet<String>();
    // Evento Corrida
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        
if(StartCorrida == true){
        if(pg.contains(event.getPlayer().getName())){
        double y = event.getPlayer().getLocation().getY() -1;
        World w = Bukkit.getWorld("evento");
        Location loc = new Location(w, event.getPlayer().getLocation().getX(), y, event.getPlayer().getLocation().getBlockZ());
        if(loc.getBlock().getType() == Material.WATER){
              double x = Bukkit.getPluginManager().getPlugin("DarkEvento").getConfig().getDouble("evento.corrida.X");
              double ys = Bukkit.getPluginManager().getPlugin("DarkEvento").getConfig().getDouble("evento.corrida.Y");
              double z = Bukkit.getPluginManager().getPlugin("DarkEvento").getConfig().getDouble("evento.corrida.Z");
              float pa = Bukkit.getPluginManager().getPlugin("DarkEvento").getConfig().getInt("evento.corrida.P");
              float a = Bukkit.getPluginManager().getPlugin("DarkEvento").getConfig().getInt("evento.corrida.A");
              Location loca = new Location(w, x, ys, z, pa, a);
              event.getPlayer().teleport(loca);  
        }        
        if(loc.getBlock().getType() == Material.BEDROCK){
            if(l1.isEmpty() == true){
              l1.add(event.getPlayer().getName());
              for (Player p : Bukkit.getOnlinePlayers()){
                    if(pg.contains(p.getName())){
                    p.sendMessage("§b§l[§f§lCorrida§b§l] §f§l"+l1.toString()+ " 1º lugar!");    
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
                    p.sendMessage("§b§l[§f§lCorrida§b§l] §f§l"+l2.toString()+ " 2º lugar!");    
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
                    p.sendMessage("§b§l[§f§lCorrida§b§l] §f§l"+l3.toString()+ " 3º lugar!");  
                    p.sendMessage("§b§l[§f§lCorrida§b§l] §f§levento acabou!");
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
         if(pg.contains(p.getName())){
              World w = Bukkit.getWorld("world");
              double x = Bukkit.getPluginManager().getPlugin("DarkEvento").getConfig().getDouble("evento.spawn.X");
              double y = Bukkit.getPluginManager().getPlugin("DarkEvento").getConfig().getDouble("evento.spawn.Y");
              double z = Bukkit.getPluginManager().getPlugin("DarkEvento").getConfig().getDouble("evento.spawn.Z");
              float pa = Bukkit.getPluginManager().getPlugin("DarkEvento").getConfig().getInt("evento.spawn.P");
              float a = Bukkit.getPluginManager().getPlugin("DarkEvento").getConfig().getInt("evento.spawn.A");
              Location loc = new Location(w, x, y, z, pa, a);
              v.teleport(loc);
         }
         }
              p.sendMessage("§b§l[§f§lCorrida§b§l] §f§l1º Lugar para "+ l1.toString());
              p.sendMessage("§b§l[§f§lCorrida§b§l] §f§l2º Lugar para "+ l2.toString());
              p.sendMessage("§b§l[§f§lCorrida§b§l] §f§l3º Lugar para "+ l3.toString());
            if(l1.contains(p.getPlayer().getName())){
            plugin.economy.depositPlayer(p.getPlayer().getName(), 40000.00);    
            }
            if(l2.contains(p.getPlayer().getName())){
            plugin.economy.depositPlayer(p.getPlayer().getName(), 20000.00);    
            }
            if(l3.contains(p.getPlayer().getName())){
            plugin.economy.depositPlayer(p.getPlayer().getName(), 13333.00);    
            } 
              }

              StartCorrida= false;
              EndCorrida=false;

              Bukkit.getScheduler().cancelTask(stop);
              

             }
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
    if(StartSpleef == true){
        if(pg.isEmpty() || pgt.isEmpty()){
            
        }
        else{
        if(PreLobby==false){
        int retval = pgt.size();
        if(pgt.get(5) >= 2){
            //Bukkit.broadcastMessage("pgt.get(5) >= 2");
            //Bukkit.broadcastMessage(pgt.get(5)+"");
        }
        else{
        if(pgt.get(5) == 1){
        
       
        for (Player pp : Bukkit.getOnlinePlayers()){
        if(pgn.get(pp.getName()) == 1 && pg.contains(pp.getName())){
         mgp.put(50, pp.getName());
         plugin.economy.depositPlayer(mgp.get(50), 40000.00);
         World w = Bukkit.getWorld("world");
         double x = Bukkit.getPluginManager().getPlugin("DarkEvento").getConfig().getDouble("evento.spawn.X");
         double ys = Bukkit.getPluginManager().getPlugin("DarkEvento").getConfig().getDouble("evento.spawn.Y");
         double z = Bukkit.getPluginManager().getPlugin("DarkEvento").getConfig().getDouble("evento.spawn.Z");
         float pa = Bukkit.getPluginManager().getPlugin("DarkEvento").getConfig().getInt("evento.spawn.P");
         float a = Bukkit.getPluginManager().getPlugin("DarkEvento").getConfig().getInt("evento.spawn.A");
         Location loca = new Location(w, x, ys, z, pa, a);
         pp.getPlayer().teleport(loca);
         pp.getInventory().clear();
                    }   
              }
        pg.clear();
        pgt.clear();
        pgn.clear();
        kits1.clear();
        kits2.clear();
        kits3.clear();
        kits4.clear();
        stop =  scheduler1.scheduleSyncDelayedTask(plugin, new Runnable(){
        public void run(){ 
            pg.clear();
            pgt.clear();
            pgn.clear();
            kits1.clear();
        kits2.clear();
        kits3.clear();
        kits4.clear();
            Bukkit.getScheduler().cancelTask(stop);
              for (Player pp : Bukkit.getOnlinePlayers()){
                    pp.sendMessage("§b§l[§f§lSpleef§b§l] §f§lGanhador: " + mgp.get(50));
                    pp.sendMessage("§b§l[§f§lSpleef§b§l] §f§lEvento Acabou!!");
              }
            Bukkit.getScheduler().cancelTask(stop);
            mgp.clear();
        StartSpleef=false;
        }
        }, 1 * 30); 
        }
        else{
        }

        }

      //  if(pg.contains(event.getPlayer().getName())){
        
       // double y = event.getPlayer().getLocation().getY() -1;
       // World w = Bukkit.getWorld("evento");
       // Location loc = new Location(w, event.getPlayer().getLocation().getX(), y, event.getPlayer().getLocation().getBlockZ());  
       // }
    }
    }
    }
          
    }
    // evento termina aqui de move EVENTE do spleef

    @EventHandler
    public void Sair(PlayerQuitEvent e){
    if(StartSpleef==true){
              for (Player p : Bukkit.getOnlinePlayers()){
                    if(pg.contains(e.getPlayer().getName())){
                    pg.remove(e.getPlayer().getName());
                    pgt.put(5, pgt.get(5) - 1);
                    pgn.put(e.getPlayer().getName(), 0);
                    p.getInventory().clear();
                    }
              }
    }
    if(StartCorrida==true){
              for (Player p : Bukkit.getOnlinePlayers()){
                    if(pg.contains(e.getPlayer().getName())){
                    pg.remove(e.getPlayer().getName());
                    }
              }
    }
    }
    @EventHandler
    public void StartEvento(PlayerDeathEvent e){
    if(StartSpleef==true){
              for (Player p : Bukkit.getOnlinePlayers()){
                    if(pg.contains(e.getEntity().getName())){
                    pg.remove(e.getEntity().getName());
                    pgt.put(5, pgt.get(5) - 1);
                    pgn.put(e.getEntity().getName(), 0);
                    
                    p.sendMessage("§b§l[§f§lSpleef§b§l] §f§l"+ e.getEntity().getName() + " Caiu da neve!");
                    }   
              }
              World w = Bukkit.getWorld("evento");
              double x = Bukkit.getPluginManager().getPlugin("DarkEvento").getConfig().getDouble("evento.spawn.X");
              double ys = Bukkit.getPluginManager().getPlugin("DarkEvento").getConfig().getDouble("evento.spawn.Y");
              double z = Bukkit.getPluginManager().getPlugin("DarkEvento").getConfig().getDouble("evento.spawn.Z");
              float pa = Bukkit.getPluginManager().getPlugin("DarkEvento").getConfig().getInt("evento.spawn.P");
              float a = Bukkit.getPluginManager().getPlugin("DarkEvento").getConfig().getInt("evento.spawn.A");
              Location loca = new Location(w, x, ys, z, pa, a);
              e.getEntity().teleport(loca);    
              e.getEntity().getInventory().clear();
    }
    if(StartCorrida==true){    
         for(Player v : Bukkit.getOnlinePlayers()){
         if(pg.contains(e.getEntity().getName())){
         pg.remove(e.getEntity().getName());
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

@EventHandler
  public void ShopSpleef(PlayerInteractEvent e) {
    Player p = e.getPlayer();
    inv = sv.createInventory(null, 9, "§6Shop Spleef");
    ItemStack kit1 = new ItemStack(273);
    ItemMeta kit1Meta = kit1.getItemMeta();
    kit1Meta.setDisplayName("§bPa - Trapezio Descendente");
    kit1.setItemMeta(kit1Meta);
    
    ItemStack kit2 = new ItemStack(256);
    ItemMeta kit2Meta = kit2.getItemMeta();
    kit2Meta.setDisplayName("§bPa - Colossus");
    kit2.setItemMeta(kit2Meta);
    
    ItemStack kit3 = new ItemStack(284);
    ItemMeta kit3Meta = kit3.getItemMeta();
    kit3Meta.setDisplayName("§bPa - Ostentaçao");
    kit3.setItemMeta(kit3Meta);
    
    ItemStack kit4 = new ItemStack(277);
    ItemMeta kit4Meta = kit4.getItemMeta();
    kit4Meta.setDisplayName("§bPa - DimaCatra");
    kit4.setItemMeta(kit4Meta);
    
      
      inv.setItem(2, kit1);
      inv.setItem(3, kit2);
      inv.setItem(5, kit3);
      inv.setItem(6, kit4);
    if (((e.getAction().equals(Action.RIGHT_CLICK_AIR)) || (e.getAction().equals(Action.RIGHT_CLICK_BLOCK))) && 
      (p.getInventory().getItemInHand().getTypeId() == 54)) {
        if(p.getItemInHand().getItemMeta().getDisplayName() == "§4§lLoja Spleef") {      
          p.openInventory(inv);
      
        }
    }
    }
  
@EventHandler
  public void ProfileSpleef(PlayerInteractEvent e) {
    Player p = e.getPlayer();
    inv = sv.createInventory(null, 9, "§6Perfil Spleef");
    ItemStack kit1 = new ItemStack(116);
    ItemMeta kit1Meta = kit1.getItemMeta();
    kit1Meta.setDisplayName("§bPartidas Ganhas:");
    kit1.setItemMeta(kit1Meta);
    
    ItemStack kit2 = new ItemStack(138);
    ItemMeta kit2Meta = kit2.getItemMeta();
    kit2Meta.setDisplayName("§bPartidas Jogadas:");
    kit2.setItemMeta(kit2Meta);
    
    ItemStack kit3 = new ItemStack(152);
    ItemMeta kit3Meta = kit3.getItemMeta();
    kit3Meta.setDisplayName("§bConfigurações");
    kit3.setItemMeta(kit3Meta);
    
    SkullMeta  meta = (SkullMeta) Bukkit.getItemFactory().getItemMeta(Material.SKULL_ITEM);
    meta.setOwner(p.getName());
    meta.setDisplayName("§bPerfil");
    ItemStack kit4 = new ItemStack(Material.SKULL_ITEM,1 , (byte)3);
    kit4.setItemMeta(meta);
    
      
      inv.setItem(2, kit1);
      inv.setItem(3, kit2);
      inv.setItem(5, kit3);
      inv.setItem(6, kit4);
    if (((e.getAction().equals(Action.RIGHT_CLICK_AIR)) || (e.getAction().equals(Action.RIGHT_CLICK_BLOCK))) && 
      (p.getInventory().getItemInHand().getType() == Material.SKULL_ITEM)) {
        if(p.getItemInHand().getItemMeta().getDisplayName() == p.getName()) {      
          p.openInventory(inv);
      
        }
    }
    }
  
  @EventHandler
  public boolean EscolhendoKitSpleef(InventoryClickEvent event) {
      if(event.getCurrentItem() != null){
            if(event.getCurrentItem().getType() != Material.AIR){
                if(event.getCurrentItem().hasItemMeta()){
    Player player = (Player)event.getWhoClicked(); 
            double p1 = plugin.economy.getBalance(player.getName());
        int kit1 = (int) (p1 - 1500);
        int kit2 = (int) (p1 - 2500);
        int kit3 = (int) (p1 - 3500);
        int kit4 = (int) (p1 - 6000);
    //kit 1
    if ((event.getCurrentItem().getItemMeta().getDisplayName().contains("§bPa - Trapezio Descendente"))) {
    if(kit1 < 0){
        player.sendMessage(ChatColor.RED + "§0§l[§6§lShop§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
        return true;
    }
    if(kits1.contains(player.getName())){
       player.sendMessage("§0§l[§6§lShop§0§l] "+ ChatColor.WHITE +"voce ja escolheu um kit!");
    }
    if(kits2.contains(player.getName())){
       player.sendMessage("§0§l[§6§lShop§0§l] "+ ChatColor.WHITE +"voce ja escolheu um kit!");
    }
    if(kits3.contains(player.getName())){
       player.sendMessage("§0§l[§6§lShop§0§l] "+ ChatColor.WHITE +"voce ja escolheu um kit!");
    }
    if(kits4.contains(player.getName())){
       player.sendMessage("§0§l[§6§lShop§0§l] "+ ChatColor.WHITE +"voce ja escolheu um kit!");
    }
    else{
        plugin.economy.withdrawPlayer(player.getName(), kit1);
        kits1.add(player.getName());
    }
    player.playSound(player.getLocation(), Sound.ENDERMAN_DEATH, 5.0F, 1.0F); 
    player.closeInventory();
    }
    //kit 2
    if ((event.getCurrentItem().getItemMeta().getDisplayName().contains("§bPa - Colossus"))) {
    if(kit2 < 0){
        player.sendMessage(ChatColor.RED + "§0§l[§6§lShop§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
        return true;
    }
    if(kits1.contains(player.getName())){
       player.sendMessage("§0§l[§6§lShop§0§l] "+ ChatColor.WHITE +"voce ja escolheu um kit!");
    }
    if(kits2.contains(player.getName())){
       player.sendMessage("§0§l[§6§lShop§0§l] "+ ChatColor.WHITE +"voce ja escolheu um kit!");
    }
    if(kits3.contains(player.getName())){
       player.sendMessage("§0§l[§6§lShop§0§l] "+ ChatColor.WHITE +"voce ja escolheu um kit!");
    }
    if(kits4.contains(player.getName())){
       player.sendMessage("§0§l[§6§lShop§0§l] "+ ChatColor.WHITE +"voce ja escolheu um kit!");
    }
    else{
        plugin.economy.withdrawPlayer(player.getName(), kit2);
        kits2.add(player.getName());
    }
    player.playSound(player.getLocation(), Sound.ENDERMAN_DEATH, 5.0F, 1.0F); 
    player.closeInventory();
    }
    //kit 3
    if ((event.getCurrentItem().getItemMeta().getDisplayName().contains("§bPa - Ostentaçao"))) {
    if(kits1.contains(player.getName())){
       player.sendMessage("§0§l[§6§lShop§0§l] "+ ChatColor.WHITE +"voce ja escolheu um kit!");
    }
    if(kits2.contains(player.getName())){
       player.sendMessage("§0§l[§6§lShop§0§l] "+ ChatColor.WHITE +"voce ja escolheu um kit!");
    }
    if(kits3.contains(player.getName())){
       player.sendMessage("§0§l[§6§lShop§0§l] "+ ChatColor.WHITE +"voce ja escolheu um kit!");
    }
    if(kits4.contains(player.getName())){
       player.sendMessage("§0§l[§6§lShop§0§l] "+ ChatColor.WHITE +"voce ja escolheu um kit!");
    }
    if(kit3 < 0){
        player.sendMessage(ChatColor.RED + "§0§l[§6§lShop§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
        return true;
    }
    else{
        plugin.economy.withdrawPlayer(player.getName(), kit3);
        kits3.add(player.getName());
    }
    player.playSound(player.getLocation(), Sound.ENDERMAN_DEATH, 5.0F, 1.0F); 
    player.closeInventory();
    }
    //kit 4
    if ((event.getCurrentItem().getItemMeta().getDisplayName().contains("§bPa - DimaCatra"))) {
    if(kits1.contains(player.getName())){
       player.sendMessage("§0§l[§6§lShop§0§l] "+ ChatColor.WHITE +"voce ja escolheu um kit!");
    }
    if(kits2.contains(player.getName())){
       player.sendMessage("§0§l[§6§lShop§0§l] "+ ChatColor.WHITE +"voce ja escolheu um kit!");
    }
    if(kits3.contains(player.getName())){
       player.sendMessage("§0§l[§6§lShop§0§l] "+ ChatColor.WHITE +"voce ja escolheu um kit!");
    }
    if(kits4.contains(player.getName())){
       player.sendMessage("§0§l[§6§lShop§0§l] "+ ChatColor.WHITE +"voce ja escolheu um kit!");
    }
    if(kit4 < 0){
        player.sendMessage(ChatColor.RED + "§0§l[§6§lShop§0§l] "+ ChatColor.WHITE +"voce nao tem money!");
        return true;
    }
    else{
        plugin.economy.withdrawPlayer(player.getName(), kit4);
        kits4.add(player.getName());
    }
    player.playSound(player.getLocation(), Sound.ENDERMAN_DEATH, 5.0F, 1.0F); 
    player.closeInventory();
    }



}
}
}
      return true;
}
}
  



