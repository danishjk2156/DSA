import java.util.Stack;

public class LargestRectangleInHistogram {

    public static int largestRectangleArea(int[] heights) {

        // Stack stores INDICES of bars, not heights.
        // Heights corresponding to these indices are always in increasing order.
        Stack<Integer> stack = new Stack<>();

        int maxArea = 0;
        int n = heights.length;

        // Go one step beyond the array.
        // At i == n, we use a height of 0 to force all remaining bars to pop.
        for (int i = 0; i <= n; i++) {

            // Current height
            int currHeight = (i == n) ? 0 : heights[i];

            // If current bar is smaller than the top bar,
            // then the top bar's rectangle ends here.
            while (!stack.isEmpty() &&
                    currHeight < heights[stack.peek()]) {

                // Height of the rectangle
                int height = heights[stack.pop()];

                // Left boundary
                // If stack is empty, no smaller element exists on left.
                int left = stack.isEmpty() ? -1 : stack.peek();

                // Right boundary is current index (i)
                // Rectangle spans from left+1 to i-1
                int width = i - left - 1;

                // Area using popped bar as the smallest height
                int area = height * width;

                // Update maximum
                maxArea = Math.max(maxArea, area);
            }

            // Current bar hasn't found a smaller bar on the right yet.
            stack.push(i);
        }

        return maxArea;
    }

    public static void main(String[] args) {

        int[] heights = {2, 1, 5, 6, 2, 3};

        System.out.println("Largest Rectangle Area = "
                + largestRectangleArea(heights));
    }
}