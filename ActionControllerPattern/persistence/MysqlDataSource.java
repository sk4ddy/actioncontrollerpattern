package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlDataSource {
	
	private String dbURI = "jdbc:mysql://localhost/dbEmployeesMgmnt";
	private String userName = "employeesMgmnt";
	private String password = "aemployeesMgmntPassword";

	public Connection getConnection() throws Exception {
		Connection connection;
		try {
		    Class.forName("com.mysql.jdbc.Driver");
		    connection = DriverManager.getConnection(dbURI,userName, password);
		} catch (ClassNotFoundException e) {
			throw new Exception(e.getMessage());
		} catch(SQLException e) {
			throw new Exception(e.getMessage());
		}
		return connection;
	}
}