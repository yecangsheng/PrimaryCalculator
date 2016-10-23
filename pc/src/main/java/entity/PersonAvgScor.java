package entity;

/**
 * Created by yufujia on 2016/10/23.
 * 说明： 用于保存用户每个等级的平均分
 */
public class PersonAvgScor implements Comparable{
    private int u_id;
    private double avgScore;
    private int rank;

    public PersonAvgScor(int u_id, double avgScore, int rank) {
        this.u_id = u_id;
        this.avgScore = avgScore;
        this.rank = rank;
    }

    public PersonAvgScor() {
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public double getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(double avgScore) {
        this.avgScore = avgScore;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int compareTo(Object o) {
        PersonAvgScor pvs = (PersonAvgScor)o;
        if (pvs.getAvgScore() > this.getAvgScore()){
            return 1;
        }else{
            return -1;
        }

    }

    @Override
    public String toString() {
        return "PersonAvgScor{" +
                "u_id=" + u_id +
                ", avgScore=" + avgScore +
                ", rank=" + rank +
                '}';
    }
}
