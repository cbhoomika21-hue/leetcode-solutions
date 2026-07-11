class Solution {
    public int countCompleteComponents(int n, int[][] edges) {
        // Create an adjacency list to represent the graph
        java.util.List<java.util.List<Integer>> adj = new java.util.ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new java.util.ArrayList<>());
        }
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        boolean[] visited = new boolean[n];
        int completeComponents = 0;

        // Traverse each component
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                java.util.List<Integer> component = new java.util.ArrayList<>();
                java.util.Queue<Integer> queue = new java.util.LinkedList<>();
                
                queue.add(i);
                visited[i] = true;
                
                while (!queue.isEmpty()) {
                    int u = queue.poll();
                    component.add(u);
                    
                    for (int v : adj.get(u)) {
                        if (!visited[v]) {
                            visited[v] = true;
                            queue.add(v);
                        }
                    }
                }
                
                // Check if the component is complete:
                // A component with 'm' nodes is complete if it has m * (m - 1) / 2 edges.
                // Alternatively, every node in the component must have degree = m - 1.
                int m = component.size();
                boolean isComplete = true;
                for (int node : component) {
                    if (adj.get(node).size() != m - 1) {
                        isComplete = false;
                        break;
                    }
                }
                
                if (isComplete) {
                    completeComponents++;
                }
            }
        }
        
        return completeComponents;
    }
}