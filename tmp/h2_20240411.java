/* 2024/04/11 楊育哲 41143264
 * 實作棋類遊戲存/讀檔
 */

 import java.util.Scanner;
 import java.io.*;

 public class h2_20240411 {
     public static void main(String args[]) throws IOException{
        Gomoku Ggame = new Gomoku();
        Scanner sc = new Scanner(System.in);
        System.out.println("input 1 for load game; 0 for new game");
        System.out.println("note that first can't load, because saveFile.txt may not init yet");
        int load = sc.nextInt();
        if(load==1){
            Ggame.loadGame();
        }
        Ggame.start();
     }
 }
 
 interface ChessBoardGame {
     public static final ChessBoard cb=new ChessBoard(10, 10);
     public static final Player players[] = new HumanPlayer[2];
     public abstract void start() throws IOException;
     public abstract int checkGameOver();
     public abstract Player getPlayer(int index);

     // new
     public abstract void saveGame() throws IOException;
     public abstract void loadGame() throws IOException;
 }
 
 class roundsManger {
     private int rounds, endRound;
     private int whoRound=1;
     public roundsManger(){
         rounds=0;
         endRound=0;
         whoRound=1;
     }
     public void setEndRound(int e){ this.endRound=e; }
     public int getWhosRound(){ return whoRound; }
     public int getRounds(){ return rounds; }
     public int getEndRounds(){ return endRound; }
     public void setInit(int r, int endR, int who){ rounds=r;endRound=endR;whoRound=who; }
     public void nextRound(){
         rounds++;
         whoRound = (whoRound==1)?2:1;
     }
     public int checkEnd(){
         if(rounds==endRound) return -1;
         return 0;
     }
 }
 
 class Gomoku extends roundsManger implements ChessBoardGame {
     private GomokuChessBoard gcb;
     private Player players[];
     public Gomoku(){
         players = new Player[3];
         players[1] = new HumanPlayer("Player A", 1);
         players[2] = new HumanPlayer("Player B", 2);
         this.gcb = new GomokuChessBoard(10, 10);
         setEndRound(100);
     }
     @Override
     public int checkGameOver(){
         System.out.println(getWhosRound());
         if(gcb.existLine(getWhosRound(), 5)) return getWhosRound();
         else return super.checkEnd();
     }
     @Override
     public Player getPlayer(int index){
         if(index>0 && index<3) return players[index];
         return null;
     }
     @Override
     public void start() throws IOException{
         Scanner sc = new Scanner(System.in);
         while(true){ //game loop
             int x=-1, y=-1;
             while(gcb.positionVaild(new Position(x, y), 0)==false){
                 System.out.print("input (y, x) or '-1' for save: ");
                 y = sc.nextInt();
                 if(y==-1){
                    saveGame();
                    return;
                 }else x = sc.nextInt();
             }
             gcb.place(getWhosRound(), new Position(x, y));
             gcb.showChessBoard();
             int check = checkGameOver();
             // System.out.println(check);
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
    public void saveGame() throws IOException {
        DataOutputStream dout = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("./saveFile.txt")));
        dout.writeInt(getRounds());
        dout.writeInt(getEndRounds());
        dout.writeInt(getWhosRound());
        dout.writeInt(players.length);
        for(int i=1; i<players.length; i++){
            dout.writeUTF(players[i].toString());
        }
        dout.writeInt(gcb.getHeight());
        dout.writeInt(gcb.getWidth());
        int[][] copyChessboard = gcb.getChessBoard();
        for(int i=0; i<gcb.getHeight(); i++){
            for(int j=0; j<gcb.getWidth(); j++){
                dout.writeInt(copyChessboard[i][j]);
            }
        }
        dout.close();
        System.out.println("saved.");
    }
    @Override
    public void loadGame() throws IOException {
        DataInputStream din = new DataInputStream(new BufferedInputStream(new FileInputStream("./saveFile.txt")));
        setInit(din.readInt(), din.readInt(), din.readInt());
        players = new Player[din.readInt()];
        for(int i=1; i<players.length; i++){
            String nameAndUse = din.readUTF();
            String splited[] =nameAndUse.split("_");
            players[i] = new HumanPlayer(splited[0], Integer.valueOf(splited[1]));
        }
        int h=din.readInt(), w=din.readInt();
        this.gcb = new GomokuChessBoard(h, w);
        for(int i=0; i<gcb.getHeight(); i++){
            for(int j=0; j<gcb.getWidth(); j++){
                gcb.place(din.readInt(), new Position(j, i));
            }
        }
        din.close();
    }
 }
 
 interface Player {
     public static final String name="";
     public static final int use=0;
     public abstract int getUse();
 }
 class HumanPlayer implements Player {
     private String name;
     private int use;
     public HumanPlayer(){
     }
     public HumanPlayer(String n, int u){
         this.name = n;
         this.use = u;
     }
     public int getUse(){ return use; }
     public String toString(){ return name+"_"+use; }
 }
 class ComputePlayer implements Player {
     private String name;
     private int use;
     public ComputePlayer(String n, int u){
         this.name = n;
         this.use = u;
     }
     public int getUse(){ return use; }
     public String toString(){ return name+"_"+use; }
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
     public int[][] getChessBoard(){
        return chessBoard;
     }
     public int getPositionValue(int y, int x){return chessBoard[y][x];}
     public boolean positionVaild(Position p, int ignore){
         int x=p.getX(), y=p.getY();
         // System.out.print("x:"+x+", y"+y+" ; ");
         if(x<0||y<0||x>=width||y>=height) return false;
         // System.out.println("y:"+p.getY()+", x:"+p.getX()+" "+ignore);
         return (chessBoard[p.getY()][p.getX()]==ignore);
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
 class GomokuChessBoard extends ChessBoard{
     public GomokuChessBoard(int h, int w) {
         super(h, w);
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
