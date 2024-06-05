import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;;

public class hw_20240530 extends Frame {
    private boolean[][] T = {{false, true, false}, {true, true, true}, {false, false, false}};
    private static boolean[] turn = {false, false};
    public hw_20240530(){
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e){
                if(e.getKeyCode()==68) turn[1]=true;
                else if(e.getKeyCode()==65) turn[0]=true;
            }
        });
        setTitle("tetris");
        setSize(350, 350);
        setBackground(Color.BLACK);
        setVisible(true);
    }
    public static void main(String args[]){
        hw_20240530 teteris = new hw_20240530();
        Timer timer = new Timer(100, new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if(turn[1]&&!turn[0]){
                    teteris.rollR();
                    System.out.println("R");
                    turn[1] = false;
                }else if(!turn[1]&&turn[0]){
                    teteris.rollL();
                    System.out.println("L");
                    turn[0] = false;
                }
                teteris.repaint();
            }
        });
        timer.start();
    }
    public void paint(Graphics g){
        g.setColor(Color.YELLOW);
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                if(T[i][j])g.fillRect(i*50+100, j*50+100, 50, 50);
            }
        }
    }
    public void rollR(){
        boolean[][] tmp = new boolean[3][3];
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                tmp[i][j] = T[j][2-i];
            }
        }
        T = tmp;
    }
    public void rollL(){
        boolean[][] tmp = new boolean[3][3];
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                tmp[i][j] = T[2-j][i];
            }
        }
        T = tmp;
    }
}
