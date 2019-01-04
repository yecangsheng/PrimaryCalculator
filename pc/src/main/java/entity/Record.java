package entity;


/**
 * Created by Bruce-Jiang on 2016/10/9.
 * 做题记录类，Model
 */
public class Record {
    /**
     * 序列号
     */
    private int id;

    /**
     * 用户序列号
     */
    private int uId;

    /**
     * 表达式序列号
     */
    private int eId;
    /**
     * 用户答案
     */
    private String result;
    /**
     * 时间戳,注意SQL时间与Java时间转化,使用
     * SimpleDateFormat将时间格式化为yyyy-MM-dd HH:mm:ss形式
     */
    private String date;

    /**
     * 用户是第几次做题
     */
    private int num;

    /**
     * 做题试卷等级
     */
    private int r_rank;

    public Record(){}
    public Record(int uId, int eId, String result, String date,int num, int rank){
        this.eId = eId;
        this.uId = uId;
        this.result = result;
        this.date = date;
        this.num = num;
        this.r_rank = rank;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public int geteId() {
        return eId;
    }

    public void seteId(int eId) {
        this.eId = eId;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getR_rank() {
        return r_rank;
    }

    public void setR_rank(int r_rank) {
        this.r_rank = r_rank;
    }

    @Override
    public String toString() {
        return "Record{" +
                "id=" + id +
                ", uId=" + uId +
                ", eId=" + eId +
                ", result='" + result + '\'' +
                ", date='" + date + '\'' +
                ", num=" + num +
                ", r_rank=" + r_rank +
                '}';
    }
}
