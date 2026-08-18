public class Todo extends Task {
  
  Todo(String desc) {
    super(desc, TaskType.TODO);
    System.out.println(Max.tabSpace + "Task added:\n" + 
                       Max.tabSpace + this);
    Task.printNumberOfTask();
  }

  @Override
  public String toString() {
    return super.toString();
  }
}
