package actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messages.MessageGenerator;
import model.Constants;
import model.EmployeeInfo;
import model.User;

import org.json.simple.JSONObject;

import persistence.PersistenceController;
import utils.CSRFTokenUtils;

public class SetEmployeeInfos implements IAction {

	private PersistenceController persistenceController = PersistenceController.getInstance();

	@Override
	public void doAction(HttpServletRequest request,HttpServletResponse response) {

		JSONObject json = (JSONObject) request.getAttribute(Constants.ATTRIBUTE_JSON);
		User currentUser = (User) request.getSession().getAttribute(Constants.ATTRIBUTE_SECURITY_CONTEXT);

		if(json!=null && currentUser!=null){
			if(CSRFTokenUtils.isValid(request.getSession(), json)){
				EmployeeInfo info = new EmployeeInfo();
				persistenceController.storeEmployeeInfos(info, currentUser.getIdUser());
				MessageGenerator.sendSuccessMessage(response);
			}
			else{
				MessageGenerator.sendErrorMessage("Empty Data", response);
			}
		}
	}
}