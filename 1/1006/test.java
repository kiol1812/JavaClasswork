public class test{
    public static String trans(String str){
        StringBuffer uniBuf = new StringBuffer();
        for(int i=0; i<str.length(); i++){
            char c = str.charAt(i);
            uniBuf.append(Integer.toHexString(c)+" ");
        }
        return uniBuf.toString();
    }
    public static void main(String args[]){
        String name="楊育哲";
        System.out.println(name);
        System.out.println(trans(name));
    }
}