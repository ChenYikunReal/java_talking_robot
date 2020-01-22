package robot2_1;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;
import java.util.regex.Pattern;
import java.io.IOException;

/**
 * Robot类一些新特点介绍
 * @author BlankSpace
 * @version 2.1
 */

public class Robot {
    //为了设计单例模式，需要新建一个Robot的实例robot，初值为null
    private static Robot robot = null;
	//声明一个用户对象数组
	private User[] users = null;
	//记录元素个数
	private int userCount=0;
	//调用Scanner类的构造器用于处理整个class的基本输入
	private Scanner scanner = new Scanner(System.in);
	//生成一个Random类实例用于整个class的随机数生成
    private Random random = new Random();
    //生成的用于储存生成随机数的int
    private int luckyNumber;
    //设置用户账户处于未登录状态
    private User userLanding = null;
    //设置用户登录已尝试次数为0
    private int tryToLoadTimes = 0; 
	
	/**
	 *  创建一个构造方法(构造器)，初始化系统用来存储用户信息的数组大小
	 *  默认为10，输入小于0的数也会为10
	 */
	private Robot() {
		users = new User[10];
	}
	
	/**
	 * 单参数构造器，新建指定程度的账户
	 * @param size 账户长度
	 */
	private Robot(int size) {
		if (size>0) {
			//创建 数组的大小
			users = new User[size];
		} else {
		    users = new User[10];
		}
	}
    
	/**
	 * 获取单例的方法
	 * @return robot单例
	 */
    public synchronized static Robot getInstance() {
        if (robot == null) {
            robot = new Robot();
        }
        return robot;
    }
	
    /**
     * 程序的可执行部分(由Robot获取单例后调用)
     * @throws IOException
     */
	public void run() throws IOException {
	    //打印一段自我介绍的沙雕文字
	    this.introduceMyself();
	    //生成系统的幸运数字 
	    luckyNumber = random.nextInt(8999)+1000;   
	    //实例化一个管理的对象，定义数组的大小	    new Robot(10);
	    //让用户进行选择并处理
	    this.analyseMainChoice();
	}

/*******************************下面是涉及处理用户选择的方法****************************/	
	
	/**
	 * 调用getChoice()，处理读取的choice，作出选择进行执行
	 * @throws IOException
	 */
    private void analyseMainChoice() throws IOException {
        //对传统印象中的for循环语句加以改造，使之更灵活
        for(int choice = this.getChoice(); choice != 0; choice = this.getChoice()) {
            //不需要default语句，因为在获取输入的时候就稳妥的处理了数据
            switch (choice) {
                case 1:
                    //登录处理
                    this.readRegister();
                    break;
                case 2:
                    //登录处理
                    this.readLanding();
                    break;
                case 3:
                    //聊天处理
                    this.readChat();
                    break;
                case 4:
                    //抽奖处理
                    this.readExtract();
                    break;
                case 5:
                    //查询处理
                    this.readPrintMemberInformation();
                    break;
                case 6:
                    //改密处理
                    this.readChangePassword();
                    break;
                case 7:
                    //删除处理
                    this.readDeleteMemberID();
                    break;
            }
        }
    }

    
    /**
         * 读取、处理选择值的方法
         * 与用户交互，读取选择的数据加以处理
     * @return 选择
     * @throws IOException
     */
    private int getChoice() throws IOException {
        //不满足条件，循环会一直持续下去
        while(true) {
            try {
                System.out.println();
                //打印主菜单
                printMainMenu();
                //提示用户输入
                System.out.print("Choice >:");
                int choice = Integer.parseInt(scanner.next());
                System.out.println();
                //提前处理数据，只有输入0到7的整数才是合法的
                if (0 <= choice && choice <= 7) {
                    return choice;
                }
                //提示用户输入错误
                System.out.println("Invalid choice:  " + choice);
            } catch (NumberFormatException numberFormatException) {
                //打印异常
                System.out.println(numberFormatException);
            }
        }
    }

/*******************************下面是涉及注册账号的方法****************************/    
    
