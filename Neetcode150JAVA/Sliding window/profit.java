public class profit {

    public static int maxProfit(int[] prices) {
        int buy = 0;
        int sell = 1;
        int maxProfit = 0;

        while (sell < prices.length) {
            if (prices[sell] > prices[buy]) {
                int profit = prices[sell] - prices[buy];
                maxProfit = Math.max(maxProfit, profit);
            } else {
                buy = sell;
            }

            sell++;
        }

        return maxProfit;
    }

    public static void main(String[] args) {
        int[] prices1 = {10, 1, 5, 6, 7, 1};
        int[] prices2 = {10, 8, 7, 5, 2};

        System.out.println(maxProfit(prices1)); // 6
        System.out.println(maxProfit(prices2)); // 0
    }
}