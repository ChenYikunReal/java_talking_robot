package robot2_1;

import java.io.IOException;

/**
 * MiniProject Menu:
 * 
 *   项目介绍： 一共有6个类 --> Robot类、User类、测试类(含main方法)、TuringRobot类、BaiduFrame类、CalculatorFrame类
 *          实际上主要是2个:Robot类、User类
 *   
 *  基本功能介绍 ：  （输入0~7的int值有效）
 *      0.退出
 *      1.注册
 *      2.登陆
 *      3.聊天
 *      4.抽奖
 *      5.查询
 *      6.修改密码
 *      7.删除用户
 *      
 *  类介绍：
 *      1.Robot类：
 *          将Robot主体功能集合封装，提供一系列功能通过public权限的operate()方法调用内部的private修饰的方法
 *      2.User类：
 *          这里因为既需要处理用户账户信息，也需要处理用户的个人信息
 *          又考虑到是一个账户对应一个主人，所以二者功能合一
 *      3.RobotTest类:
 *          仅仅是把main方法分离出来了，又剥去了operate(),什么也没做，就是运行用的
 *      4.BaiduFrame类:
 *          一个GUI的简单程序，依赖于Robot类的调用
 *          利用线程的sleep(),只有10秒钟(1000ms)的展示时常上限
 *          用最简单的awt和swing操作实现了模拟的百度界面
 *      5.CalculatorFrame类:
 *          一个GUI的简单程序，依赖于Robot类的调用
 *          利用线程的sleep(),只有10秒钟(1000ms)的展示时常上限
 *          用最简单的awt和swing操作实现了模拟的计算器界面
 *      6.TuringRobot类:
 *          做了一个图灵机器人，语料库来自Web，本地不储存
 *          以GUI界面呈现
 * 
 *  欠缺：（1）主体部分仍未加入GUI的界面
 *            （2）没有用文件或数据库保存信息，虽说是循环，但真正退出以后是没有残留的
 *            （3）没有使用多线程，因为只是单人的人机交互，但是注释了state变量便于未来改进
 *            （4）计算器本来是有内核的，但是我不想加进去。。。(也不见得说是缺陷)
 *            （5）百度一下里面的返回按钮没调好，只能撤掉事件监听器
 * 
 *  比较于version2.0做的优化提升：
 *          （1）主要是简化方法、拆解方法，尽量都缩小到15行以下
 *          （2）利用线程操作使得GUI的展示时间上限只有10秒，10秒自动回收内存返回程序
 *          （3）把GUI的展示分离出去，单独成类又可以不必须exit，所以添加了2个类
 *          （4）对Robot应用单例模式，确保机器人Sam只有一个
 *          （5）松耦合，拆嵌套，减少重复，提高可维护性
 *          （6）加入了网络编程和GUI结合的图灵机器人
 *          （7）对身份证号的正则匹配进行了加强，允许末尾的x，而且用法也更加高级了
 *          （8）进一步加强注释和改进变量名、方法名
 * 
 *  新增功能：
 *          （1）Robot的单例模式(做的不太成熟)
 *          （2）把之前展示的图灵机器人优化以后加入其中
 *          
 *  遇到的困难：
 *          （1）第一次见到的  Exception in thread "main" java.lang.StackOverflowError
 *          （2）令人绝望的事件监听器。。。。。。
 *          
 * @author 	BlankSpace
 * @version 2.1
 * @time	2019年5月30日——31日
 * 
 */

public class RobotTest{
	public static void main(String[] args)  throws IOException{
		Robot.getInstance().run();
	}
}
