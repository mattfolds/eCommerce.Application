/**
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

import java.util.Objects;


public class Review {
    
    private String reviewID;
    private String name;
    private String reviewText;
    private String rating;
     
    public Review()
    {
        
    }
    
    /**
     * Constructor which accepts a string array and assigns the elements of the
     * array to specific Class fields
     * PreCondition: object does not exist
     * PostCondition: new object created based on input
     * @param record String array used to build the object
     */
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
    
    @Override
    public boolean equals(Object obj)
    {
        Review compare = (Review)obj;
        
        if(this == obj)
        {
            return true;
        }
        else if(this.hashCode() == compare.hashCode())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.reviewID);
        hash = 23 * hash + Objects.hashCode(this.name);
        hash = 23 * hash + Objects.hashCode(this.reviewText);
        hash = 23 * hash + Objects.hashCode(this.rating);
        return hash;
    }
    
    public String toCSVString()
    {
        return reviewID + "," + name + "," + reviewText + "," + 
                rating;
    }
    
}
