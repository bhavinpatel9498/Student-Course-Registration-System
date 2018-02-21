package db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import bo.AdminBO;
import bo.StudentBO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class StudentDBOperations implements DBOperations {
	
	public StudentBO fetchStudent(String key)
	{
		Connection con = null;
		Statement stmt1 = null;
		ResultSet rs = null;
		StudentBO bo = new StudentBO();
		
		try
		{
			
		
			con = DBManagerFactory.getDBConnection();
			
			stmt1 = con.createStatement();
			String strQuery1 = "select studentid, studentname, address, email, phone, u.loginid, u.password, u.userid  from app_students a, app_users u "
					+ "where a.userid = u.userid AND studentid = '"+key+"' and rownum = 1";
						
			rs = stmt1.executeQuery(strQuery1);
			
			while(rs.next())
			{
				bo.setStudentid(rs.getString(1));
				bo.setStudentname(rs.getString(2));
				bo.setAddress(rs.getString(3));
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
			StudentBO bo = (StudentBO)obj;
		
			con = DBManagerFactory.getDBConnection();
			
			int seqVal = SequenceGenerator.getNextvalue("app_userid");
			stmt = con.createStatement();			
			String strQuery = "INSERT INTO app_users (userid, loginid, password) VALUES ("+seqVal+", '"+bo.getLoginid()+"','"+bo.getPassword() +"')";
			
			stmt.executeQuery(strQuery);
			
			stmt1 = con.createStatement();
			String strQuery1 = "insert into app_students (studentid, studentname, userid, email, phone, address) values "
					+ "('"+bo.getStudentid()+"','"+bo.getStudentname()+"','"+seqVal+"','"+bo.getEmail()+"','"+bo.getPhone()+"','"+bo.getAddress() +"')";			
			
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
			String strQuery = "select studentid, studentname, phone, email, address, u.loginid, u.password  from app_students s, app_users u WHERE s.userid = u.userid order by studentid";
			con = DBManagerFactory.getDBConnection();
			stmt = con.createStatement();			
			rs = stmt.executeQuery(strQuery);
			
			while(rs.next())
			{
				Map<String, String> dataRow = new HashMap<>();
		        dataRow.put("StudentID", rs.getString(1));
		        dataRow.put("StudentName", rs.getString(2));
		        dataRow.put("Phone", rs.getString(3));
		        dataRow.put("Email", rs.getString(4));
		        dataRow.put("Address", rs.getString(5));
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
			String strQuery = "DELETE FROM APP_STUDENTCOURSES WHERE studentid =  '"+key+"'";
			stmt.executeQuery(strQuery);
			
			stmt1 = con.createStatement();			
			String strQuery1 = "DELETE FROM app_students WHERE studentid = '"+key+"'";			
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
			StudentBO bo = (StudentBO)obj;
		
			con = DBManagerFactory.getDBConnection();
			
			stmt = con.createStatement();			
			String strQuery = "UPDATE app_users SET loginid = '"+bo.getLoginid()+"' , password = '"+bo.getPassword() +"' WHERE userid =  '"+bo.getUserid()+"'";
			
			stmt.executeQuery(strQuery);
			
			stmt1 = con.createStatement();
			String strQuery1 = "UPDATE app_students SET studentname = '"+bo.getStudentname()+"' , email = '"+bo.getEmail()+"' , phone = '"+bo.getPhone()+"' , address = '"+bo.getAddress() +"' "
					+ "WHERE studentid  = '"+bo.getStudentid()+"'";			
			
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
