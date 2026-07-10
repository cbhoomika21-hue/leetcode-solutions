import java.util.*;

class Solution {
    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        // Step 1: Create nodes with indices and sort them by value
        Integer[] p = new Integer[n];
        for (int i = 0; i < n; i++)
            p[i] = i;
        Arrays.sort(p, Comparator.comparingInt(i -> nums[i]));

        // Step 2: Binary Lifting table
        // up[k][i] stores the node reached after 2^k jumps from node p[i]
        int maxLog = 18;
        int[][] up = new int[maxLog][n];

        // Find the right-most reachable node for each index using two pointers
        int right = 0;
        for (int i = 0; i < n; i++) {
            while (right < n && nums[p[right]] - nums[p[i]] <= maxDiff) {
                right++;
            }
            up[0][i] = right - 1;
        }

        // Fill the sparse table
        for (int k = 1; k < maxLog; k++) {
            for (int i = 0; i < n; i++) {
                up[k][i] = up[k - 1][up[k - 1][i]];
            }
        }

        // Map original node index to its position in the sorted array
        int[] pos = new int[n];
        for (int i = 0; i < n; i++)
            pos[p[i]] = i;

        int[] results = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int u = pos[queries[i][0]];
            int v = pos[queries[i][1]];
            if (u > v) {
                int temp = u;
                u = v;
                v = temp;
            }
            if (u == v) {
                results[i] = 0;
                continue;
            }

            // Find distance using binary lifting
            int dist = 0;
            int curr = u;
            for (int k = maxLog - 1; k >= 0; k--) {
                if (up[k][curr] < v) {
                    curr = up[k][curr];
                    dist += (1 << k);
                }
            }

            // Check one last jump
            if (up[0][curr] >= v) {
                results[i] = dist + 1;
            } else {
                results[i] = -1;
            }
        }
        return results;
    }
}
