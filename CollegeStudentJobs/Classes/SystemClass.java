package Classes;

import java.util.ArrayList;
/**
 * Creates the SystemClass class which is our program's facade, holding all of the lists and main program functions
 * The SystemClass is a singleton
 */
public class SystemClass {
    private ArrayList<Student> studentList;
    private StudentList studentListInstance;
    private ArrayList<Business> businessList;
    private BusinessList businessListInstance;
    private ArrayList<Admin> adminList;
    private AdminList adminListInstance; 
    private ArrayList<Professor> professorList;
    private ProfessorList professorListInstance;
    private ArrayList<JobListing> jobList;
    private JobListingList jobListingListInstance; 
    private ResumeList resumeListInstance; 
    private ArrayList<Resume> resumeList; 
    private ReviewList reviewListInstance; 
    private ArrayList<Review> reviewList;  
    private ExperienceList experienceListInstance; 
    private ArrayList<Experience> experienceList; 
    private Filter filter;
    private ArrayList<User> deletedUserList;
    private DeletedUserList deletedUserListInstance; 
    private static SystemClass system;
    private static Student currentStudent;
    private static Business currentBusiness;
    private static Professor currentProfessor;
    private static Admin currentAdmin;

    /**
     * System Class constructor, instantiates all the instances of the List classes and get the actual lists themselves
     */
    private SystemClass() {
        this.reviewListInstance = ReviewList.getInstance();
        this.experienceListInstance = ExperienceList.getInstance(); 
        this.resumeListInstance = ResumeList.getInstance();
        this.studentListInstance = StudentList.getInstance();
        this.jobListingListInstance = JobListingList.getInstance(); 
        this.businessListInstance = BusinessList.getInstance();
        this.adminListInstance = AdminList.getInstance();
        this.professorListInstance = ProfessorList.getInstance(); 
        this.deletedUserListInstance = DeletedUserList.getInstance(); 
        this.reviewList = reviewListInstance.getReviews();
        this.experienceList = experienceListInstance.getExperiences(); 
        this.resumeList = resumeListInstance.getResumes(); 
        this.studentList = studentListInstance.getStudents(); 
        this.jobList = jobListingListInstance.getJobListings(); 
        this.businessList = businessListInstance.getBusinesses();
        this.adminList = adminListInstance.getAdmins(); 
        this.professorList = professorListInstance.getProfessors(); 
        this.deletedUserList = deletedUserListInstance.getDeletedUsers(); 
        this.filter = new AlphabeticalSort();
    }

    /**
     * Gets the single instance of the SystemClass object
     * @return SystemClass, the SystemClass object
     */
    public static SystemClass getInstance() {
        if (system == null) {
            system = new SystemClass();
        }
        return system;
    }

    /**
     * Creates a new student account and adds it to the student list
     * @param email String, student's email
     * @param password String, Student's password
     * @param firstName String, student's first name
     * @param lastName String, student's last name
     * @param phoneNumber String, Student's phone number
     * @return bool, true if account is created and added, false if not
     */
    public boolean createStudentAccount(String email, String password, String firstName, String lastName, String phoneNumber){
        return studentListInstance.addStudent(email,password, firstName, lastName, phoneNumber);
    }

    /**
     * Creates a new business account and adds it to the business list
     * @param email String, Business' email
     * @param password String, Business' password
     * @param companyName String, Business company name
     * @param companyAddress String, Business' address
     * @return bool, true if account is created, false if not
     */
    public boolean createBusinessAccount(String email, String password, String companyName, String companyAddress){
        return businessListInstance.addBusiness(email, password,companyName,companyAddress);
    }

    /**
     * Creates a new professor account and adds it to the professor list
     * @param email String, Professor email
     * @param password String, Professor password
     * @param firstName String, Professor first name
     * @param lastName String, Professor last name
     * @return bool, true if account is created, false if not
     */
    public boolean createProfessorAccount(String email, String password, String firstName, String lastName){
        return professorListInstance.addProfessor(email,password,firstName,lastName);
    }

    /**
     * Creates admin account and adds it to admin list
     * @param email String, Admin's email
     * @param password String, Admins password
     * @return bool, true if account is created, false if not
     */
    public boolean createAdminAccount(String email, String password){
        return adminListInstance.addAdmin(email,password);
    }

    /**
     * Removes a student from the studentlist
     * @param student Student, student to be removed
     */
    public void removeStudent(Student student) {
        studentList.remove(student);
    }

    /**
     * Removes a business from the businesslist
     * @param business Business, business to be removed
     */
    public void removeBusiness(Business business) {
        businessList.remove(business);
    }

    /**
     * Adds and admin account to the admin list
     * @param admin Admin, admin to be added to the list
     */
    public void addAdmin(Admin admin) {
        adminList.add(admin);
    }

    /**
     * Removes and admin from the admin list
     * @param admin Admin, admin to be removed
     */
    public void removeAdmin(Admin admin) {
        adminList.remove(admin);
    }

    /**
     * Removes a professor from the professor list
     * @param professor Professor, professor being removed
     */
    public void removeProfessor(Professor professor) {
        professorList.remove(professor);
    }

    /**
     * Adds a job listing  to the joblisting list
     * @param jobListing JobListing, job to be added to the list
     */
    public void addJobListing(JobListing jobListing) {
        jobList.add(jobListing);
    }

    /**
     * Removes a joblisting from the joblisting list
     * @param jobListing JobListing, joblisting being removed from the list
     */
    public void removeJobListing(JobListing jobListing) {
        jobList.remove(jobListing);
    }

    /**
     * Filters the list of jobs and returns the filtered list
     * @return Filtered jobList
     */
    public ArrayList<JobListing> filterJobs() {
        ArrayList<JobListing> filteredJobList = filter.sortList(this.jobList);
        return filteredJobList;
    }

