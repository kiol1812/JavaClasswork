/* 20240411 楊育哲 41143264
 * 檔案讀寫練習: 比較八原始資料型態(boolean, byte, short, int, char, long, float, double)
 */

 import java.io.*;

 public class h1_20240411 {
    public static void main(String args[]) throws IOException {
        Data A = new Data();
        A.fileWrite("./file.txt");
        A.fileRead("./file.txt");
        A.byteWrite("./byteData.txt");
        A.byteRead("./byteData.txt");
    }
 }

class Data {
    private String id;
    private boolean bool_;
    private byte byte_;
    private short short_;
    private int int_;
    private char char_;
    private long long_;
    private float float_;
    private double double_;
    public Data(){
        this.id = "楊育哲 41143264";
        this.bool_ = false;
        this.byte_ = (byte)(Math.random()*100%127);
        this.short_ = (short)(Math.random()*100000%32767);
        this.int_ = (int)(Math.random()*1000000000%2147483647);
        this.char_ = (char)((Math.random()*100)%26+65);
        this.long_ = (long)(Math.random()*1000000000000000000L%9223372036854775807L);
        this.float_ = (float)Math.random();
        this.double_ = Math.random();
    }
    public void fileWrite(String fileName) throws IOException {
        FileWriter fw = new FileWriter(fileName);
        BufferedWriter bfw = new BufferedWriter(fw);
        bfw.write(id+"\n");

        bfw.write(Boolean.toString(false)+"\n");
        // bfw.newLine();
        bfw.write(Byte.toString(byte_)+"\n");
        bfw.write(Short.toString(short_)+"\n");
        bfw.write(Integer.toString(int_)+"\n");
        bfw.write(Character.toString(char_)+"\n");
        bfw.write(Long.toString(long_)+"\n");
        bfw.write(Float.toString(float_)+"\n");
        bfw.write(Double.toString(double_)+"\n");
        
        bfw.flush();
        fw.close();
    }
    public void fileRead(String fileName) throws IOException {
        FileReader fr = new FileReader(fileName);
        BufferedReader bfr = new BufferedReader(fr);
        String Rid = bfr.readLine();
        boolean Rbool = Boolean.valueOf(bfr.readLine());
        byte Rbyte_ = Byte.valueOf(bfr.readLine());
        short Rshort_ = Short.valueOf(bfr.readLine());
        int Rint_ = Integer.valueOf(bfr.readLine());
        char Rchar_ = bfr.readLine().charAt(0);
        long Rlong_ = Long.valueOf(bfr.readLine());
        float Rfloat_ = Float.valueOf(bfr.readLine());
        double Rdouble_ = Double.valueOf(bfr.readLine());
        System.out.println(Rid); //equals(id)
        System.out.println(Rbool);
        System.out.println(Rbyte_);
        System.out.println(Rshort_);
        System.out.println(Rint_);
        System.out.println(Rchar_);
        System.out.println(Rlong_);
        System.out.println(Rfloat_);
        System.out.println(Rdouble_);
        fr.close();
    }
    public void byteWrite(String fileName) throws IOException {
        DataOutputStream dout = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)));
        // dout.writeChars("楊育哲 41143264");
        dout.writeBoolean(bool_);
        dout.writeByte(byte_);;
        dout.writeShort(short_);
        dout.writeInt(int_);
        dout.writeChar(char_);
        dout.writeLong(long_);
        dout.writeFloat(float_);
        dout.writeDouble(double_);
        dout.close();
    }
    public void byteRead(String fileName) throws IOException {
        // DataInputStream fiileIn = new DataInputStream(new InputStream())
        DataInputStream din = new DataInputStream(new BufferedInputStream(new FileInputStream(fileName)));
        
        // char each=' ';
        // String Rid="";
        // while(each!='\n'){
        //     each = din.readChar();
        //     Rid+=each;
        // }
        boolean Rbool = din.readBoolean();
        byte Rbyte_ = din.readByte();
        short Rshort_ = din.readShort();
        int Rint_ = din.readInt();
        char Rchar_ = din.readChar();
        long Rlong_ = din.readLong();
        float Rfloat_ = din.readFloat();
        double Rdouble_ = din.readDouble();
        // System.out.println(Rid); //equals(id)
        System.out.println(Rbool);
        System.out.println(Rbyte_);
        System.out.println(Rshort_);
        System.out.println(Rint_);
        System.out.println(Rchar_);
        System.out.println(Rlong_);
        System.out.println(Rfloat_);
        System.out.println(Rdouble_);
        din.close();
    }
}
