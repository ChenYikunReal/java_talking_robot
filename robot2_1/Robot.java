package robot2_1;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;
import java.util.regex.Pattern;
import java.io.IOException;

/**
 * Robot��һЩ���ص����
 * @author BlankSpace
 * @version 2.1
 */

public class Robot {
    //Ϊ����Ƶ���ģʽ����Ҫ�½�һ��Robot��ʵ��robot����ֵΪnull
    private static Robot robot = null;
	//����һ���û���������
	private User[] users = null;
	//��¼Ԫ�ظ���
	private int userCount=0;
	//����Scanner��Ĺ��������ڴ�������class�Ļ�������
	private Scanner scanner = new Scanner(System.in);
	//����һ��Random��ʵ����������class�����������
    private Random random = new Random();
    //���ɵ����ڴ��������������int
    private int luckyNumber;
    //�����û��˻�����δ��¼״̬
    private User userLanding = null;
    //�����û���¼�ѳ��Դ���Ϊ0
    private int tryToLoadTimes = 0; 
	
	/**
	 *  ����һ�����췽��(������)����ʼ��ϵͳ�����洢�û���Ϣ�������С
	 *  Ĭ��Ϊ10������С��0����Ҳ��Ϊ10
	 */
	private Robot() {
		users = new User[10];
	}
	
	/**
	 * ���������������½�ָ���̶ȵ��˻�
	 * @param size �˻�����
	 */
	private Robot(int size) {
		if (size>0) {
			//���� ����Ĵ�С
			users = new User[size];
		} else {
		    users = new User[10];
		}
	}
    
	/**
	 * ��ȡ�����ķ���
	 * @return robot����
	 */
    public synchronized static Robot getInstance() {
        if (robot == null) {
            robot = new Robot();
        }
        return robot;
    }
	
    /**
     * ����Ŀ�ִ�в���(��Robot��ȡ���������)
     * @throws IOException
     */
	public void run() throws IOException {
	    //��ӡһ�����ҽ��ܵ�ɳ������
	    this.introduceMyself();
	    //����ϵͳ���������� 
	    luckyNumber = random.nextInt(8999)+1000;   
	    //ʵ����һ������Ķ��󣬶�������Ĵ�С	    new Robot(10);
	    //���û�����ѡ�񲢴���
	    this.analyseMainChoice();
	}

/*******************************�������漰�����û�ѡ��ķ���****************************/	
	
	/**
	 * ����getChoice()�������ȡ��choice������ѡ�����ִ��
	 * @throws IOException
	 */
    private void analyseMainChoice() throws IOException {
        //�Դ�ͳӡ���е�forѭ�������Ը��죬ʹ֮�����
        for(int choice = this.getChoice(); choice != 0; choice = this.getChoice()) {
            //����Ҫdefault��䣬��Ϊ�ڻ�ȡ�����ʱ������׵Ĵ���������
            switch (choice) {
                case 1:
                    //��¼����
                    this.readRegister();
                    break;
                case 2:
                    //��¼����
                    this.readLanding();
                    break;
                case 3:
                    //���촦��
                    this.readChat();
                    break;
                case 4:
                    //�齱����
                    this.readExtract();
                    break;
                case 5:
                    //��ѯ����
                    this.readPrintMemberInformation();
                    break;
                case 6:
                    //���ܴ���
                    this.readChangePassword();
                    break;
                case 7:
                    //ɾ������
                    this.readDeleteMemberID();
                    break;
            }
        }
    }

    
    /**
         * ��ȡ������ѡ��ֵ�ķ���
         * ���û���������ȡѡ������ݼ��Դ���
     * @return ѡ��
     * @throws IOException
     */
    private int getChoice() throws IOException {
        //������������ѭ����һֱ������ȥ
        while(true) {
            try {
                System.out.println();
                //��ӡ���˵�
                printMainMenu();
                //��ʾ�û�����
                System.out.print("Choice >:");
                int choice = Integer.parseInt(scanner.next());
                System.out.println();
                //��ǰ�������ݣ�ֻ������0��7���������ǺϷ���
                if (0 <= choice && choice <= 7) {
                    return choice;
                }
                //��ʾ�û��������
                System.out.println("Invalid choice:  " + choice);
            } catch (NumberFormatException numberFormatException) {
                //��ӡ�쳣
                System.out.println(numberFormatException);
            }
        }
    }

/*******************************�������漰ע���˺ŵķ���****************************/    
    
