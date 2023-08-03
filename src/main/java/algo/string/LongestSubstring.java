package algo.string;

// Leetcode 3. 无重复字符的最长子串
// 链接：https://leetcode.cn/problems/longest-substring-without-repeating-characters/solution/wu-zhong-fu-zi-fu-de-zui-chang-zi-chuan-cshi-xian-/
public class LongestSubstring  {
    public static void main(String args[]) {
        String s = "abcabcbb";
        String s1 = "pwwkew";

        System.out.println(lengthOfLongestSubstring(s));
        System.out.println(lengthOfLongestSubstring(s1));
    }

    static int lengthOfLongestSubstring(String s)
    {
        //s[start,end) 前面包含 后面不包含
        int start = 0, end = 0, length = 0, result =0;
        int sSize = s.length();
        while (end < sSize)
        {
            char tmpChar = s.charAt(end);
            for (int index = start; index < end; index++)
            {
                if (tmpChar == s.charAt(index))
                {
                    start = index + 1;
                    length = end - start;
                    break;
                }
            }

            end++;
            length++;
            result = Math.max(result, length);
        }
        return result;
    }
};




