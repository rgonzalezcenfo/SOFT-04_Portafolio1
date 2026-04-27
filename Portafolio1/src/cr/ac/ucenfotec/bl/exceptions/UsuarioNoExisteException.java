package cr.ac.ucenfotec.bl.exceptions;

public class UsuarioNoExisteException extends RuntimeException {
    public UsuarioNoExisteException(String message) {
        super(message);
    }
}
