
package me.wiljafor1.saocmds.Utils;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;

import me.wiljafor1.saocmds.Utils.Database; // import the database class.
import me.wiljafor1.saocmds.Main; // import your main class
import static me.wiljafor1.saocmds.Main.ComandoC;
import static me.wiljafor1.saocmds.Main.ComandoS;
import org.bukkit.Bukkit;


public class SQLite extends Database{
    String dbname;
    public SQLite(Main instance){
        super(instance);
        dbname = "db";
    }

    public String CTabelaComandos = "CREATE TABLE IF NOT EXISTS `comandos` (" +
"	`id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
"	`Comando`	TEXT NOT NULL," +
"	`Sub`	TEXT NOT NULL," +
"	`Tipo`	TEXT NOT NULL" +
");";
    


    // SQL creation stuff, You can leave the blow stuff untouched.
    public Connection getSQLConnection() {
        File dataFolder = new File(plugin.getDataFolder(), dbname+".db");
        if (!dataFolder.exists()){
            try {
                dataFolder.createNewFile();
            } catch (IOException e) {
                plugin.getLogger().log(Level.SEVERE, "File write error: "+dbname+".db");
            }
        }
        try {
            if(connection!=null&&!connection.isClosed()){
                return connection;
            }
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + dataFolder);
            return connection;
        } catch (SQLException ex) {
            plugin.getLogger().log(Level.SEVERE,"SQLite exception on initialize", ex);
        } catch (ClassNotFoundException ex) {
            plugin.getLogger().log(Level.SEVERE, "You need the SQLite JBDC library. Google it. Put it in /lib folder.");
        }
        return null;
    }

    public void load() {
        connection = getSQLConnection();
        try {
            Statement s = connection.createStatement();
            s.executeUpdate(CTabelaComandos);
            s.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //initialize();
        CarregarComandos();
    }
    
    public void CarregarComandos(){
        table = "comandos";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = getSQLConnection();
            ps = conn.prepareStatement("SELECT * FROM " + table + ";");
   
            rs = ps.executeQuery();
            while(rs.next()){
               plugin.Comando.add(rs.getString("Sub"));
               ComandoC.put(rs.getString("Comando"), rs.getString("Comando"));
               ComandoS.put(rs.getString("Sub"), rs.getString("Comando"));
               Bukkit.getConsoleSender().sendMessage("Comando :"+ plugin.Comando.toString());
               Bukkit.getConsoleSender().sendMessage("ComandoC :"+ plugin.ComandoC.toString());
               Bukkit.getConsoleSender().sendMessage("ComandoS :"+ plugin.ComandoS.toString());
            }
            conn.close();
        } catch (SQLException ex) {
            plugin.getLogger().log(Level.SEVERE, Errors.sqlConnectionExecute(), ex);
        }
    } 
}
 