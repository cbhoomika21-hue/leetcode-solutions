
        import java.util.*;

public class Solution {
    public int romanToInt(String s) {
        int total = 0, i = 0;
        while (i < s.length()) {
            int curr = value(s.charAt(i));
            int next = (i + 1 < s.length()) ? value(s.charAt(i + 1)) : 0;
            if (curr < next) total += (next - curr); 
            else total += curr;
            i += (curr < next) ? 2 : 1;
        }
        return total;
    }
    
    private int value(char c) {
        switch(c) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
        }
        return 0;
    }
}