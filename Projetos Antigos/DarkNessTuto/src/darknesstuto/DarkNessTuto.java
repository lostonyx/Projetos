/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package darknesstuto;

import Comandos.SetarMob;
import java.util.ArrayList;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

/**
 *
 * @author WillianDev
 */
public class DarkNessTuto extends JavaPlugin implements Listener{
  public static Economy economy = null; 
  Double money = 1000.0;
  Server sv = Bukkit.getServer();
  public Inventory inv = null;
  public static int delay;
  public static int regras_1;
  public static int regras_2;
  public static int regras_3;  
  public static int regras_4; 
  public static int regras_5;
  public static int warps_1;
  public static int warps_2;
  public static int warps_3;
  public static int warps_4;
  public static int warps_5;
  public static int warps_6;
  public static int warps_7;
  public static int warps_8;
  public static int warps_9; 
  public static int ranks_1; 
  public static int ranks_2;
  public static int ranks_3; 
  public static int ranks_4; 
  public static int inicio;
  public ArrayList<String> PlayerTuto = new ArrayList<String>();
  public ArrayList<String> AntDupe = new ArrayList<String>();
  @EventHandler
  public void Regras(PlayerInteractEntityEvent e) {
      BukkitScheduler scheduler1 = getServer().getScheduler();
      final Player p = e.getPlayer();
      if(e.getRightClicked() instanceof Villager) {
        Villager a = (Villager)e.getRightClicked();
        if(a.getCustomName().equals("§f[§6§lTutorial§f]Regras")) {
        e.setCancelled(true);
        if(AntDupe.contains(p.getName())){  
        }
        AntDupe.add(p.getName());
        if(getConfig().getString(p.getName()+".regras") == null){
        regras_1 = scheduler1.scheduleSyncDelayedTask(this, new Runnable(){
        public void run(){ 
         double x = 17.300;
         double y = 102.20000;
         double z = 18.300; 
         World w = p.getWorld();
         Location loc = new Location(w, x, y, z, -47, 40);
         for(Player v : Bukkit.getOnlinePlayers()){
         v.hidePlayer(p);
         }
         PlayerTuto.add(p.getName());
         p.teleport(loc);
         p.playSound(p.getLocation(), Sound.CLICK, 5.0f, 1.0f);
             p.sendMessage("§a             ");
             p.sendMessage("§aOlá, §c"+p.getName()+" §aEsse é o spawn de novato.");
             p.sendMessage("§aprovavelmente e a primeira vez que voce entrou em nosso servidor!");
             p.sendMessage("§aE seja Bem-Vindo(a) :D");             
             p.sendMessage("§a             ");
        }      
        }, 1 * 20);
        regras_3 = scheduler1.scheduleSyncDelayedTask(this, new Runnable(){
        public void run(){
         double x = 49481.506;
         double y = 85;
         double z = 50430.153; 
         World w = p.getWorld();
         Location loc = new Location(w, x, y, z, -179, (float) 1.8);
         for(Player v : Bukkit.getOnlinePlayers()){
         v.hidePlayer(p);
         }
         p.teleport(loc);
         p.playSound(p.getLocation(), Sound.CLICK, 5.0f, 1.0f);
             p.sendMessage("§a             ");
             p.sendMessage("§aAqui são as Regras do servidor, Leia com Atenção!");          
             p.sendMessage("§a             ");
        }      
        }, 32 * 20);
        regras_4 = scheduler1.scheduleSyncDelayedTask(this, new Runnable(){
        public void run(){ 
         double x = 49481.507;
         double y = 85;
         double z = 50432.285; 
         World w = p.getWorld();
         Location loc = new Location(w, x, y, z, 0, 2);
         for(Player v : Bukkit.getOnlinePlayers()){
         v.hidePlayer(p);
         }
         p.teleport(loc);
         p.playSound(p.getLocation(), Sound.CLICK, 5.0f, 1.0f);
             p.sendMessage("§a             ");
             p.sendMessage("§aAqui são as Regras do servidor, Leia com Atenção!");          
             p.sendMessage("§a             ");
        }      
        }, 48 * 20);
        regras_5 = scheduler1.scheduleSyncDelayedTask(this, new Runnable(){
        public void run(){
         double x = 49482.353;
         double y = 85;
         double z = 50431.529; 
         World w = p.getWorld();
         Location loc = new Location(w, x, y, z, -91, 2);
         for(Player v : Bukkit.getOnlinePlayers()){
         v.hidePlayer(p);
         }
         p.teleport(loc);
         p.playSound(p.getLocation(), Sound.CLICK, 5.0f, 1.0f);
             p.sendMessage("§a             ");
             p.sendMessage("§aAqui são as Regras do servidor, Leia com Atenção!");          
             p.sendMessage("§a             ");
        }      
        }, 48 * 20);
        inicio = scheduler1.scheduleSyncDelayedTask(this, new Runnable(){
        public void run(){
         PlayerTuto.remove(p.getName());
         double x = 30.500;
         double y = 89;
         double z = 30.529; 
         World w = p.getWorld();
         Location loc = new Location(w, x, y, z, 90, 4);
         for(Player v : Bukkit.getOnlinePlayers()){
         v.showPlayer(p);
         }
         p.teleport(loc);
         p.playSound(p.getLocation(), Sound.ENDERDRAGON_GROWL, 5.0f, 1.0f);
             p.sendMessage("§a             ");
             p.sendMessage("§aParabens §c"+ p.getName()+"§a voce concluio o tutorial de regras!");          
             p.sendMessage("§a             ");
             getConfig().set(p.getName()+".regras", "1");          
             saveConfig();  
             PlayerTuto.remove(p.getName());
             PlayerTuto.clear();
             AntDupe.remove(p.getName());
        }      
        }, 64 * 20);
          }
        else{
            p.sendMessage("§f[§6§lTutorial§f]Voce já completou esse tutorial!!");
        }     
        }
      }
  }
  @EventHandler
  public void Warps(PlayerInteractEntityEvent e) {
      BukkitScheduler scheduler1 = getServer().getScheduler();
      final Player p = e.getPlayer();
      if(e.getRightClicked() instanceof Villager) {
        Villager a = (Villager)e.getRightClicked();
        if(a.getCustomName().equals("§f[§6§lTutorial§f]Warps")) {
        e.setCancelled(true);
        if(AntDupe.contains(p.getName())){  
        }
        AntDupe.add(p.getName());
        if(getConfig().getString(p.getName()+".warps") == null){
        warps_1 = scheduler1.scheduleSyncDelayedTask(this, new Runnable(){
        public void run(){
         double x = 50000.500;
         double y = 64.00000;
         double z = 50000.500; 
         World w = p.getWorld();
         Location loc = new Location(w, x, y, z, 90, 3);
         for(Player v : Bukkit.getOnlinePlayers()){
         v.hidePlayer(p);
         }
         PlayerTuto.add(p.getName());
         p.teleport(loc);
         p.playSound(p.getLocation(), Sound.NOTE_PIANO, 5.0f, 1.0f);
             p.sendMessage("§a             ");
             p.sendMessage("§aOlá, §c"+p.getName()+" §aEsse é o spawn.");
             p.sendMessage("§aprovavelmente e a primeira vez que voce entrou em nosso servidor!");
             p.sendMessage("§aE seja Bem-Vindo(a) :D");             
             p.sendMessage("§a             ");
        }      
        }, 1 * 20);
        warps_2 = scheduler1.scheduleSyncDelayedTask(this, new Runnable(){
        public void run(){
         double x = 49481.505;
         double y = 61;
         double z = 49711.447; 
         World w = p.getWorld();
         Location loc = new Location(w, x, y, z, -90, 3);
         for(Player v : Bukkit.getOnlinePlayers()){
         v.hidePlayer(p);
         }
         p.teleport(loc);
         p.playSound(p.getLocation(), Sound.NOTE_PIANO, 5.0f, 1.0f);
             p.sendMessage("§a             ");
             p.sendMessage("§afps!");          
             p.sendMessage("§a             ");
        }      
        }, 16 * 20);
        warps_3 = scheduler1.scheduleSyncDelayedTask(this, new Runnable(){
        public void run(){
         double x = 58852.634;
         double y = 154.00000;
         double z = 62436.700; 
         World w = p.getWorld();
         Location loc = new Location(w, x, y, z, 179, 90);
         for(Player v : Bukkit.getOnlinePlayers()){
         v.hidePlayer(p);
         }
         p.teleport(loc);
         p.playSound(p.getLocation(), Sound.NOTE_PIANO, 5.0f, 1.0f);
             p.sendMessage("§a             ");
             p.sendMessage("§alenhar!");          
             p.sendMessage("§a             ");
        }      
        }, 32 * 20);
        warps_4 = scheduler1.scheduleSyncDelayedTask(this, new Runnable(){
        public void run(){
         double x = 97.516;
         double y = 124.00000;
         double z = -217.527;
         World w = Bukkit.getWorld("plotworld");
         Location loc = new Location(w, x, y, z, -90, 11);
         for(Player v : Bukkit.getOnlinePlayers()){
         v.hidePlayer(p);
         }
         p.teleport(loc);
         p.playSound(p.getLocation(), Sound.NOTE_PIANO, 5.0f, 1.0f);
             p.sendMessage("§a             ");
             p.sendMessage("§aplot!");          
             p.sendMessage("§a             ");
        }      
        }, 48 * 20);
        warps_5 = scheduler1.scheduleSyncDelayedTask(this, new Runnable(){
        public void run(){
         double x = 47642.301;
         double y = 69;
         double z = 49506.587; 
         World w = Bukkit.getWorld("world");
         Location loc = new Location(w, x, y, z, -90, 7);
         for(Player v : Bukkit.getOnlinePlayers()){
         v.hidePlayer(p);
         }
         p.teleport(loc);
         p.playSound(p.getLocation(), Sound.NOTE_PIANO, 5.0f, 1.0f);
             p.sendMessage("§a             ");
             p.sendMessage("§aMinaPvP -, MinaPvp +, MinaPvPFree!");          
             p.sendMessage("§a             ");
        }      
        }, 64 * 20);
        warps_6 = scheduler1.scheduleSyncDelayedTask(this, new Runnable(){
        public void run(){
         double x = 48465.439;
         double y = 126.00000;
         double z = 50633.300; 
         World w = Bukkit.getWorld("world");
         Location loc = new Location(w, x, y, z, 0, 19);
         for(Player v : Bukkit.getOnlinePlayers()){
         v.hidePlayer(p);
         }
         p.teleport(loc);
         p.playSound(p.getLocation(), Sound.NOTE_PIANO, 5.0f, 1.0f);
             p.sendMessage("§a             ");
             p.sendMessage("§ax1!");          
             p.sendMessage("§a             ");
        }      
        }, 80 * 20);
        warps_7 = scheduler1.scheduleSyncDelayedTask(this, new Runnable(){
        public void run(){
         double x = 59206.700;
         double y = 109;
         double z = 60552.456; 
         World w = Bukkit.getWorld("world");
         Location loc = new Location(w, x, y, z, 90, 28);
         for(Player v : Bukkit.getOnlinePlayers()){
         v.hidePlayer(p);
         }
         p.teleport(loc);
         p.playSound(p.getLocation(), Sound.NOTE_PIANO, 5.0f, 1.0f);
             p.sendMessage("§a             ");
             p.sendMessage("§aArena!");          
             p.sendMessage("§a             ");
        }      
        }, 80 * 20);
        warps_8 = scheduler1.scheduleSyncDelayedTask(this, new Runnable(){
        public void run(){
         double x = -194.341;
         double y = 8.00000;
         double z = 246.300; 
         World w = Bukkit.getWorld("Builder");
         Location loc = new Location(w, x, y, z, -3, 29);
         for(Player v : Bukkit.getOnlinePlayers()){
         v.hidePlayer(p);
         }
         p.teleport(loc);
         p.playSound(p.getLocation(), Sound.NOTE_PIANO, 5.0f, 1.0f);
             p.sendMessage("§a             ");
             p.sendMessage("§aLoja!");          
             p.sendMessage("§a             ");
        }      
        }, 96 * 20);
        warps_9 = scheduler1.scheduleSyncDelayedTask(this, new Runnable(){
        public void run(){
         double x = 49709.300;
         double y = 88.00000;
         double z = 50244.700; 
         World w = Bukkit.getWorld("world");
         Location loc = new Location(w, x, y, z, -135, 30);
         for(Player v : Bukkit.getOnlinePlayers()){
         v.hidePlayer(p);
         }
         p.teleport(loc);
         p.playSound(p.getLocation(), Sound.NOTE_PIANO, 5.0f, 1.0f);
             p.sendMessage("§a             ");
             p.sendMessage("§aEncantar & Bigorna!");          
             p.sendMessage("§a             ");
        }      
        }, 112 * 20);
        inicio = scheduler1.scheduleSyncDelayedTask(this, new Runnable(){ 
        public void run(){
         PlayerTuto.remove(p.getName());
         double x = 30.500;
         double y = 89;
         double z = 30.529; 
         World w = Bukkit.getWorld("world");
         Location loc = new Location(w, x, y, z, 90, 4);
         for(Player v : Bukkit.getOnlinePlayers()){
         v.showPlayer(p);
         }
         p.teleport(loc);
         p.playSound(p.getLocation(), Sound.ENDERDRAGON_GROWL, 5.0f, 1.0f);
             p.sendMessage("§a             ");
             p.sendMessage("§aParabens §c"+ p.getName()+"§a voce concluio o tutorial de warps!");          
             p.sendMessage("§a             ");
             getConfig().set(p.getName()+".warps", "1");          
             saveConfig();  
             PlayerTuto.remove(p.getName());
             PlayerTuto.clear();
             AntDupe.remove(p.getName());
        }      
        }, 128 * 20);
          }
        else{
            p.sendMessage("§f[§6§lTutorial§f]Voce já completou esse tutorial!!");
        }     
        }
      }
  }
  @EventHandler
  public void Ranks(PlayerInteractEntityEvent e) {
      BukkitScheduler scheduler1 = getServer().getScheduler();
      final Player p = e.getPlayer();
      if(e.getRightClicked() instanceof Villager) {
        Villager a = (Villager)e.getRightClicked();
        if(a.getCustomName().equals("§f[§6§lTutorial§f]Ranks")) {
        e.setCancelled(true);
        if(AntDupe.contains(p.getName())){  
        }
        AntDupe.add(p.getName());
        if(getConfig().getString(p.getName()+".ranks") == null){
        ranks_1 = scheduler1.scheduleSyncDelayedTask(this, new Runnable(){
        public void run(){
         double x = 17.300;
         double y = 102.20000;
         double z = 18.300; 
         World w = p.getWorld();
         Location loc = new Location(w, x, y, z, 179, 4);
         for(Player v : Bukkit.getOnlinePlayers()){
         v.hidePlayer(p);
         }
         PlayerTuto.add(p.getName());
         p.teleport(loc);
         p.playSound(p.getLocation(), Sound.CLICK, 5.0f, 1.0f);
             p.sendMessage("§a             ");
             p.sendMessage("§aOlá, §c"+p.getName()+" §aNesse tutorial amostrarei os ranks & seus kits.");
             p.sendMessage("§aprovavelmente e a primeira vez que voce entrou em nosso servidor!");
             p.sendMessage("§aE seja Bem-Vindo(a) :D");             
             p.sendMessage("§a             ");
        }      
        }, 0 * 20);
        ranks_1 = scheduler1.scheduleSyncDelayedTask(this, new Runnable(){
        public void run(){
         double x = -20.253895372573314;
         double y = 15.0;
         double z = -7.505406162990324;
         double yaw = 90.76259;
         double pitch = -2.3010778;
         World w = Bukkit.getWorld("dk");
         Location loc = new Location(w, x, y, z, (float) yaw, (float) pitch);
         p.teleport(loc);
         p.playSound(p.getLocation(), Sound.CLICK, 5.0f, 1.0f);
             p.sendMessage("§a             ");
             p.sendMessage("§aRank Novato");
             p.sendMessage("§aAmostrando kit do novado em 5 segundo!");            
             p.sendMessage("§a             ");
        }      
        }, 5 * 20);
        ranks_1 = scheduler1.scheduleSyncDelayedTask(this, new Runnable(){
        public void run(){
         inv = sv.createInventory(null, 9, "§6Novato");
         ItemStack i1 = new ItemStack(364);
         ItemMeta i1meta = i1.getItemMeta();
         i1meta.setDisplayName("§a§k§l0§1§lKit-novato§a§k§l0");
         i1.setItemMeta(i1meta);
         ItemStack i2 = new ItemStack(284);
         ItemMeta i2meta = i2.getItemMeta();
         i2meta.setDisplayName("§a§k§l0§1§lKit-novato§a§k§l0");
         i2.setItemMeta(i2meta);
         //i2.addEnchantment(Enchantment., 2);
         inv.setItem(0, i1);
         inv.setItem(1, i2);
         p.openInventory(inv);
         p.playSound(p.getLocation(), Sound.CLICK, 5.0f, 1.0f);
        }      
        }, 10 * 20);
        ranks_1 = scheduler1.scheduleSyncDelayedTask(this, new Runnable(){
        public void run(){
         p.closeInventory();
         double x = -20.37315843020673;
         double y = 15.0;
         double z = -17.452824070449847;
         double yaw = 90.76259;
         double pitch = -2.3010778;
         World w = Bukkit.getWorld("dk");
         Location loc = new Location(w, x, y, z, (float) yaw, (float) pitch);
         p.teleport(loc);
         p.playSound(p.getLocation(), Sound.CLICK, 5.0f, 1.0f);
             p.sendMessage("§a             ");
             p.sendMessage("§aRank Terra");
             p.sendMessage("§aAmostrando kits do terra em 5 segundo!");            
             p.sendMessage("§a             ");
        }      
        }, 15 * 20);
        ranks_1 = scheduler1.scheduleSyncDelayedTask(this, new Runnable(){
        public void run(){
         inv = sv.createInventory(null, 27, "§6Terra");
         ItemStack i1 = new ItemStack(268);
         ItemMeta i1meta = i1.getItemMeta();
         i1meta.setDisplayName("§4§lKit Terra III");
         i1.setItemMeta(i1meta);
         
         ItemStack i2 = new ItemStack(261);
         ItemMeta i2meta = i2.getItemMeta();
         i2meta.setDisplayName("§4§lKit Terra III");
         i2.setItemMeta(i2meta);
         
         ItemStack i3 = new ItemStack(262);
         ItemMeta i3meta = i3.getItemMeta();
         i3meta.setDisplayName("§4§lKit Terra III");
         i3.setItemMeta(i3meta);
         
         ItemStack i4 = new ItemStack(298);
         ItemMeta i4meta = i4.getItemMeta();
         i4meta.setDisplayName("§4§lKit Terra III");
         i4.setItemMeta(i4meta);  
         
         ItemStack i5 = new ItemStack(299);
         ItemMeta i5meta = i5.getItemMeta();
         i5meta.setDisplayName("§4§lKit Terra III");
         i5.setItemMeta(i5meta);  
         
         ItemStack i6 = new ItemStack(300);
         ItemMeta i6meta = i6.getItemMeta();
         i6meta.setDisplayName("§4§lKit Terra III");
         i6.setItemMeta(i6meta);
         
         ItemStack i7 = new ItemStack(301);
         ItemMeta i7meta = i7.getItemMeta();
         i7meta.setDisplayName("§4§lKit Terra III");
         i7.setItemMeta(i7meta);
         
         ItemStack i8 = new ItemStack(364);
         ItemMeta i8meta = i8.getItemMeta();
         i8meta.setDisplayName("§4§lKit Terra III");
         i8.setItemMeta(i8meta);
         
         ItemStack i9 = new ItemStack(273);
         ItemMeta i9meta = i9.getItemMeta();
         i9meta.setDisplayName("§4§lKit Terra III");
         i9.setItemMeta(i9meta);  
         
         ItemStack i10 = new ItemStack(268);
         ItemMeta i10meta = i10.getItemMeta();
         i10meta.setDisplayName("§4§lKit Terra II");
         i10.setItemMeta(i10meta);
         
         ItemStack i11 = new ItemStack(261);
         ItemMeta i11meta = i11.getItemMeta();
         i11meta.setDisplayName("§4§lKit Terra II");
         i11.setItemMeta(i11meta);
         
         ItemStack i12 = new ItemStack(262);
         ItemMeta i12meta = i12.getItemMeta();
         i12meta.setDisplayName("§4§lKit Terra II");
         i12.setItemMeta(i12meta);
         
         ItemStack i13 = new ItemStack(298);
         ItemMeta i13meta = i13.getItemMeta();
         i13meta.setDisplayName("§4§lKit Terra II");
         i13.setItemMeta(i13meta);  
         
         ItemStack i14 = new ItemStack(299);
         ItemMeta i14meta = i14.getItemMeta();
         i14meta.setDisplayName("§4§lKit Terra II");
         i14.setItemMeta(i14meta);  
         
         ItemStack i15 = new ItemStack(300);
         ItemMeta i15meta = i15.getItemMeta();
         i15meta.setDisplayName("§4§lKit Terra II");
         i15.setItemMeta(i15meta);
         
         ItemStack i16 = new ItemStack(301);
         ItemMeta i16meta = i16.getItemMeta();
         i16meta.setDisplayName("§4§lKit Terra II");
         i16.setItemMeta(i16meta);
         
         ItemStack i17 = new ItemStack(364);
         ItemMeta i17meta = i17.getItemMeta();
         i17meta.setDisplayName("§4§lKit Terra II");
         i17.setItemMeta(i17meta);
         
         ItemStack i18 = new ItemStack(273);
         ItemMeta i18meta = i18.getItemMeta();
         i18meta.setDisplayName("§4§lKit Terra II");
         i18.setItemMeta(i18meta);  
         
         ItemStack i20 = new ItemStack(268);
         ItemMeta i20meta = i20.getItemMeta();
         i20meta.setDisplayName("§4§lKit Terra I");
         i20.setItemMeta(i20meta);
         
         ItemStack i21 = new ItemStack(261);
         ItemMeta i21meta = i21.getItemMeta();
         i21meta.setDisplayName("§4§lKit Terra I");
         i21.setItemMeta(i21meta);
         
         ItemStack i22 = new ItemStack(262);
         ItemMeta i22meta = i22.getItemMeta();
         i22meta.setDisplayName("§4§lKit Terra I");
         i22.setItemMeta(i22meta);
         
         ItemStack i23 = new ItemStack(298);
         ItemMeta i23meta = i23.getItemMeta();
         i23meta.setDisplayName("§4§lKit Terra I");
         i23.setItemMeta(i23meta);  
         
         ItemStack i24 = new ItemStack(299);
         ItemMeta i24meta = i24.getItemMeta();
         i24meta.setDisplayName("§4§lKit Terra I");
         i24.setItemMeta(i24meta);  
         
         ItemStack i25 = new ItemStack(300);
         ItemMeta i25meta = i25.getItemMeta();
         i25meta.setDisplayName("§4§lKit Terra I");
         i25.setItemMeta(i25meta);
         
         ItemStack i26 = new ItemStack(301);
         ItemMeta i26meta = i26.getItemMeta();
         i26meta.setDisplayName("§4§lKit Terra I");
         i26.setItemMeta(i26meta);
         
         ItemStack i27 = new ItemStack(364);
         ItemMeta i27meta = i27.getItemMeta();
         i27meta.setDisplayName("§4§lKit Terra I");
         i27.setItemMeta(i27meta);
         
         ItemStack i28 = new ItemStack(273);
         ItemMeta i28meta = i28.getItemMeta();
         i28meta.setDisplayName("§4§lKit Terra I");
         i28.setItemMeta(i28meta); 
         //i2.addEnchantment(Enchantment., 2);
         inv.setItem(0, i1);
         inv.setItem(1, i2);
         inv.setItem(2, i3);
         inv.setItem(3, i4);
         inv.setItem(4, i5);
         inv.setItem(5, i6);
         inv.setItem(6, i7);
         inv.setItem(7, i8);
         inv.setItem(8, i9);
         inv.setItem(9, i10);
         inv.setItem(10, i11);
         inv.setItem(11, i12);
         inv.setItem(12, i13);
         inv.setItem(13, i14);
         inv.setItem(14, i15);
         inv.setItem(15, i16);
         inv.setItem(16, i17);
         inv.setItem(17, i18);
         inv.setItem(18, i20);
         inv.setItem(19, i21);
         inv.setItem(20, i22);
         inv.setItem(21, i23);
         inv.setItem(22, i24);
         inv.setItem(23, i25);
         inv.setItem(24, i26);
         inv.setItem(25, i27);
         inv.setItem(26, i28);
         p.openInventory(inv);
         p.playSound(p.getLocation(), Sound.CLICK, 5.0f, 1.0f);
        }      
        }, 20 * 20);        
        ranks_1 = scheduler1.scheduleSyncDelayedTask(this, new Runnable(){
        public void run(){
         p.closeInventory();
         double x = -20.9573981451369;
         double y = 15.0;
         double z = -27.459159152262732;
         double yaw = 90.76259;
         double pitch = -2.3010778;
         World w = Bukkit.getWorld("dk");
         Location loc = new Location(w, x, y, z, (float) yaw, (float) pitch);
         p.teleport(loc);
         p.playSound(p.getLocation(), Sound.CLICK, 5.0f, 1.0f);
             p.sendMessage("§a             ");
             p.sendMessage("§aRank Areia");
             p.sendMessage("§aAmostrando kits da areia em 5 segundo!");            
             p.sendMessage("§a             ");
        }      
        }, 25 * 20);
        ranks_1 = scheduler1.scheduleSyncDelayedTask(this, new Runnable(){
        public void run(){
         inv = sv.createInventory(null, 27, "§6Areia");
         ItemStack i1 = new ItemStack(283);
         ItemMeta i1meta = i1.getItemMeta();
         i1meta.setDisplayName("§4§lKit Areia III");
         i1.setItemMeta(i1meta);
         
         ItemStack i2 = new ItemStack(261);
         ItemMeta i2meta = i2.getItemMeta();
         i2meta.setDisplayName("§4§lKit Areia III");
         i2.setItemMeta(i2meta);
         
         ItemStack i3 = new ItemStack(262);
         ItemMeta i3meta = i3.getItemMeta();
         i3meta.setDisplayName("§4§lKit Areia III");
         i3.setItemMeta(i3meta);
         
         ItemStack i4 = new ItemStack(314);
         ItemMeta i4meta = i4.getItemMeta();
         i4meta.setDisplayName("§4§lKit Areia III");
         i4.setItemMeta(i4meta);  
         
         ItemStack i5 = new ItemStack(315);
         ItemMeta i5meta = i5.getItemMeta();
         i5meta.setDisplayName("§4§lKit Areia III");
         i5.setItemMeta(i5meta);  
         
         ItemStack i6 = new ItemStack(316);
         ItemMeta i6meta = i6.getItemMeta();
         i6meta.setDisplayName("§4§lKit Areia III");
         i6.setItemMeta(i6meta);
         
         ItemStack i7 = new ItemStack(317);
         ItemMeta i7meta = i7.getItemMeta();
         i7meta.setDisplayName("§4§lKit Areia III");
         i7.setItemMeta(i7meta);
         
         ItemStack i8 = new ItemStack(284);
         ItemMeta i8meta = i8.getItemMeta();
         i8meta.setDisplayName("§4§lKit Areia III");
         i8.setItemMeta(i8meta);
         
         ItemStack i9 = new ItemStack(320);
         ItemMeta i9meta = i9.getItemMeta();
         i9meta.setDisplayName("§4§lKit Areia III");
         i9.setItemMeta(i9meta);  
         
         ItemStack i10 = new ItemStack(283);
         ItemMeta i10meta = i10.getItemMeta();
         i10meta.setDisplayName("§4§lKit Areia II");
         i10.setItemMeta(i10meta);
         
         ItemStack i11 = new ItemStack(261);
         ItemMeta i11meta = i11.getItemMeta();
         i11meta.setDisplayName("§4§lKit Areia II");
         i11.setItemMeta(i11meta);
         
         ItemStack i12 = new ItemStack(262);
         ItemMeta i12meta = i12.getItemMeta();
         i12meta.setDisplayName("§4§lKit Areia II");
         i12.setItemMeta(i12meta);
         
         ItemStack i13 = new ItemStack(314);
         ItemMeta i13meta = i13.getItemMeta();
         i13meta.setDisplayName("§4§lKit Areia II");
         i13.setItemMeta(i13meta);  
         
         ItemStack i14 = new ItemStack(315);
         ItemMeta i14meta = i14.getItemMeta();
         i14meta.setDisplayName("§4§lKit Areia II");
         i14.setItemMeta(i14meta);  
         
         ItemStack i15 = new ItemStack(316);
         ItemMeta i15meta = i15.getItemMeta();
         i15meta.setDisplayName("§4§lKit Areia II");
         i15.setItemMeta(i15meta);
         
         ItemStack i16 = new ItemStack(317);
         ItemMeta i16meta = i16.getItemMeta();
         i16meta.setDisplayName("§4§lKit Areia II");
         i16.setItemMeta(i16meta);
         
         ItemStack i17 = new ItemStack(284);
         ItemMeta i17meta = i17.getItemMeta();
         i17meta.setDisplayName("§4§lKit Areia II");
         i17.setItemMeta(i17meta);
         
         ItemStack i18 = new ItemStack(320);
         ItemMeta i18meta = i18.getItemMeta();
         i18meta.setDisplayName("§4§lKit Areia II");
         i18.setItemMeta(i18meta);  
         
         ItemStack i20 = new ItemStack(283);
         ItemMeta i20meta = i20.getItemMeta();
         i20meta.setDisplayName("§4§lKit Areia I");
         i20.setItemMeta(i20meta);
         
         ItemStack i21 = new ItemStack(261);
         ItemMeta i21meta = i21.getItemMeta();
         i21meta.setDisplayName("§4§lKit Areia I");
         i21.setItemMeta(i21meta);
         
         ItemStack i22 = new ItemStack(262);
         ItemMeta i22meta = i22.getItemMeta();
         i22meta.setDisplayName("§4§lKit Areia I");
         i22.setItemMeta(i22meta);
         
         ItemStack i23 = new ItemStack(314);
         ItemMeta i23meta = i23.getItemMeta();
         i23meta.setDisplayName("§4§lKit Areia I");
         i23.setItemMeta(i23meta);  
         
         ItemStack i24 = new ItemStack(315);
         ItemMeta i24meta = i24.getItemMeta();
         i24meta.setDisplayName("§4§lKit Areia I");
         i24.setItemMeta(i24meta);  
         
         ItemStack i25 = new ItemStack(316);
         ItemMeta i25meta = i25.getItemMeta();
         i25meta.setDisplayName("§4§lKit Areia I");
         i25.setItemMeta(i25meta);
         
         ItemStack i26 = new ItemStack(317);
         ItemMeta i26meta = i26.getItemMeta();
         i26meta.setDisplayName("§4§lKit Areia I");
         i26.setItemMeta(i26meta);
         
         ItemStack i27 = new ItemStack(284);
         ItemMeta i27meta = i27.getItemMeta();
         i27meta.setDisplayName("§4§lKit Areia I");
         i27.setItemMeta(i27meta);
         
         ItemStack i28 = new ItemStack(320);
         ItemMeta i28meta = i28.getItemMeta();
         i28meta.setDisplayName("§4§lKit Areia I");
         i28.setItemMeta(i28meta); 
         //i2.addEnchantment(Enchantment., 2);
         inv.setItem(0, i1);
         inv.setItem(1, i2);
         inv.setItem(2, i3);
         inv.setItem(3, i4);
         inv.setItem(4, i5);
         inv.setItem(5, i6);
         inv.setItem(6, i7);
         inv.setItem(7, i8);
         inv.setItem(8, i9);
         inv.setItem(9, i10);
         inv.setItem(10, i11);
         inv.setItem(11, i12);
         inv.setItem(12, i13);
         inv.setItem(13, i14);
         inv.setItem(14, i15);
         inv.setItem(15, i16);
         inv.setItem(16, i17);
         inv.setItem(17, i18);
         inv.setItem(18, i20);
         inv.setItem(19, i21);
         inv.setItem(20, i22);
         inv.setItem(21, i23);
         inv.setItem(22, i24);
         inv.setItem(23, i25);
         inv.setItem(24, i26);
         inv.setItem(25, i27);
         inv.setItem(26, i28);
         p.openInventory(inv);
         p.playSound(p.getLocation(), Sound.CLICK, 5.0f, 1.0f);
        }      
        }, 30 * 20);
        ranks_1 = scheduler1.scheduleSyncDelayedTask(this, new Runnable(){
        public void run(){
         p.closeInventory();
         double x = -20.9573981451369;
         double y = 15.0;
         double z = -27.459159152262732;
         double yaw = 90.76259;
         double pitch = -2.3010778;
         World w = Bukkit.getWorld("dk");
         Location loc = new Location(w, x, y, z, (float) yaw, (float) pitch);
         p.teleport(loc);
         p.playSound(p.getLocation(), Sound.CLICK, 5.0f, 1.0f);
             p.sendMessage("§a             ");
             p.sendMessage("§aRank Arenito");
             p.sendMessage("§aAmostrando kits da areia em 5 segundo!");            
             p.sendMessage("§a             ");
        }      
        }, 35 * 20); 
        ranks_1 = scheduler1.scheduleSyncDelayedTask(this, new Runnable(){
        public void run(){
         inv = sv.createInventory(null, 27, "§6Arenito");
         ItemStack i1 = new ItemStack(283);
         ItemMeta i1meta = i1.getItemMeta();
         i1meta.setDisplayName("§4§lKit Arenito III");
         i1.setItemMeta(i1meta);
         
         ItemStack i2 = new ItemStack(285);
         ItemMeta i2meta = i2.getItemMeta();
         i2meta.setDisplayName("§4§lKit Arenito III");
         i2.setItemMeta(i2meta);
         
         ItemStack i3 = new ItemStack(261);
         ItemMeta i3meta = i3.getItemMeta();
         i3meta.setDisplayName("§4§lKit Arenito III");
         i3.setItemMeta(i3meta);
         
         ItemStack i4 = new ItemStack(322);
         ItemMeta i4meta = i4.getItemMeta();
         i4meta.setDisplayName("§4§lKit Arenito III");
         i4.setItemMeta(i4meta);  
         
         ItemStack i5 = new ItemStack(320);
         ItemMeta i5meta = i5.getItemMeta();
         i5meta.setDisplayName("§4§lKit Arenito III");
         i5.setItemMeta(i5meta);  
         
         ItemStack i6 = new ItemStack(314);
         ItemMeta i6meta = i6.getItemMeta();
         i6meta.setDisplayName("§4§lKit Arenito III");
         i6.setItemMeta(i6meta);
         
         ItemStack i7 = new ItemStack(315);
         ItemMeta i7meta = i7.getItemMeta();
         i7meta.setDisplayName("§4§lKit Arenito III");
         i7.setItemMeta(i7meta);
         
         ItemStack i8 = new ItemStack(316);
         ItemMeta i8meta = i8.getItemMeta();
         i8meta.setDisplayName("§4§lKit Arenito III");
         i8.setItemMeta(i8meta);
         
         ItemStack i9 = new ItemStack(317);
         ItemMeta i9meta = i9.getItemMeta();
         i9meta.setDisplayName("§4§lKit Arenito III");
         i9.setItemMeta(i9meta);  
         
         ItemStack i10 = new ItemStack(283);
         ItemMeta i10meta = i10.getItemMeta();
         i10meta.setDisplayName("§4§lKit Arenito II");
         i10.setItemMeta(i10meta);
         
         ItemStack i11 = new ItemStack(285);
         ItemMeta i11meta = i11.getItemMeta();
         i11meta.setDisplayName("§4§lKit Arenito II");
         i11.setItemMeta(i11meta);
         
         ItemStack i12 = new ItemStack(261);
         ItemMeta i12meta = i12.getItemMeta();
         i12meta.setDisplayName("§4§lKit Arenito II");
         i12.setItemMeta(i12meta);
         
         ItemStack i13 = new ItemStack(322);
         ItemMeta i13meta = i13.getItemMeta();
         i13meta.setDisplayName("§4§lKit Arenito II");
         i13.setItemMeta(i13meta);  
         
         ItemStack i14 = new ItemStack(320);
         ItemMeta i14meta = i14.getItemMeta();
         i14meta.setDisplayName("§4§lKit Arenito II");
         i14.setItemMeta(i14meta);  
         
         ItemStack i15 = new ItemStack(314);
         ItemMeta i15meta = i15.getItemMeta();
         i15meta.setDisplayName("§4§lKit Arenito II");
         i15.setItemMeta(i15meta);
         
         ItemStack i16 = new ItemStack(315);
         ItemMeta i16meta = i16.getItemMeta();
         i16meta.setDisplayName("§4§lKit Arenito II");
         i16.setItemMeta(i16meta);
         
         ItemStack i17 = new ItemStack(316);
         ItemMeta i17meta = i17.getItemMeta();
         i17meta.setDisplayName("§4§lKit Arenito II");
         i17.setItemMeta(i17meta);
         
         ItemStack i18 = new ItemStack(317);
         ItemMeta i18meta = i18.getItemMeta();
         i18meta.setDisplayName("§4§lKit Arenito II");
         i18.setItemMeta(i18meta);  
         
         ItemStack i20 = new ItemStack(283);
         ItemMeta i20meta = i20.getItemMeta();
         i20meta.setDisplayName("§4§lKit Arenito I");
         i20.setItemMeta(i20meta);
         
         ItemStack i21 = new ItemStack(285);
         ItemMeta i21meta = i21.getItemMeta();
         i21meta.setDisplayName("§4§lKit Arenito I");
         i21.setItemMeta(i21meta);
         
         ItemStack i22 = new ItemStack(261);
         ItemMeta i22meta = i22.getItemMeta();
         i22meta.setDisplayName("§4§lKit Arenito I");
         i22.setItemMeta(i22meta);
         
         ItemStack i23 = new ItemStack(322);
         ItemMeta i23meta = i23.getItemMeta();
         i23meta.setDisplayName("§4§lKit Arenito I");
         i23.setItemMeta(i23meta);  
         
         ItemStack i24 = new ItemStack(320);
         ItemMeta i24meta = i24.getItemMeta();
         i24meta.setDisplayName("§4§lKit Arenito I");
         i24.setItemMeta(i24meta);  
         
         ItemStack i25 = new ItemStack(314);
         ItemMeta i25meta = i25.getItemMeta();
         i25meta.setDisplayName("§4§lKit Arenito I");
         i25.setItemMeta(i25meta);
         
         ItemStack i26 = new ItemStack(315);
         ItemMeta i26meta = i26.getItemMeta();
         i26meta.setDisplayName("§4§lKit Arenito I");
         i26.setItemMeta(i26meta);
         
         ItemStack i27 = new ItemStack(316);
         ItemMeta i27meta = i27.getItemMeta();
         i27meta.setDisplayName("§4§lKit Arenito I");
         i27.setItemMeta(i27meta);
         
         ItemStack i28 = new ItemStack(317);
         ItemMeta i28meta = i28.getItemMeta();
         i28meta.setDisplayName("§4§lKit Arenito I");
         i28.setItemMeta(i28meta); 
         //i2.addEnchantment(Enchantment., 2);
         inv.setItem(0, i1);
         inv.setItem(1, i2);
         inv.setItem(2, i3);
         inv.setItem(3, i4);
         inv.setItem(4, i5);
         inv.setItem(5, i6);
         inv.setItem(6, i7);
         inv.setItem(7, i8);
         inv.setItem(8, i9);
         inv.setItem(9, i10);
         inv.setItem(10, i11);
         inv.setItem(11, i12);
         inv.setItem(12, i13);
         inv.setItem(13, i14);
         inv.setItem(14, i15);
         inv.setItem(15, i16);
         inv.setItem(16, i17);
         inv.setItem(17, i18);
         inv.setItem(18, i20);
         inv.setItem(19, i21);
         inv.setItem(20, i22);
         inv.setItem(21, i23);
         inv.setItem(22, i24);
         inv.setItem(23, i25);
         inv.setItem(24, i26);
         inv.setItem(25, i27);
         inv.setItem(26, i28);
         p.openInventory(inv);
         p.playSound(p.getLocation(), Sound.CLICK, 5.0f, 1.0f);
        }      
        }, 40 * 20); 
        ranks_1 = scheduler1.scheduleSyncDelayedTask(this, new Runnable(){
        public void run(){
         p.closeInventory();
         double x = -20.9573981451369;
         double y = 15.0;
         double z = -27.459159152262732;
         double yaw = 90.76259;
         double pitch = -2.3010778;
         World w = Bukkit.getWorld("dk");
         Location loc = new Location(w, x, y, z, (float) yaw, (float) pitch);
         p.teleport(loc);
         p.playSound(p.getLocation(), Sound.CLICK, 5.0f, 1.0f);
             p.sendMessage("§a             ");
             p.sendMessage("§aRank Pedra");
             p.sendMessage("§aAmostrando kits da areia em 5 segundo!");            
             p.sendMessage("§a             ");
        }      
        }, 45 * 20); 
        ranks_1 = scheduler1.scheduleSyncDelayedTask(this, new Runnable(){
        public void run(){
         inv = sv.createInventory(null, 36, "§6Pedra");
         ItemStack i1 = new ItemStack(283);
         ItemMeta i1meta = i1.getItemMeta();
         i1meta.setDisplayName("§4§lKit Arenito III");
         i1.setItemMeta(i1meta);
         
         ItemStack i2 = new ItemStack(285);
         ItemMeta i2meta = i2.getItemMeta();
         i2meta.setDisplayName("§4§lKit Arenito III");
         i2.setItemMeta(i2meta);
         
         ItemStack i3 = new ItemStack(261);
         ItemMeta i3meta = i3.getItemMeta();
         i3meta.setDisplayName("§4§lKit Arenito III");
         i3.setItemMeta(i3meta);
         
         ItemStack i4 = new ItemStack(322);
         ItemMeta i4meta = i4.getItemMeta();
         i4meta.setDisplayName("§4§lKit Arenito III");
         i4.setItemMeta(i4meta);  
         
         ItemStack i5 = new ItemStack(320);
         ItemMeta i5meta = i5.getItemMeta();
         i5meta.setDisplayName("§4§lKit Arenito III");
         i5.setItemMeta(i5meta);  
         
         ItemStack i6 = new ItemStack(314);
         ItemMeta i6meta = i6.getItemMeta();
         i6meta.setDisplayName("§4§lKit Arenito III");
         i6.setItemMeta(i6meta);
         
         ItemStack i7 = new ItemStack(315);
         ItemMeta i7meta = i7.getItemMeta();
         i7meta.setDisplayName("§4§lKit Arenito III");
         i7.setItemMeta(i7meta);
         
         ItemStack i8 = new ItemStack(316);
         ItemMeta i8meta = i8.getItemMeta();
         i8meta.setDisplayName("§4§lKit Arenito III");
         i8.setItemMeta(i8meta);
         
         ItemStack i9 = new ItemStack(317);
         ItemMeta i9meta = i9.getItemMeta();
         i9meta.setDisplayName("§4§lKit Arenito III");
         i9.setItemMeta(i9meta);  
         
         ItemStack i10 = new ItemStack(283);
         ItemMeta i10meta = i10.getItemMeta();
         i10meta.setDisplayName("§4§lKit Arenito II");
         i10.setItemMeta(i10meta);
         
         ItemStack i11 = new ItemStack(285);
         ItemMeta i11meta = i11.getItemMeta();
         i11meta.setDisplayName("§4§lKit Arenito II");
         i11.setItemMeta(i11meta);
         
         ItemStack i12 = new ItemStack(261);
         ItemMeta i12meta = i12.getItemMeta();
         i12meta.setDisplayName("§4§lKit Arenito II");
         i12.setItemMeta(i12meta);
         
         ItemStack i13 = new ItemStack(322);
         ItemMeta i13meta = i13.getItemMeta();
         i13meta.setDisplayName("§4§lKit Arenito II");
         i13.setItemMeta(i13meta);  
         
         ItemStack i14 = new ItemStack(320);
         ItemMeta i14meta = i14.getItemMeta();
         i14meta.setDisplayName("§4§lKit Arenito II");
         i14.setItemMeta(i14meta);  
         
         ItemStack i15 = new ItemStack(314);
         ItemMeta i15meta = i15.getItemMeta();
         i15meta.setDisplayName("§4§lKit Arenito II");
         i15.setItemMeta(i15meta);
         
         ItemStack i16 = new ItemStack(315);
         ItemMeta i16meta = i16.getItemMeta();
         i16meta.setDisplayName("§4§lKit Arenito II");
         i16.setItemMeta(i16meta);
         
         ItemStack i17 = new ItemStack(316);
         ItemMeta i17meta = i17.getItemMeta();
         i17meta.setDisplayName("§4§lKit Arenito II");
         i17.setItemMeta(i17meta);
         
         ItemStack i18 = new ItemStack(317);
         ItemMeta i18meta = i18.getItemMeta();
         i18meta.setDisplayName("§4§lKit Arenito II");
         i18.setItemMeta(i18meta);  
         
         ItemStack i20 = new ItemStack(283);
         ItemMeta i20meta = i20.getItemMeta();
         i20meta.setDisplayName("§4§lKit Arenito I");
         i20.setItemMeta(i20meta);
         
         ItemStack i21 = new ItemStack(285);
         ItemMeta i21meta = i21.getItemMeta();
         i21meta.setDisplayName("§4§lKit Arenito I");
         i21.setItemMeta(i21meta);
         
         ItemStack i22 = new ItemStack(261);
         ItemMeta i22meta = i22.getItemMeta();
         i22meta.setDisplayName("§4§lKit Arenito I");
         i22.setItemMeta(i22meta);
         
         ItemStack i23 = new ItemStack(322);
         ItemMeta i23meta = i23.getItemMeta();
         i23meta.setDisplayName("§4§lKit Arenito I");
         i23.setItemMeta(i23meta);  
         
         ItemStack i24 = new ItemStack(320);
         ItemMeta i24meta = i24.getItemMeta();
         i24meta.setDisplayName("§4§lKit Arenito I");
         i24.setItemMeta(i24meta);  
         
         ItemStack i25 = new ItemStack(314);
         ItemMeta i25meta = i25.getItemMeta();
         i25meta.setDisplayName("§4§lKit Arenito I");
         i25.setItemMeta(i25meta);
         
         ItemStack i26 = new ItemStack(315);
         ItemMeta i26meta = i26.getItemMeta();
         i26meta.setDisplayName("§4§lKit Arenito I");
         i26.setItemMeta(i26meta);
         
         ItemStack i27 = new ItemStack(316);
         ItemMeta i27meta = i27.getItemMeta();
         i27meta.setDisplayName("§4§lKit Arenito I");
         i27.setItemMeta(i27meta);
         
         ItemStack i28 = new ItemStack(317);
         ItemMeta i28meta = i28.getItemMeta();
         i28meta.setDisplayName("§4§lKit Arenito I");
         i28.setItemMeta(i28meta); 
         //i2.addEnchantment(Enchantment., 2);
         inv.setItem(0, i1);
         inv.setItem(1, i2);
         inv.setItem(2, i3);
         inv.setItem(3, i4);
         inv.setItem(4, i5);
         inv.setItem(5, i6);
         inv.setItem(6, i7);
         inv.setItem(7, i8);
         inv.setItem(8, i9);
         inv.setItem(9, i10);
         inv.setItem(10, i11);
         inv.setItem(11, i12);
         inv.setItem(12, i13);
         inv.setItem(13, i14);
         inv.setItem(14, i15);
         inv.setItem(15, i16);
         inv.setItem(16, i17);
         inv.setItem(17, i18);
         inv.setItem(18, i20);
         inv.setItem(19, i21);
         inv.setItem(20, i22);
         inv.setItem(21, i23);
         inv.setItem(22, i24);
         inv.setItem(23, i25);
         inv.setItem(24, i26);
         inv.setItem(25, i27);
         inv.setItem(26, i28);
         p.openInventory(inv);
         p.playSound(p.getLocation(), Sound.CLICK, 5.0f, 1.0f);
        }      
        }, 45 * 20);         
        
        }
        else{
            p.sendMessage("§f[§6§lTutorial§f]Voce já completou esse tutorial!!");
        }     
        }
      }
  }
  @EventHandler
  public void End(PlayerInteractEntityEvent e) {
      BukkitScheduler scheduler1 = getServer().getScheduler();
      final Player p = e.getPlayer();
      String nome = p.getName();
      if(e.getRightClicked() instanceof Villager) {
        Villager a = (Villager)e.getRightClicked();
        if(a.getCustomName().equals("§f[§6§lTutorial§f]Concluir")) {
        e.setCancelled(true);
        if(getConfig().getString(p.getName()+".end") == null){
        if(getConfig().getString(p.getName()+".regras") != null){
          if(getConfig().getString(p.getName()+".warps") != null){
                if(getConfig().getString(p.getName()+".ranks") != null){
        if(AntDupe.contains(p.getName())){  
        }
        AntDupe.add(p.getName());
                    double x = 50000.500;
                    double y = 64.00000;
                    double z = 50000.500; 
                    World w = p.getWorld();
                    Location loc = new Location(w, x, y, z, 90, 3);
                    p.teleport(loc);
                    getConfig().set(p.getName()+".end", "1"); 
                    economy.depositPlayer(p.getName(), 5000.00);
                    PlayerTuto.remove(p.getName());
                    p.sendMessage("§f[§6§lTutorial§f]Voce completou todos tutorial parabens!!");
                    p.playSound(p.getLocation(), Sound.ENDERDRAGON_GROWL, 10.0f, 1.0f);
                    saveConfig(); 
                    AntDupe.remove(p.getName());
                }
                else{
                  p.sendMessage("§f[§6§lTutorial§f]Voce não completou todos tutorial!!");
                } 
          }
          else{
            p.sendMessage("§f[§6§lTutorial§f]Voce não completou todos tutorial!!");
          } 
        }
        else{
            p.sendMessage("§f[§6§lTutorial§f]Voce não completou todos tutorial!!");
        }     
        }
        else{
            p.sendMessage("§f[§6§lTutorial§f]Voce ja completou todos tutorial!!");
        } 
        }
      }
  }  
  @EventHandler
  public void onChat(PlayerChatEvent event){
     if(PlayerTuto.contains(event.getPlayer().getName())){
     event.setCancelled(true);
     event.setMessage("§f[§6§lTutorial§f]Para falar no chat precisa terminar o tutorial!");
     } 
  }
  @EventHandler
  public void Mover(PlayerMoveEvent event){
     if(PlayerTuto.contains(event.getPlayer().getName())){
     event.setTo(event.getFrom());
     } 
  }
  @EventHandler
public void Dano(EntityDamageEvent e) {
    if(e.getEntity() instanceof Villager) {
    Villager v = (Villager) e.getEntity();
    if(v.getCustomName() == "§f[§6§lTutorial§f]Concluir" && v.getCustomName() == "§f[§6§lTutorial§f]Ranks" && v.getCustomName() == "§f[§6§lTutorial§f]Regras" && v.getCustomName() == "§f[§6§lTutorial§f]Warps"){
     e.setCancelled(true);
     e.setDamage(0);
    }
        
    }
}
@EventHandler
public void Inventario(InventoryClickEvent e) {
    Player p = (Player) e.getWhoClicked();
    if(PlayerTuto.contains(p.getName())) {
        e.setCancelled(true);
    }
    
}
  private boolean setupEconomy(){
  RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
    if (economyProvider != null) {
        economy = economyProvider.getProvider();
    }

  return (economy != null);
  }
    @Override
    public void onEnable() {
       getCommand("npc").setExecutor(new SetarMob(this));
       Bukkit.getPluginManager().registerEvents(this,this);
       saveDefaultConfig();
       setupEconomy();
    }  
}
