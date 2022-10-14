package maincontrolleractions;

import baseclasses.Movie;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.util.ArrayList;

public class DataTester {
    ObservableList<Movie> observableList;

    public DataTester(ObservableList<Movie> observableList){
        this.observableList = observableList;
    }

    ArrayList arrayList = new ArrayList();

    public ArrayList forSubId( String currentUserLogin){
        for (Movie movie : observableList) {
            if (currentUserLogin.equals(movie.getLogin())) {
                arrayList.add(movie.getId());
            }
        }
        return arrayList;
    }

    public ArrayList<String> forSubScreenwriter(String currentUserLogin){
        for (Movie movie : observableList) {
            if (currentUserLogin.equals(movie.getLogin())) {
                arrayList.add(movie.getScreenwriter().getPersonName());
            }
        }
        return arrayList;
    }

    public int forSubIndex(){
        int i = 0;
        for (Movie movie : observableList){
            i++;
        }
        return i-1;
    }

}
