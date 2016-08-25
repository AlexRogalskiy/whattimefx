package org.deblauwe.whattimefx;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Main extends Application {

    private Label timeLabel;
    private Label hexColorLabel;
    private GridPane gridPane;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("What Colour Is It JavaFx?");

        timeLabel = new Label();
        timeLabel.getStyleClass().add("time-label");

        hexColorLabel = new Label();
        hexColorLabel.getStyleClass().add("hexcolor-label");
        hexColorLabel.setMaxWidth(Double.MAX_VALUE);

        gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(20);

        gridPane.add(timeLabel, 0, 0);
        gridPane.add(hexColorLabel, 0, 1);

        Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();
        double width = visualBounds.getWidth();
        double height = visualBounds.getHeight();

        Scene value = new Scene(gridPane, width, height);
        value.getStylesheets().add("org/deblauwe/whattimefx/style.css");
        primaryStage.setScene(value);

        bindToTime();
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    private void bindToTime() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0),
                             new EventHandler<ActionEvent>() {
                                 @Override
                                 public void handle(ActionEvent actionEvent) {
                                     Calendar time = Calendar.getInstance();
                                     SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
                                     setText(simpleDateFormat.format(time.getTime()));
                                 }
                             }
                ),
                new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void setText(String format) {
        timeLabel.setText(format);
        String color = "#" + format.replaceAll(":", "");
        hexColorLabel.setText(color);
        gridPane.setBackground(new Background(new BackgroundFill(Color.web(color), null, null)));

    }

}
