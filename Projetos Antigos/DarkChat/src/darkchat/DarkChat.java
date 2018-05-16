/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package darkchat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import mkremins.fanciful.FancyMessage;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ClickEvent.Action;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import net.sacredlabyrinth.phaed.simpleclans.Clan;
import net.sacredlabyrinth.phaed.simpleclans.SimpleClans;
import net.sacredlabyrinth.phaed.simpleclans.managers.ClanManager;
 
import org.bukkit.Bukkit;
import static org.bukkit.Bukkit.getServer;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.Statistic;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.util.Vector;

/**
 *
 * @author WillianDev
 */
public class DarkChat extends JavaPlugin implements Listener{
  public static Permission perms = null;
  public static Economy economy = null;
  private Clan clanplayer;
  private String tagcolor;
  private String tag;
  public static int musica1;
  public static int musica2;
  public static int musica3;
  public static int musica4;
  String Magnata;
  String Magnata2;
  public ArrayList<String> Playersmutado = new ArrayList<String>();

  private boolean setupPermissions()
  {
    RegisteredServiceProvider permissionProvider = getServer()
      .getServicesManager().getRegistration(
      Permission.class);
    if (permissionProvider != null) {
      perms = (Permission)permissionProvider.getProvider();
    }
    return perms != null;
  }

