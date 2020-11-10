
import java.util.Observable;
import java.util.Observer;
import javafx.application.Application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import javafx.stage.Stage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Seun Lawal 101039762
 */
public class ConnectFourApplication extends Application implements Observer {

    final int NUM_COLUMNS = 8;
    final int NUM_ROWS = 8;
    final int NUM_WIN = 4;
    final int BUTTON_SIZE = 20;
    private ConnectFourGame gameEngine;
    private ConnectButton[][] buttons;
    private String cTurn; // Current Turn
    private int tempR;//The val
    private int tempC;
    private Alert alert;
    private GridPane grid;

    @Override
    public void update(Observable obs, Object arg) {//Updates the observer or applicatiion
        if (arg == null) {
            for (int i = 0; i < this.NUM_ROWS; i++) {
                for (int j = 0; j < this.NUM_COLUMNS; j++) {
                    this.buttons[i][j].setText("Empty");
                    this.buttons[i][j].setDisable(false);
                }
            }
        }
        ConnectMove move = (ConnectMove) arg;

        buttons[move.getRow()][move.getColumn()].setText(move.getColour().toString());//Sets the button Colour
        buttons[move.getRow()][move.getColumn()].setDisable(true); //Disables the button

        this.cTurn = gameEngine.getTurn().toString();//Updates The turn
        if (gameEngine.getGameState() != ConnectFourEnum.IN_PROGRESS) { //When the game is done checks who wins
            if (ConnectFourEnum.DRAW == gameEngine.getGameState()) {
                alert.setTitle("Info");
                alert.setHeaderText("Game Over");
                alert.setContentText("Its a Draw");
                alert.showAndWait();

            } else {
                switch (gameEngine.getGameState()) {
                    case BLACK:
                        alert.setTitle("Info");
                        alert.setHeaderText("Game Over");
                        alert.setContentText("Black Wins");
                        alert.showAndWait();
                        break;
                    case RED:
                        alert.setTitle("Info");
                        alert.setHeaderText("Game Over");
                        alert.setContentText("Red Wins");
                        alert.showAndWait();
                        break;
                    default:
                        alert.setTitle("Info");
                        alert.setHeaderText("Game Over");
                        alert.setContentText("Its a Draw");
                        alert.showAndWait();
                        break;
                }
            }

            gameEngine.reset(ConnectFourEnum.BLACK);// Rests the Game
        }

    }

    @Override
    public void start(Stage primaryStage) {
        this.gameEngine = new ConnectFourGame(ConnectFourEnum.BLACK);
        this.gameEngine.addObserver(this);
        this.cTurn = gameEngine.getTurn().toString();
        this.alert = new Alert(AlertType.INFORMATION);

        BorderPane root = new BorderPane();

        Scene scene = new Scene(root, 510, 380);
        TextField turnText = new TextField("Its " + cTurn + " turn.");
        turnText.setEditable(false);
        turnText.setText(cTurn);
        root.setTop(turnText);
        Button takeTurn = new Button("Take Turn");
        root.setBottom(takeTurn);
        this.grid = new GridPane();
        root.setCenter(grid);
        this.buttons = new ConnectButton[this.NUM_ROWS][this.NUM_COLUMNS];
        EventHandler<ActionEvent> sharedHandler = new ButtonHandler();
        for (int i = 0; i < this.NUM_ROWS; i++) {
            for (int j = 0; j < this.NUM_COLUMNS; j++) {
                this.buttons[i][j] = new ConnectButton("Empty", i, j); //Creatse Buttons

                this.buttons[i][j].setMinHeight(BUTTON_SIZE);
                this.buttons[i][j].setMaxWidth(Double.MAX_VALUE);
                this.buttons[i][j].setOnAction(sharedHandler);
                grid.add(this.buttons[i][j], j, NUM_ROWS - i);

            }

        }

        takeTurn.setOnAction(new EventHandler<ActionEvent>() { //Annoyous inner class to handle take turn button

            @Override
            public void handle(ActionEvent event) {
                //System.out.println("DropChecker");
                gameEngine.takeTurn(tempR, tempC);
                turnText.setText("its " + cTurn + " turn"); //Takes turn

            }
        });

        primaryStage.setTitle("ConnectFour Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String args[]) {

        launch(args);
    }

    class ButtonHandler implements EventHandler<ActionEvent> {

        /**
         * Gets the row and Column from the button
         *
         * @param event
         */
        @Override
        public void handle(ActionEvent event) {
            //  System.out.println("ActionEvent ");
            Object source = event.getSource();
            if (source instanceof ConnectButton) {
                ConnectButton b = (ConnectButton) source;
                tempR = b.getRow();
                tempC = b.getColumn();

            }

        }

    }
}
