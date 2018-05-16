package me.wiljafor1.saocmds;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import me.wiljafor1.saocmds.Main;
import static me.wiljafor1.saocmds.MainConfig.config;
import me.wiljafor1.saocmds.Utils.CItem;
import me.wiljafor1.saocmds.Utils.Errors;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.inventory.Inventory;

public class Comandos implements Listener,CommandExecutor  {
    Main plugin;
    Connection connection;
    public Comandos(Main plugin) {
        this.plugin = plugin;
    }  
    @Override
    public boolean onCommand(CommandSender cs, Command cmnd, String string, String[] args) {
            Player p = (Player)cs;
        if(cs instanceof Player) {
            if(cs.hasPermission("sao.admin")) {//saoc comando <add/del> <comando/sub>
                if(cmnd.getName().equalsIgnoreCase("saoc")){//ewarp ,normal
                if(args.length > 0){
                    if(args[0].equalsIgnoreCase("comando")){
                        if(args.length ==4){
                            if(args[1].equalsIgnoreCase("add")){
                                    CriarComando(args[2], args[3], "ewarp");
                                    p.sendMessage("Comando Criado!!");
                                    plugin.reload();
                            }
                        }
                        else{
                            if(args.length == 3){
                                if(args[1].equalsIgnoreCase("del")){
                                    DelComando(args[2]);
                                    p.sendMessage("Comando Deletado!!");
                                    plugin.reload();
                                }
                                else{
                                    p.sendMessage("use: /saoc comando <add/del> <Nome do Warp> <Comando>");
                                }
                            }
                            else{
                                p.sendMessage("use: /saoc comando <add/del> <Nome do Warp> <Comando>");
                            }
                        }
                    }
                    else{
                        if(args[0].equalsIgnoreCase("reload")){
                            p.sendMessage("reload");
                            plugin.reload();
                        }
                        else{
                            if(args[0].equalsIgnoreCase("list")){
                                ListarComando(p);
                            }
                            else{
                            p.sendMessage("§3§m------§f [§ESAOCMDS§f] §3§m------");
                            p.sendMessage("§e/saoc comando <add/del> <Warp> <Plugin>");
                            p.sendMessage("§e/saoc list §8- §7Listar os comandos");
                            p.sendMessage("§e/saoc reload §8- §7Recarregar o Plugin");
                            p.sendMessage("§3§m------§f [§ESAOCMDS§f] §3§m------");
                            } 
                        } 
                    }
                }
                else{
                            p.sendMessage("§3§m------§f [§ESAOCMDS§f] §3§m------");
                            p.sendMessage("§e/saoc comando <add/del> <Warp> <Plugin>");
                            p.sendMessage("§e/saoc list §8- §7Listar os comandos");
                            p.sendMessage("§e/saoc reload §8- §7Recarregar o Plugin");
                            p.sendMessage("§3§m------§f [§ESAOCMDS§f] §3§m------");
                }
                }                
            }
        }
        else{
        }
        return false;
    }
    public boolean CriarComando(String cmd,String sub, String tipo){
        File dataFolder = new File(plugin.getDataFolder(), "db.db");
        try{
            connection = DriverManager.getConnection("jdbc:sqlite:" + dataFolder);
            PreparedStatement ps = connection.prepareStatement("INSERT INTO `comandos` (`id`, `Comando`, `Sub`, `Tipo`) VALUES (NULL, '"+ cmd +"', '"+ sub +"', '"+ tipo +"');");
            ps.execute();
            connection.close();
   
        } catch (SQLException ex) {
            plugin.getLogger().log(Level.SEVERE, "Unable to retreive connection", ex);
        }
        return true;
    }
    public String BuscarComando(String cmd){
        File dataFolder = new File(plugin.getDataFolder(), "db.db");
        ResultSet rs = null;
        String tipo = "s";
        try{
            connection = DriverManager.getConnection("jdbc:sqlite:" + dataFolder);
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM comandos WHERE Comando = '"+ cmd+ "'");
            rs = ps.executeQuery();
            while(rs.next()){
                
                tipo = rs.getString("Tipo");
                
            }
            connection.close();
   
        } catch (SQLException ex) {
            plugin.getLogger().log(Level.SEVERE, "Unable to retreive connection", ex);
        }
        return tipo;
    }
    public String ListarComando(Player p){
        File dataFolder = new File(plugin.getDataFolder(), "db.db");
        ResultSet rs = null;
        String tipo = "s";
        try{
            connection = DriverManager.getConnection("jdbc:sqlite:" + dataFolder);
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM comandos");
            rs = ps.executeQuery();
            while(rs.next()){
                p.sendMessage("Lista de comandos:");
                do {
		String Comandos = rs.getString("Sub");
		p.sendMessage("§2/"+Comandos);
		} 
                while(rs.next()); 
		p.sendMessage("");
            }
            connection.close();
   
        } catch (SQLException ex) {
            plugin.getLogger().log(Level.SEVERE, "Unable to retreive connection", ex);
        }
        return tipo;
    }
    public void DelComando(String cmd){
        File dataFolder = new File(plugin.getDataFolder(), "db.db");
        try{
            connection = DriverManager.getConnection("jdbc:sqlite:" + dataFolder);
            PreparedStatement ps = connection.prepareStatement("DELETE FROM comandos WHERE Sub = '"+ cmd+ "'");
            ps.execute();
            connection.close();
   
        } catch (SQLException ex) {
            plugin.getLogger().log(Level.SEVERE, "Unable to retreive connection", ex);
        }
    }
    @EventHandler
    public void command(PlayerCommandPreprocessEvent e) {
    Player p = e.getPlayer();
    String[] args = e.getMessage().split(" ");
    String Comandos = args[0].replace("/", "");
    if(args[0].equalsIgnoreCase("/deicu")){
            e.setCancelled(true);
            String[] Vip1l = new String[2];
            Vip1l[0] = "§7§LVIP DIARIO";
            Vip1l[1] = "§6Preço: §aR$ 2,90";
            //Vip1l[2] = "";
            //Vip1l[3] = "";
            String[] Vip12 = new String[2];
            Vip12[0] = "§6§LVIP SEMANAL";
            Vip12[1] = "§6Preço: §aR$ 14,90";
            //Vip12[2] = "";
            //Vip12[3] = "";
            String[] Vip13 = new String[2];
            Vip13[0] = "§9§LVIP MENSAL";
            Vip13[1] = "§6Preço: §aR$ 39,90";
            //Vip13[2] = "";
            //Vip13[3] = "";
            CItem vip1 = new CItem(Material.IRON_INGOT).setName("§7§lDiario").setLore(Vip1l);
            CItem vip2 = new CItem(Material.GOLD_INGOT).setName("§e§lSemanal").setLore(Vip12);
            CItem vip3 = new CItem(Material.DIAMOND).setName("§b§lMensal").setLore(Vip13);
            CItem painel = new CItem(Material.STAINED_GLASS_PANE).setDurability(7).setName("§8-||-");
            CItem vidro = new CItem(Material.STAINED_GLASS_PANE).setName("§8-||-");
            CItem site = new CItem(Material.EMERALD).setName("§2Site");
  
            Inventory vipver = Bukkit.createInventory(null, 27, "Vips:");
            //paineis - 1
            vipver.setItem(0, painel.build());
            vipver.setItem(1, painel.build());
            vipver.setItem(2, painel.build());
            vipver.setItem(3, painel.build());
            vipver.setItem(4, painel.build());
            vipver.setItem(5, painel.build());
            vipver.setItem(6, painel.build());
            vipver.setItem(7, painel.build());
            vipver.setItem(8, painel.build());
            //fim
            //vip - 2
            vipver.setItem(9, site.build());
            vipver.setItem(10, vidro.build());
            vipver.setItem(11, vidro.build());
            vipver.setItem(12, vip1.build());
            vipver.setItem(13, vip2.build());
            vipver.setItem(14, vip3.build());
            vipver.setItem(15, vidro.build());
            vipver.setItem(16, vidro.build());
            vipver.setItem(17, site.build());
            //fim
            //painels - 3
            vipver.setItem(18, painel.build());
            vipver.setItem(19, painel.build());
            vipver.setItem(20, painel.build());
            vipver.setItem(21, painel.build());
            vipver.setItem(22, painel.build());
            vipver.setItem(23, painel.build());
            vipver.setItem(24, painel.build());
            vipver.setItem(25, painel.build());
            vipver.setItem(26, painel.build());
            //fim
            Bukkit.getPlayer(p.getName()).openInventory(vipver); 
            return;     
    }
    if(plugin.Comando.contains(Comandos)){
        e.setCancelled(true);
        String cr = Main.ComandoS.get(Comandos);//cr COMANDO REAL
        String tipo = BuscarComando(cr);
        //p.sendMessage(Comandos);
        //p.sendMessage(cr);
        //p.sendMessage(tipo);
        if(tipo.equalsIgnoreCase("ewarp")){
            Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "ewarp "+ cr + " "+ p.getName()+"");
        }
        if(tipo.equalsIgnoreCase("gui")){
            Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "ewarp "+ cr + " "+ p.getName()+"");
        }
    }
  }
    
    @EventHandler
    public void Interações(InventoryClickEvent event){
        Player player = (Player)event.getWhoClicked(); 
        if(event.getInventory().getTitle().equalsIgnoreCase("§e§lSemanal - Ver")){
        event.setCancelled(true);
        }
        if(event.getInventory().getTitle().equalsIgnoreCase("§e§lMensal - Kits")){
        event.setCancelled(true);
        }
        if(event.getInventory().getTitle().equalsIgnoreCase("§a§lDiario - Ver")){
        event.setCancelled(true);
        }
        if(event.getInventory().getTitle().equalsIgnoreCase("§a§lMensal - Ver")){
        event.setCancelled(true);
        }
        if(event.getInventory().getTitle().equalsIgnoreCase("§7§lDiario - Ver")){
        event.setCancelled(true);    
        }
        if(event.getCurrentItem() != null){
            if(event.getCurrentItem().getType() != Material.AIR){
                if(event.getCurrentItem().hasItemMeta()){
                    String nome = player.getName();
                    if(event.getInventory().getTitle().equalsIgnoreCase("Vips:")){// --------- GUI INICIALDO VIP!! ----
                    if((event.getCurrentItem().getItemMeta().getDisplayName().contains("§8-||-"))){
                    event.setCancelled(true);
                    }
                    if((event.getCurrentItem().getItemMeta().getDisplayName().contains("§2Site"))){
                    event.setCancelled(true);
                    if(event.getWhoClicked() instanceof Player) {
                    player.closeInventory();
                    player.sendMessage("§e§lSITE: §6§osite.saomc.co");
                    }  
                    }
                    if((event.getCurrentItem().getItemMeta().getDisplayName().contains("§7§lDiario"))){
                    event.setCancelled(true);
                    if(event.getWhoClicked() instanceof Player) {
                    player.closeInventory();
                    Inventory vipver = Bukkit.createInventory(null, 18, "§7§lDiario - Ver");
                    CItem item1 = new CItem(Material.DIAMOND_HELMET).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 10).addEnchantment(Enchantment.DURABILITY, 10);
                    CItem item2 = new CItem(Material.DIAMOND_CHESTPLATE).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 10).addEnchantment(Enchantment.DURABILITY, 10);
                    CItem item3 = new CItem(Material.DIAMOND_LEGGINGS).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 10).addEnchantment(Enchantment.DURABILITY, 10);
                    CItem item4 = new CItem(Material.DIAMOND_BOOTS).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 10).addEnchantment(Enchantment.DURABILITY, 10);
                    CItem item5 = new CItem(Material.BOW).addEnchantment(Enchantment.ARROW_DAMAGE, 10).addEnchantment(Enchantment.DURABILITY, 10);
                    CItem item6 = new CItem(Material.DIAMOND_SWORD).addEnchantment(Enchantment.DURABILITY, 10).addEnchantment(Enchantment.DAMAGE_ALL, 10);
                    CItem item7 = new CItem(Material.DIAMOND_PICKAXE).addEnchantment(Enchantment.DURABILITY, 10).addEnchantment(Enchantment.DIG_SPEED, 10).addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 5);
                    CItem item8 = new CItem(Material.DIAMOND_AXE).addEnchantment(Enchantment.DURABILITY, 10).addEnchantment(Enchantment.DIG_SPEED, 10).addEnchantment(Enchantment.DAMAGE_ALL, 10);
                    CItem item9 = new CItem(Material.DIAMOND_SPADE).addEnchantment(Enchantment.DURABILITY, 10).addEnchantment(Enchantment.DIG_SPEED, 10);
                    CItem item10 = new CItem(Material.GOLDEN_APPLE).setDurability(1).setAmount(64);
                    CItem item11 = new CItem(Material.ARROW).setAmount(64);
                    CItem item12 = new CItem(Material.MOB_SPAWNER).setName("§eZombie Pigman §fSpawner").setAmount(5);
                    CItem item13 = new CItem(Material.MOB_SPAWNER).setName("§eIron Golem §fSpawner").setAmount(5);
                    vipver.setItem(0, item1.build());
                    vipver.setItem(1, item2.build());
                    vipver.setItem(2, item3.build());
                    vipver.setItem(3, item4.build());
                    vipver.setItem(4, item5.build());
                    vipver.setItem(5, item6.build());
                    vipver.setItem(6, item7.build());
                    vipver.setItem(7, item8.build());
                    vipver.setItem(8, item9.build());
                    vipver.setItem(11, item10.build());
                    vipver.setItem(12, item11.build());
                    vipver.setItem(14, item12.build());
                    vipver.setItem(15, item13.build());
                    Bukkit.getPlayer(player.getName()).openInventory(vipver);
                    }  
                    }
                    if((event.getCurrentItem().getItemMeta().getDisplayName().contains("§e§lSemanal"))){
                    event.setCancelled(true);
                    if(event.getWhoClicked() instanceof Player) {
                    player.closeInventory();
                    Inventory vipver = Bukkit.createInventory(null, 18, "§e§lSemanal - Ver");
                    CItem item1 = new CItem(Material.DIAMOND_HELMET).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 10).addEnchantment(Enchantment.DURABILITY, 10);
                    CItem item2 = new CItem(Material.DIAMOND_CHESTPLATE).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 10).addEnchantment(Enchantment.DURABILITY, 10);
                    CItem item3 = new CItem(Material.DIAMOND_LEGGINGS).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 10).addEnchantment(Enchantment.DURABILITY, 10);
                    CItem item4 = new CItem(Material.DIAMOND_BOOTS).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 10).addEnchantment(Enchantment.DURABILITY, 10);
                    CItem item5 = new CItem(Material.BOW).addEnchantment(Enchantment.ARROW_DAMAGE, 10).addEnchantment(Enchantment.DURABILITY, 10);
                    CItem item6 = new CItem(Material.DIAMOND_SWORD).addEnchantment(Enchantment.DURABILITY, 10).addEnchantment(Enchantment.DAMAGE_ALL, 10);
                    CItem item7 = new CItem(Material.DIAMOND_PICKAXE).addEnchantment(Enchantment.DURABILITY, 10).addEnchantment(Enchantment.DIG_SPEED, 10).addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 5);
                    CItem item8 = new CItem(Material.DIAMOND_AXE).addEnchantment(Enchantment.DURABILITY, 10).addEnchantment(Enchantment.DIG_SPEED, 10).addEnchantment(Enchantment.DAMAGE_ALL, 10);
                    CItem item9 = new CItem(Material.DIAMOND_SPADE).addEnchantment(Enchantment.DURABILITY, 10).addEnchantment(Enchantment.DIG_SPEED, 10);
                    CItem item10 = new CItem(Material.GOLDEN_APPLE).setDurability(1).setAmount(64);
                    CItem item11 = new CItem(Material.ARROW).setAmount(64);
                    CItem item12 = new CItem(Material.MOB_SPAWNER).setName("§eZombie Pigman §fSpawner").setAmount(10);
                    CItem item13 = new CItem(Material.MOB_SPAWNER).setName("§eIron Golem §fSpawner").setAmount(10);
                    vipver.setItem(0, item1.build());
                    vipver.setItem(1, item2.build());
                    vipver.setItem(2, item3.build());
                    vipver.setItem(3, item4.build());
                    vipver.setItem(4, item5.build());
                    vipver.setItem(5, item6.build());
                    vipver.setItem(6, item7.build());
                    vipver.setItem(7, item8.build());
                    vipver.setItem(8, item9.build());
                    vipver.setItem(11, item10.build());
                    vipver.setItem(12, item11.build());
                    vipver.setItem(14, item12.build());
                    vipver.setItem(15, item13.build());
                    Bukkit.getPlayer(player.getName()).openInventory(vipver);
                    }  
                    }
                    if((event.getCurrentItem().getItemMeta().getDisplayName().contains("§b§lMensal"))){
                    event.setCancelled(true);
                    if(event.getWhoClicked() instanceof Player) {
                    player.closeInventory();
                    Inventory vipver = Bukkit.createInventory(null, 9, "§e§lMensal - Kits");
                    CItem dia = new CItem(Material.EMERALD).setName("§a§lDiario");
                    CItem mensal = new CItem(Material.EMERALD_BLOCK).setName("§a§lMensal");
                    vipver.setItem(2, dia.build());
                    vipver.setItem(6, mensal.build());
                    Bukkit.getPlayer(player.getName()).openInventory(vipver);
                    }  
                    }
                    }
                    //INICIO VIPS



                    if(event.getInventory().getTitle().equalsIgnoreCase("§e§lMensal - Kits")){
                    if((event.getCurrentItem().getItemMeta().getDisplayName().contains("§a§lDiario"))){
                    event.setCancelled(true);
                    if(event.getWhoClicked() instanceof Player) {
                    player.closeInventory();
                    Inventory vipver = Bukkit.createInventory(null, 18, "§a§lDiario - Ver");
                    CItem item1 = new CItem(Material.DIAMOND_HELMET).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 10).addEnchantment(Enchantment.DURABILITY, 10);
                    CItem item2 = new CItem(Material.DIAMOND_CHESTPLATE).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 10).addEnchantment(Enchantment.DURABILITY, 10);
                    CItem item3 = new CItem(Material.DIAMOND_LEGGINGS).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 10).addEnchantment(Enchantment.DURABILITY, 10);
                    CItem item4 = new CItem(Material.DIAMOND_BOOTS).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 10).addEnchantment(Enchantment.DURABILITY, 10);
                    CItem item5 = new CItem(Material.BOW).addEnchantment(Enchantment.ARROW_DAMAGE, 10).addEnchantment(Enchantment.DURABILITY, 10);
                    CItem item6 = new CItem(Material.DIAMOND_SWORD).addEnchantment(Enchantment.DURABILITY, 10).addEnchantment(Enchantment.DAMAGE_ALL, 10);
                    CItem item7 = new CItem(Material.DIAMOND_PICKAXE).addEnchantment(Enchantment.DURABILITY, 10).addEnchantment(Enchantment.DIG_SPEED, 10).addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 5);
                    CItem item8 = new CItem(Material.DIAMOND_AXE).addEnchantment(Enchantment.DURABILITY, 10).addEnchantment(Enchantment.DIG_SPEED, 10).addEnchantment(Enchantment.DAMAGE_ALL, 10);
                    CItem item9 = new CItem(Material.DIAMOND_SPADE).addEnchantment(Enchantment.DURABILITY, 10).addEnchantment(Enchantment.DIG_SPEED, 10);
                    CItem item10 = new CItem(Material.GOLDEN_APPLE).setDurability(1).setAmount(64);
                    CItem item11 = new CItem(Material.ARROW).setAmount(64);
                    CItem item12 = new CItem(Material.MOB_SPAWNER).setName("§eZombie Pigman §fSpawner").setAmount(5);
                    CItem item13 = new CItem(Material.MOB_SPAWNER).setName("§eIron Golem §fSpawner").setAmount(5);
                    vipver.setItem(0, item1.build());
                    vipver.setItem(1, item2.build());
                    vipver.setItem(2, item3.build());
                    vipver.setItem(3, item4.build());
                    vipver.setItem(4, item5.build());
                    vipver.setItem(5, item6.build());
                    vipver.setItem(6, item7.build());
                    vipver.setItem(7, item8.build());
                    vipver.setItem(8, item9.build());
                    vipver.setItem(11, item10.build());
                    vipver.setItem(12, item11.build());
                    vipver.setItem(14, item12.build());
                    vipver.setItem(15, item13.build());
                    Bukkit.getPlayer(player.getName()).openInventory(vipver);
                    }  
                    }
                    if((event.getCurrentItem().getItemMeta().getDisplayName().contains("§a§lMensal"))){
                    event.setCancelled(true);
                    if(event.getWhoClicked() instanceof Player) {
                    player.closeInventory();
                    Inventory vipver = Bukkit.createInventory(null, 18, "§a§lMensal - Ver");
                    CItem item1 = new CItem(Material.DIAMOND_HELMET).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 15).addEnchantment(Enchantment.DURABILITY, 15);
                    CItem item2 = new CItem(Material.DIAMOND_CHESTPLATE).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 15).addEnchantment(Enchantment.DURABILITY, 15);
                    CItem item3 = new CItem(Material.DIAMOND_LEGGINGS).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 15).addEnchantment(Enchantment.DURABILITY, 15);
                    CItem item4 = new CItem(Material.DIAMOND_BOOTS).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 15).addEnchantment(Enchantment.DURABILITY, 15);
                    CItem item5 = new CItem(Material.BOW).addEnchantment(Enchantment.ARROW_DAMAGE, 15).addEnchantment(Enchantment.DURABILITY, 15);
                    CItem item6 = new CItem(Material.DIAMOND_SWORD).addEnchantment(Enchantment.DURABILITY, 15).addEnchantment(Enchantment.DAMAGE_ALL, 15);
                    CItem item7 = new CItem(Material.DIAMOND_PICKAXE).addEnchantment(Enchantment.DURABILITY, 15).addEnchantment(Enchantment.DIG_SPEED, 15).addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 5);
                    CItem item8 = new CItem(Material.DIAMOND_AXE).addEnchantment(Enchantment.DURABILITY, 15).addEnchantment(Enchantment.DIG_SPEED, 15).addEnchantment(Enchantment.DAMAGE_ALL, 15);
                    CItem item9 = new CItem(Material.DIAMOND_SPADE).addEnchantment(Enchantment.DURABILITY, 15).addEnchantment(Enchantment.DIG_SPEED, 15);
                    CItem item10 = new CItem(Material.GOLDEN_APPLE).setDurability(1).setAmount(64);
                    CItem item11 = new CItem(Material.GOLDEN_APPLE).setDurability(1).setAmount(64);
                    CItem item12 = new CItem(Material.ARROW).setAmount(64);
                    CItem item13 = new CItem(Material.MOB_SPAWNER).setName("§eZombie Pigman §fSpawner").setAmount(10);
                    CItem item14 = new CItem(Material.MOB_SPAWNER).setName("§eIron Golem §fSpawner").setAmount(10);
                    CItem item15 = new CItem(Material.MOB_SPAWNER).setName("§eSkeleton §fSpawner").setAmount(10);
                    CItem item16 = new CItem(Material.MOB_SPAWNER).setName("§eCreeper §fSpawner").setAmount(10);
                    CItem item17 = new CItem(Material.MOB_SPAWNER).setName("§eBlaze §fSpawner").setAmount(10);
                    CItem item18 = new CItem(Material.MOB_SPAWNER).setName("§eZombie §fSpawner").setAmount(10);
                    vipver.setItem(0, item1.build());
                    vipver.setItem(1, item2.build());
                    vipver.setItem(2, item3.build());
                    vipver.setItem(3, item4.build());
                    vipver.setItem(4, item5.build());
                    vipver.setItem(5, item6.build());
                    vipver.setItem(6, item7.build());
                    vipver.setItem(7, item8.build());
                    vipver.setItem(8, item9.build());
                    vipver.setItem(9, item10.build());
                    vipver.setItem(10, item11.build());
                    vipver.setItem(11, item12.build());
                    vipver.setItem(12, item13.build());
                    vipver.setItem(13, item14.build());
                    vipver.setItem(14, item15.build());
                    vipver.setItem(15, item16.build());
                    vipver.setItem(16, item17.build());
                    vipver.setItem(17, item18.build());
                    Bukkit.getPlayer(player.getName()).openInventory(vipver);
                    }  
                    }
                    }
                    }



                    //FIM VIPS

                    }
                }
            }
}

