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
public class Player extends Thread{
    private String WHITE="white";
    private String BLACK="black";
    private String NAME;
    private String color;

    public Player(String color) {
        this.color = color;
    }
    
    public void updateGameStatus(Player p) {
        
    }

    public String getColor() {
        return color;
    }
    
}
