
import java.util.Observable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Seun Lawal 101039762
 */
public class ConnectFourGame extends Observable {

    private int nRows;// Number of Rows
    private int nColumns; // Number of Columns
    private int numToWin; //Number required to win
    private ConnectFourEnum grid[][];//Grid
    private ConnectFourEnum gameState;//The state of the Game
    private ConnectFourEnum turn;

    public ConnectFourGame(ConnectFourEnum intialTurn) {
        this.nRows = 8;
        this.nColumns = 8;
        this.numToWin = 4;
        this.grid = new ConnectFourEnum[8][8];
        this.gameState = ConnectFourEnum.IN_PROGRESS;
        this.turn = intialTurn;
        reset(this.turn);

    }

    ConnectFourGame(int nRows, int nColumns, int numToWin, ConnectFourEnum initialTurn) {
        if (nRows < 0 || nColumns < 0) {
            throw new IllegalArgumentException("Grid must be a positive size");
        }
        if (numToWin > nRows || numToWin > nColumns) {
            throw new IllegalArgumentException("sizeToWin must be less than dimensions");
        }
        this.nRows = nRows; //Sets the number of Rows
        this.nColumns = nColumns; //Sets the Number of Columns
        this.numToWin = numToWin; //Sets the number of Wins
        this.turn = initialTurn; //Sets the intial Turn
        this.grid = new ConnectFourEnum[nRows][nColumns]; //Builds the Grid
        reset(initialTurn);

    }

    /**
     * The reset Method Rests all the the Games Variables to their default
     *
     * @param initalTurn
     */
    public void reset(ConnectFourEnum initalTurn) {
        this.turn = initalTurn;

        for (int i = 0; i < this.nRows; i++) {
            for (int j = 0; j < this.nColumns; j++) {
                this.grid[i][j] = ConnectFourEnum.EMPTY;
            }
        }
        this.gameState = ConnectFourEnum.IN_PROGRESS;
        this.setChanged();
        this.notifyObservers(); //Tells the observe to rest the board
    }

    /**
     * Returns the current Turn of the Game
     *
     * @return
     */
    public ConnectFourEnum getTurn() {

        return this.turn;
    }

    /**
     * The Method returns the current Game State
     *
     * @return
     */
    public ConnectFourEnum getGameState() {
        return this.gameState;

    }

    public ConnectFourEnum takeTurn(int row, int column) {
        if (this.gameState != ConnectFourEnum.IN_PROGRESS) {
            throw new IllegalArgumentException("The Game is Over.");
        }

        if (row < 0 || row > this.nRows) {
            throw new IllegalArgumentException("Grid is only " + this.nRows + " by " + this.nColumns + "Pick a valid spot");
        }
        if (column < 0 || column > this.nColumns) {
            throw new IllegalArgumentException("Grid is only " + this.nRows + " by " + this.nColumns + "Pick a valid spot");
        }
        if (this.grid[row][column] != ConnectFourEnum.EMPTY) {
            throw new IllegalArgumentException("This spot is taken");
        }
        if (row != 0) {
            if (this.grid[row - 1][column] == ConnectFourEnum.EMPTY) {
                throw new IllegalArgumentException("Checker cannot be put above an empty location (it will fall down!");
            }
        }
        this.grid[row][column] = this.turn;
        ConnectFourEnum oldTurn = this.turn;
        this.gameState = findWinner(row, column);
        this.turn = (this.turn == ConnectFourEnum.RED) ? ConnectFourEnum.BLACK : ConnectFourEnum.RED;

        ConnectMove checker = new ConnectMove(row, column, oldTurn);
        this.setChanged();//
        this.notifyObservers(checker);// Let the obsever know a turn has been taken
        return this.gameState; //Returns the game State

    }

    /**
     * The find Winner method Searches both the rows and the columns to find
     * complete row or column of Xs or Os
     *
     * @return
     */
    private ConnectFourEnum findWinner(int row, int column) {
// check left
        int counter = 1;
        for (int i = 1; i <= numToWin; i++) {
            if (column - i >= 0) {

                if (this.grid[row][column] == this.grid[row][column - i]) {
                    counter++;
                } else {
                    break;
                }
            }
        }
// checking right
        for (int i = 1; i <= numToWin; i++) {
            if (column + i < nColumns) {
                if (this.grid[row][column] == this.grid[row][column + i]) {
                    counter++;
                } else {
                    break;
                }
            }
        }
//checking down
        if (counter == numToWin) {
            return this.gameState = this.turn;

        }

        counter = 1;
        for (int i = 1; i <= numToWin; i++) {
            if (row - i >= 0) {

                if (this.grid[row][column] == this.grid[row - i][column]) {
                    counter++;

                } else {
                    break;
                }
            }
        }
        if (counter == numToWin) {
            return this.gameState = this.turn;

        }

        return gameState;
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nColumns; j++) {
                s += grid[i][j] + " | ";
            }
            s += "\n";
        }

        return s;
    }

}
