package cgg.a11;

import cgtools.*;

public class Cylinder implements Shape {
    Direction position;
    double radius;
    double height;
    Material material;

    public Cylinder(Direction position, double radius, double height, Material material) {
        this.position = position;
        this.radius = radius;
        this.height = height;
        this.material = material;
    }

    @Override
    public Hit intersect(Ray ray) {
        /**
         * Quellen:
         * https://de.wikipedia.org/wiki/Zylinder_(Geometrie)
         * https://silo.tips/download/studienarbeit-implementation-eines-raytracers-innerhalb-der-3d-plattform-groimp
         * https://www.math.uni-trier.de//~schulz/prosem0708/Raytracing.pdf
         */
        Point newPointPosition = Vector.subtract(ray.origin(), position);

        double a = Math.pow(ray.d().x(), 2) + Math.pow(ray.d().z(), 2);
        double b = 2 * (newPointPosition.x() * ray.d().x() + newPointPosition.z() * ray.d().z());
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
        double y1 = newPointPosition.y() + t1 * ray.d().y();
        double y2 = newPointPosition.y() + t2 * ray.d().y();

        if (y1 < -height) {
            if (y2 < -height)
                return null;
            else {
                double t = t1 + (t2 - t1) * (y1 + height) / (y1 - y2);
                if (!(ray.isValid(t))) {
                    return null;
                } else {
                    Point hitPoint = ray.pointAt(t);
                    Direction hitNormalDirection = Vector.divide(Vector.subtract(hitPoint, position), radius);
                    
                    double inclination = Math.acos(hitNormalDirection.y());
                    double azimuth = Math.PI + Math.atan2(hitNormalDirection.x(), hitNormalDirection.z());
                    double u = azimuth / (2 * Math.PI);
                    double v = inclination / Math.PI;
                    // TODO: check if this is correct new Direction(0,0,0) wegen texture mapping uv
                    return new Hit(t, hitPoint, hitNormalDirection, material, new Direction(u, v, 0));
                }

            }
        }
        if (y1 >= -height && y1 <= height) {
            if (!(ray.isValid(t1))) {
                return null;
            } else {
                Point hitVec = ray.pointAt(t1);
                Direction hitNormalDirection = Vector.divide(Vector.subtract(hitVec, position), radius);

                // // TESSSSSSSSSSST
                // Direction normal = Vector.divide(
                //         Vector.subtract(hitVec, new Point(position.x(), position.y() + height / 2, position.z())),
                //         height / 2);
                double inclination = Math.acos(hitNormalDirection.y());
                double azimuth = Math.PI + Math.atan2(hitNormalDirection.x(), hitNormalDirection.z());
                double u = azimuth / (2 * Math.PI);
                double v = inclination / Math.PI;
                // return new Hit(Double.POSITIVE_INFINITY, hitVec, normal, material, new Direction(u, v, 0));
                // //

                // TODO: check if this is correct new Direction(0,0,0) wegen texture mapping uv
                return new Hit(t1, hitVec, hitNormalDirection, material, new Direction(u, v, 0));
            }
        }
        if (y1 > height) {
            if (y2 > height) {
                return null;
            } else {
                double t = t1 + (t2 - t1) * (y1 - height) / (y1 - y2);
                if (!(ray.isValid(t))) {
                    return null;
                } else {
                    Point hitVec = ray.pointAt(t);
                    Direction hitNormalDirection = Vector.divide(Vector.subtract(hitVec, position), radius);

                    // // TESSSSSSSSSSST
                    // Direction normal = Vector.divide(
                    //         Vector.subtract(hitVec, new Point(position.x(), position.y() + height / 2, position.z())),
                    //         height / 2);
                    double inclination = Math.acos(hitNormalDirection.y());
                    double azimuth = Math.PI + Math.atan2(hitNormalDirection.x(), hitNormalDirection.z());
                    double u = azimuth / (2 * Math.PI);
                    double v = inclination / Math.PI;
                    // return new Hit(Double.POSITIVE_INFINITY, hitVec, normal, material, new Direction(u, v, 0));
                    // //

                    // TODO: check if this is correct new Direction(0,0,0) wegen texture mapping uv
                    return new Hit(t, hitVec, hitNormalDirection, material, new Direction(u, v, 0));
                }
            }
        }
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
