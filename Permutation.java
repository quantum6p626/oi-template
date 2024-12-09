class Permutation {
    boolean hasNext(int[] a) {
        int n = a.length;
        for (int i = 1; i < n; i++)
            if (a[i - 1] < a[i])
                return true;
        return false;
    }
    void next(int[] a) {
        int n = a.length, p = n - 1;
        while (p > 0 && a[p - 1] >= a[p])
            p--;
        if (p == 0)
            return;
        int q = n - 1;
        while (a[q] <= a[p - 1])
            q--;
        int tmp = a[p - 1];
        a[p - 1] = a[q];
        a[q] = tmp;
        for (int i = p, j = n - 1; i < j; i++, j--) {
            tmp = a[i];
            a[i] = a[j];
            a[j] = tmp;
        }
    }
}
