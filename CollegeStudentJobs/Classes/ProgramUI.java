package Classes;

import java.util.Scanner;
import java.util.ArrayList;
/**
 * Creates the ProgramUI class, which holds the main UI for the entire system
 */
public class ProgramUI{
    private static final String WELCOME_MESSAGE = "Welcome to The New Way to a New Job";
    private String[] mainMenuOptions = {"Log in", "Create Account", "View Job Listings", "Exit"};
    private String[] mainMenuOptions2 = {"Log in", "Create Account", "View Job Listings", "Log out"};
    private String[] creatingAccountOption = {"Student", "Business", "Professor", "Return to log in screen"};
    private String[] loginOption = {"Log in as a Student", "Log in as a Business", "Log in as a Professor", "Log in as a Admin", "Back to Main Menu"};
    private String[] sortMenu = {"Alphabetical (A-Z)", "Wage (Highest to lowest)","Date Added (Most to least recent)", "Search By Keyword", "Go Back"};
    private String[] jobListingMenu = {"View Reviews", "Go back"};
    private Scanner scanner;
    private SystemClass systemClass;
    private StudentUI studentUI;
    private BusinessUI businessUI;
    private ProfessorUI professorUI;
    private AdminUI adminUI; 
    private ArrayList<JobListing> joblisting;

    /**
     * ProgramUI constructor, initializes all of the account UIs and the Scanner object
     */
    ProgramUI(){
        scanner = new Scanner(System.in);
        systemClass = SystemClass.getInstance();
        studentUI = new StudentUI();
        businessUI = new BusinessUI();
        professorUI = new ProfessorUI();
        adminUI = new AdminUI(); 
    }

    /**
     * Main run command for the program, gives the main menu options to the user
     */
    public void run(){        
        System.out.println(WELCOME_MESSAGE);
        while(true) {
			displayMainMenu();
			int userCommand = getUserCommand(mainMenuOptions.length);
			if(userCommand == -1) {
				System.out.println("Not a valid command");
				continue;
			}
			if(userCommand == mainMenuOptions2.length -1) {
				SystemClass.logout();
                System.exit(0);
				break;
			}
			switch(userCommand) {
				case(0): {
                    login();
					break;
                }
				case(1): {
					createAccount();
					break;
                }
				case(2): {
					viewJobListings();
					break;
                }
			}
		}
		System.out.println("Good bye, and have a nice day");
	}
    
    /**
     * Displays the Main menu options to the User
     */
    private void displayMainMenu(){
        System.out.println("----------------------------");
        System.out.println("What would you like to do: ");
        for (int i=0; i<mainMenuOptions.length;i++){
            System.out.println("("+ (i+1) + ") "+ mainMenuOptions[i]);
        }
        System.out.println("\n");
    }

    /**
     * Helper method for taking in an int input from the User
     * @param UserInput Maximum value the user can input
     * @return int, corresponding value to user's input, if -1 input is invalid, if -2, user input is 0, all others correspond to the range from 0 to the maximum-1
     */
    private int getUserCommand(int UserInput){
        System.out.print("Enter a number: ");
        String input = scanner.nextLine();
        System.out.println("----------------------------");
		int command = Integer.parseInt(input) - 1;
		
		if(command >= 0 && command <= UserInput -1) return command;
        if(command == -1) return -2;
        return -1;
    }

    /**
     * Login function for the system, gives the option of logging in as one of the 4 account types or returning, logging into an account calls that account type's UI 
     */
    private void login(){
        while(true) {
            displayLogInMenu();
            int userCommand = getUserCommand(loginOption.length);
			if(userCommand == -1) {
				System.out.println("Not a valid command");
				continue;
			}
			if(userCommand == loginOption.length-1) {
                break; 
			}
			switch(userCommand) {
				case(0): {
                    studentUI.loginStudent();
					break;
                }
				case(1): {
					businessUI.loginBusiness();
					break;
                }
				case(2): {
					professorUI.loginProfessor();
					break;
                }
                case(3): {
					adminUI.loginAdmin();
					break;
                }
                case(4): {
                    break;
                }
			}
            break; 
        } 
    }
    
    /**
     * Displays the Login options to the user
     */
    private void displayLogInMenu(){
        createline();
        System.out.println("\n");
        for (int i=0; i<loginOption.length;i++){
            System.out.println("("+(i+1)+ ") "+ loginOption[i]);
        }
        System.out.println("\n");
    }

    /**
     * Main create account function, gives the user the choice between making a student, business, or professor account
     */
    private void createAccount(){
            while(true) {
                displayCreateAccountMenu();
                int userCommand = getUserCommand(creatingAccountOption.length);
                if(userCommand == -1) {
                    System.out.println("Not a valid command");
                    continue;
                }
                if(userCommand == mainMenuOptions2.length -1) {
                    break;
                }
                switch(userCommand) {
                    case(0): {
                        createStudent();
                        break;
                    }
                    case(1): {
                        createBusiness();
                        break;
                    }
                    case(2): {
                        createProfessor();
                        break;
                    }
                }
            }
    }

