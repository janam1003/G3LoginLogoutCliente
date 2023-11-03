package Exceptions;

/**
 *
 * @author Janam
 */
public class UnknownTypeException extends Exception {

    /**
     * Creates a new instance of <code>UnknownTypeException</code> without
     * detail message.
     */
    public UnknownTypeException() {
    }

    /**
     * Constructs an instance of <code>UnknownTypeException</code> with the
     * specified detail message.
     *
     * @param message the detail message.
     */
    public UnknownTypeException(String message) {
        super(message);
    }

}
