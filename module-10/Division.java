/*
Clint Scott
CSD 402
M10 Division – Abstract superclass for company divisions
07/14/2025

This abstract class represents a generic company division.
It includes fields for the division's name and account number,
and requires any subclass to implement the display() method.
*/

public abstract class Division {
    protected String divisionName;
    protected int accountNumber;

    /**
     * Constructs a Division object with a name and account number.
     *
     * @param divisionName Name of the division
     * @param accountNumber Unique account number for the division
     * @throws IllegalArgumentException if inputs are invalid
     */
    public Division(String divisionName, int accountNumber) {
        if (divisionName == null || divisionName.trim().isEmpty()) {
            throw new IllegalArgumentException("Division name cannot be null or empty.");
        }
        if (accountNumber <= 0) {
            throw new IllegalArgumentException("Account number must be a positive integer.");
        }
        this.divisionName = divisionName;
        this.accountNumber = accountNumber;
    }

    /**
     * Abstract method to display division details.
     */
    public abstract void display();
}
