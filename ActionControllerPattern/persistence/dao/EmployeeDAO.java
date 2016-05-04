package persistence.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import model.EmployeeInfo;
import model.User;
import persistence.MysqlDataSource;



public class EmployeeDAO{

	private MysqlDataSource dataSource = new MysqlDataSource();

	public User loginUser(String username, String password) throws Exception {
		Connection connection = this.dataSource.getConnection();
		PreparedStatement statement = null;
		String query  = "SELECT * FROM users where username=? and password=?";
		User user = null;
		try {
			statement = connection.prepareStatement(query);
			statement.setString(1,username);
			statement.setString(2,password);
			statement.executeQuery();
			ResultSet results = statement.getResultSet();
			while(results.next()) {
				user = new User();
				user.setIdUser(results.getInt("userId"));
				user.setUsername(results.getString("username"));
				user.setRole(results.getString("role"));
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

	public void setInfo(EmployeeInfo info, Integer userId) throws Exception{
		Connection connection = dataSource.getConnection();
		PreparedStatement statement = null; 
		String query = "INSERT INTO infos(userId, name, surname, dateOfBirth, placeOfBirth, title, lengthOfService, vacationDays, leftVacationDays, sickDays, leftSickDays, address, contactNumber, salary) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			statement = connection.prepareStatement(query);
			statement.setInt(1, userId);
			statement.setString(2, info.getName());
			statement.setString(3, info.getSurname());
			statement.setString(4, info.getDob());
			statement.setString(5, info.getPlaceOfBirth());
			statement.setString(6, info.getTitle());
			statement.setString(7, info.getLengthOfService());
			statement.setString(8, info.getVacationDays());
			statement.setString(9, info.getLeftVacationDays());
			statement.setString(10, info.getSickDays());
			statement.setString(11, info.getLeftSickDays());
			statement.setString(12, info.getAddress());
			statement.setString(13, info.getContactNumber());
			statement.setString(14, info.getSalary());
			statement.executeUpdate();
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
	}

	public EmployeeInfo getInfo(Integer userId) throws Exception{
		Connection connection = this.dataSource.getConnection();
		PreparedStatement statement = null;
		EmployeeInfo info = new EmployeeInfo();
		String query  = "SELECT * FROM infos where userId=?";
		try {
			statement = connection.prepareStatement(query);
			statement.setInt(1,userId);
			statement.executeQuery();
			ResultSet results = statement.getResultSet();
			while(results.next()) {
				info.setName(results.getString("name"));
				info.setSurname(results.getString("surname"));
				info.setDob(results.getString("dateOfBirth"));
				info.setPlaceOfBirth(results.getString("placeOfBirth"));
				info.setTitle(results.getString("title"));
				info.setLengthOfService(results.getString("lengthOfService"));
				info.setVacationDays(results.getString("vacationDays"));
				info.setLeftVacationDays(results.getString("leftVacationDays"));
				info.setSickDays(results.getString("sickDays"));
				info.setLeftSickDays(results.getString("leftSickDays"));
				info.setAddress(results.getString("address"));
				info.setContactNumber(results.getString("contactNumber"));
				info.setSalary(results.getString("salary"));
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
		return info;
	}
}