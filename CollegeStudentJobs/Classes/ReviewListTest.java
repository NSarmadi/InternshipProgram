package Classes;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

public class ReviewListTest {
    @BeforeEach
    public void setup(){
        ReviewList reviewList = ReviewList.getInstance();
        reviewList.getReviews().clear();
    }

    @Test
    public void testAddReview(){
        ReviewList reviewList = ReviewList.getInstance();
        reviewList.getReviews().clear();
        reviewList.getReviews().add(new Review(0, "comment", "firstName", "lastName", null));
        assertEquals(0 , reviewList.getReviews().get(0).getRating());

    }

    @Test
    public void addNoReview(){
        ReviewList reviewList = ReviewList.getInstance();
        reviewList.getReviews().clear();
        assertEquals(0, reviewList.getReviews().size());
    }

    @Test
    public void addMultipleReviews(){
        ReviewList reviewList = ReviewList.getInstance();
        reviewList.getReviews().clear();
        reviewList.getReviews().add(new Review( 0, "comment", "firstName", "lastName", null));
        reviewList.getReviews().add(new Review( 5, "comment1", "firstName1", "lastName1", null));
        reviewList.getReviews().add(new Review( 4, "comment2", "firstName2", "lastName2", null));
        assertEquals(5 , reviewList.getReviews().get(1).getRating());
    }

}
