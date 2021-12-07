package Classes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
public class DataLoaderTest {
    StudentList studentList = StudentList.getInstance();
    BusinessList businessList = BusinessList.getInstance();
    JobListingList jobList = JobListingList.getInstance();
    AdminList adminList = AdminList.getInstance();
    DeletedUserList deletedUsers = DeletedUserList.getInstance();
    ExperienceList experienceList = ExperienceList.getInstance();
    ProfessorList professorList = ProfessorList.getInstance();
    ResumeList resumeList = ResumeList.getInstance();
    ReviewList reviews = ReviewList.getInstance();

    @BeforeEach
    public void setup() {
        Student student = new Student("firstName", "lastName", "email", "phoneNumber", "password");
        Student student2 = new Student("firstName1", "lastName1", "email1", "phoneNumber1", "password1");
        Business bus1 = new Business("businessName", "email", "password", "address");
        Business bus2 = new Business("businessName1", "email1", "password1", "address1");
        JobListing job1 = new JobListing("businessName", "jobTitle", "jobDescription", 5);
        JobListing job2 = new JobListing("businessName1", "jobTitle1", "jobDescription1", 10);
        bus1.addJobListing(job1);
        bus2.addJobListing(job2);
        job1.addAppliedStudent(student);
        job2.addAppliedStudent(student2);
        job1.addExperience("experience");
        job2.addExperience("experience2");
        job1.addSkill("skill");
        job2.addSkill("skill2");
        Review review1 = new Review(5, "comment", "firstName", "lastName");
        Review review2 = new Review(5, "comment1", "firstName1", "lastName1");
        job1.addReview(review1);
        job2.addReview(review2);
        Resume res = new Resume("firstName", "lastName", "email", "phoneNumber");
        Resume res1 = new Resume("firstName1", "lastName1", "email1", "phoneNumber1");
        Experience exp = new Experience("companyName", "startDate", "endDate");
        Experience exp2 = new Experience("companyName1", "startDate1", "endDate1");
        exp.addDescription("description");
        exp.addDescription("description2");
        res1.addExperience(exp);
        res1.addExperience(exp2);
        student.setResume(res);
        student2.setResume(res1);
        Professor prof = new Professor("firstName2", "lastName2", "email2", "password2");
        Professor prof2 = new Professor("firstName3", "lastName3", "email3", "password3");
        Review review3 = new Review(5, "comment3", "firstName3", "lastName3");
        Review review4 = new Review(6, "comment4", "firstName4", "lastName4");
        prof.addReview(review3, student);
        prof2.addReview(review4, student2);
        Admin admin = new Admin("email", "password");
        Admin admin2 = new Admin("email1", "password1");
        User user = new User("email", "password");
        User user2 = new User("email1", "password1"); 
        studentList.getStudents().clear(); 
        businessList.getBusinesses().clear();
        jobList.getJobListings().clear();
        adminList.getAdmins().clear();
        deletedUsers.getDeletedUsers().clear();
        experienceList.getExperiences().clear();
        professorList.getProfessors().clear();
        resumeList.getResumes().clear();
        reviews.getReviews().clear();
        studentList.getStudents().add(student);
        studentList.getStudents().add(student2);
        businessList.getBusinesses().add(bus1);
        businessList.getBusinesses().add(bus2);
        jobList.addJobListing(job1);
        jobList.addJobListing(job2);
        reviews.addReviewToList(review1);
        reviews.addReviewToList(review2);
        reviews.addReviewToList(review3);
        reviews.addReviewToList(review4);
        experienceList.addExperience(exp);
        experienceList.addExperience(exp2);
        resumeList.addResume(res);
        resumeList.addResume(res1);
        professorList.getProfessors().add(prof);
        professorList.getProfessors().add(prof2);
        adminList.addAdmin(admin);
        adminList.addAdmin(admin2);
        deletedUsers.getDeletedUsers().add(user);
        deletedUsers.getDeletedUsers().add(user2);
        SystemClass.logout();
    }

    @AfterEach
    public void tearDown() {
        studentList.getStudents().clear(); 
        businessList.getBusinesses().clear();
        jobList.getJobListings().clear();
        adminList.getAdmins().clear();
        deletedUsers.getDeletedUsers().clear();
        experienceList.getExperiences().clear();
        professorList.getProfessors().clear();
        resumeList.getResumes().clear();
        reviews.getReviews().clear();
        SystemClass.logout();
    }

    @Test
    void testStudentSize() {
        assertEquals(2, DataLoader.getStudentList().size());
    }

    @Test
    void testBusinessSize() {
        assertEquals(2, DataLoader.getBusinessList().size());
    }

    @Test 
    void testJobListingSize() {
        assertEquals(2, DataLoader.getJobListingList().size());
    }

    @Test 
    void testReviewListSize() {
        assertEquals(4, DataLoader.getReviewList().size());
    }

    @Test
    void testResumeListSize() {
        assertEquals(2, DataLoader.getResumeList().size());
    }

