/**
 * Class: User
 * 
 * Purpose:
 * The purpose of this class is to hold the structure of the 
 * user record and provide methods for retrieving specific elements
 * of the record
 * 
 * Change Log:
 * Matt Folds 10/16/12 - Initial Development
 */
package ecommerce;

import java.util.Objects;


public class User {
    
    private String email;
    private String password;
    private String passwordHint;
    private String fName;
    private String lName;
    private String admin;
    
    public User()
    {
        
    }
    
    /**
     * Constructor which accepts a string array and assigns the elements of the
     * array to specific Class fields
     * PreCondition: object does not exist
     * PostCondition: new object created based on input
     * @param record String array used to build the object
     */
    public User(String[] record)
    {
        email = record[0];
        password = record[1];
        passwordHint = record[2];
        fName = record[3];
        lName = record[4];
        admin = record[5];
    }
    
    public String getEmail()
    {
        return email;
    }
    
    public String getPassword()
    {
        return password;
    }
    
    public String getPasswordHint()
    {
        return passwordHint;
    }
    
    public String getFName()
    {
        return fName;
    }
    
    public String getLName()
    {
        return lName;
    }
    
    public String getAdmin()
    {
        return admin;
    }
    
    public void setEmail(String myEmail)
    {
        email = myEmail;
    }
    
    public void setPassword(String myPassword)
    {
        password = myPassword;
    }
    
    public void setPasswordHint(String myPasswordHint)
    {
        passwordHint = myPasswordHint;
    }
    
    public void setFName(String myFName)
    {
        fName = myFName;
    }
    
    public void setLName(String myLName)
    {
        lName = myLName;
    }
    
    public void setAdmin(String myAdmin)
    {
        admin = myAdmin;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.email);
        hash = 17 * hash + Objects.hashCode(this.password);
        hash = 17 * hash + Objects.hashCode(this.passwordHint);
        hash = 17 * hash + Objects.hashCode(this.fName);
        hash = 17 * hash + Objects.hashCode(this.lName);
        hash = 17 * hash + Objects.hashCode(this.admin);
        return hash;
    }
    
    @Override
    public boolean equals(Object obj)
    {
        User compare = (User)obj;
        
        if(this == obj)
        {
            return true;
        }
        else if(this.hashCode() == compare.hashCode())
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public String toCSVString()
    {
        return email + "," + password + "," + passwordHint + "," + 
                fName + "," + lName + "," + admin;
    }
    
}
