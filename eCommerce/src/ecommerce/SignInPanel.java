/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecommerce;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Matt
 */
public class SignInPanel extends javax.swing.JPanel {

    /**
     * Creates new form SignInPanel
     */
    
    private EComData signInData;
    
    public SignInPanel() {
        initComponents();

        signInData = ECommerce.myData;
        
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
    
    public void clearForm()
    {
        emailLbl.setText("Email:");
        emailTxt.setText("");
        passwordLbl.setText("Password:");
        passwordTxt.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titleLbl = new javax.swing.JLabel();
        emailLbl = new javax.swing.JLabel();
        passwordLbl = new javax.swing.JLabel();
        emailTxt = new javax.swing.JTextField();
        passwordTxt = new javax.swing.JPasswordField();
        cancelBtn = new javax.swing.JButton();
        okBtn = new javax.swing.JButton();

        titleLbl.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        titleLbl.setText("User Sign-In");

        emailLbl.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        emailLbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        emailLbl.setText("Email:");

        passwordLbl.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        passwordLbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        passwordLbl.setText("Password:");

        emailTxt.setColumns(20);
        emailTxt.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        passwordTxt.setColumns(20);
        passwordTxt.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        passwordTxt.setToolTipText("");

        cancelBtn.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cancelBtn.setText("Cancel");

        okBtn.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        okBtn.setText("OK");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(emailLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(passwordLbl, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(passwordTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(emailTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(okBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cancelBtn)))
                .addContainerGap(68, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(titleLbl)
                .addGap(160, 160, 160))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleLbl)
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(emailLbl)
                    .addComponent(emailTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordLbl)
                    .addComponent(passwordTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(okBtn)
                    .addComponent(cancelBtn))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        titleLbl.getAccessibleContext().setAccessibleName("");
        titleLbl.getAccessibleContext().setAccessibleDescription("");
        emailLbl.getAccessibleContext().setAccessibleName("");
        emailLbl.getAccessibleContext().setAccessibleDescription("");
        passwordLbl.getAccessibleContext().setAccessibleName("");
        passwordLbl.getAccessibleContext().setAccessibleDescription("");
        emailTxt.getAccessibleContext().setAccessibleName("");
        passwordTxt.getAccessibleContext().setAccessibleName("");
        passwordTxt.getAccessibleContext().setAccessibleDescription("");
        cancelBtn.getAccessibleContext().setAccessibleName("");
        cancelBtn.getAccessibleContext().setAccessibleDescription("");
        okBtn.getAccessibleContext().setAccessibleName("");
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelBtn;
    private javax.swing.JLabel emailLbl;
    private javax.swing.JTextField emailTxt;
    private javax.swing.JButton okBtn;
    private javax.swing.JLabel passwordLbl;
    private javax.swing.JPasswordField passwordTxt;
    private javax.swing.JLabel titleLbl;
    // End of variables declaration//GEN-END:variables
}
