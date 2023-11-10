package Factory;

import Classes.SigninSignup;
import Implementation.ClientImplementation;
import java.util.logging.Logger;

/**
 * The `ClientFactory` class is responsible for creating and providing instances
 * of the `SigninSignup` interface.
 *
 * @author Janam
 */
public class ClientFactory {

    // Static variable to store the SigninSignup instance.
    private static SigninSignup data;

    /**
     * Logger object used to log messages for the application.
     */
    private static final Logger LOGGER = Logger.getLogger(ClientFactory.class.getName());

    /**
     * Get an instance of the SigninSignup interface. If an instance does not
     * exist, it is created.
     *
     * @return An instance of the SigninSignup interface.
     */
    public static SigninSignup getSigninSignup() {

        // Logger
        LOGGER.info("Initializing ClientFactory.");

        // Check if the data instance is not previously loaded.
        if (data == null) {

            /**
             * If not loaded, create a new instance of SigninSignup using the
             * ClientImplementation class.
             */
            data = (SigninSignup) new ClientImplementation();

        }

        // Return the SigninSignup instance.
        return data;
    }

}
