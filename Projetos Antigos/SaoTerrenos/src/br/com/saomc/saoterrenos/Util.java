package br.com.saomc.saoterrenos;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class Util {
    Main plugin;
    WorldGuardPlugin getWorldGuard() {
    Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("WorldGuard");
    if (plugin == null || !(plugin instanceof WorldGuardPlugin)) {
        return null; 
    }
        return (WorldGuardPlugin) plugin;
    }
    WorldGuardPlugin worldGuard = getWorldGuard();
    
    
    
    public boolean Method9(Player p, String s, String args) {
        Object[] a0 = worldGuard.getRegionContainer().get(p.getWorld()).getRegions().keySet().toArray();
        int i = a0.length;
        int i0 = 0;
        Object a1 = p;
        int i1 = 0;
        while(i0 < i) {
            if (a0[i1].toString().equals(p.getName().toLowerCase())) {
                return true;
            }
            i1 = i1 + 1;
            i0 = i1;
        }
        return false;
    }
    
    public int Method10(Player p, String s) {
        RegionManager rm = worldGuard.getRegionManager(p.getWorld());
        int i = rm.getRegion(s).getMinimumPoint().getBlockX();
        int i0 = rm.getRegion(s).getMinimumPoint().getBlockY();
        int i1 = rm.getRegion(s).getMinimumPoint().getBlockZ();
        int i2 = rm.getRegion(s).getMaximumPoint().getBlockX();
        int i3 = rm.getRegion(s).getMaximumPoint().getBlockY();
        int i4 = rm.getRegion(s).getMaximumPoint().getBlockZ();
        Object pl = p;
        int i5 = 0;
        while(i <= i2) {
            int i6 = i0;
            i6 = i0;
            while(i6 <= i3) {
                int i7 = i1;
                i7 = i1;
                while(i7 <= i4) {
                    Location a2 = new Location(p.getWorld(), (double)i, (double)i6, (double)i7);
                    Material a3 = a2.getBlock().getType();
                    Material a4 = Material.WALL_SIGN;
                            if (a3 == a4) {
                                
                            }
                            if (a2.getBlock().getType() != Material.SIGN_POST) {
                                
                            }
                            BlockState a5 = a2.getBlock().getState();
                            Sign sign = (Sign) a5;
                            String[] ln = sign.getLines();
                            if ((!ln[0].isEmpty()) && (ln[1].equalsIgnoreCase("Vendendo")) && (ln[2].equalsIgnoreCase("Preco")) && (!ln[3].isEmpty())) {
                                i5 = i5 + 1;
                            }
                            i7 = i7 + 1;
                }
                i6 = i6 + 1;
            }
            i = i + 1;
        }
        return i5;
    }
    
    public void Method11(Player p, String s) {
        Exception a0 = null;
        RegionManager a1 = worldGuard.getRegionManager(p.getWorld());
        ApplicableRegionSet a2 = a1.getApplicableRegions(p.getLocation());
        if (a2.size() == 0) {
            p.sendMessage(plugin.getConfig().getString("Mensagem.Erro.Nao_Esta_Em_Uma_Area").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7"));
            return;
        }
        ((Object)a2).toString();
        ProtectedRegion a3 = a1.getRegion(((com.sk89q.worldguard.protection.regions.ProtectedRegion)a2.iterator().next()).getId());
        if (!a3.getOwners().contains(p.getName())) {
            p.sendMessage(plugin.getConfig().getString("Mensagem.Erro.Nao_E_Sua").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7").replace((CharSequence)(Object)"@area", (CharSequence)(Object)a3.getId().replace((CharSequence)(Object)new StringBuilder(String.valueOf((Object)a.getName().toLowerCase())).append("_").toString(), (CharSequence)(Object)"")));
            return;
        }
        com.sk89q.worldguard.protection.flags.StringFlag a4 = com.sk89q.worldguard.protection.flags.DefaultFlag.GREET_MESSAGE;
        com.sk89q.worldguard.protection.flags.StringFlag a5 = com.sk89q.worldguard.protection.flags.DefaultFlag.GREET_MESSAGE;
        com.sk89q.worldguard.bukkit.WorldGuardPlugin a6 = Field4;
        label0: {
            try {
                a3.setFlag((com.sk89q.worldguard.protection.flags.Flag)a4, (Object)a5.parseInput(a6, (org.bukkit.command.CommandSender)(Object)a, new StringBuilder("\u00a7b").append(s).toString()));
                a1.save();
                a.sendMessage(plugin.getConfig().getString("Mensagem.Sucesso.Mensagem_De_Boas_Vindas").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7").replace((CharSequence)(Object)"@msg", (CharSequence)(Object)s));
            } catch(Exception a7) {
                a0 = a7;
                break label0;
            }
            return;
        }
        a0.printStackTrace();
    }
    
}
