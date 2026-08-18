package MaxExceptions;

public class InvalidTaskIDException extends MaxException {
    public InvalidTaskIDException() {
        super("Task ID is invalid, must be a number");
    }
}
