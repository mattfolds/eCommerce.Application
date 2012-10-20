/*
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


public class Item {
    
    private String name;
    private String description;
    private String photoPath;
    private String price;
    
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
    
    public String toCSVString()
    {
        return name + "," + description + "," + photoPath + "," + price;
    }
    
}
