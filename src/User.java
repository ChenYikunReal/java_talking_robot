import java.util.Calendar;

/**
 * 用户信息类
 * 本次升级着重改造了文档注释，使之基本完善
 *  @author BlankSpace
 *  @version 2.1
 *  @time 2019年5月30日
 */
public class User{

    private String userName;      // 账号

    private String password;      // 密码

    private int memberID;    // 会员号

    private String name;        // 姓名

    private String sex;            // 性别

    private int age;            // 年龄

    private String IDNumber;    // 身份证号

    private double height;        // 身高

    private double weight;        // 体重

    /**
     * 用用户名、密码、会员号三个属性组成的构造器
     * 在注册成功的时候调用构造器创建用户储存到数组里面
     * @param userName 用户名
     * @param password 账户密码
     * @param memberID 用户会员号
     */
    public User(String userName,String password, int memberID) {
        this.userName = userName;
        this.password = password;
        this.memberID = memberID;
    }

    /**
     * 用于设置userName值的方法
     * @param userName 用户名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 用于访问userName值的方法
     * @return 用户名
     */
    public String getUserName() {
        return this.userName;
    }

    /**
     * 用于设置password值的方法
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 用于访问password值的方法
     * @return 密码
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * 用于设置memberID值的方法
     * @param memberID 会员号
     */
    public void setMemberID(int memberID) {
        this.memberID = memberID;
    }

    /**
     * 用于访问memberID值的方法
     * @return 会员号
     */
    public int getMemberID() {
        return this.memberID;
    }

    /**
     * 用于设置name值的方法
     * @param name 姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 用于设置sex值的方法
     * @param sextemp 性别的临时参数M/W
     */
    public void setSex(String sextemp) {
        if (sextemp.equalsIgnoreCase("M"))  {
            sex = "男";
        }  else if (sextemp.equalsIgnoreCase("W"))  {
            sex = "女";
        }
    }

    /**
     * 用于设置IDNumber值的方法
     * @param IDNumber 身份证号码
     */
    public final void setIDNumber(String IDNumber){
        this.IDNumber = IDNumber;
    }

    /**
     * 用于设置height值的方法
     * @param height 身高
     */
    public final void setHeight(double height) {
        this.height = height;
    }

    /**
     * 用于设置weight值的方法
     * @param weight 体重
     */
    public final void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * 计算年龄、生日并依据性别分别调用的方法
     */
    public void caculateAgeAndBirthday() {
        // 把输入的合法身份证号从String的子串(7-10位)转化为int
        // 子串从0开始取、左闭右开
        int year = Integer.parseInt(IDNumber.substring(6,10));
        // 把输入的合法身份证号从String的子串(11-12位)转化为int
        int month = Integer.parseInt(IDNumber.substring(10,12));
        // 把输入的合法身份证号从String的子串(13-14位)转化为int
        int day = Integer.parseInt(IDNumber.substring(12,14));
        // 获取日历时间单例
        Calendar calendar = Calendar.getInstance();
        // 获取当前时间进行处理
        age = calendar.get(Calendar.YEAR) - year;
        if (sex.equals("男")) {
            printManInformation(month, day);
        } else if (sex.equals("女")) {
            printWomanInformation(month, day);
        }
    }

    /**
     * 输出账号信息的方法
     */
    public void printUserInformation() {
        System.out.println("UserName:"+ userName+ "\tPassword:" + password + "\tMemberID:" + memberID);
    }

    /**
     * 输出男性♂同胞个人信息的方法
     * @param month 生日的具体月份
     * @param day   生日的具体日子
     */
    private void printManInformation(int month, int day) {
        System.out.println("以下是你的个人信息：\n"
                + "姓名：" + name + "\n"
                + "性别：" + sex +"\n"
                + "身份证号：" + IDNumber + "\n"
                + "年龄：" + age + "岁\n"
                + "身高：" + height + "cm\n"
                + "体重：" + weight + "kg\n"
                + "生日：" + month + "月" + day + "日");
    }

    /**
     * 输出女性♀同胞个人信息的方法
     * 没有询问和输出女性的隐私信息（年龄、身高、体重等）
     * @param month 生日的具体月份
     * @param day   生日的具体日子
     */
    private void printWomanInformation(int month, int day) {
        System.out.println("以下是你的个人信息：\n"
                + "姓名：" + name + "\n"
                + "性别：" + sex +"\n"
                + "身份证号：" + IDNumber + "\n"
                + "生日：" + month + "月" + day + "日");
    }

    /**
     * 重写的toString()方法，打印账户信息更方便
     */
    @Override
    public String toString() {
        return getUserName()+"\t"+ getPassword()+"\t"+ getMemberID();
    }

}
