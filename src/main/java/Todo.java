public class Todo extends Task {
  
  Todo(String desc) {
    super(desc, TaskType.TODO);
  }

  Todo(String desc, Status status) {
    super(desc, TaskType.TODO, status);
  }

  @Override
  public String toString() {
    return super.toString();
  }
}
