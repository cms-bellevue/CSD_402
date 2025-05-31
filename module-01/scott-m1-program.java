/*
Clint Scott
05/31/2025
CSD 402
M1 Program Energy Calculator

This program calculates the energy (in joules) required to heat a given mass of water
from an initial temperature to a final temperature using the formula:

    Q = waterMass * (finalTemperature - initialTemperature) * SPECIFIC_HEAT_CAPACITY

    The program supports two modes of operation:
    1.  Interactive Mode: Guides the user through input prompts.
    2.  Command-Line Interface (CLI) Mode: Accepts all input values directly as arguments.
        Use --help or -h for CLI usage instructions.

    Where:
    - waterMass is the water weight in kilograms (must be a positive value)
    - initialTemperature and finalTemperature are temperatures in Celsius
		(must be between 0°C and 100°C inclusive)
    - finalTemperature must be strictly greater than initialTemperature
		(program focuses on heating only; no cooling calculations)
    - SPECIFIC_HEAT_CAPACITY (4184 J/kg·°C) is the specific heat capacity of liquid water
    - Q is the calculated energy in joules
		(formatted to exactly two decimal places with comma separators)
	- Assumes water remains in the liquid phase (0°C to 100°C).

The joule is a derived unit of energy in the International System of Units. It is equal
to the energy transferred to (or work done on) an object when a force of one newton acts
on that object in the direction of the force's motion through a distance of one metre
(1 newton metre or N·m). It is also the energy dissipated as heat when an electric current
of one ampere passes through a resistance of one ohm for one second. It is named after
the English physicist James Prescott Joule ("Joule", Wikipedia, 2024).
*/

