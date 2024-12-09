class Dijkstra {
    int n;
    HashMap<Integer, Integer>[] g;
    long[] dis;
    public Dijkstra(int n, int[][] edges) {
        //node is from 0 to n - 1
        this.n = n;
        g = new HashMap[n];
        for (int i = 0; i < n; i++)
            g[i] = new HashMap<>();;
        for (int[] e : edges)
            if (!g[e[0]].containsKey(e[1]) || e[2] < g[e[0]].get(e[1]))
                g[e[0]].put(e[1], e[2]);
    }
    public long[] run(int src) {
        dis = new long[n];
        boolean[] flag = new boolean[n];
        Arrays.fill(dis, -1);
        dis[src] = 0;
        PriorityQueue<long[]> pq = new PriorityQueue<>((long[] a, long[] b) -> {
            if (a[0] == -1 && b[0] == -1)
                return 0;
            else if (a[0] == -1)
                return 1;
            else if (b[0] == -1)
                return -1;
            else
                return Long.compare(a[0], b[0]);
        });
        pq.add(new long[]{dis[src], src});
        while (!pq.isEmpty()) {
            long[] e = pq.poll();
            int x = (int)e[1];
            if (flag[x])
                continue;
            flag[x] = true;
            if (dis[x] == -1)
                continue;
            for (int y : g[x].keySet()) {
                if (flag[y])
                    continue;
                int d = g[x].get(y);
                if (dis[y] == -1 || dis[x] + d < dis[y]) {
                    dis[y] = dis[x] + d;
                    pq.add(new long[]{dis[y], y});
                }
            }
        }
        return dis;
    }
}
