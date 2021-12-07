package Classes;

import java.util.ArrayList;
/**
 * Creates the WageSort class which sorts an arraylist of JobListings by their wage, greatest to lowest
 */
public class WageSort implements Filter{

    /**
     * SortList function, sorts the incoming list of joblistings
     * @param jobList ArrayList<JobListing>, JobList list to be sorted
     * @return ArrayList<JobListing> sorted version of the passed in job list
     */
    public ArrayList<JobListing> sortList(ArrayList<JobListing> jobList) {
        ArrayList<JobListing> temp = jobList; 
        if(temp.get(0) == null)
            return temp; 
        boolean hasChanged = true; 
        while(hasChanged) {
            hasChanged = false; 
            for(int i = 0; i < temp.size()-1; i++) {
                if(temp.get(i).getWage() < temp.get(i+1).getWage()) {
                    JobListing temp_ = temp.get(i);
                    temp.set(i, temp.get(i+1));
                    temp.set(i+1, temp_);
                    hasChanged = true; 
                }
            }
        }
        return temp; 
    }
}
