package me.wiljafor1.comandos;

import com.google.common.base.Optional;
import com.intellectualcrafters.plot.commands.CommandCategory;
import com.intellectualcrafters.plot.commands.MainCommand;
import com.intellectualcrafters.plot.commands.RequiredType;
import com.intellectualcrafters.plot.commands.SubCommand;
import com.intellectualcrafters.plot.config.C;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.intellectualcrafters.plot.database.*;
import static com.intellectualcrafters.plot.database.DBFunc.close;
import static com.intellectualcrafters.plot.database.DBFunc.dbManager;
import com.intellectualcrafters.plot.flag.Flags;
import com.intellectualcrafters.plot.object.Location;
import com.intellectualcrafters.plot.object.Plot;
import com.intellectualcrafters.plot.object.PlotId;
import com.intellectualcrafters.plot.object.PlotPlayer;
import com.intellectualcrafters.plot.object.RunnableVal2;
import com.intellectualcrafters.plot.object.RunnableVal3;
import com.intellectualcrafters.plot.util.EconHandler;
import com.intellectualcrafters.plot.util.MainUtil;
import com.intellectualcrafters.plot.util.UUIDHandler;
import com.plotsquared.general.commands.CommandDeclaration;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Set;
import me.wiljafor1.SaoPlot;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
@CommandDeclaration(
        command = "comprar",
        description = "Buy the plot you are standing on",
        usage = "/plot comprar",
        permission = "plots.buy",
        category = CommandCategory.CLAIMING,
        requiredType = RequiredType.NONE)
public class Comprar extends SubCommand  implements CommandExecutor{
    SaoPlot plugin;
    Server sv = Bukkit.getServer();
    public Inventory inv = null;
    public Comprar(SaoPlot plugin) {
        this.plugin = plugin;
    }  
    public Comprar(){
        MainCommand.getInstance().addCommand(this);
    }
@Override
public boolean onCommand(CommandSender cs, Command cmnd, String string, String[] args) {  
    Player p = (Player)cs;
    inv = sv.createInventory(null, 9, "§6Venda De Terreno:");
    ItemStack t1 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)5);
    ItemMeta t1Meta = t1.getItemMeta();
    t1Meta.setDisplayName("§bTerreno MEDIO");
    ArrayList<String> lore1 = new ArrayList<String>();
    lore1.add("VIP 20k");
    lore1.add("NAO-VIP 60k");
    t1Meta.setLore(lore1);
    t1.setItemMeta(t1Meta);
    inv.setItem(0, t1);
    ItemStack t2 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)5);
    ItemMeta t2Meta = t2.getItemMeta();
    t2Meta.setDisplayName("§bTerreno GRANDE");
    ArrayList<String> lore2 = new ArrayList<String>();
    lore2.add("VIP 100k");
    lore2.add("NAO-VIP 300k");
    t2Meta.setLore(lore2);
    t2.setItemMeta(t2Meta);
    inv.setItem(1, t2);
    ItemStack t3 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)5);
    ItemMeta t3Meta = t3.getItemMeta();
    t3Meta.setDisplayName("§bTerreno GIGANTE");
    ArrayList<String> lore3 = new ArrayList<String>();
    lore3.add("VIP 1kk");
    lore3.add("NAO-VIP 3kk");
    t3Meta.setLore(lore3);
    t3.setItemMeta(t3Meta);
    inv.setItem(2, t3);
        if(cs instanceof Player) {
            if(cs.hasPermission("sao.comprar")) {
                if(cmnd.getName().equalsIgnoreCase("terreno")){
                    if(args.length == 0) {
                        p.sendMessage("$9[Terrenos] $2 Comandos disponíveis neste local:");
                        p.sendMessage("$4» $2/terreno info");
                        p.sendMessage("$4» $2/terreno lista");
                        p.sendMessage("$4» $2/terreno comprar");
                        p.sendMessage("$4» $2/terreno amigo <add | del> <nick>");
                        p.sendMessage("$4» $2/terreno expulsar <nick>");
                        p.sendMessage("$4» $2/terreno renomear <novo nome do terreno> -- NAO DISPONIVEL");
                        p.sendMessage("$4» $2/terreno vender <valor> -- NAO DISPONIVEL");
                        p.sendMessage("$4» $2/terreno deletar -- NAO DISPONIVEL");
                    }
                    if(args.length == 1) {
                        if(args[0].equalsIgnoreCase("comprar")) {
                           p.openInventory(inv); 
                        }
                        if(args[0].equalsIgnoreCase("info")) {
                           p.sendMessage("info");
                        }
                        if(args[0].equalsIgnoreCase("lista")) {
                           p.sendMessage("lista");
                        }
                    }
                }
            }
        }
        else{
            cs.sendMessage("[CARDINAL] COMANDO PARA PLAYER");
        }
        return false;
    }

@Override
public boolean onCommand(PlotPlayer pp, String[] strings) {
        Location loc = pp.getLocation();
        PlotId plot1 = MainUtil.getPlotId(loc);
        inv = sv.createInventory(null, 9, "§6Venda De Terreno:");
        ItemStack t1 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)5);
        ItemMeta t1Meta = t1.getItemMeta();
        t1Meta.setDisplayName("§bTerreno MEDIO");
        ArrayList<String> lore1 = new ArrayList<String>();
        lore1.add("VIP 20k");
        lore1.add("NAO-VIP 60k");
        t1Meta.setLore(lore1);
        t1.setItemMeta(t1Meta);
        inv.setItem(0, t1);
        ItemStack t2 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)5);
        ItemMeta t2Meta = t2.getItemMeta();
        t2Meta.setDisplayName("§bTerreno GRANDE");
        ArrayList<String> lore2 = new ArrayList<String>();
        lore2.add("VIP 100k");
        lore2.add("NAO-VIP 300k");
        t2Meta.setLore(lore2);
        t2.setItemMeta(t2Meta);
        inv.setItem(1, t2);
        ItemStack t3 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)5);
        ItemMeta t3Meta = t3.getItemMeta();
        t3Meta.setDisplayName("§bTerreno GIGANTE");
        ArrayList<String> lore3 = new ArrayList<String>();
        lore3.add("VIP 1kk");
        lore3.add("NAO-VIP 3kk");
        t3Meta.setLore(lore3);
        t3.setItemMeta(t3Meta);
        inv.setItem(2, t3);
        Bukkit.getPlayer(pp.getName()).openInventory(inv);
        //if (plot1 == null) {
        //    pp.sendMessage("fora");
        //    return false;
        //}
        //else{
        //    pp.sendMessage("dentro");
        //}
        return true;
    }

}
