package org.provaHubFintech.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {
	
	static boolean succ = false;

	public ConnectionProvider() {
		
	}
	
	public static Connection getConnection() {
		Connection c = null;
		try {
			String driverName = "com.mysql.jdbc.Driver";
			Class.forName(driverName);
			
			String serverName = "localhost", mydatabase = "prova",
					url = "jdbc:mysql://" + serverName + "/" + mydatabase,
					username = "root",
					password = "root";
			c = DriverManager.getConnection(url, username, password);
			
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
