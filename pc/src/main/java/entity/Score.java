package entity;

/**
 * Created by shen on 2016/10/18.
 */
public class Score {
    private String period;  //其实就是x轴的一个标度
    private String score; //y轴的数字

    public Score(String period, String score) {
        this.period = period;
        this.score = score;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
