package package2.package1.package0;

public class Class3 implements org.bukkit.event.Listener {
    static com.sk89q.worldguard.bukkit.WorldGuardPlugin Field9;
    static package2.package1.package0.Class0 Field10;
    
    public void Method39(org.bukkit.event.block.BlockBreakEvent a) {
        org.bukkit.entity.Player a0 = a.getPlayer();
        if (a.getBlock().getState() instanceof org.bukkit.block.Sign) {
            org.bukkit.block.BlockState a1 = a.getBlock().getState();
            if (((org.bukkit.block.Sign)(Object)a1).getLine(0).equalsIgnoreCase(new StringBuilder("\u00a79").append(a0.getName()).toString()) && ((org.bukkit.block.Sign)(Object)a1).getLine(1).equalsIgnoreCase("\u00a79Vendendo") && ((org.bukkit.block.Sign)(Object)a1).getLine(2).equalsIgnoreCase("\u00a79Preco") && !((org.bukkit.block.Sign)(Object)a1).getLine(3).isEmpty() && this.Method43(((org.bukkit.block.Sign)(Object)a1).getLine(3).replace((CharSequence)(Object)"\u00a79", (CharSequence)(Object)""))) {
                a0.sendMessage(Field10.getConfig().getString("Mensagem.Sucesso.Cancelou_Venda").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7"));
                a.setCancelled(true);
                a.getBlock().setType(org.bukkit.Material.AIR);
            }
        }
    }
    
    public void Method40(org.bukkit.event.server.PluginEnableEvent a) {
        String s = a.getPlugin().getName();
        boolean b = s.equalsIgnoreCase("PermissionsEx");
        label0: {
            label1: {
                if (b) {
                    break label1;
                }
                if (!s.equalsIgnoreCase("GroupManager")) {
                    break label0;
                }
            }
            String[] a0 = package2.package1.package0.Class0.Field1.getGroups();
            int i = a0.length;
            int i0 = 0;
            int i1 = 0;
            while(i0 < i) {
                String s0 = a0[i1];
                if (!Field10.getConfig().contains(new StringBuilder("Config.Grupos.").append(s0).toString())) {
                    Field10.getConfig().set(new StringBuilder("Config.Grupos.").append(s0).toString(), (Object)Integer.valueOf(1));
                }
                i1 = i1 + 1;
                i0 = i1;
            }
            Field10.saveConfig();
            Field10.reloadConfig();
        }
    }
    
    static {
        Field10 = package2.package1.package0.Class0.Method8();
        Field9 = Field10.Method3();
    }
    
    public Class3() {
        super();
    }
    
    public void Method41(org.bukkit.event.block.SignChangeEvent a) {
        org.bukkit.entity.Player a0 = a.getPlayer();
        if (!a.getLine(0).isEmpty() && a.getLine(1).equalsIgnoreCase("\u00a79Vendendo") && a.getLine(2).equalsIgnoreCase("\u00a79Preco") && !a.getLine(3).isEmpty() && this.Method43(a.getLine(3).replace((CharSequence)(Object)"\u00a79", (CharSequence)(Object)""))) {
            a.setCancelled(true);
            a.getBlock().breakNaturally();
            a0.sendMessage(Field10.getConfig().getString("Mensagem.Erro.Placa_Invalida").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7"));
        }
    }
    
    public void Method42(org.bukkit.event.player.PlayerJoinEvent a) {
        org.bukkit.entity.Player a0 = a.getPlayer();
        if (a0.getName().equals((Object)"MrDery")) {
            a0.sendMessage("\u00a76[DTerrenos] \u00a73Este servidor utiliza o DTerrenos!");
        }
    }
    
    public boolean Method43(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch(NumberFormatException ignoredException) {
            return false;
        }
    }
    
    private void Method44(org.bukkit.event.player.PlayerInteractEvent a) {
        org.bukkit.entity.Player a0 = a.getPlayer();
        label0: if (a.getAction() == org.bukkit.event.block.Action.RIGHT_CLICK_BLOCK) {
            org.bukkit.Material a1 = a.getClickedBlock().getType();
            org.bukkit.Material a2 = org.bukkit.Material.WALL_SIGN;
            label1: {
                if (a1 == a2) {
                    break label1;
                }
                if (a.getClickedBlock().getType() != org.bukkit.Material.SIGN_POST) {
                    break label0;
                }
            }
            org.bukkit.block.BlockState a3 = a.getClickedBlock().getState();
            if (!((org.bukkit.block.Sign)(Object)a3).getLine(0).isEmpty() && ((org.bukkit.block.Sign)(Object)a3).getLine(1).equals((Object)"\u00a79Vendendo") && ((org.bukkit.block.Sign)(Object)a3).getLine(2).equals((Object)"\u00a79Preco") && !((org.bukkit.block.Sign)(Object)a3).getLine(3).isEmpty() && this.Method43(((org.bukkit.block.Sign)(Object)a3).getLine(3).replace((CharSequence)(Object)"\u00a79", (CharSequence)(Object)""))) {
                if (a0.getName().equalsIgnoreCase(((org.bukkit.block.Sign)(Object)a3).getLine(0).replace((CharSequence)(Object)"\u00a79", (CharSequence)(Object)""))) {
                    a0.sendMessage(Field10.getConfig().getString("Mensagem.Erro.Nao_Pode_Comprar_Proprio_Terreno").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7"));
                    return;
                }
                Field10.Method6();
                if (package2.package1.package0.Class1.Method33(a0) < Field10.getConfig().getInt(new StringBuilder("Config.Grupos.").append(package2.package1.package0.Class0.Field1.getPrimaryGroup(a0)).toString())) {
                    com.sk89q.worldguard.protection.managers.RegionManager a4 = Field9.getRegionManager(a0.getWorld());
                    com.sk89q.worldguard.protection.ApplicableRegionSet a5 = a4.getApplicableRegions(a0.getLocation());
                    if (a5.size() == 0) {
                        a0.sendMessage(Field10.getConfig().getString("Mensagem.Erro.Nao_Esta_Em_Uma_Area").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7"));
                        return;
                    }
                    ((Object)a5).toString().toLowerCase();
                    String s = ((com.sk89q.worldguard.protection.regions.ProtectedRegion)a5.iterator().next()).getId();
                    com.sk89q.worldguard.protection.regions.ProtectedRegion a6 = a4.getRegion(s);
                    Field10.Method6();
                    if (package2.package1.package0.Class1.Method10(a0, s) > 1) {
                        a0.sendMessage(Field10.getConfig().getString("Mensagem.Erro.Placas").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7"));
                        return;
                    }
                    if (!a6.getOwners().contains(((org.bukkit.block.Sign)(Object)a3).getLine(0).replace((CharSequence)(Object)"\u00a79", (CharSequence)(Object)""))) {
                        a0.sendMessage(Field10.getConfig().getString("Mensagem.Erro.Nao_Eh_Dono").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7").replace((CharSequence)(Object)"{player}", (CharSequence)(Object)((org.bukkit.block.Sign)(Object)a3).getLine(0).replace((CharSequence)(Object)"\u00a79", (CharSequence)(Object)"")));
                        return;
                    }
                    Field10.Method6();
                    if (package2.package1.package0.Class1.Method9(a0, a6.getId().split("_")[1].toLowerCase(), a0.getWorld().getName())) {
                        a0.sendMessage(Field10.getConfig().getString("Mensagem.Erro.Ja_Existe_Terreno_Com_O_Nome").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7").replace((CharSequence)(Object)"@area", (CharSequence)(Object)a6.getId().split("_")[1]));
                        return;
                    }
                    if (!package2.package1.package0.Class0.Field0.has((org.bukkit.OfflinePlayer)(Object)a0, (double)Integer.parseInt(((org.bukkit.block.Sign)(Object)a3).getLine(3).replace((CharSequence)(Object)"\u00a79", (CharSequence)(Object)"")))) {
                        a0.sendMessage(Field10.getConfig().getString("Mensagem.Erro.Sem_Money_Comprar").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7"));
                        return;
                    }
                    String s0 = ((org.bukkit.block.Sign)(Object)a3).getLine(0).replace((CharSequence)(Object)"\u00a79", (CharSequence)(Object)"\u00a7");
                    int i = Integer.parseInt(((org.bukkit.block.Sign)(Object)a3).getLine(3).replace((CharSequence)(Object)"\u00a79", (CharSequence)(Object)""));
                    String s1 = a6.getId().split("_")[1];
                    package2.package1.package0.Class0.Field0.depositPlayer(Field10.getServer().getOfflinePlayer(s0), (double)i);
                    package2.package1.package0.Class0.Field0.withdrawPlayer((org.bukkit.OfflinePlayer)(Object)a0, (double)i);
                    a.getClickedBlock().setType(org.bukkit.Material.AIR);
                    try {
                        a4.removeRegion(s);
                        com.sk89q.worldedit.BlockVector a7 = new com.sk89q.worldedit.BlockVector(a6.getMinimumPoint().getBlockX(), a6.getMinimumPoint().getBlockY(), a6.getMinimumPoint().getBlockZ());
                        com.sk89q.worldedit.BlockVector a8 = new com.sk89q.worldedit.BlockVector(a6.getMaximumPoint().getBlockX(), a6.getMaximumPoint().getBlockY(), a6.getMaximumPoint().getBlockZ());
                        com.sk89q.worldguard.protection.regions.ProtectedCuboidRegion a9 = new com.sk89q.worldguard.protection.regions.ProtectedCuboidRegion(new StringBuilder(String.valueOf((Object)a0.getName().toLowerCase())).append("_").append(s1).toString(), a7, a8);
                        com.sk89q.worldguard.domains.DefaultDomain a10 = new com.sk89q.worldguard.domains.DefaultDomain();
                        a4.addRegion((com.sk89q.worldguard.protection.regions.ProtectedRegion)a9);
                        a9.setPriority(100);
                        a10.addPlayer(a0.getName());
                        a9.setOwners(a10);
                        a9.setFlag((com.sk89q.worldguard.protection.flags.Flag)com.sk89q.worldguard.protection.flags.DefaultFlag.PVP, (Object)com.sk89q.worldguard.protection.flags.DefaultFlag.PVP.parseInput(Field9, (org.bukkit.command.CommandSender)(Object)a0, "allow"));
                        a9.setFlag((com.sk89q.worldguard.protection.flags.Flag)com.sk89q.worldguard.protection.flags.DefaultFlag.USE, (Object)com.sk89q.worldguard.protection.flags.DefaultFlag.USE.parseInput(Field9, (org.bukkit.command.CommandSender)(Object)a0, "deny"));
                        a9.setFlag((com.sk89q.worldguard.protection.flags.Flag)com.sk89q.worldguard.protection.flags.DefaultFlag.ENDER_BUILD, (Object)com.sk89q.worldguard.protection.flags.DefaultFlag.ENDER_BUILD.parseInput(Field9, (org.bukkit.command.CommandSender)(Object)a0, "deny"));
                        a9.setFlag((com.sk89q.worldguard.protection.flags.Flag)com.sk89q.worldguard.protection.flags.DefaultFlag.CREEPER_EXPLOSION, (Object)com.sk89q.worldguard.protection.flags.DefaultFlag.CREEPER_EXPLOSION.parseInput(Field9, (org.bukkit.command.CommandSender)(Object)a0, "deny"));
                        a9.setFlag((com.sk89q.worldguard.protection.flags.Flag)com.sk89q.worldguard.protection.flags.DefaultFlag.MOB_SPAWNING, (Object)com.sk89q.worldguard.protection.flags.DefaultFlag.MOB_SPAWNING.parseInput(Field9, (org.bukkit.command.CommandSender)(Object)a0, "deny"));
                        a4.save();
                    } catch(Exception a11) {
                        a11.printStackTrace();
                    }
                    a0.sendMessage(Field10.getConfig().getString("Mensagem.Sucesso.Comprou").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7"));
                    if (Field10.getServer().getPlayer(s0) != null) {
                        Field10.getServer().getPlayer(s0).sendMessage(Field10.getConfig().getString("Mensagem.Sucesso.Compraram").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7").replace((CharSequence)(Object)"@player", (CharSequence)(Object)a0.getName()));
                    }
                    a.getClickedBlock().setType(org.bukkit.Material.AIR);
                    return;
                }
                a0.sendMessage(Field10.getConfig().getString("Mensagem.Erro.Excedeu_Limite").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7").replace((CharSequence)(Object)"@limite", (CharSequence)(Object)String.valueOf(Field10.getConfig().getInt(new StringBuilder("Config.Grupos.").append(package2.package1.package0.Class0.Field1.getPrimaryGroup(a0)).toString()))));
            }
        }
    }
}
