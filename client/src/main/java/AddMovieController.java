import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import baseclasses.Color;
import baseclasses.Country;
import baseclasses.MpaaRating;
import input.InputArgumentTester;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import maincontrolleractions.DifferentWindows;
import moviemaking.AddSingleton;

public class AddMovieController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;


    @FXML
    private Label wrongNameField;

    @FXML
    private TextField budgetField;

    @FXML
    private TextField coordinatesXField;

    @FXML
    private TextField coordinatesYField;

    @FXML
    private ComboBox<Color> hairColorField;

    @FXML
    private TextField heightField;

    @FXML
    private AnchorPane mainWindow;

    @FXML
    private TextField movieNameField;

    @FXML
    private ComboBox<MpaaRating> mpaaRatingField;

    @FXML
    private ComboBox<Country> nationalityField;

    @FXML
    private TextField oscarCountField;

    @FXML
    private TextField screenwriterNameField;

    @FXML
    private TextField taglineField;

    @FXML
    private Button addButton;



    AddSingleton addSingleton = AddSingleton.getAddSingleton();
    DifferentWindows differentWindows = new DifferentWindows();
    InputArgumentTester iat = new InputArgumentTester();

    @FXML
    void initialize() {
        mpaaRatingField.getItems().addAll(MpaaRating.values());
        hairColorField.getItems().addAll(Color.values());
        nationalityField.getItems().addAll(Country.values());




        addButton.setOnAction( event -> {

            String name = movieNameField.getText().trim();
            String X =  coordinatesXField.getText().trim();
            String Y = coordinatesYField.getText().trim();
            String oscars = oscarCountField.getText().trim();
            String budget = budgetField.getText().trim();
            String tagline = taglineField.getText().trim();
            String mpaaRate = String.valueOf(mpaaRatingField.getValue());
            String personName = screenwriterNameField.getText().trim();
            String height = heightField.getText().trim();
            String color = String.valueOf(hairColorField.getValue());
            String country = String.valueOf(nationalityField.getValue());

            if (name.isEmpty() || X.isEmpty() || Y.isEmpty() || oscars.isEmpty() || budget.isEmpty() || tagline.isEmpty()
                    || mpaaRate.equals("null") || personName.isEmpty() || height.isEmpty() || color.equals("null") || country.equals("null")){
                differentWindows.wrongValueWindow("error", "null values", addSingleton.getNullValues());
                addSingleton.setCanAdd(false);
            } else {
                if (iat.assignInputX(X) && iat.assignInputY(Y) && iat.assignInputOscarCount(oscars) &&
                iat.assignInputBudget(budget) && iat.assignInputHeight(height)){
                    addSingleton.setName(name);
                    addSingleton.setX(Double.parseDouble(X));
                    addSingleton.setY(Float.parseFloat(Y));
                    addSingleton.setOscarCount(Long.parseLong(oscars));
                    addSingleton.setBudget(Long.parseLong(budget));
                    addSingleton.setTagline(tagline);
                    addSingleton.setRate(mpaaRatingField.getValue());
                    addSingleton.setPersonName(personName);
                    addSingleton.setHeight(Float.parseFloat(height));
                    addSingleton.setColor(hairColorField.getValue());
                    addSingleton.setCountry(nationalityField.getValue());
                    addSingleton.setCanAdd(true);
                    addButton.getScene().getWindow().hide();
                } else {
                    differentWindows.wrongValueWindow("error", "numerical values", addSingleton.getIncorrectValues());
                    addSingleton.setCanAdd(false);
                }
            }

        });

    }

}
