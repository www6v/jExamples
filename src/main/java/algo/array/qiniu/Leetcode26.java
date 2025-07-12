package algo.array.qiniu;

/// Leetcode26.   删除数组中的重复项, 空间复杂度为O(1)
public class Leetcode26 {

    public int removeDuplicatedElem(String[] nums) {
        int n = nums.length;
        int  j = 0;
        for (int i = 0; i < n; i++) {
            if(nums[i] != nums[j]) {
                nums[++j] = nums[i];
            }
        }

        return j+1;
    }

    /// 官方题解
    public int removeDuplicates(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int fast = 1, slow = 1;
        while (fast < n) {
            if (nums[fast] != nums[fast - 1]) {
                nums[slow] = nums[fast];
                ++slow;
            }
            ++fast;
        }
        return slow;
    }
}
