package application;

import data.Book;
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

public class ProductView<T extends Product> extends VBox {
    protected TableView<T> table;
    protected TextField nameInput, priceInput, quantityInput, authorInput;
    protected ObservableList<T> items;
    private Class<T> clazz;

    public ProductView(Class<T> clazz) {
        this.clazz = clazz;
        items = FXCollections.observableArrayList();
        setupSampleData();

        table = new TableView<>();
        table.setItems(items);
        setupColumns();

        setupInputs();

        HBox btnLayout = new HBox();
        btnLayout.setPadding(new Insets(10, 20, 10, 20));
        btnLayout.setSpacing(10);
        btnLayout.getChildren().addAll(nameInput, priceInput);

        if (clazz == Book.class) {
            btnLayout.getChildren().add(authorInput);
        } else {
            btnLayout.getChildren().add(quantityInput);
        }

        Button addButton = new Button("Add");
        addButton.setOnAction(e -> addAction());

        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> deleteAction());

        btnLayout.getChildren().addAll(addButton, deleteButton);

        this.setPadding(new Insets(10, 10, 10, 10));
        this.setSpacing(10);
        this.getChildren().addAll(table, btnLayout);
    }

    protected void setupSampleData() {
        if (clazz == Book.class) {
            items.add((T) new Book("Java Programming", 50, 100, "John Doe"));
            items.add((T) new Book("Python Guide", 40, 80, "Jane Smith"));
            items.add((T) new Book("Data Structures", 60, 50, "Bob Johnson"));
        } else {
            items.add((T) new Product("Laptop", 750, 100));
            items.add((T) new Product("Chair", 50, 30));
            items.add((T) new Product("Table", 150, 20));
            items.add((T) new Product("Screen", 250, 80));
            items.add((T) new Product("Charger", 15, 1000));
            items.add((T) new Product("Lamp", 10, 1200));
        }
    }

    protected void setupColumns() {
        TableColumn<T, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(140);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<T, Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setMinWidth(100);
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        table.getColumns().addAll(nameColumn, priceColumn);

        if (clazz == Book.class) {
            TableColumn<T, String> authorColumn = new TableColumn<>("Author");
            authorColumn.setMinWidth(140);
            authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
            table.getColumns().add(authorColumn);
        } else {
            TableColumn<T, Integer> quantityColumn = new TableColumn<>("Quantity");
            quantityColumn.setMinWidth(100);
            quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
            table.getColumns().add(quantityColumn);
        }
    }

    protected void setupInputs() {
        nameInput = new TextField();
        nameInput.setPromptText("name");
        nameInput.setMinWidth(100);

        priceInput = new TextField();
        priceInput.setPromptText("price");

        if (clazz == Book.class) {
            authorInput = new TextField();
            authorInput.setPromptText("author");
        } else {
            quantityInput = new TextField();
            quantityInput.setPromptText("quantity");
        }
    }

    protected void addAction() {
        String name = nameInput.getText();
        double price = Double.parseDouble(priceInput.getText());

        T item;
        if (clazz == Book.class) {
            String author = authorInput.getText();
            item = (T) new Book(name, price, 0, author); // Set quantity to 0 for books
        } else {
            int quantity = Integer.parseInt(quantityInput.getText());
            item = (T) new Product(name, price, quantity);
        }

        items.add(item);
        clearInputs();
    }

    protected void deleteAction() {
        ObservableList<T> selectedItems = table.getSelectionModel().getSelectedItems();
        items.removeAll(selectedItems);
    }

    protected void clearInputs() {
        nameInput.clear();
        priceInput.clear();
        if (clazz == Book.class) {
            authorInput.clear();
        } else {
            quantityInput.clear();
        }
    }
}