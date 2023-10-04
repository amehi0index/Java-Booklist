package org.openjfx.Tablefx;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
* <h1>ADD AND UPDATE CONTENT</h1>
* The TableController class implements an application that
* adds and updates the contents in the FXML document.
*
*/
public class TableController {
	@FXML private TableView<Book> tableView;
    @FXML private TextField titleField;
    @FXML private TextField authorField;
    @FXML private TextField isbnField;
    @FXML private ListView<Book> listView;
    
    
    /**
     * This method adds a new book once the button is pressed,
     * provided no fields are left empty.
     * @param event.
     * @return Nothing.
     * @exception ClassNotFoundException.
     */
    @FXML
  	protected void addBook(ActionEvent event) throws ClassNotFoundException {
    	ObservableList<Book> data = tableView.getItems();
    	
    	if(authorField.getText().isEmpty() ||  titleField.getText().isEmpty() || isbnField.getText().isEmpty() ) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Missing Field");
			alert.setContentText("Please complete all fields");
			alert.showAndWait();
    	}else{
    		//Add new book to table
            data.add(new Book(titleField.getText(), authorField.getText(), isbnField.getText() ));
            titleField.setText("");
            authorField.setText("");
            isbnField.setText("");
    	} 	
   }
     
    /**
     * This method implements a socket-server to read and write data to and from the server.
     * The method is triggered by the click of the update button by the user.
     * 
     * @param event
     * @return Nothing.
     * 
     */ 
   @FXML
   protected void updateList(ActionEvent event) throws ClassNotFoundException {
	   ObservableList<Book> data = tableView.getItems();
        
	   //Connect to server
        try(  Socket socket = new Socket("localhost", 8001)) {
	        
        	// Create an output stream to the server
	        ObjectOutputStream toServer = new ObjectOutputStream(socket.getOutputStream());
	        
	        toServer.writeObject(new ArrayList<Book>(data));
	    
	        //Read data from server
			ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
				
			//Assign data from server to myList
			ArrayList<Book> myList = new ArrayList<>();
			myList = (ArrayList<Book>) inputStream.readObject();
		
			if(myList != null) {
				//Populate listview with data from server
				ObservableList<Book> items = FXCollections.observableArrayList(myList);
	
				//Create listView
			    listView.setCellFactory(param -> new ListCell<Book>() {
			    	@Override
				    protected void updateItem(Book item, boolean empty) {
			    		super.updateItem(item, empty);
				        if (item != null) {
				        	setText(item.getTitle());
				        } else {
				            setText(""); 
				        }
				    }       
				});
			    	
			    listView.setItems(items);
			}
        }
	      catch (IOException ex) {
	        ex.printStackTrace();
	      }
	} 
	
    @FXML 
    public void initialize() { 
    	ObservableList<Book> data = tableView.getItems();
    	TableColumn<Book, Book> delCol = new TableColumn<>("");
    
    	delCol.setId("delete-col");
    	delCol.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        
    	delCol.setCellFactory(param -> new TableCell<Book, Book>() {
    		
            private final Button deleteButton = new Button("X");
          
            @Override
            protected void updateItem(Book book, boolean empty) {
                super.updateItem(book, empty);

                if (book == null) {
                    setGraphic(null);
                    return;
                }
                setGraphic(deleteButton);
                deleteButton.setId("delete-btn");
                deleteButton.setOnAction(event -> data.remove(book));
            }
        });
        
        tableView.setItems(data);
        tableView.getColumns().addAll(delCol);
    }
}


	

