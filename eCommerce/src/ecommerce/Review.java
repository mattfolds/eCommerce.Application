/*
 * Class: Review
 * 
 * Purpose:
 * The purpose of this class is to hold the structure of the 
 * Review record and provide methods for retrieving specific elements
 * of the record
 * 
 * Change Log:
 * Matt Folds 10/16/12 - Initial Development
 */
package ecommerce;


public class Review {
    
    private String reviewID;
    private String name;
    private String reviewText;
    private String rating;
    
    public Review(String[] record)
    {
        reviewID = record[0];
        name = record[1];
        reviewText = record[2];
        rating = record[3];
    }
    
    public String getReviewID()
    {
        return reviewID;
    }
    
    public String getName()
    {
        return name;
    }
    
    public String getReviewText()
    {
        return reviewText;
    }
    
    public String getRating()
    {
        return rating;
    }
    
    public void setReviewID(String myReviewID)
    {
        reviewID = myReviewID;
    }
    
    public void setName(String myName)
    {
        name = myName;
    }
    
    public void setReviewText(String myReviewText)
            
    {
        reviewText = myReviewText;
    }
    
    public void setRating(String myRating)
    {
        rating = myRating;
    }    
    
    public String toCSVString()
    {
        return reviewID + "," + name + "," + reviewText + "," + 
                rating;
    }
    
}
