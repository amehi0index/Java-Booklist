package org.openjfx.Tablefx;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
* <h1>Insert Output and Read Input</h1>
* The Server class implements an application that
* writes data from a client to a file and sends response data back to a client upon request.
*
* @author  Amelia Hill
* @version 1.0
* @since   2021-12-04
*/

public class Server {

	 private ObjectOutputStream outputToFile;
	 private ObjectInputStream inputFromClient;

	 public static void main(String[] args) {
	    new Server();
	  }
	 
	 /**
	   * This method(constructor) creates different
	   * objects for IO when an object of class is created.
	   * 
	   * @param Nothing
	   * @return Nothing.
	   */

	  public Server() {
		 
		  try(  ServerSocket serverSocket = new ServerSocket(8001)) {
	     
	      System.out.println("Server is running.");

	      outputToFile = new ObjectOutputStream( new FileOutputStream("book.dat", true));
	      
	      while (true) {
	        Socket socket = serverSocket.accept();

	        inputFromClient = new ObjectInputStream(socket.getInputStream());

	        Object object = inputFromClient.readObject();
	     
	        outputToFile.writeObject(object);
	      
	        /**
	         * Send response to client that new book has been stored.
	         */
	        System.out.println("Your updated booklist has been stored.");
	        
	        ObjectOutputStream toClient = new ObjectOutputStream(socket.getOutputStream());
	        toClient.writeObject(object);
	      }
	    }
	    catch(ClassNotFoundException ex) {
	      ex.printStackTrace();
	    }
	    catch(IOException ex) {
	      ex.printStackTrace();
	    }
	    finally {
	      try {
	        inputFromClient.close();
	        outputToFile.close();
	      }
	      catch (Exception ex) {
	        ex.printStackTrace();
	      }
	    }
	  }

}
