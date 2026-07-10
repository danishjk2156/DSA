import java.util.*;

public class rotated {

    public int search(int[] nums, int target) {

        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {

            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            }

            // Left half is sorted
            if (nums[left] <= nums[mid]) {

                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }

            }
            // Right half is sorted
            else {

                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }

            }
        }

        return -1;
    }

    public static void main(String[] args) {

        Solution obj = new Solution();

        int[] nums1 = {4,5,6,7,0,1,2};
        System.out.println(obj.search(nums1, 0)); // 4

        int[] nums2 = {4,5,6,7,0,1,2};
        System.out.println(obj.search(nums2, 3)); // -1

        int[] nums3 = {1};
        System.out.println(obj.search(nums3, 1)); // 0

        int[] nums4 = {1};
        System.out.println(obj.search(nums4, 0)); // -1

        int[] nums5 = {5,1,3};
        System.out.println(obj.search(nums5, 5)); // 0

        int[] nums6 = {5,1,3};
        System.out.println(obj.search(nums6, 3)); // 2
    }
}