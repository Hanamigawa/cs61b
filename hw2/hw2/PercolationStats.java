package hw2;
import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;
public class PercolationStats {
    private double[] means;
    public PercolationStats(int N, int T, PercolationFactory pf)   // perform T independent experiments on an N-by-N grid
    {
        means = new double[T];
        for (int i = 0; i < T; i++) {
            means[i] = perco(N, pf);
        }
    }
    public double mean()                                           // sample mean of percolation threshold
    {
        return  StdStats.mean(means);
    }
    public double stddev()                                         // sample standard deviation of percolation threshold
    {
        return StdStats.stddev(means);
    }
    public double confidenceLow()                                  // low endpoint of 95% confidence interval
    {
        return mean() - 1.96 * stddev();
    }
    public double confidenceHigh()                                 // high endpoint of 95% confidence interval
    {
        return mean() + 1.96 * stddev();
    }
    private double perco(int N, PercolationFactory pf) {
        Percolation myPerco = pf.make(N);

        while (!myPerco.percolates()) {
            myPerco.open(StdRandom.uniform(N), StdRandom.uniform(N));
        }
        return (double) myPerco.numberOfOpenSites() / (N * N);
    }

    public static void main(String[] args) {
        PercolationStats percoStatsTest = new PercolationStats(10, 10, new PercolationFactory());
        System.out.println(percoStatsTest.mean() + " " + percoStatsTest.stddev() + " " + percoStatsTest.confidenceLow());
    }
}
