package method;

public class AppMethod {

	
	// Singleton
	
	private AppMethod instance = new AppMethod();

	private AppMethod() {}
	
	public AppMethod getInstance() {
		return instance;
	}
	
	
	
	
}