        @Override
        public void onEnable() {
        this.setupEconomy();
        setupEconomy();
        if (!setupPermissions()) {
            System.out.println("Vault not found. Shutting down");
        }
       Bukkit.getPluginManager().registerEvents(this, this);  
       Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable()
{
     public void run()
          {
               Musicas();
          }
}, 0, 20 * 1000);
       
        }
        
   @EventHandler
  public void entrou(PlayerJoinEvent e) {
    if ((e.getPlayer().getDisplayName().equals(this.Magnata)) && 
      (getConfig().getBoolean("AtivarMsgJoin"))) {
      String mensagem = getConfig().getString("MensagemJoin");
      getServer().broadcastMessage(mensagem.replaceAll("&", "§").replace("$player", this.Magnata));
    }
  }

  @EventHandler
  public void saiu(PlayerQuitEvent e) {
    if ((e.getPlayer().getDisplayName().equals(this.Magnata)) && 
      (getConfig().getBoolean("AtivarMsgQuit"))) {
      String mensagem = getConfig().getString("MensagemQuit");
      getServer().broadcastMessage(mensagem.replaceAll("&", "§").replace("$player", this.Magnata));
    }
  } 
        
 public void Musicas(){
        BukkitScheduler scheduler1 = getServer().getScheduler();
        musica1 = scheduler1.scheduleSyncDelayedTask(this, new Runnable(){
        public void run(){
         for (Player p : Bukkit.getOnlinePlayers()){
         int x = 50000;
         int y = 64;
         int z = 50000; 
         World w = p.getWorld();
         Location loc = new Location(w, x, y, z);
         p.playEffect(loc, Effect.RECORD_PLAY, Material.GREEN_RECORD);
         //p.sendMessage("foi");
          }
        }      
        }, 1 * 20);  
        musica2 = scheduler1.scheduleSyncDelayedTask(this, new Runnable(){
        public void run(){
         for (Player p : Bukkit.getOnlinePlayers()){
         int x = 50000;
         int y = 64;
         int z = 50000; 
         World w = p.getWorld();
         Location loc = new Location(w, x, y, z);
         p.playEffect(loc, Effect.RECORD_PLAY, Material.RECORD_4);
         //p.sendMessage("foi 2");
          }
        }      
        }, 165 * 20);
        musica3 = scheduler1.scheduleSyncDelayedTask(this, new Runnable(){
        public void run(){
         for (Player p : Bukkit.getOnlinePlayers()){
         int x = 50000;
         int y = 64;
         int z = 50000; 
         World w = p.getWorld();
         Location loc = new Location(w, x, y, z);
         p.playEffect(loc, Effect.RECORD_PLAY, Material.RECORD_7);
         //p.sendMessage("foi 3");
          }
        }      
        }, 365 * 20);
        musica4 = scheduler1.scheduleSyncDelayedTask(this, new Runnable(){
        public void run(){
         for (Player p : Bukkit.getOnlinePlayers()){
         int x = 50000;
         int y = 64;
         int z = 50000; 
         World w = p.getWorld();
         Location loc = new Location(w, x, y, z);
         p.playEffect(loc, Effect.RECORD_PLAY, Material.RECORD_8);
         //p.sendMessage("foi 4");
          }
        }      
        }, 573 * 20);
}            

       
       // @EventHandler
        //public void onJoin(AsyncPlayerChatEvent event){
                //Player player = event.getPlayer();
                //String name = player.getDisplayName();
                //String message = event.getMessage();
                //ClanManager clanManager = SimpleClans.getInstance().getClanManager();
                //boolean hasClan = clanManager.getClanPlayer(player) != null;
                //String playerGroup = perms.getPrimaryGroup(player);
                //TextComponent nomep = new TextComponent();
                //nomep.setText(player.getDisplayName());
                //nomep.setBold(true);
                //nomep.setClickEvent(new ClickEvent(Action.RUN_COMMAND,player.getDisplayName()));
                //nomep.setHoverEvent(new HoverEvent(net.md_5.bungee.api.chat.HoverEvent.Action.SHOW_ITEM, new ComponentBuilder("§6" + player.getName() + " §fRank: " + "§a" + playerGroup)
                //.create()));
                //player.spigot().sendMessage(nomep);
        //}
  public String getTagColor(Player player)
  {
    this.clanplayer = SimpleClans.getInstance().getClanManager().getClanPlayer(player).getClan();
    this.tagcolor = this.clanplayer.getTagLabel().replaceAll("[\\[\\]\\s.]", "").replaceAll("(§([0-9|a-f|r]))+", "$1");
    this.tag = this.tagcolor.substring(0, this.tagcolor.length() - 2);
    return this.tag;
  }
        
        @EventHandler
        public void OnChat(AsyncPlayerChatEvent event){
          Player player = event.getPlayer();
          String name = player.getDisplayName();
          String message = event.getMessage();
          message.replace("&", "§");
          ClanManager clanManager = SimpleClans.getInstance().getClanManager();
          boolean hasClan = clanManager.getClanPlayer(player) != null;
          String playerGroup = perms.getPrimaryGroup(player);
          FancyMessage chatgp = new FancyMessage(playerGroup);
          if(!player.isOp()){
          if (player.hasPermission("dk.hater")){
          if(player.isOp()){
          FancyMessage chatformat = new FancyMessage(name)
          .tooltip("§6" + player.getName() + " §fRank: " + "§a" + playerGroup, "§2Money: §6" + economy.format(economy.getBalance(player.getName()))).then(" §4» " + message); 
          event.setCancelled(true);
          event.setMessage(chatformat + message);
          if (!Bukkit.getOnlinePlayers().isEmpty())
          for (Player p : Bukkit.getOnlinePlayers()){
          chatformat.send(p);
          }
          }
          else{
          FancyMessage chatformat = new FancyMessage(name)
          .tooltip("§6" + player.getName() + " §fRank: " + "§aHater", "§2Money: §6 Pobri", "§2IP DO HATER: " + "[§f" + player.getAddress() + "§2]").then(" §4» " + "§f" + "Server muito foda!!!!, gostei vo ate divulgar no meu skype :D"); 
          event.setCancelled(true);
          event.setMessage(chatformat + message);
          if (!Bukkit.getOnlinePlayers().isEmpty())
            for (Player p : Bukkit.getOnlinePlayers()){
            chatformat.send(p);
          }
          }
          }
          else{
          if(Playersmutado.contains(event.getPlayer().getName())){
              event.setCancelled(true);
              event.getPlayer().sendMessage("Voce foi mutado! :(");
              FancyMessage chatformat = new FancyMessage(name)
              .tooltip(); 
          if (!Bukkit.getOnlinePlayers().isEmpty())
            for (Player p : Bukkit.getOnlinePlayers()){
            event.setCancelled(true);
          }              
          }
          else{ 
          if (hasClan == true){
          FancyMessage chatformat = new FancyMessage(name)
          .tooltip("§6" + player.getName() + " §fRank: " + "§a" + playerGroup, "§2Money: §6" + economy.format(economy.getBalance(player.getName())), "§2Clan: " + "[§f" + SimpleClans.getInstance().getClanManager().getClanPlayer(player).getClan() + "§2]").then(" §4» " + message); 
          event.setCancelled(true);
          event.setMessage(chatformat + message);
          if (!Bukkit.getOnlinePlayers().isEmpty())
            for (Player p : Bukkit.getOnlinePlayers()){
            chatformat.send(p);
          }
          }
          else {
          FancyMessage chatformat = new FancyMessage(name)
          .tooltip("§6" + player.getName() + " §fRank: " + "§a" + playerGroup, "§2Money: §6" + economy.format(economy.getBalance(player.getName()))).then(" §4» " + message); 
          event.setCancelled(true);
          event.setMessage(chatformat + message);
          if (!Bukkit.getOnlinePlayers().isEmpty())
            for (Player p : Bukkit.getOnlinePlayers()){
            chatformat.send(p);
          }
          } 
          }
          }
          }
          else {
          FancyMessage chatformat = new FancyMessage(name)
          .tooltip("§6" + player.getName() + " §fRank: " + "§a" + playerGroup, "§2Money: §6" + economy.format(economy.getBalance(player.getName()))).then(" §4» " + message); 
          event.setCancelled(true);
          event.setMessage(chatformat + message);
          if (!Bukkit.getOnlinePlayers().isEmpty())
            for (Player p : Bukkit.getOnlinePlayers()){
            chatformat.send(p);
          }
          } 
        }
