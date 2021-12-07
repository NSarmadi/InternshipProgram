package Classes;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.UUID;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
/**
 * Creates the DataLoader class which inherits from DataConstants
 */
public class DataLoader extends DataConstants {
    /**
     * Gets the AdminList from the adminlist JSON file
     * @return ArrayList<Admin>, List of admins
     */
    public static ArrayList<Admin> getAdminList(){
        ArrayList<Admin> adminList = new ArrayList<Admin>();
        try {
            FileReader reader = new FileReader(ADMINLIST_FILE_NAME);
            JSONParser parser = new JSONParser();
            JSONArray peopleJSON = (JSONArray)new JSONParser().parse(reader);
            for (int i=0; i < peopleJSON.size(); i++){
                JSONObject personJSON = (JSONObject)peopleJSON.get(i);
                UUID adminID = UUID.fromString((String)personJSON.get(ADMIN_ID));
                String adminEmail = (String)personJSON.get(ADMIN_EMAIL);
                String adminPassword = (String)personJSON.get(ADMIN_PASSWORD);
                adminList.add(new Admin(adminID, adminEmail, adminPassword));
            }
            return adminList;
        } 
        catch (Exception a) {
            a.printStackTrace();
        }
        return null;
    }
    
    /**
     * Gets the Business List from the businesslist json file
     * @return ArrayList<Business>, List of businesses
     */
    public static ArrayList<Business> getBusinessList(){
        ArrayList<Business> businessList = new ArrayList<Business>();
        try {
            FileReader reader = new FileReader(BUSINESSLIST_FILE_NAME);
            JSONParser parser = new JSONParser();
            JSONArray peopleJSON = (JSONArray)new JSONParser().parse(reader);
            for (int i=0; i < peopleJSON.size(); i++){
            JSONObject personJSON = (JSONObject)peopleJSON.get(i);
                UUID businessID = UUID.fromString((String)personJSON.get(BUSINESS_ID));
                String businessName = (String)personJSON.get(BUSINESS_NAME);
                String businessEmail = (String)personJSON.get(BUSINESS_EMAIL);
                String businessPassword = (String)personJSON.get(BUSINESS_PASSWORD);
                String businessAddress = (String)personJSON.get(BUSINESS_ADDRESS);
                JSONArray jobListingArray = (JSONArray)personJSON.get(BUSINESS_JOBLISTING);
                ArrayList<JobListing> jobListingList = new ArrayList<JobListing>(); 
                for(int k = 0; k < jobListingArray.size(); k++) {
                    jobListingList.add(JobListingList.getInstance().getJobListing(UUID.fromString(jobListingArray.get(k).toString())));
                }
                boolean businessVerified = ((Boolean)personJSON.get(BUSINESS_ISVERIFIED)).booleanValue();
                businessList.add(new Business(businessID, businessName, businessEmail, businessPassword, businessAddress, jobListingList, businessVerified));
            }
            return businessList; 
        } catch (Exception b) {
            b.printStackTrace();
        }
        return null;
    }

    /**
     * Gets the list of deleted users from the deleteduser json
     * @return ArrayList<User>, List of deleted Users
     */
    public static ArrayList<User> getDeletedUserList(){
        ArrayList<User> deletedUserList = new ArrayList<User>();

        try {
            FileReader reader = new FileReader(DELETEDUSERLIST_FILE_NAME);
            JSONParser parser = new JSONParser();
            JSONArray peopleJSON = (JSONArray)new JSONParser().parse(reader);
            for (int i=0; i < peopleJSON.size(); i++){
                JSONObject personJSON = (JSONObject)peopleJSON.get(i);
                UUID deletedUserID = UUID.fromString((String)personJSON.get(DELETEDUSER_ID));
                String deletedUserEmail = (String)personJSON.get(DELETEDUSER_EMAIL);
                String deletedUserPassword = (String)personJSON.get(DELETEDUSER_PASSWORD);
                deletedUserList.add(new User(deletedUserID, deletedUserEmail, deletedUserPassword));
            }
            return deletedUserList; 
        } catch (Exception c) {
            c.printStackTrace();
        }
        return null;
    }

