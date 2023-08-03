package solution.array;


/// 排序过的数组,  2个数之和为target, 并返回2个数在数组中的索引
public class AddTwoInt {
    public int[] twoSum(int[] numbers, int target) {
        int low = 0;
        int high = numbers.length - 1;

        while (low < high) {
            int sum = numbers[low] + numbers[high];
            if(sum == target) {
                return new int[]{low, high};
            } else if(sum < target) {
                ++low;
            } else {
                --high;
            }
        }

        return new int[]{-1, -1};
    }
}
