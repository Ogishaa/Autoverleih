package Autoverleih;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class Auto
{
	public static void dropTableAuto(Connection c) 
	{
		Statement stmt;
		String sql;
		try 
		{
        	stmt = c.createStatement();
            sql = "DROP TABLE IF EXISTS Auto;";
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e) 
		{
            e.printStackTrace();
        }
	}
	
	public static void createTableAuto(Connection c) 
	{
		Statement stmt;
		String sql;
		try 
		{
        	stmt = c.createStatement();
            sql = "CREATE TABLE IF NOT EXISTS Auto(" +
                  "kennzeichen VARCHAR(10) PRIMARY KEY," +
                  "modell VARCHAR(20)," +
                  "ps INT," +
                  "preisPerTag DOUBLE," +
                  "zulassungsdatum DATE);";
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e) 
		{
            e.printStackTrace();
        }
    }
	
	public static void insertIntoAuto(Connection c, String kz, String modell, int ps, double ppt, LocalDate zulassungsdatum) 
	{
		try 
		{
		String sql = "INSERT INTO Auto VALUES (?, ?, ?, ?, ?)";
		PreparedStatement preStmt = c.prepareStatement(sql);
		java.sql.Date sqlZulassungsdatum = java.sql.Date.valueOf(zulassungsdatum);
		
		preStmt.setString(1, kz);
		preStmt.setString(2, modell);
        preStmt.setInt(3, ps);
        preStmt.setDouble(4, ppt);
        preStmt.setDate(5, sqlZulassungsdatum);
        preStmt.executeUpdate();
        
        preStmt.close();
	    } catch(SQLException e) 
		  {
			e.printStackTrace();
		  }
   }
}