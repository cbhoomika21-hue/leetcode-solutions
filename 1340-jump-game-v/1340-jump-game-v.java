import java.util.Arrays;

class Solution {
    private int[] memo;

    public int maxJumps(int[] arr, int d) {
        int n = arr.length;
        memo = new int[n];
        Arrays.fill(memo, -1);
        
        int maxJumps = 0;
        // Try starting from every possible index
        for (int i = 0; i < n; i++) {
            maxJumps = Math.max(maxJumps, dfs(arr, d, i));
        }
        
        return maxJumps;
    }

    private int dfs(int[] arr, int d, int curr) {
        if (memo[curr] != -1) {
            return memo[curr];
        }
        
        int maxReach = 1;
        int n = arr.length;
        
        // Check jumps to the right
        for (int x = 1; x <= d && curr + x < n; x++) {
            if (arr[curr] > arr[curr + x]) {
                maxReach = Math.max(maxReach, 1 + dfs(arr, d, curr + x));
            } else {
                // Cannot jump further if we hit a value >= arr[curr]
                break;
            }
        }
        
        // Check jumps to the left
        for (int x = 1; x <= d && curr - x >= 0; x++) {
            if (arr[curr] > arr[curr - x]) {
                maxReach = Math.max(maxReach, 1 + dfs(arr, d, curr - x));
            } else {
                // Cannot jump further if we hit a value >= arr[curr]
                break;
            }
        }
        
        return memo[curr] = maxReach;
    }
}