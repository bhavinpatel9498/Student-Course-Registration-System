package viewfx;

import static javafx.geometry.HPos.RIGHT;

import bo.AdminBO;
import db.AdminDBOperations;
import db.SequenceGenerator;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AdminPage extends Application  {
	
	@Override
    public void start(Stage primaryStage) {
		
			GridPane grid = new GridPane();
	        grid.setAlignment(Pos.TOP_CENTER);
	        grid.setHgap(10);
	        grid.setVgap(10);
	        grid.setPadding(new Insets(25, 25, 25, 25));

	        Text scenetitle = new Text("Add Admin Page");
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
	        grid.add(hbBt, 0, 0, 30, 3 );
	        
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
	        grid.add(hbBth, 0, 1, 30, 3 );
	        
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
	        grid.add(hbBth1, 0, 2, 30, 3 );
	        
	        btnHome1.setOnAction(new EventHandler<ActionEvent>() {

	            @Override
	            public void handle(ActionEvent e) {
	                AdminHome l = new AdminHome();
	                l.start(primaryStage);
	            }
	        });
	        
	        grid.setAlignment(Pos.TOP_CENTER);

	        Label sName = new Label("Admin ID:");
	        grid.add(sName, 0, 1);
	        
	        TextField userTextField = new TextField();
	        grid.add(userTextField, 1, 1);
	        
	        int strAdminid = SequenceGenerator.getNextvalue("app_adminid");
	        userTextField.setText(""+strAdminid);
	        
	        userTextField.setEditable(false);
	        
	        
	        Label sid = new Label("Name:");
	        grid.add(sid, 0, 2);

	        TextField userTextField1 = new TextField();
	        grid.add(userTextField1, 1, 2);
	        
	        Label sEmail = new Label("Designation:");
	        grid.add(sEmail, 0, 3);

	        TextField userTextField2 = new TextField();
	        grid.add(userTextField2, 1, 3);
	        
	        Label sPhone = new Label("Email:");
	        grid.add(sPhone, 0, 4);

	        TextField userTextField3 = new TextField();
	        grid.add(userTextField3, 1, 4);
	        
	        Label sAddress = new Label("Phone:");
	        grid.add(sAddress, 0, 5);

	        TextField userTextField4 = new TextField();
	        grid.add(userTextField4, 1, 5);
	        
	        Label Userid = new Label("User ID:");
	        grid.add(Userid, 0, 6);

	        TextField userTextField5 = new TextField();
	        grid.add(userTextField5, 1, 6);
	        
	        Label pass = new Label("Password:");
	        grid.add(pass, 0, 7);

	        TextField userTextField6 = new TextField();
	        grid.add(userTextField6, 1, 7);
	        
	        Text actiontarget = new Text();
	        grid.add(actiontarget, 0, 10);
	        grid.setColumnSpan(actiontarget, 2);
	        grid.setHalignment(actiontarget, RIGHT);
	        actiontarget.setId("actiontarget");
	        
	        Button btn = new Button("Save Details");
	        HBox hbBtn = new HBox(10);
	        hbBtn.setAlignment(Pos.BOTTOM_CENTER);
	        hbBtn.getChildren().add(btn);
	        grid.add(hbBtn, 0, 8, 2, 1 );
	        //grid.add(hbBtn, 1, 8);
	        
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

	                AdminBO boObj = new AdminBO();
	                boObj.setAdminid(str1);
	                boObj.setName(str2);	                
	                boObj.setDesignation(str3);
	                boObj.setEmail(str4);
	                boObj.setPhone(str5);	               
	                boObj.setLoginid(str6);
	                boObj.setPassword(str7);
	                
	                AdminDBOperations obj = new AdminDBOperations();
	                String strStatus = obj.addOperation(boObj);
	                
	                if("success".equalsIgnoreCase(strStatus))
	                {		                
		                actiontarget.setFill(Color.GREEN);
		                actiontarget.setText("Data saved successfully!");
	                }
	                else
	                {
	                	actiontarget.setFill(Color.FIREBRICK);
	                    actiontarget.setText("Error in save operation");
	                }

	            }
	        });
	        
	        primaryStage.setTitle("Add Admin Page");
	        Scene scene = new Scene(grid, 1000, 600);
	        primaryStage.setScene(scene);
	        primaryStage.show();
	}

}