    /**
     * 
     */
    private void readRegister() {
        //定义一个字符串，进入循环(满足条件时可以break的死循环)，一直判断是不是要继续注册
        String operationString1 = "Y";
        //运用equalsIgnoreCase()方法，做忽略大小写的匹配，更加友好
        while ("Y".equalsIgnoreCase(operationString1)) {
            System.out.println("机器人Sam的小小世界 ->注册");
            //用random对象生成的伪随机数确定会员号
            int memberID = random.nextInt(8999)+1000;
            //实例化一个用户对象
            User user = new User(enterUserName(), enterPassword(), memberID);
            //将新注册的用户添加到数组中
            register(user);
            System.out.println("还需要继续注册吗？(Y/N)");
            operationString1 = scanner.next();
        }
    }

	/**
	 *  注册方法
	 *  添加方法,如果添加的数超出数组的范围，就扩建数组。（Java数组本身长度不可变，这样做就相当于实现了动态数组）
	 *  向user的数组users中添加数据（判断账号是否已经存在）
	 *  记录元素个数，
	 *  判断添加的 账号是否已经存在，只有账号不存在才会添加成功
	 *  会员号重复概率极低，暂放弃考虑
	 *  注册成功后打印 一下注册后的信息
	 *  @param user
	 */
	private void register(User user) {
		//判断数组有没有被填满，防止越界无法储存
		if(userCount>=users.length)
			//扩建原来的一半
			users = Arrays.copyOf(users, users.length*3/2+1);
		if (checkDuplicate(user)) {
		    return;
		}
		//添加数组
		users[userCount] = user;
		//更新count
		userCount++; 						
		// 打印注册后的信息
		System.out.println("注册成功！！");
		System.out.println("用户名\t密码\t会员号");
		//重写了toString()方法，打印本账户信息
		System.out.println(user);
	}
	
	/**
	 * 
	 * @param user
	 * @return
	 */
	private boolean checkDuplicate(User user) {
        //遍历，循环查找，是否存在重复账号
        for (int i = 0; i < userCount; i++) {
            //判断用户名是否存在
            if (users[i].getUserName().equals(user.getUserName())) {
                System.out.println("你输入的账号重复，请重新输入！！");
                return true;
            }
        }
        return false;
	}
	
/**************************下面是涉及处理账号的公共方法*************************/
	
	private String enterUserName() {
        System.out.print("请输入账号:");
        String userName = scanner.next();
        return userName;
	}
	
	private String enterPassword() {
        System.out.print("请输入密码:");
        String password = scanner.next();
        return password;
	}
	
/*******************************下面是涉及登录的方法****************************/ 
	
	/**
         *  登陆成功则返回一个用户对象userLanding
         *  登陆失败则返回 null
         *  如果登录失败三次，则结束程序
	 */
    private void readLanding() {
        System.out.println("机器人Sam的小小世界 ->登陆");
        tryToLoadTimes = 0;
        userLanding = landing(new User(enterUserName(), enterPassword(), 0));   //登陆成功，返回对象，不成功返回空
        tryToLoadTimes++;
        if (tryToLoadTimes >= 3) {
            //这就是flag的作用——避免了break的错误执行
            return;
        }
    }


	/**
	 * 登陆方法
	 * 传入一个用户对象
	 * 遍历用户数组 (users)，查找账号
	 * 如果账号密码对应，返回这个用户对象
	 * 没找到，返回null
	 * @param user
	 * @return 要登录的账户
	 */
	private User landing(User user) {
		//int state=0;        //记录登陆状态
		//遍历用户数组 (users)，查找账号
		for (int i = 0; i < userCount; i++) {
			//找到对应的账号
			if (users[i].getUserName().equals(user.getUserName())) {
				//匹配密码是否正确
				if(users[i].getPassword().equals(user.getPassword())) {
					//state=1;
					System.out.println("登陆成功，欢迎用户" + users[i].getUserName());
					//返回这个 用户对象
					return users[i];
				}
			}
		}
		//没匹配正确就向用户报错，提示重新输入并返回null
		System.out.println("账号密码不正确，请重新输入");
		return null;
	}
	
/*******************************下面是涉及聊天"入口"的方法****************************/	
	
