/* 12/22 
 * 實作第一題: sparse matrix
 */
public class h1_test {
    public static void main(String args[]){
        SparseMatrix test = new SparseMatrix();
        test.push(new Matrix(1, 1, 1));
        test.push(new Matrix(0, 0, 0));
        // test.view();
    }
}

class Matrix{
    protected static int x, y, v;
    protected static Matrix next;
    Matrix(int nx, int ny, int nv){ x=nx;y=ny;v=nv; }
    Matrix(){ x=0;y=0;v=0;next=null; }
    public static Matrix Clone(){ return new Matrix(x, y, v); }
    public static String toStr(){ return "["+x+", "+y+"] = "+v; }
    public static Matrix getNext(){ return next; }
}
class SparseMatrix extends Matrix{
    // private static Matrix[] sm;
    Matrix head;
    private static int capacity, values;
    SparseMatrix(){
        super(0, 0, 0);
        values=0; capacity=10;
        head = new Matrix();
    }
    public void push(Matrix item){
        Matrix current = this.head;
        while(current!=null){
            System.out.println(current.toStr());
            current=current.getNext();
        }
        current.next=item;
    }
    public void view(){
        Matrix current = this.head;
        while(current!=null){
            System.out.println(current.toStr());
            current=current.getNext();
        }
    }
}
