

public class King extends Piece{

    public King(int color, Square l){
        super(color, l);
    }

    @Override
    public boolean canMove(String coordinates) {
        Square targetLocation = this.location.getBoard().getSquareAt(coordinates);
        if(isFriend(targetLocation))
            return false;

        if((location.isAtSameRow(targetLocation) && Math.abs(location.getLocation()[0]-targetLocation.getLocation()[0]) == 1 ) ||
                (this.location.isNeighborColumn(targetLocation) && (location.isAtSameDiagonal(targetLocation)))||
                Math.abs(location.getRowDistance(targetLocation)) == 1 )
            return true;
        return false;
    }

    @Override
    public String toString() {
        return color == ChessBoard.WHITE ? "K" : "k";
    }
}
