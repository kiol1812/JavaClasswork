/* 12/15 楊育哲
 * 實作第二題: non-static 練習
 */

public class h2_1215 {
    static public void main(String[] args){
        TopLevelClassTwo toRef = new TopLevelClassTwo();
        TopLevelClassTwo.NonStaticInnerClass innerRef1 = toRef.makeInstance();
        innerRef1.printMsg();

        // TopLevelClassTwo.NonStaticInnerClass innerRef2 = new TopLevelClassTwo.NonStaticInnerClass();
        TopLevelClassTwo.NonStaticInnerClass innerRef3 = toRef.new NonStaticInnerClass();

        ClassA a = new ClassA("1");
        ClassA.InnerB b = a.new InnerB("1");
        ClassA.InnerB.InnerC c = b.new InnerC("1");
        c.printMessage();

        ClassA.InnerB bb = new ClassA("2").new InnerB("2");
        ClassA.InnerB.InnerC cc = bb.new InnerC("2");
        cc.printMessage();

        ClassA.InnerB.InnerC ccc = new ClassA("3").new InnerB("3").new InnerC("3");
        ccc.printMessage();
    }
}
class TopLevelClassTwo{
    private static String msg = "shine the inner light.";
    public NonStaticInnerClass makeInstance(){return new NonStaticInnerClass();}
    class NonStaticInnerClass{
        private String string;
        public NonStaticInnerClass(){string=msg;}
        public void printMsg(){System.out.println(string);}
    }
}
class ClassA{
    private String msg = "ClassA obhect ";
    public ClassA(String objNo){msg+=objNo;}
    public void printMessage(){System.out.println(msg);}
    class InnerB{
        private String msg = "InnerB obhect ";
        public InnerB(String objNo){msg+=objNo;}
        public void printMessage(){System.out.println(msg);}
        class InnerC{
            private String msg = "InnerC obhect ";
            public InnerC(String objNo){msg+=objNo;}
            public void printMessage(){
                System.out.println(msg);
                System.out.println(this.msg);
                System.out.println(InnerC.this.msg);
                System.out.println(InnerB.this.msg);
                InnerB.this.printMessage();
                System.out.println(ClassA.this.msg);
                ClassA.this.printMessage();
            }
        }
    }
}
