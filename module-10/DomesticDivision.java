/*
Clint Scott
CSD 402
M10 DomesticDivision – Represents a U.S.-based Ubisoft division
07/14/2025

This subclass of Division includes an additional field:
- state where the domestic division is located
*/

public class DomesticDivision extends Division {
    private String state;

    /**
     * Constructs a DomesticDivision object.
     *
     * @param divisionName Name of the division
     * @param accountNumber Account number
     * @param state U.S. state of the division
     * @throws IllegalArgumentException if the state is invalid
     */
    public DomesticDivision(String divisionName, int accountNumber, String state) {
        super(divisionName, accountNumber);
        if (state == null || state.trim().isEmpty()) {
            throw new IllegalArgumentException("State cannot be null or empty.");
        }
        this.state = state;
    }

    /**
     * Displays information about the domestic division.
     */
    @Override
    public void display() {
        System.out.println("=== Domestic Division ===");
        System.out.println("Division Name  : " + divisionName);
        System.out.println("Account Number : " + accountNumber);
        System.out.println("State          : " + state);
        System.out.println("");
    }
}
