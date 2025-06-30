/*
Clint Scott
06/29/2025
CSD 402
M7 UseFans Class – Collection Usage and Display Without toString()

This program demonstrates working with a collection of Fan objects.

It includes:
- Manual display of each fan's state using methods instead of toString()
- A method for displaying an individual fan
- A method for displaying a list of fans
*/

import java.util.ArrayList;

/**
 * UseFans class creates and displays a collection of Fan objects
 * using manual output methods instead of the toString() method.
 */
public class UseFans {

    /**
     * Displays the details of all fans in the provided collection.
     * Calls displaySingleFan() for each item in the list.
     *
     * @param fans the list of Fan objects to display
     */
    public static void displayAllFans(ArrayList<Fan> fans) {
        System.out.println("Displaying all fans (without using toString):");
        System.out.println();

        int fanCount = 1; // Used to number each fan in the output

        for (Fan fan : fans) {
            System.out.println("Fan #" + fanCount);
            displaySingleFan(fan);
            System.out.println();
            fanCount++;
        }
    }

    /**
     * Displays all details for a single Fan object.
     * Will print speed only if the fan is currently turned on.
     *
     * @param fan the Fan object to display
     */
    public static void displaySingleFan(Fan fan) {
        System.out.println("Fan is " + (fan.isOn() ? "ON" : "OFF"));

        if (fan.isOn()) {
            String speedStr = switch (fan.getSpeed()) {
                case Fan.STOPPED -> "STOPPED";
                case Fan.SLOW -> "SLOW";
                case Fan.MEDIUM -> "MEDIUM";
                case Fan.FAST -> "FAST";
                default -> "UNKNOWN";
            };
            System.out.println("Speed: " + speedStr);
        }

        System.out.println("Radius: " + fan.getRadius());
        System.out.println("Color: " + fan.getColor());
    }

    /**
     * Main method creates a list of Fan objects, then
     * displays their details using the displayAllFans() method.
     *
     * @param args not used
     */
    public static void main(String[] args) {
        ArrayList<Fan> fans = new ArrayList<>();

        // Add five different Fan instances to the list
        fans.add(new Fan()); // Default fan
        fans.add(new Fan(Fan.SLOW, true, 8.0, "red"));
        fans.add(new Fan(Fan.MEDIUM, false, 10.0, "green"));
        fans.add(new Fan(Fan.FAST, true, 12.0, "black"));
        fans.add(new Fan(Fan.STOPPED, false, 7.5, "yellow"));

        // Display each fan's properties without calling toString()
        displayAllFans(fans);
    }
}