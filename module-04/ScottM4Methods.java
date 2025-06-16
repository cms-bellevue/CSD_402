/*
Clint Scott  
06/15/2025  
CSD 402  
M4 Overloaded Methods – Array Averages

This program (ScottM4Methods) demonstrates Java method overloading by defining
four versions of the average method, each accepting a different primitive
array type:
- short[]
- int[]
- long[]
- double[]

Use -h or --help as a command-line argument to display usage information.
*/

import java.util.Arrays;

public class ScottM4Methods {

    /**
     * Calculates the average of a short array.
     * Sum is accumulated in an int to avoid overflow.
     * Throws IllegalArgumentException if the array is empty.
     */
    public static short average(short[] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Array cannot be empty.");
        }
        int sum = 0;

        for (short num : array) {
            sum += num;
        }

        return (short)(sum / array.length); // Integer division, then cast to short
    }

    /**
     * Calculates the average of an int array.
     * Uses long for sum to protect against int overflow.
     * Throws IllegalArgumentException if the array is empty.
     */
    public static int average(int[] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Array cannot be empty.");
        }
        long sum = 0;

        for (int num : array) {
            sum += num;
        }

        return (int)(sum / array.length); // Long division, result cast to int
    }

    /**
     * Calculates the average of a long array.
     * Long division used directly since long is sufficient for large sums.
     * Throws IllegalArgumentException if the array is empty.
     */
    public static long average(long[] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Array cannot be empty.");
        }
        long sum = 0L;

        for (long num : array) {
            sum += num;
        }

        return sum / array.length;
    }

    /**
     * Calculates the average of a double array.
     * Uses floating-point division for precision.
     * Throws IllegalArgumentException if the array is empty.
     */
    public static double average(double[] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Array cannot be empty.");
        }
        double sum = 0.0;

        for (double num : array) {
            sum += num;
        }

        return sum / array.length;
    }

    /**
     * Main method to test all overloaded average methods.
     * Handles help requests and input validation.
     */
    public static void main(String[] args) {

        // Handle command-line help arguments
        if (args.length == 1 && (args[0].equals("-h") || args[0].equals("--help"))) {
            printHelpMessage();
            return;
        } else if (args.length > 0) {
            System.out.println("Error: Invalid command-line argument.");
            System.out.println("Use -h or --help to see usage information.");
            return;
        }

        // Sample arrays with distinct sizes and types
        short[] shortArray = {10, 20, 30};                           // Size 3
        int[] intArray = {100, 200, 300, 400};                       // Size 4
        long[] longArray = {1000L, 2000L, 3000L, 4000L, 5000L};      // Size 5
        double[] doubleArray = {1.5, 2.5, 3.5, 4.5, 5.5, 6.5};       // Size 6

        // Display short array and its average
        System.out.println("Short Array: " + Arrays.toString(shortArray));
        System.out.println("Average (short): " + average(shortArray));
        System.out.println();

        // Display int array and its average
        System.out.println("Int Array: " + Arrays.toString(intArray));
        System.out.println("Average (int): " + average(intArray));
        System.out.println();

        // Display long array and its average
        System.out.println("Long Array: " + Arrays.toString(longArray));
        System.out.println("Average (long): " + average(longArray));
        System.out.println();

        // Display double array and its average
        System.out.println("Double Array: " + Arrays.toString(doubleArray));
        System.out.println("Average (double): " + average(doubleArray));
        System.out.println();
    }

    /**
     * Prints the help message, detailing program usage.
     * This method is called when the user provides '--help' or '-h' as a command-line argument.
     */
    private static void printHelpMessage() {
        System.out.println("""
            ------------------------------------------------------------------
            ScottM4Methods Program – Usage Help

            This program (ScottM4Methods) demonstrates Java method overloading
            by defining four versions of the average method, each accepting a
            different primitive array type:
            - short[]
            - int[]
            - long[]
            - double[]

            No arguments are required to run the program.

            To display this help message:
            java ScottM4Methods --help
            java ScottM4Methods -h

            Any other command-line argument will result in an error message.
            ------------------------------------------------------------------
            """);
    }
}