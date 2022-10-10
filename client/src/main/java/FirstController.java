import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import client.Client;
import interaction.Request;
import interaction.Response;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sub.StringConstants;

public class FirstController {



    private  Client client = Client.getClient();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField loginButton;

    @FXML
    private PasswordField passButton;

    DataSingleton dataSingleton = DataSingleton.getInstance();

    @FXML
    private Button registerButton;


    @FXML
    void initialize() {

        registerButton.setOnAction(event -> {
            String login = loginButton.getText().trim();
            String password = passButton.getText().trim();
            dataSingleton.setLogin(login);
            dataSingleton.setPassword(password);


            Request authorizateRequest = new Request("authorization");
            authorizateRequest.setLogin(login);
            authorizateRequest.setPassword(password);
            client.sendRequest(authorizateRequest);
            Optional<Response> optionalResponse = client.getResponse();

            if (optionalResponse.isPresent()){
            Response response = optionalResponse.get();
                if (response.getMessage().equals("wrong")) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("error");
                    alert.setHeaderText("Can't login");
                    alert.setContentText("Введен неверный пароль для логина: " + login);
                    alert.showAndWait();
                    System.out.println(response.getMessage());
                    initialize();
                } else if (response.getMessage().equals("new")) {

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("new user");
                    alert.setHeaderText(null);
                    alert.setContentText("Зарегистрирован новый пользователь с логином: " + login);
                    alert.showAndWait();
                    afterAuthorization();
                } else {
                    System.out.println(StringConstants.StartTreatment.ENTER_IN_SYSTEM_BY_NAME + login);
                    afterAuthorization();
                }
        }

        });
    }
    public void afterAuthorization(){

        registerButton.getScene().getWindow().hide();


        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("fxml/mainPageTable.fxml"));

        try{
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = fxmlLoader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.setTitle("movies collection");
        stage.showAndWait();
    }


}
