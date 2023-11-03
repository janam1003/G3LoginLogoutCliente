package Exceptions;

/**
 *
 * @author Janam
 */
public class ServerErrorException extends Exception {

    /**
     * Creates a new instance of <code>ServerErrorException</code> without
     * detail message.
     */
    public ServerErrorException() {
    }

    /**
     * Constructs an instance of <code>ServerErrorException</code> with the
     * specified detail message.
     *
     * @param message the detail message.
     */
    public ServerErrorException(String message) {
        super(message);
    }

}
