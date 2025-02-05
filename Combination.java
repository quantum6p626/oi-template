class Combination {
    class Inv {
        long exgcd(long a, long b, long[] x, long[] y) {
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
        long inv(long a, long m) {
            long[] x = {0};
            long[] y = {0};
            long d = exgcd(a, m, x, y);
            if (d == 1)
                return (x[0] % m + m) % m;
            return -1;
        }
    }
    long MOD;
    long[] facts, rfacts;
    Inv inv;
    Combination(int n, long MOD) {
        this.MOD = MOD;
        facts = new long[n + 1];
        rfacts = new long[n + 1];
        facts[0] = 1;
        for (int i = 1; i <= n; i++)
            facts[i] = facts[i - 1] * i % MOD;
        inv = new Inv();
    }
    long rf(int n) {
        if (rfacts[n] != 0)
            return rfacts[n];
        rfacts[n] = inv.inv(facts[n], MOD);
        return rfacts[n];
    }
    long comb(int n, int k) {
        return facts[n] * rf(k) % MOD * rf(n - k) % MOD;
    }
}