import java.text.DecimalFormat;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class EnergyCalculator {

    // Constants for specific heat capacity and valid temperature range
    private static final double SPECIFIC_HEAT_CAPACITY = 4184;
    private static final double MIN_TEMP = 0.0;
    private static final double MAX_TEMP = 100.0;

    /**
     * Checks if the user input is a 'quit' command.
     *
     * @param input The string input from the user.
     * @return true if the input is "quit" or "q" (case-insensitive), false otherwise.
     */
    private static boolean handleQuit(String input) {
        return input.equalsIgnoreCase("quit") || input.equalsIgnoreCase("q");
    }

    /**
     * Prints a graceful exit message for unexpected program interruptions
     * (e.g., Ctrl+C) and terminates the program.
     */
    private static void exit() {
        System.out.println("\nProgram interrupted. Exiting gracefully.");
        System.exit(0); // Terminate the JVM
    }

    public static void main(String[] args) {
        // Using try-with-resources to ensure the Scanner is automatically closed
        try (Scanner input = new Scanner(System.in)) {
            // Formatter for displaying numeric results (water mass, temperatures, energy)
            // Ensures two decimal places and comma separators for thousands.
            DecimalFormat formatter = new DecimalFormat("#,##0.00");

            // --- Command Line Interface (CLI) Argument Handling ---
            if (args.length == 1 && (args[0].equals("--help") || args[0].equals("-h"))) {
                // If a help flag is provided, print the help message and exit.
                printHelpMessage();
                return;
            } else if (args.length == 3) {
                // If exactly three arguments are provided, attempt direct calculation.
                try {
                    double waterMass = Double.parseDouble(args[0]);
                    double initialTemp = Double.parseDouble(args[1]);
                    double finalTemp = Double.parseDouble(args[2]);

                    // Validate CLI arguments
                    if (waterMass <= 0) {
                        System.out.println("Error: Water mass must be positive.");
                        return; // Exit if validation fails
                    }
                    if (initialTemp < MIN_TEMP || initialTemp > MAX_TEMP ||
                        finalTemp < MIN_TEMP || finalTemp > MAX_TEMP) {
                        System.out.println("Error: Temperatures must be between " + MIN_TEMP + "°C and " + MAX_TEMP + "°C.");
                        return; // Exit if validation fails
                    }
                    if (finalTemp <= initialTemp) {
                        System.out.println("Error: Final temperature must be greater than initial temperature for heating calculations.");
                        return; // Exit if validation fails
                    }

                    // Perform calculation and print result for CLI mode
                    double energy = calculateEnergy(waterMass, initialTemp, finalTemp);
                    System.out.printf("The energy required to heat %s kg of water from %s°C to %s°C is %s joules.%n",
                            formatter.format(waterMass), formatter.format(initialTemp),
                            formatter.format(finalTemp), formatter.format(energy));
                    return; // Exit the program after successful CLI calculation
                } catch (NumberFormatException e) {
                    System.out.println("Error: All command-line arguments for calculation must be valid numeric values.");
                    System.out.println("Usage: java EnergyCalculator <waterMass> <initialTemperature> <finalTemperature>");
                    System.out.println("Use --help or -h for more details.");
                    return; // Exit on invalid number format in CLI args
                }
            } else if (args.length > 0) {
                // If arguments are provided but do not match help or calculation patterns, print usage error.
                System.out.println("Error: Invalid command-line arguments. Use --help or -h for usage details.");
                return; // Exit due to invalid CLI usage
            }

            // --- Interactive Mode ---
            // If no command-line arguments or invalid arguments were provided, proceed to interactive mode.
            printWelcomeMessage();

            while (true) {
                // Prompt for water mass. Returns NaN if user quits or Ctrl+C.
                double waterMass = promptPositiveDouble(input, "Enter the amount of water in kilograms (must be positive): ");
                if (Double.isNaN(waterMass)) break; // Exit loop if user quit or interrupted

                // Prompt for initial temperature. Returns NaN if user quits or Ctrl+C.
                double initialTemp = promptTemperature(input, "Enter the initial temperature (" + MIN_TEMP + "°C to " + MAX_TEMP + "°C): ");
                if (Double.isNaN(initialTemp)) break; // Exit loop if user quit or interrupted

                // Prompt for final temperature. Returns NaN if user quits or Ctrl+C.
                // This method also ensures finalTemp > initialTemp.
                double finalTemp = promptFinalTemperature(input, initialTemp);
                if (Double.isNaN(finalTemp)) break; // Exit loop if user quit or interrupted

                // Calculate and display energy for interactive mode
                double energy = calculateEnergy(waterMass, initialTemp, finalTemp);
                System.out.printf("The energy required to heat %s kg of water from %s°C to %s°C is %s joules.%n",
                        formatter.format(waterMass), formatter.format(initialTemp),
                        formatter.format(finalTemp), formatter.format(energy));
                System.out.println("Energy calculation complete.");

                // Ask if user wants to perform another calculation. Returns false if user quits or says no.
                if (!promptContinue(input)) break; // Exit loop if user doesn't want to continue
            }

        } catch (NoSuchElementException e) {
            // Catching NoSuchElementException specifically at the main level for direct Scanner issues
            exit(); // Handle program interruption gracefully
        } catch (Exception e) {
            // Catch any other unexpected exceptions that might occur during program execution.
            System.out.println("An unexpected error occurred: " + e.getMessage());
            System.out.println("Exiting program.");
        }
        // Scanner is automatically closed by try-with-resources.
    }

    /**
     * Prints the welcome message for the interactive mode of the Energy Calculator.
     */
    private static void printWelcomeMessage() {
        System.out.println("""
            ---------------------------------------------------------
            Welcome to the Energy Calculator

            This program calculates the energy (in joules) needed
            to heat water from an initial to a final temperature.

            Final temperature must be higher than initial (0°C to 100°C).

            Type 'quit' or 'q' to exit at any time.
            ---------------------------------------------------------
            """);
    }

    /**
     * Prints the help message detailing command-line usage and program arguments.
     */
    private static void printHelpMessage() {
        System.out.println("""
            ---------------------------------------------------------
            Energy Calculator Usage:

            This program calculates the energy (in joules) needed to heat
            water from an initial to a final temperature.

            To run the program in interactive mode:
            java EnergyCalculator

            To run the program from the command line with input values:
            java EnergyCalculator <waterMass> <initialTemperature> <finalTemperature>

            Example:
            java EnergyCalculator 5 20 100
            This will calculate the energy required to heat 5 kg of water
            from 20°C to 100°C and then exit.

            Arguments:
            - <waterMass>: The mass of water in kilograms (must be positive).
            - <initialTemperature>: The initial temperature in Celsius (0°C to 100°C).
            - <finalTemperature>: The final temperature in Celsius (must be greater than initial).

            To display this help message:
            java EnergyCalculator --help
            java EnergyCalculator -h

            To exit the program at any time during interactive mode, type 'quit' or 'q'.
            ---------------------------------------------------------
            """);
    }

    /**
     * Prompts the user for a positive double value. Handles non-numeric input,
     * non-positive values, and 'quit' commands.
     *
     * @param input  The Scanner object for user input.
     * @param prompt The message to display to the user.
     * @return A valid positive double, or Double.NaN if the user enters 'quit'/'q' or Ctrl+C.
     */
    private static double promptPositiveDouble(Scanner input, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                String userInput = input.nextLine().trim();
                if (handleQuit(userInput)) return Double.NaN; // Return NaN to signal exit

                double value = Double.parseDouble(userInput);
                if (value > 0) return value; // Valid positive input
                System.out.println("Error: Value must be positive.");
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid numeric value.");
            } catch (NoSuchElementException e) {
                exit(); // Handle program interruption (Ctrl+C)
                return Double.NaN; // Should not be reached due to exit()
            }
        }
    }

    /**
     * Prompts the user for a temperature within the defined MIN_TEMP and MAX_TEMP range.
     * Handles non-numeric input and 'quit' commands.
     *
     * @param input  The Scanner object for user input.
     * @param prompt The message to display to the user.
     * @return A valid temperature (double), or Double.NaN if the user enters 'quit'/'q' or Ctrl+C.
     */
    private static double promptTemperature(Scanner input, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                String userInput = input.nextLine().trim();
                if (handleQuit(userInput)) return Double.NaN; // Return NaN to signal exit

                double temp = Double.parseDouble(userInput);
                if (temp >= MIN_TEMP && temp <= MAX_TEMP) return temp; // Valid temperature within range
                System.out.println("Error: Temperature must be between " + MIN_TEMP + "°C and " + MAX_TEMP + "°C.");
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid numeric value.");
            } catch (NoSuchElementException e) {
                exit(); // Handle program interruption (Ctrl+C)
                return Double.NaN; // Should not be reached due to exit()
            }
        }
    }

    /**
     * Prompts the user for the final temperature. Validates that it's within the
     * defined range (MIN_TEMP to MAX_TEMP) and strictly greater than the initial temperature.
     * Handles non-numeric input and 'quit' commands.
     *
     * @param input      The Scanner object for user input.
     * @param initialTemp The previously entered initial temperature.
     * @return A valid final temperature (double), or Double.NaN if the user enters 'quit'/'q' or Ctrl+C.
     */
    private static double promptFinalTemperature(Scanner input, double initialTemp) {
        while (true) {
            System.out.print("Enter the final temperature (" + MIN_TEMP + "°C to " + MAX_TEMP + "°C, must be > " + initialTemp + "°C): ");
            try {
                String userInput = input.nextLine().trim();
                if (handleQuit(userInput)) return Double.NaN; // Return NaN to signal exit

                double temp = Double.parseDouble(userInput);
                if (temp > initialTemp && temp <= MAX_TEMP) return temp; // Valid final temperature

                // Provide specific error messages
                if (temp <= initialTemp) {
                    System.out.println("Error: Final temperature must be greater than " + initialTemp + "°C.");
                } else { // This implies temp > MAX_TEMP or temp < MIN_TEMP
                    System.out.println("Error: Temperature must be between " + MIN_TEMP + "°C and " + MAX_TEMP + "°C.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid numeric value.");
            } catch (NoSuchElementException e) {
                exit(); // Handle program interruption (Ctrl+C)
                return Double.NaN; // Should not be reached due to exit()
            }
        }
    }

    /**
     * Prompts the user to continue or exit the program. Handles 'yes'/'y', 'no'/'n',
     * and 'quit' commands.
     *
     * @param input The Scanner object for user input.
     * @return true if the user wants to continue, false if they want to stop or quit.
     */
    private static boolean promptContinue(Scanner input) {
        while (true) {
            System.out.print("Would you like to perform another calculation? (yes/no): ");
            try {
                String response = input.nextLine().trim();
                if (handleQuit(response)) return false; // User quit, so don't continue
                if (response.matches("(?i)y|yes")) return true; // User wants to continue
                if (response.matches("(?i)n|no")) return false; // User wants to stop
                System.out.println("Error: Please enter 'yes', 'y', 'no', or 'n'.");
            } catch (NoSuchElementException e) {
                exit(); // Handle program interruption (Ctrl+C)
                return false; // Should not be reached due to exit()
            }
        }
    }

    /**
     * Calculates the energy required to heat water.
     *
     * @param waterMass    The mass of the water in kilograms.
     * @param initialTemp  The initial temperature in Celsius.
     * @param finalTemp    The final temperature in Celsius (must be greater than initialTemp).
     * @return The calculated energy in joules.
     */
    private static double calculateEnergy(double waterMass, double initialTemp, double finalTemp) {
        // No Math.abs needed here as finalTemp is guaranteed to be > initialTemp by validation.
        return waterMass * (finalTemp - initialTemp) * SPECIFIC_HEAT_CAPACITY;
    }
}