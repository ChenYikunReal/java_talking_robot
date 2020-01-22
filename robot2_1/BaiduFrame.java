package robot2_1;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BaiduFrame extends JFrame{
    
    //ִ��Ĭ�ϵ����л�
    private static final long serialVersionUID = 1L;
    
    //�½����Ϊ10�ĵ����ı�����
    private JTextField textField = new JTextField(10);
    
    //�½���ť���ٶ�һ�¡�
    private JButton buttonBaidu = new JButton("�ٶ�һ��");
    
    //�½���ť�����ء�
    private JButton buttonExit = new JButton("����");
    
    //�½���ǩ�����About  Baidu��
    private JLabel labelAbout = new JLabel("About  Baidu");
    
    //�½���ǩ���������   22~10��
    private JLabel labelWeather = new JLabel("����   22~10");

    //��������ʵ�������classִ�еĵط�
    public BaiduFrame() {
        
        //�����ʼ�������ڼ̳���JFrame�����Ե��ø��๹������ӱ���
        super("www.baidu.com");
        
        //����JFrame�����Ĵ�С
        this.setSize(400, 130);
        
        //����BorderLayout���ֹ�������labelWeather��ǩ��ӵ�JFrame�Ķ���(NORTH)
        this.add(labelWeather, BorderLayout.NORTH);
        
        //�½�������panelCore
        JPanel panelCore = new JPanel();
        
        //�������������(WEST)��Ӷ���ĵ����ı���textField
        panelCore.add(textField, BorderLayout.WEST);
        
        //�����������ұ�(EAST)��Ӷ���İ�ťbuttonBaidu
        panelCore.add(buttonBaidu, BorderLayout.EAST);
        
        //��������panelCore��ӵ�JFrame�У�Ĭ�Ͼ���
        this.add(panelCore);
        
        //�½�������panelElse
        JPanel panelElse = new JPanel();
        
        //�������������(WAST)��Ӷ���ı�ǩlabelAbout
        panelElse.add(labelAbout, BorderLayout.WEST);
        
        //�����������ұ�(EAST)��Ӷ���İ�ťbuttonExit
        panelElse.add(buttonExit, BorderLayout.EAST);
        
        //��������panelElse��ӵ�JFrame�ĵײ�(SOUTH)
        this.add(panelElse, BorderLayout.SOUTH);
        
        //���ô���Ϊ��Ѵ�С     this.pack();    ���ڲ��������Բ��Զ�������
        
        //�û��������ڵĹرհ�ťʱ����ִ�еĲ���������ֹ����Robot��ִ�����̣��رճ����൱�ڼ�����Ӧ�¼�������
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //���ô˴��岻���Կ����û�������С��ֻ���ɳ���Ա����
        this.setResizable(false);
        
        //Ĭ�ϴ������أ�������Ҫ����JFrame��ʾ����
        this.setVisible(true);
        
        try {
            
            //ͨ�������߳�����10000ms(��10s)���ݻ�����ִ�У��Ӷ�ʹ���ͷ��ڴ�ǰ���ڿ�����ʾ10s
            Thread.sleep(10000);
            
        } catch (InterruptedException e) {
            
            //��ӡ�쳣ջ
            e.printStackTrace();
            
        }
        
        //�ͷ��ڴ棬������ʧ���ǲ�ִ���˳�����Ĳ���
        this.dispose();
        
    }

    //�������ڲ���Ԫ����
/*    public static void main(String[] args) {
        new BaiduFrame();
    }
*/
}
