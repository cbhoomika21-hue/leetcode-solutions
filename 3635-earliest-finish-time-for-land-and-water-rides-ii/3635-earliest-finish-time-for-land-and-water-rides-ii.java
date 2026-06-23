import java.util.*;

class Solution {
    public int earliestFinishTime(int[] landStartTime, int[] landDuration, int[] waterStartTime, int[] waterDuration) {
        return (int) Math.min(solve(landStartTime, landDuration, waterStartTime, waterDuration),
                              solve(waterStartTime, waterDuration, landStartTime, landDuration));
    }

    private long solve(int[] start1, int[] dur1, int[] start2, int[] dur2) {
        int n = start1.length;
        int m = start2.length;
        
        long[][] rides1 = new long[n][2];
        for (int i = 0; i < n; i++) {
            rides1[i][0] = start1[i];
            rides1[i][1] = dur1[i];
        }
        Arrays.sort(rides1, Comparator.comparingLong(a -> a[0]));

        long[][] rides2 = new long[m][2];
        for (int i = 0; i < m; i++) {
            rides2[i][0] = start2[i];
            rides2[i][1] = dur2[i];
        }
        Arrays.sort(rides2, Comparator.comparingLong(a -> a[0]));

        long[] minDur2 = new long[m];
        minDur2[0] = rides2[0][1];
        for (int i = 1; i < m; i++) {
            minDur2[i] = Math.min(minDur2[i - 1], rides2[i][1]);
        }

        long[] minFinish2 = new long[m];
        minFinish2[m - 1] = rides2[m - 1][0] + rides2[m - 1][1];
        for (int i = m - 2; i >= 0; i--) {
            minFinish2[i] = Math.min(minFinish2[i + 1], rides2[i][0] + rides2[i][1]);
        }

        long minTotalTime = Long.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            long finish1 = rides1[i][0] + rides1[i][1];
            
            int low = 0, high = m - 1;
            int idx = m;
            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (rides2[mid][0] > finish1) {
                    idx = mid;
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }

            if (idx > 0) {
                minTotalTime = Math.min(minTotalTime, finish1 + minDur2[idx - 1]);
            }
            if (idx < m) {
                minTotalTime = Math.min(minTotalTime, minFinish2[idx]);
            }
        }

        return minTotalTime;
    }
}