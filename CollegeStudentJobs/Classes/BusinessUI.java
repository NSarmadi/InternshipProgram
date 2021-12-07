package Classes;
import java.util.Scanner; 
import java.util.ArrayList;
/**
 * Creates the BusinessUI class, containing all of the UI information for a business
 */
public class BusinessUI {
    private Business currentBusiness; 
    private Scanner scanner = new Scanner(System.in);
    private String[] mainMenu = {"Create a job listing", "View your job listings", "View all job listings", "Edit account settings", "Log out"};
    private String[] newJobListingMenu = {"Add Experience", "Add Skill", "Finished"};
    private String[] existingJobListingMenu = {"Edit Job Listing", "Remove Job Listing", "View Reviews", "See Applied Students", "Go Back"};
    private String[] editJobListingMenu = {"Job Title", "Job Description", "Skills", "Experience", "Wage", "Go Back"};
    private String[] experienceOptions = {"Add a new experience", "Remove and Experience", "Go back"};
    private String[] skillsOptions = {"Add a new Skill", "Remove a Skill", "Go Back"};
    private String[] jobListingMenu = {"View Reviews", "Go back"};
    private String[] sortMenu = {"Alphabetical (A-Z)", "Wage (Highest to lowest)","Date Added (Most to least recent)", "Search By Keyword", "Go Back"};
    private String[] editSettingsMenu = {"Change Business Name", "Change Address", "Change Password", "Go Back"};
    private ArrayList<JobListing> joblisting;
    private ArrayList<JobListing> jobList;
    
    /**
     * Gets a Business' login, if the login is good, then the business UI will be ran, if not, it will prompt for a retry
     */
    public void loginBusiness(){
        System.out.println("\n");
        String email = getField("Email");
        String password = getField("Password");

        if(SystemClass.getInstance().login(email,password, "Business")){
            SystemClass.getInstance().setCurrentBusiness(email);    
            currentBusiness = SystemClass.getCurrentBusiness();
            jobList = currentBusiness.getJobListings();
            runBusiness();
        }
        else {
            display("This Business could not be found");
        }
    }

    /**
     * Main Business UI method, prompts for what the business wants to do in the main menu
     */
    private void runBusiness() {
        while(true) {
            display("");
            createline();
            display("Welcome, " + currentBusiness.getBusinessName() + ", what would you like to do?");
            displayMenu(mainMenu);
            display("");
            displaynl("Enter a number: ");
            int userCommand = getUserCommand(mainMenu.length);
            if(userCommand == mainMenu.length-1) {
                break;
            }
            if(userCommand < 0) {
                display("Invalid command");
            }
            switch(userCommand) {
                case(0):
                    if(currentBusiness.getIsVerified()) {
                        createJobListing();  
                    }
                    else {
                        display("Sorry, your account is not verified yet, please wait until then to perform this action.");
                    }
                    break;
                case(1):
                    if(currentBusiness.getIsVerified()) {
                        viewMyJobListings(); 
                    }
                    else {
                        display("Sorry, your account is not verified yet, please wait until then to perform this action.");
                    }
                    break; 
                case(2):
                    viewAllJobListings();
                    break;
                case(3):
                    editAccountSettings(); 
                    break; 
            }
        }
    }

    /**
     * Starter method for creating a job listing through UI
     */
    private void createJobListing(){
            display("");
            createline(); 
            display("Create a new job listing");
            display("Please enter the following details for the job listing");
            display("");
            String title = getField("Job title");
            String description = getField("Job description");
            double wage = Double.valueOf(getField("Wage"));
            JobListing job = new JobListing(currentBusiness.getBusinessName(), title, description, wage);
            SystemClass.getInstance().addJobListing(job);
            currentBusiness.addJobListing(job);
            while (true) {
                display("");
                createline(); 
                display("Would you like to add an experience or skill?");
                display("");
                displayMenu(newJobListingMenu);
                display("");
                displaynl("Enter a number: ");
                int userCommand = getUserCommand(newJobListingMenu.length);
                if(userCommand == newJobListingMenu.length-1){
                    break; 
                } 
                if(userCommand < 0) {
                    display("Not a valid command");
                }
                switch(userCommand) {
                    case(0):
                        addExperience(job);
                        break;
                    case(1):
                        addSkill(job);
                        break; 
                }
            }
    }

