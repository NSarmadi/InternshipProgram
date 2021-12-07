package Classes;

import java.util.Scanner;
import java.util.ArrayList;
/**
 * Creates the StudentUI class that holds all of the UI for a Student Account's functions
 */
public class StudentUI {
    private Scanner scanner;
    private String student = "Student";
    private Student currentStudent;
    private String[] mainMenu = {"Check Job Listings", "Create or Edit Resume", "Edit Profile Information", "Print Resume to Text File", "Log out"};
    private String[] sortMenu = {"Alphabetical (A-Z)", "Wage (Highest to lowest)","Date Added (Most to least recent)", "Search By Keyword", "Go Back"};
    private String[] jobListingMenu = {"View the Reviews", "Apply for this job", "Leave a review", "Go back to the job listings"};
    private String[] resumeMenuChoices = {"View Experiences", "View Skills", "Create Experience", "Go Back"};
    private String[] editExperienceChoices = {"Edit company/school name", "Edit start date", "Edit end date", "Add a new description", "Remove a description", "Delete this Experience", "Go back"};
    private String[] editCreatedExperienceChoices = {"Edit company/school name", "Edit start date", "Edit end date", "Add a new description", "Remove a description", "Go back"};
    private String[] viewSkillsMenu = {"View/Edit Skills", "Add Skill", "Go back"};
    private String[] editInformationMenu = {"First Name", "Last Name", "Phonenumber", "Password", "Go back"};
    private ArrayList<JobListing> joblisting;

    /**
     * StudentUI constructor, intializes the Scanner
     */
    public StudentUI(){
        scanner = new Scanner(System.in);
    }

    /**
     * UI for logging in a student, prompts for email and password, if both are correct then the UI will run with the Student that logs in
     */
    public void loginStudent(){
        System.out.println("\n");
        String email = getField("Email");
        String password = getField("Password");
        if(SystemClass.getInstance().login(email, password, student)){
            SystemClass.getInstance().setCurrentStudent(email);
            currentStudent = SystemClass.getCurrentStudent();
            //Run student
            runStudent();
        } else{
            System.out.println("This student account was not found");
        }
    }

    /**
     * Main UI section for the StudentUI, holds the main menu and its respective options for the Student to go through
     */
    private void runStudent(){
        while(true){
            createline();
            display("Welcome " + currentStudent.getFirstName() + " " + currentStudent.getLastName() + " what would you like to do?");
            displayMenu(mainMenu);
            display("");
            displaynl("Enter a Number: ");
            int userCommand = getUserCommand(mainMenu.length);
			if(userCommand == -1) {
				System.out.println("Not a valid command");
				continue;
			}
			if(userCommand == mainMenu.length -1) {
				SystemClass.logout();
				break;
			}
			switch(userCommand) {
				case(0): 
                    checkJobListings();
					break;
				case(1):
					editResume();
					break;
				case(2):
					editProfileInformation();
					break;
                case(3):
                    currentStudent.printResumeToText();
                    break;
			} 
        }
    }

    /**
     * Helper method to display menus to the console
     * @param menu String[], Menu to be printed out
     */
    private static void displayMenu(String[] menu){
        for (int i=0; i<menu.length;i++){
            System.out.println("("+ (i+1) + ") "+ menu[i]);
        }
    }

