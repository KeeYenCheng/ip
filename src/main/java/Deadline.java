import java.io.File;

public class Deadline extends Task {
  private String date;
  
  Deadline(String desc, String date) {
    super(desc, TaskType.DEADLINE);
    this.date = date;
  }
  Deadline(String desc, Status status, String date) {
    super(desc, TaskType.DEADLINE, status);
    this.date = date;
  }
  @Override
  public String getItemString() {
    return super.getItemString() + " | " + this.date;
  }
  @Override
  public String toString() {
    return  super.toString() + "(by: " + this.date + ")";
  }
}
