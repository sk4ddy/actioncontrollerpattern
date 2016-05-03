package actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import messages.MessageGenerator;
import model.Constants;
import model.EmployeeInfo;
import model.User;
import persistence.PersistenceController;
import utils.CSRFTokenUtils;

public class GetEmployeeInfos implements IAction {

	private PersistenceController persistenceController = PersistenceController.getInstance();

	@Override
	public void doAction(HttpServletRequest request, HttpServletResponse response) {

		User user = (User) request.getSession().getAttribute(Constants.ATTRIBUTE_SECURITY_CONTEXT);
		JSONObject json = (JSONObject) request.getAttribute(Constants.ATTRIBUTE_JSON);


		if(json!=null && user!=null){
			if(CSRFTokenUtils.isValid(request.getSession(), json)){
				EmployeeInfo infos = persistenceController.getEmployeeInfos(user.getIdUser());
				if(infos!=null)
					MessageGenerator.employeeInfo(infos, response);
			}
			else{
				MessageGenerator.wrongCSRFToken(response);
			}
		}
	}
}
