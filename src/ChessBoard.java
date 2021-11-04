

import java.util.ArrayList;

public class ChessBoard {
    private boolean whitePlaying;
    public static int WHITE = 0;
    public static int BLACK = 1;
    public static String abc = "abcdefgh";
    public static  String l123 = "12345678";

    private int whitePieces = 16;
    private  int blackPieces = 16;

    private Square[][] chessBoard = new Square[8][8];

    /*I keep the squares on the board in a two-dimensional array.
    I create this series and then edit the frames inside the series one by one with loops.*/
    public ChessBoard(){
        int row = 0;
        for(Square[] squareRow : this.chessBoard){
            for(int column = 0; column < 8; column++){
                squareRow[column]= new Square(column, row, this);
            }
            row++;
        }
        this.initialize();
    }


    private void initialize(){
        this.whitePlaying = true;

        for(int i = 8; i>0;i--){
            new Pawn(ChessBoard.BLACK, this.chessBoard[6][i-1]);
        }
        for(int i = 8; i>0;i--){
            new Pawn(ChessBoard.WHITE, this.chessBoard[1][i-1]);
        }

        new Knight(ChessBoard.BLACK, this.chessBoard[7][1]);
        new Knight(ChessBoard.BLACK, this.chessBoard[7][6]);
        new Knight(ChessBoard.WHITE, this.chessBoard[0][1]);
        new Knight(ChessBoard.WHITE, this.chessBoard[0][6]);

        new Queen(ChessBoard.BLACK, this.chessBoard[7][4]);
        new Queen(ChessBoard.WHITE, this.chessBoard[0][4]);

        new Bishop(ChessBoard.BLACK, this.chessBoard[7][2]);
        new Bishop(ChessBoard.BLACK, this.chessBoard[7][5]);
        new Bishop(ChessBoard.WHITE, this.chessBoard[0][2]);
        new Bishop(ChessBoard.WHITE, this.chessBoard[0][5]);

        new Rook(ChessBoard.BLACK, this.chessBoard[7][0]);
        new Rook(ChessBoard.BLACK, this.chessBoard[7][7]);
        new Rook(ChessBoard.WHITE, this.chessBoard[0][0]);
        new Rook(ChessBoard.WHITE, this.chessBoard[0][7]);

        new King(ChessBoard.BLACK, this.chessBoard[7][3]);
        new King(ChessBoard.WHITE, this.chessBoard[0][3]);
    }

    
    public void decrementPiece(int color){
        if(color == ChessBoard.WHITE)
            this.whitePieces--;
        else
            this.blackPieces--;
    }

  
    public Square getSquareAt(String coordinates){
        int column;
        int row;
        column = abc.indexOf(coordinates.charAt(0));
        row = l123.indexOf(coordinates.charAt(1));
        return this.chessBoard[row][column];
    }
    public Piece getPieceAt(String coordinates){
        return this.getSquareAt(coordinates).getPiece();
    }

  
  
    public Square[] getSquaresBetween(Square first, Square last){
        Square[] squaresBetween;

        if(first.isAtSameDiagonal(last) || first.isAtSameColumn(last) || first.isAtSameRow(last)){
           int x1 = first.getLocation()[0];
           int y1 = first.getLocation()[1];

           int x2 = last.getLocation()[0];
           int y2 = last.getLocation()[1];
           squaresBetween = new Square[Math.max(Math.abs(x1-x2),Math.abs(y1-y2))-1];

           for(int i = 0; i < squaresBetween.length; i++){
               if(x1!=x2)
                    x1 = x1<x2 ? x1+1 : x1-1;
               if(y1!=y2)
                    y1 = y1<y2 ? y1+1 : y1-1;
               squaresBetween[i] = chessBoard[y1][x1];
           }

            return squaresBetween;

        }
        return new Square[0];
    }

   
    public boolean isGameEnded(){
        return this.whitePieces == 0 || this.blackPieces == 0;
    }

    //to understand who's next.
    public boolean isWhitePlaying(){
        return whitePlaying;
    }
    //change players.
    public void nextPlayer(){
        this.whitePlaying = !this.whitePlaying;
    }

   
    @Override
    public String toString(){
        String outputBoard = "";

        outputBoard +="    A   B   C   D   E   F   G   H   \n";
        for(int rowCoordinates = 8; rowCoordinates > 0; rowCoordinates-- ) {
            Square[] squareRow = chessBoard[rowCoordinates-1];

            outputBoard +="  ---------------------------------  \n";
            outputBoard += rowCoordinates+" ";
            for(Square square : squareRow){
                outputBoard += "|"+square.toString();
            }
            outputBoard += "| "+rowCoordinates+" ";
            outputBoard += "\n";
        }
        outputBoard +="  ---------------------------------  \n";
        outputBoard +="    A   B   C   D   E   F   G   H  \n";
        return outputBoard;
    }

}
