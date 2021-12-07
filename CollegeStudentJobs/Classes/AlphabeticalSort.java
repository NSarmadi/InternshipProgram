package Classes;

import java.util.ArrayList;
/**
 * Creates the AlphabeticalSort object, which implements the Filter Interface
 */
public class AlphabeticalSort implements Filter{

    /**
     * Sorts the incoming list of job listings in alphabetical order by their title
     * @param jobList ArrayList<JobListing>, List of jobs to be sorted into alphabetical order 
     * @return tempList ArrayList<JobListing>, A sorted version of the passed in list of jobs
     */
    public ArrayList<JobListing> sortList(ArrayList<JobListing> jobList) {
        ArrayList<JobListing> tempList = jobList; 
        boolean hasChanged = true; 
        while(hasChanged) {
            hasChanged = false; 
            for(int i = 0; i < tempList.size()-1; i++) {
                if(tempList.get(i).getJobTitle().compareToIgnoreCase(tempList.get(i+1).getJobTitle()) > 0) {
                    JobListing temp = tempList.get(i);
                    tempList.set(i, tempList.get(i+1));
                    tempList.set(i+1, temp);
                    hasChanged = true;
                }
            }
        }
        return tempList; 
    }
    
}
