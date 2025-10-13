package lab03;

import com.lab03.dialogs.Dialogs;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class App extends Application {

    private static final String TITLE = "JavaFX Lab 03";

    public static void main(String[] args) {
        launch(args); // Starts JavaFX app
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle(TITLE);

        VBox layout = new VBox(10);
        layout.setPadding(new javafx.geometry.Insets(20));

        Label nameLabel = new Label("Enter your name:");
        TextField nameInput = new TextField();
        Button greetButton = new Button("Greet Me");

        greetButton.setOnAction(e -> {
            String name = nameInput.getText();
            if (name == null || name.trim().isEmpty()) {
                Dialogs.showWarning("Please enter a valid name!");
            } else {
                Dialogs.showWelcome("Welcome, " + name + "!");
            }
        });

        layout.getChildren().addAll(nameLabel, nameInput, greetButton);

        Scene scene = new Scene(layout, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
