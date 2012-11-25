/**
 * Class: Item
 * 
 * Purpose:
 * The purpose of this class is to hold the structure of the 
 * item record and provide methods for retrieving specific elements
 * of the record
 * 
 * Change Log:
 * Matt Folds 10/16/12 - Initial Development
 * 
 */
package ecommerce;

import java.util.Objects;




public class Item {
    
    private String name;
    private String description;
    private String photoPath;
    private String price;
    
    
    public Item()
    {
        
    }
    
    /**
     * Constructor which accepts a string array and assigns the elements of the
     * array to specific Class fields
     * PreCondition: object does not exist
     * PostCondition: new object created based on input
     * @param record String array used to build the object
     */
    public Item(String[] record)
    {
        name = record[0];
        description = record[1];
        photoPath = record[2];
        price = record[3];
    }
    
    public String getName()
    {
        return name;
    }
    
    public String getDescription()
    {
        return description;
    }
    
    public String getPhotoPath()
    {
        return photoPath;
    }
    
    public String getPrice()
    {
        return price;
    }
    
    public void setName(String myName)
    {
        name = myName;
    }
    
    public void setDescription(String myDescription)
            
    {
        description = myDescription;
    }
    
    public void setPhotoPath(String myPhotoPath)
    {
        photoPath = myPhotoPath;
    }
    
    public void setPrice(String myPrice)
    {
        price = myPrice;
    }
    
    
    @Override
    public boolean equals(Object obj)
    {
        Item compare = (Item)obj;
        
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
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.name);
        hash = 53 * hash + Objects.hashCode(this.description);
        hash = 53 * hash + Objects.hashCode(this.photoPath);
        hash = 53 * hash + Objects.hashCode(this.price);
        return hash;
    }
    
    public String toCSVString()
    {
        return name + "," + description + "," + photoPath + "," + price;
    }
    
}
