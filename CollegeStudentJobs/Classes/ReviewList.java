package Classes;
import java.util.ArrayList; 
import java.util.UUID;

/**
 * Creates a ReviewList class, which will hold a single instance of the List of Reviews
 */
public class ReviewList {
    //Attributes
    private static ReviewList reviewList;
    private ArrayList<Review> reviews;

    /**
     * ReviewList Constructor, gets the review list from json and adds it to the class' ArrayList
     */
    private ReviewList() {
        this.reviews = DataLoader.getReviewList();
    }

    /**
     * getInstance method that creates a singular instance of the ReviewList class
     * @return ReviewList, the instance of the ReviewList
     */
    public static ReviewList getInstance() {
        if (reviewList == null) {
            reviewList = new ReviewList();
        }
        return reviewList;
    }

    /**
     * accessor method that finds a review from the ReviewList, finding it by its Unique ID
     * @param id UUID, Unique ID for the review to be found and returned
     * @return Review, Review found by its Unique ID
     */
    public Review getReview(UUID id) {
        for (Review review : reviews) {
            if (review.getID().equals(id)) return review;
        }
        return null;
    }

    /**
     * accesor method for the list of reviews
     * @return ArrayList<Review>, the list of reviews
     */
    public ArrayList<Review> getReviews() {
        return this.reviews; 
    }

    /**
     * addReviewToList method which adds a review the review list
     * @param review Review, Review to be added to the review list
     */
    public void addReviewToList(Review review) {
        reviews.add(review);
    }

    /**
     * saveReviewList method to save the reviews to the reviewlist json file
     */
    public static void saveReviewList() {
        DataWriter.saveReviewList();
    }        
}
