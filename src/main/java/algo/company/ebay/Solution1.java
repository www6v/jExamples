package algo.company.ebay;

// 给定一个double类型的浮点数base和int类型的整数exponent。
// 求base的exponent次方。
public class Solution1 {

    public static void main(String args[]) {
        System.out.println(myPow(2,3));
        System.out.println(myPow(3,2));

        System.out.println(myPow(2,-1));
        System.out.println(myPow(0,3));
        System.out.println(myPow(0,-2));
    }

   public static double myPow(double base, int exponent) {
        if(base ==0) {
        }

     long n = exponent;
     return n >=0 ? quickMul(base, n) : 1.0/quickMul(base, -n);
   }

   public static double quickMul(double x, long n) {
     if(n==0) {
         return 1.0;
     }
     double y = quickMul(x, n/2);
     return n % 2 == 0 ? y*y : y*y*x;
   }
}
