package moviemaking;

import baseclasses.Color;
import baseclasses.Country;
import baseclasses.MpaaRating;
import baseclasses.Person;
//import input.InputArgumentTester;

public class CreatePerson {
    public static Person createPerson() {

        AddSingleton addSingleton = AddSingleton.getAddSingleton();

        String person = addSingleton.getPersonName();
        Float height = addSingleton.getHeight();
        Color color = addSingleton.getColor();
        Country country = addSingleton.getCountry();
        return new Person(person, height, color, country);
    }
}
