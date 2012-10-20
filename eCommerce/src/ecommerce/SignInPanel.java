/*
 * Class: NavigationPanel
 * 
 * Purpose:
 * Creates the signin panel which displays the form for sign-in
 * 
 * Change Log:
 * Matt Folds 10/18/12 - Initial Development
 * 
 */
package ecommerce;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SignInPanel extends JFrame
{
    
    private JLabel userNameLbl;
    private JLabel passwordLbl;
    
    private JTextField userNameTxt;
    private JTextField passwordTxt;
    
    private JButton okBtn;
    private JButton cancelBtn;
    
    public SignInPanel()
    {
        setTitle("Sign-In");
        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JPanel container = new JPanel();
        
        container.setLayout(new GridBagLayout());
        
        userNameLbl = new JLabel("User Name:");
        passwordLbl = new JLabel("Password:");
        
        userNameTxt = new JTextField("", 20);
        passwordTxt = new JTextField("", 20);
        
        okBtn = new JButton("OK");
        cancelBtn = new JButton("Cancel");
        
        okBtn.addActionListener(new SignInPanel.okBtnListener());
        cancelBtn.addActionListener(new SignInPanel.cancelBtnListener());
        
        //Configure layout control
        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.insets = new Insets(2,2,2,2);
        
        container.add(userNameLbl, gbc);
        container.add(userNameTxt, gbc);
        
        container.add(passwordLbl, gbc);
        container.add(passwordTxt, gbc);
        
        container.add(okBtn, gbc);
        container.add(cancelBtn, gbc);
        
        add(container);
        
        pack();
        
        setVisible(true);
        
        
    }
    
    private class okBtnListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            
        }
    }
    
    private class cancelBtnListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            
        }
    }
    
}
