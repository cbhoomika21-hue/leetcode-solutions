import java.util.*;

class Solution {
    public long minimumTotalDistance(List<Integer> robot, int[][] factory) {
        Collections.sort(robot);
        Arrays.sort(factory, Comparator.comparingInt(a -> a[0]));
        
        int m = robot.size();
        int n = factory.length;
        
        // dp[i][j] is the min cost to repair first i robots using first j factories
        long[][] dp = new long[m + 1][n + 1];
        
        // Initialize with a large value
        for (int i = 0; i <= m; i++) {
            Arrays.fill(dp[i], Long.MAX_VALUE / 2);
        }
        
        // Base case: 0 robots cost 0 to repair
        for (int j = 0; j <= n; j++) {
            dp[0][j] = 0;
        }
        
        for (int j = 1; j <= n; j++) {
            int pos = factory[j - 1][0];
            int limit = factory[j - 1][1];
            
            for (int i = 0; i <= m; i++) {
                // Option 1: Don't use this factory
                dp[i][j] = dp[i][j - 1];
                
                // Option 2: Use this factory to repair k robots
                long dist = 0;
                for (int k = 1; k <= limit && i - k >= 0; k++) {
                    dist += Math.abs(robot.get(i - k) - pos);
                    dp[i][j] = Math.min(dp[i][j], dp[i - k][j - 1] + dist);
                }
            }
        }
        
        return dp[m][n];
    }
}