package persistence;

import model.EmployeeInfo;
import model.User;
import persistence.dao.EmployeeDAO;

public class PersistenceController {

	private EmployeeDAO dao = new EmployeeDAO();

	private static PersistenceController persistenceInstance = new PersistenceController();

	public static PersistenceController getInstance(){
		return persistenceInstance;
	}
	public User loginUser(String username, String password) {
		try {
			return dao.loginUser(username, password);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public EmployeeInfo getEmployeeInfos(int idUser) {
		try {
			return dao.getInfo(idUser);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public void storeEmployeeInfos(EmployeeInfo info, int userId) {
		try {
			dao.setInfo(info, userId);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	
	}
}