    /**
     * 
     */
    private void readRegister() {
        //����һ���ַ���������ѭ��(��������ʱ����break����ѭ��)��һֱ�ж��ǲ���Ҫ����ע��
        String operationString1 = "Y";
        //����equalsIgnoreCase()�����������Դ�Сд��ƥ�䣬�����Ѻ�
        while ("Y".equalsIgnoreCase(operationString1)) {
            System.out.println("������Sam��СС���� ->ע��");
            //��random�������ɵ�α�����ȷ����Ա��
            int memberID = random.nextInt(8999)+1000;
            //ʵ����һ���û�����
            User user = new User(enterUserName(), enterPassword(), memberID);
            //����ע����û���ӵ�������
            register(user);
            System.out.println("����Ҫ����ע����(Y/N)");
            operationString1 = scanner.next();
        }
    }

	/**
	 *  ע�᷽��
	 *  ��ӷ���,�����ӵ�����������ķ�Χ�����������顣��Java���鱾���Ȳ��ɱ䣬���������൱��ʵ���˶�̬���飩
	 *  ��user������users��������ݣ��ж��˺��Ƿ��Ѿ����ڣ�
	 *  ��¼Ԫ�ظ�����
	 *  �ж���ӵ� �˺��Ƿ��Ѿ����ڣ�ֻ���˺Ų����ڲŻ���ӳɹ�
	 *  ��Ա���ظ����ʼ��ͣ��ݷ�������
	 *  ע��ɹ����ӡ һ��ע������Ϣ
	 *  @param user
	 */
	private void register(User user) {
		//�ж�������û�б���������ֹԽ���޷�����
		if(userCount>=users.length)
			//����ԭ����һ��
			users = Arrays.copyOf(users, users.length*3/2+1);
		if (checkDuplicate(user)) {
		    return;
		}
		//�������
		users[userCount] = user;
		//����count
		userCount++; 						
		// ��ӡע������Ϣ
		System.out.println("ע��ɹ�����");
		System.out.println("�û���\t����\t��Ա��");
		//��д��toString()��������ӡ���˻���Ϣ
		System.out.println(user);
	}
	
	/**
	 * 
	 * @param user
	 * @return
	 */
	private boolean checkDuplicate(User user) {
        //������ѭ�����ң��Ƿ�����ظ��˺�
        for (int i = 0; i < userCount; i++) {
            //�ж��û����Ƿ����
            if (users[i].getUserName().equals(user.getUserName())) {
                System.out.println("��������˺��ظ������������룡��");
                return true;
            }
        }
        return false;
	}
	
/**************************�������漰�����˺ŵĹ�������*************************/
	
	private String enterUserName() {
        System.out.print("�������˺�:");
        String userName = scanner.next();
        return userName;
	}
	
	private String enterPassword() {
        System.out.print("����������:");
        String password = scanner.next();
        return password;
	}
	
/*******************************�������漰��¼�ķ���****************************/ 
	
	/**
         *  ��½�ɹ��򷵻�һ���û�����userLanding
         *  ��½ʧ���򷵻� null
         *  �����¼ʧ�����Σ����������
	 */
    private void readLanding() {
        System.out.println("������Sam��СС���� ->��½");
        tryToLoadTimes = 0;
        userLanding = landing(new User(enterUserName(), enterPassword(), 0));   //��½�ɹ������ض��󣬲��ɹ����ؿ�
        tryToLoadTimes++;
        if (tryToLoadTimes >= 3) {
            //�����flag�����á���������break�Ĵ���ִ��
            return;
        }
    }


