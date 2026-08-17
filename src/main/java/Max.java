import java.util.Scanner;

public class Max {
  private static String tabSpace = "    ";
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
     
    switch (response) {
      case "bye":
        System.out.println(bye);
        return false;
      case "list":
        Task.printAllTask();
        System.out.println(bars);
        return true;
      default:
        Task newTask = new Task(response);
        System.out.println(tabSpace + " added: " + response);
        System.out.println(bars);
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
    }
    
  }
}
