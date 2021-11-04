

public class Knight extends Piece{

    public Knight(int color, Square l){
        super(color, l);
    }
    

    @Override
    public boolean canMove(String coordinates) {
        Square targetLocation = this.location.getBoard().getSquareAt(coordinates);
        if(isFriend(targetLocation))
            return false;

        int colDif = Math.abs(this.location.getLocation()[0]-targetLocation.getLocation()[0]);
        int rowDif = Math.abs(this.location.getLocation()[1]-targetLocation.getLocation()[1]);
        if((colDif == 1 && rowDif == 2) || (colDif ==2 && rowDif == 1))
            return true;
        return false;
    }

    @Override
    public String toString() {
        return color == ChessBoard.WHITE ? "N" : "n";
    }
}
