
import javafx.scene.control.Button;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Seun Lawal 101039762
 */
public class ConnectButton extends Button {

    private int row;
    private int column;
    
    /**
     * Connect Four Button constructor
     * @param label Name of the Button
     * @param row Row of the Button
     * @param column Column of the Button
     */

    public ConnectButton(String label, int row, int column) {
        super(label);
        this.row = row;
        this.column = column;

    }
/**
 * Gets the Row of the button
 * @return the Row Value 
 */
    public int getRow() {
        return this.row;
    }

    /**
 * Gets the Column of the button
 * @return the column Value 
 */
    public int getColumn() {
        return this.column;
    }

    @Override
    public String toString() {
        String s1 = "";
        s1 = s1 + "(" + this.row + "," + this.column + ")";
        return s1;

    }
}
