package Classes;
import java.util.UUID;

/**
 * Creates a Review class
 */
public class Review {
    //Attributes
    private UUID id; 
    private double rating;
    private String comment;
    private String firstName;
    private String lastName;
    private String professorFirstName;
    private String professorLastName;

    /**
     * Parameterized Review Constructor
     * @param rating Double, Rating to be included in the review
     * @param comment String, Comment to be included in the review
     * @param firstName String, First name of who the review is being made for
     * @param lastName String, Last name of who the review is being made for
     */
    public Review(double rating, String comment, String firstName, String lastName) {
        this.id = UUID.randomUUID(); 
        this.rating = rating;
        this.comment = comment;
        this.firstName = firstName;
        this.lastName = lastName;
        this.professorFirstName = "";
        this.professorLastName = "";
    }

    /**
     * Parameterized Review Constructor
     * @param rating Double, Rating to be included in the review
     * @param comment String, Comment to be included in the review
     * @param firstName String, First name of who the review is being made for
     * @param lastName String, Last name of who the review is being made for
     * @param professorFirstName String, First name of the professor creating the review
     * @param professorLastName String, Last name of the professor creating the review
     */
    public Review(double rating, String comment, String firstName, String lastName, String professorFirstName, String professorLastName) {
        this.id = UUID.randomUUID(); 
        this.rating = rating;
        this.comment = comment;
        this.firstName = firstName;
        this.lastName = lastName;
        this.professorFirstName = professorFirstName;
        this.professorLastName = professorLastName;
    }

    /**
     * Parameterized Review Constructor
     * * @param rating Double, Rating to be included in the review
     * @param comment String, Comment to be included in the review
     * @param firstName String, First name of who the review is being made for
     * @param lastName String, Last name of who the review is being made for
     * @param id UUID, the Unique ID of the review
     */
    public Review(double rating, String comment, String firstName, String lastName, UUID id) {
        this.id = id; 
        this.rating = rating; 
        this.comment = comment; 
        this.firstName = firstName; 
        this.lastName = lastName;
        this.professorFirstName = "";
        this.professorLastName = "";
    }

    /**
     * accessor method for the name of who the review is for
     * @return String, the name of the person being reviewed
     */
    public String getName() {
        return firstName + " " + lastName;
    }

    /**
     * accessor method for the comment in the review
     * @return String, the comments from the review
     */
    public String getComment() {
        return this.comment;
    }

    /**
     * accessor method for the professor's first name
     * @return String, the professor's first name
     */
    public String getProfessorFirstName() {
        return this.professorFirstName;
    }

    /**
     * accessor method for the professor's last name
     * @return String, the professor's last name
     */
    public String getProfessorLastName() {
        return this.professorLastName;
    }

    /**
     * accessor method for rating
     * @return Double, the rating included in the review
     */
    public double getRating() {
         return this.rating;
    }

    /**
     * accessor method for the Unique ID of the review
     * @return UUID, the Unique ID of the review
     */
    public UUID getID() {
        return this.id; 
    }

    /**
     * toString method that displays the review
     * @return String, a review with the name, rating, and comments
     */
    @Override
    public String toString() {
        String review = "";
        review += this.firstName + " " + this.lastName + " \nRating: " + this.rating + " \n" + this.comment;
        return review;
    }

    /**
     * alternate toString method that displays the review
     * @return String, a review with the rating and comment
     */
    public String ToString(){
        String review = "";
        review += "Rating: " + this.rating + " \n" + this.comment;
        return review;
    }

    /**
     * accessor method for the first name
     * @return String, the first name of the person being reviewed
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * accessor method for the last name of the person being reviewed
     * @return String, the last name of the person being reviewed
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * mutator method for the first name
     * @param FirstName String, the first name of the person being reviewed to be saved
     */
    public void setFirstName(String FirstName){
        this.firstName = FirstName;
    }

    /**
     * mutator method for the last name
     * @param LastName String, the last name of the person being reviewed to be saved
     */
    public void setLastName(String LastName){
        this.lastName = LastName;
    }

}
