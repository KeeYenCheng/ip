public enum TaskType {
  TODO("[T]"), 
  DEADLINE("[D]"), 
  EVENT("[E]");

  private final String s;

  TaskType(String s) {
    this.s = s;
  }

  @Override
  public String toString() {
    return this.s;
  }
}
