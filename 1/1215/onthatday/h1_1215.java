/* 12/15 楊育哲
 * 實作第一題: static inner 練習
 */
public class h1_1215 {
    public static void main(String[] args){
        TopLevelClass.NestedTopLevelClass.NestedTopLevelClass1 objRef1 = new TopLevelClass.NestedTopLevelClass.NestedTopLevelClass1();
        objRef1.test();
    }  
}
class TopLevelClass {
    static class NestedTopLevelClass {
        static class NestedTopLevelClass1 {
            public void test(){System.out.println("flag.");}
        }
    }
}
// public class h1_1215 { //AccessInTopLevelClass
//     public void nonStaticMethod(){System.out.println("nonStaticMethod in AccessInTopLevelClass.");}
//     private static class NestedTopLevelClass {
//         private static int i;
//         private int j;
//         private static void staticMethod(){System.out.println("staticMethod in NestedLevelClass.");}
//         private static class NestedTopLevelClass1 {
//             private static int k=2000;
//             private static void anotherNonstaticMethod(){
//                 int ii=i; int kk;
//                 staticMethod();
//             }
//             public static void main(String[] args){
//                 int ii=i;
//                 staticMethod();
//             }
//         }
//     }
// }