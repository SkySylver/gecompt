package method;

import database.HibernateUtil;

public class LoginMethod {

	public boolean login(String username, String password){
		return HibernateUtil.getInstance().connect("localhost", "3308", "gecompt", username, password);
	}


	private final static LoginMethod instance = new LoginMethod();

	private LoginMethod() {}
	
	public static LoginMethod getInstance() {
		return instance;
	}
}

