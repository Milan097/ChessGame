/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessgame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.*;
import javax.imageio.ImageIO;

/**
 *
 * @author MILAN BHAI
 */
public class ChessGame extends javax.swing.JFrame {
    
    private JFrame frame;
    
    public ChessGame (Board chessBoard) throws IOException {
        frame = new JFrame();
        JPanel homeGui = new JPanel(new BorderLayout());
        JButton b = new JButton("");
        homeGui.setPreferredSize(new Dimension(1350,780));
        File img = new File("D://Workspace/My_projects/ChessGame/src/chessgame/home.jpg");
        if(img.exists())
            System.out.println("exist");
        else
            System.out.println("moo");
        
        BufferedImage image = ImageIO.read(img);
        b.setIcon(new ImageIcon(image));
        
        b.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                frame.setVisible(false);
                Runnable r = new Runnable() {

                    @Override
                    public void run() {
                        ChessBoardGUI cbg = new ChessBoardGUI(chessBoard);
                        
                        JFrame f = new JFrame("Chess King");
                        f.add(cbg.getMainGui());
                        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        f.setLocationByPlatform(true);
                        
                        // To display all elements within minimum frame
                        f.pack();
                        f.setSize(1650,1000);
                        f.setResizable(false);
                        f.setVisible(true);
                        
                        String Player1_name=JOptionPane.showInputDialog("Enter White Player Name:");
                        cbg.Name1.setText(Player1_name);
                        cbg.Name1.setFont(new Font("Courier New", Font.BOLD, 18));
                        
                        String Player2_name=JOptionPane.showInputDialog("Enter Black Player Name:");
                        cbg.Name2.setText(Player2_name);
                        cbg.Name2.setFont(new Font("Courier New", Font.BOLD, 18));   
                    }
                    
                };
                SwingUtilities.invokeLater(r);
            }
        
        });
        
        homeGui.add(b);
        frame.add(homeGui);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationByPlatform(true);
        
        frame.setSize(1080,720);
        frame.setResizable(false);
        frame.setVisible(true);
    }
    /**
     * @param args the command line arguments
     */
    public static ChessGame hs;
    
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        Board bb = new Board();
        hs = new ChessGame(bb);
    }
    
//    public static void playAgain() throws IOException{
//        
//        Board bb = new Board();
//        hs = new ChessGame(bb);
//    }
    
}
