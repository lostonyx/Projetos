package package2.package1.package0;

public class Class1 {
    static com.sk89q.worldguard.bukkit.WorldGuardPlugin Field4;
    static package2.package1.package0.Class0 Field5;
    
    public static boolean Method9(org.bukkit.entity.Player a, String s, String s0) {
        Object[] a0 = Field4.getRegionContainer().get(a.getWorld()).getRegions().keySet().toArray();
        int i = a0.length;
        int i0 = 0;
        Object a1 = a;
        int i1 = 0;
        while(i0 < i) {
            if (a0[i1].toString().equals((Object)new StringBuilder(String.valueOf((Object)((org.bukkit.entity.Player)a1).getName().toLowerCase())).append("_").append(s).toString())) {
                return true;
            }
            i1 = i1 + 1;
            i0 = i1;
        }
        return false;
    }
    
    public static int Method10(org.bukkit.entity.Player a, String s) {
        com.sk89q.worldguard.protection.managers.RegionManager a0 = Field4.getRegionManager(a.getWorld());
        int i = a0.getRegion(s).getMinimumPoint().getBlockX();
        int i0 = a0.getRegion(s).getMinimumPoint().getBlockY();
        int i1 = a0.getRegion(s).getMinimumPoint().getBlockZ();
        int i2 = a0.getRegion(s).getMaximumPoint().getBlockX();
        int i3 = a0.getRegion(s).getMaximumPoint().getBlockY();
        int i4 = a0.getRegion(s).getMaximumPoint().getBlockZ();
        Object a1 = a;
        int i5 = 0;
        while(i <= i2) {
            int i6 = i0;
            i6 = i0;
            while(i6 <= i3) {
                int i7 = i1;
                i7 = i1;
                while(i7 <= i4) {
                    org.bukkit.Location a2 = new org.bukkit.Location(((org.bukkit.entity.Player)a1).getWorld(), (double)i, (double)i6, (double)i7);
                    org.bukkit.Material a3 = a2.getBlock().getType();
                    org.bukkit.Material a4 = org.bukkit.Material.WALL_SIGN;
                    label0: {
                        label1: {
                            if (a3 == a4) {
                                break label1;
                            }
                            if (a2.getBlock().getType() != org.bukkit.Material.SIGN_POST) {
                                break label0;
                            }
                        }
                        org.bukkit.block.BlockState a5 = a2.getBlock().getState();
                        if (!((org.bukkit.block.Sign)(Object)a5).getLine(0).isEmpty() && ((org.bukkit.block.Sign)(Object)a5).getLine(1).equalsIgnoreCase("\u00a79Vendendo") && ((org.bukkit.block.Sign)(Object)a5).getLine(2).equalsIgnoreCase("\u00a79Preco") && !((org.bukkit.block.Sign)(Object)a5).getLine(3).isEmpty() && package2.package1.package0.Class1.Method29(((org.bukkit.block.Sign)(Object)a5).getLine(3).replace((CharSequence)(Object)"\u00a79", (CharSequence)(Object)""))) {
                            i5 = i5 + 1;
                        }
                    }
                    i7 = i7 + 1;
                }
                i6 = i6 + 1;
            }
            i = i + 1;
        }
        return i5;
    }
    
