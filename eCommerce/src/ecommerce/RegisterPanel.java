/**
 * Class: RegisterPanel
 * 
 * Purpose:
 * Creates the sign in panel which displays the form for sign-in
 * 
 * Change Log:
 * Matt Folds 10/20/12 - Initial Development
 * 
 */
package ecommerce;

import java.awt.*;
import java.awt.event.*;
import javax.mail.internet.*;
import javax.swing.*;

public class RegisterPanel extends JFrame
{
    private JLabel emailLbl;
    private JLabel passwordLbl;
    private JLabel password2Lbl;
    private JLabel passwordHintLbl;
    private JLabel fNameLbl;
    private JLabel lNameLbl;
    
    private JLabel title;
    
    private JTextField emailTxt;
    private JTextField passwordTxt;
    private JTextField password2Txt;
    private JTextField passwordHintTxt;
    private JTextField fNameTxt;
    private JTextField lNameTxt;
    
    private JButton okBtn;
    private JButton cancelBtn;
    
    private EComData registerData = ECommerce.myData;
    private User newUser;
    
    /**
     * Constructor for building the registration form.
     */
    public RegisterPanel()
    {        
        //Set window title
        setTitle("Register New User");
        
        //Set close operation for X button
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        //Create panel to hold controls
        JPanel container = new JPanel();
        
        GridBagLayout gb = new GridBagLayout();
        
        //Set layout of panel
        container.setLayout(gb);
        
        //Create controls
        title = new JLabel("User Registration Form");
        
        emailLbl = new JLabel("E-Mail:");
        passwordLbl = new JLabel("Password:");
        password2Lbl = new JLabel("Confirm Password:");
        passwordHintLbl = new JLabel("Password Hint:");
        fNameLbl = new JLabel("First Name:");
        lNameLbl = new JLabel("Last Name:");

        emailTxt = new JTextField("",20);
        passwordTxt = new JTextField("",20);
        password2Txt = new JTextField("",20);
        passwordHintTxt = new JTextField("",20);
        fNameTxt = new JTextField("",20);
        lNameTxt = new JTextField("",20);
        
        okBtn = new JButton("OK");
        cancelBtn = new JButton("Cancel");
        
        //Handle okBtn click
        okBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                register();
            }
        });
        
        //Handle cancle button click
        cancelBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                HeaderPanel.closeRegisterForm();
            }
        });
        
        //Configure layout control
        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(10,10,10,10);
        
        container.add(title, gbc);        
        
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridwidth = GridBagConstraints.RELATIVE;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.insets = new Insets(5,2,5,2);
        
        gbc.gridwidth = GridBagConstraints.RELATIVE;
        container.add(emailLbl, gbc);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        container.add(emailTxt, gbc);
        
        gbc.gridwidth = GridBagConstraints.RELATIVE;
        container.add(passwordLbl, gbc);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        container.add(passwordTxt, gbc);
        
        gbc.gridwidth = GridBagConstraints.RELATIVE;
        container.add(password2Lbl, gbc);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        container.add(password2Txt, gbc);
        
        gbc.gridwidth = GridBagConstraints.RELATIVE;
        container.add(passwordHintLbl, gbc);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        container.add(passwordHintTxt, gbc);
        
        gbc.gridwidth = GridBagConstraints.RELATIVE;
        container.add(fNameLbl, gbc);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        container.add(fNameTxt, gbc);
        
        gbc.gridwidth = GridBagConstraints.RELATIVE;
        container.add(lNameLbl, gbc);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        container.add(lNameTxt, gbc);
        
        gbc.gridwidth = GridBagConstraints.RELATIVE;
        container.add(okBtn, gbc);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        container.add(cancelBtn, gbc);
        
        add(container);
        
        pack();
        
        setVisible(true);
    }
    
    /**
     * Method to register the new user when the OK button is clicked
     * PreCondition: User is not registered
     * PostCondition: User is registered, if fails specific error is prompted to
     * the user.
     */
    private void register()
    {
        newUser = new User();
        
        if(emailTxt.getText().equals("") || passwordTxt.getText().equals("") ||
                password2Txt.getText().equals("") || 
                passwordHintTxt.getText().equals("") || 
                fNameTxt.getText().equals("") ||
                lNameTxt.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null, "Please fill in all fields"
                    + " before submiting the form", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            if(validateEmail(emailTxt.getText()))
            {
                newUser.setEmail(emailTxt.getText());
            }
            else
            {
                JOptionPane.showMessageDialog(null, "The email address is not"
                        + "valid, Please re-enter", "Error",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            if(passwordTxt.getText().equals(password2Txt.getText()))
            {
                newUser.setPassword(passwordTxt.getText());
            }
            else
            {
                JOptionPane.showMessageDialog(null, "The passwords do not match"
                        , "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            newUser.setPasswordHint(passwordHintTxt.getText());
            newUser.setFName(fNameTxt.getText());
            newUser.setLName(lNameTxt.getText());
            
            try
            {
                registerData.writeRecord(newUser, "user");
                
                HeaderPanel.closeRegisterForm(newUser);
            }
            catch(EComException e)
            {
                JOptionPane.showMessageDialog(null, e.getMessage()
                        , "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        
    }
    
    /**
     * Method to validate the users e-mail meets specific criteria
     * PreCondition: Need to validate an e-mail address for correctness
     * PostCondition: Validation complete and results returned in boolean
     * @param email String of the e-mail to validate
     * @return True if the e-mail is valid false if not
     */
    public boolean validateEmail(String email)
    {
        boolean result = true;
        try {
           InternetAddress emailAddr = new InternetAddress(email);
           emailAddr.validate();
        } catch (AddressException ex) {
           result = false;
        }
        return result;
    }
}
