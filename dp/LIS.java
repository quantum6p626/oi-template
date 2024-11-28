class LIS {
    public int run(ArrayList<Integer> arr) {
        ArrayList<Integer> b = new ArrayList<>();
        for (int x : arr) {
            if (b.isEmpty()) {
                b.add(x);
                continue;
            }
            int p = 0, q = b.size() - 1;
            while (p < q) {
                int mid = p + (q - p) / 2;
                if (b.get(mid + 1) >= x) // >= to > for non-decreasing
                    q = mid;
                else
                    p = mid + 1;
            }
            if (b.get(p) >= x) // >= to > for non-decreasing
                b.set(p, x);
            else if (p == b.size() - 1)
                b.add(x);
            else
                b.set(p + 1, x);
        }
        return b.size();
    }
}
