import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import java.util.Random;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.geometry.Pos;

public class Main extends Application {
    private String password = "";

    @Override
    public void start(Stage stage) {
        Label label1 = new Label("Enter password length here:");
        label1.getStyleClass().add("label");

        TextField lengthInput = new TextField();
        lengthInput.setPromptText("Enter length here");
        lengthInput.getStyleClass().add("length-label");

        Button genButton = new Button("Generate");
        genButton.getStyleClass().add("generate-button"); // Apply button style

        TextArea passOutput = new TextArea();
        passOutput.setPrefRowCount(1);
        passOutput.getStyleClass().add("pass-output");

        genButton.setOnAction(e -> {
            try {
                int passLength = Integer.parseInt(lengthInput.getText());
                password = generate(passLength);
                passOutput.setText(password);
            } catch (NumberFormatException ex) {
                passOutput.setText("Please enter a valid number."); // Handle invalid input
            }
        });

        VBox vbox = new VBox(10); // 10px spacing
        vbox.getChildren().addAll(label1, lengthInput, genButton, passOutput);
        vbox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vbox, 400, 200);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm()); // Load CSS stylesheet

        vbox.getStyleClass().add("root"); // Apply style class to VBox

        stage.setTitle("Password Generator");
        stage.setScene(scene);
        stage.show();
    }

    public static String generate(int length) {
        Random pick = new Random();
        StringBuilder returnPass = new StringBuilder(); // Use StringBuilder for efficiency
        for (int i = 0; i < length; i++) {
            int pickAscii;
            do {
                pickAscii = pick.nextInt(33, 126); // Generate a random ASCII code
            } while (pickAscii == 47 || pickAscii == 92 || pickAscii == 46 || pickAscii == 123 ||
                    pickAscii == 125 || pickAscii == 91 || pickAscii == 93 || pickAscii == 40 ||
                    pickAscii == 41 || pickAscii == 124); // Exclude certain characters

            char digit = (char) pickAscii;
            returnPass.append(digit); // Use append for efficiency
        }
        return returnPass.toString();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
