/*
 * Class: Ecommerce
 * 
 * Purpose:
 * Entry point for the application.
 * 
 * Change Log:
 * Matt Folds 10/18/12 - Initial Development
 * 
 */
package ecommerce;

import java.util.*;
import javax.swing.*;
import java.awt.*;

public class ECommerce extends JFrame
{

    private final int WINDOW_WIDTH = 1024;
    private final int WINDOW_HEIGHT = 768;
    
    private HeaderPanel header;
    private NavigationPanel navigation;
    
    public static EComData myData;

    public ECommerce()
    {
        //Application Title
        setTitle("ECommerce Application");
        
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        
        //Setup application close op
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Application panel layout
        setLayout(new BorderLayout());
        
        header = new HeaderPanel();
        navigation = new NavigationPanel();
        
        add(header, BorderLayout.NORTH);
        add(navigation, BorderLayout.WEST);
        
        pack();
		
	setVisible(true);
        
    }
    
    public static void main(String[] args) {
        
        
        ECommerce go = new ECommerce();
        
        //Test of the data class and structures
        
        myData = new EComData();
        
        //Test of User class within object list
        
        LinkedList<User> allUsers = myData.getUserData();
        
        int index = 0;
        
        User myUser = allUsers.get(index);
        
        System.out.println("User Data:");
        System.out.println(myUser.getEmail());
        System.out.println(myUser.getFName());
        System.out.println(myUser.getLName());
        
        //Test of Item class within object list
        
        LinkedList<Item> allItems = myData.getItemData();
        LinkedList<Review> allReviews = myData.getReviewData();
        
        index = 0;
        
        Item myItem = allItems.get(index);
        Review myReview = allReviews.get(index);
        
        System.out.println();
        System.out.println("Item Data:");
        System.out.println(myItem.getName());
        System.out.println(myItem.getDescription());
        System.out.println(myItem.getPrice());
        System.out.println("Item Review: " + myReview.getReviewText());
        
        System.out.println();
        System.out.println("Search for User:");
        System.out.println("Searching for matt.folds@gmail.com");
        
        User mattUser = myData.queryUserData("matt.folds@gmail.com");
        
        System.out.println(mattUser.getFName() + " " + mattUser.getLName());
        
        User nullUser = myData.queryUserData("blahblah");
        
        if(nullUser == null)
        {
            System.out.println();
            System.out.println("No record found");
        }
        else
        {
        System.out.println();
        System.out.println(nullUser.getEmail());
        }
        
        try
        {
            myData.writeRecord(mattUser, "user");
        }
        catch(EComException e)
        {
            System.out.println(e.getMessage());
        }
        
        User newUser = new User();
        newUser.setEmail("tom@gsu.edu");
        newUser.setFName("Tom");
        newUser.setLName("Sawyer");
        newUser.setPassword("tSwayer");
        newUser.setPasswordHint("First init and last name");
        
        try
        {
            myData.writeRecord(newUser, "user");
        }
        catch(EComException e)
        {
            System.out.println(e.getMessage());
        }
        
        
    }
}
