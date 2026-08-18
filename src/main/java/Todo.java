public class Todo extends Task {
  
  Todo(String desc) {
    super(desc);
    System.out.println(Max.tabSpace + "Task added:\n" + 
                       Max.tabSpace + this);
    Task.printNumberOfTask();
  }

  @Override
  public String toString() {
    return "[T]"+ super.toString();
  }
}
