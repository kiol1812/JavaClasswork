/* 12/22 楊育哲
 * 實作第二題: 繼承練習2
 */
public class p2_1222 {
    public static void main(String args[]){
        Manager andy = new Manager("andy", 1, 10000);
        Developer cammy = new Developer("cammy", 2, 10000);
        System.out.println("developer's salary: "+cammy.getSalary());
        andy.adjustSalary(15000);
        System.out.println("after salary adjusted, developer's salary: "+cammy.getSalary());
        System.out.println("before cammy work overtime, she's bouns: "+cammy.getBouns());
        cammy.overtime();
        System.out.println("after cammy work overtime once, she's bouns: "+cammy.getBouns());
    }
}
class Employee{
    private int id, salary;
    static int adjustedSalary;
    private String name;
    Employee(String n, int i, int s){name=n; id=i; salary=s;adjustedSalary=s;}
    Employee(){}
    static void setSalary(int s){adjustedSalary=s;}
    public int getSalary(){ salary=adjustedSalary; return salary;}
    public int getBouns(){return salary*3;}
}
class Manager extends  Employee{
    Manager(String n, int i, int s){super(n, i, s);}
    @Override
    public int getSalary(){ return super.getSalary()+10000; }
    @Override
    public int getBouns(){ return super.getBouns()*3; }
    public void adjustSalary(int s){setSalary(s);}
}
class Developer extends Employee{
    private float rate=2;
    Developer(String n, int i, int s){super(n, i, s);}
    @Override
    public int getSalary(){
        Employee check =  new Employee();
        if(check.getSalary()!=super.getSalary()) super.setSalary(check.getSalary());
        return super.getSalary()+5000; }
    @Override
    public int getBouns(){ return (int)(super.getBouns()*rate); }
    public void overtime(){ rate+=0.1; }
}
