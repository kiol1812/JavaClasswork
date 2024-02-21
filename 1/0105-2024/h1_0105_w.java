/* 2024/01/05 楊育哲
 * 實作第三題: 利用內部類別、以及繼承類別方式，為一家百貨銷售商設計出一些類別，
 *             可以依照不同的顧客: 一般顧客、會員、VIP。
 *              a. 提供不同的貨物價格
 *              b. 以及享有不同的服務。如:特定商品只有會員以上、甚至VIP以上才可以預訂，
 *                 參考附件的probusts.txt檔，寫一個主程式來展現設計。
 */
public class h1_0105_w {
    public static void main(String args[]){
        final double MEMBER_DISCOUNT=0.95, VIP_DISCOUNT=0.9;
        DepartmentStore soGo = new DepartmentStore(MEMBER_DISCOUNT, VIP_DISCOUNT);
        DepartmentStore.Customer cs = soGo.new Customer();
        DepartmentStore.Member   mb = soGo.new Member();
        DepartmentStore.VIP     vip = soGo.new VIP();
        System.out.println("--------customer--------");
        cs.printProductList();
        System.out.println("--------member--------");
        mb.printProductList();
        System.out.println("--------vip--------");
        vip.printProductList();
        System.out.println("--------price query--------");
        System.out.println("customer t-shirt: "+cs.getPrice("t-shirt"));
        System.out.println("member   t-shirt: "+mb.getPrice("t-shirt"));
        System.out.println("vip      t-shirt: "+vip.getPrice("t-shirt"));
        System.out.println("--------reserve--------");//展現預定功能
        mb.reserve("watch");// 權限不足，所以輸出reserve failed 、 can't find product
        vip.reserve("watch");// 權限足夠，輸出reserve successful，並做預定處理
    }
}
class Product{
    private String name;
    private int price, access, quantity;
    Product(String n, int p, int a, int q){ name=n;price=p;access=a;quantity=q; }
    Product(String n, int p, int a){ name=n;price=p;access=a;quantity=10; }
    public Product deepClone(){return new Product(name, price, access);}
    public String toString(int a, double discount){//權限a足夠才輸出。
        if(a>=access){
            return name+" "+(int)(price*discount)+"\n";
        }return "";
    }
    public int get_price(String n){//依名稱回傳價格
        if(name==n)return price;
        return 0;
    }
    public boolean reserveProcess(String n, int a){//預定處理
        if(name==n){// 對應到產品名稱
            if(a>=access&&quantity>0){// 權限和數量足夠 
                quantity--;
                System.out.println("reserve successful.");
                return true;
            }else System.out.println("reserve failed.");
        }
        return false;// 沒對應到或權限、數量不足
    }
}
class DepartmentStore{
    private double memberDiscount, vipDiscount;
    private Product[] list = new Product[10];
    private int products;// 產品數量
    DepartmentStore(double md, double vd){
        memberDiscount=md;
        vipDiscount=vd;
        list[0]=new Product("condy", 10, 0);
        list[1]=new Product("t-shirt", 250, 0);
        list[2]=new Product("hat", 300, 0);
        list[3]=new Product("jean", 1200, 0);
        list[4]=new Product("belt", 800, 0);
        list[5]=new Product("limited_cup", 500, 1);
        list[6]=new Product("limited_pen", 800, 1);
        list[7]=new Product("limited_bottle", 300, 1);
        list[8]=new Product("watch", 100000, 2);
        list[9]=new Product("glass", 15000, 2);
        products=10;
    }
    class Customer{
        private Product[] reserveList;// 預定清單
        private int reserveProducts;// 預定數量
        private int userAccess=0;
        public void printProductList(){// 產品清單輸出
            for(int i=0; i<products; i++){
                System.out.print(list[i].toString(userAccess, 1));
            }
        }
        public int getPrice(String item){//回傳對應商品的價格
            int price_=0;
            for(int i=0; i<products; i++){
                price_+=list[i].get_price(item);
                if(price_>0) return price_;
            }
            return 0;
        }
        public void reserve(String item){//預定功能，會員及VIP才有權限可以預訂
            for(int i=0; i<products; i++){
                if(list[i].reserveProcess(item, userAccess)){
                    reserveList[reserveProducts++]=list[i].deepClone();
                    return;
                }
            }
            System.out.println("can't find product.");
        }
    }
    class Member extends Customer{
        Member(){ super.reserveList=new Product[10];super.reserveProducts=0;super.userAccess=1; }
        public void printProductList(){//多型，會員可購買的產品清單輸出
            for(int i=0; i<products; i++){
                System.out.print(list[i].toString(super.userAccess, memberDiscount));
            }
        }
        public int getPrice(String item){//多型，回傳價格乘上會員折扣
            return (int)(super.getPrice(item)*memberDiscount);
        }
    }
    class VIP extends Customer{
        VIP(){ super.reserveList=new Product[10];super.reserveProducts=0;super.userAccess=2; }
        public void printProductList(){//多型，VIP可購買的產品清單輸出
            for(int i=0; i<products; i++){
                System.out.print(list[i].toString(super.userAccess, vipDiscount));
            }
        }
        public int getPrice(String item){//多型，回傳價格乘上VIP折扣
            return (int)(super.getPrice(item)*vipDiscount);
        }
    }
}
