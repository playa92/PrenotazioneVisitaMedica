package persistence;

@SuppressWarnings("serial")
public class PersistenceException extends Exception {

    public PersistenceException(String message) {
        super(message);
    }
    
    public PersistenceException(Throwable cause) {
        super(cause);
    }

    public PersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}
