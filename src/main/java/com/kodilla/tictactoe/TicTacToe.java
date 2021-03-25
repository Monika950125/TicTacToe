package com.kodilla.tictactoe;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TicTacToe extends Application {

    public static Parent createContent() {
        GridPane grid = new GridPane();
        TicTacToeController controller = new TicTacToeController(grid);
        grid.setPrefSize(750, 750);

//        int tileCounter = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Tile tile = new Tile();
//                tile.setTranslateX(j * 250);
//                tile.setTranslateY(i * 250);
//                tileCounter++;
                tile.setOnMousePressed(event -> {
                        Tile source = (Tile) event.getSource();
                        controller.runAGame(source);

                });
                grid.add(tile, i, j);
            }
        }
        return grid;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(createContent()));
        primaryStage.setTitle("TicTacToe");
        primaryStage.show();
    }
}