package Classes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class JobListingListTest {
    JobListingList List = JobListingList.getInstance();
    ArrayList<JobListing> JobList = List.getJobListings(); 

    @BeforeEach
    public void setup() {
        JobList.clear();
    }

    @Test
    void testAddJobListing() {
        List.addJobListing(new JobListing("businessName", "jobTitle", "jobDescription", 0));
        assertEquals("businessName", JobList.get(0).getBusinessName());
    }

    @Test
    void testAddEmptyJobListing() {
        List.addJobListing(new JobListing("", "", "", 0));
        assertEquals("", JobList.get(0).getBusinessName());
    }

    @Test
    void testAddNullExperience() {
        List.addJobListing(null);
        assertEquals(null, JobList.get(0));
    }

    @Test
    void testRemoveJobListing() {
        List.addJobListing(new JobListing("businessName", "jobTitle", "jobDescription", 0));
        List.removeJobListing(new JobListing("businessName", "jobTitle", "jobDescription", 0));
        assertEquals(0, JobList.size());
    }

}
