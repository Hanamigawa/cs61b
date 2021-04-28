package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.ArrayList;
import java.util.List;

public class Percolation {
    private boolean[][] opened;
    private int openCount = 0;
    private int N;
    private WeightedQuickUnionUF wqu;
    public Percolation(int N) {     // create N-by-N grid, with all sites initially blocked
        if (N <= 0) {
            throw new IllegalArgumentException("N couldn't be less than one.");
        }
        this.N = N;
        opened = new boolean[N][N];
        wqu = new WeightedQuickUnionUF(N * N + 2);
        for (int i = 0; i < N; i++) {
            wqu.union(N * N, to1D(0, i));
            wqu.union(N * N + 1, to1D(N - 1, i));
        }
    }
    public void open(int row, int col)       // open the site (row, col) if it is not open already
    {
        isInputValid(row, col);
        if (isOpen(row, col)) return;
        opened[row][col] = true;
        openCount++;
        for (int neigh : findNeig(row, col)) {
            int[] neigh2D = to2D(neigh);
            int neigh_row = neigh2D[0];
            int neigh_col = neigh2D[1];
            if (opened[neigh_row][neigh_col]) {
                wqu.union(to1D(row, col), to1D(neigh_row, neigh_col));
            }
        }
    }
    public boolean isOpen(int row, int col)  // is the site (row, col) open?
    {
        isInputValid(row, col);
        return opened[row][col];
    }
    public boolean isFull(int row, int col)  // is the site (row, col) full?
    {
        isInputValid(row, col);
        return wqu.connected(to1D(row, col), N * N);
    }
    public int numberOfOpenSites()           // number of open sites
    {
        return openCount;
    }
    public boolean percolates()              // does the system percolate?
    {
        return wqu.connected(N * N, N * N + 1);
    }
    public static void main(String[] args)   // use for unit testing (not required)
    {
        Percolation percoTest = new Percolation(3);
        boolean b;
        b = percoTest.isOpen(0, 1);     //exp: false
        percoTest.open(0,1);
        b = percoTest.isOpen(0, 1);     //exp: true
        percoTest.open(2,1);
        b = percoTest.isFull(2, 1);     //exp: false
        b = percoTest.percolates();             // exp: false;
        percoTest.open(1, 1);
        b = percoTest.isFull(2, 1);     //exp: true;
        b = percoTest.percolates();             // exp true;
    }
    private boolean isInputValid(int i, int j) {
        if (i < 0 || i > N - 1 || j < 0 || j > N - 1) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return true;
    }
    private int to1D(int row, int col) {
        return row * N + col;
    }
    private int[] to2D(int x) {
        int[] res = new int[2];
        res[0] = x / N;
        res[1] = x % N;
        return res;
    }
    private List<Integer> findNeig(int row, int col) {
        List<Integer> res = new ArrayList<>();
        if (row + 1 < N) {
            res.add(to1D(row + 1, col));
        }
        if (row - 1 >= 0) {
            res.add(to1D(row - 1, col));
        }
        if (col + 1 < N) {
            res.add(to1D(row, col + 1));
        }
        if (col - 1 >= 0) {
            res.add(to1D(row, col - 1));
        }
        return res;
    }
}
