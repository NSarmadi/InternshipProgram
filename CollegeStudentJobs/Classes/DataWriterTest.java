package Classes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class DataWriterTest {

    @BeforeEach
    public void setup() {
        StudentList studentList = StudentList.getInstance();
        studentList.getStudents().clear(); 
        BusinessList businessList = BusinessList.getInstance();
        businessList.getBusinesses().clear();
        JobListingList jobList = JobListingList.getInstance();
        jobList.getJobListings().clear();
        AdminList adminList = AdminList.getInstance();
        adminList.getAdmins().clear();
        DeletedUserList deletedUsers = DeletedUserList.getInstance();
        deletedUsers.getDeletedUsers().clear();
        ExperienceList experienceList = ExperienceList.getInstance();
        experienceList.getExperiences().clear();
        ProfessorList professorList = ProfessorList.getInstance();
        professorList.getProfessors().clear();
        ResumeList resumeList = ResumeList.getInstance();
        resumeList.getResumes().clear();
        ReviewList reviews = ReviewList.getInstance();
        reviews.getReviews().clear();
    }

    @Test
    void testAddingNoStudent() {
        StudentList studentList;
        studentList = StudentList.getInstance();
        studentList.getStudents().clear();
        DataWriter.saveStudents();
        assertEquals(0, DataLoader.getStudentList().size());
    }

    @Test
    void testAddingOneStudent() {
        StudentList studentList = StudentList.getInstance();
        studentList.getStudents().clear(); 
        studentList.getStudents().add(new Student("Carter", "Marlowe", "carter@email.edu", "888-888-8888", "apassword"));
        DataWriter.saveStudents();
        assertEquals("Carter", DataLoader.getStudentList().get(0).getFirstName());
    }

    @Test
    void testAddingMultipleStudents() {
        StudentList studentList = StudentList.getInstance();
        studentList.getStudents().clear(); 
        studentList.getStudents().add(new Student("Carter", "Marlowe", "carter@email.edu", "888-888-8888", "apassword"));
        studentList.getStudents().add(new Student("Jimmy", "Man", "jimmy@email.edu", "999-999-9999", "packword"));
        studentList.getStudents().add(new Student("Jenny", "Jones", "jj@email.edu", "777-777-7777", "asdfdas"));
        studentList.getStudents().add(new Student("APerson", "AlastName", "Aperson@email.edu", "555-555-5555", "kfkkdkds"));
        studentList.getStudents().add(new Student("This is a person", "i have a last name", "email@email.edu", "333-333-3333", "iamnotarobot"));
        DataWriter.saveStudents();
        assertEquals("Jenny", DataLoader.getStudentList().get(2).getFirstName());
    }

    @Test
    void testAddingOneStudentAllFields() {
        StudentList studentList = StudentList.getInstance();
        studentList.getStudents().clear(); 
        studentList.getStudents().add(new Student("Carter", "Marlowe", "carter@email.edu", "888-888-8888", "apassword"));
        DataWriter.saveStudents();
        assertEquals("Carter", DataLoader.getStudentList().get(0).getFirstName());
        assertEquals("Marlowe", DataLoader.getStudentList().get(0).getLastName());
        assertEquals("carter@email.edu", DataLoader.getStudentList().get(0).getEmail());
        assertEquals("888-888-8888", DataLoader.getStudentList().get(0).getPhoneNumber());
        assertEquals("apassword", DataLoader.getStudentList().get(0).getPassword());
    }

    @Test
    void testAddingMultipleStudentsAllFields() {
        StudentList studentList = StudentList.getInstance();
        studentList.getStudents().clear(); 
        studentList.getStudents().add(new Student("Carter", "Marlowe", "carter@email.edu", "888-888-8888", "apassword"));
        studentList.getStudents().add(new Student("Jimmy", "Man", "jimmy@email.edu", "999-999-9999", "packword"));
        studentList.getStudents().add(new Student("Jenny", "Jones", "jj@email.edu", "777-777-7777", "asdfdas"));
        studentList.getStudents().add(new Student("APerson", "AlastName", "Aperson@email.edu", "555-555-5555", "kfkkdkds"));
        studentList.getStudents().add(new Student("This is a person", "i have a last name", "email@email.edu", "333-333-3333", "iamnotarobot"));
        DataWriter.saveStudents();
        assertEquals("Jenny", DataLoader.getStudentList().get(2).getFirstName());
        assertEquals("Jones", DataLoader.getStudentList().get(2).getLastName());
        assertEquals("jj@email.edu", DataLoader.getStudentList().get(2).getEmail());
        assertEquals("777-777-7777", DataLoader.getStudentList().get(2).getPhoneNumber());
        assertEquals("asdfdas", DataLoader.getStudentList().get(2).getPassword());
    }

    @Test
    void testAddingEmptyStudent() {
        StudentList studentList = StudentList.getInstance();
        studentList.getStudents().clear(); 
        studentList.getStudents().add(new Student("", "", "", "", ""));
        DataWriter.saveStudents();
        assertEquals("", DataLoader.getStudentList().get(0).getLastName());
    }

    @Test
    void testAddingNullStudent() {
        StudentList studentList = StudentList.getInstance();
        studentList.getStudents().clear(); 
        studentList.getStudents().add(null);
        DataWriter.saveStudents();
        assertEquals(null, DataLoader.getStudentList().get(0));
    }

    @Test 
    void testAddingNullFirstName() {
        StudentList studentList = StudentList.getInstance();
        studentList.getStudents().add(new Student(null, "", "", "", ""));
        DataWriter.saveStudents();
        assertEquals(null, DataLoader.getStudentList().get(0).getFirstName());
    }

    @Test
    void testStudentWithReview() {
        StudentList studentList = StudentList.getInstance();
        Student carter = new Student("Carter", "Marlowe", "carter@email.edu", "888-888-8888", "apassword");
        carter.addProfessorReview(new Review(5.0, "Cool", "FirstName", "LastName"));
        studentList.getStudents().add(carter);
        DataWriter.saveStudents();
        DataWriter.saveReviewList();
        assertEquals(5.0, DataLoader.getStudentList().get(0).getProfessorReviews().get(0).getRating());
    }

    @Test
    void testStudentWithResume() {
        StudentList studentList = StudentList.getInstance();
        Student carter = new Student("Carter", "Marlowe", "carter@email.edu", "888-888-8888", "apassword");
        studentList.getStudents().add(carter);
        DataWriter.saveStudents();
        assertEquals("Carter", DataLoader.getStudentList().get(0).getResume().getFirstName());
    }

    @Test 
    void testAddingOneBusiness() {
        BusinessList businessList = BusinessList.getInstance();
        businessList.getBusinesses().add(new Business("Twitter", "twitter@email.com", "secure", "111 Street Drive"));
        DataWriter.saveBusiness();
        assertEquals("Twitter", DataLoader.getBusinessList().get(0).getBusinessName());
    }

    @Test
    void testAddingThreeBusinesses() {
        BusinessList businessList = BusinessList.getInstance();
        businessList.getBusinesses().add(new Business("Twitter", "twitter@email.com", "secure", "111 Street Drive"));
        businessList.getBusinesses().add(new Business("Facebook", "Facebook@email.com", "Pass", "123 Street"));
        businessList.getBusinesses().add(new Business("Pizza Planet", "PP@email.com", "Lecool", "999 place road"));
        DataWriter.saveBusiness();
        assertEquals("Pizza Planet", DataLoader.getBusinessList().get(2).getBusinessName());
    }

    @Test 
    void testAddingOneBusinessAllFields() {
        BusinessList businessList = BusinessList.getInstance();
        businessList.getBusinesses().add(new Business("Twitter", "twitter@email.com", "secure", "111 Street Drive"));
        DataWriter.saveBusiness();
        assertEquals("Twitter", DataLoader.getBusinessList().get(0).getBusinessName());
        assertEquals("twitter@email.com", DataLoader.getBusinessList().get(0).getEmail());
        assertEquals("secure", DataLoader.getBusinessList().get(0).getPassword());
        assertEquals("111 Street Drive", DataLoader.getBusinessList().get(0).getAddress());
    }

    @Test
    void testAddingNullBusiness() {
        BusinessList businessList = BusinessList.getInstance();
        businessList.getBusinesses().add(null);
        DataWriter.saveBusiness();
        assertEquals(null, DataLoader.getBusinessList().get(0));
    }

    @Test
    void testAddingBusinessNullName() {
        BusinessList businessList = BusinessList.getInstance();
        businessList.getBusinesses().add(new Business(null, "twitter@email.com", "secure", "111 Street Drive"));
        DataWriter.saveBusiness();
        assertEquals(null, DataLoader.getBusinessList().get(0).getBusinessName());
    }

    @Test
    void buinessWithJoblisting() {
        BusinessList businessList = BusinessList.getInstance();
        Business bus = new Business("Twitter", "twitter@email.com", "secure", "111 Street Drive");
        bus.addJobListing(new JobListing("Twitter", "Title", "Description", 1.0));
        businessList.getBusinesses().add(bus);
        DataWriter.saveBusiness();
        DataWriter.saveJobListing();
        assertEquals("Title", DataLoader.getBusinessList().get(0).getJobListings().get(0).getJobTitle());
    }

    @Test
    void buinessWithNullJoblisting() {
        BusinessList businessList = BusinessList.getInstance();
        Business bus = new Business("Twitter", "twitter@email.com", "secure", "111 Street Drive");
        bus.addJobListing(null);
        businessList.getBusinesses().add(bus);
        DataWriter.saveBusiness();
        DataWriter.saveJobListing();
        assertEquals(null, DataLoader.getBusinessList().get(0).getJobListings().get(0));
    }

    @Test
    void addingJobListing() {
        JobListingList jobList = JobListingList.getInstance();
        jobList.getJobListings().add(new JobListing("Twitter", "Job title", "Job Description", 10));
        DataWriter.saveJobListing();
        assertEquals("Twitter", DataLoader.getJobListingList().get(0).getBusinessName());
    }

    @Test 
    void addingThreeJobListings() {
        JobListingList jobList = JobListingList.getInstance();
        jobList.getJobListings().add(new JobListing("Twitter", "Job title", "Job Description", 10));
        jobList.getJobListings().add(new JobListing("Name", "Job title1", "Job Description1", 14));
        jobList.getJobListings().add(new JobListing("NameAgain", "Job 2", "Job Description2", 15));
        DataWriter.saveJobListing();
        assertEquals("NameAgain", DataLoader.getJobListingList().get(2).getBusinessName());
    }

    @Test
    void addingJobListingWithReview() {
        JobListingList jobList = JobListingList.getInstance();
        JobListing job = new JobListing("Twitter", "Title", "Job Description", 10);
        job.addReview(new Review(10, "Comment", "FirstName", "LastName"));
        jobList.addJobListing(job);
        DataWriter.saveJobListing();
        DataWriter.saveReviewList();
        assertEquals("Comment", DataLoader.getJobListingList().get(0).getReviews().get(0).getComment());
    }

    @Test
    void addingJobListingWithExperiences() {
        JobListingList jobList = JobListingList.getInstance();
        JobListing job = new JobListing("Twitter", "Title", "Job Description", 10);
        job.addExperience("1");
        job.addExperience("2");
        jobList.addJobListing(job);
        DataWriter.saveJobListing();
        assertEquals("2", DataLoader.getJobListingList().get(0).getExperienceList().get(1));
    }

    @Test
    void addingJobListingWithSkills() {
        JobListingList jobList = JobListingList.getInstance();
        JobListing job = new JobListing("Twitter", "Title", "Job Description", 10);
        job.addSkill("1");
        job.addSkill("2");
        jobList.addJobListing(job);
        DataWriter.saveJobListing();
        assertEquals("2", DataLoader.getJobListingList().get(0).getSkillList().get(1));
    }

    @Test
    void addingNullJobListing() {
        JobListingList jobList = JobListingList.getInstance();
        jobList.addJobListing(null);
        DataWriter.saveJobListing();
        assertEquals(null, DataLoader.getJobListingList().get(0));
    }

    @Test
    void addingStudentToJobListing() {
        JobListingList jobList = JobListingList.getInstance();
        JobListing job = new JobListing("Twitter", "Title", "Job Description", 10);
        job.addAppliedStudent(new Student("firstName", "lastName", "email", "phonenumber", "password"));
        jobList.addJobListing(job);
        DataWriter.saveJobListing();
        DataWriter.saveStudents();
        assertEquals("firstName", DataLoader.getJobListingList().get(0).getAppliedStudents().get(0).getFirstName());
    }

    @Test
    void addOneAdmin() {
        AdminList adminList = AdminList.getInstance();
        adminList.addAdmin(new Admin("admin", "adpass"));
        DataWriter.saveAdminList();
        assertEquals("admin", DataLoader.getAdminList().get(0).getEmail());
    }

    @Test
    void addThreeAdmins() {
        AdminList adminList = AdminList.getInstance();
        adminList.addAdmin(new Admin("admin", "adpass"));
        adminList.addAdmin(new Admin("admin1", "adpass1"));
        adminList.addAdmin(new Admin("admin2", "adpass2"));
        DataWriter.saveAdminList();
        assertEquals("admin2", DataLoader.getAdminList().get(2).getEmail());
    }

    @Test
    void addEmptyAdmin() {
        AdminList adminList = AdminList.getInstance();
        adminList.addAdmin(new Admin("", ""));
        DataWriter.saveAdminList();
        assertEquals("", DataLoader.getAdminList().get(0).getEmail());
    }

    @Test
    void addNullAdmin() {
        AdminList adminList = AdminList.getInstance();
        adminList.addAdmin(null);
        DataWriter.saveAdminList();
        assertEquals(null, DataLoader.getAdminList().get(0));
    }

    @Test
    void addDeletedUser() {
        DeletedUserList deleted = DeletedUserList.getInstance();
        deleted.getDeletedUsers().add(new User("Email", "Password"));
        DataWriter.saveDeletedUsers();
        assertEquals("Email", DataLoader.getDeletedUserList().get(0).getEmail());
    }

    @Test
    void addEmptyUser() {
        DeletedUserList deleted = DeletedUserList.getInstance();
        deleted.getDeletedUsers().add(new User("", ""));
        DataWriter.saveDeletedUsers();
        assertEquals("", DataLoader.getDeletedUserList().get(0).getEmail());
    }

    @Test
    void addNullDeletedUser() {
        DeletedUserList deleted = DeletedUserList.getInstance();
        deleted.getDeletedUsers().add(null);
        DataWriter.saveDeletedUsers();
        assertEquals(null, DataLoader.getDeletedUserList().get(0));
    }

    @Test
    void addThreeDeletedUser() {
        DeletedUserList deleted = DeletedUserList.getInstance();
        deleted.getDeletedUsers().add(new User("Email", "Password"));
        deleted.getDeletedUsers().add(new User("Email1", "Password1"));
        deleted.getDeletedUsers().add(new User("Email2", "Password2"));
        DataWriter.saveDeletedUsers();
        assertEquals("Email2", DataLoader.getDeletedUserList().get(2).getEmail());
    }

    @Test
    void addOneExperience() {
        ExperienceList experienceList = ExperienceList.getInstance();
        experienceList.addExperience(new Experience("companyName", "Start Date", "end"));
        DataWriter.saveExperienceList();
        assertEquals("companyName", DataLoader.getExperienceList().get(0).getCompanyName());
    }

    @Test
    void addThreeExperiences() {
        ExperienceList experienceList = ExperienceList.getInstance();
        experienceList.addExperience(new Experience("companyName", "Start Date", "end"));
        experienceList.addExperience(new Experience("companyName1", "Start Date1", "end1"));
        experienceList.addExperience(new Experience("companyName2", "Start Date2", "end2"));
        DataWriter.saveExperienceList();
        assertEquals("companyName2", DataLoader.getExperienceList().get(2).getCompanyName());
    }

    @Test
    void addExperienceWithDescriptions() {
        ExperienceList experienceList = ExperienceList.getInstance();
        Experience exp = new Experience("name", "1", "2");
        exp.addDescription("description");
        exp.addDescription("description1");
        experienceList.addExperience(exp);
        DataWriter.saveExperienceList();
        assertEquals("description1", DataLoader.getExperienceList().get(0).getDescriptions().get(1));
    }

    @Test
    void addOneProfessor() {
        ProfessorList profList = ProfessorList.getInstance();
        profList.addProfessor("email", "pass", "name", "lastname");
        DataWriter.saveProfessor();
        assertEquals("email", DataLoader.getProfessorList().get(0).getEmail());
    }

    @Test
    void addThreeProfessors() {
        ProfessorList profList = ProfessorList.getInstance();
        profList.addProfessor("email", "pass", "name", "lastname");
        profList.addProfessor("email1", "pass1", "name1", "lastname1");
        profList.addProfessor("email2", "pass2", "name2", "lastname2");
        DataWriter.saveProfessor();
        assertEquals("email2", DataLoader.getProfessorList().get(2).getEmail());
    }

    @Test 
    void addProfessorWithReview() {
        ProfessorList profList = ProfessorList.getInstance();
        Professor prof = new Professor("firstName", "lastName", "email", "password");
        prof.createReview("comment", 10);
        profList.getProfessors().add(prof);
        DataWriter.saveProfessor();
        DataWriter.saveReviewList();
        assertEquals("comment", DataLoader.getProfessorList().get(0).getCreatedReviews().get(0).getComment());
    }

    @Test 
    void addProfessorWithNullReview() {
        ProfessorList profList = ProfessorList.getInstance();
        Professor prof = new Professor("firstName", "lastName", "email", "password");
        prof.getCreatedReviews().add(null);
        profList.getProfessors().add(prof);
        DataWriter.saveProfessor();
        DataWriter.saveReviewList();
        assertEquals(null, DataLoader.getProfessorList().get(0).getCreatedReviews().get(0));
    }

    @Test
    void addEmptyProfessor() {
        ProfessorList profList = ProfessorList.getInstance();
        profList.addProfessor("", "", "", "");
        DataWriter.saveProfessor();
        assertEquals("", DataLoader.getProfessorList().get(0).getEmail());
    }

    @Test
    void addNullProfessor() {
        ProfessorList profList = ProfessorList.getInstance();
        profList.getProfessors().add(null);
        DataWriter.saveProfessor();
        assertEquals(null, DataLoader.getProfessorList().get(0));
    }

    @Test
    void addOneResume() {
        ResumeList resumeList = ResumeList.getInstance();
        resumeList.addResume(new Resume("firstName", "lastName", "email", "phoneNumber"));
        DataWriter.saveResumeList();
        assertEquals("firstName", DataLoader.getResumeList().get(0).getFirstName());
    }

    @Test
    void addThreeResumes() {
        ResumeList resumeList = ResumeList.getInstance();
        resumeList.addResume(new Resume("firstName", "lastName", "email", "phoneNumber"));
        resumeList.addResume(new Resume("firstName1", "lastName1", "email1", "phoneNumber1"));
        resumeList.addResume(new Resume("firstName2", "lastName2", "email2", "phoneNumber2"));
        DataWriter.saveResumeList();
        assertEquals("firstName2", DataLoader.getResumeList().get(2).getFirstName());
    }

    @Test
    void addEmptyResume() {
        ResumeList resumeList = ResumeList.getInstance();
        resumeList.addResume(new Resume("", "", "", ""));
        DataWriter.saveResumeList();
        assertEquals("", DataLoader.getResumeList().get(0).getFirstName());
    }

    @Test
    void addNullResume() {
        ResumeList resumeList = ResumeList.getInstance();
        resumeList.addResume(null);
        DataWriter.saveResumeList();
        assertEquals(null, DataLoader.getResumeList().get(0));
    }

    @Test
    void addResumeWithSkills() {
        ResumeList resumeList = ResumeList.getInstance();
        Resume res = new Resume("firstName", "lastName", "email", "phoneNumber");
        res.addSkill("experience");
        res.addSkill("skill");
        resumeList.addResume(res);
        DataWriter.saveResumeList();
        assertEquals("skill", DataLoader.getResumeList().get(0).getSkills().get(1));
    }

    @Test
    void addResumeWithNullSkills() {
        ResumeList resumeList = ResumeList.getInstance();
        Resume res = new Resume("firstName", "lastName", "email", "phoneNumber");
        res.addSkill(null);
        res.addSkill(null);
        resumeList.addResume(res);
        DataWriter.saveResumeList();
        assertEquals(null, DataLoader.getResumeList().get(0).getSkills().get(1));
    }

    @Test
    void addResumeWithExperiences() {
        ResumeList resumeList = ResumeList.getInstance();
        Resume res = new Resume("firstName", "lastName", "email", "phoneNumber");
        res.addExperience(new Experience("name", "start", "end"));
        res.addExperience(new Experience("companyName", "startDate", "endDate"));
        resumeList.addResume(res);
        DataWriter.saveResumeList();
        assertEquals("companyName", DataLoader.getResumeList().get(0).getExperience().get(1).getCompanyName());
    }

    @Test
    void addResumeWithNullExperiences() {
        ResumeList resumeList = ResumeList.getInstance();
        Resume res = new Resume("firstName", "lastName", "email", "phoneNumber");
        res.addExperience(null);
        res.addExperience(null);
        resumeList.addResume(res);
        DataWriter.saveResumeList();
        assertEquals(null, DataLoader.getResumeList().get(0).getExperience().get(0).getCompanyName());
    }

    @Test
    void addResumeWithExperiencesWithDescriptions() {
        ResumeList resumeList = ResumeList.getInstance();
        Resume res = new Resume("firstName", "lastName", "email", "phoneNumber");
        Experience exp = new Experience("companyName", "start", "end");
        Experience exp1 = new Experience("companyName", "startDate", "endDate");
        exp.addDescription("description");
        exp.addDescription("2");
        exp1.addDescription("3");
        exp1.addDescription("description4");
        res.addExperience(exp);
        res.addExperience(exp1);
        resumeList.addResume(res);
        DataWriter.saveResumeList();
        assertEquals("description", DataLoader.getResumeList().get(0).getExperience().get(0).getDescriptions().get(0));
    }

    @Test
    void addResumeWithExperiencesWithNullDescriptions() {
        ResumeList resumeList = ResumeList.getInstance();
        Resume res = new Resume("firstName", "lastName", "email", "phoneNumber");
        Experience exp = new Experience("companyName", "start", "end");
        Experience exp1 = new Experience("companyName", "startDate", "endDate");
        exp.addDescription(null);
        exp.addDescription(null);
        exp1.addDescription(null);
        exp1.addDescription(null);
        res.addExperience(exp);
        res.addExperience(exp1);
        resumeList.addResume(res);
        DataWriter.saveResumeList();
        assertEquals(null, DataLoader.getResumeList().get(0).getExperience().get(0).getDescriptions().get(0));
    }

    @Test
    void addOneReview() {
        ReviewList reviewList = ReviewList.getInstance();
        reviewList.addReviewToList(new Review(20, "comment", "firstName", "lastName"));
        DataWriter.saveReviewList();
        assertEquals("comment", DataLoader.getReviewList().get(0).getComment());
    }

    @Test
    void addThreeReview() {
        ReviewList reviewList = ReviewList.getInstance();
        reviewList.addReviewToList(new Review(20, "comment", "firstName", "lastName"));
        reviewList.addReviewToList(new Review(15, "comment1", "firstName1", "lastName1"));
        reviewList.addReviewToList(new Review(14, "comment2", "firstName2", "lastName2"));
        DataWriter.saveReviewList();
        assertEquals("comment2", DataLoader.getReviewList().get(2).getComment());
    }

    @Test
    void addEmptyReview() {
        ReviewList reviewList = ReviewList.getInstance();
        reviewList.addReviewToList(new Review(0, "", "", ""));
        DataWriter.saveReviewList();
        assertEquals("", DataLoader.getReviewList().get(0).getComment());
    }

    @Test
    void addNullReview(){
        ReviewList reviewList = ReviewList.getInstance();
        reviewList.addReviewToList(null);
        DataWriter.saveReviewList();
        assertEquals(null, DataLoader.getReviewList().get(0).getComment());
    }
}