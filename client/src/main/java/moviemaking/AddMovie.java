package moviemaking;

import baseclasses.Coordinates;
import baseclasses.Movie;
import baseclasses.MpaaRating;
import baseclasses.Person;
import input.InputArgumentTester;

import java.util.Date;

public class AddMovie {


    public static Movie makeMovie() {
        AddSingleton addSingleton = AddSingleton.getAddSingleton();
        InputArgumentTester iat = new InputArgumentTester();

//        String name = iat.assignInputName();
//        System.out.println(name);
//        Double x = iat.assignInputX();
//        Float y = iat.assignInputY();
//        Coordinates coordinates = new Coordinates(x, y);
//        Date date = new Date();
//        Long oscCount = iat.assignInputOscarCount();
//        long budget = iat.assignInputBudget();
//        String tagline = iat.assignTagline();
//        MpaaRating rate = CreatePerson.chooseRating();
//        Person person = CreatePerson.ctreatePerson();

        String name = addSingleton.getName();
        Double x = addSingleton.getX();
        Float y = addSingleton.getY();
        Coordinates coordinates = new Coordinates(x, y);
        java.util.Date creationDate = new Date();
        Long oscCount = addSingleton.getOscarCount();
        long budget = addSingleton.getBudget();
        String tagline = addSingleton.getTagline();
        MpaaRating rate = addSingleton.getRate();
        Person person = CreatePerson.ctreatePerson();
        return new Movie( name, coordinates, creationDate, oscCount, budget, tagline, rate, person);
    }
}
