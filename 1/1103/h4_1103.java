/* 11/03 楊育哲
 * 實作第三題: 俄羅斯方塊
 */
class Window{
    private char[][] window=new char[30][100];//30>7*4, 100為亂取
    // public // 繪製
}
class object{
    private char[][] body=new char[2][4];
    private int state=0;//0, 1, 2, 3 -> 四種方向(以[0][0]為旋轉點)
    private int crood=0;
    public void R(){state=(state+1)%4;}
    public void L(){state=(state-1);if(state==-1)state=3;}
    public void Q(){if(crood>4)crood--;}//之後寫判斷左右邊界該是多少(+1~4, 旋轉狀況)
    public void W(){if(crood<50)crood++;}
    public object(char[][] init){
        for(int i=0; i<2; i++){
            for(int j=0; j<4; j++) body[i][j]=init[i][j];
        }
    }
};
public class h4_1103 {
    public static void main(String args[]){
        char[][] o = new char[4][2];
        char[][] i = new char[4][2];
        char[][] s = new char[4][2];
        char[][] z = new char[4][2];
        char[][] l = new char[4][2];
        char[][] j = new char[4][2];
        char[][] t = new char[4][2];
        o[0][0] = 'o';o[0][1] = 'o';o[1][0] = 'o';o[1][1] = 'o';
        object O = new object(o);
        i[0][0]='i';i[1][0]='i';i[2][0]='i';i[3][0]='i';
        object I = new object(i);
        s[0][0]='s';s[1][0]='s';s[1][1]='s';s[1][2]='s';
        object S = new object(s);
        z[0][1]='z';z[1][1]='z';z[1][0]='z';z[2][0]='z';
        object Z = new object(z);
        l[0][0]='l';l[1][0]='l';l[2][0]='l';l[2][1]='l';
        object L = new object(l);
        j[0][1]='j';j[1][1]='j';j[2][1]='j';j[2][0]='j';
        object J = new object(j);
        t[0][0]='t';t[1][0]='t';t[1][1]='t';t[2][0]='t';
        object T = new object(t);
    }
}
