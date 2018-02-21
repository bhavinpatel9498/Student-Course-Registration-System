package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SequenceGenerator {
	
	public static int getNextvalue(String sequenceName)
	{
		int iSeqVal = 0;
		
		Connection conn = null;
		
		try
		{
			conn = DBManagerFactory.getDBConnection();
			String sql = "select "+sequenceName+".NEXTVAL from dual";
			PreparedStatement pst = conn.prepareStatement(sql);
			
			ResultSet rs = pst.executeQuery();
			
			if(rs.next())
			{
				iSeqVal = rs.getInt(1);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();			
		}
		finally
		{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		
		return iSeqVal;
	}

}
