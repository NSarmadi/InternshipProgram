package Classes;
import java.util.ArrayList; 
import java.util.UUID; 
/**
 * Creates the ExperienceList class in which all of the expereiences are held.
 */
public class ExperienceList {
    private static ExperienceList experienceList;
    private ArrayList<Experience> experiences;
/**
 * ExperienceList constructor, loads experiences onto the experience list.
 */
    private ExperienceList() {
        this.experiences = DataLoader.getExperienceList();
    }
    /**
     * Returns a single instance of the ExperienceList class.
     * @return Instance of the experience list class. 
     */
    public static ExperienceList getInstance() {
        if (experienceList == null) {
            experienceList = new ExperienceList(); 
        }
        return experienceList;
    }

    /**
     * Returns list of experiences from UUID value passed. 
     * @param id String, UUID for the experiences.
     * @return Experiences that contain the matching UUID. 
     */
    public Experience getExperience(UUID id) {
        for (Experience experience : experiences) {
            if (experience.getID().equals(id)) 
                return experience;  
        }
        //place holder
        return null;
    }
    /**
     * Gets the list of all experiences
     * @return ArrayList<Experience>, all experiences in the list
     */
    public ArrayList<Experience> getExperiences() {
        return this.experiences; 
    }
    /**
     * Adds a new experience into the ExperienceList
     * @param experience String value, experiences. 
     */
    public void addExperience(Experience experience) {
        experiences.add(experience);
    }
    /**
     * Removes an expereince from the ExperienceList
     * @param i experience to be removed. 
     */
    public void removeExperience(int i) {
        experiences.remove(i);
    }
    /**
     * Saves the ExperienceList into the appropriate JSON. 
     */
    public static void saveExperiences() {
        DataWriter.saveExperienceList();
    }
}
