/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessgame;

/**
 *
 * @author MILAN BHAI
 */
public class Squares {
    public String WHITE = "white";
    public String BLACK = "black";
    public int xPos;
    public int yPos;
    public String chessPos;
    public String color;
    public Piece piece;
    public boolean highlight;
    
    public Squares(int x,int y,boolean sqrColor) {
        this.xPos = x;
        this.yPos = y;
        if(sqrColor == true)
            this.color = WHITE;
        else
            this.color = BLACK;
        this.highlight = false;
            
        this.chessPos = Character.toString((char)(xPos + 97));
        this.chessPos += Character.toString((char)(yPos + 48 + 1));
        // this.p = null;
    }

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public String getChessPos() {
        return chessPos;
    }

    public void setChessPos(String chessPos) {
        this.chessPos = chessPos;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }
    
    public boolean isHighlight() {
        return highlight;
    }

    public void setHighlight(boolean highlight) {
        this.highlight = highlight;
    }
}
