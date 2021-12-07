package Classes;

import java.util.ArrayList;
import java.util.UUID;
/**
 * Creates the Business class, which inherits from the User class
 */
public class Business extends User {
    private UUID id;
    private String businessName;
    private String address;
    private ArrayList<JobListing> myJobListings;
    private boolean isVerified;

    /**
     * Business Constructor for creating a new business account
     * @param businessName String, Name for the business
     * @param email String, email for the business
     * @param password String, password for the business
     * @param address String, address for the business
     */
    public Business(String businessName, String email, String password, String address) {
        super(email, password);
        this.id = UUID.randomUUID();
        this.businessName = businessName;
        this.address = address;
        this.myJobListings = new ArrayList<JobListing>(); 
    }

    /**
     * Business constructor for when recreating a Business from the JSON file
     * @param id UUID, unique id for the business
     * @param businessName String, Name for the business
     * @param email String, Email for the business
     * @param password String, Password for the business
     * @param address String, Address for the business
     * @param jobListings ArrayList<JobListing>, List of JobListings for the business
     * @param isVerified boolean, Verification status for the business
     */
    public Business(UUID id, String businessName, String email, String password, String address, ArrayList<JobListing> jobListings, boolean isVerified) {
        super(email, password);
        this.id = id;
        this.businessName = businessName;
        this.address = address;
        this.myJobListings = jobListings; 
        this.isVerified = isVerified;
    }

    /**
     * Adds a jobListing to the Business' list of joblistings
     * @param jobListing JobListing, the job listing to be added to the list
     */
    public void addJobListing(JobListing jobListing) {
        myJobListings.add(jobListing);
    }

    /**
     * Removes a job listing from the Business' list of joblistings
     * @param jobListing JobListing, the job listing to be removed from the list
     */
    public void removeJobListing(JobListing jobListing) {
        myJobListings.remove(jobListing); 
    }

    /**
     * Returns the ID for the Business
     * @return UUID, Unique ID for the business
     */
    public UUID getId() {
		return id;
	}
    
    /**
     * Returns the Business' name
     * @return String, Business name
     */
    public String getBusinessName() {
        return this.businessName;
    }

    /**
     * Returns the full list of the Business' job listings
     * @return ArrayList<JobListing>, list of Job Listings for the business
     */
    public ArrayList<JobListing> getJobListings() {
        return this.myJobListings;
    }

    /**
     * Returns the verification status for the Business
     * @return boolean, verification status of the business
     */
    public boolean getIsVerified() {
        return this.isVerified;
    }

    /**
     * Returns the address for the business
     * @return String, Business address
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * Returns the email for the business
     * @return String, Business' emailS
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Returns the password for the business
     * @return String, Business' password
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Sets the business' name
     * @param businessName String, new Name for the business
     */
    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    /**
     * Sets the email for the business
     * @param email String, new email for the business
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Sets the address for the business
     * @param address String, new address for the business
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Sets the password for the business
     * @param password String, new business password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Sets the verification status of the business
     * @param isVerified boolean, verification status of the business
     */
    public void setVerification(boolean isVerified) {
        this.isVerified = isVerified;
    }

    /**
     * Returns a String containing a readable version of the important information of the Business Account
     * @return String, information of the Business Account
     */
    public String toString() {
        String output = "";
        output += "Business Name: " + businessName + "\n";
        output += "Email: " + email + "\n";
        output += "Business Address: " + address + "\n";
        for (int i = 0; i < myJobListings.size(); i++) {
            output += myJobListings.get(i).toString(); 
        }
        return output; 
    }
}
