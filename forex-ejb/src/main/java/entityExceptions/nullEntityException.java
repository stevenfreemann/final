/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entityExceptions;

/**
 *
 * @author steve
 */
public class nullEntityException extends Exception{
     public nullEntityException(String message, Throwable cause) {
        super(message, cause);
    }
    public nullEntityException (String message) {
        super(message);
    }
    
}
