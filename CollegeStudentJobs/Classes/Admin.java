package Classes;

import java.util.UUID;
/**
 * Creates the admin class which inherits from User
 */
public class Admin extends User {
    private UUID userID; 
    /**
     * Admin constructor, creates the admin account, to be used when creating a new admin
     * @param email String, Admin account email
     * @param password String, Admin account password
     */
    public Admin(String email, String password) {
        super(email, password);
        this.userID = UUID.randomUUID();
    }

    /**
     * Admin constructor, to be used when restoring an admin object from JSON.
     * @param id UUID, Unique id for the admin object
     * @param email String, Email for the admin
     * @param password String, password for the admin
     */
    public Admin(UUID id, String email, String password) {
        super(email, password);
        this.userID = id;
    }

    /**
     * Sets the verification status of the professor from either verified to unverified, or vice versa
     * @param professor Professor, the professor you want to change the verification of
     * @param verification boolean, the state at which you want to set the status
     */
    public void setVerificationProfessor(Professor professor, boolean verification) {
        for (int i = 0; i < SystemClass.getInstance().getProfessorList().size(); i++) {
            if (SystemClass.getInstance().getProfessorList().get(i) == professor) {
                //Need to ask group about this line
                SystemClass.getInstance().getProfessorList().get(i).setVerification(verification);
            }
        }
    }

    /**
     * Sets the verification of a business
     * @param business Business, the business that you want to set the verification of
     * @param verification boolean, the verification status you want the business to have
     */
    public void setVerificationBusiness(Business business, boolean verification) {
        for (int i = 0; i < SystemClass.getInstance().getBusinessList().size(); i++) {
            if (SystemClass.getInstance().getBusinessList().get(i) == business) {
                SystemClass.getInstance().getBusinessList().get(i).setVerification(verification);
            }
        }
    }

    /**
     * Removes a JobListing from the main list of JobListings
     * @param jobListing JobListing, the joblisting to be removed from the main list
     */
    public void removeJobListing(JobListing jobListing) {
        SystemClass.getInstance().getJobListingList().remove(jobListing);
    }

    /**
     * Removes a review from a student's account from the main Student List
     * @param student Student, student that the review is attached to
     * @param review Review, the review to be removed
     */
    public void removeStudentReview(Student student, Review review) {
        for (int i = 0; i < SystemClass.getInstance().getStudentList().size(); i++) {
            if (SystemClass.getInstance().getStudentList().get(i) == student) {
                SystemClass.getInstance().getStudentList().get(i).removeProfessorReview(review);
            }
        }
    }

    /**
     * Removes a job review from the job listing passed in
     * @param jobListing JobListing, job listing to have a review removed from
     * @param review Review, review to be removed
     */
    public void removeJobReview(JobListing jobListing, Review review) {
        for (int i = 0; i < SystemClass.getInstance().getJobListingList().size(); i++) {
            if (SystemClass.getInstance().getJobListingList().get(i) == jobListing) {
                SystemClass.getInstance().getJobListingList().get(i).removeReview(review);
            }
        }
    }

    /**
     * Removes a student from the main StudentList
     * @param student Student, the student to be removed
     */
    public void removeStudent(Student student) {
        SystemClass.getInstance().getStudentList().remove(student);
    }

    /**
     * Removes a business from the main Business List
     * @param business Business, business to be removed
     */
    public void removeBusiness(Business business) {
        SystemClass.getInstance().getBusinessList().remove(business);
    }

    /**
     * Sets the password of the admin to the passed in String
     * @param password String, new password for the admin
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Sets the email of the Admin to the passed in String
     * @param email String, New email for the admin
     */
    public void setEmail(String email){
        this.email = email;
    }

    /**
     * Returns the UUID for the admin
     * @return UUID, admin's id
     */
    public UUID getId() {
		return this.userID;
	}

    /**
     * Returns the Email for the admin
     * @return String, Admin's email
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Returns the password for the admin
     * @return String, admin's password
     */
    public String getPassword() {
        return this.password;
    }
    
    /**
     * Returns a readable String containing the necessary information for the admin 
     * @return String, information regarding the admin
     */
    public String toString() {
        String output = "";
        output += "Email: " + this.email + "\n";
        output += "Password: " + this.password + "\n";
        output += "ID: " + this.getId() + "\n";
        return output;
    }
}
