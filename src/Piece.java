

public abstract class Piece {
    public int color;
    public Square location;

    public Piece(int c,Square l){
        this.color = c;
        this.location = l;
       
        this.location.setPiece(this);
    }

    /*Since the path must be empty in most of the stones, all the
    squares in the given series of squares are checked to see if they are empty..*/
    public boolean isPathOpen(Square[] sArray){
        for(Square s: sArray){
            if(!s.isEmpty()){
                return false;
            }
        }
        return true;
    }

    /*To check if there is a piece of the same team in the target frame.
    Because if he can not go there.*/
    public boolean isFriend(Square targetLocation){
        if(!targetLocation.isEmpty() && targetLocation.getPiece().getColor() == this.color)
            return true;
        return false;
    }

    public abstract boolean canMove(String coordinates);
    
    public void move(String coordinates){
        Square targetLocation = location.getBoard().getSquareAt(coordinates);

        targetLocation.setPiece(this);

        location.clear();
        location = targetLocation;
        location.getBoard().nextPlayer();
    }

    public int getColor(){
        return this.color;
    }


}
