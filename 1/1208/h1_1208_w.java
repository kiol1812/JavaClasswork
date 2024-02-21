/* 12/08 楊育哲
 * 實作第一題: 點集合的距離計算(三種)
 */
public class h1_1208_w {
    static class MyPoint{
        private int x;
        private int y;
        MyPoint(int x_, int y_){this.x=x_; this.y=y_;}
        public double dist(MyPoint b){
            return Math.sqrt(Math.pow((x-b.x), 2)+Math.pow((y-b.y), 2));
        }
    }
    static class MyPointSet{
        private MyPoint[] set;
        private int points;
        MyPointSet(MyPoint[] a){
            points = a.length;
            set = a;
        }
        public double completeLink(MyPointSet B){
            double ans=0;
            for(int i=0; i<points; i++){
                for(int j=0; j<B.points; j++){
                    double temp = set[i].dist(B.set[j]);
                    if(ans<temp) ans=temp;
                }
            }
            return ans;
        }
        public double singleLink(MyPointSet B){
            double ans = 1000000;// or ans=completeLink(MyPoint B); 這樣比較保險，但時間會較久
            for(int i=0; i<points; i++){
                for(int j=0; j<B.points; j++){
                    double temp = set[i].dist(B.set[j]);
                    if(ans>temp) ans=temp;
                }
            }
            return ans;
        }
        public double averageLink(MyPointSet B){
            double ans=0;
            for(int i=0; i<points; i++){
                for(int j=0; j<B.points; j++){
                    ans+=set[i].dist(B.set[j]);
                }
            }
            return ans/(points*B.points);
        }
        void view(){
            for(int i=0; i<points; i++)
                System.out.printf("(%d, %d), ", set[i].x, set[i].y);
            System.out.printf("points: %d\n", points);
        }
    }
    static public void main(String args[]){
        MyPoint[] ptA = new MyPoint[5];
        MyPoint[] ptB = new MyPoint[5];
        for(int i=0; i<5; i++) ptA[i]=new MyPoint(i, i);
        for(int i=0; i<5; i++) ptB[i]=new MyPoint(-1, i*2);
        MyPointSet A = new MyPointSet(ptA);
        MyPointSet B = new MyPointSet(ptB);
        System.out.println("A and ptA[] information:");
        A.view();
        System.out.println("B and ptB[] information:");
        B.view();

        System.out.printf("ptA[3].dist(ptB[3]) = %f\n", ptA[3].dist(ptB[3]));//(3, 3) <-> (-1, 6) dist=5

        System.out.println("complete linkage of A and B is "+ A.completeLink(B)); //根號(64+1): (0, 0) , (-1, 8)
        System.out.println("single linkage of B and A is "+ B.singleLink(A));// 1: (-1, 0) , (0, 0)
        System.out.println("average linkage of A and B is "+ A.averageLink(B));
    }
}