    /**
     * UI function for the Student to check job listings, asks for how they should be sorted, prints the sorted list
     * The student will then be given the list of joblistings and be asked to select one to view
     * From there a menu will be displayed showing the options for what a student can do with the selected job listing
     */
    private void checkJobListings(){
        while(true){
            createline();
            display("View Current Job Listings");
            display("How would you like jobs to be sorted?");
            displayMenu(sortMenu);
            display("");
            displaynl("Enter a Number for the filter to use: " );
                int userCommand = getUserCommand(sortMenu.length);
			    if(userCommand == -1) {
				    System.out.println("Not a valid command");
				    continue;
                }
			    if(userCommand == sortMenu.length-1) {
				    break;
			    }
			    switch(userCommand) {
				    case(0):
                        SystemClass.getInstance().setFilter(new AlphabeticalSort());
                        break;
				    case(1):
					    SystemClass.getInstance().setFilter(new WageSort());
                        break;
				    case(2):
					    SystemClass.getInstance().setFilter(new DateSort());
                        break;
                    case(3):
					    SystemClass.getInstance().setFilter(new SearchByKeyword());
                        break;
            }
            while(true){
                sortAndPrintJobs(); 
                displaynl("Please enter a number for a job to view, or (0) to go back: ");
                int userCommand2 = getUserCommand(joblisting.size());
                if(userCommand2 == -2) {
                    break; 
                }
                else if (userCommand2 == -1){
                    display("Invalid number, please try again");
                }
                else {
                    while(true) {
                    JobListing job = joblisting.get(userCommand2);
                    display("");
                    createline(); 
                    display(job.toString());
                    display("What would you like to do?");
                    displayMenu(jobListingMenu);
                    display("");
                    displaynl("Enter a number: ");
                    int userCommand3 = getUserCommand(jobListingMenu.length);
                    if(userCommand3 == -1) {
                        display("Not a valid command, please retry.");
                        continue;
                    }
                    if(userCommand3 == jobListingMenu.length-1) {
                        break; 
                    }
                    else {
                            switch (userCommand3) {
                                case(0):
                                        ArrayList<Review> jobReviews = job.getReviews();
                                        printReviews(jobReviews);
                                        display("");
                                        displaynl("Enter anything to continue: ");
                                        scanner.nextLine();
                                    break;
                                case(1):
                                    if(checkJobForAppliedStudent(job)) { 
                                        job.addAppliedStudent(currentStudent);
                                        display("Successfully Applied");
                                        displaynl("Enter anything to continue");
                                        scanner.nextLine();
                                    }
                                    else {
                                        display("Sorry, you can't apply more than once for a job.");
                                        displaynl("Enter anything to continue");
                                        scanner.nextLine();
                                    }
                                    break;
                                case(2):
                                    if(checkJobForStudentReview(job)) {
                                        double rating = Double.parseDouble(getField("Please give a rating"));
                                        String comment = getField("Please leave a comment");
                                        Review review = new Review(rating, comment, currentStudent.getFirstName(), currentStudent.getLastName());
                                        job.addReview(review);
                                    }
                                    else {
                                        display("Sorry, you can only add one review for this job.");
                                        displaynl("Enter anything to continue");
                                        scanner.nextLine();
                                    }
                                    break;
                                case(3):
                                    break; 
                            }
                    }
                }
            }
            }
        }
    }

    /**
     * UI function for editing a student's resume, gives the options for what a student can do with their resume
     */
    private void editResume() {
        while(true) {
            display("");
            createline();
            display("Create or edit your resume");
            display("What would you like to do with your resume");
            display("");
            displayMenu(resumeMenuChoices);
            displaynl("Enter a number: ");
            int userCommand = getUserCommand(resumeMenuChoices.length);
            if (userCommand == resumeMenuChoices.length - 1) {
                break;
            }
            switch(userCommand) {
                case(0):
                    viewExperiences(); 
                    break;
                case(1):
                    viewSkills(); 
                    break;
                case(2):
                    createExperience(); 
                    break;
                case(3):
                    break;
            }
        }
    }

