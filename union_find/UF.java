class UF {
    int[] g, sz;
    UF(int n) {
        g = new int[n];
        sz = new int[n];
        for (int i = 0; i < n; i++) {
            g[i] = i;
            sz[i] = 1;
        }
    }
    int find(int x) {
        if (g[x] != x)
            g[x] = find(g[x]);
        return g[x];
    }
    int size(int x) {
        return sz[find(x)];
    }
    void union(int x, int y) {
        int rx = find(x), ry = find(y);
        if (rx != ry) {
            if (sz[rx] >= sz[ry]) {
                g[ry] = rx;
                sz[rx] += sz[ry];
            } else {
                g[rx] = ry;
                sz[ry] += sz[rx];
            }
        }
    }
}