	/**
	 * ��½����
	 * ����һ���û�����
	 * �����û����� (users)�������˺�
	 * ����˺������Ӧ����������û�����
	 * û�ҵ�������null
	 * @param user
	 * @return Ҫ��¼���˻�
	 */
	private User landing(User user) {
		//int state=0;        //��¼��½״̬
		//�����û����� (users)�������˺�
		for (int i = 0; i < userCount; i++) {
			//�ҵ���Ӧ���˺�
			if (users[i].getUserName().equals(user.getUserName())) {
				//ƥ�������Ƿ���ȷ
				if(users[i].getPassword().equals(user.getPassword())) {
					//state=1;
					System.out.println("��½�ɹ�����ӭ�û�" + users[i].getUserName());
					//������� �û�����
					return users[i];
				}
			}
		}
		//ûƥ����ȷ�����û�������ʾ�������벢����null
		System.out.println("�˺����벻��ȷ������������");
		return null;
	}
	
/*******************************�������漰����"���"�ķ���****************************/	
	
	/**
	 * 
	 * @throws IOException
	 */
	private void readChat() throws IOException{
        /**
         * ���칦�ܣ��ж��û��ǲ���Ϊ��
         * ��Ϊ�ղſ������죨Ҳ����Ҫ���½�������죩
         */
        System.out.println("������Sam��СС���� ->����");
        if (userLanding != null)
            //��������ķ���
            chat(userLanding);
        else 
            System.out.println("���½��������");
	}
	
	/**
	 *  ���췽��
         *  ���û����н�����������Ȥ�ġ��Ի���
         *  ������������һ�㣬��������û�ʹ��
	 * @param user
	 * @throws IOException
	 */
	private void chat(User user) throws IOException{
		System.out.println("�����������");
		//���û�����
		askName(user);
		analyseChatChoice();
    	System.out.println("����������ݰݣ�лл�������˲��ڵ�ʱ�����ҽ��ƣ�");    			
	}
	
    /**
     * ��ȡ������ѡ��ֵ�ķ���
     * ���û���������ȡѡ������ݼ��Դ���
     * @return ѡ��
     * @throws IOException
     */
    private int getChatChoice() throws IOException {
        //������������ѭ����һֱ������ȥ
        while(true) {
            try {
                System.out.println();
                printChattingChoiceMenu();
                System.out.print("Choice >:");
                int choice = Integer.parseInt(scanner.next());
                System.out.println();
                //��ǰ�������ݣ�ֻ������0��5���������ǺϷ���
                if (0 <= choice && choice <= 6) {
                    return choice;
                }
                //��ʾ�û��������
                System.out.println("Invalid choice:  " + choice);
            } catch (NumberFormatException numberFormatException) {
                //��ӡ�쳣
                System.out.println(numberFormatException);
            }
        }
    }
	
    /**
     * 
     * @throws IOException
     */
    private void analyseChatChoice() throws IOException {
        //�Դ�ͳӡ���е�forѭ�������Ը��죬ʹ֮�����
        for(int choice = this.getChatChoice(); choice != 0; choice = this.getChatChoice()) {
            //����Ҫdefault��䣬��Ϊ�ڻ�ȡ�����ʱ������׵Ĵ���������
            switch (choice) {
                case 1:
                    //������
                    this.justTalk();
                    break;
                case 2:
                    //���������Ϸ
                    this.runGuessNumberGame();
                    break;
                case 3:
                    //չʾһ����Ȥ����
                    this.showFunnyFace();
                    break;
                case 4:
                    //չʾģ��İٶȽ���
                    this.showBaiDuImitation();
                    break;
                case 5:
                    //չʾģ��ļ�����
                    this.showCalculator();
                    break;
                case 6:
                    //չʾRobot���װ桪��TuringRobot
                    TuringRobot.advance();
                    break;
            }
        }
    }

/*******************************�������漰�齱�ķ���****************************/    
    
