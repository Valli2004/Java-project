package javaproject;

import javaproject.User;
import javaproject.UserDAO;
import java.util.Scanner;

public class Main 
{
	public static void main(String[] args) 
	{
		Scanner scanner=new Scanner(System.in);
		UserDAO userDAO=new UserDAO();
		FlightDAO flightDAO=new FlightDAO();
		BookingDAO bookingDAO=new BookingDAO();
		User loggedIn=null;
		System.out.println("Welcome to the Flight Ticket Booking App");
		while(true)
		{
			System.out.println("\nChoose an option:\n\t1. Register\n\t2. Log In\n\t3. Exit");
			int choice=scanner.nextInt();
			scanner.nextLine();
			switch(choice)
			{
			case 1:
				System.out.println("Enter your Name:");
				String name=scanner.next();
				System.out.println("Enter your Email:");
				String email=scanner.next();
				System.out.println("Enter your Phone number:");;
				String phone=scanner.next();
				System.out.println("Enter your Password");
				String password=scanner.next();
				
				User newUser=new User();
				newUser.setName(name);
				newUser.setEmail(email);
				newUser.setPhone(phone);
				newUser.setPassword(password);
				userDAO.addUser(newUser);
				System.out.println("Registration Successful.");
				break;
			case 2:
				System.out.println("Enter you Email:");
				email=scanner.next();
				System.out.println("Enter your Password:");
				password=scanner.next();
				
				loggedIn=userDAO.login(email,password);
				if(loggedIn!=null)
				{
					System.out.println("Login Successful! Welcome "+loggedIn.getName());
					userMenu(scanner,flightDAO,bookingDAO,loggedIn);
				}
				else
				{
					System.out.println("Invalid Name or Password. Please try again.");
				}
				break;
			case 3:
				System.out.println("Thanks for using the Flight Ticket Booking App");
				scanner.close();
				System.exit(0);
			default:
				System.out.println("Invalid choice. Please try again.");
			}
		}
	}
	public static void userMenu(Scanner scanner,FlightDAO flightDAO,BookingDAO bookingDAO,User user)
	{
		while(true)
		{
			System.out.println("\nUser Menu:\n\t1. Search Flights\n\t2. Book Flight Ticket\n\t3. Cancel Flight Ticket\n\t4. View My Bookings\n\t 5. Log Out");
			int choice=scanner.nextInt();
			switch(choice)
			{
			case 1:
				flightDAO.searchFlights();
				break;
			case 2:
				bookingDAO.bookFlight(flightDAO,user.getUserID());
				break;
			case 3:
				bookingDAO.cancelBooking(user.getUserID());
				break;
			case 4:
				bookingDAO.viewBooking(user.getUserID());
				break;
			case 5:
				System.out.println("Logging out...");
				return;
			default:
				System.out.println("Invalid choice. Please try again.");
			}
		}
	}
}
