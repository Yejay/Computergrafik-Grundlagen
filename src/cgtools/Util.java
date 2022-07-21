/** @author henrik.tramberend@beuth-hochschule.de */
package cgtools;

import static cgtools.Vector.*;
import static java.lang.Math.*;

public final class Util {

  public static final double EPSILON = 1E-3;

  public static final boolean isZero(double a) {
    return abs(a) < EPSILON;
  }

  public static final boolean areEqual(double a, double b) {
    return isZero(a - b);
  }

  public static final Color shade(Direction normal, Color color) {
    if (normal == null) return color;

    Direction lightDir = normalize(direction(1, 1, 0.5));
    Color ambient = multiply(0.1, color);
    Color diffuse = multiply(0.9 * Math.max(0, dotProduct(lightDir, normal)), color);
    return add(ambient, diffuse);
  }
}
