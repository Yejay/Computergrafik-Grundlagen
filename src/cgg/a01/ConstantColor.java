/** @author henrik.tramberend@beuth-hochschule.de */
package cgg.a01;

import cgtools.*;

// Represents the contents of an image. Provides the same color for all pixels.
public record ConstantColor(Color color) implements Sampler {
  // Returns the color for the given position.
  public Color getColor(double x, double y) {
    return color;
  }
}
