package robot2_1;



import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
//import java.beans.EventHandler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
//import javax.swing.JColorChooser;
//import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class TuringRobot {
	public TuringRobot() {
	}

	public static void main(String[] args)throws IOException{
	    advance();
	}
	public static void advance() throws IOException {
//        String information = new String("Hello");
//        String mechine = new String();
	    JFrame frame = new JFrame("与机器人聊天");
	    JPanel panel = new JPanel(new GridLayout(3,1));
	    JPanel questionPanel = new JPanel(new FlowLayout());
	    JPanel buttonPanel = new JPanel();
	    JPanel answerPanel = new JPanel(new FlowLayout());
	    JLabel question = new JLabel("问题");
	    JTextField enterQuestion = new JTextField(20);
	    JLabel answer = new JLabel("机器人回答");
	    JTextArea enterAnswer = new JTextArea(3,25);
	    JButton submit = new JButton("提交");
	    ImageIcon imgIcon = new ImageIcon("src/robot2_1/images/turing.png");
	    Icon img = imgIcon;
	    JLabel imgLabel = new JLabel();
	        
	    frame.setSize(600, 400);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setVisible(true);
	        
	    enterAnswer.setFont(new Font("宋体",Font.BOLD,15));
	    enterQuestion.setFont(new Font("宋体",Font.BOLD,15));
	    imgIcon.setImage(imgIcon.getImage().getScaledInstance(35,35,Image.SCALE_DEFAULT));
	    enterAnswer.setLineWrap(true);
	    imgLabel.setIcon(img);
	        
	    questionPanel.add(question);
	    questionPanel.add(enterQuestion);
	    answerPanel.add(imgLabel);
	    answerPanel.add(answer);
	    answerPanel.add(enterAnswer);
	    buttonPanel.add(submit);
	    panel.add(questionPanel);
	    panel.add(answerPanel);
	    panel.add(buttonPanel);
	    frame.add(panel);
	        
	    submit.addActionListener(new ActionListener(){
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            String answer = new String();
	            String q = enterQuestion.getText();
	            try {
	                answer = machine(q);
	            } catch (IOException e1) {
	                e1.printStackTrace();
	            }
	            enterAnswer.setText(answer);
	        }
	    });
	        
	    enterQuestion.addKeyListener(new KeyListener() {

	    	@Override
	    	public void keyTyped(KeyEvent e) {
	    		// TODO Auto-generated method stub
	    	}
	    	@Override
	    	public void keyPressed(KeyEvent e) {
	    		if(e.getKeyCode()==10 || e.getKeyCode()==38) {
	            	String answer = new String();
	            	String q = enterQuestion.getText();
	            	try {
	                	answer = machine(q);
	            	} catch (IOException e1) {
	                	// TODO Auto-generated catch block
	                	e1.printStackTrace();
	            	}
	            	enterAnswer.setText(answer);
	        	}
	    	}

	    	@Override
	    	public void keyReleased(KeyEvent e) {
	    		// TODO Auto-generated method stub
	                
	    	}
	    
	    });
	}
	        
	private static String machine(String quesiton) throws IOException {
	    //接入机器人，输入问题
	    String APIKEY = "86b7515cf4a44a9c913ee9ba09214fbb";
	    String INFO = URLEncoder.encode(quesiton, "utf-8");//这里可以输入问题
	    String getURL = "http://www.tuling123.com/openapi/api?key=" + APIKEY + "&info=" + INFO;
	    URL getUrl = new URL(getURL);
	    HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
	    connection.connect();

	    // 取得输入流，并使用Reader读取
	    BufferedReader reader = new BufferedReader(new InputStreamReader( connection.getInputStream(), "utf-8"));
	    StringBuffer sb = new StringBuffer();
	    String line = "";
	    while ((line = reader.readLine()) != null) {
	        sb.append(line);
	    }
	    reader.close();
	    // 断开连接
	    connection.disconnect();
	    String[] ss = new String[10];
	    String s = sb.toString();
	    String answer;
	    ss = s.split(":");
	    answer = ss[ss.length-1];
	    answer = answer.substring(1,answer.length()-2);
	    return answer;
	}

}
