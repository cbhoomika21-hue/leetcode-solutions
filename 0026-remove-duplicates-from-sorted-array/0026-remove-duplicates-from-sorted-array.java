class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0)
            return 0;

        // Pointer i keeps track of the index of the last unique element
        int i = 0;

        // Pointer j scans through the array
        for (int j = 1; j < nums.length; j++) {
            // If we find a new unique element
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j]; // Move unique element to the next position
            }
        }

        // Return the number of unique elements (index + 1)
        return i + 1;
    }
}
