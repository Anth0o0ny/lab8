package input;





import baseclasses.Color;
import baseclasses.Country;
import baseclasses.MpaaRating;
import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import sub.StringConstants;

public class InputArgumentTester {
    String name ;



    public  Long assignInputId(String arg) {
        long id;

        try {
            id = Long.parseLong(arg);
            if (id <= 0)
                throw new NumberFormatException();
        } catch (NumberFormatException e) {
            return null;
        }
        return id;
    }


    String fromField;

    public void setFromField(String fromField) {
        this.fromField = fromField;
    }


    public String assignInputName() {
        System.out.println("Starts name");
        System.out.println(fromField);
//        do {
            try {
                name = fromField;
                if (fromField.isEmpty()) {
                    throw new RuntimeException();
                }
            } catch (RuntimeException ex) {
                System.out.println(StringConstants.MovieMaking.WRONG_NAME);
                name = null;
            }
//        } while (name == null);
        return name;
        }


    String xField;
    public void setxField(String xField) {
        this.xField = xField;
    }

    public Double assignInputX() {
        Double x;
        do {
            System.out.println( StringConstants.MovieMaking.ENTER_COORDINATE + " x: ");
            System.out.print(">");

            try {
                x = Double.parseDouble(xField);
                if (x > 398) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException ex) {
                System.out.println(StringConstants.MovieMaking.WRONG_X_COORDINATE);
                x = null;
            }
        } while (x == null);
        return x;
    }


    String yField;

    public void setyField(String yField) {
        this.yField = yField;
    }

    public Float assignInputY() {
        Float y;
        do {
            System.out.println( StringConstants.MovieMaking.ENTER_COORDINATE + " y: ");
            System.out.print(">");

            try {
                y = Float.parseFloat(yField);
            } catch (NumberFormatException ex) {
                System.out.println(StringConstants.MovieMaking.WRONG_Y_COORDINATE);
                y = null;
            }
        } while (y == null);
        return y;
    }


    public void setOscarField(String oscarField) {
        this.oscarField = oscarField;
    }

    String oscarField;
    public Long assignInputOscarCount() {
        Long oscCount;
        do {
            System.out.println(StringConstants.MovieMaking.ENTER_OSCAR_COUNT);
            System.out.print(">");

            try {
                oscCount = Long.parseLong(oscarField);
                if (oscCount <= 0) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException ex) {
                System.out.println(StringConstants.MovieMaking.WRONG_OSCAR_COUNT);
                oscCount = null;
            }
        } while (oscCount == null);
        return oscCount;
    }


    String budgetField;

    public void setBudgetField(String budgetField) {
        this.budgetField = budgetField;
    }

    public long assignInputBudget() {
        long budget;
        do {
            System.out.println(StringConstants.MovieMaking.ENTER_BUDGET);
            System.out.print(">");

            try {
                budget = Long.parseLong(budgetField);
                if (budget <= 0) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException ex) {
                System.out.println(StringConstants.MovieMaking.WRONG_BUDGET);
                budget = 0;
            }
        } while (budget == 0);
        return budget;
    }



    public void setTaglineField(String taglineField) {
        this.taglineField = taglineField;
    }

    String taglineField;
    public String assignTagline() {
        String tagline;
        do {
            System.out.println(StringConstants.MovieMaking.ENTER_TAGLINE);
            System.out.print(">");
            try {
                tagline = taglineField;
                if (tagline.length() > 158) {
                    throw new RuntimeException();
                }
            } catch (RuntimeException ex) {
                System.out.println(StringConstants.MovieMaking.WRONG_TAGLINE);
                tagline = null;
            }
        } while (tagline == null);
        return tagline;
    }


    public void setMpaaField(MpaaRating mpaaField) {
        this.mpaaField = mpaaField;
    }

    MpaaRating mpaaField;
    public MpaaRating assignMpaaRating() {
        byte rateNum = 0;
        MpaaRating res = null;
        do {
            System.out.println(StringConstants.MovieMaking.ENTER_MPAA_RATING);
            for (byte i = 0; MpaaRating.values().length > i; i++) {
                System.out.println((i + 1) + ". " + MpaaRating.values()[i]);
            }
            System.out.print(">");


            try {
//                rateNum = Byte.parseByte(mpaaField);
//                res = MpaaRating.values()[rateNum - 1];
                res = mpaaField;
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException ex) {
                System.out.println(StringConstants.MovieMaking.WRONG_MPAA_RATING);
                rateNum = 0;
            }
        }
        while (rateNum == 0);
        return res;
    }



    String personNameField;

    public void setPersonNameField(String personNameField) {
        this.personNameField = personNameField;
    }

    public String assignInputPersonName(){
        String name;
        do {
            System.out.println(StringConstants.MovieMaking.ENTER_PERSON_NAME);
            System.out.print(">");
            try {
                name = personNameField;
                if (name.isEmpty()) {
                    throw new RuntimeException();
                }
            } catch (RuntimeException ex) {
                System.out.println(StringConstants.MovieMaking.WRONG_PERSON_NAME);
                name = null;
            }
        } while (name == null);
        return name;
    }



    String heightField;

    public void setHeightField(String heightField) {
        this.heightField = heightField;
    }

    public float assignInputHeight(){
        float height;
        do {
            System.out.println(StringConstants.MovieMaking.ENTER_HEIGHT);
            System.out.print(">");

            try {
                height = Float.parseFloat(heightField);
                if (height <= 0) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException ex) {
                System.out.println(StringConstants.MovieMaking.WRONG_HEIGHT);
                height = 0;
            }
        } while (height == 0);
        return height;
    }






    public void setCountry(Country country) {
        this.country = country;
    }

    Country country;
    public Country assignInputCountry(){
        byte countryNum = 0;
        Country res = null;
        do {
            System.out.println(StringConstants.MovieMaking.ENTER_COUNTRY);
            for (byte i = 0; Country.values().length > i; i++) {
                System.out.println((i + 1) + ". " + Country.values()[i]);
            }
            System.out.print(">");
            String inp = null;
            try {
//                countryNum = Byte.parseByte(inp);
//                res = Country.values()[countryNum - 1];
                res = country;
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException ex) {
                System.out.println(StringConstants.MovieMaking.WRONG_COUNTRY);
                countryNum = 0;
            }
        }
        while (countryNum == 0);
        return res;
    }






Color color;

    public void setColor(Color color) {
        this.color = color;
    }

    public Color assignInputColor(){
        byte colorNum = 0;
        Color res = null;

        do {
            System.out.println(StringConstants.MovieMaking.ENTER_COLOR);

            for (byte i = 0; Color.values().length > i; i++) {
                System.out.println((i + 1) + ". " + Color.values()[i]);
            }
            System.out.print(">");
            String inp = null;
            try {
//                colorNum = Byte.parseByte(inp);
//                res = Color.values()[colorNum - 1];
                res = color;
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException ex) {
                System.out.println(StringConstants.MovieMaking.WRONG_COLOR);
                colorNum = 0;
            }
        } while (colorNum == 0);
        return res;
    }

}


