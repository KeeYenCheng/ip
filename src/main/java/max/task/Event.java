package max.task;
import max.data.TaskType;
import max.data.Status;
import max.maxexception.InvalidDateRangeException;
import max.maxexception.MaxException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Event extends Task {
    private LocalDate start;
    private LocalDate end;

    private static final DateTimeFormatter DISPLAY =
            DateTimeFormatter.ofPattern("MMM dd yyyy");


    public Event(String desc, LocalDate start, LocalDate end) throws MaxException {
        super(desc, TaskType.EVENT);
        validateDates(start, end);
        this.start = start;
        this.end = end;

    }

    public Event(String desc, Status status, LocalDate start, LocalDate end) throws MaxException {
        super(desc, TaskType.EVENT, status);
        validateDates(start, end);
        this.start = start;
        this.end = end;
    }

    private static void validateDates(LocalDate start, LocalDate end) throws MaxException {
        if (start == null || end == null) {
            throw new InvalidDateRangeException();
        }
        if (end.isBefore(start)) {
            throw new InvalidDateRangeException();
        }
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
                    + "(from: " + this.start.format(DISPLAY)    + " to: " + this.end.format(DISPLAY)    +")";
    }
    
}
