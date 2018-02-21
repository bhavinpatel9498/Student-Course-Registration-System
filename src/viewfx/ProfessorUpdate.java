package viewfx;

import static javafx.geometry.HPos.RIGHT;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import bo.AdminBO;
import bo.ProfessorBO;
import db.AdminDBOperations;
import db.DBManagerFactory;
import db.ProfessorDBOperations;
import db.SequenceGenerator;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ProfessorUpdate extends Application  {
	
	@Override
    public void start(Stage primaryStage) {
		
			GridPane grid = new GridPane();
	        grid.setAlignment(Pos.TOP_CENTER);
	        grid.setHgap(10);
	        grid.setVgap(10);
	        grid.setPadding(new Insets(25, 25, 25, 25));

	        Text scenetitle = new Text("Professor Update Page");
	        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
	        grid.add(scenetitle, 0, 0, 2, 1);
	        
	        //grid.setAlignment(Pos.TOP_LEFT);
	        Button btnExit = new Button("Logout");
	        btnExit.setMaxWidth(Double.MAX_VALUE);
	        btnExit.setMinWidth(60);
	        btnExit.setMinHeight(30);
	       // btnExit.setStyle("-fx-background-color: crimson;");
	        HBox hbBt = new HBox(10);
	        hbBt.setAlignment(Pos.TOP_RIGHT);
	        hbBt.getChildren().add(btnExit);
	        grid.add(hbBt, 0, 1, 30, 3 );
	        
	        btnExit.setOnAction(new EventHandler<ActionEvent>() {

	            @Override
	            public void handle(ActionEvent e) {
	                Login l = new Login();
	                l.start(primaryStage);
	            }
	        });
	        
	        
	        Button btnHome = new Button("Home");
	        btnHome.setMaxWidth(Double.MAX_VALUE);
	        btnHome.setMinWidth(60);
	        btnHome.setMinHeight(30);
	       // btnExit.setStyle("-fx-background-color: crimson;");
	        HBox hbBth = new HBox(10);
	        hbBth.setAlignment(Pos.TOP_RIGHT);
	        hbBth.getChildren().add(btnHome);
	        grid.add(hbBth, 0, 4, 30, 3 );
	        
	        btnHome.setOnAction(new EventHandler<ActionEvent>() {

	            @Override
	            public void handle(ActionEvent e) {
	                WelcomePage l = new WelcomePage();
	                l.start(primaryStage);
	            }
	        });
	        
	        Button btnHome1 = new Button("Professor");
	        btnHome1.setMaxWidth(Double.MAX_VALUE);
	        btnHome1.setMinWidth(60);
	        btnHome1.setMinHeight(30);
	       // btnExit.setStyle("-fx-background-color: crimson;");
	        HBox hbBth1 = new HBox(10);
	        hbBth1.setAlignment(Pos.TOP_RIGHT);
	        hbBth1.getChildren().add(btnHome1);
	        grid.add(hbBth1, 0, 6, 30, 3 );
	        
	        btnHome1.setOnAction(new EventHandler<ActionEvent>() {

	            @Override
	            public void handle(ActionEvent e) {
	            	ProfessorHome l = new ProfessorHome();
	                l.start(primaryStage);
	            }
	        });
	        
	        grid.setAlignment(Pos.TOP_CENTER);

	        Text delLabel = new Text("Professor ID:"); 
	        grid.add(delLabel, 0, 5);
	        
	        ChoiceBox cbox = new ChoiceBox(); 
	        //cbox.getItems().addAll   ("Admin1", "Admin2", "Admin3", "Admin4", "Admin5");
	        
	        Connection con = null;
	        Statement stm = null;
	        ResultSet rs = null;
	        try
	        {
	        	
	        	String strQuery = "SELECT professorid FROM app_professor order by professorid";
	        	con = DBManagerFactory.getDBConnection();
	        	stm = con.createStatement();
	        	rs = stm.executeQuery(strQuery);
	        	
	        	while(rs.next())
	        	{
	        		cbox.getItems().add(rs.getString(1));
	        	}
	        
	        }
	        catch(Exception e)
	        {
	        	e.printStackTrace();
	        }
	        finally
	        {
	        	try
	        	{
	        		rs.close();
	        		stm.close();
	        		con.close();	        		
	        	}
	        	catch(Exception e)
	        	{	        		
	        	}
	        }
	        
	        grid.add(cbox, 0, 6);
	        
	        Button btn = new Button("Fetch Details");
	        HBox hbBtn = new HBox(10);
	        hbBtn.setAlignment(Pos.BOTTOM_CENTER);
	        hbBtn.getChildren().add(btn);
	        grid.add(hbBtn, 0, 8, 1, 1 );
	        //grid.add(hbBtn, 1, 8);
	        
	        Text actiontarget1 = new Text();
	        grid.add(actiontarget1, 0, 10);
	        grid.setColumnSpan(actiontarget1, 2);
	        grid.setHalignment(actiontarget1, RIGHT);
	        actiontarget1.setId("actiontarget");
	        
	        
	        btn.setOnAction(new EventHandler<ActionEvent>() {

	            @Override
	            public void handle(ActionEvent e) {
	            	
	            	
	            	if((String)cbox.getValue() == null)
	            	{
	                	actiontarget1.setFill(Color.FIREBRICK);
	                    actiontarget1.setText("Please select a value to fetch");
	            		return;
	            	}
	            	
	            	actiontarget1.setText("");
	            	
	            	ProfessorDBOperations aObj = new ProfessorDBOperations();
	            	ProfessorBO boObj = aObj.fetchProfessor((String)cbox.getValue());
	            	
	    	        Label sName = new Label("Professor ID:");
	    	        grid.add(sName, 0, 11);

	    	        TextField userTextField = new TextField();
	    	        grid.add(userTextField, 1, 11);
	    	        
	    	        userTextField.setText(boObj.getProfessorid());	    	 	        
	    	        userTextField.setEditable(false);
	    	        
	    	        Label sid = new Label("Professor Name:");
	    	        grid.add(sid, 0, 12);

	    	        TextField userTextField1 = new TextField();
	    	        grid.add(userTextField1, 1, 12);
	    	        userTextField1.setText(boObj.getProfessorname());
	    	        
	    	        Label sEmail = new Label("Email:");
	    	        grid.add(sEmail, 0, 13);

	    	        TextField userTextField2 = new TextField();
	    	        grid.add(userTextField2, 1, 13);
	    	        userTextField2.setText(boObj.getEmail());
	    	        
	    	        Label sPhone = new Label("Phone:");
	    	        grid.add(sPhone, 0, 14);

	    	        TextField userTextField3 = new TextField();
	    	        grid.add(userTextField3, 1, 14);
	    	        userTextField3.setText(boObj.getPhone());
	    	        
	    	        Button btn = new Button("Update Details");
	    	        HBox hbBtn = new HBox(10);
	    	        hbBtn.setAlignment(Pos.BOTTOM_CENTER);
	    	        hbBtn.getChildren().add(btn);
	    	        grid.add(hbBtn, 0, 18, 2, 1 );
	    	        //grid.add(hbBtn, 1, 8);
	    	        
	    	        Text actiontarget = new Text();
	    	        grid.add(actiontarget, 0, 20);
	    	        grid.setColumnSpan(actiontarget, 2);
	    	        grid.setHalignment(actiontarget, RIGHT);
	    	        actiontarget.setId("actiontarget");
	    	        
	    	        btn.setOnAction(new EventHandler<ActionEvent>() {

	    	            @Override
	    	            public void handle(ActionEvent e) {
	    	            	
	    	                String str1 = userTextField.getText();
	    	                String str2 = userTextField1.getText();
	    	                String str3 = userTextField2.getText();
	    	                String str4 = userTextField3.getText();
	    	           
	    	                if( "".equalsIgnoreCase(str4) || "".equalsIgnoreCase(str3) ||"".equalsIgnoreCase(str2) || "".equalsIgnoreCase(str1) )
	    	                {
	    	                	actiontarget.setFill(Color.FIREBRICK);
	    	                    actiontarget.setText("Please provide all values");
	    	                	return;
	    	                }
	    	                
	    	                
	    	                boObj.setProfessorname(str2);
	    	                boObj.setEmail(str3);
	    	                boObj.setPhone(str4);
	    	                
	    	                ProfessorDBOperations obj = new ProfessorDBOperations();
	    	                String strStatus = obj.updateOperation(boObj);
	    	                
	    	                if("success".equalsIgnoreCase(strStatus))
	    	                {		                
	    		                actiontarget.setFill(Color.GREEN);
	    		                actiontarget.setText("Data updated successfully!");
	    	                }
	    	                else
	    	                {
	    	                	actiontarget.setFill(Color.FIREBRICK);
	    	                    actiontarget.setText("Error in update operation");
	    	                }
	    	            }
	    	        });
	            	

	            }
	        });
	        
	        primaryStage.setTitle("Update Professor Page");
	        Scene scene = new Scene(grid, 1000, 600);
	        primaryStage.setScene(scene);
	        primaryStage.show();
	}

}
