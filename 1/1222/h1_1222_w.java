/* 12/22 楊育哲
 * 實作第一題: 
 */
public class h1_1222_w {
    public static void main(String args[]){
        int[][] array = new int[1000][1000];
        for(int i=0; i<10; i++){
            int x=(int)(Math.random()*999), y=(int)(Math.random()*999), v=(int)(Math.random()*98+1);
            array[x][y] = v;
            // System.out.printf("last[%d, %d] = %d\n", x, y, v);
        }
        SparseMatrix sm = new SparseMatrix(array);
        sm.view();
    }
}

class Matrix{
    protected class structure{
        protected static int x, y, value;
    }
    Matrix(int nx, int ny, int nv){ this.x=nx;this.y=ny;this.value=nv; }
    // Matrix(){}
    public static Matrix Clone(){return new Matrix(x, y, value);}
    public String toStr(){return "["+x+", "+y+"]"+" = "+value;}
}

class SparseMatrix extends Matrix{ 
    private static Matrix[] sMatrixs;
    private static int capacity, values;
    SparseMatrix(int[][] init){
        super(0, 0, 0);
        capacity = 10; values = 0;
        sMatrixs = new Matrix[capacity];
        for(int i=0; i<init.length; i++){
            int[] temp = init[i];
            for(int j=0; j<temp.length; j++){
                if(init[i][j]!=0){
                    push(i, j, init[i][j]);
                }
            }
        }
    }
    public static void push(int nx, int ny, int nv){
        // if(values==capacity-1){
        //     Matrix[] tmp = new Matrix[capacity];
        //     for(int i=0; i<capacity; i++) tmp[i]=sMatrixs[i]; //.Clone()
        //     sMatrixs = new Matrix[capacity*2];
        //     for(int i=0; i<capacity; i++) sMatrixs[i]=tmp[i]; //.Clone()
        //     capacity*=2;
        // }
        sMatrixs[values++]=new Matrix(nx, ny, nv);
        for(int k=0; k<values; k++) System.out.println(sMatrixs[k].toStr());
        System.out.println("");
    }
    public void view(){
        for(int i=0; i<values; i++){
            // System.out.printf("%d, sm[%d, %d] = %d\n", i, sMatrixs[i].x, sMatrixs[i].y, sMatrixs[i].value);
        }
    }
}
