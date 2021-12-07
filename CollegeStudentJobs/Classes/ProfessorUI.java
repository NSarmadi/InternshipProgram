package Classes;

import java.util.ArrayList;
import java.util.Scanner;
/**
 * Creates the ProfessorUI class
 */
public class ProfessorUI {
    private String professor = "Professor";
    private Scanner scanner;
    private String[] professorMenu = {"Add Student Review", "View/Remove a Student Review", "Change Profile Information","Logout"};
    private String[] editInformationMenu = {"First Name", "Last Name", "Email", "Password", "Go back"};
    private Professor currentProfessor;
    private ArrayList<Student> studentLists;
    private ArrayList<Student> ProfessorReviews;

    /**
     * ProfessorUI constructor, get the ProfessorUI class by initalizing required variables
     */
    public ProfessorUI(){
        scanner = new Scanner(System.in);
        ProfessorReviews = new ArrayList<Student>();
    }
    /**
     * Checks to see if the user inputted a valid professor account and continues if it is valid
     * Displays an error message if it is not a valid account or the Account is waiting to be verified
     */
    public void loginProfessor(){
        System.out.println("\n");
        String email = getField("Email");
        String password = getField("Password");

        if(SystemClass.getInstance().login(email,password, professor)){
            SystemClass.getInstance().setCurrentProfessor(email);
            currentProfessor = SystemClass.getCurrentProfessor();
            //Run Professor
            runProfessor();
        }
        else{
            display("No account or Account waiting to be verified");
        }
    }
    /**
     * Starts a loop to check to see if the user inputs a valid input and displays the Professors Name and the menu that has what the professor can do. 
     * It then takes in what the user wants to do and branches off accordingly.
     */
    private void runProfessor(){
        while(true){
            createline();
            display("Hello Professor " + currentProfessor.getFirstName()+ " " + currentProfessor.getLastName());
            display("What would you like to do?");
            displayMenu(professorMenu);
            displaynl("Enter a Number: ");
            int userCommand = getUserCommand(professorMenu.length);
			if(userCommand == -1) {
				System.out.println("Not a valid command");
				continue;
			}
			//if they picked the last option then log them out
			if(userCommand == professorMenu.length -1) {
				SystemClass.logout();
				break;
			}
		
			switch(userCommand) {
				case(0):
                    AddStudentReviewMain();
					break;
				case(1):
                    ViewStudentReviews();
					//RemoveStudentReview();
					break;
                case(2):
                    changeProfileInfo();
                    break;
                
			}
        }
    }
    /**
     * This is display the menu edit the profile information and takes in the user input
     */
    private void changeProfileInfo(){
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
                    editEmail(); 
                    break;
                case(3):
                    editPassword(); 
                    break;
            }
        }
    }
    /**
     * This allows the user to change their first name of their profile. This also changes their name automatically on the reviews so then they can still edit their reviews.
     */
    private void editFirstName() {
        display("");
        createline(); 
        display("Please enter a new first name");
        display("");
        String firstName = getField("First Name");
        setProfessorReviewArray();
        for(int i =0; i<ProfessorReviews.size();i++){
            if(ProfessorReviews.get(i).getSpecProfessorReviews(currentProfessor.getFirstName(), currentProfessor.getLastName()).getFirstName().equals(currentProfessor.getFirstName())){
                ProfessorReviews.get(i).getSpecProfessorReviews(currentProfessor.getFirstName(), currentProfessor.getLastName()).setFirstName(firstName);
            }
        }
        currentProfessor.setFirstName(firstName);
    }
    /**
     * This allows the user to change their last name of their profile. This also changes their name automatically on the reviews so then they can still edit their reviews.
     */
    private void editLastName() {
        display("");
        createline(); 
        display("Please enter a new Last name");
        display("");
        String lastName = getField("Last Name");
        setProfessorReviewArray();
        for(int i =0; i<ProfessorReviews.size();i++){
            if(ProfessorReviews.get(i).getSpecProfessorReviews(currentProfessor.getFirstName(), currentProfessor.getLastName()).getLastName().equals(currentProfessor.getLastName())){
                ProfessorReviews.get(i).getSpecProfessorReviews(currentProfessor.getFirstName(), currentProfessor.getLastName()).setLastName(lastName);
            }
        }
        currentProfessor.setLastName(lastName);
    }
    /**
     * This allows the user to change their email. Because their email is only on their profile it only needs to be changed on their profile.
     */
    private void editEmail(){
        display("");
        createline(); 
        display("Please enter a new Email");
        display("");
        String email = getField("Email");
        String temp = getField("Please Re-enter Email");
        if(email.equals(temp)){
            currentProfessor.setEmail(email);
            display("Email Changed");
        } else {
            display("Emails did not match");
        }
    }
    /**
     * This allows the user to change their password. As it is a password it asks for the old password before you can change the passcode. This is an added layer
     * of security. Because their password is only on their profile it only needs to be changed on their profile.
     */
    private void editPassword(){
        display("");
        createline(); 
        display("PLease enter the previous password");
        String prevpassword = getField("Old Password");
        if(prevpassword.equals(currentProfessor.getPassword())){
            display("Please enter a new Password");
            display("");
            String password = getField("Password");
            String temp = getField("Please Re-enter Password");
            if(password.equals(temp)){
                currentProfessor.setPassword(password);
                display("Password changed");
            } else {
                display("Password did not match");
            }
        } else{
            display("Incorrect password");
        }
    }
    /**
     * This sets up the Professor Review Array in this class. 
     * In essence, this creates a deep copy of the Arraylist and stores this deep copy in this class.
     * The Professor Review Array holds all the specific professors reviews.
     */
    private void setProfessorReviewArray(){
        studentLists = StudentList.getInstance().getStudents();
        ProfessorReviews.clear();
        int index = 0;
        int counter =0;
        
        for(Student student: studentLists){
            counter = student.hasProfessorReview(currentProfessor.getFirstName(),currentProfessor.getLastName(), index);
            if(counter != -1){
                if(student.getSpecProfessorReviews(currentProfessor.getFirstName(),currentProfessor.getLastName()) != null){
                    int counter2 = 0;
                    for(int i =0; i<ProfessorReviews.size();i++){
                        if(student.getSpecProfessorReviews(currentProfessor.getFirstName(), currentProfessor.getLastName()).getID().compareTo(ProfessorReviews.get(i).getID()) == 0) //Checks to see if this is already in the Professor Review ArrayList
                            counter2++;
                    }
                    if(counter2 == 0)
                        ProfessorReviews.add(student);
                    
                }
            }
        }
    }
    /**
     * This allows the professor to see the reviews they have made.
     */
    private void ViewStudentReviews(){
        setProfessorReviewArray();
        while(true){
            display("Select a Student Review, Press (0) to go back");
            for(int i =0; i < ProfessorReviews.size();i++){
                display("(" + (i+1)+ ") " + ProfessorReviews.get(i).getFirstName() + " " + ProfessorReviews.get(i).getLastName());
            }
            displaynl("Enter a Number: ");
            int userCommand2 = getUserCommand(ProfessorReviews.size());
            if(userCommand2 == -2) {
                break; 
            }
            else if (userCommand2 == -1){
                display("Invalid number, please try again");
            } else {
                display(ProfessorReviews.get(userCommand2).getFirstName() + " " + ProfessorReviews.get(userCommand2).getLastName());
                display(ProfessorReviews.get(userCommand2).getSpecProfessorReviews(currentProfessor.getFirstName(), currentProfessor.getLastName()).ToString());
                display("");
                display("Do you want to remove this review? \n Press (1) to delete, Press (0) to go back");
                int userCommand3 = getUserCommand(2); 
                switch(userCommand3){
                    case(0):
                        RemoveStudentReview(userCommand2);
                        break;
                }
            }
        }

    }
    
    /**
     * This deletes the review that the professor made on a student. Deletes the review from 3 different classes.
     * @param num int, passes the index to delete.
     */
    private void RemoveStudentReview(int num){
        ProfessorReviews.get(num).removeProfessorReview(ProfessorReviews.get(num).getSpecProfessorReviews(currentProfessor.getFirstName(), currentProfessor.getLastName()));
        SystemClass.getInstance().removeReview(ProfessorReviews.get(num).getSpecProfessorReviews(currentProfessor.getFirstName(), currentProfessor.getLastName()));
        ProfessorReviews.remove(num);
        display("Review Deleted");
    }
    /**
     * This is the AddStudentReviewMain(). Its function is to run the boolean function and if it returns true then it will display a line.
     */
    private void AddStudentReviewMain(){
        if(AddStudentReview()){
            display("Student Review Added");
            createline();
        }else{
            createline();
        }
    }
    /**
     * This is the way that the professor can add a new review to a student. It first asks for what the students name is
     * and then it validates if that student exists in this application. Once verified, the professor can add a review.
     * @return true or false depending on if the review was added.
     */
    private boolean AddStudentReview(){
        setProfessorReviewArray();
        while(true){
            createline();
            display("Please Enter (1) to Search for a Student or press (0) to go back: ");
            String UserInput = scanner.nextLine();

            if(UserInput.equals("0")){
                break;
            } else if(Integer.parseInt(UserInput) < 0 || Integer.parseInt(UserInput) > 1){
                display("Invalid Input");
                break;
            }
            
            String studentFirstName = getField("Enter Student's First Name");
            String studentLastName = getField("Enter Student's Last Name");
            Student student = StudentList.getInstance().getStudent(studentFirstName, studentLastName);
            int counter =0;
            for(int i=0; i<ProfessorReviews.size();i++){
                if(ProfessorReviews.get(i).getFirstName().equals(studentFirstName) && ProfessorReviews.get(i).getLastName().equals(studentLastName))
                    counter++;
            }
            if(counter !=0){
                display("A Review for this Student has already been added, If you want to edit the Review please remove it and add it again");
                break;
            } 
            if(student != null){

                String professorReview = getField("Enter review");
                double rating = Double.parseDouble(getField("Please give a rating"));
                Review review = new Review(rating, professorReview, currentProfessor.getFirstName(), currentProfessor.getLastName());
                SystemClass.getInstance().addReview(review);
                student.addProfessorReview(review);
                return true;
            } else{
                display("Student not found");
                return false;
            }
        }
        return false;
    }
    /**
     * This function displays any menu. This was made because there are a lot of menus that we use, so this made it easier to do.
     * @param menu String[], Any menu passed will be displayed
     */
    private static void displayMenu(String[] menu){
        for (int i=0; i<menu.length;i++){
            System.out.println("("+ (i+1) + ") "+ menu[i]);
        }
    }
    /**
     * This displays the prompt and gets a userInput.
     * @param prompt String, passes the prompt
     * @return a userInput
     */
    private String getField(String prompt) {
		System.out.print(prompt + ": ");
		return scanner.nextLine();
	}
    /**
     * This function creates a line that is used to decorate the console to allow for readablity
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
