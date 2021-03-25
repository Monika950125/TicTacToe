package com.kodilla.tictactoe;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.*;
import java.util.stream.Collectors;

class TicTacToeController {

    GridPane grid;
    Stage primaryStage;

    public TicTacToeController(GridPane grid) {
        this.grid = grid;
    }

    public List<Tile> findEmptyTiles() {
        List<Tile> emptyTiles = grid.getChildren().stream()
                .map(node -> ((Tile) node))
                .filter(tile -> tile.getText().getText().equals(""))
                .collect(Collectors.toList());

        return emptyTiles;
    }

    public List<Tile> findTiles(String mark) {
        List<Tile> tiles = grid.getChildren().stream()
                .map(node -> ((Tile) node))
                .filter(tile -> tile.getText().getText().equals(mark))
                .collect(Collectors.toList());
        return tiles;
    }

    public void computerMove() {
        List<Tile> emptyTiles = findEmptyTiles();

        Optional<Tile> anyTile = emptyTiles.stream()
                .findAny();

        if (anyTile.isPresent()) {
            Tile tile1 = anyTile.get();
            tile1.getText().setText("O");
        }
    }


    public boolean ifFieldWasUsedBefore(Tile tile) {
        boolean result = tile.getText().getText().equals("");
        return result;
    }

    public void runAGame(Tile tile) {

        if (ifFieldWasUsedBefore(tile)) {
            tile.getText().setText("X");
            verifyResult();
            computerMove();
            verifyResult();
        }
    }

    public boolean isDraw() {
        List<Tile> emptyTiles = findEmptyTiles();
        return (emptyTiles.size() == 0);
    }

    public void verifyResult() {
        if (isWinningCombination("X") || isWinningCombination("O") || isDraw()) {
            endOfGame();
        }
    }

    public boolean isWinningCombination(String mark) {

        List<Tile> tiles = findTiles(mark);

        int row0 = 0;
        int row1 = 0;
        int row2 = 0;
        int column0 = 0;
        int column1 = 0;
        int column2 = 0;
        int diagonal1 = 0;
        int diagonal2 = 0;

        for (Tile tile : tiles) {
//            System.out.println(tile.getText());
            if (GridPane.getRowIndex(tile) == 0) {
                row0++;
            } else if (GridPane.getRowIndex(tile) == 1) {
                row1++;
            } else {
                row2++;
            }
        }

        for (Tile tile : tiles) {
//            System.out.println(tile.getText());
            if (GridPane.getColumnIndex(tile) == 0) {
                column0++;
            } else if (GridPane.getColumnIndex(tile) == 1) {
                column1++;
            } else {
                column2++;
            }
        }


        for (Tile tile : tiles) {
            if (GridPane.getRowIndex(tile) == 0 && GridPane.getColumnIndex(tile) == 0) {
                diagonal1++;
            }
            if (GridPane.getRowIndex(tile) == 1 && GridPane.getColumnIndex(tile) == 1) {
                diagonal1++;
                diagonal2++;
            }
            if (GridPane.getRowIndex(tile) == 2 && GridPane.getColumnIndex(tile) == 2) {
                diagonal1++;
            }
            if (GridPane.getRowIndex(tile) == 2 && GridPane.getColumnIndex(tile) == 0) {
                diagonal2++;
            }
            if (GridPane.getRowIndex(tile) == 0 && GridPane.getColumnIndex(tile) == 2) {
                diagonal2++;
            }
        }
        return row0 == 3 || row1 == 3 || row2 == 3 || column0 == 3 || column1 == 3 || column2 == 3 || diagonal1 == 3 || diagonal2 == 3;

    }

    public void endOfGame() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Game over");
        String message = "";
        alert.setHeaderText("Thank you for playing Tic Tac Toe");
        if (isWinningCombination("X")) {
            message = "The winner is X, \nDo you want to play again?";
        } else if (isWinningCombination("O")) {
            message = "The winner is O, \nDo you want to play again?";
        } else if (isDraw()) {
            message = "Draw, \nDo you want to play again?";
        }
        alert.setContentText(message);

        ButtonType buttonYes = new ButtonType("Yes");
        ButtonType buttonNo = new ButtonType("No");
//        ButtonType buttonCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().removeAll(ButtonType.OK);
        alert.getButtonTypes().addAll(buttonYes,buttonNo);
        Optional<ButtonType> response = alert.showAndWait();

        if (response.get() == buttonYes) {
            List<Tile> allTiles = grid.getChildren().stream()
                    .map(node -> ((Tile) node))
                    .collect(Collectors.toList());

            for (Tile tile : allTiles) {
                tile.getText().setText("");
            }
//        }else if(response.get() == buttonNo){

        }

        primaryStage.close();
        }
}