/**
 * Class: NavigationPanel
 * 
 * Purpose:
 * Creates the sign in panel which displays the form for sign-in
 * 
 * Change Log:
 * Matt Folds 10/18/12 - Initial Development
 * 
 * Matt Folds 10/21/12 - Corrected form layout and added methods to validate
 * sign-in information
 * 
 */
package ecommerce;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SignInPanel extends JFrame
{
    
    private JLabel titleLbl;
    
    private JLabel emailLbl;
    private JLabel passwordLbl;
    
    private JTextField emailTxt;
    private JTextField passwordTxt;
    
    private JButton okBtn;
    private JButton cancelBtn;
    
    private EComData signInData = ECommerce.myData;
    
    /**
     * Constructor for building the panel to host the sign in controls
     */
    public SignInPanel()
    {        
        //Set window title
        setTitle("Sign-In");
        
        //Set close operation for X button
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        //Create panel to hold controls
        JPanel container = new JPanel();
        
        GridBagLayout gb = new GridBagLayout();
        
        //Set layout of panel
        container.setLayout(gb);
        
        //Create controls
        titleLbl = new JLabel("User Sign-In");
        
        emailLbl = new JLabel("E-Mail:");
        passwordLbl = new JLabel("Password:");
        
        emailTxt = new JTextField("", 20);
        passwordTxt = new JTextField("", 20);
        
        okBtn = new JButton("OK");
        cancelBtn = new JButton("Cancel");
        
        //Handle okBtn click
        okBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                signIn();
            }
        });
        
        //Handle cancle button click
        cancelBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                HeaderPanel.closeSignInForm();
            }
        });
        
        //Configure layout control
        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(10,10,10,10);
        
        container.add(titleLbl, gbc);
        
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridwidth = GridBagConstraints.RELATIVE;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.insets = new Insets(5,2,5,2);
        
        container.add(emailLbl, gbc);
        
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        container.add(emailTxt, gbc);

        gbc.gridwidth = GridBagConstraints.RELATIVE;
        container.add(passwordLbl, gbc);
        
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        container.add(passwordTxt, gbc);
        
        gbc.gridwidth = GridBagConstraints.RELATIVE;
        container.add(okBtn, gbc);
        
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        container.add(cancelBtn, gbc);
        
        add(container);
        
        pack();
        
        setVisible(true);
        
        
    }
    
    /**
     * Method to sign into the application
     * Precondition: User not signed in
     * Postcondition: If validated the user is signed into the application
     */
    private void signIn()
    {
        //Reset labels
        emailLbl.setText("E-Mail:");
        passwordLbl.setText("Password:");

        if(emailTxt.getText().equals(""))
        {
            //Username not populated
            emailLbl.setText("Please enter e-mail!");
            return;
        }

        if(passwordTxt.getText().equals(""))
        {
            //Password not populated
            passwordLbl.setText("Please enter password!");
            return;
        }
        
        //Query the user from the database
        User myUser = signInData.queryUserData(emailTxt.getText());

        if(myUser == null)
        {
            //User name not found
            emailLbl.setText("E-Mail not found, try again");
        }
        else
        {
            if(myUser.getPassword().equals(passwordTxt.getText()))
            {
                //If sign in is succesful return User to HeaderPanel
                //and close dialog
                HeaderPanel.closeSignInForm(myUser);
            }
            else
            {
                //Bad Password
                passwordLbl.setText("Incorrect Password, try again");
            }
        }
    }
        
}
