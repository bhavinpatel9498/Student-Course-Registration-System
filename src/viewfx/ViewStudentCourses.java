package viewfx;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import db.AdminDBOperations;
import db.CourseDBOperations;
import db.DBManagerFactory;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ViewStudentCourses extends Application  {
	

	
	@Override
    public void start(Stage primaryStage) {
		
			GridPane grid = new GridPane();
	        grid.setAlignment(Pos.TOP_LEFT);
	        grid.setHgap(10);
	        grid.setVgap(10);
	        grid.setPadding(new Insets(25, 25, 25, 25));

	        Text scenetitle = new Text("View Student Courses Page");
	        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
	        grid.add(scenetitle, 0, 0, 2, 1);
	        
	        
	        
	        Button btnHome = new Button("Home");
	        btnHome.setMaxWidth(Double.MAX_VALUE);
	        btnHome.setMinWidth(60);
	        btnHome.setMinHeight(30);
	       // btnExit.setStyle("-fx-background-color: crimson;");
	        HBox hbBth = new HBox(10);
	        hbBth.setAlignment(Pos.TOP_RIGHT);
	        hbBth.getChildren().add(btnHome);
	        grid.add(hbBth, 0, 1);
	        
	        btnHome.setOnAction(new EventHandler<ActionEvent>() {

	            @Override
	            public void handle(ActionEvent e) {
	                NormalWelcome l = new NormalWelcome();
	                l.start(primaryStage);
	            }
	        });
	        
	        grid.setAlignment(Pos.TOP_LEFT);
	        Button btnExit = new Button("Logout");
	        btnExit.setMaxWidth(Double.MAX_VALUE);
	        btnExit.setMinWidth(60);
	        btnExit.setMinHeight(30);
	       // btnExit.setStyle("-fx-background-color: crimson;");
	        HBox hbBt = new HBox(10);
	        hbBt.setAlignment(Pos.TOP_RIGHT);
	        hbBt.getChildren().add(btnExit);
	        grid.add(hbBt, 0, 0);
	        
	        btnExit.setOnAction(new EventHandler<ActionEvent>() {

	            @Override
	            public void handle(ActionEvent e) {
	                Login l = new Login();
	                l.start(primaryStage);
	            }
	        });
	        
	        
	        grid.setAlignment(Pos.TOP_LEFT);
	   
	        TableView table = new TableView();
	        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	        
	        table.setEditable(true);
	        
	        TableColumn firstcol = new TableColumn("CourseID");
	        firstcol.setCellValueFactory(new MapValueFactory("CourseID"));
	        firstcol.setMinWidth(30);
	 
	        TableColumn secondcol = new TableColumn("CourseName");
	        secondcol.setCellValueFactory(new MapValueFactory("CourseName"));
	        secondcol.setMinWidth(50);
	     
	        TableColumn thirdcol = new TableColumn("Term");
	        thirdcol.setCellValueFactory(new MapValueFactory("Term"));
	        thirdcol.setMinWidth(50);	     
	        
	        TableColumn forthcol = new TableColumn("Professor");
	        forthcol.setCellValueFactory(new MapValueFactory("Professor"));
	        forthcol.setMinWidth(50);
	        
	        TableColumn fifthCol = new TableColumn("TA");
	        fifthCol.setCellValueFactory(new MapValueFactory("TA"));
	        fifthCol.setMinWidth(50);
	        	  
	        table.setItems(viewCourses());
	        //table.setItems(generateDataInMap());
	        table.getColumns().addAll(firstcol, secondcol, thirdcol, forthcol, fifthCol);
	 
	        final VBox vbox = new VBox();
	        vbox.setSpacing(5);
	        vbox.setPadding(new Insets(10, 0, 0, 10));
	        vbox.getChildren().addAll(scenetitle, table);
	 
	        grid.add(vbox, 0, 3);
	        
	        primaryStage.setTitle("View Student Courses Page");
	        Scene scene = new Scene(grid, 1000, 600);
	        primaryStage.setScene(scene);
	        primaryStage.show();
	}
	
	private ObservableList<Map> viewCourses() {
		// TODO Auto-generated method stub
		ObservableList<Map> allData = FXCollections.observableArrayList();
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try
		{
			String strQuery = "select c.COURSEID, c.COURSENAME, c.TERM, p.PROFESSORNAME, t.TANAME "
					+ "from APP_STUDENTCOURSES sc, app_course c,  app_professor p, app_ta t WHERE " 
					+" sc.COURSEID = c.COURSEID and c.PROFESSORID = p.PROFESSORID and c.TAID = t.TAID and sc.STUDENTID = 'A111'";
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

}
