package client;

//import input.InputArgumentTester;
import baseclasses.Movie;
import interaction.Request;
import moviemaking.AddMovie;

import java.util.Optional;

public class ClientReceiver {



      private final AddMovie addMovie;

    public ClientReceiver() {
        this.addMovie = new AddMovie();
    }

    public Optional<Request> removeById(String arg) {
        return Optional.of(new Request("remove_by_id", arg));
    }

    public Optional<Request> exit(){
        System.exit(0);
        return Optional.empty();
    }

    public Optional<Request> removeAllByScreenwriter(String arg) {
            return Optional.of(new Request("remove_all_by_screenwriter", arg));
    }

    public Optional<Request> insertAt(String arg){
        Long position = Long.parseLong(arg);
        if (position > 0){
            return Optional.of(new Request("insert_at", arg, AddMovie.makeMovie()));
        } else {
            return Optional.empty();
        }
    }

    public Optional<Request> add() {
        return Optional.of(new Request("add", AddMovie.makeMovie()));
    }

    public Optional<Request> addIfMin() {
        return Optional.of(new Request("add_if_min", AddMovie.makeMovie()));
    }

    public Optional<Request> update(String arg){
        return  Optional.of(new Request("update", arg, AddMovie.makeMovie()));
    }

}
