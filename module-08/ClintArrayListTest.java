/*
Clint Scott
07/06/2025
CSD 402
M8 ClintArrayListTest – Find Maximum from User Input

This file defines the ClintArrayListTest class which:
- Accepts user input of integer values stored in an ArrayList
- Includes the final value 0 to stop input and include in the list
- Sends the list to a method called max()
- Returns the largest value in the list
- Returns 0 if the list is empty
- Displays the result to the user
- Supports a -h or --help flag to explain how the program works
- Handles unexpected input interruptions (e.g. Ctrl+C) gracefully
*/

import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;

public class ClintArrayListTest {

    /**
     * max
     *
     * This method receives an ArrayList of Integer values.
     * It returns the largest value in the list.
     * If the list is empty, it returns 0.
     *
     * @param list ArrayList of Integer values
     * @return Integer representing the largest value in the list, or 0 if the list is empty
     */
    public static Integer max(ArrayList<Integer> list) {
        // Return 0 if the list is empty
        if (list.isEmpty()) {
            return 0;
        }

        // Initialize the largest value using the first item in the list
        int largest = list.get(0);

        // Loop through the list and find the largest value
        for (Integer num : list) {
            if (num > largest) {
                largest = num;
            }
        }

        return largest;
    }

    /**
     * printHelp
     *
     * Displays a help screen explaining the program.
     */
    public static void printHelp() {
        System.out.println("ClintArrayListTest Help");
        System.out.println("------------------------");
        System.out.println("This program collects positive integers from the user one at a time.");
        System.out.println("The number 0 stops input and is also included in the list.");
        System.out.println("It returns the largest value entered.");
        System.out.println("Only single, non-negative integers are accepted.");
        System.out.println("Usage:");
        System.out.println("  java ClintArrayListTest         Run the program");
        System.out.println("  java ClintArrayListTest -h      Show help screen");
        System.out.println("  java ClintArrayListTest --help  Show help screen");
        System.out.println();
    }

    /**
     * main
     *
     * This method collects user input, stores it in an ArrayList, and calls the max() method.
     * It forces valid single-entry, non-negative integers and rejects invalid input.
     * Handles unexpected input interruptions like Ctrl+C gracefully.
     */
    public static void main(String[] args) {
        // Check for help argument
        if (args.length > 0 && (args[0].equals("-h") || args[0].equals("--help"))) {
            printHelp();
            return;
        }

        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> numbers = new ArrayList<>();
        String input;

        System.out.println("Welcome to ClintArrayListTest");
        System.out.println("This program finds the largest integer you enter.");
        System.out.println("Enter one non-negative number at a time. Enter 0 to stop.");
        System.out.println();

        try {
            // Collect user input until 0 is entered (0 is also stored)
            while (true) {
                System.out.print("Enter a number: ");
                input = scanner.nextLine().trim();

                // Check if input is a single valid non-negative integer
                if (!input.matches("\\d+")) {
                    System.out.println("Invalid input. Please enter a single non-negative integer.");
                    continue;
                }

                int value = Integer.parseInt(input);
                numbers.add(value);

                if (value == 0) {
                    break;
                }
            }

            // Call the max method and display the result
            Integer largestValue = max(numbers);
            System.out.println("The largest value entered is: " + largestValue);

        } catch (java.util.NoSuchElementException e) {
            // Handles unexpected interruptions like Ctrl+C
            System.out.println("\nThe program was interrupted. Exiting...");
        } catch (Exception e) {
            // General exception handler for unexpected errors
            System.out.println("\nAn unexpected error occurred: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
