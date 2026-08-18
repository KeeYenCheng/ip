public class Deadline extends Task {
  private String date;
  
  Deadline(String desc, String date) {
    super(desc, TaskType.DEADLINE);
    this.date = date;
    System.out.println(Max.tabSpace + "Task added:\n" + 
                       Max.tabSpace + this);
    Task.printNumberOfTask();
  }

  @Override
  public String toString() {
    return  super.toString() + "(by: " + this.date + ")";
  }
}
