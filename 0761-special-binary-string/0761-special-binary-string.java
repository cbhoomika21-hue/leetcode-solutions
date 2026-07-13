import java.util.*;

class Solution {
    public String makeLargestSpecial(String s) {
        List<String> mountains = new ArrayList<>();
        int count = 0, start = 0;
        
        // Decompose into mountain strings
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') count++;
            else count--;
            
            if (count == 0) {
                // We found a mountain. 
                // Recursively process the inner part (strip the outer '1' and '0')
                String inner = makeLargestSpecial(s.substring(start + 1, i));
                mountains.add("1" + inner + "0");
                start = i + 1;
            }
        }
        
        // Sort mountains in descending lexicographical order
        Collections.sort(mountains, Collections.reverseOrder());
        
        // Join them back together
        StringBuilder sb = new StringBuilder();
        for (String mountain : mountains) {
            sb.append(mountain);
        }
        
        return sb.toString();
    }
}