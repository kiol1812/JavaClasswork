/* 12/22 楊育哲
 * 實作稀疏矩陣
 */
public class test {
    public static void main(String args[]){
        // show constructor using 1000*1000 array
        System.out.println("show constructor using 1000*1000 array");
        int[][] array = new int[1000][1000];
        for(int i=0; i<10; i++){
            int x=(int)(Math.random()*999), y=(int)(Math.random()*999), v=(int)(Math.random()*98+1);
            array[x][y] = v;
        }
        SparseMatrix test = new SparseMatrix(array);
        test.view();

        // show add function
        System.out.println("show add function");
        int[][] constArray1=new int[3][1];
        int[][] constArray2=new int[3][1];
        for(int i=0; i<3; i++){
            constArray1[i][0]=(int)(Math.random()*98+1); constArray2[i][0]=(int)(Math.random()*98+1);
        }
        SparseMatrix test2 = new SparseMatrix(constArray1);
        SparseMatrix test3 = new SparseMatrix(constArray2);
        // !-----------------------------------------------------------------------------------------
        // 看未重疊位置的加法
        test2.push((int)(Math.random()*96)+4, (int)(Math.random()*96)+4, 123); //not const
        test3.push((int)(Math.random()*96)+4, (int)(Math.random()*96)+4, 456); //not const
        // !-----------------------------------------------------------------------------------------
        System.out.println("view const array1 values, and it's random...:");
        test2.view();
        System.out.println("view const array2 values, and it's random...:");
        test3.view();
        SparseMatrix afterAdd = test2.add(test3);
        System.out.println("after add:");
        afterAdd.view();

        //show trandform
        System.out.println("show transform function\nbefore:");
        test.view();
        test.transfrom("right");
        System.out.println("after transfrom right");
        test.view();

        //show lookfor
        System.out.println("show lookfor function\n lookfor (0, 0) from test2(have)="+test2.lookfor(0, 0));
        System.out.println("-1 repersent didn.t have value like (4, 4)from test2: "+test.lookfor(4, 4));
    }
}
class SparseMatrix{
    private int[][] sm; // = new int[10][3]
    private int capacity, items;
    private int r, c;
    SparseMatrix(){
        capacity=2; items=0; r=0; c=0;
        sm = new int[capacity][3];
    }
    SparseMatrix(int[][] init){
        capacity=2; items=0; r=0; c=0;
        sm = new int[capacity][3];
        for(int i=0; i<init.length; i++){
            int[] temp=init[i];
            for(int j=0; j<temp.length; j++){
                if(init[i][j]!=0) push(i, j, init[i][j]);
            }
        }
    }
    public void push(int x, int y, int v){
        if(items==capacity-1){
            int[][] tmp = new int[capacity*2][3];
            for(int k=0; k<capacity; k++){
                tmp[k][0] = sm[k][0]; //x
                tmp[k][1] = sm[k][1]; //y
                tmp[k][2] = sm[k][2]; //value
            }
            capacity*=2; sm=null;
            sm = tmp;
        }
        sm[items][0]=x; r=Math.max(x+1, r);
        sm[items][1]=y; c=Math.max(y+1, c);
        sm[items++][2]=v;
    }
    public void transfrom(String dir){
        if(dir.charAt(0)=='L'||dir.charAt(0)=='l'){
            for(int i=0; i<items; i++){
                int oldX=sm[i][0];
                sm[i][0]=sm[i][1]; //new_x = old_y
                sm[i][1]=r-1-oldX; //new_y = r-1-old_x
            }
        }else if(dir.charAt(0)=='R'||dir.charAt(0)=='r'){
            for(int i=0; i<items; i++){
                int oldY=sm[i][1];
                sm[i][1]=sm[i][0]; //new_y = old_x
                sm[i][0]=c-1-oldY; //new_y = r-1-old_x
            }
        }else System.out.println("input r or l");
    }
    public int lookfor(int x, int y){
        for(int i=0; i<items; i++){
            if(sm[i][0]==x&&sm[i][1]==y){
                return sm[i][2]-0;
            }
        }
        return -1;
    }
    public SparseMatrix add(SparseMatrix B){
        int i=0, j=0;
        SparseMatrix C = new SparseMatrix();
        while(i<items&&j<B.items){
            if(sm[i][0]==B.sm[j][0]&&sm[i][1]==B.sm[j][1]) C.push(sm[i][0], sm[i][1], sm[i][2]+B.sm[j][2]);
            else{
                C.push(sm[i][0], sm[i][1], sm[i][2]);
                C.push(B.sm[j][0], B.sm[j][1], B.sm[j][2]);
            }
            i++; j++;
        }
        while(i<items) C.push(sm[i][0], sm[i][1], sm[i][2]);
        while(j<B.items) C.push(B.sm[j][0], B.sm[j][1], B.sm[j][2]);
        return C;
    }
    public void view(){
        for(int i=0; i<items; i++) System.out.println("["+sm[i][0]+", "+sm[i][1]+"]="+sm[i][2]);
    }
}
