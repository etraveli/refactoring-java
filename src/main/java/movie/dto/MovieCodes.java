package movie.dto;

public enum MovieCodes {

    NEW("new"), CHILDRENS("childrens"), REGULAR("regular");

    public String key;

    MovieCodes(String key) {
        this.key = key;
    }
}
