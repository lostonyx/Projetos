package com.criptonnetwork.wiljafor1.wbau.sistema;

import com.criptonnetwork.wiljafor1.wbau.Main;
import com.criptonnetwork.wiljafor1.wbau.util.BauManager;
import com.criptonnetwork.wiljafor1.wbau.util.Title;
import com.criptonnetwork.wiljafor1.wreferencia.Util.CItem;
import com.criptonnetwork.wiljafor1.wreferencia.Util.ScrollerInventory;
import java.util.HashMap;
import java.util.List;
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
import org.bukkit.inventory.Inventory;

public class Bau implements CommandExecutor, Listener{ 
    Main plugin;
    BauManager bau;
    Title title;
    public static final String MenuPrincipal = "Bau - ";
    public static HashMap<String,String> bauplayer = new HashMap<String,String>();
    
    public Bau(Main pl, BauManager b){
        this.plugin = pl;
        this.bau = b;
    }
    
    public boolean onCommand(CommandSender cs, Command cmnd, String arg, String[] args) {
        if (arg.equalsIgnoreCase("bau")) {
            if(cs instanceof Player){
            Player p = (Player)cs;
            Player player = (Player)cs;  
                if(args.length == 0){
                    menu(TipoMenu.PRINCIPAL, p);
                }
                else if(args.length == 1){
                    if((p.isOp()) && (p.hasPermission("*"))){
                        bauplayer.put(p.getName(), args[0]);
                        menu(TipoMenu.OutroPlayer, p); 
                    }
                }
            }
            else{
                cs.sendMessage("§eWReferencias - Wiljafor1 - Versao: "+plugin.getDescription().getVersion());
                
                //cs.sendMessage("§eWReferencias - Players Cache: "+savedRefs);
            }

        }
        return true;
    }
    
