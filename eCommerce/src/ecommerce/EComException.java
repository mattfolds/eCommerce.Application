/*
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
    
    public EComException(String msg)
    {
        super(msg);
    }
    
    public EComException(String msg, Throwable t)
    {
        super(msg, t);
    }

}
