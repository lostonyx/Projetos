package br.com.saomc.sg.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.saomc.sg.SgConfig;
import br.com.saomc.sg.jdbc.Conexao;
import br.com.saomc.sg.jdbc.dao.model.Backup;

public class BackupDAO implements BaseDAO {

	private final Connection connection;

	public BackupDAO(SgConfig hgConfig) throws SQLException {
		this(hgConfig.getIsMySQL(), hgConfig.getServer(), hgConfig.getDatabase(), hgConfig.getUser(), hgConfig.getPassword());
	}

	public BackupDAO(Boolean isMySQL, String server, String database, String user, String password) throws SQLException {
		this.connection = Conexao.getConnection(isMySQL, server, database, user, password);
	}


	@Override
	public void closeConnection() throws SQLException {
		this.connection.close();
	}


	@Override
	public void createTableMySql() throws SQLException {

		StringBuffer sql = new StringBuffer();

		sql.append(" CREATE TABLE IF NOT EXISTS sg_backups ( ");
		sql.append("	id INT AUTO_INCREMENT, ");
		sql.append("	world VARCHAR(50) UNIQUE, ");
		sql.append("	date TIMESTAMP, ");
		sql.append("	PRIMARY KEY (id) ");
		sql.append(" ) ");

		PreparedStatement stmt = connection.prepareStatement(sql.toString());

		try {
			stmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			stmt.close();
		}

	}


	@Override
	public void createTableSqlite() throws SQLException {
		StringBuffer sql = new StringBuffer();

		sql.append(" CREATE TABLE IF NOT EXISTS sg_backups ( ");
		sql.append("	id INTEGER PRIMARY KEY AUTOINCREMENT,");
		sql.append("	world text UNIQUE, ");
		sql.append("	date text ");
		sql.append(" ) ");

		PreparedStatement stmt = connection.prepareStatement(sql.toString());

		try {
			stmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			stmt.close();
		}
	}


	public void dropTable() throws SQLException {

		StringBuffer sql = new StringBuffer();

		sql.append(" DROP TABLE IF EXISTS sg_backups ");

		PreparedStatement stmt = connection.prepareStatement(sql.toString());

		try {
			stmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			stmt.close();
		}

	}


	@Override
	public void insert(Object object) throws SQLException {

		Backup backup = (Backup) object;
		StringBuffer sql = new StringBuffer();

		sql.append("insert into sg_backups (world, date) values (?,?)");

		PreparedStatement stmt = connection.prepareStatement(sql.toString());

		stmt.setString(1, backup.getWorld());
		stmt.setString(2, backup.getDateString());

		try {
			stmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			stmt.close();
		}
	}


	@Override
	public List<Backup> listAll() throws SQLException {

		PreparedStatement stmt = connection.prepareStatement("select * from sg_backups");

		ResultSet rs = stmt.executeQuery();
		List<Backup> backups = new ArrayList<Backup>();

		while (rs.next()) {
			Backup backupDatabase = new Backup();
			backupDatabase.setId(rs.getInt("id"));
			backupDatabase.setWorld(rs.getString("world"));
			backupDatabase.setDate(rs.getString("date"));
			backups.add(backupDatabase);
		}

		rs.close();
		stmt.close();
		return backups;
	}


	@Override
	public void delete(Integer id) throws SQLException {

		String sql = "delete from sg_backups where id = " + id;
		PreparedStatement stmt = connection.prepareStatement(sql);

		try {
			stmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			stmt.close();
		}
	}


	@Override
	public Backup findById(Integer id) throws SQLException {

		String sql = "select * from sg_backups where id=" + id;
		PreparedStatement stmt = connection.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery();
		Backup backup = new Backup();

		while (rs.next()) {
			backup.setId(rs.getInt("id"));
			backup.setWorld(rs.getString("world"));
			backup.setDate(rs.getString("date"));

		}

		rs.close();
		stmt.close();
		return backup;
	}


	public Backup findBy(Backup backup) throws SQLException {

		StringBuffer sql = new StringBuffer();

		sql.append("select id from sg_backups where world=? and date=? ");

		PreparedStatement stmt = connection.prepareStatement(sql.toString());
		stmt.setString(1, backup.getWorld());
		stmt.setString(2, backup.getDateString());

		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			backup.setId(rs.getInt("id"));
		}

		rs.close();
		stmt.close();
		return backup;
	}


	@Override
	public void update(Object object) throws SQLException {

		Backup backup = (Backup) object;
		String sql = "update sg_backups set world='?', date='?' where id=?";
		PreparedStatement stmt = connection.prepareStatement(sql.toString());

		stmt.setString(1, backup.getWorld());
		stmt.setString(2, backup.getDateString());

		try {
			stmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			stmt.close();
		}
	}


	public Backup findByWorld(String world) throws SQLException {

		String sql = "select * from sg_backups where world='" + world + "'";
		PreparedStatement stmt = connection.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery();
		Backup backup = new Backup();

		while (rs.next()) {
			backup.setId(rs.getInt("id"));
			backup.setWorld(rs.getString("world"));
			backup.setDate(rs.getString("date"));

		}

		rs.close();
		stmt.close();
		return backup;
	}

    @Override
    public void insert2(Object object1, Object object2) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
