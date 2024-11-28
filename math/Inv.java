public class Inv {
    private long exgcd(long a, long b, long[] x, long[] y) {
        if (b == 0) {
            x[0] = 1L;
            y[0] = 0L;
            return a;
        }
        long d = exgcd(b, a % b, x, y);
        long tmp = x[0];
        x[0] = y[0];
        y[0] = tmp - a / b * y[0];
        return d;
    }
    public long inv(long a, long m) {
        long[] x = {0};
        long[] y = {0};
        long d = exgcd(a, m, x, y);
        if (d == 1)
            return (x[0] % m + m) % m;
        return -1;
    }
}
