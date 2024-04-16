package tmp;

import java.util.Scanner;

public class gg {
    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        System.out.println(game.board);
        game.start();
    }
}

class ChessBoardGame {
    ChessBoard board;
    Player[] player;
    int number;

    public void start() {
        int who = -1;
        //player[0].haveMaxSymbolLine(board);
        do {
            who = (who + 1) % number;
            player[who].putPiece(board);
            System.out.println("~" + who);
            System.out.println(player[who]);
        } while (!winGame(this.player[who]));
        if (board.isFullBoard())
            System.out.println("平手!!!");
        else
            System.out.println(player[who] + " 贏了!!");
    }

    public boolean winGame(Player player) {
        System.out.println("主");
        return false;
    }
}

class TicTacToe extends ChessBoardGame {
    TicTacToe() {
        super.board = new ChessBoard(3, 3);
        player = new Player[2];
        player[0] = new Player("PlayerA", 'O');
        player[1] = new Player("PlayerB", 'X');
        number = 2;
    }

    public boolean winGame(Player player) {
        System.out.println("副");
        System.out.println(player);
        System.out.println(super.board);
        System.out.println(board.width);
        int len = player.haveMaxSymbolLine(super.board);
        if (len < 3)
            return false;
        return true;
    }
}

class Gomoku extends ChessBoardGame {
    Gomoku() {
        board = new ChessBoard(15, 15);
        player = new Player[2];
        player[0] = new Player("PlayerA", '○');
        player[1] = new Player("PlayerB", '●');
        number = 2;
    }

    public boolean winGame(Player player) {
        if (player.haveMaxSymbolLine(board) < 5)
            return false;
        return true;
    }
}

class ChessBoard {
    Piece[][] board;
    int width, height;

    ChessBoard(int width, int height) {
        this.width = width;
        this.height = height;
        this.board = new Piece[width][height];
    }

    int getWidth() {
        return this.width;
    }

    int getHeight() {
        return this.height;
    }

    void addPiece(Piece piece) {
        board[piece.getX()][piece.getY()] = piece;
    }

    boolean isInRange(int x, int y) {
        return x >= 0 && x < this.width && y >= 0 && y < this.height;
    }

    boolean isNULLPosition(int x, int y) {
        return board[x][y] == null;
    }

    boolean isFullBoard() {
        for (int i = 0; i < this.width; i++)
            for (int j = 0; j < this.height; j++)
                if (!isNULLPosition(i, j))
                    return false;
        return true;
    }

    public String toString() {
        for (int i = 0; i < this.width; i++){
            for (int j = 0; j < this.height; j++){
                if (board[i][j] == null)
                    System.out.print("□");
                else
                    System.out.print(board[i][j].cnt);
            }
            System.out.println("");
        }
        return "";
    }
}

class Player {
    String name;
    char token;
    Scanner srn = new Scanner(System.in);

    Player(String name, char token) {
        this.name = name;
        this.token = token;
    }

    void putPiece(ChessBoard board) {
        // 從這裡開始
        System.out.println(board);
        while (true) {
            System.out.print(this.name + " 請輸入座標（X, Y）：");
            int x = srn.nextInt() - 1;
            int y = srn.nextInt() - 1;
            if (!board.isInRange(x, y))
                continue;
            else if (!board.isNULLPosition(x, y))
                continue;
            else
                board.addPiece(new Piece(x, y, token));
            break;
        }
    }

    public int haveMaxSymbolLine(ChessBoard board) {
        System.out.println("in");
        int len = 0;
        int loc[][] = { { -1, 0 }, { 0, -1 }, { -1, -1 }, { 1, -1 } };

        for (int k = 0; k < 4; k++) {
            int map[][] = new int[board.width][board.height];
            for (int i = 0; i < board.width; i++) {
                for (int j = 0; j < board.height; j++) {
                    map[i][j] = 0;
                }
            }
            for (int i = 0; i < board.width; i++) {
                for (int j = 0; j < board.height; j++) {
                    char test = (board.board[i][j]==null)?'n':board.board[i][j].cnt;
                    if (test != this.token)
                        map[i][j] = 0;
                    else if (!board.isInRange(i + loc[k][0], j + loc[k][1]))
                        map[i][j] = 1;
                    else
                        map[i][j] = map[i + loc[k][0]][j + loc[k][1]] + 1;

                    if (map[i][j] > len)
                        len = map[i][j];
                }
            }
            if (k == 3) {
                for (int i = 0; i < board.width; i++) {
                    for (int j = 0; j < board.height; j++) {
                        System.out.print(map[i][j]);
                        System.out.print(' ');
                    }
                    System.out.println();
                }
                System.out.println();
            }
        }
        // System.out.println(len);
        return len;
    }

    public String toString() {
        return this.name;
    }
}

class Piece {
    private int x, y;
    char cnt;

    Piece(int x, int y, char cnt) {
        this.x = x;
        this.y = y;
        this.cnt = cnt;
    }
    Piece(){
        this.x = -1;
        this.y = -1;
        this.cnt = '□';
    }

    void setX(int x) {
        this.x = x;
    }

    void setY(int y) {
        this.y = y;
    }

    int getX() {
        return this.x;
    }

    int getY() {
        return this.y;
    }

    public String toString() {
        return this.cnt + "";
    }
}
