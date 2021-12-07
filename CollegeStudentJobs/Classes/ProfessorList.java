package Classes;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Creates the ProfessorList class, which will hold a single instance of the List of Professors
 */
public class ProfessorList {
    //Attributes
    private static ProfessorList professorList;
    private static ArrayList<Professor> professors;
    
    /**
     * ProfessorList Constructor, gets the professor list from json and adds it to the class' ArrayList
     */
    private ProfessorList() {
        professors = DataLoader.getProfessorList();
    }

    /**
     * getInstance method that creates a singular instance of the ProfessorList class
     * @return ProfessorList, the instance of the ProfessorList
     */
    public static ProfessorList getInstance() {
        if (professorList == null) {
            professorList = new ProfessorList();
        }
        return professorList;
    }

    /**
     * accessor method that finds a professor from the ProfessorList, finding it by its Unique ID
     * @param id UUID, Unique ID for the Professor to be found and returned
     * @return Professor, Professor found by their Unique ID
     */
    public Professor getProfessor(UUID id) {
        for (Professor professor: professors) {
            if (professor.getID().equals(id)) return professor;
        }
        return null;
    }
    
    /**
     * accessor method that finds a professor from the ProfessorList, finding it by their email
     * @param email String, Email to be used to find the professor
     * @return Professor, Professor found by their email
     */
    public Professor getProfessor(String email) {
        for (Professor professor: professors) {
            if(professor.getEmail().equals(email)) {
                return professor;
            }
        }
        return null;
    }

    /**
     * accessor method for the list of Professors
     * @return ArrayList<Professor>, the list of professors
     */
    public ArrayList<Professor> getProfessors() {
        return professors;
    }

    /**
     * haveProfessor method that goes through the list of professors to find a professor with an email matching the parameter
     * @param email String, Email of the professor account being searched for
     * @return boolean, True if professor is found, false if not
     */
    static boolean haveProfessor(String email){
        for (Professor professor: professors){
            if(professor.getEmail().equals(email))
                return true;
        }
        return false;
    }

    /**
     * addProfessor method which creates and adds a professor to the professor list
     * @param email String, The email for the new professor being created
     * @param password String, The password for the professor being created
     * @param firstName String, First name of the professor
     * @param lastName String, Last name of the professor
     * @return boolean, True if professor account is created, false if professor account already exists
     */
    public boolean addProfessor(String email, String password, String firstName, String lastName){
        if (haveProfessor(email))return false;
        
		professors.add(new Professor(firstName, lastName, email, password));
        return true;
    }

    /**
     * saveProfessor method to save the professors to the professorlist json file
     */
    public static void saveProfessor(){
        DataWriter.saveProfessor();
    }

    /**
     * haveProfessor method which goes through the list of professors to find a professor with an email and password matching the parameters
     * @param email String, Email of the professor account being searched for
     * @param password String, Password of the professor account being searched for
     * @return boolean, True if professor is found, false if not
     */
    public static boolean haveProfessor(String email, String password) {
        for (Professor professor: professors){
            if (professor.getEmail().equals(email) && professor.getPassword().equals(password) && professor.getIsVerified())
                return true;
        }
        return false;
    }

    
}
