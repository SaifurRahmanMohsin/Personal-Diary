package com.tempestronics.helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHandler {
	Connection conn = null;
    String url = "jdbc:derby://diary;create=true";
    String usr = "app";
    String pwd = "";
    static {
    	try {
			DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
}