    /**
     * UI function for the Student to view the Experiences that are on their resume
     * Will then prompt the user to select an experience to edit or back out
     */
    private void viewExperiences() {
        while(true) {
            display("");
            createline();
            display("Here are your current Experiences");
            for(int i = 0; i < currentStudent.getResume().getExperience().size(); i++) {
                displaynl("(" + (i+1) + ") ");
                display(currentStudent.getResume().getExperience().get(i).toString());  
            }
            display("(0) Go Back");
            display("");
            int userCommand = Integer.parseInt(getField("Enter a number for the experience to edit"));
            Experience exp;
            if(userCommand == 0) {
                break;
            }
            else if (userCommand < 0 || userCommand > currentStudent.getResume().getExperience().size()) {
                display("Invalid Command, please try again");
            }
            else {
                exp = currentStudent.getResume().getExperience().get(userCommand-1);
                editExperience(exp);
            }
        }
    }

    /**
     * UI function for editing an experience, gives the options for what can be changed within an experience
     * @param experience Experience to be edited
     */
    private void editExperience(Experience experience) {
        while(true) {
            display("");
            createline();
            display("Would you like to: ");
            display("");
            displayMenu(editExperienceChoices);
            display("");
            displaynl("Please enter a Number: ");
            int userCommand = getUserCommand(editExperienceChoices.length);
            if(userCommand == editExperienceChoices.length-1)
                break;
            if(userCommand == 5) {
                SystemClass.getInstance().removeExperience(experience);
                currentStudent.getResume().removeExperience(experience);
                break;
            }
            switch(userCommand) {
                case(0):
                    editExperienceCompany(experience);
                    break;
                case(1):
                    editExperienceStartDate(experience);
                    break;
                case(2):
                    editExperienceEndDate(experience);
                    break;
                case(3):
                    addNewDescription(experience);
                    break;
                case(4):
                    removeDescription(experience);
                    break;
                case(5):
                    SystemClass.getInstance().removeExperience(experience);
                    currentStudent.getResume().removeExperience(experience);
                    break;
                case(6):
                    break; 
            }
        }
    }

    /**
     * UI function for editing a newly created experience
     * @param experience Experience, experience to be edited
     */
    private void editCreatedExperience(Experience experience) {
        while(true) {
            display("");
            createline();
            display("Would you like to: ");
            display("");
            displayMenu(editCreatedExperienceChoices);
            display("");
            displaynl("Please enter a Number: ");
            int userCommand = getUserCommand(editCreatedExperienceChoices.length);
            if(userCommand == editCreatedExperienceChoices.length-1)
                break;
            switch(userCommand) {
                case(0):
                    editExperienceCompany(experience);
                    break;
                case(1):
                    editExperienceStartDate(experience);
                    break;
                case(2):
                    editExperienceEndDate(experience);
                    break;
                case(3):
                    addNewDescription(experience);
                    break;
                case(4):
                    removeDescription(experience);
                    break;
                case(5):
                    break;
            }
        }
    }

    /**
     * UI function to edit the company for an experience
     * @param exp Experience, experience to have the company name changed
     */
    private void editExperienceCompany(Experience exp) {
        display("");
        createline();
        display("Please  enter a company or school name: ");
        String name = getField("Name");
        exp.setCompanyName(name);
    }

    /**
     * UI function to edit the Start date of an experience
     * @param exp Experience, experience to have the start date edited for
     */
    private void editExperienceStartDate(Experience exp) {
        display("");
        createline();
        display("Please enter a Start Date: ");
        String name = getField("Start Date");
        exp.setStartDate(name);
    }

    /**
     * UI function to edit the end date of an experience
     * @param exp Experience, experience to have the end date edited
     */
    private void editExperienceEndDate(Experience exp) {
        display("");
        createline();
        display("Please enter a End Date: ");
        String name = getField("End Date");
        exp.setEndDate(name);
    }

    /**
     * UI function to add a new description to an experience
     * @param exp Experience, experience to have a new description added to
     */
    private void addNewDescription(Experience exp) {
        display("");
        createline();
        display("Please enter a New Description: ");
        String name = getField("Description");
        exp.addDescription(name);
    }

