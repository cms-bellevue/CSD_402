/*
Clint Scott
06/29/2025
CSD 402
M7 Fan Class – Definition of Fan Object

This file (Fan.java) defines the Fan class used by other programs.

It includes:
- Four speed constants: STOPPED, SLOW, MEDIUM, FAST
- Private fields for speed, power state, radius, and color
- Getters and setters for encapsulated access
- A no-argument constructor with default values
- An overloaded constructor for setting all fields
- A toString method that outputs a description based on power state
*/

public class Fan {

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
     * Initializes the fan to STOPPED, off, radius 6, and color white.
     */
    public Fan() {
        this.speed = STOPPED;
        this.on = false;
        this.radius = 6.0;
        this.color = "white";
    }

    /**
     * Overloaded constructor to set all fields.
     *
     * @param speed  the speed of the fan
     * @param on     whether the fan is turned on
     * @param radius the fan’s radius
     * @param color  the fan’s color
     * @throws IllegalArgumentException if the speed value is invalid
     */
    public Fan(int speed, boolean on, double radius, String color) {
        if (speed < STOPPED || speed > FAST) {
            throw new IllegalArgumentException("Invalid speed value: " + speed +
                ". Speed must be between " + STOPPED + " and " + FAST + ".");
        }
        this.speed = speed;
        this.on = on;
        this.radius = radius;
        this.color = color;
    }

    // Getter for speed
    public int getSpeed() {
        return this.speed;
    }

    // Setter for speed with validation
    public void setSpeed(int speed) {
        if (speed < STOPPED || speed > FAST) {
            throw new IllegalArgumentException("Invalid speed value: " + speed +
                ". Speed must be between " + STOPPED + " and " + FAST + ".");
        }
        this.speed = speed;
    }

    // Getter for on/off state
    public boolean isOn() {
        return this.on;
    }

    // Setter for on/off state
    public void setOn(boolean on) {
        this.on = on;
    }

    // Getter for radius
    public double getRadius() {
        return this.radius;
    }

    // Setter for radius
    public void setRadius(double radius) {
        this.radius = radius;
    }

    // Getter for color
    public String getColor() {
        return this.color;
    }

    // Setter for color
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Returns a string describing the fan's current state.
     *
     * @return fan description string
     */
    @Override
    public String toString() {
        if (this.on) {
            String speedStr = switch (this.speed) {
                case SLOW -> "SLOW";
                case MEDIUM -> "MEDIUM";
                case FAST -> "FAST";
                case STOPPED -> "STOPPED";
                default -> "UNKNOWN SPEED";
            };
            return "Fan is ON\nSpeed: " + speedStr + "\nRadius: " + this.radius + "\nColor: " + this.color;
        } else {
            return "Fan is OFF\nRadius: " + this.radius + "\nColor: " + this.color;
        }
    }
}