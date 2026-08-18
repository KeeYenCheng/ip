public class Deadline extends Task {
  private String date;
  
  Deadline(String desc, String date) {
    super(desc);
    this.date = date;
    System.out.println(Max.tabSpace + "Task added:\n" + 
                       Max.tabSpace + this);
    Task.printNumberOfTask();
  }

  @Override
  public String toString() {
    return "[D]" + super.toString() + "(by: " + this.date + ")";
  }
}
