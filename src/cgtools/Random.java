/** @author henrik.tramberend@beuth-hochschule.de */
package cgtools;

/**
 * A faster replacement for java.util.Random.
 *
 * <p>Adapted from http://dsiutils.di.unimi.it/docs/it/unimi/dsi/util/SplitMix64Random.html
 */
public class Random extends java.util.Random {
  private static final long serialVersionUID = 1L;
  private static final long PHI = 0x9E3779B97F4A7C15L;

  private long x;

  private static Random generator;

  static {
    generator = new Random();
  }

  /**
   * Produces a pseudo random number from the interval [0,1]. A direct replacement for Math.random()
   *
   * @return A pseudo random number from the interval [0,1].
   */
  public static double random() {
    return generator.nextDouble();
  }

  public Random() {
    this(System.nanoTime());
  }

  public Random(final long seed) {
    x = seed;
  }

  private static long staffordMix13(long z) {
    z = (z ^ (z >>> 30)) * 0xBF58476D1CE4E5B9L;
    z = (z ^ (z >>> 27)) * 0x94D049BB133111EBL;
    return z ^ (z >>> 31);
  }

  private static int staffordMix4Upper32(long z) {
    z = (z ^ (z >>> 33)) * 0x62A9D9ED799705F5L;
    return (int) (((z ^ (z >>> 28)) * 0xCB24D0A5C88C35B3L) >>> 32);
  }

  @Override
  public long nextLong() {
    return staffordMix13(x += PHI);
  }

  @Override
  public int nextInt() {
    return staffordMix4Upper32(x += PHI);
  }

  @Override
  public int nextInt(final int n) {
    return (int) nextLong(n);
  }

  public long nextLong(final long n) {
    if (n <= 0) throw new IllegalArgumentException("illegal bound " + n + " (must be positive)");
    long t = staffordMix13(x += PHI);
    final long nMinus1 = n - 1;
    if ((n & nMinus1) == 0) return t & nMinus1;
    for (long u = t >>> 1; u + nMinus1 - (t = u % n) < 0; u = staffordMix13(x += PHI) >>> 1) ;
    return t;
  }

  @Override
  public double nextDouble() {
    return Double.longBitsToDouble(staffordMix13(x += PHI) >>> 12 | 0x3FFL << 52) - 1.0;
  }

  @Override
  public float nextFloat() {
    return Float.intBitsToFloat(staffordMix4Upper32(x += PHI) >>> 41 | 0x3F8 << 20) - 1.0f;
  }

  @Override
  public boolean nextBoolean() {
    return staffordMix4Upper32(x += PHI) < 0;
  }

  @Override
  public void nextBytes(final byte[] bytes) {
    int i = bytes.length, n = 0;
    while (i != 0) {
      n = Math.min(i, 8);
      for (long bits = staffordMix13(x += PHI); n-- != 0; bits >>= 8) bytes[--i] = (byte) bits;
    }
  }
}
