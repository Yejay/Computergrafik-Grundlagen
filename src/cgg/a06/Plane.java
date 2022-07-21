package cgg.a06;

import cgtools.*;

public class Plane implements Shape {
    Point anchorPoint;
    Direction normalVector;
    Material material;
    double radius;
    // might not be needed
    Color surfaceColor = Vector.gray;

    public Plane(Point anchorPoint, Direction normalVector, Material material, double radius) {
        this.anchorPoint = anchorPoint;
        this.normalVector = normalVector;
        this.material = material;
        this.radius = radius;
    }

    public Plane(Point anchorPoint, Direction normalVector, Material material) {
        this.anchorPoint = anchorPoint;
        this.normalVector = normalVector;
        this.material = material;
        this.radius = Double.POSITIVE_INFINITY;
    }

    @Override
    public Hit intersect(Ray r) {
        double t = Vector.dotProduct(Vector.subtract(anchorPoint, r.x0()), Vector.normalize(normalVector))
                / Vector.dotProduct(r.d(), Vector.normalize(normalVector));
        if (r.isValid(t)) {
            Point position = r.pointAt(t);
            // Für alle Punkte x auf der Ebene mit Abstand von p kleiner r
            /* |x-p| < r */
            if (Vector.length((Vector.subtract(position, anchorPoint))) < radius) {
                return new Hit(t, position, normalVector, material);
            }
        }
        return null;
    }

}
