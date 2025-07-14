/*
Clint Scott
CSD 402
M10 InternationalDivision – Represents a non-U.S. Ubisoft division
07/14/2025

This subclass of Division includes two additional fields:
- country where the division operates
- language spoken in the division
*/

public class InternationalDivision extends Division {
    private String country;
    private String language;

    /**
     * Constructs an InternationalDivision object.
     *
     * @param divisionName Name of the division
     * @param accountNumber Account number
     * @param country Country of the division
     * @param language Language used by the division
     * @throws IllegalArgumentException if any field is invalid
     */
    public InternationalDivision(String divisionName, int accountNumber, String country, String language) {
        super(divisionName, accountNumber);
        if (country == null || country.trim().isEmpty()) {
            throw new IllegalArgumentException("Country cannot be null or empty.");
        }
        if (language == null || language.trim().isEmpty()) {
            throw new IllegalArgumentException("Language cannot be null or empty.");
        }
        this.country = country;
        this.language = language;
    }

    /**
     * Displays information about the international division.
     */
    @Override
    public void display() {
        System.out.println("=== International Division ===");
        System.out.println("Division Name  : " + divisionName);
        System.out.println("Account Number : " + accountNumber);
        System.out.println("Country        : " + country);
        System.out.println("Language       : " + language);
        System.out.println("");
    }
}
