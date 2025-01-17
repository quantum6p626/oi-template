class STNode {
    int from, mid, to;
    STNode left, right;
    int sumVal;
    STNode(int from, int to) {
        this.from = from;
        this.to = to;
        mid = from + (to - from) / 2;
        left = null;
        right = null;
        sumVal = 0;
    }
    void inc(int p, int x) {
        sumVal += x;
        if (from == to)
            return;
        if (p <= mid) {
            if (left == null)
                left = new STNode(from, mid);
            left.inc(p, x);
        } else {
            if (right == null)
                right = new STNode(mid + 1, to);
            right.inc(p, x);
        }
    }
    int sum(int p, int q) {
        if (p == from && q == to)
            return sumVal;
        if (q <= mid)
            return left == null ? 0 : left.sum(p, q);
        else if (p > mid)
            return right == null ? 0 : right.sum(p, q);
        else
            return (left == null ? 0 : left.sum(p, mid)) + (right == null ? 0 : right.sum(mid + 1, q));
    }
}
