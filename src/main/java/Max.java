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
  

  public static void greeting() {
    System.out.println(banner);
    System.out.println(greetings);
    System.out.println(bars);
  }

  public static boolean echo(String response) {
     
    switch (response) {
      case "bye":
        System.out.println(bye);
        return true;
      default:
        System.out.println(tabSpace + response);
        System.out.println(bars);
        return false;
      }
    
  }



  public static void main(String[] args) {
    
    greeting();
    boolean exit = false;
   
    while(!exit) {
      Scanner scanner = new Scanner(System.in);
      String response = scanner.nextLine();
      System.out.println(bars); 
    
      exit = echo(response); 
    }
  }
}
