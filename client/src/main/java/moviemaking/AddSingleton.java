package moviemaking;

import baseclasses.Color;
import baseclasses.Country;
import baseclasses.MpaaRating;

public class AddSingleton {
    private  static final AddSingleton ADD_SINGLETON = new AddSingleton();
    private String name;
    private Double x;
    private Float y;
    private Long oscarCount;
    private long budget;
    private String tagline;
    private MpaaRating rate;
    private String personName;
    private Float height;
    private Color color;
    private Country country;

    private AddSingleton(){}


    public static AddSingleton getAddSingleton(){return ADD_SINGLETON;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public Long getOscarCount() {
        return oscarCount;
    }

    public void setOscarCount(Long oscarCount) {
        this.oscarCount = oscarCount;
    }

    public long getBudget() {
        return budget;
    }

    public void setBudget(long budget) {
        this.budget = budget;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public MpaaRating getRate() {
        return rate;
    }

    public void setRate(MpaaRating rate) {
        this.rate = rate;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public Float getHeight() {
        return height;
    }

    public void setHeight(Float height) {
        this.height = height;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
