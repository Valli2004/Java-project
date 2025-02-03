package javaproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection 
{
	Connection con;
	String dburl="jdbc:mysql://localhost:3306/javaproject";
	String dbuser="root";
	String dbpass="Valli@4029";
	public DatabaseConnection()
	{
		try
		{
			con=DriverManager.getConnection(dburl,dbuser,dbpass);
		}
		catch(SQLException e)
		{
			System.out.println(e);
		}
	}
}