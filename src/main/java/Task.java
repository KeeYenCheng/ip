import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

import MaxExceptions.InvalidTaskIDException;


// TODO:
// 1.Create separate class for Saving and loading 
// 2.implement OOP principle
// 3.Load the mark and unmark status of the task


public abstract class Task {

  private String desc;
  private Status isDone = Status.NOT_DONE;
  private TaskType type;

  Task(String task, TaskType type) {
    this.desc = task;
    this.type = type;
  }

  Task(String task, TaskType type, Status isDone) {
    this.desc = task;
    this.type = type;
    this.isDone = isDone;
  }

  
  public String getTask() {
     return this.desc;
  }

  public void isDone() {
    this.isDone = Status.DONE;
  }

  public void notDone() {
    this.isDone = Status.NOT_DONE;
  }

  public boolean isOn(LocalDate date) {
    return false;
  }


  public String getItemString() {
        return this.type + " | " + this.isDone + " | " + this.desc;
  }
  
  @Override
  public String toString() {
    return this.type.toString() + this.isDone + this.desc;
  }

} 
