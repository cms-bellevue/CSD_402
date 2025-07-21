/*
Clint Scott
CSD 402
M11 VBoxHeavyMetalGuitarists – Demonstrates JavaFX VBox layout with styled buttons
07/20/2025

This program:
- Displays a VBox layout with six heavy metal guitarist buttons
- Demonstrates key VBox features: spacing, alignment, padding, fillWidth
- Applies CSS-like styling to enhance visual presentation
- Supports -h or --help flags to explain how the program works
- Includes error handling when launching JavaFX
*/

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text; // Import Text for rich text formatting
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class VBoxHeavyMetalGuitarists extends Application {
    @Override
    public void start(Stage stage) {
        // Array of guitarist names and their main bands for button labels
        String[][] guitaristsAndBands = {
            {"Dimebag Darrell", "Pantera"},
            {"Tony Iommi", "Black Sabbath"},
            {"Zakk Wylde", "Ozzy Osbourne"},
            {"Randy Rhoads", "Ozzy Osbourne"},
            {"Marty Friedman", "Megadeth"},
            {"Kirk Hammett", "Metallica"}
        };

        // Create button array to match guitarist array
        Button[] guitaristButtons = new Button[guitaristsAndBands.length];

        for (int i = 0; i < guitaristsAndBands.length; i++) {
            String guitaristName = guitaristsAndBands[i][0];
            String bandName = guitaristsAndBands[i][1];
            
            // Create Text nodes for guitarist name and band name
            Text nameText = new Text(guitaristName);
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

            // Style each button with dark background, gold border, and bold white text
            // Note: Text styling is now applied directly to Text nodes, not the button's -fx-text-fill
            button.setStyle("-fx-background-color: #B22222; " +
                            "-fx-border-color: #DAA520; -fx-border-width: 2px; " +
                            "-fx-border-radius: 5px;");

            // Demonstrate fillWidth and size variation: make even-indexed buttons narrower
            // Adjusted preferred height to accommodate two lines of text
            if (i % 2 == 0) {
                button.setPrefWidth(150);
                button.setPrefHeight(60); // Increased height for multiline text
            } else {
                button.setPrefWidth(200);
                button.setPrefHeight(60); // Increased height for multiline text
            }

            guitaristButtons[i] = button;
        }

        // VBox layout pane to arrange buttons vertically
        VBox vbox = new VBox(15); // VBox spacing controls vertical gaps between buttons
        vbox.getChildren().addAll(guitaristButtons);

        vbox.setAlignment(Pos.CENTER); // Align child nodes to center of VBox horizontally
        vbox.setPadding(new Insets(20, 30, 20, 30)); // Padding adds space around VBox edges
        vbox.setFillWidth(true); // Child nodes (buttons) expand to fill VBox width

        // Style the VBox background, border, and drop shadow
        vbox.setStyle("-fx-background-color: #202020; " +
                      "-fx-border-color: #404040; -fx-border-width: 4px; " +
                      "-fx-border-radius: 12px; " +
                      "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.8), 12, 0, 0, 0);");

        // Create scene and assign it to the stage
        Scene scene = new Scene(vbox, 400, 500);
        stage.setTitle("🎸 Top 6 Heavy Metal Guitarists 🎸");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        // Display help text if -h or --help is passed
        for (String arg : args) {
            if (arg.equals("-h") || arg.equals("--help")) {
                System.out.println("This JavaFX program uses a VBox layout to display");
                System.out.println("buttons labeled with famous heavy metal guitarists.");
                System.out.println("Each button is styled and spaced vertically.");
                System.out.println("Run the program without arguments to launch the GUI.");
                return;
            }
        }

        // Launch the JavaFX application with error handling
        try {
            launch(args);
        } catch (Exception e) {
            System.err.println("Error launching JavaFX: " + e.getMessage());
        }
    }
}