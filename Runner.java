package Autoverleih;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.time.LocalDate;

public class Runner 
{
	public static Connection getConnection(String url, String user, String passwort)  
	{
		try 
		{
			return DriverManager.getConnection(url, user, passwort);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public static void main(String[] args) 
	{
		String url = "jdbc:mysql://localhost:3306/datenbank";
		String user = "ogisha";
		String passwort = "ogisha123";
		
		try 
		{
			Connection c = getConnection(url, user, passwort);
			System.out.println("Connection erfolgreich");
			c.setAutoCommit(true);

			System.out.println("\nTABELLEN-LÖSCHEN");
			AutoKundenVerleih.dropTableAutoKundenVerleih(c);
			Auto.dropTableAuto(c);
			Kunde.dropTableKunde(c);
			
			System.out.println("\nTABELLEN-ERSTELLEN");
			Auto.createTableAuto(c);
			Kunde.createTableKunde(c);
			AutoKundenVerleih.createTableAutoKundenVerleih(c);
			
			System.out.println("\nAUTO");
			Auto.insertIntoAuto(c, "I-73HSA", "BMW M4", 420,  35.20, LocalDate.of(2015, 03, 11));
			Auto.insertIntoAuto(c, "I-MFU71", "Mercedes E220", 220, 18.40, LocalDate.of(2019, 12, 21));
			Auto.insertIntoAuto(c, "I-927KD", "Audi Q3", 184, 10.10, LocalDate.of(2020, 05, 03));
			
			System.out.println("\nKUNDE");
			Kunde.insertIntoKunde(c, "Ognjen Manojlovic", LocalDate.of(2003, 12, 16), "AT52 3124 3241 4134 6533");
			Kunde.insertIntoKunde(c, "Leo Juric", LocalDate.of(2004, 12, 14), "AT52 3452 2356 9434 4780");
			Kunde.insertIntoKunde(c, "Berkan Aslan", LocalDate.of(2003, 20, 10), "AT52 4235 0292 2395 1275");
			
			System.out.println("\nSCHUELER-ZU-KLASSE");
			AutoKundenVerleih.insertIntoAutoKundenVerleih(c, 1, "I-73HSA", LocalDate.of(2021, 5, 22));
			AutoKundenVerleih.insertIntoAutoKundenVerleih(c, 2, "I-MFU71", LocalDate.of(2020, 12, 02));
			AutoKundenVerleih.insertIntoAutoKundenVerleih(c, 3, "I-927KD", LocalDate.of(2019, 8, 15));
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}