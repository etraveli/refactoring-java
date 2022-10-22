package movie.code;

public enum Codes {
  REGULAR(MovieCodeRegular.class),
  CHILDRENS(MovieCodeChildrens.class),
  NEW(MovieCodeNew.class);

  private Class<? extends MovieCode> movieCodeClass;

  private Codes(Class<? extends MovieCode> movieCodeClass) {
    this.movieCodeClass = movieCodeClass;
  }

  public Class<? extends MovieCode> getMovieCodeClass() {
    return this.movieCodeClass;
  }
}
