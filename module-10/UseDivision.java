/*
Clint Scott
CSD 402
M10 UseDivision – Demonstrates abstract and concrete class inheritance
07/14/2025

This program defines and uses the Division class hierarchy:
- Division is an abstract class for company divisions
- InternationalDivision adds country and language
- DomesticDivision adds state

The program:
- Creates two international and two domestic Ubisoft divisions
- Uses the display() method to print division details
- Supports -h or --help flags to explain how the program works
- Catches and handles invalid data and input interruptions gracefully
*/

public class UseDivision {

    /**
     * Prints a help screen describing how the program works.
     */
    public static void printHelp() {
        System.out.println("UseDivision Help");
        System.out.println("-----------------------------");
        System.out.println("This program demonstrates inheritance with abstract and concrete classes.");
        System.out.println("It creates 4 Ubisoft company divisions and displays their info.");
        System.out.println("Divisions:");
        System.out.println(" - InternationalDivision: requires country and language.");
        System.out.println(" - DomesticDivision: requires U.S. state.");
        System.out.println();
        System.out.println("Usage:");
        System.out.println("  java UseDivision           Run the program");
        System.out.println("  java UseDivision -h        Show help screen");
        System.out.println("  java UseDivision --help    Show help screen");
        System.out.println();
    }

    /**
     * Main method – creates and displays international and domestic Ubisoft divisions.
     */
    public static void main(String[] args) {
        // Show help if -h or --help flag is passed
        if (args.length > 0 && (args[0].equals("-h") || args[0].equals("--help"))) {
            printHelp();
            return;
        }

        try {
            // Create two international divisions
            Division intl1 = new InternationalDivision("Ubisoft Montreal Studio", 1001, "Canada", "French");
            Division intl2 = new InternationalDivision("Ubisoft Pune Studio", 1002, "India", "English");

            // Create two domestic divisions
            Division dom1 = new DomesticDivision("Ubisoft San Francisco Studio", 2001, "California");
            Division dom2 = new DomesticDivision("Ubisoft Cary Operations", 2002, "North Carolina");

            // Display division info
            intl1.display();
            intl2.display();
            dom1.display();
            dom2.display();

        } catch (IllegalArgumentException e) {
            System.out.println("Error creating division: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }
}
