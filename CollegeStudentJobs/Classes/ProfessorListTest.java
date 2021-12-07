package Classes;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

public class ProfessorListTest {
    
    @BeforeEach
    public void setup(){
        ProfessorList professorList = ProfessorList.getInstance();
        professorList.getProfessors().clear();
    }

    @Test
    public void testHaveProfessorWithEmail(){
        ProfessorList professorList = ProfessorList.getInstance();
        professorList.getProfessors().clear();
        professorList.getProfessors().add(new Professor("firstName", "lastName", "email", "password"));
        professorList.getProfessors().add(new Professor("firstName1", "lastName1", "email1", "password1"));
        boolean inList = professorList.haveProfessor("email");
        assertEquals(true, inList);
    }

    @Test
    public void testDontHaveProfessorWithEmail(){
        ProfessorList professorList = ProfessorList.getInstance();
        professorList.getProfessors().clear();
        professorList.getProfessors().add(new Professor("firstName", "lastName", "email", "password"));
        professorList.getProfessors().add(new Professor("firstName1", "lastName1", "email1", "password1"));
        boolean inList = professorList.getProfessors().get(0).getPassword().equals("literallylikeliterally") && professorList.getProfessors().get(0).getEmail().equals("email@email@email.college");

        assertEquals(false, inList);
    }

    @Test
    public void testHaveProfessorWithEmailAndPassword(){
        ProfessorList professorList = ProfessorList.getInstance();
        professorList.getProfessors().clear();
        professorList.getProfessors().add(new Professor("firstName", "lastName", "email", "password"));
        professorList.getProfessors().add(new Professor("firstName1", "lastName1", "email1", "password1"));
        boolean inList = professorList.getProfessors().get(0).getPassword().equals("password") && professorList.getProfessors().get(0).getEmail().equals("email");
        assertEquals(true, inList);
    }

    @Test
    public void testDontHaveProfessorWithEmailAndPassword(){
        ProfessorList professorList = ProfessorList.getInstance();
        professorList.getProfessors().clear();
        professorList.getProfessors().add(new Professor("firstName", "lastName", "email", "password"));
        professorList.getProfessors().add(new Professor("firstName1", "lastName1", "email1", "password1"));
        boolean inList = professorList.getProfessors().get(0).getPassword().equals("literallylikeliterally") && professorList.getProfessors().get(0).getEmail().equals("email@email@email.college");
        assertEquals(false, inList);
    }

    @Test
    public void testAddNoProfessor(){
        ProfessorList professorList = ProfessorList.getInstance();
        professorList.getProfessors().clear();
        assertEquals(0, professorList.getProfessors().size());
    }

    @Test
    public void addOneProfessor(){
        ProfessorList professorList = ProfessorList.getInstance();
        professorList.getProfessors().clear();
        professorList.getProfessors().add(new Professor("firstName", "lastName", "email", "password"));   
        assertEquals("email", professorList.getProfessors().get(0).getEmail());
    }

    @Test
    public void addMultipleProfessors(){
        ProfessorList professorList = ProfessorList.getInstance();
        professorList.getProfessors().clear();
        professorList.getProfessors().add(new Professor("firstName", "lastName", "email", "password"));
        professorList.getProfessors().add(new Professor("firstName1", "lastName1", "email1", "password1"));       
        professorList.getProfessors().add(new Professor("firstName2", "lastName2", "email2", "password2"));       
        assertEquals("email2", professorList.getProfessors().get(2).getEmail());
    }
    
}