    public static void Method11(org.bukkit.entity.Player a, String s) {
        Exception a0 = null;
        com.sk89q.worldguard.protection.managers.RegionManager a1 = Field4.getRegionManager(a.getWorld());
        com.sk89q.worldguard.protection.ApplicableRegionSet a2 = a1.getApplicableRegions(a.getLocation());
        if (a2.size() == 0) {
            a.sendMessage(Field5.getConfig().getString("Mensagem.Erro.Nao_Esta_Em_Uma_Area").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7"));
            return;
        }
        ((Object)a2).toString();
        com.sk89q.worldguard.protection.regions.ProtectedRegion a3 = a1.getRegion(((com.sk89q.worldguard.protection.regions.ProtectedRegion)a2.iterator().next()).getId());
        if (!a3.getOwners().contains(a.getName())) {
            a.sendMessage(Field5.getConfig().getString("Mensagem.Erro.Nao_E_Sua").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7").replace((CharSequence)(Object)"@area", (CharSequence)(Object)a3.getId().replace((CharSequence)(Object)new StringBuilder(String.valueOf((Object)a.getName().toLowerCase())).append("_").toString(), (CharSequence)(Object)"")));
            return;
        }
        com.sk89q.worldguard.protection.flags.StringFlag a4 = com.sk89q.worldguard.protection.flags.DefaultFlag.GREET_MESSAGE;
        com.sk89q.worldguard.protection.flags.StringFlag a5 = com.sk89q.worldguard.protection.flags.DefaultFlag.GREET_MESSAGE;
        com.sk89q.worldguard.bukkit.WorldGuardPlugin a6 = Field4;
        label0: {
            try {
                a3.setFlag((com.sk89q.worldguard.protection.flags.Flag)a4, (Object)a5.parseInput(a6, (org.bukkit.command.CommandSender)(Object)a, new StringBuilder("\u00a7b").append(s).toString()));
                a1.save();
                a.sendMessage(Field5.getConfig().getString("Mensagem.Sucesso.Mensagem_De_Boas_Vindas").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7").replace((CharSequence)(Object)"@msg", (CharSequence)(Object)s));
            } catch(Exception a7) {
                a0 = a7;
                break label0;
            }
            return;
        }
        a0.printStackTrace();
    }
    
    public static void Method12(org.bukkit.entity.Player a, String s) {
        com.sk89q.worldguard.protection.managers.RegionManager a0 = Field4.getRegionManager(a.getWorld());
        int i = a0.getRegion(new StringBuilder(String.valueOf((Object)a.getName().toLowerCase())).append("_").append(s).toString()).getMinimumPoint().getBlockX();
        int i0 = a0.getRegion(new StringBuilder(String.valueOf((Object)a.getName().toLowerCase())).append("_").append(s).toString()).getMinimumPoint().getBlockY();
        int i1 = a0.getRegion(new StringBuilder(String.valueOf((Object)a.getName().toLowerCase())).append("_").append(s).toString()).getMinimumPoint().getBlockZ();
        int i2 = a0.getRegion(new StringBuilder(String.valueOf((Object)a.getName().toLowerCase())).append("_").append(s).toString()).getMaximumPoint().getBlockX();
        int i3 = a0.getRegion(new StringBuilder(String.valueOf((Object)a.getName().toLowerCase())).append("_").append(s).toString()).getMaximumPoint().getBlockY();
        int i4 = a0.getRegion(new StringBuilder(String.valueOf((Object)a.getName().toLowerCase())).append("_").append(s).toString()).getMaximumPoint().getBlockZ();
        Object a1 = a;
        while(i <= i2) {
            int i5 = i0;
            i5 = i0;
            while(i5 <= i3) {
                int i6 = i1;
                i6 = i1;
                while(i6 <= i4) {
                    org.bukkit.Location a2 = new org.bukkit.Location(((org.bukkit.entity.Player)a1).getWorld(), (double)i, (double)i5, (double)i6);
                    if (a2.getBlock().getState().getType() == org.bukkit.Material.SIGN_POST) {
                        org.bukkit.block.BlockState a3 = a2.getBlock().getState();
                        if (((org.bukkit.block.Sign)(Object)a3).getLine(0).equalsIgnoreCase(new StringBuilder("\u00a79").append(((org.bukkit.entity.Player)a1).getName()).toString()) && ((org.bukkit.block.Sign)(Object)a3).getLine(1).equalsIgnoreCase("\u00a79Vendendo") && ((org.bukkit.block.Sign)(Object)a3).getLine(2).equalsIgnoreCase("\u00a79Preco") && !((org.bukkit.block.Sign)(Object)a3).getLine(3).isEmpty()) {
                            a2.getBlock().setType(org.bukkit.Material.AIR);
                        }
                    }
                    i6 = i6 + 1;
                }
                i5 = i5 + 1;
            }
            i = i + 1;
        }
    }
    
    public static String Method13(org.bukkit.entity.Player a) {
        java.util.Map a0 = Field4.getRegionContainer().get(a.getWorld()).getRegions();
        com.sk89q.worldguard.LocalPlayer a1 = Field4.wrapPlayer(a);
        java.util.Iterator a2 = a0.values().iterator();
        Object a3 = a2;
        Object a4 = a;
        String s = "";
        a3 = a2;
        while(((java.util.Iterator)a3).hasNext()) {
            com.sk89q.worldguard.protection.regions.ProtectedRegion a5 = (com.sk89q.worldguard.protection.regions.ProtectedRegion)((java.util.Iterator)a3).next();
            if (a5.isOwner(a1)) {
                s = new StringBuilder(String.valueOf((Object)s)).append(a5.getId().replace((CharSequence)(Object)new StringBuilder(String.valueOf((Object)((org.bukkit.entity.Player)a4).getName().toLowerCase())).append("_").toString(), (CharSequence)(Object)"")).append(", ").toString();
            }
        }
        if (s.length() >= 2) {
            s = s.substring(0, s.length() - 2);
        }
        return s;
    }
    
    public static void Method14(org.bukkit.entity.Player a, String s) {
        com.sk89q.worldguard.protection.managers.RegionManager a0 = Field4.getRegionManager(a.getWorld());
        com.sk89q.worldguard.protection.ApplicableRegionSet a1 = a0.getApplicableRegions(a.getLocation());
        if (a1.size() == 0) {
            a.sendMessage(Field5.getConfig().getString("Mensagem.Erro.Nao_Esta_Em_Uma_Area").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7"));
            return;
        }
        ((Object)a1).toString();
        com.sk89q.worldguard.protection.regions.ProtectedRegion a2 = a0.getRegion(((com.sk89q.worldguard.protection.regions.ProtectedRegion)a1.iterator().next()).getId());
        if (a2.getOwners().contains(a.getName())) {
            label0: {
                label4: {
                    label2: try {
                        com.sk89q.worldguard.protection.flags.StateFlag$State a3 = (com.sk89q.worldguard.protection.flags.StateFlag$State)a2.getFlag((com.sk89q.worldguard.protection.flags.Flag)com.sk89q.worldguard.protection.flags.DefaultFlag.PVP);
                        com.sk89q.worldguard.protection.flags.StateFlag$State a4 = com.sk89q.worldguard.protection.flags.StateFlag$State.ALLOW;
                        label3: {
                            if (a3 != a4) {
                                break label3;
                            }
                            if (!s.equals((Object)"allow")) {
                                break label3;
                            }
                            a.sendMessage(Field5.getConfig().getString("Mensagem.Erro.PvP_Ja_Ativado").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7"));
                            break label4;
                        }
                        com.sk89q.worldguard.protection.flags.StateFlag$State a5 = com.sk89q.worldguard.protection.flags.StateFlag$State.DENY;
                        label1: {
                            if (a3 != a5) {
                                break label1;
                            }
                            if (!s.equals((Object)"deny")) {
                                break label1;
                            }
                            a.sendMessage(Field5.getConfig().getString("Mensagem.Erro.PvP_Nao_Ativado").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7"));
                            break label2;
                        }
                        a2.setFlag((com.sk89q.worldguard.protection.flags.Flag)com.sk89q.worldguard.protection.flags.DefaultFlag.PVP, (Object)com.sk89q.worldguard.protection.flags.DefaultFlag.PVP.parseInput(Field4, (org.bukkit.command.CommandSender)(Object)a, s));
                        package2.package1.package0.Class0.Field0.withdrawPlayer((org.bukkit.OfflinePlayer)(Object)a, (double)Field5.getConfig().getInt("Config.Preco_PvP"));
                        a0.save();
                        break label0;
                    } catch(Exception a6) {
                        a6.printStackTrace();
                        break label0;
                    }
                    return;
                }
                return;
            }
            if (s.equals((Object)"allow")) {
                a.sendMessage(Field5.getConfig().getString("Mensagem.Sucesso.PvP_Desativado").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7").replace((CharSequence)(Object)"@terreno", (CharSequence)(Object)a2.getId().replace((CharSequence)(Object)new StringBuilder(String.valueOf((Object)a.getName().toLowerCase())).append("_").toString(), (CharSequence)(Object)"")));
                return;
            }
            if (s.equals((Object)"deny")) {
                a.sendMessage(Field5.getConfig().getString("Mensagem.Sucesso.PvP_Ativado").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7").replace((CharSequence)(Object)"@terreno", (CharSequence)(Object)a2.getId().replace((CharSequence)(Object)new StringBuilder(String.valueOf((Object)a.getName().toLowerCase())).append("_").toString(), (CharSequence)(Object)"")));
                return;
            }
        } else
        {
            a.sendMessage(Field5.getConfig().getString("Mensagem.Erro.Nao_E_Sua").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7").replace((CharSequence)(Object)"@area", (CharSequence)(Object)a2.getId().replace((CharSequence)(Object)new StringBuilder(String.valueOf((Object)a.getName().toLowerCase())).append("_").toString(), (CharSequence)(Object)"")));
        }
    }
    
    public static int Method15(org.bukkit.entity.Player a, String s) {
        java.util.Map a0 = Field4.getRegionContainer().get(a.getWorld()).getRegions();
        com.sk89q.worldguard.LocalPlayer a1 = Field4.wrapOfflinePlayer(Field5.getServer().getOfflinePlayer(s));
        java.util.Iterator a2 = a0.values().iterator();
        Object a3 = a2;
        int i = 0;
        a3 = a2;
        while(((java.util.Iterator)a3).hasNext()) {
            if (((com.sk89q.worldguard.protection.regions.ProtectedRegion)((java.util.Iterator)a3).next()).isOwner(a1)) {
                i = i + 1;
            }
        }
        return i;
    }
    
    public static boolean Method16(org.bukkit.entity.Player a, String s) {
        com.sk89q.worldguard.protection.managers.RegionManager a0 = Field4.getRegionManager(a.getWorld());
        int i = a0.getRegion(s).getMinimumPoint().getBlockX();
        int i0 = a0.getRegion(s).getMinimumPoint().getBlockY();
        int i1 = a0.getRegion(s).getMinimumPoint().getBlockZ();
        int i2 = a0.getRegion(s).getMaximumPoint().getBlockX();
        int i3 = a0.getRegion(s).getMaximumPoint().getBlockY();
        int i4 = a0.getRegion(s).getMaximumPoint().getBlockZ();
        Object a1 = a;
        while(i <= i2) {
            int i5 = i0;
            i5 = i0;
            while(i5 <= i3) {
                int i6 = i1;
                i6 = i1;
                while(i6 <= i4) {
                    org.bukkit.Location a2 = new org.bukkit.Location(((org.bukkit.entity.Player)a1).getWorld(), (double)i, (double)i5, (double)i6);
                    if (a2.getBlock().getState().getType() == org.bukkit.Material.SIGN_POST) {
                        org.bukkit.block.BlockState a3 = a2.getBlock().getState();
                        if (((org.bukkit.block.Sign)(Object)a3).getLine(0).equalsIgnoreCase(new StringBuilder("\u00a79").append(((org.bukkit.entity.Player)a1).getName()).toString()) && ((org.bukkit.block.Sign)(Object)a3).getLine(1).equalsIgnoreCase("\u00a79Vendendo") && ((org.bukkit.block.Sign)(Object)a3).getLine(2).equalsIgnoreCase("\u00a79Preco") && !((org.bukkit.block.Sign)(Object)a3).getLine(3).isEmpty()) {
                            return true;
                        }
                    }
                    i6 = i6 + 1;
                }
                i5 = i5 + 1;
            }
            i = i + 1;
        }
        return false;
    }
    
    public static void Method17(org.bukkit.entity.Player a, String s) {
        com.sk89q.worldguard.protection.managers.RegionManager a0 = Field4.getRegionManager(a.getWorld());
        com.sk89q.worldguard.protection.ApplicableRegionSet a1 = a0.getApplicableRegions(a.getLocation());
        if (a1.size() == 0) {
            a.sendMessage(Field5.getConfig().getString("Mensagem.Erro.Nao_Esta_Em_Uma_Area").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7"));
            return;
        }
        ((Object)a1).toString();
        com.sk89q.worldguard.protection.regions.ProtectedRegion a2 = a0.getRegion(((com.sk89q.worldguard.protection.regions.ProtectedRegion)a1.iterator().next()).getId());
        if (a2.getOwners().contains(a.getName())) {
            label0: {
                label4: {
                    label2: try {
                        com.sk89q.worldguard.protection.flags.StateFlag$State a3 = (com.sk89q.worldguard.protection.flags.StateFlag$State)a2.getFlag((com.sk89q.worldguard.protection.flags.Flag)com.sk89q.worldguard.protection.flags.DefaultFlag.MOB_SPAWNING);
                        com.sk89q.worldguard.protection.flags.StateFlag$State a4 = com.sk89q.worldguard.protection.flags.StateFlag$State.ALLOW;
                        label3: {
                            if (a3 != a4) {
                                break label3;
                            }
                            if (!s.equals((Object)"allow")) {
                                break label3;
                            }
                            a.sendMessage(Field5.getConfig().getString("Mensagem.Erro.Mob_Ja_Ativado").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7"));
                            break label4;
                        }
                        com.sk89q.worldguard.protection.flags.StateFlag$State a5 = com.sk89q.worldguard.protection.flags.StateFlag$State.DENY;
                        label1: {
                            if (a3 != a5) {
                                break label1;
                            }
                            if (!s.equals((Object)"deny")) {
                                break label1;
                            }
                            a.sendMessage(Field5.getConfig().getString("Mensagem.Erro.Mob_Nao_Ativado").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7"));
                            break label2;
                        }
                        a2.setFlag((com.sk89q.worldguard.protection.flags.Flag)com.sk89q.worldguard.protection.flags.DefaultFlag.MOB_SPAWNING, (Object)com.sk89q.worldguard.protection.flags.DefaultFlag.MOB_SPAWNING.parseInput(Field4, (org.bukkit.command.CommandSender)(Object)a, s));
                        package2.package1.package0.Class0.Field0.withdrawPlayer((org.bukkit.OfflinePlayer)(Object)a, (double)Field5.getConfig().getInt("Config.Preco_Mob"));
                        a0.save();
                        break label0;
                    } catch(Exception a6) {
                        a6.printStackTrace();
                        break label0;
                    }
                    return;
                }
                return;
            }
            if (s.equals((Object)"allow")) {
                a.sendMessage(Field5.getConfig().getString("Mensagem.Sucesso.Mob_Desativado").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7").replace((CharSequence)(Object)"@terreno", (CharSequence)(Object)a2.getId().replace((CharSequence)(Object)new StringBuilder(String.valueOf((Object)a.getName().toLowerCase())).append("_").toString(), (CharSequence)(Object)"")));
                return;
            }
            if (s.equals((Object)"deny")) {
                a.sendMessage(Field5.getConfig().getString("Mensagem.Sucesso.Mob_Ativado").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7").replace((CharSequence)(Object)"@terreno", (CharSequence)(Object)a2.getId().replace((CharSequence)(Object)new StringBuilder(String.valueOf((Object)a.getName().toLowerCase())).append("_").toString(), (CharSequence)(Object)"")));
                return;
            }
        } else
        {
            a.sendMessage(Field5.getConfig().getString("Mensagem.Erro.Nao_E_Sua").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7").replace((CharSequence)(Object)"@area", (CharSequence)(Object)a2.getId().replace((CharSequence)(Object)new StringBuilder(String.valueOf((Object)a.getName().toLowerCase())).append("_").toString(), (CharSequence)(Object)"")));
        }
    }
    
    public static void Method18(org.bukkit.entity.Player a, String s, String s0) {
        label6: {
            label1: {
                label3: {
                    label5: try {
                        com.sk89q.worldguard.protection.managers.RegionManager a0 = Field4.getRegionManager(a.getWorld());
                        boolean b = a0.hasRegion(new StringBuilder(String.valueOf((Object)a.getName().toLowerCase())).append("_").append(s).toString());
                        label4: {
                            if (b) {
                                break label4;
                            }
                            a.sendMessage(Field5.getConfig().getString("Mensagem.Erro.Area_Nao_Encontrada").replace((CharSequence)(Object)"@area", (CharSequence)(Object)s).replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7"));
                            break label5;
                        }
                        com.sk89q.worldguard.protection.regions.ProtectedRegion a1 = a0.getRegion(new StringBuilder(String.valueOf((Object)a.getName().toLowerCase())).append("_").append(s).toString());
                        boolean b0 = a1.getOwners().contains(a.getName());
                        label2: {
                            if (b0) {
                                break label2;
                            }
                            a.sendMessage(Field5.getConfig().getString("Mensagem.Erro.Nao_E_Sua").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7").replace((CharSequence)(Object)"@area", (CharSequence)(Object)s));
                            break label3;
                        }
                        boolean b1 = package2.package1.package0.Class1.Method9(a, s0, a.getWorld().getName());
                        label0: {
                            if (!b1) {
                                break label0;
                            }
                            a.sendMessage(Field5.getConfig().getString("Mensagem.Erro.Ja_Existe_Terreno_Com_O_Nome").replace((CharSequence)(Object)"@area", (CharSequence)(Object)s0).replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7"));
                            break label1;
                        }
                        a0.removeRegion(new StringBuilder(String.valueOf((Object)a.getName().toLowerCase())).append("_").append(s).toString());
                        com.sk89q.worldedit.BlockVector a2 = new com.sk89q.worldedit.BlockVector(a1.getMinimumPoint().getBlockX(), a1.getMinimumPoint().getBlockY(), a1.getMinimumPoint().getBlockZ());
                        com.sk89q.worldedit.BlockVector a3 = new com.sk89q.worldedit.BlockVector(a1.getMaximumPoint().getBlockX(), a1.getMaximumPoint().getBlockY(), a1.getMaximumPoint().getBlockZ());
                        com.sk89q.worldguard.protection.regions.ProtectedCuboidRegion a4 = new com.sk89q.worldguard.protection.regions.ProtectedCuboidRegion(new StringBuilder(String.valueOf((Object)a.getName().toLowerCase())).append("_").append(s0).toString(), a2, a3);
                        com.sk89q.worldguard.domains.DefaultDomain a5 = new com.sk89q.worldguard.domains.DefaultDomain();
                        a0.addRegion((com.sk89q.worldguard.protection.regions.ProtectedRegion)a4);
                        a4.setPriority(100);
                        a5.addPlayer(a.getName());
                        a4.setOwners(a5);
                        a4.setFlag((com.sk89q.worldguard.protection.flags.Flag)com.sk89q.worldguard.protection.flags.DefaultFlag.PVP, (Object)com.sk89q.worldguard.protection.flags.DefaultFlag.PVP.parseInput(Field4, (org.bukkit.command.CommandSender)(Object)a, "allow"));
                        a4.setFlag((com.sk89q.worldguard.protection.flags.Flag)com.sk89q.worldguard.protection.flags.DefaultFlag.USE, (Object)com.sk89q.worldguard.protection.flags.DefaultFlag.USE.parseInput(Field4, (org.bukkit.command.CommandSender)(Object)a, "deny"));
                        a4.setFlag((com.sk89q.worldguard.protection.flags.Flag)com.sk89q.worldguard.protection.flags.DefaultFlag.ENDER_BUILD, (Object)com.sk89q.worldguard.protection.flags.DefaultFlag.ENDER_BUILD.parseInput(Field4, (org.bukkit.command.CommandSender)(Object)a, "deny"));
                        a4.setFlag((com.sk89q.worldguard.protection.flags.Flag)com.sk89q.worldguard.protection.flags.DefaultFlag.CREEPER_EXPLOSION, (Object)com.sk89q.worldguard.protection.flags.DefaultFlag.CREEPER_EXPLOSION.parseInput(Field4, (org.bukkit.command.CommandSender)(Object)a, "deny"));
                        a4.setFlag((com.sk89q.worldguard.protection.flags.Flag)com.sk89q.worldguard.protection.flags.DefaultFlag.MOB_SPAWNING, (Object)com.sk89q.worldguard.protection.flags.DefaultFlag.MOB_SPAWNING.parseInput(Field4, (org.bukkit.command.CommandSender)(Object)a, "deny"));
                        a0.save();
                        a.sendMessage(Field5.getConfig().getString("Mensagem.Sucesso.Terreno_Renomeado").replace((CharSequence)(Object)"@novonome", (CharSequence)(Object)s0).replace((CharSequence)(Object)"@area", (CharSequence)(Object)s).replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7"));
                        break label6;
                    } catch(Exception a6) {
                        a6.printStackTrace();
                        return;
                    }
                    return;
                }
                return;
            }
            return;
        }
    }
    
    public static void Method19(org.bukkit.entity.Player a) {
        com.sk89q.worldedit.IncompleteRegionException a0 = null;
        com.sk89q.worldguard.protection.managers.RegionManager a1 = Field4.getRegionManager(a.getWorld());
        com.sk89q.worldguard.protection.ApplicableRegionSet a2 = a1.getApplicableRegions(a.getLocation());
        if (a2.size() == 0) {
            a.sendMessage(Field5.getConfig().getString("Mensagem.Erro.Nao_Esta_Em_Uma_Area").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7"));
            return;
        }
        com.sk89q.worldguard.protection.regions.ProtectedRegion a3 = a1.getRegion(((com.sk89q.worldguard.protection.regions.ProtectedRegion)a2.iterator().next()).getId());
        if (!a3.getOwners().contains(a.getName())) {
            a.sendMessage(Field5.getConfig().getString("Mensagem.Erro.Nao_E_Sua").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7").replace((CharSequence)(Object)"@area", (CharSequence)(Object)a3.getId().replace((CharSequence)(Object)new StringBuilder(String.valueOf((Object)a.getName().toLowerCase())).append("_").toString(), (CharSequence)(Object)"")));
            return;
        }
        try {
            com.sk89q.worldedit.bukkit.selections.CuboidSelection a4 = new com.sk89q.worldedit.bukkit.selections.CuboidSelection(a.getWorld(), a3.getMinimumPoint().add(1, 0, 1), a3.getMaximumPoint().subtract(1, 0, 1));
            a.sendMessage(Field5.getConfig().getString("Mensagem.Sucesso.Limpando").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7"));
            com.sk89q.worldedit.regions.Region a5 = ((com.sk89q.worldedit.bukkit.selections.Selection)(Object)a4).getRegionSelector().getRegion();
            a5.getWorld().regenerate(a5, com.sk89q.worldedit.WorldEdit.getInstance().getEditSessionFactory().getEditSession(a5.getWorld(), -1));
            a.sendMessage(Field5.getConfig().getString("Mensagem.Sucesso.Limpo").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7"));
            return;
        } catch(com.sk89q.worldedit.IncompleteRegionException a6) {
            a0 = a6;
        }
        a0.printStackTrace();
    }
    
    public static void Method20(org.bukkit.entity.Player a, int i) {
        com.sk89q.worldguard.protection.managers.RegionManager a0 = Field4.getRegionManager(a.getWorld());
        com.sk89q.worldguard.protection.ApplicableRegionSet a1 = a0.getApplicableRegions(a.getLocation());
        if (a1.size() == 0) {
            a.sendMessage(Field5.getConfig().getString("Mensagem.Erro.Nao_Esta_Em_Uma_Area").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7"));
            return;
        }
        ((Object)a1).toString();
        String s = ((com.sk89q.worldguard.protection.regions.ProtectedRegion)a1.iterator().next()).getId();
        if (!a0.getRegion(s).getOwners().contains(a.getName())) {
            a.sendMessage(Field5.getConfig().getString("Mensagem.Erro.Nao_E_Sua").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7"));
            return;
        }
        if (package2.package1.package0.Class1.Method16(a, s)) {
            a.sendMessage(Field5.getConfig().getString("Mensagem.Erro.Ja_Esta_Sendo_Vendido").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7"));
            return;
        }
        a.getLocation().getBlock().setType(org.bukkit.Material.SIGN_POST);
        org.bukkit.block.BlockState a2 = a.getLocation().getBlock().getState();
        ((org.bukkit.block.Sign)(Object)a2).setLine(0, new StringBuilder("\u00a79").append(a.getName()).toString());
        ((org.bukkit.block.Sign)(Object)a2).setLine(1, "\u00a79Vendendo");
        ((org.bukkit.block.Sign)(Object)a2).setLine(2, "\u00a79Preco");
        ((org.bukkit.block.Sign)(Object)a2).setLine(3, new StringBuilder("\u00a79").append(String.valueOf(i)).toString());
        ((org.bukkit.block.Sign)(Object)a2).update();
        a.sendMessage(Field5.getConfig().getString("Mensagem.Sucesso.Vendendo").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7"));
    }
    
    public static void Method21(org.bukkit.World a, java.io.File a0, com.sk89q.worldedit.Vector a1) {
        try {
            com.sk89q.worldedit.EditSession a2 = new com.sk89q.worldedit.EditSession((com.sk89q.worldedit.LocalWorld)new com.sk89q.worldedit.bukkit.BukkitWorld(a), 2147483647);
            com.sk89q.worldedit.CuboidClipboard.loadSchematic(a0).paste(a2, a1, false);
            return;
        } catch(com.sk89q.worldedit.MaxChangedBlocksException | com.sk89q.worldedit.world.DataException | java.io.IOException a3) {
            a3.printStackTrace();
            return;
        }
    }
    
    public static void Method22(org.bukkit.entity.Player a, org.bukkit.entity.Player a0) {
        label5: {
            label6: {
                label1: {
                    label3: try {
                        com.sk89q.worldguard.protection.managers.RegionManager a1 = Field4.getRegionManager(a.getWorld());
                        com.sk89q.worldguard.protection.ApplicableRegionSet a2 = a1.getApplicableRegions(a.getLocation());
                        int i = a2.size();
                        label4: {
                            if (i != 0) {
                                break label4;
                            }
                            a.sendMessage(Field5.getConfig().getString("Mensagem.Erro.Nao_Esta_Em_Uma_Area").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7"));
                            break label5;
                        }
                        ((Object)a2).toString();
                        String s = ((com.sk89q.worldguard.protection.regions.ProtectedRegion)a2.iterator().next()).getId();
                        com.sk89q.worldguard.protection.regions.ProtectedRegion a3 = a1.getRegion(s);
                        boolean b = a3.getOwners().contains(a.getName());
                        label2: {
                            if (b) {
                                break label2;
                            }
                            a.sendMessage(Field5.getConfig().getString("Mensagem.Erro.Nao_E_Sua").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7").replace((CharSequence)(Object)"@area", (CharSequence)(Object)s.split("_")[1]));
                            break label3;
                        }
                        boolean b0 = package2.package1.package0.Class1.Method9(a0, s.split("_")[1], a.getWorld().getName());
                        label0: {
                            if (!b0) {
                                break label0;
                            }
                            a.sendMessage(Field5.getConfig().getString("Mensagem.Erro.Ja_Existe_Terreno_Com_O_Nome_Transferir").replace((CharSequence)(Object)"@player", (CharSequence)(Object)a0.getName()).replace((CharSequence)(Object)"@area", (CharSequence)(Object)s.split("_")[1]).replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7"));
                            break label1;
                        }
                        Field5.Method6();
                        if (package2.package1.package0.Class1.Method33(a0) >= Field5.getConfig().getInt(new StringBuilder("Config.Grupos.").append(package2.package1.package0.Class0.Field1.getPrimaryGroup(a)).toString())) {
                            a.sendMessage(Field5.getConfig().getString("Mensagem.Erro.Excedeu_Limite_Transferir").replace((CharSequence)(Object)"@player", (CharSequence)(Object)a0.getName()).replace((CharSequence)(Object)"@limite", (CharSequence)(Object)String.valueOf(Field5.getConfig().getInt(new StringBuilder("Config.Grupos.").append(package2.package1.package0.Class0.Field1.getPrimaryGroup(a)).toString()))).replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7"));
                            return;
                        }
                        a1.removeRegion(new StringBuilder(String.valueOf((Object)a.getName().toLowerCase())).append("_").append(s.split("_")[1]).toString());
                        com.sk89q.worldedit.BlockVector a4 = new com.sk89q.worldedit.BlockVector(a3.getMinimumPoint().getBlockX(), a3.getMinimumPoint().getBlockY(), a3.getMinimumPoint().getBlockZ());
                        com.sk89q.worldedit.BlockVector a5 = new com.sk89q.worldedit.BlockVector(a3.getMaximumPoint().getBlockX(), a3.getMaximumPoint().getBlockY(), a3.getMaximumPoint().getBlockZ());
                        com.sk89q.worldguard.protection.regions.ProtectedCuboidRegion a6 = new com.sk89q.worldguard.protection.regions.ProtectedCuboidRegion(new StringBuilder(String.valueOf((Object)a0.getName().toLowerCase())).append("_").append(s.split("_")[1]).toString(), a4, a5);
                        com.sk89q.worldguard.domains.DefaultDomain a7 = new com.sk89q.worldguard.domains.DefaultDomain();
                        a1.addRegion((com.sk89q.worldguard.protection.regions.ProtectedRegion)a6);
                        a6.setPriority(100);
                        a7.addPlayer(a0.getName());
                        a6.setOwners(a7);
                        a6.setFlag((com.sk89q.worldguard.protection.flags.Flag)com.sk89q.worldguard.protection.flags.DefaultFlag.PVP, (Object)com.sk89q.worldguard.protection.flags.DefaultFlag.PVP.parseInput(Field4, (org.bukkit.command.CommandSender)(Object)a, "allow"));
                        a6.setFlag((com.sk89q.worldguard.protection.flags.Flag)com.sk89q.worldguard.protection.flags.DefaultFlag.USE, (Object)com.sk89q.worldguard.protection.flags.DefaultFlag.USE.parseInput(Field4, (org.bukkit.command.CommandSender)(Object)a, "deny"));
                        a6.setFlag((com.sk89q.worldguard.protection.flags.Flag)com.sk89q.worldguard.protection.flags.DefaultFlag.ENDER_BUILD, (Object)com.sk89q.worldguard.protection.flags.DefaultFlag.ENDER_BUILD.parseInput(Field4, (org.bukkit.command.CommandSender)(Object)a, "deny"));
                        a6.setFlag((com.sk89q.worldguard.protection.flags.Flag)com.sk89q.worldguard.protection.flags.DefaultFlag.CREEPER_EXPLOSION, (Object)com.sk89q.worldguard.protection.flags.DefaultFlag.CREEPER_EXPLOSION.parseInput(Field4, (org.bukkit.command.CommandSender)(Object)a, "deny"));
                        a6.setFlag((com.sk89q.worldguard.protection.flags.Flag)com.sk89q.worldguard.protection.flags.DefaultFlag.MOB_SPAWNING, (Object)com.sk89q.worldguard.protection.flags.DefaultFlag.MOB_SPAWNING.parseInput(Field4, (org.bukkit.command.CommandSender)(Object)a, "deny"));
                        a1.save();
                        a.sendMessage(Field5.getConfig().getString("Mensagem.Sucesso.Terreno_Transferido").replace((CharSequence)(Object)"@player", (CharSequence)(Object)a0.getName()).replace((CharSequence)(Object)"@area", (CharSequence)(Object)s.split("_")[1]).replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7"));
                        a0.sendMessage(Field5.getConfig().getString("Mensagem.Sucesso.Terreno_Transferido_Voce").replace((CharSequence)(Object)"@player", (CharSequence)(Object)a.getName()).replace((CharSequence)(Object)"@area", (CharSequence)(Object)s.split("_")[1]).replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7"));
                        break label6;
                    } catch(Exception a8) {
                        a8.printStackTrace();
                        return;
                    }
                    return;
                }
                return;
            }
            return;
        }
    }
    
    public static void Method23(org.bukkit.entity.Player a, int i, int i0, int i1, int i2) {
        org.bukkit.World a0 = a.getLocation().getWorld();
        int i3 = 0;
        Object a1 = a0;
        int i4 = 0;
        while(i3 < 360) {
            int i5 = i;
            i5 = i;
            while(i5 < i0) {
                org.bukkit.block.Block a2 = ((org.bukkit.World)a1).getBlockAt(i5, i4, i1);
                if (a2.getType() == org.bukkit.Material.getMaterial(Field5.getConfig().getString("Config.Bloco_Cercar"))) {
                    a2.setType(org.bukkit.Material.AIR);
                }
                i5 = i5 + 1;
            }
            int i6 = i;
            i6 = i;
            while(i6 <= i0) {
                org.bukkit.block.Block a3 = ((org.bukkit.World)a1).getBlockAt(i6, i4, i2);
                if (a3.getType() == org.bukkit.Material.getMaterial(Field5.getConfig().getString("Config.Bloco_Cercar"))) {
                    a3.setType(org.bukkit.Material.AIR);
                }
                i6 = i6 + 1;
            }
            int i7 = i1;
            i7 = i1;
            while(i7 < i2) {
                org.bukkit.block.Block a4 = ((org.bukkit.World)a1).getBlockAt(i, i4, i7);
                if (a4.getType() == org.bukkit.Material.getMaterial(Field5.getConfig().getString("Config.Bloco_Cercar"))) {
                    a4.setType(org.bukkit.Material.AIR);
                }
                i7 = i7 + 1;
            }
            int i8 = i1;
            i8 = i1;
            while(i8 <= i2) {
                org.bukkit.block.Block a5 = ((org.bukkit.World)a1).getBlockAt(i0, i4, i8);
                if (a5.getType() == org.bukkit.Material.getMaterial(Field5.getConfig().getString("Config.Bloco_Cercar"))) {
                    a5.setType(org.bukkit.Material.AIR);
                }
                i8 = i8 + 1;
            }
            i4 = i4 + 1;
            i3 = i4;
        }
    }
    
    static {
        Field5 = package2.package1.package0.Class0.Method8();
        Field4 = Field5.Method3();
    }
    
    public static void Method24(org.bukkit.entity.Player a, String s) {
        Exception a0 = null;
        com.sk89q.worldguard.protection.managers.RegionManager a1 = Field4.getRegionManager(a.getWorld());
        com.sk89q.worldguard.protection.ApplicableRegionSet a2 = a1.getApplicableRegions(a.getLocation());
        if (a2.size() == 0) {
            a.sendMessage(Field5.getConfig().getString("Mensagem.Erro.Nao_Esta_Em_Uma_Area").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7"));
            return;
        }
        ((Object)a2).toString();
        com.sk89q.worldguard.protection.regions.ProtectedRegion a3 = a1.getRegion(((com.sk89q.worldguard.protection.regions.ProtectedRegion)a2.iterator().next()).getId());
        if (!a3.getOwners().contains(a.getName())) {
            a.sendMessage(Field5.getConfig().getString("Mensagem.Erro.Nao_E_Sua").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7").replace((CharSequence)(Object)"@area", (CharSequence)(Object)a3.getId().replace((CharSequence)(Object)new StringBuilder(String.valueOf((Object)a.getName().toLowerCase())).append("_").toString(), (CharSequence)(Object)"")));
            return;
        }
        label0: {
            try {
                Object a4 = null;
                if (!s.contains((CharSequence)(Object)",")) {
                    if (a3.getFlag((com.sk89q.worldguard.protection.flags.Flag)com.sk89q.worldguard.protection.flags.DefaultFlag.BLOCKED_CMDS) == null) {
                        a3.setFlag((com.sk89q.worldguard.protection.flags.Flag)com.sk89q.worldguard.protection.flags.DefaultFlag.BLOCKED_CMDS, (Object)com.sk89q.worldguard.protection.flags.DefaultFlag.BLOCKED_CMDS.parseInput(Field4, (org.bukkit.command.CommandSender)(Object)a, s));
                        a1.save();
                    } else if (((java.util.Set)a3.getFlag((com.sk89q.worldguard.protection.flags.Flag)com.sk89q.worldguard.protection.flags.DefaultFlag.BLOCKED_CMDS)).contains((Object)s)) {
                        ((java.util.Set)a3.getFlag((com.sk89q.worldguard.protection.flags.Flag)com.sk89q.worldguard.protection.flags.DefaultFlag.BLOCKED_CMDS)).remove((Object)s);
                        a1.save();
                    } else
                    {
                        a.sendMessage(Field5.getConfig().getString("Mensagem.Erro.Comando_Nao_Adicionado").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7"));
                    }
                    a.sendMessage(Field5.getConfig().getString("Mensagem.Sucesso.Comando_Ativado").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7"));
                    return;
                }
                if (s.endsWith(",")) {
                    a4 = a;
                    while(s.endsWith(",")) {
                        s.substring(0, s.length() - 1);
                    }
                } else
                {
                    a4 = a;
                }
                String[] a5 = s.split(",");
                int i = a5.length;
                int i0 = 0;
                int i1 = 0;
                while(i0 < i) {
                    String s0 = a5[i1];
                    if (s0.startsWith("/")) {
                        if (a3.getFlag((com.sk89q.worldguard.protection.flags.Flag)com.sk89q.worldguard.protection.flags.DefaultFlag.BLOCKED_CMDS) == null) {
                            ((org.bukkit.entity.Player)a4).sendMessage(Field5.getConfig().getString("Mensagem.Erro.Sem_Comandos_Bloqueados").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7"));
                        } else if (((java.util.Set)a3.getFlag((com.sk89q.worldguard.protection.flags.Flag)com.sk89q.worldguard.protection.flags.DefaultFlag.BLOCKED_CMDS)).contains((Object)s0)) {
                            ((java.util.Set)a3.getFlag((com.sk89q.worldguard.protection.flags.Flag)com.sk89q.worldguard.protection.flags.DefaultFlag.BLOCKED_CMDS)).remove((Object)s0);
                            a1.save();
                            ((org.bukkit.entity.Player)a4).sendMessage(Field5.getConfig().getString("Mensagem.Sucesso.Comandos_Ativados").replace((CharSequence)(Object)"@comandos", (CharSequence)(Object)s).replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7"));
                        }
                    } else if (a3.getFlag((com.sk89q.worldguard.protection.flags.Flag)com.sk89q.worldguard.protection.flags.DefaultFlag.BLOCKED_CMDS) == null) {
                        ((org.bukkit.entity.Player)a4).sendMessage(Field5.getConfig().getString("Mensagem.Erro.Sem_Comandos_Bloqueados").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7"));
                    } else if (((java.util.Set)a3.getFlag((com.sk89q.worldguard.protection.flags.Flag)com.sk89q.worldguard.protection.flags.DefaultFlag.BLOCKED_CMDS)).contains((Object)new StringBuilder("/").append(s0).toString())) {
                        ((java.util.Set)a3.getFlag((com.sk89q.worldguard.protection.flags.Flag)com.sk89q.worldguard.protection.flags.DefaultFlag.BLOCKED_CMDS)).remove((Object)new StringBuilder("/").append(s0).toString());
                        a1.save();
                        ((org.bukkit.entity.Player)a4).sendMessage(Field5.getConfig().getString("Mensagem.Sucesso.Comandos_Ativados").replace((CharSequence)(Object)"@comandos", (CharSequence)(Object)s).replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7"));
                    }
                    i1 = i1 + 1;
                    i0 = i1;
                }
            } catch(Exception a6) {
                a0 = a6;
                break label0;
            }
            return;
        }
        a0.printStackTrace();
    }
    
    public static void Method25(org.bukkit.entity.Player a, String s, int i) {
        label4: {
            label2: {
                label5: {
                    label0: try {
                        com.sk89q.worldguard.protection.managers.RegionManager a0 = Field4.getRegionManager(a.getWorld());
                        boolean b = package2.package1.package0.Class1.Method9(a, s, a.getWorld().getName());
                        label3: {
                            if (!b) {
                                break label3;
                            }
                            a.sendMessage(Field5.getConfig().getString("Mensagem.Erro.Ja_Existe_Terreno_Com_O_Nome").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7").replace((CharSequence)(Object)"@area", (CharSequence)(Object)s));
                            break label4;
                        }
                        com.sk89q.worldedit.BlockVector a1 = new com.sk89q.worldedit.BlockVector(a.getLocation().getX() - (double)(i / 2), 0.0, a.getLocation().getZ() - (double)(i / 2));
                        com.sk89q.worldedit.BlockVector a2 = new com.sk89q.worldedit.BlockVector(a.getLocation().getX() + (double)(i / 2), 250.0, a.getLocation().getZ() + (double)(i / 2));
                        com.sk89q.worldguard.protection.regions.ProtectedCuboidRegion a3 = new com.sk89q.worldguard.protection.regions.ProtectedCuboidRegion(new StringBuilder(String.valueOf((Object)a.getName().toLowerCase())).append("_").append(s).toString(), a1, a2);
                        boolean b0 = a0.getApplicableRegions((com.sk89q.worldguard.protection.regions.ProtectedRegion)a3).isOwnerOfAll(Field4.wrapPlayer(a));
                        label1: {
                            if (b0) {
                                break label1;
                            }
                            a.sendMessage(Field5.getConfig().getString("Mensagem.Erro.Terreno_Perto").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7"));
                            a0.removeRegion(new StringBuilder(String.valueOf((Object)a.getName().toLowerCase())).append("_").append(s).toString());
                            break label2;
                        }
                        com.sk89q.worldguard.domains.DefaultDomain a4 = new com.sk89q.worldguard.domains.DefaultDomain();
                        a0.addRegion((com.sk89q.worldguard.protection.regions.ProtectedRegion)a3);
                        a3.setPriority(100);
                        a4.addPlayer(a.getName());
                        a3.setOwners(a4);
                        a3.setFlag((com.sk89q.worldguard.protection.flags.Flag)com.sk89q.worldguard.protection.flags.DefaultFlag.PVP, (Object)com.sk89q.worldguard.protection.flags.DefaultFlag.PVP.parseInput(Field4, (org.bukkit.command.CommandSender)(Object)a, "allow"));
                        a3.setFlag((com.sk89q.worldguard.protection.flags.Flag)com.sk89q.worldguard.protection.flags.DefaultFlag.ENDER_BUILD, (Object)com.sk89q.worldguard.protection.flags.DefaultFlag.ENDER_BUILD.parseInput(Field4, (org.bukkit.command.CommandSender)(Object)a, "deny"));
                        a3.setFlag((com.sk89q.worldguard.protection.flags.Flag)com.sk89q.worldguard.protection.flags.DefaultFlag.CREEPER_EXPLOSION, (Object)com.sk89q.worldguard.protection.flags.DefaultFlag.CREEPER_EXPLOSION.parseInput(Field4, (org.bukkit.command.CommandSender)(Object)a, "deny"));
                        a3.setFlag((com.sk89q.worldguard.protection.flags.Flag)com.sk89q.worldguard.protection.flags.DefaultFlag.MOB_SPAWNING, (Object)com.sk89q.worldguard.protection.flags.DefaultFlag.MOB_SPAWNING.parseInput(Field4, (org.bukkit.command.CommandSender)(Object)a, "deny"));
                        a0.save();
                        package2.package1.package0.Class1.Method35(a.getLocation(), (int)a3.getMinimumPoint().getX(), (int)a3.getMaximumPoint().getX(), (int)a3.getMinimumPoint().getZ(), (int)a3.getMaximumPoint().getZ(), i);
                        a.sendMessage(Field5.getConfig().getString("Mensagem.Sucesso.Comprou_Terreno").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7").replace((CharSequence)(Object)"@preco", (CharSequence)(Object)java.text.NumberFormat.getNumberInstance().format((long)(i * Field5.getConfig().getInt("Config.Preco_Bloco")))).replace((CharSequence)(Object)"@area", (CharSequence)(Object)s).replace((CharSequence)(Object)"@tamanho", (CharSequence)(Object)String.valueOf(i)));
                        package2.package1.package0.Class0.Field0.withdrawPlayer((org.bukkit.OfflinePlayer)(Object)a, (double)(i * Field5.getConfig().getInt("Config.Preco_Bloco")));
                        if (!Field5.getConfig().getBoolean("Config.Schematic.Ativar")) {
                            break label0;
                        }
                        if (i >= Integer.parseInt(Field5.getConfig().getString("Config.Schematic.Pequeno.Tamanho").split("-")[0]) && i <= Integer.parseInt(Field5.getConfig().getString("Config.Schematic.Pequeno.Tamanho").split("-")[1])) {
                            java.io.File a5 = new java.io.File(Field5.getDataFolder(), Field5.getConfig().getString("Config.Schematic.Pequeno.Nome"));
                            package2.package1.package0.Class1.Method21(a.getWorld(), a5, new com.sk89q.worldedit.Vector(a.getLocation().getX(), a.getLocation().getY(), a.getLocation().getZ()));
                            return;
                        }
                        if (i >= Integer.parseInt(Field5.getConfig().getString("Config.Schematic.Medio.Tamanho").split("-")[0]) && i <= Integer.parseInt(Field5.getConfig().getString("Config.Schematic.Medio.Tamanho").split("-")[1])) {
                            java.io.File a6 = new java.io.File(Field5.getDataFolder(), Field5.getConfig().getString("Config.Schematic.Medio.Nome"));
                            package2.package1.package0.Class1.Method21(a.getWorld(), a6, new com.sk89q.worldedit.Vector(a.getLocation().getX(), a.getLocation().getY(), a.getLocation().getZ()));
                            return;
                        }
                        if (i < Integer.parseInt(Field5.getConfig().getString("Config.Schematic.Grande.Tamanho").split("-")[0])) {
                            break label0;
                        }
                        if (i > Integer.parseInt(Field5.getConfig().getString("Config.Schematic.Grande.Tamanho").split("-")[1])) {
                            break label0;
                        }
                        java.io.File a7 = new java.io.File(Field5.getDataFolder(), Field5.getConfig().getString("Config.Schematic.Grande.Nome"));
                        package2.package1.package0.Class1.Method21(a.getWorld(), a7, new com.sk89q.worldedit.Vector(a.getLocation().getX(), a.getLocation().getY(), a.getLocation().getZ()));
                        break label5;
                    } catch(Exception a8) {
                        a8.printStackTrace();
                    }
                    return;
                }
                return;
            }
            return;
        }
    }
    
    public static void Method26(org.bukkit.entity.Player a, String s, String s0) {
        com.sk89q.worldguard.protection.managers.RegionManager a0 = Field4.getRegionManager(a.getWorld());
        if (!a0.hasRegion(new StringBuilder(String.valueOf((Object)a.getName().toLowerCase())).append("_").append(s0).toString())) {
            a.sendMessage(Field5.getConfig().getString("Mensagem.Erro.Area_Nao_Encontrada").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7").replace((CharSequence)(Object)"@area", (CharSequence)(Object)s0));
            return;
        }
        com.sk89q.worldguard.protection.regions.ProtectedRegion a1 = a0.getRegion(new StringBuilder(String.valueOf((Object)a.getName().toLowerCase())).append("_").append(s0).toString());
        if (!a1.getOwners().contains(a.getName())) {
            a.sendMessage(Field5.getConfig().getString("Mensagem.Erro.Nao_E_Sua").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7").replace((CharSequence)(Object)"@area", (CharSequence)(Object)s0));
            return;
        }
        if (!a1.getMembers().contains(s.toLowerCase())) {
            a.sendMessage(Field5.getConfig().getString("Mensagem.Erro.Nao_Esta_Adicionado").replace((CharSequence)(Object)"@area", (CharSequence)(Object)s0).replace((CharSequence)(Object)"@amigo", (CharSequence)(Object)s).replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7"));
            return;
        }
        a1.getMembers().removePlayer(s.toLowerCase());
        try {
            a0.save();
        } catch(Exception a2) {
            a2.printStackTrace();
        }
        a.sendMessage(Field5.getConfig().getString("Mensagem.Sucesso.Amigo_Removido").replace((CharSequence)(Object)"@area", (CharSequence)(Object)s0).replace((CharSequence)(Object)"@amigo", (CharSequence)(Object)s).replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7"));
    }
    
    public static void Method27(org.bukkit.entity.Player a, String s) {
        Exception a0 = null;
        com.sk89q.worldguard.protection.managers.RegionManager a1 = Field4.getRegionManager(a.getWorld());
        com.sk89q.worldguard.protection.ApplicableRegionSet a2 = a1.getApplicableRegions(a.getLocation());
        if (a2.size() == 0) {
            a.sendMessage(Field5.getConfig().getString("Mensagem.Erro.Nao_Esta_Em_Uma_Area").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7"));
            return;
        }
        ((Object)a2).toString();
        com.sk89q.worldguard.protection.regions.ProtectedRegion a3 = a1.getRegion(((com.sk89q.worldguard.protection.regions.ProtectedRegion)a2.iterator().next()).getId());
        if (!a3.getOwners().contains(a.getName())) {
            a.sendMessage(Field5.getConfig().getString("Mensagem.Erro.Nao_E_Sua").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7").replace((CharSequence)(Object)"@area", (CharSequence)(Object)a3.getId().replace((CharSequence)(Object)new StringBuilder(String.valueOf((Object)a.getName().toLowerCase())).append("_").toString(), (CharSequence)(Object)"")));
            return;
        }
        label0: {
            try {
                Object a4 = null;
                if (!s.contains((CharSequence)(Object)",")) {
                    if (a3.getFlag((com.sk89q.worldguard.protection.flags.Flag)com.sk89q.worldguard.protection.flags.DefaultFlag.BLOCKED_CMDS) == null) {
                        a3.setFlag((com.sk89q.worldguard.protection.flags.Flag)com.sk89q.worldguard.protection.flags.DefaultFlag.BLOCKED_CMDS, (Object)com.sk89q.worldguard.protection.flags.DefaultFlag.BLOCKED_CMDS.parseInput(Field4, (org.bukkit.command.CommandSender)(Object)a, s));
                        a1.save();
                    } else
                    {
                        ((java.util.Set)a3.getFlag((com.sk89q.worldguard.protection.flags.Flag)com.sk89q.worldguard.protection.flags.DefaultFlag.BLOCKED_CMDS)).add((Object)s);
                        a1.save();
                    }
                    a.sendMessage(Field5.getConfig().getString("Mensagem.Sucesso.Comando_Desativado").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7"));
                    return;
                }
                if (s.endsWith(",")) {
                    a4 = a;
                    while(s.endsWith(",")) {
                        s.substring(0, s.length() - 1);
                    }
                } else
                {
                    a4 = a;
                }
                String[] a5 = s.split(",");
                int i = a5.length;
                int i0 = 0;
                int i1 = 0;
                while(i0 < i) {
                    String s0 = a5[i1];
                    if (s0.startsWith("/")) {
                        if (a3.getFlag((com.sk89q.worldguard.protection.flags.Flag)com.sk89q.worldguard.protection.flags.DefaultFlag.BLOCKED_CMDS) == null) {
                            ((java.util.Set)a3.getFlag((com.sk89q.worldguard.protection.flags.Flag)com.sk89q.worldguard.protection.flags.DefaultFlag.BLOCKED_CMDS)).add((Object)"/kgjklfjgkldfhjkldjh");
                            if (!((java.util.Set)a3.getFlag((com.sk89q.worldguard.protection.flags.Flag)com.sk89q.worldguard.protection.flags.DefaultFlag.BLOCKED_CMDS)).contains((Object)s0)) {
                                ((java.util.Set)a3.getFlag((com.sk89q.worldguard.protection.flags.Flag)com.sk89q.worldguard.protection.flags.DefaultFlag.BLOCKED_CMDS)).add((Object)s0);
                                a1.save();
                                ((org.bukkit.entity.Player)a4).sendMessage(Field5.getConfig().getString("Mensagem.Sucesso.Comandos_Desativados").replace((CharSequence)(Object)"@comandos", (CharSequence)(Object)s).replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7"));
                            }
                            ((java.util.Set)a3.getFlag((com.sk89q.worldguard.protection.flags.Flag)com.sk89q.worldguard.protection.flags.DefaultFlag.BLOCKED_CMDS)).remove((Object)"/kgjklfjgkldfhjkldjh");
                        } else if (!((java.util.Set)a3.getFlag((com.sk89q.worldguard.protection.flags.Flag)com.sk89q.worldguard.protection.flags.DefaultFlag.BLOCKED_CMDS)).contains((Object)s0)) {
                            ((java.util.Set)a3.getFlag((com.sk89q.worldguard.protection.flags.Flag)com.sk89q.worldguard.protection.flags.DefaultFlag.BLOCKED_CMDS)).add((Object)s0);
                            a1.save();
                            ((org.bukkit.entity.Player)a4).sendMessage(Field5.getConfig().getString("Mensagem.Sucesso.Comandos_Desativados").replace((CharSequence)(Object)"@comandos", (CharSequence)(Object)s).replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7"));
                        }
                    } else if (a3.getFlag((com.sk89q.worldguard.protection.flags.Flag)com.sk89q.worldguard.protection.flags.DefaultFlag.BLOCKED_CMDS) == null) {
                        ((java.util.Set)a3.getFlag((com.sk89q.worldguard.protection.flags.Flag)com.sk89q.worldguard.protection.flags.DefaultFlag.BLOCKED_CMDS)).add((Object)"/kgjklfjgkldfhjkldjh");
                        if (!((java.util.Set)a3.getFlag((com.sk89q.worldguard.protection.flags.Flag)com.sk89q.worldguard.protection.flags.DefaultFlag.BLOCKED_CMDS)).contains((Object)new StringBuilder("/").append(s0).toString())) {
                            ((java.util.Set)a3.getFlag((com.sk89q.worldguard.protection.flags.Flag)com.sk89q.worldguard.protection.flags.DefaultFlag.BLOCKED_CMDS)).add((Object)new StringBuilder("/").append(s0).toString());
                            a1.save();
                            ((org.bukkit.entity.Player)a4).sendMessage(Field5.getConfig().getString("Mensagem.Sucesso.Comandos_Desativados").replace((CharSequence)(Object)"@comandos", (CharSequence)(Object)s).replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7"));
                        }
                        ((java.util.Set)a3.getFlag((com.sk89q.worldguard.protection.flags.Flag)com.sk89q.worldguard.protection.flags.DefaultFlag.BLOCKED_CMDS)).remove((Object)"/kgjklfjgkldfhjkldjh");
                    } else if (!((java.util.Set)a3.getFlag((com.sk89q.worldguard.protection.flags.Flag)com.sk89q.worldguard.protection.flags.DefaultFlag.BLOCKED_CMDS)).contains((Object)new StringBuilder("/").append(s0).toString())) {
                        ((java.util.Set)a3.getFlag((com.sk89q.worldguard.protection.flags.Flag)com.sk89q.worldguard.protection.flags.DefaultFlag.BLOCKED_CMDS)).add((Object)new StringBuilder("/").append(s0).toString());
                        a1.save();
                        ((org.bukkit.entity.Player)a4).sendMessage(Field5.getConfig().getString("Mensagem.Sucesso.Comandos_Desativados").replace((CharSequence)(Object)"@comandos", (CharSequence)(Object)s).replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7"));
                    }
                    i1 = i1 + 1;
                    i0 = i1;
                }
            } catch(Exception a6) {
                a0 = a6;
                break label0;
            }
            return;
        }
        a0.printStackTrace();
    }
    
    public static void Method28(org.bukkit.entity.Player a, String s, String s0) {
        com.sk89q.worldguard.protection.managers.RegionManager a0 = Field4.getRegionManager(a.getWorld());
        if (!a0.hasRegion(new StringBuilder(String.valueOf((Object)a.getName().toLowerCase())).append("_").append(s0).toString())) {
            a.sendMessage(Field5.getConfig().getString("Mensagem.Erro.Area_Nao_Encontrada").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7").replace((CharSequence)(Object)"@area", (CharSequence)(Object)s0));
            return;
        }
        com.sk89q.worldguard.protection.regions.ProtectedRegion a1 = a0.getRegion(new StringBuilder(String.valueOf((Object)a.getName().toLowerCase())).append("_").append(s0).toString());
        if (!a1.getOwners().contains(a.getName())) {
            a.sendMessage(Field5.getConfig().getString("Mensagem.Erro.Nao_E_Sua").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7").replace((CharSequence)(Object)"@area", (CharSequence)(Object)s0));
            return;
        }
        if (a1.getMembers().contains(s.toLowerCase())) {
            a.sendMessage(Field5.getConfig().getString("Mensagem.Erro.Ja_Esta_Adicionado").replace((CharSequence)(Object)"@area", (CharSequence)(Object)s0).replace((CharSequence)(Object)"@amigo", (CharSequence)(Object)s).replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7"));
            return;
        }
        a1.getMembers().addPlayer(s.toLowerCase());
        try {
            a0.save();
        } catch(Exception a2) {
            a2.printStackTrace();
        }
        a.sendMessage(Field5.getConfig().getString("Mensagem.Sucesso.Amigo_Adicionado").replace((CharSequence)(Object)"@area", (CharSequence)(Object)s0).replace((CharSequence)(Object)"@amigo", (CharSequence)(Object)s).replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7"));
    }
    
    public static boolean Method29(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch(NumberFormatException ignoredException) {
            return false;
        }
    }
    
    public static void Method30(org.bukkit.entity.Player a, String s) {
        com.sk89q.worldguard.protection.managers.RegionManager a0 = Field4.getRegionManager(a.getWorld());
        if (!a0.hasRegion(new StringBuilder(String.valueOf((Object)a.getName().toLowerCase())).append("_").append(s).toString())) {
            a.sendMessage(Field5.getConfig().getString("Mensagem.Erro.Area_Nao_Encontrada").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7").replace((CharSequence)(Object)"@area", (CharSequence)(Object)s));
            return;
        }
        com.sk89q.worldguard.protection.regions.ProtectedRegion a1 = a0.getRegion(new StringBuilder(String.valueOf((Object)a.getName().toLowerCase())).append("_").append(s).toString());
        if (!a1.getOwners().contains(a.getName())) {
            a.sendMessage(Field5.getConfig().getString("Mensagem.Erro.Nao_E_Sua").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7").replace((CharSequence)(Object)"@area", (CharSequence)(Object)s));
            return;
        }
        try {
            package2.package1.package0.Class1.Method12(a, s);
            package2.package1.package0.Class1.Method23(a, a1.getMinimumPoint().getBlockX(), a1.getMaximumPoint().getBlockX(), a1.getMinimumPoint().getBlockZ(), a1.getMaximumPoint().getBlockZ());
            if (Field5.getConfig().getBoolean("Config.Limpar_Terreno_Ao_Deletar")) {
                a.sendMessage(Field5.getConfig().getString("Mensagem.Sucesso.Deletando").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7").replace((CharSequence)(Object)"@area", (CharSequence)(Object)s));
                com.sk89q.worldedit.regions.Region a2 = ((com.sk89q.worldedit.bukkit.selections.Selection)(Object)new com.sk89q.worldedit.bukkit.selections.CuboidSelection(a.getWorld(), (com.sk89q.worldedit.Vector)a1.getMinimumPoint(), (com.sk89q.worldedit.Vector)a1.getMaximumPoint())).getRegionSelector().getRegion();
                a2.getWorld().regenerate(a2, com.sk89q.worldedit.WorldEdit.getInstance().getEditSessionFactory().getEditSession(a2.getWorld(), -1));
            }
            a0.removeRegion(new StringBuilder(String.valueOf((Object)a.getName().toLowerCase())).append("_").append(s).toString());
            a0.save();
        } catch(Exception a3) {
            a3.printStackTrace();
        }
        a.sendMessage(Field5.getConfig().getString("Mensagem.Sucesso.Deletou").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7").replace((CharSequence)(Object)"@area", (CharSequence)(Object)s));
    }
    
    public static String Method31(org.bukkit.entity.Player a, String s) {
        java.util.Map a0 = Field4.getRegionContainer().get(a.getWorld()).getRegions();
        com.sk89q.worldguard.LocalPlayer a1 = Field4.wrapOfflinePlayer(Field5.getServer().getOfflinePlayer(s));
        java.util.Iterator a2 = a0.values().iterator();
        Object a3 = a2;
        String s0 = "";
        a3 = a2;
        while(((java.util.Iterator)a3).hasNext()) {
            com.sk89q.worldguard.protection.regions.ProtectedRegion a4 = (com.sk89q.worldguard.protection.regions.ProtectedRegion)((java.util.Iterator)a3).next();
            if (a4.isOwner(a1)) {
                s0 = new StringBuilder(String.valueOf((Object)s0)).append(a4.getId().replace((CharSequence)(Object)new StringBuilder(String.valueOf((Object)s.toLowerCase())).append("_").toString(), (CharSequence)(Object)"")).append(", ").toString();
            }
        }
        if (s0.length() >= 2) {
            s0 = s0.substring(0, s0.length() - 2);
        }
        return s0;
    }
    
    public static void Method32(org.bukkit.entity.Player a) {
        com.sk89q.worldguard.protection.managers.RegionManager a0 = Field4.getRegionManager(a.getWorld());
        com.sk89q.worldguard.protection.ApplicableRegionSet a1 = a0.getApplicableRegions(a.getLocation());
        if (a1.size() == 0) {
            a.sendMessage(Field5.getConfig().getString("Mensagem.Erro.Nao_Esta_Em_Uma_Area").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7"));
            return;
        }
        ((Object)a1).toString();
        com.sk89q.worldguard.protection.regions.ProtectedRegion a2 = a0.getRegion(((com.sk89q.worldguard.protection.regions.ProtectedRegion)a1.iterator().next()).getId());
        if (a2.getFlag((com.sk89q.worldguard.protection.flags.Flag)com.sk89q.worldguard.protection.flags.DefaultFlag.PVP) != null && a2.getFlag((com.sk89q.worldguard.protection.flags.Flag)com.sk89q.worldguard.protection.flags.DefaultFlag.MOB_SPAWNING) != null) {
            java.util.Iterator a3 = Field5.getConfig().getStringList("Mensagem.Sucesso.Info_Area").iterator();
            Object a4 = a3;
            Object a5 = a;
            a4 = a3;
            while(((java.util.Iterator)a4).hasNext()) {
                ((org.bukkit.entity.Player)a5).sendMessage(((String)((java.util.Iterator)a4).next()).replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7").replace((CharSequence)(Object)"@mob", (CharSequence)(Object)((com.sk89q.worldguard.protection.flags.StateFlag$State)a2.getFlag((com.sk89q.worldguard.protection.flags.Flag)com.sk89q.worldguard.protection.flags.DefaultFlag.MOB_SPAWNING)).toString().replace((CharSequence)(Object)"ALLOW", (CharSequence)(Object)"Ativado").replace((CharSequence)(Object)"DENY", (CharSequence)(Object)"Desativado")).replace((CharSequence)(Object)"@pvp", (CharSequence)(Object)((com.sk89q.worldguard.protection.flags.StateFlag$State)a2.getFlag((com.sk89q.worldguard.protection.flags.Flag)com.sk89q.worldguard.protection.flags.DefaultFlag.PVP)).toString().replace((CharSequence)(Object)"ALLOW", (CharSequence)(Object)"Ativado").replace((CharSequence)(Object)"DENY", (CharSequence)(Object)"Desativado")).replace((CharSequence)(Object)"@dono", (CharSequence)(Object)a2.getId().split("_")[0]));
            }
            return;
        }
        a.sendMessage(Field5.getConfig().getString("Mensagem.Erro.Nao_Esta_Em_Um_Terreno").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7"));
    }
    
    public static int Method33(org.bukkit.entity.Player a) {
        java.util.Map a0 = Field4.getRegionContainer().get(a.getWorld()).getRegions();
        com.sk89q.worldguard.LocalPlayer a1 = Field4.wrapPlayer(a);
        java.util.Iterator a2 = a0.values().iterator();
        Object a3 = a2;
        int i = 0;
        a3 = a2;
        while(((java.util.Iterator)a3).hasNext()) {
            if (((com.sk89q.worldguard.protection.regions.ProtectedRegion)((java.util.Iterator)a3).next()).isOwner(a1)) {
                i = i + 1;
            }
        }
        return i;
    }
    
    public static void Method34(org.bukkit.entity.Player a, String s, String s0) {
        com.sk89q.worldguard.protection.managers.RegionManager a0 = Field4.getRegionManager(a.getWorld());
        if (!a0.hasRegion(new StringBuilder(String.valueOf((Object)s.toLowerCase())).append("_").append(s0).toString())) {
            a.sendMessage(Field5.getConfig().getString("Mensagem.Erro.Area_Nao_Encontrada").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7").replace((CharSequence)(Object)"@area", (CharSequence)(Object)s0));
            return;
        }
        com.sk89q.worldguard.protection.regions.ProtectedRegion a1 = a0.getRegion(new StringBuilder(String.valueOf((Object)s.toLowerCase())).append("_").append(s0).toString());
        try {
            a.teleport(new org.bukkit.Location(a.getWorld(), a1.getMaximumPoint().getX(), (double)a.getWorld().getHighestBlockYAt(a1.getMaximumPoint().getBlockX(), a1.getMaximumPoint().getBlockZ()), a1.getMaximumPoint().getZ()));
        } catch(Exception a2) {
            a2.printStackTrace();
        }
        a.sendMessage(Field5.getConfig().getString("Mensagem.Sucesso.Teleportado_Staff").replace((CharSequence)(Object)"@player", (CharSequence)(Object)s).replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7"));
    }
    
    public static void Method35(org.bukkit.Location a, int i, int i0, int i1, int i2, int i3) {
        org.bukkit.World a0 = a.getWorld();
        int i4 = i;
        Object a1 = a0;
        i4 = i;
        while(i4 < i0) {
            org.bukkit.block.Block a2 = ((org.bukkit.World)a1).getBlockAt(i4, a.getBlockY(), i1);
            org.bukkit.configuration.file.FileConfiguration a3 = Field5.getConfig();
            i4 = i4 + 1;
            a2.setType(org.bukkit.Material.getMaterial(a3.getString("Config.Bloco_Cercar")));
        }
        int i5 = i;
        i5 = i;
        while(i5 <= i0) {
            org.bukkit.block.Block a4 = ((org.bukkit.World)a1).getBlockAt(i5, a.getBlockY(), i2);
            org.bukkit.configuration.file.FileConfiguration a5 = Field5.getConfig();
            i5 = i5 + 1;
            a4.setType(org.bukkit.Material.getMaterial(a5.getString("Config.Bloco_Cercar")));
        }
        int i6 = i1;
        i6 = i1;
        while(i6 < i2) {
            org.bukkit.block.Block a6 = ((org.bukkit.World)a1).getBlockAt(i, a.getBlockY(), i6);
            org.bukkit.configuration.file.FileConfiguration a7 = Field5.getConfig();
            i6 = i6 + 1;
            a6.setType(org.bukkit.Material.getMaterial(a7.getString("Config.Bloco_Cercar")));
        }
        while(i1 <= i2) {
            org.bukkit.block.Block a8 = ((org.bukkit.World)a1).getBlockAt(i0, a.getBlockY(), i1);
            org.bukkit.configuration.file.FileConfiguration a9 = Field5.getConfig();
            int i7 = i1 + 1;
            a8.setType(org.bukkit.Material.getMaterial(a9.getString("Config.Bloco_Cercar")));
            i1 = i7;
            i1 = i7;
        }
    }
    
    public static void Method36(org.bukkit.entity.Player a, String s) {
        com.sk89q.worldguard.protection.managers.RegionManager a0 = Field4.getRegionManager(a.getWorld());
        if (!a0.hasRegion(new StringBuilder(String.valueOf((Object)a.getName().toLowerCase())).append("_").append(s).toString())) {
            a.sendMessage(Field5.getConfig().getString("Mensagem.Erro.Area_Nao_Encontrada").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7").replace((CharSequence)(Object)"@area", (CharSequence)(Object)s));
            return;
        }
        com.sk89q.worldguard.protection.regions.ProtectedRegion a1 = a0.getRegion(new StringBuilder(String.valueOf((Object)a.getName().toLowerCase())).append("_").append(s).toString());
        if (!a1.getOwners().contains(a.getName())) {
            a.sendMessage(Field5.getConfig().getString("Mensagem.Erro.Nao_E_Sua").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7").replace((CharSequence)(Object)"@area", (CharSequence)(Object)s));
            return;
        }
        try {
            a.teleport(new org.bukkit.Location(a.getWorld(), a1.getMaximumPoint().getX(), (double)a.getWorld().getHighestBlockYAt(a1.getMaximumPoint().getBlockX(), a1.getMaximumPoint().getBlockZ()), a1.getMaximumPoint().getZ()));
        } catch(Exception a2) {
            a2.printStackTrace();
        }
        a.sendMessage(Field5.getConfig().getString("Mensagem.Sucesso.Teleportado").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7"));
    }
    
    public Class1() {
        super();
    }
}
