package Classes;

import java.util.ArrayList;
import java.util.UUID; 

/**
 * Creates a StudentList class, which will hold a single instance of the List of Students
 */
public class StudentList {
    //Attributes
    private static StudentList studentList;
    private ArrayList<Student> students;
    
    /**
     * StudentList Constructor, gets the student list from json and adds it to the class' ArrayList
     */
    private StudentList() {
        students = DataLoader.getStudentList();
    }

    /**
     * getInstance method that creates a singular instance of the StudentList class
     * @return StudentList, the instance of the StudentList
     */
    public static StudentList getInstance() {
        if (studentList == null) {
            studentList = new StudentList();
        }
        return studentList;
    }

    /**
     * accessor method that finds a student from the StudentList, finding it by its Unique ID
     * @param id UUID, Unique ID for the student to be found and returned
     * @return Student, Student found by its Unique ID
     */
    public Student getStudent(UUID id) {
        for (Student student : students) {
            if (student.getID().equals(id)) return student;
        }
        return null;
    }

    /**
     * accessor method for the list of students
     * @return ArrayList<Student>, the list of students
     */
    public ArrayList<Student> getStudents() {
        return students;
    }

    /**
     * accessor method that finds a student from the StudentList, finding it by their email
     * @param email String, Email to be used to find the student
     * @return Student, Student found by their email
     */
    public Student getStudent(String email) {
        for(Student student : students) {
            if(student.getEmail().equals(email)) {
                return student;
            }
        }
        return null;
    }

    /**
     * haveStudent method which goes through the list of students to find a student with an email and password matching the parameters
     * @param email String, Email of the student account being searched for
     * @param password String, Password of the student account being searched for
     * @return boolean, True if student is found, false if not
     */
    public boolean haveStudent(String email, String password){
        for(Student student: students){
            if(student.getEmail().equals(email) && student.getPassword().equals(password))
                return true;
        }
        return false;
    }

    /**
     * professorhaveStudent method which goes through the list of students and searches for a student by their first and last name
     * @param firstName String, First name of the student being searched for
     * @param lastName String, Last name of the student being searched for
     * @return boolean, True if student is found, false if not
     */
    public boolean professorhaveStudent(String firstName, String lastName){
        for(Student student: students){
            if(student.getFirstName().equals(firstName) && student.getLastName().equals(lastName)){
                return true;
            }
        }
        return false;
    }

    /**
     * accessor method for the student
     * @param firstName String, First name of the student being searched for
     * @param lastName String, Last name of the student being searched for
     * @return Student, Student from the student list
     */
    public Student getStudent(String firstName, String lastName){
        for(Student student: students){
            if(student.getFirstName().equalsIgnoreCase(firstName) && student.getLastName().equalsIgnoreCase(lastName)){
                return student;
            }
        }
        return null;
    }

    /**
     * haveStudent method which searches the list of students, searching for a student with an email matching the parameter
     * @param email String, Email of the student being searched for
     * @return boolean, True if student found, false if not
     */
    private boolean haveStudent(String email){
        for(Student student: students){
            if(student.getEmail().equals(email))
                return true;
        }
        return false;
    }

    /**
     * addStudent method which creates and adds a student to the student list
     * @param email String, The email for the new student being created
     * @param password String, The password for the student being created
     * @param firstName String, First name of the student
     * @param lastName String, Last name of the student
     * @param phoneNumber String, Student's phone number
     * @return boolean, True if student account is created, false if student account already exists
     */
    public boolean addStudent(String email, String password, String firstName, String lastName, String phoneNumber){
        if(haveStudent(email))return false;
		students.add(new Student(firstName, lastName, email, phoneNumber, password));
        return true;
    }

    /**
     * saveStudents method to save the students to the studentlist json file
     */
    public static void saveStudents(){
        DataWriter.saveStudents();
    }
}