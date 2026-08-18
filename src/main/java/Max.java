import java.util.Scanner;

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

  public static boolean echo(String response) {
    
    String[] args = response.split(" ", 2);
       
    switch (args[0]) {
      case "bye":
        System.out.println(bye);
        return false;
      case "list":
        Task.printAllTask();
        return true;
      case "todo":       
        Todo newTodo = new Todo(args[1]);
        return true;
      case "deadline":
        String[] ddl = (args[1]).split("/by ");
        Deadline deadline = new Deadline(ddl[0], ddl[1]);
        return true;
      case "event":
        String[] evt = (args[1]).split("/from ");
        String[] start_end = evt[1].split("/to ");
        Event event = new Event(evt[0], start_end[0], start_end[1]);
        return true;
      case "mark":
        if (args.length < 2) {
          System.out.println("please provide task id");
        }
        Task.setTaskDone(Integer.parseInt(args[1]));
        return true;
      case "unmark":
        if (args.length < 2) {
          System.out.println("please provide task id");
        }
        Task.setTaskNotDone(Integer.parseInt(args[1]));
        return true; 
      default:
        Task newTask = new Task(response);
        System.out.println(tabSpace + " added: " + response);
        return true;
      }
  }


  public static void main(String[] args) {
    
    greeting();
    boolean repeat = true;
   
    while(repeat) {
      Scanner scanner = new Scanner(System.in);
      String response = scanner.nextLine();
      System.out.println(bars); 
    
      repeat = echo(response);
      System.out.println(bars); 
    
    }
    
  }
}
