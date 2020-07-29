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
public class Knight extends Piece{
    public Knight(String id, int x, int y, String color) {
        this.X = x;
        this.Y = y;
        this.chessPos = "N";
        this.id = id;
        this.color = color;
        this.posMoves = new ArrayList<>();
        this.idToPath = new HashMap<>();
        
        if(this.color.equals(WHITE)) {
            this.setIdToPath(id, "71.png");
        } else {
            this.setIdToPath(id, "01.png");
        }
    }
    
    @Override
    public ArrayList<Squares> move(Squares curState[][]) {
        this.posMoves.clear();
        int x = this.X;
        int y = this.Y;
        int xPos[] = {x-1,x-2,x-2,x-1,x+1,x+2,x+2,x+1}; 
        int yPos[] = {y-2,y-1,y+1,y+2,y-2,y-1,y+1,y+2};
        
        for(int i=0;i<8;i++) {
            if(xPos[i] >= 0 && xPos[i] <= 7 && yPos[i] >= 0 && yPos[i] <= 7) {
                if(curState[xPos[i]][yPos[i]].getPiece() == null) {
                    curState[xPos[i]][yPos[i]].highlight = true;
                    posMoves.add(curState[xPos[i]][yPos[i]]);
                } else {
                    if(!curState[xPos[i]][yPos[i]].getPiece().getColor().equals(this.getColor())) {
                        curState[xPos[i]][yPos[i]].highlight = true;
                        posMoves.add(curState[xPos[i]][yPos[i]]);
                    }
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
