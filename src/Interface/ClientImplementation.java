package Interface;

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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Janam
 */
public class ClientImplementation implements SigninSignup {

    private Message message = null;

    private final ClientSocket clientSocket = new ClientSocket();

    @Override
    public User SignIn(User user) throws IncorrectLoginException, ServerErrorException, UnknownTypeException, MaxUserException {

        try {

            //Creo un mensaje y establezco valores 
            message = new Message();

            message.setUser(user);

            message.setType(MessageType.LOGIN_REQUEST);

            Message returnMessage = clientSocket.sendRecieve(message);

            //Analizar el mensaje de resupuesta
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

        } catch (IOException ex) {

            Logger.getLogger(ClientImplementation.class.getName()).log(Level.SEVERE, null, ex);

        }

        return user;
    }

    @Override
    public User signUp(User user) throws ServerErrorException, EmailAlreadyExistException, UnknownTypeException, MaxUserException {

        try {

            //Creo un mensaje y establezco valores 
            message = new Message();

            message.setUser(user);

            message.setType(MessageType.SIGNUP_REQUEST);

            Message returnMessage = clientSocket.sendRecieve(message);

            //Analizar el mensaje de resupuesta
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

        } catch (IOException ex) {
            
            Logger.getLogger(ClientImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return user;
    }

}
