class Solution {
    private static final int MOD = 1_000_000_007;

    public int subsequencePairCount(int[] nums) {
        // dp[i][j] stores the number of pairs of disjoint subsequences
        // with gcd equal to i and j respectively.
        // We use 201 to accommodate numbers up to 200.
        long[][] dp = new long[201][201];
        
        for (int num : nums) {
            long[][] nextDp = new long[201][201];
            
            // Copy the current states to nextDp (option: skip the current num)
            for (int i = 0; i < 201; i++) {
                System.arraycopy(dp[i], 0, nextDp[i], 0, 201);
            }
            
            // Try adding the current num to existing subsequences or starting new ones
            for (int i = 0; i < 201; i++) {
                for (int j = 0; j < 201; j++) {
                    if (dp[i][j] == 0) continue;
                    
                    // Add to first subsequence
                    int nextG1 = (i == 0) ? num : gcd(i, num);
                    nextDp[nextG1][j] = (nextDp[nextG1][j] + dp[i][j]) % MOD;
                    
                    // Add to second subsequence
                    int nextG2 = (j == 0) ? num : gcd(j, num);
                    nextDp[i][nextG2] = (nextDp[i][nextG2] + dp[i][j]) % MOD;
                }
            }
            
            // Start a new subsequence with the current num
            nextDp[num][0] = (nextDp[num][0] + 1) % MOD;
            nextDp[0][num] = (nextDp[0][num] + 1) % MOD;
            
            dp = nextDp;
        }
        
        // Sum up all pairs where the GCD of both subsequences is the same
        long count = 0;
        for (int i = 1; i < 201; i++) {
            count = (count + dp[i][i]) % MOD;
        }
        
        return (int) count;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}