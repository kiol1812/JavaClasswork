/* 2024/01/12 楊育哲
 * java(一) 期末考
 */
public class JavaFinal_112_1{
    public static void main(String args[]){
        final int sizeOfSetA=5, sizeOfSetB=6;
        DataSet dataSetA, dataSetB;

        //initialize dataSetA
        int ax[]={5, 8, 7, 10, 11}, ay[]={9, 12, 6, 7, 7};
        TwoDimPoint TwoDimPoints[] = new TwoDimPoint[sizeOfSetA];
        for(int i=0; i<sizeOfSetA; i++){
            TwoDimPoints[i]=new TwoDimPoint(ax[i],ay[i]);
        }
        dataSetA = new DataSet(TwoDimPoints);
        //initialize dataSetB
        int bx[]={10, 9, 12, 8, 10, 6}, by[]={8, 6, 6, 5, 5, 2};
        dataSetB = new DataSet();
        for(int i=0; i<sizeOfSetB; i++){
            dataSetB.addTwoDimPoint(new TwoDimPoint(bx[i],by[i]));
        }

        //print data set A and B (20%)
        System.out.println("setA: "+dataSetA);
        System.out.println("setB: "+dataSetB);

        //print bounding box of set A and B (20%)
        System.out.println("setA BB: "+dataSetA.findBoundingBox());
        System.out.println("setB BB: "+dataSetB.findBoundingBox());

        //find data points localed within the intersection of bounding box A and B (20%)
        DataSet ibAB = dataSetA.boundingBoxInterset(dataSetB);
        System.out.println("data points within BB intersection: "+ibAB);

        //print bounding CIRCLE of set A and B (20%)
        System.out.println("setA BC: "+dataSetA.findBoundingCircle());
        System.out.println("setB BC: "+dataSetB.findBoundingCircle());

        //find data points localed within the intersection of bounding circle A and B (20%)
        // DataSet icAB = dataSetA.boundingCircleInterset(dataSetB);
        // System.out.println("data points within BB intersection: "+icAB);
    }
}
class TwoDimPoint{
    public int x, y;
    public TwoDimPoint(int new_x, int new_y){ x=new_x;y=new_y; }
    public String toString(){
        return "("+x+","+y+")";
    }
}
class DataSet{
    private TwoDimPoint[] points;
    public DataSet(){
        points=new TwoDimPoint[0];
    }
    public DataSet(TwoDimPoint[] init){
        points=new TwoDimPoint[init.length];
        for(int i=0; i<init.length; i++){
            points[i]=new TwoDimPoint(init[i].x, init[i].y);
        }
    }
    public void addTwoDimPoint(TwoDimPoint newPoint){
        TwoDimPoint[] tmp=new TwoDimPoint[points.length+1];
        for(int i=0; i<points.length; i++)
            tmp[i]=new TwoDimPoint(points[i].x,points[i].y);
        tmp[points.length] = new TwoDimPoint(newPoint.x, newPoint.y);
        points = new TwoDimPoint[points.length+1];
        points=tmp;
    }
    public String toString(){
        String s="";
        for(int i=0; i<points.length; i++){
            if(i!=points.length-1) s+=points[i]+",";
            else s+=points[i];
        }
        return s;
    }
    public int[] bottomLeft(){
        int topX=0, topY=0, bottomX=100, bottomY=100;
        for(int i=0; i<points.length; i++){
            if(topX<points[i].x)topX=points[i].x;
            if(bottomX>points[i].x)bottomX=points[i].x;
            if(topY<points[i].y)topY=points[i].y;
            if(bottomY>points[i].y)bottomY=points[i].y;
        }
        int ans[]=new int[4]; ans[0]=topX; ans[1]=bottomX; ans[2]=topY; ans[3]=bottomY;
        return ans;
    }
    public String findBoundingBox(){
        String s="bottom Left=(";
        int xy[]=bottomLeft();
        int topX=xy[0], bottomX=xy[1], topY=xy[2], bottomY=xy[3];
        s+=bottomX+","+bottomY+"), width="+(topX-bottomX)+", height="+(topY-bottomY);
        return s;
    }
    public DataSet boundingBoxInterset(DataSet B){
        DataSet ib=new DataSet();
        int xyA[]=bottomLeft(), xyB[]=B.bottomLeft();
        for(int i=0; i<points.length; i++){
            if(points[i].x<=xyB[0]&&points[i].x>=xyB[1]&&points[i].y<=xyB[2]&&points[i].y>=xyB[3]){
                ib.addTwoDimPoint(points[i]);
            }
        }
        for(int i=0; i<B.points.length; i++){
            if(B.points[i].x<=xyA[0]&&B.points[i].x>=xyA[1]&&B.points[i].y<=xyA[2]&&B.points[i].y>=xyA[3]){
                ib.addTwoDimPoint(B.points[i]);
            }
        }
        return ib;
    }
    public String findBoundingCircle(){
        double cx=0, cy=0;
        for(int i=0; i<points.length; i++){
            cx+=points[i].x;
            cy+=points[i].y;
        }
        cx/=points.length;
        cy/=points.length;
        double radius=0;
        for(int i=0; i<points.length; i++){
            if(((points[i].x-cx)*(points[i].x-cx)+(points[i].y-cy)*(points[i].y-cy))>radius){
                radius=((points[i].x-cx)*(points[i].x-cx)+(points[i].y-cy)*(points[i].y-cy));
            }
        }
        radius=Math.sqrt(radius);
        int tmpx=(int)(cx*10), tmpy=(int)(cy*10);
        cx=(double)(tmpx)/10; cy=(double)(tmpy)/10;
        String s="center=("+cx+","+cy+"), radius="+radius;
        return s;
    }
    // public DataSet boundingCircleInterset(DataSet B){
    // }
}