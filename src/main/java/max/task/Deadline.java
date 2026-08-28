package max.task;

import java.io.File;
import max.data.Status;
import max.data.TaskType;
import java.time.LocalDate;
import java.time.LocalDateTime;
//TODO:
//1.Convert date from String type to Date time 
//2.Store as yyyy-mm-dd and print as MMM dd yyyy 
//3.Allow ability for the bot to display event and deadline base on user input
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate date;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");

    public Deadline(String desc, LocalDate date) {
        super(desc, TaskType.DEADLINE);
        this.date = date;
    }
    public Deadline(String desc, Status status, LocalDate date) {
        super(desc, TaskType.DEADLINE, status);
        this.date = date;
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
