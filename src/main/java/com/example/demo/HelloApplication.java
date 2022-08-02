package com.example.demo;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HelloApplication extends Application {

    private final double Width = 1920.0d/4;
    private final double Height =  1080.0d/4;

    private Parent insertTiles(int w_width,int w_height,int width,int height){

        Rectangle[] f = new Rectangle[(w_height*w_width)]; // Error will occur if any indexs dont have data
        double x = 0.0d;
        double y = 0.0d; // increase this by int height for every row

        for(int i = 0 ; i < f.length;i++){
            f[i] = new Rectangle(width,height);

            if(x >= w_width){
                y += height;
                x = 0.0d;
            }

            int finalI = i;
            EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    if(e.getEventType() == MouseEvent.MOUSE_CLICKED){ // JavaFX is awkward with mouseEvents and it seems to be slow when trying multiple pixels being changed (could be my code though)
                        f[finalI].setFill(Color.DARKSLATEBLUE);       // Or my code is just bad.
                    }
                }
            };

            f[i].addEventFilter(MouseEvent.ANY,eventHandler);

            f[i].setTranslateX(x);
            f[i].setTranslateY(y);

            x += width;
            f[i].setStyle("-fx-fill:white; -fx-stroke: black; -fx-stroke-width: 0.5;");
        }

        return new Pane(f);
    }

    private double calcArea(double area){
        return (Width*Height)/area;
    }

    @Override
    public void start(Stage stage) throws IOException {

        stage.setScene(
                new Scene(
                      insertTiles(
                              (int)Width,
                              (int)Height,
                              30,
                              30
                      ),
                        (int)Width,
                        (int)Height
                )
        );

        stage.setResizable(false);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}