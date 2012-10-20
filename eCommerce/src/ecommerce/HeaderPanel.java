/*
 * Class: HeaderPanel
 * 
 * Purpose:
 * Creates the header panel which displays the customer logo,
 * register and sign-in controls.  Also host the text box for searching and
 * button to take the user to their cart.
 * 
 * Change Log:
 * Matt Folds 10/18/12 - Initial Development
 * 
 */
package ecommerce;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HeaderPanel extends JPanel
{
    private JLabel logoLabel;
    private JButton registerButton;
    private JButton signInOutButton;
    private JTextField searchField;
    private JButton searchButton;
    private JButton cartButton;
        
    public HeaderPanel()
    {
        //Create controls
        logoLabel = new JLabel("LOGO");
        registerButton = new JButton("Register");
        signInOutButton = new JButton("Sign In");
        searchField = new JTextField("", 20);
        searchButton = new JButton("Search");
        cartButton = new JButton("Cart");
        
        signInOutButton.addActionListener(new signInOutListener());
        
        //Add controls to panel
        add(logoLabel);
        add(registerButton);
        add(signInOutButton);
        add(searchField);
        add(searchButton);
        add(cartButton);
    }
    
    private class signInOutListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            SignInPanel go = new SignInPanel();
        }
    }
}