    /**
     * Sets the filter type 
     * @param filter Filter, the filter type
     */
    public void setFilter(Filter filter) {
        this.filter = filter; 
    }

    /**
     * Gets the current filter type
     * @return Filter, the current filter type
     */
    public Filter getFilter() {
        return this.filter;
    }

    /**
     * Login function, checks if a user exists based on type and login information
     * @param email String, User's email
     * @param password String, User's password
     * @param user String, User type
     * @return bool, true if user can be logged in, false if not
     */
    public boolean login(String email, String password, String user){
        //boolean thingy = false;
        if(user.equals("Student")){
            return studentListInstance.haveStudent(email, password);
        } else if (user.equals("Business")){
            return businessListInstance.haveBusiness(email, password);
        } else if (user.equals("Professor")){
            return ProfessorList.haveProfessor(email, password);
        } else if(user.equals("Admin")){
            return adminListInstance.haveAdmin(email,password);
        }
        return false;
    }

    /**
     * Sets the current student to the student found matching the email
     * @param email String, student's email
     */
    public void setCurrentStudent(String email) {
        currentStudent = studentListInstance.getStudent(email);
    }

    /**
     * Sets the current business to the business found matching the email
     * @param email String, business' email
     */
    public void setCurrentBusiness(String email) {
        currentBusiness = businessListInstance.getBusiness(email);
    }

    /**
     * Sets the current professor to the professor found matching the email
     * @param email String, professor's email
     */
    public void setCurrentProfessor(String email) {
        currentProfessor = professorListInstance.getProfessor(email);
    }

    /**
     * Sets the current admin to the admin matching the email
     * @param email String, email for the admin
     */
    public void setCurrentAdmin(String email) {
        currentAdmin = adminListInstance.getAdmin(email);
    }

    /**
     * Gets the current business
     * @return Business, the current business
     */
    public static Business getCurrentBusiness(){
        return currentBusiness;
    }

    /**
     * Gets the current student
     * @return Student, the current student
     */
    public static Student getCurrentStudent(){
        return currentStudent;
    }
    
    /**
     * Gets the current professor
     * @return Professor, gets the current professor
     */
    public static Professor getCurrentProfessor(){
        return currentProfessor;
    }
    
    /**
     * Gets the current admin
     * @return Admin, current admin
     */
    public static Admin getCurrentAdmin() {
        return currentAdmin;
    }
    
    /**
     * Logout function, saves all of the lists in the system
     */
    public static void logout(){
        StudentList.saveStudents();
        BusinessList.saveBusiness();
        ProfessorList.saveProfessor();
        AdminList.saveAdmins(); 
        ExperienceList.saveExperiences();
        ReviewList.saveReviewList();
        ResumeList.saveResumeList(); 
        JobListingList.saveJobListings();
    }

    /**
     * Gets the List of JobListings
     * @return ArrayList<JobListing>, list of job listings
     */
    public ArrayList<JobListing> getJobListingList() {
        return this.jobList; 
    }

    /**
     * Gets the list of Professors
     * @return ArrayList<Professor>, List of professors
     */
    public ArrayList<Professor> getProfessorList() {
        return this.professorList;
    }

    /**
     * Gets the list of students
     * @return ArrayList<Student> List of students
     */
    public ArrayList<Student> getStudentList() {
        return this.studentList;
    }

    /**
     * Gets the admin list
     * @return
     */
    public ArrayList<Admin> getAdminList() {
        return this.adminList;
    }

    /**
     * Gets the business list
     * @return ArrayList<Business>, business list
     */
    public ArrayList<Business> getBusinessList() {
        return this.businessList;
    }

    /**
     * gets the deleted user list
     * @return ArrayList<User>,  deleted user list
     */
    public ArrayList<User> getDeletedUserList() {
        return this.deletedUserList;
    }

    /**
     * Gets the Experience list
     * @return ArrayList<Experience>, experience list
     */
    public ArrayList<Experience> getExperienceList() {
        return this.experienceList;
    }

    /**
     * Gets the resume List
     * @return ArrayList<Resume>, resume list
     */
    public ArrayList<Resume> getResumeList() {
        return this.resumeList;
    }

    /**
     * gets the review list
     * @return ArrayList<Review>, review list
     */
    public ArrayList<Review> getReviewList() {
        return this.reviewList;
    }

    /**
     * Removes an experience from the Experience list
     * @param exp Experience, experience to be removed
     */
    public void removeExperience(Experience exp) {
        experienceList.remove(exp);
    }

    /**
     * Saves all of the lists in the system
     */
    public void save() {
        ExperienceList.saveExperiences();
        AdminList.saveAdmins();
        BusinessList.saveBusiness();
        DeletedUserList.saveDeletedUsers();
        JobListingList.saveJobListings();
        ProfessorList.saveProfessor();
        ResumeList.saveResumeList();
        ReviewList.saveReviewList();
        StudentList.saveStudents();
    }

    /**
     * Adds a review to the review list
     * @param review Review, review to be added
     */
    public void addReview(Review review) {
        reviewList.add(review);
    }

    /**
     * Removes a review from the review list
     * @param review Review, review to be removed
     */
    public void removeReview(Review review){
        reviewList.remove(review);
    }

    /**
     * Adds a resume to the resume list
     * @param resume Resume, resume to be added
     */
    public void addResume(Resume resume) {
        resumeList.add(resume);
    }
    
    /**
     * Adds and experience to the experience list
     * @param exp Experience, experience to be added
     */
    public void addExperience(Experience exp) {
        experienceList.add(exp);
    }
}