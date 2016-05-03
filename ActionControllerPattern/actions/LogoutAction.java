package actions;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Constants;

public class LogoutAction implements IAction{

	@Override
	public void doAction(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().removeAttribute(Constants.ATTRIBUTE_SECURITY_CONTEXT);
		request.getSession().removeAttribute(Constants.ATTRIBUTE_SECURITY_ROLE);	
		request.getSession().invalidate();
		request.getSession(true);
		try {
			response.sendRedirect("/monet/index.html");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
