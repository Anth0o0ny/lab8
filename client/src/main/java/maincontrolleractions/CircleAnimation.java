package maincontrolleractions;

import baseclasses.Movie;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Stack;

public class CircleAnimation {

    ArrayList<Shape> circleColl = new ArrayList<>();
    ArrayList<Shape> rectangleColl = new ArrayList<>();

    public void animate(Pane pane, Stack<Movie> collection){

        circleColl.clear();
        rectangleColl.clear();

        TabPane tabPane = new TabPane();
        tabPane.getTabs().add(createCircle(pane, collection));
        Stage stage = new Stage();
        Scene scene = new Scene(tabPane, 1100, 900);
        stage.setTitle("Drawing a Circle");

        scene.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                Stack<Movie> specialCollection =   collection;
                int i = 0;
                for (Movie movie : specialCollection) {

                    double x = movie.getCoordinates().getX();
                    float y = movie.getCoordinates().getY();
                    long r = (movie.getBudget() / 10);

                    final double startX = x;

                    double windowWidth = scene.getWindow().getWidth();

                    if ((x - r) <= mouseEvent.getX() && mouseEvent.getX() <= (x + r) &&
                            (y - r) <= mouseEvent.getY() && mouseEvent.getY() <= (y + r) &&
                            Math.sqrt(Math.pow(mouseEvent.getX() - x, 2) + Math.pow(mouseEvent.getY() - y, 2)) < r) {
                        int speed = 10000 / Math.toIntExact(movie.getOscarsCount());


                        TranslateTransition translateCircles = new TranslateTransition();
                        translateCircles.setDuration(Duration.millis(speed));
                        translateCircles.setNode(circleColl.get(i));
                        translateCircles.setToX(windowWidth - (startX + movie.getBudget() / 10) + 2*r);
//                        translateCircles.setAutoReverse(true);
//                        translateCircles.setCycleCount(2);


                        TranslateTransition translateCircles2 = new TranslateTransition();
                        translateCircles2.setDuration(Duration.millis(1));
                        translateCircles2.setNode(circleColl.get(i));
                        translateCircles2.setToX(-(windowWidth -(startX )  + r));


                        TranslateTransition translateRectangle = new TranslateTransition();
                        translateRectangle.setDuration(Duration.millis(speed));
                        translateRectangle.setNode(rectangleColl.get(i));
                        translateRectangle.setToX(-(1800 - windowWidth + 2 * movie.getBudget() / 10));



                        TranslateTransition translateRectangle2 = new TranslateTransition();
                        translateRectangle2.setDuration(Duration.millis(speed));
                        translateRectangle2.setNode(rectangleColl.get(i));
                        translateRectangle2.setToX(1);

//                        translateCircles2.setToX(-100);
//                        translateCircles.play();

//                        Translate translate = new Translate();
//    translate.setX(-(windowWidth - (startX + movie.getBudget() / 10)));

//
                        SequentialTransition seqCir = new SequentialTransition(translateCircles, translateCircles2);

                        SequentialTransition seqRec = new SequentialTransition(translateRectangle, translateRectangle2);
                        seqCir.play();
                        seqRec.play();

                        seqCir.setOnFinished( event -> {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("movie");
                                alert.setHeaderText(null);
                                alert.setContentText(giveMovieInformation(movie));
                                alert.show();
                        });

                        System.out.println(x);
                    }

                    i++;

                }
            }
        });

        stage.setScene(scene);
        stage.show();

    }



    public Tab createCircle(Pane pane, Stack<Movie> collection) {
        for (Movie movie : collection) {
            Circle circle = new Circle();
            circle.setCenterX(movie.getCoordinates().getX());
            circle.setCenterY(movie.getCoordinates().getY());
            circle.setRadius(movie.getBudget() / 10);
            circle.setFill(Color.rgb(movie.getLogin().hashCode() % 100,
                    (movie.getLogin().hashCode() / 100) % 100, (movie.getLogin().hashCode() / 10000) % 100));
            circle.setStrokeWidth(8.0);
            javafx.scene.shape.Rectangle rectangle = new javafx.scene.shape.Rectangle(1800, (movie.getCoordinates().getY() - movie.getBudget() / 10),
                    (2 * movie.getBudget() / 10 + 20), (2 * movie.getBudget() / 10 + 10));
            rectangle.setFill(Color.GRAY);
            rectangle.setStrokeWidth(8.0);

            rectangleColl.add(rectangle);
            circleColl.add(circle);

            pane.getChildren().add(circle);
            pane.getChildren().addAll(rectangle);
        }

        Tab tab = new Tab(pane.getClass().getSimpleName());
        tab.setContent(pane);
        return tab;
    }

    public static String giveMovieInformation(Movie movie) {
        StringBuilder sb = new StringBuilder();
        sb.append("Id: ").append(movie.getId()).append("\n");
        sb.append("Name: ").append(movie.getName()).append("\n");
        sb.append("X: ").append(movie.getCoordinates().getX()).append("\n");
        sb.append("Y: ").append(movie.getCoordinates().getY()).append("\n");
        sb.append("Date: ").append(movie.getCreationDate()).append("\n");
        sb.append("Oscar count: ").append(movie.getOscarsCount()).append("\n");
        sb.append("Budget: ").append(movie.getBudget()).append("\n");
        sb.append("Tagline: ").append(movie.getTagline()).append("\n");
        sb.append("MPAA Rating: ").append(movie.getMpaaRating()).append("\n");
        sb.append("Screenwriter: ").append(movie.getScreenwriter().getPersonName()).append("\n");
        sb.append("Height: ").append(movie.getScreenwriter().getHeight()).append("\n");
        sb.append("Hair color: ").append(movie.getScreenwriter().getHairColor()).append("\n");
        sb.append("Nationality: ").append(movie.getScreenwriter().getNationality()).append("\n");
        sb.append("Login: ").append(movie.getLogin()).append("\n");
        return sb.toString();
    }
}
