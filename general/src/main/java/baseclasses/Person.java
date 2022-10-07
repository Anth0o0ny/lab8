package baseclasses;





import java.io.Serializable;
import java.util.Objects;

public class Person implements Serializable {

    private String personName; //Поле не может быть null, Строка не может быть пустой
    private float height; //Значение поля должно быть больше 0
    private Color hairColor; //Поле может быть null
    private Country nationality; //Поле может быть null

    public Person(String name, float height, Color hairColor, Country nationality) {
        setPersonName(name);
        setHeight(height);
        setHairColor(hairColor);
        setNationality(nationality);
    }

    public Person(String name, float height, String hairColor, String nationality) {
        this.personName = name;
        this.height = height;
        this.hairColor = Color.fromString(hairColor);
        this.nationality = Country.fromString(nationality);
    }

    @Override
    public String toString() {
        return personName;
    }

    public String getPersonName() {
        return personName;
    }


    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public float getHeight() {
        return height;
    }


    public void setHeight(float height) {
        this.height = height;
    }

    public Color getHairColor() {
        return hairColor;
    }


    public void setHairColor(Color hairColor) {
        this.hairColor = hairColor;
    }

    public Country getNationality() {
        return nationality;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Float.compare(person.height, height) == 0 && personName.equals(person.personName) && hairColor == person.hairColor && nationality == person.nationality;
    }

    @Override
    public int hashCode() {
        return Objects.hash(personName, height, hairColor, nationality);
    }

    public void setNationality(Country nationality) {
        this.nationality = nationality;
    }


}
