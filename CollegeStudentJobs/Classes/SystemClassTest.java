package Classes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SystemClassTest {
    @BeforeEach
    public void setup() {
        StudentList.getInstance().getStudents().clear();
        BusinessList.getInstance().getBusinesses().clear();
        AdminList.getInstance().getAdmins().clear();
        ProfessorList.getInstance().getProfessors().clear();
    }

    @AfterEach
    public void tearDown() {
            StudentList.getInstance().getStudents().clear();
            BusinessList.getInstance().getBusinesses().clear();
            AdminList.getInstance().getAdmins().clear();
            ProfessorList.getInstance().getProfessors().clear();
    }

    @Test 
    void testCreateStudentAccount() {
        assertEquals(true, SystemClass.getInstance().createStudentAccount("email", "password", "firstName", "lastName", "phoneNumber"));
    }

    @Test
    void testCreateStudentWithNulls() {
        assertEquals(true, SystemClass.getInstance().createStudentAccount(null, null, null, null, null));
    }

    @Test 
    void testCreateEmptyStudentAccount() {
        assertEquals(true, SystemClass.getInstance().createStudentAccount("", "", "", "", ""));
    }

    @Test
    void testCreateBusiness() {
        assertEquals(true, SystemClass.getInstance().createBusinessAccount("email", "password", "companyName", "companyAddress"));
    }

    @Test
    void testCreateBusinessWithNulls() {
        assertEquals(true, SystemClass.getInstance().createBusinessAccount(null, null, null, null));
    }

    @Test
    void testCreateEmptyBusiness() {
        assertEquals(true, SystemClass.getInstance().createBusinessAccount("", "", "", ""));
    }

    @Test
    void testCreateProfessorAccount() {
        assertEquals(true, SystemClass.getInstance().createProfessorAccount("email", "password", "firstName", "lastName"));
    }

    @Test
    void testCreateNullProfessorAccount() {
        assertEquals(true, SystemClass.getInstance().createProfessorAccount(null, null, null, null));
    }

    @Test
    void testCreateEmptyProfessorAccount() {
        assertEquals(true, SystemClass.getInstance().createProfessorAccount("", "", "", ""));
    }

    @Test
    void testCreateAdminAccount() {
        assertEquals(true, SystemClass.getInstance().createAdminAccount("email", "password"));
    }

    @Test
    void testCreateAdminAccountWithNulls() {
        assertEquals(true, SystemClass.getInstance().createAdminAccount(null, null));
    }

    @Test
    void testCreateEmptyAdminAccount() {
        assertEquals(true, SystemClass.getInstance().createAdminAccount("", ""));
    }

    @Test
    void testRemoveStudent() {
        Student stu = new Student("firstName", "lastName", "email", "phoneNumber", "password");
        StudentList.getInstance().getStudents().add(stu);
        assertEquals("firstName", StudentList.getInstance().getStudents().get(0).getFirstName());
        SystemClass.getInstance().removeStudent(stu);
        assertEquals(0, StudentList.getInstance().getStudents().size());
    }

    @Test
    void testRemoveNullStudent() {;
        StudentList.getInstance().getStudents().add(null);
        assertEquals("firstName", StudentList.getInstance().getStudents().get(0).getFirstName());
        SystemClass.getInstance().removeStudent(null);
        assertEquals(0, StudentList.getInstance().getStudents().size());
    }

    @Test
    void testRemoveEmptyStudent() {
        Student stu = new Student("", "", "", "", "");
        StudentList.getInstance().getStudents().add(stu);
        assertEquals("", StudentList.getInstance().getStudents().get(0).getFirstName());
        SystemClass.getInstance().removeStudent(stu);
        assertEquals(0, StudentList.getInstance().getStudents().size());
    }

    @Test
    void testRemoveBusiness() {
        Business bus = new Business("businessName", "email", "password", "address");
        BusinessList.getInstance().getBusinesses().add(bus);
        assertEquals("businessName", BusinessList.getInstance().getBusinesses().get(0).getBusinessName());
        SystemClass.getInstance().removeBusiness(bus);
        assertEquals(0, BusinessList.getInstance().getBusinesses().size());
    }

    @Test
    void testRemoveNullBusiness() {
        BusinessList.getInstance().getBusinesses().add(null);
        assertEquals(null, BusinessList.getInstance().getBusinesses().get(0).getBusinessName());
        SystemClass.getInstance().removeBusiness(null);
        assertEquals(0, BusinessList.getInstance().getBusinesses().size());
    }

    @Test
    void testRemoveEmptyBusiness() {
        Business bus = new Business("", "", "", "");
        BusinessList.getInstance().getBusinesses().add(bus);
        assertEquals("", BusinessList.getInstance().getBusinesses().get(0).getBusinessName());
        SystemClass.getInstance().removeBusiness(bus);
        assertEquals(0, BusinessList.getInstance().getBusinesses().size());
    }

    @Test
    void testRemoveAdmin() {
        Admin ad = new Admin("email", "password");
        AdminList.getInstance().addAdmin(ad);  
        assertEquals("email", AdminList.getInstance().getAdmins().get(0).getEmail());
        SystemClass.getInstance().removeAdmin(ad);
        assertEquals(0, AdminList.getInstance().getAdmins().size());
    }

    @Test
    void testRemoveNullAdmin() {
        AdminList.getInstance().addAdmin(null);  
        assertEquals(null, AdminList.getInstance().getAdmins().get(0).getEmail());
        SystemClass.getInstance().removeAdmin(null);
        assertEquals(0, AdminList.getInstance().getAdmins().size());
    }

    @Test
    void testRemoveEmptyAdmin() {
        Admin ad = new Admin("", "");
        AdminList.getInstance().addAdmin(ad);  
        assertEquals("", AdminList.getInstance().getAdmins().get(0).getEmail());
        SystemClass.getInstance().removeAdmin(ad);
        assertEquals(0, AdminList.getInstance().getAdmins().size());
    }

    @Test
    void testRemoveProfessor() {
        Professor prof = new Professor("firstName", "lastName", "email", "password");
        ProfessorList.getInstance().getProfessors().add(prof);
        assertEquals("firstName", ProfessorList.getInstance().getProfessors().get(0).getFirstName());
        SystemClass.getInstance().removeProfessor(prof);
        assertEquals(0, ProfessorList.getInstance().getProfessors().size());
    }

    @Test
    void testRemoveNullProfessor() {
        ProfessorList.getInstance().getProfessors().add(null);
        assertEquals(null, ProfessorList.getInstance().getProfessors().get(0).getFirstName());
        SystemClass.getInstance().removeProfessor(null);
        assertEquals(0, ProfessorList.getInstance().getProfessors().size());
    }

    @Test
    void testRemoveEmptyProfessor() {
        Professor prof = new Professor("", "", "", "");
        ProfessorList.getInstance().getProfessors().add(prof);
        assertEquals("", ProfessorList.getInstance().getProfessors().get(0).getFirstName());
        SystemClass.getInstance().removeProfessor(prof);
        assertEquals(0, ProfessorList.getInstance().getProfessors().size());
    }

    @Test
    void testAddJobListing() {
        SystemClass.getInstance().getJobListingList().clear();
        JobListing job = new JobListing("businessName", "jobTitle", "jobDescription", 10);
        SystemClass.getInstance().addJobListing(job);
        assertEquals("businessName", SystemClass.getInstance().getJobListingList().get(0).getBusinessName());
    }

    @Test
    void testAddNullJobListing() {
        SystemClass.getInstance().getJobListingList().clear();
        SystemClass.getInstance().addJobListing(null);
        assertEquals(null, SystemClass.getInstance().getJobListingList().get(0).getBusinessName());
    }

    @Test
    void testAddingEmptyJobListing() {
        SystemClass.getInstance().getJobListingList().clear();
        JobListing job = new JobListing("", "", "", 10);
        SystemClass.getInstance().addJobListing(job);
        assertEquals("", SystemClass.getInstance().getJobListingList().get(0).getBusinessName());
    }

    @Test
    void testRemovingJobListing() {
        SystemClass.getInstance().getJobListingList().clear();
        JobListing job = new JobListing("businessName", "jobTitle", "jobDescription", 0); 
        JobListingList.getInstance().addJobListing(job);
        SystemClass.getInstance().removeJobListing(job);
        assertEquals(0, JobListingList.getInstance().getJobListings().size());
    }

    @Test
    void testRemovingNullJobListing() {
        SystemClass.getInstance().getJobListingList().clear();
        JobListingList.getInstance().addJobListing(null);
        SystemClass.getInstance().removeJobListing(null);
        assertEquals(0, JobListingList.getInstance().getJobListings().size());
    }

    @Test
    void testRemovingEmptyJobListing() {
        SystemClass.getInstance().getJobListingList().clear();
        JobListing job = new JobListing("", "", "", 0); 
        JobListingList.getInstance().addJobListing(job);
        SystemClass.getInstance().removeJobListing(job);
        assertEquals(0, JobListingList.getInstance().getJobListings().size());
    }

    @Test
    void testLoginExistingStudent() {
        Student stu = new Student("firstName", "lastName", "email", "phoneNumber", "password");
        StudentList.getInstance().getStudents().add(stu);
        assertEquals(true, SystemClass.getInstance().login("email", "password", "Student"));
    }

    @Test
    void testLoginNonExistingStudent() {
        assertEquals(false, SystemClass.getInstance().login("email", "password", "Student"));
    }

    @Test
    void testLoginExistingBusiness() {
        Business buss = new Business("businessName", "email", "password", "address");
        BusinessList.getInstance().getBusinesses().add(buss);
        assertEquals(true, SystemClass.getInstance().login("email", "password", "Business"));
    }

    @Test
    void testLoginNonExistingBusiness() {
        assertEquals(false, SystemClass.getInstance().login("email", "password", "Business"));
    }

    @Test
    void testLoginExistingProfessor() {
        Professor prof = new Professor("firstName", "lastName", "email", "password");
        ProfessorList.getInstance().getProfessors().add(prof);
        assertEquals(true, SystemClass.getInstance().login("email", "password", "Professor"));
    }

    @Test
    void testLoginNonExistingProfessor() {
        assertEquals(false, SystemClass.getInstance().login("email", "password", "Professor"));
    }

    @Test
    void testLoginExistingAdmin() {
        Admin prof = new Admin("email", "password");
        AdminList.getInstance().getAdmins().add(prof);
        assertEquals(true, SystemClass.getInstance().login("email", "password", "Admin"));
    }

    @Test
    void testRemoveExperience() {
        ExperienceList.getInstance().getExperiences().clear();
        Experience exp = new Experience("companyName", "startDate", "endDate");
        ExperienceList.getInstance().addExperience(exp);
        SystemClass.getInstance().removeExperience(exp);
        assertEquals(0, ExperienceList.getInstance().getExperiences().size());
    }

    @Test
    void testRemoveNullExperience() {
        ExperienceList.getInstance().getExperiences().clear();
        ExperienceList.getInstance().addExperience(null);
        SystemClass.getInstance().removeExperience(null);
        assertEquals(0, ExperienceList.getInstance().getExperiences().size());
    }

    @Test
    void testRemoveEmptyExperience() {
        ExperienceList.getInstance().getExperiences().clear();
        Experience exp = new Experience("", "", "");
        ExperienceList.getInstance().addExperience(exp);
        SystemClass.getInstance().removeExperience(exp);
        assertEquals(0, ExperienceList.getInstance().getExperiences().size());
    }

    @Test
    void testAddReview() {
        ReviewList.getInstance().getReviews().clear();
        Review rev = new Review(0, "comment", "firstName", "lastName");
        SystemClass.getInstance().addReview(rev);
        assertEquals("comment", ReviewList.getInstance().getReviews().get(0).getComment());
    }

    @Test
    void testAddNullReview() {
        ReviewList.getInstance().getReviews().clear();
        SystemClass.getInstance().addReview(null);
        assertEquals(null, ReviewList.getInstance().getReviews().get(0).getComment());
    }

    @Test
    void testAddEmptyReview() {
        ReviewList.getInstance().getReviews().clear();
        Review rev = new Review(0, "", "", "");
        SystemClass.getInstance().addReview(rev);
        assertEquals("", ReviewList.getInstance().getReviews().get(0).getComment());
    }

    @Test
    void testRemoveReview() {
        ReviewList.getInstance().getReviews().clear();
        Review rev = new Review(0, "comment", "firstName", "lastName");
        SystemClass.getInstance().addReview(rev);
        SystemClass.getInstance().removeReview(rev);
        assertEquals(0, ReviewList.getInstance().getReviews().size());
    }

    @Test
    void testRemoveNullReview() {
        ReviewList.getInstance().getReviews().clear();
        SystemClass.getInstance().addReview(null);
        SystemClass.getInstance().removeReview(null);
        assertEquals(0, ReviewList.getInstance().getReviews().size());
    }

    @Test
    void testRemoveEmptyReivew() {
        ReviewList.getInstance().getReviews().clear();
        Review rev = new Review(0, "", "", "");
        SystemClass.getInstance().addReview(rev);
        SystemClass.getInstance().removeReview(rev);
        assertEquals(0, ReviewList.getInstance().getReviews().size());
    }

    @Test
    void testAddResume() {
        ResumeList.getInstance().getResumes().clear();
        Resume res = new Resume("firstName", "lastName", "email", "phoneNumber");
        SystemClass.getInstance().addResume(res);
        assertEquals("firstName", ResumeList.getInstance().getResumes().get(0).getFirstName());
    }

    @Test
    void testAddNullResume() {
        ResumeList.getInstance().getResumes().clear();
        SystemClass.getInstance().addResume(null);
        assertEquals(null, ResumeList.getInstance().getResumes().get(0).getFirstName());
    }

    @Test
    void testAddEmptyResume() {
        ResumeList.getInstance().getResumes().clear();
        Resume res = new Resume("", "", "", "");
        SystemClass.getInstance().addResume(res);
        assertEquals("", ResumeList.getInstance().getResumes().get(0).getFirstName());
    }

    @Test
    void testAddExperience() {
        ExperienceList.getInstance().getExperiences().clear();
        Experience exp = new Experience("companyName", "startDate", "endDate");
        SystemClass.getInstance().addExperience(exp);
        assertEquals("companyName", ExperienceList.getInstance().getExperiences().get(0).getCompanyName());
    }

    @Test
    void testAddNullExperience() {
        ExperienceList.getInstance().getExperiences().clear();
        SystemClass.getInstance().addExperience(null);
        assertEquals(null, ExperienceList.getInstance().getExperiences().get(0).getCompanyName());
    }

    @Test
    void testAddEmptyExperience() {
        ExperienceList.getInstance().getExperiences().clear();
        Experience exp = new Experience("", "", "");
        SystemClass.getInstance().addExperience(exp);
        assertEquals("", ExperienceList.getInstance().getExperiences().get(0).getCompanyName());
    }
}
