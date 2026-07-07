package Day7;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class DatabaseConnection {
	private static String jdbcUrl = "jdbc:postgresql://localhost:5432/postgres";
	private static String username = "postgres";
	private static String password = "abc123!";
	private static Scanner scanner = new Scanner(System.in);
	
	// SQL QUERIES
	private static String addStudent = 
			"INSERT INTO student "
			+ "(firstname, lastname, email, password) "
			+ "VALUES (?, ?, ?, ?);";
	private static String viewStudent = 
			"SELECT "
			+ "studentid, "
			+ "firstname, "
			+ "lastname, "
			+ "email, "
			+ "dateadded, "
			+ "dateupdated "
			+ "FROM student "
			+ "WHERE CAST(studentid AS TEXT) LIKE ? "
			+ "OR firstname LIKE ? "
			+ "OR lastname LIKE ? "
			+ "OR email LIKE ?;";
	private static String updatePassword = 
			"UPDATE student SET "
			+ "password = ?, "
			+ "dateupdated = CURRENT_TIMESTAMP "
			+ "WHERE email = ? AND password = ?;";
	private static String deleteStudent = 
			"DELETE FROM student "
			+ "WHERE email = ? AND password = ?;";
	private static String countMatchingCredentials = 
			"SELECT 1 AS results "
			+ "FROM student "
			+ "WHERE email = ? AND password = ?;";
	private static String countMatchingEmail = 
			"SELECT 1 AS results "
			+ "FROM student "
			+ "WHERE email = ?;";
	
	/**
	 * Choose CRUD-related options iteratively
	 * @param args
	 */
	public static void main(String[] args) {
		boolean querying = true;
		while (querying) {
			try {
				System.out.println("=== MENU ===");
				System.out.println("[A]dd");
				System.out.println("[V]iew");
				System.out.println("[U]pdate Password");
				System.out.println("[D]elete");
				System.out.println("[Q]uit\n");
				System.out.print("Enter choice: ");	
				
				String choice = scanner.nextLine().trim().toUpperCase();
				
				switch (choice) {
				case "A" -> add();
				case "V" -> view();
				case "U" -> updatePassword();
				case "D" -> delete();
				case "Q" -> querying = false;
				}
			} catch (Exception e) {
				System.out.println("Choice must be valid.");
			}
		}
		System.out.println("Quit successfully!");
		scanner.close();
	}
	
	/**
	 * Create a new student
	 */
	public static void add() {
		try {
			Connection con = DriverManager.getConnection(jdbcUrl, username, password);
			
			System.out.print("Email: ");
			String inputEmail = scanner.nextLine();
			
			PreparedStatement checkStatement = con.prepareStatement(countMatchingEmail);
			checkStatement.setString(1, inputEmail);
			
			ResultSet matchingStudents = checkStatement.executeQuery();
			
			if (!matchingStudents.next()) {
				System.out.print("Password: ");
				String inputPassword = scanner.nextLine();
				
				System.out.print("First name: ");
				String inputFirstname = scanner.nextLine();
				
				System.out.print("Last name: ");
				String inputLastname = scanner.nextLine();
				
				PreparedStatement ps = con.prepareStatement(addStudent);
				ps.setString(1, inputFirstname);
				ps.setString(2, inputLastname);
				ps.setString(3, inputEmail);
				ps.setString(4, inputPassword);
				int rowsAffected = ps.executeUpdate();
				
				if (rowsAffected > 0) {
					System.out.println("Row added successfully!");
				}
			} else {
				System.out.println("Email already taken!");
			}
			con.close();
		} catch (SQLException e) {
			System.out.println("Issue with insert.");
		}
	}
	
	/**
	 * Find student/s that match user input
	 */
	public static void view() {
		try {
			Connection con = DriverManager.getConnection(jdbcUrl, username, password);
			System.out.print("Search: ");
			String fieldValue = scanner.nextLine();
			boolean hasOutput = false;

			PreparedStatement ps = con.prepareStatement(viewStudent);
			ps.setString(1, "%" + fieldValue + "%");
			ps.setString(2, "%" + fieldValue + "%");
			ps.setString(3, "%" + fieldValue + "%");
			ps.setString(4, "%" + fieldValue + "%");
			
			ResultSet rs = ps.executeQuery();
	        while (rs.next()) {
	        	hasOutput = true;
	        	System.out.println(String.format(
	        			"ID: %s | Name: %s %s | Email: %s | Date Added: %s | Date Updated: %s", 
	        			rs.getInt("studentid"),
	        			rs.getString("firstname"),
	        			rs.getString("lastname"),
	        			rs.getString("email"),
	        			rs.getString("dateadded"),
	        			rs.getString("dateupdated")));
	        }
	        if (!hasOutput) {
	        	System.out.println("No matching row/s found.");
	        }
			con.close();
		} catch (SQLException e) {
			System.out.println("Issue with view.");
		}
	}
	
	/**
	 * Change user password when valid credentials
	 */
	public static void updatePassword() {
		try {
			Connection con = DriverManager.getConnection(jdbcUrl, username, password);

			System.out.print("Email: ");
			String inputEmail = scanner.nextLine();
			
			PreparedStatement checkEmailStatement = con.prepareStatement(countMatchingEmail);
			checkEmailStatement.setString(1, inputEmail);
			
			ResultSet matchingEmails = checkEmailStatement.executeQuery();
			if (matchingEmails.next()) {
				System.out.print("Password: ");
				String inputPassword = scanner.nextLine();
				
				PreparedStatement checkStatement = con.prepareStatement(countMatchingCredentials);
				checkStatement.setString(1, inputEmail);
				checkStatement.setString(2, inputPassword);
				
				ResultSet matchingCredentials = checkStatement.executeQuery();
				if (matchingCredentials.next()) {
					System.out.print("New password: ");
					String inputNewPassword = scanner.nextLine();
					
					PreparedStatement ps = con.prepareStatement(updatePassword);
					ps.setString(1, inputNewPassword);
					ps.setString(2, inputEmail);
					ps.setString(3, inputPassword);
					
					int rowsAffected = ps.executeUpdate();
					
					if (rowsAffected > 0) {
						System.out.println("Row/s updated successfully!");
					}
				} else {
					System.out.println("Invalid credentials.");
				}
			} else {
				System.out.println("Email not found.");
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Delete user when valid credentials
	 */
	public static void delete() {
		try {
			Connection con = DriverManager.getConnection(jdbcUrl, username, password);

			System.out.print("Email: ");
			String inputEmail = scanner.nextLine();
			
			PreparedStatement checkEmailStatement = con.prepareStatement(countMatchingEmail);
			checkEmailStatement.setString(1, inputEmail);
			
			ResultSet matchingEmails = checkEmailStatement.executeQuery();
			
			if (matchingEmails.next()) {
				System.out.print("Password: ");
				String inputPassword = scanner.nextLine();
				
				PreparedStatement checkStatement = con.prepareStatement(countMatchingCredentials);
				checkStatement.setString(1, inputEmail);
				checkStatement.setString(2, inputPassword);
				
				ResultSet matchingCredentials = checkStatement.executeQuery();
				if (matchingCredentials.next()) {
					PreparedStatement ps = con.prepareStatement(deleteStudent);
					ps.setString(1, inputEmail);
					ps.setString(2, inputPassword);
					
					int rowsAffected = ps.executeUpdate();
					if (rowsAffected > 0) {
						System.out.println("Row/s deleted successfully!");
					}
				} else {
					System.out.println("Invalid credentials.");
				}
			} else {
				System.out.println("Email does not exist.");
			}
			con.close();
		} catch (SQLException e) {
			System.out.println("Issue with deletion.");
		}
	}
}
