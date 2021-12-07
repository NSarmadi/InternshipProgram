package Classes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
public class AlphabeticalSortTest {
    private ArrayList<JobListing> job;
    private AlphabeticalSort alph;

    @BeforeEach
    public void setup() {
        job = new ArrayList<JobListing>();
        job.add(new JobListing("businessName", "P", "jobDescription", 10));
        job.add(new JobListing("This is a business", "B", "Working", 11));
        job.add(new JobListing("Bruh", "D", "jobDesc", 12));
        alph = new AlphabeticalSort();
    }

    @Test
    void testAlphabeticalSort() {
        job = alph.sortList(job);
        assertEquals("B", job.get(0).getJobTitle());
    }

    @Test
    void testAlphabeticalSortEmptyList() {
        job.clear();
        job = alph.sortList(job);
        assertEquals(0, job.size());
    }

    @Test
    void testAlphabeticalSortWithNullJob() {
        job.add(null);
        job = alph.sortList(job);
        assertEquals(null, job.get(job.size()-1));
    }

    @Test
    void testAlphabeticalSortWithCopyJobs() {
        job.add(new JobListing("Twitter", "A Job", "A Job Description", 10));
        job.add(new JobListing("Twitter", "A Job", "A Job Description", 10));
        job = alph.sortList(job);
        assertEquals(job.get(0).getJobTitle(), job.get(1).getJobTitle());
    }

    @Test
    void testAlphabeticalSortWithOneThousandJobs() {
        job.clear();
        for(int i = 999; i > 0; i--) {
            job.add(new JobListing(i+"", i+"", i+"", i));
        }
        job = alph.sortList(job);
        assertEquals("999", job.get(job.size()-1).getJobTitle());
    }
}
