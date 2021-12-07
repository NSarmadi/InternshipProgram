package Classes;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Creates the resume class in which all of the required information is held.
 */
public class Resume {
    private UUID id; 
    private String firstName; 
    private String lastName; 
    private String email; 
    private String phoneNumber; 
    private ArrayList<Experience> experiences;
    private ArrayList<String> skills;
    /**
     * Constructor for creating a new resume, assigns a new UUID.
     * @param firstName String value, first name of student.
     * @param lastName String value, last name of student.
     * @param email String value, email of student.
     * @param phoneNumber String value, phone number of student.
     */
    public Resume(String firstName, String lastName, String email, String phoneNumber) {
        this.id = UUID.randomUUID(); 
        this.firstName = firstName; 
        this.lastName = lastName; 
        this.email = email; 
        this.phoneNumber = phoneNumber;  
        this.experiences =  new ArrayList<Experience>();
        this.skills = new ArrayList<String>();
        SystemClass.getInstance().addResume(this);
    }
    /**
     * Constructor for resumes when recreating them in the JSON file.
     * @param firstName String value, students first name.
     * @param lastName String value, students last name.
     * @param email String value, students email.
     * @param phoneNumber String value, students phone number.
     * @param experience String value, students experience. 
     * @param skills String value, students skills.
     * @param id String value, UUID.
     */
    public Resume(String firstName, String lastName, String email, String phoneNumber, ArrayList<Experience> experience, ArrayList<String> skills, UUID id) {
        this.id = id; 
        this.firstName = firstName; 
        this.lastName = lastName; 
        this.email = email; 
        this.phoneNumber = phoneNumber; 
        this.experiences = experience;
        this.skills = skills;
    }
    /**
     * Method to add a skill to the array of skills.
     * @param skill String value, students skills.
     */
    public void addSkill(String skill) {
        skills.add(skill);
    }
    /**
     * Method to remove a skill from the array of skills.
     * @param skill String value, students skills.
     */
    public void removeSkill(String skill) {
        skills.remove(skill);
    }
    /**
     * Method to add a students experience to the list of experiences. 
     * @param experience String value, students experiences.
     */
    public void addExperience(Experience experience) {
        experiences.add(experience);
    }
    /**
     * Method to remove a students experience from the list of experiences.
     * @param experience String value, students experience.
     */
    public void removeExperience(Experience experience) {
        experiences.remove(experience);
    }
    /**
     * Gets the first name of the student
     * @return String, returns the students first name.
     */
    public String getFirstName() {
        return this.firstName;
    }
    /**
     * Get the last name of the student.
     * @return String, returns the students last name.
     */
    public String getLastName() {
        return this.lastName; 
    }
    /**
     * Gets the email of the student.
     * @return String, returns the students email.
     */
    public String getEmail() {
        return this.email;
    }
    /**
     * Gets the students phone number.
     * @return String, returns the students phone number.
     */
    public String getPhoneNumber() {
        return this.phoneNumber; 
    }
    /**
     * Gets the students experience.
     * @return String, returns the students experience.
     */
    public ArrayList<Experience> getExperience() {
        return this.experiences;
    }
    /**
     * Gets the students skills.
     * @return String, returns the students skills. 
     */
    public ArrayList<String> getSkills() {
        return this.skills;
    }
    /**
     * Gets the ID of the student.
     * @return String, returns the students ID.
     */
    public UUID getID() {
        return this.id; 
    }
    /**
     * Sets the first name of the student.
     * @param firstName String, students first name.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    /**
     * Sets the last name of the student
     * @param lastName String, students last name.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    /**
     * Sets the students phone number.
     * @param phoneNumber String, students phone number.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    /**
     * Returns a readable translated version of the information entered for a resume listing.
     * @return String, completed resume. 
     */
    public String toString() {
        String output = "";
        output += "                          \n";
        output += "         " + firstName + " " + lastName + "     \n";
        output += "     " + email + "           \n";
        output += "         " + phoneNumber + "             \n";
        output += "\n";
        output += "Experiences:\n";
        for(Experience exp : experiences) {
            output += exp.toString();
            output += "\n";
        }
        output += "\n";
        output += "Skills:\n";
        for (String skill : skills) {
            output += skill + ", "; 
        }
        output += "\n";
        return output; 
    }
}
