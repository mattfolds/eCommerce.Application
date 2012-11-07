/**
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
 * Matt Folds 10/20/12 - Added several methods to support sign-in, sign-out,
 * and registering new users.
 * 
 */
package ecommerce;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class HeaderPanel extends JPanel
{
    private JLabel logoLabel;
    private JButton registerButton;
    private static JButton signInOutButton;
    private JTextField searchField;
    private JButton searchButton;
    private JButton cartButton;
    
    private static SignInPanel signIn;
    private static RegisterPanel register;

    /**
     * Constructor to build the controls for the panel for displaying on the ui
     */
    public HeaderPanel()
    {
        //Create controls
        logoLabel = new JLabel("LOGO");
        registerButton = new JButton("Register");
        signInOutButton = new JButton("Sign In");
        searchField = new JTextField("", 20);
        searchButton = new JButton("Search");
        cartButton = new JButton("Cart");
        
        /**
         * ActionListener for the registerButton that calls displayRegisterForm
         */
        registerButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                displayRegisterForm();
            }
        });
        
        /**
         * ActionListener for sign-in / sign-out button calls displaySignInForm
         * if there is no one currently signed in otherwise userSignOut is 
         * called.
         */
        signInOutButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(signInOutButton.getText().equals("Sign In"))
                {
                    displaySignInForm();
                }
                else
                {
                    userSignOut();
                }
            }
        });
        
        GridBagLayout gb = new GridBagLayout();
        
        //Set layout of panel
        setLayout(gb);
        
        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.WEST;
        //gbc.gridwidth = GridBagConstraints.RELATIVE;
        //gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.insets = new Insets(5,2,5,2);
        
        //Add controls to panel
        add(logoLabel, gbc);
        add(registerButton, gbc);
        add(signInOutButton, gbc);
        add(searchField, gbc);
        add(searchButton, gbc);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        add(cartButton, gbc);
        
    }
    
    /**
     * Method called by SignInOutButton when clicked and a user is not currently
     * signed in.  The method displays the SignInPanel for the user to enter
     * their credentials
     * PreCondition: SignIn Form is not displayed
     * PostCondition: SignIn Form is displayed to user
     */
    private static void displaySignInForm()
    {
        ECommerce.currentUser = null;
        signIn = new SignInPanel();
    }
    
    /**
     * Method called by SignInPanel to close the SignIn Form when the user is
     * presses the cancel key.
     * PreCondition: Request to close the SignIn form
     * PostCondition: SignIn Form is closed
     */
    public static void closeSignInForm()
    {
        signIn.dispose();   
    }
    
    /**
     * Method called by SignInPanel to close the SignInForm when the user is
     * validated.  This method is also responsible to setting the global
     * variable for the current user that is signed in.
     * PreCondition: User is validated close the form
     * PostCondition: SignIn Form is closed and current user is set
     * @param myUser Instance of User class of the current user signed in
     */
    public static void closeSignInForm(User myUser)
    {
        ECommerce.currentUser = myUser;
        
        signIn.dispose();
        
        userSignIn();        
    }
     
    /**
     * Method called by registerButton ActionListener to display the new user 
     * register form.
     * PreCondition: New user register Form is not displayed
     * PostCondition: New user register Form is displayed to user
     */   
    private void displayRegisterForm()
    {
        ECommerce.currentUser = null;
        register = new RegisterPanel();
    }
    
    /**
     * Method called by SignInPanel to close the new user 
     * register form when cancel button is clicked
     * PreCondition: New user register Form is canceled
     * PostCondition: New user register Form is closed
     */ 
    public static void closeRegisterForm()
    {
        register.dispose();
    }    
    
    /**
     * Method called by SignInPanel to close the new user register form when ok
     * button is clicked and to call user SignIn method
     * PreCondition: New user register Form is validated current user is set
     * PostCondition: New user register Form is closed
     * @param newUser Instance of User class of the newly registered user
     */ 
    public static void closeRegisterForm(User newUser)
    {
        ECommerce.currentUser = newUser;
        
        register.dispose();
        
        userSignIn();
    }
    
    /**
     * Method called by closeSignInForm and closeRegisterForm to change the text
     * of the sign-in button to sign-out
     * PreCondition: User signed in
     * PostCondition: Change SignIn button text to sign out
     */     
    private static void userSignIn()
    {
        signInOutButton.setText("Sign Out");
    }

    /**
     * Method called by SignInOutButton when clicked and a user is signed in.
     * The method clears the currentUser GLOBAL variable and sets text on the
     * sign-out button to sign-in.
     * PreCondition: User has signed out
     * PostCondition: Change SignIn button text to sign in null current user
     */  
    private static void userSignOut()
    {
        ECommerce.currentUser = null;
        
        signInOutButton.setText("Sign In");
    }
    
}
