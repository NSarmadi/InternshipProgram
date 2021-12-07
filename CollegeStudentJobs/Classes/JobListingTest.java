package Classes;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;


public class JobListingTest {
    @BeforeEach
    public void setup(){
        JobListingList List = JobListingList.getInstance();
        List.getJobListings().clear();
    }

    @Test
    public void testAddNewReview(){
        JobListingList.getInstance().getJobListings().clear();
        JobListing job = new JobListing("businessName", "jobTitle", "jobDescription", 10);
        job.addReview(new Review(0, "comment", "firstName", "lastName"));
        assertEquals("comment", job.getReviews().get(0).getComment());
    }

    @Test
    public void testAddEmptyReview(){
        JobListingList.getInstance().getJobListings().clear();
        JobListing job = new JobListing("businessName", "jobTitle", "jobDescription", 10);
        job.addReview(new Review(0, "", "", ""));
        assertEquals("", job.getReviews().get(0).getComment());
    }

    @Test
    public void testAddNullReview(){
        JobListingList.getInstance().getJobListings().clear();
        JobListing job = new JobListing("businessName", "jobTitle", "jobDescription", 10);
        job.addReview(null);
        assertEquals(null, job.getReviews().get(0));
    }

    @Test
    public void testMultipleReview(){
        JobListingList.getInstance().getJobListings().clear();
        JobListing job = new JobListing("businessName", "jobTitle", "jobDescription", 10);
        job.addReview(new Review(0, "comment", "firstName", "lastName"));
        job.addReview(new Review(0, "1", "1", "1"));
        job.addReview(new Review(0, "2", "2", "2"));
        job.addReview(new Review(0, "3", "3", "3"));
        assertEquals(4, job.getReviews().size());
    }

    @Test
    public void testRemoveReview(){
        JobListingList.getInstance().getJobListings().clear();
        JobListing job = new JobListing("businessName", "jobTitle", "jobDescription", 10);
        job.getReviews().clear();
        job.addReview(new Review(0, "comment", "firstName", "lastName"));
        job.addReview(new Review(0, "1", "1", "1"));
        job.addReview(new Review(0, "2", "2", "2"));
        job.addReview(new Review(0, "3", "3", "3"));
        job.removeReview(new Review(0, "comment", "firstName", "lastName"));
        assertEquals(3, job.getReviews().size());
    }

    @Test
    public void testAddExperience(){
        JobListingList.getInstance().getJobListings().clear();
        JobListing job = new JobListing("businessName", "jobTitle", "jobDescription", 10);
        job.addExperience(new Experience("companyName", "startDate", "endDate").toString());
        assertEquals(new Experience("companyName", "startDate", "endDate").toString(), job.getExperienceList().get(0));
    }

    @Test
    public void testAddEmptyExperience(){
        JobListingList.getInstance().getJobListings().clear();
        JobListing job = new JobListing("businessName", "jobTitle", "jobDescription", 10);
        job.addExperience(new Experience("", "", "").toString());
        assertEquals(new Experience("", "", "").toString(), job.getExperienceList().get(0));
    }

    @Test
    public void testAddNullExperience(){
        JobListingList.getInstance().getJobListings().clear();
        JobListing job = new JobListing("businessName", "jobTitle", "jobDescription", 10);
        job.addExperience(null);
        assertEquals(null, job.getExperienceList().get(0));
    }

    @Test
    public void testAddMultipleExperience(){
        JobListingList.getInstance().getJobListings().clear();
        JobListing job = new JobListing("businessName", "jobTitle", "jobDescription", 10);
        job.addExperience(new Experience("w", "w", "w").toString());
        job.addExperience(new Experience("q", "q", "q").toString());
        job.addExperience(new Experience("y", "y", "y").toString());
        job.addExperience(new Experience("x", "x", "x").toString());
        assertEquals(4, job.getExperienceList().size());
    }

    @Test
    public void testRemoveExperience(){
        JobListingList.getInstance().getJobListings().clear();
        JobListing job = new JobListing("businessName", "jobTitle", "jobDescription", 10);
        job.addExperience(new Experience("w", "w", "w").toString());
        job.addExperience(new Experience("q", "q", "q").toString());
        job.addExperience(new Experience("y", "y", "y").toString());
        job.addExperience(new Experience("x", "x", "x").toString());
        job.removeExperience(2);
        assertEquals(3, job.getExperienceList().size());
    }

    @Test
    public void testaddSkill(){
        JobListingList.getInstance().getJobListings().clear();
        JobListing job = new JobListing("businessName", "jobTitle", "jobDescription", 10);
        job.addSkill("Bling");
        assertEquals("Bling", job.getSkillList().get(0));
    }

    @Test
    public void testaddMultipleSkills(){
        JobListingList.getInstance().getJobListings().clear();
        JobListing job = new JobListing("businessName", "jobTitle", "jobDescription", 10);
        job.addSkill("Bling");
        job.addSkill("BLing");
        job.addSkill("BLIng");
        job.addSkill("BLINg");
        assertEquals(4, job.getSkillList().size());
    }

    @Test
    public void testAddEmptySkill(){
        JobListingList.getInstance().getJobListings().clear();
        JobListing job = new JobListing("businessName", "jobTitle", "jobDescription", 10);
        job.addSkill("");
        assertEquals("", job.getSkillList().get(0));
    }

    @Test
    public void testAddNullSkill(){
        JobListingList.getInstance().getJobListings().clear();
        JobListing job = new JobListing("businessName", "jobTitle", "jobDescription", 10);
        job.addSkill(null);
        assertEquals(null, job.getSkillList().get(0));
    }

    @Test
    public void testRemoveSkill(){
        JobListingList.getInstance().getJobListings().clear();
        JobListing job = new JobListing("businessName", "jobTitle", "jobDescription", 10);
        job.addSkill("Bling");
        job.addSkill("BLing");
        job.addSkill("BLIng");
        job.addSkill("BLINg");
        job.removeSkill(2);
        assertEquals(3, job.getSkillList().size());
    }

    @Test
    public void testAddAppliedStudent(){
        JobListingList.getInstance().getJobListings().clear();
        JobListing job = new JobListing("businessName", "jobTitle", "jobDescription", 10);
        job.addAppliedStudent(new Student("firstName", "lastName", "email", "phoneNumber", "password"));
        assertEquals("firstName", job.getAppliedStudents().get(0).getFirstName());
    }

    @Test
    public void testAddMultipleAppliedStudent(){
        JobListingList.getInstance().getJobListings().clear();
        JobListing job = new JobListing("businessName", "jobTitle", "jobDescription", 10);
        job.addAppliedStudent(new Student("firstName", "lastName", "email", "phoneNumber", "password"));
        job.addAppliedStudent(new Student("firstName1", "lastName1", "ema1il", "pho1neNumber", "pass1word"));
        job.addAppliedStudent(new Student("fir2stName", "last2Name", "ema2il", "phoneNu2mber", "pa2ssword"));
        assertEquals(3, job.getAppliedStudents().size());
    }

    @Test
    public void testAddNullAppliedStudent(){
        JobListingList.getInstance().getJobListings().clear();
        JobListing job = new JobListing("businessName", "jobTitle", "jobDescription", 10);
        job.addAppliedStudent(null);
        assertEquals(null, job.getAppliedStudents().get(0));
    }

    


}
