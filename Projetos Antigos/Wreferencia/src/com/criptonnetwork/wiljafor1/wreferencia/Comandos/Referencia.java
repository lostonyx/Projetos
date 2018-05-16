package com.criptonnetwork.wiljafor1.wreferencia.Comandos;
import com.criptonnetwork.wiljafor1.wreferencia.Main;
import com.criptonnetwork.wiljafor1.wreferencia.Util.CItem;
import com.criptonnetwork.wiljafor1.wreferencia.Util.SQL;
import com.criptonnetwork.wiljafor1.wreferencia.Util.ScrollerInventory;
import com.criptonnetwork.wiljafor1.wreferencia.Util.Title;
import com.criptonnetwork.wiljafor1.wreferencia.Util.UserData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Referencia implements CommandExecutor, Listener{
    
    Main plugin;
    UserData userdata;
    SQL sql;
    Title title;
    public static final String MenuPrincipal = "§9Referencias";
    public static final String MenuPrincipalRef = "§eReferencias: §7";
    public static final String MenuPrincipalTop = "§eTOP";
    public static final String MenuTop = "§9TOP";
    public static final String MenuRef = "§9Total de Referencias";
    
    public Referencia(Main pl, UserData us){
        this.plugin = pl;
        this.userdata = us;
    }
    
    public boolean onCommand(CommandSender cs, Command cmnd, String arg, String[] args) {
        if (arg.equalsIgnoreCase("ref") || arg.equalsIgnoreCase("referencia") || arg.equalsIgnoreCase("referencias")) {
            if(cs instanceof Player){
            Player p = (Player)cs;
            Player player = (Player)cs;  
                if(args.length == 0){
                    if(sql.VerificarDados(p.getUniqueId()) == false){
                        sql.CriarDados(p.getUniqueId(), p.getName(), plugin.FormatKey());
                        menu("principal", p);
                    }
                    else{
                        if(VeredicarTotal(p)==true){
                        menu("principal", p);  
                        }
                        else{
                        userdata.delChest(p.getUniqueId());
                        sql.DeletarDados(p.getUniqueId());
                        sql.CriarDados(p.getUniqueId(), p.getName(), plugin.FormatKey());
                        menu("principal", p);
                        }
                    }
                }
                else if(args.length == 1){
                    if(args[0].equalsIgnoreCase("total")){
                        if(sql.VerificarDados(p.getUniqueId()) == false){
                            sql.CriarDados(p.getUniqueId(), p.getName(), plugin.FormatKey());
                            AbrirMenu(p);
                        }
                        else{
                            if(VeredicarTotal(p)==true){
                            AbrirMenu(p);
                            }
                        }
                    }
                    else if(args[0].equalsIgnoreCase("top")){
                        menu("top", p);
                    }
                    else if(sql.getUUID(args[0]) != null){
                        if(sql.getStatus(p.getUniqueId())==true){
                            Title.sendTitle(player, 20, 60, 20, "&6&lReferencias", "&7Ja Indicou uma key!");
                        }
                        else{
                            Player pu = Bukkit.getPlayer(sql.getNick(args[0]));
                            List<String> lista = userdata.pegarChest(pu);
                            lista.add(p.getUniqueId().toString());
                            int savedRefs = userdata.pegarChestSalvar();
                            sql.atualizarDadosTotal(pu.getUniqueId());
                            sql.atualizarDadosIndicou(p.getUniqueId());
                            Title.sendTitle(player, 20, 60, 20, "&6&lReferencias", "&7Key indicada com sucesso!");
                        }
                    }
                    else{
                        Title.sendTitle(player, 20, 60, 20, "&6&lReferencias", "&7Key nao encontrada!");
                    }
                }
            }
            else{
                cs.sendMessage("§eWReferencias - Wiljafor1 - Versao: "+plugin.getDescription().getVersion());
                int savedRefs = userdata.pegarChestSalvar();
                cs.sendMessage("§eWReferencias - Players Cache: "+savedRefs);
            }

        }
        return true;
    }
    
    public void menu(String tipo, Player p){
        if(tipo.contains("principal")){
            Inventory inv = Bukkit.createInventory(null, 27, MenuPrincipal);
            CItem Cabeca = new CItem(Material.SKULL_ITEM).setDurability(3).setSkullOwner(p.getName()).setName("§e"+p.getName());
            CItem Key = new CItem(Material.NAME_TAG).setName("§eCodigo: §7"+SQL.getKey(p.getUniqueId()));
            CItem Referencias = new CItem(Material.REDSTONE_COMPARATOR).setName(MenuPrincipalRef+SQL.getTotal(p.getUniqueId()));
            CItem Top = new CItem(Material.PAINTING).setName(MenuPrincipalTop);
            inv.setItem(10, Cabeca.build());
            inv.setItem(12, Key.build());
            inv.setItem(14, Referencias.build());
            inv.setItem(16, Top.build());
            p.openInventory(inv);   
        }
        if(tipo.contains("top")){
            Inventory inv = Bukkit.createInventory(null, 27, MenuTop);
            for(int i = 0;i < sql.getTop().size(); i++){
            inv.setItem(11+i, sql.getTop().get(i));  
            }
            p.openInventory(inv);   
        }
    }
    
    public void AbrirMenu(Player p){
        ArrayList<ItemStack> items = new ArrayList<ItemStack>();
        List<String> lista = userdata.pegarChest(p);
        for (String siti : lista) {
            CItem Cabeca = new CItem(Material.SKULL_ITEM).setDurability(3).setSkullOwner(sql.getNickUUID(siti)).setName("§e"+sql.getNickUUID(siti));
            items.add(Cabeca.build());   
        }
        ScrollerInventory lul = new ScrollerInventory();
        lul.ScrollerInventory(items, MenuRef, p);
    }

    public boolean VeredicarTotal(Player p){
        List<String> lista = userdata.pegarChest(p);
        int total = SQL.getTotal(p.getUniqueId());
        if(lista.size() == total){
            return true;
        }
        else{
            return false; 
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onClick(InventoryClickEvent event){
       if(!(event.getWhoClicked() instanceof Player)) return;
        Player p = (Player) event.getWhoClicked();
        //Get the current scroller inventory the player is looking at, if the player is looking at one.
        if(!ScrollerInventory.users.containsKey(p.getUniqueId())) return;
        ScrollerInventory inv = ScrollerInventory.users.get(p.getUniqueId());
        if(event.getCurrentItem() == null) return;
        if(event.getCurrentItem().getItemMeta() == null) return;
        if(event.getCurrentItem().getItemMeta().getDisplayName() == null) return;
        //If the pressed item was a nextpage button
        if(event.getCurrentItem().getItemMeta().getDisplayName().equals(ScrollerInventory.nextPageName)){
            event.setCancelled(true);
            //If there is no next page, don't do anything
            if(inv.currpage >= inv.pages.size()-1){
                return;
            }else{
                //Next page exists, flip the page
                inv.currpage += 1;
                p.openInventory(inv.pages.get(inv.currpage));
            }
                        //if the pressed item was a previous page button
        }else if(event.getCurrentItem().getItemMeta().getDisplayName().equals(ScrollerInventory.previousPageName)){
             event.setCancelled(true);
             //If the page number is more than 0 (So a previous page exists)
             if(inv.currpage > 0){
             //Flip to previous page
                 inv.currpage -= 1;
                 p.openInventory(inv.pages.get(inv.currpage));
             }
        }
        else if(event.getCurrentItem().getItemMeta().getDisplayName().equals(ScrollerInventory.close)){
             event.setCancelled(true);
             p.closeInventory();
        }
    }
    
    @EventHandler
    public void Interações(InventoryClickEvent event){
        Player player = (Player)event.getWhoClicked(); 
        if(event.getInventory().getTitle().equalsIgnoreCase(MenuPrincipal)){
        event.setCancelled(true);    
        }
        if(event.getInventory().getTitle().equalsIgnoreCase(MenuRef)){
        event.setCancelled(true);    
        }
        if(event.getInventory().getTitle().equalsIgnoreCase(MenuTop)){
        event.setCancelled(true);    
        }
        if(event.getCurrentItem() != null){
            if(event.getCurrentItem().getType() != Material.AIR){
                if(event.getCurrentItem().hasItemMeta()){
                    String nome = player.getName();
                    if((event.getCurrentItem().getItemMeta().getDisplayName().contains(ScrollerInventory.close))){
                        event.setCancelled(true);
                        player.closeInventory();
                    }
                    if(event.getInventory().getTitle().equalsIgnoreCase(MenuPrincipal)){
                        if((event.getCurrentItem().getItemMeta().getDisplayName().contains(MenuPrincipalRef+SQL.getTotal(player.getUniqueId())))){
                        if(event.getWhoClicked() instanceof Player) {
                                player.closeInventory();
                                AbrirMenu(player);
                        }  
                        }
                        if((event.getCurrentItem().getItemMeta().getDisplayName().contains(MenuPrincipalTop))){
                        if(event.getWhoClicked() instanceof Player) {
                                player.closeInventory();
                                menu("top", player);
                        }  
                        }
                    }
                }
            }
        }
                
    }
    
}
