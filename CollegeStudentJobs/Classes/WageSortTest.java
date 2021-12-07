package Classes;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
public class WageSortTest {
    private ArrayList<JobListing> job;
    private WageSort wage;
    

    @BeforeEach
    public void setup() {
        job = new ArrayList<JobListing>();
        job.add(new JobListing("businessName", "P", "jobDescription", 10));
        job.add(new JobListing("This is a business", "B", "Working", 11));
        job.add(new JobListing("Bruh", "D", "jobDesc", 12));
        wage = new WageSort();
    }

    @Test
    void testWageSort() {
        job = wage.sortList(job);
        assertEquals(12, job.get(0).getWage());
    }

    @Test
    void testWageSortEmptyList() {
        job.clear();
        job = wage.sortList(job);
        assertEquals(0, job.size());
    }

    @Test
    void testWageSortListWithNull() {
        job.add(null);
        job = wage.sortList(job);
        assertEquals(null, job.get(job.size()-1));
    }

    @Test
    void testWageSortWithCopyJobs() {
        JobListing jobl = new JobListing("hello", "HelloAgain", "Description", 10000);
        job.add(jobl);
        job.add(jobl);
        job = wage.sortList(job);
        assertEquals(job.get(0).getWage(), job.get(1).getWage());
    }

    @Test
    void testWageSortWithOneThousandJobs() {
        job.clear();
        for(int i = 999; i > 0; i--) {
            job.add(new JobListing(i+"", i+"", i+"", i));
        }
        job = wage.sortList(job);
        assertEquals(999, job.get(0).getWage());
    }
}
