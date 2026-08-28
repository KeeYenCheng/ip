package max.task;
import max.data.TaskType;
import max.data.Status;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Event extends Task {
  private LocalDate start;
  private LocalDate end;

  private static final DateTimeFormatter DISPLAY =
      DateTimeFormatter.ofPattern("MMM dd yyyy");


  public Event(String desc, LocalDate start, LocalDate end) {
    super(desc, TaskType.EVENT);
    this.start = start;
    this.end = end;

  }

  public Event(String desc, Status status, LocalDate start, LocalDate end) {
    super(desc, TaskType.EVENT, status);
    this.start = start;
    this.end = end;
  }

  @Override
  public boolean isOn(LocalDate date) {
    return !date.isBefore(start) && !date.isAfter(end);
  }
  
  @Override
  public String getItemString() {
    return super.getItemString() + " | " + this.start + " | " + this.end;
  }

  @Override
  public String toString() {
    return super.toString()
          + "(from: " + this.start.format(DISPLAY)  + " to: " + this.end.format(DISPLAY)  +")";
  }
  
}