	/**
	 * 
	 * @throws IOException
	 */
	private void readChat() throws IOException{
        /**
         * 聊天功能，判断用户是不是为空
         * 不为空才可以聊天（也就是要求登陆后再聊天）
         */
        System.out.println("机器人Sam的小小世界 ->聊天");
        if (userLanding != null)
            //调用聊天的方法
            chat(userLanding);
        else 
            System.out.println("请登陆后再聊天");
	}
	
	/**
	 *  聊天方法
         *  与用户进行交互，进行有趣的“对话”
         *  聊天内容幼稚一点，方便低龄用户使用
	 * @param user
	 * @throws IOException
	 */
	private void chat(User user) throws IOException{
		System.out.println("我们来聊天吧");
		//对用户提问
		askName(user);
		analyseChatChoice();
    	System.out.println("聊天结束，拜拜，谢谢你在主人不在的时候陪我解闷！");    			
	}
	
    /**
     * 读取、处理选择值的方法
     * 与用户交互，读取选择的数据加以处理
     * @return 选择
     * @throws IOException
     */
    private int getChatChoice() throws IOException {
        //不满足条件，循环会一直持续下去
        while(true) {
            try {
                System.out.println();
                printChattingChoiceMenu();
                System.out.print("Choice >:");
                int choice = Integer.parseInt(scanner.next());
                System.out.println();
                //提前处理数据，只有输入0到5的整数才是合法的
                if (0 <= choice && choice <= 6) {
                    return choice;
                }
                //提示用户输入错误
                System.out.println("Invalid choice:  " + choice);
            } catch (NumberFormatException numberFormatException) {
                //打印异常
                System.out.println(numberFormatException);
            }
        }
    }
	
    /**
     * 
     * @throws IOException
     */
    private void analyseChatChoice() throws IOException {
        //对传统印象中的for循环语句加以改造，使之更灵活
        for(int choice = this.getChatChoice(); choice != 0; choice = this.getChatChoice()) {
            //不需要default语句，因为在获取输入的时候就稳妥的处理了数据
            switch (choice) {
                case 1:
                    //聊聊天
                    this.justTalk();
                    break;
                case 2:
                    //玩猜数字游戏
                    this.runGuessNumberGame();
                    break;
                case 3:
                    //展示一张有趣的脸
                    this.showFunnyFace();
                    break;
                case 4:
                    //展示模拟的百度界面
                    this.showBaiDuImitation();
                    break;
                case 5:
                    //展示模拟的计算器
                    this.showCalculator();
                    break;
                case 6:
                    //展示Robot进阶版——TuringRobot
                    TuringRobot.advance();
                    break;
            }
        }
    }

/*******************************下面是涉及抽奖的方法****************************/    
    
	/**
	 * 
	 */
	private void readExtract() {
        /*
         * 抽奖功能，判断用户是不是为空
         * 不为空才可以抽奖（也就是要求登陆后再抽奖）
         * 抽奖就是两个伪随机数的匹配，理论上有1/9999的概率欧一把
         */
         System.out.println("机器人Sam的小小世界 ->抽奖");
         if (userLanding != null)
             //调用抽奖的方法
             extract(userLanding, luckyNumber);
         else
             System.out.println("请登陆后再抽奖");
	}

	/**
         *  抽奖方法
         *  传入用户对象和幸运号码(num)
         *  查看 对象的会员号  和幸运数字 是否 匹配
	 * @param user
	 * @param num
	 */
	private void extract(User user,int num) {
		if (user.getMemberID()==num) {
			System.out.println("今天的幸运数字为:"+num+" ,你的会员号为:"+user.getMemberID()+",恭喜你中奖了");
		}else {
			System.out.println("今天的幸运数字为:"+num+" ,你的会员号为:"+user.getMemberID()+",今天不是你的幸运日!!!");
		}
	}

/*******************************下面是涉及删除账号的方法****************************/	
	
	/**
	 * 
	 */
	private void readDeleteMemberID() {
        String operationString4 = "Y";
        while("Y".equalsIgnoreCase(operationString4)) {
            System.out.println("机器人Sam的小小世界 ->删除账号");   
            //删除指定的账户及密码信息
            deleteMemberID(enterUserName(), enterPassword());        
            System.out.println("是否继续删除用户:(Y/N)");
            operationString4 = scanner.next();
        }
	}
	
