import java.util.*;

class Solution {
    public int minScore(int n, int[][] roads) {
        // Build the graph using an adjacency list
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] road : roads) {
            graph.get(road[0]).add(new int[] { road[1], road[2] });
            graph.get(road[1]).add(new int[] { road[0], road[2] });
        }

        int minScore = Integer.MAX_VALUE;
        boolean[] visited = new boolean[n + 1];
        Queue<Integer> queue = new LinkedList<>();

        queue.add(1);
        visited[1] = true;

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            for (int[] edge : graph.get(curr)) {
                int neighbor = edge[0];
                int distance = edge[1];

                // Update the global minimum with this edge's weight
                minScore = Math.min(minScore, distance);

                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }
        return minScore;
    }
}