import baseclasses.Movie;

import java.util.Stack;

public class DrawSingleton {
    private static final DrawSingleton INSTANCE = new DrawSingleton();

    private  String answer;

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    private Stack<Movie> movieStack;

    private DrawSingleton(){}

    public static DrawSingleton getInstance(){
        return INSTANCE;
    }


    public Stack<Movie> getMovieStack() {
        return movieStack;
    }

    public void setMovieStack(Stack<Movie> movieStack) {
        this.movieStack = movieStack;
    }
}
