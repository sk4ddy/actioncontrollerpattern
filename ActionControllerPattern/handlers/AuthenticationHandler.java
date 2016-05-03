package handlers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import actions.IAction;
import actions.LoginAction;
import actions.LogoutAction;

@SuppressWarnings("serial")
public class AuthenticationHandler extends HttpServlet  {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			IAction action = new LogoutAction();
			action.doAction(request, response);
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	
		try {
			IAction action = new LoginAction();
			action.doAction(request, response);
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}