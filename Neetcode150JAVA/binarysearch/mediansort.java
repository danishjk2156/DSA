import java.util.*;

public class mediansort {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        // Always binary search on the smaller array
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int m = nums1.length;
        int n = nums2.length;

        int left = 0;
        int right = m;

        while (left <= right) {

            int cut1 = left + (right - left) / 2;
            int cut2 = (m + n + 1) / 2 - cut1;

            int left1 = (cut1 == 0) ? Integer.MIN_VALUE : nums1[cut1 - 1];
            int right1 = (cut1 == m) ? Integer.MAX_VALUE : nums1[cut1];

            int left2 = (cut2 == 0) ? Integer.MIN_VALUE : nums2[cut2 - 1];
            int right2 = (cut2 == n) ? Integer.MAX_VALUE : nums2[cut2];

            // Correct partition
            if (left1 <= right2 && left2 <= right1) {

                // Odd total length
                if ((m + n) % 2 == 1) {
                    return Math.max(left1, left2);
                }

                // Even total length
                return (Math.max(left1, left2) +
                        Math.min(right1, right2)) / 2.0;
            }

            // Move left
            else if (left1 > right2) {
                right = cut1 - 1;
            }

            // Move right
            else {
                left = cut1 + 1;
            }
        }

        return 0;
    }

    public static void main(String[] args) {

        Solution obj = new Solution();

        int[] a = {1, 3};
        int[] b = {2};

        System.out.println(obj.findMedianSortedArrays(a, b)); // 2.0

        int[] c = {1, 2};
        int[] d = {3, 4};

        System.out.println(obj.findMedianSortedArrays(c, d)); // 2.5
    }
}