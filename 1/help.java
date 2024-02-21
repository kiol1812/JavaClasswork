public class help{
    public static void main(String args[]){
        String s="011110010010010010011110000010", ss="";
        for(int i=s.length()-1; i>=0; i--) ss+=s.charAt(i);
        System.out.println(ss);
    }
}