    /**
     * UI function to remove the a description from an Experience, prints out the descriptions of the experience and asks which to remove or to go back
     * @param exp Experience, the experience to have a description removed from
     */
    private void removeDescription(Experience exp) {
        display("");
        createline(); 
        display("Please Enter the number for a description to remove, or (0) to go back");
        display("");
        for(int i = 0; i < exp.getDescriptions().size(); i++) {
            displaynl("(" + (i+1) + ") ");
            display(exp.getDescriptions().get(i));
        }
        displaynl("Enter a number: ");
        int userCommand = getUserCommand(exp.getDescriptions().size());
        if(userCommand < 0 && userCommand != 0) {
            display("Invalid command");
        }
        else if(userCommand >= 0 && userCommand < exp.getDescriptions().size()) {
            exp.removeDescription(userCommand);
        }
    }

    /**
     * UI function to sort the list of jobs and then print out the sorted list of jobs
     */
    private void sortAndPrintJobs() {
        createline();
        display("Job Listings:");
        joblisting = SystemClass.getInstance().getJobListingList();
        joblisting = SystemClass.getInstance().getFilter().sortList(joblisting);
        if (joblisting==null) {
            joblisting = SystemClass.getInstance().getJobListingList();
            return;
        }
        for(int i=0;i<joblisting.size();i++){
            display("("+(i+1)+")  " + joblisting.get(i).jobListingBasicInfo());
        }
    }

    /**
     * UI function for the Skills on a student's resume
     */
    private void viewSkills() {
        while(true){
            display("");
            createline();
            display("What Would you like to do?: ");
            displayMenu(viewSkillsMenu);
            display("");
            displaynl("Enter a number: ");
            int userCommand = getUserCommand(viewSkillsMenu.length);
            if(userCommand == viewSkillsMenu.length-1)
                break;
            switch(userCommand) {
                case(0):
                    editSkills();
                    break;
                case(1):
                    createSkill();
                    break;
                case(2):    
                    break; 
            }
        }
    }

    /**
     * UI function for a student to edit the skills currently on their resume, prompts them to select a number to remove a skill or go back
     */
    private void editSkills() {
        while(true) {
            display("");
            createline();
            display("Here are your skills: ");
            display("");
            for(int i = 0; i < currentStudent.getResume().getSkills().size(); i++) {
                displaynl("(" + (i+1) + ") ");
                display(currentStudent.getResume().getSkills().get(i));
            }
            display("(0) Go Back");
            display("");
            displaynl("Enter a number to remove a skill or (0) to go back: ");
            int userCommand = getUserCommand(currentStudent.getResume().getSkills().size());
            if(userCommand == -2) {
                break; 
            }
            else if(userCommand < 0) {
                display("Invalid Number");
            }
            else {
                currentStudent.getResume().removeSkill(currentStudent.getResume().getSkills().get(userCommand));
            }
        }
    }

    /**
     * UI function for creating a new skill for a student's resume
     */
    private void createSkill() {
        display("");
        createline();
        display("Please enter your new skill");
        display("");
        String userInput = getField("Skill");
        currentStudent.getResume().addSkill(userInput);
    }

    /**
     * UI function for creating a new Experience object for a Student's resume
     */
    private void createExperience() {
        Experience exp = new Experience("Empty", "Empty", "Empty");
        currentStudent.getResume().addExperience(exp);
        SystemClass.getInstance().addExperience(exp);
        editCreatedExperience(exp);
    }

    /**
     * UI function for editing a students profile information, prompts the user to select which information they want to edit
     */
    private void editProfileInformation(){
        while(true) {      
            display("");
            createline(); 
            display("What information would you like to edit?");
            display("");
            displayMenu(editInformationMenu);
            display("");
            displaynl("Enter a Number: ");
            int userCommand = getUserCommand(editInformationMenu.length);
            if(userCommand == editInformationMenu.length-1)
                break;
            switch(userCommand) {
                case(0):
                    editFirstName();
                    break;
                case(1):
                    editLastName(); 
                    break;
                case(2):
                    editPhoneNumber(); 
                    break;
                case(3):
                    editPassword(); 
                    break;
            }
        }
    }

