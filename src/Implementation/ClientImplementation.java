package Implementation;

import Classes.Message;
import Classes.MessageType;
import Classes.SigninSignup;
import Classes.User;
import Exceptions.EmailAlreadyExistException;
import Exceptions.IncorrectLoginException;
import Exceptions.MaxUserException;
import Exceptions.ServerErrorException;
import Exceptions.UnknownTypeException;
import Socket.ClientSocket;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * This class implements the SigninSignup interface and provides methods for
 * user sign-in and sign-up.
 *
 * @author Janam
 */
public class ClientImplementation implements SigninSignup {

	// A member variable to hold a Message object for communication with a server.
	private Message message = null;

	// A member variable to create a ClientSocket for network communication
	private final ClientSocket clientSocket = new ClientSocket();

	/**
	 * Logger object used to log messages for application.
	 */
	private static final Logger LOGGER = Logger.getLogger(ClientImplementation.class.getName());

	/**
	 * Attempts to sign-in a user.
	 *
	 * @param user The User object containing login credentials.
	 * @return The User object after a successful sign-in.
	 * @throws IncorrectLoginException if the login credentials are incorrect or
	 *                                 the user does not exist.
	 * @throws ServerErrorException    if an error occurs while reaching the
	 *                                 server.
	 * @throws UnknownTypeException    if an unknown message type is received.
	 * @throws MaxUserException        if the server cannot handle more users.
	 */
	@Override
	public User SignIn(User user)
			throws IncorrectLoginException, ServerErrorException, UnknownTypeException, MaxUserException {

		LOGGER.info("Attempting to signIn the user.");

		// Create a Message object and set its values
		message = new Message();

		message.setUser(user);

		message.setType(MessageType.LOGIN_REQUEST);

		// Send the message and receive a response
		Message returnMessage = clientSocket.sendRecieve(message);

		// Analyze the response message
		switch (returnMessage.getType()) {

			case SERVER_ERROR_RESPONSE:

				throw new ServerErrorException("Error at reaching the server.");

			case INCORRECT_LOGIN_RESPONSE:

				throw new IncorrectLoginException("Email or password is incorrect or user does not exist.");

			case MAX_USER_EXCEPTION:

				throw new MaxUserException("Server can not handle more users.");

			case OKAY_RESPONSE:

				user = returnMessage.getUser();

				break;

			default:

				throw new UnknownTypeException("Unknown type of message.");

		}

		return user;

	}

	/**
	 * Attempts to sign-up a new user.
	 *
	 * @param user The User object containing registration information.
	 * @return The User object after a successful sign-up.
	 * @throws ServerErrorException       if an error occurs while reaching the
	 *                                    server.
	 * @throws EmailAlreadyExistException if the email provided already exists.
	 * @throws UnknownTypeException       if an unknown message type is received.
	 * @throws MaxUserException           if the server cannot handle more users.
	 */
	@Override
	public User signUp(User user)
			throws ServerErrorException, EmailAlreadyExistException, UnknownTypeException, MaxUserException {

		LOGGER.info("Attempting to signUp a new user.");

		// Create a Message object and set its values
		message = new Message();

		message.setUser(user);

		message.setType(MessageType.SIGNUP_REQUEST);

		// Send the message and receive a response
		Message returnMessage = clientSocket.sendRecieve(message);

		// Analyze the response message
		switch (returnMessage.getType()) {

			case SERVER_ERROR_RESPONSE:

				throw new ServerErrorException("Error at reaching the server.");

			case EMAIL_ALREADY_EXIST_RESPONSE:

				throw new EmailAlreadyExistException("Email already exists.");

			case MAX_USER_EXCEPTION:

				throw new MaxUserException("Server can not handle more users.");

			case OKAY_RESPONSE:

				user = returnMessage.getUser();

				break;

			default:

				throw new UnknownTypeException("Unknown type of message.");

		}

		return user;

	}

}
