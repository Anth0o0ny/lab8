import java.net.URL;
import java.util.*;

import baseclasses.Color;
import baseclasses.Coordinates;
import baseclasses.Movie;
import client.Client;
import interaction.Request;
import interaction.Response;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleSetProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class MainTableController {


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
    private Button removeAllByScreenwriterActionButton;

    @FXML
    private Button removeByIdActionButton;

    @FXML
    private Button showActionButton;

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

    ObservableList<Movie> movieObservableList = FXCollections.observableArrayList();


    @FXML
    void initialize() {
        updateMovieCollect();
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
                    Stack<Movie> collFromServer = tmpRes.getCollection();
                    boolean same = true;


                    if (collFromServer.size() == movieObservableList.size()){
                        for (Movie movie : movieObservableList){


                            if ( collFromServer.search(movie) == -1){

                                same = false;
                                break;
                            }
                        }
                    } else {
                        same = false;
                    }

                    if (!same ){

                        mainTable.getItems().clear();

                        for (Movie movie : tmpRes.getCollection()){
                            Movie res = new Movie(movie.getId(), movie.getName(), movie.getCoordinates().getX(),
                                    movie.getCoordinates().getY(), movie.getCreationDate(), movie.getOscarsCount(),
                                    movie.getBudget(), movie.getTagline(), movie.getMpaaRating().toString(),
                                    movie.getScreenwriter().getPersonName(), movie.getScreenwriter().getHeight(),
                                    movie.getScreenwriter().getHairColor().toString(),
                                    movie.getScreenwriter().getNationality().toString(), movie.getLogin());


                            movieObservableList.add(res);
                        }
                    }
                    mainTable.setItems(movieObservableList);

                }

                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

//    private void prepareTable(){
//        idTableColumn.setCellValueFactory(cellData -> cellData.getValue().);
//
//    }

}
