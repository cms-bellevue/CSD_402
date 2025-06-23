/*
Clint Scott
06/22/2025
CSD 402
M6 Fan Class – Object Creation and Testing

This program (ScottM6Fan) demonstrates the creation and use of a `Fan` class.

The Fan class includes:
- Four speed constants: STOPPED, SLOW, MEDIUM, FAST (0–3)
- Private fields: speed, on (boolean), radius, and color
- Full encapsulation through getters and setters
- A no-argument constructor with default values
- An overloaded constructor with custom values
- A toString method to describe the fan's current state

The program tests both constructors and demonstrates the behavior of
all methods in the Fan class.

Use -h or --help as a command-line argument to display usage information.
*/

public class ScottM6Fan {

    /**
     * Main method to test the Fan class functionality.
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

        // Create a Fan object using the default constructor
        Fan defaultFan = new Fan();

        // Create a Fan object using the argument constructor
        Fan customFan = new Fan(Fan.FAST, true, 12.5, "black");

        // Display both fan objects to show initial states
        System.out.println("Default Fan:");
        System.out.println(defaultFan);
        System.out.println();

        System.out.println("Custom Fan:");
        System.out.println(customFan);
        System.out.println();

        // Modify default fan values using setters and display the updated state
        System.out.println("Updating Default Fan properties...");
		System.out.println();		
        defaultFan.setSpeed(Fan.MEDIUM);
        defaultFan.setOn(true);
        defaultFan.setRadius(9.0);
        defaultFan.setColor("blue");

        System.out.println("Updated Default Fan:");
        System.out.println(defaultFan);
    }

    /**
     * Prints the help message, detailing program usage.
     * This method is called when the user provides '--help' or '-h' as a command-line argument.
     */
    private static void printHelpMessage() {
        System.out.println("""
            ------------------------------------------------------------------
            ScottM6Fan Program – Usage Help

            This program creates and demonstrates a Fan class with the following:
            - Speed constants: STOPPED, SLOW, MEDIUM, FAST
            - Fields: speed, on/off, radius, and color
            - Getters and setters for all fields
            - A default and overloaded constructor
            - A toString method to describe the fan's current state

            The program tests both constructors and method functionality.

            To display this help message:
            java ScottM6Fan --help
            java ScottM6Fan -h

            Any other command-line argument will result in an error message.
            ------------------------------------------------------------------
            """);
    }
}

/**
 * Fan class defines the structure and behavior of a fan object.
 * Includes constants, private fields, constructors, and utility methods.
 */
class Fan {

    // Speed constants
    public static final int STOPPED = 0;
    public static final int SLOW = 1;
    public static final int MEDIUM = 2;
    public static final int FAST = 3;

    // Private fields
    private int speed;
    private boolean on;
    private double radius;
    private String color;

    /**
     * No-argument constructor.
     * Initializes the fan to STOPPED, off, radius 6, color white.
     */
    public Fan() {
        this.speed = STOPPED;
        this.on = false;
        this.radius = 6.0;
        this.color = "white";
    }

    /**
     * Overloaded constructor to set all fields.
     * @param speed the speed of the fan
     * @param on whether the fan is turned on
     * @param radius the fan’s radius
     * @param color the fan’s color
     * @throws IllegalArgumentException if the speed value is invalid
     */
    public Fan(int speed, boolean on, double radius, String color) {
        // Validate speed upon construction to ensure it's within the allowed range
        if (speed < STOPPED || speed > FAST) {
            throw new IllegalArgumentException("Invalid speed value: " + speed + ". Speed must be between " + STOPPED + " and " + FAST + ".");
        }
        this.speed = speed;
        this.on = on;
        this.radius = radius;
        this.color = color;
    }

    // Getter for speed
    public int getSpeed() {
        return speed;
    }

    /**
     * Setter for speed.
     * @param speed the desired speed of the fan (STOPPED, SLOW, MEDIUM, FAST)
     * @throws IllegalArgumentException if the speed value is invalid
     */
    public void setSpeed(int speed) {
        // Validate speed to ensure it's within the allowed range
        if (speed < STOPPED || speed > FAST) {
            throw new IllegalArgumentException("Invalid speed value: " + speed + ". Speed must be between " + STOPPED + " and " + FAST + ".");
        }
        this.speed = speed;
    }

    // Getter for on
    public boolean isOn() {
        return on;
    }

    // Setter for on
    public void setOn(boolean on) {
        this.on = on;
    }

    // Getter for radius
    public double getRadius() {
        return radius;
    }

    // Setter for radius
    public void setRadius(double radius) {
        this.radius = radius;
    }

    // Getter for color
    public String getColor() {
        return color;
    }

    // Setter for color
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Returns a string describing the fan's current state.
     * Indicates whether it's on or off, speed (if on), radius, and color.
     * @return fan description string
     */
    @Override
    public String toString() {
        if (on) {
            String speedStr = switch (speed) {
                case SLOW -> "SLOW";
                case MEDIUM -> "MEDIUM";
                case FAST -> "FAST";
                case STOPPED -> "STOPPED";
                default -> "UNKNOWN SPEED"; // Should not be reached with proper validation
            };
            return "Fan is ON\nSpeed: " + speedStr + "\nRadius: " + radius + "\nColor: " + color;
        } else {
            return "Fan is OFF\nRadius: " + radius + "\nColor: " + color;
        }
    }
}