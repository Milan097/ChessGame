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
public class Queen extends Piece{
    public Queen(String id, int x, int y, String color) {
        this.X = x;
        this.Y = y;
        this.chessPos = "Q";
        this.id = id;
        this.color = color;
        this.posMoves = new ArrayList<>();
        this.idToPath = new HashMap<>();
        
        if(this.color.equals(WHITE)) {
            this.setIdToPath(id, "73.png");
        } else {
            this.setIdToPath(id, "03.png");
        }
    }
    
    @Override
    public ArrayList<Squares> move(Squares curState[][]) {
        this.posMoves.clear();
        int x = this.X;
        int y = this.Y;
        // -------------------------------------------------------------------- //
        // Rook's all moves
        
        // Horizontally left moves 
        for(int i=y-1;i>=0;i--) {
            // If there is no piece on a square
            if(curState[x][i].getPiece() == null) {
                curState[x][i].highlight = true;
                posMoves.add(curState[x][i]);
            }
            // If the piece is there then rook can't go further
            else {
                // If it's enemy then we can move to that also
                if(!curState[x][i].getPiece().getColor().equals(this.getColor())) {
                    curState[x][i].highlight = true;
                    posMoves.add(curState[x][i]);
                }
                break;
            }
        }
        
        // Horizontally right moves
        for(int i=y+1;i<=7;i++) {
            // If there is no piece on a square
            if(curState[x][i].getPiece() == null) {
                curState[x][i].highlight = true;
                posMoves.add(curState[x][i]);
            }
            // If the piece is there then rook can't go further
            else {
                // If it's enemy then we can move to that also
                if(!curState[x][i].getPiece().getColor().equals(this.getColor())) {
                    curState[x][i].highlight = true;
                    posMoves.add(curState[x][i]);
                }   
                break;
            }
        }
        
        // Vertically upwards
        for(int i=x+1;i<=7;i++) {
            // If there is no piece on a square
            if(curState[i][y].getPiece() == null) {
                curState[i][y].highlight = true;
                posMoves.add(curState[i][y]);
            }
            // If the piece is there then rook can't go further
            else {
                // If it's enemy then we can move to that also
                if(!curState[i][y].getPiece().getColor().equals(this.getColor())) {
                    curState[i][y].highlight = true;
                    posMoves.add(curState[i][y]);
                }   
                break;
            }
        }
        
        // Vertically downwards
        for(int i=x-1;i>=0;i--) {
            // If there is no piece on a square
            if(curState[i][y].getPiece() == null) {
                curState[i][y].highlight = true;
                posMoves.add(curState[i][y]);
            }
            // If the piece is there then rook can't go further
            else {
                // If it's enemy then we can move to that also
                if(!curState[i][y].getPiece().getColor().equals(this.getColor())) {
                    curState[i][y].highlight = true;
                    posMoves.add(curState[i][y]);
                }   
                break;
            }
        }
        
        // -------------------------------------------------------------------- //
        
        // Bishoop's All moves
        // Move Diagonally left-up
        for(int i=x-1,j=y-1 ; i>=0&&j>=0 ; i--,j--) {
            // If there is no piece on a square
            if(curState[i][j].getPiece() == null) {
                curState[i][j].highlight = true;
                posMoves.add(curState[i][j]);
            } 
            // If the piece is there then rook can't go further
            else {
                if(!curState[i][j].getPiece().getColor().equals(this.getColor())) {
                    curState[i][j].highlight = true;
                    posMoves.add(curState[i][j]);
                }
                break;
            }
        }
        
        // Move Diagonally left-down
        for(int i=x+1,j=y-1 ; i<=7&&j>=0 ; i++,j--) {
            // If there is no piece on a square
            if(curState[i][j].getPiece() == null) {
                curState[i][j].highlight = true;
                posMoves.add(curState[i][j]);
            } 
            // If the piece is there then rook can't go further
            else {
                if(!curState[i][j].getPiece().getColor().equals(this.getColor())) {
                    curState[i][j].highlight = true;
                    posMoves.add(curState[i][j]);
                }
                break;
            }
        }
        
        // Move Diagonally right-up
        for(int i=x-1,j=y+1 ; i>=0&&j<=7 ; i--,j++) {
            // If there is no piece on a square
            if(curState[i][j].getPiece() == null) {
                curState[i][j].highlight = true;
                posMoves.add(curState[i][j]);
            } 
            // If the piece is there then rook can't go further
            else {
                if(!curState[i][j].getPiece().getColor().equals(this.getColor())) {
                    curState[i][j].highlight = true;
                    posMoves.add(curState[i][j]);
                }
                break;
            }
        }
        
        // Move Diagonally right-down
        for(int i=x+1,j=y+1 ; i<=7&&j<=7 ; i++,j++) {
            // If there is no piece on a square
            if(curState[i][j].getPiece() == null) {
                curState[i][j].highlight = true;
                posMoves.add(curState[i][j]);
            } 
            // If the piece is there then rook can't go further
            else {
                if(!curState[i][j].getPiece().getColor().equals(this.getColor())) {
                    curState[i][j].highlight = true;
                    posMoves.add(curState[i][j]);
                }
                break;
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