	/**
         * 删除账号功能
         *  循环遍历 数组，先匹配到账号
         *  如果密码也匹配到，把删除位置以后的元素往前挪一位，users[count] == null;把最后的元素释放
     *  count-1
	 * @param userName
	 * @param password
	 */
	private void deleteMemberID(String userName, String password) {
		//遍历所有的账号信息
		for (int i = 0; i < userCount; i++) {
			//找到对应的账号
			if (users[i].getUserName().equals(userName)) {
				//匹配密码是否正确
				if(users[i].getPassword().equals(password)) {
					//把删除位置以后的元素逐位往前挪一位
					for(int j = i; j < userCount; j++) {
						users[i] = users[i+1];
					}
					//把原来数组最后一位释放
					users[userCount]=null;
					//userCount-1
					userCount--;
					//提示用户删除成功
					System.out.println("删除用户成功");
					return;
				}
			}
		}
		//没有匹配到，提示用户错误
		System.out.println("你输入的账号密码不匹配,请重新输入！！！");
	}

/*******************************下面是涉及修改密码的方法*************************/	
	
	/**
	 * 
	 */
	private void readChangePassword() {
        /*
         *  修改密码功能 ，一共用到了两个方法:
         *  matchPassword:  查看账号密码是否匹配，成功的话返回下标，失败返回-1
         *  changePassword: 匹配成功后带着下标和新密码去找用户对象
         */
        String operationString2 = "Y";
        while("Y".equalsIgnoreCase(operationString2)) {
            System.out.println("机器人Sam的小小世界 ->修改密码");
            int passwordIndex = matchPassword(enterUserName(), enterPassword()); 
            //匹配账号密码，成功返回下标，失败返回-1
            if (passwordIndex >= 0) {
                //循环判断两次新密码是否匹配，用神奇的while(true)
                while (true) {
                    String passwordtemp = enterNewPassword();
                    //账号密码相同则调用第二个方法
                    if (passwordtemp.equals(enterNewPasswordAgain())) {
                        changePassword(passwordIndex, passwordtemp);
                        break;
                    } else {
                        //否则因不一致而报错给用户
                        System.out.println("两次密码不相同");                        
                    }
                    System.out.println("是否要重新输入新密码(Y/N)");    
                    String operationString3 = scanner.next();
                    //判断是否重新输入新密码
                    if("Y".equalsIgnoreCase(operationString3)) {
                        continue;                        
                    }
                    else {
                        break;                        
                    }
                }
            }
            System.out.println("是否继续修改密码:(Y/N)");
            operationString2 = scanner.next();
        }
	}
	
	private String enterNewPassword() {
        System.out.println("请输入新密码:");
        String newPassword = scanner.next();
	    return newPassword;
	}
	
	private String enterNewPasswordAgain() {
        //防止用户输入一次密码出现差错，需要第二次输入作为验证
        System.out.println("请再次输入新密码:");
        String newPassword = scanner.next();
        return newPassword;
	}

	/**
         *  修改密码的方法
         *  用第一个方法匹配账号密码，成功返回下标，失败返回-1
         *  再用第二个方法，传入下标和新密码       
         *  把 新传入的密码 赋值给对应用户对象的密码属性 
	 * @param name
	 * @param password
	 * @return
	 */
	private int matchPassword(String name, String password) {
		//遍历所有的账号信息
		for (int i = 0; i < userCount; i++) {
			//找到对应的账号
			if (users[i].getUserName().equals(name)) {
				//匹配密码是否正确
				if(users[i].getPassword().equals(password)) {
					return i;
				}
			}
		}
		//没有匹配到，提示用户错误
		System.out.println("你输入的账号密码不匹配，请重新输入！！");
		return -1;
	}

	/**
	 * 
	 * @param i
	 * @param password1
	 */
	private void changePassword(int i, String password1) {
		users[i].setPassword(password1);
		System.out.println("修改密码成功！！");
	}

/*************************下面是涉及查询所有账号信息的方法********************************/	
	
