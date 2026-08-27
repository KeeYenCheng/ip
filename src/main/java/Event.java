
public class Event extends Task {
  private String start;
  private String end;

  Event(String desc, String start, String end) {
    super(desc, TaskType.EVENT);
    this.start = start;
    this.end = end;

  }

  Event(String desc, Status status, String start, String end) {
    super(desc, TaskType.EVENT, status);
    this.start = start;
    this.end = end;
  }
  
  @Override
  public String getItemString() {
    return super.getItemString() + " | " + this.start + " | " + this.end;
  }

  @Override
  public String toString() {
    return super.toString()
          + "(from: " + this.start + "to: " + this.end +")";
  }
  
}
