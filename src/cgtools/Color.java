/** @author henrik.tramberend@beuth-hochschule.de */
package cgtools;

public record Color(double r, double g, double b) {

  @Override
  public String toString() {
    return String.format("(Col: %.2f %.2f %.2f)", r, g, b);
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Color))
      return false;
    if (o == this)
      return true;
    Color v = (Color) o;
    return v.r == r && v.g == g && v.b == b;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + Double.valueOf(r).hashCode();
    result = prime * result + Double.valueOf(g).hashCode();
    result = prime * result + Double.valueOf(b).hashCode();
    return result;
  }
}
