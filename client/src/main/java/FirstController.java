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
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sub.StringConstants;

public class FirstController {
    private String login;
    private String password;



    private  Client client = Client.getClient();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField loginButton;

    @FXML
    private PasswordField passButton;

    @FXML
    private Button registerButton;


    @FXML
    void initialize() {
        registerButton.setOnAction(event -> {
            login = loginButton.getText().trim();
            password = passButton.getText().trim();
            System.out.println( "Ваши данные: \n" + login + "\n" + password);

            Request authorizateRequest = new Request("authorization");
            authorizateRequest.setLogin(login);
            authorizateRequest.setPassword(password);
            client.sendRequest(authorizateRequest);
            Optional<Response> optionalResponse = client.getResponse();

            if (optionalResponse.isPresent()){
            Response response = optionalResponse.get();
                if (!response.getMessage().isEmpty()){
                    System.out.println(response.getMessage());
                    initialize();
                }
        }

            System.out.println(StringConstants.StartTreatment.ENTER_IN_SYSTEM_BY_NAME + login);
            registerButton.getScene().getWindow().hide();


            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("mainPageTable.fxml"));

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

        });
    }




}
