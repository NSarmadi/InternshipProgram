package Classes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class StudentListTest {
    
    //ArrayList<Student> StudList = List.getStudents(); 
    StudentList List = StudentList.getInstance();
    ArrayList<Student> StudList = List.getStudents();

    @BeforeEach
    public void setup() {
        StudList.clear();
    }

    @Test
    public void testAddStudent() {
        StudentList List = StudentList.getInstance();
        List.getStudents().clear();
        boolean test = List.addStudent("firstName", "lastName", "email", "phoneNumber", "password");
        assertEquals(true, test);
    }

    @Test
    public void testAddSameStudent() {
        List.addStudent("firstName", "lastName", "email", "phoneNumber", "password");
        List.addStudent("firstName", "lastName", "email", "phoneNumber", "password");
        assertEquals(1, StudList.size());
    }

    @Test
    void testAddEmptyStudent() {
        List.addStudent("", "", "", "", "");
        assertEquals("", StudList.get(0).getFirstName());
    }

    @Test
    void testAddNullStudent() {
        List.addStudent(null,null,null,null,null);
        assertEquals(null, StudList.get(0).getFirstName());
    }

    @Test
    void testHaveStudent() {
        List.addStudent("email", "password", "firstname", "lastname", "phonenumber");
        boolean test = List.haveStudent("email", "password");
        assertEquals(true, test);
    }

}
