class Solution {
    public int numberOfSubstrings(String s) {
        int count = 0;
        int n = s.length();
        // Array to store the last seen index of 'a', 'b', and 'c'
        // Index 0 for 'a', 1 for 'b', 2 for 'c'
        int[] lastSeen = { -1, -1, -1 };

        for (int i = 0; i < n; i++) {
            // Update the last seen position of the current character
            lastSeen[s.charAt(i) - 'a'] = i;

            // The minimum of the three last seen positions tells us 
            // how far back a valid substring can start.
            // Any index from 0 to min(lastSeen) is a valid starting point.
            int minLastSeen = Math.min(lastSeen[0], Math.min(lastSeen[1], lastSeen[2]));

            // If all characters have been seen at least once
            if (minLastSeen != -1) {
                count += (minLastSeen + 1);
            }
        }

        return count;
    }
}
