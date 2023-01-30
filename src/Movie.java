public class Movie {
   
    private String title;
    private String code;

    public Movie(String title, String code) {

        this.title = title;
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public String getCode() {
        return code;
    }
    /*
     * Added setters, even though they are not required in this scenario, in the bigger picture setters are required.
     */
    
    public void setTitle(String title)
    {
        this.title = title;
    }

    public void setCode(String code)
    {
        this.code = code;
    }
}
