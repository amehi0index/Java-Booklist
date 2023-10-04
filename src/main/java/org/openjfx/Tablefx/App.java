package org.openjfx.Tablefx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.net.URL;

/**
 * 
 * This application provides users a means to record book information in the form of 
 * a list by implementing server sockets to write and read user input to and from the server.
 * User input includes title of book, author and isbn. Response data from the server, which is 
 * based on changes made by the user, is displayed in the GUI ListView.
 * 
 * @param primary stage
 * @return Nothing.
 * @exception IOException On input error.
 * @see IOException
 * @author Amelia Hill
 * @version 1.0
 * @since 2021-12-04
 * 
 */
	
public class App extends Application {
	
	  @Override
	    public void start(Stage primaryStage) throws Exception {
	        Parent root = FXMLLoader.load(getClass().getResource("primary.fxml"));
	        Scene scene = new Scene(root, 850, 650);
	        
	       //Get css file
	       //scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
	        URL url = this.getClass().getResource("style.css");
	        if (url == null) {
	            System.exit(-1);
	        }
	        
	       String css = url.toExternalForm(); 
	       scene.getStylesheets().add(css);
	       scene.getStylesheets().add(
	    		   "https://fonts.googleapis.com/css2?family=Montserrat&family=Poppins&family=Road+Rage&family=Roboto&display=swap"
	       );

	        primaryStage.setScene(scene);
	        primaryStage.show();
	    }
	   
		public static void main(String[] args) {
		    launch();
		}
	}