package model;

public enum MovieType {
  REGULAR("Regular"),
  NEW("New"),
  CHILDREN("Children");

  private final String label;

  private MovieType(String label) {
    this.label = label;
  }
}
