package robot2_1;

//���ڴ���һЩ�����յ�����
import java.util.Calendar;

/**
 * �û���Ϣ�� 
 * �����������ظ������ĵ�ע�ͣ�ʹ֮��������
 *  @author BlankSpace
 *  @version 2.1
 *  @time 2019��5��30��
 */

public class User{
	/*
	 * ��private�������ݡ���װ�û�����
	 * ��� ͬʱ ֻ��һ�������� �ⲻ�ö��� state ����
	 */
	private String userName;  	//�˺�
	private String password;  	//����
	private int    memberID;	//��Ա��	
//	private int state;   		//״̬
	private String name;		//����
    private String sex;			//�Ա�
    private int    age;			//����
    private String IDNumber;	//���֤��
    private double height;		//���
    private double weight;		//����		
	
    /**
     * ���û��������롢��Ա������������ɵĹ�����
     * ��ע��ɹ���ʱ����ù����������û����浽��������
     * @param username �û���
     * @param password �˻�����
     * @param memberID �û���Ա��
     */
	public User(String userName,String password, int memberID) {
		this.userName = userName;
		this.password = password;
		this.memberID = memberID;
	}
	
	/**
	 * ��������userNameֵ�ķ���
	 * @param userName �û���
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	/**
	 * ���ڷ���userNameֵ�ķ���
	 * @return �û���
	 */
	public String getUserName() {
		return this.userName;
	}
	
	/**
	 * ��������passwordֵ�ķ���
	 * @param password ����
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * ���ڷ���passwordֵ�ķ���
	 * @return ����
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * ��������memberIDֵ�ķ���
	 * @param memberID ��Ա��
	 */
	public void setMemberID(int memberID) {
		this.memberID = memberID;
	}
	
	/**
	 * ���ڷ���memberIDֵ�ķ���
	 * @return ��Ա��
	 */
	public int getMemberID() {
		return this.memberID;
	}
	
	/**
	 * ��������nameֵ�ķ���
	 * @param name ����
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * ��������sexֵ�ķ���
	 * @param sextemp �Ա����ʱ����M/W
	 */
	public void setSex(String sextemp) {
		if (sextemp.equalsIgnoreCase("M"))
			sex = "��";
		else if (sextemp.equalsIgnoreCase("W"))
			sex = "Ů";
	}

	/**
	 * ��������IDNumberֵ�ķ���
	 * @param IDNumber ���֤����
	 */
    public final void setIDNumber(String IDNumber){
        this.IDNumber = IDNumber;
    }

    /**
     * ��������heightֵ�ķ���
     * @param height ���
     */
    public final void setHeight(double height) {
        this.height = height;
    }

    /**
     * ��������weightֵ�ķ���
     * @param weight ����
     */
    public final void setWeight(double weight) {
        this.weight = weight;            
    }
    
    /**
     * �������䡢���ղ������Ա�ֱ���õķ���
     */
    public void caculateAgeAndBirthday() {
        //������ĺϷ����֤�Ŵ�String���Ӵ�(7-10λ)ת��Ϊint
        //�Ӵ���0��ʼȡ������ҿ�
        int year = Integer.parseInt(IDNumber.substring(6,10));        
        //������ĺϷ����֤�Ŵ�String���Ӵ�(11-12λ)ת��Ϊint
        int month = Integer.parseInt(IDNumber.substring(10,12));        
        //������ĺϷ����֤�Ŵ�String���Ӵ�(13-14λ)ת��Ϊint
        int day = Integer.parseInt(IDNumber.substring(12,14));        
        //��ȡ����ʱ�䵥������
        Calendar calendar = Calendar.getInstance();
        //��ȡ��ǰʱ����д���
        age = calendar.get(Calendar.YEAR) - year;        
        if (sex.equals("��")) {
            printManInformation(month, day);
        } else if (sex.equals("Ů")) {
        	printWomanInformation(month, day);
        }
    }
	
	/**
	 * ����˺���Ϣ�ķ���
	 */
	public void printUserInformation() {
		System.out.println("UserName:"+ userName+ "\tPassword:" + password + "\tMemberID:" + memberID);
	}
	
	/**
	 * ������ԡ�ͬ��������Ϣ�ķ���
	 * @param month ���յľ����·�
	 * @param day   ���յľ�������
	 */
    private void printManInformation(int month, int day) {
        System.out.println("��������ĸ�����Ϣ��\n"
        		+ "������" + name + "\n"
                + "�Ա�" + sex +"\n"
                + "���֤�ţ�" + IDNumber + "\n"
                + "���䣺" + age + "��\n"
                + "��ߣ�" + height + "cm\n"
                + "���أ�" + weight + "kg\n"
                + "���գ�" + month + "��" + day + "��");
    }
    
    /**
     * ���Ů�ԡ�ͬ��������Ϣ�ķ���
     * û��ѯ�ʺ����Ů�Ե���˽��Ϣ�����䡢��ߡ����صȣ�
     * @param month ���յľ����·�
     * @param day   ���յľ�������
     */
    private void printWomanInformation(int month, int day) {
        System.out.println("��������ĸ�����Ϣ��\n"
        		+ "������" + name + "\n"
                + "�Ա�" + sex +"\n"
                + "���֤�ţ�" + IDNumber + "\n"
                + "���գ�" + month + "��" + day + "��");
    }
	
    /**
     * ��д��toString()��������ӡ�˻���Ϣ������
     */
    @Override
    public String toString() {
        return getUserName()+"\t"+ getPassword()+"\t"+ getMemberID();
    }
}
