package data;

public class Book extends Product {

	private String author;
	
	public Book() {
		super();
	}
	
	public Book(String name, double price, int quantity, String author) {
		super(name, price, quantity);
		this.setAuthor(author);
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
}
