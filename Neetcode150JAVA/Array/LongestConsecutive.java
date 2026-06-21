import java.util.*;

public class LongestConsecutive {

    public static int longestConsecutive(int[] nums) {
        HashSet<Integer> set = new HashSet<>();

        // Step 1: add all elements to set
        for (int num : nums) {
            set.add(num);
        }

        int res = 0;

        // Step 2: find sequences
        for (int num : set) {

            // 🔑 start only if it's the beginning
            if (!set.contains(num - 1)) {

                int curr = num;
                int streak = 1;

                while (set.contains(curr + 1)) {
                    curr++;
                    streak++;
                }

                res = Math.max(res, streak);
            }
        }

        return res;
    }

    public static void main(String[] args) {

        // Test case 1
        int[] nums1 = {100, 4, 200, 1, 3, 2};
        System.out.println("Input: " + Arrays.toString(nums1));
        System.out.println("Output: " + longestConsecutive(nums1));

        // Test case 2
        int[] nums2 = {0,3,7,2,5,8,4,6,0,1};
        System.out.println("Input: " + Arrays.toString(nums2));
        System.out.println("Output: " + longestConsecutive(nums2));

        // Test case 3
        int[] nums3 = {1,0,1,2};
        System.out.println("Input: " + Arrays.toString(nums3));
        System.out.println("Output: " + longestConsecutive(nums3));
    }
}