/**
 * Class: EComException
 * 
 * Purpose:
 * Exception class to handle specific application errors
 *  
 * Change Log:
 * Matt Folds 10/16/12 - Initial Development
 * 
 */

package ecommerce;


public class EComException extends Exception{
    
    /**
    * Constructor to throw exception
    * PreCondition: Calling method populates error message
    * Postcondition: Exception is thrown and should be caught
    * @param msg String which includes error detail
    */
    public EComException(String msg)
    {
        super(msg);
    }
    
    /**
    * Constructor to throw exception
    * PreCondition: Calling method populates error message
    * Postcondition: Exception is thrown and should be caught
    * @param msg String which includes error detail
    * @param t Detailed cause of the error
    */   
    public EComException(String msg, Throwable t)
    {
        super(msg, t);
    }

}
