package entity;

/**
 * Created by Bruce-Jiang on 2016/10/2.
 * User表的对应实体类
 * @Vsersion V0.0.1
 */
public class User {
    /**
     * 用户编号
     */
    private int id;
    /**
     * 用户注册邮箱
     */
    private String uMail;
    /**
     * 用户昵称
     */
    private String uNickname;
    /**
     * 用户密码
     */
    private String uPassword;
    /**
     * 用户身份
     */
    private int uIdentity;


    public User(){}
    public User(String uMail, String uPassword,int uIdentity){
        this.uMail = uMail;
        this.uPassword = uPassword;
        this.uIdentity = uIdentity;
    }
    public User(String uMail, String uNickname, String uPassword, int uIdentity){
        this.uMail = uMail;
        this.uNickname = uNickname;
        this.uPassword = uPassword;
        this.uIdentity = uIdentity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getuMail() {
        return uMail;
    }

    public void setuEmail(String uMail) {
        this.uMail = uMail;
    }

    public String getuNickname() {
        return uNickname;
    }

    public void setuNickname(String uNickname) {
        this.uNickname = uNickname;
    }

    public String getuPassword() {
        return uPassword;
    }

    public void setuPassword(String uPassword) {
        this.uPassword = uPassword;
    }

    public int getuIdentity() {
        return uIdentity;
    }

    public void setuIdentity(int uIdentity) {
        this.uIdentity = uIdentity;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", uMail='" + uMail + '\'' +
                ", uNickname='" + uNickname + '\'' +
                ", uPassword='" + uPassword + '\'' +
                ", uIdentity=" + uIdentity +
                '}';
    }
}
