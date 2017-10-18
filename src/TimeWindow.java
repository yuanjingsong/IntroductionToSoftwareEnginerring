import javax.swing.*;
import java.awt.*;

public class TimeWindow extends JFrame{
    JPanel jp1, jp2,jp3;
    JLabel Time, showTime, inputLabel;
    JTextField inputTime;
    JButton confirm, cancel, ok;
    TimeWindow () {
        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();
        Time = new JLabel("时间");
        showTime = new JLabel("123");
        inputLabel = new JLabel("输入专心时长");
        inputTime = new JTextField(10);
        confirm = new JButton("确认");
        cancel = new JButton("取消");
        ok = new JButton("完成");
        jp1.add(inputLabel);
        jp1.add(inputTime);
        jp2.add(Time);
        jp2.add(showTime);
        jp3.add(confirm);
        jp3.add(cancel);
        add(jp1);
        add(jp3);
        setLayout(new GridLayout(2,1));
        setTitle("倒计时");
        setSize(300,150);
        setLocation(600,300);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
    public void init(TimeWindow timeWindow) {
        setTimer setTimer = new setTimer(timeWindow);
        timeWindow.confirm.addActionListener(setTimer);
        Cancel cancel = new Cancel(timeWindow);
        timeWindow.cancel.addActionListener(cancel);

    }

}
