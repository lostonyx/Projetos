package me.wiljafor1.saoloja.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import me.wiljafor1.saoloja.Main;

public class SQLite {
	
	public SQLite(){
		try {
			Connection c;
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:plugins/RecompensaRPG/storage.db");
			Statement stmt = c.createStatement();
			stmt.execute("CREATE TABLE IF NOT EXISTS Delays (Player TEXT, Time TEXT)");
			Main.plugin.debug("SQLite conectado.");
			c.close();
			stmt.close();
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	public Connection getNewConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:sqlite:plugins/RecompensaRPG/storage.db");
	}

}
