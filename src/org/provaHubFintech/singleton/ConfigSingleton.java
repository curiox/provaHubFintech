package org.provaHubFintech.singleton;

public class ConfigSingleton {
	
	public String user = "root";
	public String password = "root";
	public String serverName = "localhost";
	public String dbName = "prova";
	public String indexPath = "file:///C:\\Users\\junic\\git\\provaHubFintech\\WebContent\\index.html";
	
	private static ConfigSingleton onlyInstance;
	
	private ConfigSingleton() {}
	
	public static ConfigSingleton getInstance() {
		if(onlyInstance == null) {
			onlyInstance = new ConfigSingleton();
		}
		return onlyInstance;
	}
}
