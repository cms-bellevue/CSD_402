/*
Clint Scott  
07/06/2025  
CSD 402  
Spell Class – Represents a D&D Spell with a name and description  
*/

public class Spell {
    private String name;
    private String details;

    /**
     * Constructor for the Spell class.
     *
     * @param name    The name of the spell
     * @param details The description or details of the spell
     */
    public Spell(String name, String details) {
        this.name = name;
        this.details = details;
    }

    // Getter for spell name
    public String getName() {
        return name;
    }

    // Getter for spell details
    public String getDetails() {
        return details;
    }

    /**
     * toString method to return the spell's name and details.
     */
    @Override
    public String toString() {
        return "Spell: " + name + "\nDetails: " + details;
    }
}
