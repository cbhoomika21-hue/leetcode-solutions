class Solution {
    public int[] separateDigits(int[] nums) {
        // Use a StringBuilder to collect all digits as we process the numbers
        StringBuilder sb = new StringBuilder();

        for (int num : nums) {
            sb.append(num);
        }

        // Convert the combined string of digits into an integer array
        String s = sb.toString();
        int[] result = new int[s.length()];

        for (int i = 0; i < s.length(); i++) {
            // Character.getNumericValue converts a char digit to its int value
            result[i] = Character.getNumericValue(s.charAt(i));
        }

        return result;
    }
}
