package Classes;
import java.util.Scanner;
import java.util.ArrayList;
public class AdminUI {
    /**
     * Creates the AdminUI class, contains all of the information required for the AdminUI
     */
    private ArrayList<Review> professorReviews;
    private ArrayList<JobListing> jobListing;
    private String[] businessMenu = {"View this Business' Job listings", "Remove this Account", "Go back"};
    private String[] editInformationMenu = {"Email", "Password", "Go back"};
    private String[] adminMenu = {"Search for Student", "Search for Business", "Search for Professor", "Verify a Business/Professor", "Add new Admin", "Edit Profile Information", "Log Out"};
    private Scanner scanner;
    private Admin currentAdmin;
    private ArrayList<Student> ProfessorReviews;
    private ArrayList<Student> studentLists;
    /**
     * Constructor for AdminUI class
     */
    public AdminUI(){
        scanner = new Scanner(System.in);
        professorReviews = new ArrayList<Review>();
        //professorList = new ArrayList<Professor>();
        jobListing = new ArrayList<JobListing>();
        ProfessorReviews = new ArrayList<Student>();
    }
    /**
     * Logs in the admin only if the admins email and password are correct. Once the admin is logged in they are prompted with the adminUI menu.
     */
    public void loginAdmin(){
        createline();
        System.out.println("\n");
        String email = getField("Email");
        String password = getField("Password");

        if(SystemClass.getInstance().login(email,password, "Admin")){
            SystemClass.getInstance().setCurrentAdmin(email);
            currentAdmin = SystemClass.getCurrentAdmin();
            runAdmin();
        }
    }
/**
 * Main menu method for the AdminUI, once logged in it asks an admin what they wish to do.
 */
    private void runAdmin(){
        while(true){
            createline();
            display("Welcome admin, what would you like to do?");
            displayMenu(adminMenu);
            displaynl("Enter a Number: ");
            int userCommand = getUserCommand(adminMenu.length);
			if(userCommand == -1) {
				System.out.println("Not a valid command");
				continue;
			}
			//admin logged out if last option is picked
			if(userCommand == adminMenu.length -1) {
				SystemClass.logout();
				break;
			}
		
			switch(userCommand) {
				case(0):
                    SearchForStudent();
					break;
				case(1):
					SearchForBusiness();
					break;
                case(2):
					SearchForProfessor();
					break;
                case(3):
                    VerifyBusinessOrProfessor();
                    break;
                case(4):
                    AddNewAdmin();
                    break;
                case(5):
                    editProfileInformation();
                    break;
			}
        }
    }
    /**
     * Method that runs the UI menu to verify a business or a professor depending on what the user inputs. 
     */
    private void VerifyBusinessOrProfessor(){
        while(true){
            display("Please select if you want to Verify a Business (1) or a Professor (2) or select (0) to Go Back");
            int userCommand2 = getUserCommand(2);
            if(userCommand2 == -2) {
                break; 
            }
            else if (userCommand2 == -1){
                display("Invalid number, please try again");
            } else {
                    switch(userCommand2){
                        case(0):
                            VerifyBusiness();
                            break;
                        case(1):
                            VerifyProfessor();
                            break;
                    }  
            }
        }
    }
/**
 * Method that verifys the business by taking in the business email. 
 */
    private void VerifyBusiness(){
        while(true){
            display("Enter the Business's Email");
            String email = getField("Email");
            
            Business business = BusinessList.getInstance().getBusiness(email);
            if(business != null){
                createline();
                display("");
                display(business.toString());
                display("");
                display("Verify Business? Press (1) to verify, press (0) to go back");
                String UserInput = scanner.nextLine();
                if(UserInput.equals("0")){
                    break;
                } else if(Integer.parseInt(UserInput) < 0 || Integer.parseInt(UserInput) > 1){
                    display("Invalid Input");
                    break;
                } else{
                    business.setVerification(true);
                    display("Business is Verified");
                    break;
                }
            } else {
                display("Business not Found");
                break;
            }
        }
    }
    /**
     * Method that verifys a professor by taking in the professors email.
     */
    private void VerifyProfessor(){
        while(true){
            display("Enter the Professor's Email");
            String email = getField("Email");
            
            Professor professor = ProfessorList.getInstance().getProfessor(email);
            if(professor != null){
                createline();
                display("");
                display(professor.toString());
                display("");
                display("Verify Professor? Press (1) to verify, press (0) to go back");
                String UserInput = scanner.nextLine();
                if(UserInput.equals("0")){
                    break;
                } else if(Integer.parseInt(UserInput) < 0 || Integer.parseInt(UserInput) > 1){
                    display("Invalid Input");
                    break;
                } else{
                    professor.setVerification(true);
                    display("Professor is Verified");
                    break;
                }
            } else {
                display("Professor not Found");
                break;
            }
        }
    }
    /**
     * Method that runs the UI for adding a new admin
     */
    private void AddNewAdmin(){
        createline();
        display("Enter the email and a password: ");

        String email = getField("Email");
        String password = getField("Password");

        if(SystemClass.getInstance().createAdminAccount(email, password)){
            display("Account Successfully Created");
        } else {
            display("This Admin Account already exists");
        }
    }
    /**
     * Method to edit a profiles information, either the password or email. 
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
                    editEmail(); 
                    break;
                case(1):
                    editPassword(); 
                    break;
            }
        }
    }
    /**
     * Method to edit the admins email, email must be entered identically twice.
     */
    private void editEmail(){
        display("");
        createline(); 
        display("Please enter a new Email");
        display("");
        String email = getField("Email");
        String temp = getField("Please Re-enter Email");
        if(email.equals(temp)){
            currentAdmin.setEmail(email);
            display("Email Changed");
        } else {
            display("Emails did not match");
        }
    }
    /**
     * Method to change the admins password, first you must enter the old password and then enter the new password identically twice. 
     */
    private void editPassword(){
        display("");
        createline(); 
        display("PLease enter the previous password");
        String prevpassword = getField("Old Password");
        if(prevpassword.equals(currentAdmin.getPassword())){
            display("Please enter a new Password");
            display("");
            String password = getField("Password");
            String temp = getField("Please Re-enter Password");
            if(password.equals(temp)){
                currentAdmin.setPassword(password);
                display("Password changed");
            } else {
                display("Password did not match");
            }
        } else{
            display("Incorrect password");
        }
    }
    /**
     * Method that prints out menus, acts as a helper
     * @param menu Array of type string that is printed. 
     */
    private static void displayMenu(String[] menu){
        for (int i=0; i<menu.length;i++)  {
            System.out.println("("+ (i+1) + ") "+ menu[i]);
        }
    }
    /**
     * Method to search for student using the first name and last name.
     * Displays the full name and email if student is found.
     */
    public void SearchForStudent(){
        while(true){
            display("Please Enter (1) to Search for Student or press (0) to go back: ");
            int userCommand = getUserCommand(1);
            if(userCommand == -2) {
                break; 
            }
            else if (userCommand == -1){
                display("Invalid number, please try again");
            }else{
                String studentFirstName = getField("First Name");
                String studentLastName = getField("Last Name" );
                Student student = StudentList.getInstance().getStudent(studentFirstName, studentLastName);
                if(student != null){
                    display("Student Found");
                    createline();
                    display("Name: "+student.getFirstName()+ " " +student.getLastName());
                    display("Email: " +student.getEmail());
                    display("Enter a (1) to view this student's professor reviews,");
                    display("a (2) to remove this student's account");
                    display("or a (0) to go back");
                    int userCommand2 = getUserCommand(2);
                    if(userCommand2 == -2){
                        break;
                    }
                    else if(userCommand2 == -1){
                        display("Not valid command");
                        continue;
                    }else {
                    switch(userCommand2){
                        case(0):
                            ViewProfessorReviews(student);
                            break;
                        case(1):
                            removeStudentAccount(student);
                            break;
                    }
                }
                }
                else{
                    display("Student Not Found");
                }
            }
        }
    }
    /**
     * Method to set the professor review to the array of reviews
     * @param student String value, student to leave the review.
     */
    private void setProfessorReviewArray(Student student){
        professorReviews.clear();
        professorReviews = student.getProfessorReviews();
    }
    /**
     * Method to view the professor reviews for a given student
     * @param student String value, student
     */
    private void ViewProfessorReviews(Student student){
        while(true){
            setProfessorReviewArray(student);
            display(student.getFirstName()+ " " +student.getLastName()+ "'s reviews");
            for (int i = 0; i < professorReviews.size(); i++){
                display("("+ (i+1) + ") "+ professorReviews.get(i).toString());
            }
                display("Enter a number of a review to remove,");
                display("or a (0) to go back.");
                if(professorReviews.size() == 0){
                    display("There are no Reviews for this student");
                    break;
                }else{
                    int userCommand = getUserCommand(professorReviews.size());
                    if(userCommand == -2){
                        break;
                    }
                    else if (userCommand == -1){
                        display("Not valid command.");
                        continue;
                    } else {
                        switch(userCommand){
                            case(0):
                                RemoveStudentReview(student, userCommand);
                                break;
                        }
                        break;  
                    }
                }
        }
    }
    /**
     * Method to remove a students review from the list of reviews
     * @param student String, student
     * @param num int, number of review to be deleted.
     */
    private void RemoveStudentReview(Student student, int num){
        SystemClass.getInstance().removeReview(professorReviews.get(num));
        student.removeProfessorReview(professorReviews.get(num));
        professorReviews.remove(num);
        display("Review Deleted");
    }
    /**
     * Method to remove a students account from a list of accounts.
     * @param student String, student
     */
    private void removeStudentAccount(Student student){
        DeletedUserList.getInstance().removeAccount(student);
        SystemClass.getInstance().removeStudent(student);
        display("Student Account Deleted");
    }
    /**
     * Method to search for a business specified by the email of said business. 
     */
    public void SearchForBusiness(){
        while(true){
            display("Please enter a (1) to search for business or (0) to go back: ");
            int userCommand = getUserCommand(1);
            
            if(userCommand == -2) {
                break; 
            }
            else if (userCommand == -1){
                display("Invalid number, please try again");
            }else{
                String businessEmail = getField("Business Email");
                Business business = BusinessList.getInstance().getBusiness(businessEmail);
                if(business != null){
                    display("Business found");
                    createline();
                    display("Business Profile");
                    display("Name: " + business.getBusinessName());
                    display("Email: " + business.getEmail());
                    display("Address: " + business.getAddress());                        
                    display("");
                    display("What would you like to do?");
                    setJobListingArray(business);
                    displayMenu(businessMenu); 
                    int userCommand2 = getUserCommand(businessMenu.length);
                    if(userCommand2 == -2){
                        break;
                    }
                    else if(userCommand2 == -1){
                        display("Not valid command");
                        continue;
                    }else {
                    switch(userCommand2){
                        case(0):
                            viewJobListings(business);
                            break;
                        case(1):
                            removeBusinessAccount(business);
                            break;
                        }
                    } 
                }else {
                    display("Business Not Found");
                }
            }
        }
    }

    
    private void setJobListingArray(Business business){
        jobListing = business.getJobListings();
    }
    /**
     * Method to view a passed in business' job listings, 
     * @param business String, business passed in
     */
    private void viewJobListings(Business business){
        while(true){
            
            display(business.getBusinessName()+ "'s Job Listings");
            for (int i = 0; i < jobListing.size(); i++){
                display("("+ (i+1) + ") "+ jobListing.get(i).jobListingBasicInfo());
                
            }

            display("");
            display("JobListing size = " + jobListing.size());
                display("Enter a number of a Job Listing to remove,");
                display("or a (0) to go back.");
                if(jobListing.size() == 0){
                    display("There are no Job Listings for this Business");
                    break;
                }else{
                    int userCommand = getUserCommand(jobListing.size());
                    if(userCommand == -2){
                        break;
                    }
                    else if (userCommand == -1){
                        display("Not valid command.");
                        continue;
                    } else {
                        JobListing job = jobListing.get(userCommand);
                        display(jobListing.get(userCommand).toString());
                        display("");
                        display("Do you want to remove this JobListing? \n Press (1) to delete, Press (0) to go back");
                        display("userCommand: "+ userCommand);
                        int userCommand3 = getUserCommand(1); //Fix
                        switch(userCommand3){
                            case(0):
                                RemoveJobListingthingy(business, job);
                                break;
                        }
                        break;  
                    }
                }
        }
    }
    /**
     * Method that removes a business from the list of business'
     * @param business String, business
     */
    private void removeBusinessAccount(Business business){
        SystemClass.getInstance().removeBusiness(business);
        display("Business Account Deleted");
    }
    /**
     * Method to allow the admin to remove a specific job listing. 
     * @param business String, business
     * @param job String, job
     */
    private void RemoveJobListingthingy(Business business, JobListing job){
        SystemClass.getInstance().removeJobListing(job);
        business.removeJobListing(job);
    }
    /**
     * Method to search for a professor using the professor email.
     */
    private void SearchForProfessor(){
        while(true){
            display("Please Enter (1) to Search for Professor or press (0) to go back: ");
            int userCommand = getUserCommand(1);
            if(userCommand == -2) {
                break; 
            }
            else if (userCommand == -1){
                display("Invalid number, please try again");
            }else{
                String professorEmail = getField("Professor Email");
                Professor professor = ProfessorList.getInstance().getProfessor(professorEmail);
                if(professor != null){
                    display("Professor Found");
                    createline();
                    display(professor.toString());
                    display("Enter a (1) to view this professor reviews,");
                    display("a (2) to remove this professor's account");
                    display("or a (0) to go back");
                    int userCommand2 = getUserCommand(2);
                    if(userCommand2 == -2){
                        break;
                    }
                    else if(userCommand2 == -1){
                        display("Not valid command");
                        continue;
                    }else {
                    switch(userCommand2){
                        case(0):
                            ViewProfessorRev(professor);
                            break;
                        case(1):
                            removeProfessorAccount(professor);
                            break;
                    }
                }
                }
                else{
                    display("Professor Not Found");
                }
            }
        }
    }
    /**
     * Method that sets the professors arraylist of reviews
     * @param professor String, professor
     */
    private void setProfessorRevArray(Professor professor){
        studentLists = StudentList.getInstance().getStudents();
        ProfessorReviews.clear();
        int index = 0;
        int counter =0;
        
        for(Student student: studentLists){
            counter = student.hasProfessorReview(professor.getFirstName(),professor.getLastName(), index);
            if(counter != -1){
                if(student.getSpecProfessorReviews(professor.getFirstName(),professor.getLastName()) != null){
                    int counter2 = 0;
                    for(int i =0; i<ProfessorReviews.size();i++){
                        if(student.getSpecProfessorReviews(professor.getFirstName(), professor.getLastName()).getID().compareTo(ProfessorReviews.get(i).getID()) == 0) //Checks to see if this is already in the Professor Review ArrayList
                            counter2++;
                    }
                    if(counter2 == 0){
                        ProfessorReviews.add(student);

                    }
                }
            }
        }
    }
    /**
     * Method to view a chosen professor review 
     * @param professor String, professor
     */
    private void ViewProfessorRev(Professor professor){
        while(true){
            setProfessorRevArray(professor);
            display("Select a Review, Press (0) to go back");
            for(int i =0; i < ProfessorReviews.size();i++){
                display("(" + (i+1)+ ") " + ProfessorReviews.get(i).getFirstName() + " " + ProfessorReviews.get(i).getLastName());
            }
                
                if(ProfessorReviews.size() == 0){
                    display("There are no Reviews from this Professor");
                    break;
                }else{
                    display("Enter a number of a review to remove,");
                    display("or a (0) to go back.");

                    int userCommand = getUserCommand(ProfessorReviews.size());
                    if(userCommand == -2){
                        break;
                    }
                    else if (userCommand == -1){
                        display("Not valid command.");
                        continue;
                    } else {
                        switch(userCommand){
                            case(0):
                                RemoveProfessorReview(professor, userCommand);
                                break;
                        }
                        break;  
                    }
                }
        }
    }
    /**
     * Method to remove a professor's review
     * @param professor String, professor
     * @param num int, number of review to be removed.
     */
    private void RemoveProfessorReview(Professor professor, int num){
        ProfessorReviews.get(num).removeProfessorReview(ProfessorReviews.get(num).getSpecProfessorReviews(professor.getFirstName(), professor.getLastName()));
        SystemClass.getInstance().removeReview(ProfessorReviews.get(num).getSpecProfessorReviews(professor.getFirstName(), professor.getLastName()));
        ProfessorReviews.remove(num);
        display("Review Deleted");
    }
    /**
     * Method to remove a professors account
     * @param professor String, professor
     */
    private void removeProfessorAccount(Professor professor){
        DeletedUserList.getInstance().removeAccount(professor);
        SystemClass.getInstance().removeProfessor(professor);
        display("Professor Account Deleted");
    }
    
    /**
     * Method that acts as a helper to get the input of the user.
     * @param prompt String, prompts the user to enter the desired information
     * @return
     */
    private String getField(String prompt) {
		System.out.print(prompt + ": ");
		return scanner.nextLine();
	}
    /**
     * Method that acts as a helper to create a line.
     */
    private static void createline(){
        System.out.println("----------------------------");
    }
    /**
     * Method that acts as a helper to print something to console
     * @param stuff String, the string to be printed to console.
     */
    private static void display(String stuff){
        System.out.println(stuff);
    }
    /**
     * Method that acts as a helper to print something to the console without a new line.
     * @param stuff String, the string to be printed to console.
     */
    private static void displaynl(String stuff){
        System.out.print(stuff);
    }
    /**
     * Method that acts as a helper to take in a user's input.
     * @param UserInput Int, the largest ammount a user should input
     * @return int, relates to the user's input. 
     */
    private int getUserCommand(int UserInput){
        String input = scanner.nextLine();
        System.out.println("----------------------------");
		int command = Integer.parseInt(input) - 1;
		
		if(command >= 0 && command <= UserInput -1) return command;
        if(command == -1) return -2;
        return -1;
    }
}
