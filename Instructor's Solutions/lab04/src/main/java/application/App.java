package application;


import application.ui.MainMenuUI;
import application.ui.ProductsUI;
import data.Product;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;



public class App extends Application {
	
	Stage window;

	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) { // Stage is the same as document/window in HTML
		try {
			window = primaryStage;
			primaryStage.setTitle("Tasks Manager 07 TableView");
			
			MainMenuUI menuUI = new MainMenuUI();
			
			BorderPane layout = new BorderPane();
			layout.setTop(menuUI.getMainMenu(layout));
			
			Scene scene = new Scene(layout, 640, 400);
			window.setScene(scene);
			window.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
