import java.util.*;

public class Solution {

    public boolean searchMatrix(int[][] matrix, int target) {

        int r = matrix.length;
        int c = matrix[0].length;

        int left = 0;
        int right = r * c - 1;

        while (left <= right) {

            int mid = left + (right - left) / 2;

            int row = mid / c;
            int col = mid % c;

            if (matrix[row][col] == target) {
                return true;
            } else if (matrix[row][col] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return false;
    }

    public static void main(String[] args) {

        Solution obj = new Solution();

        int[][] matrix = {
                {1, 3, 5, 7},
                {10, 11, 16, 20},
                {23, 30, 34, 60}
        };

        System.out.println(obj.searchMatrix(matrix, 3));   // true
        System.out.println(obj.searchMatrix(matrix, 13));  // false
        System.out.println(obj.searchMatrix(matrix, 60));  // true
        System.out.println(obj.searchMatrix(matrix, 1));   // true
        System.out.println(obj.searchMatrix(matrix, 100)); // false
    }
}