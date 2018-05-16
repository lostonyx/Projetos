package package2.package1.package0;

public class Class0 extends org.bukkit.plugin.java.JavaPlugin {
    public static net.milkbowl.vault.economy.Economy Field0;
    public static net.milkbowl.vault.permission.Permission Field1;
    private package2.package1.package0.Class1 Field2;
    private package2.package1.package0.Class2 Field3;
    
    public Class0() {
        super();
    }
    
    public boolean Method0() {
        org.bukkit.plugin.RegisteredServiceProvider a = this.getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (a != null) {
            Field0 = (net.milkbowl.vault.economy.Economy)a.getProvider();
        }
        if (Field0 == null) {
            return false;
        }
        return true;
    }
    
    public void Method1() {
        java.net.URLConnection a = new java.net.URL("http://derydery.esy.es/dterrenos.txt").openConnection();
        a.setReadTimeout(5000);
        String s = new java.io.BufferedReader((java.io.Reader)new java.io.InputStreamReader(a.getInputStream())).readLine();
        if (s == null) {
            this.getServer().getConsoleSender().sendMessage(" \u00a74Ocorreu um erro ao verificar o status do plugin!");
            org.bukkit.Bukkit.getServer().getPluginManager().disablePlugin((org.bukkit.plugin.Plugin)(Object)this);
            return;
        }
        if (!s.contains((CharSequence)(Object)"OK")) {
            this.getServer().getConsoleSender().sendMessage(" \u00a74O plugin esta desativado!");
            org.bukkit.Bukkit.getServer().getPluginManager().disablePlugin((org.bukkit.plugin.Plugin)(Object)this);
            return;
        }
        this.getServer().getConsoleSender().sendMessage("\u00a73==========[\u00a7bDTerrenos\u00a73]==========");
        this.getServer().getConsoleSender().sendMessage(" \u00a73Status: \u00a7bAtivado");
        this.getServer().getConsoleSender().sendMessage(" \u00a73By: \u00a7bDery");
        this.getServer().getConsoleSender().sendMessage(new StringBuilder(" \u00a73Versao: \u00a7b").append(this.getDescription().getVersion()).toString());
        if (org.bukkit.Bukkit.getServer().getPluginManager().getPlugin("Vault") == null) {
            this.getServer().getConsoleSender().sendMessage(" \u00a73Vault: \u00a7bNao Encontrado");
            org.bukkit.Bukkit.getPluginManager().disablePlugin((org.bukkit.plugin.Plugin)(Object)this);
            return;
        }
        this.getServer().getConsoleSender().sendMessage(" \u00a73Vault: \u00a7bHooked (Economy)");
        if (org.bukkit.Bukkit.getServer().getPluginManager().getPlugin("WorldEdit") == null) {
            this.getServer().getConsoleSender().sendMessage(" \u00a73WorldEdit: \u00a7bNao Encontrado");
            org.bukkit.Bukkit.getPluginManager().disablePlugin((org.bukkit.plugin.Plugin)(Object)this);
            return;
        }
        this.getServer().getConsoleSender().sendMessage(" \u00a73WorldEdit: \u00a7bHooked");
        if (org.bukkit.Bukkit.getServer().getPluginManager().getPlugin("WorldGuard") == null) {
            this.getServer().getConsoleSender().sendMessage(" \u00a73WorldGuard: \u00a7bNao Encontrado");
            org.bukkit.Bukkit.getPluginManager().disablePlugin((org.bukkit.plugin.Plugin)(Object)this);
            return;
        }
        this.getServer().getConsoleSender().sendMessage(" \u00a73WorldGuard: \u00a7bHooked (Regions)");
        this.Method0();
        this.Method5();
        this.Method2();
        this.getCommand("terreno").setExecutor((org.bukkit.command.CommandExecutor)(Object)new package2.package1.package0.Class2());
        this.getCommand("terrenos").setExecutor((org.bukkit.command.CommandExecutor)(Object)new package2.package1.package0.Class2());
        org.bukkit.Bukkit.getServer().getPluginManager().registerEvents((org.bukkit.event.Listener)(Object)new package2.package1.package0.Class3(), (org.bukkit.plugin.Plugin)(Object)this);
        boolean b = new java.io.File(this.getDataFolder(), "config.yml").exists();
        label0: {
            label3: {
                label6: {
                    label1: {
                        {
                            label5: {
                                if (b) {
                                    break label5;
                                }
                                this.saveDefaultConfig();
                                this.getServer().getConsoleSender().sendMessage(" \u00a73Config: \u00a7bCriada");
                                org.bukkit.plugin.Plugin a0 = org.bukkit.Bukkit.getServer().getPluginManager().getPlugin("PermissionsEx");
                                label4: {
                                    if (a0 == null) {
                                        break label4;
                                    }
                                    if (org.bukkit.Bukkit.getServer().getPluginManager().getPlugin("PermissionsEx").isEnabled()) {
                                        break label3;
                                    }
                                }
                                if (org.bukkit.Bukkit.getServer().getPluginManager().getPlugin("GroupManager") == null) {
                                    break label0;
                                }
                                if (!org.bukkit.Bukkit.getServer().getPluginManager().getPlugin("GroupManager").isEnabled()) {
                                    break label0;
                                }
                                break label6;
                            }
                            org.bukkit.plugin.Plugin a1 = org.bukkit.Bukkit.getServer().getPluginManager().getPlugin("PermissionsEx");
                            label2: {
                                if (a1 == null) {
                                    break label2;
                                }
                                if (org.bukkit.Bukkit.getServer().getPluginManager().getPlugin("PermissionsEx").isEnabled()) {
                                    break label1;
                                }
                            }
                            if (org.bukkit.Bukkit.getServer().getPluginManager().getPlugin("GroupManager") == null) {
                                break label0;
                            }
                            if (!org.bukkit.Bukkit.getServer().getPluginManager().getPlugin("GroupManager").isEnabled()) {
                                break label0;
                            }
                            try {
                                String[] a2 = Field1.getGroups();
                                int i = a2.length;
                                int i0 = 0;
                                boolean b0 = false;
                                int i1 = 0;
                                while(i0 < i) {
                                    String s0 = a2[i1];
                                    if (!this.getConfig().contains(new StringBuilder("Config.Grupos.").append(s0).toString())) {
                                        this.getConfig().set(new StringBuilder("Config.Grupos.").append(s0).toString(), (Object)Integer.valueOf(1));
                                        b0 = true;
                                    }
                                    i1 = i1 + 1;
                                    i0 = i1;
                                }
                                this.saveConfig();
                                this.reloadConfig();
                                if (b0) {
                                    this.getServer().getConsoleSender().sendMessage(" \u00a73Config: \u00a7bGrupos Atualizados!");
                                } else
                                {
                                    this.getServer().getConsoleSender().sendMessage(" \u00a73Config: \u00a7bJa Existente");
                                }
                            } catch(Exception ignoredException) {
                            }
                        }
                        break label0;
                    }
                    try {
                        String[] a3 = Field1.getGroups();
                        int i2 = a3.length;
                        int i3 = 0;
                        boolean b1 = false;
                        int i4 = 0;
                        while(i3 < i2) {
                            String s1 = a3[i4];
                            if (!this.getConfig().contains(new StringBuilder("Config.Grupos.").append(s1).toString())) {
                                this.getConfig().set(new StringBuilder("Config.Grupos.").append(s1).toString(), (Object)Integer.valueOf(1));
                                b1 = true;
                            }
                            i4 = i4 + 1;
                            i3 = i4;
                        }
                        this.saveConfig();
                        this.reloadConfig();
                        if (b1) {
                            this.getServer().getConsoleSender().sendMessage(" \u00a73Config: \u00a7bGrupos Atualizados!");
                            break label0;
                        } else
                        {
                            this.getServer().getConsoleSender().sendMessage(" \u00a73Config: \u00a7bJa Existente");
                            break label0;
                        }
                    } catch(Exception ignoredException0) {
                        break label0;
                    }
                }
                try {
                    String[] a4 = Field1.getGroups();
                    int i5 = a4.length;
                    int i6 = 0;
                    int i7 = 0;
                    while(i6 < i5) {
                        String s2 = a4[i7];
                        org.bukkit.configuration.file.FileConfiguration a5 = this.getConfig();
                        String s3 = new StringBuilder("Config.Grupos.").append(s2).toString();
                        i7 = i7 + 1;
                        a5.set(s3, (Object)Integer.valueOf(1));
                        i6 = i7;
                    }
                    this.saveConfig();
                    this.reloadConfig();
                    break label0;
                } catch(Exception ignoredException1) {
                    break label0;
                }
            }
            try {
                String[] a6 = Field1.getGroups();
                int i8 = a6.length;
                int i9 = 0;
                int i10 = 0;
                while(i9 < i8) {
                    String s4 = a6[i10];
                    org.bukkit.configuration.file.FileConfiguration a7 = this.getConfig();
                    String s5 = new StringBuilder("Config.Grupos.").append(s4).toString();
                    i10 = i10 + 1;
                    a7.set(s5, (Object)Integer.valueOf(1));
                    i9 = i10;
                }
                this.saveConfig();
                this.reloadConfig();
            } catch(Exception ignoredException2) {
            }
        }
        this.getServer().getConsoleSender().sendMessage("\u00a73==========[\u00a7bDTerrenos\u00a73]==========");
    }
    
