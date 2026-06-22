class Solution {

    static class TrieNode {
        TrieNode[] child = new TrieNode[26];

        int idx;
        int len;

        TrieNode(int idx, int len) {
            this.idx = idx;
            this.len = len;
        }
    }

    public int[] stringIndices(String[] wordsContainer, String[] wordsQuery) {

        int n = wordsContainer.length;

        int minIdx = 0;
        for (int i = 1; i < n; i++) {
            if (wordsContainer[i].length() < wordsContainer[minIdx].length()) {
                minIdx = i;
            }
        }

        TrieNode root = new TrieNode(minIdx,
                wordsContainer[minIdx].length());

        // Build reversed trie
        for (int i = 0; i < n; i++) {
            String word = wordsContainer[i];

            TrieNode curr = root;

            update(curr, i, word.length());

            for (int j = word.length() - 1; j >= 0; j--) {
                int c = word.charAt(j) - 'a';

                if (curr.child[c] == null) {
                    curr.child[c] =
                            new TrieNode(i, word.length());
                }

                curr = curr.child[c];

                update(curr, i, word.length());
            }
        }

        int[] ans = new int[wordsQuery.length];

        for (int i = 0; i < wordsQuery.length; i++) {
            TrieNode curr = root;
            String q = wordsQuery[i];

            for (int j = q.length() - 1; j >= 0; j--) {
                int c = q.charAt(j) - 'a';

                if (curr.child[c] == null) {
                    break;
                }

                curr = curr.child[c];
            }

            ans[i] = curr.idx;
        }

        return ans;
    }

    private void update(TrieNode node, int idx, int len) {
        if (len < node.len) {
            node.len = len;
            node.idx = idx;
        }
    }
}