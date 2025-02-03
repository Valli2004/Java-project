package javaproject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class BookingDAO extends DatabaseConnection
{
	Scanner scanner=new Scanner(System.in);
	public void bookFlight(FlightDAO flightDAO,int userID)
	{
		System.out.println("Enter FlightID to book:");
		int flightID=scanner.nextInt();
		System.out.println("Enter number of seats to book:");
		int seats=scanner.nextInt();
		if(!flightDAO.isSeatAvailable(flightID,seats))
		{
			System.out.println("Not enough seats available!");
			return;
		}
		String query="INSERT INTO bookings(User_id,Flight_id,Booking_date,Seats_booked)VALUES(?,?,CURDATE(),?)";
		try
		{
			PreparedStatement ps=con.prepareStatement(query);
			ps.setInt(1,userID);
			ps.setInt(2,flightID);
			ps.setInt(3,seats);
			ps.executeUpdate();
			flightDAO.updateSeatAvailability(flightID, -seats);
			System.out.println("Booking Successful!");
		}
		catch(Exception e)
		{
			e.printStackTrace();		
		}
	}
	public boolean cancelBooking(int userID)
	{
		System.out.println("Enter the Booking ID to cancel:");
		int bookingID=scanner.nextInt();
		String query="DELETE FROM bookings WHERE Booking_id=? AND User_id=?";
		try
		{
			PreparedStatement ps=con.prepareStatement(query);
			ps.setInt(1, bookingID);
			ps.setInt(2, userID);
			int rowsDeleted=ps.executeUpdate();
			if(rowsDeleted>0)
			{
				System.out.println("Booking cancelled successfully");
				return true;
			}
			else
			{
				System.out.println("Booking not found.");
				return false;
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return false;
		}
	}
	public void viewBooking(int userID)
	{
		String query="SELECT Booking_id,Flight_id,Seats_booked FROM bookings WHERE User_id=?";
		try
		{
			PreparedStatement ps=con.prepareStatement(query);
			ps.setInt(1, userID);
			ResultSet rs=ps.executeQuery();
			System.out.println("Your Bookings:");
			System.out.println("BookingID | FlightID | Seats Booked");
			while(rs.next())
			{
				System.out.println(rs.getInt("Booking_id")+"|"+rs.getInt("Flight_id")+"|"+rs.getInt("Seats_booked"));
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
}
