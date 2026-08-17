public class Task {
  private static Task[] tasks = new Task[100];
  private static int count = 0;


  private String desc;
  private int id;
  private boolean done = false;

  Task(String task) {
    this.desc = task;
    this.id = count;
    Task.tasks[id] = this;
    count++;
  }
  
  public String getTask() {
     return this.desc;
  }

  public void done() {
    this.done = true;
  }

  public void notDone() {
    this.done = false;
  }

  /**
   * Print all task 
   */
  public static void printAllTask() {
    for(int i = 0; i < tasks.length; i++) {
      if (tasks[i] == null) {
        continue;
      }
      System.out.println((i+1) + "." + tasks[i]);
    }
  }

  public static void setTaskDone(int i) {
    if (tasks[i] == null) {
      // TODO error handling
      return;
    }
    tasks[i-1].done();
    System.out.println(Max.tabSpace + "Nice! I've marked this task as done");
    System.out.println(Max.tabSpace + tasks[i-1]);

  }

  public static void setTaskNotDone(int i) {
    if (tasks[i] == null) {
      // TODO error handling
      return;
    }
    tasks[i-1].notDone();
    System.out.println(Max.tabSpace + "Ok! I've marked this task as not done");
    System.out.println(Max.tabSpace + tasks[i-1]);
  }

  @Override
  public String toString() {
    String isDone = this.done ? "[X]" : "[ ]";
    return isDone + this.desc;
  }

}
