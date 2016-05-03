package actions.controller;

import java.util.HashMap;
import java.util.Map;

import model.Constants;

public class UserActionsController extends ActionController {

	private static Map<String, String> type2action = new HashMap<String, String>();

	static {
		type2action.put(Constants.TYPE_GET_INFOS, "actions.GetEmployeeInfos");
		type2action.put(Constants.TYPE_SET_INFOS, "actions.SetEmployeeInfos");
		type2action.put(Constants.TYPE_GET_CSRF_TOKEN, "actions.GetCSRFTokenAction");
	}

	public UserActionsController() {
		super(type2action);
	}

}