    @Test
    void testDeletedUsersSize() {
        assertEquals(2, DataLoader.getDeletedUserList().size());
    }

    @Test
    void testExperienceListSize() {
        assertEquals(2, DataLoader.getExperienceList().size());
    }

    @Test
    void testAdminListSize() {
        assertEquals(2, DataLoader.getAdminList().size());
    }

    @Test
    void testProfessorListSize() {
        assertEquals(2, DataLoader.getProfessorList().size());
    }

    @Test
    void testLoadingStudent() {
        assertEquals("firstName", DataLoader.getStudentList().get(0).getFirstName());
    }

    @Test
    void testLoadingBusiness() {
        assertEquals("businessName", DataLoader.getBusinessList().get(0).getBusinessName());
    }

    @Test
    void testLoadingJobLising() {
        assertEquals("jobTitle", DataLoader.getJobListingList().get(0).getJobTitle());
    }

    @Test
    void testLoadingAdmin() {
        assertEquals("email", DataLoader.getAdminList().get(0).getEmail());
    }

    @Test
    void testLoadingDeletedUsers() {
        assertEquals("email", DataLoader.getDeletedUserList().get(0).getEmail());
    }

    @Test
    void testExperienceList() {
        assertEquals("companyName", DataLoader.getExperienceList().get(0).getCompanyName());
    }

    @Test
    void testLoadingProfessor() {
        assertEquals("firstName2", DataLoader.getProfessorList().get(0).getFirstName());
    }

    @Test
    void testLoadingReview() {
        assertEquals("comment", DataLoader.getReviewList().get(0).getComment());
    }

    @Test
    void testLoadingResume() {
        assertEquals("firstName", DataLoader.getResumeList().get(0).getFirstName());
    }

    @Test
    void testStudentLoadingResumeExperience() {
        Student stu = DataLoader.getStudentList().get(0);
        assertEquals("companyName", stu.getResume().getExperience().get(0).getCompanyName());
    }

    @Test
    void testStudentLoadingResumes() {
        assertEquals("firstName", DataLoader.getStudentList().get(0).getResume().getFirstName());
    }

    @Test
    void testStudentLoadingReviews() {
        assertEquals("comment2", DataLoader.getStudentList().get(0).getProfessorReviews().get(0));
    }

    @Test 
    void testBusinessLoadingJobListing() {
        assertEquals("jobTitle", DataLoader.getBusinessList().get(0).getJobListings().get(0).getJobTitle());
    }

    @Test
    void testJobListingLoadingExperiences() {
        assertEquals("experience", DataLoader.getJobListingList().get(0).getExperienceList().get(0));
    }

    @Test
    void testJobListingLoadingStudents() {
        assertEquals("firstName", DataLoader.getJobListingList().get(0).getAppliedStudents().get(0).getFirstName());
    }

    @Test
    void testJobListingLoadingReviews() {
        assertEquals("comment", DataLoader.getJobListingList().get(0).getReviews().get(0).getComment());
    }

    @Test
    void testProfessorLoadingReviews() {
        assertEquals("comment2", DataLoader.getProfessorList().get(0).getCreatedReviews().get(0).getComment());
    }

    @Test
    void testResumeLoadingExperience() {
        assertEquals("companyName", DataLoader.getResumeList().get(0).getExperience().get(0).getCompanyName());
    }

    @Test
    void testLoadingNullStudent() {
        studentList.getStudents().add(null);
        DataWriter.saveStudents();
        assertEquals(null, DataLoader.getStudentList().get(2));
    }

    @Test
    void testLoadingNullBusiness() {
        businessList.getBusinesses().add(null);
        DataWriter.saveBusiness();
        assertEquals(null, DataLoader.getBusinessList().get(2));
    }

    @Test
    void testLoadingNullJobListing() {
        jobList.addJobListing(null);
        DataWriter.saveJobListing();
        assertEquals(null, DataLoader.getJobListingList().get(2));
    }

    @Test
    void testLoadingNullExperience() {
        experienceList.addExperience(null);
        DataWriter.saveExperienceList();
        assertEquals(null, DataLoader.getExperienceList().get(2));
    }

    @Test
    void testLoadingNullReview() {
        reviews.addReviewToList(null);
        DataWriter.saveReviewList();
        assertEquals(null, DataLoader.getReviewList().get(4));
    }

    @Test
    void testLoadingNullResume() {
        resumeList.addResume(null);
        DataWriter.saveResumeList();
        assertEquals(null, DataLoader.getResumeList().get(2));
    }

    @Test
    void testLoadingNullAdmin() {
        adminList.addAdmin(null);
        DataWriter.saveAdminList();
        assertEquals(null, DataLoader.getAdminList().get(2));
    }

    @Test
    void testLoadingNullProfessor() {
        professorList.getProfessors().add(null);
        DataWriter.saveProfessor();
        assertEquals(null, DataLoader.getProfessorList().get(2));
    }

    
}
