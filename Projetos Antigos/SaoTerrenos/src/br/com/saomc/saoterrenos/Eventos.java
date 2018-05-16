package br.com.saomc.saoterrenos;

import com.sk89q.worldedit.BlockVector;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

public class Eventos implements Listener{
    WorldGuardPlugin getWorldGuard() {
    Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("WorldGuard");
    if (plugin == null || !(plugin instanceof WorldGuardPlugin)) {
        return null; 
    }
        return (WorldGuardPlugin) plugin;
    }
    WorldGuardPlugin worldGuard = getWorldGuard();
    Main plugin;
    
    public void CancelarCompra(BlockBreakEvent e) {
        Block b = e.getBlock();
        Player p = e.getPlayer();
        if (b.getState() instanceof Sign) {
            BlockState bq = b.getState();
            Sign sign = (Sign) bq;
            String[] ln = sign.getLines();
            if ((ln[0].equalsIgnoreCase(""+p.getName().toString())) && (ln[1].equalsIgnoreCase("Vendendo")) && (ln[2].equalsIgnoreCase("Preco")) && (!ln[3].isEmpty())) {
                p.sendMessage(plugin.getConfig().getString("Mensagem.Sucesso.Cancelou_Venda").replace("&", "§"));
                e.setCancelled(true);
                e.getBlock().setType(Material.AIR);
            }
        }
    }
    
    public void PlacaInvalida(SignChangeEvent e) {
        Block b = e.getBlock();
        Player p = e.getPlayer();
        if ((!e.getLine(0).isEmpty()) && (e.getLine(1).equalsIgnoreCase("Vendendo")) && (e.getLine(2).equalsIgnoreCase("Preco")) && (!e.getLine(3).isEmpty())) {
            e.setCancelled(true);
            e.getBlock().breakNaturally();
            p.sendMessage(plugin.getConfig().getString("Mensagem.Erro.Placa_Invalida").replace("&", "§"));
        }
    }
    
    public void Katiau(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (p.getName().equals("MrDery")) {
            p.sendMessage("[SAO] Este servidor é feito a mão lul!");
            Bukkit.getPlayer("MrDery").kickPlayer("§fvlw flw");
        }
        if (p.getName().equals("Wiljafor1")) {
            p.sendMessage("[SAO] Plugin ta on!");
        }
    }
    
