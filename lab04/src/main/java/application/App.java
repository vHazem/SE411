package application;

import data.Product;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class App extends Application {
	
	Stage window;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
			window = primaryStage;
			primaryStage.setTitle("Lab 04 Application");
			
			BorderPane borderPane = new BorderPane();
			
			MenuBar menuBar = new MenuBar();
			Menu viewMenu = new Menu("Menu");
			
			MenuItem productsItem = new MenuItem("Products");
			MenuItem booksItem = new MenuItem("Books");
			MenuItem exitItem = new MenuItem("Exit");
			
			viewMenu.getItems().addAll(productsItem, booksItem, new SeparatorMenuItem(), exitItem);
			menuBar.getMenus().add(viewMenu);
			
			borderPane.setTop(menuBar);
			
			ProductView<Product> productsView = new ProductView<>(Product.class);
			borderPane.setCenter(productsView);
			
			productsItem.setOnAction(e -> borderPane.setCenter(new ProductView<Product>(Product.class)));
			booksItem.setOnAction(e -> borderPane.setCenter(new BookView()));
			exitItem.setOnAction(e -> System.exit(0));
			
			Scene scene = new Scene(borderPane, 640, 400);
			window.setScene(scene);
			window.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