private boolean setupEconomy()
    {
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (economyProvider != null) {
            economy = economyProvider.getProvider();
        }

        return (economy != null);
    }
@EventHandler
public void PlayListServer(PlayerJoinEvent event){
 int x = 50000;
 int y = 64;
 int z = 50000;  
 Sound string;
 Player p = event.getPlayer();
 World w = p.getWorld();
 Location loc = new Location(w, x, y, z);
 p.playEffect(loc, Effect.RECORD_PLAY, Material.GREEN_RECORD); 
}

   @EventHandler
   public void PlayerMutado(PlayerChatEvent e){
          if(Playersmutado.contains(e.getPlayer().getName())){
              e.setCancelled(true);
              e.getPlayer().sendMessage("Voce foi mutado! :(");
          }else{
                   
           }
   }

    @Override
    public boolean onCommand(CommandSender cs, Command cmnd, String string, String[] args) {
        if(cs instanceof Player) {
            if(cmnd.getName().equalsIgnoreCase("tocar")) {
            if(args.length == 1) {
            if (args[0].equalsIgnoreCase("1")) {
            Player p = (Player)cs;
            World w = p.getWorld();
            Location loc = p.getLocation();            
            int x = loc.getBlockX();
            int y = loc.getBlockY();
            int z = loc.getBlockZ(); 
            p.playEffect(loc, Effect.RECORD_PLAY, Material.GREEN_RECORD);   
            p.sendMessage("Agora estou tocando para vc XD");               
            return true;
            }
            if (args[0].equalsIgnoreCase("2")) {
            Player p = (Player)cs;
            World w = p.getWorld();
            Location loc = p.getLocation();            
            int x = loc.getBlockX();
            int y = loc.getBlockY();
            int z = loc.getBlockZ(); 
            p.playEffect(loc, Effect.RECORD_PLAY, Material.RECORD_4);   
            p.sendMessage("Agora estou tocando para vc XD");               
            return true;
            }            
            }
            }
            if(cmnd.getName().equalsIgnoreCase("dkmutar")){
            if(cs.isOp()){
            if(args.length == 1) {
            Player p = Bukkit.getPlayer(args[0]);
            if(Playersmutado.contains(p.getName())){
            Playersmutado.remove(p.getName());
            p.sendMessage(ChatColor.BLUE + "Seu mute foi tirado");
            }
            else{
            Playersmutado.add(p.getName());
            p.sendMessage(ChatColor.BLUE + "Vc foi mutado");
            }
            }   
            }
            }
        }
    return false;
    }
@EventHandler
public void onProjectileLaunch(ProjectileLaunchEvent event) {
    Projectile proj = event.getEntity();
    if (proj instanceof EnderPearl) {
        EnderPearl pearl = (EnderPearl)proj;
        ProjectileSource source = pearl.getShooter();
        if (source instanceof Player) {
            Player player = (Player)source;
            player.setVelocity(new Vector(130,130,130));
            pearl.setPassenger(player);
        }
    }
}
   
    @EventHandler
    public void onPlayerTeleport(PlayerTeleportEvent event) {
       
        if(event.getCause().equals(TeleportCause.ENDER_PEARL)) {
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void Dano(EntityDamageEvent event) {
    if(event.getEntity() instanceof Player) {
    Player p = (Player) event.getEntity();
        if(event.getCause().equals(TeleportCause.ENDER_PEARL)) {
            event.setCancelled(true);
        }
    }
}
    
     
    
}



