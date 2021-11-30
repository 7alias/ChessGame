import java.util.Locale;

public class ChessBoard {

    public ChessPiece[][] board = new ChessPiece[8][8];
    /**
     * Чей сейчас ход  - "White" или "Black"
     */
    String nowPlayer;

    public ChessBoard(String nowPlayer) {
        this.nowPlayer = nowPlayer;
    }

    public String nowPlayerColor() {
        return this.nowPlayer;
    }

    public boolean moveToPosition(int startLine, int startColumn, int endLine, int endColumn) {
// проверяем, что такая клетка существует на доске
        if (checkPos(startLine) && checkPos(startColumn)) {

            //если цвет текущего игрока не совпадает с цветом фигуры на данной клетке, то ход невозможен
            //нельзя двигать чужие фигуры
            if (!nowPlayer.equals(board[startLine][startColumn].getColor())) return false;

            //если данная фигура может быть сдвинута на эту позицию
            if (board[startLine][startColumn].canMoveToPosition(this, startLine, startColumn, endLine, endColumn)) {
// проверяем позицию для рокировки
                //если фигура - это король (King) или ладья (Rook)
                if (board[startLine][startColumn].getSymbol().equals(("K")) ||
                        board[startLine][startColumn].getSymbol().equals("R")) {
                    //устанавливаем что данная фигура еще не двигалась
                    board[startLine][startColumn].check = false;
                }
                //если была возможность сдвинуть фигуру, то переместили на конечную клетку
                board[endLine][endColumn] = board[startLine][startColumn];
                // удалили фигуру со стартовой клетки
                board[startLine][startColumn] = null;
                this.nowPlayer = this.nowPlayerColor().equals("White") ? "Black" : "White";
                return true;
            } else return false;
        } else return false;
    }

    public void printBoard() {
        //print board to console
        System.out.println("Turn " + nowPlayer);
        System.out.println();
        System.out.println("Player 2 (Black)");
        System.out.println();
        System.out.println("\t0\t1\t2\t3\t4\t5\t6\t7");
        for (int i = 7; i > -1; i--) {
            System.out.println(i + "\t");
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == null) {
                    System.out.print(".." + "\t");
                } else {
                    System.out.print(board[i][j].getSymbol() + board[i][j].getColor().substring(0, 1).toLowerCase() + "\t");
                }
            }
            System.out.println();
            System.out.println();

        }
        System.out.println("Player 1(White)");

    }

    public boolean checkPos(int pos) {
        return pos >= 0 && pos <= 7;
    }

    public boolean castling0(){


        if (nowPlayer.equals("White")) {
            if (board[0][0] == null || board[0][4] == null){ return false;}
            if (board[0][0].getSymbol().equals("R") && board[0][4].getSymbol().equals("K") && //check that King and Rook never moved
                    board[0][1] == null && board[0][2] == null && board[0][3] == null) {
                if (board[0][0].getColor().equals("White") && board[0][4].getColor().equals("White") &&
                        board[0][0].check && board[0][4].check &&
                        !new King("White").isUnderAttack(this, 0, 2)) {//check that position is not under attack
                    board[0][4] = null;
                    board[0][1] = new King("White"); //move King
                    board[0][1].check = false;
                    board[0][0] = null;
                    board[0][2] = new Rook("White"); //move Rook
                    board[0][2].check = false;
                    nowPlayer = "Black"; //next turn
                    return true;
                }
            }
        }
        return false;
    }
}








