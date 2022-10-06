package baseclasses;



import java.util.Stack;



public class MoviesCollection {


    private Stack<Movie> collection = new Stack<>();

    public Stack<Movie> getCollection() {
        return collection;
    }

    public void setCollection(Stack<Movie> collection) {
        this.collection = collection;
    }
}
