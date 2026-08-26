import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

import MaxExceptions.InvalidTaskIDException;


// TODO:
// 1.abstract the system.out.println into Max class instead
// 2.abstract the saving and loading of the 
// 3.implement OOP principle


public abstract class Task {
  private static ArrayList<Task> tasks = new ArrayList<>();
  private static int count = 0;


  private String desc;
  private Status done = Status.NOT_DONE;

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
    this.done = isDone;
    tasks.add(this);
    count++;
  }


  
  public String getTask() {
     return this.desc;
  }

  public void done() {
    this.done = Status.DONE;
  }

  public void notDone() {
    this.done = Status.NOT_DONE;
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
    tasks.get(i-1).done();
    System.out.println(Max.tabSpace + "Nice! I've marked this task as done");
    System.out.println(Max.tabSpace + tasks.get(i-1));

  }

  public static void setTaskNotDone(int i) throws InvalidTaskIDException {
    if (i <= 0 || i > tasks.size())  {
      throw new InvalidTaskIDException();
    }
    tasks.get(i-1).notDone();
    System.out.println(Max.tabSpace + "Ok! I've marked this task as not done");
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
      System.out.println(t); 
      String[] data = t.split(Pattern.quote(" | "));
      for (int i = 0; i < data.length; i++) {
        System.out.println(data[i]); 
      }
      TaskType type = TaskType.fromSymbol(data[0]);
      switch (type) {
        case TODO:
          new Todo(data[2]);
          break;
        case DEADLINE:
          new Deadline(data[2], data[3]);
          break;
        case EVENT:
          new Event(data[2], data[3], data[4]);
          break;
        default:
          break;
      }
    }
  }

  public String getItemString() {
    return this.type + " | " + this.done + " | " + this.desc;
  }

  @Override
  public String toString() {
    return this.type.toString() + this.done + this.desc;
  }

} 
