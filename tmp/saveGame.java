// 41143213 吳哲瑋(J.D.I.哲哲)
// 2024.4.11
// 五子棋（Gomoku）& 井字遊戲（TicTacToe） 注重於存檔與讀檔的實作應用於遊戲中
import java.io.*;
import java.util.Scanner;

public class saveGame {
    public static void main(String[] args) throws IOException, ClassNotFoundException{
        ChessBoardGame game = new Gomoku();
        System.out.println("Game Start!!");
        System.out.print("載入遊戲？(Y/N) ");
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        if(s.equals("Y") || s.equals("y"))
            game.loadGame();
        game.start();
    }
}

abstract class ChessBoardGame implements Serializable{
    protected ChessBoard board;
    protected Player player[];
    protected int num;

    public void start() throws IOException, ClassNotFoundException{
        int who = -1;
        do {
            who = (who + 1) % this.num;
            if(!player[who].putPiece(this.board)){
                saveGame();
                return;
            }
        } while (!winGame(this.player[who]));
        System.out.print(board);
        if (board.isFullBoard())
            System.out.println("平手!!!");
        else
            System.out.println(player[who] + " 贏了!!");
    }

    abstract public boolean winGame(Player player);

    public void saveGame() throws IOException, ClassNotFoundException{
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("game"));
            oos.writeUTF(player[0].name);
            oos.writeChar(player[0].token);
            oos.writeObject(board);
            oos.writeInt(num);
            oos.flush();
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadGame() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("game"));
            player[0].name = ois.readUTF();
            player[0].token = ois.readChar();
            board = (ChessBoard)ois.readObject();
            num = ois.readInt();
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
        //} catch (Exception e) {
            //e.printStackTrace();
            System.out.println("載入失敗");
            System.out.println("重新開始遊戲");
        }
    }
}

class Gomoku extends ChessBoardGame {
    Gomoku() {
        super.board = new ChessBoard(15, 15);
        super.player = new Player[2];
        super.player[0] = new HumanPlayer("playerA", '○');
        super.player[1] = new ComputerPlayer("playerB", '●');
        super.num = 2;
    }

    public boolean winGame(Player player) {
        if (player.haveMaxSymbolLine(super.board) < 5)
            return false;
        return true;
    }
}

class ChessBoard implements Serializable{
    private int width, height;
    protected Piece[][] pieceBoard;

    ChessBoard(int width, int height) {
        this.width = width;
        this.height = height;
        this.pieceBoard = new Piece[width][height];
    }
    
    int getWidth() {
        return this.width;
    }
    
    int getHeight() {
        return this.height;
    }

    void addPiece(Piece piece) {
        this.pieceBoard[piece.getX()][piece.getY()] = piece;
    }

    boolean isInRange(int x, int y) {
        return x >= 0 && x < this.width && y >= 0 && y < this.height;
    }

    boolean isNULLPosition(int x, int y) {
        return this.pieceBoard[x][y] == null;
    }

    boolean isFullBoard() {
        for (int i = 0; i < this.width; i++)
            for (int j = 0; j < this.height; j++)
                if (!isNULLPosition(i, j))
                    return false;
        return true;
    }

    public String toString() {
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                if (this.pieceBoard[j][i] == null)
                    System.out.print("□");
                else
                    System.out.print(this.pieceBoard[j][i].cnt);
            }
            System.out.println();
        }
        return "";
    }
}

abstract class Player implements Serializable{
    protected String name;
    protected char token;

    Player(String name, char token) {
        this.name = name;
        this.token = token;
    }

    public int haveMaxSymbolLine(ChessBoard board) {
        int len = 0;
        int loc[][] = { { -1, 0 }, { 0, -1 }, { -1, 1 }, { -1, -1 } };

        for (int k = 0; k < 4; k++) {
            int map[][] = new int[board.getWidth()][board.getHeight()];
            for (int i = 0; i < board.getWidth(); i++) {
                for (int j = 0; j < board.getWidth(); j++) {
                    map[i][j] = 0;
                }
            }
            for (int i = 0; i < board.getWidth(); i++) {
                for (int j = 0; j < board.getHeight(); j++) {
                    if(board.pieceBoard[i][j] == null)
                        map[i][j] = 0;
                    else if (board.pieceBoard[i][j].cnt != this.token)
                        map[i][j] = 0;
                    else if (!board.isInRange(i + loc[k][0], j + loc[k][1]))
                        map[i][j] = 1;
                    else
                        map[i][j] = map[i + loc[k][0]][j + loc[k][1]] + 1;

                    if (map[i][j] > len) len = map[i][j];
                }
            }
        }
        return len;
    }

    public String toString() {
        return this.name;
    }

    abstract public boolean putPiece(ChessBoard board);
}

class ComputerPlayer extends Player implements Serializable{
    ComputerPlayer(String name, char token) {
        super(name, token);
    }

    public boolean putPiece(ChessBoard board) {
        int x,y;
        do{
            x = (int)(Math.random()*board.getWidth());
            y = (int)(Math.random()*board.getHeight());
        }while(!board.isInRange(x, y) || !board.isNULLPosition(x, y));
        board.addPiece(new Piece(x, y, token));
        System.out.println("電腦下在x="+(x+1)+", y="+(y+1));
        return true;
    }
}

class HumanPlayer extends Player implements Serializable{
    Scanner srn = new Scanner(System.in);

    HumanPlayer(String name, char token) {
        super(name, token);
    }

    protected void finalize() {
        srn.close();
    }

    public boolean putPiece(ChessBoard board) {
        System.out.println(board);
        while (true) {
            System.out.print(this.name + " 請輸入座標（X, Y）or -1(結束遊戲)：");
            int x = srn.nextInt() - 1;
            if (x == -2) return false;
            int y = srn.nextInt() - 1;
            if (!board.isInRange(x, y))
                continue;
            else if (!board.isNULLPosition(x, y))
                continue;
            else
                board.addPiece(new Piece(x, y, token));
            break;
        }
        return true;
    }
}

class Piece implements Serializable{
    private int x, y;
    char cnt;

    Piece(int x, int y, char cnt) {
        this.x = x;
        this.y = y;
        this.cnt = cnt;
    }

    void setX(int x) {
        this.x = x;
    }

    void setY(int y) {
        this.y = y;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }
}