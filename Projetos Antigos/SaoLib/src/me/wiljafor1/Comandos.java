package me.wiljafor1;

import java.util.ArrayList;
import java.util.List;
import me.clip.placeholderapi.PlaceholderAPI;
import static me.wiljafor1.Main.plugin;
import static me.wiljafor1.MainConfig.config;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
public class Comandos implements Listener,CommandExecutor{
    Main plugin;
    public Comandos(Main plugin) {
        this.plugin = plugin;
    }  
    public static ArrayList Cmds = new ArrayList();
    
  @Override
  public boolean onCommand(CommandSender cs, Command cmnd, String string, String[] args) {
            Player p = (Player)cs;
        if(cs instanceof Player) {
            if(cs.hasPermission("sao.admin")) {//saoinfo staff add <grupo> <nome>
                if(cmnd.getName().equalsIgnoreCase("saoinfo"))
                    if(args.length == 4) {
                        if(args[0].equalsIgnoreCase("staff")) {
                            if(args[1].equalsIgnoreCase("add")){
                                if(args[2].contains("dono")){
                                List<String> rank = plugin.getConfig().getStringList("staff.dono");
                                if(rank.contains(args[3])){
                                    p.sendMessage("Esse player já está no grupo!");
                                }
                                else{
                                    rank.add(args[3]); 
                                    p.sendMessage("Player Setado! - "+ args[3]);
                                }
                                config.set("staff.dono", rank);
                                plugin.saveConfig();
                                
                                }
                                if(args[2].contains("subdono")){
                                List<String> rank = plugin.getConfig().getStringList("staff.subdono");
                                if(rank.contains(args[3])){
                                    p.sendMessage("Esse player já está no grupo!");
                                }
                                else{
                                    rank.add(args[3]);  
                                    p.sendMessage("Player Setado! - "+ args[3]);
                                }
                                config.set("staff.subdono", rank);
                                plugin.saveConfig();
                                
                                }
                                if(args[2].contains("administrador")){
                                List<String> rank = plugin.getConfig().getStringList("staff.admin");
                                if(rank.contains(args[3])){
                                    p.sendMessage("Esse player já está no grupo!");
                                }
                                else{
                                    rank.add(args[3]);   
                                    p.sendMessage("Player Setado! - "+ args[3]);
                                }
                                config.set("staff.admin", rank);
                                plugin.saveConfig();
                                
                                }
                                if(args[2].contains("moderador")){
                                List<String> rank = plugin.getConfig().getStringList("staff.mod");
                                if(rank.contains(args[3])){
                                    p.sendMessage("Esse player já está no grupo!");
                                }
                                else{
                                    rank.add(args[3]);   
                                    p.sendMessage("Player Setado! - "+ args[3]);
                                }
                                config.set("staff.mod", rank);
                                plugin.saveConfig();
                                
                                }
                                if(args[2].contains("ajudantes")){
                                List<String> rank = plugin.getConfig().getStringList("staff.help");
                                if(rank.contains(args[3])){
                                    p.sendMessage("Esse player já está no grupo!");
                                }
                                else{
                                    rank.add(args[3]); 
                                    p.sendMessage("Player Setado! - "+ args[3]);
                                }
                                config.set("staff.help", rank);
                                plugin.saveConfig();
                                
                                }
                            }
                            if(args[1].equalsIgnoreCase("del")){
                                if(args[2].contains("dono")){
                                List<String> rank = plugin.getConfig().getStringList("staff.dono");
                                rank.remove(args[3]);
                                config.set("staff.dono", rank);
                                plugin.saveConfig();
                                p.sendMessage("Player removido! - "+ args[3]);
                                }
                                if(args[2].contains("subdono")){
                                List<String> rank = plugin.getConfig().getStringList("staff.subdono");
                                rank.remove(args[3]);
                                config.set("staff.subdono", rank);
                                plugin.saveConfig();
                                p.sendMessage("Player removido! - "+ args[3]);
                                }
                                if(args[2].contains("administrador")){
                                List<String> rank = plugin.getConfig().getStringList("staff.admin");
                                rank.remove(args[3]);
                                config.set("staff.admin", rank);
                                plugin.saveConfig();
                                p.sendMessage("Player removido! - "+ args[3]);
                                }
                                if(args[2].contains("moderador")){
                                List<String> rank = plugin.getConfig().getStringList("staff.mod");
                                rank.remove(args[3]);
                                config.set("staff.mod", rank);
                                plugin.saveConfig();
                                p.sendMessage("Player removido! - "+ args[3]);
                                }
                                if(args[2].contains("ajudantes")){
                                List<String> rank = plugin.getConfig().getStringList("staff.help");
                                rank.remove(args[3]);
                                config.set("staff.help", rank);
                                plugin.saveConfig();
                                p.sendMessage("Player removido! - "+ args[3]);
                                }
                            }
                        }
                    }
                    else{
                        if(args[0].equalsIgnoreCase("reload")){
                        p.sendMessage("Recarregado!");
                        plugin.reload();
                        }
                        else{
                        p.sendMessage("use /saoinfo staff <add/del> <grupo> <nome>"); 
                        }
                    }
            }
        }
        else{
            cs.sendMessage("[CARDINAL] COMANDO PARA PLAYER");
        }
        return false;
    }

    
  @EventHandler(priority = EventPriority.HIGHEST)
  public void command(PlayerCommandPreprocessEvent e) {
    Player p = e.getPlayer();
    String[] args = e.getMessage().split(" ");
    if(Cmds.contains(args[0])){
        //e.setCancelled(true);
        String Comandos = args[0].replace("/", "");
        if(args[0].contains("/info")){
            e.setCancelled(true);
            p.sendMessage(config.getString("itag").replace("&", "§"));
            for(String linha:config.getStringList("info")){
            String Msg = linha.replace("&", "§");
            String MsgP = PlaceholderAPI.setPlaceholders(e.getPlayer(), Msg);
            p.sendMessage(MsgP);
            //p.sendMessage(linha.replace("&", "§").replace("%player%", p.getDisplayName()));
            }
        return;    
        }
        if(args[0].contains("/informacoes")){
            e.setCancelled(true);
            p.sendMessage(config.getString("itag").replace("&", "§"));
            for(String linha:config.getStringList("info")){
            String Msg = linha.replace("&", "§");
            String MsgP = PlaceholderAPI.setPlaceholders(e.getPlayer(), Msg);
            p.sendMessage(MsgP);
            }
        return;    
        }
        if(args[0].contains("/sao")){
            e.setCancelled(true);
            p.sendMessage(config.getString("itag").replace("&", "§"));
            for(String linha:config.getStringList("info")){
            String Msg = linha.replace("&", "§");
            String MsgP = PlaceholderAPI.setPlaceholders(e.getPlayer(), Msg);
            p.sendMessage(MsgP);
            }
        return;    
        }
        if(args[0].contains("/plugins")){
            e.setCancelled(true);
            p.sendMessage(config.getString("ptag").replace("&", "§"));
            for(String linha:config.getStringList("plugin")){
            String Msg = linha.replace("&", "§");
            String MsgP = PlaceholderAPI.setPlaceholders(e.getPlayer(), Msg);
            p.sendMessage(MsgP);
            }
        return;    
        }
        if(args[0].contains("/pl")){
            e.setCancelled(true);
            p.sendMessage(config.getString("ptag").replace("&", "§"));
            for(String linha:config.getStringList("plugin")){
            String Msg = linha.replace("&", "§");
            String MsgP = PlaceholderAPI.setPlaceholders(e.getPlayer(), Msg);
            p.sendMessage(MsgP);
            }
        return;    
        }
        if(args[0].contains("/dev")){
            e.setCancelled(true);
            p.sendMessage(config.getString("dtag").replace("&", "§"));
            for(String linha:config.getStringList("dev")){
            String Msg = linha.replace("&", "§");
            String MsgP = PlaceholderAPI.setPlaceholders(e.getPlayer(), Msg);
            p.sendMessage(MsgP);
            }
        return;    
        }
        if(args[0].contains("/bukkit")){
            e.setCancelled(true);
            p.sendMessage(config.getString("dtag").replace("&", "§"));
            for(String linha:config.getStringList("dev")){
            String Msg = linha.replace("&", "§");
            String MsgP = PlaceholderAPI.setPlaceholders(e.getPlayer(), Msg);
            p.sendMessage(MsgP);
            }
        return;    
        }
        if(args[0].contains("/version")){
            e.setCancelled(true);
            p.sendMessage(config.getString("dtag").replace("&", "§"));
            for(String linha:config.getStringList("dev")){
            String Msg = linha.replace("&", "§");
            String MsgP = PlaceholderAPI.setPlaceholders(e.getPlayer(), Msg);
            p.sendMessage(MsgP);
            }
        return;    
        }
        if(args[0].contains("/ajuda")){
            e.setCancelled(true);
            p.sendMessage(config.getString("atag").replace("&", "§"));
            for(String linha:config.getStringList("ajuda")){
            String Msg = linha.replace("&", "§");
            String MsgP = PlaceholderAPI.setPlaceholders(e.getPlayer(), Msg);
            p.sendMessage(MsgP);
            }
        return;    
        }
        if(args[0].contains("/help")){
            e.setCancelled(true);
            p.sendMessage(config.getString("atag").replace("&", "§"));
            for(String linha:config.getStringList("ajuda")){
            String Msg = linha.replace("&", "§");
            String MsgP = PlaceholderAPI.setPlaceholders(e.getPlayer(), Msg);
            p.sendMessage(MsgP);
            }
        return;    
        }
        if(args[0].contains("/ajd")){
            e.setCancelled(true);
            p.sendMessage(config.getString("atag").replace("&", "§"));
            for(String linha:config.getStringList("ajuda")){
            String Msg = linha.replace("&", "§");
            String MsgP = PlaceholderAPI.setPlaceholders(e.getPlayer(), Msg);
            p.sendMessage(MsgP);
            }
        return;    
        }
        if(args[0].contains("/comandos")){
            e.setCancelled(true);
            p.sendMessage(config.getString("ctag").replace("&", "§"));
            for(String linha:config.getStringList("comandos")){
            String Msg = linha.replace("&", "§");
            String MsgP = PlaceholderAPI.setPlaceholders(e.getPlayer(), Msg);
            p.sendMessage(MsgP);
            }
        return;    
        }
        if(args[0].contains("/cmds")){
            e.setCancelled(true);
            p.sendMessage(config.getString("ctag").replace("&", "§"));
            for(String linha:config.getStringList("comandos")){
            String Msg = linha.replace("&", "§");
            String MsgP = PlaceholderAPI.setPlaceholders(e.getPlayer(), Msg);
            p.sendMessage(MsgP);
            }
        return;    
        }
        if(args[0].contains("/formulario")){
            e.setCancelled(true);
            p.sendMessage(config.getString("ftag").replace("&", "§"));
            for(String linha:config.getStringList("formulario")){
            String Msg = linha.replace("&", "§");
            String MsgP = PlaceholderAPI.setPlaceholders(e.getPlayer(), Msg);
            p.sendMessage(MsgP);
            }
        return;    
        }
        if(args[0].contains("/form")){
            e.setCancelled(true);
            p.sendMessage(config.getString("ftag").replace("&", "§"));
            for(String linha:config.getStringList("formulario")){
            String Msg = linha.replace("&", "§");
            String MsgP = PlaceholderAPI.setPlaceholders(e.getPlayer(), Msg);
            p.sendMessage(MsgP);
            }
        return;    
        }
        if(args[0].contains("/serstaff")){
            e.setCancelled(true);
            p.sendMessage(config.getString("ftag").replace("&", "§"));
            for(String linha:config.getStringList("formulario")){
            String Msg = linha.replace("&", "§");
            String MsgP = PlaceholderAPI.setPlaceholders(e.getPlayer(), Msg);
            p.sendMessage(MsgP);
            }
        return;    
        }
        /*
        if(args[0].contains("/vip")){
            if(args[1].isEmpty()){
            e.setCancelled(true);
            p.sendMessage(config.getString("vtag").replace("&", "§"));
            for(String linha:config.getStringList("vip")){
            String Msg = linha.replace("&", "§");
            String MsgP = PlaceholderAPI.setPlaceholders(e.getPlayer(), Msg);
            p.sendMessage(MsgP);
            }
            }
            else{
            e.setCancelled(false);
            return;   
            }
        }*/
        if(args[0].contains("/vips")){
            e.setCancelled(true);
            p.sendMessage(config.getString("vtag").replace("&", "§"));
            for(String linha:config.getStringList("vip")){
            String Msg = linha.replace("&", "§");
            String MsgP = PlaceholderAPI.setPlaceholders(e.getPlayer(), Msg);
            p.sendMessage(MsgP);
            }
        return;    
        }
        if(args[0].contains("/doacao")){
            e.setCancelled(true);
            p.sendMessage(config.getString("vtag").replace("&", "§"));
            for(String linha:config.getStringList("vip")){
            String Msg = linha.replace("&", "§");
            String MsgP = PlaceholderAPI.setPlaceholders(e.getPlayer(), Msg);
            p.sendMessage(MsgP);
            }
        return;    
        }
        if(args[0].contains("/comprar")){
            e.setCancelled(true);
            p.sendMessage(config.getString("vtag").replace("&", "§"));
            for(String linha:config.getStringList("vip")){
            String Msg = linha.replace("&", "§");
            String MsgP = PlaceholderAPI.setPlaceholders(e.getPlayer(), Msg);
            p.sendMessage(MsgP);
            }
        return;    
        }
        if(args[0].contains("/site")){
            e.setCancelled(true);
            p.sendMessage(config.getString("itagst").replace("&", "§"));
            for(String linha:config.getStringList("site")){
            String Msg = linha.replace("&", "§");
            String MsgP = PlaceholderAPI.setPlaceholders(e.getPlayer(), Msg);
            p.sendMessage(MsgP);
            }
        return;    
        }
        if(args[0].contains("/social")){
            e.setCancelled(true);
            p.sendMessage(config.getString("itags").replace("&", "§"));
            for(String linha:config.getStringList("social")){
            String Msg = linha.replace("&", "§");
            String MsgP = PlaceholderAPI.setPlaceholders(e.getPlayer(), Msg);
            p.sendMessage(MsgP);
            }
        return;    
        }
        if(args[0].contains("/staff")){
            e.setCancelled(true);
            p.sendMessage(config.getString("stag").replace("&", "§"));
            p.sendMessage(""+ config.get("stagD").toString().replace("&", "§")); 
            for(String linha:config.getStringList("staff.dono")){
                if(linha.length() == 0){
                p.sendMessage(config.getString("Sp"));
                }
                else{
                p.sendMessage(linha.replace("&", "§").replace("%player%", p.getDisplayName()));    
                }
            }
            p.sendMessage(""+ config.get("stagSD").toString().replace("&", "§")); 
            for(String linha:config.getStringList("staff.subdono")){
                if(linha.length() == 0){
                p.sendMessage(config.getString("Sp"));
                }
                else{
                p.sendMessage(linha.replace("&", "§").replace("%player%", p.getDisplayName()));    
                }
            }
            p.sendMessage(""+ config.get("stagA").toString().replace("&", "§")); 
            for(String linha:config.getStringList("staff.admin")){
                if(linha.length() == 0){
                p.sendMessage(config.getString("Sp"));
                }
                else{
                p.sendMessage(linha.replace("&", "§").replace("%player%", p.getDisplayName()));    
                }
            }
            p.sendMessage(""+ config.get("stagM").toString().replace("&", "§")); 
            for(String linha:config.getStringList("staff.mod")){
                if(linha.length() == 0){
                p.sendMessage(config.getString("Sp"));
                }
                else{
                p.sendMessage(linha.replace("&", "§").replace("%player%", p.getDisplayName()));    
                }
            }
            p.sendMessage(""+ config.get("stagH").toString().replace("&", "§")); 
            for(String linha:config.getStringList("staff.help")){
                if(linha.length() == 0){
                p.sendMessage(config.getString("Sp"));
                }
                else{
                p.sendMessage(linha.replace("&", "§").replace("%player%", p.getDisplayName()));    
                }
            }
        return;    
        }
        
    }
  }

}