	/**
	 * 
	 */
	private void readPrintMemberInformation() {
        System.out.println("机器人Sam的小小世界 ->查询");
        //打印所有注册而未删除的账户信息
        printMemberInformation();
	}
	
	/**
	 *  打印所有的账号 密码信息
	 */
	private void printMemberInformation() {
		//遍历所有的账号信息
		for (int i = 0; i < userCount; i++)
			//调用用户类中的打印方法
			users[i].printUserInformation();
	}


/*************************下面是聊天系统中比较有意思的三个的功能*************************/
    
    /**
         * 展示一张有趣的脸的沙雕方法
     */
	private void showFunnyFace() {
		String face = "  ///////////////  \n"
				+ " +\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"+ \n"
				+ "(|    o      o   |)\n"
				+ " |       ^       |\n"
				+ " |      \'--\'     | \n"
				+ " +---------------+";
		System.out.println(face);
	}
	
	/**
	  * 展示GUI百度界面的沙雕方法
	  * 调用BaiduFrame类
	 */
	private void showBaiDuImitation() {
	    new BaiduFrame();
	}
	
	/**
	  * 展示GUI计算器界面的沙雕方法
	  * 调用CalculatorFrame类
	 */
	private void showCalculator() {
	    new CalculatorFrame();
	}
 
/********************************下面是聊天功能问询个人信息部分******************************/	
	
	/**
	 * 
	 * @param user
	 */
    private void askName(User user) {
        System.out.println("你好,可以告诉我你的名字吗？");
        String name = scanner.next();
    	if (name.length() > 8 || name.length() < 2) {
            System.out.println("你输入的人名不符合要求！");
    		name = "无名氏";
    	} else {
    		user.setName(name);
    	}
    	askIDNumber(user, name);
    }
    
    /**
     * 
     * @param user
     * @param name
     */
    private void askIDNumber(User user, String name) {
        System.out.println("你好，" + name + "，请输入你的18位身份证号：");
        String IDNumber = scanner.next(); 
        //对ID进行逐位正则匹配，老版本的是：\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d
        if (! Pattern.matches("^((\\d{18})|([0-9x]{18})|([0-9X]{18}))$" , IDNumber)) {
            System.out.println("你输入的身份证号码无法识别");
            return;
        } else {
            user.setIDNumber(IDNumber);
            askSex(user, name, IDNumber);
        }
    }
    
    private void askSex(User user, String name, String IDNumber) {
        System.out.println("Hi，" + name + "（帅气的）小哥哥/（漂亮的）小姐姐,请输入你的性别(M、W):");
        String sextemp = scanner.next();
        judgeSexThenAskMore(user, name, IDNumber, sextemp);
    }
    
    /**
     * 
     */
    private void judgeSexThenAskMore(User user, String name, String IDNumber, String sextemp){
        String sexCalled;
        if (sextemp.equalsIgnoreCase("M")) {
            sexCalled = "小哥哥";
            user.setSex(sextemp);
            askHeight(user);
            user.caculateAgeAndBirthday();
        } else if (sextemp.equalsIgnoreCase("W")) {
            sexCalled = "小姐姐";
            user.setSex(sextemp);
            System.out.println("你好，" + name + sexCalled);
            user.caculateAgeAndBirthday();
        } else {
            return;            
        }
    }
    
    /**
     * 
     * @param user
     */
    private void askHeight(User user) {
        System.out.println("请输入你的身高（cm）和体重（kg）：");
        double height = scanner.nextDouble(), weight = scanner.nextDouble();
        if (height > 250 || height <= 0) {
            System.out.println("你输入的身高不符合要求"); 
            height = 0.0;
        } else {
            user.setHeight(height);            
        }
        askWeight(user, weight);
    }
    
    /**
     * 
     * @param user
     * @param weight
     */
    private void askWeight(User user, double weight) {
        if (weight > 150 || weight <= 0) {
            System.out.println("你输入的体重不符合要求");
            weight = 0.0;
        } else {
            user.setWeight(weight);            
        }
    }
    
/*************************************下面是聊天内容部分**********************************/
    
