/* 12/29 楊育哲
 * 實作範例程式, 多型練習 
 */
public class h1_1229 {
    public static void main(String args[]){
        CShape shp[]=new CShape[5];
        shp[0]=new CCircle(12);       // 144*PI
        shp[1]=new CCircle(21);       // 441*PI
        shp[2]=new CSquare(15);       // 225
        shp[3]=new CTriangle(12, 7);// 42
        shp[4]=new CTriangle(3, 18);// 27
        CShape.largest(shp); // shp[1]
    }
}
class CShape{
    private float myArea;
    public float area(){ return myArea; }
    public String shape(){ return "unknow"; }
    public static void largest(CShape[] arr){
        float max=0;
        int index=0;
        for(int i=0; i<arr.length; i++){
            if(max<arr[i].area()){
                max=arr[i].area();
                index=i;
            }
        }
        System.out.println("index: "+index+", largest shape is "+arr[index].shape()+", and it's area: "+arr[index].area());
    }
}
class CCircle extends CShape{
    private float r;
    public String shape(){ return "circle"; }
    CCircle(float R){ r=R; }
    public float area(){ return (float)(Math.acos(-1)*r*r); }
}
class CSquare extends CShape{
    private float h;
    public String shape(){ return "square";}
    CSquare(float H){ h=H; }
    public float area(){ return h*h; }
}
class CTriangle extends CShape{
    private float w, h;
    public String shape(){ return "triangle"; }
    CTriangle(float W, float H){ w=W;h=H; }
    public float area(){ return w*h/2; }
}