package db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import bo.AdminBO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AdminDBOperations implements DBOperations {

	public AdminBO fetchAdmin(String key)
	{
		Connection con = null;
		Statement stmt1 = null;
		ResultSet rs = null;
		AdminBO bo = new AdminBO();
		
		try
		{
			
		
			con = DBManagerFactory.getDBConnection();
			
			stmt1 = con.createStatement();
			String strQuery1 = "select adminid, name, designation, email, phone, u.loginid, u.password, u.userid  from app_admin a, app_users u "
					+ "where a.userid = u.userid AND adminid = '"+key+"' and rownum = 1";
						
			rs = stmt1.executeQuery(strQuery1);
			
			while(rs.next())
			{
				bo.setAdminid(rs.getString(1));
				bo.setName(rs.getString(2));
				bo.setDesignation(rs.getString(3));
				bo.setEmail(rs.getString(4));
				bo.setPhone(rs.getString(5));
				bo.setLoginid(rs.getString(6));
				bo.setPassword(rs.getString(7));
				bo.setUserid(rs.getString(8));				
			}
			
		}
		catch(Exception e)
		{			
			e.printStackTrace();
		}
		finally
		{
			try{
			rs.close();
			stmt1.close();
			con.close();
			}
			catch(Exception e)
			{
				//e.printStackTrace();
			}
		}
		
		
		
		return bo;
	}
	
	@Override
	public String addOperation(Object obj) {
		// TODO Auto-generated method stub
		Connection con = null;
		Statement stmt = null;
		Statement stmt1 = null;
		String strStatus = "error";
		
		try
		{
			AdminBO bo = (AdminBO)obj;
		
			con = DBManagerFactory.getDBConnection();
			
			int seqVal = SequenceGenerator.getNextvalue("app_userid");
			stmt = con.createStatement();			
			String strQuery = "INSERT INTO app_users (userid, loginid, password) VALUES ("+seqVal+", '"+bo.getLoginid()+"','"+bo.getPassword() +"')";
			
			stmt.executeQuery(strQuery);
			
			stmt1 = con.createStatement();
			String strQuery1 = "INSERT INTO app_admin (adminid, name, userid, designation, email, phone) values "
					+ "('"+bo.getAdminid()+"','"+bo.getName()+"','"+seqVal+"','"+bo.getDesignation()+"','"+bo.getEmail()+"','"+bo.getPhone()  +"')";			
			
			stmt1.executeQuery(strQuery1);
			
			con.commit();
			
			strStatus = "success";
		}
		catch(Exception e)
		{
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				//e1.printStackTrace();
			}
			e.printStackTrace();
		}
		finally
		{
			try{
			stmt.close();
			stmt1.close();
			con.close();
			}
			catch(Exception e)
			{
				//e.printStackTrace();
			}
		}
		return strStatus;
	}

	@Override
	public ObservableList<Map> viewOperation() {
		// TODO Auto-generated method stub
		ObservableList<Map> allData = FXCollections.observableArrayList();
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try
		{
			String strQuery = "SELECT adminid, name, designation, phone, email, loginid, password FROM app_admin a, app_users u WHERE a.userid = u.userid";
			con = DBManagerFactory.getDBConnection();
			stmt = con.createStatement();			
			rs = stmt.executeQuery(strQuery);
			
			while(rs.next())
			{
				Map<String, String> dataRow = new HashMap<>();
		        dataRow.put("AdminID", rs.getString(1));
		        dataRow.put("Name", rs.getString(2));
		        dataRow.put("Designation", rs.getString(3));
		        dataRow.put("Phone", rs.getString(4));
		        dataRow.put("Email", rs.getString(5));
		        dataRow.put("UserID", rs.getString(6));
		        dataRow.put("password", rs.getString(7));
		 
		        allData.add(dataRow);		  
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try{
			rs.close();
			stmt.close();
			con.close();
			}
			catch(Exception e)
			{				
			}
		}
		
		
		return allData;
	}

	@Override
	public String deleteOperation(String key) {
		
		Connection con = null;
		Statement stmt = null;
		Statement stmt1 = null;
		
		String strStatus = "fail";
		
		try
		{						
			con = DBManagerFactory.getDBConnection();
			
			stmt =  con.createStatement();
			String strQuery = "DELETE FROM app_admin WHERE userid IN (SELECT userid FROM app_users WHERE loginid = '"+key+"')";
			stmt.executeQuery(strQuery);
			
			stmt1 = con.createStatement();			
			String strQuery1 = "DELETE FROM app_users WHERE loginid = '"+key+"'";			
			stmt1.executeQuery(strQuery1);
			
			con.commit();
			
			strStatus = "success";
		}
		catch(Exception e)
		{
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		finally
		{
			try
			{
				stmt1.close();
				stmt.close();
				con.close();
			}catch(Exception e)
			{				
			}
		}
		return strStatus;
	}

	@Override
	public String updateOperation(Object obj) {
		Connection con = null;
		Statement stmt = null;
		Statement stmt1 = null;
		String strStatus = "error";
		
		try
		{
			AdminBO bo = (AdminBO)obj;
		
			con = DBManagerFactory.getDBConnection();
			
			stmt = con.createStatement();			
			String strQuery = "UPDATE app_users set loginid = '"+bo.getLoginid()+"' , password = '"+bo.getPassword() +"' where userid = '"+bo.getUserid()+"'";
			
			stmt.executeQuery(strQuery);
			
			stmt1 = con.createStatement();
			String strQuery1 = "UPDATE app_admin set name = '"+bo.getName()+"' , designation = '"+bo.getDesignation()+"' , email = '"+bo.getEmail()+"' , phone = '"+bo.getPhone()  +"'"
					+ " where adminid =  '"+bo.getAdminid()+"'";			
			
			stmt1.executeQuery(strQuery1);
			
			con.commit();
			
			strStatus = "success";
		}
		catch(Exception e)
		{
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				//e1.printStackTrace();
			}
			e.printStackTrace();
		}
		finally
		{
			try{
			stmt.close();
			stmt1.close();
			con.close();
			}
			catch(Exception e)
			{
				//e.printStackTrace();
			}
		}
		return strStatus;
	}

}
