import client.Client;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.net.URL;

public class ClientMain extends Application {

    public static void main(String[] args){

        Client client = new Client();
//        ClientReceiver clientReceiver = new ClientReceiver();
//        ClientInvoker clientInvoker = new ClientInvoker(clientReceiver);
        client.setClient(client);

        client.connect();

        launch();
//        Terminal terminal = new Terminal(clientInvoker, client);
//        try {
//            terminal.inputKeyboard();
//        }catch (NoSuchElementException ignore){
//
//        } finally {
//            System.out.println(StringConstants.Client.CLIENT_END_WORK);
//        }


    }

    @Override
    public void start(Stage stage) throws Exception {
        URL fxmlLocation = getClass().getResource("fxml/reg.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(fxmlLocation);
        Scene scene = new Scene(fxmlLoader.load());
        stage.setResizable(false);
        stage.setTitle("authorization");
        stage.setScene(scene);
        stage.show();
    }
}
