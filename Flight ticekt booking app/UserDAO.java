package javaproject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO extends DatabaseConnection
{
	public void addUser(User newUser)
	{
		String query="INSERT INTO users(Name,Email,Phone_number,Password)VALUES(?,?,?,?)";
		try
		{
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1, newUser.getName());
			ps.setString(2, newUser.getEmail());
			ps.setString(3, newUser.getPhone());
			ps.setString(4, newUser.getPassword());
			ps.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public User login(String email,String password)
	{
		String query="SELECT * FROM users WHERE Email=? AND Password=?";
		try
		{
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1, email);
			ps.setString(2, password);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				User newUser=new User();
				newUser.setUserID(rs.getInt("User_id"));
				newUser.setName(rs.getString("Name"));
				newUser.setEmail(rs.getString("Email"));
				newUser.setPhone(rs.getString("Phone_number"));
				newUser.setPassword(rs.getString("Password"));
				return newUser;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