    /**
         * 按顺序调用四个聊天扯皮的方法
     */
    private void justTalk() {
        this.talkAboutEat();
        this.talkAboutDrink();
        this.talkAboutPlay();
        this.talkAboutSleep();
    }
    
    /**
         * 聊吃的的方法
     */
    private void talkAboutEat() {
        System.out.println("我是吃货，我爱吃热狗，真香~~\n");
        askMechanically("吃");
    }
    
    /**
         * 聊喝的的方法
     */
    private void talkAboutDrink() {
        System.out.println("今天喝了点小酒，很舒服~~~~(嘿嘿，骗你的)\n"
        		+ "你喜欢喝肥宅快乐水吗？(Y/N)");
        String coke = scanner.next();
        if (coke.equalsIgnoreCase("Y")) {
        	System.out.println("不错耶，看来你是个肥宅");
        } else if (coke.equalsIgnoreCase("N")) {
            askMechanically("喝");
        } else {
        	System.out.println("抱歉，我无法识别你喜欢喝什么，姑且认为你喜欢喝白开水吧！");
        }
    }
    
    /**
         * 聊玩游戏的方法
     */
    private void talkAboutPlay() {
    	System.out.println("你喜欢玩游戏吗？(Y/N)");
        String playChoice = scanner.next();
        if (playChoice.equalsIgnoreCase("Y")) {
            sayTanWanLanYue();
            askMechanically("玩");
        } else if (playChoice.equalsIgnoreCase("N")) {
        	System.out.println("哇偶，厉害厉害，居然不玩游戏");
        } else {
        	System.out.println("抱歉，我无法识别你喜不喜欢玩！");
        }
        System.out.println("对了，我还喜欢唱、跳、RAP、篮球，Music~~~\n你打球真像cxk");
    }
    
    /**
         * 被talkAboutPlay()调用的、将会打印出来的一段话
     */
    private void sayTanWanLanYue() {
        System.out.println("不错耶，看来你是个同道中人\n"
                + "弟弟我用我的传奇手机跟渣渣辉和古田螺一起玩了一天传奇游戏贪玩蓝月,\n"
                + "装备回收，交易自由,\n"
                + "给大渣推荐一款曹好碗的游戏,\n"
                + "探碗懒月，你没有玩过的船新版本，\n"
                + "挤需体验三番中，你就会干我一样，爱向介款游戏......\n"
                + "我今天玩的真开森，nice！");
    }
    
    /**
         * 聊睡眠的方法
     */
    private void talkAboutSleep() {
        System.out.println("对我来说，除了吃饭和打篮球，最幸福的事就是睡觉了。。喵。。。");
    }
    
    /**
         * 从几个沙雕的问题中找到共有的语句抽取成方法
     */
    private void askMechanically(String var1) {
        System.out.println("你喜欢" + var1 +  "什么呢？");
        String var2 = scanner.next();
        System.out.println("原来你喜欢" + var1 + var2 + "啊，我也喜欢! \n 真巧呢");
    }

/*********************************下面是猜数字游戏部分**************************************/        
    /**
         * 猜数字的基本问题
     */
    private String askGuess() {
        System.out.println("是否猜数字？(Y/N)");
        System.out.print("choice >");
        String choice = scanner.next();
        return choice;
    }
    
    private void runGuessNumberGame(/*int guessnumberGameCounter, String choice*/) {
        int guessNumberGameCounter = 0;
        while(askGuess().equalsIgnoreCase("Y")) {
            int[] setNumberArray = new int[3];
            int[] getNumberArray = new int[3];      
            int setNumber = random.nextInt(900)+100;
            if (! judgeNumberIsCorrect(setNumber)) {
                return;
            }
            guessNumberSave(setNumber, setNumberArray);
            System.out.println("你至多有5次机会猜数字~~~\n请输入一个三位整数");            
            guessNumber(setNumberArray, getNumberArray, guessNumberGameCounter, setNumber);
        }
    }
    
