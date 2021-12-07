package Classes;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StudentTest {

    @BeforeEach
	public void setup() {
		ArrayList<Review> professorReviews = new ArrayList<>();
        professorReviews.clear();
	}

    @Test
    public void testHasProfessorReview() {
        Student student = new Student("Naveen", "Chithan", "naveenchithan@email.com", "8885557536", "randopassword");
        student.addProfessorReview(new Review(5.0, "Amazing Student", "Portia", "Plante"));
        student.addProfessorReview(new Review(4.5, "Great Student", "Carter", "Marlowe"));
        student.addProfessorReview(new Review(4.0, "Good Student", "Neekon", "Sarmadi"));
        student.addProfessorReview(new Review(1.0, "Should Drop Out", "Gonzalo", "Rodas"));
        int found = student.hasProfessorReview("Carter", "Marlowe", 0);
        assertEquals(1, found);
    }

    @Test
    public void testHasProfessorReviewNotFound() {
        Student student = new Student("Naveen", "Chithan", "naveenchithan@email.com", "8885557536", "randopassword");
        student.addProfessorReview(new Review(5.0, "Amazing Student", "Portia", "Plante"));
        student.addProfessorReview(new Review(4.5, "Great Student", "Carter", "Marlowe"));
        student.addProfessorReview(new Review(4.0, "Good Student", "Neekon", "Sarmadi"));
        student.addProfessorReview(new Review(1.0, "Should Drop Out", "Gonzalo", "Rodas"));
        int found = student.hasProfessorReview("Mike", "White", 0);
        assertEquals(-1, found);
    }

    @Test
    public void testPrintProfessorReviews() {
        Student student = new Student("Naveen", "Chithan", "naveenchithan@email.com", "8885557536", "randopassword");
        student.addProfessorReview(new Review(5.0, "Amazing Student", "Portia", "Plante"));
        String reviews = "Rating: 5.0 \n";
        reviews += "Amazing Student";
        assertEquals(reviews.length(), student.getProfessorReviews().get(0).ToString().length());
    }

    @Test
    public void testGetSpecProfessorReviewsInList() {
        Student student = new Student("Naveen", "Chithan", "naveenchithan@email.com", "8885557536", "randopassword");
        student.addProfessorReview(new Review(5.0, "Amazing Student", "Portia", "Plante"));
        student.addProfessorReview(new Review(4.5, "Great Student", "Carter", "Marlowe"));
        student.addProfessorReview(new Review(4.0, "Good Student", "Neekon", "Sarmadi"));
        student.addProfessorReview(new Review(1.0, "Should Drop Out", "Gonzalo", "Rodas"));
        Review found = student.getSpecProfessorReviews("Gonzalo", "Rodas");
        assertEquals(student.getProfessorReviews().get(3), found);
    }

    @Test
    public void testGetSpecProfessorReviewsNotInList() {
        Student student = new Student("Naveen", "Chithan", "naveenchithan@email.com", "8885557536", "randopassword");
        student.addProfessorReview(new Review(5.0, "Amazing Student", "Portia", "Plante"));
        student.addProfessorReview(new Review(4.5, "Great Student", "Carter", "Marlowe"));
        student.addProfessorReview(new Review(4.0, "Good Student", "Neekon", "Sarmadi"));
        Review found = new Review(1.0, "Should Drop Out", "Gonzalo", "Rodas");
        assertEquals(null, student.getSpecProfessorReviews("Gonzalo", "Rodas"));
    }

    @Test
    public void testAddProfessorReview() {
        Student student = new Student("Naveen", "Chithan", "naveenchithan@email.com", "8885557536", "randopassword");
        student.addProfessorReview(new Review(5.0, "Amazing Student", "Portia", "Plante"));
        student.addProfessorReview(new Review(4.5, "Great Student", "Carter", "Marlowe"));
        student.addProfessorReview(new Review(4.0, "Good Student", "Neekon", "Sarmadi"));
        assertEquals(3, student.getProfessorReviews().size());
    }

    @Test
    public void testRemoveProfessorReview() {
        Student student = new Student("Naveen", "Chithan", "naveenchithan@email.com", "8885557536", "randopassword");
        student.addProfessorReview(new Review(5.0, "Amazing Student", "Portia", "Plante"));
        student.addProfessorReview(new Review(4.5, "Great Student", "Carter", "Marlowe"));
        student.addProfessorReview(new Review(4.0, "Good Student", "Neekon", "Sarmadi"));
        student.removeProfessorReview(student.getProfessorReviews().get(1));
        assertEquals(2, student.getProfessorReviews().size());
    }
}
