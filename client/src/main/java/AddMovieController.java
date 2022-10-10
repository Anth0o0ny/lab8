import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import baseclasses.Color;
import baseclasses.Country;
import baseclasses.MpaaRating;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
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
    ArrayList<MpaaRating> arrayList = new ArrayList<>();

    AddSingleton addSingleton = AddSingleton.getAddSingleton();

    @FXML
    void initialize() {

        mpaaRatingField.getItems().addAll(MpaaRating.values());
        hairColorField.getItems().addAll(Color.values());
        nationalityField.getItems().addAll(Country.values());



        addButton.setOnAction( event -> {


                if (assignInputName()) {
                    System.out.println("Not null");
                    addSingleton.setName(name);
                } else {
                    System.out.println("Null");
                    wrongNameField.setText("Input not null value");
                }


//                addSingleton.setName(name);
                addSingleton.setX(Double.parseDouble( coordinatesXField.getText().trim()));
                addSingleton.setY(Float.parseFloat(coordinatesYField.getText().trim()));
                addSingleton.setOscarCount(Long.parseLong(oscarCountField.getText().trim()));
                addSingleton.setBudget(Long.parseLong(budgetField.getText().trim()));
                addSingleton.setTagline(taglineField.getText().trim());
                addSingleton.setRate(mpaaRatingField.getValue());

                addSingleton.setPersonName(screenwriterNameField.getText().trim());
                addSingleton.setHeight(Float.parseFloat(heightField.getText().trim()));
                addSingleton.setColor(hairColorField.getValue());
                addSingleton.setCountry(nationalityField.getValue());


            addButton.getScene().getWindow().hide();

        });

    }

    boolean nameBoolean;
    String name = null;
    public boolean assignInputName(){
        try {
            name = movieNameField.getText().trim();
            if (name.isEmpty()){
                throw new NumberFormatException();}
            return true;
        } catch (NumberFormatException e){

            return false;
        }
    }


}
