package max.command;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import max.maxexception.EmptyDescriptionException;
import max.maxexception.InvalidTaskIDException;
import max.maxexception.MaxException;
import max.maxexception.MissingDatesException;


public class Parser {

  /**
   * Get the command from a given String.
   *
   * @param input String of the input where command is splice from.
   * @return String the command.
   *
   * @example
   * ```
   * Parser.getCommandWord("todo read book")
   * returns todo
   * ```
   */

  public static String getCommandWord(String input) {
    return input.split(" ", 2)[0];
  }

  /**
   * Get arguments base on the given input.
   *
   * @param input string where the arguments are retrieved from  
   * @return String arguments of the string 
   *
   */

  public static String getArguments(String input) throws EmptyDescriptionException {
    String[] parts = input.split(" ", 2);
    if (parts.length < 2) {
      throw new EmptyDescriptionException();
    }
    return parts[1];
  }

  /**
   * A one-line summary.
   *
   * Description.
   *
   * @param name  Type and description of the parameter.
   * @return Type and description of the returned object.
   *
   * @example
   * ```
   * Write me later
   * ```
   */

  public static String[] parseDeadline(String args) throws MissingDatesException {
    String[] ddl = args.split("/by ");
    if (ddl.length < 2) {
      throw new MissingDatesException();
    }
    return ddl;
  }

  /**
   * Split the arguments of event command into relevant segments.
   *
   * @param args arguments to be split  
   * @return String array of the arguments.
   *
   */
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

  /**
   * Convert string to integer.
   *
   * @param args string to be connverted to int .
   * @return int representation of string.
   *
   */
  public static int parseIndex(String str) throws InvalidTaskIDException {
    try {
      return Integer.parseInt(str.trim());
    } catch (NumberFormatException e) {
      throw new InvalidTaskIDException();
    }
  }


  /**
   * Convert date representation from string to LocalDate
   *
   * @param dateStr  string representation of date 
   * @return LocalDate LocalDate representation of date string 
   *
   */
  public static LocalDate parseDate(String dateStr) throws MaxException {
    try {
      return LocalDate.parse(dateStr.trim());
    } catch (DateTimeParseException e) {
      throw new MaxException("InvalidTaskIDException");
    }
  }
}