	/**
	 * 
	 */
	private void readExtract() {
        /*
         * �齱���ܣ��ж��û��ǲ���Ϊ��
         * ��Ϊ�ղſ��Գ齱��Ҳ����Ҫ���½���ٳ齱��
         * �齱��������α�������ƥ�䣬��������1/9999�ĸ���ŷһ��
         */
         System.out.println("������Sam��СС���� ->�齱");
         if (userLanding != null)
             //���ó齱�ķ���
             extract(userLanding, luckyNumber);
         else
             System.out.println("���½���ٳ齱");
	}

	/**
         *  �齱����
         *  �����û���������˺���(num)
         *  �鿴 ����Ļ�Ա��  ���������� �Ƿ� ƥ��
	 * @param user
	 * @param num
	 */
	private void extract(User user,int num) {
		if (user.getMemberID()==num) {
			System.out.println("�������������Ϊ:"+num+" ,��Ļ�Ա��Ϊ:"+user.getMemberID()+",��ϲ���н���");
		}else {
			System.out.println("�������������Ϊ:"+num+" ,��Ļ�Ա��Ϊ:"+user.getMemberID()+",���첻�����������!!!");
		}
	}

/*******************************�������漰ɾ���˺ŵķ���****************************/	
	
	/**
	 * 
	 */
	private void readDeleteMemberID() {
        String operationString4 = "Y";
        while("Y".equalsIgnoreCase(operationString4)) {
            System.out.println("������Sam��СС���� ->ɾ���˺�");   
            //ɾ��ָ�����˻���������Ϣ
            deleteMemberID(enterUserName(), enterPassword());        
            System.out.println("�Ƿ����ɾ���û�:(Y/N)");
            operationString4 = scanner.next();
        }
	}
	
	/**
         * ɾ���˺Ź���
         *  ѭ������ ���飬��ƥ�䵽�˺�
         *  �������Ҳƥ�䵽����ɾ��λ���Ժ��Ԫ����ǰŲһλ��users[count] == null;������Ԫ���ͷ�
     *  count-1
	 * @param userName
	 * @param password
	 */
	private void deleteMemberID(String userName, String password) {
		//�������е��˺���Ϣ
		for (int i = 0; i < userCount; i++) {
			//�ҵ���Ӧ���˺�
			if (users[i].getUserName().equals(userName)) {
				//ƥ�������Ƿ���ȷ
				if(users[i].getPassword().equals(password)) {
					//��ɾ��λ���Ժ��Ԫ����λ��ǰŲһλ
					for(int j = i; j < userCount; j++) {
						users[i] = users[i+1];
					}
					//��ԭ���������һλ�ͷ�
					users[userCount]=null;
					//userCount-1
					userCount--;
					//��ʾ�û�ɾ���ɹ�
					System.out.println("ɾ���û��ɹ�");
					return;
				}
			}
		}
		//û��ƥ�䵽����ʾ�û�����
		System.out.println("��������˺����벻ƥ��,���������룡����");
	}

/*******************************�������漰�޸�����ķ���*************************/	
	
	/**
	 * 
	 */
	private void readChangePassword() {
        /*
         *  �޸����빦�� ��һ���õ�����������:
         *  matchPassword:  �鿴�˺������Ƿ�ƥ�䣬�ɹ��Ļ������±꣬ʧ�ܷ���-1
         *  changePassword: ƥ��ɹ�������±��������ȥ���û�����
         */
        String operationString2 = "Y";
        while("Y".equalsIgnoreCase(operationString2)) {
            System.out.println("������Sam��СС���� ->�޸�����");
            int passwordIndex = matchPassword(enterUserName(), enterPassword()); 
            //ƥ���˺����룬�ɹ������±꣬ʧ�ܷ���-1
            if (passwordIndex >= 0) {
                //ѭ���ж������������Ƿ�ƥ�䣬�������while(true)
                while (true) {
                    String passwordtemp = enterNewPassword();
                    //�˺�������ͬ����õڶ�������
                    if (passwordtemp.equals(enterNewPasswordAgain())) {
                        changePassword(passwordIndex, passwordtemp);
                        break;
                    } else {
                        //������һ�¶�������û�
                        System.out.println("�������벻��ͬ");                        
                    }
                    System.out.println("�Ƿ�Ҫ��������������(Y/N)");    
                    String operationString3 = scanner.next();
                    //�ж��Ƿ���������������
                    if("Y".equalsIgnoreCase(operationString3)) {
                        continue;                        
                    }
                    else {
                        break;                        
                    }
                }
            }
            System.out.println("�Ƿ�����޸�����:(Y/N)");
            operationString2 = scanner.next();
        }
	}
	
