public class Horse extends ChessPiece {
    public Horse(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        //all coordinates exist
        if (checkPos(line) && checkPos(column) && checkPos(toLine) && checkPos(toColumn)) {
            //starting position is not equal ending position
            if (line != toLine && column != toColumn &&
                    //and last cell is empty nor color in ending cell equals current
                    (chessBoard.board[toLine][toColumn] == null || !chessBoard.board[toLine][toColumn].color.equals(this.color))
                    //and starting cell is not empty
                    && chessBoard.board[line][column] != null) {
                // starting cell not equals horse
                if (!chessBoard.board[line][column].equals(this)) {
                    return false;
                }

                //all possible positions of horse
                int[][] positions = new int[][]{
                        {line - 2, column - 1}, {line - 2, column + 1},
                        {line + 2, column - 1}, {line + 2, column + 1},
                        {line - 1, column - 2}, {line - 1, column + 2},
                        {line + 1, column - 2}, {line + 1, column + 2}
                };
                // can move to position
                for (int i = 0; i < positions.length; i++){
                    if (positions[i][0] == toLine && positions[i][1] == toColumn) {
                        return true;
                    }
            }
            }

        } else return false;
     return true;
}
    @Override
    public String getSymbol() {
        return "H";
    }
}
