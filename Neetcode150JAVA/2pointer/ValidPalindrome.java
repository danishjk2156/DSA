import java.util.*;

/**
 * LeetCode 125. Valid Palindrome
 * Given a string, determine if it is a palindrome, considering only
 * alphanumeric characters and ignoring case.
 *
 * Approach: Clean string (strip non-alphanumeric, lowercase) + Two Pointers
 * Time:  O(n)
 * Space: O(n) for the cleaned string
 */
class Solution {
    public boolean isPalindrome(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                sb.append(Character.toLowerCase(c));
            }
        }
        s = sb.toString();

        int i = 0;
        int j = s.length() - 1;
        boolean palin = true;
        while (i <= j) {
            if (s.charAt(i) == s.charAt(j)) {
                i++;
                j--;
            } else {
                palin = false;
                break;
            }
        }
        return palin;
    }
}

/**
 * Simple test runner — compile and run this file directly:
 *   javac ValidPalindrome.java
 *   java ValidPalindrome
 */
public class ValidPalindrome {
    public static void main(String[] args) {
        Solution sol = new Solution();

        String test1 = "A man, a plan, a canal: Panama";
        System.out.println("Input: \"" + test1 + "\"");
        System.out.println("Output: " + sol.isPalindrome(test1));
        System.out.println("Expected: true\n");

        String test2 = "race a car";
        System.out.println("Input: \"" + test2 + "\"");
        System.out.println("Output: " + sol.isPalindrome(test2));
        System.out.println("Expected: false\n");

        String test3 = " ";
        System.out.println("Input: \"" + test3 + "\"");
        System.out.println("Output: " + sol.isPalindrome(test3));
        System.out.println("Expected: true (empty after cleaning)\n");

        String test4 = "0P";
        System.out.println("Input: \"" + test4 + "\"");
        System.out.println("Output: " + sol.isPalindrome(test4));
        System.out.println("Expected: false\n");
    }
}