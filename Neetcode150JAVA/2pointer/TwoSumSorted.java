import java.util.*;

/**
 * LeetCode 167. Two Sum II - Input Array Is Sorted
 * Given a 1-indexed sorted array, find two numbers that add up to target.
 * Return their 1-indexed positions as [index1, index2].
 *
 * Approach: Two Pointers (array is already sorted, so this works directly)
 * Time:  O(n)
 * Space: O(1)
 */
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int i = 0;
        int j = numbers.length - 1;
        while (i <= j) {
            int sum = numbers[i] + numbers[j];
            if (sum == target) {
                return new int[]{i + 1, j + 1}; // 1-indexed
            } else if (sum > target) {
                j--;
            } else {
                i++;
            }
        }
        return new int[]{-1, -1};
    }
}

/**
 * Simple test runner — compile and run this file directly:
 *   javac TwoSumSorted.java
 *   java TwoSumSorted
 */
public class TwoSumSorted {
    public static void main(String[] args) {
        Solution sol = new Solution();

        int[] numbers1 = {2, 7, 11, 15};
        int target1 = 9;
        System.out.println("Input: " + Arrays.toString(numbers1) + ", target = " + target1);
        System.out.println("Output: " + Arrays.toString(sol.twoSum(numbers1, target1)));
        System.out.println("Expected: [1, 2]\n");

        int[] numbers2 = {2, 3, 4};
        int target2 = 6;
        System.out.println("Input: " + Arrays.toString(numbers2) + ", target = " + target2);
        System.out.println("Output: " + Arrays.toString(sol.twoSum(numbers2, target2)));
        System.out.println("Expected: [1, 3]\n");

        int[] numbers3 = {-1, 0};
        int target3 = -1;
        System.out.println("Input: " + Arrays.toString(numbers3) + ", target = " + target3);
        System.out.println("Output: " + Arrays.toString(sol.twoSum(numbers3, target3)));
        System.out.println("Expected: [1, 2]\n");

        int[] numbers4 = {1, 2, 3};
        int target4 = 100;
        System.out.println("Input: " + Arrays.toString(numbers4) + ", target = " + target4);
        System.out.println("Output: " + Arrays.toString(sol.twoSum(numbers4, target4)));
        System.out.println("Expected: [-1, -1] (no valid pair)\n");
    }
}