    public void onEnable() {
        try {
            this.Method1();
            return;
        } catch(java.io.IOException a) {
            a.printStackTrace();
            return;
        }
    }
    
    public void Method2() {
        if (!new java.io.File(this.getDataFolder(), "pequeno.schematic").exists()) {
            this.saveResource("pequeno.schematic", false);
        }
        if (!new java.io.File(this.getDataFolder(), "medio.schematic").exists()) {
            this.saveResource("medio.schematic", false);
        }
        if (!new java.io.File(this.getDataFolder(), "grande.schematic").exists()) {
            this.saveResource("grande.schematic", false);
        }
    }
    
    public void onDisable() {
        org.bukkit.event.HandlerList.unregisterAll((org.bukkit.plugin.Plugin)(Object)this);
        this.getServer().getConsoleSender().sendMessage("\u00a74==========[\u00a7cDTerrenos\u00a74]==========");
        this.getServer().getConsoleSender().sendMessage(" \u00a74Status: \u00a7cDesativado");
        this.getServer().getConsoleSender().sendMessage(" \u00a74By: \u00a7cDery");
        this.getServer().getConsoleSender().sendMessage(new StringBuilder(" \u00a74Versao: \u00a7c").append(this.getDescription().getVersion()).toString());
        this.getServer().getConsoleSender().sendMessage("\u00a74==========[\u00a7cDTerrenos\u00a74]==========");
    }
    
