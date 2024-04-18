/* 41143264 楊育哲 2024/04/18
 * 112學年第二學期期中考1第3題 > 實作檔案讀寫並排列 (!輸出部分改成了英文)
 */

import java.io.*;
import java.util.Scanner;

public class exam112_2_1_3 {
    public static void main(String args[]) throws IOException{
        Scanner sc = new Scanner(System.in);
        System.out.println("please input a number of size of students");
        int sizeOfStudents = sc.nextInt();
        Student[] students = new Student[sizeOfStudents];
        for(int i=0; i<students.length; i++){
            System.out.println("please input student's infomation: (like 'john' 45 80 91):");
            String n = sc.next();
            double s1=sc.nextDouble(), s2=sc.nextDouble(), s3=sc.nextDouble();
            sc.nextLine();
            students[i] = new Student(n, s1, s2, s3);
        }
        write("./data.txt", students);
        readAndSort("./data.txt");
    }
    public static void write(String filename, Student[] data) throws IOException {
        DataOutputStream dout = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(filename)));
        dout.writeInt(data.length);
        for(int i=0; i<data.length; i++){
            dout.writeUTF(data[i].name);
            dout.writeDouble(data[i].score1);
            dout.writeDouble(data[i].score2);
            dout.writeDouble(data[i].score3);
        }
        dout.close();
    }
    public static void readAndSort(String filename) throws IOException {
        DataInputStream din = new DataInputStream(new BufferedInputStream(new FileInputStream(filename)));
        int size = din.readInt();
        Student[] readStudents = new Student[size];
        double avgs[]=new double[size];
        for(int i=0; i<size; i++){
            String n = din.readUTF();
            double s1=din.readDouble(), s2=din.readDouble(), s3=din.readDouble();
            readStudents[i] = new Student(n, s1, s2, s3);
            avgs[i] = (s1+s2+s3)/3;
        }
        din.close();
        System.out.println("rank    name    avg    score1   score2   score3");
        for(int i=0; i<size; i++){
            double max=0;
            int index=0;
            for(int j=0; j<size; j++){
                if(max<avgs[j]){
                    index=j;
                    max=avgs[j];
                }
            }
            avgs[index]=0;
            System.out.println((i+1)+"       "+readStudents[index]);
        }
    }
}
class Student{
    String name;
    double score1, score2, score3; //國、英、數
    public Student(){}
    public Student(String n, double s1, double s2, double s3){
        name=n;
        score1=s1;
        score2=s2;
        score3=s3;
    }
    public String toString(){
        return name+"    "+(double)((int)((score1+score2+score3)/3*10))/10+"     "+score1+"    "+score2+"    "+score3;
    }
}