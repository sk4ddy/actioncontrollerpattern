package actions.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Constants;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import actions.IAction;

public abstract class ActionController {
	

	protected Map<String, String> type2action;

	public ActionController(Map<String, String> actionsMap) {
		type2action = actionsMap;
	}

	public void executeAction(HttpServletRequest request, HttpServletResponse response) throws Exception{

			String data = request.getReader().readLine();
			if (data != null) {
				try {
					JSONObject json = (JSONObject) new JSONParser().parse(data);
					String className = type2action.get((String) json.get(Constants.ATTRIBUTE_ACTION_TYPE));
					System.out.println("### Recognized class: "+className);
					
				
					if(className!=null){
						request.setAttribute(Constants.ATTRIBUTE_JSON, json);
						try {
							IAction action = ((IAction) Class.forName(className).newInstance());
							action.doAction(request,response);
							System.out.println("### "+json.get("type")+" action executed");

						} catch (InstantiationException | IllegalAccessException| ClassNotFoundException e) {
							e.printStackTrace();
						}
					}
					else{
						System.out.println("### "+json.get("type")+" DIDN'T pass validation");
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}	
			}
	}
}