/* 2024/05/02 41143264 楊育哲
 * 作業一: 實作文字跑馬燈
 */

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.nio.CharBuffer;

public class h1_20240502 {
    private static int stars_number=50;
    public static void main(String args[]) throws InterruptedException{
        Frame frm = new Frame("h1");
        frm.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e){ System.exit(0); }
        });
        frm.setSize(600, 400);
        frm.setLayout(null);
        frm.setBackground(Color.black);
        myLabel stars[] = new myLabel[stars_number];
        for(int i=0; i<stars_number; i++){
            stars[i]=new myLabel("*", (int)(Math.random()*600), (int)(Math.random()*400), 30, 30);
            frm.add(stars[i]);
        }
        myLabel lab1 = new myLabel("Closing credits:", 30, 200, 200, 30);
        myLabel lab2 = new myLabel("author: 41143264", 60, 200, 200, 30);
        myLabel lab3 = new myLabel("animater: 41143264", 90, 200, 200, 30);
        frm.add(lab1);
        frm.add(lab2);
        frm.add(lab3);
        frm.setVisible(true);
        while(true){
            lab1.moveDown();
            lab2.moveDown();
            lab3.moveDown();
            for(int i=0; i<stars_number; i++) stars[i].moveRU();
            Thread.sleep(100);
        }
    }
}
class myLabel extends Label{
    private int positionX, positionY;
    myLabel(){}
    myLabel(String str, int y, int x, int w, int h){
        super(str); //setText(str);
        positionX=x;
        positionY=y;
        setLocation(x, y);
        setSize(w, h);
        setAlignment(Label.CENTER);
        setForeground(Color.WHITE);
        setBackground(Color.BLACK);
        setFont(new Font("Serief", Font.ITALIC+Font.BOLD, 18));
    }
    public void moveDown(){
        positionY = (positionY+1)%480+20;
        super.setLocation(positionX, positionY);
        super.setForeground(new Color((int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255)));
    }
    public void moveRU(){
        positionX = (positionX+2)%580+20;
        positionY = (positionY+1)%380+20;
        super.setLocation(positionX, positionY);
        super.setForeground(new Color((int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255)));
    }
}
