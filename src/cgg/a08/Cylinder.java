package cgg.a08;

import cgtools.*;

public record Cylinder(Direction position, double radius, double height, Material material) implements Shape {
    @Override
    public Hit intersect(Ray r) {
        /**
         * Quellen:
         * https://de.wikipedia.org/wiki/Zylinder_(Geometrie)
         * https://silo.tips/download/studienarbeit-implementation-eines-raytracers-innerhalb-der-3d-plattform-groimp
         * https://www.math.uni-trier.de//~schulz/prosem0708/Raytracing.pdf
         */

        Point newPointPosition = Vector.subtract(r.x0(), position);

        double a = Math.pow(r.d().x(), 2) + Math.pow(r.d().z(), 2);
        double b = 2 * (newPointPosition.x() * r.d().x() + newPointPosition.z() * r.d().z());
        double c = Math.pow(newPointPosition.x(), 2) + Math.pow(newPointPosition.z(), 2) - Math.pow(radius, 2);

        double discriminant = Math.sqrt(Math.pow(b, 2) - (4 * a * c));

        if (discriminant < 0) {
            return null;
        }

        double t1 = (-b + discriminant) / (2 * a);
        double t2 = (-b - discriminant) / (2 * a);
        double t0 = t1;

        if (t1 > t2) {
            t1 = t2;
        }

        t2 = t0;
        double y1 = newPointPosition.y() + t1 * r.d().y();
        double y2 = newPointPosition.y() + t2 * r.d().y();

        if (y1 < -height) {
            if (y2 < -height)
                return null;
            else {
                double t = t1 + (t2 - t1) * (y1 + height) / (y1 - y2);
                if (!(r.isValid(t))) {
                    return null;
                } else {
                    Point hitPoint = r.pointAt(t);
                    Direction hitNormalDirection = Vector.divide(Vector.subtract(hitPoint, position), radius);
                    return new Hit(t, hitPoint, hitNormalDirection, material);
                }

            }
        }
        if (y1 >= -height && y1 <= height) {
            if (!(r.isValid(t1))) {
                return null;
            } else {
                Point hitVec = r.pointAt(t1);
                Direction hitNormalDirection = Vector.divide(Vector.subtract(hitVec, position), radius);
                return new Hit(t1, hitVec, hitNormalDirection, material);
            }
        }
        if (y1 > height) {
            if (y2 > height) {
                return null;
            } else {
                double t = t1 + (t2 - t1) * (y1 - height) / (y1 - y2);
                if (!(r.isValid(t))) {
                    return null;
                } else {
                    Point hitVec = r.pointAt(t);
                    Direction hitNormalDirection = Vector.divide(Vector.subtract(hitVec, position), radius);
                    return new Hit(t, hitVec, hitNormalDirection, material);
                }
            }
        }
        return null;

    }

}
