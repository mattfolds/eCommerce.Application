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

import java.awt.*;
import javax.swing.*;

public class ECommerce extends JFrame
{
    
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
        
        setSize(new Dimension(800,750));
        
        setResizable(false);
        
        //Setup application close op
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        GridBagLayout gb = new GridBagLayout();
        
        //Application panel layout
        setLayout(gb);
        
        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.insets = new Insets(5,2,5,2);
        
        header = new HeaderPanel();
        navigation = new NavigationPanel();
        
        add(header, gbc);
        
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.NONE;        
        gbc.gridwidth = GridBagConstraints.RELATIVE;
        add(navigation, gbc);
        
        pack();
		
	setVisible(true);
        
    }
    
    public static void main(String[] args) {
        
        
        ECommerce go = new ECommerce();
        
        
    }
}
