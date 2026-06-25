class Solution {
    public int countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;
        int totalValidSubarrays = 0;

        // Iterate through all possible starting points of subarrays
        for (int i = 0; i < n; i++) {
            int targetCount = 0;
            
            // Iterate through all possible ending points for the current start i
            for (int j = i; j < n; j++) {
                if (nums[j] == target) {
                    targetCount++;
                }
                
                // Calculate the current subarray length
                int subarrayLength = j - i + 1;
                
                // Check if target is the majority element:
                // targetCount > subarrayLength / 2 is equivalent to
                // 2 * targetCount > subarrayLength
                if (2 * targetCount > subarrayLength) {
                    totalValidSubarrays++;
                }
            }
        }
        
        return totalValidSubarrays;
    }
}