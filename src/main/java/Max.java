import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import MaxExceptions.EmptyDescriptionException;
import MaxExceptions.InvalidTaskIDException;
import MaxExceptions.MaxException;
import MaxExceptions.MissingDatesException;
import MaxExceptions.UnknownCommandException;

public class Max {
  public static String tabSpace = "    ";
  private static String banner = "  _____ _____  ___  ___\n" 
                 + " /     \\\\__  \\ \\  \\/  /\n" 
                 + "|  Y Y  \\/ __ \\_>    < \n"
                 + "|__|_|  (____  /__/\\_ \\\n" 
                 + "      \\/     \\/      \\/\n";
  private static String greetings = tabSpace + "Sup! I'm Max.\n"
                     + tabSpace + "What do you need?";
  private static String bars = tabSpace + "________________________________________";
  private static String bye = tabSpace + "See ya later!\n";


  /**
   * Print greetings
   */
  public static void greeting() {
    System.out.println(banner);
    System.out.println(greetings);
    System.out.println(bars);
  }

  /**
   * Echo string provided 
   *
   * @param response the string to be echoed.
   * @return True if successfully echoed string, false otherwise
   *
   */
  
  //
  public static boolean echo(String response) throws MaxException {
    
    String[] args = response.split(" ", 2);
       
    switch (args[0]) {
      case "bye":
        System.out.println(bye);
        return false;
      case "list":
        Task.printAllTask();
        return true;
      case "todo":
        if (args.length < 2) {
          throw new EmptyDescriptionException();
        }
        Todo newTodo = new Todo(args[1]);
        System.out.println(Max.tabSpace + "Task added:\n" + 
                       Max.tabSpace + newTodo);
        return true;
      case "deadline":
        if (args.length < 2) {
          throw new EmptyDescriptionException();
        }
        String[] ddl = (args[1]).split("/by ");
        if (ddl.length < 2) {
          throw new MissingDatesException();
        }
        Deadline deadline = new Deadline(ddl[0], ddl[1]);
        System.out.println(Max.tabSpace + "Task added:\n" + 
                       Max.tabSpace + deadline);
        return true;
      case "event":
        if (args.length < 2) {
          throw new EmptyDescriptionException();
        }
        String[] evt = (args[1]).split("/from ");
        if (evt.length < 2) {
          throw new MissingDatesException();
        }
        String[] start_end = evt[1].split("/to ");
        if (start_end.length < 2) {
          throw new MissingDatesException();
        }
        Event event = new Event(evt[0], start_end[0], start_end[1]);
        System.out.println(Max.tabSpace + "Task added:\n" + 
                       Max.tabSpace + event);
        return true;
      case "mark":
        if (args.length < 2) {
          throw new InvalidTaskIDException();
        }
        try {
          Task.setTaskDone(Integer.parseInt(args[1])); 
        } catch (NumberFormatException e) {
          throw new InvalidTaskIDException();
        }
        return true;
      case "unmark":
        if (args.length < 2) {
          throw new InvalidTaskIDException();
        }
        try {
          Task.setTaskNotDone(Integer.parseInt(args[1]));
        } catch (NumberFormatException e) {
          throw new InvalidTaskIDException();
        }
        return true;
      case "delete":
        if (args.length < 2) {
          throw new InvalidTaskIDException();
        }
        try {
          Task.deleteTask(Integer.parseInt(args[1]));
        } catch (NumberFormatException e) {
          throw new InvalidTaskIDException();
        }
        return true;

      default:
        throw new UnknownCommandException();
      }
  }


  public static void main(String[] args) {
    greeting();
    boolean repeat = true;
    try {
      Task.loadList();
    } catch(FileNotFoundException e) {

    }
    Scanner scanner = new Scanner(System.in);

    while(repeat) {
      String response = scanner.nextLine();
      System.out.println(bars); 
    
      try {
        repeat = echo(response);
        Task.printNumberOfTask();
      } catch (MaxException e) {
        System.out.println(tabSpace + "Error: " + e.getMessage());
        System.out.println(bars);
        continue;
      }
      System.out.println(bars); 
      try {
        Task.saveList();
      } catch (IOException e) {
        System.out.println(e); 
      }
    }
    
  }
}
