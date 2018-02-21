package viewfx;

import java.util.HashMap;
import java.util.Map;

import db.AdminDBOperations;
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

public class AdminView extends Application  {
	

	
	@Override
    public void start(Stage primaryStage) {
		
			GridPane grid = new GridPane();
	        grid.setAlignment(Pos.TOP_LEFT);
	        grid.setHgap(10);
	        grid.setVgap(10);
	        grid.setPadding(new Insets(25, 25, 25, 25));

	        Text scenetitle = new Text("Admin View Page");
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
	                WelcomePage l = new WelcomePage();
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
	        

	       
	        Button btnHome1 = new Button("Admin");
	        btnHome1.setMaxWidth(Double.MAX_VALUE);
	        btnHome1.setMinWidth(60);
	        btnHome1.setMinHeight(30);
	       // btnExit.setStyle("-fx-background-color: crimson;");
	        HBox hbBth1 = new HBox(10);
	        hbBth1.setAlignment(Pos.TOP_RIGHT);
	        hbBth1.getChildren().add(btnHome1);
	        grid.add(hbBth1, 0, 2);
	        
	        btnHome1.setOnAction(new EventHandler<ActionEvent>() {

	            @Override
	            public void handle(ActionEvent e) {
	                AdminHome l = new AdminHome();
	                l.start(primaryStage);
	            }
	        });
	        
	        grid.setAlignment(Pos.TOP_LEFT);
	   
	        TableView table = new TableView();
	        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	        
	        table.setEditable(true);
	        
	        TableColumn firstcol = new TableColumn("AdminID");
	        firstcol.setCellValueFactory(new MapValueFactory("AdminID"));
	        firstcol.setMinWidth(30);
	 
	        TableColumn secondcol = new TableColumn("Name");
	        secondcol.setCellValueFactory(new MapValueFactory("Name"));
	        secondcol.setMinWidth(50);
	     
	        TableColumn thirdcol = new TableColumn("Designation");
	        thirdcol.setCellValueFactory(new MapValueFactory("Designation"));
	        thirdcol.setMinWidth(100);	     
	        
	        TableColumn forthcol = new TableColumn("Phone");
	        forthcol.setCellValueFactory(new MapValueFactory("Phone"));
	        forthcol.setMinWidth(50);
	        
	        TableColumn fifthCol = new TableColumn("Email");
	        fifthCol.setCellValueFactory(new MapValueFactory("Email"));
	        fifthCol.setMinWidth(50);
	        
	        TableColumn sixth = new TableColumn("UserID");
	        sixth.setCellValueFactory(new MapValueFactory("UserID"));
	        sixth.setMinWidth(50);
	        
	        TableColumn seventh = new TableColumn("Password");
	        seventh.setCellValueFactory(new MapValueFactory("password"));
	        seventh.setMinWidth(50);
	        
	        AdminDBOperations obj = new AdminDBOperations ();	  
	        table.setItems(obj.viewOperation());
	       
	        table.getColumns().addAll(firstcol, secondcol, thirdcol, forthcol, fifthCol, sixth, seventh);
	        
	 
	        final VBox vbox = new VBox();
	        vbox.setSpacing(5);
	        vbox.setPadding(new Insets(10, 0, 0, 10));
	        vbox.getChildren().addAll(scenetitle, table);
	 
	        grid.add(vbox, 0, 3);
	        
	        primaryStage.setTitle("View Admin Page");
	        Scene scene = new Scene(grid, 1000, 600);
	        primaryStage.setScene(scene);
	        primaryStage.show();
	}

}