    private void Method44(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Material a1 = e.getClickedBlock().getType();
            Material a2 = Material.WALL_SIGN;
            label1: {
                if (a1 == a2) {
                    return;
                }
                if (e.getClickedBlock().getType() != org.bukkit.Material.SIGN_POST) {
                    return;
                }
            }
            //BlockState a3 = e.getClickedBlock().getState();
            BlockState bq = e.getClickedBlock().getState();
            Sign sign = (Sign) bq;
            String[] ln = sign.getLines();
            if ((!ln[0].isEmpty()) && (ln[1].equals("Vendendo")) && (ln[2].equals("Preco")) && ((!ln[3].isEmpty()))) {
                if (p.getName().equalsIgnoreCase(ln[0])) {
                    p.sendMessage(plugin.getConfig().getString("Mensagem.Erro.Nao_Pode_Comprar_Proprio_Terreno").replace("&", "§"));
                    return;
                }
                //plugin.Method6();
                if (package2.package1.package0.Class1.Method33(p) < plugin.getConfig().getInt(new StringBuilder("Config.Grupos.").append(package2.package1.package0.Class0.Field1.getPrimaryGroup(p)).toString())) {
                    com.sk89q.worldguard.protection.managers.RegionManager a4 = Field9.getRegionManager(p.getWorld());
                    com.sk89q.worldguard.protection.ApplicableRegionSet a5 = a4.getApplicableRegions(p.getLocation());
                    if (a5.size() == 0) {
                        p.sendMessage(plugin.getConfig().getString("Mensagem.Erro.Nao_Esta_Em_Uma_Area").replace("&", "§"));
                        return;
                    }
                    ((Object)a5).toString().toLowerCase();
                    String s = ((com.sk89q.worldguard.protection.regions.ProtectedRegion)a5.iterator().next()).getId();
                    com.sk89q.worldguard.protection.regions.ProtectedRegion a6 = a4.getRegion(s);
                    //plugin.Method6();
                    if (package2.package1.package0.Class1.Method10(p, s) > 1) {
                        p.sendMessage(plugin.getConfig().getString("Mensagem.Erro.Placas").replace("&", "§"));
                        return;
                    }
                    if (!a6.getOwners().contains(((org.bukkit.block.Sign)(Object)a3).getLine(0).replace((CharSequence)(Object)"\u00a79", (CharSequence)(Object)""))) {
                        p.sendMessage(plugin.getConfig().getString("Mensagem.Erro.Nao_Eh_Dono").replace("&", "§").replace((CharSequence)(Object)"{player}", (CharSequence)(Object)((org.bukkit.block.Sign)(Object)a3).getLine(0).replace((CharSequence)(Object)"\u00a79", (CharSequence)(Object)"")));
                        return;
                    }
                    //plugin.Method6();
                    if (package2.package1.package0.Class1.Method9(p, a6.getId().split("_")[1].toLowerCase(), p.getWorld().getName())) {
                        p.sendMessage(plugin.getConfig().getString("Mensagem.Erro.Ja_Existe_Terreno_Com_O_Nome").replace("&", "§").replace((CharSequence)(Object)"@area", (CharSequence)(Object)a6.getId().split("_")[1]));
                        return;
                    }
                    if (!package2.package1.package0.Class0.Field0.has((org.bukkit.OfflinePlayer)(Object)p, (double)Integer.parseInt(((org.bukkit.block.Sign)(Object)a3).getLine(3).replace((CharSequence)(Object)"\u00a79", (CharSequence)(Object)"")))) {
                        p.sendMessage(plugin.getConfig().getString("Mensagem.Erro.Sem_Money_Comprar").replace("&", "§"));
                        return;
                    }
                    String s0 = ((org.bukkit.block.Sign)(Object)a3).getLine(0).replace((CharSequence)(Object)"\u00a79", (CharSequence)(Object)"\u00a7");
                    int i = Integer.parseInt(((org.bukkit.block.Sign)(Object)a3).getLine(3).replace((CharSequence)(Object)"\u00a79", (CharSequence)(Object)""));
                    String s1 = a6.getId().split("_")[1];
                    package2.package1.package0.Class0.Field0.depositPlayer(plugin.getServer().getOfflinePlayer(s0), (double)i);
                    package2.package1.package0.Class0.Field0.withdrawPlayer((org.bukkit.OfflinePlayer)(Object)p, (double)i);
                    e.getClickedBlock().setType(Material.AIR);
                    try {
                        a4.removeRegion(s);
                        BlockVector a7 = new BlockVector(a6.getMinimumPoint().getBlockX(), a6.getMinimumPoint().getBlockY(), a6.getMinimumPoint().getBlockZ());
                        BlockVector a8 = new BlockVector(a6.getMaximumPoint().getBlockX(), a6.getMaximumPoint().getBlockY(), a6.getMaximumPoint().getBlockZ());
                        com.sk89q.worldguard.protection.regions.ProtectedCuboidRegion a9 = new com.sk89q.worldguard.protection.regions.ProtectedCuboidRegion(new StringBuilder(String.valueOf((Object)p.getName().toLowerCase())).append("_").append(s1).toString(), a7, a8);
                        com.sk89q.worldguard.domains.DefaultDomain a10 = new com.sk89q.worldguard.domains.DefaultDomain();
                        a4.addRegion((com.sk89q.worldguard.protection.regions.ProtectedRegion)a9);
                        a9.setPriority(100);
                        a10.addPlayer(p.getName());
                        a9.setOwners(a10);
                        a9.setFlag((com.sk89q.worldguard.protection.flags.Flag)com.sk89q.worldguard.protection.flags.DefaultFlag.PVP, (Object)com.sk89q.worldguard.protection.flags.DefaultFlag.PVP.parseInput(Field9, (org.bukkit.command.CommandSender)(Object)p, "allow"));
                        a9.setFlag((com.sk89q.worldguard.protection.flags.Flag)com.sk89q.worldguard.protection.flags.DefaultFlag.USE, (Object)com.sk89q.worldguard.protection.flags.DefaultFlag.USE.parseInput(Field9, (org.bukkit.command.CommandSender)(Object)p, "deny"));
                        a9.setFlag((com.sk89q.worldguard.protection.flags.Flag)com.sk89q.worldguard.protection.flags.DefaultFlag.ENDER_BUILD, (Object)com.sk89q.worldguard.protection.flags.DefaultFlag.ENDER_BUILD.parseInput(Field9, (org.bukkit.command.CommandSender)(Object)p, "deny"));
                        a9.setFlag((com.sk89q.worldguard.protection.flags.Flag)com.sk89q.worldguard.protection.flags.DefaultFlag.CREEPER_EXPLOSION, (Object)com.sk89q.worldguard.protection.flags.DefaultFlag.CREEPER_EXPLOSION.parseInput(Field9, (org.bukkit.command.CommandSender)(Object)p, "deny"));
                        a9.setFlag((com.sk89q.worldguard.protection.flags.Flag)com.sk89q.worldguard.protection.flags.DefaultFlag.MOB_SPAWNING, (Object)com.sk89q.worldguard.protection.flags.DefaultFlag.MOB_SPAWNING.parseInput(Field9, (org.bukkit.command.CommandSender)(Object)p, "deny"));
                        a4.save();
                    } catch(Exception a11) {
                        a11.printStackTrace();
                    }
                    p.sendMessage(plugin.getConfig().getString("Mensagem.Sucesso.Comprou").replace("&", "§"));
                    if (plugin.getServer().getPlayer(s0) != null) {
                        plugin.getServer().getPlayer(s0).sendMessage(plugin.getConfig().getString("Mensagem.Sucesso.Compraram").replace("&", "§").replace((CharSequence)(Object)"@player", (CharSequence)(Object)p.getName()));
                    }
                    e.getClickedBlock().setType(Material.AIR);
                    return;
                }
                //p.sendMessage(plugin.getConfig().getString("Mensagem.Erro.Excedeu_Limite").replace("&", "§").replace("@limite", String.valueOf(plugin.getConfig().getInt(new StringBuilder("Config.Grupos.").append(package2.package1.package0.Class0.Field1.getPrimaryGroup(p)).toString()))));
            }
        }
    }
    
}
