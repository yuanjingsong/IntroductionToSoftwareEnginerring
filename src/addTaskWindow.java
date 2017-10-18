import javax.swing.*;
import java.awt.*;
public class addTaskWindow extends JFrame {
    JPanel jp1, jp2, jp3, jp4;
    JLabel NameLabel, Despanel, TagLabel;
    JTextField NameField, DesField, TagField;
    JButton Confirm, Cancel;
    addTaskWindow() {
        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();
        jp4 = new JPanel();
        NameLabel = new JLabel("名称:");
        Despanel = new JLabel("描述:");
        TagLabel = new JLabel("标签");
        NameField = new JTextField(10);
        DesField = new JTextField(10);
        TagField = new JTextField(10);
        Confirm = new JButton("确认");
        Cancel = new JButton("取消");
        setLayout(new GridLayout(4, 1));
        jp1.add(NameLabel);
        jp1.add(NameField);
        jp2.add(Despanel);
        jp2.add(DesField);
        jp3.add(TagLabel);
        jp3.add(TagField);
        jp4.add(Confirm);
        jp4.add(Cancel);
        add(jp1);
        add(jp2);
        add(jp3);
        add(jp4);
        setTitle("添加任务");
        setSize(300,200);
        setLocation(600,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    public void init (addTaskWindow window){

    }
}
