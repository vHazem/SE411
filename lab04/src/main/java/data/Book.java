package data;

public class Book extends Product {
	private String author;

	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Book(String name, double price, int quantity, String author) {
		super(name, price, quantity);
		this.author = author;

	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
}
