package cn.littlechen.linux.autorun;


import cn.littlechen.linux.utils.ShellUtils;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Created by lenovo on 2017/8/24.
 */

/**
 * 一个界面，不支持键盘操作。
 */
public class AutoRun extends JFrame implements ActionListener {
    /**
     * 界面上的键的显示名字
     */
    private final String[] KEYS = {"安装"};
    /**
     * 界面上键的按钮
     */
    private JButton keys[] = new JButton[KEYS.length];
    /**
     * 命令文本框
     */
    private JTextField resultText = new JTextField("wine QQ.exe /S");

    /**
     * 构造函数
     */
    public AutoRun() {
        super();
        // 初始化界面
        init();
        // 设置界面的背景颜色
        this.setBackground(Color.LIGHT_GRAY);
        this.setTitle("AutoRunExe");
        // 在屏幕(500, 300)坐标处显示界面
        this.setLocation(500, 300);
        // 不许修改界面的大小
        this.setResizable(false);
        // 使界面中各组件大小合适
        this.pack();
    }

    /**
     * 初始化界面
     */
    private void init() {
        // 初始化界面上键的按钮，将键放在一个画板内
        JPanel calckeysPanel = new JPanel();
        // 用网格布局器，1行，1列的网格，网格之间的水平方向间隔为3个象素，垂直方向间隔为3个象素
        calckeysPanel.setLayout(new GridLayout(3, 1, 3, 3));
        for (int i = 0; i < KEYS.length; i++) {
            keys[i] = new JButton(KEYS[i]);
            calckeysPanel.add(keys[i]);
            keys[i].setForeground(Color.blue);
        }
        JPanel panel1 = new JPanel();
        panel1.setLayout(new BorderLayout(3, 3));
        panel1.add("Center", calckeysPanel);
        // 建立一个画板放文本框
        JPanel top = new JPanel();
        top.setLayout(new BorderLayout());
        top.add("Center", resultText);
        // 整体布局
        getContentPane().setLayout(new BorderLayout(3, 5));
        getContentPane().add("North", top);
        getContentPane().add("Center", panel1);
        // 为各按钮添加事件侦听器
        // 都使用同一个事件侦听器，即本对象。本类的声明中有implements ActionListener
        for (int i = 0; i < KEYS.length; i++) {
            keys[i].addActionListener(this);
        }
    }

    /**
     * 处理事件
     */
    public void actionPerformed(ActionEvent e) {
        // 获取事件源的标签
        String label = e.getActionCommand();
        if (label.equals(KEYS[0])) {
            // 用户按了键
            exeShell();
        }
    }

    /**
     * 处理键被按下的事件
     */
    private void exeShell() {
        ShellUtils shellUtils = new ShellUtils();
        String text = resultText.getText();//"wine QQ.exe /S"
        shellUtils.execSh(text);
    }


    public static void main(String args[]) {
        AutoRun autoRun = new AutoRun();
        autoRun.setVisible(true);
        autoRun.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}