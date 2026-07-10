import java.util.*;

class banana {

    public int minEatingSpeed(int[] piles, int h) {

        int left = 1;
        int right = 0;

        // Find the maximum pile
        for (int pile : piles) {
            right = Math.max(right, pile);
        }

        // Binary Search
        while (left < right) {

            int mid = left + (right - left) / 2;

            int hours = 0;

            // Calculate total hours needed at speed = mid
            for (int pile : piles) {
                hours += (pile + mid - 1) / mid; // Ceiling division
            }

            if (hours <= h) {
                right = mid;      // Try a smaller valid speed
            } else {
                left = mid + 1;   // Need a larger speed
            }
        }

        return left;
    }
}

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of piles: ");
        int n = sc.nextInt();

        int[] piles = new int[n];

        System.out.println("Enter the piles:");

        for (int i = 0; i < n; i++) {
            piles[i] = sc.nextInt();
        }

        System.out.print("Enter h (hours): ");
        int h = sc.nextInt();

        Solution sol = new Solution();

        int answer = sol.minEatingSpeed(piles, h);

        System.out.println("\nMinimum Eating Speed = " + answer);

        sc.close();
    }
}