package Socket;

import Classes.Message;
import Exceptions.ServerErrorException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ResourceBundle;
import java.util.logging.Logger;

/**
 * The ClientSocket class provides a client-side socket communication mechanism
 * for sending and receiving messages.
 *
 * @author Janam
 */
public class ClientSocket {

    // Load configuration properties from a file named "Config.config"
    private final ResourceBundle configFile = ResourceBundle.getBundle("Config.config");

    // Get the port number from the configuration file and store it as an Integer
    private final Integer PUERTO = Integer.parseInt(configFile.getString("PORT"));

    // Get the host name from the configuration file
    private final String HOST = configFile.getString("HOST");

    // Get the tiemout time value from the configuration file and store it as an Integer
    private final Integer TIMEOUT = Integer.parseInt(configFile.getString("TIMEOUT"));

    // Create an instance of the Message class to store received messages
    private Message msgReceive = new Message();

    // Logger object used to log messages for application.
    private static final Logger LOGGER = Logger.getLogger(ClientSocket.class.getName());

    /**
     * Sends a message to a server and receives a response.
     *
     * @param mesg The message to be sent to the server.
     * @return The received message from the server.
     * @throws IOException If an I/O error occurs during socket communication.
     */
    public Message sendRecieve(Message mesg) throws IOException, ServerErrorException {

        try {

            LOGGER.info("Sending a message to a server to receive a response.");

            // Create a client socket connection to the specified host and port
            Socket skCliente = new Socket();

            // Gives timeout if cant find the server
            skCliente.connect(new InetSocketAddress(HOST, PUERTO), TIMEOUT);

            // Get the output stream of the socket for sending data
            ObjectOutputStream os = new ObjectOutputStream(skCliente.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(skCliente.getInputStream());

            // Write the provided 'mesg' object to the output stream
            os.writeObject(mesg);

            /**
             * Receive a message from the server Get the input stream of the
             * socket for receiving data
             */
            msgReceive = (Message) ois.readObject();

            // Return the received message 
            return msgReceive;

        } catch (SocketTimeoutException e) {

            LOGGER.severe("Error: Connection timeout when reaching the server." + e.getMessage());

            throw new ServerErrorException("Error at reaching the server.");

        } catch (IOException | ClassNotFoundException e) {

            LOGGER.severe("Error: " + e.getMessage());

        }

        return msgReceive;
    }
}
