package application.ui;


import java.util.ArrayList;
import java.util.List;

import data.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ProductsUI<T extends Product> {
	
	TableView<T> elementsTable;
	TextField nameInput, priceInput, quantityInput;
	
	@SuppressWarnings("unchecked")
	public VBox getUI () {
		List<TableColumn<T, ?>> columns = getColumns();
		
		elementsTable = new TableView<T>(); 
		elementsTable.setItems(getElements());
		elementsTable.getColumns().addAll(columns);
		
		List<TextField> fields = getFields();
		
		
		Button addButton = new Button("Add");
		addButton.setOnAction(e -> {
			addButtonAction();
		});
		
		Button deleteButton = new Button("Delete");
		deleteButton.setOnAction(e -> {
			deleteButtonAction();
		});
		
		HBox btnLayout = new HBox();
		btnLayout.setPadding(new Insets(10, 20, 10, 20));
		btnLayout.setSpacing(10);
		btnLayout.getChildren().addAll(fields);
		btnLayout.getChildren().addAll(addButton, deleteButton);
		
		VBox layout = new VBox(10);
		layout.setPadding(new Insets(10, 10, 10, 10));
		layout.getChildren().addAll(elementsTable, btnLayout);
		
		return layout;
	}
	

	protected List<TextField> getFields() {
		List<TextField> fields = new ArrayList<TextField>();
		
		nameInput = new TextField();
		nameInput.setPromptText("name");
		nameInput.minWidth(100);
		
		priceInput = new TextField();
		priceInput.setPromptText("price");
		
		quantityInput = new TextField();
		quantityInput.setPromptText("quantity");
		
		fields.add(nameInput);
		fields.add(priceInput);
		fields.add(quantityInput);
		return fields;
	}


	protected List<TableColumn<T, ?>> getColumns() {
		List<TableColumn<T, ?>> columns = new ArrayList<TableColumn<T, ?>>();
		
		TableColumn<T, String> nameColumn = new TableColumn<T, String>("Name");
		nameColumn.setMinWidth(140);
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		TableColumn<T, Double> priceColumn = new TableColumn<T, Double>("Price");
		priceColumn.setMinWidth(100);
		priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
		
		TableColumn<T, Integer> quantityColumn = new TableColumn<T, Integer>("Quantity");
		quantityColumn.setMinWidth(100);
		quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		
		columns.add(nameColumn);
		columns.add(priceColumn);
		columns.add(quantityColumn);
		return columns;
	}


	private void deleteButtonAction() {
		ObservableList<T> selectedElements, allElements;
		allElements = elementsTable.getItems();
		selectedElements = elementsTable.getSelectionModel().getSelectedItems();
		
		for(Product p : selectedElements ) {
			allElements.remove(p);
		}
		
	}

	@SuppressWarnings("unchecked")
	protected void addButtonAction() {
		Product product = new Product();
		product.setName(nameInput.getText());
		product.setPrice(Double.parseDouble(priceInput.getText()));
		product.setQuantity(Integer.parseInt(quantityInput.getText()));
		elementsTable.getItems().add((T)product);
	}

	// get all products
	@SuppressWarnings("unchecked")
	public ObservableList<T> getElements() {
		ObservableList<Product> products = FXCollections.observableArrayList();
		products.add(new Product("Laptop", 750, 100));
		products.add(new Product("Chair", 50, 30));
		products.add(new Product("Table", 150, 20));
		products.add(new Product("Screen", 250, 80));
		products.add(new Product("Charger", 15, 1000));
		products.add(new Product("Lamp", 10, 1200));
		return (ObservableList<T>) products;
	}

}
