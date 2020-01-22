package robot2_1;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CalculatorFrame extends JFrame{
    
    //ִ��Ĭ�ϵ����л�
    private static final long serialVersionUID = 1L;
    
    //��������ʵ�������classִ�еĵط�
    public CalculatorFrame() {
        
        //�����ʼ�������ڼ̳���JFrame�����Ե��ø��๹������ӱ���
        super("������Sam��С������");
        
        //�½�������panel1
        JPanel panel1 = new JPanel();
        
        //��panel1����ӿ��Ϊ30�ĵ����ı���
        panel1.add(new JTextField(30));
        
        //��������panel1��ӵ�JFrame�Ķ���(NORTH)
        this.add(panel1, BorderLayout.NORTH);
        
        //�½�������panel2
        JPanel panel2 = new JPanel();
        
        //����panel2ʹ��GridLayout���ֹ�����
        panel2.setLayout(new GridLayout(3, 5, 4, 4));
        
        //�½����鴢���ʾJButton���ݵ�String
        String[] name = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "+", "-", "��", "��", "."};
        
        //��Panel���������15����ť
        for (int i = 0; i< name.length; i++) {
            
            //�������飬������Ԫ��������
            panel2.add(new JButton(name[i]));
            
        }
        
        //Ĭ�Ͻ�JPanel������ӵ�JFrame���ڵ��м�
        this.add(panel2);
        
        //���ô���Ϊ��Ѵ�С
        this.pack();
        
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
        // TODO Auto-generated method stub
        new CalculatorFrame();
    }
*/
}
