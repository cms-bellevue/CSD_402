/*
Clint Scott
07/06/2025
CSD 402
M9 Program 2 – File Creation, Writing, and Reading Random Integers

This file defines the ClintFileWriteReadTest class which:
- Creates a file named data.file if it does not already exist
- Writes or appends 10 random integers (0–99), separated by spaces
- Closes the file, then reopens it
- Reads and displays all contents of the file
- Supports a -h or --help flag to explain how the program works
- Handles unexpected input interruptions (e.g., Ctrl+C) gracefully
*/

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Random;
import java.util.NoSuchElementException;

public class ClintFileWriteReadTest {

    /**
     * printHelp
     *
     * Displays a help screen explaining the program.
     */
    public static void printHelp() {
        System.out.println("ClintFileWriteReadTest Help");
        System.out.println("----------------------------");
        System.out.println("This program writes 10 random integers (0–99) to a file named 'data.file'.");
        System.out.println("If the file exists, it appends the new values to the end.");
        System.out.println("After writing, it reads and displays the full contents of the file.");
        System.out.println("Usage:");
        System.out.println("  java ClintFileWriteReadTest         Run the program");
        System.out.println("  java ClintFileWriteReadTest -h      Show help screen");
        System.out.println("  java ClintFileWriteReadTest --help  Show help screen");
        System.out.println();
    }

    public static void main(String[] args) {
        // Check for help argument
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
            // Write or append 10 random numbers to the file
            try (FileWriter writer = new FileWriter(file, true)) {
                for (int i = 0; i < 10; i++) {
                    int number = random.nextInt(100);  // Random number 0–99
                    writer.write(number + " ");
                }
                writer.write("\n"); // Add newline after writing the batch
            } catch (IOException e) {
                System.out.println("Error writing to file: " + e.getMessage());
                return;
            } catch (Exception e) {
                System.out.println("Unexpected error while writing: " + e.getMessage());
                return;
            }

            // Read and display contents of the file
            System.out.println("Contents of data.file:");
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line.trim());
                }
            } catch (IOException e) {
                System.out.println("Error reading from file: " + e.getMessage());
            } catch (NoSuchElementException e) {
                System.out.println("Unexpected error while reading: No line found.");
            } catch (Exception e) {
                System.out.println("Unexpected error while reading: " + e.getMessage());
            }

        } catch (NoSuchElementException e) {
            System.out.println("The program was interrupted. Exiting...");
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }
}
