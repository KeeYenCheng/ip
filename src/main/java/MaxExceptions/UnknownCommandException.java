package MaxExceptions;
public class UnknownCommandException extends MaxException {
    public UnknownCommandException() {
        super("Don't know what you mean >:(");
    }
}
