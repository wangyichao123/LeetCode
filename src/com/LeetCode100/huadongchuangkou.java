package com.LeetCode100;
import org.junit.Test;
import java.util.*;
public class huadongchuangkou {
    @Test
    public void test(){  //3. 无重复字符的最长子串
        System.out.println(lengthOfLongestSubstring("pwwkew"));
    }

    public int lengthOfLongestSubstring(String s) {
        Set<Character> str=new HashSet<>();
        int start=-1;
        int res=0;
        for(int i=0;i<s.length();i++){
            if(i!=0){
                str.remove(s.charAt(i-1));  //窗口左侧向右移一位
            }
            while (start+1<s.length()&&!str.contains(s.charAt(start+1))){  //找到重复的位置 不停的滑动窗口右侧
                str.add(s.charAt(start+1));
                start++;
            }
            res=Math.max(res,start-i+1);
        }
        return res;
    }

    public static void main(String[] args) {
        // write your code here

    }
}
