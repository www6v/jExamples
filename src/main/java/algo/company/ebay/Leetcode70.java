package algo.company.ebay;

/// DP
/// 青蛙跳台阶
/// f(n) = f(n-1) + f(n-2)
public class Leetcode70 {
    public int numWays(int n) {
       int a = 1;
       int b = 1;
       int sum;
        for (int i = 0; i < n; i++) {
            sum = a + b;
            a = b;
            b = sum;
        }

        return a;
    }
}
