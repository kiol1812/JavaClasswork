/* 2024/03/07 楊育哲 41143264
 * 實作五指棋(Gomoku)
 */

import java.util.Scanner;

public class Gomoku_update {
    public static void main(String args[]){
        Game game = new Game();
        game.start();//開始遊戲(基本初始化)，可擴充建構子成繼承未結束的遊戲(未寫)
        boolean notGameOver = true;
        while(notGameOver){//未結束前持續進行遊戲
            game.round();//某玩家回合，可放置一枚旗子，或選擇暫停(未寫)
            game.rotate();//換另一位玩家的回合
            notGameOver = (game.checkGameOver()<0);
        }
        System.out.println(game.whoIsWinner());
    }
}

class Game{
    Scanner sc = new Scanner(System.in);
    private Player players[];
    private ChessBoard cb;
    private boolean who;
    private int winner;
    private int rounds;
    public void start(){
        System.out.println("input chess board size with height and width, for example 10 10");
        int h=sc.nextInt(), w=sc.nextInt();
        this.cb = new ChessBoard(h, w);
        this.players = new Player[3]; //保留'0'index, 所以填3
        this.players[1] = new Player("player A", Player.BLACK);
        this.players[2] = new Player("player B", Player.WHITE);
        this.who = true; //for change round
        this.winner=-1;  //index of players, to get winner name
        this.rounds = 0; //counting round, 用來判斷是否和局(棋盤滿了但沒連線)
    }
    public void round(){
        cb.show();
        if(who){
            System.out.println("A round, input two int repesent x and y");
            int x=sc.nextInt(), y=sc.nextInt();
            while(!players[1].setPieces(cb, new Piece(x, y))){
                System.out.println("please choose allowed position.");
                x = sc.nextInt();
                y = sc.nextInt();
            }
        }else{
            System.out.println("B round, input two int repesent x and y");
            int x=sc.nextInt(), y=sc.nextInt();
            while(!players[2].setPieces(cb, new Piece(x, y))){
                System.out.println("please choose allowed position.");
                x = sc.nextInt();
                y = sc.nextInt();
            }
        }
    }
    public void rotate(){
        who = !who;
        rounds++;
    }
    public int checkGameOver(){
        int whoWin = cb.exitLine(5);
        if(whoWin>0){
            this.winner=whoWin;
            return whoWin; //某玩家獲勝
        }
        if(rounds>=cb.getHeight()*cb.getWidth()){
            System.out.println("end");
            return 0; //和局
        }
        return -1; //未結束
    }
    public String whoIsWinner(){
        cb.show();
        if(winner<=0) return "nobody win.";
        return players[winner]+" win!";
    }
}
class Player{
    private int use;
    private String name;
    protected final static int BLACK=1;
    protected final static int WHITE=2;
    // protected final static int COMPUTER_B=3;
    // protected final static int COMPUTER_W=4;
    public Player(String name, int use){
        this.name = name;
        this.use = use;
    }
    public boolean setPieces(ChessBoard cb, Piece position){
        if(!cb.checkPositionPossible(position, 0)) return false;
        cb.place(use, position);
        return true;
    }
    public String toString(){ return name; }
}
class ChessBoard{
    private int width, height;
    private int stateBuf[][];
    private int dir[][];
    public ChessBoard(int n, int m){
        this.height = n;
        this.width  = m;
        this.stateBuf = new int[height][width];
        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                stateBuf[i][j] = 0;
            }
        }
        dir = new int[4][2]; // = {{1, -1}, {0, 1}, {1, 1}, {1, 0}};
        dir[0][1] = -1;
        dir[0][0]=dir[1][1]=dir[2][0]=dir[2][1]=dir[3][0]=1;
        dir[1][0]=dir[3][1]=0;
    }
    public boolean checkPositionPossible(Piece pos, int use){
        if(pos.getX()<0||pos.getX()>=width||pos.getY()<0||pos.getY()>=height) return false;
        else if(stateBuf[pos.getY()][pos.getX()]!=use) return false;
        return true;
    }
    public void place(int use, Piece pos){
        stateBuf[pos.getY()][pos.getX()]=use;
    }
    public void show(){
        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++) System.out.print(stateBuf[i][j]+" ");
            System.out.println("");
        }
    }
    public int exitLine(int lineLenght){
        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                if(stateBuf[i][j]!=0){
                    int use = stateBuf[i][j];//目前使用的棋子
                    for(int k=0;  k<4; k++){
                        int y=i+dir[k][0], x=j+dir[k][1], lineSize=1;
                        while(checkPositionPossible(new Piece(x, y), use)){
                            lineSize++;
                            y+=dir[k][0];
                            x+=dir[k][1];
                        }
                        if(lineSize>=lineLenght) return use;
                    }
                }
            }
        }
        return -1;
    }
    public int getWidth(){ return width; }
    public int getHeight(){ return height; }
}
class Piece{
    private int x, y;
    public Piece(int x, int y){
        this.x = x;
        this.y = y;
    }
    public int getX(){ return x; }
    public int getY(){ return y; }
}