    private void guessNumber(int[] setNumberArray, int[] getNumberArray, int guessNumberGameCounter, int setNumber) {
        for (int j= 0; j < 5; j++) {
            int getNumber = scanner.nextInt();
            if (! judgeNumberIsCorrect(getNumber)) {
                return;
            }
            guessNumberSave(getNumber, getNumberArray);
            guessNumberGameCounter = 0;     
            for (int i = 0; i < 3; i++) {
                guessNumberGameCounter = guessNumberJudge(setNumberArray, getNumberArray, i, guessNumberGameCounter);
            }
            if (guessNumberGameCounter == 3) {
                System.out.println("恭喜你，你猜对了！");
                break;
            }           
            if (j == 4) {
                System.out.println("要猜的数是：" + setNumber);
            }
        }
    }
    
    /**
     * 
     * @param number
     * @return
     */
    private boolean judgeNumberIsCorrect(int number) {
        if (number < 100 || number > 999) {
            System.err.println("数据不合法,猜数字游戏结束");
            return false;
        }
        return true;
    }
    
    /**
     * 
     * @param number
     * @param numberArray
     * @return
     */
	private int[] guessNumberSave(int number, int[] numberArray) {
		int hundred = number/100;
		int decade  = (number-hundred*100)/10;
		int unit    = number-hundred*100-decade*10;
		numberArray[0] = hundred;
		numberArray[1] = decade;
		numberArray[2] = unit;		
		return numberArray;
	}
	
	/**
	 * 
	 * @param numberArray1
	 * @param numberArray2
	 * @param i
	 * @param guessNumberGameCounter
	 * @return
	 */
	private int guessNumberJudge(int[] numberArray1, int[] numberArray2, int i, int guessNumberGameCounter) {
		if (numberArray1[i] > numberArray2[i]) {
			System.out.println("第" + (i+1) + "位猜低了！");
		} else if (numberArray1[i] < numberArray2[i]) {
			System.out.println("第" + (i+1) + "位猜高了！");
		} else {
			System.out.println("恭喜你，第" + (i+1) + "位猜对了！");
			guessNumberGameCounter++;
		}
		return guessNumberGameCounter;
	}
	
/******************************下面是要打印的清单和问候语************************************/
	
	/**
         * 自我介绍方法
         * 用一串打印的语句做简单的“自我介绍”
     */
    private void introduceMyself() {
        System.out.println("你好，我是练习时长两年半的机器人：Sam\n"
                + "我还是个孩子，你可不许欺负我\n"
                + "我的主人是XXX，嘿嘿，你认识他吗？\n"
                + "主人说我是个\"男生\"，嘿嘿，你猜猜我健壮吗？\n"
                + "我诞生于东北大学图书馆，那可是个好地方呢\n"
                + "我的小小世界里做选择时可以忽略大小写哦");
    }

	/**
	  *  打印主菜单的方法
	 */
	private void printMainMenu() {
		System.out.println("********欢迎来到机器人Sam的小小世界**********\n"
				+ "     \t        请作出你的选择吧		\n"
				+ "             1.注册					\n"
				+ "             2.登陆					\n" 
				+ "             3.聊天					\n"
				+ "             4.抽奖					\n"
				+ "             5.查询					\n"
				+ "             6.修改密码				\n"
				+ "             7.删除账号				\n"
				+ "      \t      其他数字退出系统	    \n"
				+ "********************************************");
	}
	
	  
    /**
         * 打印聊天菜单的方法
     */
    private void printChattingChoiceMenu() {
        System.out.println("********************************************\n"
                + "\t聊聊天，放放松，心情愉悦身体棒     \n"
                + "\t 0:残忍退出                    \n"
                + "\t 1:聊聊各种吃喝玩乐            \n"
                + "\t 2:玩猜数字的游戏              \n"
                + "\t 3:看一张有趣的脸              \n"
                + "\t 4.看看模拟出来的百度一下的界面\n"
                + "\t 5:看一下模拟出来的小小计算器  \n"
                + "\t 6.看我的进阶状态              \n"
                + "\t 7.do nothing                  \n"
                + "\t 输入其他的都是不合法的哦             \n"
                + "********************************************");
    }
    
/********************************所有的方法都结束了***********************************/
    
}
