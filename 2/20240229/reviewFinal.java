/* 2024/02/29 楊育哲
 * 複習上學期期末( 112_1 )
*/
public class reviewFinal {
    public static void main(String args[]) {
        final int sizeOfSetA=4, sizeOfSetB=7;
        DataSet dataSetA, dataSetB;

        //initialize dataSetA
        int ax[]={5, 8, 7, 10}, ay[]={9, 12, 6, 7};
        // int ax[]={5, 8, 7, 10, 11}, ay[]={9, 12, 6, 7, 7};
        TwoDimPoint TwoDimPoints[] = new TwoDimPoint[sizeOfSetA];
        for(int i=0; i<sizeOfSetA; i++){
            TwoDimPoints[i]=new TwoDimPoint((double)ax[i], (double)ay[i]);
        }
        dataSetA = new DataSet(TwoDimPoints);
        //initialize dataSetB
        int bx[]={10, 9, 12, 8, 10, 6, 13}, by[]={8, 6, 6, 5, 5, 2, 1};
        // int bx[]={10, 9, 12, 8, 10, 6}, by[]={8, 6, 6, 5, 5, 2};
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

        // find data points localed within the intersection of bounding circle A and B (20%)
        DataSet icAB = dataSetB.boundingCircleInterset(dataSetA);
        System.out.println("data points within BB intersection: "+icAB);
    }
}
class Rectangle{
    private double x, y, width, height;
    Rectangle(double bottomLeftX, double bottomLeftY, double init_w, double init_h){
        this.x = bottomLeftX;
        this.y = bottomLeftY;
        this.width = init_w;
        this.height = init_h;
    }
    boolean contains(TwoDimPoint pt){
        final double ptX = pt.getX();
        final double ptY = pt.getY();
        if(ptX>=x&&ptX<=x+width&&ptY>=y&&ptY<=y+height){
            return true;
        }else return false;
    }
    public String toString(){
        return "bottom left=("+this.x+","+this.y+"), width="+width+", height="+height;
    }
}
class Circle{
    private TwoDimPoint center;
    private double radius;
    Circle(TwoDimPoint init_center, double init_radius){
        this.center = new TwoDimPoint(init_center.getX(), init_center.getY());
        this.radius = init_radius;
    }
    boolean contains(TwoDimPoint pt){
        return (center.getDistanceTo(pt)<=radius);
    }
    public String toString(){
        return "center=("+(int)(this.center.getX()*10)/10.0+","+(int)(this.center.getY()*10)/10.0+"), radius="+(int)(radius*10000)/10000.0;
    }
}
class TwoDimPoint{
    private double x, y;
    public TwoDimPoint(double init_x, double init_y){
        this.x = init_x;
        this.y = init_y;
    }
    public void setX(double new_x){
        this.x = new_x;
    }
    public void setY(double new_y){
        this.y = new_y;
    }
    public double getX(){
        return this.x;
    }
    public double getY(){
        return this.y;
    }
    public double getDistanceTo(TwoDimPoint pt){
        return (Math.sqrt(Math.pow(pt.getX()-x, 2)+Math.pow(pt.getY()-y, 2)));
    }
    public String toString(){
        return "("+(int)(this.x*10)/10.0+","+(int)(this.y*10)/10.0+")";
    }
}
class DataSet{
    final int defaultCapacity=10;
    private int capacity, top;
    private TwoDimPoint[] points;
    public DataSet(){
        this.capacity = defaultCapacity;
        this.top = 0;
        this.points = new TwoDimPoint[capacity];
    }
    public DataSet(TwoDimPoint[] init){
        this.capacity = init.length*2;
        this.top = init.length;
        this.points = new TwoDimPoint[capacity];
        for(int i=0; i<init.length; i++){
            points[i] = new TwoDimPoint(init[i].getX(), init[i].getY());
        }
    }
    public int size(){
        return top;
    }
    public TwoDimPoint getPt(int index){
        if(index>=0&&index<top){
            return new TwoDimPoint(points[index].getX(), points[index].getY());
        }
        return null;
    }
    public void addTwoDimPoint(TwoDimPoint newPoint){
        if(top==capacity-1){
            TwoDimPoint[] tmp = new TwoDimPoint[capacity*2];
            for(int i=0; i<top; i++){
                tmp[i] = new TwoDimPoint(points[i].getX(), points[i].getY());
            }
            capacity*=2;
            points = new TwoDimPoint[capacity];
            points = tmp;
        }
        points[top++] = new TwoDimPoint(newPoint.getX(), newPoint.getY());
    }
    public Rectangle findBoundingBox(){
        double rx=1000, ry=1000, rw=0, rh=0;
        for(int i=0; i<top; i++){
            rx = Math.min(rx, points[i].getX());
            ry = Math.min(ry, points[i].getY());
            rw = Math.max(rw, points[i].getX());
            rh = Math.max(rh, points[i].getY());
        }
        return new Rectangle(rx, ry, Math.abs(rw-rx), Math.abs(rh-ry));
    }
    public Circle findBoundingCircle(){
        double cx=0, cy=0, cr=0;
        for(int i=0; i<top; i++){
            cx+=points[i].getX();
            cy+=points[i].getY();
        }
        TwoDimPoint cp = new TwoDimPoint(cx/top, cy/top);
        for(int i=0; i<top; i++){
            double distance = cp.getDistanceTo(points[i]);
            cr = Math.max(cr, distance);
        }
        return new Circle(cp, cr);
    }
    public DataSet boundingBoxInterset(DataSet B){
        DataSet result = new DataSet();
        Rectangle rA = findBoundingBox();
        Rectangle rB = B.findBoundingBox();
        for(int i=0; i<size(); i++){
            if(rB.contains(points[i]))
                result.addTwoDimPoint(points[i]);
        }
        for(int i=0; i<B.size(); i++){
            if(rA.contains(B.getPt(i)))
                result.addTwoDimPoint(B.getPt(i));
        }
        return result;
    }
    public DataSet boundingCircleInterset(DataSet B){
        DataSet result = new DataSet();
        Circle cA = findBoundingCircle();
        Circle cB = B.findBoundingCircle();
        for(int i=0; i<size(); i++){
            if(cB.contains(points[i]))
                result.addTwoDimPoint(points[i]);
        }
        for(int i=0; i<B.size(); i++){
            if(cA.contains(B.getPt(i)))
                result.addTwoDimPoint(B.getPt(i));
        }
        return result;
    }
    public String toString(){
        String s="";
        for(int i=0; i<top; i++){
            s += ((i>0)?",":" ")+points[i];
        }
        return s;
    }
}
