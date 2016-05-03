package messages;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import model.Constants;
import model.EmployeeInfo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

public class MessageGenerator {

	private static MessageGenerator instance = new MessageGenerator();

	public static MessageGenerator getInstance(){
		return instance;
	}

	public static void redirectMessage(String destination, HttpServletResponse response){
		StringBuffer msg = new StringBuffer("{\"").append(Constants.ATTRIBUTE_ACTION_TYPE).append("\":\"").
				append(Constants.JSON_REDIRECT).append("\",\"").append(Constants.JSON_LOCATION).append("\":\"").
				append(destination).append("\"}");
		send(msg.toString(),response);
	}

	public static void sendErrorMessage(String error, HttpServletResponse response) {
		JsonObject msg = new JsonObject();
		msg.addProperty(Constants.JSON_ATTRIBUTE_RESULT, Constants.JSON_VALUE_ERROR);
		msg.addProperty(Constants.JSON_ATTRIBUTE_ERROR_MSG, error);
		send(msg.toString(),response);
	}

	public static void sendSuccessMessage(HttpServletResponse response) {
		JsonObject msg = new JsonObject();
		msg.addProperty(Constants.JSON_ATTRIBUTE_RESULT, Constants.JSON_VALUE_SUCCESS);
		send(msg.toString(),response);
	}


	public static void csrfToken(String token, HttpServletResponse response) {
		StringBuffer msg = new StringBuffer("{\"").append(Constants.CSRF_TOKEN).append("\":\"").
				append(token).append("\"}");
		send(msg.toString(),response);
	}

	public static void employeeInfo(EmployeeInfo info, HttpServletResponse response) {
		Gson gson = new GsonBuilder().create();
		send(gson.toJson(info),response);
	}

	public void informationsStored(HttpServletResponse response) {
		StringBuffer msg = new StringBuffer("{\"").append("result").append("\":\"").
				append("stored").append("\"}");
		send(msg.toString(),response);
	}

	private static void send(String msg, HttpServletResponse response){
		try {
			PrintWriter out = response.getWriter();
			out.print(msg.toString());
			out.flush();
		} catch (IOException e) {
			System.out.println("HTTP Post Response. Exception: " + e.getMessage());
		}
	}

	public static void wrongCSRFToken(HttpServletResponse response) {
		JsonObject msg = new JsonObject();
		msg.addProperty(Constants.JSON_ATTRIBUTE_RESULT, Constants.JSON_VALUE_ERROR);
		msg.addProperty(Constants.JSON_ATTRIBUTE_ERROR_MSG, "Wrong CSRF Token");
		send(msg.toString(),response);
		
	}
}