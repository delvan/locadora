package com.locadora.connection.br;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnetionDB {
	
	  public Connection conecta() {
		  Connection con = null;
		  try
	      {
	          Class.forName("com.mysql.jdbc.Driver");
	          con=DriverManager.getConnection(
	                  "jdbc:mysql://localhost:3306/db_locadora","root","admin");
	          Statement stmt=con.createStatement();  
	          ResultSet rs=stmt.executeQuery("show databases;");
	          System.out.println("Connected");  
	      }
	      catch(Exception e)
	      {
	          System.out.println(e);
	      }
		  
		  return con;
		  
	  }

}
