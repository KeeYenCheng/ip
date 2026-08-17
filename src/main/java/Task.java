public class Task {
  private static Task[] tasks = new Task[100];
  private static int count = 0;

  private String desc;
  private int id;

  Task(String task) {
    this.desc = task;
    this.id = count;
    Task.tasks[id] = this;
    count++;
  }
  
  public String getTask() {
     return this.desc;
  }

  /**
   * Print all task 
   */
  public static void printAllTask() {
    for(int i = 0; i < tasks.length; i++) {
      if (tasks[i] == null) {
        continue;
      }
      System.out.println((i+1) + ". " + tasks[i]);
    }
  }

  @Override
  public String toString() {
    return this.desc;
  }

}
