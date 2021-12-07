package Classes;

import java.util.ArrayList;
/**
 * Creates the Filter interface, laying out how a filter should be implemented
 */
public interface Filter {
    /**
     * Method to sort a list of jobs
     * @param jobList ArrayList<JobListing>, List of jobs to be sorted
     * @return ArrayList<JobListing> sorted list of jobs
     */
    public ArrayList<JobListing> sortList(ArrayList<JobListing> jobList);
}
