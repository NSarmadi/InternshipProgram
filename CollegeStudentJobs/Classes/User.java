package Classes;

import java.util.UUID;
/**
 * Creates the User class which is a Parent Class for all the users
 */
public class User {
    protected String email;
    protected String password;
    protected UUID userID;
    /**
     * User constructor, creates the user account, to be used when creating a new user
     * @param userID UUID, User UUID for json 
     * @param email String, User account email
     * @param password String, User account password
     */
    public User(UUID userID, String email, String password) {
        this.userID = userID;
        this.email = email;
        this.password = password;
    }
    /**
     * User constructor, creates the user account, to be used when creating a new user 
     * @param email String, User account email
     * @param password String, User account password
     */
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
    /**
     * Returns the email for the user
     * @return String, user email
     */
    public String getEmail() {
        return this.email;
    }
    /**
     * Returns the password for the user
     * @return String, user password
     */
    public String getPassword() {
        return this.password; 
    }
    /**
     * Returns the userID for the user
     * @return UUID, user id for json
     */
    public UUID getID(){
        return this.userID; 
    }
}
