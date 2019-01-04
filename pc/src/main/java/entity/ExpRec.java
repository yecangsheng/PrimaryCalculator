package entity;

/**
 * Created by Bruce-Jiang on 2016/10/17.
 * 联合实体
 */
public class ExpRec {
    /**
     * 用户编号
     */
    private int uId;

    /**
     * 表达式ID
     */
    private int eId;

    /**
     * 表达式
     */
    private String exp;

    /**
     * 用户给出的答案
     */
    private String result;

    /**
     * 正确答案
     */
    private String pResult;

    /**
     * 做题次数
     */
    private int num;

    public ExpRec(){}
    public ExpRec(int uId, int eId,String exp,String result,String pResult, int num){
        this.eId = eId;
        this.uId = uId;
        this.exp = exp;
        this.pResult = pResult;
        this.result = result;
        this.num = num;
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

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getpResult() {
        return pResult;
    }

    public void setpResult(String pResult) {
        this.pResult = pResult;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
