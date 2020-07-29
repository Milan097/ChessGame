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
public class King extends Piece{
    
    boolean moved;
    
    public King(String id, int x, int y, String color) {
        this.X = x;
        this.Y = y;
        this.chessPos = "K";
        this.id = id;
        this.color = color;
        this.moved = false;
        this.posMoves = new ArrayList<>();
        this.idToPath = new HashMap<>();
        
        if(this.color.equals(WHITE)) {
            this.setIdToPath(id, "74.png");
        } else {
            this.setIdToPath(id, "04.png");
        }
    }
    
    @Override
    public ArrayList<Squares> move(Squares curState[][]) {
        this.posMoves.clear();
        int x = this.X;
        int y = this.Y;
        int xPos[] = {x, x-1, x-1, x-1, x, x+1, x+1, x+1};
        int yPos[] = {y-1, y-1, y, y+1, y+1, y+1, y, y-1};
        
        for(int i=0;i<8;i++) {
            if(xPos[i]>=0 && xPos[i]<=7 && yPos[i]>=0 && yPos[i]<=7) {
                if(curState[xPos[i]][yPos[i]].getPiece() == null) {
                    if(!isKingInDanger(curState, xPos[i], yPos[i])) {
                        curState[xPos[i]][yPos[i]].highlight = true;
                        posMoves.add(curState[xPos[i]][yPos[i]]);
                    }
                } else {
                    if(!curState[xPos[i]][yPos[i]].getPiece().getColor().equals(this.getColor())) {
                        if(isKingInDanger(curState, xPos[i], yPos[i]) == false) {
                            curState[xPos[i]][yPos[i]].highlight = true;
                            posMoves.add(curState[xPos[i]][yPos[i]]);
                        }
                    }
                }
            }
        }
        return posMoves;
    }
    
