import java.awt.*;
import java.awt.event.*;

public class ItemEventDemo implements ItemListener {
    Frame frame;
    Checkbox checkbox1, checkbox2, checkbox3;
    public static void main(String args[]){
        new ItemEventDemo();
    }
    public  ItemEventDemo(){
        frame = new Frame("AWTDemo");
        frame.addWindowListener(new AdapterDemo());
        frame.setLayout(new FlowLayout());

        checkbox1 = new Checkbox("c1");
        checkbox1.addItemListener(this);
        checkbox2 = new Checkbox("c2");
        checkbox2.addItemListener(this);
        checkbox3 = new Checkbox("c3");
        checkbox3.addItemListener(this);

        frame.add(checkbox1);
        frame.add(checkbox2);
        frame.add(checkbox3);

        frame.pack();
        frame.setVisible(true);
    }
    public void itemStateChanged(ItemEvent e){
        String l = (String)e.getItem();
        if(e.getStateChange()==ItemEvent.SELECTED){
            System.out.println(1+"selected");
        }
        if(e.getStateChange()==ItemEvent.DESELECTED){
            System.out.println(1+"deselected");
        }
    }
}
class AdapterDemo extends WindowAdapter {
    public void windowClosing(WindowEvent e){
        System.exit(0);
    }
}