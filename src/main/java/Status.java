public enum Status {
  DONE("[X]"),
  NOT_DONE("[ ]");

  private final String s;

  Status(String symbol) {
    this.s = symbol;
  }

  @Override
  public String toString() {
    return s;
  }
}
