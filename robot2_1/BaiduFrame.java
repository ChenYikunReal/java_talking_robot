package robot2_1;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BaiduFrame extends JFrame{
    
    //执行默认的序列化
    private static final long serialVersionUID = 1L;
    
    //新建宽度为10的单行文本区域
    private JTextField textField = new JTextField(10);
    
    //新建按钮“百度一下”
    private JButton buttonBaidu = new JButton("百度一下");
    
    //新建按钮“返回”
    private JButton buttonExit = new JButton("返回");
    
    //新建标签组件“About  Baidu”
    private JLabel labelAbout = new JLabel("About  Baidu");
    
    //新建标签组件“沈阳   22~10”
    private JLabel labelWeather = new JLabel("沈阳   22~10");

    //构造器其实正是这个class执行的地方
    public BaiduFrame() {
        
        //界面初始化，由于继承了JFrame，所以调用父类构造器添加标题
        super("www.baidu.com");
        
        //设置JFrame容器的大小
        this.setSize(400, 130);
        
        //利用BorderLayout布局管理器把labelWeather标签添加到JFrame的顶部(NORTH)
        this.add(labelWeather, BorderLayout.NORTH);
        
        //新建面板组件panelCore
        JPanel panelCore = new JPanel();
        
        //向面板组件的左边(WEST)添加定义的单行文本域textField
        panelCore.add(textField, BorderLayout.WEST);
        
        //向面板组件的右边(EAST)添加定义的按钮buttonBaidu
        panelCore.add(buttonBaidu, BorderLayout.EAST);
        
        //把面板组件panelCore添加到JFrame中，默认居中
        this.add(panelCore);
        
        //新建面板组件panelElse
        JPanel panelElse = new JPanel();
        
        //向面板组件的左边(WAST)添加定义的标签labelAbout
        panelElse.add(labelAbout, BorderLayout.WEST);
        
        //向面板组件的右边(EAST)添加定义的按钮buttonExit
        panelElse.add(buttonExit, BorderLayout.EAST);
        
        //把面板组件panelElse添加到JFrame的底部(SOUTH)
        this.add(panelElse, BorderLayout.SOUTH);
        
        //设置窗口为最佳大小     this.pack();    由于不美观所以不自动设置了
        
        //用户单击窗口的关闭按钮时程序执行的操作，会终止整个Robot的执行流程，关闭程序，相当于加上相应事件监听器
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //设置此窗体不可以可由用户调整大小，只能由程序员决定
        this.setResizable(false);
        
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
        new BaiduFrame();
    }
*/
}
