import java.util.*;

/**
 * LeetCode 15. 3Sum
 * Given an integer array nums, return all triplets [nums[i], nums[j], nums[k]]
 * such that i != j, i != k, j != k, and nums[i] + nums[j] + nums[k] == 0.
 *
 * Approach: Sort + Two Pointers
 * Time:  O(n^2)
 * Space: O(1) extra (excluding output and sort space)
 */
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < nums.length - 2; i++) {
            // Skip duplicate anchor values to avoid duplicate triplets
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            // If smallest possible sum from here is already > 0, no triplet can work from here on
            if (nums[i] + nums[i + 1] + nums[i + 2] > 0) break;

            // If largest possible sum with this i is still < 0, this i can't work; try next i
            if (nums[i] + nums[nums.length - 1] + nums[nums.length - 2] < 0) continue;

            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    left++;
                    right--;
                    // Skip duplicate values for left and right pointers
                    while (left < right && nums[left] == nums[left - 1]) left++;
                    while (left < right && nums[right] == nums[right + 1]) right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        return result;
    }
}

/**
 * Simple test runner — compile and run this file directly:
 *   javac ThreeSum.java
 *   java ThreeSum
 */
public class ThreeSum {
    public static void main(String[] args) {
        Solution sol = new Solution();

        int[] test1 = {-1, 0, 1, 2, -1, -4};
        System.out.println("Input: " + Arrays.toString(test1));
        System.out.println("Output: " + sol.threeSum(test1));
        System.out.println("Expected: [[-1,-1,2],[-1,0,1]] (order may vary)\n");

        int[] test2 = {0, 0, 0, 0};
        System.out.println("Input: " + Arrays.toString(test2));
        System.out.println("Output: " + sol.threeSum(test2));
        System.out.println("Expected: [[0,0,0]]\n");

        int[] test3 = {0, 1, 1};
        System.out.println("Input: " + Arrays.toString(test3));
        System.out.println("Output: " + sol.threeSum(test3));
        System.out.println("Expected: [] (no valid triplet)\n");

        int[] test4 = {};
        System.out.println("Input: " + Arrays.toString(test4));
        System.out.println("Output: " + sol.threeSum(test4));
        System.out.println("Expected: [] (empty input)\n");
    }
}