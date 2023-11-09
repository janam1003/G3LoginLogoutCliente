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

	// socket object
	private Socket skCliente;

	// inputStream object
	private ObjectInputStream inputStream = null;

	// outputStream object
	private ObjectOutputStream outputStream = null;

	// Load configuration properties from a file named "Config.config"
	private final ResourceBundle configFile = ResourceBundle.getBundle("Config.config");

	// Get the port number from the configuration file and store it as an Integer
	private final Integer PUERTO = Integer.parseInt(configFile.getString("PORT"));

	// Get the host name from the configuration file
	private final String HOST = configFile.getString("HOST");

	// Get the tiemout time value from the configuration file and store it as an
	// Integer
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
         * @throws ServerErrorException If an I/O error occurs during connection.
	 */
	public Message sendRecieve(Message mesg) throws IOException, ServerErrorException {

		try {

			// Logger
			LOGGER.info("Sending a message to a server to receive a response.");

			// Create a client socket connection to the specified host and port
			skCliente = new Socket();

			// Gives timeout if cant find the server
			skCliente.connect(new InetSocketAddress(HOST, PUERTO), TIMEOUT);

			// Get the output stream of the socket for sending data
			outputStream = new ObjectOutputStream(skCliente.getOutputStream());
			inputStream = new ObjectInputStream(skCliente.getInputStream());

			// Write the provided 'mesg' object to the output stream
			outputStream.writeObject(mesg);

			/**
			 * Receive a message from the server Get the input stream of the
			 * socket for receiving data
			 */
			msgReceive = (Message) inputStream.readObject();

			// Return the received message
			return msgReceive;
		} catch (SocketTimeoutException e) {

			LOGGER.severe("Error: Connection timeout when reaching the server." + e.getMessage());
			throw new ServerErrorException("Error at reaching the server.");

		} catch (Exception e) {

			LOGGER.severe("Error: " + e.getMessage());
			throw new ServerErrorException("Error " + e.getMessage());

		} finally {

			try {

				// Close the output stream if exists
				if (outputStream != null)
					outputStream.close();

				// Close the input stream if exists
				if (inputStream != null)
					inputStream.close();

				// Close the client socket if exists
				if (skCliente != null)
					skCliente.close();

			} catch (Exception e) {

				/**
				 * Handle any exceptions that may occur during the closing of
				 * streams or the socket.
				 */
				LOGGER.severe("Error: " + e.getMessage());

				throw new ServerErrorException("Error while closing the socket and streams. " + e.getMessage());
			}
		}
	}
}
