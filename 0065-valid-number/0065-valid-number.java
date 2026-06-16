class Solution {
    public boolean isNumber(String s) {
        boolean seenDigit = false;
        boolean seenDot = false;
        boolean seenExp = false;
        boolean digitAfterExp = true;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (Character.isDigit(c)) {
                seenDigit = true;
                if (seenExp) {
                    digitAfterExp = true;
                }
            }
            else if (c == '+' || c == '-') {
                // Sign is valid only at start or immediately after e/E
                if (i > 0 && s.charAt(i - 1) != 'e' && s.charAt(i - 1) != 'E') {
                    return false;
                }
            }
            else if (c == '.') {
                // Dot cannot appear after exponent or more than once
                if (seenDot || seenExp) {
                    return false;
                }
                seenDot = true;
            }
            else if (c == 'e' || c == 'E') {
                // Exponent must appear once and after at least one digit
                if (seenExp || !seenDigit) {
                    return false;
                }
                seenExp = true;
                digitAfterExp = false;
            }
            else {
                return false;
            }
        }

        return seenDigit && digitAfterExp;
    }
}