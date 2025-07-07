/*
Clint Scott
07/06/2025
CSD 402
M8 ClintArrayListTest – Find Maximum from User Input

This program defines the ClintArrayListTest class, which:
- Accepts user input of non-negative integers stored in an ArrayList
- Uses 0 as both a valid input and a signal to stop input
- Sends the list to the max() method to find the largest value
- Returns 0 if the list is empty
- Displays the result to the user
- Supports -h or --help flags to explain how the program works
- Handles unexpected input interruptions (e.g., Ctrl+C) gracefully
*/

import java.util.ArrayList;
import java.util.Scanner;

public class ClintArrayListTest {

    /**
     * Finds the largest value in a list of Long values.
     * Returns 0 if the list is empty.
     *
     * @param list ArrayList of Long values
     * @return the largest value, or 0 if the list is empty
     */
    public static Long max(ArrayList<Long> list) {
        if (list.isEmpty()) {
            return 0L;
        }

        long largest = list.get(0);

        for (Long num : list) {
            if (num > largest) {
                largest = num;
            }
        }

        return largest;
    }

    /**
     * Displays a help screen explaining the program usage.
     */
    public static void printHelp() {
        System.out.println("ClintArrayListTest Help");
        System.out.println("------------------------");
        System.out.println("This program collects non-negative integers from the user one at a time.");
        System.out.println("The number 0 stops input and is also included in the list.");
        System.out.println("It returns the largest value entered.");
        System.out.println("Only single, non-negative integers up to " + Long.MAX_VALUE + " are accepted.");
        System.out.println("Usage:");
        System.out.println("  java ClintArrayListTest          Run the program");
        System.out.println("  java ClintArrayListTest -h       Show help screen");
        System.out.println("  java ClintArrayListTest --help   Show help screen");
        System.out.println();
    }

    /**
     * Main method - Collects user input, validates entries, stores them in an ArrayList,
     * and calls the max() method to find and display the largest value.
     */
    public static void main(String[] args) {
        // Show help screen if user passes -h or --help
        if (args.length > 0 && (args[0].equals("-h") || args[0].equals("--help"))) {
            printHelp();
            return;
        }

        Scanner scanner = new Scanner(System.in);
        ArrayList<Long> numbers = new ArrayList<>();
        String input;

        System.out.println("Welcome to ClintArrayListTest");
        System.out.println("This program finds the largest non-negative integer you enter.");
        System.out.println("Enter one non-negative number at a time. Enter 0 to stop.");
        System.out.println();

        try {
            while (true) {
                System.out.print("Enter a number: ");
                input = scanner.nextLine().trim();

                // Validate input as a non-negative integer
                if (!input.matches("\\d+")) {
                    System.out.println("Invalid input. Please enter a single non-negative integer.");
                    continue;
                }

                long value;
                try {
                    value = Long.parseLong(input);
                } catch (NumberFormatException e) {
                    System.out.println("The number you entered is too large. Please enter a number up to " + Long.MAX_VALUE + ".");
                    continue;
                }

                numbers.add(value);

                if (value == 0) {
                    break;
                }
            }

            Long largestValue = max(numbers);
            System.out.println();
            System.out.println("The largest value entered is: " + largestValue);

        } catch (java.util.NoSuchElementException e) {
            // Handle Ctrl+C or similar interruption
            System.out.println();
            System.out.println("The program was interrupted. Exiting...");
        } catch (Exception e) {
            // Catch unexpected exceptions
            System.out.println("\nAn unexpected error occurred: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}