import java.util.ArrayList;

import MaxExceptions.InvalidTaskIDException;

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

  @Override
  public String toString() {
    return this.type.toString() + this.done + this.desc;
  }

}
