package max.task;

import max.data.Status;
import max.data.TaskType;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate date;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");

    public Deadline(String desc, LocalDate date) {
        super(desc, TaskType.DEADLINE);
        assert date != null : "A deadline must have a date";
        this.date = date;
    }
    public Deadline(String desc, Status status, LocalDate date) {
        super(desc, TaskType.DEADLINE, status);
        assert date != null : "A deadline must have a date";
        this.date = date;
    }

    /** Returns the date by which this task should be completed. */
    public LocalDate getDate() {
        return date;
    }
    
    @Override
    public boolean isOn(LocalDate date) {
        return this.date.equals(date);
    }

    @Override
    public String getItemString() {
        
        return super.getItemString() + " | " + this.date;
    }
    @Override
    public String toString() {
        return  super.toString() + "(by: " + this.date.format(formatter) + ")";
    }
}
