package com.stardy.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseUtil {
   public static Connection getConnection() {
	      
	      try {
	         String dbURL ="jdbc:oracle:thin:@hi.namoolab.com:1521/xepdb1";
	         String dbID ="Stardy";
	         String dbPassword ="12345";
	         Class.forName("oracle.jdbc.OracleDriver");
	         
	         return DriverManager.getConnection(dbURL,dbID,dbPassword);
	      }catch (Exception e) {
	         e.printStackTrace();
	      }
	      System.out.println("DB연결 안됨 ?");
	      return null;
	   }

}
