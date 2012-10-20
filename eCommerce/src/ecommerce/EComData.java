/*
 * Class: EComData
 * 
 * Purpose:
 * The purpose of this class is to perform all IO on the data
 * files and allow structured searchable access to the applications data.
 * 
 * Change Log:
 * Matt Folds 10/16/12 - Initial Development
 * 
 * Matt Folds 10/18/12 - Added the ability to write and delete records from the
 * files. 
 */

package ecommerce;

import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;

public class EComData {
    
    /*
     * Class variables
     */
    
    //File Names
    private String userFile;
    private String itemFile;
    private String reviewFile;
    private String tmpFile;
    
    //Linked List
    private LinkedList<User> userData;
    private LinkedList<Item> itemData;
    private LinkedList<Review> reviewData;
    
    //Iterators
    private Iterator<User> userIter;
    private Iterator<Item> itemIter;
    private Iterator<Review> reviewIter;
    
    /*
     * Constructor to initialize data
     */
    public EComData()
    {
        userFile = "user.csv";
        itemFile = "item.csv";
        reviewFile = "review.csv";
        tmpFile = "tmp.csv";
        
        userData = readFile(userFile);
        itemData = readFile(itemFile);
        reviewData = readFile(reviewFile);
        
    }
    
    /* 
     * Method to read a file and parse it by it's delimeter
     * and store results into a data structure to be parsed by the 
     * application
     */
    private LinkedList readFile(String fileName)
    {
        //Used to read file
        FileReader myFile;
        BufferedReader myReader;
        
        //Used to store lines and tokens in file
        String line;
        String[] tokens;
        
        //Used to house the collection of elements from the file
        LinkedList elements = new LinkedList();
        
        try
        {
            //Create file reader
            myFile = new FileReader(fileName);
            myReader = new BufferedReader(myFile);
            
            //Read in the first line
            line = myReader.readLine();
            
            //Cont. to read until at the end of the file
            while (line != null)
            {
                //Parse each line by the ',' character
                tokens = line.split(",");
                
                //Block of if statements to determine
                //what file I am processing so the correct
                //record is created.
                if(fileName.equals(userFile))
                {
                    User record = new User(tokens);
                    
                    //Add array of strings to list
                    elements.add(record);
                }
                else if(fileName.equals(itemFile))
                {
                    Item record = new Item(tokens);
                    
                    //Add array of strings to list
                    elements.add(record);
                }
                else if(fileName.equals(reviewFile))
                {
                    Review record = new Review(tokens);
                    
                    //Add array of strings to list
                    elements.add(record);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Unknown File",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    break;
                }
                
                //Read the next line
                line = myReader.readLine();
            }
        }
        //If the file does not exist throw dialog message
        catch(FileNotFoundException e)
        {
            JOptionPane.showMessageDialog(null, "File not found: " +
                    fileName, "Error", JOptionPane.ERROR_MESSAGE);
        }
        //Catch any other IOException and throw dialog message
        catch (IOException e)
        {
            JOptionPane.showMessageDialog(null, "Caught IOException:" +
                    e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        //Return the list of elements to the caller
        return elements;
    }
    
    //Method to query based on email and return the specific record
    //in the form of a User object
    public User queryUserData(String email)
    {
        User myUser;
        userIter = userData.iterator();
        
        while(userIter.hasNext())
        {
            myUser = userIter.next();
            
            if( myUser.getEmail().equals(email))
            {
                return myUser;
            }
        }
        
        return null;
    }

    //Method to query based on itemName and return the specific record
    //in the form of a item object
    public Item queryItemData(String itemName)
    {
        Item myItem;
        itemIter = itemData.iterator();
        
        while(itemIter.hasNext())
        {
            myItem = itemIter.next();
            
            if( myItem.getName().equals(itemName))
            {
                return myItem;
            }
        }
        
        return null;
    }

    /*
     *Method to query based on itemName and return a linked list of
     *review objects associated with the item
     */
    public LinkedList queryReviewData(String itemName)
    {
        Review myReview;
        reviewIter = reviewData.iterator();
        LinkedList<Review> itemReviews = new LinkedList();
        
        while(reviewIter.hasNext())
        {
            myReview = reviewIter.next();
            
            if( myReview.getName().equals(itemName))
            {
                itemReviews.add(myReview);
            }
        }
        
        return itemReviews;
    }
    
    /*
     * Method to write new records to the data files.  The method accepts a 
     * record and a type.  The type will determine which file is written to.
    */
    public void writeRecord(Object record, String recType) throws EComException
    {
        //Define writer
        FileWriter myFile;
        BufferedWriter myWriter;
        
        //Test for user record
        if(recType.equals("user"))
        {
            //Cast record in User object
            User myUser = (User)record;
            User testUser;
            
            try
            {
                //Initialize the writer
                myFile = new FileWriter(userFile, true);
                myWriter = new BufferedWriter(myFile);
                
                //Search for the record attempting to be written to 
                //avoid duplicate entries
                testUser = queryUserData(myUser.getEmail());
                
                //If a duplicate was not found write the record
                if(testUser == null)
                {
                    myWriter.newLine();
                    myWriter.write(myUser.toCSVString());
                    myWriter.close();
                }
                
                //Throw error if the record already exist in the file
                else
                {
                    throw new EComException("The record already exist in"
                            + " the file.");
                }
            }
            
            //Catch any IO exceptions
            catch(IOException e)
            {
                JOptionPane.showMessageDialog(null, "Caught IOException:" +
                    e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);                
            }         
            
        }
        
        //Test for item record
        else if(recType.equals("item"))
        {
            //Cast record in Item object
            Item myItem = (Item)record;
            Item testItem;
            
            try
            {
                
                //Initialize the writer
                myFile = new FileWriter(itemFile, true);
                myWriter = new BufferedWriter(myFile);
                
                //Search for the record attempting to be written to 
                //avoid duplicate entries
                testItem = queryItemData(myItem.getName());
                
                //If a duplicate was not found write the record
                if(testItem == null)
                {
                    myWriter.newLine();
                    myWriter.write(myItem.toCSVString());
                    myWriter.close();
                }
                //Throw error if the record already exist in the file
                else
                {
                    throw new EComException("The record already exist in"
                            + " the file.");
                }
            }
            //Catch any IO exceptions
            catch(IOException e)
            {
                JOptionPane.showMessageDialog(null, "Caught IOException:" +
                    e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);                
            }              
        }
        
        //Test for review record
        else if(recType.equals("review"))
        {
            Review myReview = (Review)record;
            
            try
            {
                //Initialize the writer
                myFile = new FileWriter(reviewFile, true);
                myWriter = new BufferedWriter(myFile);
                
                //Write the review to the file
                myWriter.newLine();
                myWriter.write(myReview.toCSVString());
                myWriter.close();
                
            }
            //Catch any exceptions
            catch(IOException e)
            {
                JOptionPane.showMessageDialog(null, "Caught IOException:" +
                    e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);                
            }              
        }
        
        //Invalid record type throw error
        else
        {
            throw new EComException("Invalid recType!");
        }
    }
    
    /*
     * Method to delete records from the data files.  The method accepts a 
     * record and a type.  The type will determine which file to delete the 
     * record from.  The method to delete builds an identical file without the
     * record that is to be deleted and renames it to the original file name.
    */
    public boolean deleteRecord(Object record, String recType) 
            throws EComException
    {
        //Define writer
        File myTmpFile;
        BufferedWriter myWriter;
        
        //Define Reader
        File myFile;
        BufferedReader myReader;
        
        //Test for user record
        if(recType.equals("user"))
        {
            //Cast record in User object
            User myUser = (User)record;

            try
            {
                //Initialize the writer
                myTmpFile = new File(tmpFile);
                myWriter = new BufferedWriter(new FileWriter(myTmpFile));
                
                //Initialize the reader
                myFile = new File(userFile);
                myReader = new BufferedReader(new FileReader(myFile));
                
                //Get the key of the record to delete
                String recordToDelete = myUser.getEmail();
                
                String currentRecord;
                
                //Read until the end of the file
                while((currentRecord = myReader.readLine()) != null)
                {
                    //Parse line into String array
                    String[] tokens = currentRecord.split(",");
                    
                    //If the line matches the record to delete skip it
                    if(tokens[0].equals(recordToDelete))
                    {
                        continue;
                    }
                    
                    //Write record to temp file
                    myWriter.write(currentRecord);
                }
                
                //Close File handles
                myReader.close();
                myWriter.close();
                
                boolean successful = myTmpFile.renameTo(myFile);
                
                //Return status
                return successful;

            }
            
            //Catch any IO exceptions
            catch(IOException e)
            {
                JOptionPane.showMessageDialog(null, "Caught IOException:" +
                    e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);                
            }         
            
        }
        
        //Test for item record
        else if(recType.equals("item"))
        {
            //Cast record in Item object
            Item myItem = (Item)record;
            
            try
            {
                //Initialize the writer
                myTmpFile = new File(tmpFile);
                myWriter = new BufferedWriter(new FileWriter(myTmpFile));
                
                //Initialize the reader
                myFile = new File(itemFile);
                myReader = new BufferedReader(new FileReader(myFile));
                
                //Get the key of the record to delete
                String recordToDelete = myItem.getName();
                
                String currentRecord;
                
                //Read until the end of the file
                while((currentRecord = myReader.readLine()) != null)
                {
                    //Parse line into String array
                    String[] tokens = currentRecord.split(",");
                    
                    //If the line matches the record to delete skip it
                    //and do not write it to the temp file
                    if(tokens[0].equals(recordToDelete))
                    {
                        continue;
                    }
                    
                    //Write record to temp file
                    myWriter.write(currentRecord);
                }
                
                //Close File handles
                myReader.close();
                myWriter.close();
                
                boolean successful = myTmpFile.renameTo(myFile);
                
                //Return status
                return successful;

            }
            
            //Catch any IO exceptions
            catch(IOException e)
            {
                JOptionPane.showMessageDialog(null, "Caught IOException:" +
                    e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);                
            }
        }
        
        //Test for review record
        else if(recType.equals("review"))
        {
            //Cast record in Item object
            Review myReview = (Review)record;
            
            try
            {
                //Initialize the writer
                myTmpFile = new File(tmpFile);
                myWriter = new BufferedWriter(new FileWriter(myTmpFile));
                
                //Initialize the reader
                myFile = new File(reviewFile);
                myReader = new BufferedReader(new FileReader(myFile));
                
                //Get the key of the record to delete
                String recordToDelete = myReview.getReviewID();
                
                String currentRecord;
                
                //Read until the end of the file
                while((currentRecord = myReader.readLine()) != null)
                {
                    //Parse line into String array
                    String[] tokens = currentRecord.split(",");
                    
                    //If the line matches the record to delete skip it
                    //and do not write it to the temp file
                    if(tokens[0].equals(recordToDelete))
                    {
                        continue;
                    }
                    
                    //Write record to temp file
                    myWriter.write(currentRecord);
                }
                
                //Close File handles
                myReader.close();
                myWriter.close();
                
                boolean successful = myTmpFile.renameTo(myFile);
                
                //Return status
                return successful;

            }
            
            //Catch any IO exceptions
            catch(IOException e)
            {
                JOptionPane.showMessageDialog(null, "Caught IOException:" +
                    e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);                
            }
        }
        
        //Invalid record type throw error
        else
        {
            throw new EComException("Invalid recType!");
        }
        
        return false;
    }
    
    //Access to complete user Linked List
    public LinkedList getUserData()
    {
        return userData;
    }
    
    //Access to complete item Linked List
    public LinkedList getItemData()
    {
        return itemData;
    }

    //Access to complete review Linked List
    public LinkedList getReviewData()
    {
        return reviewData;
    }
    
}
