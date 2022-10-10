package server;

import baseclasses.Movie;

import java.util.Comparator;

class SortById implements Comparator<Movie> {

    @Override
    public int compare(Movie m1, Movie m2) {
        return m1.getId().compareTo(m2.getId());
    }
}