    public com.sk89q.worldguard.bukkit.WorldGuardPlugin Method3() {
        org.bukkit.plugin.Plugin a = this.getServer().getPluginManager().getPlugin("WorldGuard");
        if (a != null && a instanceof com.sk89q.worldguard.bukkit.WorldGuardPlugin) {
            return (com.sk89q.worldguard.bukkit.WorldGuardPlugin)(Object)a;
        }
        com.sk89q.worldguard.bukkit.WorldGuardPlugin a0 = null;
        return a0;
    }
    
    public com.sk89q.worldedit.bukkit.WorldEditPlugin Method4() {
        org.bukkit.plugin.Plugin a = this.getServer().getPluginManager().getPlugin("WorldEdit");
        if (a != null && a instanceof com.sk89q.worldedit.bukkit.WorldEditPlugin) {
            return (com.sk89q.worldedit.bukkit.WorldEditPlugin)(Object)a;
        }
        com.sk89q.worldedit.bukkit.WorldEditPlugin a0 = null;
        return a0;
    }
    
    public boolean Method5() {
        org.bukkit.plugin.RegisteredServiceProvider a = this.getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
        if (a != null) {
            Field1 = (net.milkbowl.vault.permission.Permission)a.getProvider();
        }
        if (Field1 == null) {
            return false;
        }
        return true;
    }
    
    public package2.package1.package0.Class1 Method6() {
        return this.Field2;
    }
    
    public package2.package1.package0.Class2 Method7() {
        return this.Field3;
    }
    
    static {
        Field0 = null;
        Field1 = null;
    }
    
    public static package2.package1.package0.Class0 Method8() {
        return (package2.package1.package0.Class0)(Object)org.bukkit.Bukkit.getServer().getPluginManager().getPlugin("DTerrenos");
    }
}
