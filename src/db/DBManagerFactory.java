package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBManagerFactory {
	
	
	public static Connection getDBConnection()
	{
		/*Replace DB connection details with desired DB */
		
		String strDBUserID = "appuser";
		String strDBPwd = "password";
		String strHostName = "localhost";
		String strDBSID = "xe";
		String strPortNo = "1521";
		String strDBType = "oracle";
		
		Connection conn = null;
		
		try
		{
			if("oracle".equalsIgnoreCase(strDBType))
			{
				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn = DriverManager.getConnection("jdbc:oracle:thin:@"+strHostName+":"+strPortNo+":"+strDBSID, strDBUserID, strDBPwd);
			}
			else if("mysql".equalsIgnoreCase(strDBType))
			{
				
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://"+strHostName+":"+strPortNo+"/"+strDBSID, strDBUserID, strDBPwd);
			}
			
			conn.setAutoCommit(false);
			
		}
		catch(ClassNotFoundException cnfe)
		{
			cnfe.printStackTrace();
			conn = null;
		}
		catch(SQLException se)
		{
			se.printStackTrace();
			conn = null;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			conn = null;
		}		
		
		
		return conn;
		
	}

}
