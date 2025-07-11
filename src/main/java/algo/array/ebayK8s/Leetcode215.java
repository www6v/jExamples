package algo.company.ebayK8s;

import java.util.Random;


///  题目: array中第n大的数字
///  结果不太对[todo]
public class Solution {

    public static void main(String args[]) {
       int[]  nums = {5,39, 7, 56, 23 , 3};
       int n = 2;

       int result = findNLargest(nums, n);

        for (int i = 0; i < nums.length; i++) {
             System.out.println( nums[i] );
        }

       System.out.println(result);
    }
    static Random random = new Random();

    public static int findNLargest(int[] nums, int n) {
       return quickSelect(nums, 0, nums.length-1, nums.length- n);
    }

    public static int quickSelect(int[] a, int l, int r, int index) {
        int q = randomPartition(a,l,r);
        if(q==index) {
            return a[q];
        } else {
            return q<index?quickSelect(a, q+1,r,index):quickSelect(a,l,q-1,index);
        }
    }

    public static int randomPartition(int[]a, int l, int r){
        int i = random.nextInt(r-l+1) + l;
        swap(a,i,r);
        return partition(a,l,r);
    }

    public static int partition(int[]a,int l, int r) {
       int x = a[r];
       int i = l-1;
        for (int j = l; j < r; ++j) {
            if(a[j]<=x) {
                swap(a, i+1,r);
            }
        }
        swap(a, i+1, r);
        return i+1;
    }

    public static void swap(int[]a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
