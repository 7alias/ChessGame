public class Pawn extends ChessPiece{
    public Pawn(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        //all coordinates exist
        if (checkPos(line)
                && checkPos(column)
                && checkPos(toLine)
                && checkPos(toColumn)
            // there is a figure in this cell
        && chessBoard.board[line][column] != null){
            //cannot move obliquely
            if (column == toColumn){
                int dir; // how many cells forward
                int start;
                if (color.equals("White")) {  //for white figures
                    dir = 1; //direction of move up for white
                    start = 1; //starting line
                } else  { //for black figures
                    dir = -1; // move direction for black
                    start = 6; // black pawn starting line
            }
                // check if can move to position
                 if (line + dir == toLine) {
                     //true if cell is empty
                     return chessBoard.board[toLine][toColumn] == null;
                 }
                 //if starting position can move 2 cells
                if (line == start
                        && line +2 * dir == toLine){
                    // if ending position is empty and there is no figures on the way
                    return chessBoard.board[toLine][toColumn]== null
                            && chessBoard.board[line + dir][column] == null;
                }
                 }
        } else { // cuts cases
            // move by 1
            if ((column - toColumn == 1 || column - toColumn == -1)
                    &&(line - toLine == 1 || line - toLine == -1)
                    // if there is a figure on the cell
                    && chessBoard.board[toLine][toColumn] != null) {
                // if the color of beaten figure not equals current: return true
                return !chessBoard.board[toLine][toColumn].getColor().equals((color));
            } else return false;

        }
return true;
    }

    @Override
    public String getSymbol() {
        return "P";
    }
}
