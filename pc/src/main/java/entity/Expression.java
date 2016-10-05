package entity;

/**
 * Created by Bruce-Jiang on 2016/10/4.
 * Model层，表达式
 */
public class Expression {
    /**
     * 主键，记录编号
     */
    private int id;
    /**
     * 表达式
     */
    private String eExpre;
    /**
     * 表达式结果
     */
    private String eResult;
    /**
     *表达式难度等级
     */
    private int eRank;

    public Expression(){

    }

    public Expression(String eExpre, String eResult, int eRank){
        this.eExpre = eExpre;
        this.eResult = eResult;
        this.eRank = eRank;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String geteExpre() {
        return eExpre;
    }

    public void seteExpre(String eExpre) {
        this.eExpre = eExpre;
    }

    public String geteResult() {
        return eResult;
    }

    public void seteResult(String eResult) {
        this.eResult = eResult;
    }

    public int geteRank() {
        return eRank;
    }

    public void seteRank(int eRank) {
        this.eRank = eRank;
    }
}
