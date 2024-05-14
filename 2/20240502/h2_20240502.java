/* 2024/05/02 41143264 楊育哲
 * 作業二: 實作圖片要求
 */

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class h2_20240502 {
    private static myFrame frms[] = new myFrame[24];
    private static int size=60;
    public static void main(String args[]) throws InterruptedException{
        Frame mainWin = new Frame("h2");
        mainWin.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e){ System.exit(0); }
        });
        mainWin.setSize(600, 600);
        mainWin.setLayout(null);
        boolean dir=true;
        for(int i=0; i<4; i++){
            if(dir){
                for(int j=0; j<6; j++){
                    frms[i*6+j]=new myFrame(String.valueOf(i*6+j+1), i*size+30, j*size);
                    mainWin.add(frms[i*6+j]);
                }
            }else{
                for(int j=0; j<6; j++){
                    frms[i*6+j]=new myFrame(String.valueOf(i*6+j+1), i*size+30, (5-j)*size);
                    mainWin.add(frms[i*6+j]);
                }
            }
            dir = !dir;
        }
        mainWin.setVisible(true);
        for(int i=0; i<24; i++){
            frms[i].setVisible(true);
            if(i>=5) frms[i-5].setVisible(false);
            Thread.sleep(1000);
        }
        for(int i=19; i<24; i++){
            frms[i].setVisible(false);
            Thread.sleep(1000);
        }
        for(int j=0; j<2; j++){
            for(int i=0; i<24; i++){
                frms[i].setVisible(true);
            }
            Thread.sleep(2000);
            for(int i=0; i<24; i++){
                frms[i].setVisible(false);
            }
            Thread.sleep(2000);
        }
        while(true){
            int select = (int)(Math.random()*24);
            frms[select].setVisible(true);
            Thread.sleep(1000);
            frms[select].setVisible(false);
        }
    }
}
class myFrame extends Button{
    private int size=60;
    public myFrame(String str, int y, int x){
        super(str);
        setSize(size, size);
        setLocation(x, y);
        setVisible(false);
    }
}
