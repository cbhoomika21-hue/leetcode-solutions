class Solution {
    public int maxProfit(int[] prices) {
        
        int buy1 = Integer.MAX_VALUE, buy2 = Integer.MAX_VALUE;
        int profit1 = 0, profit2 = 0;

        for (int p : prices) {
            buy1 = Math.min(buy1, p);
            profit1 = Math.max(profit1, p - buy1);
            buy2 = Math.min(buy2, p - profit1);
            profit2 = Math.max(profit2, p - buy2);
        }

        return profit2;
    }
}
    
