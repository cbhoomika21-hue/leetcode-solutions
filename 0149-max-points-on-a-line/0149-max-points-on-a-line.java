import java.util.*;

class Solution {
    public int maxPoints(int[][] points) {
        int n = points.length;
        if (n <= 2) return n;
        
        int max = 0;
        
        for (int i = 0; i < n; i++) {
            Map<String, Integer> slopeMap = new HashMap<>();
            int currentMax = 0;
            
            for (int j = i + 1; j < n; j++) {
                int dx = points[j][0] - points[i][0];
                int dy = points[j][1] - points[i][1];
                
                int gcd = getGcd(dx, dy);
                // Simplify the slope
                String slope = (dx / gcd) + "/" + (dy / gcd);
                
                slopeMap.put(slope, slopeMap.getOrDefault(slope, 0) + 1);
                currentMax = Math.max(currentMax, slopeMap.get(slope));
            }
            max = Math.max(max, currentMax + 1);
        }
        return max;
    }
    
    private int getGcd(int a, int b) {
        return b == 0 ? a : getGcd(b, a % b);
    }
}