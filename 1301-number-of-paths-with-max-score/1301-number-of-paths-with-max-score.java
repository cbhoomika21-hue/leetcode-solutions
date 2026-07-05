import java.util.*;

class Solution {
    public int[] pathsWithMaxScore(List<String> board) {
        int n = board.size();
        int MOD = 1_000_000_007;

        // maxScore[i][j] stores the max score to reach cell (i, j)
        int[][] maxScore = new int[n][n];
        // paths[i][j] stores the number of ways to reach (i, j) with maxScore
        int[][] paths = new int[n][n];

        // Initialize with -1 to indicate unreachable cells
        for (int[] row : maxScore)
            Arrays.fill(row, -1);

        // Starting point (bottom-right)
        maxScore[n - 1][n - 1] = 0;
        paths[n - 1][n - 1] = 1;

        // Iterate backwards from (n-1, n-1) to (0, 0)
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (board.get(i).charAt(j) == 'X' || (i == n - 1 && j == n - 1))
                    continue;

                int currentVal = 0;
                if (board.get(i).charAt(j) != 'E') {
                    currentVal = board.get(i).charAt(j) - '0';
                }

                // Look at the 3 possible previous cells: right, down, diagonal down-right
                int[][] dirs = { { 0, 1 }, { 1, 0 }, { 1, 1 } };
                for (int[] d : dirs) {
                    int ni = i + d[0], nj = j + d[1];
                    if (ni < n && nj < n && maxScore[ni][nj] != -1) {
                        int score = maxScore[ni][nj] + currentVal;
                        if (score > maxScore[i][j]) {
                            maxScore[i][j] = score;
                            paths[i][j] = paths[ni][nj];
                        } else if (score == maxScore[i][j]) {
                            paths[i][j] = (paths[i][j] + paths[ni][nj]) % MOD;
                        }
                    }
                }
            }
        }

        // If the start (0,0) is still -1, no path was found
        if (maxScore[0][0] == -1)
            return new int[] { 0, 0 };

        return new int[] { maxScore[0][0], paths[0][0] };
    }
}