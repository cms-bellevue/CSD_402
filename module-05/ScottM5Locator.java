/*
Clint Scott
06/22/2025
CSD 402
M5 Locate Largest and Smallest Elements – 2D Arrays

This program (ScottM5Locator) defines overloaded methods to find the position
of the largest and smallest elements within two-dimensional arrays.

Four methods are implemented:
- locateLargest(double[][])
- locateLargest(int[][])
- locateSmallest(double[][])
- locateSmallest(int[][])

Each method returns a one-dimensional int array containing the row and column
of the located element.

Use -h or --help as a command-line argument to display usage information.
*/

import java.util.Arrays;

public class ScottM5Locator {

    /**
     * Main method to demonstrate and test all four location methods.
     * Handles help requests and displays test output.
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

        // Sample int 2D array
        int[][] intArray = {
            {29, 72, 16},
            {55, 99, 64},
            {20, 8, 35}
        };

        // Sample double 2D array
        double[][] doubleArray = {
            {1.5, 3.2, 9.8},
            {0.2, 7.7, 5.5},
            {6.3, 4.1, 8.6}
        };

        // Locate and display results for int array
        int[] largestInt = locateLargest(intArray);
        int[] smallestInt = locateSmallest(intArray);
        System.out.println("Int Array:");
        print2DArray(intArray);
        System.out.println("Largest int at: [" + largestInt[0] + "][" + largestInt[1] + "]");
        System.out.println("Smallest int at: [" + smallestInt[0] + "][" + smallestInt[1] + "]");
        System.out.println();

        // Locate and display results for double array
        int[] largestDouble = locateLargest(doubleArray);
        int[] smallestDouble = locateSmallest(doubleArray);
        System.out.println("Double Array:");
        print2DArray(doubleArray);
        System.out.println("Largest double at: [" + largestDouble[0] + "][" + largestDouble[1] + "]");
        System.out.println("Smallest double at: [" + smallestDouble[0] + "][" + smallestDouble[1] + "]");
        System.out.println();
    }

    /**
     * Finds the position of the largest value in a 2D array of doubles.
     * @param arrayParam the 2D double array to scan
     * @return int array with [row, column] of the largest element
     */
    public static int[] locateLargest(double[][] arrayParam) {
        int[] position = new int[2];
        double max = arrayParam[0][0];

        for (int row = 0; row < arrayParam.length; row++) {
            for (int col = 0; col < arrayParam[row].length; col++) {
                if (arrayParam[row][col] > max) {
                    max = arrayParam[row][col];
                    position[0] = row;
                    position[1] = col;
                }
            }
        }

        return position;
    }

    /**
     * Finds the position of the largest value in a 2D array of ints.
     * @param arrayParam the 2D int array to scan
     * @return int array with [row, column] of the largest element
     */
    public static int[] locateLargest(int[][] arrayParam) {
        int[] position = new int[2];
        int max = arrayParam[0][0];

        for (int row = 0; row < arrayParam.length; row++) {
            for (int col = 0; col < arrayParam[row].length; col++) {
                if (arrayParam[row][col] > max) {
                    max = arrayParam[row][col];
                    position[0] = row;
                    position[1] = col;
                }
            }
        }

        return position;
    }

    /**
     * Finds the position of the smallest value in a 2D array of doubles.
     * @param arrayParam the 2D double array to scan
     * @return int array with [row, column] of the smallest element
     */
    public static int[] locateSmallest(double[][] arrayParam) {
        int[] position = new int[2];
        double min = arrayParam[0][0];

        for (int row = 0; row < arrayParam.length; row++) {
            for (int col = 0; col < arrayParam[row].length; col++) {
                if (arrayParam[row][col] < min) {
                    min = arrayParam[row][col];
                    position[0] = row;
                    position[1] = col;
                }
            }
        }

        return position;
    }

    /**
     * Finds the position of the smallest value in a 2D array of ints.
     * @param arrayParam the 2D int array to scan
     * @return int array with [row, column] of the smallest element
     */
    public static int[] locateSmallest(int[][] arrayParam) {
        int[] position = new int[2];
        int min = arrayParam[0][0];

        for (int row = 0; row < arrayParam.length; row++) {
            for (int col = 0; col < arrayParam[row].length; col++) {
                if (arrayParam[row][col] < min) {
                    min = arrayParam[row][col];
                    position[0] = row;
                    position[1] = col;
                }
            }
        }

        return position;
    }

    /**
     * Prints a 2D int array in matrix format.
     * @param array the int[][] to print
     */
    private static void print2DArray(int[][] array) {
        for (int[] row : array) {
            System.out.println(Arrays.toString(row));
        }
    }

    /**
     * Prints a 2D double array in matrix format.
     * @param array the double[][] to print
     */
    private static void print2DArray(double[][] array) {
        for (double[] row : array) {
            System.out.println(Arrays.toString(row));
        }
    }

    /**
     * Prints the help message, detailing program usage.
     */
    private static void printHelpMessage() {
        System.out.println("""
            ------------------------------------------------------------------
            ScottM5Locator Program – Usage Help

            This program defines overloaded methods to find the row and column
            of the largest or smallest values in 2D arrays of either int or double.

            Four methods:
            - locateLargest(double[][])
            - locateLargest(int[][])
            - locateSmallest(double[][])
            - locateSmallest(int[][])

            No arguments are required to run the program.

            To display this help message:
            java ScottM5Locator --help
            java ScottM5Locator -h

            Any other command-line argument will result in an error message.
            ------------------------------------------------------------------
            """);
    }
}
