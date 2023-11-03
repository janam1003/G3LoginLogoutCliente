package Exceptions;

/**
 *
 * @author Janam
 */
public class EmailAlreadyExistException extends Exception {

    /**
     * Creates a new instance of <code>EmailAlreadyExistException</code> without
     * detail message.
     */
    public EmailAlreadyExistException() {
    }

    /**
     * Constructs an instance of <code>EmailAlreadyExistException</code> with
     * the specified detail message.
     *
     * @param message the detail message.
     */
    public EmailAlreadyExistException(String message) {
        super(message);
    }

}
