/*
Clint Scott
07/06/2025
CSD 402
M9 Program 1 - ArrayList Exception Handling with User Input

This file defines the ClintArrayListExceptionTest class which:
- Stores at least 10 String elements in an ArrayList
- Uses a for-each loop to display all elements
- Prompts the user to select an index to view again
- Uses try/catch to handle any out-of-bounds exception
- Demonstrates autoboxing and auto-unboxing with an Integer index
- Includes spell details when selected by the user
- Supports a -h or --help flag to explain how the program works
*/

import java.util.ArrayList;
import java.util.Scanner;
import java.util.NoSuchElementException;

public class ClintArrayListExceptionTest {

    /**
     * printHelp
     *
     * Displays a help screen explaining the program.
     */
    public static void printHelp() {
        System.out.println("ClintArrayListExceptionTest Help");
        System.out.println("---------------------------------");
        System.out.println("This program shows a list of 10 D&D spell names.");
        System.out.println("It then asks the user for an index (0-9) to display that specific spell again.");
        System.out.println("If an invalid index is entered, the program will display an exception message.");
        System.out.println("Usage:");
        System.out.println("  java ClintArrayListExceptionTest         Run the program");
        System.out.println("  java ClintArrayListExceptionTest -h      Show help screen");
        System.out.println("  java ClintArrayListExceptionTest --help  Show help screen");
        System.out.println();
    }

    /**
     * Main method - Displays spell list, handles user input, and shows selected spell details.
     */
    public static void main(String[] args) {
        // Check for help argument
        if (args.length > 0 && (args[0].equals("-h") || args[0].equals("--help"))) {
            printHelp();
            return;
        }

        ArrayList<Spell> spellbook = new ArrayList<>();

        // Add 10 Spell objects to the list
        spellbook.add(new Spell("Fireball", "A fiery explosion dealing 8d6 fire damage."));
        spellbook.add(new Spell("Healing Word", "Heals a creature for 1d4 + your spellcasting modifier."));
        spellbook.add(new Spell("Shield", "Grants a +5 bonus to AC for 1 round."));
        spellbook.add(new Spell("Invisibility", "The target becomes invisible for up to 1 hour."));
        spellbook.add(new Spell("Mage Armor", "Protects a creature with magical force, increasing AC to 13 + Dexterity modifier."));
        spellbook.add(new Spell("Lightning Bolt", "A bolt of lightning that deals 8d6 lightning damage in a line."));
        spellbook.add(new Spell("Teleport", "Instantly transports the caster and willing creatures to a location."));
        spellbook.add(new Spell("Counterspell", "Interrupts and negates another creature's spell."));
        spellbook.add(new Spell("Sleep", "Puts creatures within a 20-foot radius to sleep."));
        spellbook.add(new Spell("Fly", "Grants the ability to fly for 10 minutes."));

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to ClintArrayListExceptionTest");
        System.out.println("This program helps you view details of D&D spells.");
        System.out.println("Enter one spell index at a time. Enter 0 to stop.");
        System.out.println();

        // Main loop for user interaction
        while (true) {
            // Display all elements using a for-each loop with manual index
            System.out.println("Spellbook contents:");
            int i = 0;
            for (Spell s : spellbook) {
                System.out.println(i + ": " + s.getName());
                i++;
            }

            System.out.print("\nEnter the index of the spell you want to see again (0 to 9, q to quit): ");
            String input = null;
            try {
                input = scanner.nextLine();
            } catch (NoSuchElementException e) {
                System.out.println("\nThe program was interrupted. Exiting...");
                break;
            }

            if (input.equals("q")) {
                System.out.println("Exiting the program...");
                break;
            }

            try {
                // Autoboxing to Integer
                Integer index = Integer.valueOf(input);

                // Auto-unboxing when used as index
                if (index < 0 || index >= spellbook.size()) {
                    System.out.println("Exception: Out of Bounds");
                    continue;
                }

                Spell selectedSpell = spellbook.get(index);
                System.out.println(selectedSpell);

                System.out.print("\nDo you want to select another spell? (y/n): ");
                String anotherSpell = scanner.nextLine().toLowerCase();

                if (anotherSpell.equals("n")) {
                    System.out.println("Exiting the program...\n");
                    break;
                } else if (anotherSpell.equals("y")) {
                    System.out.println();
                    continue;
                } else {
                    System.out.println("Invalid response. Exiting the program...\n");
                    break;
                }

            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a numeric index.");
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }

        scanner.close();
    }
}
