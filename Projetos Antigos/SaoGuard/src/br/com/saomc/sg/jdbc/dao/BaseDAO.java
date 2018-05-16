package br.com.saomc.sg.jdbc.dao;

import java.sql.SQLException;
import java.util.List;

public interface BaseDAO {

	public void closeConnection() throws SQLException;


	public void createTableMySql() throws SQLException;


	public void createTableSqlite() throws SQLException;


	public void insert(Object object) throws SQLException;
        
        public void insert2(Object object1, Object object2) throws SQLException;

	public List<?> listAll() throws SQLException;


	public Object findById(Integer id) throws SQLException;


	public void update(Object object) throws SQLException;


	public void delete(Integer id) throws SQLException;

}
