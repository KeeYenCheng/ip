import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import MaxExceptions.EmptyDescriptionException;
import MaxExceptions.InvalidTaskIDException;
import MaxExceptions.MaxException;
import MaxExceptions.MissingDatesException;

public class Parser {

  public static String getCommandWord(String input) {
    return input.split(" ", 2)[0];
  }

  public static String getArguments(String input) throws EmptyDescriptionException {
    String[] parts = input.split(" ", 2);
    if (parts.length < 2) {
      throw new EmptyDescriptionException();
    }
    return parts[1];
  }

  public static String[] parseDeadline(String args) throws MissingDatesException {
    String[] ddl = args.split("/by ");
    if (ddl.length < 2) {
      throw new MissingDatesException();
    }
    return ddl;
  }

  public static String[] parseEvent(String args) throws MissingDatesException {
    String[] evt = args.split("/from ");
    if (evt.length < 2) {
      throw new MissingDatesException();
    }
    String[] startEnd = evt[1].split(" /to ");
    if (startEnd.length < 2) {
      throw new MissingDatesException();
    }
    return new String[] {evt[0], startEnd[0], startEnd[1]};
  }

  public static int parseIndex(String args) throws InvalidTaskIDException {
    try {
      return Integer.parseInt(args.trim());
    } catch (NumberFormatException e) {
      throw new InvalidTaskIDException();
    }
  }

  public static LocalDate parseDate(String dateStr) throws MaxException {
    try {
      return LocalDate.parse(dateStr.trim());
    } catch (DateTimeParseException e) {
      throw new MaxException("InvalidTaskIDException");
    }
  }
}
