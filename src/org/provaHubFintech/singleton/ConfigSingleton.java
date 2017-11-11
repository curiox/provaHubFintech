package org.provaHubFintech.singleton;

public class ConfigSingleton {
	
	public String user = "root";
	public String password = "root";
	public String serverName = "localhost";
	public String dbName = "prova";
	
	private static ConfigSingleton onlyInstance;
	
	private ConfigSingleton() {}
	
	public static ConfigSingleton getInstance() {
		if(onlyInstance == null) {
			onlyInstance = new ConfigSingleton();
		}
		return onlyInstance;
	}
}
