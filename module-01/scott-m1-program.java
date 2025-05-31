import java.util.Scanner;

public class WaterHeatingEnergyCalculator {
    public static void main(String[] args) {
        // Create a Scanner object for user input
        Scanner input = new Scanner(System.in);

        // Prompt user for water mass in kilograms
        System.out.print("Enter the amount of water in kilograms: ");
        double waterMass = input.nextDouble();

        // Prompt user for initial temperature in Celsius
        System.out.print("Enter the initial temperature (°C): ");
        double initialTemperature = input.nextDouble();

        // Prompt user for final temperature in Celsius
        System.out.print("Enter the final temperature (°C): ");
        double finalTemperature = input.nextDouble();

        // Calculate the energy using the given formula
        double energy = waterMass * (finalTemperature - initialTemperature) * 4184;

        // Display the result
        System.out.printf("The energy required to heat the water is %.2f joules.%n", energy);

        // Close the scanner
        input.close();
    }
}
