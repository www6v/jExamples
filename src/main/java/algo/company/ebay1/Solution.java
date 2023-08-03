package algo.company.ebay1;

/**
 * // Given an array of integers nums and an integer target ,
 * // return indices of the two numbers such that they add up to target .
 * // [2,3,1,5] 6  [2,3]
 */
public class Solution {

    public static void main(String args[]) {
        int [] a = new int[]{2,3,1,5};
        int target = 6;
        twoSum1(a, target);
    }

    public static int[] twoSum1(int[] numbers, int target) {
        for(int i=0; i <numbers.length; i++ ) {
            for(int j=1; j <numbers.length; j++) {
                if(i == j) {
                    continue;
                }
                int sum = numbers[i] + numbers[j];
                if(sum == target) {
                    return new int[]{i, j};
                }
            }
        }

        return new int[]{-1, -1};
    }
}