    public void menu(TipoMenu tipo, Player p){
        if(tipo == TipoMenu.PRINCIPAL){
        Inventory inv = Bukkit.createInventory(null,5 * 9, MenuPrincipal+p.getName());
        CItem Bau1 = new CItem(Material.NOTE_BLOCK).setName("§eBau #1").setGlow(true);
        CItem Bau2 = new CItem(Material.NOTE_BLOCK).setName("§eBau #2").setGlow(true);
        CItem Bau3 = new CItem(Material.NOTE_BLOCK).setName("§eBau #3").setGlow(true);
        CItem Bau4 = new CItem(Material.NOTE_BLOCK).setName("§eBau #4").setGlow(true);
        CItem Bau5 = new CItem(Material.NOTE_BLOCK).setName("§eBau #5").setGlow(true);
        CItem Mesa = new CItem(Material.WORKBENCH).setName("§eMesa De Trabalho").setGlow(true);
        CItem Fim = new CItem(Material.ENDER_CHEST).setName("§eBau do Fim").setGlow(true);
        if(p.hasPermission("wbau.lvl1")){
        Bau1 = new CItem(Material.NOTE_BLOCK).setName("§7Bau #1").setGlow(false);    
        }
        if(p.hasPermission("wbau.lvl2")){
        Bau2 = new CItem(Material.NOTE_BLOCK).setName("§7Bau #2").setGlow(false); 
        }
        if(p.hasPermission("wbau.lvl3")){
        Bau3 = new CItem(Material.NOTE_BLOCK).setName("§7Bau #3").setGlow(false); 
        }
        if(p.hasPermission("wbau.lvl4")){
        Bau4 = new CItem(Material.NOTE_BLOCK).setName("§7Bau #4").setGlow(false); 
        }
        if(p.hasPermission("wbau.lvl5")){
        Bau5 = new CItem(Material.NOTE_BLOCK).setName("§7Bau #5").setGlow(false); 
        }
        if(p.hasPermission("wbau.mesa")){
        Mesa = new CItem(Material.WORKBENCH).setName("§7Mesa De Trabalho").setGlow(false); 
        }
        if(p.hasPermission("wbau.fim")){
        Fim = new CItem(Material.ENDER_CHEST).setName("§7Bau do Fim").setGlow(false);
        }
        inv.setItem(11, Bau1.build());
        inv.setItem(12, Bau2.build());
        inv.setItem(13, Bau3.build());
        inv.setItem(14, Bau4.build());
        inv.setItem(15, Bau5.build());
        inv.setItem(30, Mesa.build());
        inv.setItem(32, Fim.build());
        p.openInventory(inv);
        }
        else if(tipo == TipoMenu.OutroPlayer){
        Inventory inv = Bukkit.createInventory(null,5 * 9, MenuPrincipal+bauplayer.get(p.getName()));
        CItem Bau1 = new CItem(Material.NOTE_BLOCK).setName("§eBau #1").setGlow(true);
        CItem Bau2 = new CItem(Material.NOTE_BLOCK).setName("§eBau #2").setGlow(true);
        CItem Bau3 = new CItem(Material.NOTE_BLOCK).setName("§eBau #3").setGlow(true);
        CItem Bau4 = new CItem(Material.NOTE_BLOCK).setName("§eBau #4").setGlow(true);
        CItem Bau5 = new CItem(Material.NOTE_BLOCK).setName("§eBau #5").setGlow(true);
        CItem Mesa = new CItem(Material.WORKBENCH).setName("§eMesa De Trabalho").setGlow(true);
        CItem Fim = new CItem(Material.ENDER_CHEST).setName("§eBau do Fim").setGlow(true);
        if(p.hasPermission("wbau.lvl1")){
        Bau1 = new CItem(Material.NOTE_BLOCK).setName("§7Bau #1").setGlow(false);    
        }
        if(p.hasPermission("wbau.lvl2")){
        Bau2 = new CItem(Material.NOTE_BLOCK).setName("§7Bau #2").setGlow(false); 
        }
        if(p.hasPermission("wbau.lvl3")){
        Bau3 = new CItem(Material.NOTE_BLOCK).setName("§7Bau #3").setGlow(false); 
        }
        if(p.hasPermission("wbau.lvl4")){
        Bau4 = new CItem(Material.NOTE_BLOCK).setName("§7Bau #4").setGlow(false); 
        }
        if(p.hasPermission("wbau.lvl5")){
        Bau5 = new CItem(Material.NOTE_BLOCK).setName("§7Bau #5").setGlow(false); 
        }
        if(p.hasPermission("wbau.mesa")){
        Mesa = new CItem(Material.WORKBENCH).setName("§7Mesa De Trabalho").setGlow(false); 
        }
        if(p.hasPermission("wbau.fim")){
        Fim = new CItem(Material.ENDER_CHEST).setName("§7Bau do Fim").setGlow(false);
        }
        inv.setItem(11, Bau1.build());
        inv.setItem(12, Bau2.build());
        inv.setItem(13, Bau3.build());
        inv.setItem(14, Bau4.build());
        inv.setItem(15, Bau5.build());
        inv.setItem(30, Mesa.build());
        inv.setItem(32, Fim.build());
        p.openInventory(inv);
        }
    }
    
