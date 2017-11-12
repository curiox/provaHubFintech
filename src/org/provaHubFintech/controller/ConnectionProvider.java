package org.provaHubFintech.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.provaHubFintech.singleton.ConfigSingleton;

public class ConnectionProvider {
	
	static boolean succ = false;

	public ConnectionProvider() {
		
	}
	
	public static Connection getConnection() {
		Connection c = null;
		try {
			String driverName = "com.mysql.jdbc.Driver";
			Class.forName(driverName);
			
			ConfigSingleton singleton = ConfigSingleton.getInstance();
			
			String url = "jdbc:mysql://" + singleton.serverName + "/" + singleton.dbName;
			c = DriverManager.getConnection(url, singleton.user, singleton.password);
			
			if (c != null) {
				succ = true;
			} else {
				succ = false;
			}
			
			return c;
		} catch (ClassNotFoundException e) {
			System.out.println("O driver especificado não foi encontrado.");
			return null;
		} catch (SQLException e) {
			System.out.println("Não foi possível conectar ao BD");
			return null;
		}
	}
	
	public static boolean statusConnection() {
		return succ;
	}
	
	public static boolean closeConnection() {
		try {
			ConnectionProvider.getConnection().close();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
	
}
