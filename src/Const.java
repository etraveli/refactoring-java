
/**
 * Using constants is better than hard coded values. It is easier in
 * modification and less error prone
 * 
 * I use inner classes to be able to classify the constants in case we have a
 * lot of them
 */

public class Const {

    public static class Code {
        public static final String REGULAR = "regular";
        public static final String NEW = "new";
        public static final String CHILDREN = "children";
    }

    public static class Msg {
        public static final String NO_RESULT = "No Rentals Found";
    }

}
