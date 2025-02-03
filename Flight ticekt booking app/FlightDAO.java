package javaproject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class FlightDAO extends DatabaseConnection
{
	Scanner scanner=new Scanner(System.in);
	public void searchFlights()
	{
		System.out.println("Enter Source:");
		String source=scanner.nextLine();
		System.out.println("Enter Destination:");
		String destination=scanner.nextLine();
		String query="SELECT * FROM flights WHERE Source=? AND Destination=?";
		try
		{
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1, source);
			ps.setString(2, destination);
			ResultSet rs=ps.executeQuery();
			System.out.println("Available Flights:");
			System.out.println("FlightID\tAirline\tDeparture\tArrival\tSeats");
			while(rs.next())
			{
				System.out.println(rs.getInt("Flight_id")+"\t"+rs.getString("Airline")+"\t"+rs.getString("Departure_time_date")+"\t"+rs.getString("Arrival_time_date")+"\t"+rs.getString("Seats_available"));
				
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	public boolean isSeatAvailable(int flightID,int seats)
	{
		String query="SELECT Seats_available FROM flights WHERE Flight_id=?";
		try
		{
			PreparedStatement ps=con.prepareStatement(query);
			ps.setInt(1,flightID);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				int seatsAvailable=rs.getInt("Seats_available");
				return seatsAvailable>=seats;
			}
			else
			{
				System.out.println("Flight not found.");
				return false;
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return false;
		}
	}
	public boolean updateSeatAvailability(int flightID, int seats)
	{
		String query="UPDATE flights SET Seats_available=Seats_available+? WHERE Flight_id=?";
		try
		{
			PreparedStatement ps=con.prepareStatement(query);
			ps.setInt(1, seats);
			ps.setInt(2, flightID);
			int rowsUpdated=ps.executeUpdate();
			return rowsUpdated>0;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return false;
		}
	}
}
