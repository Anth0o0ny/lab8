package moviemaking;

import baseclasses.Coordinates;
import baseclasses.Movie;
import baseclasses.MpaaRating;
import baseclasses.Person;
//import input.InputArgumentTester;

import java.util.Date;

public class AddMovie {


    public static Movie makeMovie() {
        AddSingleton addSingleton = AddSingleton.getAddSingleton();

        String name = addSingleton.getName();
        Double x = addSingleton.getX();
        Float y = addSingleton.getY();
        Coordinates coordinates = new Coordinates(x, y);
        java.util.Date creationDate = new Date();
        Long oscCount = addSingleton.getOscarCount();
        long budget = addSingleton.getBudget();
        String tagline = addSingleton.getTagline();
        MpaaRating rate = addSingleton.getRate();
        Person person = CreatePerson.createPerson();
        return new Movie( name, coordinates, creationDate, oscCount, budget, tagline, rate, person);
    }
}
