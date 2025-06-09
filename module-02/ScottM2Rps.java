/*
Clint Scott
06/08/2025
CSD 402
M2 Rock-Paper-Scissors

This program simulates a classic game of Rock-Paper-Scissors.
The computer randomly selects Rock, Paper, or Scissors, and then
prompts the user to make their selection. The game continues to play
round after round until the user explicitly types 'quit' or 'q'.

For each round, the program displays the choices of both the computer
and the user, along with the game's outcome.

The program also keeps a running tally of user wins, losses, and ties.
When the user chooses to exit, it displays the final game statistics
in a clear, readable format, indicating who won more rounds overall.

The game rules are:
- Rock (1) beats Scissors (3)
- Paper (2) beats Rock (1)
- Scissors (3) beats Paper (2)
- If both players choose the same, it's a tie.
*/

import java.security.SecureRandom;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ScottM3Nested {

    // Constants for game choices
    private static final int ROCK = 1;
    private static final int PAPER = 2;
    private static final int SCISSORS = 3;

    // SecureRandom for generating unpredictable computer choices
    private static final SecureRandom random = new SecureRandom();

    // Game statistics tallies
    private static int userWins = 0;
    private static int computerWins = 0;
    private static int ties = 0;

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

            // --- Command Line Interface (CLI) Argument Handling ---
            if (args.length == 1 && (args[0].equals("--help") || args[0].equals("-h"))) {
                // If a help flag is provided, print the help message and exit.
                printHelpMessage();
                return;
            } else if (args.length > 0) {
                // If any other arguments are provided, it's considered invalid CLI usage for this program.
                System.out.println("Error: Invalid command-line arguments. Use --help or -h for usage details.");
                return; // Exit due to invalid CLI usage
            }

            // --- Interactive Mode ---
            // If no command-line arguments or invalid arguments were provided, proceed to interactive mode.
            printWelcomeMessage();

            // Game loop continues until the user explicitly quits
            while (true) {
                // Generate computer's choice
                int computerChoice = generateComputerChoice();

                // Prompt user for their choice. Returns -1 if the user quits. Program exits on Ctrl+C.
                int userChoice = promptUserChoice(input, "Enter your choice (1 for Rock, 2 for Paper, 3 for Scissors): ");
                if (userChoice == -1) {
                    break; // Exit game loop if user quit
                }

                // Display selections and determine/update results
                displaySelections(computerChoice, userChoice);
                determineWinner(computerChoice, userChoice); // This method updates the tallies
                System.out.println("Game round complete. Type 'quit' or 'q' to exit.");
            }

            // Display final statistics before exiting the program
            displayFinalStatistics();

        } catch (NoSuchElementException e) {
            // Catching NoSuchElementException specifically at the main level for direct Scanner issues
            exit(); // Handle program interruption gracefully (e.g., Ctrl+C)
        } catch (Exception e) {
            // Catch any other unexpected exceptions that might occur during program execution.
            System.out.println("An unexpected error occurred: " + e.getMessage());
            System.out.println("Exiting program.");
        }
        // Scanner is automatically closed by try-with-resources.
    }

    /**
     * Prints the welcome message for the interactive mode of the Rock-Paper-Scissors game.
     */
    private static void printWelcomeMessage() {
        System.out.println("""
            -----------------------------------------------------------------
            Welcome to the Rock-Paper-Scissors Simulator!

            Choose: 1 for Rock, 2 for Paper, 3 for Scissors.

            The game continues until you type 'quit' or 'q'.
            -----------------------------------------------------------------""");
    }

    /**
     * Prints the help message detailing command-line usage for the game.
     */
    private static void printHelpMessage() {
        System.out.println("""
            -----------------------------------------------------------------
            Rock-Paper-Scissors Usage:

            This program allows you to play a game of Rock-Paper-Scissors
            against the computer.

            To run the program in interactive mode:
            java ScottM3Nested.java

            To display this help message:
            java ScottM3Nested.java --help
            java ScottM3Nested.java -h

            There are no command-line arguments for playing a game directly.

            To exit the program at any time during interactive mode, type 'quit' or 'q'.
            -----------------------------------------------------------------
            """);
    }

    /**
     * Generates a random choice for the computer (1, 2, or 3).
     *
     * @return An integer representing the computer's choice: 1 (Rock), 2 (Paper), or 3 (Scissors).
     */
    private static int generateComputerChoice() {
        // Generates a number between 0 (inclusive) and 3 (exclusive), then adds 1
        return random.nextInt(3) + 1;
    }

    /**
     * Prompts the user to enter their choice for Rock, Paper, or Scissors.
     * Handles non-numeric input, out-of-range values, and 'quit' commands.
     *
     * @param input The Scanner object for user input.
     * @param prompt The message to display to the user.
     * @return A valid user choice (1, 2, or 3), or -1 if the user enters 'quit'/'q' or Ctrl+C.
     */
    private static int promptUserChoice(Scanner input, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                String userInput = input.nextLine().trim();
                if (handleQuit(userInput)) return -1; // Return -1 to signal exit

                int choice = Integer.parseInt(userInput);
                if (choice >= ROCK && choice <= SCISSORS) {
                    return choice; // Valid input
                } else {
                    System.out.println("Error: Please enter 1 for Rock, 2 for Paper, or 3 for Scissors.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid number (1, 2, or 3).");
            } catch (NoSuchElementException e) {
                exit(); // Handle program interruption (Ctrl+C)
                return -1; // Should not be reached due to exit()
            }
        }
    }

    /**
     * Converts a numeric choice (1, 2, or 3) into its corresponding string representation.
     *
     * @param choice The integer choice (1 for Rock, 2 for Paper, 3 for Scissors).
     * @return The string representation of the choice (e.g., "Rock", "Paper", "Scissors").
     */
    private static String getChoiceString(int choice) {
        return switch (choice) {
            case ROCK -> "Rock";
            case PAPER -> "Paper";
            case SCISSORS -> "Scissors";
            default -> "Unknown"; // Should not happen with proper validation
        };
    }

    /**
     * Displays both the computer's and the user's selections.
     *
     * @param computerChoice The computer's choice (1, 2, or 3).
     * @param userChoice     The user's choice (1, 2, or 3).
     */
    private static void displaySelections(int computerChoice, int userChoice) {
        System.out.println("Computer chose: " + getChoiceString(computerChoice));
        System.out.println("You chose: " + getChoiceString(userChoice));
    }

    /**
     * Determines the winner of the Rock-Paper-Scissors game, prints the result,
     * and updates the win/loss/tie tallies.
     *
     * @param computerChoice The computer's choice (1, 2, or 3).
     * @param userChoice     The user's choice (1, 2, or 3).
     */
    private static void determineWinner(int computerChoice, int userChoice) {
        if (computerChoice == userChoice) {
            System.out.println("It's a tie!");
            ties++; // Increment tie tally
        } else if ((userChoice == ROCK && computerChoice == SCISSORS) ||
                   (userChoice == PAPER && computerChoice == ROCK) ||
                   (userChoice == SCISSORS && computerChoice == PAPER)) {
            System.out.println("You win!");
            userWins++; // Increment user win tally
        } else {
            System.out.println("Computer wins!");
            computerWins++; // Increment computer win (user loss) tally
        }
    }

    /**
     * Calculates the Greatest Common Divisor (GCD) of two numbers using the Euclidean algorithm.
     * Used for simplifying ratios.
     *
     * @param a The first number.
     * @param b The second number.
     * @return The GCD of a and b.
     */
    private static int findGCD(int a, int b) {
        if (b == 0) {
            return a;
        }
        return findGCD(b, a % b);
    }

    /**
     * Displays the final game statistics, including total wins, losses, and ties,
     * and a final win/loss ratio for the user, clearly stating who won more overall.
     */
    private static void displayFinalStatistics() {
        int totalGames = userWins + computerWins + ties;
        System.out.println("\n-----------------------------------------------------------------");
        System.out.println("Game Over! Here are the final statistics...");
        System.out.println("Total Games Played: " + totalGames);
        System.out.println("Your Wins: " + userWins);
        System.out.println("Computer Wins: " + computerWins);
        System.out.println("Ties: " + ties);

        if (totalGames > 0) {
            String ratioMessage;
            if (userWins > 0 || computerWins > 0) { // Only calculate ratio if there were actual wins/losses
                int winningPlayerWins = Math.max(userWins, computerWins);
                int losingPlayerWins = Math.min(userWins, computerWins);

                // Handle cases where one player has 0 wins
                if (losingPlayerWins == 0 && winningPlayerWins > 0) {
                    if (userWins > computerWins) {
                        ratioMessage = String.format("You beat the Computer at a ratio of %d:%d", winningPlayerWins, 0);
                    } else {
                        ratioMessage = String.format("The Computer beat you at a ratio of %d:%d", winningPlayerWins, 0);
                    }
                } else if (winningPlayerWins == 0 && losingPlayerWins == 0) {
                    ratioMessage = "No wins for either player."; // All ties or no games
                } else {
                    int gcd = findGCD(winningPlayerWins, losingPlayerWins);
                    int simplifiedWinningWins = winningPlayerWins / gcd;
                    int simplifiedLosingWins = losingPlayerWins / gcd;

                    if (userWins > computerWins) {
                        ratioMessage = String.format("You beat the Computer at a ratio of %d:%d", simplifiedWinningWins, simplifiedLosingWins);
                    } else if (computerWins > userWins) {
                        ratioMessage = String.format("The Computer beat you at a ratio of %d:%d", simplifiedWinningWins, simplifiedLosingWins);
                    } else { // userWins == computerWins, and both are > 0
                        ratioMessage = String.format("You and the Computer tied at %d:%d", simplifiedWinningWins, simplifiedLosingWins);
                    }
                }
            } else { // No wins or losses, only ties or no games
                ratioMessage = "No decisive wins or losses for either player.";
            }

            System.out.println("Final Result: " + ratioMessage);
        } else {
            System.out.println("No games were completed.");
        }
        System.out.println("Thanks for playing!");
        System.out.println("-----------------------------------------------------------------");
    }
}