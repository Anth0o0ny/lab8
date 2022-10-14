package input;





import baseclasses.Color;
import baseclasses.Country;
import baseclasses.MpaaRating;
import sub.StringConstants;

public class InputArgumentTester {
    String name ;



    String fromField;

    public void setFromField(String fromField) {
        this.fromField = fromField;
    }



    public boolean assignInputX(String X) {
        Double x;
        boolean res = true;
            try {
                x = Double.parseDouble(X);
                if (x < 0) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException ex) {

                 res = false;
            }
        return res;
    }


    public boolean assignInputY( String Y) {
        Float y;

        boolean res = true;
        try {
            y = Float.parseFloat(Y);
            if (y < 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException ex) {
            res = false;
        }
        return res;
    }



    public boolean assignInputOscarCount(String oscarField) {
        Long oscCount;
        boolean res = true;
            try {
                oscCount = Long.parseLong(oscarField);
                if (oscCount <= 0) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException ex) {
                res = false;
            }
        return res;
    }

    public boolean assignInputBudget(String budgetField) {
        long budget;
        boolean res = true;
            try {
                budget = Long.parseLong(budgetField);
                if (budget <= 0) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException ex) {
                res = false;
            }
        return res;
    }

    public boolean assignInputHeight(String heightField){
        float height;
        boolean res = true;
            try {
                height = Float.parseFloat(heightField);
                if (height <= 0) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException ex) {
                res = false;
            }
        return res;
    }




}


