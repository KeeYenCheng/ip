import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import MaxExceptions.InvalidTaskIDException;


// TODO:
// 1.Create separate class for Saving and loading 
// 2.implement OOP principle
// 3.Load the mark and unmark status of the task


public abstract class Task {
  private static ArrayList<Task> tasks = new ArrayList<>();
  private static int count = 0;


  private String desc;
  private Status isDone = Status.NOT_DONE;

  private TaskType type;

  Task(String task, TaskType type) {
    this.desc = task;
    this.type = type;
    tasks.add(this);
    count++;
  }

  Task(String task, TaskType type, Status isDone) {
    this.desc = task;
    this.type = type;
    this.isDone = isDone;
    tasks.add(this);
    count++;
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

  /**
   * Print all task 
   */
  public static void printAllTask() {
    if (tasks.size() == 0) {
     System.out.println(Max.tabSpace+ "there is currently no task");
    }
    for(int i = 0; i < tasks.size(); i++) {
      if (tasks.get(i) == null) {
        continue;
      }
      System.out.println(Max.tabSpace + (i+1) + "." + tasks.get(i));
    }
  }

  public static void printNumberOfTask() {
    System.out.println(Max.tabSpace + "Now you have " + Task.count + " in the list");
  }

  public static void deleteTask(int i) throws InvalidTaskIDException {
    if (i <= 0 || i > tasks.size()) {
      throw new InvalidTaskIDException();
    }
    System.out.println(Max.tabSpace + "Okay! I've deleted this task from the list");
    System.out.println(Max.tabSpace + tasks.get(i-1)); 
    tasks.remove(i-1);
    Task.count--;
  }


  public static void setTaskDone(int i) throws InvalidTaskIDException {
    if (i <= 0 || i > tasks.size()) {
      throw new InvalidTaskIDException();
    }
    tasks.get(i-1).isDone();
    System.out.println(Max.tabSpace + "Nice! I've marked this task as isDone");
    System.out.println(Max.tabSpace + tasks.get(i-1));

  }

  public static void setTaskNotDone(int i) throws InvalidTaskIDException {
    if (i <= 0 || i > tasks.size())  {
      throw new InvalidTaskIDException();
    }
    tasks.get(i-1).notDone();
    System.out.println(Max.tabSpace + "Ok! I've marked this task as not isDone");
    System.out.println(Max.tabSpace + tasks.get(i-1));
  }

  public static void saveList() throws IOException{
    String res = "";
    FileWriter fw = new FileWriter("../../data/Max.txt");
    for(int i = 0; i < tasks.size(); i++) {
      String item = tasks.get(i).getItemString() + "\n";
      res += item;
    }
    fw.write(res);
    fw.close();
  }

  public static void loadList() throws FileNotFoundException {
    File f = new File("../../data/Max.txt");
    Scanner s = new Scanner(f);
    while (s.hasNext()) {
      String t = s.nextLine();
      String[] data = t.split(Pattern.quote(" | "));
    
      TaskType type = TaskType.fromSymbol(data[0]);
      switch (type) {
        case TODO:
          new Todo(data[2], Status.fromSymbol(data[1]));
          break;
        case DEADLINE:
          LocalDate date = LocalDate.parse(data[3]);
          new Deadline(data[2], Status.fromSymbol(data[1]), date);
          break;
        case EVENT:
          LocalDate start = LocalDate.parse(data[3]);
          LocalDate end = LocalDate.parse(data[4]);
          new Event(data[2], Status.fromSymbol(data[1]), start, end);
          break;
        default:
          break;
      }
    }
  }

  public static void printTasksOn(LocalDate date) {
    List<Task> list = tasks.stream().filter(task -> task.isOn(date)).collect(Collectors.toList());
    for(int i = 0; i < list.size(); i++) {
      System.out.println(Max.tabSpace + list.get(i)); 
    }
  }

  public String getItemString() {
    return this.type + " | " + this.isDone + " | " + this.desc;
  }

  @Override
  public String toString() {
    return this.type.toString() + this.isDone + this.desc;
  }

} 
