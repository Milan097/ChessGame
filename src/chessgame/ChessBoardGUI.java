/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessgame;

import java.awt.*;
import javax.imageio.ImageIO;
import static javax.swing.SwingConstants.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

/**
 *
 * @author MILAN BHAI
 */
public class ChessBoardGUI {
    private final JPanel mainGui;
    public JButton[][] boardSquares;
    private JPanel board;
    private JPanel player_stats;
    private final Board chessBoard;
    private JLabel white;
    private JLabel black;
    private JLabel player1;
    private JLabel player2;
    private JLabel dead;
    public JLabel []Dead_pieces_white;
    public JLabel []Dead_Pawns_white;
    public JLabel []Dead_pieces_black;
    public JLabel []Dead_Pawns_black;
    public JLabel Game_Result;
    public TextField Name1;
    public TextField Name2;
    public JLabel msg;
    private static final String COLS = "abcdefgh";
    private FlowLayout layout;
    private Image img;
    public Thread thread;

    public ChessBoardGUI() {
        this.mainGui = null;
        this.msg = null;
        this.chessBoard = null;
    }
    
    public ChessBoardGUI(Board chessBoard) {
        this.chessBoard = chessBoard;
        mainGui = new JPanel(new BorderLayout());
        // Initialize all Components to base
        initializeComp();   
        
        // Initialize the mainGui to set Chess GUI
        initializeMainGui();
    }
    
    private void initializeComp() {
        mainGui.setPreferredSize(new Dimension(600, 600));
        board = new JPanel(new GridLayout(9, 9));
        layout = new FlowLayout();
        player_stats = new JPanel(layout);
        
        Dead_Pawns_black = new JLabel[8];
        Dead_pieces_black = new JLabel[7];
        Dead_Pawns_white = new JLabel[8];
        Dead_pieces_white = new JLabel[7];
        
        boardSquares = new JButton[8][8];
        msg = new JLabel("Ready...");
        thread = new Thread();
    }
    
