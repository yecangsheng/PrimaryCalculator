package util.calculator;

/**
 * Created by Bruce-Jiang on 2016/9/29.
 * 抽象工具类，封装了多个不同的工具方法
 */
public class CommonTools {
    /**
     * 求两个数的最大公约数，欧几里得算法
     *
     * @param a
     * @param b
     * @return
     */
    public static int gcd(int a, int b) { // 求a和b的最大公约数
        if (a < b) {
            int c = a;
            a = b;
            b = c;
        }
        int r = a % b;
        while (r != 0) {
            a = b;
            b = r;
            r = a % b;
        }
        return b;
    }
}
