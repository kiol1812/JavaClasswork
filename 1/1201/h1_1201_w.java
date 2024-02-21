/* 12/01  楊育哲
 * 實作第一題: 設計myVector ,可記錄多維度向量
 */
public class h1_1201_w {
    static class MyVector{
        private float[] vector;
        MyVector(float init[]){
            vector = new float[init.length];
            for(int i=0; i<init.length; i++){
                vector[i] = init[i];
            }
        }
        float norm(){
            float ans=0;
            for(int i=0; i<vector.length; i++) ans+=vector[i]*vector[i];
            return ans/2;
        }
        float innorProduct(MyVector b){
            float ans=0;
            if(vector.length!=b.vector.length) return 0;//
            for(int i=0; i<vector.length; i++) ans+=vector[i]*b.vector[i];
            return ans;
        }
        float theta(MyVector b){
            return innorProduct(b)/(norm()*b.norm());
        }
        void Print(){
            System.out.print("(");
            for(int i=0; i<vector.length-1; i++) System.out.printf("%7.2f,", vector[i]);
            System.out.printf("%7.2f)\n", vector[vector.length-1]);
        }
    }
    static public void main(String args[]){
        float[] a = {1, 2, 3};
        MyVector A = new MyVector(a);
        float[] b = {4, 5, 6};
        MyVector B = new MyVector(b);
        A.Print();
        B.Print();
        System.out.printf("A.norm: %f\n", A.norm());
        System.out.printf("B.norm: %f\n", B.norm());
        System.out.printf("innor product : %5.2f\n", A.innorProduct(B));
        System.out.printf("theta : %10.9f\n", A.theta(B));
    }
}
