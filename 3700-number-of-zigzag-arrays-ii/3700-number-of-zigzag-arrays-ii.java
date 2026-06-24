class Solution {
    private static final long MOD = 1_000_000_007L;

    public int zigZagArrays(int n, int l, int r) {
        int m = r - l + 1;
        if (n == 1) return m;
        
        int states = 2 * m;
        long[][] T = new long[states][states];

        // T[to][from] = 1 if transition is possible
        for (int x = 0; x < m; x++) {
            int downState = x;     // Current is x, next must be < x (down)
            int upState = x + m;   // Current is x, next must be > x (up)

            // From UP state (x), next must be Y > x. Y will be in DOWN state.
            for (int y = x + 1; y < m; y++) {
                T[y][upState] = 1;
            }
            // From DOWN state (x), next must be Y < x. Y will be in UP state.
            for (int y = 0; y < x; y++) {
                T[y + m][downState] = 1;
            }
        }

        long[][] T_pow = power(T, n - 1);
        
        // Initial vector: all starting values are valid (as either first in Up or Down)
        long[] startVec = new long[states];
        for (int i = 0; i < m; i++) {
            startVec[i] = 1;     // Starts as a 'down' move
            startVec[i + m] = 1; // Starts as an 'up' move
        }

        long[] finalVec = multiplyVec(T_pow, startVec);
        long ans = 0;
        for (long val : finalVec) ans = (ans + val) % MOD;
        return (int) ans;
    }

    private long[][] power(long[][] a, int b) {
        int n = a.length;
        long[][] res = new long[n][n];
        for (int i = 0; i < n; i++) res[i][i] = 1;
        while (b > 0) {
            if ((b & 1) == 1) res = multiply(res, a);
            a = multiply(a, a);
            b >>= 1;
        }
        return res;
    }

    private long[][] multiply(long[][] a, long[][] b) {
        int n = a.length;
        long[][] res = new long[n][n];
        for (int i = 0; i < n; i++) {
            for (int k = 0; k < n; k++) {
                if (a[i][k] == 0) continue;
                for (int j = 0; j < n; j++) {
                    res[i][j] = (res[i][j] + a[i][k] * b[k][j]) % MOD;
                }
            }
        }
        return res;
    }

    private long[] multiplyVec(long[][] mat, long[] vec) {
        int n = mat.length;
        long[] res = new long[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                res[i] = (res[i] + mat[i][j] * vec[j]) % MOD;
            }
        }
        return res;
    }
}