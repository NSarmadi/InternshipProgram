package Classes;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AdminTest {

	@BeforeEach
	public void setup() {
		ProfessorList professorList = ProfessorList.getInstance();
        professorList.getProfessors().clear();
        BusinessList businessList = BusinessList.getInstance();
        businessList.getBusinesses().clear();
        JobListingList jobListingList = JobListingList.getInstance();
        jobListingList.getJobListings().clear();
        StudentList studentList = StudentList.getInstance();
        studentList.getStudents().clear();
	}

    @Test
    public void testRemoveJobListingInList() {
        Admin admin = new Admin("email", "password");
        JobListingList jobListingList = JobListingList.getInstance();
        jobListingList.getJobListings().clear();
        jobListingList.getJobListings().add(new JobListing("Business 1", "Job Title 1", "Job Description 1", 500));
        jobListingList.getJobListings().add(new JobListing("Business 2", "Job Title 2", "Job Description 2", 600));
        jobListingList.getJobListings().add(new JobListing("Business 3", "JOb Title 3", "Job Description 3", 700));
        admin.removeJobListing(jobListingList.getJobListings().get(1));
        assertEquals(2, jobListingList.getJobListings().size());
    }

    @Test
    public void testRemoveJobListingNotInList() {
        Admin admin = new Admin("email", "password");
        JobListingList jobListingList = JobListingList.getInstance();
        jobListingList.getJobListings().clear();
        jobListingList.getJobListings().add(new JobListing("Business 1", "Job Title 1", "Job Description 1", 500));
        jobListingList.getJobListings().add(new JobListing("Business 2", "Job Title 2", "Job Description 2", 600));
        jobListingList.getJobListings().add(new JobListing("Business 3", "Job Title 3", "Job Description 3", 700));
        JobListing jobListing = new JobListing("Business 4", "Job Title 4", "Job Description 4", 800);
        admin.removeJobListing(jobListing);
        assertEquals(3, jobListingList.getJobListings().size());
    }

    @Test
    public void testRemoveStudentReviewInList() {
        Admin admin = new Admin("email", "password");
        StudentList studentList = StudentList.getInstance();
        studentList.getStudents().clear();
        studentList.getStudents().add(new Student("Naveen", "Chithan", "naveenchithan@email.com", "8885559670", "password1"));
        studentList.getStudents().get(0).addProfessorReview(new Review(5.0, "great student", "Naveen", "Chithan"));
        studentList.getStudents().get(0).addProfessorReview(new Review(2.5, "decent student", "Naveen", "Chithan"));
        studentList.getStudents().get(0).addProfessorReview(new Review(4.5, "alright student", "Naveen", "Chithan"));
        admin.removeStudentReview(studentList.getStudents().get(0), studentList.getStudents().get(0).getProfessorReviews().get(1));
        assertEquals(2, studentList.getStudents().get(0).getProfessorReviews().size());
    }

    @Test
    public void testRemoveStudentReviewNotInList() {
        Admin admin = new Admin("email", "password");
        Review review = new Review(5.0, "Great Student", "Gonzalo", "Rodas");
        StudentList studentList = StudentList.getInstance();
        studentList.getStudents().clear();
        studentList.getStudents().add(new Student("Naveen", "Chithan", "naveenchithan@email.com", "8885559670", "password1"));
        studentList.getStudents().get(0).addProfessorReview(new Review(5.0, "great student", "Naveen", "Chithan"));
        studentList.getStudents().get(0).addProfessorReview(new Review(2.5, "decent student", "Naveen", "Chithan"));
        studentList.getStudents().get(0).addProfessorReview(new Review(4.5, "alright student", "Naveen", "Chithan"));
        admin.removeStudentReview(studentList.getStudents().get(0), review);
        assertEquals(3, studentList.getStudents().get(0).getProfessorReviews().size());
    }

    @Test
    public void testRemoveStudentReviewStudentNotInList() {
        Admin admin = new Admin("email", "password");
        Student student = new Student("Gonzalo", "Rodas", "gonzalo@email.com", "8885555464", "password?");
        StudentList studentList = StudentList.getInstance();
        studentList.getStudents().clear();
        studentList.getStudents().add(new Student("Naveen", "Chithan", "naveenchithan@email.com", "8885559670", "password1"));
        studentList.getStudents().get(0).addProfessorReview(new Review(5.0, "great student", "Naveen", "Chithan"));
        studentList.getStudents().get(0).addProfessorReview(new Review(2.5, "decent student", "Naveen", "Chithan"));
        studentList.getStudents().get(0).addProfessorReview(new Review(4.5, "alright student", "Naveen", "Chithan"));
        admin.removeStudentReview(student, studentList.getStudents().get(0).getProfessorReviews().get(1));
        assertEquals(3, studentList.getStudents().get(0).getProfessorReviews().size());
    }

    @Test
    public void testRemoveJobReviewInList() {
        Admin admin = new Admin("email", "password");
        JobListingList jobListingList = JobListingList.getInstance();
        jobListingList.getJobListings().clear();
        jobListingList.getJobListings().add(new JobListing("Business 1", "Job Title 1", "Job Description 1", 500));
        jobListingList.getJobListings().get(0).addReview(new Review(5.0, "Amazing opportunity", "Job Title 1", "Job Description"));
        jobListingList.getJobListings().get(0).addReview(new Review(4.0, "Good opportunity", "Job Title 1", "Job Description"));
        jobListingList.getJobListings().get(0).addReview(new Review(3.0, "Decent opportunity", "Job Title 1", "Job Description"));
        admin.removeJobReview(jobListingList.getJobListings().get(0), jobListingList.getJobListings().get(0).getReviews().get(0));
        assertEquals(2, jobListingList.getJobListings().get(0).getReviews().size());
    }

    @Test
    public void testRemoveJobReviewNotInList() {
        Admin admin = new Admin("email", "password");
        JobListing jobListing = new JobListing("Random Business", "Random Title", "Work 20 hours a day", 100);
        JobListingList jobListingList = JobListingList.getInstance();
        jobListingList.getJobListings().clear();
        jobListingList.getJobListings().add(new JobListing("Business 1", "Job Title 1", "Job Description 1", 500));
        jobListingList.getJobListings().get(0).addReview(new Review(5.0, "Amazing opportunity", "Job Title 1", "Job Description"));
        jobListingList.getJobListings().get(0).addReview(new Review(4.0, "Good opportunity", "Job Title 1", "Job Description"));
        jobListingList.getJobListings().get(0).addReview(new Review(3.0, "Decent opportunity", "Job Title 1", "Job Description"));
        admin.removeJobReview(jobListing, jobListingList.getJobListings().get(0).getReviews().get(0));
        assertEquals(3, jobListingList.getJobListings().get(0).getReviews().size());
    }

    @Test
    public void testRemoveJobReviewJobListingNotInList() {
        Admin admin = new Admin("email", "password");
        Review review = new Review(1.0, "Terrible opportunity", "Random Business", "Terrible Job");
        JobListingList jobListingList = JobListingList.getInstance();
        jobListingList.getJobListings().clear();
        jobListingList.getJobListings().add(new JobListing("Business 1", "Job Title 1", "Job Description 1", 500));
        jobListingList.getJobListings().get(0).addReview(new Review(5.0, "Amazing opportunity", "Job Title 1", "Job Description"));
        jobListingList.getJobListings().get(0).addReview(new Review(4.0, "Good opportunity", "Job Title 1", "Job Description"));
        jobListingList.getJobListings().get(0).addReview(new Review(3.0, "Decent opportunity", "Job Title 1", "Job Description"));
        admin.removeJobReview(jobListingList.getJobListings().get(0), review);
        assertEquals(3, jobListingList.getJobListings().get(0).getReviews().size());
    }

    @Test
    public void testRemoveStudentInList() {
        Admin admin = new Admin("email", "password");
        StudentList studentList = StudentList.getInstance();
        studentList.getStudents().clear();
        studentList.getStudents().add(new Student("Bob", "Marley", "BobMarley@email.com", "8885550420", "love"));
        studentList.getStudents().add(new Student("Tom", "Jenkins", "TomJenkins@email.com", "8885555475", "weird"));
        studentList.getStudents().add(new Student("Carl", "Hickey", "CarlHickey@email.com", "8885554575", "cars4ever"));
        admin.removeStudent(studentList.getStudents().get(1));
        assertEquals(2, studentList.getStudents().size());
    }

    @Test
    public void testRemoveStudentNotInList() {
        Admin admin = new Admin("email", "password");
        Student student = new Student("Timothy", "Hawkins", "TimothyHawkins@email.com", "8885557548", "birdsrcool");
        StudentList studentList = StudentList.getInstance();
        studentList.getStudents().clear();
        studentList.getStudents().add(new Student("Bob", "Marley", "BobMarley@email.com", "8885550420", "love"));
        studentList.getStudents().add(new Student("Tom", "Jenkins", "TomJenkins@email.com", "8885555475", "weird"));
        studentList.getStudents().add(new Student("Carl", "Hickey", "CarlHickey@email.com", "8885554575", "cars4ever"));
        admin.removeStudent(student);
        assertEquals(3, studentList.getStudents().size());
    }

    @Test
    public void testRemoveBusinessInList() {
        Admin admin = new Admin("email", "password");
        BusinessList businessList = BusinessList.getInstance();
        businessList.getBusinesses().clear();
        businessList.getBusinesses().add(new Business("Random business 1", "business1@email.com", "password1", "address1"));
        businessList.getBusinesses().add(new Business("Random business 2", "business2@email.com", "password2", "address2"));
        businessList.getBusinesses().add(new Business("Random business 3", "business3@email.com", "password3", "address3"));
        admin.removeBusiness(businessList.getBusinesses().get(0));
        assertEquals(2, businessList.getBusinesses().size());
    }

    @Test
    public void testRemoveBusinessNotInList() {
        Admin admin = new Admin("email", "password");
        Business business = new Business("Petco", "petco@email.com", "iloveanimals", "123 Sesame St");
        BusinessList businessList = BusinessList.getInstance();
        businessList.getBusinesses().clear();
        businessList.getBusinesses().add(new Business("Random business 1", "business1@email.com", "password1", "address1"));
        businessList.getBusinesses().add(new Business("Random business 2", "business2@email.com", "password2", "address2"));
        businessList.getBusinesses().add(new Business("Random business 3", "business3@email.com", "password3", "address3"));
        admin.removeBusiness(business);
        assertEquals(3, businessList.getBusinesses().size());
    }
}
