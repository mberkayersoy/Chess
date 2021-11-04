


public class Square {
    private int column,row;
    private Piece piece = null;
    private ChessBoard board;
    public Square(int c, int r, ChessBoard b){
        this.column = c;
        this.row = r;
        this.board = b;
    }
    public Square(int c, int r, ChessBoard b, Piece p){
        this.column = c;
        this.row = r;
        this.piece = p;
        this.board = b;
    }

    
    public boolean isEmpty(){
        return (this.piece == null);
    }

    /*Tahtanın son satırı olup olmadığını kontrol etmek için. 
    Şöyleki piyon rakibin tarafında son satıra ulaşırsa vezir olur 
    yani siyaha göre son satır 1 beyaza göre 8.*/
    public boolean isAtLastRow(int color){
        return (color == ChessBoard.WHITE && this.row == 7) || (color == ChessBoard.BLACK && this.row == 0);
    }

    /*Bunlar Piece.canMove() metodlarında özellikle kullandığım metodlar.
    Adlarında ne yazıyorsa o aslında. Kareler arasındaki ilişkleri belirlemek için.*/
    public boolean isNeighborColumn(Square s){
        return Math.abs(s.getLocation()[0]-this.column) == 1;
    }
    public boolean isAtSameColumn(Square s){
        return s.getLocation()[0] == this.column;
    }

    public boolean isAtSameDiagonal(Square s){
        return Math.abs(s.getLocation()[0]-this.column) == Math.abs(s.getLocation()[1]-this.row);
    }

    public boolean isAtSameRow(Square s){
        return s.getLocation()[1] == this.row;
    }
    public int getRowDistance(Square s){
        return this.row-s.getLocation()[1];
    }


    //Karedeki taş bilgisini temizliyor.
    public void clear(){
            this.piece = null;
    }

    @Override
    public String toString(){
        return this.piece == null ? "   " : " "+this.piece.toString()+" ";
    }

    //Piyon son satıra ulaşırsa yerine vezir koymak için.(bkz Pawn.move())
    public void putNewQueen(int color){
       this.setPiece(new Queen(color, this));
    }

    /*Kareye taş yerleştirmek için. Eğer daha önce orada bir rakip takım taşı
    bulunyorsa da onu silerken ChessBoard.whitePiece/blackPiece değişkenindeki miktarıda azaltıyor.
    (bkz ChessBoard.isGameEnded)*/
    public void setPiece(Piece p){
        if(this.piece != null){
            board.decrementPiece(this.piece.getColor());
        }
        this.piece = p;
    }

    //Burda hangi taş var? getBoard()'la aynı mantık.
    public Piece getPiece(){
        return this.piece;
    }

    //Karenin konumunu döndürüyor dizi olarak. [col,row]
    public int[] getLocation(){
        int[] location = {column, row};
        return location;
    }

    /*Kareleri oluşturuken buaraya geçen tahtayı çeğırmak için. 
    Böyle kare nesnesine sahip olan bir sınıf karenin bulundupu tahta nesnesine de erişebiliyor.*/
    public ChessBoard getBoard(){
        return board;
    }
}