    @EventHandler
    public void Interações(InventoryClickEvent event){
        Player player = (Player)event.getWhoClicked(); 
        if(event.getInventory().getTitle().equalsIgnoreCase(MenuPrincipal+player.getName())){
        event.setCancelled(true);    
        }
        if(event.getInventory().getTitle().equalsIgnoreCase(MenuPrincipal+bauplayer.get(player.getName()))){
        event.setCancelled(true);    
        }
        if(event.getCurrentItem() != null){
            if(event.getCurrentItem().getType() != Material.AIR){
                if(event.getCurrentItem().hasItemMeta()){
                    String nome = player.getName();
                    if(event.getInventory().getTitle().equalsIgnoreCase(MenuPrincipal+player.getName())){
                        if((event.getCurrentItem().getItemMeta().getDisplayName().contains("§7Bau #1"))){
                        if(event.getWhoClicked() instanceof Player) {
                                player.closeInventory();
                                Inventory chest = bau.pegarChest(player.getName()+".1",2);
                                player.openInventory(chest);
                                int savedChests = bau.pegarChestSalvar();
                        }  
                        }
                        if((event.getCurrentItem().getItemMeta().getDisplayName().contains("§7Bau #2"))){
                        if(event.getWhoClicked() instanceof Player) {
                                player.closeInventory();
                                Inventory chest = bau.pegarChest(player.getName()+".2",3);
                                player.openInventory(chest);
                                int savedChests = bau.pegarChestSalvar();
                        }  
                        }
                        if((event.getCurrentItem().getItemMeta().getDisplayName().contains("§7Bau #3"))){
                        if(event.getWhoClicked() instanceof Player) {
                                player.closeInventory();
                                Inventory chest = bau.pegarChest(player.getName()+".3",4);
                                player.openInventory(chest);
                                int savedChests = bau.pegarChestSalvar();
                        }  
                        }
                        if((event.getCurrentItem().getItemMeta().getDisplayName().contains("§7Bau #4"))){
                        if(event.getWhoClicked() instanceof Player) {
                                player.closeInventory();
                                Inventory chest = bau.pegarChest(player.getName()+".4",5);
                                player.openInventory(chest);
                                int savedChests = bau.pegarChestSalvar();
                        }  
                        }
                        if((event.getCurrentItem().getItemMeta().getDisplayName().contains("§7Bau #5"))){
                        if(event.getWhoClicked() instanceof Player) {
                                player.closeInventory();
                                Inventory chest = bau.pegarChest(player.getName()+".5",6);
                                player.openInventory(chest);
                                int savedChests = bau.pegarChestSalvar();
                        }  
                        }
                        if((event.getCurrentItem().getItemMeta().getDisplayName().contains("§7Bau do Fim"))){
                        if(event.getWhoClicked() instanceof Player) {
                                player.closeInventory();
                                player.openInventory(player.getEnderChest());
                        }  
                        }
                        if((event.getCurrentItem().getItemMeta().getDisplayName().contains("§7Mesa De Trabalho"))){
                        if(event.getWhoClicked() instanceof Player) {
                                player.closeInventory();
                                player.openWorkbench(null, true);
                        }  
                        }
                    }
                    if(event.getInventory().getTitle().equalsIgnoreCase(MenuPrincipal+bauplayer.get(player.getName()))){
                        String nomep = event.getInventory().getTitle().replace(MenuPrincipal, "");
                        if((event.getCurrentItem().getItemMeta().getDisplayName().contains("§7Bau #1"))){
                        if(event.getWhoClicked() instanceof Player) {
                                player.closeInventory();
                                Inventory chest = bau.pegarChest(nomep+".1", 2);
                                player.openInventory(chest);
                                int savedChests = bau.pegarChestSalvar();
                        }  
                        }
                        if((event.getCurrentItem().getItemMeta().getDisplayName().contains("§7Bau #2"))){
                        if(event.getWhoClicked() instanceof Player) {
                                player.closeInventory();
                                Inventory chest = bau.pegarChest(nomep+".2", 3);
                                player.openInventory(chest);
                                int savedChests = bau.pegarChestSalvar();
                        }  
                        }
                        if((event.getCurrentItem().getItemMeta().getDisplayName().contains("§7Bau #3"))){
                        if(event.getWhoClicked() instanceof Player) {
                                player.closeInventory();
                                Inventory chest = bau.pegarChest(nomep+".3", 4);
                                player.openInventory(chest);
                                int savedChests = bau.pegarChestSalvar();
                        }  
                        }
                        if((event.getCurrentItem().getItemMeta().getDisplayName().contains("§7Bau #4"))){
                        if(event.getWhoClicked() instanceof Player) {
                                player.closeInventory();
                                Inventory chest = bau.pegarChest(nomep+".4", 5);
                                player.openInventory(chest);
                                int savedChests = bau.pegarChestSalvar();
                        }  
                        }
                        if((event.getCurrentItem().getItemMeta().getDisplayName().contains("§7Bau #5"))){
                        if(event.getWhoClicked() instanceof Player) {
                                player.closeInventory();
                                Inventory chest = bau.pegarChest(nomep+".5", 6);
                                player.openInventory(chest);
                                int savedChests = bau.pegarChestSalvar();
                        }  
                        }
                        if((event.getCurrentItem().getItemMeta().getDisplayName().contains("§7Bau do Fim"))){
                        if(event.getWhoClicked() instanceof Player) {
                                player.closeInventory();
                                player.openInventory(Bukkit.getPlayer(bauplayer.get(player.getName())).getEnderChest());
                        }  
                        }
                        if((event.getCurrentItem().getItemMeta().getDisplayName().contains("§7Mesa De Trabalho"))){
                        if(event.getWhoClicked() instanceof Player) {
                                player.closeInventory();
                                player.openWorkbench(null, true);
                        }  
                        }
                    }
                }
            }
        }
                
    }
}
