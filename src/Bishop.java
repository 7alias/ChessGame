public class Bishop extends ChessPiece {
    public Bishop(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        //starting line not equals ending
        if (line != toLine && column != toColumn &&
                //moving diagonally
                getMax(line, toLine) - getMin(line, toLine)
                        == getMax(column, toColumn) - getMin(column, toColumn)
                // all coordinates exist
                && checkPos(line)
                && checkPos(column)
                && checkPos(toLine) && checkPos(toColumn)
                // ending cell is empty or there is a figure of another color
                && (chessBoard.board[toLine][toColumn] == null
                || !chessBoard.board[toLine][toColumn].color.equals(this.color))
                // starting cell is not empty
                && chessBoard.board[line][column] != null) {
            // top left  -> down right
            if ((column == getMin(column, toColumn)
                    && line == getMax(line, toLine))
                    || (toColumn == getMin(column, toColumn)
                    && toLine == getMax(line, toLine))) {
//Max & Min for moving backwards top right -> down left
                int fromL = getMax(line, toLine);
                int fromC = getMin(column, toColumn);
                int toL = getMin(line, toLine);
                int toC = getMax(column, toColumn);
                //positions which bishops walks by
                int[][] positions = new int[toC - fromC][1];
                // columns number = lines number
                for (int i = 1; i < toC - fromC; i++) {
                    if (chessBoard.board[fromL - i][fromC + i] == null) {
                        positions[i - 1] = new int[]{fromL - i, fromC + i};
                    } else if (!chessBoard.board[fromL - i][fromC + i].color.equals(this.color)
                            && fromL - i == toLine) {
                        positions[i - 1] = new int[]{fromL - i, fromC + i};
                    } else {
                        return false;
                    }
                }
                return true;
            } else {
//top right -> down left
                int fromL = getMax(line, toLine);
                int fromC = getMin(column, toColumn);
                int toL = getMin(line, toLine);
                int toC = getMax(column, toColumn);
                //positions which bishops walk by
                int[][] positions = new int[toC - fromC][1];
                // columns number = lines number
                for (int i = 1; i < toC - fromC; i++) {
                    if (chessBoard.board[fromL - i][fromC + i] == null) {
                        positions[i - 1] = new int[]{fromL + i, fromC + i};
                    } else if (!chessBoard.board[fromL - i][fromC + i].color.equals(this.color)
                            && fromL - i == toLine) {
                        positions[i - 1] = new int[]{fromL + i, fromC + i};
                    } else {
                        return false;
                    }
                }
                return true;
            }
        } else return true;

    }

    @Override
    public String getSymbol() {
        return "B";
    }
}


