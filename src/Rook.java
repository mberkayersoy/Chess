

public class Rook extends Piece {

    public Rook(int color, Square l){
        super(color, l);
    }



    @Override
    public boolean canMove(String coordinates) {
        Square targetLocation = this.location.getBoard().getSquareAt(coordinates);
        if(isFriend(targetLocation))
            return false;

        if(this.location.isAtSameColumn(targetLocation) || this.location.isAtSameRow(targetLocation)){
            Square[] between = this.location.getBoard().getSquaresBetween(location, targetLocation);
            return isPathOpen(between);
        }

        return false;
    }

    @Override
    public String toString() {
        return color == ChessBoard.WHITE ? "R" : "r";
    }
}
