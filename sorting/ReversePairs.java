class ReversePairs {
    int ans = 0;
    void mergeSort(int[] a, int from, int to) {
        if (from == to)
            return;
        int mid = from + (to - from) / 2;
        mergeSort(a, from, mid);
        mergeSort(a, mid + 1, to);
        for (int p1 = from, p2 = mid + 1; p1 <= mid; p1++) {
            while (p2 <= to) {
                if (a[p1] > 0) {
                    if (a[p2] > 0) {
                        if (a[p2] > Integer.MAX_VALUE / 2)
                            break;
                        if (a[p1] <= 2 * a[p2])
                            break;
                    }
                } else if (a[p1] == 0) {
                    if (a[p2] >= 0)
                        break;
                } else {
                    if (a[p2] >= 0)
                        break;
                    if (a[p2] >= Integer.MIN_VALUE / 2 && a[p1] <= 2 * a[p2])
                        break;
                }
                p2++;
            }
            ans += p2 - mid - 1;
        }
        int[] a2 = new int[to - from + 1];
        for (int p1 = from, p2 = mid + 1, p = 0; p1 <= mid || p2 <= to;)
            if (p1 > mid)
                a2[p++] = a[p2++];
            else if (p2 > to)
                a2[p++] = a[p1++];
            else if (a[p1] <= a[p2])
                a2[p++] = a[p1++];
            else
                a2[p++] = a[p2++];
        for (int i = 0; i < a2.length; i++)
            a[from + i] = a2[i];
    }
    public int reversePairs(int[] nums) {
        if (nums.length == 0)
            return 0;
        mergeSort(nums, 0, nums.length - 1);
        return ans;
    }
}
