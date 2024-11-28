import java.util.*;
import java.io.*;
import java.math.*;

public class Mat {
    private long[][] m;
    private int row, col;
    private long mod;
    public Mat(long[][] m0, long mod0) {
        m = m0;
        row = m0 == null ? 0 : m.length;
        col = row == 0 ? 0 : m[0].length;
        mod = mod0;
        for (int r = 0; r < row; r++)
            for (int c = 0; c < col; c++)
                m[r][c] %= mod;
    }
    public long get(int r, int c) {
        return m[r][c];
    }
    public Mat mul(Mat mat) {
        if (col != mat.row)
            return null;
        long[][] nm = new long[row][mat.col];
        for (int r = 0; r < row; r++)
            for (int c = 0; c < mat.col; c++)
                for (int i = 0; i < col; i++)
                    nm[r][c] = (nm[r][c] + m[r][i] * mat.m[i][c]) % mod;
        return new Mat(nm, mod);
    }
    public Mat pow(long x) {
        Mat mat = new Mat(new long[][]{{1, 0}, {0, 1}}, mod), base = this;
        while (x > 0) {
            if (x % 2 != 0)
                mat = mat.mul(base);
            base = base.mul(base);
            x /= 2;
        }
        return mat;
    }
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Mat))
            return false;
        Mat mat = (Mat)o;
        if (row != mat.row || col != mat.col)
            return false;
        for (int r = 0; r < row; r++)
            for (int c = 0; c < col; c++)
                if (m[r][c] != mat.m[r][c])
                    return false;
        return true;
    }
    public void print() {
        System.out.println('[');
        for (int r = 0; r < row; r++) {
            System.out.print(' ');
            for (int c = 0; c < col; c++) {
                if (c > 0)
                    System.out.print(' ');
                System.out.print(m[r][c]);
            }
            System.out.println();
        }
        System.out.println(']');
    }
}
