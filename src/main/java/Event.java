
public class Event extends Task {
  private String start;
  private String end;

  Event(String desc, String start, String end) {
    super(desc, TaskType.EVENT);
    this.start = start;
    this.end = end;
    System.out.println(Max.tabSpace + "Task added:\n" + 
                       Max.tabSpace + this);
    Task.printNumberOfTask();
  }

  @Override
  public String toString() {
    return super.toString()
          + "(from: " + this.start + "to: " + this.end +")";
  }
  
}
