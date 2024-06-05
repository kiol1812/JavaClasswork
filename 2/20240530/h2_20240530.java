// 024/05/30 41143264 楊育哲
// pacman

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.*;
import javax.swing.Timer;

public class h2_20240530 extends Frame {
    private final int DELAY=10, SIZE=50;
    private final int moveX=5, moveY=0;
    private int angleStart, angleEnd;
    private int x, y;
    private boolean[] flags = new boolean[10];
    public h2_20240530(){
        addWindowListener(new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent e){
            System.exit(0); }
        });
        setTitle("animation demo");
        setSize(400, 400);
        setBackground(Color.BLACK);
        setVisible(true);
        y = 200;
        x = 100;
        for(int i=0; i<10; i++) flags[i]=true;
    }
    public static void main(String args[]){
        h2_20240530 pacman = new h2_20240530();
        Timer timer = new Timer(100, new ActionListener() {
            public void actionPerformed(ActionEvent e){
                pacman.x+=pacman.moveX;
                pacman.angleStart=(pacman.angleStart==90?45:90);
                pacman.angleEnd=(pacman.angleEnd==360?270:360);
                pacman.repaint();
            }
        });
        timer.start();
    }
    public void paint(Graphics g){
        g.setColor(Color.YELLOW);
        // g.fillOval(x+15, y+15, 50, 50);
        g.fillArc(x, y, 50, 50, angleStart, angleEnd);
        g.setColor(Color.YELLOW);
        g.fillArc(x, y, 50, 50, angleStart, angleEnd);
        for(int i=0; i<10; i++){
            int ex = 100+30*i;
            if(ex<=x) flags[i]=false;
            if(flags[i]) g.fillOval(ex, 225, 10, 10);
        }
        g.setColor(Color.BLACK);
        g.fillOval(x+20, y+10, 5, 5);
    }
}
