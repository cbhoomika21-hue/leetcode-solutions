class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> result = new ArrayList<>();
        String digits = "123456789";
        
        // Iterate through possible lengths of sequential numbers (from 2 to 9)
        for (int length = 2; length <= 9; length++) {
            // Iterate through possible starting positions in the "123456789" string
            for (int i = 0; i <= 9 - length; i++) {
                int num = Integer.parseInt(digits.substring(i, i + length));
                
                // Add to result if within the specified range [low, high]
                if (num >= low && num <= high) {
                    result.add(num);
                }
            }
        }
        
        // The numbers are naturally generated in increasing order
        return result;
    }
}