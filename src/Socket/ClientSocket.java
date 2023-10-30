package Socket;

import Classes.Message;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ResourceBundle;
import java.util.logging.Level;
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

    // Create an instance of the Message class to store received messages
    private Message msgReceive = new Message();

    /**
     * Sends a message to a server and receives a response.
     *
     * @param mesg The message to be sent to the server.
     * @return The received message from the server.
     * @throws IOException If an I/O error occurs during socket communication.
     */
    public Message sendRecieve(Message mesg) throws IOException {

        try {

            // Create a client socket connection to the specified host and port
            Socket skCliente = new Socket(HOST, PUERTO);

            // Get the output stream of the socket for sending data
            OutputStream os = skCliente.getOutputStream();

            // Create an ObjectOutputStream to write objects to the output stream
            ObjectOutputStream oos = new ObjectOutputStream(os);

            // Write the provided 'mesg' object to the output stream
            oos.writeObject(mesg);

            /**
             * Receive a message from the server Get the input stream of the
             * socket for receiving data
             */
            InputStream is = skCliente.getInputStream();

            // Create an ObjectInputStream to read objects from the input stream
            ObjectInputStream ois = new ObjectInputStream(is);

            // Read an object from the input stream and cast it to a Message object
            msgReceive = (Message) ois.readObject();

            // Return the received message 
            return msgReceive;

        } catch (IOException | ClassNotFoundException e) {

            // Handle exceptions by logging the error
            Logger.getLogger(ClientSocket.class.getName()).log(Level.SEVERE, null, e);

        }

        return msgReceive;
    }
}
