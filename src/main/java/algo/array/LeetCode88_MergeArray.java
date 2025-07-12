package algo.array;

import java.util.Arrays;

/// 合并有序数组  LeetCode 88
public class LeetCode88_MergeArray {

}

// 方法一：直接合并后排序
class MergeArray1 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        for (int i = 0; i != n; ++i) {
            nums1[m + i] = nums2[i];
        }
        Arrays.sort(nums1);
    }
}

// 方法二：双指针
class MergeArray2 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p1 = 0, p2 = 0;
        int[] sorted = new int[m + n];
        int cur;
        while (p1 < m || p2 < n) {
            if (p1 == m) {
                cur = nums2[p2++];
            } else if (p2 == n) {
                cur = nums1[p1++];
            } else if (nums1[p1] < nums2[p2]) {
                cur = nums1[p1++];
            } else {
                cur = nums2[p2++];
            }
            sorted[p1 + p2 - 1] = cur;
        }
        for (int i = 0; i != m + n; ++i) {
            nums1[i] = sorted[i];
        }
    }
}

class MergeArray2P1 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int k = m + n;
        int[] tmp = new int[k];
        int nums1Index = 0;
        int nums2Index = 0;
        for (int index = 0; index < k; index++) {
            if (nums1Index >= m) {
                tmp[index] = nums2[nums2Index++];
            } else if (nums2Index >= n) {
                tmp[index] = nums1[nums1Index++];
            } else if (nums1[nums1Index] < nums2[nums2Index]) {
                tmp[index] = nums1[nums1Index++];
            } else if (nums1[nums1Index] > nums2[nums2Index]) {
                tmp[index] = nums2[nums2Index++];
            }
        }

        for (int i = 0; i < k; i++) {
            nums1[i] = tmp[i];
        }
    }
}

/// 方法三：逆向双指针
class MergeArray3 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1, p2 = n - 1;
        int tail = m + n - 1;
        int cur;
        while (p1 >= 0 || p2 >= 0) {
            if (p1 == -1) {
                cur = nums2[p2--];
            } else if (p2 == -1) {
                cur = nums1[p1--];
            } else if (nums1[p1] > nums2[p2]) {
                cur = nums1[p1--];
            } else {
                cur = nums2[p2--];
            }
            nums1[tail--] = cur;
        }
    }
}


