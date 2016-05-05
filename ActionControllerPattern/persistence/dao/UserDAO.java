package persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.commons.codec.digest.DigestUtils;

import model.User;
import persistence.MysqlDataSource;
import utils.SaltGenerator;
import com.mysql.jdbc.Statement;

public class UserDAO {

	private MysqlDataSource dataSource = new MysqlDataSource();
	
	public Integer addUser(User user, String password) throws Exception{

		Connection connection = dataSource.getConnection();
		String salt = SaltGenerator.getNextSalt();
		String pwd = DigestUtils.sha512Hex(password.concat(salt)); 
		String query  = "INSERT INTO users(username, password, salt, email, role) VALUES (?, ?, ?, ?, ?)";
		PreparedStatement statement = null; 
		
		try {
			statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, user.getUsername());
			statement.setString(2, pwd);
			statement.setString(3, salt);
			statement.setString(4, user.getEmail());
			statement.setInt(5, user.getRole()); 
			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			    rs.next();
			return rs.getInt(1);

		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		} finally {
			try {
				if (statement != null) 
					statement.close();
				if (connection!= null)
					connection.close();
			} catch (SQLException e) {
				throw new SQLException(e.getMessage());
			}
		}
	}
	
	public Integer getUserIdFromEmail(String email) throws Exception{
		Connection connection = this.dataSource.getConnection();
		PreparedStatement statement = null;
		String query  = "SELECT IDUSER FROM USERS WHERE EMAIL = ?";
		try {
			statement = connection.prepareStatement(query);
			statement.setString(1,email);;
			statement.executeQuery();
			ResultSet results = statement.getResultSet();
			if(results.next()) {
				return results.getInt("idUser");
			}
		} catch (SQLException e) {
			throw new Exception(e.getMessage());
		} finally {
			try {
				if (statement != null) 
					statement.close();
				if (connection!= null)
					connection.close();
			} catch (SQLException e) {
				throw new Exception(e.getMessage());
			}
		}
		return null;
	}
	
	public User loginUser(String username, String password) throws Exception {
		Connection connection = this.dataSource.getConnection();
		PreparedStatement statement = null;
		String query  = "SELECT * FROM USERS WHERE USERNAME = ? AND PASSWORD = SHA2(CONCAT(?,(SELECT SALT FROM USERS WHERE USERNAME = ?)),512)";
		User user = null;
		try {
			statement = connection.prepareStatement(query);
			statement.setString(1,username);
			statement.setString(2,password);
			statement.setString(3,username);
			statement.executeQuery();
			ResultSet results = statement.getResultSet();
			while(results.next()) {
				user = new User();
				user.setIdUser(results.getInt("idUser"));
				user.setUsername(results.getString("username"));
				user.setRole(results.getInt("role"));
				user.setEmail(results.getString("email"));
			}
		} catch (SQLException e) {
			throw new Exception(e.getMessage());
		} finally {
			try {
				if (statement != null) 
					statement.close();
				if (connection!= null)
					connection.close();
			} catch (SQLException e) {
				throw new Exception(e.getMessage());
			}
		}
		return user;
	}
}
