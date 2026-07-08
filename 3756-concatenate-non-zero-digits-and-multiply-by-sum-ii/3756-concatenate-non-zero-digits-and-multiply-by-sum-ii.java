class Solution {
    public int[] sumAndMultiply(String s, int[][] queries) {
        int m = s.length();
        int MOD = 1_000_000_007;

        // Prefix arrays for sum of non-zero digits and the concatenated number
        long[] sumPrefix = new long[m + 1];
        long[] concatPrefix = new long[m + 1];
        int[] nonZeroCount = new int[m + 1];

        // Precompute powers of 10
        long[] pow10 = new long[m + 1];
        pow10[0] = 1;
        for (int i = 1; i <= m; i++) {
            pow10[i] = (pow10[i - 1] * 10) % MOD;
        }

        // Fill prefix arrays
        for (int i = 0; i < m; i++) {
            int digit = s.charAt(i) - '0';
            sumPrefix[i + 1] = sumPrefix[i];
            concatPrefix[i + 1] = concatPrefix[i];
            nonZeroCount[i + 1] = nonZeroCount[i];

            if (digit > 0) {
                sumPrefix[i + 1] = (sumPrefix[i + 1] + digit) % MOD;
                concatPrefix[i + 1] = (concatPrefix[i] * 10 + digit) % MOD;
                nonZeroCount[i + 1]++;
            }
        }

        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int l = queries[i][0];
            int r = queries[i][1];

            // Extract the range sum and count of non-zero digits
            long currentSum = (sumPrefix[r + 1] - sumPrefix[l] + MOD) % MOD;
            int count = nonZeroCount[r + 1] - nonZeroCount[l];

            if (count == 0) {
                ans[i] = 0;
            } else {
                // Calculate the concatenated number x for s[l..r]
                // Formula: (prefix[r+1] - prefix[l] * 10^(count)) % MOD
                long x = (concatPrefix[r + 1] - (concatPrefix[l] * pow10[count]) % MOD + MOD) % MOD;
                ans[i] = (int) ((x * currentSum) % MOD);
            }
        }

        return ans;
    }
}