    private void initializeMainGui() {
        mainGui.setBorder(new LineBorder(Color.WHITE));
        board.setBorder(new LineBorder(Color.BLACK));
        board.setPreferredSize(new Dimension(1100,1000));
        player_stats.setBorder(new LineBorder(Color.BLACK));
        player_stats.setPreferredSize(new Dimension(400, 1000));
        layout.setAlignment(FlowLayout.TRAILING);
        layout.setHgap(10);
        layout.setVgap(25);
        
        
        white = new JLabel("                    WHITE                           ");
        white.setFont(new Font("Times New Roman", Font.BOLD, 24));
        white.setForeground(Color.BLACK);
        player1 = new JLabel("Player 1 : ");
        player1.setFont(new Font("Times New Roman", Font.ITALIC, 18));
        Name1 = new TextField();
        Name1.setColumns(35);
        dead = new JLabel("Dead : ");
        dead.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        
        player_stats.add(white);
        player_stats.add(player1);
        player_stats.add(Name1);
        player_stats.add(dead);
        
        for(int i=0;i<8;i++) {
            Dead_Pawns_white[i] = new JLabel();
            Dead_Pawns_white[i].setPreferredSize(new Dimension(40, 40));
            player_stats.add(Dead_Pawns_white[i]);
        }
        for(int i=0;i<7;i++) {
            Dead_pieces_white[i] = new JLabel();
            Dead_pieces_white[i].setPreferredSize(new Dimension(40, 40));
            player_stats.add(Dead_pieces_white[i]);
        }
        
        JLabel Line=new JLabel();
        Line.setText("______________________________________________________");
        player_stats.add(Line);
        
        // PLAYER 1 finish....
        
        Game_Result = new JLabel("   Game Status");
        Game_Result.setPreferredSize(new Dimension(450,40));
        Game_Result.setFont(new Font("ALGERIAN",Font.BOLD, 24));
        Game_Result.setHorizontalAlignment(CENTER);
        Game_Result.setHorizontalTextPosition(CENTER);
        player_stats.add(Game_Result);
        JLabel Line1=new JLabel();
        Line1.setText("______________________________________________________");
        player_stats.add(Line1);
        
        // Game Result Finish...
        
        black = new JLabel("                    BLACK                              ");
        black.setFont(new Font("Times New Roman", Font.BOLD, 24));
        black.setForeground(Color.BLACK);
        player2 = new JLabel("Player 2 : ");
        player2.setFont(new Font("Times New Roman", Font.ITALIC, 18));
        Name2 = new TextField();
        Name2.setColumns(35);
        dead = new JLabel("Dead : ");
        dead.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        
        player_stats.add(black);
        player_stats.add(player2);
        player_stats.add(Name2);
        player_stats.add(dead);
        
        for(int i=0;i<8;i++) {
            Dead_Pawns_black[i] = new JLabel();
            Dead_Pawns_black[i].setPreferredSize(new Dimension(40, 40));
            player_stats.add(Dead_Pawns_black[i]);
        }
        for(int i=0;i<7;i++) {
            Dead_pieces_black[i] = new JLabel();
            Dead_pieces_black[i].setPreferredSize(new Dimension(40, 40));
            player_stats.add(Dead_pieces_black[i]);
        }
        
        // Player 2 finish...
        
        // Add Board and player to main gui
        player_stats.setBackground(Color.LIGHT_GRAY);
        board.setBackground(Color.BLACK);
        mainGui.setBackground(Color.BLACK);
        mainGui.add(board,BorderLayout.WEST);
        mainGui.add(player_stats,BorderLayout.EAST);
        
        
        // Now Start Board GUI
        
        Button_Handler[][] handler = new Button_Handler[8][8];
        int i,j;
        Insets buttonMargin = new Insets(0, 0, 0, 0);
        for(i = 0;i < boardSquares.length;i++) {
            for(j = 0;j < boardSquares[0].length;j++) {
                
                handler[i][j] = new Button_Handler(i, j, chessBoard);
                handler[i][j].jb.setMargin(buttonMargin);
                
                if((j%2==1 && i%2==1) || (j%2==0 && i%2==0)) {
                    handler[i][j].jb.setBackground(Color.white);
                } else {
                    handler[i][j].jb.setBackground(Color.blue);
                }
                
                try {
                    if(i == 1) {
                        img = ImageIO.read(getClass().getResource("/Images/1.png"));
                        handler[i][j].jb.setIcon(new ImageIcon(img));
                    } else if(i == 0) {
                        String str = "/Images/0"+j+".png";
                        img = ImageIO.read(getClass().getResource(str));
                        handler[i][j].jb.setIcon(new ImageIcon(img));
                    } else if(i == 7) {
                        String str = "/Images/7"+j+".png";
                        img = ImageIO.read(getClass().getResource(str));
                        handler[i][j].jb.setIcon(new ImageIcon(img));
                    } else if(i == 6) {
                        img = ImageIO.read(getClass().getResource("/Images/2.png"));
                        handler[i][j].jb.setIcon(new ImageIcon(img));
                    } else {
                        handler[i][j].jb.setIcon(null);
                    }
                } catch(Exception e) {
                    e.printStackTrace();
                }
                boardSquares[i][j] = handler[i][j].jb;
            }
        }
        
        board.add(new JLabel(""));
        for(i=0;i<8;i++) {
            JLabel jl = new JLabel(COLS.substring(i, i+1),SwingConstants.CENTER);
            jl.setForeground(Color.white);
            jl.setFont(new Font("Courier New", Font.CENTER_BASELINE, 18));
            board.add(jl);
        }
        
        for(i=0;i<8;i++) {
            for(j=0;j<8;j++) {
                if(j == 0) {
                    JLabel jl = new JLabel(""+(i+1),SwingConstants.CENTER);
                    jl.setForeground(Color.white);
                    jl.setFont(new Font("Courier New", Font.CENTER_BASELINE, 18));
                    board.add(jl);
                }
                board.add(boardSquares[i][j]);
                handler[i][j].setListener(i, j, this, handler);
            }
        }
        
    }

    public JPanel getMainGui() {
        return mainGui;
    }

    public JPanel getBoard() {
        return board;
    }
    
}
