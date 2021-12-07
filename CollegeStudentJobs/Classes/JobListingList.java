package Classes;

import java.util.ArrayList;
import java.util.UUID;
/**
 * Creates the JobListingList class, which will holds all the List of JobListings
 */
public class JobListingList {
    private static JobListingList jobListingList;
    private static ArrayList<JobListing> jobListings;
    /**
     * JobListingList Constructor, gets the JobListing list from json and adds it to the class' ArrayList
     */
    private JobListingList() {
        jobListings = DataLoader.getJobListingList();
    }
    /**
     * Returns the singular instance of the JobListingList class
     * @return LobListingList, the instance of the JobListingList
     */
    public static JobListingList getInstance() {
        if (jobListingList == null) {
            jobListingList = new JobListingList();
        }
        return jobListingList;
    }
    /**
     * Returns a JobListing from the JobListingList, finding it by UUID
     * @param id UUID, id for the JobListing to be found and returned
     * @return jobListing, the specfic jobListing
     */
    public JobListing getJobListing(UUID id) {
        for (JobListing jobListing: jobListings) {
            if (jobListing.getID().equals(id)) return jobListing;
        }
        return null;
    }
     /**
     * Returns the full ArrayList of JobListings 
     * @return ArrayList<JobListing>, the full ArrayList of JobListing
     */
    public ArrayList<JobListing> getJobListings() {
        return jobListings;
    }
    /**
     * Adds the passed in JobListing to the JobListing list
     * @param job JobListing, the JobListing to be added to the list
     */
    public void addJobListing(JobListing job) {
        jobListings.add(job);
    }
    /**
     * Removes the passed in JobListing from the ArrayList
     * @param job JobListing, JobListing to be removed from the list
     */
    public void removeJobListing(JobListing job) {
        jobListings.remove(job);
    }
    /**
     * Saves the list of JobListing to the JobListings json file
     */
    public static void saveJobListings() {
        DataWriter.saveJobListing();
    }
}
