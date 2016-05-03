package actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messages.MessageGenerator;
import model.Constants;
import model.User;
import persistence.PersistenceController;
import utils.CSRFTokenUtils;

public class LoginAction implements IAction  {

	@Override
	public void doAction(HttpServletRequest request, HttpServletResponse response) {		

		String username = request.getParameter("j_username");
		String password = request.getParameter("j_password");
		User user = PersistenceController.getInstance().loginUser(username, password);
		if(null != user && user.getUsername()!=null) {
			request.getSession().invalidate();
			request.getSession(true);
			request.getSession().setAttribute(Constants.ATTRIBUTE_SECURITY_CONTEXT, user);
			request.getSession().setAttribute(Constants.ATTRIBUTE_SECURITY_ROLE, user.getRole());
			CSRFTokenUtils.setToken(request.getSession());
			MessageGenerator.redirectMessage("/logged/home.html", response);
		} else {
			MessageGenerator.redirectMessage("/index.html", response);
		}	
	}
}