import java.util.*;

class Solution {
    private static final int MOD = 1_000_000_007;

    public int xorAfterQueries(int[] nums, int[][] queries) {
        int n = nums.length;
        int B = (int) Math.sqrt(n);

        List<int[]>[] smallKQueries = new ArrayList[B + 1];
        for (int i = 0; i <= B; i++)
            smallKQueries[i] = new ArrayList<>();

        for (int[] q : queries) {
            int l = q[0], r = q[1], k = q[2], v = q[3];
            if (k > B) {
                for (int i = l; i <= r; i += k) {
                    nums[i] = (int) ((1L * nums[i] * v) % MOD);
                }
            } else {
                smallKQueries[k].add(new int[] { l, r, v });
            }
        }

        for (int k = 1; k <= B; k++) {
            if (smallKQueries[k].isEmpty())
                continue;

            long[] diff = new long[n + 1];
            Arrays.fill(diff, 1L);

            for (int[] q : smallKQueries[k]) {
                int l = q[0], r = q[1], v = q[2];
                diff[l] = (diff[l] * v) % MOD;
                // Calculate the first index outside the range [l, r] with step k
                int stop = l + ((r - l) / k + 1) * k;
                if (stop < n) {
                    diff[stop] = (diff[stop] * power(v, MOD - 2)) % MOD;
                }
            }

            // Propagate multipliers along the stride k
            for (int i = k; i < n; i++) {
                diff[i] = (diff[i] * diff[i - k]) % MOD;
            }

            for (int i = 0; i < n; i++) {
                nums[i] = (int) ((1L * nums[i] * diff[i]) % MOD);
            }
        }

        int xorSum = 0;
        for (int num : nums)
            xorSum ^= num;
        return xorSum;
    }

    private long power(long base, long exp) {
        long res = 1;
        base %= MOD;
        while (exp > 0) {
            if (exp % 2 == 1)
                res = (res * base) % MOD;
            base = (base * base) % MOD;
            exp /= 2;
        }
        return res;
    }
}