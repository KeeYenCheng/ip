package max.maxexception;

/** Indicates that a date does not use the supported ISO-8601 format. */
public class InvalidDateException extends MaxException {
    public InvalidDateException() {
        super("Invalid date. Use YYYY-MM-DD.");
    }
}
