package com.criptonnetwork.wiljafor1.wreferencia.Util;

import com.criptonnetwork.wiljafor1.wreferencia.Main;
import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class SQL {

    private static Connection connection;
    private static File db;
	private Main plugin;

    public SQL(Main main) {
        plugin = main;
    }

    public static void setDb(File db) {
        SQL.db = db;
    }

    public static void closeConnection() {
        try {
            if (connection != null && connection.isClosed()) return;

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static int getTotal(UUID uuid) {//Total de Refencia
    int resultado;
    try {
        openConnection();
	String sql = "SELECT * FROM WReferencia WHERE uuid = ?";
	PreparedStatement statement = connection.prepareStatement(sql);
	statement.setObject(1, uuid.toString());
	ResultSet rs = statement.executeQuery();
	if (rs.next()) {
            resultado = rs.getInt("total");
            statement.close();
            rs.close();
            return resultado;
	} else {
            statement.close();
            rs.close();
            return 0;
	}
    } catch (SQLException e) {
	System.out.println(e.toString());
    }
    return 0;
    }
        
	public static int getTotalNick(String nickname) {//Total de Refencia
            int resultado;
		try {
            openConnection();
			String sql = "SELECT * FROM WReferencia WHERE nickname = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setObject(1, nickname);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				resultado = rs.getInt("total");
				statement.close();
				rs.close();
				return resultado;
			} else {
				statement.close();
				rs.close();
				return 0;
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		return 0;
	}
        
	public static ArrayList<ItemStack> getTop() {
            ArrayList<ItemStack> itens = new ArrayList<ItemStack>();
		try {
            openConnection();
			String sql = "SELECT * FROM WReferencia ORDER BY total DESC LIMIT 5";
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
                        int colocacao = 0;
			while (rs.next()) {
                                colocacao++;
                                CItem item = new CItem(Material.SKULL_ITEM).setDurability(3).setSkullOwner(rs.getString("nickname")).setName("§e"+rs.getString("nickname")+" #"+colocacao).setLore(new String[] {"§7Referencias: §f"+getTotalNick(rs.getString("nickname"))});
                                itens.add(item.build());
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		return itens;
	}
        
	public static String getKey(UUID uuid) {//pegar key
            String resultado;
		try {
            openConnection();
			String sql = "SELECT * FROM WReferencia WHERE uuid = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setObject(1, uuid.toString());
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				resultado = rs.getString("key");
				statement.close();
				rs.close();
				return resultado;
			} else {
				statement.close();
				rs.close();
				return null;
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		return null;
	}
        
	public static String getUUID(String key) {//pegar uuid
            String resultado;
		try {
            openConnection();
			String sql = "SELECT * FROM WReferencia WHERE key = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setObject(1, key);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				resultado = rs.getString("uuid");
				statement.close();
				rs.close();
				return resultado;
			} else {
				statement.close();
				rs.close();
				return null;
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		return null;
	}
        
	public static String getNick(String key) {//pegar uuid
            String resultado;
		try {
            openConnection();
			String sql = "SELECT * FROM WReferencia WHERE key = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setObject(1, key);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				resultado = rs.getString("nickname");
				statement.close();
				rs.close();
				return resultado;
			} else {
				statement.close();
				rs.close();
				return null;
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		return null;
	}
        
	public static String getNickUUID(String uuid) {//pegar uuid
            String resultado;
		try {
            openConnection();
			String sql = "SELECT * FROM WReferencia WHERE uuid = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setObject(1, uuid);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				resultado = rs.getString("nickname");
				statement.close();
				rs.close();
				return resultado;
			} else {
				statement.close();
				rs.close();
				return null;
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		return null;
	}
        
        public static Boolean getStatus(UUID uuid) {//pegar uuid
            Boolean resultado;
		try {
            openConnection();
			String sql = "SELECT * FROM WReferencia WHERE uuid = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setObject(1, uuid.toString());
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
                            if(rs.getInt("indicou") == 1){
                                resultado = true;
				statement.close();
				rs.close();
				return resultado;
                            }
                            else{
                                resultado = false;
				statement.close();
				rs.close();
				return resultado;    
                            }
			} else {
				statement.close();
				rs.close();
				return false;
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		return false;
	}
    
    public static void CriarDados(UUID uuid, String nickname, String key) {
		try {
                        openConnection();
			String sql = "SELECT * FROM `WReferencia` WHERE uuid = '"+ uuid +"'";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				ps.close();
				rs.close();
			} else {
				String sql2 = "INSERT INTO `WReferencia` (uuid,nickname,key,total,indicou) VALUES ('"+ uuid.toString() +"','"+ nickname +"','"+ key +"',0,0);";
				ps = connection.prepareStatement(sql2);
				ps.executeUpdate();
				ps.close();
				rs.close();
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
	}
    
    public static void atualizarDadosTotal(UUID uuid) {
	try {
            openConnection();
	    String sql = "UPDATE WReferencia" + " SET total = total + 1 " + "WHERE uuid = ?;";
	    PreparedStatement ps = connection.prepareStatement(sql);
	    ps.setObject(1, uuid.toString());
            ps.executeUpdate();
            ps.close();
	} catch (SQLException e) {
            System.out.println(e.toString());
	}
    }
    
    public static void DeletarDados(UUID uuid) {
	try {
            openConnection();
	    String sql = "DELETE FROM WReferencia WHERE uuid = ?;";
	    PreparedStatement ps = connection.prepareStatement(sql);
	    ps.setObject(1, uuid.toString());
            ps.executeUpdate();
            ps.close();
	} catch (SQLException e) {
            System.out.println(e.toString());
	}
    }
    
    public static void atualizarDadosIndicou(UUID uuid) {
	try {
            openConnection();
	    String sql = "UPDATE WReferencia" + " SET indicou = 1 " + "WHERE uuid = ?;";
	    PreparedStatement ps = connection.prepareStatement(sql);
	    ps.setObject(1, uuid.toString());
            ps.executeUpdate();
            ps.close();
	} catch (SQLException e) {
            System.out.println(e.toString());
	}
    }
    
    public static boolean VerificarDados(UUID uuid) {
		try {
                        openConnection();
			String sql = "SELECT * FROM `WReferencia` WHERE uuid = '"+ uuid +"'";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				ps.close();
				rs.close();
                                return true;
			} else {
                          return false;
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
    return false;
    }

    public static void createTable() {
        openConnection();//CREATE TABLE IF NOT EXISTS
        try {
            PreparedStatement preparedStatement = connection.prepareStatement
                ("CREATE TABLE IF NOT EXISTS `WReferencia` (" +
"	`id`	INTEGER PRIMARY KEY AUTOINCREMENT," +
"	`uuid`	TEXT NOT NULL," +
"	`nickname`	TEXT NOT NULL," +
"	`key`	TEXT NOT NULL," +
"	`total`	INTEGER NOT NULL DEFAULT 0," +
"	`indicou`	INTEGER DEFAULT 0" +
");");
            
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException ex) {
                ex.printStackTrace();
            createTable();
        }
    }
    
    private static void openConnection() {
        try {
            if (connection != null && !connection.isClosed()) return;

            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + db.getAbsolutePath());
        } catch (ClassNotFoundException | SQLException ex8) {
            ex8.printStackTrace();
        }
    }



}
