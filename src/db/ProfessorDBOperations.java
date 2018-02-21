package db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import bo.AdminBO;
import bo.ProfessorBO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProfessorDBOperations  implements DBOperations{
	
	public ProfessorBO fetchProfessor(String key)
	{
		Connection con = null;
		Statement stmt1 = null;
		ResultSet rs = null;
		ProfessorBO bo = new ProfessorBO();
		
		try
		{
			
		
			con = DBManagerFactory.getDBConnection();
			
			stmt1 = con.createStatement();
			String strQuery1 = "select professorid, professorname, email, phone from app_professor "
					+ "where professorid = '"+key+"' and rownum = 1";
						
			rs = stmt1.executeQuery(strQuery1);
			
			while(rs.next())
			{
				bo.setProfessorid(rs.getString(1));
				bo.setProfessorname(rs.getString(2));
				bo.setEmail(rs.getString(3));
				bo.setPhone(rs.getString(4));
								
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
		Statement stmt1 = null;
		String strStatus = "error";
		
		try
		{
			ProfessorBO bo = (ProfessorBO)obj;
		
			con = DBManagerFactory.getDBConnection();
		
			stmt1 = con.createStatement();
			String strQuery1 = "insert into app_professor (professorid, professorname, email, phone) values "
					+ "('"+bo.getProfessorid()+"','"+bo.getProfessorname()+"','"+bo.getEmail()+"','"+bo.getPhone()  +"')";			
			
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
			String strQuery = "select professorid, PROFESSORNAME, email, phone from app_professor order by professorid";
			con = DBManagerFactory.getDBConnection();
			stmt = con.createStatement();			
			rs = stmt.executeQuery(strQuery);
			
			while(rs.next())
			{
				Map<String, String> dataRow = new HashMap<>();
		        dataRow.put("ProfessorID", rs.getString(1));
		        dataRow.put("ProfessorName", rs.getString(2));
		        dataRow.put("Phone", rs.getString(3));
		        dataRow.put("Email", rs.getString(4));
		 
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
		Statement stmt1 = null;
		
		String strStatus = "fail";
		
		try
		{						
			con = DBManagerFactory.getDBConnection();
					
			stmt1 = con.createStatement();			
			String strQuery1 = "DELETE FROM app_professor WHERE professorid = '"+key+"'";			
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
		Statement stmt1 = null;
		String strStatus = "error";
		
		try
		{
			ProfessorBO bo = (ProfessorBO)obj;
		
			con = DBManagerFactory.getDBConnection();
		
			stmt1 = con.createStatement();
			String strQuery1 = "UPDATE app_professor SET professorname = '"+bo.getProfessorname()+"' , email = '"+bo.getEmail()+"' , phone = '"+bo.getPhone()  +"' "
					+ "WHERE professorid = '"+bo.getProfessorid()+"'";			
			
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
