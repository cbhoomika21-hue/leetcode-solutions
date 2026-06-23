import java.util.*;

class Solution {

    static class SparseTable {
        int n, LOG;
        int[][] mx, mn;
        int[] lg;

        SparseTable(int[] nums) {
            n = nums.length;
            LOG = 32 - Integer.numberOfLeadingZeros(n);

            mx = new int[n][LOG + 1];
            mn = new int[n][LOG + 1];
            lg = new int[n + 1];

            for (int i = 2; i <= n; i++) {
                lg[i] = lg[i / 2] + 1;
            }

            for (int i = 0; i < n; i++) {
                mx[i][0] = nums[i];
                mn[i][0] = nums[i];
            }

            for (int j = 1; j <= LOG; j++) {
                for (int i = 0; i + (1 << j) <= n; i++) {
                    mx[i][j] = Math.max(
                        mx[i][j - 1],
                        mx[i + (1 << (j - 1))][j - 1]
                    );

                    mn[i][j] = Math.min(
                        mn[i][j - 1],
                        mn[i + (1 << (j - 1))][j - 1]
                    );
                }
            }
        }

        int queryMax(int l, int r) {
            int k = lg[r - l + 1];
            return Math.max(
                mx[l][k],
                mx[r - (1 << k) + 1][k]
            );
        }

        int queryMin(int l, int r) {
            int k = lg[r - l + 1];
            return Math.min(
                mn[l][k],
                mn[r - (1 << k) + 1][k]
            );
        }

        long value(int l, int r) {
            return (long) queryMax(l, r) - queryMin(l, r);
        }
    }

    public long maxTotalValue(int[] nums, int k) {
        int n = nums.length;

        SparseTable st = new SparseTable(nums);

        PriorityQueue<long[]> pq =
            new PriorityQueue<>((a, b) -> Long.compare(b[0], a[0]));

        for (int l = 0; l < n; l++) {
            pq.offer(new long[]{
                st.value(l, n - 1),
                l,
                n - 1
            });
        }

        long ans = 0;

        for (int i = 0; i < k; i++) {
            long[] cur = pq.poll();

            long val = cur[0];
            int l = (int) cur[1];
            int r = (int) cur[2];

            ans += val;

            if (r > l) {
                pq.offer(new long[]{
                    st.value(l, r - 1),
                    l,
                    r - 1
                });
            }
        }

        return ans;
    }
}