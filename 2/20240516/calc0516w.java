import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.border.Border;;

public class calc0516w {
    public static void main(String args[]){
        Frame loginWindow=Login.getInstaence();
    }
}

class Login {
    private static Frame InstanceFrame;
    private Login(){}
    public static Frame getInstaence(){
        if(InstanceFrame==null){
            InstanceFrame = new Frame("login");
            InstanceFrame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e){ System.exit(0); }
            });
            InstanceFrame.setSize(240, 180);
            Label usr = new Label("User");
            usr.setSize(100, 30);
            usr.setLocation(10, 30);
            usr.setVisible(true);
            TextField usrInput = new TextField();
            usrInput.setLocation(110, 30);
            usrInput.setSize(100, 30);
            Label pwd = new Label("Password");
            pwd.setSize(100, 30);
            pwd.setLocation(10, 60);
            pwd.setVisible(true);
            TextField pwdInput = new TextField();
            pwdInput.setLocation(110, 60);
            pwdInput.setSize(100, 30);
            pwdInput.setEchoChar('*');
            Button submit = new Button("Submit");
            submit.setSize(90, 30);
            submit.setLocation(10, 100);
            submit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // System.out.println(usrInput.getText());
                    // System.out.println(pwdInput.getText());
                    if(usrInput.getText().equals("kiol")&&pwdInput.getText().equals("password")){
                        InstanceFrame.setVisible(false);
                        Frame CalcWindow =CalcAPP.getInstance();
                    }else{
                        try {
                            Point now = InstanceFrame.getLocation();
                            for(int times=0; times<5; times++){
                                InstanceFrame.setLocation((int)now.getX()+10, (int)now.getY());
                                Thread.sleep(30);
                                InstanceFrame.setLocation((int)now.getX(), (int)now.getY());
                                Thread.sleep(30);
                            }
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            });
            Label last = new Label(""); //fixed break
            submit.setVisible(true);
            InstanceFrame.add(submit);
            InstanceFrame.add(usr);
            InstanceFrame.add(usrInput);
            InstanceFrame.add(pwd);
            InstanceFrame.add(pwdInput);
            InstanceFrame.add(last);
            InstanceFrame.setVisible(true);
        }
        return InstanceFrame;
    }
}
class myButton extends JButton {
    public myButton(String title, int w, int h){
        super(title);
        setMargin(new Insets(0, 0, 0, 0));
        setBorder(new RoundBorder());
        setContentAreaFilled(false);
        setFocusPainted(false);
        setSize(w, h);
        setVisible(true);
    }
    protected void paintComponent(Graphics g) {
        if(getModel().isArmed()) {
            g.setColor(Color.GREEN);
        }else{
            g.setColor(getBackground());
        }
        g.fillRoundRect(0, 0, getSize().width-1, getSize().height-1, 10, 10);
        super.paintComponent(g);
    }
}
class CalcAPP {
    private CalcAPP(){}
    private static Frame InstanceFrame;
    public static Frame getInstance(){
        if(InstanceFrame==null){
            InstanceFrame=new Frame("Calc");
            InstanceFrame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e){ System.exit(0); }
            });
            InstanceFrame.setSize(340, 540);
            TextField show = new TextField();
            show.setSize(300, 50);
            show.setLocation(10, 60);
            show.setEditable(false);
            Panel panel = new Panel();
            CheckboxGroup test=new CheckboxGroup();
            Checkbox basic = new Checkbox("basic", test, true);
            Checkbox Engineer = new Checkbox("Engineer", test, false);
            panel.add(basic);
            panel.add(Engineer);

            // buttons
            final String[] syms = {"AC", "+/-", " % ", " / ", "1", "2", "3", " * ", "4", "5", "6", " - ", "7", "8", "9", " + ", "0", "0", " . ", "="};
            myButton[] numbers = new myButton[20];
            for(int i=0; i<5; i++){
                for(int j=0; j<4; j++){
                    if(i==4&&j==1) continue;
                    final int now = i*4+j;
                    numbers[now]=new myButton(syms[now], 80+((i==4&&j==0)?80:0), 80);
                    Font newButtonFont=new Font(numbers[now].getFont().getName(),numbers[now].getFont().getStyle(),20);
                    numbers[now].setFont(newButtonFont);
                    if(now!=19&&now!=0){
                        numbers[now].addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                show.setText(show.getText()+syms[now]);
                            }
                        });
                    }else if(now==19){
                        numbers[now].addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                String[] origin = show.getText().split(" ");
                                ArrayList<Double> variables=new ArrayList<Double>();
                                ArrayList<String> symbols = new ArrayList<String>();
                                for(int i=0; i<origin.length; i++){
                                    if(origin[i].charAt(0)-'0'>=0&&origin[i].charAt(0)-'0'<10){
                                        variables.add(Double.valueOf(origin[i]));
                                    }else symbols.add(origin[i]);
                                }
                                double ans=variables.get(0);
                                int now=0;
                                while(now<symbols.size()){
                                    if(symbols.get(now).equals("+")) ans+=variables.get(now+1);
                                    else if(symbols.get(now).equals("-")) ans-=variables.get(now+1);
                                    else if(symbols.get(now).equals("*")) ans*=variables.get(now+1);
                                    else if(symbols.get(now).equals("/")) ans/=variables.get(now+1);
                                    now++;
                                }
                                show.setText(String.valueOf(ans));
                            }
                        });
                    }else{
                        numbers[now].addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                show.setText("");
                            }
                        });
                    }
                    numbers[now].setLocation(j*80, i*80+120);
                    InstanceFrame.add(numbers[now]);
                }
            }
            InstanceFrame.add(show);
            InstanceFrame.add(panel);
            InstanceFrame.setVisible(true);
        }
        return InstanceFrame;
    }
}

class RoundBorder implements Border {
    public Insets getBorderInsets(Component c){
        return new Insets(0, 0, 0, 0);
    }
    public boolean isBorderOpaque(){ return false; }
    public void paintBorder(Component c, Graphics g, int x, int y, int w, int h){
        g.setColor(Color.BLACK);
        g.drawRoundRect(0, 0, c.getWidth(), c.getHeight()-1, 10, 10);
    }
}

