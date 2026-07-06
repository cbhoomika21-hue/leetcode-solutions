import java.util.Arrays;

class Solution {
    public int removeCoveredIntervals(int[][] intervals) {
        // Sort by start time ascending, 
        // if start times are equal, sort by end time descending
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1];
            }
            return a[0] - b[0];
        });

        int count = 0;
        int maxEnd = 0;

        for (int[] interval : intervals) {
            // If the current interval ends after the maxEnd seen so far,
            // it is not covered by any previous interval.
            if (interval[1] > maxEnd) {
                count++;
                maxEnd = interval[1];
            }
        }

        return count;
    }
}