    /**
     * UI function for editing the first name of a Student
     */
    private void editFirstName() {
        display("");
        createline(); 
        display("Please enter a new first name");
        display("");
        String firstName = getField("First Name");
        currentStudent.setFirstName(firstName);
        currentStudent.getResume().setFirstName(firstName);
    }

    /**
     * UI function for editing the Last Name of a Student
     */
    private void editLastName() {
        display("");
        createline(); 
        display("Please enter a new Last name");
        display("");
        String lastName = getField("Last Name");
        currentStudent.setLastName(lastName);
        currentStudent.getResume().setLastName(lastName);
    }

    /**
     * UI function for editing the Phone number of a student
     */
    private void editPhoneNumber() {
        display("");
        createline(); 
        display("Please enter a new Phone Number");
        display("");
        String phoneNumber = getField("Phone Number");
        currentStudent.setPhoneNumber(phoneNumber);
        currentStudent.getResume().setPhoneNumber(phoneNumber);;
    }

    /**
     * UI function for editing the password of a student
     */
    private void editPassword() {
        display("");
        createline(); 
        display("Please your current password");
        String passEntered = getField("Password");
        if(passEntered.equals(currentStudent.getPassword())) {
            display("Please your new password");
            String newPassword = getField("New Password");
            currentStudent.setPassword(newPassword);
        }
        else {
            display("Invalid Password, Please retry");
        }
    }

    /**
     * Checks if a job already has the current student applied for it
     * @param job JobListing being checked for
     * @return
     */
    private boolean checkJobForAppliedStudent(JobListing job) {
        for(Student student : job.getAppliedStudents()) {
            if(student.getID().equals(currentStudent.getID())) {
                return false; 
            }
        }
        return true; 
    }

    /**
     * Checks if a job listing already has a review from a 
     * @param job
     * @return
     */
    private boolean checkJobForStudentReview(JobListing job) {
        for(Review review : job.getReviews()) {
            if(review.getFirstName().equals(currentStudent.getFirstName()) && review.getLastName().equals(currentStudent.getFirstName())) {
                return false; 
            }
        }
        return true; 
    }

    /**
     * Prints a list of reviews to the console
     * @param reviews ArrayList<Review>, list of reviews to be displayed
     */
    private static void printReviews(ArrayList<Review> reviews) {
        for(int i = 0; i < reviews.size(); i++) {
            display("");
            display(reviews.get(i).toString());
        }
    }

    /**
     * Helper method to get a input from the user
     * @param prompt String, prompt telling the user what to input
     * @return String, User's input
     */
    private String getField(String prompt) {
		System.out.print(prompt + ": ");
		return scanner.nextLine();
	}

    /**
     * Helper method to print a line to the console
     */
    private static void createline(){
        System.out.println("----------------------------");
    }

    /**
     * Helper method to print something to the console
     * @param stuff String, thing to be printed
     */
    private static void display(String stuff){
        System.out.println(stuff);
    }

    /**
     * Helper method to print something to the console without ending the line
     * @param stuff String, thing to be printed
     */
    private static void displaynl(String stuff){
        System.out.print(stuff);
    }

    /**
     * Helper method to take in an int input from the user
     * @param UserInput Maximum value for the user's input
     * @return int, corresponding value to user's input, if -1 input is invalid, if -2, user input is 0, all others correspond to the range from 0 to the maximum-1
     */
    private int getUserCommand(int UserInput){
        //System.out.print("Enter a number: ");
        String input = scanner.nextLine();
        System.out.println("----------------------------");
		int command = Integer.parseInt(input) - 1;
		if(command >= 0 && command <= UserInput -1) return command;
        if(command == -1) return -2;
        return -1;
    }
}
