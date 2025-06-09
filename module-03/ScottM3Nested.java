/*
Clint Scott
06/08/2025
CSD 402
M3 Nested For Loops

This program generates and displays a unique numerical pattern using nested for loops.
The pattern consists of rows of powers of two, increasing to a central value
and then decreasing symmetrically. An '@' symbol is appended to the end of each row.

The output is precisely formatted for visual alignment:
- Peak numbers are vertically centered.
- All '@' symbols are perfectly right-aligned.

This program also supports a command-line --help or -h option to display usage instructions.
*/

public class ScottM3Nested {

    public static void main(String[] args) {
        // Handle command-line arguments for help or invalid usage.
        if (args.length == 1 && (args[0].equals("--help") || args[0].equals("-h"))) {
            printHelpMessage();
            return;
        } else if (args.length > 0) {
            System.out.println("Error: Invalid command-line arguments. Use --help or -h for usage details.");
            return;
        }

        // Define the number of rows for the pyramid pattern.
        int rows = 7;

        // Calculate the largest number (e.g., 2^(rows-1)) and its string width for formatting.
        int maxNumber = (int) Math.pow(2, rows - 1);
        int maxWidth = String.valueOf(maxNumber).length();

        // Calculate the total width of the longest numerical line to ensure proper centering and '@' alignment.
        int totalNums = rows * 2 - 1;
        int lineWidth = totalNums * (maxWidth + 1) - 1;

        // Iterate through each row to build and print the pattern.
        for (int i = 1; i <= rows; i++) {
            StringBuilder line = new StringBuilder();

            // Calculate current row's number count and width.
            int numCount = i * 2 - 1;
            int currentLineWidth = numCount * (maxWidth + 1) - 1;

            // Add leading spaces to center the numerical pattern.
            int padding = (lineWidth - currentLineWidth) / 2;
            line.append(" ".repeat(padding));

            // Build the left side (increasing powers of 2).
            for (int j = 0; j < i; j++) {
                int num = (int) Math.pow(2, j);
                line.append(String.format("%" + maxWidth + "d ", num));
            }

            // Build the right side (decreasing powers of 2).
            for (int j = i - 2; j >= 0; j--) {
                int num = (int) Math.pow(2, j);
                line.append(String.format("%" + maxWidth + "d ", num));
            }

            // Prepare the line for printing: trim trailing space and add alignment for '@'.
            String numberLine = line.toString().stripTrailing();
            int totalLineLength = numberLine.length();

            // Position the '@' symbol for right alignment.
            int atColumn = lineWidth + 3;
            int spacesToAt = atColumn - totalLineLength;
            numberLine += " ".repeat(spacesToAt) + "@";

            // Print the completed line.
            System.out.println(numberLine);
        }
    }

    /**
     * Prints the help message, detailing program usage.
     * This method is called when the user provides '--help' or '-h' as a command-line argument.
     */
    private static void printHelpMessage() {
        System.out.println("""
            -----------------------------------------------------------------
            Power Pyramid Aligned Pattern Generator Usage:

            This program generates a symmetrical numerical pattern using powers of two
            and aligns an '@' symbol to the right of each row.

            To run the program and display the pattern:
            java ScottM3Nested.java

            To display this help message:
            java ScottM3Nested.java --help
            java ScottM3Nested.java -h

            There are no other command-line arguments to modify the pattern directly.
            -----------------------------------------------------------------
            """);
    }
}