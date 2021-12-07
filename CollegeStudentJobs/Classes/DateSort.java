package Classes;
import java.util.ArrayList; 
/**
 * Creates the DateSort class which implements the Filter interface
 */
public class DateSort implements Filter {
    /**
     * Sorts the input list of JobListings into order by when they were added
     * @param jobList ArrayList<JobListing>, ArrayList to be sorted
     * @return ArrayList<JobListing> Sorted array list
     */
    public ArrayList<JobListing> sortList(ArrayList<JobListing> jobList) {
        ArrayList<JobListing> list = jobList;
        for(int i = 0; i < jobList.size(); i++) {
            list.set(i, SystemClass.getInstance().getJobListingList().get(i));
        }
        return list; 
    }
    
}
