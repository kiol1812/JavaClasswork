// 2024/05/30 41143264 楊育哲
// 繪圖練習

import java.awt.*;
import java.awt.event.*;;

public class h1_20240530 extends Frame implements AdjustmentListener {
    static h1_20240530 frm = new h1_20240530();
    // static Button draw = new Button("Draw");
    static Scrollbar scr1 = new Scrollbar(Scrollbar.VERTICAL);
    static Scrollbar scr2 = new Scrollbar(Scrollbar.HORIZONTAL);
    static Scrollbar scr3 = new Scrollbar(Scrollbar.VERTICAL);
    static Scrollbar scr4 = new Scrollbar(Scrollbar.HORIZONTAL);
    boolean clicked = false;
    public static void main(String args[]){
        frm.addWindowListener(new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent e){
            System.exit(0); }
        });
        BorderLayout br = new BorderLayout(5, 5);
        frm.setTitle("Display color");
        frm.setSize(200, 150);
        frm.add(scr1, br.WEST);
        frm.add(scr2, br.SOUTH);
        frm.add(scr3, br.EAST);
        frm.add(scr4, br.NORTH);
        scr1.setValues(255, 45, 0, 255);
        scr2.setValues(255, 45, 0, 255);
        scr3.setValues(140, 45, 0, 255);
        scr4.setValues(140, 45, 0, 255);
        scr1.addAdjustmentListener(frm);
        scr2.addAdjustmentListener(frm);
        scr3.addAdjustmentListener(frm);
        scr4.addAdjustmentListener(frm);
        frm.setVisible(true);
    }

    public void adjustmentValueChanged(AdjustmentEvent e){
        Graphics g = getGraphics();
        paint(g);
    }
    public void paint(Graphics g){
        int red = scr1.getValue();
        int green = scr2.getValue();
        int blue = scr3.getValue();
        int alpha = scr4.getValue();

        // g.setColor(Color.WHITE);
        // g.fillRect(0, 0, getWidth(), getHeight());
        
        String str = "Color("+red+", "+green+", "+blue+", "+alpha+")";
        g.setColor(new Color(red, green, blue, alpha));
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.BLACK);
        g.drawString(str, 45, 80);
        // repaint();
    }
}
