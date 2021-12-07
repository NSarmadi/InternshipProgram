package Classes;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

public class BusinessTest {
    @BeforeEach
    public void setup(){
        JobListingList jobListingList = JobListingList.getInstance();
        jobListingList.getJobListings().clear();
    }

    @Test
    public void testAddJobListing(){
        JobListingList jobListingList = JobListingList.getInstance();
        jobListingList.getJobListings().clear();
        jobListingList.getJobListings().add(new JobListing("Business 1", "Job 1", "do this and that", 100));
        assertEquals(1, jobListingList.getJobListings().size());
    }

    @Test
    public void testAddMultipleJobListings(){
        JobListingList jobListingList = JobListingList.getInstance();
        jobListingList.getJobListings().clear();
        jobListingList.getJobListings().add(new JobListing("Business 1", "Job 1", "do this and that", 100));
        jobListingList.getJobListings().add(new JobListing("Business 2", "Job 2", "do that and this", 200));
        jobListingList.getJobListings().add(new JobListing("Business 3", "Job 3", "do nothing", 300));
        jobListingList.getJobListings().add(new JobListing("Business 4", "Job 4", "do everything", 400));
        assertEquals(4, jobListingList.getJobListings().size());
    }

    @Test
    public void testRemoveJobListing(){
        JobListingList jobListingList = JobListingList.getInstance();
        jobListingList.getJobListings().clear();
        jobListingList.getJobListings().add(new JobListing("Business 1", "Job 1", "do this and that", 100));
        jobListingList.getJobListings().add(new JobListing("Business 2", "Job 2", "do that and this", 200));
        jobListingList.getJobListings().add(new JobListing("Business 3", "Job 3", "do nothing", 300));
        jobListingList.getJobListings().add(new JobListing("Business 4", "Job 4", "do everything", 400));
        jobListingList.removeJobListing(jobListingList.getJobListings().get(2));
        assertEquals(3, jobListingList.getJobListings().size());
    }
    
}
