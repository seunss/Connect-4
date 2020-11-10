/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Seun Lawal 101039762
 */
public class ConnectMove {

    private int row;
    private int column;
    private ConnectFourEnum colour;
/**
 * Connect Move Button constructor 
 * @param row
 * @param column
 * @param colour 
 */
    ConnectMove(int row, int column, ConnectFourEnum colour) {
        this.row = row;
        this.column = column;
        this.colour = colour;

    }
/**
 * Gets the Row of the button/Move
 * @return the Row Value 
 */
    public int getRow() {
        return this.row;

    }
/**
 * Gets the Column of the button/Move
 * @return the column Value 
 */
    public int getColumn() {
        return this.column;

    }
/**
 * Gets the Color of the connect Button
 * @return 
 */
    public ConnectFourEnum getColour() {

        return this.colour;

    }

}
