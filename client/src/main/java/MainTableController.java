import java.io.IOException;
import java.net.URL;
import java.util.*;

import baseclasses.Movie;
import client.Client;
import client.ClientInvoker;
import client.ClientReceiver;
import interaction.Request;
import interaction.Response;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class MainTableController {

ClientReceiver clientReceiver = new ClientReceiver();
ClientInvoker clientInvoker = new ClientInvoker(clientReceiver);
Client client = Client.getClient();
    private String login ;
    private String password ;

    @FXML
    private Button addActionButton;

    @FXML
    private Button addIfMinActionButton;

    @FXML
    private Button clearActionButton;

    @FXML
    private Button executeScriptActionButton;

    @FXML
    private Button exitActionButton;

    @FXML
    private Button groupCountingByTaglineActionButton;

    @FXML
    private Button helpActionButton;

    @FXML
    private Button infoActionButton;

    @FXML
    private Button insertAtButton;

    @FXML
    private Button removeAllByScreenwriterActionButton;

    @FXML
    private Button removeByIdActionButton;

    @FXML
    private Button shuffleButton;

    @FXML
    private Button updateActionButton;


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Movie, Long> budgetTableColumn;

    @FXML
    private TableColumn<Movie, Date> dateTableColumn;

    @FXML
    private TableColumn<Movie, String> hairColorTableColumn;

    @FXML
    private TableColumn<Movie, Long> idTableColumn;

    @FXML
    private TableColumn<Movie, String> loginTableColumn;

    @FXML
    private AnchorPane mainPageTable;


    @FXML
    private TableView<Movie> mainTable = new TableView<>();

    @FXML
    private TableColumn<Movie, String> mpaaTableColumn;

    @FXML
    private TableColumn<Movie, String> nameTableColumn;

    @FXML
    private TableColumn<Movie, String> nationalityTableColumn;

    @FXML
    private TableColumn<Movie, Long> oscarsTableColumn;

    @FXML
    private TableColumn<Movie, Float> personHeightTableColumn;

    @FXML
    private TableColumn<Movie, String> personNameTableColumn;

    @FXML
    private TableColumn<Movie, String> taglineTableColumn;

    @FXML
    private TableColumn<Movie, Double> xTableColumn;

    @FXML
    private TableColumn<Movie, Float> yTableColumn;

    @FXML
    private Label userLogin;


    DataSingleton dataSingleton = DataSingleton.getInstance();

    ObservableList<Movie> movieObservableList = FXCollections.observableArrayList();


    @FXML
    void initialize() {


        login = dataSingleton.getLogin();
        password = dataSingleton.getPassword();

        userLogin.setText(login);

        idTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        xTableColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getCoordinates().getX()));
        yTableColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getCoordinates().getY()));
        dateTableColumn.setCellValueFactory(new PropertyValueFactory<>("creationDate"));
        oscarsTableColumn.setCellValueFactory(new PropertyValueFactory<>("oscarsCount"));
        budgetTableColumn.setCellValueFactory(new PropertyValueFactory<>("budget"));
        taglineTableColumn.setCellValueFactory(new PropertyValueFactory<>("tagline"));
        mpaaTableColumn.setCellValueFactory(new PropertyValueFactory<>("mpaaRating"));
        personNameTableColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getScreenwriter().getPersonName()));
        personHeightTableColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getScreenwriter().getHeight()));
        hairColorTableColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getScreenwriter().getHairColor().toString()));
        nationalityTableColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getScreenwriter().getNationality().toString()));
        loginTableColumn.setCellValueFactory(new PropertyValueFactory<>("login"));

        updateMovieCollect();




        helpActionButton.setOnAction( actionEvent -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("help");
            alert.setHeaderText("Help. Справка по командам");
            alert.setContentText(responseProcessing(changeInformation("help", null)));
            alert.showAndWait();
        });

        infoActionButton.setOnAction( actionEvent -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("info");
            alert.setHeaderText("Info. Информация о коллекции");
            alert.setContentText(responseProcessing(changeInformation("info", null)));
            alert.showAndWait();
        });

        groupCountingByTaglineActionButton.setOnAction( actionEvent -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("group counting by tagline");
            alert.setHeaderText("Group by tagline");
            alert.setContentText(responseProcessing(changeInformation("group_counting_by_tagline", null)));
            alert.showAndWait();
        });

        shuffleButton.setOnAction( event -> {
            changeInformation("shuffle", null);
        });

        clearActionButton.setOnAction( event -> {
            changeInformation("clear", null);
        });

        exitActionButton.setOnAction( event -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("exit");
            alert.setHeaderText(null);
            alert.setContentText("Спасибо за работу! До свидания!");
            alert.showAndWait();
            changeInformation("exit", null);
            exitActionButton.getScene().getWindow().hide();
        });

        removeByIdActionButton.setOnAction( event -> {
            TextInputDialog dialog = new TextInputDialog("");
            dialog.setTitle("enter id");
            dialog.setHeaderText("Для удаления \"Movie\" по его id, необходимо указать id.");
            dialog.setContentText("Введите id:");
            Optional<String> result = dialog.showAndWait();

            if (result.isPresent()){
                changeInformation("remove_by_id", result.get());
//
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("error");
                alert.setHeaderText("Can't remove");
                alert.setContentText("Введен несуществующий id");
                alert.showAndWait();
            }
        });

        removeAllByScreenwriterActionButton.setOnAction( event -> {
            TextInputDialog dialog = new TextInputDialog("");
            dialog.setTitle("enter screenwriter");
            dialog.setHeaderText("Для удаления \"Movie\" по значению поля screenwriter, необходимо указать screenwriter.");
            dialog.setContentText("Введите screenwriter:");
            Optional<String> result = dialog.showAndWait();

            if (result.isPresent()){
                changeInformation("remove_all_by_screenwriter", result.get());
            } else {

            }
        });

        addActionButton.setOnAction( event -> {
            callAddedTable();
            changeInformation("add", null);
        });

        addIfMinActionButton.setOnAction( event -> {
           callAddedTable();

           changeInformation("add_if_min", null);
        });

        updateActionButton.setOnAction( event -> {

            TextInputDialog dialog = new TextInputDialog("");
            dialog.setTitle("enter id");
            dialog.setHeaderText("Для изменения \"Movie\" по его id, необходимо указать id.");
            dialog.setContentText("Введите id:");
            Optional<String> result = dialog.showAndWait();


            ArrayList arrayList = new ArrayList();
            for (Movie movie : movieObservableList ){
                arrayList.add(movie.getId());
            }

            if (result.isPresent() && arrayList.contains(Long.parseLong( result.get()))){
                callAddedTable();
                changeInformation("update", result.get());

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("error");
                alert.setHeaderText("Can't update");
                alert.setContentText("Введен несуществующий id");
                alert.showAndWait();

            }

        });


        insertAtButton.setOnAction( event -> {
            TextInputDialog dialog = new TextInputDialog("");
            dialog.setTitle("enter index");
            dialog.setHeaderText("Укажите index, для вставки нового элемента в заданную позицию ");
            dialog.setContentText("Введите index:");
            Optional<String> result = dialog.showAndWait();

            if (result.isPresent()){
                callAddedTable();
                changeInformation("insert_at", result.get());
            }
            
        });



    }

    public void updateMovieCollect(){

        new Thread(() -> {
            while (true) {
                Client client = Client.getClient();
                Request request = new Request("update_table");

                client.sendRequest(request);
                Optional<Response> optionalResponse = client.getResponse();
                if (optionalResponse.isPresent()) {
                    Response tmpRes = optionalResponse.get();
                    Stack<Movie> collFromServer = (Stack<Movie>) tmpRes.getCollection();
                    boolean same = true;
                    if (collFromServer.size() == movieObservableList.size() && collFromServer.get(0) == movieObservableList.get(0)){

                        for (Movie movie : movieObservableList){
                            if ( collFromServer.search(movie) == -1){
                                same = false;
                                break;
                            }
                        }
                    } else {
                        same = false;
                    }
                    if ( !same ){
                        mainTable.getItems().clear();

                        for (Movie movie : tmpRes.getCollection()){
                            Movie res = new Movie(movie.getId(), movie.getName(), movie.getCoordinates().getX(),
                                    movie.getCoordinates().getY(), movie.getCreationDate(), movie.getOscarsCount(),
                                    movie.getBudget(),
                                    movie.getTagline(),
                                    movie.getMpaaRating().toString(),
                                    movie.getScreenwriter().getPersonName(), movie.getScreenwriter().getHeight(),
                                    movie.getScreenwriter().getHairColor().toString(),
                                    movie.getScreenwriter().getNationality().toString(), movie.getLogin());
                            movieObservableList.add(res);
                    }
                    mainTable.setItems(movieObservableList);
                }

                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                }
            }
        }).start();
    }


    public Response changeInformation(String cmd, String arg){
        Response response = null;
        if (client.isConnected()){
            Optional<Request> optionalRequest = clientInvoker.check(cmd, arg);
            if (optionalRequest.isPresent()) {
                Request request = optionalRequest.get();
                request.setLogin(login);
                request.setPassword(password);
                client.sendRequest(request);

                Optional<Response> optionalResponse = client.getResponse();
                if (optionalResponse.isPresent()) {
                     response = optionalResponse.get();
                }
            }
            return response;
        } else {
            client.reconnect();
            return null;
        }
    }


    private String responseProcessing(Response response) {
        String str = "";
        if (response.getAnswer() == null) {
            str = response.getMessage();

        } else {
            StringBuilder stringBuilder = new StringBuilder();
            for (String ans : response.getAnswer()) {
                stringBuilder.append(ans).append("\n");
            }
            str = stringBuilder.toString();
        }
        return str;
    }

    public void callAddedTable(){
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("fxml/createMovie.fxml"));

        try{
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = fxmlLoader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.setTitle("create");
        stage.showAndWait();

    }
}