    /**
     * UI for adding an experience to the passed in job listing
     * @param job JobListing, job to have an experience added to it
     */
    private void addExperience(JobListing job) {
        display("");
        createline();
        display("Please write a new experience for the job listing: ");
        display("");
        String exp = getField("Experience");
        job.addExperience(exp);
    }

    /**
     * UI for adding a skill to the passed in job listing
     * @param job JobListing, job to have a Skill added to it
     */
    private void addSkill(JobListing job) {
        display("");
        createline();
        display("Please write a new skill for the job listing: ");
        display("");
        String skill = getField("Skill");
        job.addSkill(skill);
    }

    /**
     * UI method for showing the Business' job listings and giving the menu for what to do with the job listing
     */
    private void viewMyJobListings() {
        display("");
        createline();
        display(currentBusiness.getBusinessName() + "'s job listings: ");
        display("");
        while(true) {
            printJobListings();
            display(""); 
            displaynl("Enter a number: ");
            int userCommand = getUserCommand(jobList.size());
            if(userCommand == -2) {
                break;
            }
            if(userCommand < 0) {
                display("Not a valid number");
            }
            else {
                while (true) {
                    JobListing job = jobList.get(userCommand);
                    display("");
                    createline(); 
                    display("Here is the job listing: ");
                    display(job.toString());
                    display("");
                    display("What would you like to do?");
                    displayMenu(existingJobListingMenu);
                    display("");
                    displaynl("Enter a number: ");
                    int userCommand2 = getUserCommand(existingJobListingMenu.length);
                    if(userCommand2 == existingJobListingMenu.length-1) {
                        break;
                    }
                    if(userCommand2 < 0) {
                        display("Invalid command");
                    }
                    if(userCommand2 == 1) {
                        removeJobListing(job);
                        break;
                    }
                    else {
                        switch(userCommand2) {
                            case(0):
                                editJobListing(job);
                                break;
                            case(2):
                                viewReviews(job);
                                break;
                            case(3):
                                viewAppliedStudents(job);
                                break;
                        }
                    }
                }
            }
        }
    }

    /**
     * UI for editting a job listing, gives the options for what to edit
     * @param job JobListing, job to be editted
     */
    private void editJobListing(JobListing job) {
        while(true) {
            display("");
            createline();
            display("What do you want to edit?");
            display("");
            displayMenu(editJobListingMenu);
            display("");
            displaynl("Enter a number: ");
            int userCommand = getUserCommand(editJobListingMenu.length);
            if(userCommand == editJobListingMenu.length-1) {
                break;
            }
            if(userCommand < 0) {
                display("Not a valid command");
            }
            else {
                switch(userCommand) {
                    case(0):
                        editJobTitle(job);
                        break;
                    case(1):
                        editJobDescription(job);
                        break;
                    case(2):
                        editSkills(job);
                        break;
                    case(3): 
                        editExperience(job);
                        break;
                    case(4):
                        editWage(job);
                        break;
                }
            }
        }
    }

    /**
     * UI for editing a job's title
     * @param job JobListing, job to have the title edited
     */
    private void editJobTitle(JobListing job) {
        display("");
        createline();
        display("Please enter a new job title");
        display("");
        String title = getField("Job Title");
        job.setTitle(title);
        display("Job title edited");
    }

    /**
     * UI for editing a job's description
     * @param job JobListing, job to have the description edited
     */
    private void editJobDescription(JobListing job) {
        display("");
        createline();
        display("Please enter a new job description");
        display("");
        String title = getField("Job Description");
        job.setDescription(title);
        display("Job title edited");
    }

    /**
     * UI for editing a job's experiences 
     * @param job JobListing, job to have the Experiences edited
     */
    private void editExperience(JobListing job) {
        while(true) {
            display("");
            createline();
            display("What would you like to do with the job's experiences");
            display("");   
            displayMenu(experienceOptions);
            display("");
            displaynl("Enter a number: ");
            int userCommand = getUserCommand(experienceOptions.length);
            if(userCommand == experienceOptions.length-1) {
                break;
            }
            if(userCommand < 0) {
                display("Invalid command");
            }
            else {
                switch(userCommand) {
                    case(0):
                        addNewExperience(job);
                        break;
                    case(1):
                        removeJobExperience(job);
                        break; 
                }
            }
        }
    }

