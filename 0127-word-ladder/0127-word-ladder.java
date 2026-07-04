import java.util.*;

class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord))
            return 0;

        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);

        int level = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String currentWord = queue.poll();

                // Try changing each character of the current word
                char[] wordChars = currentWord.toCharArray();
                for (int j = 0; j < wordChars.length; j++) {
                    char originalChar = wordChars[j];

                    for (char c = 'a'; c <= 'z'; c++) {
                        if (wordChars[j] == c)
                            continue;
                        wordChars[j] = c;
                        String transformedWord = new String(wordChars);

                        if (transformedWord.equals(endWord))
                            return level + 1;

                        if (wordSet.contains(transformedWord)) {
                            queue.offer(transformedWord);
                            wordSet.remove(transformedWord); // Mark as visited
                        }
                    }
                    wordChars[j] = originalChar; // Restore for next iteration
                }
            }
            level++;
        }

        return 0;
    }
}