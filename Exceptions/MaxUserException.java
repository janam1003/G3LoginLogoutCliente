package Exceptions;

/**
 *
 * @author Janam
 */
public class MaxUserException extends Exception {

    /**
     * Creates a new instance of <code>MaxUserException</code> without detail
     * message.
     */
    public MaxUserException() {
    }

    /**
     * Constructs an instance of <code>MaxUserException</code> with the
     * specified detail message.
     *
     * @param message the detail message.
     */
    public MaxUserException(String message) {
        super(message);
    }

}
