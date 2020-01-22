package robot2_1;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CalculatorFrame extends JFrame{
    
    //执行默认的序列化
    private static final long serialVersionUID = 1L;
    
    //构造器其实正是这个class执行的地方
    public CalculatorFrame() {
        
        //界面初始化，由于继承了JFrame，所以调用父类构造器添加标题
        super("机器人Sam的小计算器");
        
        //新建面板组件panel1
        JPanel panel1 = new JPanel();
        
        //在panel1中添加宽度为30的单行文本域
        panel1.add(new JTextField(30));
        
        //把面板组件panel1添加到JFrame的顶部(NORTH)
        this.add(panel1, BorderLayout.NORTH);
        
        //新建面板组件panel2
        JPanel panel2 = new JPanel();
        
        //设置panel2使用GridLayout布局管理器
        panel2.setLayout(new GridLayout(3, 5, 4, 4));
        
        //新建数组储存表示JButton内容的String
        String[] name = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "+", "-", "×", "÷", "."};
        
        //向Panel容器中添加15个按钮
        for (int i = 0; i< name.length; i++) {
            
            //遍历数组，将其中元素逐个添加
            panel2.add(new JButton(name[i]));
            
        }
        
        //默认将JPanel对象添加到JFrame窗口的中间
        this.add(panel2);
        
        //设置窗口为最佳大小
        this.pack();
        
        //默认窗口隐藏，这里需要设置JFrame显示出来
        this.setVisible(true);
        
        try {
            //通过操作线程休眠10000ms(即10s)，暂缓程序执行，从而使得释放内存前窗口可以显示10s
            Thread.sleep(10000);
            
        } catch (InterruptedException e) {
            
            //打印异常栈
            e.printStackTrace();
            
        }
        
        //释放内存，窗口消失但是不执行退出程序的操作
        this.dispose();
    }
  
    //用于做内部单元测试
/*    public static void main(String[] args) {
        // TODO Auto-generated method stub
        new CalculatorFrame();
    }
*/
}
