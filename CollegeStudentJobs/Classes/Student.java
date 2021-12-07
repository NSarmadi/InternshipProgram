package Classes;

import java.util.ArrayList;
import java.util.UUID;
import java.io.PrintStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File; 

/**
 * Creates a Student class, which inherits from the User class
 */
public class Student extends User {
    //Attributes
    private UUID id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Resume resume;
    private ArrayList<Review> professorReviews;

    /**
     * Parameterized Student Constructor
     * @param firstName String, Student's first name
     * @param lastName String, Student's last name
     * @param email String, Student's email
     * @param phoneNumber String, Student's phone number
     * @param password String, Student's password
     */
    public Student(String firstName, String lastName, String email, String phoneNumber, String password) {
        super(email, password);
        this.id = UUID.randomUUID();
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.resume = new Resume(firstName, lastName, phoneNumber, password);
        this.professorReviews = new ArrayList<Review>();
    }

    /**
     * Parameterized Student Constructor
     * @param id UUID, Student's Unique ID
     * @param firstName String, Student's first name
     * @param lastName String, Student's last name
     * @param email String, Student's email
     * @param phoneNumber String, Student's phone number
     * @param resume Resume, Student's resume
     * @param password String, Student's password
     * @param professorReviews ArrayList<Review>, Student's reviews made by professors
     */
    public Student(UUID id, String firstName, String lastName, String email, String phoneNumber, Resume resume, String password, ArrayList<Review> professorReviews) {
        super(email, password);
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.resume = resume;
        this.professorReviews = professorReviews;
    }

    public void setResume(Resume resume) {
        this.resume = resume;   
    }

    /**
     * printResumeToText method so the student can print their resume
     */
    public void printResumeToText() {
        String resumeName = firstName + lastName + "resume.txt";
        try {
            File myObj = new File(resumeName);
            if (myObj.createNewFile()) {
              System.out.println("File created: " + myObj.getName());
            } else {
              System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        try (PrintStream out = new PrintStream(resumeName)) {
            out.print(this.resume.toString());
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * accessor method for the student's Unique ID
     * @return UUID, the student's Unique ID
     */
    public UUID getID() {
		return id;
	}
    
    /**
     * accessor method for the student's first name
     * @return String, the student's first name
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * accessor method for the student's last name
     * @return String, the student's last name
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * accessor method for the student's email
     * @return String, the student's email
     */
    public String getEmail() {
        return this.email;
    }
    
    /**
     * accessor method for the student's password
     * @return String, the student's password
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * accessor method for the student's phone number
     * @return String, the student's phone number
     */
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    /**
     * accessor method for the student's resume
     * @return Resume, the student's resume
     */
    public Resume getResume() {
        return this.resume;
    }

    /**
     * printProfessorReviews method so the student can see the professors reviews
     */
    public void printProfessorReviews() {
        for (Review review : professorReviews) {
            review.ToString();
        }
    }

    /**
     * hasProfessorReview method so student can check if they have a review from a specific professor
     * @param FirstName String, First name of the professor whose review is being looked for
     * @param LastName String, Last name of the professor whose review is being looked for
     * @param index int, Where to start looking in the list of professor reviews
     * @return int, where the specific professor's review was found, if not found then -1 is returned
     */
     public int hasProfessorReview(String FirstName, String LastName, int index){
        for(int i=index;i<professorReviews.size();i++){
            if(professorReviews.get(i).getFirstName().equalsIgnoreCase(FirstName) && professorReviews.get(i).getLastName().equals(LastName))
                return i;
        }
        return -1;
    }

    /**
     * getSpecProfessorReviews method so the student can get a specific professor's review
     * @param FirstName String, First name of the professor whose review is being looked for
     * @param LastName String, Last name of the professor whose review is being looked for
     * @return Review, the specific professor's review, if it's not found then return null
     */
    public Review getSpecProfessorReviews(String FirstName, String LastName) {
        for (Review review : professorReviews) {
            if(review.getFirstName().equalsIgnoreCase(FirstName) && review.getLastName().equalsIgnoreCase(LastName))
                return review;
        }
        return null;
    }

    /**
     * mutator method for the students first name
     * @param firstName String, student's first name to be saved
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * mutator method for the student's last name
     * @param lastName String, student's last name to be saved
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * mutator method for the student's email
     * @param email String, student's email to be saved
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * mutator method for the student's password
     * @param password String, student's password to be saved
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * addProfessorReview method so a professor can add a review to the list of reviews
     * @param review Review, review being added to the list of reviews
     */
    public void addProfessorReview(Review review) {
        professorReviews.add(review);
    }

    /**
     * removeProfessorReview method where a professor can remove a review from the list of reviews
     * @param review Review, review being removed from the list of reviews
     */
    public void removeProfessorReview(Review review) {
        professorReviews.remove(review);
    }

    /**
     * mutator method for the student's phone number
     * @param phoneNumber String, student's phone number to be saved
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * accessor method for the list of reviews
     * @return ArrayList<Review>, the list of reviews
     */
    public ArrayList<Review> getProfessorReviews(){
        return professorReviews;
    }

    /**
     * toString method that prints out the student's information
     * @return String, the student's information
     */
    public String toString() {
        String output = "";
        output += "First name: " + this.firstName + "\n";
        output += "Last name: " + this.lastName + "\n";
        output += "Phone Number: " + this.phoneNumber + "\n";
        if (resume == null) {
            output += "Resume Unavailable";
            return output; 
        }
        output += resume.toString() + "\n";
        return output; 
    }
}
