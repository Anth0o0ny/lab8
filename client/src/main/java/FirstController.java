import java.io.IOException;

import java.util.*;

import client.Client;
import interaction.Request;
import interaction.Response;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import maincontrolleractions.DifferentWindows;
import sub.StringConstants;

public class FirstController {


    private final Client client = Client.getClient();

    DifferentWindows subInfoWindow = new DifferentWindows();

    AuthSingleton dataSingleton = AuthSingleton.getInstance();

    @FXML
    private TextField loginButton;

    @FXML
    private PasswordField passButton;

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

            if (optionalResponse.isPresent()) {
                Response response = optionalResponse.get();
                if (response.getMessage().equals("wrong")) {
                    subInfoWindow.wrongValueWindow("error", "Can't login",
                            "Wrong login password entered:");
                    initialize();
                } else if (response.getMessage().equals("new")) {
                    subInfoWindow.wrongValueWindow("new user", null, "New user registered with login: " + login);
                    afterAuthorization();
                } else {
                    System.out.println(StringConstants.StartTreatment.ENTER_IN_SYSTEM_BY_NAME + login);
                    afterAuthorization();
                }
            }
        });
    }

    public void afterAuthorization() {

        registerButton.getScene().getWindow().hide();

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("fxml/mainPageTable.fxml"));

        try {
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("movies collection");
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