	private String enterNewPassword() {
        System.out.println("������������:");
        String newPassword = scanner.next();
	    return newPassword;
	}
	
	private String enterNewPasswordAgain() {
        //��ֹ�û�����һ��������ֲ����Ҫ�ڶ���������Ϊ��֤
        System.out.println("���ٴ�����������:");
        String newPassword = scanner.next();
        return newPassword;
	}

	/**
         *  �޸�����ķ���
         *  �õ�һ������ƥ���˺����룬�ɹ������±꣬ʧ�ܷ���-1
         *  ���õڶ��������������±��������       
         *  �� �´�������� ��ֵ����Ӧ�û�������������� 
	 * @param name
	 * @param password
	 * @return
	 */
	private int matchPassword(String name, String password) {
		//�������е��˺���Ϣ
		for (int i = 0; i < userCount; i++) {
			//�ҵ���Ӧ���˺�
			if (users[i].getUserName().equals(name)) {
				//ƥ�������Ƿ���ȷ
				if(users[i].getPassword().equals(password)) {
					return i;
				}
			}
		}
		//û��ƥ�䵽����ʾ�û�����
		System.out.println("��������˺����벻ƥ�䣬���������룡��");
		return -1;
	}

	/**
	 * 
	 * @param i
	 * @param password1
	 */
	private void changePassword(int i, String password1) {
		users[i].setPassword(password1);
		System.out.println("�޸�����ɹ�����");
	}

/*************************�������漰��ѯ�����˺���Ϣ�ķ���********************************/	
	
	/**
	 * 
	 */
	private void readPrintMemberInformation() {
        System.out.println("������Sam��СС���� ->��ѯ");
        //��ӡ����ע���δɾ�����˻���Ϣ
        printMemberInformation();
	}
	
	/**
	 *  ��ӡ���е��˺� ������Ϣ
	 */
	private void printMemberInformation() {
		//�������е��˺���Ϣ
		for (int i = 0; i < userCount; i++)
			//�����û����еĴ�ӡ����
			users[i].printUserInformation();
	}


/*************************����������ϵͳ�бȽ�����˼�������Ĺ���*************************/
    
    /**
         * չʾһ����Ȥ������ɳ�񷽷�
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
	  * չʾGUI�ٶȽ����ɳ�񷽷�
	  * ����BaiduFrame��
	 */
	private void showBaiDuImitation() {
	    new BaiduFrame();
	}
	
	/**
	  * չʾGUI�����������ɳ�񷽷�
	  * ����CalculatorFrame��
	 */
	private void showCalculator() {
	    new CalculatorFrame();
	}
 
/********************************���������칦����ѯ������Ϣ����******************************/	
	
