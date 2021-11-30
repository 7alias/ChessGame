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
        //начальная точка не равна конечной
        if (line != toLine && column != toColumn &&
                //и ходит по диагонали
                getMax(line, toLine) - getMin(line, toLine) == getMax(column, toColumn) - getMin(column, toColumn) &&
                // и все координаты существуют
                checkPos(line) && checkPos(column) && checkPos(toLine) && checkPos(toColumn) &&
                // и конечная точка пустая или на ней стоит фигура другого цвета
                (chessBoard.board[toLine][toColumn] == null || !chessBoard.board[toLine][toColumn].color.equals(this.color)) &&
                // и стартовая клетка не пустая
                chessBoard.board[line][column] != null) {
            // сверху слева -> направо вниз
            if ((column == getMin(column, toColumn) && line == getMax(line, toLine)) ||
                    (toColumn == getMin(column, toColumn) && toLine == getMax(line, toLine))) {
//Max и Min нужны чтобы можно было делать обратный ход: сверху справа -> вниз налево
                int fromL = getMax(line, toLine);
                int fromC = getMin(column, toColumn);
                int toL = getMin(line, toLine);
                int toC = getMax(column, toColumn);
                //позиции которые слон проходит по пути
                int[][] positions = new int[toC - fromC][1];
                // число колонок = числу линий пройденных слоном
                for (int i = 1; i < toC - fromC; i++) {
                    if (chessBoard.board[fromL - i][fromC + i] == null) {
                        positions[i - 1] = new int[]{fromL - i, fromC + i};
                    } else if (!chessBoard.board[fromL - i][fromC + i].color.equals(this.color) && fromL - i == toLine) {
                        positions[i - 1] = new int[]{fromL - i, fromC + i};
                    } else {
                        return false;
                    }
                }
                return true;
            } else {
//сверху справа -> налево вниз
                    int fromL = getMax(line, toLine);
                    int fromC = getMin(column, toColumn);
                    int toL = getMin(line, toLine);
                    int toC = getMax(column, toColumn);
                    //позиции которые слон проходит по пути
                    int[][] positions = new int[toC - fromC][1];
                    // число колонок = числу линий пройденных слоном
                    for (int i = 1; i < toC - fromC; i++) {
                        if (chessBoard.board[fromL - i][fromC + i] == null) {
                            positions[i - 1] = new int[]{fromL + i, fromC + i};
                        } else if (!chessBoard.board[fromL - i][fromC + i].color.equals(this.color) && fromL - i == toLine) {
                            positions[i - 1] = new int[]{fromL + i, fromC + i};
                        } else {
                            return false;
                        }
                    }
                    return true;}
            } else return false;

    }

            @Override
            public String getSymbol () {
                return "B";
            }
        }


