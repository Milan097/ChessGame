/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessgame;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 *
 * @author MILAN BHAI
 */
public class Button_Handler {
    public final String WHITE="white";
    public final String BLACK="black";
    JButton jb;
    int X,Y;
    ChessBoardGUI cbg;
    final Board chessboard;
    Button_Handler handler;
    static String curImage;
    Image img;
    static int previousX, previousY;

    static boolean turn = true; // true --> WHITE turn , false --> BLACK turn
    
    static Piece previousPiece;
    static ArrayList<Squares> previousPosMoves;
    ArrayList<Squares> posMoves;
    static int whitePawn = 0;
    static int whitePiece = 0;
    static int blackPawn = 0;
    static int blackPiece = 0;
    
    
    public Button_Handler() {
        this.chessboard = null;
        this.curImage = null;
    }

    public Button_Handler(int X, int Y, Board chessboard) {
        this.X = X;
        this.Y = Y;
        this.chessboard = chessboard;
        this.jb = new JButton();
        curImage = null;
    }
    
    public void setListener(int i,int j,ChessBoardGUI cbg,Button_Handler[][] handler) {
        cbg.boardSquares[i][j].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                int x = handler[i][j].getX();
                int y = handler[i][j].getY();
                
                Piece p = chessboard.squares[i][j].getPiece();
                if(sourceSelected(chessboard.squares)) {
                    System.out.println("Source selected");
                    
                    previousX = x;previousY = y;
                    previousPiece = p;
                    if(p == null) {
                        DialogMessage("No piece on the selected box.");
                        return;
                    }
                    if(turn == true && p.getColor().equals(BLACK)) {
                        DialogMessage("White's turn and selected Black piece");
                        return;
                    }
                    if(turn == false && p.getColor().equals(WHITE)) {
                        DialogMessage("Black's turn and selected White piece");
                        return;
                    }
                    if(isKingInDanger(cbg)) {
                        if(!(p instanceof King)) {
                            DialogMessage("King under check!");
                            System.out.println("Check");
                            clearAllHighlight(chessboard.squares);
                            return;
                        } else {
                            posMoves = p.move(chessboard.squares);
                            if(posMoves.isEmpty()) {
                                if(p.getColor().equals(WHITE)) 
                                    DialogMessage("Black Wins!!");
                                else
                                    DialogMessage("White Wins!!");
                                
                                System.exit(0);
                            } else {
                                Border emptyBorder = BorderFactory.createEmptyBorder();
                                cbg.boardSquares[x][y].setBorder(emptyBorder);
                                clearAllHighlight(chessboard.squares);
                            }
                        }
                    }
                    
                    curImage = p.getIdToPath(p.id);
                    posMoves = p.move(chessboard.squares);
                    if(posMoves.size() == 0) {
                        DialogMessage("No possible moves");
                        clearAllHighlight(chessboard.squares);
                    } else {
                        addHighlight(cbg, posMoves);
                        previousPosMoves = posMoves;
                    }
                    
                } else {
                    System.out.println("Destination");
                    if(p == null) {
                        if(previousPosMoves.contains(chessboard.squares[x][y])) {
                            setAndRemoveImage(handler, x, y);
                            setAndRemovePiece(x, y);
                            removeHighlight(cbg, previousPosMoves, chessboard.squares);
                        } else {
                            DialogMessage("Illegal moves");
                            return;
                        }
                    } else {
                        if(x == previousX && y == previousY) {
                            removeHighlight(cbg, posMoves, chessboard.squares);
                            return;
                        }
                        if(previousPosMoves.contains(chessboard.squares[x][y])) {
                            // Take Image of attackedPiece
                            String attackedPieceImage = p.getIdToPath(p.getId());
                            
                            // Set the dead piece image
                            setDeadPiece(cbg, p, attackedPieceImage);
                            
                            // set and remove piece image from squares
                            setAndRemoveImage(handler, x, y);
                            
                            // set and remove piece objects from squares
                            setAndRemovePiece(x, y);
                            
                            removeHighlight(cbg, previousPosMoves, chessboard.squares);
                            
                            previousX = x;
                            previousY = y;
                        } else {
                            DialogMessage("Illegal moves");
                            return;
                        }
                    }
                    
                    changeTurn();
                    
                    if(turn == true)
                        cbg.Game_Result.setText("WHITE to Play");
                    else 
                        cbg.Game_Result.setText("BLACK to Play");
                }
            }
        });
    }
    
    public boolean sourceSelected(Squares[][] curState) {
        for(int i=0;i<8;i++) {
            for(int j=0;j<8;j++) {
                if(curState[i][j].highlight == true) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public void addHighlight(ChessBoardGUI cbg, ArrayList<Squares> posMoves) {
        for(Squares sq : posMoves) {
            int x = sq.getxPos();
            int y = sq.getyPos();
            
            Border greenBorder = new LineBorder(Color.GREEN, 8);
            Border redBorder = new LineBorder(Color.RED, 8);
            
            if(sq.getPiece() != null) 
                cbg.boardSquares[x][y].setBorder(redBorder);
            else
                cbg.boardSquares[x][y].setBorder(greenBorder);
        }
    }
    
    public void removeHighlight(ChessBoardGUI cbg, ArrayList<Squares> posMoves, Squares[][] curState) {
        for(Squares sq : posMoves) {
            int x = sq.getxPos();
            int y = sq.getyPos();
            
            Border emptyBorder = BorderFactory.createEmptyBorder();
            cbg.boardSquares[x][y].setBorder(emptyBorder);
        }
        
        clearAllHighlight(curState);
    }
    
    public void clearAllHighlight(Squares[][] curState) {
        for(int i=0;i<8;i++) {
            for(int j=0;j<8;j++) {
                curState[i][j].highlight = false;
            }
        }
    }
    
    public void setAndRemoveImage(Button_Handler[][] handler, int x, int y) {
        try {
            img = ImageIO.read(getClass().getResource("/Images/"+curImage));
        } catch(IOException e) {
            e.printStackTrace();
        }
        
        handler[x][y].jb.setIcon(new ImageIcon(img));
        handler[previousX][previousY].jb.setIcon(new ImageIcon());
    }
    
    public void setAndRemovePiece(int x, int y) {
        previousPiece.setX(x);
        previousPiece.setY(y);
        
        chessboard.squares[x][y].setPiece(previousPiece);
        
        chessboard.squares[previousX][previousY].setPiece(null);
    }
    
    public void setDeadPiece(ChessBoardGUI cbg, Piece p, String s) {
        if(p.getColor().equals(WHITE)) {
            if(p instanceof Pawn) {
                try {
                    Image img = ImageIO.read(getClass().getResource("/Dead/2.png"));
                    cbg.Dead_Pawns_white[whitePawn].setIcon(new ImageIcon(img));
                    whitePawn++;
                } catch(IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    Image img = ImageIO.read(getClass().getResource("/Dead/"+p.getIdToPath(p.getId())));
                    cbg.Dead_pieces_white[whitePiece].setIcon(new ImageIcon(img));
                    whitePiece++;
                } catch(IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            if(p instanceof Pawn) {
                try {
                    Image img = ImageIO.read(getClass().getResource("/Dead/1.png"));
                    cbg.Dead_Pawns_black[blackPawn].setIcon(new ImageIcon(img));
                    blackPawn++;
                } catch(IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    Image img = ImageIO.read(getClass().getResource("/Dead/"+p.getIdToPath(p.getId())));
                    cbg.Dead_pieces_black[blackPiece].setIcon(new ImageIcon(img));
                    blackPiece++;
                } catch(IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public void changeTurn() {
        turn = !turn;
    }
    
    public boolean isKingInDanger(ChessBoardGUI cbg) {
        King k;
        if(turn == true)
            k = getWhiteKing();
        else
            k = getBlackKing();
        
        if(k.isCheckMate(chessboard.squares, k.getX(), k.getY())) {
            if(k.color.equals(WHITE)) {
                cbg.Game_Result.setText("BLACK WINS !");
                DialogMessage("CHECKMATE ! BLACK WINS !!\r\n");
                System.exit(0);
            }
            if(k.color.equals(BLACK)) {
                cbg.Game_Result.setText("WHITE WINS !");
                DialogMessage("CHECKMATE ! WHITE WINS !!\r\n");
                System.exit(0);
            }
        }
        
        if(k.isKingInDanger(chessboard.squares, k.getX(), k.getY())) {
            Border redBorder = new LineBorder(Color.red, 8);
            cbg.boardSquares[k.getX()][k.getY()].setBorder(redBorder);
            return true;
        }
        
        return false;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }
    
    public King getWhiteKing() {
        return (King)chessboard.wKing;
    }
    
    public King getBlackKing() {
        return (King)chessboard.bKing;
    }
    
    public void DialogMessage(String s) {
        JOptionPane.showMessageDialog(null, s);
    }
}
