package leetcode;

import org.junit.Test;

import java.util.*;

/**
 * @Description:
 * @Author RanXuCan
 * @Date 2020/9/9 20:53
 */
public class DayOne {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    /**
     * 单链表实现两大整数的加法
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(0);
        ListNode temp = result;
        int carry = 0;
        while (l1 != null && l2 != null) {
            int sum = l1.val + l2.val + carry;
            if (sum >= 10) {
                sum = sum % 10;
                carry = 1;
            } else carry = 0;
            temp.val = sum;
            temp.next = new ListNode(0);
            temp = temp.next;
            l1 = l1.next;
            l2 = l2.next;
        }
        while (l1 != null) {
            int sum = l1.val + carry;
            if (sum >= 10) {
                sum = sum % 10;
                carry = 1;
            } else carry = 0;
            temp.val = sum;
            temp.next = new ListNode(0);
            temp = temp.next;
            l1 = l1.next;
        }
        while (l2 != null) {
            int sum = l2.val + carry;
            if (sum >= 10) {
                sum = sum % 10;
                carry = 1;
            } else carry = 0;
            temp.val = sum;
            temp.next = new ListNode(0);
            temp = temp.next;
            l2 = l2.next;
        }
        if (carry != 0) {
            temp.val = carry;
        } else {
            temp = result;
            while (temp.next.next != null) {
                temp = temp.next;
            }
            temp.next = null;
        }
        return result;
    }

    @Test
    public void addTwoNumsTest() {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(5);

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

        ListNode result = addTwoNumbers(l1, l2);
        while (result != null) {
            System.out.print(result.val);
            result = result.next;
        }
    }

    /**
     * 无重复的最长子串
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        int result = 0;
        //超时解法，暴力遍历
//        HashSet<String> substrings = new HashSet<>();
//        for (int i = 0; i < s.length(); i++)
//            for (int j = i + 1; j <= s.length(); j++) {
//                substrings.add(s.substring(i, j));
//                String substr = s.substring(i, j);
//                char[] temp = substr.toCharArray();
//                HashSet<Character> tempSet = new HashSet<>();
//                for (int k = 0; k < temp.length; k++) {
//                    tempSet.add(temp[k]);
//                }
//                if (tempSet.size() == temp.length && result < tempSet.size()) {
//                    result = tempSet.size();
//                }
//            }
        //滑动窗口
        char[] chars = s.toCharArray();
        HashSet<Character> hashSet = new HashSet<>();
        for (int i = 0; i < chars.length; i++) {
            for (int j = i; j < chars.length; j++) {
                if (!hashSet.contains(chars[j])) {
                    hashSet.add(chars[j]);
                } else break;
            }
            result = Math.max(result, hashSet.size());
            hashSet.clear();
        }
        return result;
    }

    @Test
    public void lengthOfLongestSubstringTest() {
        String[] strs = {"abcabcb", "bbbbb", "pwwkew", "", " "};
        for (String s : strs) {
            System.out.println(lengthOfLongestSubstring(s));
        }
    }

    @Test
    public void hashMapTest() {
        HashMap<Integer, String> s = new HashMap<>();
        s.put(1, "a");
        s.put(2, "b");
        s.put(3, "c");
        s.put(4, "d");
        s.put(5, "e");
        s.put(6, "f");
        s.put(7, "g");
        s.put(8, "h");
        s.put(9, "a");
        s.put(10, "b");
        Set<Integer> keySet = s.keySet();
        for (Integer i : keySet) {
            System.out.println(i + "=" + s.get(i));
        }


        System.out.println(s.containsKey(3));
        System.out.println(s.containsValue("d"));
        s.remove(1);
        System.out.println(s.size());


        Set<Map.Entry<Integer, String>> entrySet = s.entrySet();
        for (Map.Entry<Integer, String> e : entrySet) {
            System.out.println(e.toString());
            System.out.println(e.getKey() + "----------->" + e.getValue());
        }
    }
}
