package Classes;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

public class ProfessorTest {
    ProfessorList professorList = ProfessorList.getInstance();
    ReviewList reviewList = ReviewList.getInstance();

    @BeforeEach
    public void setup(){
        professorList.getProfessors().clear();
        reviewList.getReviews().clear();
    }

    @Test
    public void testAddReview() {
       Professor prof = new Professor("firstName", "lastName", "email", "password");
       prof.createReview("comment", 0);
       assertEquals(0, prof.getCreatedReviews().size());
    }

    @Test
    public void testAddNoReview(){
        Professor prof = new Professor("firstName", "lastName", "email", "password");
        prof.createReview("", 0);
        assertEquals(0, prof.getCreatedReviews().size());
    }
    
    @Test
    public void testAddMultipleReviews(){
        Professor prof = new Professor("firstName", "lastName", "email", "password");
        prof.createReview("comment", 0);
        prof.createReview("comment1", 0);
        prof.createReview("comment2", 0);
        assertEquals(3,prof.getCreatedReviews().size());
    }

    @Test
    public void testRemoveReview(){
        Professor prof = new Professor("firstName", "lastName", "email", "password");
        Student student = new Student("firstName", "lastName", "email", "phoneNumber", "password");
        Review review = new Review(0, "comment", "firstName", "lastName");
        prof.createReview("comment", 0);
        prof.removeReview(review, student);
        assertEquals(0, prof.getCreatedReviews().size());

    }
}
