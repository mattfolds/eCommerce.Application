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
    
    //Globals
    public static EComData myData;
    public static User currentUser;

    public ECommerce()
    {
        myData = new EComData();
        
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
        
        
    }
}
