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
public class Pawn extends Piece {
    int cnt;
    public Pawn(String id, int x, int y, String color) {
        this.X = x;
        this.Y = y;
        this.chessPos = "";
        this.cnt = 0;
        this.id = id;
        this.color = color;
        this.posMoves = new ArrayList<>();
        this.idToPath = new HashMap<>();
        
        if(this.color.equals(WHITE)) {
            this.setIdToPath(id, "2.png");
        } else {
            this.setIdToPath(id, "1.png");
        }
    }
    
    @Override
    public ArrayList<Squares> move(Squares curState[][]) {
        posMoves.clear();
        int x = this.X;
        int y = this.Y;
        // White Pawns
        if(this.getColor().equals(WHITE)) {
            // For the Pawns moving first time
            if(x == 6) {
                if(curState[x-1][y].getPiece() == null && curState[x-2][y].getPiece() == null) {
                    curState[x-2][y].highlight = true;
                    this.posMoves.add(curState[x-2][y]);
                }
            }
            
            // For the Normal Pawns move
            if(x-1 >= 0 && curState[x-1][y].getPiece() == null) {
                curState[x-1][y].highlight = true;
                this.posMoves.add(curState[x-1][y]);
            }
            
            // For move diagonally left 
            if(x-1>=0 && y-1>=0 && curState[x-1][y-1].getPiece() != null) {
                if(!curState[x-1][y-1].getPiece().getColor().equals(this.getColor())) {
                    curState[x-1][y-1].highlight = true;
                    this.posMoves.add(curState[x-1][y-1]);
                }
            }
            
            // For move diagonally right
            if(x-1>=0 && y+1<=7 && curState[x-1][y+1].getPiece() != null) {
                if(!curState[x-1][y+1].getPiece().getColor().equals(this.getColor())) {
                    curState[x-1][y+1].highlight = true;
                    this.posMoves.add(curState[x-1][y+1]);
                }
            }
        } 
        // Black Pawns
        else {
            // For the Pawns moving first time
            if(x == 1) {
                if(curState[x+1][y].getPiece() == null && curState[x+2][y].getPiece() == null) {
                    curState[x+2][y].highlight = true;
                    this.posMoves.add(curState[x+2][y]);
                }
            }
            
            // For the Normal Pawns move
            if(x+1 <= 7 && curState[x+1][y].getPiece() == null) {
                curState[x+1][y].highlight = true;
                this.posMoves.add(curState[x+1][y]);
            }
            
            // For move diagonally left 
            if(x+1<=7 && y-1>=0 && curState[x+1][y-1].getPiece() != null) {
                if(!curState[x+1][y-1].getPiece().getColor().equals(this.getColor())) {
                    curState[x+1][y-1].highlight = true;
                    this.posMoves.add(curState[x+1][y-1]);
                }
            }
            
            // For move diagonally right
            if(x+1<=7 && y+1<=7 && curState[x+1][y+1].getPiece() != null) {
                if(!curState[x+1][y+1].getPiece().getColor().equals(this.getColor())) {
                    curState[x+1][y+1].highlight = true;
                    this.posMoves.add(curState[x+1][y+1]);
                }
            }
        }
        
        return posMoves;
    }
    
    @Override
    public String getIdToPath(String id) {
        return this.idToPath.get(id);
    }
    
    @Override
    public void setIdToPath(String id, String imgName) {
        this.idToPath.put(id, imgName);
    }
}
