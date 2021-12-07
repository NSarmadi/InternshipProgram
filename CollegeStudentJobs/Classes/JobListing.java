package Classes;

import java.util.ArrayList;
import java.util.UUID; 
/**
 * Creates the JobListing class
 */
public class JobListing {
    private String businessName; 
    private UUID id; 
    private String jobTitle;
    private String jobDescription;
    private ArrayList<String> experiences;
    private ArrayList<String> skills;
    private ArrayList<Review> reviews;
    private double wage;
    private ArrayList<Student> appliedStudents;
    /**
     * JobListing constructor, creates the JobListing, to be used when creating a new JobListing
     * @param businessName String, JobListing business Name
     * @param jobTitle String, JobListing job title
     * @param jobDescription String, JobListing job description(Description of job)
     * @param wage double, the wage of the job
     */
    public JobListing(String businessName, String jobTitle, String jobDescription, double wage) {
        this.businessName = businessName;
        this.jobTitle = jobTitle;
        this.jobDescription = jobDescription;
        this.wage = wage;
        appliedStudents = new ArrayList<Student>();
        experiences = new ArrayList<String>();
        reviews = new ArrayList<Review>();
        skills = new ArrayList<String>();
        this.id = UUID.randomUUID();
    }
    /**
     * Another JobListing constructor, creates the JobListing, to be used when creating a new JobListing when we know the specifics
     * @param businessName String, JobListing business Name
     * @param jobTitle String, JobListing job title
     * @param jobDescription String, JobListing job description(Description of job)
     * @param wage double, the wage of the job
     * @param ArrayList<String> experience, the arraylist of experiences for the specific jobListing
     * @param ArrayList<String> skills, the arraylist of skills for the specific jobListing
     * @param ArrayList<Review> reviews, the arraylist of reviews for the specific jobListing
     * @param ArrayList<Student> appliedStudents, the arraylist of students that have applied to the specific jobListing
     * @param UUID id, the specific UUID of the jobListing
     */

    public JobListing(String businessName, String jobTitle, String jobDescription, double wage, ArrayList<String> experience, ArrayList<String> skills, ArrayList<Review> reviews, ArrayList<Student> appliedStudents, UUID id) {
        this.businessName = businessName;
        this.jobTitle = jobTitle;
        this.jobDescription = jobDescription;
        this.wage = wage;
        this.experiences = experience; 
        this.skills = skills; 
        this.reviews = reviews; 
        this.appliedStudents = appliedStudents; 
        this.id = id;
    }
    /**
     * Adds the passed review to the review list and adds it to the SystemClass review list
     * @param Review review, the review to be added to the list
     */
    public void addReview(Review review) {
        reviews.add(review);
        SystemClass.getInstance().addReview(review);
    }
    /**
     * Removes the passed review from the review list
     * @param Review review, the review to be removed from the list
     */
    public void removeReview(Review review) {
        reviews.remove(review);
    }
    /**
     * Adds the passed experience to the experience list
     * @param String experience, the experience to be added to the list
     */
    public void addExperience(String experience) {
        experiences.add(experience);
    }
    /**
     * Removes the passed experience from the experience list
     * @param int experience, the experience to be removed from the list
     */
    public void removeExperience(int experience) {
        experiences.remove(experience);
    }
    /**
     * Adds the passed in skill to the skill list
     * @param String skill, the skill to be added to the list
     */
    public void addSkill(String skill) {
        skills.add(skill);
    }
    /**
     * Removes the passed skill from the skill list
     * @param int skill, the skill to be removed from the list
     */
    public void removeSkill(int skill) {
        skills.remove(skill);
    }
    /**
     * Returns the wage of the jobListing 
     * @return double, the wage of jobListing
     */
    public double getWage() {
        return this.wage;
    }
    /**
     * Sets the Description of the specific jobListing
     * @param String description, the descriptiong of the specific jobListing
     */
    public void setDescription(String description) {
        this.jobDescription = description; 
    }
    /**
     * Sets the wage of the specific jobListing
     * @param double wage, the wage of the specific jobListing
     */
    public void setWage(double wage) {
        this.wage = wage;
    }
    /**
     * Sets the title of the specific jobListing
     * @param String title, the title of the specific jobListing
     */
    public void setTitle(String title) {
        this.jobTitle = title; 
    }
    /**
     * Returns the jobTitle of the jobListing 
     * @return String, the jobTitle of the jobListing
     */
    public String getJobTitle() {
        return this.jobTitle;
    }
    /**
     * Returns the jobDescription of the jobListing 
     * @return String, the jobDescription of the jobListing
     */
    public String getJobDescription() {
        return this.jobDescription;
    }
    /**
     * Returns the experiences of the jobListing 
     * @return ArrayList<String>, the experiences of the jobListing
     */
    public ArrayList<String> getExperienceList() {
        return this.experiences;
    }
    /**
     * Returns the skills of the jobListing 
     * @return ArrayList<String>, the skills of the jobListing
     */
    public ArrayList<String> getSkillList() {
        return this.skills;
    }
    /**
     * Returns the reviews of the jobListing 
     * @return ArrayList<Review>, the reviews of the jobListing
     */
    public ArrayList<Review> getReviews() {
        return this.reviews;
    }
    /**
     * Returns the Student that applied to the jobListing 
     * @return ArrayList<Student>, the Student that applied to the jobListing
     */
    public ArrayList<Student> getAppliedStudents() {
        return this.appliedStudents;
    }
    /**
     * Adds the passed in student to the appliedstudent list
     * @param Student student, the student to be added to the list
     */
    public void addAppliedStudent(Student student) {
        this.appliedStudents.add(student);
    }
    /**
     * Returns the UUID of the jobListing 
     * @return UUID, the id of the jobListing
     */
    public UUID getID() {
        return this.id; 
    }
    /**
     * Returns the businessName of the jobListing 
     * @return String, the businessName of the jobListing
     */
    public String getBusinessName() {
        return this.businessName;
    }
    /**
     * Returns a readable String containing the necessary information for the JobListing 
     * @return String, information regarding the JobListing
     */
    public String toString() {
        String output = "";
        output += "Title: " + this.jobTitle + "\n";
        output += "Description: " + this.jobDescription + "\n";
        output += "Business: " + this.businessName + "\n";
        output += "Wage: " + wage + "\n"; 
        output += "Experiences: ";
        for(int i = 0; i < experiences.size(); i++) {
            output += experiences.get(i) + ", ";
        }
        output += "\n";
        output += "Skills: ";
        for(int i = 0; i < skills.size(); i++) {
            output += skills.get(i) + ", ";
        }
        //output += "\n";
        //output += "Reviews: \n";
        //for(int i = 0; i < reviews.size(); i++) {
          //  output += (i+1) + " " + reviews.get(i).toString() + "\n";
            //output += "\n";
        //}
        return output; 
    }
    /**
     * Returns a shorter readable String containing the necessary information for the JobListing 
     * @return String, information regarding the JobListing
     */
    public String jobListingBasicInfo() {
        String output = "";
        output += "Title: " + jobTitle + "\n";
        output += "Company: " + businessName + "\n";
        output += "Wage: " + wage;
        return output; 
    }
}
