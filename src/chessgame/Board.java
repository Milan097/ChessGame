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
public class Board {
    final public String WHITE="white";
    final public String BLACK="black";
    
    public Squares[][] squares;
    Piece wRook1, wRook2, wKnight1, wKnight2, wBishoop1, wBishoop2, wQueen, wKing, wPawn[];
    Piece bRook1, bRook2, bKnight1, bKnight2, bBishoop1, bBishoop2, bQueen, bKing, bPawn[];
    
    public Board() {
        squares = new Squares[8][8];
        
        intializeBoard();
        intializePieces();
    }
    
    private void intializeBoard() {
        boolean sqrColor = true;
        for(int i=0;i<8;i++) {
            for(int j=0;j<8;j++) {
                squares[i][j] = new Squares(i, j, sqrColor);
                sqrColor = !sqrColor;
            }
        }
    }
    
    private void intializePieces() {
        wRook1 = new Rook("wRook1", 7, 0, WHITE);
        wRook2 = new Rook("wRook2", 7, 7, WHITE);
        wKnight1 = new Knight("wKnight1", 7, 1, WHITE);
        wKnight2 = new Knight("wKnight2", 7, 6, WHITE);
        wBishoop1 = new Bishoop("wBishoop1", 7, 2, WHITE);
        wBishoop2 = new Bishoop("wBishoop2", 7, 5, WHITE);
        wQueen = new Queen("wQueen", 7, 3, WHITE);
        wKing = new King("wKing", 7, 4, WHITE);
        
        wPawn = new Piece[8];
        for(int i=0;i<8;i++)
            wPawn[i] = new Pawn("wPawn"+(i+1), 6, i, WHITE);
        
        
        bRook1 = new Rook("bRook1", 0, 0, BLACK);
        bRook2 = new Rook("bRook2", 0, 7, BLACK);
        bKnight1 = new Knight("bKnight1", 0, 1, BLACK);
        bKnight2 = new Knight("bKnight2", 0, 6, BLACK);
        bBishoop1 = new Bishoop("bBishoop1", 0, 2, BLACK);
        bBishoop2 = new Bishoop("bBishoop2", 0, 5, BLACK);
        bQueen = new Queen("bQueen", 0, 3, BLACK);
        bKing = new King("bKing", 0, 4, BLACK);
        
        bPawn = new Piece[8];
        for(int i=0;i<8;i++)
            bPawn[i] = new Pawn("bPawn"+(i+1), 1, i, BLACK);
        
        
        squares[7][0].piece = wRook1;
        squares[7][1].piece = wKnight1;
        squares[7][2].piece = wBishoop1;
        squares[7][3].piece = wQueen;
        squares[7][4].piece = wKing;
        squares[7][5].piece = wBishoop2;
        squares[7][6].piece = wKnight2;
        squares[7][7].piece = wRook2;
        
        for(int i=0;i<8;i++)
            squares[6][i].piece = wPawn[i];
        
        squares[0][0].piece = bRook1;
        squares[0][1].piece = bKnight1;
        squares[0][2].piece = bBishoop1;
        squares[0][3].piece = bQueen;
        squares[0][4].piece = bKing;
        squares[0][5].piece = bBishoop2;
        squares[0][6].piece = bKnight2;
        squares[0][7].piece = bRook2;
        
        for(int i=0;i<8;i++)
            squares[1][i].piece = bPawn[i];
    }
}
