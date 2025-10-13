package lab03;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {

	private String TITLE = "Lab03 Application";
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle(TITLE);
		VBox layout = new VBox(10);
		
		Label inputLabel = new Label("Enter your name:");
		TextField inputName = new TextField();
		inputName.setPromptText("your name");
		Button btnAlert = new Button("Display");
		btnAlert.setOnAction(e -> new AlertBox().display("Welcome", "Welcome " + inputName.getText()));
		
		layout.getChildren().addAll(inputLabel, inputName, btnAlert);
		
		Scene scene = new Scene(layout, 400, 400);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
