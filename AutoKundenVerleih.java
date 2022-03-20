package Autoverleih;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class AutoKundenVerleih 
{
	public static void dropTableAutoKundenVerleih(Connection c) 
	{
		Statement stmt;
		String sql;
		try 
		{
        	stmt = c.createStatement();
            sql = "DROP TABLE IF EXISTS AutoKundenVerleih;";
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e) 
		{
            e.printStackTrace();
        }
	}
	
	public static void createTableAutoKundenVerleih(Connection c) 
	{
		Statement stmt;
		String sql;
		try 
		{
        	stmt = c.createStatement();
            sql = "CREATE TABLE IF NOT EXISTS KundeleihtAuto(" +
                  "kundenid INT NOT NULL," +
                  "kennzeichen VARCHAR(8) NOT NULL," +
                  "beginn DATE," +
                  "ende DATE," +
                  "PRIMARY KEY(kundenid, kennzeichen, beginn)," +
                  "FOREIGN KEY(kundenid) REFERENCES Kunde(id)," +
                  "FOREIGN KEY(kennzeichen) REFERENCES Auto(kennzeichen));";
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e) 
		{
            e.printStackTrace();
        }
    }

	public static void insertIntoAutoKundenVerleih(Connection c, int kundenid, String kennzeichen, LocalDate ende) {
		try 
		{
			String sql = "INSERT INTO KundeLeihtAuto VALUES (?, ?, ?, ?);";
			PreparedStatement preStmt = c.prepareStatement(sql);
			java.sql.Date sqlBeginn = java.sql.Date.valueOf(LocalDate.now());
			java.sql.Date sqlEnde = java.sql.Date.valueOf(ende);
			
			preStmt.setInt(1, kundenid);
			preStmt.setString(2, kennzeichen);
	        preStmt.setDate(3, sqlBeginn);
	        preStmt.setDate(4, sqlEnde);
	        preStmt.executeUpdate();
			
            preStmt.close();
		} catch (SQLException e) 
		{
            e.printStackTrace();
        }
	}
}
