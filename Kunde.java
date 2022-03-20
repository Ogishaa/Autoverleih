package Autoverleih;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class Kunde 
{
	public static void dropTableKunde(Connection c) 
	{
		Statement stmt;
		String sql;
		try 
		{
        	stmt = c.createStatement();
            sql = "DROP TABLE IF EXISTS kunde;";
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e) 
		{
            e.printStackTrace();
        }
	}
	
	public static void createTableKunde(Connection c) 
	{
		Statement stmt;
		String sql;
		try 
		{
        	stmt = c.createStatement();
            sql = "CREATE TABLE IF NOT EXISTS Kunde(" +
                  "id INT PRIMARY KEY AUTO_INCREMENT," +
                  "name VARCHAR(25)," +
                  "geburtsdatum DATE," +
                  "iban CHAR(24));";
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e) 
		{
            e.printStackTrace();
        }
    }
	
	public static void insertIntoKunde(Connection c, String name, LocalDate geburtsdatum, String iban) 
	{
		try {
    		String sql = "INSERT INTO Kunde (name, geburtsdatum, iban) VALUES (?, ?, ?)";
			PreparedStatement preStmt = c.prepareStatement(sql);
			java.sql.Date sqlGeburtsdatum = java.sql.Date.valueOf(geburtsdatum);
			
			preStmt.setString(1, name);
			preStmt.setDate(2, sqlGeburtsdatum);
	        preStmt.setString(3, iban);
	        preStmt.executeUpdate();
	        
	        preStmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
