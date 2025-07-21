/*
Clint Scott
CSD 402
M11 HBoxHeavyMetalDrummers – Demonstrates JavaFX HBox layout with styled buttons
07/20/2025

This program:
- Displays an HBox layout with six heavy metal drummer buttons
- Demonstrates key HBox features: spacing, alignment, padding, fillHeight
- Uses CSS-like styling to create a dark themed layout with bold button design
- Supports -h or --help flags to explain how the program works
- Includes error handling when launching JavaFX
*/

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class HBoxHeavyMetalDrummers extends Application {
    @Override
    public void start(Stage stage) {
        // Array of drummer names and their main bands for button labels
        String[][] drummersAndBands = {
            {"Vinnie Paul", "Pantera"},
            {"Joey Jordison", "Slipknot"},
            {"Charlie Benante", "Anthrax"},
            {"Mike Portnoy", "Dream Theater"},
            {"Dave Lombardo", "Slayer"},
            {"Bill Ward", "Black Sabbath"}
        };

        // Create button array to match drummer array
        Button[] drummerButtons = new Button[drummersAndBands.length];

        for (int i = 0; i < drummersAndBands.length; i++) {
            String drummerName = drummersAndBands[i][0];
            String bandName = drummersAndBands[i][1];
            
            // Create Text nodes for drummer name and band name
            Text nameText = new Text(drummerName);
            // Apply common text styling to the name
            nameText.setStyle("-fx-fill: white; -fx-font-size: 15px; -fx-font-weight: bold;");

            Text bandText = new Text(bandName);
            // Apply italic style and common text styling to the band name
            bandText.setStyle("-fx-fill: white; -fx-font-size: 15px; -fx-font-weight: normal; -fx-font-style: italic;");

            // Create a VBox to hold the two Text nodes vertically
            VBox content = new VBox(0, nameText, bandText); // 0 spacing between text nodes
            content.setAlignment(Pos.CENTER); // Center align the text within the VBox

            // Create the button without text, and set the VBox as its graphic
            Button button = new Button();
            button.setGraphic(content);

            // Style buttons with dark background, gold border, bold white text
            button.setStyle("-fx-background-color: #B22222; " +
                            "-fx-border-color: #DAA520; -fx-border-width: 2px; " +
                            "-fx-border-radius: 5px; -fx-pref-width: 150px;");

            if (i % 2 != 0) {
                button.setPrefHeight(60);
            } else {
                button.setPrefHeight(60);
            }

            drummerButtons[i] = button;
        }

        // HBox layout pane to arrange buttons horizontally
        HBox hbox = new HBox(15); // Spacing controls horizontal gaps between buttons
        hbox.getChildren().addAll(drummerButtons);

        hbox.setAlignment(Pos.CENTER); // Center-align child nodes within the HBox
        hbox.setPadding(new Insets(30, 20, 30, 20)); // Padding adds space around HBox edges
        hbox.setFillHeight(true); // Child nodes stretch to fill full vertical space of HBox

        // Style the HBox background, border, and shadow
        hbox.setStyle("-fx-background-color: #202020; " +
                      "-fx-border-color: #404040; -fx-border-width: 4px; " +
                      "-fx-border-radius: 12px; " +
                      "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.8), 12, 0, 0, 0);");

        // Create scene and assign to stage
        Scene scene = new Scene(hbox, 1000, 200);
        stage.setTitle("🤘 Top 6 Heavy Metal Drummers 🤘");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        // Handle -h or --help flag
        for (String arg : args) {
            if (arg.equals("-h") || arg.equals("--help")) {
                System.out.println("This JavaFX program uses an HBox layout to display");
                System.out.println("buttons labeled with famous heavy metal drummers.");
                System.out.println("Each button is styled and spaced horizontally.");
                System.out.println("Run the program without arguments to launch the GUI.");
                return;
            }
        }

        // Launch JavaFX application with error handling
        try {
            launch(args);
        } catch (Exception e) {
            System.err.println("Error launching JavaFX: " + e.getMessage());
        }
    }
}