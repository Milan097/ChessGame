/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessgame;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author MILAN BHAI
 */
public class Piece {
    String color;
    int X,Y;
    String chessPos;
    Board board;
    String id="";
    ArrayList<Squares> posMoves;
    HashMap<String, String> idToPath;
    public final String WHITE = "white";
    public final String BLACK = "black";

    public Piece() {
    }

    public ArrayList<Squares> move(Squares boardState[][]) {
        return null;
        //whenever you move a piece make sure you change the hasPiece attribute of the square
    }
    
    public int getX() {
        return X;
    }

    public void setX(int X) {
        this.X = X;
    }

    public int getY() {
        return Y;
    }

    public void setY(int Y) {
        this.Y = Y;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getId() {
        return id;
    }

    public String getIdToPath(String id) {
        return null;
    }

    public void setIdToPath(String id, String imgName) {
        
    }
    
    
}
