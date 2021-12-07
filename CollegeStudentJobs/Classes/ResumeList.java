package Classes;
import java.util.ArrayList;
import java.util.UUID; 
/**
 * Creates the ResumeList class, which will hold a single instance of the List of Resumes
 */
public class ResumeList {
    private static ResumeList resumeList;
    private ArrayList<Resume> resumes;
    /**
     * ResumeList Constructor, gets the resume list from json and adds it to the class' ArrayList
     */
    private ResumeList() {
        this.resumes = DataLoader.getResumeList();
    }
    /**
     * getInstance method that creates a singular instance of the ResumeList class
     * @return ResumeList, the instance of the ResumeList
     */
    public static ResumeList getInstance() {
        if (resumeList == null) {
            resumeList = new ResumeList();
        }
        return resumeList;
    }

    /**
     * accessor method that finds a Resume from the ResumeList, finding it by its Unique ID
     * @param id UUID, Unique ID for the Resume to be found and returned
     * @return Resume, Resume found by their Unique ID
     */
    public Resume getResume(UUID resumeID) {
        for (Resume resume: resumes) {
            if (resume.getID().equals(resumeID)) return resume;
        }
        return null;
    }
    /**
     * accessor method for the list of Resumes
     * @return ArrayList<Resume>, the list of Resumes
     */
    public ArrayList<Resume> getResumes(){
        return this.resumes; 
    }
    /**
     * addResume method which creates and adds a Resume to the Resume list
     * @param resume Resume, Object Resume 
     */
    public void addResume(Resume resume) {
        this.resumes.add(resume);
    }
    /**
     * removeResume method removes a Resume from the Resume list
     * @param resume Resume, Object Resume 
     */
    public void removeResume(Resume resume) {
        this.resumes.remove(resume);
    }
    /**
     * saveResumeList method to save the Resume to the Resumes json file
     */
    public static void saveResumeList() {
        DataWriter.saveResumeList();
    }
}
