package Classes;

import java.util.ArrayList;
import java.util.UUID;
/**
 * Creates a Professor class, which inherits from the User class
 */
public class Professor extends User {
    //Attributes
    private UUID id;
    private String firstName;
    private String lastName;
    private boolean isVerified;
    private ArrayList<Review> createdReviews;
    
    /**
     * Parameterized Professor Constructor to create a new Professor
     * @param firstName String, Professor's first name
     * @param lastName String, Professor's last name
     * @param email String, Professor's email
     * @param password String, Professor's password
     */
    public Professor(String firstName, String lastName, String email, String password) {
        super(email, password);
        this.id = UUID.randomUUID();
        this.firstName = firstName;
        this.lastName = lastName;
        this.createdReviews = new ArrayList<Review>();
        this.isVerified = false;
    }

    /**
     * Parameterized Professor Constructor
     * @param id UUID, Professor's Unique ID
     * @param firstName String, Professor's first name
     * @param lastName String, Professor's last name
     * @param email String, Professor's email
     * @param password String, Professor's password
     * @param createdReviews ArrayList<Review>, Reviews created by professor
     * @param isVerified boolean, Verification status of professor
     */
    public Professor(UUID id, String firstName, String lastName, String email, String password, ArrayList<Review> createdReviews, boolean isVerified) {
        super(email, password);
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.createdReviews = createdReviews;
        this.isVerified = isVerified;
    }

    /**
     * addReview method so the professor can add a review to a student
     * Searches for the specific student in the list of students and adds the review to that student
     * @param review Review, Review to be added
     * @param student Student, Student that will receive the review
     */
    public void addReview(Review review, Student student) {
        //need to check this method
        for (int i = 0; i < SystemClass.getInstance().getStudentList().size(); i++) {
            if (SystemClass.getInstance().getStudentList().get(i) == student) {
                SystemClass.getInstance().getStudentList().get(i).addProfessorReview(review);
            }
        }
    }

    /**
     * removeReview method so the professor can remove a review they've made
     * Searches for the specific student in the list of students and removes the review made by the professor
     * @param review Review, Review to removed
     * @param student Student, Student that will have review removed from
     */
    public void removeReview(Review review, Student student) {
        //need to check this method
        for (int i = 0; i < SystemClass.getInstance().getStudentList().size(); i++) {
            if (SystemClass.getInstance().getStudentList().get(i) == student) {
                SystemClass.getInstance().getStudentList().get(i).removeProfessorReview(review);
            }
        }
    }

    /**
     * createReview method so the professor can create a review
     * @return Review, Review created by the professor
     */
    public Review createReview(String comment, double rating) {
        //need to change this method
        if(!isVerified) {
            return null;
        }
        Review review = new Review(rating, comment, firstName, lastName);
        return review;
    }

    /**
     * accessor method for the professor's first name
     * @return String, the professor's first name
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * accessor method for the professor's last name
     * @return String, the professor's last name
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * mutator method that sets the verification of the professor
     * @param isVerified boolean, verification status of the professor
     */
    public void setVerification(boolean isVerified) {
        this.isVerified = isVerified;
    }

    /**
     * accessor method for the professor's email
     * @return String, the professor's email
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * accessor method for the professor's password
     * @return String, the professor's password
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * accessor method for the professor's created reviews
     * @return ArrayList<Review>, the list of reviews created by the professor
     */
    public ArrayList<Review> getCreatedReviews() {
        return this.createdReviews;
    }
    
    /**
     * accessor method for the professor's ID
     * @return UUID, the professor's Unique ID
     */
    public UUID getId() {
		return id;
	}

    /**
     * accessor method for the professor's verification status
     * @return boolean, the professor's verification status
     */
    public boolean getIsVerified(){
        return isVerified;
    }

    /**
     * mutator method that sets the professor's first name
     * @param FirstName String, professor's first name to be saved
     */
    public void setFirstName(String FirstName){
        this.firstName = FirstName;
    }

    /**
     * mutator method that sets the professor's last name
     * @param LastName String, professor's last name to be saved
     */
    public void setLastName(String LastName){
        this.lastName = LastName;
    }

    /**
     * mutator method that sets the professor's email
     * @param email String, professor's email to be saved
     */
    public void setEmail(String email){
        this.email = email;
    }

    /**
     * mutator method that sets the professor's password
     * @param password String, professor's password to be saved
     */
    public void setPassword(String password){
        this.password = password;
    }
    /**
     * toString method that prints out the professor's information
     * @return String, professor's information
     */
    @Override
    public String toString(){
        String temp = "Professor ";
        temp += firstName + " " + lastName;
        temp += "\nEmail: " + email;
        return temp;
    }
}
