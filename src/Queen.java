

public class Queen extends Piece{
    public Queen(int color, Square l){
        super(color,l);
    }

    /*Vezir nasıl hareket eder? Gideceği yer aynı satır-sütun yada çaprazda 
    olsun yeter ve yolunda taş olmasın. Diğerlerinden pek farkı yok.*/
    @Override
    public boolean canMove(String coordinates) {
        Square targetLocation = this.location.getBoard().getSquareAt(coordinates);
        if(isFriend(targetLocation))
            return false;

        if(this.location.isAtSameColumn(targetLocation) || this.location.isAtSameDiagonal(targetLocation) || this.location.isAtSameRow(targetLocation)){
           Square[] between = this.location.getBoard().getSquaresBetween(location, targetLocation);
           return isPathOpen(between);
        }

        return false;
    }

    @Override
    public String toString() {
        return color == ChessBoard.WHITE ? "Q" : "q";
    }
}
