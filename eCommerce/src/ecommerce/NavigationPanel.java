/*
 * Class: NavigationPanel
 * 
 * Purpose:
 * Creates the navigation panel which displays the categories to select from
 * 
 * Change Log:
 * Matt Folds 10/18/12 - Initial Development
 * 
 */
package ecommerce;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class NavigationPanel extends JPanel
{
    private JLabel title;
    private JButton cat1;
    private JButton cat2;
    private JButton cat3;
    private JButton cat4;
    private JButton cat5;
    private JButton cat6;
    private JButton cat7;
    private JButton cat8;
    private JButton cat9;
    private JButton cat10;
    
    private Color backGround;
    private Color btnBackGround;
    
    public NavigationPanel()
    {
        //Setup customer colors
        backGround = new Color(209, 206, 206);
        btnBackGround = new Color(221, 220, 220);
        
        //Set Layout
        setLayout(new GridBagLayout());
        
        //set background color
        setBackground(backGround);
        
        //Configure layout control
        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.ipadx = 115;
        gbc.ipady = 35;
        gbc.insets = new Insets(2,2,2,2);
        
        //Create control objects
        title = new JLabel("Categories", JLabel.CENTER);
        cat1 = new JButton("");
        cat2 = new JButton("");
        cat3 = new JButton("");
        cat4 = new JButton("");
        cat5 = new JButton("");
        cat6 = new JButton("");
        cat7 = new JButton("");
        cat8 = new JButton("");
        cat9 = new JButton("");
        cat10 = new JButton("");
        
        //Set color of buttons
        cat1.setBackground(btnBackGround);
        cat2.setBackground(btnBackGround);
        cat3.setBackground(btnBackGround);
        cat4.setBackground(btnBackGround);
        cat5.setBackground(btnBackGround);
        cat6.setBackground(btnBackGround);
        cat7.setBackground(btnBackGround);
        cat8.setBackground(btnBackGround);
        cat9.setBackground(btnBackGround);
        cat10.setBackground(btnBackGround);
        
        //Add controls to panel
        add(title, gbc);
        add(cat1, gbc);
        add(cat2, gbc);
        add(cat3, gbc);
        add(cat4, gbc);
        add(cat5, gbc);
        add(cat6, gbc);
        add(cat7, gbc);
        add(cat8, gbc);
        add(cat9, gbc);
        add(cat10, gbc);
    }
    
}
