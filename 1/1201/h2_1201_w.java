/* 1201 楊育哲
 * 實作第二題: myMatrix
 */
public class h2_1201_w {
    static class MyVector{
        private float[] vector;
        MyVector(float init[]){
            vector = new float[init.length];
            for(int i=0; i<init.length; i++){
                vector[i] = init[i];
            }
        }
    }
    static class MyMatrix{
        private float[][] array;
        MyMatrix(float init[][]){
            array = new float[init.length][];
            for(int i=0; i<init.length; i++){
                array[i] = new float[init[i].length];
                for(int j=0; j<init[0].length; j++){
                    array[i][j] = init[i][j];
                }
            }
        }
        MyMatrix Add(MyMatrix b){
            float[][] c = new float[array.length][];
            for(int i=0; i<array.length; i++){
                c[i] = new float[array[i].length];
                for(int j=0; j<array[1].length; j++){
                    c[i][j] = array[i][j]+b.array[i][j];
                }
            }
            MyMatrix C = new MyMatrix(c);
            return C;
        }
        MyMatrix Minus(MyMatrix b){
            float[][] c = new float[array.length][];
            for(int i=0; i<array.length; i++){
                c[i] = new float[array[i].length];
                for(int j=0; j<array[1].length; j++){
                    c[i][j] = array[i][j]-b.array[i][j];
                }
            }
            MyMatrix C = new MyMatrix(c);
            return C;
        }
        MyMatrix Mult_matrix(MyMatrix b){
            int h=Math.max(array.length, array[0].length);
            int w=Math.min(array.length, array[0].length);
            float[][] c = new float[h][];
            for(int i=0; i<h; i++){
                c[i] = new float[h];
                for(int j=0; j<h; j++){
                    for(int k=0; k<w; k++){
                        c[i][j]+=array[i][k]*b.array[k][j];
                    }
                }
            }
            MyMatrix C = new MyMatrix(c);
            return C;
        }
        MyMatrix Mult_vector(MyVector b){
            float[][] c = new float[array.length][];
            for(int i=0; i<array.length; i++){
                c[i] = new float[array[i].length];
                for(int j=0; j<array[1].length; j++){
                    c[i][j] = array[i][j]*b.vector[i];
                }
            }
            MyMatrix C = new MyMatrix(c);
            return C;
        }
        void Print(){
            for(int i=0; i<array.length; i++){
                for(int j=0; j<array[1].length; j++){
                    System.out.printf("%5.2f ", array[i][j]);
                }
                System.out.println("");
            }
        }
    }
    static public void main(String args[]){
        float[][] a = {{1, 2},
                       {3, 4},
                       {5, 6}};
        MyMatrix A = new MyMatrix(a);
        float[][] a_ = {{6, 5},
                       {4, 3},
                       {2, 1}};
        MyMatrix A_ = new MyMatrix(a_);
        float[][] b = {{1, 2, 3},
                       {4, 5, 6}};
        MyMatrix B = new MyMatrix(b);
        float[] c = {2, 3, 4};
        MyVector C = new MyVector(c);
        MyMatrix Ac = A.Mult_vector(C);
        MyMatrix AB = A.Mult_matrix(B);
        MyMatrix F = A_.Add(A);
        MyMatrix G = A_.Minus(A);
        System.out.println("A:");
        A.Print();//原始A矩陣
        System.out.println("A_:");
        A_.Print();//原始A_矩陣
        System.out.println("B:");
        B.Print();//原始B矩陣
        System.out.println("A * C{vector->{2, 3, 4}}:");
        Ac.Print();//A矩陣乘上向量C
        System.out.println("A * B{matrix}:");
        AB.Print();//A矩陣乘上矩陣B
        System.out.println("A_ + A");
        F.Print();//矩陣A_加上矩陣A
        System.out.println("A_ - A");
        G.Print();//矩陣A_減去矩陣A
    }
}
