/** @author henrik.tramberend@beuth-hochschule.de */
package cgtools;

import static cgtools.Vector.*;

public record Direction(double x, double y, double z) {

  @Override
  public String toString() {
    return String.format("(Dir: %.2f %.2f %.2f)", x, y, z);
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Direction))
      return false;
    if (o == this)
      return true;
    Direction v = (Direction) o;
    return Util.isZero(length(subtract(this, v)));
  }
}
