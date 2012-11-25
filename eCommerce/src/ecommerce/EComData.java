/**
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
    
    /**
     * Constructor to initialize data
     * PreCondition: Class fields are not initialized
     * Postcondition: Class field are initialized
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
    
    /**
     * Method to read a file and parse it by it's delimeter
     * and store results into a data structure to be parsed by the 
     * application
     * PreCondition: File that is passed to the method is not resident in memory
     * PostCondition: All records in file now resided in memory and can be ref
     * from a linked list.
     * @param fileName The name of the file that is to be read
     * @return A LinkedList of all records in the file that was read
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
            
            myReader.close();
            myFile.close();
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
    
    /**
     * Method to query based on email and return the specific record
     * in the form of a User object
     * PreCondition: Calling application needs to locate a specific record in
     * memory
     * Postcondition: Record is found and returned to calling method or record
     * is not found and calling method is notified by return null
     * @param email The email of the record searched for
     * @return A User object, if null the record was not found
     */
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

    /**
     * Method to query based on itemName and return the specific record
     * in the form of a Item object
     * PreCondition: Calling application needs to locate a specific record in
     * memory
     * Postcondition: Record is found and returned to calling method or record
     * is not found and calling method is notified by return null
     * @param itemName The item name of the record searched for
     * @return A Item object, if null the record was not found
     */
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
    
        public Review queryReviewID(String reviewID)
    {
        Review myReview;
        reviewIter = reviewData.iterator();
        
        while(reviewIter.hasNext())
        {
            myReview = reviewIter.next();
            
            if( myReview.getName().equals(reviewID))
            {
                return myReview;
            }
        }
        
        return null;
    }

    /**
     * Method to query based on itemName and return the specific record
     * in the form of a LinkedList containing reviews
     * PreCondition: Calling application needs to locate a specific record in
     * memory to return reviews
     * Postcondition: Record is found and returned to calling method or record
     * is not found and calling method is notified by return null
     * @param itemName The item name of the record searched for
     * @return A LinkedList containing all item reviews for the item queried.
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
    
    /**
     * Method to write new records to the data files.  The method accepts a 
     * record and a type.  The type will determine which file is written to.
     * PreCondition: New records exist in memory but need to be written
     * to disk to preserve the changes long term
     * Postcondition: Record is written to the file for the first time
     * @param record The object being written to the file
     * @param recType The type of record to write (user, item, or review)
     * @throws EComException Exception for invalid record type or if the record
     * already exist in the file.
     * @throws IOException If an input or output exception occurred
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
                    myWriter.write(myUser.toCSVString());
                    myWriter.newLine();
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
            
            userData = readFile(userFile);
           
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
                    
                    myWriter.write(myItem.toCSVString());
                    myWriter.newLine();
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
            
            itemData = readFile(itemFile);

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
                
                myWriter.write(myReview.toCSVString());
                myWriter.newLine();
                myWriter.close();
                
            }
            //Catch any exceptions
            catch(IOException e)
            {
                JOptionPane.showMessageDialog(null, "Caught IOException:" +
                    e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);                
            }
            
            reviewData = readFile(reviewFile);
        }
        
        //Invalid record type throw error
        else
        {
            throw new EComException("Invalid recType!");
        }
    }
    
    /**
     * Method to delete records from the data files.  The method accepts a 
     * record and a type.  The type will determine which file to delete the 
     * record from.  The method to delete builds an identical file without the
     * record that is to be deleted and renames it to the original file name.
     * PreCondition: A record has changed or needs to be purged from the data
     * file
     * Postcondition: Record is deleted from the file
     * @param record The object being deleted from the file
     * @param recType The type of record to delete (user, item, or review)
     * @throws EComException Exception for invalid record type or if the record
     * @throws IOException If an input or output exception occurred
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
                    myWriter.newLine();
                }
                
                //Close File handles
                myReader.close();
                myWriter.close();
                
                myFile.delete();
                
                int maxRetry = 60;
                boolean successful = false;
                
                while(maxRetry > 0)
                {
                    if(successful = myTmpFile.renameTo(myFile))
                    {
                        break;
                    }
                    try
                    {
                        Thread.sleep(1000);
                    }
                    catch(InterruptedException e)
                    {
                        
                    }
                }
                
                userData = readFile(userFile);
                
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
                    myWriter.newLine();
                }
                
                //Close File handles
                myReader.close();
                myWriter.close();
                
                myFile.delete();
                
                int maxRetry = 60;
                boolean successful = false;
                
                while(maxRetry > 0)
                {
                    if(successful = myTmpFile.renameTo(myFile))
                    {
                        break;
                    }
                    try
                    {
                        Thread.sleep(1000);
                    }
                    catch(InterruptedException e)
                    {
                        
                    }
                }
                
                itemData = readFile(itemFile);
                
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
                    myWriter.newLine();
                }
                
                //Close File handles
                myReader.close();
                myWriter.close();
                
                myFile.delete();
                
                int maxRetry = 60;
                boolean successful = false;
                
                while(maxRetry > 0)
                {
                    if(successful = myTmpFile.renameTo(myFile))
                    {
                        break;
                    }
                    try
                    {
                        Thread.sleep(1000);
                    }
                    catch(InterruptedException e)
                    {
                        
                    }
                }
                
                reviewData = readFile(reviewFile);
                
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
    
    public boolean updateRecord(Object record, String recType) 
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
                
                //Get the key of the record to update
                String recordToUpdate = myUser.getEmail();
                
                String currentRecord;
                
                //Read until the end of the file
                while((currentRecord = myReader.readLine()) != null)
                {
                    //Parse line into String array
                    String[] tokens = currentRecord.split(",");
                    
                    //If the line matches the record to update write it
                    if(tokens[0].equals(recordToUpdate))
                    {
                        myWriter.write(myUser.toCSVString());
                        myWriter.newLine();
                        continue;
                    }
                    
                    //Write record to temp file
                    myWriter.write(currentRecord);
                    myWriter.newLine();
                }
                
                //Close File handles
                myReader.close();
                myWriter.close();
                
                myFile.delete();
                
                int maxRetry = 60;
                boolean successful = false;
                
                while(maxRetry > 0)
                {
                    if(successful = myTmpFile.renameTo(myFile))
                    {
                        break;
                    }
                    try
                    {
                        Thread.sleep(1000);
                    }
                    catch(InterruptedException e)
                    {
                        
                    }
                }
                
                userData = readFile(userFile);
                
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
                
                //Get the key of the record to update
                String recordToUpdate = myItem.getName();
                
                String currentRecord;
                
                //Read until the end of the file
                while((currentRecord = myReader.readLine()) != null)
                {
                    //Parse line into String array
                    String[] tokens = currentRecord.split(",");
                    
                    //If the line matches the record to update
                    //write it to the temp file
                    if(tokens[0].equals(recordToUpdate))
                    {
                        myWriter.write(myItem.toCSVString());
                        myWriter.newLine();
                        continue;
                    }
                    
                    //Write record to temp file
                    myWriter.write(currentRecord);
                    myWriter.newLine();
                }
                
                //Close File handles
                myReader.close();
                myWriter.close();
                
                myFile.delete();
                
                int maxRetry = 60;
                boolean successful = false;
                
                while(maxRetry > 0)
                {
                    if(successful = myTmpFile.renameTo(myFile))
                    {
                        break;
                    }
                    try
                    {
                        Thread.sleep(1000);
                    }
                    catch(InterruptedException e)
                    {
                        
                    }
                }
                
                itemData = readFile(itemFile);
                
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
                
                //Get the key of the record to update
                String recordToUpdate = myReview.getReviewID();
                
                String currentRecord;
                
                //Read until the end of the file
                while((currentRecord = myReader.readLine()) != null)
                {
                    //Parse line into String array
                    String[] tokens = currentRecord.split(",");
                    
                    //If the line matches the record to update
                    //write it to the temp file
                    if(tokens[0].equals(recordToUpdate))
                    {
                        myWriter.write(myReview.toCSVString());
                        myWriter.newLine();
                        continue;
                    }
                    
                    //Write record to temp file
                    myWriter.write(currentRecord);
                    myWriter.newLine();
                }
                
                //Close File handles
                myReader.close();
                myWriter.close();
                
                myFile.delete();
                
                int maxRetry = 60;
                boolean successful = false;
                
                while(maxRetry > 0)
                {
                    if(successful = myTmpFile.renameTo(myFile))
                    {
                        break;
                    }
                    try
                    {
                        Thread.sleep(1000);
                    }
                    catch(InterruptedException e)
                    {
                        
                    }
                }
                
                reviewData = readFile(reviewFile);
                
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
    
     /**
     * Method to return the private userData class field
     * PreCondition: Calling methods need the complete userData object
     * Postcondition: userData object is returned
     * @return userData object is returned
     */
    public LinkedList getUserData()
    {
        return userData;
    }
    
     /**
     * Method to return the private itemData class field
     * PreCondition: Calling methods need the complete itemData object
     * Postcondition: itemData object is returned
     * @return itemData object is returned
     */
    public LinkedList getItemData()
    {
        return itemData;
    }

     /**
     * Method to return the private reviewData class field
     * PreCondition: Calling methods need the complete reviewData object
     * Postcondition: reviewData object is returned
     * @return reviewData object is returned
     */
    public LinkedList getReviewData()
    {
        return reviewData;
    }
    
}