    /**
     * Gets the list of JobListings from the joblistinglist json
     * @return ArrayList<JobListing>, list of joblistings
     */
    public static ArrayList<JobListing> getJobListingList() {
        ArrayList<JobListing> jobListingList = new ArrayList<JobListing>();
        try {
            FileReader reader = new FileReader(JOBLISTINGS_FILE_NAME);
            JSONParser parser = new JSONParser();
            JSONArray peopleJSON = (JSONArray)new JSONParser().parse(reader);
            for (int i=0; i < peopleJSON.size(); i++){
                JSONObject personJSON = (JSONObject)peopleJSON.get(i);
                UUID jobListingID = UUID.fromString((String)personJSON.get(JOBLISTING_ID));
                String jobListingBusinessName = (String)personJSON.get(JOBLISTING_BUSINESSNAME);
                String jobListingTitle = (String)personJSON.get(JOBLISTING_JOB_TITLE);
                String jobListingDescription = (String)personJSON.get(JOBLISTING_JOB_DESCRIPTION);
                JSONArray experienceArray = (JSONArray)personJSON.get(JOBLISTING_EXPERINECE);
                ArrayList<String> experienceList = new ArrayList<String>(); 
                for(int k =0; k < experienceArray.size(); k++) {
                    experienceList.add(experienceArray.get(k).toString());
                }
                JSONArray skillsArray = (JSONArray)personJSON.get(JOBLISTING_SKILLS);
                ArrayList<String> skillsList = new ArrayList<String>(); 
                for(int k = 0; k < skillsArray.size(); k++) {
                    skillsList.add(skillsArray.get(k).toString());
                }
                JSONArray reviewsArray = (JSONArray)personJSON.get(JOBLISTING_REVIEWS);
                ArrayList<Review> reviewsList = new ArrayList<Review>(); 
                for(int k = 0; k < reviewsArray.size(); k++) {
                    reviewsList.add(ReviewList.getInstance().getReview(UUID.fromString(reviewsArray.get(k).toString())));
                }
                double jobListingWages = ((Double)personJSON.get(JOBLISTING_WAGE)).doubleValue();
                JSONArray appliedStudentsArray = (JSONArray)personJSON.get(JOBLISTING_APPLIED_STUDENTS);
                ArrayList<Student> studentList = new ArrayList<Student>(); 
                for(int k = 0; k < appliedStudentsArray.size(); k++) {
                    studentList.add(StudentList.getInstance().getStudent(UUID.fromString(appliedStudentsArray.get(k).toString())));
                }
                JobListing jobListing = new JobListing(jobListingBusinessName, jobListingTitle, jobListingDescription, jobListingWages, experienceList, skillsList, reviewsList, studentList, jobListingID);
                jobListingList.add(jobListing);
            }
            return jobListingList; 
        } catch (Exception d) {
            d.printStackTrace();
        }
        return null;
    }

