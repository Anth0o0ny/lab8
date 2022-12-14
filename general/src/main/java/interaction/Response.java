package interaction;

import baseclasses.Movie;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.Stack;

public class Response implements Serializable {

    private static final long serialVersionUID = -4311158671713476273L;

    private final String message;
    private final String[] answer;

    private List<Movie> collection = null;

    public Response(String message, String[] answer) {
        this.message = message;
        this.answer = answer;
    }

    public Response(String message) {
        this(message, null);
    }

    public Response(String[] answer) {
        this(null, answer);
    }


    public String[] getAnswer() {
        return answer;
    }

    public String getMessage() {
        return message;
    }

    public List<Movie> getCollection() {
        return collection;
    }

    public void setCollection(List<Movie> collection) {
        this.collection = collection;
    }
}