    /**
     * Displays the menu options for creating an account
     */
    private void displayCreateAccountMenu(){
        createline();
        System.out.println("Create a new account: ");
        System.out.println("What kind of account are you creating?");
        for (int i=0; i<creatingAccountOption.length;i++){
            System.out.println("("+(i+1)+ ") "+ creatingAccountOption[i]);
        }
        System.out.println("\n");
    }

    /**
     * UI for Creating a new student account
     */
    private void createStudent(){
        createline();
        System.out.println("Enter a .edu Student email and a password");
        String email = getField("email");
        char[] emailsplit = email.toCharArray();
        String test = "";
        if(emailsplit.length > 5){
            for(int i = emailsplit.length - 4; i < emailsplit.length; i++){
                test += emailsplit[i];
            }
            if(test.equals(".edu")){
                if(checkForDeletedUser(email)) {
                    display("This email has a deleted account associated with it and cannot be reused.");
                }
                else {
                    String password = getField("password");
                    String firstName = getField("First Name");
                    String lastName = getField("Last Name");
                    String phoneNumber = getField("Phone Number");
                    createline();
                    if(systemClass.createStudentAccount(email, password, firstName, lastName, phoneNumber)){
                         System.out.println("Account Successfully Created");
                    } else {
                        System.out.println("This Student Account already exists");
                    }
                }   
            } else{
                display("Invalid Email");
                display("Please enter a .edu email.");
            }
        } else{
            display("Enter Valid Email");
        }
    }

    /**
     * UI function for creating a new Business Account
     */
    private void createBusiness(){
        createline();
        System.out.println("Enter a business email and a password");
        String email = getField("Email");
        if(checkForDeletedUser(email)) {
            display("This email has a deleted account and cannot be reused.");
        }
        else {
            String password = getField("Password");
            System.out.println("\n");
            System.out.println("Please enter your Company details");
            String companyName = getField("Company Name");
            String companyAddress = getField("Company Address");
            if(companyName.equals("") || companyAddress.equals(""))
                display("Please enter a company name/Company Address");
            else{
                if(systemClass.createBusinessAccount(email, password, companyName, companyAddress)){
                    System.out.println("Account Successfully Created");
                    System.out.println("Awaiting verification by an admin. \nPlease Check back later to create Job Listing.");
                } else {
                    System.out.println("This Business Account already exists");
                }
            }
        }
    }

    /**
     * UI function for creating a new Professor account
     */
    private void createProfessor(){
        createline();
        System.out.println("Enter your school email and a password: ");
        String email = getField("Email");
        if(checkForDeletedUser(email)) {
            display("This email has a deleted account and cannot be reused.");
        }
        else {
            String password = getField("Password");
            String firstName = getField("First Name");
            String lastName = getField("Last Name");
            if(systemClass.createProfessorAccount(email, password, firstName, lastName)){
                System.out.println("Account Successfully Created");
                System.out.println("Awaiting verification by an admin. \nPlease Check back later to add Student Reviews.");
            } else {
                System.out.println("This Professor Account already exists");
            }
        }
    }

    /**
     * UI function for printing all of the JobListings without logging in, gives options for how to sort the job listings
     */
    private void viewJobListings(){
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
     * Helper method that creates a line in the console
     */
    private void createline(){
        System.out.println("----------------------------");
    }

    /**
     * Helper method to take in the String input from a user
     * @param prompt String, prompts the user to input something
     * @return String, User input
     */
    private String getField(String prompt) {
		System.out.print(prompt + ": ");
		return scanner.nextLine();
	}

    /**
     * Helper method to display something to the console
     * @param stuff String, thing to be displayed
     */
    private static void display(String stuff){
         System.out.println(stuff);
    }

    /**
     * Helper method to display something to the console without a new line
     * @param stuff String, thing to be displayed
     */
    private static void displaynl(String stuff){
        System.out.print(stuff);
    }

    /**
     * UI method to sort jobs and then print them to the console
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
     * Helper method to check if an email is associated with a deleted account
     * @param email String, email to be checked for
     * @return boolean, true if the email is found to have a deleted user, false if not
     */
    private boolean checkForDeletedUser(String email) {
        for(User user : SystemClass.getInstance().getDeletedUserList()) {
            if(user.getEmail().equalsIgnoreCase(email))
                return true; 
        }
        return false;
    }

    /**
     * Helper method to display String arrays 
     * @param menu String[], menu to be displayed
     */
    private static void displayMenu(String[] menu){
        for (int i=0; i<menu.length;i++){
            System.out.println("("+ (i+1) + ") "+ menu[i]);
        }
    }
    /**
     * Main function, runs the program
     * @param args String[], Takes in arguments when ran
     */
    public static void main(String[] args){
        ProgramUI Mainthingy = new ProgramUI();
        Mainthingy.run();
    }
}