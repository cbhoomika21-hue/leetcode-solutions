import java.util.*;

class Solution {

    static class SegmentTree {
        int n;
        int[] tree;

        SegmentTree(int n) {
            this.n = n;
            tree = new int[4 * n];
        }

        void update(int idx, int val) {
            update(1, 0, n - 1, idx, val);
        }

        private void update(int node, int l, int r, int idx, int val) {
            if (l == r) {
                tree[node] = val;
                return;
            }

            int mid = (l + r) >> 1;

            if (idx <= mid)
                update(node * 2, l, mid, idx, val);
            else
                update(node * 2 + 1, mid + 1, r, idx, val);

            tree[node] = Math.max(tree[node * 2], tree[node * 2 + 1]);
        }

        int query(int L, int R) {
            return query(1, 0, n - 1, L, R);
        }

        private int query(int node, int l, int r, int L, int R) {
            if (R < l || r < L)
                return 0;

            if (L <= l && r <= R)
                return tree[node];

            int mid = (l + r) >> 1;

            return Math.max(
                query(node * 2, l, mid, L, R),
                query(node * 2 + 1, mid + 1, r, L, R)
            );
        }
    }

    public List<Boolean> getResults(int[][] queries) {

        int LIMIT = 50001;

        TreeSet<Integer> obstacles = new TreeSet<>();
        obstacles.add(0);
        obstacles.add(LIMIT);

        SegmentTree seg = new SegmentTree(LIMIT + 1);

        seg.update(LIMIT, LIMIT);

        List<Boolean> ans = new ArrayList<>();

        for (int[] q : queries) {

            if (q[0] == 1) {

                int x = q[1];

                Integer left = obstacles.lower(x);
                Integer right = obstacles.higher(x);

                seg.update(x, x - left);
                seg.update(right, right - x);

                obstacles.add(x);

            } else {

                int x = q[1];
                int sz = q[2];

                Integer last = obstacles.floor(x);

                int maxGap = seg.query(0, last);

                maxGap = Math.max(maxGap, x - last);

                ans.add(maxGap >= sz);
            }
        }

        return ans;
    }
}