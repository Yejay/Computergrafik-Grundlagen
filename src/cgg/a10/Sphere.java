package cgg.a10;

import cgtools.*;

public record Sphere(Point center, double radius, Material material) implements Shape {

    @Override
    public Hit intersect(Ray ray) {
        Direction shift = Vector.subtract(ray.origin(), center);
        // a = d² = d skalar d
        double a = Vector.dotProduct(ray.d(), ray.d());
        // b = 2x0d = 2 skalar (x0 skalar d)
        // double b = 2 * Vector.dotProduct(r.x0(), r.d());
        double b = 2 * Vector.dotProduct(shift, ray.d());
        // x0² - r² = (x0 skalar x0) - r²
        double c = Vector.dotProduct(shift, shift) - Math.pow(radius, 2);

        // t1,2 = -b+-sqrt(b²-4ac) / 2a
        // Nur negativ relevant, da wir den ersten Schnittpunkt mit der Kugel haben
        // wollen
        double discriminant = Math.pow(b, 2) - 4 * a * c;
        double t1 = (-b - Math.sqrt(discriminant)) / 2 * a;
        double t2 = (-b + Math.sqrt(discriminant)) / 2 * a;

        /**
         * Schnitt: Strahl - Kugel 16min
         * kleiner 0 = kein Schnittpunkt mit der Kugel
         * gleich 0 = Ein Schnittpunkt (Tangente mit der Kugel, also am Rand)
         * größer 0 = mehrere Schnittpunkte mit der Kugel
         */

        if (0 <= discriminant) {

            if (ray.isValid(t1) && t1 < t2) {
                Direction n = Vector.divide(Vector.subtract(ray.pointAt(t1), center), radius);
                // TODO: texture mapping
                double inclination = Math.acos(n.y());
                double azimut = Math.PI + Math.atan2(n.x(), n.z());
                double u = azimut / (2 * Math.PI);
                double v = inclination / Math.PI;
                Direction uv = new Direction(u, v, 0);
                //
                return new Hit(t1, ray.pointAt(t1), Vector.normalize(n), material, uv);
            }

            if (ray.isValid(t2) && t2 < t1) {
                Direction n = Vector.divide(Vector.subtract(ray.pointAt(t2), center), radius);
                // TODO: texture mapping
                double inclination = Math.acos(n.y());
                double azimut = Math.PI + Math.atan2(n.x(), n.z());
                double u = azimut / (2 * Math.PI);
                double v = inclination / Math.PI;
                Direction uv = new Direction(u, v, 0);
                //
                return new Hit(t2, ray.pointAt(t2), Vector.normalize(n), material, uv);
            }
        }
        // If there is no hit, return null
        return null;
    }

    @Override
    public BoundingBox bounds() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public int hashCode() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return null;
    }
}
