class Dijkstra {
    int n;
    HashMap<Integer, HashMap<Integer, Integer>> g = new HashMap<>();
    int[] dis;
    //given a graph, return the minimum distance array from node src to all nodes
    public int[] run(int n, int[][] edges, int src) {
        //node is from 0 to n - 1
        this.n = n;
        for (int i = 0; i < n; i++)
            g.put(i, new HashMap<>());
        for (int[] e : edges)
            g.get(e[0]).put(e[1], e[2]);
        dis = new int[n];
        boolean[] flag = new boolean[n];
        for (int i = 0; i < n; i++)
            if (i != src)
                dis[i] = -1;
        PriorityQueue<int[]> pq = new PriorityQueue<>((int[] a, int[] b) -> {
            if (a[0] == b[0])
                return 0;
            if (a[0] == -1)
                return 1;
            if (b[0] == -1)
                return -1;
            return Integer.compare(a[0], b[0]);
        });
        for (int i = 0; i < n; i++)
            pq.add(new int[]{dis[i], i});
        while (pq.size() > 0) {
            int[] e = pq.poll();
            if (flag[e[1]])
                continue;
            flag[e[1]] = true;
            if (dis[e[1]] == -1)
                continue;
            for (int x : g.get(e[1]).keySet()) {
                if (flag[x])
                    continue;
                int d = g.get(e[1]).get(x);
                if (dis[x] == -1 || dis[e[1]] + d < dis[x]) {
                    dis[x] = dis[e[1]] + d;
                    pq.add(new int[]{dis[x], x});
                }
            }
        }
        return dis;
    }
}
