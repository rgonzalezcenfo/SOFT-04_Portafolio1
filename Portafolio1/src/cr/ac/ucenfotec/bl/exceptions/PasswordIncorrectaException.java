package cr.ac.ucenfotec.bl.exceptions;

public class PasswordIncorrectaException extends RuntimeException {
    public PasswordIncorrectaException(String message) {
        super(message);
    }
}
