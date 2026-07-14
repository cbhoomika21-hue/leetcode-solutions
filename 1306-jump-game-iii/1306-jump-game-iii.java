class Solution {
    public boolean canReach(int[] arr, int start) {
        // Base cases: check bounds and if already visited
        if (start < 0 || start >= arr.length || arr[start] < 0) {
            return false;
        }
        
        // Check if we reached a 0
        if (arr[start] == 0) {
            return true;
        }
        
        // Mark the current index as visited by making its value negative
        arr[start] = -arr[start];
        
        // Recursively check both possible jump directions
        return canReach(arr, start + arr[start]) || 
               canReach(arr, start - arr[start]);
    }
}