    /**
     * UI for adding a new experience to a job
     * @param job JobListing, job to have a new experience added to
     */
    private void addNewExperience(JobListing job) {
        display("");
        createline();
        display("Please enter your new experience");
        display("");
        String experience = getField("Experience");
        job.addExperience(experience);
        display("Experience added");
    }

    /**
     * UI for removing an experience for a job listing
     * @param job JobListing, job to have an experience removed from
     */
    private void removeJobExperience(JobListing job) {
        while(true) {
            display("");
            createline();
            display("Which experience would you like to remove");
            display("");
            displayExperiences(job);
            display("");
            display("Enter a number: ");
            int userCommand = getUserCommand(job.getExperienceList().size());
            if(userCommand == -2){
                break;
            }
            if(userCommand < 0) {
                display("Invalid command");
            }
            else {
                job.removeExperience(userCommand);
                display("Experience Removed");
            }
        }
    }

    /**
     * Display's all the experiences for a job listing
     * @param job JobListing, job to have the experiences listed from
     */
    private void displayExperiences(JobListing job) {
        for(int i = 0; i < job.getExperienceList().size(); i++) {
            display("(" + (i+1) + ") " + job.getExperienceList().get(i));
        }
        display("(0) Go Back");
    }

    /**
     * UI for editing skills for a jobListing
     * @param job JobListing, job to have the skills edited
     */
    private void editSkills(JobListing job) {
        while(true) {
            display("");
            createline();
            display("What would you like to do with the job's skills");
            display("");   
            displayMenu(skillsOptions);
            display("");
            displaynl("Enter a number: ");
            int userCommand = getUserCommand(skillsOptions.length);
            if(userCommand == skillsOptions.length-1) {
                break;
            }
            if(userCommand < 0) {
                display("Invalid command");
            }
            else {
                switch(userCommand) {
                    case(0):
                        addNewSkill(job);
                        break;
                    case(1):
                        removeSkill(job);
                        break; 
                }
            }
        }
    }

    /**
     * UI for adding a new skill to a job listing
     * @param job JobListing, job to have a skill added to
     */
    private void addNewSkill(JobListing job) {
        display("");
        createline();
        display("Please enter your new skill");
        display("");
        String skill = getField("Skill");
        job.addSkill(skill);
        display("Skill added");
    }

    /**
     * UI for removing a skill to a job listing
     * @param job JobListing, job to have a skill removed from 
     */
    private void removeSkill(JobListing job) {
        while(true) {
            display("");
            createline();
            display("Which skill would you like to remove");
            display("");
            displaySkills(job);
            display("");
            display("Enter a number: ");
            int userCommand = getUserCommand(job.getSkillList().size());
            if(userCommand == -2){
                break;
            }
            if(userCommand < 0) {
                display("Invalid command");
            }
            else {
                job.removeSkill(userCommand);
                display("Skill Removed");
            }
        }
    }

    /**
     * Displays the skills from a job listing
     * @param job JobListing, job to display the skills of
     */
    private void displaySkills(JobListing job) {
        for(int i = 0; i < job.getSkillList().size(); i++) {
            display("(" + (i+1) + ") " + job.getSkillList().get(i));
        }
        display("(0) Go Back");
    }

    /**
     * UI for editing the wage of a job listing
     * @param job JobListing, job to have the wage edited
     */
    private void editWage(JobListing job) {
        display("");
        createline(); 
        display("Please enter what you want the wage to be");
        display("");
        double wage = Double.valueOf(getField("Wage"));
        job.setWage(wage);
    }

    /**
     * UI for removing a job listing
     * @param job JobListing, job to be removed
     */
    private void removeJobListing(JobListing job) {
        SystemClass.getInstance().removeJobListing(job);
        currentBusiness.removeJobListing(job);
    }

    /**
     * UI for viewing the reviews of a job listing
     * @param job JobListing, job to view the reviews of
     */
    private void viewReviews(JobListing job) {
        display(""); 
        createline(); 
        display("Reviews for " + job.getJobTitle());
        display("");
        for(int i = 0; i < job.getReviews().size(); i++) {
            display("(" + (i+1) + ") " + job.getReviews().get(i).toString());
            display("");
        }
        display("Enter anything when ready to move on");
        scanner.nextLine();
    }

