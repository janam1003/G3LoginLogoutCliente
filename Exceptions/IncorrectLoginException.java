package Exceptions;

/**
 *
 * @author Janam
 */
public class IncorrectLoginException extends Exception {

    /**
     * Creates a new instance of <code>IncorrectLoginException</code> without
     * detail message.
     */
    public IncorrectLoginException() {
    }

    /**
     * Constructs an instance of <code>IncorrectLoginException</code> with the
     * specified detail message.
     *
     * @param message the detail message.
     */
    public IncorrectLoginException(String message) {
        super(message);
    }

}
