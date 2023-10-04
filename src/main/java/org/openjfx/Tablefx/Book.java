package org.openjfx.Tablefx;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import javafx.beans.property.SimpleStringProperty;

public class Book implements Serializable{

	private SimpleStringProperty title = new SimpleStringProperty("");
	private SimpleStringProperty author = new SimpleStringProperty("");
	private SimpleStringProperty isbn = new SimpleStringProperty("");

	public Book() {
	    this("", "", "");
	 }
	 
/**
   * This method calls all setters to give all the values to attributes of books.
   * @param title
   * @param author
   * @param isbn
   * @return nothing
   */

	public Book(String title, String author, String isbn) {
		setTitle(title);
	    setAuthor(author);
	    setIsbn(isbn);
	}

	public String getTitle() {
		return title.get();
	}
	 
	public void setTitle(String title) {
		this.title.set(title);
	}
		        
	public String getAuthor() {
		return author.get();
	}

	public void setAuthor(String author) {
        this.author.set(author);
    }
    
	public String getIsbn() {
	    return isbn.get();
	} 	

	 public void setIsbn(String isbn) {
		this.isbn.set(isbn);
	}
	    
	private void writeObject(ObjectOutputStream out) throws IOException {
		out.writeObject(getTitle());
		out.writeObject(getAuthor());
		out.writeObject(getIsbn());
	}

	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
	    title = new SimpleStringProperty((String) in.readObject());
	    author= new SimpleStringProperty((String) in.readObject());
	    isbn = new SimpleStringProperty((String) in.readObject());
	}	    	   
}

