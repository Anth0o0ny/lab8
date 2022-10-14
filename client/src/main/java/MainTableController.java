import java.io.IOException;

import java.util.*;

import baseclasses.Movie;
import client.Client;
import client.ClientInvoker;
import client.ClientReceiver;
import interaction.Request;
import interaction.Response;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import maincontrolleractions.AppLanguages;
import maincontrolleractions.CircleAnimation;
import maincontrolleractions.DifferentWindows;
import maincontrolleractions.DataTester;
import moviemaking.AddSingleton;


public class MainTableController {

    private final Pane pane = new Pane();
    ClientReceiver clientReceiver = new ClientReceiver();
    ClientInvoker clientInvoker = new ClientInvoker(clientReceiver);
    Client client = Client.getClient();
    private String login;
    private String password;

    private Stack<Movie> collection;
    AuthSingleton dataSingleton = AuthSingleton.getInstance();
    ObservableList<Movie> movieObservableList = FXCollections.observableArrayList();
    CircleAnimation circleAnimation = new CircleAnimation();
    DifferentWindows subInfoWindow = new DifferentWindows();
    DataTester dataTester = new DataTester(movieObservableList);
    DrawSingleton drawSingleton = DrawSingleton.getInstance();
    AddSingleton addSingleton = AddSingleton.getAddSingleton();

    @FXML
    private Button addActionButton;

    @FXML
    private Button animationButton;

    @FXML
    private Button addIfMinActionButton;

    @FXML
    private Button clearActionButton;

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

    @FXML
    private ComboBox<AppLanguages> chooseLanguagePanel;

    @FXML
    private Label currentLang;

    @FXML
    private Text textId;

    @FXML
    private Label moviesCollection;

    @FXML
    private Label textCurrentLanguage;

    String exitHelp;
    String helpReference;
    String infoReference;
    String removeIdHeader;
    String enterId;
    String removeByScreen;
    String enterScreen;
    String updateHead;
    String insertAtHead;
    String enterIndex;
    String imposterChange;
    ArrayList<String> arrays = new ArrayList<>();
    String collectionType;
    String numberOfObjects;
    String initializeDate;

    @FXML
    void initialize() {


        Locale locale = new Locale("ru", "RU");
        refreshRB(locale);

        chooseLanguagePanel.getItems().addAll(AppLanguages.values());
        currentLang.setText(AppLanguages.RUSSIAN.toString());

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

        editableCols();

        collection = collectionFromResponse();
        drawSingleton.setMovieStack(collection);

        updateMovieCollect();

        chooseLanguagePanel.setOnAction(event -> {
            currentLang.setText(chooseLanguagePanel.getValue().toString());

            if (currentLang.getText().equals("Spanish")) {
                Locale localeSp = new Locale.Builder().setLanguage("es").setRegion("ES").build();
                refreshRB(localeSp);
                System.out.println("SP");
            } else if (currentLang.getText().equals("French")) {
                Locale locale1 = new Locale.Builder().setLanguage("fr").setRegion("FR").build();
                refreshRB(locale1);
                System.out.println("FR");
            } else if (currentLang.getText().equals("Icelandic")) {
                Locale locale2 = new Locale.Builder().setLanguage("is").setRegion("IS").build();
                refreshRB(locale2);
                System.out.println("ICE");
            } else {
                Locale localeRu = new Locale("ru", "RU");
                refreshRB(localeRu);
                System.out.println("RU");
            }
        });

        helpActionButton.setOnAction(actionEvent -> {
                subInfoWindow.wrongValueWindow("help", helpReference,
                responseProcessing(changeInformation("help", null)));
                System.out.println(arrays);   }
        );


        infoActionButton.setOnAction(actionEvent -> {
            responseProcessing(changeInformation("info", null));
            StringBuilder sb = new StringBuilder();
            sb.append(collectionType).append(arrays.get(0)).append("\n").append(numberOfObjects).append(arrays.get(1)).append("\n").append(initializeDate).append(arrays.get(2));
            subInfoWindow.wrongValueWindow("info", infoReference, String.valueOf(sb));
        });

        groupCountingByTaglineActionButton.setOnAction(actionEvent -> subInfoWindow.wrongValueWindow("group counting by tagline", "Group by tagline",
                responseProcessing(changeInformation("group_counting_by_tagline", null))));

        shuffleButton.setOnAction(event -> changeInformation("shuffle", null));

        clearActionButton.setOnAction(event -> changeInformation("clear", null));


        exitActionButton.setOnAction(event -> {
            subInfoWindow.wrongValueWindow("exit", null, exitHelp);
            changeInformation("exit", null);
        });

        addActionButton.setOnAction(event -> {
            callAddedTable();
            if (addSingleton.isCanAdd()) {
                changeInformation("add", null);
            }
        });

        addIfMinActionButton.setOnAction(event -> {
            callAddedTable();
            if (addSingleton.isCanAdd()) {
                changeInformation("add_if_min", null);
            }
        });


        removeByIdActionButton.setOnAction(event -> {
            Optional<String> result = subInfoWindow.enterValueWindow("enter id", removeIdHeader, enterId);
            try {
                if (result.isPresent() && dataTester.forSubId(login).contains(Long.parseLong(result.get()))) {
                    changeInformation("remove_by_id", result.get());
                } else if (result.get() == null) {
                    throw new NoSuchElementException();
                } else {
                    imposterChange();
                }
            } catch (NoSuchElementException e) {
            }
        });

        removeAllByScreenwriterActionButton.setOnAction(event -> {

            Optional<String> result = subInfoWindow.enterValueWindow("enter screenwriter", removeByScreen, enterScreen);

            try {
                if (result.isPresent() && dataTester.forSubScreenwriter(login).contains((result.get()))) {
                    changeInformation("remove_all_by_screenwriter", result.get());
                } else if (result.get() == null) {
                    throw new NoSuchElementException();
                } else {
                    imposterChange();
                }
            } catch (NoSuchElementException e) {
            }
        });


        updateActionButton.setOnAction(event -> {
            Optional<String> result = subInfoWindow.enterValueWindow("enter id", updateHead, enterId);

            try {
                if (result.isPresent() && dataTester.forSubId(login).contains(Long.parseLong(result.get()))) {
                    callAddedTable();
                    if (addSingleton.isCanAdd()) {
                        changeInformation("update", result.get());
                    }
                } else if (result.get() == null) {
                    throw new NoSuchElementException();
                } else {
                    imposterChange();
                }
            } catch (NoSuchElementException e) {
            }
        });

        insertAtButton.setOnAction(event -> {
            Optional<String> result = subInfoWindow.enterValueWindow("enter index",
                    insertAtHead, enterIndex);

            try {
                int userIndex = Integer.parseInt(result.get());
                if (result.isPresent() &&  userIndex <= dataTester.forSubIndex() && userIndex > -1) {
                    callAddedTable();
                    if (addSingleton.isCanAdd()) {
                        changeInformation("insert_at", result.get());
                    }
                } else if (result.get() == null) {
                    throw new NoSuchElementException();
                } else {
                    subInfoWindow.wrongValueWindow("error", "Can't complete", "Incorrect index");
                }
            } catch (NoSuchElementException e) {
            }

        });

        animationButton.setOnAction(event -> {circleAnimation.animate( new Pane(), collection);});
    }


