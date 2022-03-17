public class Rook extends ChessPiece {
    public Rook(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (checkPos(line)
                && checkPos(column)
                && checkPos(toLine)
                && checkPos(toColumn)) {
            if (column == toColumn) {
                for (int i = getMin(line, toLine); i < getMax(line, toLine); i++) {
                    if (chessBoard.board[i][column] != null) {
                        if (chessBoard.board[i][column] == this
                                && i == getMax(line, toLine)) return false;
                        else if (chessBoard.board[i][column].getColor().equals(this.color) && i == toLine)
                            return false;
                        else if (!chessBoard.board[i][column].getColor().equals(this.color) && i == toLine)
                            return true;
                    }
                }
                if (chessBoard.board[toLine][column] != null) {
                    if (chessBoard.board[toLine][toColumn].getColor().equals(this.color)
                            && chessBoard.board[toLine][column] != this)
                        return false;
                    else
                        return !chessBoard.board[toLine][column].getColor().equals(this.color)
                                && chessBoard.board[toLine][column] != this;
                } else return true;

            } else if (line == toLine) {
                for (int i = getMin(toColumn, column); i < getMax(column, toColumn); i++) {
                    if (chessBoard.board[line][i] != null) {
                        if (chessBoard.board[line][i] == this && i
                                == getMax(column, toColumn)) return false;
                        else if (chessBoard.board[line][i].getColor().equals(this.color)
                                && i == toColumn)
                            return false;
                        else if (!chessBoard.board[line][i].getColor().equals(this.color)
                                && i == toColumn)
                            return true;
                    }
                }
                if (chessBoard.board[toLine][toColumn] != null) {
                    if (chessBoard.board[toLine][toColumn].getColor().equals(this.color)
                            && chessBoard.board[toLine][toColumn] != this)
                        return false;
                    else
                        return !chessBoard.board[toLine][toColumn].getColor().equals(this.color)
                                && chessBoard.board[toLine][toColumn] != this;
                } else return true;
            } else return false;
        } else return true;
    }

    @Override
    public String getSymbol() {
        return "R";
    }
}
