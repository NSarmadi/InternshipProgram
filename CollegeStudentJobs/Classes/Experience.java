package Classes;

import java.util.ArrayList;
import java.util.UUID;
/**
 * Creates the experince class in which is held the list of required informaton.
 */
public class Experience {
    private UUID id; 
    private String companyName;
    private String startDate;
    private String endDate;
    private ArrayList<String> descriptions;
/**
 * Constructor for creating a new experience, assigns a random UUID. 
 * @param companyName String value, holds the information pertaining to the company name.
 * @param startDate String value, holds the information pertaining to the start date.
 * @param endDate String value, holds the information pertaining to the end date.
 */
    public Experience(String companyName, String startDate, String endDate) {
        this.id = UUID.randomUUID();
        this.companyName = companyName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.descriptions = new ArrayList<>();
    }
/**
 * Constructor for experiences when recreating them in the JSON file.
 * @param companyName String value, holds the information pertaining to the company name.
 * @param startDate String value, holds the information pertaining to the start date.
 * @param endDate String value, holds the information pertaining to the end date.
 * @param descriptions String value, holds the information for descriptions.
 * @param id String value, holds the ID.
 */
    public Experience(String companyName, String startDate, String endDate, ArrayList<String> descriptions, UUID id) {
        this.companyName = companyName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.descriptions = descriptions;
        this.id = id; 
    }
    /**
     * Returns the company name
     * @return String value, company name.
     */
    public String getCompanyName() {
        return this.companyName;
    }
    /**
     * Returns the start date
     * @return String value, start date.
     */
    public String getStartDate() {
        return this.startDate;
    }
    /**
     * Returns the end date.
     * @return String value, end date.
     */
    public String getEndDate() {
        return this.endDate;
    }
    /**
     * Sets the companys name
     * @param companyName String value, company name.
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    /**
     * Sets the start date
     * @param startDate String value, start date.
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    /**
     * Sets the ending date.
     * @param endDate String value, end date.
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    /**
     * Gets the descriptions.
     * @return String value, descriptions.
     */
    public ArrayList<String> getDescriptions() {
        return this.descriptions;
    }
    /**
     * Adds a description to an array list of discriptions
     * @param description String value for descriptions.
     */
    public void addDescription(String description) {
        descriptions.add(description);
    }
    /**
     * Removes a description from an array list of descriptions
     * @param description String value, description.
     */
    public void removeDescription(int description) {
        descriptions.remove(description);
    }
    /**
     * Gets the UUID of the experience listing
     * @return String value, UUID of experience listing. 
     */
    public UUID getID() {
        return this.id;
    }
    /**
     * Returns a readable translated version of the information entered for an experience listing.
     * @return String value, experience.
     */
    public String toString() {
        String output = "";
        output += companyName + "\n";
        output += startDate + " - " + endDate + "\n";
        for (int i = 0; i < descriptions.size(); i++) {
            output += descriptions.get(i) + "\n";
        } 
        return output; 
    }
}
