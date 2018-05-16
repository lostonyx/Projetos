package package2.package1.package0;

public class Class2 implements org.bukkit.command.CommandExecutor {
    static package2.package1.package0.Class0 Field6;
    static package2.package1.package0.Class1 Field7;
    static java.util.Random Field8;
    
    public Class2() {
        super();
    }
    
    public boolean Method37(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch(NumberFormatException ignoredException) {
            return false;
        }
    }
    
    public void Method38(org.bukkit.entity.Player a, java.util.List a0) {
        java.util.Iterator a1 = a0.iterator();
        Object a2 = a1;
        Object a3 = a;
        a2 = a1;
        while(((java.util.Iterator)a2).hasNext()) {
            ((org.bukkit.entity.Player)a3).sendMessage(((String)((java.util.Iterator)a2).next()).replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7"));
        }
    }
    
    public boolean onCommand(org.bukkit.command.CommandSender a, org.bukkit.command.Command a0, String s, String[] a1) {
        label1: if (a0.getName().equalsIgnoreCase("terreno")) {
            if (!(a instanceof org.bukkit.entity.Player)) {
                a.sendMessage("Unknown command. Type \"help\" for help.");
                return true;
            }
            if (a1.length == 0) {
                this.Method38((org.bukkit.entity.Player)(Object)a, Field6.getConfig().getStringList("Mensagem.Comandos"));
                return true;
            }
            if (a1[0].equalsIgnoreCase(Field6.getConfig().getString("Config.Sub_Comandos.Comprar"))) {
                if (Field6.getConfig().getStringList("Config.Mundos_Possiveis").contains((Object)((org.bukkit.entity.Player)(Object)a).getWorld().getName())) {
                    if (a1.length < 3) {
                        ((org.bukkit.entity.Player)(Object)a).sendMessage(Field6.getConfig().getString("Mensagem.Erro.Erro_Comprar").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7"));
                        return true;
                    }
                    if (!a1[2].equalsIgnoreCase("pequeno") && !a1[2].equalsIgnoreCase("medio") && !a1[2].equalsIgnoreCase("grande") && !this.Method37(a1[2])) {
                        ((org.bukkit.entity.Player)(Object)a).sendMessage(Field6.getConfig().getString("Mensagem.Erro.Somente_Numeros").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7"));
                        return true;
                    }
                    if (!a1[2].equalsIgnoreCase("pequeno") && !a1[2].equalsIgnoreCase("medio") && !a1[2].equalsIgnoreCase("grande") && Integer.parseInt(a1[2]) % 2 != 0) {
                        ((org.bukkit.entity.Player)(Object)a).sendMessage(Field6.getConfig().getString("Mensagem.Erro.Nao_E_Par").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7"));
                        return true;
                    }
                    label8: if (this.Method37(a1[2])) {
                        int i = Integer.parseInt(a1[2]);
                        int i0 = Field6.getConfig().getInt("Config.Tamanho_Min");
                        label9: {
                            if (i < i0) {
                                break label9;
                            }
                            if (Integer.parseInt(a1[2]) <= Field6.getConfig().getInt("Config.Tamanho_Max")) {
                                break label8;
                            }
                        }
                        ((org.bukkit.entity.Player)(Object)a).sendMessage(Field6.getConfig().getString("Mensagem.Erro.Tamanho_Invalido").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7"));
                        return true;
                    }
                    if (a1[1].length() > 8) {
                        ((org.bukkit.entity.Player)(Object)a).sendMessage(Field6.getConfig().getString("Mensagem.Erro.Tamanho_Maximo_Nome").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7"));
                        return true;
                    }
                    if (package2.package1.package0.Class1.Method33((org.bukkit.entity.Player)(Object)a) >= Field6.getConfig().getInt(new StringBuilder("Config.Grupos.").append(package2.package1.package0.Class0.Field1.getPrimaryGroup((org.bukkit.entity.Player)(Object)a)).toString())) {
                        ((org.bukkit.entity.Player)(Object)a).sendMessage(Field6.getConfig().getString("Mensagem.Erro.Excedeu_Limite").replace((CharSequence)(Object)"@limite", (CharSequence)(Object)String.valueOf(Field6.getConfig().getInt(new StringBuilder("Config.Grupos.").append(package2.package1.package0.Class0.Field1.getPrimaryGroup((org.bukkit.entity.Player)(Object)a)).toString()))).replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7"));
                    } else
                    {
                        String s0 = a1[1];
                        if (a1[2].equalsIgnoreCase("pequeno")) {
                            if (!((org.bukkit.entity.Player)(Object)a).hasPermission("dterrenos.player") && !((org.bukkit.entity.Player)(Object)a).hasPermission("dterrenos.comprar.pequeno") && !((org.bukkit.entity.Player)(Object)a).hasPermission("dterrenos.comprar.*")) {
                                ((org.bukkit.entity.Player)(Object)a).sendMessage(Field6.getConfig().getString("Mensagem.Sem_Permissao_Pequeno").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7"));
                                return true;
                            }
                            if (package2.package1.package0.Class0.Field0.has((org.bukkit.OfflinePlayer)(Object)a, (double)(Field6.getConfig().getInt("Config.Preco_Bloco") * Field6.getConfig().getInt("Config.Tamanho_Terreno_Pequeno")))) {
                                package2.package1.package0.Class1.Method25((org.bukkit.entity.Player)(Object)a, s0, Field6.getConfig().getInt("Config.Tamanho_Terreno_Pequeno"));
                            } else
                            {
                                ((org.bukkit.entity.Player)(Object)a).sendMessage(Field6.getConfig().getString("Mensagem.Erro.Sem_Money_Comprar").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7"));
                            }
                        } else if (a1[2].equalsIgnoreCase("medio")) {
                            if (!((org.bukkit.entity.Player)(Object)a).hasPermission("dterrenos.player") && !((org.bukkit.entity.Player)(Object)a).hasPermission("dterrenos.comprar.medio") && !((org.bukkit.entity.Player)(Object)a).hasPermission("dterrenos.comprar.*")) {
                                ((org.bukkit.entity.Player)(Object)a).sendMessage(Field6.getConfig().getString("Mensagem.Sem_Permissao_Medio").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7"));
                                return true;
                            }
                            if (package2.package1.package0.Class0.Field0.has((org.bukkit.OfflinePlayer)(Object)a, (double)(Field6.getConfig().getInt("Config.Preco_Bloco") * Field6.getConfig().getInt("Config.Tamanho_Terreno_Medio")))) {
                                package2.package1.package0.Class1.Method25((org.bukkit.entity.Player)(Object)a, s0, Field6.getConfig().getInt("Config.Tamanho_Terreno_Medio"));
                            } else
                            {
                                ((org.bukkit.entity.Player)(Object)a).sendMessage(Field6.getConfig().getString("Mensagem.Erro.Sem_Money_Comprar").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7"));
                            }
                        } else if (a1[2].equalsIgnoreCase("grande")) {
                            if (!((org.bukkit.entity.Player)(Object)a).hasPermission("dterrenos.player") && !((org.bukkit.entity.Player)(Object)a).hasPermission("dterrenos.comprar.grande") && !((org.bukkit.entity.Player)(Object)a).hasPermission("dterrenos.comprar.*")) {
                                ((org.bukkit.entity.Player)(Object)a).sendMessage(Field6.getConfig().getString("Mensagem.Sem_Permissao_Grande").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7"));
                                return true;
                            }
                            if (package2.package1.package0.Class0.Field0.has((org.bukkit.OfflinePlayer)(Object)a, (double)(Field6.getConfig().getInt("Config.Preco_Bloco") * Field6.getConfig().getInt("Config.Tamanho_Terreno_Grande")))) {
                                package2.package1.package0.Class1.Method25((org.bukkit.entity.Player)(Object)a, s0, Field6.getConfig().getInt("Config.Tamanho_Terreno_Grande"));
                            } else
                            {
                                ((org.bukkit.entity.Player)(Object)a).sendMessage(Field6.getConfig().getString("Mensagem.Erro.Sem_Money_Comprar").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7"));
                            }
                        } else
                        {
                            if (!((org.bukkit.entity.Player)(Object)a).hasPermission("dterrenos.player") && !((org.bukkit.entity.Player)(Object)a).hasPermission("dterrenos.comprar.personalizado") && !((org.bukkit.entity.Player)(Object)a).hasPermission("dterrenos.comprar.*")) {
                                ((org.bukkit.entity.Player)(Object)a).sendMessage(Field6.getConfig().getString("Mensagem.Sem_Permissao_Personalizado").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7"));
                                return true;
                            }
                            if (package2.package1.package0.Class0.Field0.has((org.bukkit.OfflinePlayer)(Object)a, (double)(Field6.getConfig().getInt("Config.Preco_Bloco") * Integer.parseInt(a1[2])))) {
                                package2.package1.package0.Class1.Method25((org.bukkit.entity.Player)(Object)a, s0, Integer.parseInt(a1[2]));
                            } else
                            {
                                ((org.bukkit.entity.Player)(Object)a).sendMessage(Field6.getConfig().getString("Mensagem.Erro.Sem_Money_Comprar").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7"));
                            }
                        }
                    }
                } else
                {
                    ((org.bukkit.entity.Player)(Object)a).sendMessage(Field6.getConfig().getString("Mensagem.Erro.Mundo_Desativado").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7"));
                }
            } else
            {
                if (a1[0].equalsIgnoreCase(Field6.getConfig().getString("Config.Sub_Comandos.Deletar"))) {
                    if (!((org.bukkit.entity.Player)(Object)a).hasPermission("dterrenos.deletar") && !((org.bukkit.entity.Player)(Object)a).hasPermission("dterrenos.player")) {
                        ((org.bukkit.entity.Player)(Object)a).sendMessage(Field6.getConfig().getString("Mensagem.Sem_Permissao").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7").replace((CharSequence)(Object)"{1}", (CharSequence)(Object)a1[0]));
                        return true;
                    }
                    if (a1.length != 1) {
                        package2.package1.package0.Class1.Method30((org.bukkit.entity.Player)(Object)a, a1[1]);
                        return true;
                    }
                    ((org.bukkit.entity.Player)(Object)a).sendMessage(Field6.getConfig().getString("Mensagem.Erro.Erro_Deletar").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7"));
                    return true;
                }
                if (a1[0].equalsIgnoreCase(Field6.getConfig().getString("Config.Sub_Comandos.Renomear"))) {
                    if (!((org.bukkit.entity.Player)(Object)a).hasPermission("dterrenos.renomear") && !((org.bukkit.entity.Player)(Object)a).hasPermission("dterrenos.player")) {
                        ((org.bukkit.entity.Player)(Object)a).sendMessage(Field6.getConfig().getString("Mensagem.Sem_Permissao").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7").replace((CharSequence)(Object)"{1}", (CharSequence)(Object)a1[0]));
                        return true;
                    }
                    if (a1.length >= 3) {
                        package2.package1.package0.Class1.Method18((org.bukkit.entity.Player)(Object)a, a1[1], a1[2]);
                        return true;
                    }
                    ((org.bukkit.entity.Player)(Object)a).sendMessage(Field6.getConfig().getString("Mensagem.Erro.Erro_Renomear").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7"));
                    return true;
                }
                if (a1[0].equalsIgnoreCase(Field6.getConfig().getString("Config.Sub_Comandos.AddAmigo"))) {
                    if (!((org.bukkit.entity.Player)(Object)a).hasPermission("dterrenos.addamigo") && !((org.bukkit.entity.Player)(Object)a).hasPermission("dterrenos.player")) {
                        ((org.bukkit.entity.Player)(Object)a).sendMessage(Field6.getConfig().getString("Mensagem.Sem_Permissao").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7").replace((CharSequence)(Object)"{1}", (CharSequence)(Object)a1[0]));
                        return true;
                    }
                    if (a1.length < 3) {
                        ((org.bukkit.entity.Player)(Object)a).sendMessage(Field6.getConfig().getString("Mensagem.Erro.Erro_AddAmigo").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7"));
                        return true;
                    }
                    if (!a1[2].equalsIgnoreCase(((org.bukkit.entity.Player)(Object)a).getName())) {
                        package2.package1.package0.Class1.Method28((org.bukkit.entity.Player)(Object)a, a1[2], a1[1]);
                        return true;
                    }
                    ((org.bukkit.entity.Player)(Object)a).sendMessage(Field6.getConfig().getString("Mensagem.Erro.Nao_Pode_Se_Adicionar").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7"));
                    return true;
                }
                if (a1[0].equalsIgnoreCase(Field6.getConfig().getString("Config.Sub_Comandos.DelAmigo"))) {
                    if (!((org.bukkit.entity.Player)(Object)a).hasPermission("dterrenos.delamigo") && !((org.bukkit.entity.Player)(Object)a).hasPermission("dterrenos.player")) {
                        ((org.bukkit.entity.Player)(Object)a).sendMessage(Field6.getConfig().getString("Mensagem.Sem_Permissao").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7").replace((CharSequence)(Object)"{1}", (CharSequence)(Object)a1[0]));
                        return true;
                    }
                    if (a1.length >= 3) {
                        package2.package1.package0.Class1.Method26((org.bukkit.entity.Player)(Object)a, a1[2], a1[1]);
                        return true;
                    }
                    ((org.bukkit.entity.Player)(Object)a).sendMessage(Field6.getConfig().getString("Mensagem.Erro.Erro_DelAmigo").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7"));
                    return true;
                }
                if (a1[0].equalsIgnoreCase(Field6.getConfig().getString("Config.Sub_Comandos.PvP"))) {
                    if (!((org.bukkit.entity.Player)(Object)a).hasPermission("dterrenos.pvp") && !((org.bukkit.entity.Player)(Object)a).hasPermission("dterrenos.player")) {
                        ((org.bukkit.entity.Player)(Object)a).sendMessage(Field6.getConfig().getString("Mensagem.Sem_Permissao").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7").replace((CharSequence)(Object)"{1}", (CharSequence)(Object)a1[0]));
                        return true;
                    }
                    if (a1.length == 1) {
                        ((org.bukkit.entity.Player)(Object)a).sendMessage(Field6.getConfig().getString("Mensagem.Erro.Erro_PvP").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7"));
                        return true;
                    }
                    boolean b = a1[1].equalsIgnoreCase("ativar");
                    label7: {
                        label6: {
                            if (b) {
                                break label6;
                            }
                            if (a1[1].equalsIgnoreCase("desativar")) {
                                break label6;
                            }
                            ((org.bukkit.entity.Player)(Object)a).sendMessage(Field6.getConfig().getString("Mensagem.Erro.PvP").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7"));
                            break label7;
                        }
                        if (package2.package1.package0.Class0.Field0.has((org.bukkit.OfflinePlayer)(Object)a, (double)Field6.getConfig().getInt("Config.Preco_PvP"))) {
                            if (a1[1].equalsIgnoreCase("ativar")) {
                                package2.package1.package0.Class1.Method14((org.bukkit.entity.Player)(Object)a, "allow");
                            } else
                            {
                                package2.package1.package0.Class1.Method14((org.bukkit.entity.Player)(Object)a, "deny");
                            }
                        } else
                        {
                            ((org.bukkit.entity.Player)(Object)a).sendMessage(Field6.getConfig().getString("Mensagem.Erro.Sem_Money_PvP").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7"));
                        }
                    }
                    return true;
                }
                if (a1[0].equalsIgnoreCase(Field6.getConfig().getString("Config.Sub_Comandos.Mobs"))) {
                    if (!((org.bukkit.entity.Player)(Object)a).hasPermission("dterrenos.mob") && !((org.bukkit.entity.Player)(Object)a).hasPermission("dterrenos.player")) {
                        ((org.bukkit.entity.Player)(Object)a).sendMessage(Field6.getConfig().getString("Mensagem.Sem_Permissao").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7").replace((CharSequence)(Object)"{1}", (CharSequence)(Object)a1[0]));
                        return true;
                    }
                    if (a1.length == 1) {
                        ((org.bukkit.entity.Player)(Object)a).sendMessage(Field6.getConfig().getString("Mensagem.Erro.Erro_Mob").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7"));
                        return true;
                    }
                    boolean b0 = a1[1].equalsIgnoreCase("ativar");
                    label5: {
                        label4: {
                            if (b0) {
                                break label4;
                            }
                            if (a1[1].equalsIgnoreCase("desativar")) {
                                break label4;
                            }
                            ((org.bukkit.entity.Player)(Object)a).sendMessage(Field6.getConfig().getString("Mensagem.Erro.Mob").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7"));
                            break label5;
                        }
                        if (package2.package1.package0.Class0.Field0.has((org.bukkit.OfflinePlayer)(Object)a, (double)Field6.getConfig().getInt("Config.Preco_Mob"))) {
                            if (a1[1].equalsIgnoreCase("ativar")) {
                                package2.package1.package0.Class1.Method17((org.bukkit.entity.Player)(Object)a, "allow");
                            } else
                            {
                                package2.package1.package0.Class1.Method17((org.bukkit.entity.Player)(Object)a, "deny");
                            }
                        } else
                        {
                            ((org.bukkit.entity.Player)(Object)a).sendMessage(Field6.getConfig().getString("Mensagem.Erro.Sem_Money_Mob").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7"));
                        }
                    }
                    return true;
                }
                if (a1[0].equalsIgnoreCase(Field6.getConfig().getString("Config.Sub_Comandos.Msg"))) {
                    if (!((org.bukkit.entity.Player)(Object)a).hasPermission("dterrenos.msg") && !((org.bukkit.entity.Player)(Object)a).hasPermission("dterrenos.player")) {
                        ((org.bukkit.entity.Player)(Object)a).sendMessage(Field6.getConfig().getString("Mensagem.Sem_Permissao").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7").replace((CharSequence)(Object)"{1}", (CharSequence)(Object)a1[0]));
                        return true;
                    }
                    if (a1.length == 1) {
                        ((org.bukkit.entity.Player)(Object)a).sendMessage(Field6.getConfig().getString("Mensagem.Erro.Erro_Msg").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7"));
                        return true;
                    }
                    int i1 = 1;
                    Object a2 = a;
                    String s1 = "";
                    int i2 = 1;
                    while(i1 < a1.length) {
                        StringBuilder a3 = new StringBuilder(String.valueOf((Object)s1)).append(a1[i2]);
                        i2 = i2 + 1;
                        s1 = a3.append(" ").toString();
                        i1 = i2;
                    }
                    package2.package1.package0.Class1.Method11((org.bukkit.entity.Player)a2, s1.substring(0, s1.length() - 1));
                    return true;
                }
                if (a1[0].equalsIgnoreCase(Field6.getConfig().getString("Config.Sub_Comandos.BlockCmd"))) {
                    if (!((org.bukkit.entity.Player)(Object)a).hasPermission("dterrenos.blockcmd") && !((org.bukkit.entity.Player)(Object)a).hasPermission("dterrenos.player")) {
                        ((org.bukkit.entity.Player)(Object)a).sendMessage(Field6.getConfig().getString("Mensagem.Sem_Permissao").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7").replace((CharSequence)(Object)"{1}", (CharSequence)(Object)a1[0]));
                        return true;
                    }
                    if (a1.length != 1) {
                        package2.package1.package0.Class1.Method27((org.bukkit.entity.Player)(Object)a, a1[1]);
                        return true;
                    }
                    ((org.bukkit.entity.Player)(Object)a).sendMessage(Field6.getConfig().getString("Mensagem.Erro.Erro_Block_Cmd").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7"));
                    return true;
                }
                if (a1[0].equalsIgnoreCase(Field6.getConfig().getString("Config.Sub_Comandos.UnBlockCmd"))) {
                    if (!((org.bukkit.entity.Player)(Object)a).hasPermission("dterrenos.unblockcmd") && !((org.bukkit.entity.Player)(Object)a).hasPermission("dterrenos.player")) {
                        ((org.bukkit.entity.Player)(Object)a).sendMessage(Field6.getConfig().getString("Mensagem.Sem_Permissao").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7").replace((CharSequence)(Object)"{1}", (CharSequence)(Object)a1[0]));
                        return true;
                    }
                    if (a1.length != 1) {
                        package2.package1.package0.Class1.Method24((org.bukkit.entity.Player)(Object)a, a1[1]);
                        return true;
                    }
                    ((org.bukkit.entity.Player)(Object)a).sendMessage(Field6.getConfig().getString("Mensagem.Erro.Erro_UnBlock_Cmd").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7"));
                    return true;
                }
                if (a1[0].equalsIgnoreCase(Field6.getConfig().getString("Config.Sub_Comandos.TpArea"))) {
                    if (!((org.bukkit.entity.Player)(Object)a).hasPermission("dterrenos.tparea") && !((org.bukkit.entity.Player)(Object)a).hasPermission("dterrenos.player")) {
                        ((org.bukkit.entity.Player)(Object)a).sendMessage(Field6.getConfig().getString("Mensagem.Sem_Permissao").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7").replace((CharSequence)(Object)"{1}", (CharSequence)(Object)a1[0]));
                        return true;
                    }
                    if (a1.length != 1) {
                        package2.package1.package0.Class1.Method36((org.bukkit.entity.Player)(Object)a, a1[1]);
                        return true;
                    }
                    ((org.bukkit.entity.Player)(Object)a).sendMessage(Field6.getConfig().getString("Mensagem.Erro.Erro_TpArea").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7"));
                    return true;
                }
                if (a1[0].equalsIgnoreCase(Field6.getConfig().getString("Config.Sub_Comandos.Lista"))) {
                    if (!((org.bukkit.entity.Player)(Object)a).hasPermission("dterrenos.lista") && !((org.bukkit.entity.Player)(Object)a).hasPermission("dterrenos.player")) {
                        ((org.bukkit.entity.Player)(Object)a).sendMessage(Field6.getConfig().getString("Mensagem.Sem_Permissao").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7").replace((CharSequence)(Object)"{1}", (CharSequence)(Object)a1[0]));
                        return true;
                    }
                    if (package2.package1.package0.Class1.Method33((org.bukkit.entity.Player)(Object)a) != 0) {
                        ((org.bukkit.entity.Player)(Object)a).sendMessage(Field6.getConfig().getString("Mensagem.Sucesso.Lista").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7").replace((CharSequence)(Object)"@areas", (CharSequence)(Object)package2.package1.package0.Class1.Method13((org.bukkit.entity.Player)(Object)a)).replace((CharSequence)(Object)"@nareas", (CharSequence)(Object)String.valueOf(package2.package1.package0.Class1.Method33((org.bukkit.entity.Player)(Object)a))));
                    } else
                    {
                        ((org.bukkit.entity.Player)(Object)a).sendMessage(Field6.getConfig().getString("Mensagem.Erro.Sem_Terrenos").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7"));
                    }
                    return true;
                }
                if (a1[0].equalsIgnoreCase(Field6.getConfig().getString("Config.Sub_Comandos.Info"))) {
                    if (!((org.bukkit.entity.Player)(Object)a).hasPermission("dterrenos.lista") && !((org.bukkit.entity.Player)(Object)a).hasPermission("dterrenos.player")) {
                        ((org.bukkit.entity.Player)(Object)a).sendMessage(Field6.getConfig().getString("Mensagem.Sem_Permissao").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7").replace((CharSequence)(Object)"{1}", (CharSequence)(Object)a1[0]));
                        return true;
                    }
                    package2.package1.package0.Class1.Method32((org.bukkit.entity.Player)(Object)a);
                    return true;
                }
                if (a1[0].equalsIgnoreCase(Field6.getConfig().getString("Config.Sub_Comandos.Vender"))) {
                    if (!((org.bukkit.entity.Player)(Object)a).hasPermission("dterrenos.vender") && !((org.bukkit.entity.Player)(Object)a).hasPermission("dterrenos.player")) {
                        ((org.bukkit.entity.Player)(Object)a).sendMessage(Field6.getConfig().getString("Mensagem.Sem_Permissao").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7").replace((CharSequence)(Object)"{1}", (CharSequence)(Object)a1[0]));
                        return true;
                    }
                    if (a1.length == 1) {
                        ((org.bukkit.entity.Player)(Object)a).sendMessage(Field6.getConfig().getString("Mensagem.Erro.Erro_Vender").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7"));
                        return true;
                    }
                    if (this.Method37(a1[1])) {
                        package2.package1.package0.Class1.Method20((org.bukkit.entity.Player)(Object)a, Integer.parseInt(a1[1]));
                        return true;
                    }
                    ((org.bukkit.entity.Player)(Object)a).sendMessage(Field6.getConfig().getString("Mensagem.Erro.Somente_Numeros").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7"));
                    return true;
                }
                if (a1[0].equalsIgnoreCase(Field6.getConfig().getString("Config.Sub_Comandos.Transferir"))) {
                    if (!((org.bukkit.entity.Player)(Object)a).hasPermission("dterrenos.transferir") && !((org.bukkit.entity.Player)(Object)a).hasPermission("dterrenos.player")) {
                        ((org.bukkit.entity.Player)(Object)a).sendMessage(Field6.getConfig().getString("Mensagem.Sem_Permissao").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7").replace((CharSequence)(Object)"{1}", (CharSequence)(Object)a1[0]));
                        return true;
                    }
                    if (a1.length == 1) {
                        ((org.bukkit.entity.Player)(Object)a).sendMessage(Field6.getConfig().getString("Mensagem.Erro.Erro_Transferir").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7"));
                        return true;
                    }
                    org.bukkit.entity.Player a4 = Field6.getServer().getPlayer(a1[1]);
                    if (a4 == null) {
                        ((org.bukkit.entity.Player)(Object)a).sendMessage(Field6.getConfig().getString("Mensagem.Erro.Player_Offline").replace((CharSequence)(Object)"@player", (CharSequence)(Object)a1[1]).replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7"));
                        return true;
                    }
                    if (!a4.getName().equalsIgnoreCase(((org.bukkit.entity.Player)(Object)a).getName())) {
                        package2.package1.package0.Class1.Method22((org.bukkit.entity.Player)(Object)a, a4);
                        return true;
                    }
                    ((org.bukkit.entity.Player)(Object)a).sendMessage(Field6.getConfig().getString("Mensagem.Erro.Transferir_Si_Mesmo").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7"));
                    return true;
                }
                if (a1[0].equalsIgnoreCase(Field6.getConfig().getString("Config.Sub_Comandos.Limpar"))) {
                    if (!((org.bukkit.entity.Player)(Object)a).hasPermission("dterrenos.limpar") && !((org.bukkit.entity.Player)(Object)a).hasPermission("dterrenos.player")) {
                        ((org.bukkit.entity.Player)(Object)a).sendMessage(Field6.getConfig().getString("Mensagem.Sem_Permissao").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7").replace((CharSequence)(Object)"{1}", (CharSequence)(Object)a1[0]));
                        return true;
                    }
                    package2.package1.package0.Class1.Method19((org.bukkit.entity.Player)(Object)a);
                } else if (a1[0].equalsIgnoreCase(Field6.getConfig().getString("Config.Sub_Comandos.Admin.Principal"))) {
                    if (a1.length == 1) {
                        java.util.Iterator a5 = Field6.getConfig().getStringList("Mensagem.Comandos_Admin").iterator();
                        Object a6 = a5;
                        Object a7 = a;
                        a6 = a5;
                        while(((java.util.Iterator)a6).hasNext()) {
                            ((org.bukkit.entity.Player)a7).sendMessage(((String)((java.util.Iterator)a6).next()).replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7"));
                        }
                        return true;
                    }
                    if (a1[1].equalsIgnoreCase(Field6.getConfig().getString("Config.Sub_Comandos.Admin.TpArea"))) {
                        if (a1.length <= 3) {
                            ((org.bukkit.entity.Player)(Object)a).sendMessage(Field6.getConfig().getString("Mensagem.Erro.Erro_TpArea_Admin").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7"));
                            return true;
                        }
                        boolean b1 = ((org.bukkit.entity.Player)(Object)a).hasPermission("dterrenos.admin.tparea");
                        label2: {
                            label3: {
                                if (b1) {
                                    break label3;
                                }
                                if (!((org.bukkit.entity.Player)(Object)a).hasPermission("dterrenos.admin")) {
                                    break label2;
                                }
                            }
                            package2.package1.package0.Class1.Method34((org.bukkit.entity.Player)(Object)a, a1[2], a1[3]);
                            break label1;
                        }
                        ((org.bukkit.entity.Player)(Object)a).sendMessage(Field6.getConfig().getString("Mensagem.Sem_Permissao").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7").replace((CharSequence)(Object)"{1}", (CharSequence)(Object)a1[0]));
                    } else if (a1[1].equalsIgnoreCase(Field6.getConfig().getString("Config.Sub_Comandos.Admin.Lista"))) {
                        if (a1.length == 2) {
                            ((org.bukkit.entity.Player)(Object)a).sendMessage(Field6.getConfig().getString("Mensagem.Erro.Erro_Lista_Admin").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7"));
                            return true;
                        }
                        boolean b2 = ((org.bukkit.entity.Player)(Object)a).hasPermission("dterrenos.admin.lista");
                        label0: {
                            if (b2) {
                                break label0;
                            }
                            if (((org.bukkit.entity.Player)(Object)a).hasPermission("dterrenos.admin")) {
                                break label0;
                            }
                            ((org.bukkit.entity.Player)(Object)a).sendMessage(Field6.getConfig().getString("Mensagem.Sem_Permissao").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7").replace((CharSequence)(Object)"{1}", (CharSequence)(Object)a1[0]));
                            break label1;
                        }
                        if (package2.package1.package0.Class1.Method15((org.bukkit.entity.Player)(Object)a, a1[2]) != 0) {
                            ((org.bukkit.entity.Player)(Object)a).sendMessage(Field6.getConfig().getString("Mensagem.Sucesso.Lista_Staff").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7").replace((CharSequence)(Object)"@player", (CharSequence)(Object)((Field6.getServer().getPlayer(a1[2]) != null) ? Field6.getServer().getPlayer(a1[2]).getName() : a1[2])).replace((CharSequence)(Object)"@areas", (CharSequence)(Object)package2.package1.package0.Class1.Method31((org.bukkit.entity.Player)(Object)a, a1[2])).replace((CharSequence)(Object)"@nareas", (CharSequence)(Object)String.valueOf(package2.package1.package0.Class1.Method15((org.bukkit.entity.Player)(Object)a, a1[2]))));
                        } else
                        {
                            ((org.bukkit.entity.Player)(Object)a).sendMessage(Field6.getConfig().getString("Mensagem.Erro.Sem_Terrenos_Staff").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7"));
                        }
                    }
                } else
                {
                    this.Method38((org.bukkit.entity.Player)(Object)a, Field6.getConfig().getStringList("Mensagem.Comandos"));
                }
            }
        } else if (a0.getName().equalsIgnoreCase("terrenos")) {
            if (!(a instanceof org.bukkit.entity.Player)) {
                a.sendMessage("Unknown command. Type \"help\" for help.");
                return true;
            }
            if (!Field6.getConfig().getBoolean("Config.Ativar_Terrenos")) {
                ((org.bukkit.entity.Player)(Object)a).sendMessage(Field6.getConfig().getString("Mensagem.Erro.Terrenos_Desativado").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7"));
                return true;
            }
            if (!((org.bukkit.entity.Player)(Object)a).hasPermission("dterrenos.terrenos")) {
                ((org.bukkit.entity.Player)(Object)a).sendMessage(Field6.getConfig().getString("Mensagem.Sem_Permissao_Terrenos").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7"));
                return true;
            }
            int i3 = Field8.nextInt(20001) + -10000;
            int i4 = Field8.nextInt(20001) + -10000;
            org.bukkit.Location a8 = new org.bukkit.Location(Field6.getServer().getWorld(Field6.getConfig().getString("Config.Mundo_Terrenos")), (double)i3, (double)((org.bukkit.entity.Player)(Object)a).getWorld().getHighestBlockYAt(i3, i4), (double)i4, ((org.bukkit.entity.Player)(Object)a).getLocation().getYaw(), ((org.bukkit.entity.Player)(Object)a).getLocation().getPitch());
            if (!a8.getChunk().isLoaded()) {
                a8.getChunk().load();
            }
            ((org.bukkit.entity.Player)(Object)a).teleport(a8);
            ((org.bukkit.entity.Player)(Object)a).sendMessage(Field6.getConfig().getString("Mensagem.Sucesso.Teleportado_Mundo").replace((CharSequence)(Object)"&", (CharSequence)(Object)"\u00a7"));
        }
        return false;
    }
    
    static {
        Field6 = package2.package1.package0.Class0.Method8();
        Field7 = Field6.Method6();
        Field8 = new java.util.Random();
    }
}