    /**
     * Gets the list of professors from professorlist json
     * @return ArrayList<Professor>, list of professors
     */
    public static ArrayList<Professor> getProfessorList() {
        ArrayList<Professor> professorList = new ArrayList<Professor>();

        try{
            FileReader reader = new FileReader(PROFESSORLIST_FILE_NAME);
            JSONParser parser = new JSONParser();
            JSONArray peopleJSON = (JSONArray)new JSONParser().parse(reader);
            for (int i=0; i < peopleJSON.size(); i++){
                JSONObject personJSON = (JSONObject)peopleJSON.get(i);
                UUID professorID = UUID.fromString((String)personJSON.get(PROFESSORLIST_ID));
                boolean professorVerified = ((Boolean)personJSON.get(PROFESSORLIST_ISVERIFIED)).booleanValue();
                String professorFirstName = (String)personJSON.get(PROFESSORLIST_FIRST_NAME);
                String professorLastName = (String)personJSON.get(PROFESSORLIST_LAST_NAME);
                String professorEmail = (String)personJSON.get(PROFESSORLIST_EMAIL);
                String professorPassword = (String)personJSON.get(PROFESSORLIST_PASSWORD);
                JSONArray professorReviewArray = (JSONArray)personJSON.get(PROFESSORLIST_REVIEWS);
                ArrayList<Review> professorReviewList = new ArrayList<Review>(); 
                for(int k = 0; k < professorReviewArray.size(); k++) {
                    professorReviewList.add(ReviewList.getInstance().getReview(UUID.fromString(professorReviewArray.get(k).toString())));
                }
                professorList.add(new Professor(professorID, professorFirstName, professorLastName, professorEmail, professorPassword, professorReviewList, professorVerified));
            }
            return professorList; 

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Gets the list of Students for studentlist json
     * @return ArrayList<Student>, List of Students
     */
    public static ArrayList<Student> getStudentList() {
        ArrayList<Student> studentList = new ArrayList<Student>();

        try{
            FileReader reader = new FileReader(STUDENTLIST_FILE_NAME);
            JSONParser parser = new JSONParser();
            JSONArray peopleJSON = (JSONArray)new JSONParser().parse(reader);
            for (int i=0; i < peopleJSON.size(); i++){
                JSONObject personJSON = (JSONObject)peopleJSON.get(i);
                UUID studentID = UUID.fromString((String)personJSON.get(STUDENTLIST_ID));
                String studentFirstName = (String)personJSON.get(STUDENTLIST_FIRST_NAME);
                String studentLastName = (String)personJSON.get(STUDENTLIST_LAST_NAME);
                String studentEmail = (String)personJSON.get(STUDENTLIST_EMAIL);
                String studentPassword = (String)personJSON.get(STUDENTLIST_PASSWORD);
                String studentPhoneNumber = (String)personJSON.get(STUDENTLIST_PHONE_NUMBER);
                UUID studentResumeID = UUID.fromString((String)personJSON.get(STUDENTLIST_RESUME));
                Resume studentResume = ResumeList.getInstance().getResume(studentResumeID);
                JSONArray professorReviewsArray = (JSONArray)personJSON.get(STUDENTLIST_PROFESSOR_REVIEWS);
                ArrayList<Review> professorReviewsList = new ArrayList<Review>(); 
                for(int k = 0; k < professorReviewsArray.size(); k++) {
                    professorReviewsList.add(ReviewList.getInstance().getReview(UUID.fromString(professorReviewsArray.get(k).toString())));
                }
                studentList.add(new Student(studentID, studentFirstName, studentLastName, studentEmail, studentPhoneNumber, studentResume, studentPassword, professorReviewsList));
            }
            return studentList; 
            
        } catch (Exception f) {
            f.printStackTrace();
        }
        return null;
    }

    /**
     * Gets the list of Experiences from the experiencelist json
     * @return ArrayList<Experience>, list of Experiences
     */
    public static ArrayList<Experience> getExperienceList() {
        ArrayList<Experience> experienceList = new ArrayList<Experience>();

        try {
            FileReader reader = new FileReader(EXPERIENCELIST_FILE_NAME);
            JSONParser parser = new JSONParser();
            JSONArray peopleJSON = (JSONArray)new JSONParser().parse(reader);

            for (int i=0; i < peopleJSON.size(); i++){
                JSONObject personJSON = (JSONObject)peopleJSON.get(i);
                UUID experienceID = UUID.fromString((String)personJSON.get(EXPERIENCELIST_ID));
                String experienceCompanyName = (String)personJSON.get(EXPERIENCELIST_COMPANYNAME);
                String experienceStartDate = (String)personJSON.get(EXPERIENCELIST_STARTDATE);
                String experienceEndDate = (String)personJSON.get(EXPERIENCELIST_ENDDATE);
                JSONArray experienceDescriptionArray = (JSONArray)personJSON.get(EXPERIENCELIST_DESCRIPTIONS);
                ArrayList<String> experienceDescriptionList = new ArrayList<String>(); 
                for(int k = 0; k < experienceDescriptionArray.size(); k++) {
                    experienceDescriptionList.add(experienceDescriptionArray.get(k).toString());
                }
                experienceList.add(new Experience(experienceCompanyName, experienceStartDate, experienceEndDate, experienceDescriptionList, experienceID));
            } 
            return experienceList;
        }
            catch (Exception a) {
                a.printStackTrace();
            }
            return null;
    }
    
    /**
     * Gets the list of resumes from the resumelist json
     * @return ArrayList<Resume>, list of resumes
     */
    public static ArrayList<Resume> getResumeList() {
        ArrayList<Resume> resumeList = new ArrayList<Resume>();

        try {
            FileReader reader = new FileReader(RESUMELIST_FILE_NAME);
            JSONParser parser = new JSONParser();
            JSONArray peopleJSON = (JSONArray)new JSONParser().parse(reader);

            for (int i=0; i < peopleJSON.size(); i++){
                JSONObject personJSON = (JSONObject)peopleJSON.get(i);
                UUID resumeID = UUID.fromString((String)personJSON.get(RESUMELIST_ID));
                String resumeFirstName = (String)personJSON.get(RESUMELIST_FIRST_NAME);
                String resumeLastName = (String)personJSON.get(RESUMELIST_LAST_NAME);
                String resumeEmail = (String)personJSON.get(RESUMELIST_EMAIL);
                String phoneNumber = (String)personJSON.get(RESUMELIST_PHONE_NUMBER);
                JSONArray experienceArray = (JSONArray)personJSON.get(RESUMELIST_EXPERIENCES);
                ArrayList<Experience> experienceList = new ArrayList<Experience>(); 
                for(int k = 0; k < experienceArray.size(); k++) {
                    experienceList.add(ExperienceList.getInstance().getExperience(UUID.fromString(experienceArray.get(k).toString())));
                }
                JSONArray skillsArray = (JSONArray)personJSON.get(RESUMELIST_SKILLS);
                ArrayList<String> skillsList = new ArrayList<String>(); 
                for(int k = 0; k < skillsArray.size(); k++) {
                    skillsList.add(skillsArray.get(k).toString());
                }
                resumeList.add(new Resume(resumeFirstName, resumeLastName, resumeEmail, phoneNumber, experienceList, skillsList, resumeID));
            }
            return resumeList;
        } 
        catch (Exception a) {
            a.printStackTrace();
        }
        return null;
    }

    /**
     * Gets the list of reviews from the reviewlist json
     * @return ArrayList<Review>, list of Reviews
     */
    public static ArrayList<Review> getReviewList() {
        ArrayList<Review> reviewList = new ArrayList<Review>();

        try {
            FileReader reader = new FileReader(REVIEWLIST_FILE_NAME);
            JSONParser parser = new JSONParser();
            JSONArray peopleJSON = (JSONArray)new JSONParser().parse(reader);
            for (int i=0; i < peopleJSON.size(); i++){
                JSONObject personJSON = (JSONObject)peopleJSON.get(i);
                UUID reviewID = UUID.fromString((String)personJSON.get(REVIEWLIST_ID));
                double reviewRating = ((Double)personJSON.get(REVIEWLIST_RATING)).doubleValue();
                String reviewComment = (String)personJSON.get(REVIEWLIST_COMMENT);
                String firstName = (String)personJSON.get(REVIEWLIST_FIRST_NAME);
                String lastName = (String)personJSON.get(REVIEWLIST_LAST_NAME);
                reviewList.add(new Review(reviewRating, reviewComment, firstName, lastName, reviewID));   
            }
                return reviewList;
            } 
            
            catch (Exception a) {
                a.printStackTrace();
            }
            return null;
    }
}