    /**
     * UI for viewing the applied students for a job listing
     * @param job JobListing, job to view the applied students of
     */
    private void viewAppliedStudents(JobListing job) {
        while(true) {
            display("");
            createline();
            display("Applied Students for " + job.getJobTitle());
            display("");
            for(int i = 0; i < job.getAppliedStudents().size(); i++) {
                display("(" + (i+1) + ") " + job.getAppliedStudents().get(i).getFirstName() + " " + job.getAppliedStudents().get(i).getLastName());
                display("");
            }
            display("(0) Go Back");
            display("");
            displaynl("Enter a number for the student who's profile you want to view");
            int userCommand = getUserCommand(job.getAppliedStudents().size());
            if(userCommand == -2) {
                break; 
            }
            if(userCommand < 0) {
                display("Invalid command");
            }
            else {
                display("");
                createline();
                display(job.getAppliedStudents().get(userCommand).toString());
                displaynl("Enter anything when ready to move on: ");
                scanner.nextLine();
            }    
        }
    }

    /**
     * UI for printing all of the job listings in the system
     */
    private void printJobListings() {
        for(int i = 0; i < jobList.size(); i++) {
            displaynl("(" + (i+1) + ") ");
            display(jobList.get(i).getJobTitle());
        }
        display("(0) Go back");
    }

    /**
     * UI for for viewing all of the job listings in the system
     */
    private void viewAllJobListings() {
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
                display("");
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
                        if(userCommand3 == jobListingMenu.length-1) {
                            break;
                        }
                        else {
                            display("");
                            createline();
                            while(true) {
                                for(int i = 0; i < job.getReviews().size(); i++) {
                                    display(job.getReviews().get(i).toString());
                                }
                                display("");
                                display("Enter anything to go back");
                                scanner.nextLine();
                                break;
                            }   
                        }
                    }
                }
            }
        }
    }

    /**
     * UI for editing the current business' account settings
     */
    private void editAccountSettings() {
        while(true) {
            display("");
            createline(); 
            display("What setting would you like to change?");
            display("");
            displayMenu(editSettingsMenu);
            display("");
            displaynl("Enter a number: ");
            int userCommand = getUserCommand(editSettingsMenu.length);
            if(userCommand == editSettingsMenu.length-1) {
                break;
            }
            if(userCommand < 0) {
                display("Invalid command");
            }
            else {
                switch(userCommand) {
                    case(0):
                        editBusinessName();
                        break;
                    case(1):
                        editBusinessAddress(); 
                        break;
                    case(2):
                        editBusinessPassword();
                        break;
                }
            }
        }
    }

    /**
     * UI for editing business' name
     */
    private void editBusinessName() {
        display("");
        createline();
        display("Enter the new business name");
        display("");
        String name = getField("Business Name");
        currentBusiness.setBusinessName(name);
    }

    /**
     * UI for editing business' address
     */
    private void editBusinessAddress() {
        display("");
        createline();
        display("Enter the new business address");
        display("");
        String address = getField("Business Address");
        currentBusiness.setAddress(address);
    }

    /**
     * UI for editing business' password
     */
    private void editBusinessPassword() {
        display("");
        createline();
        display("Enter your current password");
        String pass = getField("Current Password");
        if(pass.equals(currentBusiness.getPassword())) {
            display("Enter your new password");
            String newpass = getField("New Password");
            currentBusiness.setPassword(newpass);
        }
        else {
            display("Password is incorrect, please try again");
        }
    }

    /**
     * UI for sorting jobs by how the user wants and printing the joblistings out
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
     * Helper method to print out menus
     * @param menu String Array to be printed
     */
    private static void displayMenu(String[] menu){
        for (int i=0; i<menu.length;i++){
            System.out.println("("+ (i+1) + ") "+ menu[i]);
        }
    }

    /**
     * Helper method to receive the input of user
     * @param prompt String, prompt for the user for what they need to enter
     * @return
     */
    private String getField(String prompt) {
		System.out.print(prompt + ": ");
		return scanner.nextLine();
	}

    /**
     * Helper method to create a line in the console
     */
    private static void createline(){
        System.out.println("----------------------------");
    }

    /**
     * Helper method to display something in the console
     * @param stuff String, thing to be displayed to the console
     */
    private static void display(String stuff){
        System.out.println(stuff);
    }

    /**
     * Helper method to display something in the console with no new line
     * @param stuff String, thing to be displayed to console
     */
    private static void displaynl(String stuff){
        System.out.print(stuff);
    }

    /**
     * Helper method to take in a User's int input
     * @param UserInput int, Maximum ammount that a user should input
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