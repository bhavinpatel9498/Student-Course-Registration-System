package viewfx;

import static javafx.geometry.HPos.RIGHT;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import bo.AdminBO;
import db.AdminDBOperations;
import db.DBManagerFactory;
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

public class AdminUpdate extends Application  {
	
	@Override
    public void start(Stage primaryStage) {
		
			GridPane grid = new GridPane();
	        grid.setAlignment(Pos.TOP_CENTER);
	        grid.setHgap(10);
	        grid.setVgap(10);
	        grid.setPadding(new Insets(25, 25, 25, 25));

	        Text scenetitle = new Text("Admin Update Page");
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
	        
	        Button btnHome1 = new Button("Admin");
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
	                AdminHome l = new AdminHome();
	                l.start(primaryStage);
	            }
	        });
	        
	        grid.setAlignment(Pos.TOP_CENTER);

	        Text delLabel = new Text("Admin ID:"); 
	        grid.add(delLabel, 0, 5);
	        
	        ChoiceBox cbox = new ChoiceBox(); 
	        //cbox.getItems().addAll   ("Admin1", "Admin2", "Admin3", "Admin4", "Admin5");
	        
	        Connection con = null;
	        Statement stm = null;
	        ResultSet rs = null;
	        try
	        {
	        	
	        	String strQuery = "SELECT adminid FROM app_users u, app_admin a WHERE a.userid = u.userid";
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
	            	
	            	AdminDBOperations aObj = new AdminDBOperations();
	            	AdminBO boObj = aObj.fetchAdmin((String)cbox.getValue());
	            	
	    	        Label sName = new Label("Admin ID:");
	    	        grid.add(sName, 0, 11);
	    	        
	    	        TextField userTextField = new TextField();
	    	        grid.add(userTextField, 1, 11);
	    	        
	    	        userTextField.setText(boObj.getAdminid());	    	        
	    	        userTextField.setEditable(false);
	    	        
	    	        Label sid = new Label("Name:");
	    	        grid.add(sid, 0, 12);

	    	        TextField userTextField1 = new TextField();
	    	        grid.add(userTextField1, 1, 12);
	    	        userTextField1.setText(boObj.getName());
	    	        
	    	        Label sEmail = new Label("Designation:");
	    	        grid.add(sEmail, 0, 13);

	    	        TextField userTextField2 = new TextField();
	    	        grid.add(userTextField2, 1, 13);
	    	        userTextField2.setText(boObj.getDesignation());
	    	        
	    	        Label sPhone = new Label("Email:");
	    	        grid.add(sPhone, 0, 14);

	    	        TextField userTextField3 = new TextField();
	    	        grid.add(userTextField3, 1, 14);
	    	        userTextField3.setText(boObj.getEmail());
	    	        
	    	        Label sAddress = new Label("Phone:");
	    	        grid.add(sAddress, 0, 15);

	    	        TextField userTextField4 = new TextField();
	    	        grid.add(userTextField4, 1, 15);
	    	        userTextField4.setText(boObj.getPhone());
	    	        
	    	        Label Userid = new Label("User ID:");
	    	        grid.add(Userid, 0, 16);

	    	        TextField userTextField5 = new TextField();
	    	        grid.add(userTextField5, 1, 16);
	    	        userTextField5.setText(boObj.getLoginid());
	    	        
	    	        Label pass = new Label("Password:");
	    	        grid.add(pass, 0, 17);

	    	        TextField userTextField6 = new TextField();
	    	        grid.add(userTextField6, 1, 17);
	    	        userTextField6.setText(boObj.getPassword());
	    	        
	    	        Text actiontarget = new Text();
	    	        grid.add(actiontarget, 0, 20);
	    	        grid.setColumnSpan(actiontarget, 2);
	    	        grid.setHalignment(actiontarget, RIGHT);
	    	        actiontarget.setId("actiontarget");
	    	       
	    	        
	    	        Button btn = new Button("Update Details");
	    	        HBox hbBtn = new HBox(10);
	    	        hbBtn.setAlignment(Pos.BOTTOM_CENTER);
	    	        hbBtn.getChildren().add(btn);
	    	        grid.add(hbBtn, 0, 18, 2, 1 );
	    	        
	    	        btn.setOnAction(new EventHandler<ActionEvent>() {

	    	            @Override
	    	            public void handle(ActionEvent e) {
	    	            	//System.out.println("Save Logic Here");
	    	            	
	    	                String str1 = userTextField.getText();
	    	                String str2 = userTextField1.getText();
	    	                String str3 = userTextField2.getText();
	    	                String str4 = userTextField3.getText();
	    	                String str5 = userTextField4.getText();	    
	    	                String str6 = userTextField5.getText();	
	    	                String str7 = userTextField6.getText();	
	    	           
	    	                if("".equalsIgnoreCase(str7) || "".equalsIgnoreCase(str6) || "".equalsIgnoreCase(str5) || "".equalsIgnoreCase(str4) || "".equalsIgnoreCase(str3) ||"".equalsIgnoreCase(str2) || "".equalsIgnoreCase(str1) )
	    	                {
	    	                	actiontarget.setFill(Color.FIREBRICK);
	    	                    actiontarget.setText("Please provide all values");
	    	                	return;
	    	                }

	    	                boObj.setAdminid(str1);
	    	                boObj.setName(str2);	                
	    	                boObj.setDesignation(str3);
	    	                boObj.setEmail(str4);
	    	                boObj.setPhone(str5);	               
	    	                boObj.setLoginid(str6);
	    	                boObj.setPassword(str7);
	    	                
	    	                AdminDBOperations obj = new AdminDBOperations();
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
	        
	        primaryStage.setTitle("Update Admin Page");
	        Scene scene = new Scene(grid, 1000, 600);
	        primaryStage.setScene(scene);
	        primaryStage.show();
	}

}
