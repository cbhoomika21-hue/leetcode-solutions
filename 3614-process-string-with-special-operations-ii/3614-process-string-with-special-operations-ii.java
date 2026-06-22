class Solution {
    public char processStr(String s, long k) {
        int n = s.length();

        long[] len = new long[n + 1];

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);

            if (c >= 'a' && c <= 'z') {
                len[i + 1] = len[i] + 1;
            } else if (c == '*') {
                len[i + 1] = Math.max(0L, len[i] - 1);
            } else if (c == '#') {
                len[i + 1] = Math.min((long)1e15, len[i] * 2);
            } else { // '%'
                len[i + 1] = len[i];
            }
        }

        if (k >= len[n]) {
            return '.';
        }

        for (int i = n - 1; i >= 0; i--) {
            char c = s.charAt(i);

            if (c >= 'a' && c <= 'z') {
                if (k == len[i]) {
                    return c;
                }
            } else if (c == '*') {
                // no change needed
            } else if (c == '#') {
                long half = len[i];
                if (k >= half) {
                    k -= half;
                }
            } else { // '%'
                k = len[i] - 1 - k;
            }
        }

        return '.';
    }
}