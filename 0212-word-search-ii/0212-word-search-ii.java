class Solution {

    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        String word;
    }

    public List<String> findWords(char[][] board, String[] words) {

        TrieNode root = buildTrie(words);

        List<String> result = new ArrayList<>();

        int m = board.length;
        int n = board[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(board, i, j, root, result);
            }
        }

        return result;
    }

    private void dfs(char[][] board,
                     int row,
                     int col,
                     TrieNode node,
                     List<String> result) {

        if (row < 0 || col < 0 ||
            row >= board.length ||
            col >= board[0].length) {
            return;
        }

        char ch = board[row][col];

        if (ch == '#') {
            return;
        }

        TrieNode next = node.children[ch - 'a'];

        if (next == null) {
            return;
        }

        if (next.word != null) {
            result.add(next.word);
            next.word = null;
        }

        board[row][col] = '#';

        dfs(board, row + 1, col, next, result);
        dfs(board, row - 1, col, next, result);
        dfs(board, row, col + 1, next, result);
        dfs(board, row, col - 1, next, result);

        board[row][col] = ch;
    }

    private TrieNode buildTrie(String[] words) {

        TrieNode root = new TrieNode();

        for (String word : words) {

            TrieNode curr = root;

            for (char ch : word.toCharArray()) {

                int idx = ch - 'a';

                if (curr.children[idx] == null) {
                    curr.children[idx] = new TrieNode();
                }

                curr = curr.children[idx];
            }

            curr.word = word;
        }

        return root;
    }
}