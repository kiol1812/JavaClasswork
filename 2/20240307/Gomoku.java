/* 2024/03/07 楊育哲 41143264
 * 實作五指棋(Gomoku)，未寫和局(棋盤滿了)的判斷
 */

import java.util.Scanner;

public class Gomoku {
    public static void main(String args[]){
        Game game = new Game();
        game.start();//開始遊戲(基本初始化)，可擴充建構子成繼承未結束的遊戲(未寫)
        boolean notGameOver = true;
        while(notGameOver){//未結束前持續進行遊戲
            game.round();//某玩家回合，可放置一枚旗子，或選擇暫停(未寫)
            game.rotate();//換另一位玩家的回合
            notGameOver = (game.checkGameOver()<=0);
        }
        System.out.println(game.whoIsWinner()+" win!");
    }
}

class Game{
    Scanner sc = new Scanner(System.in);
    private String players[];
    private Player playerA, playerB;
    private ChessBoard cb;
    private boolean who;
    private int winner;
    public void start(){
        this.cb = new ChessBoard(10, 10);
        this.playerA = new Player(Player.BLACK);
        this.playerB = new Player(Player.WHITE);
        this.who = true;
        this.winner=-1;
        this.players = new String[3];
        players[1] = "player A";
        players[2] = "player B";
    }
    public void round(){
        cb.show();
        if(who){
            System.out.println("A round, input two int repesent x and y");
            int x=sc.nextInt(), y=sc.nextInt();
            if(!playerA.setPieces(cb, new Piece(x, y))){
                boolean allowedPosition = false;
                while(!allowedPosition){
                    System.out.println("please choose allowed position.");
                    x = sc.nextInt();
                    y = sc.nextInt();
                    allowedPosition = playerA.setPieces(cb, new Piece(x, y));
                }
            }
        }else{
            System.out.println("B round, input two int repesent x and y");
            int x=sc.nextInt(), y=sc.nextInt();
            if(!playerB.setPieces(cb, new Piece(x, y))){
                boolean allowedPosition = false;
                while(!allowedPosition){
                    System.out.println("please choose allowed position.");
                    x = sc.nextInt();
                    y = sc.nextInt();
                    allowedPosition = playerB.setPieces(cb, new Piece(x, y));
                }
            }
        }
    }
    public void rotate(){
        // 清空畫面
        who = !who;
    }
    public int checkGameOver(){
        int whoWin = cb.haveALine();
        if(whoWin>=0) this.winner=whoWin;
        return whoWin;
    }
    public String whoIsWinner(){
        cb.show();
        if(winner<0) return "none win.";
        return players[winner];
    }
}
class Player{
    private int use;
    protected final static int BLACK=1;
    protected final static int WHITE=2;
    // protected final static int COMPUTER_B=3;
    // protected final static int COMPUTER_W=4;
    public Player(int use){
        this.use = use;
    }
    public boolean setPieces(ChessBoard cb, Piece position){
        if(!cb.checkPositionPossible(position, 0)) return false;
        cb.place(use, position);
        return true;
    }
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
        dir = new int[4][2]; // = {{-1, 1}, {0, 1}, {1, 1}, {1, 0}};
        dir[0][0] = -1;
        dir[0][1]=dir[1][1]=dir[2][0]=dir[2][1]=dir[3][0]=1;
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
    public int haveALine(){
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
                        if(lineSize>=5) return use;
                    }
                }
            }
        }
        return -1;
    }
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

