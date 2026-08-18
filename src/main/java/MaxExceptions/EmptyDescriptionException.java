package MaxExceptions;

public class EmptyDescriptionException extends MaxException {
    public EmptyDescriptionException() {
        super("Empty prompt");
    }
}
