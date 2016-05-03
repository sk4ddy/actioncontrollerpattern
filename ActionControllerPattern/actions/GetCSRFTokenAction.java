package actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messages.MessageGenerator;
import model.Constants;

import org.json.simple.JSONObject;

public class GetCSRFTokenAction implements IAction {

	@Override
	public void doAction(HttpServletRequest request, HttpServletResponse response) {


		JSONObject json = (JSONObject) request.getAttribute(Constants.ATTRIBUTE_JSON);
		if (json != null) {	
			String csrfToken = (String) request.getSession().getAttribute(Constants.CSRF_TOKEN);
			MessageGenerator.csrfToken(csrfToken, response);
		}
	}
}
