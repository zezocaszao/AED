public class DoublingHypothesis {

  private static final long LIMIT = 128000;
  private static final int AVERAGE_OF = 5;

  public static double getElapsedTimeMillis(int N) {
	  WeightedQUPathCompressionUF uf = new WeightedQUPathCompressionUF(N);
    double start = System.currentTimeMillis();
    for (int i = 0; i < N; i++) {
      int p = randInt(0, N - 1);
      int q = randInt(0, N - 1);
      uf.connected(p, q);
    }
    double end = System.currentTimeMillis();
    return end - start;
  }

  public static double getAverageTimeMillis(int N, int trials) {
    double sum = 0;
    for (int i = 0; i < trials; i++)
      sum += getElapsedTimeMillis(N);
    return sum / trials;
  }

  private static int randInt(int start, int end) {
    return (int) (start + (end - start) * Math.random());
  }

  private static double millisecondsToSeconds(double millis) {
    return millis / 1000.0;
  }

  public static void applyDoublingHypothesis() {
    double previous = millisecondsToSeconds(getAverageTimeMillis(125, AVERAGE_OF));
    double lgRatio = 0;

    System.out.println("Doubling Hypothesis\n");
    System.out.println("N\t\tT(N)\t\tratio\t\tlg(ratio)");

    for (int n = 250; n <= LIMIT; n += n) {
      double time = millisecondsToSeconds(getAverageTimeMillis(n, AVERAGE_OF));
      double ratio = time / previous;
      lgRatio = Math.log(ratio) / Math.log(2);
      System.out.printf("%d\t\t%.3f\t\t%.3f\t\t%.3f\n", n, time, ratio, lgRatio);
      previous = time;
    }
    double b = lgRatio;
    double a = previous / Math.pow(LIMIT, b);
    System.out.println("\na = " + a);
    System.out.println("b = " + b);
    System.out.println("\nT(N) = " + a + " * N^" + b);
    System.out.println("\nT(10^9) = " + a * Math.pow(10e9, b) + " seconds");
  }

  public static void main(String[] args) {
    applyDoublingHypothesis();
  }
}