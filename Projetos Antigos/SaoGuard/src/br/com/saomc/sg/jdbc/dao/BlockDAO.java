package br.com.saomc.sg.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.saomc.sg.SgConfig;
import br.com.saomc.sg.jdbc.Conexao;
import br.com.saomc.sg.jdbc.dao.model.Block;
import br.com.saomc.sg.jdbc.dao.model.Player;
import br.com.saomc.sg.jdbc.dao.model.Region;

public class BlockDAO implements BaseDAO {

	private final Connection connection;

	public BlockDAO(SgConfig hgConfig) throws SQLException {
		this(hgConfig.getIsMySQL(), hgConfig.getServer(), hgConfig.getDatabase(), hgConfig.getUser(), hgConfig.getPassword());
	}
	
	public BlockDAO(Boolean isMySQL, String server, String database, String user, String password) throws SQLException {
		this.connection = Conexao.getConnection(isMySQL, server, database, user, password);
	}


	@Override
	public void closeConnection() throws SQLException {
		this.connection.close();
	}


	@Override
	public void createTableMySql() throws SQLException {

		StringBuffer sql = new StringBuffer();

		sql.append(" CREATE TABLE IF NOT EXISTS sg_blocks ( ");
		sql.append("	id INTEGER PRIMARY KEY AUTOINCREMENT,");
		sql.append("	blokedplayer_id INTEGER NOT NULL,");
                sql.append("	terreno_id INTEGER NOT NULL,");
                sql.append("	full_name TEXT NOT NULL,");
                sql.append("	FOREIGN KEY (full_name)  REFERENCES sg_regions(full_name), ");
		sql.append("	FOREIGN KEY (blokedplayer_name)  REFERENCES sg_players(name), ");
                sql.append("	FOREIGN KEY (terreno_id)  REFERENCES sg_regions(id) ");
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

		sql.append(" CREATE TABLE IF NOT EXISTS sg_blocks ( ");
		sql.append("	id INTEGER PRIMARY KEY AUTOINCREMENT,");
		sql.append("	blokedplayer_name TEXT NOT NULL,");
                sql.append("	terreno_id INTEGER NOT NULL,");
                sql.append("	full_name TEXT NOT NULL,");
                sql.append("	FOREIGN KEY (full_name)  REFERENCES sg_regions(full_name), ");
		sql.append("	FOREIGN KEY (blokedplayer_name)  REFERENCES sg_players(name), ");
                sql.append("	FOREIGN KEY (terreno_id)  REFERENCES sg_regions(id) ");
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

		sql.append(" DROP TABLE IF EXISTS sg_blocks ");

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
	public void insert2(Object ro,Object po) throws SQLException {

		Region region = (Region) ro;
                String player = (String) po;
                //Player player = (Player) po;
		StringBuffer sql = new StringBuffer();

		sql.append("insert into sg_blocks (blokedplayer_name, terreno_id, full_name) values (?,?,?)");

		PreparedStatement stmt = connection.prepareStatement(sql.toString());

		stmt.setString(1, player);
                stmt.setInt(2, region.getId());
                stmt.setString(3, region.getFullName());
		try {
			stmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			stmt.close();
		}
	}


	@Override
	public List<Block> listAll() throws SQLException {

		PreparedStatement stmt = connection.prepareStatement("select * from sg_blocks");

		ResultSet rs = stmt.executeQuery();
		List<Block> blocks = new ArrayList<Block>();

		while (rs.next()) {
			//Block blockDatabase = new Block();
			//blockDatabase.setId(rs.getInt("id"));
			//blockDatabase.setBlocksAndLocations(rs.getString("blokedplayer_name"));
			//blocks.add(blockDatabase);
		}

		rs.close();
		stmt.close();
		return blocks;
	}


	@Override
	public void delete(Integer id) throws SQLException {

		String sql = "delete from sg_blocks where id = " + id;
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
	public Block findById(Integer id) throws SQLException {

		String sql = "select * from sg_blocks where id=" + id;
		PreparedStatement stmt = connection.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery();
		Block block = new Block();

		while (rs.next()) {
			block.setId(rs.getInt("id"));
			block.setBlocksAndLocations(rs.getString("blocks_and_locations"));

		}

		rs.close();
		stmt.close();
		return block;
	}


	public Block findBy(Block block) throws SQLException {

		StringBuffer sql = new StringBuffer();

		sql.append("select id from sg_blocks where blocks_and_locations=? ");

		PreparedStatement stmt = connection.prepareStatement(sql.toString());
		stmt.setString(1, block.getBlocksAndLocations());

		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			block.setId(rs.getInt("id"));
		}

		rs.close();
		stmt.close();
		return block;
	}

	public String findByNome(Object nome) throws SQLException {

		StringBuffer sql = new StringBuffer();
                String player = (String) nome;
                String fullname = null;

		sql.append("select full_name from sg_blocks where blokedplayer_name=? ");

		PreparedStatement stmt = connection.prepareStatement(sql.toString());
		stmt.setString(1, player);

		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			//block.setId(rs.getInt("id"));
                        fullname = rs.getString("full_name");
		}

		rs.close();
		stmt.close();
		return fullname;
	}
        
	public String findFullNome(Object nome) throws SQLException {

		StringBuffer sql = new StringBuffer();
                String player = (String) nome;
                String fullname = null;

		sql.append("select blokedplayer_name from sg_blocks where full_name=? ");

		PreparedStatement stmt = connection.prepareStatement(sql.toString());
		stmt.setString(1, player);

		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			//block.setId(rs.getInt("id"));
                        fullname = rs.getString("blokedplayer_name");
		}

		rs.close();
		stmt.close();
		return fullname;
	}
        
	public Integer findByTerrenoid(Object nome) throws SQLException {

		StringBuffer sql = new StringBuffer();
                String player = (String) nome;
                int idp = 0;

		sql.append("select terreno_id from sg_blocks where blokedplayer_name=? ");

		PreparedStatement stmt = connection.prepareStatement(sql.toString());
		stmt.setString(1, player);

		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			//block.setId(rs.getInt("id"));
                        idp = rs.getInt("terreno_id");
		}

		rs.close();
		stmt.close();
		return idp;
	}
        
	public String findTerrenoid(Object nome) throws SQLException {

		StringBuffer sql = new StringBuffer();
                String player = (String) nome;
                String idp = null;

		sql.append("select blokedplayer_name from sg_blocks where terreno_id=? ");

		PreparedStatement stmt = connection.prepareStatement(sql.toString());
		stmt.setString(1, player);

		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			//block.setId(rs.getInt("id"));
                        idp = rs.getString("blokedplayer_name");
		}

		rs.close();
		stmt.close();
		return idp;
	}

	@Override
	public void update(Object object) throws SQLException {

//		Block block = (Block) object;
//		String sql = "update sg_blocks set blocks_and_locations=? where id=?";
//		PreparedStatement stmt = connection.prepareStatement(sql.toString());
//
//		stmt.setString(1, block.getBlocksAndLocations());
//		stmt.setInt(2, block.getBackup().getId());
//
//		try {
//			stmt.execute();
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			stmt.close();
//		}
	}

    @Override
    public void insert(Object object) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
