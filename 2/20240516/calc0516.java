import java.awt.*;
import java.awt.event.*;;

public class calc0516 {
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
class myButton extends Button {
    public myButton(String title, int w, int h){
        super(title);
        setSize(w, h);
        setVisible(true);
    }
    public void set_location(int x, int y){
        setLocation(x, y);
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
            Panel panel = new Panel();
            CheckboxGroup test=new CheckboxGroup();
            Checkbox basic = new Checkbox("basic", test, true);
            Checkbox Engineer = new Checkbox("Engineer", test, false);
            panel.add(basic);
            panel.add(Engineer);
            myButton[] numbers = new myButton[19];
            for(int i=0; i<3; i++){
                for(int j=0; j<3; j++){
                    numbers[i*3+j]=new myButton(String.valueOf(i*3+j+1), 80, 80);
                    numbers[i*3+j].set_location(j*80, i*80+200);
                    InstanceFrame.add(numbers[i*3+j]);
                }
            }
            numbers[9] = new myButton(String.valueOf(0), 160, 80);
            numbers[9].set_location(0, 440);
            myButton[] symbles = new myButton[9];
            symbles[0] = new myButton(".", 80, 80); symbles[0].set_location(160, 440);
            symbles[1] = new myButton("=", 80, 80); symbles[1].set_location(240, 440);
            symbles[2] = new myButton("+", 80, 80); symbles[2].set_location(240, 360);
            symbles[3] = new myButton("-", 80, 80); symbles[3].set_location(240, 280);
            symbles[4] = new myButton("*", 80, 80); symbles[4].set_location(240, 200);
            symbles[5] = new myButton("/", 80, 80); symbles[5].set_location(240, 120);
            symbles[6] = new myButton("%", 80, 80); symbles[6].set_location(160, 120);
            symbles[7] = new myButton("+/-", 80, 80); symbles[7].set_location(80, 120);
            symbles[8] = new myButton("AC", 80, 80); symbles[8].set_location(0, 120);
            for(int i=0; i<9; i++) InstanceFrame.add(symbles[i]);
            InstanceFrame.add(numbers[9]);
            TextField show = new TextField();
            show.setSize(300, 50);
            show.setLocation(10, 60);
            InstanceFrame.add(show);
            InstanceFrame.add(panel);
            InstanceFrame.setVisible(true);
        }
        return InstanceFrame;
    }
}