	/**
	 * 
	 * @param user
	 */
    private void askName(User user) {
        System.out.println("���,���Ը��������������");
        String name = scanner.next();
    	if (name.length() > 8 || name.length() < 2) {
            System.out.println("�����������������Ҫ��");
    		name = "������";
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
        System.out.println("��ã�" + name + "�����������18λ���֤�ţ�");
        String IDNumber = scanner.next(); 
        //��ID������λ����ƥ�䣬�ϰ汾���ǣ�\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d
        if (! Pattern.matches("^((\\d{18})|([0-9x]{18})|([0-9X]{18}))$" , IDNumber)) {
            System.out.println("����������֤�����޷�ʶ��");
            return;
        } else {
            user.setIDNumber(IDNumber);
            askSex(user, name, IDNumber);
        }
    }
    
    private void askSex(User user, String name, String IDNumber) {
        System.out.println("Hi��" + name + "��˧���ģ�С���/��Ư���ģ�С���,����������Ա�(M��W):");
        String sextemp = scanner.next();
        judgeSexThenAskMore(user, name, IDNumber, sextemp);
    }
    
    /**
     * 
     */
    private void judgeSexThenAskMore(User user, String name, String IDNumber, String sextemp){
        String sexCalled;
        if (sextemp.equalsIgnoreCase("M")) {
            sexCalled = "С���";
            user.setSex(sextemp);
            askHeight(user);
            user.caculateAgeAndBirthday();
        } else if (sextemp.equalsIgnoreCase("W")) {
            sexCalled = "С���";
            user.setSex(sextemp);
            System.out.println("��ã�" + name + sexCalled);
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
        System.out.println("�����������ߣ�cm�������أ�kg����");
        double height = scanner.nextDouble(), weight = scanner.nextDouble();
        if (height > 250 || height <= 0) {
            System.out.println("���������߲�����Ҫ��"); 
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
            System.out.println("����������ز�����Ҫ��");
            weight = 0.0;
        } else {
            user.setWeight(weight);            
        }
    }
    
/*************************************�������������ݲ���**********************************/
    
    /**
         * ��˳������ĸ����쳶Ƥ�ķ���
     */
    private void justTalk() {
        this.talkAboutEat();
        this.talkAboutDrink();
        this.talkAboutPlay();
        this.talkAboutSleep();
    }
    
    /**
         * �ĳԵĵķ���
     */
    private void talkAboutEat() {
        System.out.println("���ǳԻ����Ұ����ȹ�������~~\n");
        askMechanically("��");
    }
    
    /**
         * �ĺȵĵķ���
     */
    private void talkAboutDrink() {
        System.out.println("������˵�С�ƣ������~~~~(�ٺ٣�ƭ���)\n"
        		+ "��ϲ���ȷ�լ����ˮ��(Y/N)");
        String coke = scanner.next();
        if (coke.equalsIgnoreCase("Y")) {
        	System.out.println("����Ү���������Ǹ���լ");
        } else if (coke.equalsIgnoreCase("N")) {
            askMechanically("��");
        } else {
        	System.out.println("��Ǹ�����޷�ʶ����ϲ����ʲô��������Ϊ��ϲ���Ȱ׿�ˮ�ɣ�");
        }
    }
    
    /**
         * ������Ϸ�ķ���
     */
    private void talkAboutPlay() {
    	System.out.println("��ϲ������Ϸ��(Y/N)");
        String playChoice = scanner.next();
        if (playChoice.equalsIgnoreCase("Y")) {
            sayTanWanLanYue();
            askMechanically("��");
        } else if (playChoice.equalsIgnoreCase("N")) {
        	System.out.println("��ż��������������Ȼ������Ϸ");
        } else {
        	System.out.println("��Ǹ�����޷�ʶ����ϲ��ϲ���棡");
        }
        System.out.println("���ˣ��һ�ϲ����������RAP������Music~~~\n���������cxk");
    }
    
    /**
         * ��talkAboutPlay()���õġ������ӡ������һ�λ�
     */
    private void sayTanWanLanYue() {
        System.out.println("����Ү���������Ǹ�ͬ������\n"
                + "�ܵ������ҵĴ����ֻ��������Ժ͹�����һ������һ�촫����Ϸ̰������,\n"
                + "װ�����գ���������,\n"
                + "�������Ƽ�һ��ܺ������Ϸ,\n"
                + "̽�����£���û������Ĵ��°汾��\n"
                + "�������������У���ͻ����һ������������Ϸ......\n"
                + "�ҽ�������濪ɭ��nice��");
    }
    
    /**
         * ��˯�ߵķ���
     */
    private void talkAboutSleep() {
        System.out.println("������˵�����˳Է��ʹ��������Ҹ����¾���˯���ˡ�����������");
    }
    
    /**
         * �Ӽ���ɳ����������ҵ����е�����ȡ�ɷ���
     */
    private void askMechanically(String var1) {
        System.out.println("��ϲ��" + var1 +  "ʲô�أ�");
        String var2 = scanner.next();
        System.out.println("ԭ����ϲ��" + var1 + var2 + "������Ҳϲ��! \n ������");
    }

/*********************************�����ǲ�������Ϸ����**************************************/        
    /**
         * �����ֵĻ�������
     */
    private String askGuess() {
        System.out.println("�Ƿ�����֣�(Y/N)");
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
            System.out.println("��������5�λ��������~~~\n������һ����λ����");            
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
                System.out.println("��ϲ�㣬��¶��ˣ�");
                break;
            }           
            if (j == 4) {
                System.out.println("Ҫ�µ����ǣ�" + setNumber);
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
            System.err.println("���ݲ��Ϸ�,��������Ϸ����");
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
			System.out.println("��" + (i+1) + "λ�µ��ˣ�");
		} else if (numberArray1[i] < numberArray2[i]) {
			System.out.println("��" + (i+1) + "λ�¸��ˣ�");
		} else {
			System.out.println("��ϲ�㣬��" + (i+1) + "λ�¶��ˣ�");
			guessNumberGameCounter++;
		}
		return guessNumberGameCounter;
	}
	
/******************************������Ҫ��ӡ���嵥���ʺ���************************************/
	
	/**
         * ���ҽ��ܷ���
         * ��һ����ӡ��������򵥵ġ����ҽ��ܡ�
     */
    private void introduceMyself() {
        System.out.println("��ã�������ϰʱ�������Ļ����ˣ�Sam\n"
                + "�һ��Ǹ����ӣ���ɲ����۸���\n"
                + "�ҵ�������XXX���ٺ٣�����ʶ����\n"
                + "����˵���Ǹ�\"����\"���ٺ٣���²��ҽ�׳��\n"
                + "�ҵ����ڶ�����ѧͼ��ݣ��ǿ��Ǹ��õط���\n"
                + "�ҵ�СС��������ѡ��ʱ���Ժ��Դ�СдŶ");
    }

	/**
	  *  ��ӡ���˵��ķ���
	 */
	private void printMainMenu() {
		System.out.println("********��ӭ����������Sam��СС����**********\n"
				+ "     \t        ���������ѡ���		\n"
				+ "             1.ע��					\n"
				+ "             2.��½					\n" 
				+ "             3.����					\n"
				+ "             4.�齱					\n"
				+ "             5.��ѯ					\n"
				+ "             6.�޸�����				\n"
				+ "             7.ɾ���˺�				\n"
				+ "      \t      ���������˳�ϵͳ	    \n"
				+ "********************************************");
	}
	
	  
    /**
         * ��ӡ����˵��ķ���
     */
    private void printChattingChoiceMenu() {
        System.out.println("********************************************\n"
                + "\t�����죬�ŷ��ɣ��������������     \n"
                + "\t 0:�����˳�                    \n"
                + "\t 1:���ĸ��ֳԺ�����            \n"
                + "\t 2:������ֵ���Ϸ              \n"
                + "\t 3:��һ����Ȥ����              \n"
                + "\t 4.����ģ������İٶ�һ�µĽ���\n"
                + "\t 5:��һ��ģ�������СС������  \n"
                + "\t 6.���ҵĽ���״̬              \n"
                + "\t 7.do nothing                  \n"
                + "\t ���������Ķ��ǲ��Ϸ���Ŷ             \n"
                + "********************************************");
    }
    
/********************************���еķ�����������***********************************/
    
}
