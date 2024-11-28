class STNode {
    long from, mid, to;
    STNode left, right;
    long sumVal, minVal;
    //parent marks have higher priority than children marks
    boolean setFlag;
    long setVal, incVal;
    STNode(long from, long to) {
        this.from = from;
        this.to = to;
        mid = from + (to - from) / 2;
        left = null;
        right = null;
        sumVal = 0;
        minVal = 0;
        setFlag = false;
    }
    //for each modifying operation, it should be guarded by a pair of push and merge
    //push to clear the marks for the current node
    void push() {
        if (from == to) {
            if (setFlag) {
                sumVal = setVal;
                minVal = setVal;
                setFlag = false;
            }
            if (incVal != 0) {
                sumVal += incVal;
                minVal += incVal;
                incVal = 0;
            }
        } else {
            if (left == null)
                left = new STNode(from, mid);
            if (right == null)
                right = new STNode(mid + 1, to);
            if (setFlag) {
                left.setFlag = true;
                left.setVal = setVal;
                left.incVal = 0;
                right.setFlag = true;
                right.setVal = setVal;
                right.incVal = 0;
                setFlag = false;
            }
            if (incVal != 0) {
                left.incVal += incVal;
                right.incVal += incVal;
                incVal = 0;
            }
        }
    }
    //merge to keep data up-to-date
    void merge() {
        if (from < to) {
            long leftLen = left.to - left.from + 1, rightLen = right.to - right.from + 1;
            long leftSum = (left.setFlag ? left.setVal * leftLen : left.sumVal) + left.incVal * leftLen;
            long rightSum = (right.setFlag ? right.setVal * rightLen : right.sumVal) + right.incVal * rightLen;
            sumVal = leftSum + rightSum;
            long leftMin = (left.setFlag ? left.setVal : left.minVal) + left.incVal;
            long rightMin = (right.setFlag ? right.setVal : right.minVal) + right.incVal;
            minVal = Math.min(leftMin, rightMin);
        }
    }
    void set(long p, long q, long val) {
        push();
        if (from == p && to == q) {
            setFlag = true;
            setVal = val;
            incVal = 0;
        } else {
            if (q <= mid)
                left.set(p, q, val);
            else if (p > mid)
                right.set(p, q, val);
            else {
                left.set(p, mid, val);
                right.set(mid + 1, q, val);
            }
        }
        merge();
    }
    void inc(long p, long q, long val) {
        push();
        if (from == p && to == q)
            incVal += val;
        else {
            if (q <= mid)
                left.inc(p, q, val);
            else if (p > mid)
                right.inc(p, q, val);
            else {
                left.inc(p, mid, val);
                right.inc(mid + 1, q, val);
            }
        }
        merge();
    }
    long sum(long p, long q) {
        push();
        merge();
        if (from == p && to == q)
            return sumVal;
        else if (q <= mid)
            return left.sum(p, q);
        else if (p > mid)
            return right.sum(p, q);
        else
            return left.sum(p, mid) + right.sum(mid + 1, q);
    }
    long min(long p, long q) {
        push();
        merge();
        if (from == p && to == q)
            return minVal;
        else if (q <= mid)
            return left.min(p, q);
        else if (p > mid)
            return right.min(p, q);
        else
            return Math.min(left.min(p, mid), right.min(mid + 1, q));
    }
}
