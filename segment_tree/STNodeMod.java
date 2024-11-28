class STNodeMod {
    final long MOD = 1000000007;
    int from, to, mid;
    long sumVal, incVal, mulVal;
    STNodeMod left, right;
    STNodeMod(int from, int to) {
        this.from = from;
        this.to = to;
        mid = from + (to - from) / 2;
        sumVal = 0;
        incVal = 0;
        mulVal = 1;
        left = null;
        right = null;
    }
    void inc(int p, int q, long val) {
        sumVal = (sumVal + val * (q - p + 1) % MOD) % MOD;
        if (p == from && q == to) {
            incVal = (incVal + val) % MOD;
            return;
        }
        if (p <= mid && left == null)
            left = new STNodeMod(from, mid);
        if (q > mid && right == null)
            right = new STNodeMod(mid + 1, to);
        if (q <= mid)
            left.inc(p, q, val);
        else if (p > mid)
            right.inc(p, q, val);
        else {
            left.inc(p, mid, val);
            right.inc(mid + 1, q, val);
        }
    }
    void mul(int p, int q, long val) {
        sumVal = sumVal * val % MOD;
        if (p == from && q == to) {
            mulVal = mulVal * val % MOD;
            incVal = incVal * val % MOD;
            return;
        }
        if (p <= mid && left == null)
            left = new STNodeMod(from, mid);
        if (q > mid && right == null)
            right = new STNodeMod(mid + 1, to);
        if (q <= mid)
            left.mul(p, q, val);
        else if (p > mid)
            right.mul(p, q, val);
        else {
            left.mul(p, mid, val);
            right.mul(mid + 1, q, val);
        }
    }
    long sum(int p, int q) {
        if (p == from && q == to)
            return sumVal;
        pushDown();
        if (q <= mid)
            return left.sum(p, q);
        else if (p > mid)
            return right.sum(p, q);
        return (left.sum(p, mid) + right.sum(mid + 1, q)) % MOD;
    }
    void pushDown() {
        if (left == null)
            left = new STNodeMod(from, mid);
        if (right == null)
            right = new STNodeMod(mid + 1, to);
        left.sumVal = (left.sumVal * mulVal % MOD + (left.to - left.from + 1) * incVal % MOD) % MOD;
        right.sumVal = (right.sumVal * mulVal % MOD + (right.to - right.from + 1) * incVal % MOD) % MOD;
        left.mulVal = left.mulVal * mulVal % MOD;
        left.incVal = (left.incVal * mulVal % MOD + incVal) % MOD;
        right.mulVal = right.mulVal * mulVal % MOD;
        right.incVal = (right.incVal * mulVal % MOD + incVal) % MOD;
        incVal = 0;
        mulVal = 1;
    }
}