    public void refreshRB(Locale locale) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("Language", locale);
        textId.setText(resourceBundle.getString("currentUser"));
        moviesCollection.setText(resourceBundle.getString("moviesCollection"));
        textCurrentLanguage.setText(resourceBundle.getString("currentLanguage"));
        exitHelp=resourceBundle.getString("exit");
        helpReference = resourceBundle.getString("helpReference");
        infoReference = resourceBundle.getString("infoReference");
        removeIdHeader = resourceBundle.getString("removeIdHeader");
        enterId = resourceBundle.getString("enterId");
        removeByScreen = resourceBundle.getString("removeByScreen");
        enterScreen = resourceBundle.getString("enterScreen");
        updateHead = resourceBundle.getString("updateHead");
        insertAtHead = resourceBundle.getString("insertAtHead");
        enterIndex = resourceBundle.getString("enterIndex");
        imposterChange = resourceBundle.getString("imposterChange");
        collectionType = resourceBundle.getString("collectionType");
        numberOfObjects = resourceBundle.getString("numberOfObjects");
        initializeDate = resourceBundle.getString("initializeDate");
        addSingleton.setNullValues(resourceBundle.getString("nullValues"));
        addSingleton.setIncorrectValues("incorrectValues");
    }

    private void editableCols() {
        mainTable.getSelectionModel().getSelectedCells().addListener(this::selectCells);
    }

    private void selectCells(ListChangeListener.Change<? extends TablePosition> c) {
        c.getList().forEach(tablePosition -> {
                    long id = collection.get(tablePosition.getRow()).getId();
                    if (login.equals(collection.get(tablePosition.getRow()).getLogin())) {

                        callAddedTable();

                        if (addSingleton.isCanAdd()) {
//                                Platform.runLater(() ->  );
                            changeInformation("update", String.valueOf(id));
                        }
                    }
                }
        );
    }

    public void imposterChange() {
        subInfoWindow.wrongValueWindow("error", "Can't complete", imposterChange);
    }


    public void callAddedTable() {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("fxml/createMovie.fxml"));
        try {
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

    public Response changeInformation(String cmd, String arg) {
        Response response = null;
        if (client.isConnected()) {
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
        String str;
        if (response.getAnswer() == null) {
            str = response.getMessage();
        } else {
            arrays.clear();
            StringBuilder stringBuilder = new StringBuilder();
            for (String ans : response.getAnswer()) {
                stringBuilder.append(ans).append("\n");

                arrays.add(ans);
            }


            str = stringBuilder.toString();

        }
        return str;
    }

    public static Stack<Movie> collectionFromResponse() {
        Client client = Client.getClient();
        Request request = new Request("update_table");
        Stack<Movie> callFromServer = null;
        client.sendRequest(request);
        Optional<Response> optionalResponse = client.getResponse();
        if (optionalResponse.isPresent()) {
            Response tmpRes = optionalResponse.get();
            callFromServer = (Stack<Movie>) tmpRes.getCollection();
        }
        return callFromServer;
    }

    public void updateMovieCollect() {
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
                    if (!same){
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
}
