package application.ui;

import java.util.List;

import data.Book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class BooksUI extends ProductsUI<Book> {
	
	private TextField authorInput;

	@Override
	protected List<TableColumn<Book, ?>> getColumns() {
		List<TableColumn<Book, ?>> columns = super.getColumns();
		
		TableColumn<Book, String> authorColumn = new TableColumn<Book, String>("Author");
		authorColumn.setMinWidth(140);
		authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
		
		columns.add(authorColumn);
		
		return columns;
	}
	
	@Override
	protected List<TextField> getFields() {
		List<TextField> fields = super.getFields();
		
		authorInput = new TextField();
		authorInput.setPromptText("author");
		authorInput.minWidth(100);
		
		fields.add(authorInput);

		return fields;
	}

	@Override
	protected void addButtonAction() {
		Book book = new Book();
		book.setName(nameInput.getText());
		book.setPrice(Double.parseDouble(priceInput.getText()));
		book.setQuantity(Integer.parseInt(quantityInput.getText()));
		book.setAuthor(authorInput.getText());
		elementsTable.getItems().add(book);
	}

	@Override
	public ObservableList<Book> getElements() {
		ObservableList<Book> books = FXCollections.observableArrayList();
		books.add(new Book("CS101 Textbook", 750, 100, "XYZ"));
		books.add(new Book("CS102 Textbook", 750, 100, "DFG"));
		return (ObservableList<Book>) books;
	}
	
}
