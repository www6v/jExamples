package algorithm.company.qiniu;

/// 删除数组中的重复项
public class Solution {

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
}
