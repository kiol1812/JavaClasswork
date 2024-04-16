/* 2024/03/20 楊育哲 41143264
 * 實作棋類遊戲(讓五子棋、井字棋繼承)
 */

import java.util.Scanner;

public class CBGame {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int choose=0;
        while(choose>=0){
            System.out.println("choose which game to play: [1]Gomoku / [2]TicTacToe / [-1]exit : ");
            choose  = sc.nextInt();
            switch (choose) {
                case 1:
                    Gomoku Ggame = new Gomoku();
                    Ggame.start();
                    break;
                case 2:
                    TicTacToe Tgame = new TicTacToe();
                    Tgame.start();
                    break;
                default:
                    break;
            }
        }
    }
}

class ChessBoard{
    private int width, height;
    private int[][] chessBoard;
    private int[][] dir;
    public ChessBoard(int h, int w){
        this.height = h;
        this.width = w;
        this.chessBoard = new int[h][w];
        dir = new int[4][2]; // = {{1, -1}, {0, 1}, {1, 1}, {1, 0}};
        dir[0][1] = -1;
        dir[0][0]=dir[1][1]=dir[2][0]=dir[2][1]=dir[3][0]=1;
        dir[1][0]=dir[3][1]=0;
    }
    public int getHeight(){ return height; }
    public int getWidth(){ return width; }
    public int getPositionValue(int y, int x){return chessBoard[y][x];}
    public boolean positionVaild(Position p, int ignore){
        int x=p.getX(), y=p.getY();
        if(x<0||y<0||x>=width||y>=height) return false;
        return chessBoard[p.getY()][p.getX()]==ignore;
    }
    public void place(int use, Position pos){
        chessBoard[pos.getY()][pos.getX()]=use;
    }
    public void showChessBoard(){
        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++) System.out.print(chessBoard[i][j]+" ");
            System.out.println("");
        }
    }
    public boolean existLine(int use, int lineLenght){
        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                if(chessBoard[i][j]==use){
                    for(int k=0;  k<4; k++){
                        int y=i+dir[k][0], x=j+dir[k][1], lineSize=1;
                        while(positionVaild(new Position(x, y), use)){
                            lineSize++;
                            y+=dir[k][0];
                            x+=dir[k][1];
                        }
                        if(lineSize>=lineLenght) return true;
                    }
                }
            }
        }
        return false;
    }
}
class Player{
    private String name;
    private int use;
    public Player(String n, int u){
        this.name = n;
        this.use = u;
    }
    public int getUse(){ return use; }
    public String toString(){ return name; }
}
class ChessBoardGame{
    private ChessBoard cb;
    private Player[] players;
    private int rounds, endRound;
    private int whoRound;
    public static Scanner sc = new Scanner(System.in);
    public ChessBoardGame(){
        this.rounds = 1;
        this.players = new Player[3];
        players[1] = new Player("player A", 1);
        players[2] = new Player("player B", 2);
        this.whoRound = 1;
    }
    public void start(){ // 五子棋等使用自己的棋盤、會覆寫掉
        this.cb = new ChessBoard(10, 10); //基本start函式，由繼承者覆寫
        this.endRound = cb.getHeight()*cb.getWidth();
    }
    public int checkGameOver(){
        if(rounds==endRound) return -1;
        // if(cb.existLine(whoRound, 5)) return whoRound;
        return 0;
    }
    public void nextRound(){
        whoRound=(whoRound==1) ? 2 : 1;
        rounds++;
    }
    public void setEndRound(int e){ this.endRound=e; }
    public int getWhosRound(){ return whoRound; }
    public Player getPlayer(int index){ return players[index]; }
}
class Gomoku extends ChessBoardGame{
    private GomokuChessBoard gcb;
    @Override
    public int checkGameOver(){
        if(gcb.existLine(getWhosRound(), 5)) return getWhosRound();
        else return super.checkGameOver();
    }
    @Override
    public void start(){
        this.gcb = new GomokuChessBoard(10, 10);
        setEndRound(100);
        while(true){ //game loop
            int x=-1, y=-1;
            while(gcb.positionVaild(new Position(x, y), 0)==false){
                System.out.print("input (y, x): ");
                y = sc.nextInt();
                x = sc.nextInt();
            }
            gcb.place(getWhosRound(), new Position(x, y));
            gcb.showChessBoard();
            int check = checkGameOver();
            System.out.println(check);
            if(check==-1){
                System.out.println("end");
                break;
            }else if(check>0){
                System.out.println(getPlayer(check)+"win.");
                break;
            }
            nextRound();
        }
    }
}
class TicTacToe extends ChessBoardGame{
    private TicTacToeChessBoard tcb;
    @Override
    public void start(){
        this.tcb = new TicTacToeChessBoard();
        while(true){ //game loop
            int x=-1, y=-1;
            while(tcb.positionVaild(new Position(x, y), 0)==false){
                System.out.print("input (y, x): ");
                y = sc.nextInt();
                x = sc.nextInt();
            }
            tcb.place(getWhosRound(), new Position(x, y));
            tcb.showChessBoard();
            int check = checkGameOver();
            if(check==-1){
                System.out.println("end");
                break;
            }else if(check>0){
                System.out.println(getPlayer(check)+"win.");
                break;
            }
            nextRound();
        }
    }
    @Override
    public int checkGameOver(){
        if(tcb.existLine(getWhosRound(), 3)) return getWhosRound();
        else return super.checkGameOver();
    }
}
class GomokuChessBoard extends ChessBoard{
    public GomokuChessBoard(int h, int w) {
        super(h, w);
    }
}
class TicTacToeChessBoard extends ChessBoard{
    private final static int width=3, height=3;
    public TicTacToeChessBoard(){
        super(width, height);
    }
}
class Position{
    private int x, y;
    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }
    public int getX(){ return x; }
    public int getY(){ return y; }
}