    public boolean isKingInDanger(Squares curState[][], int x,int y) {
        // Here we need to check for all peices to make the King safe
        
        // If we check for Queen then no need to check for Rook and Bishoop...
        // Horizontally left moves 
        for(int i=y-1;i>=0;i--) {
            // If there is no piece on a square
            if(curState[x][i].getPiece() == null) {
                continue;
            }
            // If the piece is there then rook can't go further
            else {
                // If it's enemy then we can move to that also
                if(!curState[x][i].getPiece().getColor().equals(this.getColor())) {
                    if((curState[x][i].getPiece() instanceof Rook) || (curState[x][i].getPiece() instanceof Queen))
                        return true;
                }
                break;
            }
        }
        
        // Horizontally right moves
        for(int i=y+1;i<=7;i++) {
            // If there is no piece on a square
            if(curState[x][i].getPiece() == null) {
                continue;
            }
            // If the piece is there then rook can't go further
            else {
                // If it's enemy then we can move to that also
                if(!curState[x][i].getPiece().getColor().equals(this.getColor())) {
                    if((curState[x][i].getPiece() instanceof Rook) || (curState[x][i].getPiece() instanceof Queen))
                        return true;
                }
                break;
            }
        }
        
        // Vertically upwards
        for(int i=x+1;i<=7;i++) {
            // If there is no piece on a square
            if(curState[i][y].getPiece() == null) {
                continue;
            }
            // If the piece is there then rook can't go further
            else {
                // If it's enemy then we can move to that also
                if(!curState[i][y].getPiece().getColor().equals(this.getColor())) {
                    if((curState[i][y].getPiece() instanceof Rook) || (curState[i][y].getPiece() instanceof Queen))
                        return true;
                }
                break;
            }
        }
        
        // Vertically downwards
        for(int i=x-1;i>=0;i--) {
            // If there is no piece on a square
            if(curState[i][y].getPiece() == null) {
                continue;
            }
            // If the piece is there then rook can't go further
            else {
                // If it's enemy then we can move to that also
                if(!curState[i][y].getPiece().getColor().equals(this.getColor())) {
                    if((curState[i][y].getPiece() instanceof Rook) || (curState[i][y].getPiece() instanceof Queen))
                        return true;
                }
                break;
            }
        }
        
        // Move Diagonally left-up
        for(int i=x-1,j=y-1 ; i>=0&&j>=0 ; i--,j--) {
            // If there is no piece on a square
            if(curState[i][j].getPiece() == null) {
                continue;
            } 
            else {
                if(!curState[i][j].getPiece().getColor().equals(this.getColor())) {
                    if((curState[i][j].getPiece() instanceof Bishoop) || (curState[i][j].getPiece() instanceof Queen))
                        return true;
                }
                break;
            }
        }
        
        // Move Diagonally left-down
        for(int i=x+1,j=y-1 ; i<=7&&j>=0 ; i++,j--) {
            // If there is no piece on a square
            if(curState[i][j].getPiece() == null) {
                continue;
            } 
            else {
                System.out.println(i+" "+j+" "+curState[i][j].getPiece());
                if(!curState[i][j].getPiece().getColor().equals(this.getColor())) {
                    if((curState[i][j].getPiece() instanceof Bishoop) || (curState[i][j].getPiece() instanceof Queen))
                        return true;
                }
                break;
            }
        }
        
        // Move Diagonally right-up
        for(int i=x-1,j=y+1 ; i>=0&&j<=7 ; i--,j++) {
            // If there is no piece on a square
            if(curState[i][j].getPiece() == null) {
                continue;
            } 
            // If the piece is there then rook can't go further
            else {
                if(!curState[i][j].getPiece().getColor().equals(this.getColor())) {
                    if((curState[i][j].getPiece() instanceof Bishoop) || (curState[i][j].getPiece() instanceof Queen))
                        return true;
                }
                break;
            }
        }
        
        // Move Diagonally right-down
        for(int i=x+1,j=y+1 ; i<=7&&j<=7 ; i++,j++) {
            // If there is no piece on a square
            if(curState[i][j].getPiece() == null) {
                continue;
            } 
            // If the piece is there then rook can't go further
            else {
                if(!curState[i][j].getPiece().getColor().equals(this.getColor())) {
                    if((curState[i][j].getPiece() instanceof Bishoop) || (curState[i][j].getPiece() instanceof Queen))
                        return true;
                }
                break;
            }
        }
        
        // Now check for Knight's moves
        int xPos[] = {x-1,x-2,x-2,x-1,x+1,x+2,x+2,x+1}; 
        int yPos[] = {y-2,y-1,y+1,y+2,y-2,y-1,y+1,y+2};
        
        for(int i=0;i<8;i++) {
            if(xPos[i] >= 0 && xPos[i] <= 7 && yPos[i] >= 0 && yPos[i] <= 7) {
                if(curState[xPos[i]][yPos[i]].getPiece() == null) {
                    continue;
                } else {
                    if(!curState[xPos[i]][yPos[i]].getPiece().getColor().equals(this.getColor())) {
                        if(curState[xPos[i]][yPos[i]].getPiece() instanceof Knight)
                            return true;
                    }
                }
            }
        }
        
        // Now check for Pawn's moves...
        // White King
        if(this.getColor().equals("white")) {
            if(x-1>=0 && y-1>=0 && curState[x-1][y-1].getPiece() != null) {
                if(curState[x-1][y-1].getPiece().getColor().equals("black") && (curState[x-1][y-1].getPiece() instanceof Pawn))
                    return true;
            }
            if(x-1>=0 && y+1<=7 && curState[x-1][y+1].getPiece() != null) {
                if(curState[x-1][y+1].getPiece().getColor().equals("black") && (curState[x-1][y+1].getPiece() instanceof Pawn))
                    return true;
            }
        }
        // Black King
        else {
            if(x+1<=7 && y-1>=0 && curState[x+1][y-1].getPiece() != null) {
                if(curState[x+1][y-1].getPiece().getColor().equals("white") && (curState[x+1][y-1].getPiece() instanceof Pawn))
                    return true;
            }
            if(x+1<=7 && y+1<=7 && curState[x+1][y+1].getPiece() != null) {
                if(curState[x+1][y+1].getPiece().getColor().equals("white") && (curState[x+1][y+1].getPiece() instanceof Pawn))
                    return true;
            }
        }
        
        // If King is not in danger
        return false;
    }
    
    // CheckMate
    public boolean isCheckMate(Squares curState[][], int x,int y) {
        ArrayList<Squares> possibleMoves = move(curState);
        if(isKingInDanger(curState, x, y)) {
            if(possibleMoves.isEmpty())
                return true;
        }
        return false;
    }
    
    // This is to know whether castling is possible or not
    public boolean isMoved(King k) {
        if(k.moved)
            return true;
        return false;
    }
    
    public boolean canCastle(Squares curState[][]) {
        if(isMoved(this))
            return false;
        // White King
        if(this.getColor().equals("white")) {
            // short castle
            if(curState[7][5].getPiece() == null && curState[7][6].getPiece() == null) {
                if(curState[7][7].getPiece().getId().equals("wRook2")) 
                    return true;
            }
            
            // long castle
            if(curState[7][1].getPiece() == null && curState[7][2].getPiece() == null && curState[7][3].getPiece() == null) {
                if(curState[7][0].getPiece().getId().equals("wRook1")) 
                    return true;
            }
            
            return false;
        }
        // Black King
        else {
            if(curState[0][5].getPiece() == null && curState[0][6].getPiece() == null) {
                if(curState[0][7].getPiece().getId().equals("bRook2")) 
                    return true;
            }
            
            // long castle
            if(curState[0][1].getPiece() == null && curState[0][2].getPiece() == null && curState[0][3].getPiece() == null) {
                if(curState[0][0].getPiece().getId().equals("wRook1")) 
                    return true;
            }
            return false;
        }
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
