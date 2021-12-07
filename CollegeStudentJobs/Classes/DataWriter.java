package Classes;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class DataWriter extends DataConstants {

	/**
	 * Writes the Admin List to the adminlist json file
	 */
    public static void saveAdminList() {
		AdminList adminList = AdminList.getInstance();
		ArrayList<Admin> adminsList = adminList.getAdmins();
		JSONArray jsonAdminlist = new JSONArray();
		
		for(int i=0; i< adminsList.size(); i++) {
			jsonAdminlist.add(getAdminJSON(adminsList.get(i)));
		}
		
        try (FileWriter file = new FileWriter(ADMINLIST_FILE_NAME)) {
            file.write(jsonAdminlist.toJSONString());
            file.flush();
        } 
		catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * Turns each admin object into a json object to have its information stored
	 * @param admin Admin, admin to be turned into a json object
	 * @return JSONObject, json object to be put into the json
	 */
	public static JSONObject getAdminJSON(Admin admin) {
		JSONObject adminDetails = new JSONObject();
		adminDetails.put(ADMIN_ID, admin.getId().toString());
		adminDetails.put(ADMIN_EMAIL, admin.getEmail());
		adminDetails.put(ADMIN_PASSWORD, admin.getPassword());
        return adminDetails;
	}

	/**
	 * Writes the Student List to studentlist json file
	 */
    public static void saveStudents() {
		StudentList studentList = StudentList.getInstance();
		ArrayList<Student> studentlist = studentList.getStudents();
		JSONArray jsonStudents = new JSONArray();
		for(int i=0; i< studentlist.size(); i++) {
			jsonStudents.add(getStudentJSON(studentlist.get(i)));
		}
        try (FileWriter file = new FileWriter(STUDENTLIST_FILE_NAME)) {
            file.write(jsonStudents.toJSONString());
            file.flush();
        } 
		catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * Turns a student object into a json object to be stored in the json file
	 * @param student Student, student to be turned into a json file
	 * @return JSONObject, json object to be stored in the file
	 */
	public static JSONObject getStudentJSON(Student student) {
		JSONObject studentDetails = new JSONObject();
		studentDetails.put(STUDENTLIST_ID, student.getID().toString());
		studentDetails.put(STUDENTLIST_FIRST_NAME, student.getFirstName());
		studentDetails.put(STUDENTLIST_LAST_NAME, student.getLastName());
		studentDetails.put(STUDENTLIST_PHONE_NUMBER, student.getPhoneNumber());
		studentDetails.put(STUDENTLIST_RESUME, student.getResume().getID().toString());
		studentDetails.put(STUDENTLIST_EMAIL, student.getEmail());
		studentDetails.put(STUDENTLIST_PASSWORD, student.getPassword());
        JSONArray professorReviewArray = new JSONArray();
		for(int i = 0; i < student.getProfessorReviews().size(); i++) {
			professorReviewArray.add(student.getProfessorReviews().get(i).getID().toString());
		}
		studentDetails.put(STUDENTLIST_PROFESSOR_REVIEWS, professorReviewArray);
        return studentDetails;
	}

	/**
	 * Writes the business list into the businesslist json file
	 */
	public static void saveBusiness() {
		BusinessList businessList = BusinessList.getInstance();
		ArrayList<Business> businesslist = businessList.getBusinesses();
		JSONArray jsonBusiness = new JSONArray();
		for(int i=0; i< businesslist.size(); i++) {
			jsonBusiness.add(getBusinessJSON(businesslist.get(i))); 
		}
        try (FileWriter file = new FileWriter(BUSINESSLIST_FILE_NAME)) {
            file.write(jsonBusiness.toJSONString());
            file.flush();
        } 
		catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * Turns a Business object into a json object to be stored in the json file
	 * @param business Business, business to be turned into a json object
	 * @return JSONObject, json object to be stored in the file
	 */
	public static JSONObject getBusinessJSON(Business business) {
		JSONObject BusinessDetails = new JSONObject();
		BusinessDetails.put(BUSINESS_ID, business.getId().toString());
		BusinessDetails.put(BUSINESS_NAME, business.getBusinessName());
		BusinessDetails.put(BUSINESS_EMAIL, business.getEmail());
		BusinessDetails.put(BUSINESS_PASSWORD, business.getPassword());
		BusinessDetails.put(BUSINESS_ADDRESS, business.getAddress());
		ArrayList<UUID> jobListingIDs = new ArrayList<UUID>();
		for(int i = 0; i < business.getJobListings().size(); i++) {
			jobListingIDs.add(business.getJobListings().get(i).getID());
		}
		JSONArray jobListingArray = new JSONArray();
		for(int i = 0; i < jobListingIDs.size(); i++) {
			jobListingArray.add(jobListingIDs.get(i).toString());
		}
		BusinessDetails.put(BUSINESS_JOBLISTING, jobListingArray);
		BusinessDetails.put(BUSINESS_ISVERIFIED, business.getIsVerified());
		
        return BusinessDetails;
	}

	/**
	 * Writes the Professor list to the professorlist json file
	 */
	public static void saveProfessor() {
		ProfessorList professorList = ProfessorList.getInstance();
		ArrayList<Professor> professorlist = professorList.getProfessors();
		JSONArray jsonProfessor = new JSONArray();
		for(int i=0; i< professorlist.size(); i++) {
			jsonProfessor.add(getProfessorJSON(professorlist.get(i))); 
		}
        try (FileWriter file = new FileWriter(PROFESSORLIST_FILE_NAME)) {
            file.write(jsonProfessor.toJSONString());
            file.flush();
        } 
		catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * Turns a professor object into a JSON object to be stored in the file
	 * @param professor Professor, professor to be turned into a json file
	 * @return JSONObject, json object to be added into the file
	 */
	public static JSONObject getProfessorJSON(Professor professor) {
		JSONObject professorDetails = new JSONObject();
		professorDetails.put(PROFESSORLIST_ID, professor.getId().toString());
		professorDetails.put(PROFESSORLIST_FIRST_NAME, professor.getFirstName());
		professorDetails.put(PROFESSORLIST_LAST_NAME, professor.getLastName());
		professorDetails.put(PROFESSORLIST_EMAIL, professor.getEmail());
		professorDetails.put(PROFESSORLIST_PASSWORD, professor.getPassword());
		professorDetails.put(PROFESSORLIST_ISVERIFIED, professor.getIsVerified());
		JSONArray professorReviews = new JSONArray(); 
		for(int i = 0; i < professor.getCreatedReviews().size(); i++) {
			professorReviews.add(professor.getCreatedReviews().get(i).getID().toString());
		}
		professorDetails.put(PROFESSORLIST_REVIEWS, professorReviews);
        return professorDetails;
	}

	/**
	 * Saves the list of joblistings to the joblistinglist json file
	 */
	public static void saveJobListing() {
		JobListingList jobListing = JobListingList.getInstance();
		ArrayList<JobListing> joblisting = jobListing.getJobListings();
		JSONArray jsonJobListings = new JSONArray();
		for(int i=0; i< joblisting.size(); i++) {
			jsonJobListings.add(getJobListingJSON(joblisting.get(i)));
		}
        try (FileWriter file = new FileWriter(JOBLISTINGS_FILE_NAME)) {
            file.write(jsonJobListings.toJSONString());
            file.flush();
        } 
		catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * Turns a JobListing object into a JSONObject to be stored in the file
	 * @param jobListing JobListing, job listing to be turned into a json object
	 * @return JSONObject, json object to be stored in the file
	 */
	public static JSONObject getJobListingJSON(JobListing jobListing) {
		JSONObject JobListingDetails = new JSONObject();
		JobListingDetails.put(JOBLISTING_ID, jobListing.getID().toString());
		JobListingDetails.put(JOBLISTING_BUSINESSNAME, jobListing.getBusinessName());
		JobListingDetails.put(JOBLISTING_JOB_TITLE, jobListing.getJobTitle());
		JobListingDetails.put(JOBLISTING_JOB_DESCRIPTION, jobListing.getJobDescription());
		ArrayList<String> experiences = new ArrayList<String>();
		experiences = jobListing.getExperienceList();
		JSONArray experiencesArray = new JSONArray();
		for(int i = 0; i < experiences.size(); i++) {
			experiencesArray.add(experiences.get(i));
		}
		JobListingDetails.put(JOBLISTING_EXPERINECE, experiencesArray);
		ArrayList<String> skills = new ArrayList<String>();
		skills = jobListing.getSkillList();
		JSONArray skillsArray = new JSONArray();
		for(int i = 0; i < skills.size(); i++) {
			skillsArray.add(skills.get(i));
		}
		JobListingDetails.put(JOBLISTING_SKILLS, skillsArray);
		ArrayList<UUID> ReviewIDs = new ArrayList<UUID>();
		for(int i = 0; i < jobListing.getReviews().size(); i++) {
			ReviewIDs.add(jobListing.getReviews().get(i).getID());
		}
		JSONArray reviewsArray = new JSONArray(); 
		for(int i = 0; i < ReviewIDs.size(); i++) {
			reviewsArray.add(ReviewIDs.get(i).toString());
		}
		JobListingDetails.put(JOBLISTING_REVIEWS, reviewsArray);
		JobListingDetails.put(JOBLISTING_WAGE, jobListing.getWage());
		ArrayList<UUID> AppliedStudentIDs = new ArrayList<UUID>();
		for(int i = 0; i < jobListing.getAppliedStudents().size(); i++) {
			AppliedStudentIDs.add(jobListing.getAppliedStudents().get(i).getID());
		}
		JSONArray AppliedStudentArray = new JSONArray(); 
		for(int i = 0; i < AppliedStudentIDs.size(); i++) {
			AppliedStudentArray.add(AppliedStudentIDs.get(i).toString());
		}
		JobListingDetails.put(JOBLISTING_APPLIED_STUDENTS, AppliedStudentArray);
        return JobListingDetails;
	}
	
	/**
	 * Saves the list of deleted users to the deletedusers json file
	 */
	public static void saveDeletedUsers() {
		DeletedUserList deletedUsers = DeletedUserList.getInstance();
		ArrayList<User> deletedusers = deletedUsers.getDeletedUsers();
		JSONArray jsonDeletedUsers = new JSONArray();
		for(int i=0; i< deletedusers.size(); i++) {
			jsonDeletedUsers.add(getDeletedUserJSON(deletedusers.get(i)));
		}
        try (FileWriter file = new FileWriter(DELETEDUSERLIST_FILE_NAME)) {
 
            file.write(jsonDeletedUsers.toJSONString());
            file.flush();
 
        } 
		catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * Turns a User into a json object to be stored in the deleted users json
	 * @param user User, user to be turned into a json object
	 * @return JSONObject, json object to be stored in the json
	 */
	public static JSONObject getDeletedUserJSON(User user) {
		JSONObject DeletedUsersDetails = new JSONObject();
		DeletedUsersDetails.put(DELETEDUSER_ID, user.getID().toString());
		DeletedUsersDetails.put(DELETEDUSER_EMAIL, user.getEmail());
		DeletedUsersDetails.put(DELETEDUSER_PASSWORD, user.getPassword());
        return DeletedUsersDetails;
	}

	/**
	 * Saves the list of Experiences to the experiencelist json
	 */
	public static void saveExperienceList() {
		ExperienceList experienceList = ExperienceList.getInstance();
		ArrayList<Experience> experiences = experienceList.getExperiences();
		JSONArray jsonExperiences = new JSONArray();
		for(int i=0; i< experiences.size(); i++) {
			jsonExperiences.add(getExperienceJSON(experiences.get(i)));
		}
		//Write JSON file
        try (FileWriter file = new FileWriter(EXPERIENCELIST_FILE_NAME)) {
            file.write(jsonExperiences.toJSONString());
            file.flush();
        } 
		catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * Turns an experience object into a json object to be stored in the experiencelist json
	 * @param experience Experience, experience to be turned into a json object
	 * @return JSONObject, json object to be added to the json file
	 */
	public static JSONObject getExperienceJSON(Experience experience) {
		JSONObject experienceDetails = new JSONObject();
		experienceDetails.put(EXPERIENCELIST_ID, experience.getID().toString());
		experienceDetails.put(EXPERIENCELIST_COMPANYNAME, experience.getCompanyName());
		experienceDetails.put(EXPERIENCELIST_STARTDATE, experience.getStartDate());
		experienceDetails.put(EXPERIENCELIST_ENDDATE, experience.getEndDate());
		JSONArray jsonArray = new JSONArray();
		ArrayList<String> descriptions = experience.getDescriptions(); 
		for(int i = 0; i < descriptions.size(); i++) {
			jsonArray.add(descriptions.get(i));
		}
		experienceDetails.put(EXPERIENCELIST_DESCRIPTIONS, descriptions);
        return experienceDetails;
	}
	
	/**
	 * Writes the list of Resumes to the resumes json
	 */
	public static void saveResumeList() {
		ResumeList resumeList = ResumeList.getInstance();
		ArrayList<Resume> resumes = resumeList.getResumes();
		JSONArray jsonResumes = new JSONArray();
		for(int i=0; i< resumes.size(); i++) {
			jsonResumes.add(getResumeJSON(resumes.get(i)));
		}
        try (FileWriter file = new FileWriter(RESUMELIST_FILE_NAME)) {
            file.write(jsonResumes.toJSONString());
            file.flush();
        } 
		catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * Turns a resume object into a json object to be stored in the resumelist json
	 * @param resume Resume, resume to be turned into a json object
	 * @return JSONObject, json object to be stored into the json file
	 */
	public static JSONObject getResumeJSON(Resume resume) {
		JSONObject resumeDetails = new JSONObject();
		resumeDetails.put(RESUMELIST_ID, resume.getID().toString());
		resumeDetails.put(RESUMELIST_FIRST_NAME, resume.getFirstName());
		resumeDetails.put(RESUMELIST_LAST_NAME, resume.getLastName());
		resumeDetails.put(RESUMELIST_EMAIL, resume.getEmail());
		resumeDetails.put(RESUMELIST_PHONE_NUMBER, resume.getPhoneNumber());
		ArrayList<UUID> experienceIDs = new ArrayList<UUID>();
		for (int i = 0; i < resume.getExperience().size(); i++) {
			experienceIDs.add(resume.getExperience().get(i).getID()); 
		}
		JSONArray experienceIDJSON = new JSONArray(); 
		for (int i = 0; i < experienceIDs.size(); i++) {
			experienceIDJSON.add(experienceIDs.get(i).toString());
		}
		resumeDetails.put(RESUMELIST_EXPERIENCES, experienceIDJSON);
		ArrayList<String> skills = new ArrayList<String>();
		skills = resume.getSkills(); 
		JSONArray skillsArray = new JSONArray(); 
		for(int i = 0; i < skills.size(); i++)  {
			skillsArray.add(skills.get(i));
		} 
		resumeDetails.put(RESUMELIST_SKILLS, skillsArray);
        return resumeDetails;
	}

	/**
	 * Writes the list of Reviews to the reviews json
	 */
	public static void saveReviewList() {
		ReviewList reviewList = ReviewList.getInstance();
		ArrayList<Review> reviews = reviewList.getReviews();
		JSONArray jsonReview = new JSONArray();
		for(int i=0; i < reviews.size(); i++) {
			jsonReview.add(getReviewJSON(reviews.get(i)));
		}
        try (FileWriter file = new FileWriter(REVIEWLIST_FILE_NAME)) {
            file.write(jsonReview.toJSONString());
            file.flush();
        } 
		catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * Turns a Review object into a json object to be stored into the reviews json
	 * @param review Review, review to be turned into a json object
	 * @return JSONObject, json object to be stored in the json file
	 */
	public static JSONObject getReviewJSON(Review review) {
		JSONObject reviewDetails = new JSONObject();
		reviewDetails.put(REVIEWLIST_ID, review.getID().toString());
		reviewDetails.put(REVIEWLIST_RATING, review.getRating());
		reviewDetails.put(REVIEWLIST_COMMENT, review.getComment());
		reviewDetails.put(REVIEWLIST_FIRST_NAME, review.getFirstName());
		reviewDetails.put(REVIEWLIST_LAST_NAME, review.getLastName());
        return reviewDetails;
	}
}