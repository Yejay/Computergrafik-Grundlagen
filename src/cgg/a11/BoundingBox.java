//
// Author: Henrik Tramberend <tramberend@beuth-hochschule.de>
//


package cgg.a11;

import static cgtools.Matrix.*;
import static cgtools.Vector.*;

import cgtools.*;

public class BoundingBox {

  public final Point min, max;

  public static BoundingBox everything = new BoundingBox(Double.MAX_VALUE);
  public static BoundingBox empty = new BoundingBox(-Double.MAX_VALUE);

  private BoundingBox(double size) {
    min = point(-size, -size, -size);
    max = point(size, size, size);
  }

  public BoundingBox(Point min, Point max) {
    this.min = min;
    this.max = max;
  }

  public BoundingBox extend(BoundingBox bb) {
    return new BoundingBox(point(Math.min(min.x(), bb.min.x()), Math.min(min.y(), bb.min.y()), Math.min(min.z(), bb.min.z())),
        point(Math.max(max.x(), bb.max.x()), Math.max(max.y(), bb.max.y()), Math.max(max.z(), bb.max.z())));
  }

  public BoundingBox extend(Point p) {
    return new BoundingBox(point(Math.min(min.x(), p.x()), Math.min(min.y(), p.y()), Math.min(min.z(), p.z())),
        point(Math.max(max.x(), p.x()), Math.max(max.y(), p.y()), Math.max(max.z(), p.z())));
  }

  public BoundingBox splitLeft() {
    Direction size2 = subtract(divide(subtract(max, zero), 2), divide(subtract(min, zero), 2));
    if (size2.x() >= size2.y() && size2.x() >= size2.z()) {
      return new BoundingBox(min, point(min.x() + size2.x(), max.y(), max.z()));
    } else if (size2.y() >= size2.x() && size2.y() >= size2.z()) {
      return new BoundingBox(min, point(max.x(), min.y() + size2.y(), max.z()));
    } else {
      return new BoundingBox(min, point(max.x(), max.y(), min.z() + size2.z()));
    }
  }

  public BoundingBox splitRight() {
    Direction size2 = subtract(divide(subtract(max, zero), 2), divide(subtract(min, zero), 2));
    if (size2.x() >= size2.y() && size2.x() >= size2.z()) {
      return new BoundingBox(point(min.x() + size2.x(), min.y(), min.z()), max);
    } else if (size2.y() >= size2.x() && size2.y() >= size2.z()) {
      return new BoundingBox(point(min.x(), min.y() + size2.y(), min.z()), max);
    } else {
      return new BoundingBox(point(min.x(), min.y(), min.z() + size2.z()), max);
    }
  }

  public BoundingBox transform(Matrix xform) {
    BoundingBox result = BoundingBox.empty;

    result = result.extend(multiply(xform, min));
    result = result.extend(multiply(xform, point(min.x(), min.y(), max.z())));
    result = result.extend(multiply(xform, point(min.x(), max.y(), min.z())));
    result = result.extend(multiply(xform, point(min.x(), max.y(), max.z())));
    result = result.extend(multiply(xform, point(max.x(), min.y(), min.z())));
    result = result.extend(multiply(xform, point(max.x(), min.y(), max.z())));
    result = result.extend(multiply(xform, point(max.x(), max.y(), min.z())));
    result = result.extend(multiply(xform, max));

    return result;
  }

  public boolean contains(Point v) {
    return min.x() <= v.x() && min.y() <= v.y() && min.z() <= v.z() && max.x() >= v.x() && max.y() >= v.y() && max.z() >= v.z();
  }

  public boolean contains(BoundingBox bb) {
    return min.x() <= bb.min.x() && min.y() <= bb.min.y() && min.z() <= bb.min.z() && max.x() >= bb.max.x() && max.y() >= bb.max.y()
        && max.z() >= bb.max.z();
  }

  //
  // Adapted from
  // https://tavianator.com/cgit/dimension.git/tree/libdimension/bvh/bvh.c
  //
  public boolean intersect(Ray ray) {
    if (this.equals(everything))
      return true;
    if (this.contains(ray.pointAt(ray.tmin())))
      return true;
    if (this.contains(ray.pointAt(ray.tmax())))
      return true;

    double dix = 1.0 / ray.d().x();
    double diy = 1.0 / ray.d().y();
    double diz = 1.0 / ray.d().z();

    double tx1 = (min.x() - ray.origin().x()) * dix;
    double tx2 = (max.x() - ray.origin().x()) * dix;

    double tmin = Math.min(tx1, tx2);
    double tmax = Math.max(tx1, tx2);

    double ty1 = (min.y() - ray.origin().y()) * diy;
    double ty2 = (max.y() - ray.origin().y()) * diy;

    tmin = Math.max(tmin, Math.min(ty1, ty2));
    tmax = Math.min(tmax, Math.max(ty1, ty2));

    double tz1 = (min.z() - ray.origin().z()) * diz;
    double tz2 = (max.z() - ray.origin().z()) * diz;

    tmin = Math.max(tmin, Math.min(tz1, tz2));
    tmax = Math.min(tmax, Math.max(tz1, tz2));

    return (tmax >= tmin && ray.isValid(tmin));
  }

  public Direction size() {
    return subtract(max, min);
  }

  public Point center() {
    return interplolate(max, min, 0.5);
  }

  public BoundingBox scale(double factor) {
    Point c = center();
    return new BoundingBox(add(multiply(factor, subtract(min, c)), c), add(multiply(factor, subtract(max, c)), c));
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof BoundingBox))
      return false;
    if (o == this)
      return true;
    BoundingBox v = (BoundingBox) o;
    return min.equals(v.min) && max.equals(v.max);
  }

  @Override
  public String toString() {
    return String.format("(BBox: %s %s)", min, max);
  }
}
