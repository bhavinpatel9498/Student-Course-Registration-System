package db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import bo.AdminBO;
import bo.CourseBO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CourseDBOperations implements DBOperations {
	
	public CourseBO fetchCourseStudent(String key)
	{
		Connection con = null;
		Statement stmt1 = null;
		ResultSet rs = null;
		CourseBO bo = new CourseBO();
		
		try
		{			
		
			con = DBManagerFactory.getDBConnection();
			
			stmt1 = con.createStatement();
			String strQuery1 = "select courseid, coursename, term, p.professorname, t.taname from app_course c, app_professor p, app_ta t"
					+ " where courseid = '"+key+"' and c.professorid = p.professorid and t.taid = c.taid  and rownum = 1";
						
			rs = stmt1.executeQuery(strQuery1);
			
			while(rs.next())
			{				
				bo.setCourseid(rs.getString(1));
				bo.setCoursename(rs.getString(2));
				bo.setTerm(rs.getString(3));
				bo.setProfessorid(rs.getString(4));
				bo.setTaid(rs.getString(5));			
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
	
	public CourseBO fetchCourse(String key)
	{
		Connection con = null;
		Statement stmt1 = null;
		ResultSet rs = null;
		CourseBO bo = new CourseBO();
		
		try
		{			
		
			con = DBManagerFactory.getDBConnection();
			
			stmt1 = con.createStatement();
			String strQuery1 = "select courseid, coursename, term, professorid, taid from app_course where courseid = '"+key+"' and rownum = 1";
						
			rs = stmt1.executeQuery(strQuery1);
			
			while(rs.next())
			{				
				bo.setCourseid(rs.getString(1));
				bo.setCoursename(rs.getString(2));
				bo.setTerm(rs.getString(3));
				bo.setProfessorid(rs.getString(4));
				bo.setTaid(rs.getString(5));			
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
			CourseBO bo = (CourseBO)obj;
		
			con = DBManagerFactory.getDBConnection();
			
			
			stmt1 = con.createStatement();
			String strQuery1 = "insert into app_course (courseid, coursename, term, professorid, taid) values "
					+ "('"+bo.getCourseid()+"','"+bo.getCoursename()+"','"+bo.getTerm()+"','"+bo.getProfessorid()+"','"+bo.getTaid()  +"')";			
			
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
			String strQuery = "select courseid, coursename, term, professorid, taid from app_course order by courseid";
			con = DBManagerFactory.getDBConnection();
			stmt = con.createStatement();			
			rs = stmt.executeQuery(strQuery);
			
			while(rs.next())
			{
				Map<String, String> dataRow = new HashMap<>();
		        dataRow.put("CourseID", rs.getString(1));
		        dataRow.put("CourseName", rs.getString(2));
		        dataRow.put("Term", rs.getString(3));
		        dataRow.put("Professor", rs.getString(4));
		        dataRow.put("TA", rs.getString(5));
		 
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
			String strQuery = "DELETE FROM APP_STUDENTCOURSES WHERE courseid = '"+key+"'";
			stmt.executeQuery(strQuery);
			
			stmt1 = con.createStatement();			
			String strQuery1 = "DELETE FROM app_course WHERE courseid = '"+key+"'";			
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
		Statement stmt1 = null;
		String strStatus = "error";
		
		try
		{
			CourseBO bo = (CourseBO)obj;
		
			con = DBManagerFactory.getDBConnection();
			
			
			stmt1 = con.createStatement();
			String strQuery1 = "UPDATE app_course SET coursename = '"+bo.getCoursename()+"' , term = '"+bo.getTerm()+"', professorid = '"+bo.getProfessorid()+"' , taid = '"+bo.getTaid()  +"' "
					+ "WHERE courseid = '"+bo.getCourseid()+"'";			
			
					
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
