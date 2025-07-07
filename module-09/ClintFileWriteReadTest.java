import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Random;

public class ClintFileWriteReadTest {

    // Displays a help screen explaining the program
    public static void printHelp() {
        System.out.println("ClintFileWriteReadTest Help");
        System.out.println("----------------------------");
        System.out.println("This program writes 10 random integers (0–99) to a file named 'data.file'.");
        System.out.println("If the file exists, it appends the new values to the end.");
        System.out.println("After writing, it reads and displays the full contents of the file.");
        System.out.println("Usage:");
        System.out.println("  java ClintFileWriteReadTest       Run the program");
        System.out.println("  java ClintFileWriteReadTest -h    Show help screen");
        System.out.println("  java ClintFileWriteReadTest --help  Show help screen");
        System.out.println();
    }

    /**
     * Main method - Writes random numbers to a file, then reads and displays the file's contents.
     */
    public static void main(String[] args) {
        if (args.length > 0 && (args[0].equals("-h") || args[0].equals("--help"))) {
            printHelp();
            return;
        }

        File file = new File("data.file");
        Random random = new Random();

        System.out.println("Welcome to ClintFileWriteReadTest");
        System.out.println("This program appends 10 random numbers to 'data.file' and displays the full contents.");
        System.out.println();

        try {
            // Write or append 10 random numbers to the file with explicit CRLF line endings
            try (FileWriter writer = new FileWriter(file, true)) {
                for (int i = 0; i < 10; i++) {
                    int number = random.nextInt(100);  // Random number 0–99
                    writer.write(number + " ");
                }
                writer.write("\r\n"); // Add CRLF (Windows-style) line endings
                System.out.println("Successfully wrote 10 random numbers to data.file.");
            } catch (IOException e) {
                System.err.println("Error writing to file: " + e.getMessage());
                return;
            }

            // Read and display contents of the file
            System.out.println("\n--- Contents of data.file ---");
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line.trim());
                }
            } catch (IOException e) {
                System.err.println("Error reading from file: " + e.getMessage());
            }

        } catch (Exception e) {
            // Handle unexpected exceptions
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }
    }
}
