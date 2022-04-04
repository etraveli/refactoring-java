package model;

import lombok.Getter;

public enum MovieType {
  REGULAR("Regular"),
  NEW("New"),
  CHILDREN("Children");

  @Getter
  private final String label;

  MovieType(String label) {
    this.label = label;
  }

  public static MovieType find(MovieType movieType) {
    MovieType result = null;
    for (var type: values()) {
      if (type.getLabel().equalsIgnoreCase(movieType.getLabel())) {
        result = type;
        break;
      }
    }
    return result;
  }
}
