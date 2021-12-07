package Classes;

import java.util.ArrayList;
/**
 * Creates the BusinessList class which holds the List of all businesses in the program
 */
public class BusinessList {
    private static BusinessList businessList;
    private static ArrayList<Business> businesses;
    /**
     * BusinessList constructor, loads the businesses into the business list
     */
    private BusinessList() {
        businesses = DataLoader.getBusinessList();
    }

    /**
     * Returns the single instance of the BusinessList class
     * @return BusinessList, Instance of the BusinessList class
     */
    public static BusinessList getInstance() {
        if (businessList == null) {
            businessList = new BusinessList();
        }
        return businessList;
    }

    /**
     * Returns a business based on a passed in email
     * @param email String, email for the business being found
     * @return Business, business matching the passed in Email
     */
    public Business getBusiness(String email) {
        for (Business business: businesses) {
            if (business.getEmail().equals(email)) return business;
        }
        return null;
    }

    /**
     * Gets the full list of businesses
     * @return ArrayList<Business>, list of businesses
     */
    public ArrayList<Business> getBusinesses() {
        return businesses;
    }
    
    /**
     * Checks if the business list contains a business
     * @param email String, Email of the business being checked for
     * @param password String, Password of the business being checked for
     * @return boolean, whether or not the business is in the list
     */
    public boolean haveBusiness(String email, String password){
        for(Business business: businesses){
            if(business.getEmail().equals(email) && business.getPassword().equals(password))
                return true;
        }
        return false;
    }

    /**
     * Checks if the business list contains a business
     * @param email String, Email of the business being checked for
     * @return Business, business that has been found, null if not found
     */
    public Business haveBusiness(String email){
        for(Business business: businesses){
            if(business.getEmail().equals(email))
                return business;
        }
        return null;
    }

    /**
     * Helper method to check if a business is in the list from a certain email
     * @param email String, Email of the business being checked for
     * @return boolean, true if business is found, false if not
     */
    private static boolean hasBusiness(String email){
        for(Business business: businesses){
            if(business.getEmail().equals(email))
                return true;
        }
        return false;
    }

    /**
     * Adds a new business into the BusinessList
     * @param email String, email for the business
     * @param password String, password for the business
     * @param companyName String, name for the business
     * @param companyAddress String, Address for the business
     * @return boolean, true if the business was added, false if a business with the same email already exists
     */
    public boolean addBusiness(String email, String password, String companyName, String companyAddress){
        if(hasBusiness(email))return false;
        
		businesses.add(new Business(companyName, email, password, companyAddress));
        return true;
    }

    /**
     * Saves the list of businesses to the json
     */
    public static void saveBusiness(){
        DataWriter.saveBusiness();
    }
}
