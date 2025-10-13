package application.ui;

import data.Product;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.BorderPane;

public class MainMenuUI {

	
	public MenuBar getMainMenu(BorderPane layout) {
		Menu fileMenu = new Menu("File");
		
		MenuItem productsMenu = new MenuItem("Manage Products");
		productsMenu.setOnAction(e -> {
			ProductsUI<Product> pdui = new ProductsUI<Product>();
			layout.setCenter(pdui.getUI());
		});
		
		fileMenu.getItems().add(productsMenu);
		
		MenuItem booksMenu = new MenuItem("Manage Books");
		booksMenu.setOnAction(e -> {
			BooksUI bkui = new BooksUI();
			layout.setCenter(bkui.getUI());
		});
		
		fileMenu.getItems().add(booksMenu);
		fileMenu.getItems().add(new SeparatorMenuItem());
		fileMenu.getItems().add(new MenuItem("Exit"));

		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().addAll(fileMenu);
		
		return menuBar;
	}
}
