public class King extends ChessPiece{
    public King(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (checkPos(line) && checkPos(column) && checkPos(toLine) && checkPos(toColumn)) {
            //если пытаемся сходить дальше, чем на 1 клетку
            if (Math.abs(line - toLine) > 1 || Math.abs(column - toColumn) > 1) return false;
            //нельзя ходить если король окажется под атакой
            if (isUnderAttack(chessBoard, toLine, toColumn)) return false;
            //если клетка не пустая, то вернет true если там фигура другого цвета
            if (chessBoard.board[toLine][toColumn] != null) {
                return !chessBoard.board[toLine][toColumn].getColor().equals(color);
            }
            return true;
        } else return false;
    }




    @Override
    public String getSymbol() {
        return "K";
    }
    public boolean isUnderAttack(ChessBoard chessBoard, int line, int column){
        //проходка по всем клеткам
        if (checkPos(line) && checkPos(column)) {
            for (int i =0; i<7; i++){
                for (int j = 0; j < 7; j++) {
                    if (!chessBoard.board[i][j].getColor().equals(color) && chessBoard.board[i][j].canMoveToPosition(chessBoard, i, j , line, column)){
                        return true;
                    }
                }
            } return false;
        } else return false;
    }
}
